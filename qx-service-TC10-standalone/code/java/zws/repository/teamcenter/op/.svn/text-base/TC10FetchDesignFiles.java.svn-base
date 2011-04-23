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
import java.util.Collection;
import java.util.Iterator;

import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.source.RepositoryBinarySource;
import zws.repository.teamcenter.TC10RepositoryBase;

public class TC10FetchDesignFiles extends TC10OpBase {

  public void execute() throws Exception {
    //zws.util.Logwriter.printOnConsole(this,"execute",getOrigin());
    TC10RepositoryBase rep = getRepository();
    RepositoryBinarySource source = rep.materializeBinarySource();
    Collection f = source.fetchDesignFilesLocally(new QxContext(), getOrigin(), getAuthentication());
    File file = null;
    String fileName = null;
    Iterator itr = f.iterator();
    while(itr.hasNext()) {
       file = (File) itr.next();
       fileName = file.getAbsolutePath();
       store(fileName);
    }
  }

  public Object getResult() {
    String f = null;
    StringBuffer resultStr = new StringBuffer();;

    if(getResults().size()>0)
    {
      Iterator i = getResults().iterator();
      while(i.hasNext()) {
        f = (String)i.next();
        resultStr.append("<file path='").append(f).append("'/>");
      }
    }
    return resultStr.toString();
  }

  public Origin getOrigin() { return origin; }
  public void setOrigin(Origin o) { origin=o; }

  private Origin origin = null;
}
