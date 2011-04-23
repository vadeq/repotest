/**
 *
 */
package zws.hi.report;

import zws.data.Metadata;
import zws.origin.Origin;
import zws.service.synchronization.qx.SynchronizationClient;
import zws.service.synchronization.qx.SynchronizationService;


import java.util.Collection;

/*
 DesignState - Design Compression Technology
 @author: D Reddy
 @version: 1.0
 Created on Jul 12, 2007 11:04:30 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

/**
 * @author D Reddy
 *
 */
public class PublishMetadataAdapter extends MetadataAdapter {
  private boolean publishState;
  //private static String PUBLISH_STATUS= Names.METADATA_PUBLISH_STATUS;
  public void adapt(Metadata data){
    metadata=data;
    String value;
    values = new String[fields.length];
    name=data.getName();
    origin=data.getOrigin();
    for(int i = 0; i<=fields.length-1; i++) {
      {} //System.out.println("field...."+fields[i]+"\n");
      if(null != fields[i]) {
      value = data.get(fields[i]);
      if (null==value) value="";
      values[i]=value;
      }
      {} //System.out.println("value....."+values[i]+"\n");
    }
    assignPublishStatus(metadata);
    initSynchronizationStatus();
  }


  public void assignPublishStatus(Metadata meta)
  {
    boolean b = true;
    try {
      SynchronizationService S = SynchronizationClient.getClient();
      Collection c = S.findAllSynchronizationOrigins(meta.getOrigin());
      if (c == null || c.isEmpty()) {
        b = false;
      }
      setPublishState(b);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  /**
   * @return the publishState
   */
  public boolean isPublishState() {
    return publishState;
  }

  private void initSynchronizationStatus() {
    if (publishState) {
      synchronizationStatus=SYNCHRONIZED;
      return;
    }
    try {
      SynchronizationService sync = SynchronizationClient.getClient();
      Origin lastPublish = sync.lastSynchronization(origin.getDomainName(), origin.getServerName(), origin.getDatasourceName(), origin.getName());
      if (null==lastPublish) synchronizationStatus = NOT_SYNCHRONIZED;
      else synchronizationStatus = PREVIOUSLY_SYNCHRONIZED;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * @param publishState the publishState to set
   */
  public void setPublishState(boolean publishState) {
    this.publishState = publishState;
  }

  public String getSynchronizationStatus() {
    return synchronizationStatus;
  }

  public boolean isSynchronized() {
    return SYNCHRONIZED.equalsIgnoreCase(synchronizationStatus);
  }

  private String synchronizationStatus = NOT_SYNCHRONIZED;

  public static String SYNCHRONIZED = "synchronized";
  public static String NOT_SYNCHRONIZED = "not-synchronized";
  public static String PREVIOUSLY_SYNCHRONIZED = "previously-synchronized";
}
