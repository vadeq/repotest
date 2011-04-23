package zws.hi.scheduler; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.ProcessorClient;
import zws.application.Names;
import zws.application.Properties;
import zws.util.AdapterPrototype;
import zws.util.AdapterVector;

import com.zws.hi.hiList;

public class hiProcessList extends hiList {

  public void bind() throws Exception {
    AdapterPrototype adapter = new hiProcessor();
    setItems(new AdapterVector(adapter));
    getItems().addAll(ProcessorClient.getAllProcessors(Properties.get(Names.CENTRAL_SERVER)));
  }

  public boolean idChoosesItem(String id, Object process) {
      return id.equals(((hiProcessor)process).getName());
  }

  public String processNow() {
    try {
      choose();
      ProcessorClient.launch( Properties.get(Names.CENTRAL_SERVER), ((hiProcessor)getChosenItem()).getProcessor());
      return ctrlOK;
    }
    catch (Exception e) {
      return ctrlERROR;
    }
  }
}
