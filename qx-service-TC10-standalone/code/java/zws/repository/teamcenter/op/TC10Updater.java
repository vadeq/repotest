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

import zws.data.Metadata;
import zws.qx.QxContext;
import zws.repository.target.RepositoryMetadataTarget;
import zws.repository.teamcenter.TC10RepositoryBase;

public class TC10Updater extends TC10OpBase {

  public void execute() throws Exception {
    //zws.util.Logwriter.printOnConsole(this,"execute",getMetadata());
    TC10RepositoryBase rep = getRepository();
    RepositoryMetadataTarget target = rep.materializeMetadataTarget();
    Metadata m = target.update(new QxContext(), getMetadata(), getFile(), getAuthentication());
    store(m);
    {} //System.out.println(m);
  }

  public Metadata getMetadata() { return data; }
  public void setMetadata(Metadata m) { data=m; }
  
  public File getFile() { return file; }
  public void setFile(File f) { file=f; }
  
  private Metadata data = null;
  private File file = null;
}
