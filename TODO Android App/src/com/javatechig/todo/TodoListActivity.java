package com.javatechig.todo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class TodoListActivity extends ActionBarActivity {
	private SQLController dbcon;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_emp_list);

		dbcon = new SQLController(this);
		dbcon.open();
		listView = (ListView) findViewById(R.id.list_view);
		listView.setEmptyView(findViewById(R.id.empty));
		// Attach The Data From DataBase Into ListView Using Crusor Adapter
		Cursor cursor = dbcon.fetch();
		String[] from = new String[] { DBhelper._ID, DBhelper.TODO_SUBJECT,  DBhelper.TODO_DESC};
		int[] to = new int[] { R.id.id, R.id.title, R.id.desc };
		

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor,
				from, to);

		adapter.notifyDataSetChanged();
		listView.setAdapter(adapter);

		// OnCLickListiner For List Items
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
				TextView id_tv = (TextView) view.findViewById(R.id.id);
				TextView title_tv = (TextView) view.findViewById(R.id.title);
				TextView desc_tv = (TextView) view.findViewById(R.id.desc);

				String id = id_tv.getText().toString();
				String title = title_tv.getText().toString();
				String desc = desc_tv.getText().toString();

				Intent modify_intent = new Intent(getApplicationContext(), ModifyTodoActivity.class);
				modify_intent.putExtra("title", title);
				modify_intent.putExtra("desc", desc);
				modify_intent.putExtra("id", id);
				startActivity(modify_intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.add_record) {
			Intent add_mem = new Intent(this, AddTodoActivity.class);
			startActivity(add_mem);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
