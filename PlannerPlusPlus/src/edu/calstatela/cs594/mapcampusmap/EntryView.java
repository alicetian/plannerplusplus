package edu.calstatela.cs594.mapcampusmap;



import edu.calstatela.cs594.plannerplusplus.R;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EntryView extends Fragment{
	
	public interface OnItemAddedListener{
		public void onLocationEntered(String name, Double lat, Double lon);
	}
	
	OnItemAddedListener mCallback;
	Button enterButton;
	EditText nameText;
	EditText latText;
	EditText lonText;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view =  inflater.inflate(R.layout.entry_classroom_layout, container, true );
		
		enterButton = (Button)view.findViewById(R.id.enterButton);
		nameText = (EditText)view.findViewById(R.id.nameText);
		latText = (EditText)view.findViewById(R.id.latText);
		lonText = (EditText)view.findViewById(R.id.lonText);
		
		enterButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				String name = nameText.getText().toString();
				Double lat = Double.parseDouble(latText.getText().toString());
				Double lon = Double.parseDouble(lonText.getText().toString());

				mCallback.onLocationEntered(name, lat, lon);
				
			}});
		
		
		return view;
		
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		   try {
	            mCallback = (OnItemAddedListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement OnHeadlineSelectedListener");
	        }

		
		
	}
	
	
	
	

}
