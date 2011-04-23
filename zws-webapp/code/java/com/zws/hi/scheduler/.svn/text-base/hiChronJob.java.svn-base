package com.zws.hi.scheduler;

import com.zws.functor.processor.ChronJob;
import com.zws.hi.hiItem;
import com.zws.hi.util.hiTimes;
import com.zws.service.chron.ChronService;
import com.zws.service.chron.DuplicateJobName;
import com.zws.service.config.DataProcessorService;

import java.util.*;

public class hiChronJob extends hiItem {

  public hiChronJob() { super(); }
  public hiChronJob(ChronJob j) { super(null); job = j; render();}
  public void bind() throws Exception {
    if (null==getID() || "".equals(getID())) return;
    job = ChronService.find(getID());
  }

  public void render() {
    if (null==job) return;
    setJobName(job.getName());
    setID(job.getName());
    setProcessName(job.getProcessName());
    setStartHour(""+job.getStartTime().get(Calendar.HOUR_OF_DAY));
    setStartMinute(""+job.getStartTime().get(Calendar.MINUTE));
    if (1==getStartHour().length()) setStartHour("0"+getStartHour());
    if (1==getStartMinute().length()) setStartMinute("0"+getStartMinute());
    setPeriodHours(job.getPeriodHours().toString());
    setPeriodMinutes(job.getPeriodMinutes().toString());
    if (1==getPeriodHours().length()) setPeriodHours("0"+getPeriodHours());
    if (1==getPeriodMinutes().length()) setPeriodMinutes("0"+getPeriodMinutes());
    if (null!=ChronService.findScheduledJob(job.getName())) setStatus("scheduled");
    else setStatus("stopped"); //todo: move "started" and stopped to string resources
  }

/*
  public String update() throws Exception {
    Calendar t = new GregorianCalendar();
    t.set(1999,8,8,Integer.valueOf(getStartingHour1()).intValue(), Integer.valueOf(getStartingMinute1()).intValue());
    mDataService.setProcessTime(1, t);
    t.set(1999,8,8,Integer.valueOf(getStartingHour2()).intValue(), Integer.valueOf(getStartingMinute2()).intValue());
    mDataService.setProcessTime(2, t);
    TaskScheduler.cancel(KEY_PROCESS_1);
    TaskScheduler.cancel(KEY_PROCESS_2);
    return ctrlVIEW;
  }
*/

  public String save() {
    populate();
    try {
      ChronService.update(job);
      logFormStatus("msg.item.saved", getID());
      return ctrlOK;
    }
    catch (Exception e) {
      e.printStackTrace();
      logFormError(keyRUNTIME_ERROR);
      return ctrlSYSTEM_ERROR;
    }
  }
  public String add() {
    setID(getJobName());
    job = new ChronJob();
    job.setName(getJobName());
    populate();
    try {
      ChronService.add(job);
      logFormStatus("msg.item.saved", getID());
      return ctrlOK;
    }
    catch (DuplicateJobName e) {
      logFormError("err.duplicate.name", getJobName());
      return ctrlERROR;
    }
    catch (Exception e) {
      e.printStackTrace();
      logFormError(keyRUNTIME_ERROR);
      return ctrlSYSTEM_ERROR;
    }
  }

  private void populate() {
    Calendar t = new GregorianCalendar();
    job.setProcessName(getProcessName());
    t.set(1999,8,8,Integer.valueOf(getStartHour()).intValue(), Integer.valueOf(getStartMinute()).intValue());
    job.setStartTime(t);
    job.setPeriodHours(new Long(getPeriodHours()));
    job.setPeriodMinutes(new Long(getPeriodMinutes()));
  }

  public String getJobName() { return jobName; }
  public void setJobName(String s) { jobName=s; }
  public String getProcessName() { return processName; }
  public void setProcessName(String s) { processName=s; }
  public Collection getAvailableProcessors() { return DataProcessorService.getProcessorNames(); }
  public String getStartHour(){ return startHour; }
  public void setStartHour(String s) { startHour=s; }
  public String getStartMinute() { return startMinute; }
  public void setStartMinute(String s) { startMinute=s; }
  public String getPeriodHours() { return periodHours; }
  public void setPeriodHours(String s) { periodHours=s; }
  public String getPeriodMinutes() { return periodMinutes; }
  public void setPeriodMinutes(String s) { periodMinutes=s; }
  public Collection getHoursInPeriod() { return hiTimes.getTimes(100, 1, 1); }
  public Collection getHoursInDay() { return hiTimes.getHoursInDay(); }
  public Collection getMinutesInHour() { return hiTimes.getMinutesInHour(); }
  public String getStatus() { return status; }
  public void setStatus(String s) { status=s; }

  private ChronJob job;
  private String jobName=null;
  private String processName=null;
  private String startHour=null;
  private String startMinute=null;
  private String periodHours=null;
  private String periodMinutes=null;
//  private String periodSeconds=null;
  private String status=null;
}
