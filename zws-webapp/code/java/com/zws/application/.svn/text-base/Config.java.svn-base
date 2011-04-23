package com.zws.application;

import java.io.InputStream;
import java.util.Properties;

public class Config {
  public static long uniqueVMID=00;
  public static long getUniqueVMID() { return uniqueVMID++; }
  public static String FILE_SEPARATOR = System.getProperty("file.separator");
  public static String DATA_SOURCE_APPLICATION_DATABASE= "data-source-application-database";
  
  public static String STREAM_SEARCH_RESULTS="stream-search-results";

  public static String DIR_TEMP = "dir-temp";
  public static String DIR_DOWNLOAD = "dir-download";
  public static String RELEASE_LEVEL="release-level";
  public static String SEARCH_CRITERIA="search-criteria";
  public static String MAX_SEARCH_COUNT="max-count";

  public static String OUTPUT_ILINK_RESULT = "output-ilink-result";
  public static String CFG_ILINK_METADATA = "cfg-ilink-metadata-spec";
  public static String EXE_ILINK_SEARCH = "exe-ilink-search";
  public static String EXE_ILINK_CHECKIN = "exe-ilink-checkin";
  public static String EXE_ILINK_CHECKOUT = "exe-ilink-checkout";
  public static String EXE_ILINK_WSDESTROY = "exe-ilink-destroy-workspace";
  public static String EXE_ILINK_EXPORT = "exe-ilink-export";
  public static String EXE_DEERE_DRP_EXPORT = "exe-deere-drp-export";

  public static String CFG_METADATA = "cfg-metadata";

  public static String EXE_PDF_GENERATOR = "exe-pdf-generator";
  public static String REPOSITORY_PDF = "repository-pdf";

  public static String EXE_PROI_TOOLKIT_ENVIRONMENT = "exe-proi-tk-env";
  public static String EXE_PRO_COMM_MESSAGE = "exe-pro-comm-msg";
  public static String USERNAME_INTRALINK = "username-ilink";
  public static String PASSWORD_INTRALINK = "password-ilink";

  public static String ATTRIBUTE_REVISION = "rev";
  public static String ATTRIBUTE_VERSION = "ver";

  public static String CFG_SEARCH_AGENTS = "cfg-search-agents";

  private static Properties configuration = null;

  public static String getProperty(String key){ return getConfiguration().getProperty(key); }
  private static Properties getConfiguration(){
     if (null==configuration) {
       try {
         InputStream in = (new Config()).getClass().getResourceAsStream("/Config.properties");
         configuration = new Properties();
         configuration.load(in);
         in.close();
       }
       catch (Exception e) {
         {} //System.out.println("\nCould not load Config.properties file from class path");
         e.printStackTrace();
       }
     }
     return configuration;
   }
}
