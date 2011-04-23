package com.zws.functor.finder;

import com.zws.application.Config;
import com.zws.application.Properties;
import com.zws.datasource.IntralinkSource;
import com.zws.functor.intralink.*;
import com.zws.functor.util.file.ZipWriter;

import java.io.*;


public class IntralinkFinder extends Finder {

  public synchronized void find() throws Exception {
    File f = new File(getOutputFileName());
    clean();
    checkout();
    export();
    if (isMultipleFiles()) {
      setBinary(getBinary()+".zip");
      zip();
    }
    else copyToDownloadDir();
    clean();
  }
  public InputStream openStream() throws Exception {
    File f = new File(getOutputFileName());
    setStream(new FileInputStream(f));
    return getStream();
  }
  private boolean isMultipleFiles() {
    File f = new File(getExportPath());
    File[] files = f.listFiles();
    return files.length != 1;
  }
  protected void clean() throws Exception { //This should not be done in this functor
    File f = new File(getExportPath());
    if (f.exists()) {
      File[] files = f.listFiles();
      int idx = 0;
      while ( idx < files.length) files[idx++].delete();
    }
    f.delete();
    destroyWorkspace();
  }
  protected void destroyWorkspace() throws Exception {
    DestroyWorkspace wsDestroyer = new DestroyWorkspace();
    wsDestroyer.setWorkspaceName(getWorkspaceName());
    wsDestroyer.setUsername(getDataSource().getUsername());
    wsDestroyer.setPassword(getDataSource().getPassword());
    wsDestroyer.setEXEToolkitEnv(getDataSource().getEXEToolkitEnv());
    wsDestroyer.execute();
  }
  public int getDataSize() {
    File f = new File(getOutputFileName());
    return (int)f.length();
  }
  protected void checkout() throws Exception {
    Checkout f = new Checkout();
    f.setComponentName(getComponentName());
    f.setWorkspaceName(getWorkspaceName());
    f.setUsername(getDataSource().getUsername());
    f.setPassword(getDataSource().getPassword() );
    f.setEXEToolkitEnv(getDataSource().getEXEToolkitEnv());
    f.execute();
  }
  protected String getExportPath() {
    return Config.getProperty(Config.DIR_TEMP) + Config.FILE_SEPARATOR + getComponentName();
  }
  protected String getExportFileName() {
    return getExportPath() + Config.FILE_SEPARATOR + getComponentName();
  }
  protected String getOutputFileName() {
  	
    return Properties.get(Properties.downloadDirectory) + Config.FILE_SEPARATOR + getBinary();
  }
  protected void export() throws Exception {
    File outDir = new File(getExportPath());
    outDir.mkdirs();
    Export f = new Export();
    f.setComponentName(getComponentName());
    f.setOutputDirectory(getExportPath());
    f.setWorkspaceName(getWorkspaceName());
    f.setEXEToolkitEnv(getDataSource().getEXEToolkitEnv());
    f.execute();
  }
  protected void zip()throws Exception  {
    ZipWriter z = new ZipWriter();
    z.setSource(getExportPath());
    z.setZipFileName(getOutputFileName());
    z.execute();
  }
  protected void copyToDownloadDir()throws Exception  {
    File f = new File (getExportFileName());
    File download = new File(getOutputFileName());
    f.renameTo(download);
  }
  public String getComponentName() { return componentName; }
  public void setComponentName(String s) { componentName=s; }
  public String getWorkspaceName() { return workspaceName; }
  public void setWorkspaceName(String s) { workspaceName=s; }
  public IntralinkSource getDataSource() { return dataSource; }
  public void setDataSource (IntralinkSource d) { dataSource = d; }
  private IntralinkSource dataSource = null;
  public String componentName=null;
  public String workspaceName=null;
}
