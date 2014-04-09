package com.zipp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	String inputPath = Environment.getExternalStorageDirectory().getPath()+ "/ZipDemo/";
	String inputFile = "Apply.zip";
	String outputPath = Environment.getExternalStorageDirectory().getPath()+ "/UnZipDemo/";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		((Button) findViewById(R.id.button_zip))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View view) {
						// declare an array for storing the files i.e the path
						// of your source files
						String[] s = new String[2];

						// Type the path of the files in here
						s[0] = inputPath + "/image.jpg";
						s[1] = inputPath + "/textfile.txt"; // /sdcard/ZipDemo/textfile.txt

						// first parameter is d files second parameter is zip
						// file name
						ZipManager zipManager = new ZipManager();

						// calling the zip function
						zipManager.zip(s, inputPath + inputFile);
					}
				});

		((Button) findViewById(R.id.button_unzip))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View view) {
						ZipManager zipManager = new ZipManager();
						zipManager.unzip(inputPath + inputFile, outputPath);
					}
				});

		//moveFile(inputPath, inputFile, outputPath);

	}

	private void moveFile(String inputPath, String inputFile, String outputPath) {

		InputStream in = null;
		OutputStream out = null;
		try {
			// create output directory if it doesn't exist
			File dir = new File(outputPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			in = new FileInputStream(inputPath + inputFile);
			out = new FileOutputStream(outputPath + inputFile);

			byte[] buffer = new byte[1024];
			int read;
			while ((read = in.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}
			in.close();
			in = null;

			// write the output file
			out.flush();
			out.close();
			out = null;

			// delete the original file
			new File(inputPath + inputFile).delete();
		}

		catch (FileNotFoundException fnfe1) {
			Log.e("tag", fnfe1.getMessage());
		} catch (Exception e) {
			Log.e("tag", e.getMessage());
		}
	}
}