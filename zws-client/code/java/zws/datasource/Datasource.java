package zws.datasource;/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.exception.*;
import zws.mapping.MetadataMappingSet;
import zws.origin.Origin;
import zws.pkg.DataPackage;
import zws.replication.report.ConflictReport;
import zws.search.SearchAgent;
import zws.security.Authentication;
import zws.util.Named;
import zws.util.Prototype;

import java.io.*;
import java.util.Collection;

public interface Datasource extends Prototype, Named, Serializable  {
  public String getDomainName();
  public String getServerName();
  public String getType();
  public String getName();
  public String getDescription();
  public SearchAgent materializeSearchAgent();
  public SearchAgent materializeLatestSearchAgent();
  public SearchAgent materializeLatestRevSearchAgent();
  
  public void verifyConfiguration() throws InvalidConfiguration;
  public void verifyConnection() throws NotConnected;
  public void configure() throws InvalidConfiguration;
  public void inactivate();
  
  // Identification Info
  
  public String getDefaultUsername(); 
  public String getDefaultPassword(); 
  public boolean contains(Origin o, Authentication id) throws Exception;
  public boolean hasChildren(Origin o, Authentication id) throws Exception ;
  public boolean mayHaveSubComponents(Metadata data);
  //public Metadata bindAsStoredSubComponents(Origin o) throws Exception;
  //public Metadata bindLatestSubComponents(Origin o) throws Exception;
  //public Metadata bindSubComponents(Origin o, boolean asStored) throws Exception;
  public void bindFirstLevelSubComponents(Metadata parent, boolean asStored) throws Exception;
  public Collection findAsStoredSubComponents(Metadata data) throws Exception;
  public Collection findLatestSubComponents(Metadata data) throws Exception;
  //public BillOfMaterials getAsStoredBill(Origin origin) throws Exception;
  //public BillOfMaterials getLatestBill(Origin origin) throws Exception;
  public BillOfMaterials getBill(Origin origin, boolean asStored) throws Exception;
  public String getReplicationDependencyConfiguration();
  
  public Origin lookupOrigin(String uid, Authentication id) throws Exception;
  public Metadata find(Origin o, Authentication id) throws Exception;
  public Metadata findLatest(String name, Authentication id) throws Exception;
  public Collection listNames(Authentication id) throws Exception;
  public Collection getQueuedSnapshots();
  public void snapshotImage(Origin o, String outputType, String targetDatasource, Authentication id) throws Exception;
  //public Metadata snapshotImage(Origin o, String imageNamingConvention, String stampNamingConvention, boolean deleteOldImage, Authentication id) throws Exception;
  public void deleteFromRepository(String name, Authentication id) throws Exception;
  public void deleteVersionFromRepository(Origin o, Authentication id) throws Exception;
  public InputStream findBinary(Origin o, Authentication id) throws Exception;
  public File exportBinary( Origin o, File outputDir, Authentication id) throws Exception;
  // Basic Document Managment functionality
  //public Origin storeBinary(Origin source, InputStream binary, long len, Authentication id) throws Exception;
  //public Origin addBinary(Origin source, InputStream binary, long len, Authentication id) throws Exception;
  //public Origin updateBinary(Origin source, InputStream binary, long len, Authentication id) throws Exception;
  public Origin storeBinary(Origin target, InputStream binary, Authentication id) throws Exception;
  public Origin addBinary(Origin target, InputStream binary, Authentication id) throws Exception;
  public Origin updateBinary(Origin target, InputStream binary, Authentication id) throws Exception;
  
  public long getBinaryLength(Origin o, Authentication id) throws Exception;
  public void storeMetadata(Metadata data, Authentication id) throws Exception;
  public void addMetadata(Metadata data, Authentication id) throws Exception;
  public void updateMetadata(Metadata data, Authentication id) throws Exception;

  // Document Version Control Functionality
  public void checkin(String workspaceName, boolean deleteFiles, Authentication id) throws Exception;
  public void checkin(Origin o, String workspaceName, boolean deleteFile, Authentication id) throws Exception;
  public void checkinUpdate(String workspaceName, Authentication id) throws Exception;  //check in workspace, but keeps files checked out
  public void checkinUpdate(Origin o, String workspaceName, Authentication id) throws Exception; //check in document, but keep file checked out
  public void checkout(Origin o, String workspaceName, Authentication id) throws Exception;
  public void getCopy(Origin o, String workspaceName, Authentication id) throws Exception;
  public void lock(Origin o, Authentication id) throws Exception;
  public void unlock(Origin o, Authentication id) throws Exception;
  public void link(Origin source, Origin target, Authentication id) throws Exception;
  public void unlink(Origin source, Origin target, Authentication id) throws Exception;
  
  public Collection listComponents(Authentication id) throws PathDoesNotExist, Exception;
  public Collection listComponents(String path, Authentication id) throws PathDoesNotExist, Exception;
  public Collection listComponents(String path, boolean recursively, Authentication id) throws PathDoesNotExist, Exception;
  public Collection listSubFolders(String path, boolean recursive, Authentication id) throws PathDoesNotExist, Exception;
  public Collection getFolderListing(String path, boolean recursive, Authentication id) throws PathDoesNotExist, Exception;
  public void createFolder(String name, String path, Authentication id) throws InvalidName, PathDoesNotExist, DuplicateName, Exception;
  public void deleteFolder(String path, Authentication id) throws PathDoesNotExist, NotEmpty, Exception; 

  public DataPackage createDesignPackage(String pkgName, Collection updates, String tarballName, boolean includeHistory, Authentication id) throws Exception;
  public DataPackage createPackage(String pkgName, Collection updates, String tarballName, boolean includeHistory, Authentication id) throws Exception;
  public Collection importDesignPackage(DataPackage pkg, MetadataMappingSet mappings, boolean lockOnImport, boolean overwriteConflicts, Authentication id) throws Exception;
  public Collection importPackage(DataPackage pkg, MetadataMappingSet mappings, boolean lockOnImport, Authentication id) throws Exception;
  public Collection synchronizePackage(DataPackage pkg, MetadataMappingSet mappings, boolean lockOnSynk, Authentication id) throws Exception;
  public ConflictReport reportConflicts (DataPackage dPkg, Authentication id) throws Exception;

  //public Origin importFromPackage(PackageSource pkg, Metadata m, MetadataMappingSet mappings, Authentication id) throws Exception;
  //public Collection synchronizeFromPackage(PackageSource pkg, Collection metadataList, MetadataMappingSet mappings, Authentication id) throws Exception;
  //public void importPackage(PackageSource pkg) throws Exception;

  public File getDataPath();
  public File getDataPath(String subDir);
  public File getDataPathToFile(String subDir, String filename);
}