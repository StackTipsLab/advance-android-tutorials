package com.javatechig.feedreader;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		
		webView = (WebView) findViewById(R.id.webView);
		webView.setWebViewClient(new MyWebViewClient());

		Bundle bundle = this.getIntent().getExtras();
		String url = bundle.getString("url");

		if (null != url) {
			webView.getSettings().setJavaScriptEnabled(true);
			webView.loadUrl(url);
		}
	}

	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

}
