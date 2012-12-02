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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
 
/**  
 *
 * Purpose: this class is mainly for ...  
 *
 * Project Name : CSVReader 
 * First created by: marco.bonati 
 * Creation date: 30/nov/2012 
 * 
 **/
public class Country implements Bean {

	private static final Logger LOG = LoggerFactory.getLogger(Country.class);

	private String countryCode;
	private String description;

	public Country(String countryCode, String description) {
		super();
		this.countryCode = countryCode;
		this.description = description;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
