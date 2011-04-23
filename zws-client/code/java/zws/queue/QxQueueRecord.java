/* file name  : zws\recorder\ExecutionRecord.java
 * authors    : Sulakshan Shetty
 * created    : 02/13/2006 10:36:38
 * copyright  : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 *
 * modifications:
 *
 */
package zws.queue;


import java.io.Serializable;

/** 
 * ExecutionRecord interface. 
 * @author Sulakshan Shetty
 */
public interface QxQueueRecord extends Serializable {
  
  public String getQ_name();
  public void setQ_name(String s);
  public String getQ_state();
  public void setQ_state(String s);
  public String getQ_notes();
  public void setQ_notes(String s);    
  }
