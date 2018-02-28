package com.example.karol.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Karol on 24.02.2018.
 */

public class Forecast {

    CityModel cityModel;

    @SerializedName("pressure")
    String pressure;

    @SerializedName("humidity")
    int humidity;

    //Temperature temp;

    @SerializedName("weather")
    List<Weather> weather;

    @SerializedName("main")
    Temperature temperature;




    @SerializedName("coord")
    Coordinates coord;
    @SerializedName("name")
    String cityCoord;

    //List<Main> main;
    public Forecast(String pressure, int humidity, Temperature temperature, List<Weather> weather, CityModel cityModel, Coordinates coord, String cityCoord) {
        this.pressure = pressure;
        this.humidity = humidity;
        this.temperature = temperature;
        this.weather = weather;
        this.cityModel = cityModel;
        this.coord = coord;
        this.cityCoord = cityCoord;
    }


    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public String getCityCoord() {
        return cityCoord;
    }

    public void setCityCoord(String cityCoord) {
        this.cityCoord = cityCoord;
    }

    public CityModel getCityModel() {
        return cityModel;
    }

    public void setCityModel(CityModel cityModel) {
        this.cityModel = cityModel;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }


    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

}
