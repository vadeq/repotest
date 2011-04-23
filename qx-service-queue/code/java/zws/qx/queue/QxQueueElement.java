package zws.qx.queue;

import zws.qx.QxContext;
import zws.qx.xml.QxXML;
import java.io.Serializable;

public class QxQueueElement implements Comparable, Serializable {
  
  public QxQueueElement() { }
  public QxQueueElement(long id, int p, QxContext qxCtx, QxXML xmlDataInstr) {
    recordID = id;
    priority = p;
    qxContext = qxCtx;
    xmlDataInstruction = xmlDataInstr;
  }
  
  public QxQueueElement(int p, QxContext qxCtx, QxXML xmlDataInstr) {
    priority = p;
    qxContext = qxCtx;
    xmlDataInstruction = xmlDataInstr;
  }
  
  public int compareTo(Object obj) {
    QxQueueElement e = (QxQueueElement)obj;
    if (e.getPriority()> priority) { 
      return -1;
    } else if (e.getPriority()< priority) {  
      return 1;
    }
    return 1;
  }

  public QxContext getQxContext() {
    return qxContext;
  }

  public void setQxContext(QxContext c) {
    qxContext = c;
  }

  public QxXML getXmlDataInstruction() {
    return xmlDataInstruction;
  }

  public void setXmlDataInstruction(QxXML x) {
    xmlDataInstruction = x;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int p) {
    priority = p;
  }

  public long getRecordID() {
    return recordID;
  }

  public void setRecordID(long l) {
    recordID = l;
  }

  public void setSummary(String s) { 
    qxContext.set(QxContext.SUMMARY, s); 
  }
  
  public String getSummary() {
    String s = qxContext.get(QxContext.SUMMARY);
    if (null == s || "".equals(s.trim())) s = "[no summary]";
    return s;
  }
  
  private static final long serialVersionUID = 1L;
  private QxContext qxContext = null;
  private QxXML xmlDataInstruction = null;
  private long recordID = 0;
  private int priority = -1;
}
