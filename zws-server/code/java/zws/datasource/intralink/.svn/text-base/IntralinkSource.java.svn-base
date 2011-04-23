package zws.datasource.intralink;/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Alert;
import zws.Server;
import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.attribute.Attribute;
import zws.attribute.Enumeration;
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataFamilyInstance;
import zws.data.MetadataFamilyInstanceBase;
import zws.data.MetadataSubComponent;
import zws.data.MetadataSubComponentBase;
import zws.data.workspace.Workspace;
import zws.data.workspace.WorkspaceConflict;
import zws.data.workspace.WorkspaceItem;
import zws.datasource.DatasourceBase;
import zws.datasource.agile.AgileSource;
import zws.datasource.filesystem.FileSystemSource;
import zws.datasource.filesystem.op.DWG2PDF;
import zws.datasource.filesystem.op.DWGLayouts2PDF;
import zws.datasource.intralink.op.commonspace.CommonSpaceDelete;
import zws.datasource.intralink.op.commonspace.CreateBaseline;
import zws.datasource.intralink.op.commonspace.CreateFolder;
import zws.datasource.intralink.op.commonspace.DeleteBaseline;
import zws.datasource.intralink.op.commonspace.DeleteFolder;
import zws.datasource.intralink.op.commonspace.Demote;
import zws.datasource.intralink.op.commonspace.DemoteInstance;
import zws.datasource.intralink.op.commonspace.Find;
import zws.datasource.intralink.op.commonspace.FindLatest;
import zws.datasource.intralink.op.commonspace.GetDependencies;
import zws.datasource.intralink.op.commonspace.GetFolderListing;
import zws.datasource.intralink.op.commonspace.GetFolderTree;
import zws.datasource.intralink.op.commonspace.ListAttributes;
import zws.datasource.intralink.op.commonspace.ListNames;
import zws.datasource.intralink.op.commonspace.Lock;
import zws.datasource.intralink.op.commonspace.Move;
import zws.datasource.intralink.op.commonspace.Promote;
import zws.datasource.intralink.op.commonspace.PromoteFormSearch;
import zws.datasource.intralink.op.commonspace.PromoteInstance;
import zws.datasource.intralink.op.commonspace.Rename;
import zws.datasource.intralink.op.commonspace.ReportBill;
import zws.datasource.intralink.op.commonspace.ReportLatestBill;
import zws.datasource.intralink.op.commonspace.SetLifeCycleAttribute;
import zws.datasource.intralink.op.commonspace.UnSetLifeCycleAttribute;
import zws.datasource.intralink.op.commonspace.Unlock;
import zws.datasource.intralink.op.workspace.ASMPRT2IDF;
import zws.datasource.intralink.op.workspace.ASMPRT2IGES;
import zws.datasource.intralink.op.workspace.ASMPRT2Neutral;
import zws.datasource.intralink.op.workspace.ASMPRT2STEP;
import zws.datasource.intralink.op.workspace.AdoptGhosts;
import zws.datasource.intralink.op.workspace.AutoPartCreationOp;
import zws.datasource.intralink.op.workspace.Checkin;
import zws.datasource.intralink.op.workspace.Checkout;
import zws.datasource.intralink.op.workspace.CheckoutLatest;
import zws.datasource.intralink.op.workspace.CheckoutList;
import zws.datasource.intralink.op.workspace.CreateWorkspace;
import zws.datasource.intralink.op.workspace.DRW2CGM;
import zws.datasource.intralink.op.workspace.DRW2DWG;
import zws.datasource.intralink.op.workspace.DRW2DXF;
import zws.datasource.intralink.op.workspace.DRW2HPGL;
import zws.datasource.intralink.op.workspace.DRW2PS;
import zws.datasource.intralink.op.workspace.DestroyWorkspace;
import zws.datasource.intralink.op.workspace.FindInWorkspace;
import zws.datasource.intralink.op.workspace.HoldGhosts;
import zws.datasource.intralink.op.workspace.Import;
import zws.datasource.intralink.op.workspace.Link;
import zws.datasource.intralink.op.workspace.ListWorkspaceContents;
import zws.datasource.intralink.op.workspace.ListWorkspaces;
import zws.datasource.intralink.op.workspace.ModelCheck;
import zws.datasource.intralink.op.workspace.ProEConverter;
import zws.datasource.intralink.op.workspace.RemoveFromWorkspace;
import zws.datasource.intralink.op.workspace.RemoveQuarantinedDuplicates;
import zws.datasource.intralink.op.workspace.ReportAllWorkspaceStatus;
import zws.datasource.intralink.op.workspace.ReportWorkspaceStatus;
import zws.datasource.intralink.op.workspace.SetAttribute;
import zws.datasource.intralink.op.workspace.SetAttributeList;
import zws.datasource.intralink.op.workspace.mdimpexExport;
import zws.datasource.intralink.op.workspace.mdimpexExportList;
import zws.datasource.intralink.op.workspace.mdimpexImport;
import zws.datasource.intralink.op.workspace.mdimpexImportFamilyTable;
import zws.datasource.intralink.op.workspace.mdimpexImportList;
import zws.datasource.intralink.op.workspace.mdimpexImportListFamilyTable;
import zws.datasource.pkg.PackageSource;
import zws.datasource.pkg.PackageSourceBase;
import zws.exception.DuplicateName;
import zws.exception.EmptyDirectory;
import zws.exception.FailedToExportBinaryFiles;
import zws.exception.FailedToTransferFile;
import zws.exception.InvalidConfiguration;
import zws.exception.InvalidName;
import zws.exception.InvalidState;
import zws.exception.IsLocked;
import zws.exception.MatchNotFound;
import zws.exception.MultipleVersionsOfSameFile;
import zws.exception.NameNotFound;
import zws.exception.NoSuchType;
import zws.exception.NotAFile;
import zws.exception.NotConnected;
import zws.exception.NotEmpty;
import zws.exception.PathDoesNotExist;
import zws.exception.Unmodifyable;
import zws.folder.IntralinkFolder;
import zws.log.failure.Failure;
import zws.mapping.MetadataMappingSet;
import zws.origin.IntralinkOrigin;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.origin.WorkspaceOrigin;
import zws.replication.report.ConflictBase;
import zws.search.filter.LatestByKey;
import zws.security.Authentication;
import zws.service.datasource.DatasourceSvc;
import zws.service.document.DocumentSvc;
import zws.service.search.SearchAgentSvc;
import zws.service.synchronization.SynchronizationSvc;
import zws.util.DeleteFile;
import zws.util.DomainContext;
import zws.util.FileNameUtil;
import zws.util.FileUtil;
//import zws.util.{}//Logwriter;
import zws.util.MapUtil;
import zws.util.MetadataUtil;
import zws.util.PrintUtil;
import zws.util.ZipWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

public class IntralinkSource extends DatasourceBase {

  public String getName() {
    if (null==alias) return super.getName();
    return alias;
  }

  public String getLicensePoolName() {
    if (null==licensePoolName) return getName();
    return licensePoolName;
  }

  public void setLicensePoolName(String s) {
    if (null==s || "".equals(s.trim())) licensePoolName=null;
    licensePoolName=s;
  }

  public String getAlias() { return alias; }
  public void setAlias(String s) { alias=s; }

  private EventListener eventListener = null;
  public int getServerMinutesOffset() { return minutesOffset; }
  public void setServerMinutesOffset(int i) { minutesOffset=i; }

  public IntralinkSource() {
    eventListener = new EventListener(this);
    if (!getActivateEventWatcher()) return;
    try {startEventListener(); }
    catch (InvalidState e) { e.printStackTrace(); }
  }

  public int getMaxLicenses() { return maxLicenses; }
  public void setMaxLicenses(int i) { maxLicenses=i; }

  public void configure() throws InvalidConfiguration {
    IlinkTKLicensePool.initializeTokenPool(getName(), getLicensePoolName(), getMaxLicenses());
    try {
      SearchAgent agent = (SearchAgent)materializeSearchAgent();
      if (null!=agent && null!=agent.getName()) SearchAgentSvc.add(agent);

      SearchAgent latestAgent = (SearchAgent)materializeLatestSearchAgent();
      if (null!=latestAgent && null!=latestAgent.getName()) SearchAgentSvc.add(latestAgent);

      SearchAgent latestRevAgent = (SearchAgent)materializeLatestRevSearchAgent();
      if (null!=latestRevAgent && null!=latestRevAgent.getName()) SearchAgentSvc.add(latestRevAgent);

      //add other default agents ...
      //latest only
      //baseline/history
    }
    catch (DuplicateName e) { throw new InvalidConfiguration(e.getMessage()); }
  }

  public void inactivate() {
    {}//Logwriter.printOnConsole("!!!!!Inactivating Datasource: " + getName());
    //try { throw new Exception("Inactivating Datasource" + getName()); } catch (Exception e) {e.printStackTrace(); }
    
    //if (true) throw new RuntimeException("****!Inactivating Datasource: " + getName());
    if (null==eventListener) return;
    try {
      stopEventListener();
      eventListener=null;
    }
    catch (Exception ignore) { }
    try {
      SearchAgent agent = (SearchAgent)materializeSearchAgent();
      if (null!=agent && null!=agent.getName()) SearchAgentSvc.remove(agent);
    }
    catch (Exception ignore) { }
    try {
      SearchAgent latestAgent = (SearchAgent)materializeLatestSearchAgent();
      if (null!=latestAgent && null!=latestAgent.getName()) SearchAgentSvc.remove(latestAgent);
    }
    catch (Exception ignore) { }
    try {
      SearchAgent latestRevAgent = (SearchAgent)materializeLatestRevSearchAgent();
      if (null!=latestRevAgent && null!=latestRevAgent.getName()) SearchAgentSvc.remove(latestRevAgent);
    }
    catch (Exception ignore) { }
  }

  public String getType() { return Origin.FROM_ILINK; }

  public void pauseEventListener() throws InvalidState { eventListener.suspend(); }
  public void resumeEventListener() throws InvalidState { eventListener.resume(); }
  public void stopEventListener() throws InvalidState { eventListener.stop(); }
  public void startEventListener() throws InvalidState { eventListener.start(); }
  public String getEventListenerRunningState() { return eventListener.getRunningState(); }
  public String getEventListenerEventFiringState() { return eventListener.getEventFiringState(); }
  public void ignoreEventListenerEvents() { eventListener.ignoreEvents(); }
  public void fireEventListenerEvents() { eventListener.fireEvents(); }

  public Collection getEventListenerHistoryLog() { return eventListener.getHistoryLog(); }
  public int getEventListenerHistoryLogDuration() { return eventListener.getHistoryLogDuration(); }
  public void setEventListenerHistoryLogDuration(int hours) { eventListener.setHistoryLogDuration(hours); }
  public int getEventListenerRunPeriod() { return eventListener.getRunPeriod(); }
  public void setEventListenerRunPeriod(int seconds) { eventListener.setRunPeriod(seconds); }

  public Collection getEventListenerTargetQueueNodes() { return eventListener.getTargetQueueNodes(); }
  public void addEventListenerTargetQueueNode(String serverNode) { eventListener.addTargetQueueNode(serverNode); }
  public void removeEventListenerTargetQueueNode(String serverNode) { eventListener.removeTargetQueueNode(serverNode); }

  public zws.search.SearchAgent materializeSearchAgent() {
    SearchAgent agent = new SearchAgent();
    agent.setDatasource(this);
    agent.setName(getName());
    Iterator i = getUserDefinedAttributes().iterator();
    String select = NAME+","+BRANCH+","+REVISION+","+VERSION+","+CREATED_ON+","+CREATED_BY+","+RELEASE_LEVEL+","+FOLDER+","+GENERIC+","+INSTANCE+","+DESCRIPTION;
    while (i.hasNext()) select+=","+i.next();
    agent.setSelect(select);
    return agent;
  }

  public zws.search.SearchAgent materializeLatestSearchAgent() {
	  SearchAgent agent = (SearchAgent) materializeSearchAgent();
	  agent.setName(getName()+"-latest");
	  LatestByKey latestFilter = new LatestByKey();
	  latestFilter.setKeyFields("name | branch");    
	  agent.add(latestFilter);
	  return agent;
	}

  public zws.search.SearchAgent materializeLatestRevSearchAgent() {
	  SearchAgent agent = (SearchAgent) materializeSearchAgent();
	  agent.setName(getName()+"-latest-rev");
	  LatestByKey latestFilter = new LatestByKey();
	  latestFilter.setKeyFields("name | branch | rev");    
	  agent.add(latestFilter);
	  return agent;
	}

  public boolean contains(String name, Authentication id) {
    try {
      findLatest(name, id);
      return true;
    }
    catch (Exception e) { return false; }
  }

  /* Agile Integration
	static IAgileSession session=null; 
  
  public static IAgileObject createAgileObject(String name ) throws APIException {
    Map m = new HashMap();
    m.put(ItemConstants.ATT_TITLE_BLOCK_NUMBER,  name);
    m.put(ItemConstants.ATT_TITLE_BLOCK_DESCRIPTION, "hello worlds");
    return session.createObject(ItemConstants.CLASS_PART, m);
  }
  
public static void agilePutChildren (String parentNum, Vector childNums) {
		IItem parentItem;
		IRow childRow;
		ITable bomTable;
		HashMap params;
		int childSize = 0;
		
		try {
			parentItem = (IItem)session.getObject(ItemConstants.CLASS_PART, parentNum);
      if (parentItem == null) {
        Map m = new HashMap();
        m.put(ItemConstants.ATT_TITLE_BLOCK_NUMBER,  parentNum);
        m.put(ItemConstants.ATT_TITLE_BLOCK_DESCRIPTION, "assembly");
        parentItem = (IItem)session.createObject(ItemConstants.CLASS_PART, m);
      }
      if (parentItem != null) {
        String parentItemName = parentItem.getName();
        {}//Logwriter.printOnConsole("PutChildren parentItemName : " + parentItemName);
        bomTable = (ITable)parentItem.getTable(ItemConstants.TABLE_BOM);
			
      
        childSize = childNums.size();
        for (int i = 0; i < childSize; i++) {
          params = new HashMap();
          String childNum = (String)childNums.get(i);
        	params.put(ItemConstants.ATT_BOM_ITEM_NUMBER, childNum);
          params.put(ItemConstants.ATT_BOM_QTY, "1");
          parentItem.setManufacturingSite(ManufacturingSiteConstants.COMMON_SITE);
          IRow row = bomTable.createRow(params);	
        }
      }
      else {
       
        {}//Logwriter.printOnConsole("Parent Item Does not exist...");
        
      }
		
		}
		catch (APIException apiEx) {
		
			{}//Logwriter.printOnConsole("PutChildren error : " + apiEx.getMessage());	
		}	 
		
	}
  
	public static boolean AgileConnect(String url, String user, String pass) {		
		String testUrl = "http://plm.agilesoft.com/Agile";
		try {
			{}//Logwriter.printOnConsole("url : " + url);
			//AgileSessionFactory factory = AgileSessionFactory.getInstance(url);
			//test factory
			AgileSessionFactory factory = AgileSessionFactory.getInstance(testUrl);
			HashMap map = new HashMap();
			map.put(AgileSessionFactory.USERNAME, user);
			map.put(AgileSessionFactory.PASSWORD, pass);
			session = factory.createSession(map);
			boolean connected = false;
			connected = session.isOpen();
			{}//Logwriter.printOnConsole("connected  : " + connected);
			return connected;
		}
		catch (APIException apiEx) {
			{}//Logwriter.printOnConsole("Login error : " + apiEx.getMessage());
			return false;
		}
	}
	
	public static void disconnect()
	{
		boolean connected;	
		session.close();
		try {
			connected = session.isOpen();
			{}//Logwriter.printOnConsole("connected  : " + connected);
		}
		catch (APIException apiEx) { apiEx.printStackTrace(); }
	}
*/  


  public void verifyConfiguration() throws InvalidConfiguration { return; }
  public void verifyConnection() throws NotConnected { return; }

  /*
  public Origin createOrigin(String branch, String name, String revision, String version, String createdOn) throws Exception {
    String n = objectName(name);
    long dateCreated=0;    
    try {dateCreated = parseDate(createdOn); }
    catch (Exception e) {}
    String delim = Names.ORIGIN_DELIMITER;
    Origin o = new Origin(Properties.get(Names.SERVER_NAME), getName(), Origin.ILINK, dateCreated, branch+delim+n+delim+revision+delim+version);
    o.setName(n);
    return o;
  }

  public Origin createOrigin(Metadata data) throws Exception {
    String branch, name, revision, version, createdOn;
    branch = data.get(BRANCH);
    name = objectName(data.getName());
    revision = data.get(REVISION);
    version = data.get(VERSION);
    createdOn = data.get(CREATED_ON);
    Origin o = createOrigin(branch, name, revision, version, createdOn);
    o.setName(name);
    return o;
  }
  */
  /*
  public static String getBranchFromOrigin( Origin o) throws InvalidValue { return getBranchFromUniqueID(o.getUniqueID()); }
  public static String getBranchFromUniqueID( String uid ) throws InvalidValue {
    StringTokenizer tokens = new StringTokenizer(uid, Names.ORIGIN_DELIMITER);
    if (tokens.hasMoreTokens()) return tokens.nextToken();
    throw new InvalidValue(uid);
  }
  public static String getNameFromOrigin( Origin o ) throws InvalidValue { return getNameFromUniqueID(o.getUniqueID()); }
  public static String getNameFromUniqueID( String uid ) throws InvalidValue {
    StringTokenizer tokens = new StringTokenizer(uid, Names.ORIGIN_DELIMITER);
    if (tokens.hasMoreTokens()) tokens.nextToken();
    if (tokens.hasMoreTokens()) return tokens.nextToken();
    throw new InvalidValue(uid);
  }
  public static String getRevisionFromOrigin( Origin o ) throws InvalidValue { return getRevisionFromUniqueID(o.getUniqueID()); }
  public static String getRevisionFromUniqueID( String uid ) throws InvalidValue {
    StringTokenizer tokens = new StringTokenizer(uid, Names.ORIGIN_DELIMITER);
    if (tokens.hasMoreTokens()) tokens.nextToken();
    if (tokens.hasMoreTokens()) tokens.nextToken();
    if (tokens.hasMoreTokens()) return tokens.nextToken();
    throw new InvalidValue(uid);
  }  
  public static String getVersionFromOrigin( Origin o ) throws InvalidValue { return getVersionFromUniqueID(o.getUniqueID()); }
  public static String getVersionFromUniqueID( String uid) throws InvalidValue {
    StringTokenizer tokens = new StringTokenizer(uid, Names.ORIGIN_DELIMITER);
    if (tokens.hasMoreTokens()) tokens.nextToken();
    if (tokens.hasMoreTokens()) tokens.nextToken();
    if (tokens.hasMoreTokens()) tokens.nextToken();
    if (tokens.hasMoreTokens()) return tokens.nextToken();
    throw new InvalidValue(uid);
  }
*/
  public String workspaceName(IntralinkOrigin o) {
    //++ modify to use uniqueSeq stead of ilinkorigin
    String branch = ((IntralinkOrigin)o).getBranch();
    if (branch.equalsIgnoreCase("main")) branch ="";
    String workspace = objectName(o.getName())+branch+((IntralinkOrigin)o).getRevision()+((IntralinkOrigin)o).getVersion();
    if (25 < workspace.length()) workspace = workspace.substring(0,24); // hopefully this wont conflict with another.
    workspace = workspace.replace('.', '_');
    return workspace;
  }

  public InputStream findBinary(Origin o, Authentication id) throws Exception {
    return findBinary(o, true, id);
  }
  public InputStream findBinary(Origin o, boolean destroyWorkspaceOnCompletion, Authentication id) throws Exception {
    File tempDir = new File(Properties.get(Properties.tempDirectory)+Names.PATH_SEPARATOR + "binary_" + getNewCount());
    tempDir.mkdirs();
    return new FileInputStream(exportBinary(o, tempDir, destroyWorkspaceOnCompletion, id));
  }
  
  public InputStream findBinary(Origin o, String dependencies, boolean associateInstances, boolean override, boolean metadataOnly, boolean destroyWorkspaceOnCompletion, Authentication id) throws Exception {
    File tempDir = new File(Properties.get(Properties.tempDirectory)+Names.PATH_SEPARATOR + "binary_" + getNewCount());
    if (tempDir.exists()) deleteDir(tempDir);
    tempDir.mkdirs();
    return new FileInputStream(exportBinary(o, dependencies, associateInstances, override, metadataOnly, tempDir, destroyWorkspaceOnCompletion, id));
  }

