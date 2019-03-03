package com.asmarasoftwaresolutions.data_layer.repository;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;
import com.asmarasoftwaresolutions.data_layer.Constants;
import com.asmarasoftwaresolutions.data_layer.R;
import com.asmarasoftwaresolutions.data_layer.network.FetchWeatherData;

public class TemperatureInterfaceImpl implements TemperatureInterface, SensorEventListener {

    private Context mContext;
    private Sensor mSensor;
    private SensorManager mSensorManager;
    public static float[] mTemperature;
    private static String TAG = TemperatureInterfaceImpl.class.getCanonicalName();

    public TemperatureInterfaceImpl(Context context) {
        this.mContext = context;
    }

    public void registerSensorListener(String city) {
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        PackageManager packageManager = mContext.getPackageManager();
        if (!packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_AMBIENT_TEMPERATURE)) {//do something}
            //if(mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) !=null)
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            //Here get city wide request using retrofit
            mTemperature = FetchWeatherData.makeWeatherRequest(city, Constants.KEY);
            Log.e(TAG, "Device doesn't support temperature sensor data");
            Toast.makeText(mContext, mContext.getResources().getString(R.string.temperature_error), Toast.LENGTH_LONG).show();
        }
    }

    public void unRegisterSensorListener() {
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        mTemperature = event.values;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public float[] getTemperature(String city) {
        registerSensorListener(city);
        return mTemperature;
    }
}
