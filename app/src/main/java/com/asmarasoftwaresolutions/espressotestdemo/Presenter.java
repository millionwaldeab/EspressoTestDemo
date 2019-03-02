package com.asmarasoftwaresolutions.espressotestdemo;

import android.content.Context;
import com.asmarasoftwaresolutions.data_layer.repository.PressureInterface;
import com.asmarasoftwaresolutions.data_layer.repository.PressureInterfaceImpl;
import com.asmarasoftwaresolutions.data_layer.repository.TemperatureInterface;
import com.asmarasoftwaresolutions.data_layer.repository.TemperatureInterfaceImpl;
import com.asmarasoftwaresolutions.data_layer.rx_android.PressureObservable;
import com.asmarasoftwaresolutions.data_layer.rx_android.TemperatureObservable;
import com.asmarasoftwaresolutions.espressotestdemo.rx_java.PressureObserver;
import com.asmarasoftwaresolutions.espressotestdemo.rx_java.TemperatureObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Presenter {

    private static Context mContext;
    private static String TAG = Presenter.class.getCanonicalName();

    private TemperatureInterface mTemperatureInterface = new TemperatureInterfaceImpl(mContext);
    private PressureInterface mPressureInterface = new PressureInterfaceImpl(mContext);
    private DisposableObserver<float[]> mTempertatureObserver;
    private DisposableObserver<float[]> mPressureObserver;
    private Observable<float[]> mTempertatureObservable;
    private Observable<float[]> mPressureObservable;
    private CompositeDisposable mDisposable = new CompositeDisposable();

    public float[] getPressure(){
        subscribePressure();
        return mPressureInterface.getPressure();
    }

    public float[] getTemperature(){
        subscribeTemperature();
        return mTemperatureInterface.getTemperature();
    }

    public void subscribePressure(){
        mPressureObservable = new PressureObservable(mPressureInterface);
        mPressureObserver = new PressureObserver();

        mDisposable.add(mPressureObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(mPressureObserver));
    }

    public void subscribeTemperature(){
        mTempertatureObserver = new TemperatureObserver();
        mTempertatureObservable = new TemperatureObservable(mTemperatureInterface);

        mDisposable.add(mTempertatureObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(mTempertatureObserver));


    }

    public void unsubscribe(){
        mDisposable.clear();
    }
}
