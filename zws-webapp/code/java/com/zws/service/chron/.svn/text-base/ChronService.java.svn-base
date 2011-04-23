package com.zws.service.chron;

import zws.database.DB;
import zws.database.Database;
import zws.exception.*;

import zws.ProcessorClient;
import zws.application.Names;
import zws.application.Properties;
import zws.op.TimerTaskOp;

import com.zws.functor.processor.ChronJob;
//import com.zws.service.Database;

import java.sql.*;
import java.util.*;

public class ChronService {
  private static ChronService service;
  private static String dataSourceName = Properties.get(Properties.DesignStateDatabase);

	// Rodney McCabe 11/26/2007 oracle port
	private final static String SQL_INSERT = "INSERT chronJob (name, processName, startTime, periodHours, periodMinutes, periodSeconds) VALUES (?,?,?,?,?,?)";
	private final static String SQL_UPDATE = "UPDATE chronJob SET processName=?, startTime=?, periodHours=?, periodMinutes=?, periodSeconds=? WHERE name=?";

  private ChronService(){}
  public static ChronService getService(){ if (null==service) service = new ChronService(); return service; }

  public static ChronJob find(String name) throws JobNotFound, Exception {
    ChronJob job=null;
    String sql = "SELECT * from chronJob WHERE name='"+name+"'";
    PreparedStatement s = database().prepareStatement(sql);
    ResultSet resultSet = database().executeQuery(s);
    while (resultSet.next()) job=unmarshall(resultSet);
    resultSet.close();
    if (null==job) throw new JobNotFound(name);
    return job;
  }

  public static Collection findAll() throws Exception{
    Collection c=new Vector();
    ChronJob job;
    String sql = "SELECT * from chronJob";
    PreparedStatement s = database().prepareStatement(sql);
    ResultSet resultSet = database().executeQuery(s);
    while (resultSet.next()) c.add(unmarshall(resultSet));
    resultSet.close();
    return c;
  }

  private static ChronJob unmarshall(ResultSet r) throws SQLException {
    ChronJob job = new ChronJob();
    Calendar c = new GregorianCalendar();
    Time t=null;
    job.setName(r.getString("name"));
    job.setProcessName(r.getString("processName"));
    job.setPeriodHours(new Long(r.getString("periodHours")));
    job.setPeriodMinutes(new Long(r.getString("periodMinutes")));
    job.setPeriodSeconds(new Long(r.getString("periodSeconds")));
    t = r.getTime("startTime");
    c.setTime(new java.util.Date(t.getTime()));
    job.setStartTime(c);
    return job;
  }

  public static synchronized void add(ChronJob job) throws DuplicateJobName, Exception{
    ChronJob j=null;
    try{ j = find(job.getName()); }
    catch (Exception ignore){;}
    if (null!=j) throw new DuplicateJobName(job.getName());
    Time t = new Time(job.getStartTime().getTime().getTime());
    //String sql = "INSERT chronJob (name, processName, startTime, periodHours, periodMinutes, periodSeconds) VALUES ('"+job.getName()+"','"+job.getProcessName()+"', '"+t.toString()+"', '"+job.getPeriodHours()+"', '"+job.getPeriodMinutes()+"', '"+job.getPeriodSeconds()+"')";

    // Rodney McCabe 11/26/2007 oracle port, use parameters, not inline sql
    PreparedStatement s = database().prepareStatement(SQL_INSERT);
    s.setString(1, job.getName());
    s.setString(2, job.getProcessName());
    s.setTime(3, t);
    s.setString(4, job.getPeriodHours().toString());
    s.setString(5, job.getPeriodMinutes().toString());
    s.setString(6, job.getPeriodSeconds().toString());

    database().execute(s);
  }

  public static synchronized void update(ChronJob job) throws Exception{
    Time t = new Time(job.getStartTime().getTime().getTime());
    //String sql = "UPDATE chronJob SET processName='"+job.getProcessName()+"', startTime='"+t.toString()+"', periodHours='"+job.getPeriodHours()+"', periodMinutes='"+job.getPeriodMinutes()+"', periodSeconds='"+job.getPeriodSeconds()+"' WHERE name='"+job.getName()+"'";

    // Rodney McCabe 11/26/2007 oracle port, use parameters, not inline sql
    PreparedStatement s = database().prepareStatement(SQL_UPDATE);
    s.setString(1, job.getProcessName());
    s.setTime(2, t);
    s.setString(3, job.getPeriodHours().toString());
    s.setString(4, job.getPeriodMinutes().toString());
    s.setString(5, job.getPeriodSeconds().toString());
	s.setString(6, job.getName());

    database().execute(s);
  }

  public static synchronized void delete(String name) throws Exception{
    String sql = "DELETE FROM chronJob WHERE name='"+name+"'";
    PreparedStatement s = database().prepareStatement(sql);
    database().execute(s);
  }

  public static synchronized void scheduleJob(String jobName) throws JobNotFound, SQLException, Exception  { scheduleJob(find(jobName)); }
  public static synchronized void scheduleJob(ChronJob job) throws Exception { //String key, TimerTask task, Calendar firstRun, long period){
    long timeNow, timeFirst, startTime = 0;
    int hourNow, hourFirst, minuteNow, minuteFirst;
    Calendar now = new GregorianCalendar();
    hourNow = now.get(Calendar.HOUR_OF_DAY);
    hourFirst = job.getStartTime().get(Calendar.HOUR_OF_DAY);
    minuteNow = now.get(Calendar.MINUTE);
    minuteFirst = job.getStartTime().get(Calendar.MINUTE);
    timeNow = (hourNow*60*60 + 60*minuteNow) * 1000;
    timeFirst = (60*60*hourFirst + 60*minuteFirst) * 1000;
    if (timeNow >= timeFirst) timeFirst += 24*60*60*1000;
    startTime = timeFirst-timeNow;
    Timer t = new Timer();

    String serverName = Properties.get(Names.CENTRAL_SERVER);
    TimerTask task = ((TimerTaskOp)ProcessorClient.getProcessor(serverName, job.getProcessName())).getTask();
    job.setTask(task);

//    t.schedule(job.getProcess().getTask(), startTime, job.getPeriod());
    t.schedule(task, startTime, job.getPeriod());
    tasks.put(job.getName(), job);
    timers.put(job.getName(), job);
  }

  public static ChronJob findScheduledJob(String name){ return (ChronJob)tasks.get(name); }

  public static synchronized void cancelScheduledJob(String name){
    timers.remove(name);
    ChronJob job = (ChronJob)tasks.remove(name);
    if (null!= job) job.getTask().cancel();
  }

  private static Database database()  throws Exception { return DB.source(dataSourceName); }

  public static Map getTasks(){ return tasks; }
  private static Map tasks = Collections.synchronizedMap(new HashMap());
  private static Map timers = Collections.synchronizedMap(new HashMap());
}
