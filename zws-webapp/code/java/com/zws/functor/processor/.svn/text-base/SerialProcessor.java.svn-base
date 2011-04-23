package com.zws.functor.processor;

import com.zws.application.Constants;
import com.zws.service.config.ConfigurationNotFound;
import com.zws.service.config.DataProcessorService;

import java.util.*;

public class SerialProcessor extends DataProcessor {

  public void execute() throws Exception {
    try { process(); }
    catch (Exception e) {
      setException(e);
      e.printStackTrace();
    }
  }

  public void process() throws Exception {
    DataProcessor p;
    Iterator i = processes.iterator();
    while(i.hasNext()) {
      p = (DataProcessor) i.next();
      p.process(); //todo: set flag to continue even if there is an exception
    }
  }

  public String getProcessList() { return processList; }
  public void setProcessList(String s) throws ConfigurationNotFound {
    processList=s;
    StringTokenizer tokens = new StringTokenizer(processList, Constants.DELIMITER);
    DataProcessor processor;
    String name;
    while (tokens.hasMoreTokens()) {
      name = tokens.nextToken().trim();
      try { processor = DataProcessorService.find(name); }
      catch (Exception e) { processor=null; }
      if (null==processor) throw new ConfigurationNotFound("Process called '"+name+"' not found");
      processes.add(processor);
    }
  }

  private String processList=null;
  private Collection processes=new Vector();
}
