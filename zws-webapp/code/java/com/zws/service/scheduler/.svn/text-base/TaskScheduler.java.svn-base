package com.zws.service.scheduler;
import java.util.*;

public class TaskScheduler {
  public static synchronized void schedule(String key, TimerTask task, Calendar firstRun, long period){
    long timeNow, timeFirst, startTime = 0;
    int hourNow, hourFirst, minuteNow, minuteFirst;
    Calendar now = new GregorianCalendar();
    hourNow = now.get(Calendar.HOUR_OF_DAY);
    hourFirst = firstRun.get(Calendar.HOUR_OF_DAY);
    minuteNow = now.get(Calendar.MINUTE);
    minuteFirst = firstRun.get(Calendar.MINUTE);
    timeNow = (hourNow*60*60 + 60*minuteNow) * 1000;
    timeFirst = (60*60*hourFirst + 60*minuteFirst) * 1000;
    if (timeNow >= timeFirst) timeFirst += 24*60*60*1000;
    startTime = timeFirst-timeNow;
    Timer t = new Timer();
    t.schedule(task, startTime, period);
    tasks.put(key, task);
    timers.put(key, task);
  }
  public static TimerTask find(String  key){ return (TimerTask)tasks.get(key); }

  public static synchronized void cancel(String key){
    timers.remove(key);
    TimerTask task = (TimerTask)tasks.remove(key);
    if (null!= task) task.cancel();
  }
  public static void run(Timer key) {
    TimerTask task = (TimerTask) tasks.get(key);
    if (null!= task) task.run();
  }
  public static Map getTasks(){ return tasks; }
  private static Map tasks = Collections.synchronizedMap(new HashMap());
  private static Map timers = Collections.synchronizedMap(new HashMap());
}
