package zws.datasource.filesystem.xml; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.MetadataBase;
import zws.datasource.filesystem.FileSystemSource;
import zws.util.Storable;

import java.util.Collection;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class FileSystemResultHandler extends DefaultHandler {
  public Storable getStorable(){ return storable; }
  public void setStorable(Storable s) { storable=s; }
  public FileSystemSource getDatasource() { return datasource; }
  public void setDatasource(FileSystemSource s) { datasource=s; }
  public Collection getResults(){ return storable.getResults(); }
  
  protected void loadAttributes(MetadataBase metadata, Attributes atts) {
    String key, value;
    for (int idx = 0; idx < atts.getLength(); idx++) {
      if (atts.getQName(idx).equalsIgnoreCase("name")) continue;
      metadata.set(atts.getQName(idx), atts.getValue(idx)); 
    }    
  }
  
  private Storable storable=null;
  private FileSystemSource datasource=null;
}
