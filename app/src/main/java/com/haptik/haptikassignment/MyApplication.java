package com.haptik.haptikassignment;

import android.app.Application;

/**
 * Created by yogeshmadaan on 11/05/16.
 */
public class MyApplication extends Application {
    private MyApplication _instance;

    @Override
    public void onCreate() {
        super.onCreate();
        set_instance(this);
    }

    public MyApplication get_instance() {
        return _instance;
    }

    public void set_instance(MyApplication _instance) {
        this._instance = _instance;
    }
}
