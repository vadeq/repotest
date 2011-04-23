package com.zws.functor.processor.action.datasource;

import com.zws.domo.document.Document;

public class Store extends Operation {
  protected Document doOperation() throws Exception {
    getDatasource().store(getDocument());
    {} //System.out.println("storing " + getDocument().getName()+" to "+ getDatasourceName());
    return getDocument();
  }
}
