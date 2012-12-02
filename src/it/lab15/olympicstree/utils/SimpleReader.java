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
 
package it.lab15.olympicstree.utils;

import java.io.FileReader;

import au.com.bytecode.opencsv.CSVReader;
 
 
/**  
 *
 * Purpose: this class is mainly for ...  
 *
 * Project Name : CSVReader 
 * First created by: marco.bonati 
 * Creation date: 30/nov/2012 
 * 
 **/
public class SimpleReader {

	public static void main(String[] args){
		 CSVReader reader;
		try {
			reader = new CSVReader(new FileReader("data/OlympicsOpenData.csv"));
		    String [] nextLine;
		    while ((nextLine = reader.readNext()) != null) {
		        // nextLine[] is an array of values from the line
		        System.out.println(nextLine[0] +" " + nextLine[1] + " etc...");
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
