package com.javatechig.droid;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ImageDownladerActivity extends Activity {

	private ImageView downloadedImg;
	private ProgressDialog simpleWaitDialog;
	private String downloadUrl = "http://www.9ori.com/store/media/images/8ab579a656.jpg";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asynch);
		Button imageDownloaderBtn = (Button) findViewById(R.id.downloadButton);

		downloadedImg = (ImageView) findViewById(R.id.imageView);

		imageDownloaderBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new ImageDownloader().execute(downloadUrl);
			}

		});
	}

	private class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... param) {
			// TODO Auto-generated method stub
			return downloadBitmap(param[0]);
		}

		@Override
		protected void onPreExecute() {
			Log.i("Async-Example", "onPreExecute Called");
			simpleWaitDialog = ProgressDialog.show(ImageDownladerActivity.this,"Wait", "Downloading Image");

		}

		@Override
		protected void onPostExecute(Bitmap result) {
			Log.i("Async-Example", "onPostExecute Called");
			downloadedImg.setImageBitmap(result);
			simpleWaitDialog.dismiss();

		}

		private Bitmap downloadBitmap(String url) {
			// initilize the default HTTP client object
			final DefaultHttpClient client = new DefaultHttpClient();
			
			//forming a HttoGet request 
			final HttpGet getRequest = new HttpGet(url);
			try {
				
				HttpResponse response = client.execute(getRequest);

				//check 200 OK for success
				final int statusCode = response.getStatusLine().getStatusCode();

				if (statusCode != HttpStatus.SC_OK) {
					Log.w("ImageDownloader", "Error " + statusCode + " while retrieving bitmap from " + url);
					return null;

				}
				
				final HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream inputStream = null;
					try {
						// getting contents from the stream 
						inputStream = entity.getContent();
						
						// decoding stream data back into image Bitmap that android understands
						final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

						return bitmap;
					} finally {
						if (inputStream != null) {
							inputStream.close();
						}
						entity.consumeContent();
					}
				}
			} catch (Exception e) {
				// You Could provide a more explicit error message for IOException
				getRequest.abort();
				Log.e("ImageDownloader", "Something went wrong while retrieving bitmap from " + url + e.toString());
			} 

			return null;
		}
	}
}