  public File exportBinary(Origin o, File outputDir, Authentication id) throws Exception {
    return exportBinary(o, outputDir, true, id);
  }
  public File exportBinary(Origin o, File outputDir, boolean destroyWorkspaceOnCompletion, Authentication id) throws Exception {
    return exportBinary(o, ALL_DEPENDENCIES, true,  true, false, outputDir, destroyWorkspaceOnCompletion, id);
  }
  
  public synchronized File exportBinary(Origin o, String dependencies, boolean associateInstances, boolean override, boolean metadataOnly, File outputDir, boolean destroyWorkspaceOnCompletion, Authentication id) throws Exception {
    //long unique = getNewCount();
    String workspace=null;
    boolean usePersonalWorkspace=false;
    if (o instanceof IntralinkOrigin) workspace = workspaceName((IntralinkOrigin)o);
    if (o instanceof WorkspaceOrigin) {
      workspace = ((WorkspaceOrigin)o).getWorkspace();
      usePersonalWorkspace=true;
    }
    //String workspace = workspaceName((IntralinkOrigin)o);
    File exportDir = new File(Properties.get(Properties.tempDirectory)+Names.PATH_SEPARATOR + nextCount() + "export_" + workspace);
    deleteDir(exportDir);
    if (!usePersonalWorkspace) { //create a temporary workspace and check out the file
      createWorkspace(workspace, id);
      checkout(o, workspace, dependencies, associateInstances, override, metadataOnly, id);
    }
    //checkout(o, workspace, ALL_DEPENDENCIES, associateInstances, override, false, id);
    export(workspace, exportDir.getAbsolutePath(), o.getName(), dependencies, usePersonalWorkspace, id);
    File binary=null;
    //check to see if files exported successfully:
    // - at least one file exists in export dir
    // - all files in export dir have size>0
    boolean exportOK = true;
    File[] files = exportDir.listFiles();
    if (1 > files.length) exportOK = false;
    else {
      int idx = 0;
      while ( exportOK && idx < files.length)
        if ( 1 > files[idx++].length() ) exportOK = false;
    }
    if (exportOK) binary = createBinary(exportDir, outputDir, objectName(o.getName()));
    else {
      File workspaceDir = getWorkspacePath(workspace, id);
      binary = createBinary(workspaceDir, outputDir, objectName(o.getName()));
    }
    if (!usePersonalWorkspace) destroyWorkspace(workspace, id); //delete the temporary workspace
    deleteDir(exportDir);
    return binary;
  }

  public File getWorkspacePath(String workspaceName, Authentication id) {
	  String sep = Names.PATH_SEPARATOR;
	  File dotProI = new File(getLDBLocation(workspaceName, id), ".proi");
	  if (!dotProI.exists()) dotProI.mkdirs();
	  return new File (dotProI, workspaceName);
	 }
  
  public File getLDBLocation(String workspaceName, Authentication id) {
    return this.getTransitoryLDBLocation(id.getUsername(), workspaceName);
    /*
	  String user = null;
    if (null==id) user = getDefaultUsername();
    else user = id.getUsername();
	  String sep = Names.PATH_SEPARATOR;
	  String repository = getName();
	  String ws = workspaceName;
	  String userspaces = Properties.get(Names.USER_SPACE);
	  if (null==userspaces || "".equals(userspaces))
	    userspaces = Properties.get(Names.DATA_DIR) + sep + "space" + sep + "user";
	  String ldbPath = userspaces + sep + user + sep + "workspace" + sep + repository + sep + ws;
	  File ldb = new File(ldbPath);
	  File dotProI = new File(ldbPath, ".proi");
	  if (!dotProI.exists()) dotProI.mkdirs();
	  return ldb;
	  */
	 }
  
