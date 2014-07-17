package com.example.mapmaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;


public class CSVHandler {
	
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
    
    public static boolean fileExists() {
    	// checks to see if file exists
        // If external storage is not currently mounted this will think the
        // picture doesn't exist.
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS);

        File file = new File(path, Q.FILE_NAME);
        return file.exists();
    }
    
    /**
     * reads the csv and creates a list of clients
     * @return a list of client objects representing the given csv
     */
	   public static List<Client> getClients(){
	    	BufferedReader in = null;
	    	try{
	    		
		    	File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
		    	File file = new File(path,Q.FILE_NAME);
	    	
	  
				in = new BufferedReader(new FileReader(file));
				

				String line = "";
				String[] data;
				List<Client> clients = new ArrayList<Client>();
				Client client;
				
				while( (line = in.readLine()) != null){
					data = line.split(Q.FILE_SEPARATOR);
					client = new Client(data[Q.CUSTOMER_NAME],data[Q.ADDRESS], data[Q.CITY], data[Q.ZIP], data[Q.PHONE], data[Q.DATE_SOLD]);
					clients.add(client);
				}
				
				
				return clients;
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//Toast.makeText(this, Q.FILE_NAME + " not found in Downloads", Toast.LENGTH_LONG).show();
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
	    	//returns empty, must return something
	    	return new ArrayList<Client>();
	    }
	    
}
