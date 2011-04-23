package zws.repository.ilink3;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.exception.Empty;
import zws.exception.UnsupportedOperation;

import zws.origin.IntralinkOrigin;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.repository.ilink3.qx.client.op.xml.Ilink3QxFileDepotXferInvokerResultHandler;
import zws.repository.ilink3.qx.client.op.xml.NamesHandler;
import zws.repository.ilink3.qx.client.op.xml.SearchResultHandler;
import zws.repository.ilink3.qx.program.Checkout;
import zws.repository.ilink3.qx.program.CheckoutLatest;
import zws.repository.ilink3.qx.program.CreateWorkspace;
import zws.repository.ilink3.qx.program.Export;
import zws.repository.ilink3.qx.program.ExportList;
import zws.repository.ilink3.qx.program.IlinkQxProgram;
import zws.repository.ilink3.qx.program.IntralinkPoet;
import zws.repository.ilink3.qx.program.ListAllInWorkspace;
import zws.repository.ilink3.qx.program.ListNamesInWorkspace;
import zws.repository.ilink3.qx.program.OpenRepository;
import zws.repository.ilink3.qx.program.OpenSandbox;
import zws.repository.ilink3.qx.program.CreateSandbox;
import zws.repository.ilink3.qx.program.OpenWorkspace;
import zws.repository.ilink3.qx.program.QxProgram;
import zws.repository.ilink3.qx.program.RemoveWorkspace;
import zws.repository.ilink3.qx.program.Store;
import zws.repository.source.RepositoryBinarySource;
import zws.security.Authentication;
import zws.service.file.depot.FileDepotClient;
import zws.util.FileUtil;
//import zws.util.{}//Logwriter;
import zws.util.RemotePackage;
import zws.application.Properties;
import zws.application.Names;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;


/**
 * The Class Ilink3RepositoryBinarySource.
 */
public class Ilink3RepositoryBinarySource extends Ilink3RepositoryBase implements RepositoryBinarySource {

  /**
   * The Constructor.
   * @param parent the parent
   */
  protected Ilink3RepositoryBinarySource(QxContext parent) {
    configureParentContext(parent);
    }


  public RemotePackage fetchNativeFile(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    RemotePackage rf = null;
    String tempDirPath = Properties.get(Names.TEMP_DIR)  + Names.PATH_SEPARATOR + "ilink3-native-"+nextCount();
    File tempDir= new File(tempDirPath );
    FileUtil.delete(tempDir);

    String downloadPath = tempDirPath + Names.PATH_SEPARATOR + "download";
    File downloadDir= new File(downloadPath );

    String fileDepotDir = tempDirPath + Names.PATH_SEPARATOR + "file-depot";

    File source = downloadLatest(runningCtx, origin, downloadPath, id);
    //+++downloadLatest should store files to the file depot, return a remote package

    FileDepotClient c = FileDepotClient.materializeClient(fileDepotDir);
    rf = c.storeDirectory(downloadDir, source.getName());
    FileUtil.delete(tempDir);
    return rf;
  }

 public RemotePackage fetchDesignFiles(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
   RemotePackage rf = null;
   String tempDirPath = Properties.get(Names.TEMP_DIR)  + Names.PATH_SEPARATOR + "ilink3-native-"+nextCount();
   File tempDir= new File(tempDirPath );
   FileUtil.delete(tempDir);

   String downloadPath = tempDirPath + Names.PATH_SEPARATOR + "download";
   File downloadDir= new File(downloadPath );

   String fileDepotDir = tempDirPath + Names.PATH_SEPARATOR + "file-depot";

   //File source = downloadDesignFiles(runningCtx, origin, downloadPath, id);
   downloadDesignFiles(runningCtx, origin, downloadPath, id);
   //+++download should store files to the file depot, return a remote package
   FileDepotClient c = FileDepotClient.materializeClient(fileDepotDir);
   rf = c.storeDirectory(downloadDir, origin.getName());
   FileUtil.delete(tempDir);
   return rf;
  }

  private File downloadLatest(QxContext runningCtx, Origin origin, String location, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole("Downloading " + origin.getName());
    {}//Logwriter.printOnConsole("Using ilink environment: " + getEnvRoot());
    QxInstruction checkoutLatest = null;
    checkoutLatest = new CheckoutLatest(origin.getName());
    File f = downloadFile(runningCtx, origin, location, checkoutLatest, id);
    return f;
  }

  private File downloadDesignFiles(QxContext runningCtx, Origin origin, String location, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole("Downloading " + origin.getName());
    {}//Logwriter.printOnConsole("Using ilink environment: " + getEnvRoot());
    Checkout c = null;
    IntralinkOrigin o = (IntralinkOrigin) origin;
    c = new Checkout();
    c.setComponentName(o.getName());
    c.setBranch(o.getBranch());
    c.setRevision(o.getRevision());
    c.setVersion(""+o.getVersion());
    c.setDependencies(Ilink3Constants.DEPENDENCIES_ALL);
    File f = downloadFile(runningCtx, origin, location, c, id);
    return f;
  }


