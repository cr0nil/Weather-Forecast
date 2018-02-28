package com.example.karol.weatherforecast;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Karol on 24.02.2018.
 */

public class Main {
@SerializedName("temp")
    double temp;

    public Main(double temp) {
        this.temp = temp;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
