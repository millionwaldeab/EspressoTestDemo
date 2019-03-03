package com.asmarasoftwaresolutions.data_layer.repository;

@FunctionalInterface //Has only one abstarct method
public interface PressureInterface {

    float[] getPressure(String city);
}
