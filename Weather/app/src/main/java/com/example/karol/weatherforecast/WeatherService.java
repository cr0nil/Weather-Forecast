package com.example.karol.weatherforecast;

import com.example.karol.weatherforecast.model.CityModel;
import com.example.karol.weatherforecast.model.Forecast;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Karol on 24.02.2018.
 */
public class WeatherService {
    @SerializedName("city")
    private CityModel city;

    
    private CityModel cityModel;


    @SerializedName("list")
    private List<Forecast> forecast;

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel cityName) {
        this.city = cityName;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public CityModel getCityModel() {
        return cityModel;
    }

    public void setCityModel(CityModel cityModel) {
        this.cityModel = cityModel;
    }

    public WeatherService(CityModel city, List<Forecast> forecast, CityModel cityModel) {
this.cityModel = cityModel;
        this.city = city;
        this.forecast = forecast;
    }
}

