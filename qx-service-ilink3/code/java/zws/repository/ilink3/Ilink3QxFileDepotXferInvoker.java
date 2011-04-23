/*
 DesignState - Design Compression Technology
 @author: Emmanuel Ankutse
 @version: 1.0
 Created on Nov 8
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.repository.ilink3;

import zws.Server;
import zws.application.Names;
import zws.application.Properties;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.repository.ilink3.qx.program.Tags;
import zws.service.file.depot.FileDepotClient;
import zws.util.Counter;
import zws.util.DeleteFile;
//import zws.util.{}//Logwriter;
import zws.util.RemotePackage;

import java.io.File;

public class Ilink3QxFileDepotXferInvoker {
  
  public static QxXML executeQx(QxXML instruction) throws Exception {
    QxInstruction store = instruction.toQxProgram();
    if (!"store".equalsIgnoreCase(store.getName())) {
      return new QxXML("<exception message='Unsupported instruction' instruction='"+store.getName()+"'");
    }
    
    String componentName = store.get(Tags.COMPONENT_NAME);
    String inDirPath = store.get(Tags.INPUT_PATH);
    String dependencies = store.get(Tags.DEPENDENCIES);
    
    if (null==dependencies) dependencies="all";  //semantics?
    if (null==componentName) 
      throw new Exception("\""+Tags.COMPONENT_NAME+"\"" + " attribute of store instruction is null!");    
    if (null==inDirPath) 
      throw new Exception("\""+Tags.INPUT_PATH+"\"" + " attribute of store instruction is null!");

    File localFileDir = new File(inDirPath);
    {}//Logwriter.printOnConsole("storing " + localFileDir.getPath()+File.separator+componentName + " to File Depot");    
    
    String fileDepotDir = Properties.get(Names.TEMP_DIR) + Names.PATH_SEPARATOR + "ilink-export-file-depot" + Counter.nextCount(); 

    FileDepotClient c = FileDepotClient.materializeClient(fileDepotDir);
    RemotePackage rp = c.storeDirectory(localFileDir, componentName);
    
    if (!Server.debugMode()) {
    File workingDir = new File(localFileDir.getPath());      
    //todo: queue directory for deletion and return immediately
    DeleteFile deleter = new DeleteFile();
    deleter.setDeleteIfNotEmpty(true);
    deleter.setFile(workingDir);
    deleter.execute();
    if (Boolean.FALSE == (Boolean)deleter.getResult()) {} {}//Logwriter.printOnConsole("Could not delete working dir: " + workingDir.getAbsolutePath());
    }    
    return new QxXML(rp.toString());
  }
}