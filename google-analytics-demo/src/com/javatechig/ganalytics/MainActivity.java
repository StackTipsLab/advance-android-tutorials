package com.javatechig.ganalytics;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.StandardExceptionParser;

public class MainActivity extends Activity {

	private EasyTracker easyTracker = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		easyTracker = EasyTracker.getInstance(MainActivity.this);

		findViewById(R.id.trackEvent).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				easyTracker.send(MapBuilder.createEvent("your_action",
						"envet_name", "button_name/id", null).build());
			}
		});

		findViewById(R.id.trackCrash).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				try {
					int a[] = new int[2];
					int num = a[4];
				} catch (ArrayIndexOutOfBoundsException e) {
					easyTracker.send(MapBuilder.createException(
									new StandardExceptionParser(MainActivity.this, null)
											.getDescription(Thread.currentThread().getName(), e), false).build());
				}

			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
