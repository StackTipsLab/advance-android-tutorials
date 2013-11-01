package com.javatechig.listallfiles;

import java.io.File;
import java.util.ArrayList;

import com.example.listallfiles.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private File root;
	private ArrayList<File> fileList = new ArrayList<File>();
	private LinearLayout view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		view = (LinearLayout) findViewById(R.id.view);

		//getting SDcard root path
		root = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath());
		getfile(root);

		for (int i = 0; i < fileList.size(); i++) {
			TextView textView = new TextView(this);
			textView.setText(fileList.get(i).getName());
			textView.setPadding(5, 5, 5, 5);

			System.out.println(fileList.get(i).getName());

			if (fileList.get(i).isDirectory()) {
				textView.setTextColor(Color.parseColor("#FF0000"));
			}
			view.addView(textView);
		}

	}

	public ArrayList<File> getfile(File dir) {
		File listFile[] = dir.listFiles();
		if (listFile != null && listFile.length > 0) {
			for (int i = 0; i < listFile.length; i++) {

				if (listFile[i].isDirectory()) {
					fileList.add(listFile[i]);
					getfile(listFile[i]);

				} else {
					if (listFile[i].getName().endsWith(".png")
							|| listFile[i].getName().endsWith(".jpg")
							|| listFile[i].getName().endsWith(".jpeg")
							|| listFile[i].getName().endsWith(".gif"))

					{
						fileList.add(listFile[i]);
					}
				}

			}
		}
		return fileList;
	}

}
