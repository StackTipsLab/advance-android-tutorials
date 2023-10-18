package com.stacktips.example;

import android.app.Application;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by npanigrahy on 13/08/2016.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseInstanceId.getInstance().getToken();
    }
}
