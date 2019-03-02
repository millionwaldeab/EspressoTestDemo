package com.asmarasoftwaresolutions.data_layer.repository;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

import com.asmarasoftwaresolutions.data_layer.R;

public class PressureInterfaceImpl implements PressureInterface, SensorEventListener {

    Context mContext;
    private Sensor mSensor;
    private SensorManager mSensorManager;
    public static float[] mPressure;

    public PressureInterfaceImpl(Context context) {
        this.mContext = context;
    }

    public void setUp() {
        registerSensorListener();
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
    }

    public void registerSensorListener() {
        if(mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) !=null) {
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(mContext, mContext.getResources().getString(R.string.temperature_error), Toast.LENGTH_LONG).show();
        }
    }

    public void unRegisterSensorListener() {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        mPressure = event.values;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public float[] getPressure() {
        return mPressure;
    }
}
