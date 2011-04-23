/**
 *
 */
package zws.repository.teamcenter.op;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Aug 31, 2007 3:28:35 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.teamcenter.TC10RepositoryBase;

public class TC10Finder extends TC10OpBase {

  public void execute() throws Exception {
    //zws.util.Logwriter.printOnConsole(this,"execute",getOrigin());
    TC10RepositoryBase rep = getRepository();
    RepositoryMetadataSource source = rep.materializeMetadataSource();
    Metadata m = source.find(new QxContext(), getOrigin(), getAuthentication());
    store(m);
    {} //System.out.println(m);
  }

  public Origin getOrigin() { return origin; }
  public void setOrigin(Origin o) { origin=o; }
  private Origin origin = null;
}
