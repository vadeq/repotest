package com.zws.hi.baseline;

import com.zws.exception.EntryNotFound;
import com.zws.hi.hiList;
import com.zws.service.baseline.BaselineService;

/**
 * <p>Title: DesignState</p>
 * <p>Description: Design Compression Technology</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Zero Wait-State</p>
 * @author not attributable
 * @version 1.0
 */

public class hiBaselineList extends hiList {


private BaselineService service;
public void bind() throws Exception { service = BaselineService.getService(); }
public void render () throws Exception { setItems(service.findAll()); }


public String delete() {
  try {
    service.delete(getID(), getAuthentication());
    logFormStatus("msg.item.deleted", getID());
    return ctrlLIST;
  }
  catch (EntryNotFound e) {
    logFormError("err.baseline.not.found", getID());
    return ctrlERROR;
  }
  catch (Exception e) {
    logFormError("system.err.event", "delete", e.getMessage(), "");
    return ctrlSYSTEM_ERROR;
  }
}

}

