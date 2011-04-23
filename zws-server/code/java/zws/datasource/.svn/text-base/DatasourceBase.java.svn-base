package zws.datasource;/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.*;
import zws.Alert;
import zws.Server;
import zws.application.Names;
import zws.application.Properties;
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.datasource.pkg.PackageSource;
import zws.datasource.pkg.PackageSourceBase;
import zws.exception.*;
import zws.mapping.MetadataMappingSet;
import zws.op.queue.Queue;
import zws.op.queue.QueueOp;
import zws.origin.Origin;
import zws.origin.OriginBase;
import zws.pkg.DataPackage;
import zws.pkg.DataPackageBase;
import zws.replication.report.*;
import zws.search.SearchAgent;
import zws.security.Authentication;
import zws.service.packaging.PackagingSvc;
import zws.service.search.SearchAgentSvc;
import zws.service.synchronization.SynchronizationSvc;
import zws.util.*;

import java.io.File;
import java.io.InputStream;
import java.util.*;

public abstract class DatasourceBase extends RoutedDataBase implements Datasource {
  public boolean supportsDeepCopy() { return false; }
  
  public abstract String getType();
  public String getDomainName() { return Server.getDomainName(); }
  public String getServerName() { return Server.getName(); }
  public void setDomainName() { throw new UnsupportedOperation("Can not set domainName for an implementation of DatasourceBase"); }
  public void setServerName() { throw new UnsupportedOperation("Can not set serverName for an implementation of DatasourceBase"); }
  public final void setName(String s) { super.setName(s); super.setDatasourceName(s); }
    
  public String objectName(String name) {
    if (lowerCasedFilenames) return name.toLowerCase();
    else if (upperCasedFilenames) return name.toUpperCase();
    else return name;
  }

  public void inactivate() { }
  public void verifyConfiguration() throws InvalidConfiguration { return; }
  public void verifyConnection() throws NotConnected { return; }
  public final String getDescription() { return description; }
  public final void setDescription(String s) { description=s; }

  public String getDefaultUsername() { return null; }
  public String getDefaultPassword() { return null; }

  public abstract SearchAgent materializeSearchAgent();
  public abstract SearchAgent materializeLatestSearchAgent();
  public abstract SearchAgent materializeLatestRevSearchAgent();

  public void configure() throws InvalidConfiguration {
    SearchAgent agent = materializeSearchAgent();
    try {
      if (null!=agent && null!=agent.getName()) SearchAgentSvc.add(agent);
    }
    catch (DuplicateName e) { throw new InvalidConfiguration(e.getMessage()); }
  }

  public abstract boolean contains(String name, Authentication id) throws Exception;

