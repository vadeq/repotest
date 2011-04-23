/**
 *
 */
package zws.repository.teamcenter.op;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Aug 31, 2007 3:28:35 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import java.util.Iterator;

import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.source.RepositoryBinarySource;
import zws.repository.teamcenter.TC10RepositoryBase;
import zws.util.RemotePackage;

public class TC10FetchNativeFile extends TC10OpBase {

  public void execute() throws Exception {
    //zws.util.Logwriter.printOnConsole(this,"execute",getOrigin());
    TC10RepositoryBase rep = getRepository();
    RepositoryBinarySource source = rep.materializeBinarySource();
    RemotePackage f = source.fetchNativeFile(new QxContext(), getOrigin(), getAuthentication());        
    store(f);
    //{} //System.out.println(m);
    //{} //System.out.println("Downloader: "+f.getAbsolutePath());
  }

  public Object getResult() {    
    RemotePackage  f = null;
    String resultStr = "";
    
    if(getResults().size()>0)
    {
      Iterator i = getResults().iterator();
      if(i.hasNext()) f = (RemotePackage)i.next();
      resultStr = f.toString();
    }    
    return resultStr; 
  }
  
  public Origin getOrigin() { return origin; }
  public void setOrigin(Origin o) { origin=o; }
  
  private Origin origin = null;
}
