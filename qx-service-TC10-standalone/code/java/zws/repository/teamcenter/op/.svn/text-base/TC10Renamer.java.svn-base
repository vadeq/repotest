/**
 *
 */
package zws.repository.teamcenter.op;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Aug 31, 2007 3:28:35 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.target.RepositoryMetadataTarget;
import zws.repository.teamcenter.TC10RepositoryBase;

public class TC10Renamer extends TC10OpBase {

  public void execute() throws Exception {
    //zws.util.Logwriter.printOnConsole(this,"execute",getOrigin());
    TC10RepositoryBase rep = getRepository();
    RepositoryMetadataTarget target = rep.materializeMetadataTarget();
    Origin o = target.rename(new QxContext(), getOrigin(), getNewName(), getAuthentication());
    store(o);
    {} //System.out.println(o);
  }

  public Origin getOrigin() { return origin; }
  public void setOrigin(Origin o) { origin=o; }
  
  public String getNewName() { return newName; }
  public void setNewName(String n) { newName=n; }
  
  private Origin origin = null;
  private String newName = null;
  
}
