package com.example.mapmaker;

//this class holds constants
//and cool gadgets
public class Q {
	
	//map related constants
    public static final float DEFAULT_ZOOM = 15;
    
    //file read related constants
	public static String FILE_SEPARATOR = "%";
    public static final String FILE_NAME = "example.csv";
    public static final String PERSIST_FILE_SEPARATOR = ",";
    public static final String PERSIST_FILE_NAME = "clients.csv";
    
    //expected column numbers for data in input csvs
    public static int CUSTOMER_NAME = 1;
    public static int ADDRESS = 2;
    public static int CITY = 3;
    public static int ZIP = 4;
    public static int PHONE = 5;
    public static int DATE_SOLD = 7;
    
    //column numbers for data in persistent csv
    public static int PERSIST_CUSTOMER_NAME = 1;
    public static int PERSIST_ADDRESS = 2;
    public static int PERSIST_CITY = 3;
    public static int PERSIST_ZIP = 4;
    public static int PERSIST_PHONE = 5;
    public static int PERSIST_DATE_SOLD = 6;
    public static int PERSIST_LATITUDE = 7;
    public static int PERSIST_LONGITUDE = 8;
    

}
