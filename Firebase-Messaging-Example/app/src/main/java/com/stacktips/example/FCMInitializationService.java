package com.stacktips.example;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by npanigrahy on 13/08/2016.
 */
public class FCMInitializationService extends FirebaseInstanceIdService {
    private static final String TAG = "FCMInitializationService";

    @Override
    public void onTokenRefresh() {
        String fcmToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "FCM Device Token:" + fcmToken);
        //Save or send FCM registration token
    }
}