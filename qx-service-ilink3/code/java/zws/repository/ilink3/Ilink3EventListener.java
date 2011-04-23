package zws.repository.ilink3; 

import zws.application.s;
import zws.op.PollingThreadOpBase;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.recorder.ExecutionRecord;
import zws.repository.ilink3.qx.client.op.xml.IntralinkResultHandler;
import zws.repository.ilink3.qx.client.op.xml.ListEventsHandler;
import zws.repository.ilink3.qx.program.IlinkQxProgram;
import zws.repository.ilink3.qx.program.ListEvents;
import zws.repository.ilink3.qx.program.OpenRepository;
import zws.repository.ilink3.qx.program.QxProgram;
import zws.security.Authentication;
import zws.security.CryptoUtil;
import zws.service.event.EventQueuePlugin;
import zws.service.recorder.qx.RecorderClient;
import zws.service.recorder.qx.RecorderService;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
//import zws.util.{}//Logwriter;
import zws.util.Named;
import zws.util.RoutedEventBase;
import zws.util.Storable;
import zws.util.StorableBase;
import zws.util.TimeUtil;
import zws.util.comparator.AlphaNumericComparator;

import java.io.StringReader;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;



public class Ilink3EventListener extends PollingThreadOpBase implements Named {

  public Ilink3EventListener () {  }

  public String getTimeOfLastEvent() {
    String timeOfLastEvent=null;
    ExecutionRecord lastEventRecord = lastEventRecord();
    if (null!=lastEventRecord ) {
      {}//Logwriter.printOnConsole(getEventNamespace() + " looking for new events");
      lastRecordedTime = lastEventRecord.getDuration().getStartTime().asCalendar();
     } else if (null==lastRecordedTime) {
      {}//Logwriter.printOnConsole(getEventNamespace() + ": no new events.");
      lastRecordedTime = new GregorianCalendar();
      lastRecordedTime.add(Calendar.MINUTE, getServerMinutesOffset());
    }
    timeOfLastEvent = TimeUtil.asString(lastRecordedTime);
    return timeOfLastEvent;
  }

  public void executePolling() throws Exception {
    Collection events = null;
    String pollAfter = getTimeOfLastEvent();
    {}//Logwriter.printOnConsole(  "polling at: " + TimeUtil.asString(Calendar.getInstance()));    
    {}//Logwriter.printOnConsole(  "polling for events since: " + pollAfter);
    try { events = listEvents(pollAfter); }
    catch (Exception e) { e.printStackTrace(); }
    if (null==events || events.isEmpty()) {
      {}//Logwriter.printOnConsole("none after: " + pollAfter);
      return;
    }
    Iterator i = events.iterator();
    RoutedEventBase ev;
    while (i.hasNext()) {
      ev = (RoutedEventBase) i.next();
      fire(ev);
    }
  }

  private void fire(RoutedEventBase event) throws Exception {
    if (!firingEvents) {
      {}//Logwriter.printOnConsole("Not Firing Any Events!");
      return;
    }
    if (getTargetQueueNodes().isEmpty()) {
      {}//Logwriter.printOnConsole("No Target handler nodes specified!");
      return;
    }
    Iterator i = getTargetQueueNodes().iterator();
    String node;
    RecorderClient r = RecorderClient.getClient();
    r.recordFiredEvent(event);
    while(i.hasNext()) {
      node = (String)i.next();
     // HandlingClient.getClient(node).fire(ev);
      // put the event in the event-queue
      Authentication id = new Authentication(getSystemUsername(),getSystemPassword());
      EventQueuePlugin eventQ = new EventQueuePlugin();
      eventQ.fire(id, event);
    }
  }

  public int getHistoryLogDuration() { return logHistoryDuration; }
  public void setHistoryLogDuration(int hours) { logHistoryDuration=hours; }
  
  private ExecutionRecord lastEventRecord() {
    ExecutionRecord record=null;
    RecorderService r = RecorderClient.getClient();
    try { 
      record = r.getLastRecording(getEventNamespace());
    }
    catch (Exception ignore) { ignore.printStackTrace(); }
    return record;
  }

  public Collection getTargetQueueNodes() {
    if (null==targetQueueNodes) {
      targetQueueNodes = new TreeSet(new AlphaNumericComparator());
      targetQueueNodes.add("DesignState-0"); //tmprary
    }
    return targetQueueNodes;
  }

  public synchronized void addTargetQueueNode(String serverNode) {
    getTargetQueueNodes().add(serverNode);
    notify();
  }

  public synchronized void removeTargetQueueNode(String serverNode) {
    Iterator i = getTargetQueueNodes().iterator();
    String node;
    while(i.hasNext()) {
      node = (String)i.next();
      if (serverNode.equals(node)) i.remove();
    }
    if ( getTargetQueueNodes().isEmpty()) stop(); //autostop thread
  }

