package com.zws.functor.processor;

import java.util.Calendar;
import java.util.TimerTask;

//todo: make this a functor - if necessary
public class ChronJob {

  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public Calendar getStartTime() { return startTime; }
  public void setStartTime(Calendar c) { startTime=c; }
  //number of milliseconds in period
  public long getPeriod(){ return (1000*getPeriodSeconds().longValue())+(1000*60*getPeriodMinutes().longValue())+(1000*60*60*getPeriodHours().longValue()); }
  public Long getPeriodHours() { return periodHours; }
  public void setPeriodHours(Long l) { periodHours=l; }
  public Long getPeriodMinutes() { return periodMinutes; }
  public void setPeriodMinutes(Long l) { periodMinutes=l; }
  public Long getPeriodSeconds() { return periodSeconds; }
  public void setPeriodSeconds(Long l) { periodSeconds=l; }

  public String getProcessName() { return processName; }
  public void setProcessName(String s) {
    processName=s;
//    try { process =DataProcessorService.find(processName); }
//    catch (Exception e) { e.printStackTrace(); }//todo: log error
  }
//  public DataProcessor getProcess(){ return process; }
//  public void setProcess(DataProcessor p) { process=p; processName=p.getName(); }

  public TimerTask getTask() { return task; }
  public void setTask(TimerTask t) { task=t; }
  
  
  private Calendar startTime;
//  private Long periodHours=new Long(1000*60*60*24); //defaults to 24 hours;
  private Long periodHours=new Long(24);
  private Long periodMinutes=new Long(0);
  private Long periodSeconds=new Long(0);
  private String name;
  private String processName;
//  private DataProcessor process;
  private TimerTask task;
}
