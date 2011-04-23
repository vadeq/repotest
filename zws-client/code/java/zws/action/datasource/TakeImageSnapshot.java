package zws.action.datasource;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on March 1, 2005, 4:58 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.DatasourceAccess;
import zws.action.ActionBase;
import zws.origin.Origin;

public class TakeImageSnapshot extends ActionBase {
  public void execute() throws Exception {
    Origin o = grabOrigin();
    DatasourceAccess access = DatasourceAccess.getDatasourceAccess(o);
    access.snapshotImage(o, imageType, imageRepository, null);    
  }

  public String getImageType() { return imageType; }
  public void setImageType(String s) { imageType=s; }
  public String getImageRepository() { return imageRepository; }
  public void setImageRepository(String s) { imageRepository = s; }
  
  private String imageType="pdf";
  private String imageRepository=null;
}
