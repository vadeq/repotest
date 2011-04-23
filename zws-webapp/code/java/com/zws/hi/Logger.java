package com.zws.hi;
import org.apache.struts.action.ActionErrors;

class Logger extends ActionErrors {
  public void log(String key, Log l) { add(key, l); }
}
