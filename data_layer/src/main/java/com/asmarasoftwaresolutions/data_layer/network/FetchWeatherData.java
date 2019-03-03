package com.asmarasoftwaresolutions.data_layer.network;

import com.asmarasoftwaresolutions.data_layer.network.client.WeatherClient;
import com.asmarasoftwaresolutions.data_layer.network.response.WeatherReponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FetchWeatherData {
    private static float[] resultArray;
    /**
     * This makes a web request from https://home.openweathermap.org to get data by the city.
     */
    public static float[] makeWeatherRequest(String city, String key) {
        //Obtain an instance of Retrofit by calling the static method.
        Retrofit retrofit = WeatherClient.getRetrofitClient();
        /*
        The main purpose of Retrofit is to create HTTP calls from the Java interface based on the annotation associated with each method. This is achieved by just passing the interface class as parameter to the create method
        */
        WeatherData weatherData = retrofit.create(WeatherData.class);
        /*
        Invoke the method corresponding to the HTTP request which will return a Call object. This Call object will used to send the actual network request with the specified parameters
        */
        Call call = weatherData.getWeatherByCity(city, key);
        /*
        This is the line which actually sends a network request. Calling enqueue() executes a call asynchronously. It has two callback listeners which will invoked on the main thread
        */
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                if (response.body() != null) {
                    WeatherReponse wResponse = (WeatherReponse) response.body();
                    float pres = Float.parseFloat(wResponse.getMain().getPressure().toString());
                    float temp = Float.parseFloat(wResponse.getMain().getTemp().toString());
                    float result[] = new float[]{pres, temp};
                    resultArray = result;
                    //responseText.setText("Temp: " + wResponse.getMain().getTemp() + "\n " +
                            //"Humidity: " + wResponse.getMain().getHumidity() + "\n" +
                            //"Pressure: " + wResponse.getMain().getPressure());
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                /*
                Error callback
                */
            }
        });

        return resultArray;
    }
}
