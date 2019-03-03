package com.asmarasoftwaresolutions.data_layer.repository;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;
import com.asmarasoftwaresolutions.data_layer.Constants;
import com.asmarasoftwaresolutions.data_layer.R;
import com.asmarasoftwaresolutions.data_layer.network.FetchWeatherData;


public class PressureInterfaceImpl implements PressureInterface, SensorEventListener {

    Context mContext;
    private Sensor mSensor;
    private SensorManager mSensorManager;
    public static float[] mPressure;

    public PressureInterfaceImpl(Context context) {
        this.mContext = context;
    }


    public void registerSensorListener(String city) {
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if(mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) !=null) {
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            //Here get city wide request using retrofit
            mPressure = FetchWeatherData.makeWeatherRequest(city, Constants.KEY);
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
    public float[] getPressure(String city) {
        registerSensorListener(city);
        return mPressure;
    }
}
