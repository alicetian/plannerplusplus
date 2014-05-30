package com.example.gridview;


import java.util.List;

import com.example.model.Classes;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
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
		android.app.ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0000ff")));
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
				
				Classes classes = (Classes)parent.getAdapter().getItem(position);
			    Intent intent = new Intent(ViewClasses.this, EditClass.class);
			    intent.putExtra("class_id", classes.getId());
			    intent.putExtra("class_name", classes.getClassName());
			    intent.putExtra("class_desc", classes.getDescription());
			    intent.putExtra("class_times", classes.getTime());
			    intent.putExtra("class_days", classes.getDays());
			    startActivity(intent);
			    
			    
			}
		});

	}
	public void onResume(){
		super.onResume();
		db.open();
		classSchedule = db.getAllClasses();
		
		setListAdapter(new ArrayAdapter<Classes>(this, R.layout.list_classes, classSchedule));
		
		
	}
	protected void onPause() {
	    super.onPause();
	    	
	    db.close();
	  }

}