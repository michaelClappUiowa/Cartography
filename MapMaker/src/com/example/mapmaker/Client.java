package com.example.mapmaker;

import android.location.Address;

/**
 * @author Julio 
 *
 */
public class Client {
	//geoAddress is the geolocated address object
	//contains latitude/longitude and locality name
	private Address geoAddress;
	
	private String customerName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String dateSold;
	
	
	
	/**
	 * returns filled client object, geoAddress not included
	 * @param customerName
	 * @param address
	 * @param city
	 * @param zip
	 * @param phone
	 * @param dateSold
	 */
	public Client(String customerName, String address, String city, String zip,
			String phone, String dateSold) {

		this.customerName = customerName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.dateSold = dateSold;
	}

	/**
	 * default constructor, returns empty client object
	 */
	public Client(){
		
	}
	


	/**
	 * TODO : should handle case where address, city or zip are not given, either here or in csvhandler
	 * @return the address along with city and zip
	 */
	public String getLocation(){
		return address + " " + city + " " + zip;
	}
	/**
	 * @return the customerName, format not guaranteed
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the dateSold
	 */
	public String getDateSold() {
		return dateSold;
	}

	/**
	 * @param dateSold the dateSold to set
	 */
	public void setDateSold(String dateSold) {
		this.dateSold = dateSold;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Client [customerName=" + customerName + ", address=" + address
				+ ", city=" + city + ", zip=" + zip + "]";
	}


	/**
	 * @return the geoAddress
	 */
	public Address getGeoAddress() {
		return geoAddress;
	}

	/**
	 * @param geoAddress the geoAddress to set
	 */
	public void setGeoAddress(Address geoAddress) {
		this.geoAddress = geoAddress;
	}
	
	
}
