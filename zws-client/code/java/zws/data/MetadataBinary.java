package zws.data;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 3, 2004, 1:31 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;

import java.io.InputStream;
import java.net.URL;

public interface MetadataBinary  extends Metadata{
  public String getFilename();
  public String getBaseName();
  public String getExtention();
  public String getFileType();
  public MetadataBinary convert(String toType, String location);
  public InputStream download();
  public URL getURL();
  public boolean isDerived();
  public Origin getSource();  //if this file represents a derived binary
  public boolean isAvailable();
  public MetadataBinary generate();
}
