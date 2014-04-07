package com.javatechig.regemail;

import java.util.ArrayList;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ArrayList<Item> list = null;
	private ListView listView;
	private LovelyListAdapter listadaptor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		list = getData();
		listView = (ListView) findViewById(R.id.listView1);
		listadaptor = new LovelyListAdapter(this, R.layout.lovely_view_layout, list);
		listView.setAdapter(listadaptor);
	}

	private ArrayList<Item> getData() {
		ArrayList<Item> accountsList = new ArrayList<Item>();
		
		//Getting all registered Google Accounts;
		try {
			Account[] accounts = AccountManager.get(this).getAccountsByType("com.google");
			for (Account account : accounts) {
				Item item = new Item( account.type, account.name);
				accountsList.add(item);
			}
		} catch (Exception e) {
			Log.i("Exception", "Exception:" + e);
		}

		
		//For all registered accounts;
		/*try {
			Account[] accounts = AccountManager.get(this).getAccounts();
			for (Account account : accounts) {
				Item item = new Item( account.type, account.name);
				accountsList.add(item);
			}
		} catch (Exception e) {
			Log.i("Exception", "Exception:" + e);
		}*/
		return accountsList;
	}
}
