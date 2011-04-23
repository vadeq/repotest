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
import zws.qx.QxContext;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.teamcenter.TC10RepositoryBase;

public class TC10LatestFinder extends TC10OpBase {

  public void execute() throws Exception {
    //zws.util.Logwriter.printOnConsole(this,"execute",getItemId());
    TC10RepositoryBase rep = getRepository();
    RepositoryMetadataSource source = rep.materializeMetadataSource();
    Metadata m = source.findLatest(new QxContext(), getItemId(), getAuthentication());
    store(m);
    {} //System.out.println(m);
  }

  public String getItemId() { return itemId; }
  public void setItemId(String i) { itemId=i; }
  private String itemId = null;
}
