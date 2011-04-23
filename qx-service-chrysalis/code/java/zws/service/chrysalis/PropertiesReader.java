/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Sep 28, 2007 7:42:05 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.service.chrysalis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesReader  {

  private Properties properties;
  private File propertiesFile;
  
  public PropertiesReader() {
    this.properties = null;
    this.propertiesFile = null;
  }
    
  public PropertiesReader (File propertiesFile) {    
    this.properties = null;
    this.propertiesFile = propertiesFile;
  }  
  
  public void setPropertiesFile(File propertiesFile) {
    this.propertiesFile = propertiesFile;     
  }
    
  private File getPropertiesFile() {
    return this.propertiesFile;
  }
  
  public void setProperties(Properties properties) {
    this.properties = properties;  
  }

  public Properties getProperties() {
    return this.properties;
  }
 
  
  public java.util.Properties fetchProperties() throws FileNotFoundException, IOException {
    File pFile;   
    FileInputStream pfis;
    java.util.Properties props = new Properties();
    try {
        pFile = getPropertiesFile(); 
      
        if (null == pFile ) {
          {} //System.out.println("Chrysalis Properties Reader has Null file!");
        }
        else if (!pFile.canRead()) {
          {} //System.out.println("Chrysalis Properties Reader cannot read properties file - " + pFile.getPath() + "!!");
        }
        else {
        pfis = new FileInputStream(pFile);

        props.load(pfis);
        setProperties(props);

//        {} //System.out.println("");
//        {} //System.out.println("====== PROPPERTIES ===============");      
//        printProperties(); 
//        {} //System.out.println("=======END PROPERTIES===============");
//        {} //System.out.println("");
        
        pfis.close();
        }
  
      } catch (FileNotFoundException e) {
        {} //System.out.println(e.toString());
        e.printStackTrace();
        throw e;
      } catch (IOException e) {
        {} //System.out.println(e.toString());
        e.printStackTrace();
        throw e;
      }
      return props;      
   }  
 
  private void printProperties() {
    Enumeration names = properties.propertyNames();
    
    String name = null;
    while( names.hasMoreElements()) {
       name = (String) names.nextElement();
       {} //System.out.println(name + " = " + properties.getProperty(name));    
    }
    
  }
  
}
