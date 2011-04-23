package zws.datasource.intralink.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.intralink.xml.ListEventsHandler;
import zws.datasource.intralink.xml.IntralinkResultHandler;
import zws.util.TimeUtil;

import java.io.*;
import java.util.*;

public class ListEvents extends IntralinkRepositoryOp {
  public IntralinkResultHandler getXMLResultHandler() { return new ListEventsHandler(); }
  protected void writeRepositoryInstruction() throws IOException {
    openTag("list-event-history");
    if (null!=firedAfter) writeParameter("fired-after", firedAfter);
    if (null!=firedBefore) writeParameter("fired-before", firedBefore);
    closeTag();
  }
  
  public String getFiredAfter() { return firedAfter; }
  public void setFiredAfter(String s) { firedAfter=s; }
  public void setFiredAfter(Calendar c) { firedAfter=TimeUtil.asString(c); }
  
  public String getFiredBefore() { return firedBefore; }
  public void setFiredBefore(String s) { firedBefore=s; }
  public void setFiredBefore(Calendar c) { firedBefore=TimeUtil.asString(c); }
  
  private String firedAfter=null;
  private String firedBefore=null;
}
