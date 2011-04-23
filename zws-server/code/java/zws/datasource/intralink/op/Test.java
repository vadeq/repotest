package zws.datasource.intralink.op;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 29, 2004, 5:01 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.intralink.IntralinkSource;
import zws.datasource.intralink.op.workspace.SetAttributeList;

import java.util.HashMap;
import java.util.Map;

public class Test {
  public Test() { }
  public static void main(String[] args) {
    IntralinkSource source = new IntralinkSource();
    source.setDefaultUsername("admin");
    source.setDefaultPassword("admin");
    SetAttributeList op = new SetAttributeList();
    //op.setDatasource(source);
    Map atts = new HashMap();
    atts.put("Revision","D");
    atts.put("Release-Level","In Progress");
    op.setWorkspaceName("myspace");
    op.setAttributes("aaa.pdf", atts);
    op.setAttributes("bbb.pdf", atts);
//    op.blast();
  }
}
