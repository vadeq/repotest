package com.zws.functor.processor.action.datasource;

import com.zws.domo.document.Document;

public class Find extends Operation {
  protected Document doOperation() throws Exception { return getDatasource().find(getDocument().getOrigin()); }
}
