package com.example.karol.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Karol on 24.02.2018.
 */

public class Weather {
    @SerializedName("main")
    String main;
    @SerializedName("description")
    String description;


    public Weather(String main, String description) {
        this.main = main;
        this.description = description;
    }


    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
