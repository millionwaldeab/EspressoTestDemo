package com.asmarasoftwaresolutions.espressotestdemo.rx_java;

import android.util.Log;
import io.reactivex.observers.DisposableObserver;

public class TemperatureObserver extends DisposableObserver<float[]> {

    public static String TAG = TemperatureObserver.class.getCanonicalName();

    @Override
    public void onNext(float[] temp) {

    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