  public boolean contains(Origin o, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".contains(origin, id)");
  }
  
  public void snapshotImage(Origin o, String outputType, String targetDatasource, Authentication id) throws Exception {
    //+++genericize for any type of viewable image
    String viewable = objectName(o.getName());
    Collection synkRecords = SynchronizationSvc.findAllSynchronizationRecords(o);
    if (null!=synkRecords) {
      Iterator i = synkRecords.iterator();
      Origin synch;
      String ext;
      while (i.hasNext()){
        synch = (Origin)i.next();
        ext = FileNameUtil.getFileNameExtension(synch.getName());
        if ("pdf".equalsIgnoreCase(outputType) && "pdf".equalsIgnoreCase(ext)) {
          {}//Logwriter.printOnConsole(o.getUniqueID()+ " already has a PDF Image: " + synch.getUniqueID());
          return;
        }
        if (outputType.toLowerCase().startsWith("hpg") && ("hpg".equalsIgnoreCase(ext) || "hpgl".equalsIgnoreCase(ext))) {
            {}//Logwriter.printOnConsole(o.getUniqueID()+ " already has a HPGL Image: " + synch.getUniqueID());
            return;
          }
        if (("iges".equalsIgnoreCase(outputType)  || "igs".equalsIgnoreCase(outputType)) && ("iges".equalsIgnoreCase(ext) || "igs".equalsIgnoreCase(ext))) {
            {}//Logwriter.printOnConsole(o.getUniqueID()+ " already has an IGES Image: " + synch.getUniqueID());
            return;
          }
        if ("cgm".equalsIgnoreCase(outputType) && "cgm".equalsIgnoreCase(ext)) {
            {}//Logwriter.printOnConsole(o.getUniqueID()+ " already has a CGM Image: " + synch.getUniqueID());
            return;
          }
        if ("idf".equalsIgnoreCase(outputType) && "idf".equalsIgnoreCase(ext)) {
            {}//Logwriter.printOnConsole(o.getUniqueID()+ " already has an IDF Image: " + synch.getUniqueID());
            return;
          }
      }
    }
    Quadrouplet q = new Quadrouplet(o, outputType, targetDatasource, id);
    getSnapshotQueue().add(q);
  }
  protected void makeImageSnapshot(Origin o, String outputType, String targetDatasource, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".snapshotImage(origin, outputType, id)");
  }
  /*
  public Metadata snapshotImage(Origin o, String imageNamingConvention, String stampNamingConvention, boolean deleteOldImage, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".snapshotImage(origin, imageNamingConvention, stampNamingConvention, deleteOldImage, id)");
  }
   */
  
  public Collection getQueuedSnapshots(){
    Collection c = new Vector();
    Collection queue = getSnapshotQueue().getQueue();
    if (null==queue) return c;
    Iterator i = queue.iterator();
    Pair p;
    Quadrouplet q;
    while(i.hasNext()) {
      q = (Quadrouplet)i.next();
      p = new Pair(q.getObject0(), q.getObject1());
      c.add(p);
    }
    return c;
  }

  private Queue snapshotQueue = null;
  private Queue getSnapshotQueue() { 
    if (null==snapshotQueue) snapshotQueue = new Queue(new ImageSnapshotOp());
    return snapshotQueue;
  }
  
  private class ImageSnapshotOp extends QueueOp {
    public void processNext(Object originID) throws Exception { processNext((Quadrouplet)originID); }
    public void processNext(Quadrouplet originTypeID) throws Exception {
      try {
        Origin o = (Origin)originTypeID.getObject0();
        String outputType = (String)originTypeID.getObject1();
        String targetDatasource = (String) originTypeID.getObject2();
        Authentication id = (Authentication) originTypeID.getObject3();
        makeImageSnapshot(o, outputType, targetDatasource, id);
      }
      catch (Exception e) {
        String msg = "Could not generate image snapshot: " + Names.NEW_LINE;
        msg += originTypeID.getObject0() + Names.NEW_LINE;
        msg += originTypeID.getObject1() + Names.NEW_LINE;
        msg += originTypeID.getObject2() + Names.NEW_LINE;
        msg += originTypeID.getObject3() + Names.NEW_LINE;
        Alert.notify("Failed To Generate Image Snapshot", msg);
      }
    }
    public void inactivate() {};
  }
  
  public Origin lookupOrigin(String uid, Authentication id) throws Exception{
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".lookupOrigin(uid, id)");
  }
  
  public boolean mayHaveSubComponents(Metadata data) { return false; }
  public Metadata bindAsStoredSubComponents(Origin o) throws Exception { 
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".contains(origin, id)");
  }
  public Metadata bindLatestSubComponents(Origin o) throws Exception { 
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".contains(origin, id)");
  }
  public Metadata bindSubComponents(Origin o, boolean asStored) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".contains(origin, id)");
  }
  public void bindFirstLevelSubComponents(Metadata parent, boolean asStored) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".contains(origin, id)");
  }    
  public Collection findAsStoredSubComponents(Metadata data) throws Exception { 
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".contains(origin, id)");
  }
  public Collection findLatestSubComponents(Metadata data) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".contains(origin, id)");
  }
  public BillOfMaterials getAsStoredBill(Origin origin) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".contains(origin, id)");
  }
  public BillOfMaterials getLatestBill(Origin origin) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".contains(origin, id)");
  }
  public BillOfMaterials getBill(Origin origin, boolean asStored) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".contains(origin, id)");
  }

  public boolean hasChildren(Origin o, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".hasChildren(origin, id)");
  }
  
  public Metadata find(Origin o, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".find(origin, id)");
  }  

  public Metadata findLatest(String name, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".find(origin, id)");
  }  

  public void deleteFromRepository(String name, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".deleteFromRepository(name, id)");
  }
  public void deleteVersionFromRepository(Origin o, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".deleteVersionFromRepository(origin, id)");
  }
  public InputStream findBinary(Origin o, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".deleteVersionFromRepository(origin, id)");
  }
  public InputStream findBinary(Origin o, boolean cleanup, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".deleteVersionFromRepository(origin, id)");
  }
  public long getBinaryLength(Origin o, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".getBinaryLength(origin, id) ");
  }
  public File exportBinary( Origin o, File outputDir, Authentication id) throws Exception {
      throw new UnsupportedOperation(getServerName() + "|"+getName()+".exportBinary(origin, id)");
  }

  // Basic Document Managment functionality
  public void addMetadata(Metadata data, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".add(metadata, id)");
  }
  public void updateMetadata(Metadata data, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".update(metadata, id)");
  }
  public void storeMetadata(Metadata data, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".store(metadata, id)");
  }
  /*
  public Origin storeBinary(Origin source, InputStream binary, long len, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".(origin, binary, len, id)");
  }
  public Origin addBinary(Origin source, InputStream binary, long len, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".addBinary(origin, binary, len, id)");
  }
  public Origin updateBinary(Origin source, InputStream binary, long len, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".updateBinary(origin, binary, len, id)");
  }
   */
  public Origin storeBinary(Origin target, InputStream binary, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".(origin, binary, len, id)");
  }
  public Origin addBinary(Origin target, InputStream binary, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".addBinary(origin, binary, len, id)");
  }
  public Origin updateBinary(Origin target, InputStream binary, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".updateBinary(origin, binary, len, id)");
  }

  public Collection listNames(Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".listNames()");
  }
  
  protected void  exportToPackage(PackageSourceBase pkg, Collection updates, boolean includeHistory, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".exportToPackage()");
  }

  public String getReplicationDependencyConfiguration() { return replicationDependencyConfiguration; }
  public void setReplicationDependencyConfiguration(String s) { replicationDependencyConfiguration=s; }

  public DataPackage createDesignPackage(String pkgName, Collection updates, String tarballName, boolean includeHistory, Authentication id) throws Exception {
	  if (null==updates || updates.size()<1) return null;
	  PackageSourceBase pkg = new PackageSourceBase();
	  pkg.setName(pkgName);
	  pkg.clear();
	  exportToPackage(pkg, updates, includeHistory, id);	  
    /*
	  Iterator i = updates.iterator();
	  Metadata m;
	  while (i.hasNext()) {
	    m = (Metadata)i.next();
	    try {
	      InputStream stream = findBinaryForPackage(m, false, id);
	      m = findMetadataForPackage(m, includeHistory, id);
	      pkg.storeBinary(m.getOrigin(), stream, id);
	      stream.close();
	      pkg.storeMetadata(m, null);
	      cleanUp(m, id);
	    }
	    catch(Exception e) { 
	      e.printStackTrace(); 
	      {}//Logwriter.printOnConsole("Errored while packaging: " + m );
	      {}//Logwriter.printOnConsole(e.getClass().getName() +": " + e.getMessage());
	      {}//Logwriter.printOnConsole("Continuing package creation anyway");
	      continue; 
	    }
	  }
	  */
	  
	  pkg.tarball(tarballName);
	  DataPackageBase dPkg = new DataPackageBase();
	  dPkg.setDomainName(Server.getDomainName());
	  dPkg.setServerName(Server.getName());
	  dPkg.setName(pkgName);
	  return dPkg;
	}

  
  
  //update to checkout all items into single workspace and then export list
  public DataPackage createDesignPackage_old(String pkgName, Collection updates, String tarballName, boolean includeHistory, Authentication id) throws Exception {
    PackageSourceBase pkg = new PackageSourceBase();
    pkg.setName(pkgName);
    pkg.clear();
    if (null==updates || updates.size()<1) return null;
    Iterator i = updates.iterator();
    Metadata m;
    while (i.hasNext()) {
      m = (Metadata)i.next();
      try {
        InputStream stream = findBinaryForPackage(m, false, id);
        m = findMetadataForPackage(m, includeHistory, id);
        pkg.storeBinary(m.getOrigin(), stream, id);
        stream.close();
        pkg.storeMetadata(m, null);
        cleanUp(m, id);
      }
      catch(Exception e) { 
        e.printStackTrace(); 
        {}//Logwriter.printOnConsole("Errored while packaging: " + m );
        {}//Logwriter.printOnConsole(e.getClass().getName() +": " + e.getMessage());
        {}//Logwriter.printOnConsole("Continuing package creation anyway");
        continue; 
      }
    }
    pkg.tarball(tarballName);
    DataPackageBase dPkg = new DataPackageBase();
    dPkg.setDomainName(Server.getDomainName());
    dPkg.setServerName(Server.getName());
    dPkg.setName(pkgName);
    return dPkg;
  }

  public DataPackage createPackage(String pkgName, Collection updates, String tarballName, boolean includeHistory, Authentication id) throws Exception {
    PackageSourceBase pkg = new PackageSourceBase();
    pkg.setName(pkgName);
    pkg.clear();
    if (null==updates || updates.size()<1) return null;
    Iterator i = updates.iterator();
    Metadata m;
    while (i.hasNext()) {
      m = (Metadata)i.next();
      try {
        InputStream stream = findBinaryForPackage(m,id);
        m = findMetadataForPackage(m, includeHistory, id);
        pkg.storeBinary(m.getOrigin(),stream,id);
        stream.close();
        pkg.storeMetadata(m, null);
        cleanUp(m, id);
      }
      catch(Exception e) { 
        e.printStackTrace(); 
        {}//Logwriter.printOnConsole("Errored while packaging: " + m );
        {}//Logwriter.printOnConsole(e.getClass().getName() +": " + e.getMessage());
        {}//Logwriter.printOnConsole("Continuing package creation anyway");
        continue; 
      }
    }

    pkg.tarball(tarballName);
    DataPackageBase dPkg = new DataPackageBase();
    dPkg.setDomainName(Server.getDomainName());
    dPkg.setServerName(Server.getName());
    dPkg.setName(pkgName);
    return dPkg;
  }

  public InputStream findBinaryForPackage(Metadata data, Authentication id) throws Exception { return findBinary(data.getOrigin(), id); }
  public InputStream findBinaryForPackage(Metadata data, boolean cleanup, Authentication id) throws Exception { return findBinary(data.getOrigin(), cleanup, id); }
  public abstract Metadata findMetadataForPackage(Metadata data, boolean includeHistory, Authentication id) throws Exception;
  public void cleanUp(Metadata data, Authentication id) throws Exception { return; }

  /*
  public void importPackage(PackageSource pkg) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".importPackage(pkg)");
  }
  public Origin importFromPackage(PackageSource pkg, Metadata m, MetadataMappingSet mappings, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".importFromPackage(pkg, metadata, mapping, id)");
  }
   **/
  protected Collection importFromPackage(PackageSource pkg, Collection metadataList, MetadataMappingSet mappings, boolean lockOnImport, boolean overwriteConflicts, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".importFromPackage(pkg, metadata, mapping, id)");
  }

  protected Collection synchronizeFromPackage(PackageSource pkg, Collection metadataList, MetadataMappingSet mappings, Authentication id) throws Exception {
    Collection origins = new Vector();
    if (metadataList==null || metadataList.size()==0) return origins;
    Iterator i = metadataList.iterator();
    Origin source;
    Origin target;
    while (i.hasNext()) {
      source = ((Metadata)i.next()).getOrigin();
      target = findCorrespondingOrigin(source);
      if (null!=target) origins.add(target);
    }
    return origins;
  }
  protected Origin findCorrespondingOrigin(Origin o) {
    OriginBase origin = (OriginBase) o.copy();
    origin.setDomainName(getDomainName());
    origin.setServerName(getServerName());
    origin.setDatasourceName(getName());
    return origin;
  }
  
  public boolean containsBinary(Origin o, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".containsBinary(origin)");
  }
  
  public ConflictReport reportConflicts (DataPackage dPkg, Authentication id) throws Exception {
    System.gc(); //make some space
    ConflictBase conflict;
    ConflictReportBase report = new ConflictReportBase();
    //PackagingSvc.retrievePackage(dPkg);
    PackagingSvc.unpackTarballs();
    PackageSourceBase pkg = new PackageSourceBase();
    pkg.setName(dPkg.getName());
    SortedMap toImport = MetadataUtil.organizeChronologically(pkg.listRootComponents(id));
    
    Metadata m;
    Iterator i = toImport.values().iterator(); // buckets, ordered chronologically
    Origin source;
    List bucket=null;
    Iterator j;
    Collection synkRecords;
    Collection conflicts;
    while (i.hasNext()) {
      bucket = (List)i.next();
      j = bucket.iterator();
      while(j.hasNext()) {
        m = (Metadata)j.next();
        source = m.getOrigin();
        synkRecords = SynchronizationSvc.findNameSynchronization(source.getDomainName(), source.getServerName(), source.getDatasourceName(), source.getName());
        if (null!=synkRecords && synkRecords.size()>0) { //check if prior version has been replicated
        }
        else { //this is a first synchronization for this component - check for duplicate name collision in the repository
          conflicts = findConflicts(source.getName(), pkg, id);
          if (null!=conflicts && conflicts.size()>0) report.addAll(conflicts);
          /*
          
          if (contains(source.getName(), id) ) {
            conflict = new ConflictBase(); 
            conflict.setMessageKey("duplicate.name");
            conflict.addParameter(source.getName());
            report.add(conflict);
          }
          */
        }
      }
    }
    if (report.getConflicts().isEmpty()) {
      conflict = new ConflictBase();
      conflict.setType("no-conflicts");
      report.add(conflict);
      return report;
    }      
    return report;
  }

  protected Collection findConflicts(String name, PackageSourceBase pkg, Authentication id) { throw new UnsupportedOperation("findConflicts(name, pkg, id);"); }  
  
  public Collection importDesignPackage(DataPackage dPkg, MetadataMappingSet mappings, boolean lockOnImport, boolean overwriteConflicts, Authentication id) throws Exception {
    return loadDesignPackage(dPkg, mappings, false, lockOnImport, overwriteConflicts, id);
  }

  public Collection importPackage(DataPackage dPkg, MetadataMappingSet mappings, boolean lockOnImport, Authentication id) throws Exception {
    return loadPackage(dPkg, mappings, false, lockOnImport, id);    
  }
  public Collection synchronizePackage(DataPackage dPkg, MetadataMappingSet mappings, boolean lockOnImport, Authentication id) throws Exception {
    return loadPackage(dPkg, mappings, true, lockOnImport, id);
  }

  protected Collection loadDesignPackage(DataPackage dPkg, MetadataMappingSet mappings, boolean justSynchronize, boolean lockOnImport, boolean overwriteConflicts, Authentication id) throws Exception { 
    System.gc(); //make some space
    //+++ parameterize packageSvc.retrievepackage for just import or for full replication.
    //(dark area will not need to retrieve package)
    {}//Logwriter.printOnConsole("Retriving package...");
    PackagingSvc.retrievePackage(dPkg);
    
    PackagingSvc.unpackTarballs();
    PackageSourceBase pkg = new PackageSourceBase();
    pkg.setName(dPkg.getName());
    Collection syncRecords = new Vector();
    //create buckets of data that have the same time-of-creation, order the buckets chonologically
    //(Handles cyclical dependencies - since updated items that depend on each other would have had to be
    //checked in together - having the same timestamp (at least in Intralink).
    //+++lazy load the actual metadata in the package when needed.
    //that way the entire package does not have to be loaded into memory at once.

    //SortedMap toImport = MetadataUtil.organizeChronologically(pkg.listComponents(id));
    Collection packageItems = pkg.listComponents(id);

    if (packageItems.isEmpty()) return syncRecords;
    Metadata m;
    //Iterator i = packageItems.iterator();
    Map sources = new HashMap();
    Origin source, target;
    List bucket=new ArrayList();;
    Iterator j;
    //while (i.hasNext()) {
      //bucket = (List)i.next();
      bucket.addAll(packageItems);
      j = bucket.iterator();
      while(j.hasNext()) {
        m = (Metadata)j.next();
        source = m.getOrigin();
        sources.put(source.getName().toLowerCase(), source); //capture the source origin
        if (SynchronizationSvc.isSynchronizedToDatasource(source, this) || SynchronizationSvc.isIndirectlySynchronizedToDatasource(source, this)) {
            j.remove();
            {}//Logwriter.printOnConsole("Already Synchronized: " + source.getUniqueID());
        }
      }
      //list of origins for the imported bucket
      try { 
	      Collection targets;
	      if (justSynchronize) targets=synchronizeFromPackage(pkg, bucket, mappings, id); //++lazyload metadata here...
	      else targets=importFromPackage(pkg, bucket, mappings, lockOnImport, overwriteConflicts, id); //++lazyload metadata here...
	      j=targets.iterator();
	      while (j.hasNext()) {
	        target=(Origin)j.next();
	        source=(Origin)sources.get(target.getName().toLowerCase());
	        syncRecords.add(SynchronizationSvc.record(source,target));
	      }
	      
	      //send update alert
	      String DOT = ".";
	      String msg = "package: " + pkg.getName() + Names.NEW_LINE;
	      msg += "target: " + getDomainName()+DOT+getServerName()+DOT+getName()+Names.NEW_LINE ;
	      if (targets.size()==0) {
	        msg += "No files were transfered." + Names.NEW_LINE;
	        alert("IER NO UPDATES: Transfered 0 Files To " + getName(), msg, id);
	      }
	      else {
		      j = targets.iterator();
		      while (j.hasNext()) {
		        target = (Origin)j.next();
		        source=(Origin)sources.get(target.getName().toLowerCase());
		        msg += " + " + target.getUniqueIDDisplay() + "==" + source.getUniqueIDDisplay() + Names.NEW_LINE;
		      }
		      msg += Names.NEW_LINE;
		      if(targets.size()==1) alert("IER UPDATES: Transfered 1 File To " + getName(), msg, id);
		      else alert("IER UPDATES: Transfered " + targets.size() + " Files To " + getName(), msg, id);
	      }
	      
	      //clear out some memory
	      bucket.clear();
	      System.gc();
      }
      catch (Exception e) {
        String stop = Properties.get(Names.STOP_PACKAGE_IMPORT_ON_ERROR);
        if (!"false".equalsIgnoreCase(stop)) stop = "true"; //default to T
        boolean stopOnError = "true".equalsIgnoreCase(stop);
        if (stopOnError) throw e;
        else e.printStackTrace();
      }
    //}
    return syncRecords;
  }

  protected void alert(String subject, String message, Authentication id) {
    String msg = message + Names.NEW_LINE;
    msg += Names.NEW_LINE + Names.NEW_LINE+ "________________________________________" +Names.NEW_LINE ;
    msg += "User: " +id.getUsername();
    Alert.notify(subject, msg);    
  }
  
  protected Collection loadPackage(DataPackage dPkg, MetadataMappingSet mappings, boolean justSynchronize, boolean lockOnImport, Authentication id) throws Exception { 
    System.gc(); //make some space
    //+++ parameterize packageSvc.retrievepackage for just import or for full replication.
    //(dark area will not need to retrieve package)
    {}//Logwriter.printOnConsole("Retriving package...");
    PackagingSvc.retrievePackage(dPkg);
    
    PackagingSvc.unpackTarballs();
    PackageSourceBase pkg = new PackageSourceBase();
    pkg.setName(dPkg.getName());
    Collection syncRecords = new Vector();
    //create buckets of data that have the same time-of-creation, order the buckets chonologically
    //(Handles cyclical dependencies - since updated items that depend on each other would have had to be
    //checked in together - having the same timestamp (at least in Intralink).
    //+++lazy load the actual metadata in the package when needed.
    //that way the entire package does not have to be loaded into memory at once.
    SortedMap toImport = MetadataUtil.organizeChronologically(pkg.listComponents(id));
    
    if (toImport.isEmpty()) return syncRecords;
    Metadata m;
    Iterator i = toImport.values().iterator(); // buckets, ordered chronologically
    Map sources = new HashMap();
    Origin source, target;
    List bucket=null;
    Iterator j;
    while (i.hasNext()) {
      bucket = (List)i.next();
      j = bucket.iterator();
      while(j.hasNext()) {
        m = (Metadata)j.next();
        source = m.getOrigin();
        sources.put(source.getName().toLowerCase(), source); //capture the source origin
        if (SynchronizationSvc.isSynchronizedToDatasource(source, this) || SynchronizationSvc.isIndirectlySynchronizedToDatasource(source, this)) {
            j.remove();
            {}//Logwriter.printOnConsole("Already Synchronized: " + source.getUniqueID());
        }
      }
      //list of origins for the imported bucket
      try { 
	      Collection targets;
	      if (justSynchronize) targets=synchronizeFromPackage(pkg, bucket, mappings, id); //++lazyload metadata here...
	      else targets=importFromPackage(pkg, bucket, mappings, lockOnImport, false, id); //++lazyload metadata here...
	      j=targets.iterator();
	      while (j.hasNext()) {
	        target=(Origin)j.next();
	        source=(Origin)sources.get(target.getName().toLowerCase());
	        syncRecords.add(SynchronizationSvc.record(source,target));
	      }
	      
	      //send update alert
	      String DOT = ".";
	      String msg = "package: " + pkg.getName() + Names.NEW_LINE;
	      msg += "target: " + getDomainName()+DOT+getServerName()+DOT+getName()+Names.NEW_LINE ;
	      if (targets.size()==0) {
	        msg += "No files were transfered." + Names.NEW_LINE;
	        alert("IER NO UPDATES: Transfered 0 Files To " + getName(), msg, id);
	      }
	      else {
		      j = targets.iterator();
		      while (j.hasNext()) {
		        target = (Origin)j.next();
		        source=(Origin)sources.get(target.getName().toLowerCase());
		        msg += " + " + target.getUniqueIDDisplay() + "==" + source.getUniqueIDDisplay() + Names.NEW_LINE;
		      }
		      msg += Names.NEW_LINE;
		      if(targets.size()==1) alert("IER UPDATES: Transfered 1 File To " + getName(), msg, id);
		      else alert("IER UPDATES: Transfered " + targets.size() + " Files To " + getName(), msg, id);
	      }
	      
	      //clear out some memory
	      bucket.clear();
	      System.gc();
      }
      catch (Exception e) {
        String stop = Properties.get(Names.STOP_PACKAGE_IMPORT_ON_ERROR);
        if (!"false".equalsIgnoreCase(stop)) stop = "true"; //default to T
        boolean stopOnError = "true".equalsIgnoreCase(stop);
        if (stopOnError) throw e;
        else e.printStackTrace();
      }
    }
    return syncRecords;
  }

