package com.asmarasoftwaresolutions.data_layer.network;

import com.asmarasoftwaresolutions.data_layer.network.response.WeatherReponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherData {
    /*
   Get request to fetch city weather.Takes in two parameter-city name and API key.
   */
    @GET("/data/2.5/weather")
    Call<WeatherReponse> getWeatherByCity(@Query("q") String city, @Query("sample_demo") String apiKey);

}
