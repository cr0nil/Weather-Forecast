package com.example.karol.weatherforecast.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Karol on 24.02.2018.
 */

public class ApiClient {

    private static String baseURL = "http://api.openweathermap.org/data/2.5/";


    public static Retrofit getClient() {

           Retrofit retrofit = new Retrofit.Builder().baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }
}
