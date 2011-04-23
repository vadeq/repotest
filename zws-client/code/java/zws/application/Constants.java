package zws.application; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class Constants {
 public static String SERVER_NAME = "server-name";

 public static String DOT = ".";
 public static String DASH = "-";
 public static String QUOTE = "\"";

 public static String DELIMITER = "|";
 public static String ORIGIN_DELIMITER = "|";

 public static String FILENAME_SEPARATOR = "_";
 public static String PATH_SEPARATOR = System.getProperty("file.separator");
 public static String NEW_LINE = System.getProperty("line.separator");

 public static String PROPERTY_REF_START="[";
 public static String PROPERTY_REF_END="]";
 public static String POPERTY_LITERAL_START = "'";
 public static String POPERTY_LITERAL_END = "'";

 //Criteria Grammar tokens:
 public static String CRITERIA_EQUALS = "=";
 public static String CRITERIA_OR_DELIMITER="|";
 public static String CRITERIA_AND_START_BLOCK="[";
 public static String CRITERIA_AND_END_BLOCK="]";
 
 //common metadata field names
 public static String METADATA_NAME="Name";
 public static String METADATA_TYPE = "zws-type";
 public static String METADATA_ORIGIN="Origin";
 public static String METADATA_EXTENTION = "zws-extention";
 public static String METADATA_SERVER_NAME = "zws-server-name";
 public static String METADATA_DATASOURCE_NAME = "zws-datasource-name";
 public static String METADATA_DATASOURCE_SEARCH_AGENT_NAME = "zws-datasource-search-agent-name";
 public static String METADATA_SEARCH_AGENT_NAME = "zws-search-agent-name";

 public static String METADATA_SOURCEFILE_NAME = "zws-sourcefile-name";
 public static String METADATA_SOURCEFILE_TYPE = "zws-sourcefile-type";
 public static String METADATA_SOURCEFILE_EXTENTION = "zws-sourcefile-extention";
 public static String METADATA_SOURCEFILE_SERVER_NAME = "zws-sourcefile-server-name";
 public static String METADATA_SOURCEFILE_DATASOURCE_NAME = "zws-sourcefile-datasource-name";
 public static String METADATA_SOURCEFILE_SEARCH_AGENT_NAME = "zws-sourcefile-search-agent-name";

 //metadata field names for file-system datasource
 public static String METADATA_FILE_SIZE="zws-file-size";
 public static String METADATA_FILE_PERMISSIONS="zws-permissions";
 public static String METADATA_FILE_LAST_MODIFIED="zws-last-modified";
 public static String METADATA_FILE_DATE_LAST_MODIFIED="zws-date-last-modified";
 public static String METADATA_FILE_TIME_LAST_MODIFIED="zws-time-last-modified";

 //values for METADATA_FILE_PERMISSIONS
 public static String METADATA_VALUE_READ_ONLY_PERMISSIONS="read only";
 public static String METADATA_VALUE_READ_WRITE_PERMISSIONS="read write";

 //Report server constants
 public static String DELIMITER_REPORT_AGENT_MAPPING = "report-agent-mapping-delimiter";
 
 public static int keyPROCESS_TIMED_OUT = -888;
}
