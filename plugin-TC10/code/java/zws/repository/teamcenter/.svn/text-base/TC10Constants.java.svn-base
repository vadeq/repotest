package zws.repository.teamcenter;
/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on May 23, 2007 10:48:58 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import java.util.HashMap;

import zws.origin.Origin;

public class TC10Constants {

  //System Constants
  public static final String crlf = System.getProperty("line.separator");
  public static String DOT = ".";
  public static String DASH = "-";
  public static String QUOTE = "\"";
  public static String DELIMITER = "|";
  public static String TIME_DELIMITER =".";
  public static String ORIGIN_DELIMITER = "|";
  public static String FILENAME_SEPARATOR = "_";
  public static String PATH_SEPARATOR = System.getProperty("file.separator");
  public static String NEW_LINE = System.getProperty("line.separator");
  public static String delim = Origin.delim;

  //System Defaults
  public String DEFAULT_DATE = "8/8/1888 08:08:08";

  //public static String RELEASE_SCHEME = "release-scheme";

  //context parameters
  public static String CONNECTION_TIMEOUT = "connection-timeout";
  public static String DATE_FORMAT = "date-format";
  public static String LOWER_CASED_FILENAMES = "lower-cased-filenames";
  public static String UPPER_CASED_FILENAMES = "upper-cased-filenames";

  //default parameter values
  public static String DEFAULT_DATE_FORMAT = "M/d/y H:m:s";
  //public static String DOMAIN_NAME = "domain-name";
  //public static String NODE_NUMBER = "node-number";
  //public static String REPOSITORY_NAME = "repository-name";
  //public static String ENV_ROOT = "env-root";

  //Some ZWS system attributes
  public static String N="n";
  //public static String ORIGIN="origin";
  //public static String FILE_TYPE = "file-type";
  //public static String PATH = "path";

  public static String TIME = "time";
  //public static String LOCATION = "folder";
  //public static String PROMOTE_TO = "promote-to";
  //public static String DEMOTE_TO = "demote-to";
  public static String CREATED_ON = "created-on";
  public static String CREATED_BY = "created-by";

  public static String LOCK_STATUS = "checked_out";
  public static String LOCKED = "locked";

  //default configurations
  //public static String AS_STORED_CONFIGURATION = "as-stored";
  //public static String LATEST_CONFIGURATION = "latest";

  //Dependency types
  //public static String NO_DEPENDENCIES = "none";
  //public static String ALL_DEPENDENCIES = "all";
  //public static String REQUIRED_DEPENDENCIES = "required";

  //Dependency Graph Configurations
  //public static String AS_STORED="as-stored";
  //public static String LATEST="latest";

  //Operation Tags
  public static String TAG_QX= "TC-10-qx";
  public static String TAG_OPEN_REPOSITORY= "open-repository";
  public static String TAG_FIND= "find";
  public static String TAG_FIND_LATEST= "find-latest";
  public static String TAG_SEARCH= "search";
  public static String TAG_FETCH_NATIVE_FILE= "fetch-native-file";
  public static String TAG_FETCH_DESIGN_FILES= "fetch-design-files";
  public static String TAG_REPORT_BOM= "reportBOM";
  public static String TAG_CREATE= "create";
  public static String TAG_UPDATE= "update";
  public static String TAG_RENAME= "rename";
  public static String TAG_ADD_BINARY= "add-binary";
  public static String TAG_UPDATE_BINARY= "update-binary";
  public static String TAG_ADD_ATTACHMENT= "add-attachment";
  public static String TAG_UPDATE_ATTACHMENT= "update-attachment";

  public static String TAG_FILE= "file";

  //Tag Attributes
  public static String ATT_USER= "user";
  //public static String ATT_PASSWORD= "password";
  public static String ATT_ENCRYPTED_PASSWORD= "encrypted-password";
  public static String ATT_CRITERIA= "criteria";
  public static String ATT_ORIGIN= "origin";
  public static String ATT_REPOSITORY_NAME= "repository-name";

  public static String ATT_NAME= "name";
  public static String ATT_REV= "rev";
  public static String ATT_CONFIGURATION= "configuration";
  public static String ATT_ITEM_ID= "item-id";
  public static String ATT_TO_DIR= "to-dir";
  public static String ATT_ABS_PATH= "absolute-path";
  public static String ATT_METADATA= "metadata";

  //Teamcenter attributes for ZWS System
  public static String ITEM_ID = "ItemID";
  public static String REVISION = "Revision";
  public static String TYPE = "Type";
  public static String NAME = "Name";
  public static String DESCRIPTION = "Description";
  public static String UNIT_OF_MEASURE = "UnitOfMeasure";
  public static String OWNER = "OwningUser";
  public static String CREATION_DATE = "CreationDate";

  public static String ORIGINAL_FILE_NAME = "original_file_name";
  public static String NAMED_REF = "NamedReference";

  public static String QUANTITY = "quantity";

  //Real Teamcenter attributes
  public static String RELEASE_LEVEL = "last_release_status";
    //Teamcenter Class Names
  public static String CLASS_ITEM="Item";
  public static String CLASS_ITEM_REV="ItemRevision";
  public static String CLASS_DATASET="Dataset";
  public static String CLASS_IMANFILE="ImanFile";
    //Teamcenter Defaults
  public static String DEFAULT_CONFIGURATION = "Latest Working";

