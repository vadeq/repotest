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

import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.target.RepositoryBinaryTarget;
import zws.repository.teamcenter.TC10RepositoryBase;

public class TC10BinaryUpdater extends TC10OpBase {

  public void execute() throws Exception {
    //zws.util.Logwriter.printOnConsole(this,"execute", getFile());
    TC10RepositoryBase rep = getRepository();
    RepositoryBinaryTarget target = rep.materializeBinaryTarget();
    Origin o = target.update(new QxContext(), getFile(), getAuthentication());
    store(o);
    {} //System.out.println(o);
  }

  public File getFile() { return file; }
  public void setFile(File f) { file=f; }
  private File file = null;
}
