package com.asmarasoftwaresolutions.data_layer.rx_android;

import android.content.Context;

import com.asmarasoftwaresolutions.data_layer.repository.TemperatureInterface;
import com.asmarasoftwaresolutions.data_layer.repository.TemperatureInterfaceImpl;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class TemperatureObservable extends Observable<float[]> {

    private static Context mContext;
    private static TemperatureInterface mTempretureInterface = new TemperatureInterfaceImpl(mContext);
    private static Observable<float[]> mTempreatureObservable;

    public TemperatureObservable(TemperatureInterface temperatureInterface) {
        this.mTempretureInterface = temperatureInterface;
    }

    public static Observable<float[]> getTemperatureObservable(){
        mTempreatureObservable = Observable.fromArray(mTempretureInterface.getTemperature(""));
        return mTempreatureObservable;
    }

    @Override
    protected void subscribeActual(Observer observer) {

    }
}