  //Mapping: Teamcenter-to-ZWS
  public static HashMap fromTC10ToZwsMap = new HashMap();
  static {
    fromTC10ToZwsMap.put("Name", "Name");
    fromTC10ToZwsMap.put("item_id", "ItemID");
    fromTC10ToZwsMap.put("item_revision_id", "Revision");
    fromTC10ToZwsMap.put("DatasetID", "DatasetID");
    fromTC10ToZwsMap.put("DatasetType", "DatasetType");
    //fromTC10ToZwsMap.put("creation_date", "CreationDate");
    fromTC10ToZwsMap.put("creation_date", "created-on");
    fromTC10ToZwsMap.put("Keywords", "tc_fts_keyword");
    fromTC10ToZwsMap.put("Alias ID", "AliasID");
    fromTC10ToZwsMap.put("Alias IdContext Name", "AliasIdContextName");
    fromTC10ToZwsMap.put("Alias Type", "AliasType");
    fromTC10ToZwsMap.put("Alternate ID", "AlternateID");
    fromTC10ToZwsMap.put("Alternate IdContext Name", "AlternateIdContextName");
    fromTC10ToZwsMap.put("Alternate Type", "AlternateType");
    fromTC10ToZwsMap.put("Description", "Description");
    fromTC10ToZwsMap.put("object_type", "Type");
    //fromTC10ToZwsMap.put("owning_user", "OwningUser");
    fromTC10ToZwsMap.put("owning_user", "creator");

    fromTC10ToZwsMap.put("Owning Group", "OwningGroup");
    fromTC10ToZwsMap.put("Created After", "CreatedAfter");
    fromTC10ToZwsMap.put("Created Before", "CreatedBefore");
    fromTC10ToZwsMap.put("Modified After", "ModifiedAfter");
    fromTC10ToZwsMap.put("Modified Before", "ModifiedBefore");
    fromTC10ToZwsMap.put("Released After", "ReleasedAfter");
    fromTC10ToZwsMap.put("Released Before", "ReleasedBefore");
    fromTC10ToZwsMap.put("Release Status", "ReleaseStatus");
    fromTC10ToZwsMap.put("Current Task", "CurrentTask");

    fromTC10ToZwsMap.put("Name", "file_name");
    fromTC10ToZwsMap.put("Original File Name", "original_file_name");
    fromTC10ToZwsMap.put("relative_directory_path", "relative_directory_path");

    fromTC10ToZwsMap.put("last_mod_date", "modified-on");
    fromTC10ToZwsMap.put("last_mod_user", "modified-by");
    fromTC10ToZwsMap.put("object_application", "authoring-application");



  }

//Mapping: ZWS-to-Teamcenter
  public static HashMap fromZwsToTC10Map = new HashMap();
  static {
    fromZwsToTC10Map.put("Name", "Name");
    fromZwsToTC10Map.put("ItemID", "Item ID");
    fromZwsToTC10Map.put("Revision", "Revision");
    fromZwsToTC10Map.put("DatasetID", "DatasetID");
    fromZwsToTC10Map.put("DatasetType", "DatasetType");
    //fromZwsToTC10Map.put("CreationDate", "creation_date");
    fromZwsToTC10Map.put("created-on", "creation_date");
    fromZwsToTC10Map.put("tc_fts_keyword", "Keywords");
    fromZwsToTC10Map.put("AliasID", "Alias ID");
    fromZwsToTC10Map.put("AliasIdContextName", "Alias IdContext Name");
    fromZwsToTC10Map.put("AliasType", "Alias Type");
    fromZwsToTC10Map.put("AlternateID", "Alternate ID");
    fromZwsToTC10Map.put("AlternateIdContextName", "Alternate IdContext Name");
    fromZwsToTC10Map.put("AlternateType", "Alternate Type");
    fromZwsToTC10Map.put("Description", "Description");
    fromZwsToTC10Map.put("Type", "Type");
    //fromZwsToTC10Map.put("OwningUser", "Owning User");
    fromZwsToTC10Map.put("creator", "Owning User");
    fromZwsToTC10Map.put("OwningGroup", "Owning Group");
    fromZwsToTC10Map.put("CreatedAfter", "Created After");
    fromZwsToTC10Map.put("CreatedBefore", "Created Before");
    fromZwsToTC10Map.put("ModifiedAfter", "Modified After");
    fromZwsToTC10Map.put("ModifiedBefore", "Modified Before");
    fromZwsToTC10Map.put("ReleasedAfter", "Released After");
    fromZwsToTC10Map.put("ReleasedBefore", "Released Before");
    fromZwsToTC10Map.put("ReleaseStatus", "Release Status");
    fromZwsToTC10Map.put("CurrentTask", "Current Task");

    fromZwsToTC10Map.put("file_name", "Name");
    fromZwsToTC10Map.put("original_file_name", "Original File Name");
    fromZwsToTC10Map.put("relative_directory_path", "relative_directory_path");

    fromZwsToTC10Map.put("modified-on", "last_mod_date");
    fromZwsToTC10Map.put("modified-by", "last_mod_user");
    fromTC10ToZwsMap.put("authoring-application", "object_application");
  }

}
