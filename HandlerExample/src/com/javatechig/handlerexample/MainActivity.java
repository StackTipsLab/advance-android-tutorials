package com.javatechig.handlerexample;

import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.javatechige.handlerexample.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    
    private ProgressDialog progressDialog;
    private ImageView imageView;
    private String url = "http://www.9ori.com/store/media/images/8ab579a656.jpg";
    private Bitmap bitmap = null;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        imageView = (ImageView) findViewById(R.id.imageView);
        
        Button start = (Button) findViewById(R.id.button1);
        start.setOnClickListener(new OnClickListener() {

        	@Override
            public void onClick(View arg0) {
        		progressDialog = ProgressDialog.show(MainActivity.this,
        				"", "Loading..");
        		new Thread() {
        			public void run() {
        				bitmap = downloadBitmap(url);
        				messageHandler.sendEmptyMessage(0);
        			}
        		}.start();
        	
            }
        });
    }
 
    private Handler messageHandler = new Handler() {
        public void handleMessage(Message msg) {
        	super.handleMessage(msg);
        	imageView.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    };
    
    private Bitmap downloadBitmap(String url) {
        // Initialize the default HTTP client object
        final DefaultHttpClient client = new DefaultHttpClient();

        //forming a HttoGet request
        final HttpGet getRequest = new HttpGet(url);
        try {

            HttpResponse response = client.execute(getRequest);

            //check 200 OK for success
            final int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                Log.w("ImageDownloader", "Error " + statusCode +
                		" while retrieving bitmap from " + url);
                return null;
            }

            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream is = null;
                try {
                    // getting contents from the stream
                    is = entity.getContent();

                    // decoding stream data back into image Bitmap
                    final Bitmap bitmap = BitmapFactory.decodeStream(is);

                    return bitmap;
                } finally {
                    if (is != null) {
                        is.close();
                    }
                    entity.consumeContent();
                }
            }
        } catch (Exception e) {
            getRequest.abort();
            Log.e(getString(R.string.app_name), "Error "+ e.toString());
        }

        return null;
    }
}