package zws.xml.op.create; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0s
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

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