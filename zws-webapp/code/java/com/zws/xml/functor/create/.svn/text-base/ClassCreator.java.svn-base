package com.zws.xml.functor.create;

public class ClassCreator extends CreateFunctor {
  public ClassCreator() { }

  public ClassCreator(String fqcn) { setFqcn(fqcn); }

  public final Object create() throws CreateException { return createClass(); }
  public Class createClass() throws CreateException {
    try{ return Class.forName(getFqcn()); }
    catch (Exception e) { throw new CreateException("Could not load class fqcn=\""+fqcn+"\""); }
  }
  public String getFqcn(){ return fqcn; }
  public void setFqcn(String fqcn){ this.fqcn = fqcn; }
  private String fqcn;
}