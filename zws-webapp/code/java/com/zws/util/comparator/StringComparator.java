package com.zws.util.comparator;

//todo: create a CompareString(type, operation) that does this compare
public class StringComparator {


  public boolean isLessThanOrEqual(String compareTo){ return isEqual(compareTo) || isLessThan(compareTo); }
  public boolean isGreaterThanOrEqual(String compareTo){ return isEqual(compareTo) || isGreaterThan(compareTo); }
  public boolean isNotEqual(String compareTo){ return !isEqual(compareTo); }

  public boolean isEqual(String compareTo){
    String s0 = initString(getData());
    String s1 = initString(compareTo);
    if (getType().equalsIgnoreCase("string")) {
      if (getIgnoreCase()) return s0.equalsIgnoreCase(s1);
      else return s0.equals(s1);
    }
    else if (getType().equalsIgnoreCase("int") || getType().equalsIgnoreCase("integer")) return Integer.valueOf(s0).intValue() == Integer.valueOf(s1).intValue();
    else if (getType().equalsIgnoreCase("long")) return Long.valueOf(s0).longValue() == Long.valueOf(s1).longValue();
    else if (getType().equalsIgnoreCase("float")) return Float.valueOf(s0).floatValue() == Float.valueOf(s1).floatValue();
    else if (getType().equalsIgnoreCase("double")) return Double.valueOf(s0).doubleValue() == Double.valueOf(s1).doubleValue();
    else return false;
  }
  public boolean isLessThan(String compareTo){
    String s0 = initString(getData());
    String s1 = initString(compareTo);
    if (getType().equalsIgnoreCase("string")) {
      if (getIgnoreCase()) return s0.toLowerCase().compareTo(s1.toLowerCase()) < 0;
      else return s0.compareTo(s1) < 0;
    }
    else if (getType().equalsIgnoreCase("int") || getType().equalsIgnoreCase("integer")) return Integer.valueOf(s0).intValue() < Integer.valueOf(s1).intValue();
    else if (getType().equalsIgnoreCase("long")) return Long.valueOf(s0).longValue() < Long.valueOf(s1).longValue();
    else if (getType().equalsIgnoreCase("float")) return Float.valueOf(s0).floatValue() < Float.valueOf(s1).floatValue();
    else if (getType().equalsIgnoreCase("double")) return Double.valueOf(s0).doubleValue() < Double.valueOf(s1).doubleValue();
    else return false;
  }
  public boolean isGreaterThan(String compareTo){
    String s0 = initString(getData());
    String s1 = initString(compareTo);
    if (getType().equalsIgnoreCase("string")) {
      if (getIgnoreCase()) return s0.toLowerCase().compareTo(s1.toLowerCase()) > 0;
      else return s0.compareTo(s1) > 0;
    }
    else if (getType().equalsIgnoreCase("int") || getType().equalsIgnoreCase("integer")) return Integer.valueOf(s0).intValue() > Integer.valueOf(s1).intValue();
    else if (getType().equalsIgnoreCase("long")) return Long.valueOf(s0).longValue() > Long.valueOf(s1).longValue();
    else if (getType().equalsIgnoreCase("float")) return Float.valueOf(s0).floatValue() > Float.valueOf(s1).floatValue();
    else if (getType().equalsIgnoreCase("double")) return Double.valueOf(s0).doubleValue() > Double.valueOf(s1).doubleValue();
    else return false;
  }

  public String initString(String s) {
    if (getType().equalsIgnoreCase("string")) if (null==s) return "";
    else if (null==s || "".equals(s)) return "0";
    return s;
  }

  public String getData() { return data; }
  public void setData(String s) { data=s; }

  public String getType() { return type; }
  public void setType(String s) { type=s; }
  public boolean getIgnoreCase() { return ignoreCase; }
  public void setIgnoreCase(boolean b) { ignoreCase=b; }

  public boolean ignoreCase=true;
  private String type="string";
  public String data="";
}
