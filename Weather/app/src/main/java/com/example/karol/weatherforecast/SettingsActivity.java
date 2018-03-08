package com.example.karol.weatherforecast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {

    public static final String UNITS = "com.example.karol.weatherforecast";
    RadioButton c;
    RadioButton k;
    RadioButton f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        c = (RadioButton) findViewById(R.id.rbCelsius);
        f = (RadioButton) findViewById(R.id.rbFahrenheit);
        k = (RadioButton) findViewById(R.id.rbKelvin);
        SharedPreferences settings = getSharedPreferences("Answers", 0);
        boolean answerA = settings.getBoolean("questionA", false); // The second argument is a default value, if value with name "questionA" will not be found
        boolean answerB = settings.getBoolean("questionB", false);
        boolean answerC = settings.getBoolean("questionC", false);
        if(answerA==true){
            c.setChecked(true);
        }
        else if(answerB==true){
            f.setChecked(true);
        }
        else if(answerC==true){
            k.setChecked(true);
        }


        Button button = (Button) findViewById(R.id.setBtn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(SettingsActivity.this, MainActivity.class);
                SharedPreferences settings = getSharedPreferences("Answers", 0); // first argument is just a name of your SharedPreferences which you want to use. It's up to you how you will name it, but you have to use the same name later when you want to retrieve data.
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("questionA", c.isChecked()); // first argument is a name of a data that you will later use to retrieve it and the second argument is a value that will be stored
                editor.putBoolean("questionB", f.isChecked());
                editor.putBoolean("questionC", k.isChecked());

                editor.commit();

                startActivity(intent2);
            }
        });


    }




}
