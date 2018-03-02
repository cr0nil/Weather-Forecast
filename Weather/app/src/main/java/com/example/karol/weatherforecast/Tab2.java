package com.example.karol.weatherforecast;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karol.weatherforecast.api.ApiClient;
import com.example.karol.weatherforecast.api.ApiService;
import com.example.karol.weatherforecast.model.CityModel;
import com.example.karol.weatherforecast.model.Forecast;
import com.example.karol.weatherforecast.model.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Karol on 28.02.2018.
 */

public class Tab2 extends Fragment {
    public static final String API_KEY = "6c1b1944e53ff6202e68e84bd70c6910";
    private final static String MODE = "json";
    private final static String UNITS = "metric";
    private final static String CNT = "16";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);
        final TextView cityView = (TextView) view.findViewById(R.id.cityTxtView);
        final TextView tempView = (TextView) view.findViewById(R.id.tempTxtView);
        ApiService service = ApiClient.getClient().create(ApiService.class);
        TextView timeV = (TextView) view.findViewById(R.id.day1View);
        timeV.setText("pojutrze");
        String strtext = getArguments().getString("edttext");
        Toast.makeText(getContext(),strtext,Toast.LENGTH_SHORT).show();
//        String unit = intent.getStringExtra("1");
       // String qqCity = "rzeszów";
        Call<WeatherService> call = service.getTrends(strtext, MODE, UNITS, CNT, API_KEY);
        call.enqueue(new Callback<WeatherService>() {
            @Override
            public void onResponse(Call<WeatherService> call, Response<WeatherService> response) {
                CityModel city = response.body().getCity();
                String cityName = city.getName();
                List<Forecast> forecast = response.body().getForecast();
                double temp = forecast.get(15).getTemperature().getTemp();

                // int humidity = forecast.get(0).getHumidity();
                //  List<Main> mains = forecast.get(0).getMain();
                // double t = mains.get(0).getTemp();
                //DecimalFormat df = new DecimalFormat("0.#");
                List<Weather> weather = forecast.get(15).getWeather();
                String main = weather.get(0).getMain();
                String desc = weather.get(0).getDescription();
                cityView.setText(cityName);
//             //   mianView.setText(main);
                tempView.setText(Double.toString(temp));

//                descView.setText(desc);

            }

            @Override
            public void onFailure(Call<WeatherService> call, Throwable t) {
                //todo
            }
        });
        //forecastInfo();
        return view;
    }
}
