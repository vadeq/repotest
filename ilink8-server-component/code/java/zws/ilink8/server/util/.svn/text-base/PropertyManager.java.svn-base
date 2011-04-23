package zws.ilink8.server.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import javax.servlet.http.HttpServletRequest;
import wt.util.WTProperties;

public class PropertyManager{ 
	private static String PROPETY_FILE = null;
	static {
		try{
			PROPETY_FILE = WTProperties.getLocalProperties().getProperty("wt.home") + "/zwsresource/pdmresource.properties";
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//----------------------------------------------------------------------------------------------------
	public void updateProperty(HttpServletRequest request) throws Exception {
		Enumeration keys = request.getParameterNames();
		HashMap hashMap = new HashMap();
		while(keys.hasMoreElements()){			
			String key = (String) keys.nextElement();
			key = key.toUpperCase();			
			if(key.startsWith("ZWS")){
				String value = (String) request.getParameter(key);
				hashMap.put(key.toUpperCase(), value);
			}
		}
		writeToPropertyFile(hashMap);
	}

	//----------------------------------------------------------------------------------------------------
	
	public void writeToPropertyFile(HashMap propertyMap) throws Exception {
		Properties properties = new Properties();	
		FileInputStream fileInputStream = new FileInputStream(PROPETY_FILE);
		properties.load(fileInputStream);

		Iterator iterator = propertyMap.keySet().iterator();
		while(iterator.hasNext()){
			String key = (String) iterator.next();
			String value = (String) propertyMap.get(key);
			properties.setProperty(key, value);
		}
		
		FileOutputStream fileOutputStream = new FileOutputStream(PROPETY_FILE);
		properties.store(fileOutputStream, null);
		fileOutputStream.close();		
	}
	
	//----------------------------------------------------------------------------------------------------
	
	public static String getPropertyValue(String key, String defaultValue) throws Exception {
		PropertyResourceBundle resourceBundle = new PropertyResourceBundle(new FileInputStream(PROPETY_FILE)); 
		 try {
			 return (String)resourceBundle.getObject(key); 
		 }
		 catch(Exception e){
			 if(defaultValue != null) { return defaultValue; } 
			 throw new Exception(e);
		 }		
	}		
	//----------------------------------------------------------------------------------------------------
	public static String getPropertyValue(String key) throws Exception {
		return getPropertyValue(key, null);
	}		
}