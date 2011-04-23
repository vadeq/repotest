package zws.origin; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 14, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

public class IlinkPartNumberOrigin extends IntralinkOrigin {
  public IlinkPartNumberOrigin() { super(); }
  
  public IlinkPartNumberOrigin(IntralinkOrigin base, String partNumber) {
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
  
  public String getDatasourceType() { return OriginMaker.FROM_ILINK_PART_NUMBER; }

  public String getName() { return partNumber; }
  public void setName(String s) { partNumber =s; }
  public String getPartNumber() { return partNumber; }
  public void setPartNumber(String s) { partNumber =s; }
  
  private String partNumber;
}
