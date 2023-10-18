package com.stacktips.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
        mFirebaseAnalytics.setMinimumSessionDuration(20000);

        Button subscribeButton = (Button) findViewById(R.id.button1);
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                String id = "1year";
                String name = "Annual membership subscription";
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
                bundle.putString(FirebaseAnalytics.Param.CURRENCY, "EUR");
                bundle.putString(FirebaseAnalytics.Param.PRICE, "299.00");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            }
        });

    }
}
