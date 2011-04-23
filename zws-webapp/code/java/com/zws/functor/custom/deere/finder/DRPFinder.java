package com.zws.functor.custom.deere.finder;

import com.zws.application.Config;
import com.zws.functor.custom.deere.DRPExport;
import com.zws.functor.custom.deere.search.DRPDatabaseAgent;
import com.zws.functor.finder.Finder;

import java.io.*;

public class DRPFinder extends Finder {

  public synchronized void find() throws Exception {
    File f = new File(getOutputFileName());
    drpExport();
  }
  public InputStream openStream() throws Exception {
    File f = new File(getOutputFileName());
    setStream(new FileInputStream(f));
    return getStream();
  }
  public int getDataSize() {
    File f = new File(getOutputFileName());
    return (int)f.length();
  }
  public String getOutputFileName() {
    return getOutputDirectoryName() + Config.FILE_SEPARATOR + getBinary();
  }
  private String getOutputDirectoryName() {
    return Config.getProperty(Config.DIR_DOWNLOAD);
  }
  private void drpExport() throws Exception {
    setBinary(getBinary()+".tif");
    File outDir = new File(getOutputDirectoryName());
    outDir.mkdirs();
    DRPExport f = new DRPExport();
    f.setImageName(getImageName());
    f.setOutputFileName(getOutputFileName());
    f.execute();
  }
  public String getImageName() { return imageName; }
  public void setImageName(String s) { imageName=s; }
  public DRPDatabaseAgent getDataSource() { return dataSource; }
  public void setDataSource (DRPDatabaseAgent d) { dataSource = d; }
  private DRPDatabaseAgent dataSource = null;  //this should be changed to a data source stead of an agent (agent should be split)

  public String imageName=null;
}
