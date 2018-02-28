package com.example.karol.weatherforecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Karol on 23.02.2018.
 */

public class CityModel {
    @SerializedName("id")
     private int id;

     @SerializedName("name")
     private String name;

     public CityModel(int id, String name) {
         this.id = id;
         this.name = name;
     }

    public CityModel() {

    }

    public int getId() {

              return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
              return name;
            }

            public void setName(String name) {
                this.name = name;
            }
 }
