/**
 *
 */
package zws.repository.teamcenter.op;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Aug 31, 2007 3:28:35 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import java.io.File;
import java.util.Iterator;

import zws.origin.Origin;
import zws.qx.program.QxInstruction;
import zws.repository.source.RepositoryBinarySource;
import zws.repository.teamcenter.TC10Constants;
import zws.repository.teamcenter.TC10RepositoryBase;

public class TC10Downloader extends TC10OpBase {

  public void execute() throws Exception {
    //zws.util.Logwriter.printOnConsole(this,"execute",getOrigin());
    TC10RepositoryBase rep = getRepository();
    RepositoryBinarySource source = rep.materializeBinarySource();
    /*File f = source.download(new QxContext(), getOrigin(), getToDir(), getAuthentication());        
    store(f);*/
    //{} //System.out.println(m);
    //{} //System.out.println("Downloader: "+f.getAbsolutePath());
  }

  public Object getResult() {    
    File f = null;
    String resultStr = "";
    
    if(getResults().size()>0)
    {
      QxInstruction download = new QxInstruction(TC10Constants.TAG_FILE);
      Iterator i = getResults().iterator();
      if(i.hasNext()) f = (File)i.next();
      download.set(TC10Constants.ATT_ABS_PATH, f.getAbsolutePath());
      resultStr = download.toString();
    }    
    return resultStr; 
  }
  
  public Origin getOrigin() { return origin; }
  public void setOrigin(Origin o) { origin=o; }
  public String getToDir() { return toDir; }
  public void setToDir(String d) { toDir=d; }
  
  private Origin origin = null;
  private String toDir = "";
}
