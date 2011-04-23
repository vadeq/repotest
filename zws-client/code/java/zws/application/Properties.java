package zws.application; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.KeyValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

public class Properties {

  public static void add(String field, String value) {
    set(field, value);
    {} //System.out.println("Property : " + field + "=" + get(field));
  }
  public static void add(KeyValue pair) {
    String value="";
    if (null!= pair.getValue()) value=pair.getValue().toString();
    set(pair.getKey(), value);
    {} //System.out.println("Property : " + pair.getKey() + "=" + get(pair.getKey()));
  }
  
  public static void addFilePath(KeyValue pair) throws FileNotFoundException {
    String path = (String)pair.getValue();
    path = expandReferences(path);
    File f = new File(path);
    if (!f.exists()) throw new FileNotFoundException(pair.getKey() +"="+(String)pair.getValue());
    set(pair.getKey(), (String)pair.getValue());
    {} //System.out.println("File : " + pair.getKey() + "=" + get(pair.getKey()));
  }

  public static void addDirectoryPath(KeyValue pair) throws FileNotFoundException {
    String path = (String)pair.getValue();
    path = expandReferences(path);
    File f = new File(path);
    if (!f.isDirectory()) throw new FileNotFoundException(pair.getKey() +"="+(String)pair.getValue()+" <- no such directory exists");
    if (!f.exists()) throw new FileNotFoundException(pair.getKey() +"="+(String)pair.getValue());
    set(pair.getKey(), (String)pair.getValue());
    {} //System.out.println("Path : " + pair.getKey() + "=" + get(pair.getKey()));
  }

  public static void set(String property, String value) { properties.put(property, value); }
  
  public static Collection getCollection(String property) {
    
    Collection collection = new ArrayList();
    String value = (String) properties.get(property);
    if (value != null) {
      
      // split the input value by comma
      String[] values = value.split(",");
      
      // cleanup, expand, and store each value .. if valid
      for(int i=0; i<values.length; i++) {
        if (values[i] != null) 
          collection.add( expandReferences(values[i].trim()) );
      }      
    }
    return collection; 
  }  
  
  public static String get(String property) {
    return expandReferences((String) properties.get(property)); 
  }

  public static String expandReferences(String s) {
    if (null==s) return "";
    int start=-1,end=-1;
    String value=s;
    String ref;
    while (((start=value.indexOf(Names.PROPERTY_REF_START)) > -1) &&
           ((end=value.indexOf(Names.PROPERTY_REF_END))>start)){
      ref = value.substring(start+1, end);
      try { value=value.substring(0,start)+get(ref)+value.substring(end+1); }
      catch (NullPointerException e) { value=null; }
      if (null==value) throw new RuntimeException("Reference to property not found: "+ref);
    }
    return value;
  }
  private static Map properties=new HashMap();

  public static String docBase = "doc-base";
  public static String tempDirectory = "zws-temp";
  public static String DesignStateDatabase = "DesignState-database";
  public static String exeProE= "exe-proE";
  public static String exeProCommMsg = "exe-proCOMM-msg";
  public static String exeILinkTKenv= "exe-ilink-tk-env";
  public static String exeGhostScript = "exe-gs-ps2pdf";
  public static String iLinkCheckout = "ilink-checkout";
  public static String iLinkCheckoutAsStored = "ilink-checkout-as-stored";
  public static String iLinkGetBOM = "ilink-bom-as-stored";
  public static String iLinkDestroyWorkspace = "ilink-destroy-workspace";
  public static String iLinkExport = "ilink-export";
  public static String iLinkSearch = "ilink-search";
  public static String iLinkModelcheck = "ilink-modelcheck";
  public static String dwg2ps = "dwg2ps";
  public static String drw2ps = "drw2ps";
  public static String Office2pdf = "office2pdf";
  public static String drw2psOutputDrive ="drw2ps-output-drive"; //todo: remove this once toolkit is fixed to take inputfilename and output filename as parameters
  public static String drw2psType = "drw2ps-output-type"; //todo: remove this once toolkit is fixed to take inputfilename and output filename as parameters
  public static String drw2psTimeout = "drw2ps-timeout";
  public static String ps2pdf= "ps2pdf";
  public static String ps2colorpdf= "ps2colorpdf";
  public static String spoolerOutDirectory = "spooler-output-directory";
  public static String colorPDF = "color-pdf";
  public static String blackWhitePDF = "black-and-white-pdf";

  public static String SQLServerDriverClass = "ms-sql-server-driver-class";
}
