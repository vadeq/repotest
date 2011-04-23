package com.zws.functor.processor.action.datasource;

import com.zws.domo.document.Document;

public class Update extends Operation {
  protected Document doOperation() throws Exception {
    getDatasource().update(getDocument());
    {} //System.out.println("updating " + getDocument().getName()+" to "+ getDatasourceName());
    return getDocument();
  }
}
