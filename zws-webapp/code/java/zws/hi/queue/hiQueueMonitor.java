/* DesignState - Design Compression Technology
 * @author: Rodney
 * @version: 1.0
 * Created on Feb 27, 2008 1:10:43 PM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.hi.queue;

import com.zws.hi.Interactor;
import zws.application.Names;
import zws.application.Properties;
import java.util.ArrayList;
import java.util.List;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.queue.admin.QueueAdminPlugin;
import zws.qx.xml.QxXML;

public class hiQueueMonitor  extends Interactor {

    private ArrayList monitorInfo = new ArrayList();
    private String queue = "";
  
    public String purge() {     
      
      try {  
        QueueAdminPlugin adminQ = new QueueAdminPlugin();
        QxWebClient client = QxWebClient.materializeClient();
        QxContext context = materializeQxContext();
        client.clearKeys(context);
        QxXML xml = adminQ.purge(queue);
        synchronized(monitorInfo) { monitorInfo.clear(); }        
       
      } catch (Exception e) {
        e.printStackTrace();
      }      
      return "index";           
    }
    
    public void render() {      
      
      try {  
        QueueAdminPlugin adminQ = new QueueAdminPlugin();
        QxWebClient client = QxWebClient.materializeClient();
        QxContext context = materializeQxContext();
        client.clearKeys(context);
        QxXML xml = adminQ.list();
        
        MonitorPluginParser monitor = new MonitorPluginParser();
        monitor.parseQueues( xml.toString() );
        MonitoredQueue[] queues = monitor.getQueues();

        synchronized(monitorInfo) {
          monitorInfo.clear();
          for(int i=0; i<queues.length; i++) {
            monitorInfo.add(queues[i]);
          }
        }            
      } catch (Exception e) {
        e.printStackTrace();
      }      
      //return "index";      
    }
 
    public String cancel() {     

      try {
        QueueAdminPlugin adminQ = new QueueAdminPlugin();
        QxWebClient client = QxWebClient.materializeClient();
        QxContext context = materializeQxContext();
        client.clearKeys(context);
        QxXML xml = adminQ.cancel(queue);
        
      } catch (Exception e) {
        e.printStackTrace();
      }      
      return "index";      
    }
    
    public String start() {     

      try {
        QueueAdminPlugin adminQ = new QueueAdminPlugin();
        QxWebClient client = QxWebClient.materializeClient();
        QxContext context = materializeQxContext();
        client.clearKeys(context);
        QxXML xml = adminQ.start(queue);
        
      } catch (Exception e) {
        e.printStackTrace();
      }      
      return "index";      
    }
    
    public String stop() {     

      try {
        QueueAdminPlugin adminQ = new QueueAdminPlugin();
        QxWebClient client = QxWebClient.materializeClient();
        QxContext context = materializeQxContext();
        client.clearKeys(context);
        QxXML xml = adminQ.stop(queue);
        
      } catch (Exception e) {
        e.printStackTrace();
      }      
      return "index";      
    } 
    
    public void setQueue(String value)    { queue = value; }
    public List getMonitorInfo()          { return monitorInfo; }
    
    /**
     * Materialize qx context.
     * @return qxcontext
     */
    private QxContext materializeQxContext() {
      QxContext context = new QxContext();
      context.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.queue");
      context.set(QxContext.JAVA_SERVICE_CLASSNAME, "QxQueueService");
      context.set(QxContext.SOAP_HOSTNAME, Properties.get(Names.SERVICE_FINDER_HOSTNAME));
      context.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
      context.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
      return context;
    }
}
