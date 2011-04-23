package zws.origin; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 14, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

public class HarrisOrigin extends IntralinkOrigin {
  public HarrisOrigin() { super(); }
  
  public HarrisOrigin(IntralinkOrigin base, String partNumber) {
    super();
    setName(partNumber);
    setTimeOfCreationInMillis(base.getTimeOfCreationInMillis());
    setDomainName(base.getDomainName());
    setServerName(base.getServerName());
    setDatasourceName(base.getDatasourceName());
    setBranch(base.getBranch());
    setRevision(base.getRevision());
    setVersion(base.getVersion());
  }
  
  public String getDatasourceType() { return OriginMaker.FROM_HARRIS_ILINK; }
    
  public String getName() { return partNumber; }
  public void setName(String s) { partNumber =s; }
  
  private String partNumber;
}
