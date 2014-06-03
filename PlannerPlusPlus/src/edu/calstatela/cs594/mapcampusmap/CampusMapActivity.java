package edu.calstatela.cs594.mapcampusmap;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.calstatela.cs594.plannerplusplus.R;
import edu.calstatela.cs594.data.*;

public class CampusMapActivity extends Activity implements OnMapClickListener {
	// Coordinates used for centering the Map

//		private static final double CAMERA_LNG = -118.166;
//		private static final double CAMERA_LAT = 34.067;
	private static final double CAMERA_LNG = -118.168;
	private static final double CAMERA_LAT = 34.012;

	private int idx = 0;
		// The Map Object
		private GoogleMap mMap;
		String[] idlist = {"ETA220", "ETA332", "Student Union"};
		double[] latlist = {34.066686, 34.066397, 34.068245};
		double[] lonlist = {-118.166363, -118.166169, -118.168956};
		List<ClassRoom> places;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.mapfrag);
			
			places = new ArrayList<ClassRoom>();
			for(int i = 0; i < idlist.length; i++)
				places.add(new ClassRoom(idlist[i], latlist[i], lonlist[i]));
			
			mMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			// Add a marker for every place
			for(ClassRoom rec : places) {
				mMap.addMarker(new MarkerOptions()

				// Set the Marker's position
				.position(new LatLng(rec.getLat(), rec.getLon()))

				// Set the title of the Marker's information window
				.title(rec.getName()));
			}// Center the map 
			// Should compute map center from the actual data
			
/*			
			mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(
					places.get(idx).getLat(), places.get(idx).getLon())));
			mMap.animateCamera( CameraUpdateFactory.zoomTo( 18.0f ) );
			
*/
			//			mMap.animateCamera(CameraUpdateFactory.zoomIn());

			mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(
					places.get(idx).getLat(), places.get(idx).getLon()) , 18.0f) );

			/*

			CameraPosition cameraPosition = new CameraPosition.Builder().target(
	                new LatLng(places.get(idx).getLat(), places.get(idx).getLon())).zoom(18).build();
			
			mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			
			
			mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			mMap.getUiSettings().setMyLocationButtonEnabled(true);
			*/
			mMap.setMyLocationEnabled(false); // false to disable
			mMap.setOnMapClickListener(this);
		}

		@Override
		public void onMapClick(LatLng arg0) {
			// TODO Auto-generated method stub
			mMap.addMarker(new MarkerOptions()

			// Set the Marker's position
			.position(arg0)

			// Set the title of the Marker's information window
			.title(arg0.toString()));

			
		}
		

		
}
