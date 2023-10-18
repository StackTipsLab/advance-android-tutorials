package com.javatechig.regemail;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LovelyListAdapter extends ArrayAdapter<Item> {
	private List<Item> appsList = null;
	private Context context;

	public LovelyListAdapter(Context context, int textViewResourceId, List<Item> appsList) {
		super(context, textViewResourceId, appsList);
		this.context = context;
		this.appsList = appsList;
	}

	@Override
	public int getCount() {
		return ((null != appsList) ? appsList.size() : 0);
	}

	@Override
	public Item getItem(int position) {
		return ((null != appsList) ? appsList.get(position) : null);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (null == view) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.lovely_view_layout, null);
		}
		
		if (position % 2 == 1) {
		    view.setBackgroundColor(context.getResources().getColor(R.color.lovely_row_bg1));  
		} else {
		    view.setBackgroundColor(context.getResources().getColor(R.color.lovely_row_bg2));  
		}
		

		Item data = appsList.get(position);
		if (null != data) {
			
			TextView appName = (TextView) view.findViewById(R.id.key);
			TextView packageName = (TextView) view.findViewById(R.id.value);

			appName.setText(data.getKey());
			packageName.setText(data.getValue());
		}
		return view;
	}
}