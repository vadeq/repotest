package com.zws.application;

public class Constants {
  public static String SERVICE_NAME = "service-name";

  public static String QUOTE = "\"";
  public static String DOT = ".";
  public static String LINE_SEPARATOR = System.getProperty("line.separator");
  public static String FILE_SEPARATOR = System.getProperty("file.separator");
  public static String DELIMITER = ";";
  public static String METADATA_DELIMITER = ";";
  public static String ORIGIN_DELIMITER = ":";
  public static String FILENAME_SEPARATOR = "_";
  public static String PROPERTY_REF_START="[";
  public static String PROPERTY_REF_END="]";

  public static String METADATA_ORIGIN="Origin";
  public static String METADATA_NAME="Name";
  public static String METADATA_PATH="Path";

  public static String METADATA_FINDER_SPEC="ds-finder-spec";
  public static String METADATA_SERVICE_NAME = "ds-service-name";
  public static String METADATA_DATASOURCE = "ds-datasource";
  public static String METADATA_SEARCH_AGENT ="ds-search-agent";
  public static String METADATA_REPORT="ds-report";
  public static String METADATA_TYPE="ds-type";
  public static String METADATA_EXTENTION="ds-extention";
  public static String METADATA_BINARY_FILENAME="ds-binary-filename";

  public static String METADATA_SOURCEFILE_ORIGIN="ds-sourcefile-origin";
  public static String METADATA_SOURCEFILE_FINDER_SPEC="ds-sourcefile-finder-spec";
  public static String METADATA_SOURCEFILE_SERVICE_NAME="ds-sourcefile-service-name";
  public static String METADATA_SOURCEFILE_NAME="ds-sourcefile-name";
  public static String METADATA_SOURCEFILE_TYPE="ds-sourcefile-type";
  public static String METADATA_SOURCEFILE_EXTENTION="ds-sourcefile-extension";
  public static String METADATA_SOURCEFILE_DATASOURCE="ds-sourcefile-datasource";

  //File attributes that can be retreived from disk (java.io.File)
  public static String METADATA_FILE_SIZE="fs-size";
  public static String METADATA_FILE_PERMISSIONS="fs-permissions";
  public static String METADATA_FILE_LAST_MODIFIED="fs-last-modified";
  public static String METADATA_FILE_DATE_LAST_MODIFIED="fs-date-last-modified";
  public static String METADATA_FILE_TIME_LAST_MODIFIED="fs-time-last-modified";

  public static int PROCESS_TIMED_OUT = -888;
}
