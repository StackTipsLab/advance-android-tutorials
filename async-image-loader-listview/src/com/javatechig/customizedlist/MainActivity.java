package com.javatechig.customizedlist;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import com.example.customizedlist.R;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayList<NewsItem> image_details = getListData();
		final ListView lv1 = (ListView) findViewById(R.id.custom_list);
		lv1.setAdapter(new CustomListAdapter(this, image_details));
		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Object o = lv1.getItemAtPosition(position);
				NewsItem newsData = (NewsItem) o;
				Toast.makeText(MainActivity.this, "Selected :" + " " + newsData,
						Toast.LENGTH_LONG).show();
			}

		});

	}

	private ArrayList<NewsItem> getListData() {
		ArrayList<NewsItem> results = new ArrayList<NewsItem>();
		NewsItem newsData = new NewsItem();
		newsData.setHeadline("Dance of Democracy");
		newsData.setReporterName("Pankaj Gupta");
		newsData.setDate("May 26, 2013, 13:35");
		newsData.setUrl("http://lh5.ggpht.com/_hepKlJWopDg/TB-_WXikaYI/AAAAAAAAElI/715k4NvBM4w/s144-c/IMG_0075.JPG");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setHeadline("Major Naxal attacks in the past");
		newsData.setReporterName("Pankaj Gupta");
		newsData.setDate("May 26, 2013, 13:35");
		newsData.setUrl("http://lh4.ggpht.com/_4f1e_yo-zMQ/TCe5h9yN-TI/AAAAAAAAXqs/8X2fIjtKjmw/s144-c/IMG_1786.JPG");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setHeadline("BCCI suspends Gurunath pending inquiry ");
		newsData.setReporterName("Rajiv Chandan");
		newsData.setDate("May 26, 2013, 13:35");
		newsData.setUrl("http://lh3.ggpht.com/_GEnSvSHk4iE/TDSfmyCfn0I/AAAAAAAAF8Y/cqmhEoxbwys/s144-c/_MG_3675.jpg");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setHeadline("Life convict can`t claim freedom after 14 yrs: SC");
		newsData.setReporterName("Pankaj Gupta");
		newsData.setDate("May 26, 2013, 13:35");
		newsData.setUrl("http://lh6.ggpht.com/_ZN5zQnkI67I/TCFFZaJHDnI/AAAAAAAABVk/YoUbDQHJRdo/s144-c/P9250508.JPG");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setHeadline("Indian Army refuses to share info on soldiers mutilated at LoC");
		newsData.setReporterName("Pankaj Gupta");
		newsData.setDate("May 26, 2013, 13:35");
		newsData.setUrl("http://lh4.ggpht.com/_XjNwVI0kmW8/TCOwNtzGheI/AAAAAAAAC84/SxFJhG7Scgo/s144-c/0014.jpg");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setHeadline("French soldier stabbed; link to Woolwich attack being probed");
		newsData.setReporterName("Sudeep Nanda");
		newsData.setDate("May 26, 2013, 13:35");
		newsData.setUrl("http://lh6.ggpht.com/_Nsxc889y6hY/TBp7jfx-cgI/AAAAAAAAHAg/Rr7jX44r2Gc/s144-c/IMGP9775a.jpg");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setHeadline("Life convict can`t claim freedom after 14 yrs: SC");
		newsData.setReporterName("Pankaj Gupta");
		newsData.setDate("May 26, 2013, 13:35");
		newsData.setUrl("http://lh6.ggpht.com/_ZN5zQnkI67I/TCFFZaJHDnI/AAAAAAAABVk/YoUbDQHJRdo/s144-c/P9250508.JPG");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setHeadline("Dance of Democracy");
		newsData.setReporterName("Pankaj Gupta");
		newsData.setDate("May 26, 2013, 13:35");
		newsData.setUrl("http://lh5.ggpht.com/_hepKlJWopDg/TB-_WXikaYI/AAAAAAAAElI/715k4NvBM4w/s144-c/IMG_0075.JPG");
		results.add(newsData);

		return results;
	}
}