  /** download file.
   * @param runningCtx QxContext
   * @param origin origin
   * @param location toDir
   * @param id Authentication
   * @return File file
   * @throws Exception exception
   */
  private File downloadFile(QxContext runningCtx, Origin origin, String location, QxInstruction checkoutOp, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole("Downloading " + origin.getName());
    {}//Logwriter.printOnConsole("Using ilink environment: " + getEnvRoot());
    QxInstruction qx = null;
    QxInstruction ilinkQx = null;
    QxInstruction openRep = null;
    QxInstruction createSandbox = null;   
    QxInstruction openSandbox = null;
    QxInstruction createWorkspace = null;
    QxInstruction removeWorkspace = null;
    QxInstruction openWorkspace = null;
    QxInstruction listNamesInWorkspace = null;
    IntralinkPoet ilinkPoet = null;

    //checkout
    String workspaceParent = origin.getName();
    File ld_path = new File(getLDBPathBase()+ Names.PATH_SEPARATOR + workspaceParent);
    new File (ld_path,".proi").mkdirs();    
    
    long unique = nextCount(); 
    String workspace = "ws-"+unique;;

    qx = new QxProgram();
    ilinkQx = new IlinkQxProgram();
    openRep = new OpenRepository(id.getUsername(), id.getPassword());
    createSandbox = new CreateSandbox(ld_path.getPath());    
    openSandbox = new OpenSandbox(ld_path.getPath());
    createWorkspace = new CreateWorkspace(workspace);
    openWorkspace = new OpenWorkspace(workspace);
    ilinkPoet = new IntralinkPoet();
    ilinkPoet.setRepository(this);
    
    qx.add(ilinkQx);
    ilinkQx.add(openRep);
    openRep.add(createSandbox);
    openRep.add(openSandbox);
    openSandbox.add(createWorkspace);
    openSandbox.add(openWorkspace);
    openWorkspace.add(checkoutOp);
    ilinkPoet.setProgram(qx);    
    ilinkPoet.execute();

    //report names in Workspace
    qx = new QxProgram();
    ilinkQx = new IlinkQxProgram();
    openRep = new OpenRepository(id.getUsername(), id.getPassword());
    openSandbox = new OpenSandbox(ld_path.getPath());
    openWorkspace = new OpenWorkspace(workspace);
    listNamesInWorkspace = new ListNamesInWorkspace(false);
    
    ilinkPoet = new IntralinkPoet();
    ilinkPoet.setRepository(this);
    qx.add(ilinkQx);
    ilinkQx.add(openRep);
    openRep.add(openSandbox);
    openSandbox.add(openWorkspace);
    openWorkspace.add(listNamesInWorkspace);
    ilinkPoet.setProgram(qx);
    ilinkPoet.setXMLResultHandler(new NamesHandler());
    ilinkPoet.execute();
    Collection workspaceList = ilinkPoet.getResults();
    
    //Get list of workspace items
    
    if (null==workspaceList || workspaceList.isEmpty()) throw new Empty("Workspace ["+workspace+"] is empty");
    //Export all items that are in workspace - 
    //This will correctly export top level generics - even when nested family table instance is being "exported"
    ExportList exportList = new ExportList();
    exportList.setWorkspace(workspace);
    exportList.setWorkspaceParent(workspaceParent);
    exportList.setOutputPath(location);
    exportList.setDependencies(Ilink3Constants.EXPORT_DEPENDENCIES_REQUIRED);
    Iterator i = workspaceList.iterator();
    Metadata m;
    while ( i.hasNext() ) { 
      m = (Metadata)i.next();
      exportList.addComponent(m.getName());
    }
    ilinkPoet.setOpType("export-list");
    ilinkPoet.setProgram(exportList);
    ilinkPoet.execute();      
    
    //Archive or Delete workspace
    if ("true".equalsIgnoreCase(Properties.get(Names.DEBUG_MODE))) {
      File ld_archive = new File(getLDBPathBase(), "archive");
      if (!ld_archive.exists()) ld_archive.mkdirs();
      String suffix = new Long(System.currentTimeMillis()).toString();
      suffix = workspaceParent+"-"+suffix;
      File dest = new File (ld_archive, suffix);
      ld_path.renameTo(dest);
    }
    else {    
      qx = new QxProgram();
      ilinkQx = new IlinkQxProgram();
      openRep = new OpenRepository(id.getUsername(), id.getPassword());
      openSandbox = new OpenSandbox(ld_path.getPath());
      removeWorkspace = new RemoveWorkspace(workspace);
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
  
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      openRep.add(openSandbox);
      openSandbox.add(removeWorkspace);
      ilinkPoet.setProgram(qx);
      ilinkPoet.execute();  
      
      FileUtil.delete(ld_path);
    }
    
    return null;
  }

  private RemotePackage store(QxContext runningCtx, Origin origin, String location, Authentication id) throws Exception {
    //upload the exported data to the file depot
    IntralinkPoet ilinkPoet = new IntralinkPoet();
    ilinkPoet.setRepository(this);
    ilinkPoet.setXMLResultHandler(new Ilink3QxFileDepotXferInvokerResultHandler());

    Store store = new Store();
    store.setComponentName(origin.getName());
    store.setInputPath(location);
    ilinkPoet.setOpType("store");
    ilinkPoet.setProgram(store);
    ilinkPoet.execute();

    Collection c = ilinkPoet.getResults();
    if (null == c || c.isEmpty()) {
      {}//Logwriter.printOnConsole("No results found in Findlatest.");
      //throw new Exception("No results found in Findlatest");
    }
    RemotePackage rf = (RemotePackage) c.iterator().next();
    {}//Logwriter.printOnConsole("Result data in Ilink3RepositoryBinarySource");
    return rf;
  }
  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryBinarySource#fetchDesignFilesLocally(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public Collection fetchDesignFilesLocally(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    throw new UnsupportedOperation("fetchDesignFilesLocally(QxContext runningCtx, Origin origin, Authentication id)");
  }

  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryBinarySource#fetchNativeFileLocally(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public File fetchNativeFileLocally(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    throw new UnsupportedOperation("fetchNativeFileLocally(QxContext runningCtx, Origin origin, Authentication id)");
  }

  /**
   * Next count.
   *
   * @return the long
   */
  private static synchronized long nextCount() { return count++; }

  private static long count = 0;
}
