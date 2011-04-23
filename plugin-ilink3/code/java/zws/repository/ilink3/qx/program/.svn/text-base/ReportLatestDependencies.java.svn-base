package zws.repository.ilink3.qx.program; /*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 11, 2004, 11:41 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.IntralinkOrigin;
import zws.origin.Origin;
import zws.qx.program.QxInstruction;
import zws.repository.ilink3.Ilink3Constants;
//impoer zws.util.Logwriter;

public class ReportLatestDependencies extends QxInstruction {
  public ReportLatestDependencies(Origin origin) {
    IntralinkOrigin ilinkOrigin  = (IntralinkOrigin) origin;
    setName("report-dependencies");
    set("name", ilinkOrigin.getName());
    set("branch", ilinkOrigin.getBranch());
    set("revision", ilinkOrigin.getRevision());
    set("version", String.valueOf(ilinkOrigin.getVersion()));
    set("configuration", Ilink3Constants.LATEST);
    set("associations", getAssociations());
    {}//Logwriter.printOnConsole("get dependencies" + ilinkOrigin.toString());
  }

  public String getAssociations() {return associations; }
  public void setAssociations(String s) {associations=s; }

  private String associations = Ilink3Constants.DEPENDENCIES_ALL;
}
