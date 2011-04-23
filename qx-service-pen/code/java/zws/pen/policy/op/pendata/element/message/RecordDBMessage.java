package zws.pen.policy.op.pendata.element.message;
/*
* DesignState - Design Compression Technology
* @author: arbind
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import zws.recorder.util.RecorderUtil;
import zws.pen.policy.op.pendata.util.RecordMessageOpBase;

public class RecordDBMessage extends RecordMessageOpBase  {

  protected void recordMessage(String s) throws Exception{
    RecorderUtil.logActivity(getQxCtx(), activity, description, s);    
  }
  public String getActivity() {return activity;}
  public void setActivity(String s) { activity = s;}
  public String getDescription() {return description;}
  public void setDescription(String s) { description = s;}  
  String activity = null;  
  String description = null;
 }
