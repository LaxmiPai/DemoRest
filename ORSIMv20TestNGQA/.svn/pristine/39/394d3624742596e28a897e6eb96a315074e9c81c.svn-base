package com.oracle.core.utils;

import static com.oracle.core.utils.Constants.TEST_PROPERTIES;
import static com.oracle.core.utils.Constants.APPLICATION_PROPERTIES;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;



public class PropertyReader {
	
	/*Code to fetch value from Application Property */
	public String getApplicationproperty(String sKey) throws IOException {
		Properties props = new Properties();
		String sFilePath = System.getProperty("user.dir");
		sFilePath = sFilePath + File.separator + APPLICATION_PROPERTIES;
		FileInputStream fs = new FileInputStream(sFilePath);
		String sVal = "";
		try {
			props.load(fs);
			sVal = props.getProperty(sKey);
			if (sVal == "") {
				return null;
			}
		} catch (IOException e) {
			
		} finally {
			fs.close();
		}
		return sVal;
	}
	
	
public void updateTestproperty(String key,String value ) throws IOException {
		
		Properties props = new Properties();
		String sFilePath = System.getProperty("user.dir");
		sFilePath = sFilePath + File.separator + TEST_PROPERTIES;
		
		FileInputStream in = new FileInputStream(sFilePath);
		
		props.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream(sFilePath);
		props.setProperty(key, value);
		props.store(out, null);
		out.close();

		
		
	}


public String getTestproperty(String sKey) throws IOException {
	Properties props = new Properties();
	String sFilePath = System.getProperty("user.dir");
	sFilePath = sFilePath + File.separator + TEST_PROPERTIES;
	FileInputStream fs = new FileInputStream(sFilePath);
	String sVal = "";
	try {
		props.load(fs);
		sVal = props.getProperty(sKey);
		if (sVal == "") {
			return null;
		}
	} catch (IOException e) {
	} finally {
		fs.close();
	}
	return sVal;
}
	
	
}
	

