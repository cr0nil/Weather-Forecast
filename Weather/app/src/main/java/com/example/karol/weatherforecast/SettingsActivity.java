package com.example.karol.weatherforecast;

import android.content.Intent;
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

         Button button = (Button) findViewById(R.id.setBtn);
        final EditText editText = (EditText)findViewById(R.id.editText2);

        //final
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                intent.putExtra("1", s);
                startActivity(intent);
            }
        });


//            intent.setData(Uri.parse("metric"));



       // setResult(RESULT_OK,intent);
    }



}
