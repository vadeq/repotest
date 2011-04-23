package zws.data; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.origin.Origin;

import java.io.Serializable;
import java.util.*;

/** A set of name, value pairs for a given origin. */
public interface Metadata extends Serializable {
  
  /*
   *  
   * Metadata
   *  |_ ItemOrigin [origin]
   *  |_ attribute (*) [String]
   *  |_ sub components (*) [Metadata]
   *  |_ binary file (*) [Metadata]
   */
  
  public Origin getOrigin();
  public String getName();
  public MetadataBase getMetadataBase();
  public String get(String fieldName);
  public void set(String fieldName, String value);
  //public void clearMetadata();
  public boolean hasFieldName(String s);
  public Collection getFieldNames();
  public Collection getFieldValues();
  public Map getAttributes();
  public boolean isLater(Metadata data);
  public boolean isEarlier(Metadata data);
  public boolean hasSameOrigin(Metadata data);
  public void write(StringBuffer xml) throws Exception;
  public void write(StringBuffer xml, String tagName) throws Exception;
  public void write(StringBuffer xml, Collection metadataFields) throws Exception;
  public void write(StringBuffer xml, String tagName, Collection metadataFields) throws Exception;
  public Collection getBinaries(); //binary axis
  public Collection getLinks();     //association axis
  public Collection getSubComponents(); //sub component axis
  public boolean hasAncestorNamed(String name);
  public Metadata getAncestor();
  public void setAncestor(Metadata data);
  public boolean hasSubComponents();
  public boolean hasSubComponent(String name);
  public Collection getFamilyInstances(); //sub component axis
  public boolean hasFamilyInstances();
  public SortedSet getBranches();
  public void addBinary(MetadataBinary binary);
  public void addLink(Metadata metadata);
  public void addSubComponent(MetadataSubComponent sub);
  public void addFamilyInstance(MetadataFamilyInstance instance);
  public void merge(Metadata input, boolean keepValuesProtected);

  public static String DOMAIN_NAME = Names.METADATA_DOMAIN_NAME;
  public static String SERVER_NAME = Names.METADATA_SERVER_NAME;
  public static String REPOSITORY_NAME = Names.METADATA_REPOSITORY_NAME;
  public static String BRANCH = Names.METADATA_BRANCH;
  public static String REVISION = Names.METADATA_REVISION;
  public static String VERSION = Names.METADATA_VERSION;
  public static String DATASOURCE_NAME= Names.METADATA_DATASOURCE_SEARCH_AGENT_NAME ;
  public static String CREATED_ON= Names.METADATA_CREATED_ON;
  public static String CREATED_BY= Names.METADATA_CREATED_BY;
  public static String FILE_TYPE = Names.METADATA_FILE_TYPE;
  public static String LOCATION = Names.METADATA_LOCATION;
  public static String TIME_OF_CREATION = Names.METADATA_TIME_OF_CREATION;
  public static String RELEASE_LEVEL= Names.METADATA_RELEASE_LEVEL;
  public static String LOCK_STATUS = Names.METADATA_LOCK_STATUS;
  public static String OWNER = Names.METADATA_OWNER;

  public static String ORIGIN = Names.METADATA_ORIGIN;
  public static String NAME= Names.METADATA_NAME;
  public static String COUNT = Names.METADATA_COUNT;
  public static String IS_GENERIC= Names.METADATA_IS_GENERIC;
  public static String IS_INSTANCE= Names.METADATA_IS_INSTANCE;
}