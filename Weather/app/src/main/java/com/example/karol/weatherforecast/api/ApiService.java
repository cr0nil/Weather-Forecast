package com.example.karol.weatherforecast.api;


import com.example.karol.weatherforecast.model.CityModel;
import com.example.karol.weatherforecast.model.Forecast;
import com.example.karol.weatherforecast.WeatherService;


import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Query;

/**
 * Created by Karol on 23.02.2018.
 */

public interface ApiService {

    @GET("forecast")
    Call<WeatherService> getTrends(@Query("q") String city,
                                   @Query("mode") String mode,
                                   @Query("units") String units,
                                   @Query("cnt") String cnt,

                                   @Query("appid") String appId

    );
    @GET("weather")
    Call<Forecast> getWeather(@Query("q") String city,
                              @Query("mode") String mode,
                              @Query("units") String units,
                              @Query("appid") String appId);
    @GET("weather")
    Call<CityModel> getWeather2(@Query("q") String city,
                                @Query("mode") String mode,
                                @Query("units") String units,
                                @Query("appid") String appId);
    @GET("find")
    Call<WeatherService> getWeather3(@Query("lat") double lat,
                                @Query("lon") double lon,
                                @Query("mode") String mode,
                                @Query("units") String units,
                                @Query("cnt") String cnt,
                                @Query("appid") String appId);
}