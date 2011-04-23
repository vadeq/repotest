package com.zws.service.config;

import com.zws.functor.processor.DataProcessor;

import java.util.*;

public class DataProcessorService {
  private static String RESOURCE_TYPE = "Data Processor";
  private static DataProcessorService service=null;

//todo: make constructor private when xml processor is finished
//  private DataProcessorService() {}
  public static DataProcessorService getInstance() {
    if (null==service) service = new DataProcessorService();
    return service;
  }


  public static Collection getProcessorNames(){ return dataProcessorPrototypes.keySet(); }

  public static DataProcessor find(String name) throws ConfigurationNotFound {
    DataProcessor processor = (DataProcessor)dataProcessorPrototypes.get(name);
    if (null==processor) throw new ConfigurationNotFound(RESOURCE_TYPE, name);
    return (DataProcessor) processor.copy();
  }

  public static Collection findAll() {
    Collection c = new Vector();
    Iterator i = dataProcessorPrototypes.values().iterator();
    while (i.hasNext()) c.add(((DataProcessor)i.next()).copy());
    return c;
  }

  public static void add(DataProcessor processor) throws DuplicateConfigurationName{
    if (null==dataProcessorPrototypes.get(processor.getName()))
      dataProcessorPrototypes.put(processor.getName(), processor);
    else throw new DuplicateConfigurationName(RESOURCE_TYPE, processor.getName());
  }

  public static DataProcessor remove(DataProcessor processor) { return remove(processor.getName()); }

  public static DataProcessor remove(String processorName) { return (DataProcessor) dataProcessorPrototypes.remove(processorName); }

  public static void update(DataProcessor processor) {
    dataProcessorPrototypes.remove(processor.getName());
    dataProcessorPrototypes.put(processor.getName(), processor);
  }

  public static void unload() { dataProcessorPrototypes.clear(); }

  private static Map dataProcessorPrototypes = new HashMap();
}
