package zws.datasource.filesystem.xml; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.MetadataBase;

import org.xml.sax.Attributes;

public class AutoCADMetadataHandler extends FileSystemResultHandler {
  public void startElement (String uri, String name, String qName, Attributes atts) { 
    if ( qName.equalsIgnoreCase("metadata")) { loadAttributes(metadata, atts); return; }
  }
  public void setMetadata(MetadataBase data) { metadata = data; }
  public MetadataBase getMetadata() { return metadata; }
  private MetadataBase metadata=null;
}
