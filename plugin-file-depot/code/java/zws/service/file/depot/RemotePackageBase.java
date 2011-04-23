/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Dec 11, 2007 10:58:12 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.service.file.depot;

import zws.util.RemotePackage;


import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import java.util.Vector;


public class RemotePackageBase implements RemotePackage {

  protected RemotePackageBase(String primaryName) { name = primaryName; }
  
  public String getName() { return name;   }
  public Collection getFileNames() { return fileNames;   }
  public void addFile(String fileName) {
    fileNames.add(fileName);
  }

  public String getLocation() { return location; }
  public void setLocation(String s) { location = s; }

  public URL getUrl() { return url; }
  public void setUrl(URL u) { url = u; }
  
  
  
  public String toString() { return toXML(); }

  public String toXML() {
    String xml=null;
    Map atts = new HashMap();
    atts.put(RemotePackage.URL, getUrl().toString());
    atts.put(RemotePackage.LOCATION, getLocation());
    atts.put(RemotePackage.PRIMARY_NAME, getName());
    if (null!=getFileNames() && !getFileNames().isEmpty()) {
      Object[] names = getFileNames().toArray();      
      String fileList=names[0].toString();

      for(int i=1; i< names.length; i++) {
        if (null != names[i])
          fileList += RemotePackage.DELIMITER + names[i].toString();
      }
      atts.put(RemotePackage.FILE_LIST, fileList);
    }
    try {
      xml = zws.util.StringUtil.unaryXMLTag(RemotePackage.TAG_NAME, atts);
    }
    catch(Exception e) {e.printStackTrace(); }
    return xml;
  }
  
  private String name=null;
  private URL url=null;
  private Collection fileNames=new Vector();
  private String location=null;
}
