/*
* Copyright 1997-2012 SoftSolutions! srl 
* All Rights Reserved. 
* 
* NOTICE: All information contained herein is, and remains the property of SoftSolutions! srl 
* The intellectual and technical concepts contained herein are proprietary to SoftSolutions! srl and 
* may be covered by EU, U.S. and other Foreign Patents, patents in process, and 
* are protected by trade secret or copyright law. 
* Dissemination of this information or reproduction of this material is strictly forbidden 
* unless prior written permission is obtained from SoftSolutions! srl.
* Any additional licenses, terms and conditions, if any, are defined in file 'LICENSE.txt', 
* which may be part of this software package.
*/
 
package it.lab15.olympicstree.core.data.beans;
 
 
/**  
 *
 * Purpose: this class is mainly for ...  
 *
 * Project Name : CSVReader 
 * First created by: marco.bonati 
 * Creation date: 30/nov/2012 
 * 
 **/
public class Edition implements Bean {

	private int id;
	private int year;
	private String hostCity;

	public Edition(int id, int year, String hostCity) {
		super();
		this.id = id;
		this.year = year;
		this.hostCity = hostCity;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHostCity() {
		return hostCity;
	}

	public void setHostCity(String hostCity) {
		this.hostCity = hostCity;
	}


}
