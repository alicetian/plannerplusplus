package com.example.gridview;

import com.example.model.Classes;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class EditClass extends ActionBarActivity {

	private EditText editClass;
	private EditText editDesc;
	private EditText editTime;
	private EditText editDays;
	private String className;
	private int classId;
	private String classDesc;
	private String classTime;
	private String classDays;
	private DBAdapterAddClass db;
	public static final int passedId = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_class);
		db = new DBAdapterAddClass(this);
		db.open();
		editClass = (EditText) findViewById(R.id.editText12);
	    editDesc = (EditText) findViewById(R.id.editText22);
	    editTime = (EditText) findViewById(R.id.timeOfClass1);
	    editDays = (EditText) findViewById(R.id.daysOfClass1);
		
		Intent intent = getIntent();
        className = intent.getStringExtra("class_name");
        classId = intent.getIntExtra("class_id", passedId);
        classDesc = intent.getStringExtra("class_desc");
        classTime = intent.getStringExtra("class_times");
        classDays = intent.getStringExtra("class_days");
        
        editClass.setText(className);
        editDesc.setText(classDesc);
        editTime.setText(classTime);
        editDays.setText(classDays);
        

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_class, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
public void updateClass(View view){
		
		editClass = (EditText) findViewById(R.id.editText12);
	    editDesc = (EditText) findViewById(R.id.editText22);
	    editTime = (EditText) findViewById(R.id.timeOfClass1);
	    editDays = (EditText) findViewById(R.id.daysOfClass1);
	    String addClass1 = editClass.getText().toString();
	    String addDesc1 = editDesc.getText().toString();
	    String addTime1 = editTime.getText().toString();
	    String addDate1 = editDays.getText().toString();
	    Classes classes = new Classes(classId, addClass1, addDesc1, addTime1, addDate1);
		
		switch(view.getId()) {
		case R.id.button11:

			// insertUser() method will insert a user and return a row ID
			long id = db.updateClass(classes);
			
			// if the row ID is -1 there was some error, otherwise it was successful
			if (id != -1)
				displayMessage(addClass1 + " has been changed!");
			else
				displayMessage(addClass1 + " was not changed?"); 
			
			db.close();

			break;
		
	}
		
		
		finish();
		
	    
		
	}
	
	private void displayMessage(String msg) {
	Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
}
}
