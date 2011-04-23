package com.zws.functor.processor.action.datasource;

import com.zws.domo.document.Document;

public class Delete extends Operation {
  protected Document doOperation() throws Exception {
    getDatasource().delete(getDocument().getOrigin());;
    {} //System.out.println("deleting " + getDocument().getName()+" to "+ getDatasourceName());
    return getDocument();
  }
}
