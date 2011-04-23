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
public interface QxElementRecord extends Serializable {
  
  /*public boolean enQueue(String qName, int priority, String ctx, String instruction);
  public boolean enQueue(String qName, int priority, String ctx, String instruction, String summary);
  
  public QxElement deQueue(String qName);
  public QxElement archive(long qID);*/
  
  public String getName();
  public void setName(String name);

  public String getState();
  public void setState(String state);

  public long getId();
  public void setId(long id);

  public String getNamespace();
  public void setNamespace(String s);

  public int getPriority();
  public void setPriority(int s);

  public int getQorder();
  public void setQorder(int s);

  public String getContext();
  public void setContext(String s);

  public String getInstruction();
  public void setInstruction(String s);

  public String getSummary();
  public void setSummary(String s);


  public String getNotes();
  public void setNotes(String s);
  
  }
