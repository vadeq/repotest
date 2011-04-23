package com.zws.functor.filter;

import com.zws.domo.document.Document;

public class DocumentUnitFilter extends UnitFilter {
  public final boolean keep(Object doc) throws Exception { return keep((Document)doc); }
  public final Object transform(Object doc) throws Exception { return transform((Document)doc); }

  public boolean keep(Document doc) throws Exception { return true; } //override this
  public Object transform(Document doc) throws Exception { return doc; } //override this

  public Document getDocument() { return (Document)getBinding(); }
  public void setDocument(Document doc) { bind(doc); }
}
