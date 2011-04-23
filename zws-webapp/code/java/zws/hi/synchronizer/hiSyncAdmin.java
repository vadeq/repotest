/* DesignState - Design Compression Technology
 * @author: Rodney
 * @version: 1.0
 * Created on Apr 23, 2008 7:53:47 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.hi.synchronizer;

import com.zws.hi.Interactor;

import java.util.*;
import zws.service.synchronization.qx.SynchronizationClient;
import zws.synchronization.SynchronizationRecord;
import java.util.ArrayList;

public class hiSyncAdmin extends Interactor {

  // without initialization this will skew the webpage!
  private Collection origins = new ArrayList();
  private String search = "";
  private String criteria = "";
  
  public String search() {
    
    if (search == null) return "index";
    
    try {      
      SynchronizationClient client = SynchronizationClient.getClient();
      origins = client.searchSynchronizationRecords(search);
      criteria = search;
    } catch (Exception e) {
      e.printStackTrace();
    }    
    return "index";      
  }

  public String delete() {

    try {
      SynchronizationRecord record;
      SynchronizationClient client = SynchronizationClient.getClient();
      origins = client.searchSynchronizationRecords(criteria);      
      Iterator iterator = origins.iterator();
      while (iterator.hasNext()) {
        record = (SynchronizationRecord) iterator.next();
        client.remove(record);
      }
      criteria = search;
      search();
      
    } catch (Exception e) {
      e.printStackTrace();
    }    
    return "index";      
  }
  
  public Collection getListing()        { return origins; }
  public String getCriteria()           { return criteria; }
  public String getSearch()             { return search; }  
  public void setSearch(String value)   { search = value.trim(); }
}
