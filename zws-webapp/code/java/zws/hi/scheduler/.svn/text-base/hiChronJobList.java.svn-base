package zws.hi.scheduler;
import zws.ProcessorClient;
import zws.application.Names;
import zws.application.Properties;
import zws.processor.Processor;
import zws.util.AdapterPrototype;
import zws.util.AdapterVector;

import com.zws.functor.processor.ChronJob;
import com.zws.hi.hiList;
import com.zws.service.chron.ChronService;

import java.util.Iterator;

public class hiChronJobList extends hiList {

  public void initialize() {}

  
  public void bind() throws Exception {
    AdapterPrototype adapter = new hiChronJob();
    setItems(new AdapterVector(adapter));
    getItems().addAll(ChronService.getService().findAll());
  }

  public boolean idChoosesItem(String id, Object job) { return id.equals(((hiChronJob)job).getJobName()); }

  public String processNow() {
      String serverName = Properties.get(Names.CENTRAL_SERVER);
    try {
      choose();
      hiChronJob j = (hiChronJob)getChosenItem();
      Processor p = ProcessorClient.getProcessor(serverName, j.getProcessName());
      ProcessorClient.launch( serverName, p);
      return ctrlOK;
    }
    catch (Exception e) {
      e.printStackTrace();
      return ctrlERROR;
    }
  }

  public boolean isBound(){ return (null!=getItems()); }

  public void render() {
    Iterator i = getItems().iterator();
    while (i.hasNext()) ((hiChronJob)i.next()).render();
  }

  public String toggleJob() {
    ChronJob job = ChronService.findScheduledJob(getID());
    if (null==job) {
      try { ChronService.scheduleJob(getID());}
      catch(Exception e) { e.printStackTrace(); return ctrlSYSTEM_ERROR; }
    }
    else ChronService.cancelScheduledJob(getID());
    return ctrlLIST;
  }


  public String delete() {
    try {
      ChronService.delete(getID());
      logFormStatus("msg.item.deleted", getID());
      return ctrlLIST;
    }
    catch (Exception e) {
      logFormError("system.err.event", "delete", e.getMessage(), "");
      return ctrlSYSTEM_ERROR;
    }
  }
}
