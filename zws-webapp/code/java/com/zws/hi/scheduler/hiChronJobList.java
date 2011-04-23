package com.zws.hi.scheduler;
import com.zws.functor.processor.ChronJob;
import com.zws.hi.hiList;
import com.zws.service.chron.ChronService;

import java.util.*;

public class hiChronJobList extends hiList {

  public void initialize() {}
  public void bind() throws Exception {
    //com.zws.service.Database.bind(servlet); //temporary hack
    setItems(adapt(ChronService.findAll()));
  }
  private Collection adapt(Collection jobs) {
    Collection c = new Vector();
    Iterator i = jobs.iterator();
    while (i.hasNext()) c.add(adapt((ChronJob)i.next()));
    return c;
  }
  private hiChronJob adapt(ChronJob job) { return new hiChronJob(job); }
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

/*
  public String processNow() {
    Timer timer = new Timer();
    ChronJob job=null;
    try { job=ChronService.find(getID()); }
    catch (Exception e) {
      e.printStackTrace();
      logFormError(keyRUNTIME_ERROR);
      return ctrlSYSTEM_ERROR;
    }
    if (null==job.getProcess()) {
      logFormError(keyRUNTIME_ERROR);
      return ctrlSYSTEM_ERROR;
    }
    TimerTask task = job.getProcess().getTask();
    timer.schedule(task, 50);
    return ctrlLIST;
  }
 **/
}
