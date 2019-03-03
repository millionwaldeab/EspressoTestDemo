package com.asmarasoftwaresolutions.data_layer.repository;

@FunctionalInterface
public interface TemperatureInterface {
    float[] getTemperature(String city);
}
