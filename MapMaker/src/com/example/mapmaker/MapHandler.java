package com.example.mapmaker;

import java.util.List;

import android.app.Activity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapHandler {
	private GoogleMap googleMap;
	private Activity main;
	
	
    public MapHandler(Activity main) {
		this.googleMap = googleMap;
		this.main = main;
	}


	public void initializeMap(){
    	if(googleMap == null){
    		googleMap = ((MapFragment) main.getFragmentManager().findFragmentById(R.id.map)).getMap();
    		googleMap.setMyLocationEnabled(true);
    		googleMap.getUiSettings().setMyLocationButtonEnabled(true);
    		
    		if(googleMap == null){
    			Toast.makeText(main.getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
    		}
    		
    	}
    }
	
	/**
	 * centers google map view on given location
	 * @param lat latitude of location
	 * @param lng longitude of location
	 * @param zoom amount of zoom (TODO : figure out what this means)
	 */
    public void goToLocation(double lat, double lng, float zoom){
    	LatLng coord = new LatLng(lat,lng);
    	CameraUpdate update = CameraUpdateFactory.newLatLngZoom(coord,zoom);
    	googleMap.moveCamera(update);
    }
    
    public void markClients(List<Client> clients){
    	LatLng coord;
    	int length = clients.size();
    	Client client;
    	for(int i = 0;i < length;i++){
    		client = clients.get(i);
    		coord = new LatLng(client.getGeoAddress().getLatitude(),client.getGeoAddress().getLongitude());
    		googleMap.addMarker(new MarkerOptions()
    				.position(coord)
    				.title(client.getDateSold())
    				.snippet(client.getLocation()));
    		
    		if(i == length){
    			goToLocation(client.getGeoAddress().getLatitude(),client.getGeoAddress().getLongitude(),Q.DEFAULT_ZOOM);
    		}
    		
    	}
    	
    	
    }
}
