package com.example.gridview;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class AddEvent extends ActionBarActivity {

	private int hour;
    private int minute;
    private DatePicker dpResult;
	private TimePicker timePicker;
	private int year;
	private int month;
	private int day;
	static final int DATE_DIALOG_ID = 999;
	static final int TIME_DIALOG_ID = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_event);

	    Button redirectBtn = (Button) findViewById(R.id.redirectButton);
	    Button setTime = (Button) findViewById(R.id.timeDue);
	    Button setDate = (Button) findViewById(R.id.dateDue);
        setTime.setOnClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog(TIME_DIALOG_ID);
			}
		});
        
setDate.setOnClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
			}
		});
	    redirectBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_event, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_add_event,
					container, false);
			return rootView;
		}
	}

	@Override
	
	    protected Dialog onCreateDialog(int id) {

	        if (id == TIME_DIALOG_ID){
	
	       
	
	            // set time picker as current time
	
	            return new TimePickerDialog(this, timePickerListener, hour, minute,false);	
	        }
	        
	        else if (id == DATE_DIALOG_ID){
			   // set date picker as current date
			   return new DatePickerDialog(this, datePickerListener, 
	                         year, month,day);
			
	        }
	        return null;
	
	}
	
	 
	
	    private TimePickerDialog.OnTimeSetListener timePickerListener =  new TimePickerDialog.OnTimeSetListener() {
	
	        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
	
	            hour = selectedHour;
	
	            minute = selectedMinute;
	
	 
	
	            // set current time into textview
	
	        
	
	            // set current time into timepicker
	
	            timePicker.setCurrentHour(hour);
	
	            timePicker.setCurrentMinute(minute);
	
	 
	
	        }
	
	    };
	    
	    private static String padding_str(int c) {
	    	
	    	        if (c >= 10)
	    	
	    	           return String.valueOf(c);
	    	
	    	        else
	    	
	    	           return "0" + String.valueOf(c);
	    	
	    	    }
	    
	    private DatePickerDialog.OnDateSetListener datePickerListener 
        = new DatePickerDialog.OnDateSetListener() {

// when dialog box is closed, below method will be called.
public void onDateSet(DatePicker view, int selectedYear,
		int selectedMonth, int selectedDay) {
	year = selectedYear;
	month = selectedMonth;
	day = selectedDay;



	// set selected date into datepicker also
	dpResult.init(year, month, day, null);

}
};


}
