package com.example.karol.weatherforecast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karol.weatherforecast.adapter.ExpandableAdapter;
import com.example.karol.weatherforecast.api.ApiClient;
import com.example.karol.weatherforecast.api.ApiService;
import com.example.karol.weatherforecast.model.CityModel;
import com.example.karol.weatherforecast.model.Forecast;
import com.example.karol.weatherforecast.model.Temperature;
import com.example.karol.weatherforecast.model.Weather;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "6c1b1944e53ff6202e68e84bd70c6910";
    private final static String MODE = "json";
    private final static String UNITS = "metric";
    private final static String CNT = "1"; //default=10 max =50
    private DatabaseReference reference;
    private ExpandableListView expandableListView;
    private ExpandableAdapter expandableAdapter;
    private List<String> listDat;
    private HashMap<String, List<String>> listHashMap;
    final String[] x = new String[10];
    final List<String> cityList = new ArrayList<>();
    final List<String> fav = new ArrayList<>();

    // 24api ---> data-time
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reference = FirebaseDatabase.getInstance().getReference();
        TextView timeV = (TextView) findViewById(R.id.timeView);
        String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());

        timeV.setText(currentDateTimeString);
        final EditText editText = (EditText) findViewById(R.id.editText);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        intiData();
        expandableAdapter = new ExpandableAdapter(listDat, this, listHashMap);
        expandableListView.setAdapter(expandableAdapter);
        final Intent intent = getIntent();


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                final String selected = (String) expandableAdapter.getChild(
                        groupPosition, childPosition);
                fav.add(selected);

                weatherInfo(selected, setUnits());
                
                Toast.makeText(MainActivity.this, selected, Toast.LENGTH_LONG).show();
                return true;
            }
        });
        Button getWaether = (Button) findViewById(R.id.showBtn);
        getWaether.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //forecastInfo();
                final String qqCity = editText.getText().toString();

                if (qqCity.equals("")) {
                    coordInfo(setUnits());
                } else
                    weatherInfo(qqCity, setUnits());

            }


        });


        Button forecas = (Button) findViewById(R.id.forecastBtn);
        forecas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
                final String qqCity = editText.getText().toString();

                intent1.putExtra("2", qqCity);
                startActivity(intent1);
            }
        });
        Toast.makeText(MainActivity.this, setUnits(), Toast.LENGTH_LONG).show();
    }

    public String setUnits() {
        String globalUnits;
        SharedPreferences settings = getSharedPreferences("Answers", 0);
        boolean answerA = settings.getBoolean("questionA", false);
        boolean answerB = settings.getBoolean("questionB", false);
        boolean answerC = settings.getBoolean("questionC", false);

        if (answerA == true) {
            globalUnits = "Metric";
        } else if (answerB == true) {
            globalUnits = "Imperial";
        } else {
            globalUnits = "Default";
        }
        //Toast.makeText(MainActivity.this, answerA + " " + answerB + " " + answerC+" ---" +globalUnits, Toast.LENGTH_LONG).show();
        return globalUnits;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent, 0);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

//                Toast.makeText(this,data.getData().toString(),Toast.LENGTH_LONG).show();

    }


    // wstawianie do expandableList
    private void intiData() {
        listDat = new ArrayList<>();
        listHashMap = new HashMap<>();
        listDat.add("City");
        // cityList.add("Berlin");

        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //showData(dataSnapshot);
                cityList.clear();
                int i = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    // CityModel cityModel = new CityModel();
                    String s = ds.getValue(String.class);
                    // i++;
                    cityList.add(s);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //  listHashMap.put(listDat.get(0),cityList);
        listHashMap.put(listDat.get(0), cityList);
    }


    public void weatherInfo(final String favCity, final String units) {
        ApiService service = ApiClient.getClient().create(ApiService.class);


        //  Toast.makeText(MainActivity.this, units, Toast.LENGTH_LONG).show();
        Call<Forecast> call = service.getWeather(favCity, MODE, units, API_KEY);
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                Temperature temperature = response.body().getTemperature();
                List<Weather> weathers = response.body().getWeather();
                String m = weathers.get(0).getMain();
                String d = weathers.get(0).getDescription();

                double temp = temperature.getTemp();
                final TextView mView = (TextView) findViewById(R.id.mainView);
                final TextView dView = (TextView) findViewById(R.id.descView);
                final TextView tempView = (TextView) findViewById(R.id.tempView);
                dView.setText(d);
                mView.setText(m);

                tempView.setText(Double.toString(temp));
                cityy(favCity);


            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                //TODO
            }
        });


    }

    public void cityy(String fCity) {
        EditText editText = (EditText) findViewById(R.id.editText);
        final String qqCity = editText.getText().toString();
        final TextView cityV = (TextView) findViewById(R.id.textView2);

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<CityModel> call = service.getWeather2(fCity, MODE, UNITS, API_KEY);

        call.enqueue(new Callback<CityModel>() {
            @Override
            public void onResponse(Call<CityModel> call, Response<CityModel> response) {
                //int i=0;
                String city = response.body().getName();
                int id = response.body().getId();
                String ids = Integer.toString(id);
                x[0] = ids;
                cityV.setText(city);

            }

            @Override
            public void onFailure(Call<CityModel> call, Throwable t) {
                //TODO
            }
        });

        Button sendData = (Button) findViewById(R.id.add);

        sendData.setOnClickListener(new View.OnClickListener() {
            // int k = 0;

            @Override
            public void onClick(View v) {
                reference.child(x[0]).setValue(cityV.getText().toString());
                //if click
                //  k++;
            }
        });
    }

    public void coordInfo(String qUnits) {
        final EditText latVal = (EditText) findViewById(R.id.editlat);
        final EditText lonVal = (EditText) findViewById(R.id.editLon);
        double lat = Double.parseDouble(latVal.getText().toString());
        double lon = Double.parseDouble(lonVal.getText().toString());
        final TextView mianView = (TextView) findViewById(R.id.mainView);
        final TextView tempView = (TextView) findViewById(R.id.tempView);
        final TextView descView = (TextView) findViewById(R.id.descView);
        final TextView cityView = (TextView) findViewById(R.id.textView2);

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<WeatherService> call = service.getWeather3(lat, lon, MODE, qUnits, CNT, API_KEY);
        call.enqueue(new Callback<WeatherService>() {
            @Override
            public void onResponse(Call<WeatherService> call, Response<WeatherService> response) {
                //CityModel city = response.body().getCity();

                List<Forecast> forecast = response.body().getForecast();
                double temp = forecast.get(0).getTemperature().getTemp();
                List<Weather> weather = forecast.get(0).getWeather();
                String cityCoord = forecast.get(0).getCityCoord();
                String main = weather.get(0).getMain();
                String desc = weather.get(0).getDescription();
                cityView.setText(cityCoord);
                mianView.setText(main);

                tempView.setText(Double.toString(temp));
                descView.setText(desc);
            }

            @Override
            public void onFailure(Call<WeatherService> call, Throwable t) {
                //todo
            }
        });
    }


}
