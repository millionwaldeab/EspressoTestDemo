package com.asmarasoftwaresolutions.data_layer.rx_android;


import android.content.Context;
import com.asmarasoftwaresolutions.data_layer.repository.PressureInterface;
import com.asmarasoftwaresolutions.data_layer.repository.PressureInterfaceImpl;
import io.reactivex.Observable;
import io.reactivex.Observer;

public class PressureObservable extends Observable<float[]> {

    private static Context mContext;
    private static Observable<float[]> mPressureObservable;
    private static PressureInterface mPressureInterface = new PressureInterfaceImpl(mContext);

    public PressureObservable(PressureInterface pressureInterface) {
        this.mPressureInterface = pressureInterface;
    }

    public static Observable<float[]> getPressureObservable(){
        mPressureObservable = Observable.fromArray(mPressureInterface.getPressure());
        return mPressureObservable;
    }

    @Override
    protected void subscribeActual(Observer observer) {

    }
}