  private File createBinary(File inputDir, File outputDir, String zipFilename) throws Exception {
    if (null==inputDir) throw new zws.exception.NotADirectory(inputDir);
    if (!inputDir.exists() || !inputDir.isDirectory()) throw new zws.exception.NotADirectory(inputDir);
    File binary;
    if (0 == inputDir.listFiles().length) throw new EmptyDirectory(inputDir);
    try {
	    if (1 == inputDir.listFiles().length) { 
	      binary = new File(outputDir, inputDir.listFiles()[0].getName());
	      //+++ eliminate proi numeric extentions - e.g.: file.prt.1
	      inputDir.listFiles()[0].renameTo(binary);
	      //Problem w/ auto moving a file out of a workspace is that the component may have been renamed.
	      //using mdimpex to export the file will rename the binary to match the component.
	      //so watch for this open issue, or fix it by looking the the component name and renaming the binary to match (if necessary)
	    }
	    else {
	      binary = new File(outputDir, zipFilename + ".zip");
	      ZipWriter zip = new ZipWriter();
	      zip.setSource(inputDir);
	      zip.setZipFilename(binary.getAbsolutePath());
	      zip.execute();
	    }
	    return binary;
    }
    catch(Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  private synchronized void deleteDir(File dir) throws Exception {
    if (dir.exists()) {
      DeleteFile cleaner = new DeleteFile();
      cleaner.setFile(dir);
      cleaner.setDeleteIfNotEmpty(true);
      cleaner.execute();
    }
  }    

  public boolean containsBinary(Origin o, Authentication id) { try {return (null==find(o,id)); } catch (Exception e) { return false; } }
  /*
  public Origin lookupOrigin(String uid, Authentication id) throws Exception { 
    Metadata m = find(uid,id);
    if (null==m) return null;
    return m.getOrigin();
  }
   */

  public Metadata findLatest(String name, Authentication id) throws Exception {
    FindLatest op = new FindLatest();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.setComponentName(objectName(name));
    op.execute();
    Collection results = op.getResults();
    if (null==results || 0==results.size()) throw new NameNotFound(name);
    return (Metadata) results.toArray()[0];
  }

  public Metadata find(Origin o, Authentication id) throws Exception {
    String n=objectName(o.getName());
    String branch = ((IntralinkOrigin)o).getBranch();
    String revision = ((IntralinkOrigin)o).getRevision();
    int version = ((IntralinkOrigin)o).getVersion();
    Find op = new Find();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.setComponentName(n);
    op.setBranch(branch);
    op.setRevision(revision);
    op.setVersion(version);
    op.execute();
    Collection results = op.getResults();
    if (null==results || 0==results.size()) return null;
    MetadataBase m = (MetadataBase) results.toArray()[0];
    m.set(Names.METADATA_DOMAIN_NAME, Server.getDomainName());
    m.set(Names.METADATA_SERVER_NAME, Server.getName());
    m.set(Names.METADATA_DATASOURCE_NAME, getName());
    return m;
  }

  public void add(StartPartDefinition x) { startParts.put(x.getType().toLowerCase(), x); }
  
  public Origin createNewProEModel(Metadata data, Authentication id) throws Exception {
    String type= data.get("type");
    if (null==type|| "".equals(type.trim())) throw new NoSuchType(type);
    
    String newPart = data.get("new-name");
    if (null==newPart || "".equals(newPart.trim())) throw new InvalidName("new-name is null! " + data.toString());
    StartPartDefinition startDef = (StartPartDefinition) startParts.get(type.toLowerCase());
    String startPart = startDef.getStartPartName(data);

    //String folder = data.get("mcad-folder");
    //if (null==folder || "".equals(folder.trim())) folder = startDef.getNewPartLocation();
    String folder = startDef.getNewPartLocation();
    Map ilinkAtts = new HashMap();
    ilinkAtts.put("folder", folder);
    //ilinkAtts.put("validated", "false");

    Map proParameters = startDef.getProParameters(data);
    //+++tobuild
    //return createNewProEModel(startPart, newPart, ilinkAtts, proParameters, id);
    
    {}//Logwriter.printOnConsole("::::Start PART");
    {}//Logwriter.printOnConsole("::::Type: " + type );
    {}//Logwriter.printOnConsole("::::Start:" + startPart);
    {}//Logwriter.printOnConsole("::::Name:" + newPart);
    {}//Logwriter.printOnConsole("::::Location:" + folder);
    PrintUtil.print("Pro Parameters", proParameters);
    return createNewProEModel(startPart, newPart, ilinkAtts, proParameters, id);
  }
  
  public Origin createNewProEModel(String prototypePart, String newPart, Map ilinkAttributes, Map proParameters, Authentication id) throws Exception {
    try {
      findLatest(newPart+".prt", id);
      throw new DuplicateName(newPart);
    }
    catch (NameNotFound ignore) {}
    try {
	    findLatest(newPart+".drw", id);
	    throw new DuplicateName(newPart);
	  }
	  catch (NameNotFound ignore) {}
    String workspace = "createnewpart";
    createWorkspace(workspace, id);
    checkout(prototypePart+".drw", "main", workspace, ALL_DEPENDENCIES, true, true, false, id);
    createNewProEModel(workspace, prototypePart, newPart, proParameters, id);
    setAttributes(workspace, newPart+".prt", ilinkAttributes, id);
    setAttributes(workspace, newPart+".drw", ilinkAttributes, id);
    checkin(workspace, id);
    Metadata data;
    data=findInWorkspace(workspace, newPart+".drw", false, id);
    data=findInWorkspace(workspace, newPart+".prt", false, id);
    return data.getOrigin();
  }

  void createNewProEModel(String workspace, String prototypePart, String newPart, Map proParameters, Authentication id) throws Exception {
	  AutoPartCreationOp op = new AutoPartCreationOp();
	  op.setDatasource(this);
	  op.setAuthentication(lookupID(id));
	  op.setWorkspaceName(workspace);
	  op.setStartPart(prototypePart);
	  op.setNewPartName(newPart);
	  op.setProParameters(proParameters);
	  op.execute();
  }
  
  
  private static int count = 0;
  private static synchronized long nextCount() { return count++; }

  public void makeImageSnapshot(Origin o, String outputType, String targetDatasource, Authentication id) throws Exception {
    if ("paper-space-pdfs".equalsIgnoreCase(outputType)) {
      makePaperSpaceSnapshots(o, targetDatasource, id);
      return;
    }
    String imageNamingConvention=null;
    if ("pdf".equalsIgnoreCase(outputType))  imageNamingConvention=getDefaultPDFNamingConvention();
    else if ("ps".equalsIgnoreCase(outputType))  imageNamingConvention=getDefaultPSNamingConvention();
    else if ("hpg".equalsIgnoreCase(outputType)) imageNamingConvention=getDefaultHPGNamingConvention();
    //else if ("hpgl".equalsIgnoreCase(outputType)) imageNamingConvention=getDefaultHPGNamingConvention();
    else if ("cgm".equalsIgnoreCase(outputType)) imageNamingConvention=getDefaultCGMNamingConvention();
    else if ("igs".equalsIgnoreCase(outputType)) imageNamingConvention=getDefaultIGSNamingConvention();
    //else if ("iges".equalsIgnoreCase(outputType)) imageNamingConvention=getDefaultIGSNamingConvention();
    else if ("stp".equalsIgnoreCase(outputType)) imageNamingConvention=getDefaultSTPNamingConvention();
    //else if ("step".equalsIgnoreCase(outputType)) imageNamingConvention=getDefaultSTPNamingConvention();
    else if ("neu".equalsIgnoreCase(outputType)) imageNamingConvention=getDefaultNEUNamingConvention();
    else if ("idf".equalsIgnoreCase(outputType)) imageNamingConvention=getDefaultIDFNamingConvention();
    else if ("dxf".equalsIgnoreCase(outputType)) imageNamingConvention=getDefaultDXFNamingConvention();
    else if ("dwg".equalsIgnoreCase(outputType)) imageNamingConvention=getDefaultDWGNamingConvention();
    else throw new InvalidConfiguration("output type="+outputType);
    if (null==imageNamingConvention || "".equals(imageNamingConvention)) throw new InvalidConfiguration("Image Naming Convention Not Defined for " + outputType);
    makeImageSnapshot(o, imageNamingConvention, null, deleteOldPDFImage, targetDatasource, id);
  }

  protected synchronized void makeImageSnapshot(Origin o, String imageNamingConvention, String stampNamingConvention, boolean deleteOldImage, String targetDatasource, Authentication ignoreid) throws Exception {
    if (null==imageNamingConvention || "".equals(imageNamingConvention)) throw new InvalidConfiguration("Image Naming Convention Not Defined");
    Collection synkRecords = SynchronizationSvc.findAllSynchronizationRecords(o);
    //fast check if already synchronized
    if (null!=synkRecords && !synkRecords.isEmpty()) {
      String ext =FileNameUtil.getFileNameExtension(imageNamingConvention);
      if (null!=ext) {
        Origin imageOrigin;
        Iterator j = synkRecords.iterator();
        while (j.hasNext()) {
          imageOrigin = (Origin) j.next();
          if (ext.equalsIgnoreCase(FileNameUtil.getFileNameExtension(imageOrigin.getName()))) {
            {}//Logwriter.printOnConsole(o.getName()+ " already synchronized to " + imageOrigin.getName());        
            return;
          }
        }
      }
    }
    //--fast Check if already synchronized
    
    Authentication id = null;
    String workspace = "image" + nextCount();
    Metadata source = find(o, id);
    String imageName = MetadataUtil.parseNamingGrammar(source, imageNamingConvention);  
    String viewable = objectName(imageName);
    
    //slow Check if already synchronized (lookup synchronized file)
    if (null!=synkRecords) {
      Iterator i = synkRecords.iterator();
      Origin synch;
      while (i.hasNext()){
        synch = (Origin)i.next();
        if (viewable.equals(objectName(synch.getName()))) {
          {}//Logwriter.printOnConsole(viewable + " already synchronized:");
          {}//Logwriter.printOnConsole(o);
          {}//Logwriter.printOnConsole(synch);
          //return find(synch,id);
          return;
        }
      }
    }
    //-- slow Check if already synchronized (lookup synchronized file)
    
    destroyWorkspace(workspace, id);
    createWorkspace(workspace, id);
    checkout(o, workspace, ILINK_REQUIRED_DEPENDENCIES, true, true, false, id);
    String sourceType = FileNameUtil.getFileNameExtension(o.getName()).toLowerCase();
    String fileType = FileNameUtil.getFileNameExtension(viewable).toLowerCase();
    File imageLocation = getDataPath("images");
    //+++ check source part type to be drawing (or appropiate)
    if ("pdf".equalsIgnoreCase(fileType)) {
      if ("drw".equalsIgnoreCase(sourceType)) DocumentSvc.convertDRW2PDF(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
      else if ("dwg".equalsIgnoreCase(sourceType)) convertDWG2PDF(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
      if (null!=stampNamingConvention && !stampNamingConvention.trim().equals("")) {
        String stampText = MetadataUtil.parseNamingGrammar(source, stampNamingConvention);
        File pdf = new File(imageLocation, viewable);
        DocumentSvc.stampPDF(pdf, stampText);
      }
    }
    else if ("drw".equalsIgnoreCase(sourceType)) {
      if ("ps".equalsIgnoreCase(fileType)) DocumentSvc.convertDRW2PS(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
      else if ("hpg".equalsIgnoreCase(fileType)) DocumentSvc.convertDRW2HPGL(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
      else if ("hpgl".equalsIgnoreCase(fileType)) DocumentSvc.convertDRW2HPGL(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
      else if ("cgm".equalsIgnoreCase(fileType)) {
        viewable=viewable+".zip";
        DocumentSvc.convertDRW2CGM(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
      }
      else if ("dxf".equalsIgnoreCase(fileType)) {
        viewable=viewable+".zip";
        DocumentSvc.convertDRW2DXF(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
      }
      else if ("dwg".equalsIgnoreCase(fileType)) {
        viewable=viewable+".zip";
        DocumentSvc.convertDRW2DWG(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
      }
    }
    //else if ("zip".equalsIgnoreCase(fileType)) DocumentSvc.convertDRW2CGM(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
    else if ("prt".equalsIgnoreCase(sourceType) || "asm".equalsIgnoreCase(sourceType)) {
      if ("igs".equalsIgnoreCase(fileType)) convert2IGES(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
      else if ("iges".equalsIgnoreCase(fileType)) convert2IGES(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
      else if ("stp".equalsIgnoreCase(fileType)) convert2STEP(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
      else if ("step".equalsIgnoreCase(fileType)) convert2STEP(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
      else if ("neu".equalsIgnoreCase(fileType)) {
        if ("asm".equalsIgnoreCase(sourceType)) viewable=viewable+".zip";
        convert2Neutral(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
      }
      else if ("idf".equalsIgnoreCase(fileType)) convert2IDF(o, workspace, imageLocation.getAbsolutePath(), viewable, id); 
    }
    //+++ add other source part types
    File imagePath = getDataPathToFile("images", viewable );
    if (!imagePath.exists()) throw new Exception ("Failed To Generate " + viewable + " from workspace " + workspace);
    if (null==targetDatasource || targetDatasource.equals(getName())) {
	    if (deleteOldImage) {
	      try {
	        Metadata ignore = findLatest(viewable, id); //may throw NameNotFound
	        deleteFromRepository(viewable, id);
	        SynchronizationSvc.purgeByName(getDomainName(), getServerName(), getName(), viewable);
	      }
	      catch(NameNotFound ignore) { }
	      catch(Exception e) { e.printStackTrace(); }
	    }
	    
	    importToWorkspace(workspace, imagePath.getAbsolutePath(), id);
	    String snapshotFolder = Properties.get(Names.SNAPSHOT_FOLDER);
	    if (null!=snapshotFolder && !"".equals(snapshotFolder.trim()))
	      source.set("folder", snapshotFolder);
	    setAttributes(workspace, viewable , source.getAttributes(), id);
	    
	    checkin(workspace, id);
	    Metadata image = findInWorkspace(workspace, viewable , false, id);
	    if (!"unchanged".equalsIgnoreCase(image.get("workspace-state"))) throw new Exception ("Failed To checkin " + workspace);
	    destroyWorkspace(workspace, id);    
	    SynchronizationSvc.record(source.getOrigin(), image.getOrigin());
	    //return image;
	    return;
    }
    else { //assume local filesystem
		  FileSystemSource targetRepository = (FileSystemSource)DatasourceSvc.find(targetDatasource);
		  File root = targetRepository.getDataPath(Names.PATH_SEPARATOR);
	    File storedTo = new File (root, imagePath.getName());
		  if (deleteOldPDFImage) {
		    SynchronizationSvc.purgeByName(getDomainName(), getServerName(), getName(), imagePath.getName());
		    if (storedTo.exists()) storedTo.delete();
	  	}
 	    Origin target = OriginMaker.materialize(targetRepository.getName(), root, storedTo );
 	    Origin fileOrigin = targetRepository.storeBinary(target, imagePath, id);
      SynchronizationSvc.record(source.getOrigin(), fileOrigin);
		  return;
    }
  }

  public Collection makePaperSpaceSnapshots(Origin o, String targetDatasource, Authentication ignoreID) throws Exception {
	  Authentication id = null;
	  String workspace = "image" + nextCount();
	  Metadata source = find(o, id);
	  
	  Collection synkRecords = SynchronizationSvc.findAllSynchronizationRecords(o);
	  if (null!=synkRecords) {
	    Iterator i = synkRecords.iterator();
	    Origin synch;
	    while (i.hasNext()){
	      synch = (Origin)i.next();
	      if ("pdf".equalsIgnoreCase(FileNameUtil.lookupFileType(synch.getName()))) {
	        {}//Logwriter.printOnConsole(o.getName()+ " already synchronized:");
	        return synkRecords;
	      }
	    }
	  }
	  destroyWorkspace(workspace, id);
	  createWorkspace(workspace, id);
	  checkout(o,workspace, id);
	  File destination= getDataPath("images");
	  //+++ check source part type to be drawing (or appropiate)
	  Collection pdfFiles = convertDWGLayouts2PDF(o, workspace, id);
	  if (0==pdfFiles.size()) throw new Exception ("Failed To Generate PDF for "+o.getName()+" from workspace " + workspace);
	  /* PDF STAMP
    if (null!=stampNamingConvention && !stampNamingConvention.trim().equals("")) {
	    String stampText = MetadataUtil.parseNamingGrammar(source, stampNamingConvention);
	    File pdf = new File(imageLocation, viewable);
	    DocumentSvc.stampPDF(pdf, stampText);
	  }
	  */
	  //+++ add other source part types	  

	  Iterator i = pdfFiles.iterator();
	  File pdf, storedTo;
	  Origin fileOrigin;
	  Collection pdfOrigins = new Vector();
	  Origin target;
	  FileSystemSource targetRepository = (FileSystemSource)DatasourceSvc.find(targetDatasource);
	  File root = targetRepository.getDataPath(Names.PATH_SEPARATOR);
	  while (i.hasNext()){
	    pdf = (File)i.next();
	    String basename = FileNameUtil.getBaseName(o.getName())+"_";
	    String pdfName= pdf.getName();
	    if (pdfName.startsWith(basename)) pdfName = pdfName.substring(basename.length()); 	    
	    storedTo = new File (root, pdfName);
	    if (deleteOldPDFImage) {
	      SynchronizationSvc.purgeByName(getDomainName(), getServerName(), getName(), pdf.getName());
	      if (storedTo.exists()) storedTo.delete();
  	  }
	    target = OriginMaker.materialize(targetRepository.getName(), root, storedTo );
	    //pdf.renameTo(storedTo);
	    //fileOrigin = OriginMaker.materialize(getName(), storedTo.getParentFile(), storedTo);
	    //inStream = new FileInputStream(pdf);
	    fileOrigin = targetRepository.storeBinary(target, pdf, id);
      SynchronizationSvc.record(source.getOrigin(), fileOrigin);
      pdfOrigins.add(fileOrigin);
	  }
	  return pdfOrigins;
  }
    
  //public long lookupTimeOfCreation(IntralinkOrigin o) throws Exception { return getDataServer().lookupTimeOfCreation(o); }  
  /*
  public boolean mayHaveSubComponents(Metadata data) {
    String name;
    try {name = data.getName(); } //temp hack
    catch (Exception e) { return false; }
    if (name==null || "".equals(name)) return false;
    name = name.toLowerCase();
    if (name.endsWith(".prt")) return false;
    return true;
  }
  */

  public void bindFirstLevelSubComponents(Metadata parent, boolean includeHistory) throws Exception {
    Collection c = getDependencies((IntralinkOrigin)parent.getOrigin(), includeHistory, null);
    Iterator i = c.iterator();
    while (i.hasNext()) {
      parent.addSubComponent((MetadataSubComponent)i.next());
    }
  }

  //public Metadata bindAsStoredSubComponents(Origin o) throws Exception { return getDataServer().bindAsStoredSubComponents(o); }
  //public Metadata bindLatestSubComponents(Origin o) throws Exception { return getDataServer().bindLatestSubComponents(o); }
  //public Metadata bindSubComponents(Origin o, boolean asStored) throws Exception { return getDataServer().bindSubComponents(o, asStored); }
  /*
  public void bindFirstLevelSubComponents(Metadata parent, boolean asStored) throws Exception {
    IntralinkOrigin origin = (IntralinkOrigin)parent.getOrigin();
    GetDependencies op = new GetDependencies();
    String n = objectName(origin.getName());
    String branch = origin.getBranch();
    String revision = origin.getRevision();
    int version = origin.getVersion();
    op.setDatasource(this);
    op.setAuthentication(id);
    op.setComponentName(n);
    op.setBranch(branch);
    op.setRevision(revision);
    op.setVersion(version);
    op.setAssociations(op.ALL);
    if (asStored) op.setConfiguration(op.AS_STORED);
    else op.setConfiguration(op.LATEST);
    op.execute();
    Collection c = op.getResults();
    Iterator i = c.iterator();
    MetadataSubComponentBase sub;
    while (i.hasNext()) {
      sub = new MetadataSubComponentBase((Metadata)i.next());
      parent.addSubComponent(sub);
    }
  }
*/

  /*
  public BillOfMaterials getAsStoredBill(Origin origin) throws Exception {
    Metadata d = getDataServer().bindAsStoredSubComponents(origin);
    {}//Logwriter.printOnConsole(d);
    BillOfMaterials bill = new BillOfMaterials(d);
    {}//Logwriter.printOnConsole(bill.structuredXML());
    return bill;    
  }
  public BillOfMaterials getLatestBill(Origin origin) throws Exception {
    Metadata d = getDataServer().bindLatestSubComponents(origin);
    {}//Logwriter.printOnConsole(d);
    BillOfMaterials bill = new BillOfMaterials(d);
    {}//Logwriter.printOnConsole(bill.structuredXML());
    return bill;    
  }
  */

  public synchronized String publishToAgile(Collection origins, Authentication id) throws Exception {
	  zws.util.PrintUtil.print(origins);
	  if (null==origins || origins.size()==0) return null;
	  IntralinkOrigin o;
	  Iterator i = origins.iterator();
	  AgileSource agile = new AgileSource();
	  BillOfMaterials bill;
	  Collection data = new Vector();
	  while(i.hasNext()) {
	    o = (IntralinkOrigin)i.next();
	    bill = reportBill(o, id);
	    data.add(bill.getMetadata());
	  }
	  //generateAgileViewables(data, id);
	  return agile.publish(data, id);
	}
  /*
  public String publishToAgile_old(Collection origins, Authentication id) throws Exception {
    zws.util.PrintUtil.print(origins);
    if (null==origins || origins.size()==0) return null;
    IntralinkOrigin o;
    Iterator i = origins.iterator();
    o = (IntralinkOrigin)i.next();
    String criteria= "[ Name="+o.getName()+" & Branch="+o.getBranch()+" & Revision="+o.getRevision()+" & Version="+o.getVersion()+" ]";
    while(i.hasNext()) {
      o = (IntralinkOrigin)i.next();
      criteria += " | [ Name="+o.getName()+" & Branch="+o.getBranch()+" & Revision="+o.getRevision()+" & Version="+o.getVersion()+" ]";
    }
    SearchAgent agent = (SearchAgent)getSearchAgent();
    agent.setCriteria(criteria);
    agent.search();
    Collection metadataList = agent.getResults();
    AgileSource agile = new AgileSource();
    i = metadataList.iterator();
    Metadata m, p;
    BillOfMaterials bill;
    while (i.hasNext()) {
     m=(Metadata) i.next(); 
     o = (IntralinkOrigin)m.getOrigin();
     if (o.getName().endsWith("asm")){ 
       bill=reportBill(o, id);
       m.merge(bill.getMetadata(), true);
     }
    }
    generateAgileViewables(metadataList, id);
    return agile.publish(metadataList, id);
  }
  */
  public synchronized String publishIDFToAgile(Collection origins, Authentication id) throws Exception {
    zws.util.PrintUtil.print(origins);
    if (null==origins || origins.size()==0) return null;
    IntralinkOrigin o;
    Iterator i = origins.iterator();
    o = (IntralinkOrigin)i.next();
    String criteria= "[ Name="+o.getName()+" & Branch="+o.getBranch()+" & Revision="+o.getRevision()+" & Version="+o.getVersion()+" ]";
    while(i.hasNext()) {
      o = (IntralinkOrigin)i.next();
      criteria += " | [ Name="+o.getName()+" & Branch="+o.getBranch()+" & Revision="+o.getRevision()+" & Version="+o.getVersion()+" ]";
    }
    SearchAgent agent = (SearchAgent)materializeSearchAgent();
    agent.setCriteria(criteria);
    agent.search();
    Collection metadataList = agent.getResults();
    AgileSource agile = new AgileSource();
    i = metadataList.iterator();
    generateAgileIDFViewables(metadataList, id);
    return agile.publish(metadataList, id);
  }
  
  
  private synchronized void generateAgileViewables(Collection metadataList, Authentication id) throws Exception {
   if (null==metadataList || 0 == metadataList.size()) return;
   Iterator i = metadataList.iterator();
   Metadata data;
   while (i.hasNext()) {
     data = (Metadata)i.next();
     generateAgileViewables(data, id);
   }
  }
  private synchronized void generateAgileIDFViewables(Collection metadataList, Authentication id) throws Exception {
   if (null==metadataList || 0 == metadataList.size()) return;
   Iterator i = metadataList.iterator();
   Metadata data;
   while (i.hasNext()) {
     data = (Metadata)i.next();
     generateAgileIDFViewable(data, id);
   }
  }
  private synchronized void generateAgileIDFViewable(Metadata data, Authentication id) throws Exception {
    IntralinkOrigin o = (IntralinkOrigin)data.getOrigin();
    String space = workspaceName(o); //"ws-"+o.getName() + "-" + o.getBranch() + "-" + o.getRevision() + "-" + o.getVersion();
    String type = data.getName().substring(data.getName().lastIndexOf(".")+1).toLowerCase();
    String binaryFileName = FileNameUtil.getBaseName(o.getName())+"_"+o.getRevision()+"_"+o.getVersion()+"_"+type+"."+type;
    File outputPath=null;
    File outputFile=null;
    if ("asm".equals(type) || "prt".equals(type)) {
      outputPath = new File (Properties.get(Names.DATA_DIR) + Names.PATH_SEPARATOR + "publish2agile" +Names.PATH_SEPARATOR +"idf");
      if (!outputPath.exists()) outputPath.mkdirs();
      binaryFileName = FileNameUtil.convertType(binaryFileName, "emn");
      Origin p = OriginMaker.materialize(Server.getDomainName(),Server.getName(),"agile","agile",0,o.getRevision(),binaryFileName,null, null);
      if (!SynchronizationSvc.isSynchronized(o,p)) {
        checkout(o, space, id);
        convert2IDF(o, space, outputPath.getAbsolutePath(), binaryFileName, id);        
        SynchronizationSvc.record(o, p);
        outputFile = new File(outputPath, binaryFileName);
        data.set("binary-path", outputFile.getAbsolutePath());
      }
      else PrintUtil.println("component "+o.getUniqueID()+" is already synchronized to "+p.getUniqueID());
    }
  }
  
  private synchronized void generateAgileViewables(Metadata data, Authentication id) throws Exception {
    IntralinkOrigin o = (IntralinkOrigin)data.getOrigin();
    String space = workspaceName(o); //"ws-"+o.getName() + "-" + o.getBranch() + "-" + o.getRevision() + "-" + o.getVersion();
    createWorkspace(space, id);
    checkout(o, space, id);
    generateAgileViewables(data, space, id);
  }
  
  private synchronized void generateAgileViewables(Metadata data, String workspace, Authentication id) throws Exception {
    generateAgileViewable(data, workspace, id);
    if (!data.hasSubComponents()) return;
    Iterator i = data.getSubComponents().iterator();
    while(i.hasNext()) generateAgileViewables((Metadata)i.next(), workspace, id);
  }
  
  private synchronized void generateAgileViewable(Metadata data, String space, Authentication id) throws Exception {
    IntralinkOrigin o = (IntralinkOrigin)data.getOrigin();
    String type = data.getName().substring(data.getName().lastIndexOf(".")+1).toLowerCase();
    String binaryFileName = FileNameUtil.getBaseName(o.getName())+"_"+o.getRevision()+"_"+o.getVersion()+"_"+type+"."+type;
    File outputPath=null;
    File outputFile=null;
    if ("drw".equals(type)) {
      outputPath = new File (Properties.get(Names.DATA_DIR) + Names.PATH_SEPARATOR + "publish2agile" +Names.PATH_SEPARATOR +"pdf");
      if (!outputPath.exists()) outputPath.mkdirs();
      String psName = FileNameUtil.convertType(binaryFileName, "ps");
      binaryFileName = FileNameUtil.convertType(binaryFileName, "pdf");
      Origin p = OriginMaker.materialize(Server.getDomainName(),Server.getName(),"agile","agile",0,o.getRevision(),binaryFileName,null, null);
      if (!SynchronizationSvc.isSynchronized(o,p)) {
        convert2PS(o, space, outputPath.getAbsolutePath(), psName, id);
        DocumentSvc.convertPS2PDF(outputPath.getAbsolutePath(), psName, outputPath.getAbsolutePath(), binaryFileName, true);
        SynchronizationSvc.record(o, p);
        outputFile = new File(outputPath, binaryFileName);
        data.set("binary-path", outputFile.getAbsolutePath());
      }
      else PrintUtil.println("component "+o.getUniqueID()+" is already synchronized to "+p.getUniqueID());
    }
    if ("asm".equals(type) || "prt".equals(type)) {
      outputPath = new File (Properties.get(Names.DATA_DIR) + Names.PATH_SEPARATOR + "publish2agile" +Names.PATH_SEPARATOR +"iges");
      if (!outputPath.exists()) outputPath.mkdirs();
      binaryFileName = FileNameUtil.convertType(binaryFileName, "iges");
      Origin p = OriginMaker.materialize(Server.getDomainName(),Server.getName(),"agile","agile",0,o.getRevision()+"|"+o.getVersion(),binaryFileName,null, null);
      if (!SynchronizationSvc.isSynchronized(o,p)) {
        convert2IGES(o, space, outputPath.getAbsolutePath(), binaryFileName, id);        
        SynchronizationSvc.record(o, p);
        outputFile = new File(outputPath, binaryFileName);
        if (outputFile.exists()) data.set("binary-path", outputFile.getAbsolutePath());
      }
      else PrintUtil.println("component "+o.getUniqueID()+" is already synchronized to "+p.getUniqueID());
    }
  }
  
  public BillOfMaterials reportLatestBill(String name, Authentication id) throws Exception {
      String n = objectName(name);
      ReportLatestBill op = new ReportLatestBill();
      op.setDatasource(this);
      op.setAuthentication(lookupID(id));
      op.setComponentName(n);
      op.execute();
      return op.getBillOfMaterials();
  }
  
  public BillOfMaterials reportBill(Origin origin, Authentication id) throws Exception {
    String n = objectName(origin.getName());
    String branch = ((IntralinkOrigin)origin).getBranch();
    String rev = ((IntralinkOrigin)origin).getRevision();
    String ver = ""+((IntralinkOrigin)origin).getVersion();
    ReportBill op = new ReportBill();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.setComponentName(n);
    op.setBranch(branch);
    op.setRevision(rev);
    op.setVersion(ver);
    op.execute();
    return op.getBillOfMaterials();
    
/*    
    Metadata m = loadBill(n, branch, revision, version, id);
    {}//Logwriter.printOnConsole(m);    
    BillOfMaterials bill = new BillOfMaterials(m);
    return bill;
*/
    /*
    if (!m.hasSubComponents()) return null;
    Metadata data;
    MetadataSubComponentBase sub;
    Iterator i = m.getSubComponents().iterator();
    while (i.hasNext()) {
      sub = (MetadataSubComponentBase)i.next();
      if (sub.getName().endsWith(".asm")) {
        n = sub.getName();
        branch="main";
        revision = sub.get("revision");
        version = sub.get("version");
        data = loadBill(n, branch, revision, version, id);
        sub.setComponent(data);
      }
    }
    {}//Logwriter.printOnConsole(m);
    return null;
    */
  }
/*
  private Metadata loadBill(String name, String branch, String rev, String ver, Authentication id) throws Exception {
    GetBill op = new GetBill();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.setBranch(branch);
    op.setComponentName(name);
    op.setRevision(rev);
    op.setVersion(ver);
    op.execute();
    Metadata m = (Metadata)op.getResult();
    if (!m.hasSubComponents()) return m;
    Metadata data;
    MetadataSubComponentBase sub;
    Iterator i = m.getSubComponents().iterator();
    String n;
    branch="main";
    String revision;
    String version;
    while (i.hasNext()) {
      sub = (MetadataSubComponentBase)i.next();
      if (sub.getName().endsWith(".asm")) {
        n = sub.getName();
        revision = sub.get("Revision");
        version = sub.get("Version");
        data = loadBill(n, branch, revision, version, id);
        sub.setComponent(data);
      }
    }
    
    i = m.getSubComponents().iterator();
    SearchAgent agent;
    String c;
    while (i.hasNext()) {
      sub = (MetadataSubComponentBase)i.next();
      n = sub.getName();
      revision = sub.get("Revision");
      version = sub.get("Version");
      c = "[ Name="+n+" & Branch="+branch+" & Revision="+revision+" & Version="+version+" ]";
      agent = (SearchAgent)getSearchAgent();
      agent.setCriteria(c);
      agent.search();
      if (agent.getResults()!=null && agent.getResults().size()>0) {
        data = (Metadata)agent.getResults().toArray()[0];
        //sub.clearMetadata();
        sub.setOrigin(data.getOrigin());
        sub.merge(data, false);
      }
    }
    return m;
  }

  public BillOfMaterials getBill(Origin origin, boolean asStored) throws Exception {
    //Metadata d = getDataServer().bindSubComponents(origin, asStored);
    {}//Logwriter.printOnConsole(d);
    //BillOfMaterials bill = new BillOfMaterials(d);
    {}//Logwriter.printOnConsole(bill.structuredXML());
    //return bill;    
    return null;
  }
 */
  public synchronized void lock(String name, Authentication id) throws Exception {
    lock("main", name, id);
  }
  public synchronized void lock(String branch, String name, Authentication id) throws Exception {
    String n=objectName(name);
    Lock op = new Lock();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.setBranch(branch);
    op.add(n);
    op.execute();
  }

  public synchronized void lock(Collection names, Authentication id) throws Exception {
    Lock op = new Lock();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.add(names);
    op.execute();
  }

  public synchronized void unlock(String name, Authentication id) throws Exception {
    unlock("main", name, id);
  }

  public synchronized void unlock(String branch, String name, Authentication id) throws Exception {
    String n=objectName(name);
    Unlock op = new Unlock();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.setBranch(branch);
    op.add(n);
    op.execute();
  }

  public synchronized void unlock(Collection names, Authentication id) throws Exception {
	  Unlock op = new Unlock();
	  op.setDatasource(this);
	  op.setAuthentication(lookupID(id));
	  op.add(names);
	  op.execute();
	}

  public synchronized void deleteFromRepository(String name, Authentication id) throws Exception {
    String n=objectName(name);
    CommonSpaceDelete op = new CommonSpaceDelete();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.setComponentName(n);
    {}//Logwriter.printOnConsole("Simulating delete " + n + " from repository");
    op.execute();
  }

  public synchronized void deleteVersionFromRepository(Origin o, Authentication id) throws Exception {
    String n=objectName(o.getName());
    CommonSpaceDelete op = new CommonSpaceDelete();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.setComponentName(n);
    op.setBranch(((IntralinkOrigin)o).getBranch());
    op.setRevision(((IntralinkOrigin)o).getRevision());
    op.setVersion(""+((IntralinkOrigin)o).getVersion());
//    {}//Logwriter.printOnConsole("Simulating specific delete of " + n + " revision " + op.getRevision() + " version " + op.getVersion() + " from repository");
    op.execute();
  }

  public void personalCheckout(Origin origin, String workspaceName, Authentication id) throws Exception {
    personalCheckout(origin, workspaceName, REQUIRED_DEPENDENCIES, true, true, false, id);
  }
  public void personalCheckout(Origin origin, String workspaceName, String dependencies, boolean associateInstances, boolean override, boolean metadataOnly, Authentication id) throws Exception {
    checkout(origin, workspaceName, dependencies, associateInstances, override, metadataOnly, true, id);
    try { lock(origin.getName(), id); }
    catch (Exception ignore) {}
  }
  public void personalCheckout(String name, String branch, String workspaceName, String dependencies, boolean associateInstances, boolean override, boolean metadataOnly, Authentication id) throws Exception {
   checkout(name, branch, workspaceName, dependencies, associateInstances, override, metadataOnly, true, id);      
   try { lock(branch, name, id); }
   catch (Exception ignore) {}
  }
  
  public void checkout(Origin origin, String workspaceName, Authentication id) throws Exception {
   checkout(origin, workspaceName, REQUIRED_DEPENDENCIES, true, true, false, id);
  }
  public void checkout(Origin origin, String workspaceName, String dependencies, boolean associateInstances, boolean override, boolean metadataOnly, Authentication id) throws Exception {
    checkout(origin, workspaceName, dependencies, associateInstances, override, metadataOnly, false, id);
  }
  public void checkout(String name, String branch, String workspaceName, String dependencies, boolean associateInstances, boolean override, boolean metadataOnly, Authentication id) throws Exception {
	  checkout(name, branch, workspaceName, dependencies, associateInstances, override, metadataOnly, false, id);
  }
  
  private void checkout(Origin origin, String workspaceName, String dependencies, boolean associateInstances, boolean override, boolean metadataOnly, boolean personalWorkspace, Authentication id) throws Exception {
    String n=objectName(origin.getName());
    Checkout f = new Checkout();
    f.setDatasource(this);
    f.setUsingPersonalWorkspace(personalWorkspace);
    f.setAuthentication(lookupID(id));
    f.setComponentName(n);
    f.setWorkspaceName(workspaceName);
    f.setBranch(((IntralinkOrigin)origin).getBranch());
    f.setRevision(((IntralinkOrigin)origin).getRevision());
    f.setVersion(""+((IntralinkOrigin)origin).getVersion());
    f.setDependencies(dependencies);
    f.setAssociateInstances(associateInstances);
    f.setOverride(override);
    f.setMetadataOnly(metadataOnly);
    f.execute();
  }
  
  private void checkout(String name, String branch, String workspaceName, String dependencies, boolean associateInstances, boolean override, boolean metadataOnly, boolean personalWorkspace, Authentication id) throws Exception {
    String n=objectName(name);
    Checkout f = new Checkout();
    f.setDatasource(this);
    f.setUsingPersonalWorkspace(personalWorkspace);
    f.setAuthentication(lookupID(id));
    f.setComponentName(n);
    f.setWorkspaceName(workspaceName);
    f.setBranch(branch);
    f.setDependencies(dependencies);
    f.setAssociateInstances(associateInstances);
    f.setOverride(override);
    f.setMetadataOnly(metadataOnly);
    f.execute();
  }

  private Collection checkoutList(Collection dataList, String workspaceName, String dependencies, boolean associateInstances, boolean override, boolean metadataOnly, boolean showDependencies, boolean showHistory, boolean personalWorkspace, Authentication id) throws Exception {
    CheckoutList f = new CheckoutList();
    f.setDatasource(this);
    f.setUsingPersonalWorkspace(personalWorkspace);
    f.setAuthentication(lookupID(id));
    f.setMetadataList(dataList);
    f.setWorkspaceName(workspaceName);
    f.setDependencies(dependencies);
    f.setAssociateInstances(associateInstances);
    f.setShowHistory(showHistory);
    f.setShowDependencies(showDependencies);
    f.setOverride(override);
    f.setMetadataOnly(metadataOnly);
    f.execute();
    return f.getResults();
  }
  
  public void personalRemoveFromWorkspace(String workspaceName, String name, Authentication id) throws NameNotFound, Exception {
    removeFromWorkspace(workspaceName, name, true, id);
  }
  public void removeFromWorkspace(String workspaceName, String name, Authentication id) throws NameNotFound, Exception {
    removeFromWorkspace(workspaceName, name, false, id);
  }
  public void removeFromWorkspace(String workspaceName, String name, boolean usingPersonalWorkspace, Authentication id) throws NameNotFound, Exception {
    RemoveFromWorkspace op = new RemoveFromWorkspace();
    op.setDatasource(this);
    op.setComponentName(objectName(name));
    op.setWorkspaceName(workspaceName);
    op.setUsingPersonalWorkspace(usingPersonalWorkspace);
    op.setAuthentication(lookupID(id));
    op.execute();
  }


  public void personalSynchronizeWithCommonSpace(String workspaceName, String name, Authentication id) throws NameNotFound, Exception {
    synchronizeWithCommonSpace(workspaceName, name, true, id);
  }
  public void synchronizeWithCommonSpace(String workspaceName, String name, Authentication id) throws NameNotFound, Exception {
    synchronizeWithCommonSpace(workspaceName, name, false, id);
  }
  public void synchronizeWithCommonSpace(String workspaceName, String name, boolean usingPersonalWorkspace, Authentication id) throws NameNotFound, Exception {
    checkoutLatest(workspaceName, name, usingPersonalWorkspace, id);
  }

  public void personalCheckoutLatest(String workspaceName, String name, Authentication id) throws NameNotFound, Exception {
    checkoutLatest(workspaceName, name, true, id);
  }
  public void checkoutLatest(String workspaceName, String name, Authentication id) throws NameNotFound, Exception {
    checkoutLatest(workspaceName, name, false, id);
  }

  public void checkoutLatest(String workspaceName, String name, boolean usingPersonalWorkspace, Authentication id) throws NameNotFound, Exception {
    CheckoutLatest op = new CheckoutLatest();
    op.setDatasource(this);
    op.setComponentName(objectName(name));
    op.setWorkspaceName(workspaceName);
    op.setUsingPersonalWorkspace(usingPersonalWorkspace);
    op.setAuthentication(lookupID(id));
    op.execute();
  }

  public void promote(Origin origin, String promotionLevel, Authentication id) throws Exception {
    promote(origin, promotionLevel, null, null, id);
  }

  public synchronized void promote(Origin origin, String promotionLevel, String dependencies, String configuration, Authentication id) throws Exception {
    String name=objectName(origin.getName());
    Promote f = new Promote();
    f.setDatasource(this);
    f.setAuthentication(lookupID(id));
    f.setComponentName(name);
    f.setBranch(((IntralinkOrigin)origin).getBranch());
    f.setRevision(((IntralinkOrigin)origin).getRevision());
    f.setVersion(""+((IntralinkOrigin)origin).getVersion());
    f.setPromoteTo(promotionLevel);
    f.setDependencies(dependencies);
    f.setConfiguration(configuration);
    String location=Properties.get(Names.PROMOTE_FORM_FOLDER);
    f.setFormLocation(location);
    f.execute();
    if (null!=f.getFailures() && 0 < f.getFailures().size()) {
      Failure x = (Failure)f.getFailures().iterator().next();
      Exception e = x.getException();
      if (null==e) e = new Exception (x.getMessageKey());
      throw e;
    }
  }

  public void promoteInstance(Origin genericOrigin, String instanceName, String promotionLevel, Authentication id) throws Exception {
    promoteInstance(genericOrigin, instanceName, promotionLevel, null, null, id);
  }
  public synchronized void promoteInstance(Origin genericOrigin, String instanceName, String promotionLevel, String dependencies, String configuration, Authentication id) throws Exception {
    String genericName=objectName(genericOrigin.getName());
    String instance=objectName(instanceName);
    PromoteInstance f = new PromoteInstance();
    f.setDatasource(this);
    f.setAuthentication(lookupID(id));
    f.setInstanceName(instance);
    f.setGenericName(genericName);
    f.setGenericBranch(((IntralinkOrigin)genericOrigin).getBranch());
    f.setGenericRevision(((IntralinkOrigin)genericOrigin).getRevision());
    f.setGenericVersion(""+((IntralinkOrigin)genericOrigin).getVersion());
    f.setPromoteTo(promotionLevel);
    f.setDependencies(dependencies);
    f.setConfiguration(configuration);
    String location=Properties.get(Names.PROMOTE_FORM_FOLDER);
    f.setFormLocation(location);
    f.execute();
    if (null!=f.getFailures() && 0 < f.getFailures().size()) {
      Failure x = (Failure)f.getFailures().iterator().next();
      Exception e = x.getException();
      if (null==e) e = new Exception (x.getMessageKey());
      throw e;
    }
  }

  public void demote(Origin origin, String promotionLevel, Authentication id) throws Exception {
      demote(origin, promotionLevel, null, null, id);
  }

  public synchronized void demote(Origin origin, String demotionLevel, String dependencies, String configuration, Authentication id) throws Exception {
    String name=objectName(origin.getName());
    Demote f = new Demote();
    f.setDatasource(this);
    f.setAuthentication(lookupID(id));
    f.setComponentName(name);
    f.setBranch(((IntralinkOrigin)origin).getBranch());
    f.setRevision(((IntralinkOrigin)origin).getRevision());
    f.setVersion(""+((IntralinkOrigin)origin).getVersion());
    f.setDemoteTo(demotionLevel);
    f.setDependencies(dependencies);
    f.setConfiguration(configuration);
    String location=Properties.get(Names.PROMOTE_FORM_FOLDER);
    f.setFormLocation(location);
    f.execute();
    if (null!=f.getFailures() && 0 < f.getFailures().size()) {
      Failure x = (Failure)f.getFailures().iterator().next();
      Exception e = x.getException();
      if (null==e) e = new Exception (x.getMessageKey());
      throw e;
    }
  }
  

  public void demoteInstance(Origin genericOrigin, String instanceName, String demotionLevel, Authentication id) throws Exception {
    demoteInstance(genericOrigin, instanceName, demotionLevel, null, null, id);
  }
  public synchronized void demoteInstance(Origin genericOrigin, String instanceName, String demotionLevel, String dependencies, String configuration, Authentication id) throws Exception {
    String genericName=objectName(genericOrigin.getName());
    String instance=objectName(instanceName);
    DemoteInstance f = new DemoteInstance();
    f.setDatasource(this);
    f.setAuthentication(lookupID(id));
    f.setInstanceName(instance);
    f.setGenericName(genericName);
    f.setGenericBranch(((IntralinkOrigin)genericOrigin).getBranch());
    f.setGenericRevision(((IntralinkOrigin)genericOrigin).getRevision());
    f.setGenericVersion(""+((IntralinkOrigin)genericOrigin).getVersion());
    f.setDemoteTo(demotionLevel);
    f.setDependencies(dependencies);
    f.setConfiguration(configuration);
    String location=Properties.get(Names.PROMOTE_FORM_FOLDER);
    f.setFormLocation(location);
    f.execute();
    if (null!=f.getFailures() && 0 < f.getFailures().size()) {
      Failure x = (Failure)f.getFailures().iterator().next();
      Exception e = x.getException();
      if (null==e) e = new Exception (x.getMessageKey());
      throw e;
    }
  }

  public synchronized void modelCheck(Origin origin, Authentication id) throws Exception {
    String name=objectName(origin.getName());
    String ws = "mdlcheck" + getNewCount();
    createWorkspace(ws, id);
    checkout(origin, ws, id);
    ModelCheck op = new ModelCheck();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.setWorkspaceName(ws);
    op.setComponentName(name);
    op.execute();
  }
  
  public Collection getAttributes(Authentication id) throws Exception {
    Collection atts = new Vector();
    ListAttributes op = new ListAttributes();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.execute();
    Collection c = op.getResults();
    Iterator i = c.iterator();
    IntralinkAttribute att;
    atts.add("Name");
    atts.add("Branch");
    atts.add("Rev");
    atts.add("Ver");
    atts.add("Release-Level");
    atts.add("Description");
    atts.add("Folder");
    atts.add("Created-By");
    atts.add("Created-On");
    while (i.hasNext()) {
      att = (IntralinkAttribute)i.next();
      atts.add(att.getName());
    }
    return atts;
  }
  
  public Collection getUserDefinedAttributes(Authentication id) throws Exception {
    Collection atts = new Vector();
    ListAttributes op = new ListAttributes();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.execute();
    Collection c = op.getResults();
    Iterator i = c.iterator();
    IntralinkAttribute att;
    while (i.hasNext()) {
      att = (IntralinkAttribute)i.next();
      atts.add(att.getName());
      //atts.add(att);
    }
    return atts;
  }
  
  
  public void personalSetAttribute(String workspace, String name, String attribute, String value, Authentication id) throws Exception {
    setAttribute(workspace, name, attribute, value, true, id);     
  }
  
  public void setAttribute(String workspace, String name, String attribute, String value, Authentication id) throws Exception {
    setAttribute(workspace, name, attribute, value, false, id);     
  }
     
  public synchronized void setAttribute(String workspace, String name, String attribute, String value, boolean personalWorkspace, Authentication id) throws Exception {
    if (!isModifyableAttribute(attribute)) throw new Unmodifyable(attribute);
    String n=objectName(name);
    SetAttribute op = new SetAttribute();
    op.setDatasource(this);
    op.setUsingPersonalWorkspace(personalWorkspace);
    op.setAuthentication(lookupID(id));
    op.setComponentName(n);
    op.setWorkspaceName(workspace);
    op.set(attribute, value);
    op.execute();
  }

  
  public void personalSetAttributes(String workspace, String name, Map attributes, Authentication id) throws Exception{
    setAttributes(workspace, name, attributes, true, id);
  }    
  
  public void setAttributes(String workspace, String name, Map attributes, Authentication id) throws Exception{
    setAttributes(workspace, name, attributes, false, id);
  }    
  public synchronized void setAttributes(String workspace, String name, Map attributes, boolean personalWorkspace, Authentication id) throws Exception{
    Map atts = extractModifyableAttributes(attributes);
    if (0==atts.size()) return;
    String n=objectName(name);
    SetAttributeList op = new SetAttributeList();
    op.setDatasource(this);
    op.setUsingPersonalWorkspace(personalWorkspace);
    op.setAuthentication(lookupID(id));
    op.setWorkspaceName(workspace);
    op.setAttributes(n, atts);
    op.execute();
  }
  
  
  /*
  public void setAttributeForAll(String workspace, String attribute, String value, Authentication id) throws Exception {
    if (!isModifyableAttribute(attribute)) throw new Unmodifyable(attribute);
    SetAttribute op = new SetAttribute();
    op.setDatasource(this);
    op.setName(null);
    op.setWorkspaceName(workspace);
    op.set(attribute, value);
    op.setAuthentication(lookupID(id));
    op.execute();
  }
  
  public void setAttributesForAll(String workspace, Map attributes, Authentication id) throws Exception{
    Map atts = extractModifyableAttributes(attributes);
    if (0==atts.size()) return;
    SetAttribute op = new SetAttribute();
    op.setDatasource(this);
    op.setName(null);
    op.setWorkspaceName(workspace);
    op.setAttributes(atts);
    op.setAuthentication(lookupID(id));
    op.execute();
  }
*/
  private Map extractModifyableAttributes(Map attributes) {
    String name;
    Map atts = new HashMap();
    Iterator i = attributes.keySet().iterator();
    while (i.hasNext()) {
      name = (String)i.next();
      if (userDefinedAttributes.contains(name) || isModifyableSystemAttribute(name)) atts.put(name, attributes.get(name));
    }
    return atts;
  }

  public synchronized void unsetLifeCycleAttribute(Origin origin, String attribute, Authentication id) throws Exception {
    IntralinkOrigin o = (IntralinkOrigin) origin;
	  String n=objectName(o.getName());
	  UnSetLifeCycleAttribute op = new UnSetLifeCycleAttribute();
	  op.setDatasource(this);
	  op.setAuthentication(lookupID(id));
	  op.setComponentName(n);
	  op.setBranch(o.getBranch());
	  op.setRevision(o.getRevision());
	  op.setVersion(o.getVersion());
	  op.setParameter(attribute);
	  op.execute();
	}

  public synchronized void setLifeCycleAttribute(Origin origin, String attribute, String value, Authentication id) throws Exception {
    IntralinkOrigin o = (IntralinkOrigin) origin;
	  String n=objectName(o.getName());
	  SetLifeCycleAttribute op = new SetLifeCycleAttribute();
	  op.setDatasource(this);
	  op.setAuthentication(lookupID(id));
	  op.setComponentName(n);
	  op.setBranch(o.getBranch());
	  op.setRevision(o.getRevision());
	  op.setVersion(o.getVersion());
	  op.set(attribute, value);
	  op.execute();
	}
  
	public synchronized void setLifeCycleAttributes(Collection metadataList, String metadataField, String attribute, Authentication id) throws Exception{
	  Metadata data;
	  Origin o;
	  Iterator i = metadataList.iterator();
	  String value;
	  while (i.hasNext()) {
	    data = (Metadata) i.next();
	    o = data.getOrigin();
	    value = data.get(metadataField);
	    setLifeCycleAttribute(o, attribute, value, id);
	  }
	}
  
	public synchronized void unsetLifeCycleAttributes(Collection metadataList, String attribute, Authentication id) throws Exception{
	  Metadata data;
	  Origin o;
	  Iterator i = metadataList.iterator();
	  while (i.hasNext()) {
	    data = (Metadata) i.next();
	    o = data.getOrigin();
	    unsetLifeCycleAttribute(o, attribute, id);
	  }
	}

  private boolean isSystemAttribute(String name) { return getSystemAttributes().containsKey(name); }
  private boolean isUserDefinedAttribute(String name) { return userDefinedAttributes.contains(name); }
  private boolean isModifyableSystemAttribute(String name) { 
    return getSystemAttributes().containsKey(name) && true == ((Boolean)getSystemAttributes().get(name)).booleanValue(); 
  }
  private boolean isModifyableAttribute(String name) { return isModifyableSystemAttribute(name) || isUserDefinedAttribute(name); }

  public Map getSystemAttributes() {
    if (null==systemAttributes) {
      systemAttributes = new HashMap();
      systemAttributes.put(BRANCH, new Boolean(true));
      systemAttributes.put(FOLDER, new Boolean(true));
      systemAttributes.put(REVISION, new Boolean(true));
      systemAttributes.put(VERSION, new Boolean(false));
      systemAttributes.put(RELEASE_LEVEL, new Boolean(true));
      systemAttributes.put(GENERIC, new Boolean(false));
      systemAttributes.put(INSTANCE, new Boolean(false));
      systemAttributes.put(CREATED_ON, new Boolean(false));
      systemAttributes.put(CREATED_BY, new Boolean(false));
      systemAttributes.put(DESCRIPTION, new Boolean(true));
      systemAttributes.put(RELEASE_SCHEMA, new Boolean(true));
      systemAttributes.put(SCOPE, new Boolean(true));
      systemAttributes.put(STATUS_DESCRIPTION, new Boolean(true));
      systemAttributes.put(RULE, new Boolean(true));
      systemAttributes.put(ROLE, new Boolean(true));
      systemAttributes.put(RELEASE_SCHEME, new Boolean(true));
      systemAttributes.put(RELEASE_PROCEDURE, new Boolean(true));
      systemAttributes.put(QUANTITY, new Boolean(true));
      systemAttributes.put(PROMOTE_TO, new Boolean(false));
      systemAttributes.put(DEMOTE_TO, new Boolean(false));
      systemAttributes.put(OBJECT, new Boolean(false));
      systemAttributes.put(MEMBER_TYPE, new Boolean(true));
      systemAttributes.put(DEPENDENCIES_ARE_COMPLETE, new Boolean(false));
      systemAttributes.put(BRANCH_DOMAIN, new Boolean(false));
      systemAttributes.put(ATTENTION, new Boolean(false));
      systemAttributes.put(RELATIONSHIP_TYPES, new Boolean(false));
      systemAttributes.put(ROW_NUMBER, new Boolean(false));
      systemAttributes.put(SAME_FILE, new Boolean(false));
      systemAttributes.put(TYPE_NAME, new Boolean(false));
    }
    return systemAttributes;
  }

  public void personalLink(String workspace, String parent, String child, Authentication id) throws Exception{
    link(workspace, parent, child, true, id);
  }
  public void link(String workspace, String parent, String child, Authentication id) throws Exception{
    link(workspace, parent, child, false, id);
  }
  public synchronized void link(String workspace, String parent, String child, boolean personalWorkspace, Authentication id) throws Exception{
    String p=objectName(parent);
    String c=objectName(child);
 	  Link f = new Link();
  	f.setDatasource(this);
  	f.setUsingPersonalWorkspace(personalWorkspace);
    f.setAuthentication(lookupID(id));
	  f.setWorkspaceName(workspace);
    f.setParentName(p);
	  f.setChildName(c);
	  f.execute();
  }	

  public void personalCheckin(String workspace, Authentication id) throws Exception {
    checkin(workspace, true, id);      
  }

  public void checkin(String workspace, Authentication id) throws Exception {
    checkin(workspace, false, id);      
  }

  public void checkin(String workspace, boolean personalWorkspace, Authentication id) throws Exception {
	 Checkin f = new Checkin();
	 f.setDatasource(this);
	 f.setUsingPersonalWorkspace(personalWorkspace);
	 f.setAuthentication(lookupID(id));
	 f.setWorkspaceName(workspace);
	 f.execute();
  }

  public void adoptGhosts(String workspace, Authentication id) throws Exception {
	 AdoptGhosts op = new AdoptGhosts();
	 op.setDatasource(this);
	 op.setAuthentication(lookupID(id));
	 op.setWorkspaceName(workspace);
   op.setQuarantineFolder(quarantineFolder);
	 op.execute();
  }

  public void removeQuarantinedDuplicates(String workspace, Authentication id) throws Exception {
	 RemoveQuarantinedDuplicates op = new RemoveQuarantinedDuplicates();
	 op.setDatasource(this);
	 op.setAuthentication(lookupID(id));
	 op.setWorkspaceName(workspace);
   op.setQuarantineFolder(quarantineFolder);
	 op.execute();
  }

  
  public void holdGhosts(String workspace, Authentication id) throws Exception {
	 HoldGhosts op = new HoldGhosts();
	 op.setDatasource(this);
	 op.setAuthentication(lookupID(id));
	 op.setWorkspaceName(workspace);
	 op.execute();
  }

  public String convertToIGES(Origin origin, String workspace, String outputPath, Authentication id) throws Exception{
    String igesFileName = origin.getName() + ".iges";
    convert2IGES(origin, workspace, outputPath, igesFileName, id);
    return igesFileName;
  }

  public String convertToSTEP(Origin origin, String workspace, String outputPath, Authentication id) throws Exception{
    String stepFileName = origin.getName() + ".stp";
    convert2STEP(origin, workspace, outputPath, stepFileName, id);
    return stepFileName;
  }

  public Collection personalExport(String workspace, String outputPath, String name, Authentication id) throws Exception{
    return export(workspace, outputPath, name, ALL_DEPENDENCIES, true, id);
  }

  public Collection export(String workspace, String outputPath, String name, Authentication id) throws Exception{
    return export(workspace, outputPath, name, ALL_DEPENDENCIES, id);
  }

  public Collection personalExport(String workspace, String outputPath, String name, String dependencies, Authentication id) throws Exception{
    return export(workspace, outputPath, name, dependencies, true, id);
  }

  public Collection export(String workspace, String outputPath, String name, String dependencies, Authentication id) throws Exception{
    return export(workspace, outputPath, name, dependencies, false, id);
  }
  public Collection export(String workspace, String outputPath, String name, String dependencies, boolean personalWorkspace, Authentication id) throws Exception{
    String n=objectName(name);
    File outDir = new File(outputPath);
    if (!outDir.exists()) outDir.mkdirs();
    mdimpexExport f = new mdimpexExport();
    f.setDatasource(this);
    f.setUsingPersonalWorkspace(personalWorkspace);
    f.setAuthentication(lookupID(id));
    f.setComponentName(n);
    f.setOutputDirectory(outputPath);
    f.setWorkspaceName(workspace);
    f.setDependencies(dependencies);
    f.execute();
    return null; //+++ modify to return f.getResutls()
  }
  
  public synchronized void exportList(String workspace, Collection fileList, String dependencies, File exportDir, boolean personalWorkspace, Authentication id) throws Exception {
    if (null ==fileList|| fileList.isEmpty()) return;
    
    mdimpexExportList op = new mdimpexExportList();
    op.setDatasource(this);
    op.setUsingPersonalWorkspace(personalWorkspace);
    op.setAuthentication(lookupID(id));
    op.setFileList(fileList);
    op.setWorkspaceName(workspace);
    op.setDependencies(dependencies);
    op.setExportDir(exportDir);
    op.execute();
    {}//Logwriter.printOnConsole("--------------------------");
  }

  
  public void personalImportToWorkspace(String workspace, Collection fileList, Authentication id) throws Exception{
    importToWorkspace(workspace, fileList, ALL_DEPENDENCIES, true, id);
  }

  public void importToWorkspace(String workspace, Collection fileList, Authentication id) throws Exception{
    importToWorkspace(workspace, fileList, ALL_DEPENDENCIES, id);
  }

  public void importToWorkspace(String workspace, Collection fileList, String dependencies,Authentication id) throws Exception {
    importToWorkspace(workspace, fileList, dependencies, false, id);
  }
  public synchronized void importToWorkspace(String workspace, Collection fileList, String dependencies, boolean personalWorkspace, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole("--Importing List:---------");
    Collection proeFiles = new Vector();
    Iterator i = fileList.iterator();
    File f;
    boolean listHasProEFiles = false;
    while (i.hasNext()) {
      f = (File) i.next();
      if (isProEngineerFile(f.getName())) {
        proeFiles.add(f);
        listHasProEFiles = true;
        {}//Logwriter.printOnConsole("  ProE:" + f.getName());
      }
      else importToWorkspace(workspace, f.getAbsolutePath(), NO_DEPENDENCIES, false, id);
    }

    if (!listHasProEFiles) {
      {}//Logwriter.printOnConsole("No ProE files in list");
      return;
    }
    mdimpexImportList op = new mdimpexImportList();
    op.setDatasource(this);
    op.setUsingPersonalWorkspace(personalWorkspace);
    op.setAuthentication(lookupID(id));
    op.setFileList(proeFiles);
    op.setWorkspaceName(workspace);
    op.setDependencies(dependencies);
    op.execute();
    {}//Logwriter.printOnConsole("--------------------------");
  }

  

  public void personalImportFamilyTableToWorkspace(String workspace, Collection fileList, Authentication id) throws Exception{
    importFamilyTableToWorkspace(workspace, fileList, ALL_DEPENDENCIES, true, id);
  }

  public void importFamilyTableToWorkspace(String workspace, Collection fileList, Authentication id) throws Exception{
    importFamilyTableToWorkspace(workspace, fileList, ALL_DEPENDENCIES, id);
  }

  public void importFamilyTableToWorkspace(String workspace, Collection fileList, String dependencies,Authentication id) throws Exception {
    importFamilyTableToWorkspace(workspace, fileList, dependencies, false, id);
  }
  
  public synchronized void importFamilyTableToWorkspace(String workspace, Collection fileList, String dependencies, boolean personalWorkspace, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole("--Importing List:---------");
    Collection proeFiles = new Vector();
    Iterator i = fileList.iterator();
    File f;
    boolean listHasProEFiles = false;
    while (i.hasNext()) {
      f = (File) i.next();
      if (isProEngineerFile(f.getName())) {
        proeFiles.add(f);
        listHasProEFiles = true;
        {}//Logwriter.printOnConsole("  ProE:" + f.getName());
      }
      else importToWorkspace(workspace, f.getAbsolutePath(), NO_DEPENDENCIES, false, id);
    }

    if (!listHasProEFiles) {
      {}//Logwriter.printOnConsole("No ProE files in list");
      return;
    }
    mdimpexImportListFamilyTable op = new mdimpexImportListFamilyTable();
    op.setDatasource(this);
    op.setUsingPersonalWorkspace(personalWorkspace);
    op.setAuthentication(lookupID(id));
    op.setFileList(proeFiles);
    op.setWorkspaceName(workspace);
    op.setDependencies(dependencies);
    op.execute();
    {}//Logwriter.printOnConsole("--------------------------");
  }
  
  public void personalImportToWorkspace(String workspace, String objectName, URL source, Authentication id) throws Exception{
    importToWorkspace(workspace, objectName, source, true, id);
  }

  public void importToWorkspace(String workspace, String objectName, URL source, Authentication id) throws Exception{
    importToWorkspace(workspace, objectName, source, false, id);
  }

  public void importToWorkspace(String workspace, String objectName, URL source, boolean personalWorkspace, Authentication id) throws Exception{
    File tmpDir = new File(Properties.get(Names.TEMP_DIR),"import"+nextCount());
    if (tmpDir.exists()) FileUtil.deleteContents(tmpDir);
    tmpDir.mkdirs();
    File f = new File(tmpDir, objectName);
    FileUtil.save(source.openStream(), f, true);
    importToWorkspace(workspace, f.getAbsolutePath(), ALL_DEPENDENCIES, personalWorkspace, id);
  }

  public void personalImportToWorkspace(String workspace, String sourcePath, Authentication id) throws Exception{
    importToWorkspace(workspace, sourcePath, ALL_DEPENDENCIES, true, id);
  }

  public void importToWorkspace(String workspace, String sourcePath, Authentication id) throws Exception{
    importToWorkspace(workspace, sourcePath, ALL_DEPENDENCIES, false, id);
  }

  public synchronized void importToWorkspace(String workspace, String sourcePath, String dependencies, boolean personalWorkspace, Authentication id) throws Exception{
    File source = new File(sourcePath);
    if (isProEngineerFile(source.getName())) {
      mdimpexImportToWorkspace(workspace, sourcePath, dependencies, id);
      return;
    }    
    {}//Logwriter.printOnConsole("--Importing non ProE:" + source.getName());
    if (!source.exists()) throw new FileNotFoundException(sourcePath);
    if (!source.isFile()) throw new NotAFile(sourcePath, sourcePath);
    Import op = new Import();
    op.setDatasource(this);
    op.setUsingPersonalWorkspace(personalWorkspace);
    op.setAuthentication(lookupID(id));
    op.setNameIsLowerCased(getLowerCasedFilenames());
    op.setNameIsUpperCased(getUpperCasedFilenames());
    op.setComponentPath(sourcePath);
    op.setWorkspaceName(workspace);
    op.setDependencies(dependencies);
    op.execute();
  }

  public synchronized void importFamilyTableToWorkspace(String workspace, String sourcePath, String dependencies, boolean personalWorkspace, Authentication id) throws Exception{
    File source = new File(sourcePath);
    if (isProEngineerFile(source.getName())) {
      mdimpexImportFamilyTableToWorkspace(workspace, sourcePath, dependencies, id);
      return;
    }
  }

  public void personalMdimpexImportToWorkspace(String workspace, String filePath, String dependencies, Authentication id) throws Exception {
    mdimpexImportToWorkspace(workspace, filePath, dependencies, true, id);
  }
      
  public void mdimpexImportToWorkspace(String workspace, String filePath, String dependencies, Authentication id) throws Exception {
    mdimpexImportToWorkspace(workspace, filePath, dependencies, false, id);
  }

  public synchronized void mdimpexImportToWorkspace(String workspace, String filePath, String dependencies, boolean personalWorkspace, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole("--ProE:" + filePath);
    mdimpexImport op = new mdimpexImport();
    op.setDatasource(this);
    op.setUsingPersonalWorkspace(personalWorkspace);
    op.setAuthentication(lookupID(id));
    op.setFilePath(filePath);
    op.setWorkspaceName(workspace);
    op.execute();
  }

  public void personalMdimpexImportFamilyTableToWorkspace(String workspace, String filePath, String dependencies, Authentication id) throws Exception {
    mdimpexImportFamilyTableToWorkspace(workspace, filePath, dependencies, true, id);
  }
      
  public void mdimpexImportFamilyTableToWorkspace(String workspace, String filePath, String dependencies, Authentication id) throws Exception {
    mdimpexImportFamilyTableToWorkspace(workspace, filePath, dependencies, false, id);
  }

  public synchronized void mdimpexImportFamilyTableToWorkspace(String workspace, String filePath, String dependencies, boolean personalWorkspace, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole("--ProE:" + filePath);
    mdimpexImportFamilyTable op = new mdimpexImportFamilyTable();
    op.setDatasource(this);
    op.setUsingPersonalWorkspace(personalWorkspace);
    op.setAuthentication(lookupID(id));
    op.setFilePath(filePath);
    op.setWorkspaceName(workspace);
    op.execute();
  }
  
  public boolean isProEngineerFile(String filename){
    String type = FileNameUtil.getFileNameExtension(filename);
    if (null==type || "".equals(type)) return false;
    type = type.toLowerCase();
    if (type.equals("asm")) return true;
    else if (type.equals("prt")) return true;
    else if (type.equals("drw")) return true;
    else if (type.equals("lay")) return true;
    else if (type.equals("frm")) return true;
    else if (type.equals("dgm")) return true;
    else if (type.equals("cgp")) return true;
    else if (type.equals("des")) return true;
    else if (type.equals("dft")) return true;
    else if (type.equals("fmd")) return true;
    else if (type.equals("fmp")) return true;
    else if (type.equals("fra")) return true;
    else if (type.equals("int")) return true;
    else if (type.equals("ipf")) return true;
    else if (type.equals("mfg")) return true;
    else if (type.equals("mda")) return true;
    else if (type.equals("mdb")) return true;
    else if (type.equals("mma")) return true;
    else if (type.equals("mmp")) return true;
    else if (type.equals("mrk")) return true;
    else if (type.equals("rep")) return true;
    else if (type.equals("rsb")) return true;
    else if (type.equals("rsd")) return true;
    else if (type.equals("rss")) return true;
    else if (type.equals("sec")) return true;

    /*
    else if (type.equals("cfg")) return true;
    else if (type.equals("grt")) return true;
    else if (type.equals("gph")) return true;
    else if (type.equals("int")) return true;
    else if (type.equals("ipf")) return true;
    else if (type.equals("inp")) return true;
    else if (type.equals("lsl")) return true;
    else if (type.equals("lgh")) return true;
    else if (type.equals("mac")) return true;
    else if (type.equals("mda")) return true;
    else if (type.equals("pbk")) return true;
    else if (type.equals("pba")) return true;
    else if (type.equals("pro")) return true;
    else if (type.equals("sup")) return true;
    else if (type.equals("snp")) return true;
    else if (type.equals("tp")) return true;    
    else if (type.equals("win")) return true;
    else if (type.equals("xrp")) return true;
    else if (type.equals("xpr")) return true;
    else if (type.equals("xas")) return true;
    */
    else return false;
  }

  public synchronized void personalDestroyWorkspace(String workspace, Authentication id) throws Exception {
    destroyWorkspace(workspace, true, id);      
  }

  public synchronized void destroyWorkspace(String workspace, Authentication id) throws Exception {
    if (zws.Server.debugMode()) return;
    destroyWorkspace(workspace, false, id);      
  }
  public synchronized void destroyWorkspace(String workspace, boolean personalWorkspace, Authentication id) throws Exception {
    DestroyWorkspace op = new DestroyWorkspace();
    op.setDatasource(this);
    op.setUsingPersonalWorkspace(personalWorkspace);
    op.setAuthentication(lookupID(id));
    op.setWorkspaceName(workspace);
    op.execute();
  }

  public synchronized void personalCreateWorkspace(String workspace, Authentication id) throws Exception {
    createWorkspace(workspace, true, id);      
  }

  public synchronized void createWorkspace(String workspace, Authentication id) throws Exception {
    createWorkspace(workspace, false, id);      
  }
  public synchronized void createWorkspace(String workspace, boolean personalWorkspace, Authentication id) throws Exception {
    if (workspace.indexOf('.')>-1) throw new InvalidName("workspace: " + workspace);      
    CreateWorkspace op= new CreateWorkspace();
    op.setDatasource(this);
    op.setUsingPersonalWorkspace(personalWorkspace);
    op.setAuthentication(lookupID(id));
    op.setWorkspaceName(workspace);
    op.execute();
  }

  public synchronized void createBaseline(String name, String folderLocation, String releaseLevel, Collection components, Authentication id) throws Exception {
    CreateBaseline op = new CreateBaseline();
    /*
    op.setDatasource(this);
    op.setComponentName(name);
    op.setFolderLocation(folderLocation);
    op.setReleaseLevel(releaseLevel);
    op.setComponents(components);
    op.setAuthentication(lookupID(id));
    op.execute();
    */
    //++todo detect and throw DuplicateName
  }

  public synchronized void deleteBaseline(String name, Authentication id) throws Exception {
    DeleteBaseline op = new DeleteBaseline();
    /*
    op.setComponentName(name);
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.execute();
    */
  }

  public Collection findRTPForms(String datedAfter, String promoteTo, Authentication id) throws Exception {
    PromoteFormSearch op = new PromoteFormSearch();
    op.setDatasource(this);
    op.setDatedAfter(datedAfter);
    op.setPromoteToLevel(promoteTo);
    op.setAuthentication(lookupID(id));
    op.execute();
    return op.getResults();
  }

  public void convert2PS(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    DRW2PS converter = new DRW2PS();
    convertDrawing(converter, origin, workspace, outputPath, outputName, id);
  }
  
  public void convert2HPGL(Origin origin, String workspace, String outputPath, String outputName,Authentication id) throws Exception {
    DRW2HPGL converter = new DRW2HPGL();
    convertDrawing(converter, origin, workspace, outputPath, outputName, id);
  }
  
  public void convert2CGM(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    DRW2CGM converter = new DRW2CGM();
    convertDrawing(converter, origin, workspace, outputPath, outputName, id);
  }
  
  public void convert2DXF(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    DRW2DXF converter = new DRW2DXF();
    convertDrawing(converter, origin, workspace, outputPath, outputName, id);
  }

  public void convert2DWG(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    DRW2DWG converter = new DRW2DWG();
    convertDrawing(converter, origin, workspace, outputPath, outputName, id);
  }

  public synchronized void convert2IGES(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    ASMPRT2IGES converter = new ASMPRT2IGES();
    String type = FileNameUtil.getFileNameExtension(origin.getName());
    if (!type.equalsIgnoreCase("asm") && !type.equalsIgnoreCase("prt")) 
      throw new Exception("Can not convert '"+origin.getName()+"' to IGES: it is not an assembly or part.");
    String n=objectName(origin.getName());
    converter.setDatasource(this);
    converter.setAuthentication(lookupID(id));
    converter.setComponentName(n);
    converter.setWorkspaceName(workspace);
    converter.setBinaryOutputPath(outputPath);
    converter.setBinaryFilename(outputName);
    converter.execute();
    String igesFilename;
    if (type.equalsIgnoreCase("prt")) igesFilename = FileNameUtil.convertType(n,"igs");
    else igesFilename = FileNameUtil.getBaseName(n)+"_asm.igs";
    try {
      export(workspace,outputPath,igesFilename, id);
      File outDir = new File(outputPath);
      if (!outDir.exists()) outDir.mkdirs();
      File igesFile = new File(outDir, igesFilename);
      File outputFile = new File(outDir, outputName);
      igesFile.renameTo(outputFile);
      removeFromWorkspace(workspace,igesFilename, id);
    }
    catch (Exception e) { System.out.print("Failed to export " + igesFilename); {}//Logwriter.printOnConsole(e.getMessage());}
    
    }
  }
  
  
  public synchronized void convert2STEP(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    ASMPRT2STEP converter = new ASMPRT2STEP();
    String type = FileNameUtil.getFileNameExtension(origin.getName());
    if (!type.equalsIgnoreCase("asm") && !type.equalsIgnoreCase("prt")) 
      throw new Exception("Can not convert '"+origin.getName()+"' to STEP: it is not an assembly or part.");
    String n=objectName(origin.getName());
    converter.setDatasource(this);
    converter.setAuthentication(lookupID(id));
    converter.setComponentName(n);
    converter.setWorkspaceName(workspace);
    converter.setBinaryOutputPath(outputPath);
    converter.setBinaryFilename(outputName);
    converter.execute();
    String stepFilename;
    if (type.equalsIgnoreCase("prt")) stepFilename = FileNameUtil.convertType(n,"stp");
    else stepFilename = FileNameUtil.getBaseName(n)+"_asm.stp";
    try {
      export(workspace,outputPath,stepFilename, id);
      File outDir = new File(outputPath);
      if (!outDir.exists()) outDir.mkdirs();
      File stepFile = new File(outDir, stepFilename);
      File outputFile = new File(outDir, outputName);
      stepFile.renameTo(outputFile);
      removeFromWorkspace(workspace,stepFilename, id);
    }
    catch (Exception e) { System.out.print("Failed to export " + stepFilename); {}//Logwriter.printOnConsole(e.getMessage());}
    
    }
  }
  
  
  public synchronized void convert2Neutral(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    ASMPRT2Neutral converter = new ASMPRT2Neutral();
    String type = FileNameUtil.getFileNameExtension(origin.getName());
    if (!type.equalsIgnoreCase("asm") && !type.equalsIgnoreCase("prt")) 
      throw new Exception("Can not convert '"+origin.getName()+"' to IGES: it is not an assembly or part.");
    String n=objectName(origin.getName());
    converter.setDatasource(this);
    converter.setAuthentication(lookupID(id));
    converter.setComponentName(n);
    converter.setWorkspaceName(workspace);
    converter.setBinaryOutputPath(outputPath);
    converter.setBinaryFilename(outputName);
    converter.execute();
    String neuFilename;
    if (type.equalsIgnoreCase("prt")) neuFilename = FileNameUtil.convertType(n,"neu");
    else neuFilename = FileNameUtil.getBaseName(n)+"_asm.neu.zip";
    try {
      export(workspace,outputPath,neuFilename, id);
      File outDir = new File(outputPath);
      if (!outDir.exists()) outDir.mkdirs();
      File neuFile = new File(outDir, neuFilename);
      File outputFile = new File(outDir, outputName);
      removeFromWorkspace(workspace, neuFilename, id);
      neuFile.renameTo(outputFile);
    }
    catch (Exception e) { System.out.print("Failed to export " + neuFilename); {}//Logwriter.printOnConsole(e.getMessage());}
    
    }
  }

  public synchronized void convert2IDF(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    ASMPRT2IDF converter = new ASMPRT2IDF();
    String type = FileNameUtil.getFileNameExtension(origin.getName());
    if (!type.equalsIgnoreCase("asm") && !type.equalsIgnoreCase("prt")) 
      throw new Exception("Can not convert '"+origin.getName()+"' to IDF: it is not an assembly or part.");
    String n=objectName(origin.getName());
    converter.setDatasource(this);
    converter.setAuthentication(lookupID(id));
    converter.setComponentName(n);
    converter.setWorkspaceName(workspace);
    converter.setBinaryOutputPath(outputPath);
    converter.setBinaryFilename(outputName);
    converter.execute();
    String idfFilename=FileNameUtil.convertType(n,"emn");
    try {
      export(workspace,outputPath,idfFilename, id);
      File outDir = new File(outputPath);
      if (!outDir.exists()) outDir.mkdirs();
      File idfFile = new File(outDir, idfFilename);
      File outputFile = new File(outDir, outputName);
      removeFromWorkspace(workspace, idfFilename, id);
      idfFile.renameTo(outputFile);
    }
    catch (Exception e) { System.out.print("Failed to export " + idfFilename); {}//Logwriter.printOnConsole(e.getMessage());}
    
    }
  }

  public synchronized void convertDWG2PDF(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    String type = FileNameUtil.getFileNameExtension(origin.getName());
    if (!type.equalsIgnoreCase("dwg")) 
      throw new Exception("Can not convert '"+origin.getName()+"' to PDF: it is not an an AutoCAD Drawing (.dwg).");
    File outDir = new File(outputPath);
    if (!outDir.exists()) outDir.mkdirs();
    
    String n=objectName(origin.getName());
    try { export(workspace,outputPath,n, id); }
    catch (Exception e) { System.out.print("Failed to export " + n); {}//Logwriter.printOnConsole(e.getMessage());} 
    
    
    }
    
    File sourceDWG = new File(outDir, n);
    DWG2PDF converter = new DWG2PDF();
    converter.setDatasource(null);
    converter.setAuthentication(lookupID(id));
    converter.setTargetAutoCADFile(sourceDWG);
    converter.setOutputDirectory(outputPath);
    converter.execute();
  }

  public synchronized Collection convertDWGLayouts2PDF(Origin origin, String workspace, Authentication id) throws Exception {
	  String type = FileNameUtil.getFileNameExtension(origin.getName());
	  if (!type.equalsIgnoreCase("dwg")) 
	    throw new Exception("Can not convert '"+origin.getName()+"' to PDF: it is not an an AutoCAD Drawing (.dwg).");
	  String tmpDir = Properties.get(Names.TEMP_DIR) + Names.PATH_SEPARATOR + "dwgLayouts-" + nextCount();
	  File outDir = new File(tmpDir);
	  //if (!outDir.exists()) outDir.mkdirs();
	  
	  String n=objectName(origin.getName());
	  try { export(workspace, tmpDir, n, id); }
	  catch (Exception e) { System.out.print("Failed to export " + n); {}//Logwriter.printOnConsole(e.getMessage());}
	  
	  }
	  
	  File sourceDWG = new File(outDir, n);
	  DWGLayouts2PDF converter = new DWGLayouts2PDF();
	  converter.setDatasource(null);
	  converter.setAuthentication(lookupID(id));
	  converter.setSourceAutoCADFile(sourceDWG);
	  //converter.setOutputDirectory(outDir);
	  converter.execute();
	  return converter.getResults();
  }

  private synchronized void convertDrawing(ProEConverter converter, Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    if (!FileNameUtil.getFileNameExtension(origin.getName()).equalsIgnoreCase("drw")) 
      throw new Exception("Can not convert '"+origin.getName()+"': it is not a drawing.");
    String n=objectName(origin.getName());
    converter.setDatasource(this);
    converter.setAuthentication(lookupID(id));
    converter.setComponentName(n);
    converter.setWorkspaceName(workspace);
    converter.setBinaryOutputPath(outputPath);
    converter.setBinaryFilename(outputName);
    converter.execute();
  }

  public String getDefaultUsername() { return defaultUsername; }
  public void setDefaultUsername(String s) { defaultUsername=s; }
  public String getDefaultPassword() { return defaultPassword; }
  public void setDefaultPassword(String s) { defaultPassword=s; }
  
	public String getQuarantineFolder() { return quarantineFolder; }
	public void setQuarantineFolder(String s) { quarantineFolder = s; }
  public String getBinPath() { return binPath; }
  public void setBinPath(String s) { binPath=s; }
  public String getILLIBPath() { return illibPath; }
  public void setILLIBPath(String s) { illibPath=s; }
  public String getEXEToolkitEnv() { return exeToolkitEnv; }
  public void setEXEToolkitEnv(String s) { exeToolkitEnv=s; }
  //public String getTNSNamesPath(){ return getDataServer().getTNSNamesPath(); }
  //public void setTNSNamesPath(String s) throws Exception { getDataServer().setTNSNamesPath(s); }
  //public String getDataServerUsername() { return getDataServer().getUsername(); }
  //public void setDataServerUsername(String s) throws Exception { getDataServer().setUsername(s); }
  //public String getDataServerPassword() { return getDataServer().getPassword(); }
  //public void setDataServerPassword(String s) throws Exception { getDataServer().setPassword(s); }
  public boolean getCleanExportDir() { return cleanExportDir; }
  public void setCleanExportDir(boolean b) { cleanExportDir=b; }
  
  public void setUserDefinedAttributes(String s) { 
    StringTokenizer tokens = new StringTokenizer(s, Names.DELIMITER);
    while (tokens.hasMoreTokens()) defineUserAttribute(tokens.nextToken().trim());
  }

  public Collection getUserDefinedAttributes() { return userDefinedAttributes; }
  public void defineUserAttribute(String name) { userDefinedAttributes.add(name); }
  
  public String getDateFormat() { return dateFormat; }
  public void setDateFormat(String s) { dateFormat=s; }

  public long parseDate(String formattedDate) {
    long d=0;
    if (null==formattedDate || "".equals(formattedDate)) return 0;
    if (formattedDate.indexOf(".")>0) d = parseDottedDate(formattedDate);
    else d = parseSimpleDate(formattedDate);
    return d;
  }

  private long parseDottedDate(String dottedDate) {
    int year=0, month=0, day=0, hour=0, min=0, sec=0;
    StringTokenizer tokens = new StringTokenizer(dottedDate, ".");
    if (tokens.hasMoreTokens()) year = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) month = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) day = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) hour = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) min = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) sec = Integer.valueOf(tokens.nextToken()).intValue();
    Calendar c = new GregorianCalendar(year, month-1, day, hour, min, sec);
    {}//Logwriter.printOnConsole("Dotted Date: " + c.getTimeInMillis());
    return c.getTimeInMillis();
  }
  
  private long parseSimpleDate(String formattedDate) {
    if (null==formattedDate || "".equals(formattedDate)) return 0;
    SimpleDateFormat dateFormatter = new SimpleDateFormat(getDateFormat());
    String d = formattedDate.replace(',', ' ');
    return dateFormatter.parse(d, new ParsePosition(0)).getTime();
  }  

  public String getReleaseLevelConfiguration() { return releaseLevelConfiguration; }
  public void setReleaseLevelConfiguration(String s){
    releaseLevelConfiguration=s;
    StringTokenizer tokens = new StringTokenizer(s, Names.DELIMITER);
    int idx=1;
    while (tokens.hasMoreTokens()) releaseLevelSequence.put(tokens.nextToken().trim(), new Integer(idx++));
  }
 
  public String getRevisionConfiguration() { return revisionConfiguration; }
  public void setRevisionConfiguration(String s){
    revisionConfiguration=s;
    StringTokenizer tokens = new StringTokenizer(s, Names.DELIMITER);
    int idx=1;
    while (tokens.hasMoreTokens()) revisionSequence.put(tokens.nextToken().trim(), new Integer(idx++));
  }
  public int getSequenceForReleaseLevel(String releaseLevel) {
    Integer idx = (Integer)releaseLevelSequence.get(releaseLevel);
    if (null==idx) return -1;
    return idx.intValue();
  }
  public int getSequenceForRevision(String Revision) {
    Integer idx = (Integer)revisionSequence.get(Revision);
    if (null==idx) return -1;
    return idx.intValue();
  }
  public void add(ReleaseScheme s) throws DuplicateName {
    if (releaseSchemes.containsKey(s.getName())) throw new DuplicateName(s.getName(), "ReleaseScheme");
    releaseSchemes.put(s.getName(), s);
  }
  public void add(IntralinkUser u) throws DuplicateName {
    if (users.containsKey(u.getLogin())) throw new DuplicateName(u.getLogin(), "Intralink User");
    users.put(u.getLogin(), u);
  }

  public void add(Attribute a) throws DuplicateName {
    if (userAttributes.containsKey(a.getName())) throw new DuplicateName(a.getName(), "UserDefinedAttribute");
    userAttributes.put(a.getName(), a);
  }

  //public void add(IntralinkFolder root) { rootFolder=root; {}//Logwriter.printOnConsole(root); }

  public void setRevisionSequence(Enumeration e) { revisions=e; }
  
  public Enumeration getRevisionSequence() { return revisions; }
  public Map getReleaseSchemes() { return releaseSchemes; }
  public ReleaseScheme getDefaultReleaseScheme() { return (ReleaseScheme)releaseSchemes.get(defaultReleaseScheme); }
  public void setDefaultReleaseScheme(String name) { defaultReleaseScheme=name; }
  
  public void rename(String name, String newName, Authentication id) throws NameNotFound, DuplicateName, IsLocked, Exception { 
    Rename op = new Rename();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.setComponentName(objectName(name));
    op.setNewName(objectName(newName));
    op.execute();
  }
  
  public void trash(String name, Authentication id) throws NameNotFound, DuplicateName, IsLocked, Exception { 
    move(name, getTrashFolder(),id);
  }
  public void move(String name, String path, Authentication id) throws NameNotFound, PathDoesNotExist, IsLocked, Exception {
    Move op = new Move();
    op.setDatasource(this);
    op.setComponentName(objectName(name));
    op.setPath(path);
    op.setAuthentication(lookupID(id));
    op.execute();
  }
  
  //Temporarily read from configuration
  //public IntralinkFolder getRootFolder() throws Exception { return getFolderTree(); }

  public IntralinkFolder getRootFolder(Authentication id)throws Exception {
	  GetFolderTree op = new GetFolderTree();
	  op.setDatasource(this);
	  op.setAuthentication(lookupID(id));
	  op.execute();
	  return op.getRootFolder();
	}  
  
  public Collection listNames(Authentication id) throws Exception {
    ListNames op = new ListNames();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.execute();
    return op.getResults();
  }

  public Collection listComponents(String path, boolean recursively, Authentication id) throws PathDoesNotExist, Exception {
    //SearchAgent agent = new SearchAgent();
    //agent.setDatasource(this);
    //agent.setCriteria("[Name=* & Folder=*RepSource]");
    //agent.search();
    //return agent.getResults();
    return null;
  }

  public Collection listComponents(String path, Authentication id) throws PathDoesNotExist, Exception {
    GetFolderListing op = new GetFolderListing();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.setPath(path);
    op.setListComponents(true);
    op.setListFolders(false);
    op.execute();
    return op.getResults();
  }
  public Collection listSubFolders(String path, boolean recursive, Authentication id) throws PathDoesNotExist, Exception {
    GetFolderListing op = new GetFolderListing();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.setPath(path);
    op.setListComponents(false);
    op.setListFolders(true);
    op.setListRecursively(recursive);
    op.execute();
    return op.getResults();
  }
  public Collection getFolderListing(String path, boolean recursive, Authentication id) throws PathDoesNotExist, Exception {
    GetFolderListing op = new GetFolderListing();
    op.setDatasource(this);
    op.setPath(path);
    op.setListComponents(true);
    op.setListFolders(true);
    op.setListRecursively(recursive);
    op.setAuthentication(lookupID(id));
    op.execute();
    return op.getResults();
  }

  public void createFolder(String name, String parent, Authentication id) throws InvalidName, PathDoesNotExist, DuplicateName, Exception {
    CreateFolder op = new CreateFolder();
    op.setDatasource(this);
    op.setName(name);
    op.setParent(parent);
    op.setAuthentication(lookupID(id));
    op.execute();
  }
  public void createFolder(String name, String parent, String releaseScheme, Authentication id) throws InvalidName, PathDoesNotExist, NameNotFound, DuplicateName, Exception {
    CreateFolder op = new CreateFolder();
    op.setDatasource(this);
    op.setName(name);
    op.setParent(parent);
    op.setReleaseScheme(releaseScheme);
    op.setAuthentication(lookupID(id));
    op.execute();
  }
  
  public void createFolder(String name, String parent, String releaseScheme, String fileSpace, Authentication id) throws InvalidName, PathDoesNotExist, NameNotFound, DuplicateName, Exception {
    CreateFolder op = new CreateFolder();
    op.setDatasource(this);
    op.setName(name);
    op.setParent(parent);
    op.setReleaseScheme(releaseScheme);
    op.setFileSpace(fileSpace);
    op.setAuthentication(lookupID(id));
    op.execute();
  }

  public void deleteFolder(String path, Authentication id) throws PathDoesNotExist, NotEmpty, Exception {
    DeleteFolder op = new DeleteFolder();
    op.setDatasource(this);
    op.setPath(path);
    op.setAuthentication(lookupID(id));
    op.execute();
  }
  
  public Collection personalListWorkspaces(Authentication id) throws NameNotFound, Exception {
	  ListWorkspaces op = new ListWorkspaces();
	  op.setDatasource(this);
	  op.setUsingPersonalWorkspace(true);
	  op.setAuthentication(lookupID(id));
	  op.execute();
	  return op.getResults();
  }
 
  public Collection listWorkspaces(String workspace, Authentication id) throws NameNotFound, Exception {
      ListWorkspaces op = new ListWorkspaces();
      op.setDatasource(this);
      op.setWorkspaceName(workspace);
      op.setUsingPersonalWorkspace(false);
      op.setAuthentication(lookupID(id));
      op.execute();
      return op.getResults();
  }
  
  public Collection listWorkspaces(boolean personalWorkspace, String workspace, Authentication id) throws NameNotFound, Exception {
    ListWorkspaces op = new ListWorkspaces();
    op.setDatasource(this);
    op.setUsingPersonalWorkspace(personalWorkspace);
    op.setAuthentication(lookupID(id));
    op.execute();
    return op.getResults();
  }
  
  public Workspace personalReportWorkspaceStatus(String workspaceName, Authentication id) throws NameNotFound, Exception {
    return reportWorkspaceStatus(workspaceName, true, id);      
  }
 
  public Workspace reportWorkspaceStatus(String workspaceName, Authentication id) throws NameNotFound, Exception {
    return reportWorkspaceStatus(workspaceName, false, id);      
  }
  
  public Workspace reportWorkspaceStatus(String workspaceName, boolean personalWorkspace, Authentication id) throws NameNotFound, Exception {
    ReportWorkspaceStatus op = new ReportWorkspaceStatus();
    op.setDatasource(this);
    op.setUsingPersonalWorkspace(personalWorkspace);
    op.setWorkspaceName(workspaceName);
    op.setAuthentication(lookupID(id));
    op.execute();
    return op.getWorkspace();
  }

  public Collection  personalReportAllWorkspaceStatus(Authentication id) throws NameNotFound, Exception {
	  ReportAllWorkspaceStatus op = new ReportAllWorkspaceStatus();
	  op.setDatasource(this);
	  op.setUsingPersonalWorkspace(true);
	  op.setAuthentication(lookupID(id));
	  op.execute();
	  return op.getResults();
  }

  public Collection reportAllWorkspaceStatus(String workspace, Authentication id) throws NameNotFound, Exception {
	  ReportAllWorkspaceStatus op = new ReportAllWorkspaceStatus();
	  op.setDatasource(this);
	  op.setWorkspaceName(workspace);
	  op.setUsingPersonalWorkspace(false);
	  op.setAuthentication(lookupID(id));
	  op.execute();
	  return op.getResults();
  }

  public Collection personalListWorkspaceContents(String workspaceName, Authentication id) throws NameNotFound, Exception {
    return listWorkspaceContents(workspaceName, false, true, id);      
  }

  public Collection listWorkspaceContents(String workspaceName, boolean showInstances, Authentication id) throws NameNotFound, Exception {
    return listWorkspaceContents(workspaceName, showInstances, false, id);      
  }

  public Collection listWorkspaceContents(String workspaceName, boolean showInstances, boolean personalWorkspace, Authentication id) throws NameNotFound, Exception {
    ListWorkspaceContents op = new ListWorkspaceContents();
    op.setDatasource(this);
    op.setUsingPersonalWorkspace(personalWorkspace);
    op.setWorkspaceName(workspaceName);
    op.setShowInstances(showInstances);
    op.setAuthentication(lookupID(id));
    op.execute();
    return op.getResults();
  }

  public Metadata findInWorkspace(String workspaceName, String name, boolean showInstances, Authentication id) throws NameNotFound, Exception {
    Metadata data = null;
    FindInWorkspace op = new FindInWorkspace();
    op.setDatasource(this);
    op.setComponentName(objectName(name));
    op.setWorkspaceName(workspaceName);
    op.setShowInstances(showInstances);
    op.setAuthentication(lookupID(id));
    op.execute();
    data = (Metadata)op.getResult();
    if (null==data) throw new NameNotFound(name, workspaceName);
    if (name.equalsIgnoreCase(data.getName())) return data;
    else throw new NameNotFound(name, workspaceName);
  }

  public InputStream findBinaryForPackage(Metadata data, Authentication id) throws Exception { 
    return findBinary(data.getOrigin(), NO_DEPENDENCIES, true, true, false, !Server.debugMode(), id); 
  }

  public Collection getDependencies(IntralinkOrigin o, boolean includeHistory, Authentication id) throws Exception {
    return getDependencies(o, ALL_DEPENDENCIES, includeHistory, id); 
  }

  public Collection getDependencies(IntralinkOrigin o, String associations, boolean includeHistory, Authentication id) throws Exception {
    String n=objectName(o.getName());
    GetDependencies op = new GetDependencies();
    op.setDatasource(this);
    op.setAuthentication(lookupID(id));
    op.setComponentName(n);
    op.setBranch(o.getBranch());
    op.setRevision(o.getRevision());
    op.setVersion(o.getVersion());
    op.setAssociations(associations);
    if (includeHistory) op.setConfiguration(AS_STORED_CONFIGURATION);
    else op.setConfiguration(LATEST_CONFIGURATION);
    op.execute();
    return op.getResults();
  }

  public void bindDependenciesAsSubComponents(Metadata parent, boolean includeHistory, Authentication id) throws Exception {
    Metadata data;
    Collection dependencies;
    dependencies = getDependencies((IntralinkOrigin)parent.getOrigin(), ALL_DEPENDENCIES, includeHistory, id);
    MetadataSubComponentBase kid;
    Iterator i = dependencies.iterator();
    while (i.hasNext()) {
      data = (Metadata)i.next();
      kid = new MetadataSubComponentBase(data);
      parent.addSubComponent(kid);
    }
  }

  public Metadata findMetadataForPackage(Metadata data, boolean includeHistory, Authentication id) throws Exception { 
    //Transform origin into IntralinkOrigin if necesary - or better - modify workspaceName to use uniqueSeq stead of ilinkorigin
    Metadata m = findInWorkspace(workspaceName((IntralinkOrigin)data.getOrigin()), data.getName(), true, id);
    long time = m.getOrigin().getTimeOfCreationInMillis();
    //if (mayHaveSubComponents(m)) bindDependenciesAsSubComponents(m, id); 
    bindDependenciesAsSubComponents(m, includeHistory, id);
    {}//Logwriter.printOnConsole("##################################################");
    {}//Logwriter.printOnConsole(m);
    return m;
  } 
  public void cleanUp(Metadata data, Authentication id) throws Exception { 
    destroyWorkspace(workspaceName((IntralinkOrigin)data.getOrigin()), id);
  }
/*
  public Origin importFromPackage(PackageSource pkg, Metadata data, MetadataMappingSet mappings, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole("Importing " + data.getOrigin().getUniqueID());
    Origin o = data.getOrigin();
    File destination = new File(Properties.get(Properties.tempDirectory)+Names.PATH_SEPARATOR + "destination" + getNewCount());    
    if (destination.exists()) deleteDir(destination);
    destination.mkdirs();
    File target = pkg.exportBinary(o, destination, id);
    String workspace = workspaceName((IntralinkOrigin)o);
    createWorkspace(workspace, id); //destroyWorkspace removes the .proi directory. createWorkspace does not create it. and fails
    Metadata mappedData = mapAttributes(data, mappings);
    try { checkout(mappedData.getName(), mappedData.get(BRANCH), workspace, ALL_DEPENDENCIES, true, true, false, id); }
    catch (NameNotFound e) { {}//Logwriter.printOnConsole("Importing a new object: "+ mappedData); } 
    if (data.hasSubComponents()) checkoutSynchronizedSubComponents(workspace, data.getSubComponents(), null, id);
    importToWorkspace(workspace, target.getAbsolutePath(), NO_DEPENDENCIES);
    importAttributes(mappedData, workspace, id);
    checkin(workspace, id);
    //replace with wsi or with lookup
    Origin update;
    try { update = findInWorkspace(workspace, data.getName(), id).getOrigin(); }
    catch (Exception e) { throw new Exception("Failed to import into workspace: " + data,e); }
    destroyWorkspace(workspace, id);
    return update;
  }
*/
  
  protected Collection findConflicts(Origin o, PackageSourceBase pkg, Authentication id) {
    Collection conflicts = new Vector();
    Metadata data;
    Metadata latest;
    ConflictBase conflict;
    try {
        data = pkg.findMetadata(o, id);
        latest = findLatest(data.getName(), id);
        conflict = new ConflictBase(); 
        conflict.setMessageKey("duplicate.name");
        conflict.addParameter(latest.getName());
        conflict.addParameter(latest.get("folder"));
        conflicts.add(conflict);
    }
    catch (Exception e) { return conflicts; }
    if (data.hasFamilyInstances()) {
     Iterator i = data.getFamilyInstances().iterator();
     while (i.hasNext()) {
      data = (Metadata)i.next();
      try {
          latest = findLatest(data.getName(), id);
          conflict = new ConflictBase(); 
          conflict.setMessageKey("duplicate.name");
          conflict.addParameter(latest.getName());
          conflict.addParameter(latest.get("folder"));
          conflicts.add(conflict);
      }
      catch (Exception e) { }
     }
    }
    return conflicts;
  }

  private Map mapProEFiles(File dir) {
    Map fileMap = new HashMap();
    if (!dir.isDirectory()) return fileMap;
    File[] files = dir.listFiles();
    if (0==files.length) return fileMap;
    String ext, filename;
    int iteration;
    for (int idx=0; idx<files.length; idx++) {
     iteration=-1;
     ext = FileNameUtil.getFileNameExtension(files[idx].getName());
     try { 
       iteration = Integer.valueOf(ext).intValue();
       filename = FileNameUtil.getBaseName(files[idx].getName());
     }
     catch (NumberFormatException ignore) {
       filename = files[idx].getName();
     }
     MapUtil.getCollectionFromMap(fileMap, filename).add(files[idx]);
    }
    Iterator i;
    Iterator f = fileMap.keySet().iterator();
    Collection iterations;
    File file, fileMax;
    int max;
    while(f.hasNext()) {
      filename = (String)f.next();
      iterations= (Collection)fileMap.get(filename);
      i = iterations.iterator();
      if (1==iterations.size()) {
        fileMap.put(filename, i.next());
        continue;
      }
      max=-1;
      fileMax=null;
      while (i.hasNext()) {
        file = (File)i.next();
        if (null==fileMax) {
          fileMax=file;
          continue;
        }
        ext = FileNameUtil.getFileNameExtension(file.getName());        
        try { iteration = Integer.valueOf(ext).intValue(); }
        catch (NumberFormatException ignore) { iteration = 0;}
        ext = FileNameUtil.getFileNameExtension(fileMax.getName());        
        try { max = Integer.valueOf(ext).intValue(); }
        catch (NumberFormatException ignore) { max = 0;}
        if (iteration>max) fileMax = file;
      }
      fileMap.put(filename, fileMax);
    }
    return fileMap;
  }

  protected void  exportToPackage(PackageSourceBase pkg, Collection updates, boolean includeHistory, Authentication id) throws Exception {
	  if (null==updates || updates.size()<1) return;
	  String workspace = null;
	  workspace = "pkg" +getNewCount();
    File exportDir= new File(Properties.get(Properties.tempDirectory)+Names.PATH_SEPARATOR + workspace);
    //if (exportDir.exists()) deleteDir(exportDir);
    exportDir.mkdirs();
    FileUtil.deleteContents(exportDir);

	  createWorkspace(workspace, id);
	  boolean showDependencies = true;
    
    //trim duplicate files
    Map uniqueItems = new HashMap();
    Iterator j = updates.iterator();
    Metadata m,n;
    Origin o, p;
    while (j.hasNext()) {
      m = (Metadata)j.next();
      o = m.getOrigin();
      if (uniqueItems.containsKey(o.getName())) {
        n = (Metadata)uniqueItems.get(o.getName());
        p = n.getOrigin();
        if (!p.isExactlyTheSame(o)) throw new MultipleVersionsOfSameFile(o, p);
      }
      else uniqueItems.put(o.getName(), m);
    }
    
    Collection updatedItems = uniqueItems.values();
	  Collection metadataList = this.checkoutList(updatedItems, workspace, NO_DEPENDENCIES, true, true, false, showDependencies, includeHistory, false, id);

	  exportList(workspace, updatedItems, NO_DEPENDENCIES, exportDir, false, id);
	  File[] files = exportDir.listFiles();
	  if (files.length != updatedItems.size()) {
	    Alert.notify("IER Warning("+getName()+"): Number of exported files do not match!", "Attempted to export "+updatedItems.size()+" files and "+files.length+" files were found");
	  }
	  if (files.length < updatedItems.size()) {
		  throw new FailedToExportBinaryFiles(getName(), updatedItems, files);
		}
	  Iterator i = metadataList.iterator();
	  InputStream binary;
	  Map exportedFiles = mapProEFiles(exportDir);
	  while (i.hasNext()) {
	    try {
		    m = (Metadata)i.next();
		    {}//Logwriter.printOnConsole("Packaging: " + ((File)exportedFiles.get(m.getName())).getAbsolutePath());
	      binary = new FileInputStream((File)exportedFiles.get(m.getName()));
	      pkg.storeBinary(m.getOrigin(), binary, id);
	      binary.close();
	      pkg.storeMetadata(m, null);
	    }
	    catch(Exception e) {
	      String msg = "Errored while packaging: " + e.getMessage();
	      msg += "Continuing package creation anyway";
	      Alert.notify("IER ERROR!!", msg);
	    }
	  }
	  if (!Server.debugMode()) {
      if (exportDir.exists()) deleteDir(exportDir);
      destroyWorkspace(workspace, id);
	  }
	}

  protected Collection importFromPackage(PackageSource pkg, Collection metadataList, MetadataMappingSet mappings, boolean lockOnImport, boolean overwriteConflicts, Authentication id) throws Exception {
    if (null==metadataList || metadataList.isEmpty()) return new Vector();
    String key = "pkg" +getNewCount();
    File destination = new File(Properties.get(Properties.tempDirectory)+Names.PATH_SEPARATOR + key);    
    if (destination.exists()) deleteDir(destination);
    destination.mkdirs();
    
    Collection targetFiles = new Vector();
    Iterator i = metadataList.iterator();
    Metadata data;
    File target=null;
    Origin o=null;
    boolean importingFamilyTables = false;
    while (i.hasNext()){
      data = (Metadata)i.next();
      o = data.getOrigin();
      target = pkg.exportBinary(o, destination, id);
      targetFiles.add(target);
      if (data.hasFamilyInstances() && isProEngineerFile(data.getName())) importingFamilyTables = true;
    }
    String workspace = key; //workspaceName((IntralinkOrigin)o);
    createWorkspace(workspace, id); //destroyWorkspace removes the .proi directory. createWorkspace does not create it. and fails

    //map the data from source list to a target list
    //checkout the items in the list and their dependencies
    i = metadataList.iterator();
    Metadata mappedData=null;
    Collection mappedMetadataList = new Vector();
    Map circularReferences = new HashMap();
    Origin sync;
    while (i.hasNext()) {
      data = (Metadata)i.next();
      {}//Logwriter.printOnConsole("IFPSource: " + data);
      mappedData = mapAttributes(data, mappings);
      mappedMetadataList.add(mappedData);
      {}//Logwriter.printOnConsole("IFPMapped: " + mappedData);
      sync=null;
      try {
        sync = SynchronizationSvc.lastSynchronization(getDomainName(), getServerName(), getName(), data.getName());
      }
      catch(Exception ignore) {}
      try {
        if (null!=sync) checkoutLatest(workspace, mappedData.getName(), id); 
            //checkout(mappedData.getName(), mappedData.get(BRANCH), workspace, ALL_DEPENDENCIES, true, true, false, id);
      }
      catch (NameNotFound e) { } {}//Logwriter.printOnConsole("Importing a new object: "+ mappedData); }
      if (data.hasSubComponents()) checkoutSynchronizedSubComponents(workspace, data.getSubComponents(), mappedMetadataList, circularReferences, id);
    }
    //import everything in the list at once - gets the cyclical deps
    {}//Logwriter.printOnConsole("importing: " + mappedData);
    if (importingFamilyTables) {
      if (1==targetFiles.size()) importFamilyTableToWorkspace(workspace, target.getAbsolutePath(), NO_DEPENDENCIES, false, id);
      else importFamilyTableToWorkspace(workspace, targetFiles, NO_DEPENDENCIES, id);
    }
    else {
      if (1==targetFiles.size()) importToWorkspace(workspace, target.getAbsolutePath(), NO_DEPENDENCIES, false, id);
      //else importToWorkspace(workspace, targetFiles, ALL_DEPENDENCIES, id);
      else importToWorkspace(workspace, targetFiles, NO_DEPENDENCIES, id);
    }
    //update the mapped attributes
    
    /*
    i = mappedMetadataList.iterator();
    while(i.hasNext()) {
      mappedData = (Metadata)i.next();
      importAttributes(mappedData, workspace, id);
    }
    */

    importAttributes(mappedMetadataList, workspace, id);
    adoptGhosts(workspace, id);
    
    Collection wsStatus = reportAllWorkspaceStatus(workspace, id);
    Workspace importWS = (Workspace)wsStatus.iterator().next();

    //also look for modified objects that have not been synchronized - these are duplicates
    if (importWS.hasConflicts()) {
      Iterator con = importWS.getConflicts().iterator();
      WorkspaceItem wsItem;
      WorkspaceConflict wsConflict;
      while (con.hasNext()) {
        wsItem = (WorkspaceItem)con.next();
        Iterator itemCon = wsItem.getConflicts().iterator();
        while(itemCon.hasNext()) {
          wsConflict = (WorkspaceConflict) itemCon.next();
          if (wsConflict.getConflictKey().equals("duplicate.name")) {
            {}//Logwriter.printOnConsole("==============================");
            {}//Logwriter.printOnConsole("["+wsConflict.getName() + ": " + wsConflict.getConflictKey() +"] ");
            {}//Logwriter.printOnConsole("==============================");
          }
        }
      }
    }

    String DOT = Names.DOT;
    boolean overwritable = overwriteConflicts;
    if (importWS.hasConflicts()) {
      String conflictMessage="Conflicts importing data" + Names.NEW_LINE;
      conflictMessage = "package: " + pkg.getName() + Names.NEW_LINE;
      conflictMessage += "target: " + getDomainName()+DOT+getServerName()+DOT+getName()+Names.NEW_LINE ;
      Iterator con = importWS.getConflicts().iterator();
      WorkspaceItem wsItem;
      WorkspaceConflict wsConflict;
      while (con.hasNext()) {
        wsItem = (WorkspaceItem)con.next();
        Iterator itemCon = wsItem.getConflicts().iterator();
        while(itemCon.hasNext()) {
          wsConflict = (WorkspaceConflict) itemCon.next();
          conflictMessage += Names.NEW_LINE +wsConflict.getName() + ": " + wsConflict.getConflictKey();
          if ("folder.is.undefined".equals(wsConflict.getConflictKey())) conflictMessage += ": check for unverified instance";
          if (!"duplicate.name".equals(wsConflict.getConflictKey())) overwritable=false;;
        }
      }
      {}//Logwriter.printOnConsole(conflictMessage);
      String subj = "IER CONFLICT!! ";
      if(importWS.getConflicts().size()==1) subj += "Detected 1 Conflict";
      else subj += "Detected " + importWS.getConflicts().size()+" Conflicts";
      if (overwriteConflicts) {
        if (!overwritable) subj += " (can not overwrite!)";
        else subj += " (attempting overwrite)";
      }
      else subj += " (import cancelled)";
      alert(subj, conflictMessage, id);
      if (overwriteConflicts && !overwritable) throw new Exception("IER ERROR!! Could not overwrite conflicts");      
      if (!overwritable) throw new Exception("IER ERROR!! Import cancelled due to conflicts");
    }
    checkin(workspace, id);
    wsStatus = reportAllWorkspaceStatus(workspace, id);
    importWS = (Workspace)wsStatus.iterator().next();
    if (importWS.hasNewItems() || importWS.hasModifiedItems()) {
      String msg = "Workspace Could not be checked in." + Names.NEW_LINE;
      msg += "Check for unverified instances" + Names.NEW_LINE;
      Iterator newItems = importWS.getNewItems().iterator();
  	  WorkspaceItem item;
      while(newItems.hasNext()) {
      	item = (WorkspaceItem)newItems.next();
      	msg += Names.NEW_LINE + " - " +item.getName() + " is new.";
        
      }
      Iterator modItems = importWS.getModifiedItems().iterator();
      while(modItems.hasNext()) {
      	item = (WorkspaceItem)modItems.next();
      	msg += Names.NEW_LINE + " - " +item.getName() + " has been modified.";
      }
      alert("IER ERROR!! Importing Files to " + getName() + "!!", msg, id);
      throw new FailedToTransferFile(mappedData.getName(), getName());      
    }
        

    //retrieve targets
    //replace with wsi or with lookup
    Collection updates=new Vector();
    Origin update=null;
    Collection locks = new Vector();
    i = mappedMetadataList.iterator();
    while(i.hasNext()) {
      mappedData = (Metadata)i.next();
      try { update = findInWorkspace(workspace, mappedData.getName(), false, id).getOrigin(); }
      catch (Exception e) { 
        /*
        boolean continueImportOnError = false;
        String s = Properties.get("continue-package-import-on-error");
        if (s!=null && s.trim().toLowerCase().startsWith("true")) continueImportOnError = true;
        if(continueImportOnError) break; //+++log this collection of files that did not import!
        //Alert.notify("Replication conflict", "Failed to import into workspace");
         */
        String msg = mappedData.getName() + " could not be imported into " + getName() + Names.NEW_LINE;
        		
        Iterator j = mappedMetadataList.iterator();
        Metadata m;
        String n="";
        String fileList="";
        int importCount = 0;
        if (j.hasNext()) {
            m = (Metadata)j.next();
            n=m.getName();
            if (!n.equals(mappedData.getName())) {
              fileList += " + " + n + Names.NEW_LINE;
              importCount++;
            }
        }
        while (j.hasNext() && !n.equals(mappedData.getName())) {
          m = (Metadata)j.next();
          fileList += " + " + n + Names.NEW_LINE;
          importCount++;
        }
        if (importCount > 0 ) {
          msg += "However, the following files may have been imported" + Names.NEW_LINE;
          msg += fileList;
          msg += "If these files were imported they will not have been synchronized." + Names.NEW_LINE;
          msg += "They may show up as duplicates next time they are replicated" + Names.NEW_LINE;
        }
        alert("IER ERROR!! Importing Files to " + getName() + "!!", msg, id);
        throw new FailedToTransferFile(mappedData.getName(), getName());
      }
      locks.add(update.getName());
      updates.add(update);
    }
    try  { if(lockOnImport) lock(locks, id); }
    catch (Exception e) {
      {}//Logwriter.printOnConsole("could not lock " + mappedData.getName());
      alert("IER ERROR!! Locking File", "Could not lock " + mappedData.getName(), id);
      //++++notify
    }          
    destroyWorkspace(workspace, id);
    return updates;
  }
  
  private void checkoutSynchronizedSubComponents(String workspace, Collection subComponents, Collection links, Map circularRef, Authentication id) throws Exception {
    MetadataSubComponent sub;
    Origin o=null;
    Iterator i = subComponents.iterator();
    Iterator j;
    Metadata linked;
    boolean subComponentIsLinked = false;
    while (i.hasNext()) {
      sub = (MetadataSubComponent)i.next();
      j = links.iterator();
      subComponentIsLinked = false; 
      while(j.hasNext() && subComponentIsLinked) {
        linked = (Metadata)j.next();
        if(linked.getName().equalsIgnoreCase(sub.getName())) subComponentIsLinked=true;
      }//Uncomment below line and get this safety check working working again
      Object cycle;
      o = null;
      if (!subComponentIsLinked) { // if a subcomponent is not linked/imported with this component, it should be already synchronized
        try {
          o = SynchronizationSvc.findSynchronization(sub.getOrigin(), getDomainName(), getServerName(), getName());
          {}//Logwriter.printOnConsole("Subcomponent not changed - retrieving excact synchronization: " + sub.getName());
          //o = SynchronizationSvc.lastSynchronization(getDomainName(), getServerName(), getName(), sub.getName());
          cycle = circularRef.get(o.getUniqueID());
          if (null==cycle) {
            checkout(o, workspace, NO_DEPENDENCIES, true, true, false, id);
            //checkoutLatest(workspace, o.getName(), id);
            circularRef.put(o.getUniqueID(), o);
          }
        }
        catch (MatchNotFound e) {// if a subcomponent is not linked/imported with this component, it should be already synchronized
          //+++ log that this dependency has not been synchronized
          //    so we attempted to find it and checkout the latest from the target anyway.
          //+++ attempt to find it before trying to check it out.
          
          //don't check out subcomponent if it is not linked.
          cycle = circularRef.get(sub.getOrigin().getName());
          if (null==cycle) {
            try {
              o = SynchronizationSvc.lastSynchronization(getDomainName(), getServerName(), getName(), sub.getName());
              //checkout(sub.getOrigin().getName(), "main", workspace, NO_DEPENDENCIES, true, true, false, id);
              if (null!=o) {
                {}//Logwriter.printOnConsole("exact synchronization not, checking out latest: " + sub.getName());
                checkoutLatest(workspace, sub.getName(), id);
                circularRef.put(sub.getOrigin().getName(),sub.getOrigin());
              }
              else {
                {}//Logwriter.printOnConsole("Subcomponent not yet synchronized: " + sub.getName());                  
              }
            }
            catch (Exception ignore) {}
          }
        }
      }
      else {
	      cycle = circularRef.get(sub.getOrigin().getName());
	      if (null==cycle) {
	        try {
	          o = SynchronizationSvc.lastSynchronization(getDomainName(), getServerName(), getName(), sub.getName());
	          //checkout(sub.getOrigin().getName(), "main", workspace, NO_DEPENDENCIES, true, true, false, id);
	          if (null!=o) {
	            {}//Logwriter.printOnConsole("Subcomponent updated - retrieving prior synchronization: " + sub.getName());
	            checkoutLatest(workspace, sub.getName(), id);
	            circularRef.put(sub.getOrigin().getName(),sub.getOrigin());
	          }
	          else {
	            {}//Logwriter.printOnConsole("Subcomponent added : " + sub.getName());                  
	          }
	        }
	        catch (Exception ignore) {}
	      }          
      }
    }
  }
/*
  private Metadata mapAttributes(Metadata data, MetadataMappingSet mappings) throws Exception {
    if (null==mappings.getContext()) mappings.setContext(new DomainContext());
    mappings.getContext().setTargetRouting(this);
    MetadataBase mappedData=new MetadataBase();
    mappedData.setName(data.getName()); //incase name is not mapped
    mappings.setSource(data);
    mappings.setTarget(mappedData);
    mappings.map();
    mappedData.setOrigin(materialize(mappedData));
    if (!data.hasFamilyInstances()) return mappedData;
    //map family table members
    Iterator i = data.getFamilyInstances().iterator();
    MetadataBase d;
    MetadataFamilyInstance instance;
    while (i.hasNext()) {
      instance= (MetadataFamilyInstance)i.next();
      d = new MetadataBase();
      d.setName(instance.getName()); //incase name is not mapped
      mappings.setSource(instance);
      mappings.setTarget(d);
      mappings.map();
      d.setOrigin(materialize(d));
      mappedData.addFamilyInstance(new MetadataFamilyInstanceBase(d));
    }
    return mappedData;
  }
*/

  private Metadata mapAttributes(Metadata data, MetadataMappingSet mappings) throws Exception {
    if (null==mappings.getContext()) mappings.setContext(new DomainContext());
    mappings.getContext().setTargetRouting(this);
    MetadataBase mappedData=new MetadataBase();
    mappedData.setName(data.getName()); //incase name is not mapped
    mappings.setSource(data);
    mappings.setTarget(mappedData);
    mappings.map();
    mappedData.setOrigin(materialize(mappedData));
    if (!data.hasFamilyInstances()) return mappedData;
    //map family table members
    Iterator i = data.getFamilyInstances().iterator();
    MetadataFamilyInstance instance;
    MetadataFamilyInstance mappedInstance;
    while (i.hasNext()) {
      instance=(MetadataFamilyInstance)i.next();
      mappedInstance = mapAttributes(instance, mappings);
      mappedData.addFamilyInstance(mappedInstance);
    }
    if (Server.debugMode()) {
      {}//Logwriter.printOnConsole(data);
      {}//Logwriter.printOnConsole("mapped to");
      {}//Logwriter.printOnConsole(mappedData);
    }
    return mappedData;
  }

  private MetadataFamilyInstance mapAttributes(MetadataFamilyInstance instance, MetadataMappingSet mappings) throws Exception {
    MetadataBase d=new MetadataBase();
    MetadataFamilyInstanceBase mappedInstance=new MetadataFamilyInstanceBase(d);
    mappedInstance.setName(instance.getName()); //incase name is not mapped
    mappings.setSource(instance);
    mappings.setTarget(mappedInstance);
    mappings.map();
    mappedInstance.setOrigin(materialize(mappedInstance));
    if (!instance.hasFamilyInstances()) return mappedInstance;
    //recursive map family table members
    Iterator i=instance.getFamilyInstances().iterator();
    MetadataFamilyInstance inst;
    MetadataFamilyInstance mappedInst;
    while (i.hasNext()) {
      inst=(MetadataFamilyInstance)i.next();
      mappedInst=mapAttributes(inst, mappings);
      mappedInstance.addFamilyInstance(mappedInst);
    }
    return mappedInstance;
  }

/*
  private Metadata importAttributes(Metadata data, String workspace, Authentication id) throws Exception {
    setAttributes(workspace,data,id);
//    if (!data.hasFamilyInstances()) return findInWorkspace(workspace, data.getName(), id);
    if (!data.hasFamilyInstances()) return null;
    Iterator i = data.getFamilyInstances().iterator();
    Metadata d;
    MetadataFamilyInstance instance;
    while (i.hasNext()) {
      instance= (MetadataFamilyInstance)i.next();
      setAttributes(workspace,instance,id);
    }
    //return findInWorkspace(workspace, data.getName(), id);
    return null;
  }
 */
  
  public void importAttributes(Collection dataList, String workspace, Authentication id) throws Exception {
	  SetAttributeList op = new SetAttributeList();
	  op.setDatasource(this);
	  op.setWorkspaceName(workspace);
	  op.setAuthentication(lookupID(id));
	  Iterator i = dataList.iterator();
	  Metadata data;
	  while(i.hasNext()) {
	    data = (Metadata) i.next();
		  importAttributes(data, op);
	  }
	  op.execute();
	}
  
  public void importAttributes(Metadata data, String workspace, Authentication id) throws Exception {
    SetAttributeList op = new SetAttributeList();
    op.setDatasource(this);
    op.setWorkspaceName(workspace);
    op.setAuthentication(lookupID(id));
    importAttributes(data, op);
    op.execute();
  }

  private void importAttributes(Metadata data, SetAttributeList op) throws Exception {
    {}//Logwriter.printOnConsole("importing Attributes For " + data);
    op.setAttributes(objectName(data.getName()), extractModifyableAttributes(data.getAttributes()));
    if (data.hasFamilyInstances()) {
      Iterator i = data.getFamilyInstances().iterator();
      MetadataFamilyInstance instance;
      while (i.hasNext()) {
        instance= (MetadataFamilyInstance)i.next();
        importAttributes(instance, op);
      }
    }
  }

  private IntralinkOrigin materialize(Metadata data) throws Exception {
    StringBuffer b = new StringBuffer();
    if (null==data.get(CREATED_ON)) data.set(CREATED_ON, DEFAULT_DATE); //must be set in order to materialize an origin: value is invalid
    if (null==data.get(VERSION)) data.set(VERSION,"0"); //must be set in order to materialize an origin: value is invalid
    b.append(getDomainName()).append(delim).append(getServerName()).append(delim).append(getType()).append(delim).append(getName()).append(delim).append(parseDate(data.get(CREATED_ON))).append(delim).append(data.get(BRANCH)).append(delim).append(data.get(REVISION)).append(delim).append(data.get(VERSION)).append(delim).append(data.getName());
    return (IntralinkOrigin)OriginMaker.materialize(b.toString());
  }

  private Metadata importAttributes(Metadata data, MetadataMappingSet mappings, String workspace, Authentication id) throws Exception {
    MetadataBase dataIn=new MetadataBase();
    dataIn.setOrigin(data.getOrigin());
    mappings.setSource(data);
    mappings.setTarget(dataIn);
    mappings.map();
    setAttributes(workspace,dataIn,id);
    StringBuffer b = new StringBuffer();
    b.append(getDomainName()).append(delim).append(getServerName()).append(delim).append(getType()).append(delim).append(getName()).append(delim).append(0).append(delim).append(dataIn.get(BRANCH)).append(delim).append(dataIn.get(REVISION)).append(delim).append(dataIn.get(VERSION)).append(delim).append(dataIn.getName());
    dataIn.setOrigin(OriginMaker.materialize(b.toString()));
    if (!data.hasFamilyInstances()) return dataIn;

    //import family table attributes, but don't worry about updating dataIn with family members
    Iterator i = data.getFamilyInstances().iterator();
    Metadata member;
    while (i.hasNext()) {
      member = (Metadata)i.next();
      dataIn=new MetadataBase();
      dataIn.setOrigin(member.getOrigin());
      mappings.setSource(member);
      mappings.setTarget(dataIn);
      mappings.map();
      setAttributes(workspace,dataIn,id);
    }
    return dataIn;
  }

  
  public void setAttributes(String workspaceName, Metadata data, Authentication id) throws Exception {
    Map m = new HashMap();
    Iterator i = data.getFieldNames().iterator();
    String fieldName;
    while (i.hasNext()){
      fieldName = (String)i.next();
      m.put(fieldName, data.get(fieldName));
    }
//    m.put("Folder", "/Root Folder/RepTarget");
//    m.put("Release-Level", "In Progress");
//    m.put("Revision", "A");
    setAttributes(workspaceName, data.getName(), m, id);
  }

  /*
  public DataServer getDataServer() {
    if (null==dataServer) dataServer=new DataServer(this);
    return dataServer;
  }
*/
  public void setEnvRoot(String path) throws FileNotFoundException {
    envRoot = new File(path);
    if (!envRoot.exists()) throw new FileNotFoundException(envRoot.getAbsolutePath());
    if (null==getName()) setName(envRoot.getName());
  }
  
  public File getEnvRoot() { return envRoot; }


  public File getPersonalLDBLocation(String user) {
   String sep = Names.PATH_SEPARATOR;
   String repository = getEnvRoot().getName();
   String userspaces = Properties.get(Names.USER_SPACE);
   if (null==userspaces || "".equals(userspaces))
     userspaces = Properties.get(Names.DATA_DIR) + sep + "space" + sep + "user";
   String ldbPath = userspaces + sep + user + sep + "workspace" + sep + repository + sep + "personal";
   File ldb = new File(ldbPath);
   File dotProI = new File(ldbPath, ".proi");
   if (!dotProI.exists()) dotProI.mkdirs();
   return ldb;
  }


  public File getTransitoryLDBLocation(String user, String ws) {
   String sep = Names.PATH_SEPARATOR;
   String repository = getEnvRoot().getName();
   String userspaces = Properties.get(Names.USER_SPACE);
   if (null==userspaces || "".equals(userspaces))
     userspaces = Properties.get(Names.DATA_DIR) + sep + "space" + sep + "user";
   String ldbPath = userspaces + sep + user + sep + "workspace" + sep + repository + sep + "transitory";
   ldbPath += sep + ws;
   File ldb = new File(ldbPath);
   File dotProI = new File(ldbPath, ".proi");
   if (!dotProI.exists()) dotProI.mkdirs();
   return ldb;
  }
 
  public Authentication getDefaultAuthentication() {
	  Authentication auth = new Authentication();
	  auth.setUsername(defaultUsername);
	  auth.setPassword(defaultPassword);
	  return auth;      
  }

  private Authentication lookupID(Authentication id) {
    if (null!=id) return id;
    return getDefaultAuthentication();
  }
  public int getTimeout() { return timeout; }
  public void setTimeout(int duration) { timeout=duration; }

  public String getTrashFolder() { return trashFolder; }
  public void setTrashFolder(String s) { trashFolder =s; }
  
  private File envRoot = null;
  private String alias = null;
  private String licensePoolName = null;
  private int maxLicenses=1;
  private String tnsNamesOraPath=null;
  private int timeout = 600000;
  private String trashFolder = "Root Folder/Trash";
  
  private String defaultReleaseScheme=null;
  private Map releaseSchemes = new HashMap();
  private Map userAttributes = new HashMap();
  private Enumeration revisions = new Enumeration();
  //private IntralinkFolder rootFolder=null;
  private Map users = new HashMap();
  
  private String dateFormat = "M/d/y H:m:s";
  private String DEFAULT_DATE = "8/8/1888 08:08:08";
  private String releaseLevelConfiguration=null;
  private String revisionConfiguration=null;
  private Map releaseLevelSequence = new HashMap();
  private Map revisionSequence = new HashMap();
  private String defaultUsername="INTRALINK";
  private String defaultPassword="INTRALINK";
  public String ldbPath = null;
  private String quarantineFolder=null;
  private String binPath=null;
  private String illibPath=null;
  private String exeToolkitEnv=null; 
  private boolean cleanExportDir = true;
  private Collection userDefinedAttributes = new Vector();
  private Collection lifeCycleAttributes = new Vector();
  private Map systemAttributes = null;
  private Map startParts = new HashMap();
  
  //Some system attributes
  public static String NAME = "name";
  public static String BRANCH = "branch";
  public static String REVISION = "rev";
  public static String VERSION = "ver";
  public static String CREATED_ON = "created-on";
  public static String CREATED_BY = "created-by";
  public static String RELEASE_LEVEL = "release-level";
  public static String FOLDER = "folder";
  public static String GENERIC = "is-generic";
  public static String INSTANCE = "is-instance";
  public static String DESCRIPTION = "description";
  public static String LOCKED = "locked";
  public static String RELEASE_SCHEMA = "release-schema";
  public static String SCOPE = "scope";
  public static String STATUS_DESCRIPTION = "status-description";
  public static String RULE = "Rule";
  public static String ROLE = "Role";
  public static String RELEASE_SCHEME = "release-scheme";
  public static String RELEASE_PROCEDURE = "release-procedure";
  public static String QUANTITY = "quantity";
  public static String PROMOTE_TO = "promote-to";
  public static String DEMOTE_TO = "demote-to";
  public static String OBJECT = "Object";
  public static String MEMBER_TYPE = "Member-Type";
  public static String DEPENDENCIES_ARE_COMPLETE = "Dependencies-Complete";
  public static String BRANCH_DOMAIN = "Branch-Domain";
  public static String ATTENTION = "Attention";
  public static String RELATIONSHIP_TYPES = "Relationship-Types";
  public static String ROW_NUMBER = "Row-Number";
  public static String SAME_FILE = "Same-File";
  public static String TYPE_NAME = "Type-Name";

  public String getDefaultStampTextConvention() {
    return Properties.get(Names.PDF_STAMP_CONVENTION); 
  }

  private static String defaultNamingConventionBaseName = "mmeta{zws-datasource-name} | '_' | {basename} | '_' | meta{rev} | '_' | meta{ver} | '_' | {type} | ";
  
  //2-D Drawing formats
  public String getDefaultPSNamingConvention() { 
    String s = Properties.get(Names.PS_NAMING_CONVENTION); 
    if (null==s || "".equals(s.trim())) s = defaultNamingConventionBaseName + "'.ps'";
    return s;
  }

  public String getDefaultPDFNamingConvention() { 
    String s = Properties.get(Names.PDF_NAMING_CONVENTION); 
    if (null==s || "".equals(s.trim())) s = defaultNamingConventionBaseName+ "'.pdf'";
    return s;
  }
  public String getDefaultHPGNamingConvention() { 
    String s = Properties.get(Names.HPGL_NAMING_CONVENTION); 
    if (null==s || "".equals(s.trim())) s = defaultNamingConventionBaseName + "'.hpg'";
    return s;
  }
  public String getDefaultCGMNamingConvention() { 
    String s = Properties.get(Names.CGM_NAMING_CONVENTION); 
    if (null==s || "".equals(s.trim())) s = defaultNamingConventionBaseName + "'.cgm'";
    return s;
  }
  
  public String getDefaultDXFNamingConvention() { 
    String s = Properties.get(Names.DXF_NAMING_CONVENTION);
    if (null==s || "".equals(s.trim())) s = defaultNamingConventionBaseName + "'.dxf'";
    return s;
  }
  
  public String getDefaultDWGNamingConvention() { 
    String s = Properties.get(Names.DWG_NAMING_CONVENTION);
    if (null==s || "".equals(s.trim())) s = defaultNamingConventionBaseName + "'.dwg'";
    return s;
  }

  //3-D Modeling formats
  public String getDefaultIGSNamingConvention() { 
    String s = Properties.get(Names.IGS_NAMING_CONVENTION); 
    if (null==s || "".equals(s.trim())) s = defaultNamingConventionBaseName + "'.igs'";
    return s;
  }
  public String getDefaultSTPNamingConvention() { 
    String s = Properties.get(Names.STP_NAMING_CONVENTION); 
    if (null==s || "".equals(s.trim())) s = defaultNamingConventionBaseName + "'.stp'";
    return s;
  }
  public String getDefaultNEUNamingConvention() { 
    String s = Properties.get(Names.NEU_NAMING_CONVENTION); 
    if (null==s || "".equals(s.trim())) s = defaultNamingConventionBaseName + "'.neu'";
    return s;
  }
  
  public String getDefaultIDFNamingConvention() {
    String s = Properties.get(Names.IDF_NAMING_CONVENTION);
    if (null==s || "".equals(s.trim())) s = defaultNamingConventionBaseName + "'.idf'";
    return s;
  }

  public boolean getActivateEventWatcher() { return activateEventWatcher; }
  public void setActivateEventWatcher(boolean b) { activateEventWatcher=b; }

  public boolean getDeleteOldPDFImage() { return deleteOldPDFImage; }
  public void setDeleteOldPDFImage(boolean b) { deleteOldPDFImage=b; }

  //Image Snapshot Configurations
  boolean deleteOldPDFImage = true;
  private int minutesOffset=0;
  private boolean activateEventWatcher=true;
  
  //default configurations
  public static String AS_STORED_CONFIGURATION = "as-stored";
  public static String LATEST_CONFIGURATION = "latest";

  public static String NO_DEPENDENCIES = "none";
  public static String ALL_DEPENDENCIES = "all";
  public static String ILINK_ALL_DEPENDENCIES = "ilink-all";
  public static String REQUIRED_DEPENDENCIES = "required";
  public static String ILINK_REQUIRED_DEPENDENCIES = "ilink-required";

  private static String delim = Origin.delim;
}




/*
protected synchronized Metadata Snapshot(Origin o, String imageNamingConvention, String stampNamingConvention, boolean deleteOldImage, String outputType, Authentication ignoreid) throws Exception {
Authentication id = null;
String workspace = "image" + nextCount();
Metadata source = find(o, id);

/-*--------------------------------------------------
Metadata image = findSynchronizedImage(source);
if (null!=image) return image;
destroyWorkspace(workspace, id);
createWorkspace(workspace, id);
checkout(o,workspace, id);
Collection images = snapshot(...);
Iterator i = images.iterator();
File imageFile;
while (i.hasNext()) {
  imageFile = (File)i.next();
  if (delete oldImage) deleteOldImage
  if (stamp) stamp
  import
  if (folderdefined) source.setfolder;
  setattributes
}
checkin
i = images.iterator()l
while (i.hasNext()) {
  idata = find in workspace();
  record(source, idata)
}
destroy workspace
*--/-----------------------------------------

String imageName = MetadataUtil.parseNamingGrammar(source, imageNamingConvention);  
String viewable = objectName(imageName);

Collection synkRecords = SynchronizationSvc.findAllSynchronizationRecords(o);
if (null!=synkRecords) {
  Iterator i = synkRecords.iterator();
  Origin synch;
  while (i.hasNext()){
    synch = (Origin)i.next();
    if (viewable.equals(objectName(synch.getName()))) {
      {}//Logwriter.printOnConsole(viewable + " already synchronized:");
      {}//Logwriter.printOnConsole(o);
      {}//Logwriter.printOnConsole(synch);
      return find(synch,id);
    }
  }
}
destroyWorkspace(workspace, id);
createWorkspace(workspace, id);
checkout(o,workspace, id);
String sourceType = FileNameUtil.getFileNameExtension(o.getName()).toLowerCase();
String fileType = FileNameUtil.getFileNameExtension(viewable).toLowerCase();
File imageLocation = getDataPath("images");
//+++ check source part type to be drawing (or appropiate)
if ("pdf".equalsIgnoreCase(fileType)) {
  if ("drw".equalsIgnoreCase(sourceType)) DocumentSvc.convertDRW2PDF(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
  else if ("dwg".equalsIgnoreCase(sourceType)) convertDWG2PDF(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
  if (null!=stampNamingConvention && !stampNamingConvention.trim().equals("")) {
    String stampText = MetadataUtil.parseNamingGrammar(source, stampNamingConvention);
    File pdf = new File(imageLocation, viewable);
    DocumentSvc.stampPDF(pdf, stampText);
  }
}
else if ("hpg".equalsIgnoreCase(fileType)) DocumentSvc.convertDRW2HPGL(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
else if ("cgm".equalsIgnoreCase(fileType)) DocumentSvc.convertDRW2CGM(o, workspace, imageLocation.getAbsolutePath(), viewable, id);
//+++ add other source part types
File imagePath = getDataPathToFile("images", viewable );
if (!imagePath.exists()) throw new Exception ("Failed To Generate " + viewable + " from workspace " + workspace);
if (deleteOldImage) {
  try {
    Metadata ignore = findLatest(viewable, id); //may throw NameNotFound
    deleteFromRepository(viewable, id);
    SynchronizationSvc.purgeByName(getDomainName(), getServerName(), getName(), viewable);
  }
  catch(NameNotFound ignore) { }
  catch(Exception e) { e.printStackTrace(); }
}

importToWorkspace(workspace, imagePath.getAbsolutePath(), id);
String snapshotFolder = Properties.get(Names.SNAPSHOT_FOLDER);
if (null!=snapshotFolder && !"".equals(snapshotFolder.trim()))
  source.set("folder", snapshotFolder);
setAttributes(workspace, viewable , source.getAttributes(), id);

checkin(workspace, id);
Metadata image = findInWorkspace(workspace, viewable , false, id);
if (!"unchanged".equalsIgnoreCase(image.get("workspace-state"))) throw new Exception ("Failed To checkin " + workspace);
destroyWorkspace(workspace, id);
SynchronizationSvc.record(source.getOrigin(), image.getOrigin());
return image;
}

*/