/*
  public Collection synchronizePackage(DataPackage dPkg, MetadataMappingSet mappings, Authentication id) throws Exception {
    PackagingSvc.retrievePackage(dPkg);
    PackageSourceBase pkg = new PackageSourceBase();
    pkg.setName(dPkg.getName());
    Collection syncRecords = new Vector();
    
    Collection c = MetadataUtil.sortByDependency(pkg.listComponents(id));
    
    PrintUtil.print(c);
    if (null==c || c.size()<1) return syncRecords;
    Iterator i = c.iterator();
    Metadata m;
    i=c.iterator();
    Origin source, target=null;
    long len;
    InputStream stream;
    while (i.hasNext()) {
      m = (Metadata)i.next();
      source = m.getOrigin();
      if (!SynchronizationSvc.isSynchronizedToDatasource(source, this) || !containsBinary(source, id) ) {
        if (!SynchronizationSvc.isIndirectlySynchronizedToDatasource(source, this)) target=importFromPackage(pkg, m, mappings, id);
        //if (null==target) try {target = lookupOrigin(source.getUniqueID(), id); } catch (InvalidValue e) {;}
        if (null!=target) syncRecords.add(SynchronizationSvc.record(source,target)); //+++ use the client!!
      }
    }
    return syncRecords;
  }
*/  
  
  /*
  public Collection importEventPackage(DataPackage dPkg, MetadataMappingSet mappings, Authentication id) throws Exception {
    PackagingSvc.retrievePackage(dPkg);
    PackageSourceBase pkg = new PackageSourceBase();
    pkg.setName(dPkg.getName());
    Collection syncRecords = new Vector();
    Collection c = MetadataUtil.sortByDependency(pkg.listComponents(id));
    PrintUtil.print(c);
    
    if (null==c || c.size()<1) return syncRecords;
    Iterator i = c.iterator();
    Metadata m;
    Origin source, target=null;
    long len;
    InputStream stream;
    while (i.hasNext()) {
      m = (Metadata)i.next();
      source = m.getOrigin();
      if (!SynchronizationSvc.isSynchronizedToDatasource(source, this) || !containsBinary(source, id) ) {
        if (!SynchronizationSvc.isIndirectlySynchronizedToDatasource(source, this)) target=importFromPackage(pkg, m, mappings, id);
        //if (null==target) try {target = lookupOrigin(source.getUniqueID(), id); } catch (InvalidValue e) {;}
        if (null!=target) syncRecords.add(SynchronizationSvc.record(source,target)); //+++ use the client!!
      }
    }
    return syncRecords;
  }
*/
  
  // Document Version Control Functionality
  public void checkin(String workspaceName, boolean deleteFiles, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".checkin(workspaceName, deleteFiles, id)");
  }
  public void checkin(Origin o, String workspaceName, boolean deleteFile, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".checkin(origin, workspaceName, deleteFile, id)");
  }
  public void checkinUpdate(String workspaceName, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".checkinUpdate(workspaceName, id)");
  }
  public void checkinUpdate(Origin o, String workspaceName, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".checkinUpdate(origin, workspaceName, id)");
  }
  public void checkout(Origin o, String workspaceName, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".checkout(origin, workspaceName, id)");
  }
  public void getCopy(Origin o, String workspaceName, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".getCopy(origin, workspaceName, id)");
  }
  public void lock(Origin o, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".lock(origin, id)");
  }
  public void unlock(Origin o, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".unlock(origin, id)");
  }
  public void link(Origin source, Origin target, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".link(source, target, id)");
  }
  public void unlink(Origin source, Origin target, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".unlink(source, target, id)");
  }
  
  public Collection listComponents(Authentication id) throws PathDoesNotExist, Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".listComponents(id)");
  }
  public Collection listComponents(String path, Authentication id) throws PathDoesNotExist, Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".listComponents(path, id)");
  }
  public Collection listComponents(String path, boolean recursively, Authentication id) throws PathDoesNotExist, Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".listComponents(path, id)");
  }
  public Collection listSubFolders(String path, boolean recursive, Authentication id) throws PathDoesNotExist, Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".listSubFolders(path, recursive, id)");
  }
  public Collection getFolderListing(String path, boolean recursive, Authentication id) throws PathDoesNotExist, Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".getFolderListing(path, ecursive, id)");
  }
  public void createFolder(String name, String path, Authentication id) throws InvalidName, PathDoesNotExist, DuplicateName, Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".createFolder(name, path, id) ");
  }
  public void deleteFolder(String path, Authentication id) throws PathDoesNotExist, NotEmpty, Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".deleteFolder(path, id) ");
  }
  

  public final Object copy() {
    return this;
//    if (supportsDeepCopy()) return deepCopy();
//    return shallowCopy();
  }

  public Object shallowCopy(){
    return this;
/*
    try {return super.clone(); }
    catch (CloneNotSupportedException e){
        e.printStackTrace();
        throw new RuntimeException(e.getMessage()); }
 */
  }
  /*
  public void addSchemaAttribute(KeyValue attribute){
	  schema.put(attribute.getKey(), attribute);
  }
  public Map getSchema(){ return schema; }
*/
  public Object deepCopy(){throw new UnsupportedOperation("deepCopy", "use shallowCopy() or an implementation that supports deepCopy()");}

  public File getDataPath() {
    File f = new File(Properties.get(Names.DATA_DIR));
    if (!f.exists()) f.mkdirs();
    return f;
  }

  public File getDataPath(String subDir) {
    File f = new File(Properties.get(Names.DATA_DIR) + Names.PATH_SEPARATOR + subDir);
    if (!f.exists()) f.mkdirs();
    return f;
  }

  public File getDataPathToFile(String subDir, String filename) {
    File f = getDataPath(subDir); //create necessary directories
    return new File(f, filename);
  }

  private static String findLocalPath(String location) { return Properties.get(Names.LOCAL_DIR) + Names.PATH_SEPARATOR + location; }    
  private static String findLocalPath(String location, String filename) { return Properties.get(Names.LOCAL_DIR) + Names.PATH_SEPARATOR + location + Names.PATH_SEPARATOR + filename; }    

  public boolean getUpperCasedFilenames() { return upperCasedFilenames; }
  public void setUpperCasedFilenames(boolean b) { upperCasedFilenames = b; }
  public boolean getLowerCasedFilenames() { return lowerCasedFilenames; }
  public void setLowerCasedFilenames(boolean b) { lowerCasedFilenames=b; }
 
  private boolean upperCasedFilenames = false;
  private boolean lowerCasedFilenames = false;
  private String replicationDependencyConfiguration=AS_STORED; 
  private String description=null;

  public static String AS_STORED ="as-stored";
  public static String LATEST = "latest";
  
  protected static synchronized long getNewCount() { return count++; }
  protected static long count;
  protected HashMap schema = new HashMap();
}