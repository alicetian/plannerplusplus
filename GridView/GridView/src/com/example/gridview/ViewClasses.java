package com.example.gridview;


import java.util.List;

import com.example.model.Classes;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ViewClasses extends ListActivity {

	
	
	private DBAdapterAddClass db;
	private static List<Classes> classSchedule;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = new DBAdapterAddClass(this);
		db.open();
		
		classSchedule = db.getAllClasses();
		
		setListAdapter(new ArrayAdapter<Classes>(this, R.layout.list_classes, classSchedule));

		ListView listView = getListView();
		//listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
			    
			}
		});

	}

}