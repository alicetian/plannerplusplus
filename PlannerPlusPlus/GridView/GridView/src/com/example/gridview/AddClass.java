package com.example.gridview;

import java.util.List;

import com.example.model.Classes;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class AddClass extends ActionBarActivity {

	private EditText addClass;
	private EditText addDesc;
	private EditText addTime;
	private EditText addDate;
	private DBAdapterAddClass db;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_class);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0000ff")));
		db = new DBAdapterAddClass(this);
		db.open();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_class, menu);
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
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_add_class,
					container, false);
			return rootView;
		}
	}
	
	public void addClass(View view){
		
		addClass = (EditText) findViewById(R.id.editText1);
	    addDesc = (EditText) findViewById(R.id.editText2);
	    addTime = (EditText) findViewById(R.id.timeOfClass);
	    addDate = (EditText) findViewById(R.id.daysOfClass);
	    String addClass1 = addClass.getText().toString();
	    String addDesc1 = addDesc.getText().toString();
	    String addTime1 = addTime.getText().toString();
	    String addDate1 = addDate.getText().toString();
	    
		
		switch(view.getId()) {
		case R.id.button1:

			// insertUser() method will insert a user and return a row ID
			long id = db.insertClass(addClass1, addDesc1, addDate1, addTime1);
			
			// if the row ID is -1 there was some error, otherwise it was successful
			if (id != -1)
				displayMessage(addClass1 + " inserted!");
			else
				displayMessage(addClass1 + " wasn't inserted?"); 
			
			db.close();

			break;
		
	}
		
		
		finish();
		
	    
		
	}

	private void displayMessage(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

}
