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

import zws.data.Metadata;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.source.RepositoryStructureSource;

import zws.repository.teamcenter.TC10RepositoryBase;

public class TC10BOMReporter extends TC10OpBase {

  public void execute() throws Exception {
    //zws.util.Logwriter.printOnConsole(this,"execute",getOrigin());
    TC10RepositoryBase rep = getRepository();
    RepositoryStructureSource source = rep.materializeStructureSource();
    Metadata m = source.reportBOM(new QxContext(), getOrigin(),  getAuthentication());
    store(m);
    //{} //System.out.println(m);
  }

  public Object getResult() {
    
    Metadata m = null;
    
    if(getResults().size()>0)
    {
      //QxInstruction bom = new QxInstruction(TC10Constants.TAG_FILE);
      Iterator i = getResults().iterator();
      if(i.hasNext()) m = (Metadata)i.next();
      //download.set(TC10Constants.ATT_ABS_PATH, f.getAbsolutePath());
      //resultStr = download.toString();
    }    
    return m; 
  }  
  
  public Origin getOrigin() { return origin; }
  public void setOrigin(Origin o) { origin=o; }
  public String getConfiguration() { return configuration; }
  public void setConfiguration(String c) { configuration=c; }

  private Origin origin = null;
  private String configuration = null;
}
