package com.asmarasoftwaresolutions.espressotestdemo.rx_java;

import android.util.Log;
import io.reactivex.observers.DisposableObserver;

public class PressureObserver extends DisposableObserver<float[]> {
    private static String TAG = PressureObserver.class.getCanonicalName();



    @Override
    public void onNext(float[] pressure) {

    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
