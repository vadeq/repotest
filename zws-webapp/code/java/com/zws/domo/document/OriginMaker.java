package com.zws.domo.document;

import com.zws.application.Constants;
import com.zws.functor.Functor;

import java.util.StringTokenizer;

public class OriginMaker extends Functor {

  public void execute() throws Exception { setOrigin(createOrigin()); }

  public String createOrigin() throws Exception {
    String o=null, delim = Constants.ORIGIN_DELIMITER;
    if ( null==getServiceName()) throw new Exception("Can not create an origin without a service name");
    if ( null==getDatasourceName()) throw new Exception("Can not create an origin without a datasource name");
    if ( null==getDatasourceName()) throw new Exception("Need to specify metadata field(s) to create an origin");
    o = getServiceName()+ delim + getDatasourceName();
    StringTokenizer tok = new StringTokenizer(originFields, delim);
    while (tok.hasMoreTokens()) o += delim + getDocument().get(tok.nextToken());
    return o;
  }

  public DocumentInterface getDocument() { return doc; }
  public void setDocument(DocumentInterface d) { doc=d; }
  public String getOrigin() { return origin; }
  public void setOrigin(String s) { origin=s; }
  public String getServiceName() { return serviceName; }
  public void setServiceName(String s) { serviceName=s;}
  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }
  public String getOriginFields() { return originFields; }
  public void setOriginFields(String s) { originFields=s; }

  public Object getResult() { return origin; }
  public void setResult(Object o) { origin = (String)o; }
  public void bind(Object o) { doc = (DocumentInterface)o; }

  private DocumentInterface doc=null;
  private String origin=null;

  private String serviceName=null;
  private String datasourceName=null;
  private String originFields=null;
}
