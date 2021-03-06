package com.asmarasoftwaresolutions.espressotestdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mCityName;
    TextView mTextMessage, mPressure, mTemperature;
    Button mgetTemp, mGetPres, mUnsubscribe;
    Presenter mPresenter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mPressure = findViewById(R.id.tv_pressure);
        mTemperature = findViewById(R.id.tv_temperature);
        mGetPres = findViewById(R.id.b_get_pressure);
        mgetTemp = findViewById(R.id.b_get_temp);
        mUnsubscribe = findViewById(R.id.b_unsubscribe);
        mGetPres.setOnClickListener(this);
        mgetTemp.setOnClickListener(this);
        mUnsubscribe.setOnClickListener(this);
        mCityName = findViewById(R.id.et_city_name);
        mPresenter = new Presenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_get_pressure:
                if(mCityName.getText() == null) mCityName.setText("");
                mPressure.setText(String.valueOf(mPresenter.getPressure(mCityName.getText().toString())[0]));
                break;
            case R.id.b_get_temp:
                if(mCityName.getText() == null) mCityName.setText("");
                mTemperature.setText(String.valueOf(mPresenter.getTemperature(mCityName.getText().toString())[1]));
                break;
            case R.id.b_unsubscribe:
                mPresenter.unsubscribe();
                mUnsubscribe.setText(R.string.unsubscribed);
                break;

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unsubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }
}
