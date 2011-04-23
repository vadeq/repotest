package zws.qx.queue;

import zws.application.Names;
import zws.qx.QxContext;
import zws.qx.queue.persistence.QxQueueFactory;
import zws.qx.queue.persistence.QxQueueSvc;
import zws.qx.queue.QxQueueElement;
import zws.queue.QxElementRecordBase;
import zws.queue.QxQueueRecordBase;
import zws.qx.xml.QxXML;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.SortedSet;

public class PersistentQueue {
  
  public PersistentQueue(String qName) throws Exception {
    QxQueueRecordBase qRecord = QxQueueFactory.getQueue(qName);
    if(null == qRecord) {
      QxQueueFactory.createQueue(qName);
      qRecord = QxQueueFactory.getQueue(qName);
    }
    queueName = qName;
    queueState = qRecord.getQ_state();
  }

  public synchronized void startQueue() throws Exception {
    QxQueueFactory.startQueue(queueName);
  }

  public synchronized void stopQueue() throws Exception {
    QxQueueFactory.stopQueue(queueName);
  }

  public synchronized void enqueue(int priority, Object o, String summary, String annotations) throws Exception{
    QxQueueElement queueElement = (QxQueueElement) o;
    QxQueueSvc.enQueue(queueNamespace, getQueueName(), priority, 
                       queueElement.getQxContext().toString(), 
                       queueElement.getXmlDataInstruction().toString(),
                       summary, annotations);
  }
  
  public synchronized void enqueue(int priority, Object o) throws Exception{
    QxQueueElement queueElement = (QxQueueElement) o;
    QxQueueSvc.enQueue(queueNamespace, getQueueName(), priority, 
                       queueElement.getQxContext().toString(), 
                       queueElement.getXmlDataInstruction().toString());
  }

  public synchronized Object dequeueToActive()  throws Exception{
    QxElementRecordBase qRecord = null;
    qRecord  = QxQueueSvc.deQueue(queueNamespace, getQueueName());
    if(null == qRecord) return null;
    QxQueueElement qElement = new QxQueueElement(qRecord.getId(), qRecord.getPriority(), 
                                                new QxContext(qRecord.getContext()), 
                                                new QxXML(qRecord.getInstruction())); 
    return qElement;
  }
  
  public synchronized void requeue(long id) throws Exception{
    QxQueueSvc.reQueue(id);
  }
  
  public synchronized void deleteActive(QxQueueElement element) throws Exception{
    QxQueueSvc.archive(element.getRecordID());
  }

  public synchronized void purgeAllActiveItems() throws Exception{
    QxElementRecordBase   qRecord = null;
    SortedSet set = QxQueueSvc.getAllActiveElements(queueNamespace, getQueueName());
    if(null == set || set.size()<1) return ;
    Iterator itr = set.iterator();
    while(itr.hasNext()) {
      qRecord = (QxElementRecordBase) itr.next();
      QxQueueSvc.archive(qRecord.getId());
    }
  }
  
  public synchronized void purgeAllItems() throws Exception{
    QxElementRecordBase   qRecord = null;
    SortedSet set = QxQueueSvc.getAllActiveElements(queueNamespace, getQueueName());
    set.addAll(QxQueueSvc.getAllPendingElements(queueNamespace, getQueueName()));
    if(null == set || set.size()<1) return ;
    Iterator itr = set.iterator();
    while(itr.hasNext()) {
      qRecord = (QxElementRecordBase) itr.next();
      QxQueueSvc.archive(qRecord.getId());
    }
  }
  
  public synchronized boolean isEmpty() throws Exception{
    boolean empty = true;
    SortedSet set = QxQueueSvc.getPendingElements(queueNamespace, getQueueName(),1);
    if(null != set && set.size() > 0) { empty = false; } 
    return empty;
  }
  
  public boolean hasPendingInstructions() throws Exception{
    boolean result = false;
    SortedSet set = QxQueueSvc.getPendingElements(queueNamespace, getQueueName(),1);
    if(null != set && set.size() > 0) { result = true; }
    return result;
  }
  
  public boolean hasActiveInstructions() throws Exception{
    boolean result = false;
    SortedSet set = QxQueueSvc.getActiveElements(queueNamespace, getQueueName(),1);
    if(null != set && set.size() > 0) { result = true; }
    return result;
  }

