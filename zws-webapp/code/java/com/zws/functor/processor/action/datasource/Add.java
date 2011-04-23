package com.zws.functor.processor.action.datasource;

import com.zws.domo.document.Document;

public class Add extends Operation {
  protected Document doOperation() throws Exception {
    getDatasource().add(getDocument());
    {} //System.out.println("adding " + getDocument().getName()+" to "+ getDatasourceName());
    return getDocument();
  }
}
