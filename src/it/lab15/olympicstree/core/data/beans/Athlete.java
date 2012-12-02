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
public class Athlete {

	private String name;
	private String surnName;
	private String fullName;
	private String gender;
	private String countryCode;

	public Athlete(String name, String surnName, String fullName, String gender, String countryCode) {
		super();
		this.name = name;
		this.surnName = surnName;
		this.fullName = fullName;
		this.gender = gender;
		this.countryCode = countryCode;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurnName() {
		return surnName;
	}
	public void setSurnName(String surnName) {
		this.surnName = surnName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
}
