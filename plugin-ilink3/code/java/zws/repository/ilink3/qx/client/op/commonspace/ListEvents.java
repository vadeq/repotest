package zws.repository.ilink3.qx.client.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.repository.ilink3.qx.client.op.xml.IntralinkResultHandler;
import zws.repository.ilink3.qx.client.op.xml.ListEventsHandler;
import zws.util.TimeUtil;

import java.util.Calendar;

public class ListEvents extends IntralinkRepositoryOp {
  public IntralinkResultHandler getXMLResultHandler() { return new ListEventsHandler(); }
  
  protected void createOpInstructionXML(){
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