  public void ignoreEvents() { firingEvents=false; }
  public void fireEvents() { firingEvents=true; }
  public String getEventFiringState() {
    if (firingEvents) return FIRING_EVENTS;
    else return IGNORING_EVENTS;
  }

  public String getRunningState() {
    if (getTargetQueueNodes().isEmpty()) return NO_TARGET;
    return super.getRunningState();
  }

  public String getEventNamespace() {
    if (null==ilinkEventNamespace) ilinkEventNamespace = "event" + s.dot + getDomainName() + s.dot + getServerName()+ s.dot + getRepositoryName();
    return ilinkEventNamespace;
  }
  
  private void purgeHistoryLog() {
    //+++todo
  }
  public Collection getHistoryLog() { return null; }
  private Collection listEvents(String firedAfter) throws Exception {
    Collection events = null;
    QxInstruction qx          = null;
    QxInstruction ilinkQx     = null;
    QxInstruction openRep     = null;
    QxInstruction listEvents  = null;
    qx          = new QxProgram();
    ilinkQx     = new IlinkQxProgram();
    openRep     = new OpenRepository(getSystemUsername(), getSystemPassword());
    listEvents = new ListEvents(firedAfter);
    qx.add(ilinkQx);
    ilinkQx.add(openRep);
    openRep.add(listEvents);
    QxXML instruction = new QxXML(qx.toString());
    QxXML response = Ilink3QxInvoker.executeQx(lookupRepository().getEnvRoot(), "ListEvents", instruction, true);
    Collection c = handleResponse(response);
    return c;
  }
  
  protected Collection handleResponse(QxXML response) throws Exception {
    if (null==response) {
      {}//Logwriter.printOnConsole("No Output Results.");
      return null;
    }
    IntralinkResultHandler handler = new ListEventsHandler();
    if (null==handler) {
      {}//Logwriter.printOnConsole("No Result Handler Defined.");
      return null;
    }
    XMLReader xr = getParser(false).getXMLReader();
    Storable storage = new StorableBase();
    storage .throwOnFailure(false);
    handler.setStorable(storage);
    handler.setRepository(lookupRepository());
    xr.setContentHandler(handler);
    xr.setErrorHandler(handler);

    xr.parse(new InputSource(new StringReader(response.toString())));
    if (handler.hasError()) handler.throwError();
    Collection c = handler.getResults();
    return c;
   }  
  
  protected static SAXParser getParser(boolean validate) throws SAXException, ParserConfigurationException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(validate);
    return factory.newSAXParser();
  }
  
  
  private Ilink3RepositoryBase lookupRepository() {
    if (null==repository) {
      RepositoryService r = RepositoryClient.getClient();
      repository = (Ilink3RepositoryBase)r.findRepository(getRepositoryName());
    }
    return repository;
  }
  public boolean supportsDeepCopy() {
    return false;
  }
  
  public int getServerMinutesOffset() { return minutesOffset; }
  public void setServerMinutesOffset(int i) { minutesOffset=i; }

  public String getDomainName() { return lookupRepository().getDomainName(); }
  public String getServerName() { return lookupRepository().getServerName(); }
  
  public String getSystemUsername() { return systemUsername; }
  public void setSystemUsername(String s) { systemUsername = s; }
  
  public void setSystemPassword(String s) { systemPassword = s; }  
  public String getSystemPassword() { return systemPassword; }
  
  /*public String getEncryptedSystemPassword() {
  return encryptedSystemPassword;
}*/

public void setEncryptedSystemPassword(String s) throws Exception{
  encryptedSystemPassword = s;
  try {
    CryptoUtil cryptoUtil = new CryptoUtil();
    systemPassword = cryptoUtil.decrypt(encryptedSystemPassword);
  } catch (Exception e) {
    System.out.println("Not able to decrypt the password " + s);
    throw e;
  } 
}
      
  public String getRepositoryName() { return repositoryName; }
  public void setRepositoryName(String s) { repositoryName=s; }
  
  public boolean isLoadOnStartup() {return loadOnStartup;}
  public void setLoadOnStartup(boolean b) {loadOnStartup = b;}
  
  public String getName() { return getRepositoryName(); }
  
  

  private String systemUsername=null;
  private String systemPassword=null;
  private String encryptedSystemPassword=null;
  private String repositoryName=null;
  private boolean loadOnStartup = false;
  private Ilink3RepositoryBase repository=null;
  
  private int logHistoryDuration=24; //hours
  private Collection targetQueueNodes = null;
  private boolean firingEvents = true;

  private static String FIRING_EVENTS = "firing";
  private static String IGNORING_EVENTS = "ignored";
  private static String NO_TARGET = "no target";
  
  private Calendar lastRecordedTime=null;
  private String ilinkEventNamespace= null;
  
  private int minutesOffset=0;
  
  





}
