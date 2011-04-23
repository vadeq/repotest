package com.zws.functor.finder;

import com.zws.application.Constants;
import com.zws.domo.document.Document;

import java.io.*;

public class FileFinder extends Finder {
  public void find() {
    if (null==getBinding()) return;
    Document doc = (Document) getBinding();
    if (null!=binaryMetadata) setBinary(doc.get(binaryMetadata));
    if (null!=displayNameMetadata) setDisplayName(doc.get(displayNameMetadata));
    if (null!=locationMetadata) setLocation(doc.get(locationMetadata));
  } //this file should already be on disk
  public InputStream openStream() throws Exception {
    setStream(new FileInputStream(getAbsoluteFileName()));
    return getStream();
  }
  public int getDataSize(){
    File f = new File(getAbsoluteFileName());
    return (int)f.length();
  }
  public String getAbsoluteFileName() { return getLocation()+Constants.FILE_SEPARATOR+getBinary(); }
  public String getLocationMetadata() { return locationMetadata; }
  public void setLocationMetadata(String s) { locationMetadata=s; }
  public String getBinaryMetadata() { return binaryMetadata; }
  public void setBinaryMetadata(String s) { binaryMetadata=s; }
  public String getDisplayNameMetadata() { return displayNameMetadata; }
  public void setDisplayNameMetadata(String s) { displayNameMetadata=s; }

  private String locationMetadata=null;
  private String binaryMetadata=null;
  private String displayNameMetadata=null;
}
