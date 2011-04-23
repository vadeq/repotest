package zws.datasource.filesystem.op; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.data.MetadataBase;
import zws.datasource.filesystem.xml.AutoCADMetadataHandler;
import zws.datasource.filesystem.xml.FileSystemResultHandler;
import zws.util.*;

import java.io.File;

public class DWGMetadataExtractor extends FileSystemOpBase {
  protected String getEXEName() { return "dwgMetadataExtractor.bat"; }
	 protected String getConnectorPath() { return Properties.get(Names.ENV_DIR) + Names.PATH_SEPARATOR +"autocad" + Names.PATH_SEPARATOR + "bin" + Names.PATH_SEPARATOR + "connector"; }

  public FileSystemResultHandler getXMLResultHandler() { 
    AutoCADMetadataHandler handler = new AutoCADMetadataHandler(); 
    handler.setMetadata(metadata);
    return handler;
  }
  protected String getOutputFileName(File f) { return f.getAbsolutePath()+Names.PATH_SEPARATOR+targetBaseName+".xml"; }
  public void setMetadata(MetadataBase data) { metadata = data; }
  public MetadataBase getMetadata() { return metadata; }
  public void setTargetAutoCADFile(String path) { targetAutoCADFile = new File(path); }
  public void setTargetAutoCADFile(File f) { targetAutoCADFile = f; }
  public File getTargetAutoCADFile() { return targetAutoCADFile; }
  
  protected void setArguments(ExecShell shell) {
    shell.addCommandLineArgument(workingDir.getAbsolutePath() + Names.PATH_SEPARATOR + targetAutoCADFile.getName(), false); 
  }    

  protected void initExecution() throws Exception {
    File f = new File(workingDir, targetAutoCADFile.getName());
    FileUtil.copy(targetAutoCADFile, f);
    targetBaseName = FileNameUtil.getBaseName(targetAutoCADFile.getName());
  }
  
  protected void finishExecution(){
    File f = new File(workingDir, targetAutoCADFile.getName());
    if (f.exists()) f.delete();
  }

  private File targetAutoCADFile=null;
  private String targetBaseName=null;
  private MetadataBase metadata = null;
}
