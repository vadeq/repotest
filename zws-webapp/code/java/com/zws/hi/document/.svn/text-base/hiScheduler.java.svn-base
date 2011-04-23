package com.zws.hi.document;
import com.zws.application.Config;
import com.zws.functor.MetadataExtractor;
import com.zws.hi.hiItem;
import com.zws.service.repository.MetadataConfigurator;
import com.zws.service.scheduler.TaskScheduler;

import java.util.*;

public class hiScheduler extends hiItem {
  private static String KEY_PROCESS_1 = "process1";
  private static String KEY_PROCESS_2 = "process2";

  private MetadataConfigurator mDataService;
  private Calendar processTime1, processTime2;
  public void initialize() { if (null==hours) fillTimes(); }
  public void bind() throws Exception {
      /* temporarily disabled
      mDataService = MetadataConfigurator.getService();
      processTime1 = MetadataConfigurator.getProcessTime(1);
      processTime2 = MetadataConfigurator.getProcessTime(2);
      */
  }
  public boolean isBound(){ return (null!=processTime1 && null!=processTime2); }

  public void render() {
    String h, m;
    h = String.valueOf(processTime1.get(Calendar.HOUR_OF_DAY));
    m = String.valueOf(processTime1.get(Calendar.MINUTE));
    if (1==h.length()) h = "0"+h;
    if (1==m.length()) m = "0"+m;
    setStartingHour1(h);
    setStartingMinute1(m);

    h = String.valueOf(processTime2.get(Calendar.HOUR_OF_DAY));
    m = String.valueOf(processTime2.get(Calendar.MINUTE));
    if (1==h.length()) h = "0"+h;
    if (1==m.length()) m = "0"+m;
    setStartingHour2(h);
    setStartingMinute2(m);
    if (null==TaskScheduler.find(KEY_PROCESS_1)) setStatus1("stopped");
    else setStatus1("started");
    if (null==TaskScheduler.find(KEY_PROCESS_2)) setStatus2("stopped");
    else setStatus2("started");
  }

  public String toggleProcess1() { return toggleProcess(KEY_PROCESS_1); }
  public String toggleProcess2() { return toggleProcess(KEY_PROCESS_2); }

  private String toggleProcess(String key) {
    TimerTask task = TaskScheduler.find(key);
    if (null != task) TaskScheduler.cancel(key);
    else  {
      Calendar c = new GregorianCalendar();
      if (key.equals(KEY_PROCESS_1)) {
        c.set(Calendar.HOUR_OF_DAY, Integer.valueOf(getStartingHour1()).intValue());
        c.set(Calendar.MINUTE, Integer.valueOf(getStartingMinute1()).intValue());
      }
      else {
        c.set(Calendar.HOUR_OF_DAY, Integer.valueOf(getStartingHour2()).intValue());
        c.set(Calendar.MINUTE, Integer.valueOf(getStartingMinute2()).intValue());
      }
      MetadataExtractor extractor = new MetadataExtractor();

      extractor.setGeneratePDFs(true);
      extractor.setCriteria(Config.getProperty(Config.SEARCH_CRITERIA));
      extractor.setReleaseLevel(Config.getProperty(Config.RELEASE_LEVEL));
      extractor.setMaxCount(Long.valueOf(Config.getProperty(Config.MAX_SEARCH_COUNT)).longValue());
      extractor.setMetadataFile(Config.getProperty(Config.CFG_ILINK_METADATA));
      extractor.setOutputFile(Config.getProperty(Config.OUTPUT_ILINK_RESULT));
      extractor.setUsername(Config.getProperty(Config.USERNAME_INTRALINK));
      extractor.setPassword(Config.getProperty(Config.PASSWORD_INTRALINK));
      extractor.setEXEToolkitEnv(Config.getProperty(Config.EXE_PROI_TOOLKIT_ENVIRONMENT));
      TaskScheduler.schedule(key, extractor.getTask(), c, 1000*60*60*24);
    }
    return ctrlOK;
  }

  public String processNow() {
    MetadataExtractor task = new MetadataExtractor();
    task.setGeneratePDFs(true);
    task.setCriteria(Config.getProperty(Config.SEARCH_CRITERIA));
    task.setReleaseLevel(Config.getProperty(Config.RELEASE_LEVEL));
    task.setMaxCount(Long.valueOf(Config.getProperty(Config.MAX_SEARCH_COUNT)).longValue());
    task.setMetadataFile(Config.getProperty(Config.CFG_ILINK_METADATA));
    task.setOutputFile(Config.getProperty(Config.OUTPUT_ILINK_RESULT));
    task.setUsername(Config.getProperty(Config.USERNAME_INTRALINK));
    task.setPassword(Config.getProperty(Config.PASSWORD_INTRALINK));
    task.setEXEToolkitEnv(Config.getProperty(Config.EXE_PROI_TOOLKIT_ENVIRONMENT));

    Timer timer = new Timer();
    timer.schedule(task.getTask(), 100);
    return ctrlOK;
  }

  public String update() throws Exception {
      /*
    Calendar t = new GregorianCalendar();
    t.set(1999,8,8,Integer.valueOf(getStartingHour1()).intValue(), Integer.valueOf(getStartingMinute1()).intValue());
	MetadataConfigurator.setProcessTime(1, t);
    t.set(1999,8,8,Integer.valueOf(getStartingHour2()).intValue(), Integer.valueOf(getStartingMinute2()).intValue());
	MetadataConfigurator.setProcessTime(2, t);
    TaskScheduler.cancel(KEY_PROCESS_1);
    TaskScheduler.cancel(KEY_PROCESS_2);
    */
    return ctrlVIEW;
  }

  private void fillTimes() {
    int i;
    String s;
    hours = new Vector();
    minutes = new Vector();
    for (i=1; i<25; i++) {
      s = String.valueOf(i);
      if (i < 10) s = "0"+s;
      hours.add(s);
    }
    for (i=0; i<61; i+=1){
      s = String.valueOf(i);
      if (i<10) s = "0" + s;
      minutes.add(s);
    }
  }

  public String getStartingHour1() { return startingHour1; }
  public void setStartingHour1(String s) { startingHour1 = s; }
  public String getStartingMinute1() { return startingMinute1; }
  public void setStartingMinute1(String s) { startingMinute1 = s; }
  public String getStatus1() { return status1; }
  public void setStatus1(String s) { status1=s; }
  public String getStartingHour2() { return startingHour2; }
  public void setStartingHour2(String s) { startingHour2 = s; }
  public String getStartingMinute2() { return startingMinute2; }
  public void setStartingMinute2(String s) { startingMinute2 = s; }
  public String getStatus2() { return status2; }
  public void setStatus2(String s) { status2=s; }
  public Collection getHours() { return hours; }
  public Collection getMinutes() { return minutes; }

  private String startingHour1;
  private String startingMinute1;
  private String startingHour2;
  private String startingMinute2;
  private String status1;
  private String status2;
  private static Collection hours;
  private static Collection minutes;
}
