package zws.handler.replication; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.RoutedData;
import zws.handler.HandlerBase;
import zws.handler.HandlerRegistry;
import zws.util.RoutedEventBase;

import java.util.*;

abstract public class EchoVerificationHandler extends HandlerBase {
  public EchoVerificationHandler(long handlerID, Class evClass, RoutedData route, Map verifyEventFields, Collection verifyMetadata) {
    hID = handlerID;
    eventClass = evClass;
    routing = route;
    eventFieldVerifiers = verifyEventFields;
    metadataVerifiers= verifyMetadata;
  }

  public void initRegistration(HandlerRegistry r) {
    setRegistry(r);
    //notify Echo Event action being invoked
    //start timeout thread    
  }

  public void closeRegistration() { }

  public long getHandlerID() { return hID; }
  //public void sethandlerID(long l) { handlerID=l; }

  public Class getEventClass() { return eventClass; }
  //public void setEventClass(Class c) { eventClass=c; }

  public RoutedData getRouting() { return routing; }
  //public void setRouting(RoutedData d) { routing=d; }

  //public void verifyEventFieldValues(Map m) { fieldValues =m; }
  //public void verifyMetadataList(Collection metadataList) { names=metadataList; }
  
  public boolean handles(RoutedEventBase event) { 
    if(!event.getClass().equals(getEventClass())) return false;
    if (!event.getDomainName().equals(getRouting().getDomainName())) return false;
    if (!event.getServerName().equals(getRouting().getServerName())) return false;
    if (!event.getDatasourceName().equals(getRouting().getDatasourceName())) return false;
    return true;
  }

  public void handle(RoutedEventBase event) {
    //verify()
    //if verifiedok { 
      //notify event action verified successfully
      //Update handlerID Status
      //unregister self
      //stop timeout thread
    //}
  }

  private long hID=-1;

  private Class eventClass=null;
  private RoutedData routing=null;
  private String targetName=null;
  private Collection metadataVerifiers=null;
  private Map eventFieldVerifiers = null;

  private long msTimeOut=1000*60*60; // 1 hour
}
