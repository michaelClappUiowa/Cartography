package com.example.mapmaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;


public class MainActivity extends Activity {
	
    private GoogleMap googleMap;
    //constants
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
    	
    	try{
    		initilizeMap();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    private void initilizeMap(){
    	if(googleMap == null){
    		googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
    		googleMap.setMyLocationEnabled(true);
    		googleMap.getUiSettings().setMyLocationButtonEnabled(true);
    		
    		if(googleMap == null){
    			Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
    		}
    		
    	}
    }
    
    private void goToLocation(double lat, double lng, float zoom){
    	LatLng coord = new LatLng(lat,lng);
    	CameraUpdate update = CameraUpdateFactory.newLatLngZoom(coord,zoom);
    	googleMap.moveCamera(update);
    }
    
 

    public void geoLocate(View v){
    	try{
    		//hideSoftKeyboard(v);
    		//EditText et = (EditText) findViewById(R.id.editText1);
    		//String location = et.getText().toString();
    		
    		if(CSVHandler.isExternalStorageReadable() && CSVHandler.fileExists()){
	    		String location = CSVHandler.getLocationCSV();
	    		Geocoder gc = new Geocoder(this);
	    	
	    		List<Address> listAdd;
			
				listAdd = gc.getFromLocationName(location,1);
				Address add = listAdd.get(0);
				String locality = add.getLocality();
				
				Toast.makeText(this, locality, Toast.LENGTH_LONG).show();
				
				double lat = add.getLatitude();
				double lng = add.getLongitude();
				
				
				goToLocation(lat,lng,Q.DEFAULT_ZOOM);
    		}
    		else{
    			
    			String eMsg = "Can't find " + Q.FILE_NAME + " in Downloads";
    			if(!CSVHandler.isExternalStorageReadable()){eMsg = "Can't read external storage";}
    			
    			Toast.makeText(this, eMsg, Toast.LENGTH_LONG).show();
    		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "IOException: " + e.getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
    	
    	
    }
    
    private void hideSoftKeyboard(View v){
    	InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    	imm.hideSoftInputFromWindow(v.getWindowToken(),0);
    }
    
    

    @Override
    protected void onResume(){
    	super.onResume();
    	initilizeMap();
    }
}