  public Collection getPendingInstructions() throws Exception{
    return QxQueueSvc.getAllPendingElements(queueNamespace, getQueueName());
  }
  
  public Collection getPendingInstructions(int count) throws Exception{
    return QxQueueSvc.getPendingElements(queueNamespace, getQueueName(), count);
  }
  
  public Collection getActiveInstructions() throws Exception{
    return QxQueueSvc.getAllActiveElements(queueNamespace, getQueueName());
  }
  
  public synchronized void notifyActiveItems() throws Exception{
    try {
      QxElementRecordBase   qRecord = null;
      String subject = "Reprocessing interuppted items!";
      StringBuffer message = new StringBuffer();
      SortedSet set = QxQueueSvc.getAllActiveElements(queueNamespace, getQueueName());
      if(null == set || set.size() < 1) return ;
      Iterator itr = set.iterator();
      while(itr.hasNext()) {
        qRecord = (QxElementRecordBase) itr.next();
        message.append("ID ").append(qRecord.getId()).append(": ");
        QxXML xmlInstruction = new QxXML(qRecord.getInstruction());
        String instruction = xmlInstruction.toQxProgram().getName();
        message.append(instruction).append(" " );
        message.append(qRecord.getSummary());
        message.append(Names.NEW_LINE).append(Names.NEW_LINE);
      }
      zws.Alert.notify(subject, message.toString());
    } catch(Exception ex){
      ex.printStackTrace();
    }    
  }
  
  
  public QxQueueElement[] getAllQueueElements() throws Exception{
    
    ArrayList items = new ArrayList();
    SortedSet activeSet = QxQueueSvc.getAllActiveElements(queueNamespace, getQueueName());
    SortedSet pendingSet = QxQueueSvc.getAllPendingElements(queueNamespace, getQueueName());
    
    if( null != activeSet && activeSet.size()>0) {
      items.addAll(getQxQueueElements(activeSet));
    }
    if( null != pendingSet && pendingSet.size()>0) {
      items.addAll(getQxQueueElements(pendingSet));
    }
        
    QxQueueElement[] elements = new QxQueueElement[items.size()];
    items.toArray(elements);
    return elements;
  }
  
  public QxQueueElement[] getQueueElements(int count) throws Exception{
    
    ArrayList items = new ArrayList();
    ArrayList restrictedItems = new ArrayList();
    SortedSet activeSet = QxQueueSvc.getActiveElements(queueNamespace, getQueueName(),count);
    SortedSet pendingSet = QxQueueSvc.getPendingElements(queueNamespace, getQueueName(),count);
    
    if( null != activeSet && activeSet.size()>0) {
      items.addAll(getQxQueueElements(activeSet));
    }
    if( null != pendingSet && pendingSet.size()>0) {
      items.addAll(getQxQueueElements(pendingSet));
    }
    if(items.size()> count) {
      for(int i=0;i<count;i++) {
        restrictedItems.add(items.get(i));
      }      
    } else {
      restrictedItems = items;
    }
        
    QxQueueElement[] elements = new QxQueueElement[restrictedItems.size()];
    restrictedItems.toArray(elements);
    return elements;
  }
 
  private Collection getQxQueueElements(SortedSet set) {
    Collection qElements = new ArrayList();
    if(null == set || set.size()<1) return qElements;
    QxQueueElement qElement = null;
    QxElementRecordBase qRecord = null;
    Iterator itr = set.iterator();
    while(itr.hasNext()) {
      qRecord = (QxElementRecordBase) itr.next();
      qElement = new QxQueueElement(qRecord.getId(), qRecord.getPriority(), new QxContext(qRecord.getContext()), new QxXML(qRecord.getInstruction()));
      qElements.add(qElement);
    }
    return qElements;
  }
  
  public synchronized String getQueueState() {
    return queueState;
  }

  public synchronized void setQueueState(String s) {
    queueState = s;
    }
  
  public synchronized void loadPersistantState() throws Exception {
    this.queueState = QxQueueFactory.getQueueState(queueName);
  }

  public String getQueueName() {
    return queueName;
  }

  public void setQueueName(String s) {
    queueName = s;
  }
    
  private static String queueNamespace ="QUEUE";
  private String queueName =null;
  private String queueState =null;
}

