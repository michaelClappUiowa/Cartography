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
    private static final float DEFAULT_ZOOM = 15;
    private static final String FILE_NAME = "test.csv";
    
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
    private void goToLocation(double lat, double lng, float zoom){
    	LatLng ll = new LatLng(lat,lng);
    	CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll,zoom);
    	googleMap.moveCamera(update);
    }
    
    public String getLocationCSV(){
    	BufferedReader in = null;
    	try{
	    	File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
	    	File file = new File(path,FILE_NAME);
	    	String location = "";
    	
  
			in = new BufferedReader(new FileReader(file));
			
			/**
			String line = "";
			while( (line = in.readLine()) != null){
				
			}
			**/
			String line = in.readLine();
			if(line != null){
				location = line.split(",")[1];
			}
			
			return location;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, FILE_NAME + " not found in Downloads", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	return "";
    }
    
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
    
    boolean fileExists() {
        // Create a path where we will place our picture in the user's
        // public pictures directory and check if the file exists.  If
        // external storage is not currently mounted this will think the
        // picture doesn't exist.
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS);

        File file = new File(path, FILE_NAME);
        return file.exists();
    }
    public void geoLocate(View v){
    	try{
    		//hideSoftKeyboard(v);
    		//EditText et = (EditText) findViewById(R.id.editText1);
    		//String location = et.getText().toString();
    		
    		if(isExternalStorageReadable() && fileExists()){
	    		String location = getLocationCSV();
	    		Geocoder gc = new Geocoder(this);
	    	
	    		List<Address> listAdd;
			
				listAdd = gc.getFromLocationName(location,1);
				Address add = listAdd.get(0);
				String locality = add.getLocality();
				
				Toast.makeText(this, locality, Toast.LENGTH_LONG).show();
				
				double lat = add.getLatitude();
				double lng = add.getLongitude();
				
				
				goToLocation(lat,lng,DEFAULT_ZOOM);
    		}
    		else{
    			
    			String eMsg = "Can't find " + FILE_NAME + " in Downloads";
    			if(!isExternalStorageReadable()){eMsg = "Can't read external storage";}
    			
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
    private void initilizeMap(){
    	if(googleMap == null){
    		googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
    		
    		if(googleMap == null){
    			Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
    		}
    		
    	}
    }
    

    @Override
    protected void onResume(){
    	super.onResume();
    	initilizeMap();
    }
}