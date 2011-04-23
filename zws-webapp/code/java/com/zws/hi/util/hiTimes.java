package com.zws.hi.util;

import java.util.Collection;
import java.util.Vector;

public class hiTimes {
  public static Collection getHoursInDay() { return getTimes(24, 1, 1); }
  public static Collection getMinutesInHour() { return getTimes(59, 0, 1); }
  public static Collection getTimes(int max, int start, int step) {
    int i;
    String s;
    Collection times = new Vector();
    for (i=start; i<=max; i+=step) {
      s = String.valueOf(i);
      if (i < 10) s = "0"+s;
      times.add(s);
    }
    return times;
  }
}
