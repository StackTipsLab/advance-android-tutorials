package com.javatechig.todo;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
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

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to);

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
		else if (id == R.id.export_records) {
			Cursor cursor = dbcon.fetch();
			
			exportToExcel(cursor);
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void exportToExcel(Cursor cursor) {
		
		final String fileName = "TodoList.xls";
		
		//Saving file in external storage
		File sdCard = Environment.getExternalStorageDirectory();
		
		File directory = new File(sdCard.getAbsolutePath() + "/javatechig.todo");
		
		//create directory if not exist
		if(!directory.isDirectory()){
			directory.mkdirs();	
		}
		
		//file path
		File file = new File(directory, fileName);
		
		WorkbookSettings wbSettings = new WorkbookSettings();
		wbSettings.setLocale(new Locale("en", "EN"));
		
		WritableWorkbook workbook;
		
		try {
			workbook = Workbook.createWorkbook(file, wbSettings);
			
			//Excel sheet name. 0 represents first sheet
			WritableSheet sheet = workbook.createSheet("MyShoppingList", 0);
			
			//Supply row and column index
			Label label0 = new Label(0, 0, "Subject");
			Label label1 = new Label(1, 0, "Description");

			try {
				sheet.addCell(label0);
				sheet.addCell(label1);
				
				if (cursor.moveToFirst()) {
					do {
						String title = cursor.getString(cursor.getColumnIndex(DBhelper.TODO_SUBJECT));
						String desc = cursor.getString(cursor.getColumnIndex(DBhelper.TODO_DESC));

						int i = cursor.getPosition() + 1;
						
						sheet.addCell(new Label(0, i, title));
						sheet.addCell(new Label(1, i, desc));
					} while (cursor.moveToNext());
				}
				
				//closing cursor
				cursor.close();
					
			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
			
			workbook.write();
			
			try {
				workbook.close();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
