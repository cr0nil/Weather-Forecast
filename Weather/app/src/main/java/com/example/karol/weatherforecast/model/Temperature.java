package com.example.karol.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Karol on 24.02.2018.
 */

public class Temperature {
    @SerializedName("temp")
    double temp;


    public double getTemp() {
        return temp;
    }
}
