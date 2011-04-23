package zws.util; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class Profiler {

  private Map records = new HashMap();
  private Collection recordList = new Vector();
  public void dump(PrintStream out) {
    Iterator i = getRecords().iterator();
    while (i.hasNext()) out.println(((Duration)i.next()).getElapsedTimeDisplay());
    out.println("------------");
  }

  private Collection getRecords() { return recordList; }

  private Calendar getElapsedTime(String category, String name) {
    Map cat = getCategory(category);
    Duration d = (Duration)cat.get(name);
    return d.getElapsedTime();
  }

  public Map getCategory(String category) { return (Map) records.get(category); }

  public void start(String category, String name){
    Duration d = new Duration(name);
    Map cat = (Map) records.get(category);
    if (null==cat) {
      cat = new HashMap();
      records.put(category, cat);
    }
    cat.put(name, d);
    recordList.add(d);
  }

  public void stop (String category, String name) {
    Map cat = (Map)records.get(category);
    Duration d = (Duration)cat.get(name);
    d.stop();
//    if (null==d) throw new Exception ("Profiler: "+category+":"+name+" not found");
  }

  public class Duration {
    public Duration(String n){
      start = Calendar.getInstance();
      name=n;
    }
    public void start() { start = Calendar.getInstance(); }
    public void stop() {end = Calendar.getInstance(); }

    public String getElapsedTimeDisplay(){
      Calendar elapsedTime = getElapsedTime();
      SimpleDateFormat f = new SimpleDateFormat("mm ss SSS");
      return name + ": " + f.format(getElapsedTime().getTime());
    }

    public Calendar getElapsedTime(){
      Calendar elapsedTime = new GregorianCalendar();
      elapsedTime.setTime(new Date(Math.abs(end.getTime().getTime() - start.getTime().getTime())));
      return elapsedTime;
    }
    public String getName() { return name; }
    public void setName(String s) { name = s; }
    public Calendar getStartTime() { return start; }
    public Calendar getEndTime() { return end; }

    private String name=null;
    private Calendar start=null;
    private Calendar end=null;
  }
}
