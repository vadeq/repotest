package com.zws.functor.filter;

import com.zws.domo.document.Document;

//enforces starting data are of type Document
public class DocumentCollectiveFilter extends CollectiveFilter {
  public boolean keep(Object doc)  throws Exception { return keep((Document)doc); }
  public Object transform(Object doc)  throws Exception { return transform((Document)doc); }

  public boolean keep(Document doc)  throws Exception { return true; }
  public Object transform(Document doc)  throws Exception { return doc; }
}
