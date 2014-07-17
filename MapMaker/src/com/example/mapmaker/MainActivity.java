package com.example.mapmaker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;



public class MainActivity extends Activity {
	
    //private GoogleMap googleMap;
    private MapHandler mapHandler;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
    	mapHandler = new MapHandler(this);
    	try{
    		mapHandler.initializeMap();
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
/**
 * onClick method : geolocates and marks clients from csv
 * @param v View layout object
 */
    public void geoLocate(View v){
    	try{
    		hideSoftKeyboard(v);
    		//EditText et = (EditText) findViewById(R.id.editText1);
    		//String location = et.getText().toString();
    		
    		if(CSVHandler.isExternalStorageReadable() && CSVHandler.fileExists()){
    			List<Client> clients = CSVHandler.getClients();
    			List<Client> failed = new ArrayList<Client>();
    			List<Client> success = new ArrayList<Client>();
    			
	    		String location = "";
	    		Geocoder gc = new Geocoder(this);
	    	
	    		List<Address> listAdd; 
	    		Address addr;
			
	    		for(Client client : clients){
	    			try{
	    				location = client.getLocation();
	    			
	    				listAdd = gc.getFromLocationName(location,1);
						addr = listAdd.get(0);
						client.setGeoAddress(addr);
						
						Log.d("client's name", client.getCustomerName());
						Log.d("geoLocate", addr.getLocality());
						
						success.add(client);

	    			} catch (IOException e){
	    				failed.add(client);
	    				e.printStackTrace();
	    			}
					 

	    		}
	    		
	    		Log.d("failed clients",failed.toString());
				
				mapHandler.markClients(success);
    		}
    		else{
    			
    			String eMsg = "Can't find " + Q.FILE_NAME + " in Downloads";
    			if(!CSVHandler.isExternalStorageReadable()){eMsg = "Can't read external storage";}
    			
    			Toast.makeText(this, eMsg, Toast.LENGTH_LONG).show();
    		}
		} catch (Exception e){
			e.printStackTrace();
		}
    	
    	
    }
    
    /**
     * hides the keyboard
     * @param v View layout object
     */
    private void hideSoftKeyboard(View v){
    	InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    	imm.hideSoftInputFromWindow(v.getWindowToken(),0);
    }
    
    

    @Override
    protected void onResume(){
    	super.onResume();
    	//
    	try {
			mapHandler.initializeMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}