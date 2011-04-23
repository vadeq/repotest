package zws.action.metadata;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 27, 2004, 6:49 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.action.ActionBase;
import zws.op.Op;
import zws.origin.*;

public class TransformOrigin extends ActionBase {
 
  public void execute() throws Exception {
  //fix origin transoformers to use the new Origin!!
    Origin oldOrigin = grabOrigin();
    String n=getString("name");
    String domain=getString("domainName");
    String server=getString("serverName");
    String datasource=getString("datasourceName");
    String type = getString("datasourceType");
    String creationDate = getString("created");
    long createdOn=0;
    String unique=getString("uid");
    String location=getString("location");
    String state=getString("state");
    
    if (null==n) n=oldOrigin.getName();
    if (null==domain) domain=oldOrigin.getDomainName();
    if (null==server) server=oldOrigin.getServerName();
    if (null==datasource) datasource=oldOrigin.getDatasourceName();
    if (null==type) type=oldOrigin.getDatasourceType();
    if (null==creationDate) createdOn = oldOrigin.getTimeOfCreationInMillis();
    else createdOn = Long.valueOf(creationDate).longValue();
    if (null==unique) unique=oldOrigin.getUniqueID();
    unique = parse(unique,true);
    OriginBase origin = (OriginBase) OriginMaker.materialize(domain, server, type, datasource, createdOn, unique, location, state);
    //Origin origin = new Origin(server, datasource, type, createdOn, unique);
    origin.setName(n);
    setResult(origin);
  }
  
  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public String getMetaName() { return metaName; }
  public void setMetaName(String s) { metaName=s; }
  public String getCtxName() { return ctxName; }
  public void setCtxName(String s) { ctxName=s; }
  public Op getNameOp() { return nameOp; }
  public void setNameOp (Op op) { nameOp=op; }
  
  public String getDomainName() { return domainName; }
  public void setDomainName(String s) { domainName=s; }
  public String getMetaDomainName() { return metaDomainName; }
  public void setMetaDomainName(String s) { metaDomainName=s; }
  public String getCtxDomainName() { return ctxDomainName; }
  public void setCtxDomainName(String s) { ctxDomainName=s; }
  public Op getDomainNameOp() { return domainNameOp; }
  public void setDomainNameOp (Op op) { domainNameOp=op; }
  
  public String getServerName() { return serverName; }
  public void setServerName(String s) { serverName=s; }
  public String getMetaServerName() { return metaServerName; }
  public void setMetaServerName(String s) { metaServerName=s; }
  public String getCtxServerName() { return ctxServerName; }
  public void setCtxServerName(String s) { ctxServerName=s; }
  public Op getServerNameOp() { return serverNameOp; }
  public void setServerNameOp (Op op) { serverNameOp=op; }
  
  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }
  public String getMetaDatasourceName() { return metaDatasourceName; }
  public void setMetaDatasourceName(String s) { metaDatasourceName=s; }
  public String getCtxDatasourceName() { return ctxDatasourceName; }
  public void setCtxDatasourceName(String s) { ctxDatasourceName=s; }
  public Op getDatasourceNameOp() { return datasourceNameOp; }
  public void setDatasourceNameOp (Op op) { datasourceNameOp=op; }

  public String getCreated() { return created; }
  public void setCreated(String s) { created=s; }
  public String getMetaCreated() { return metaCreated; }
  public void setMetaCreated(String s) { metaCreated=s; }
  public String getCreatedCtx() { return ctxCreated; }
  public void setCreatedCtx(String s) { ctxCreated=s; }
  public Op getCreatedOp() { return createdOp; }
  public void setCeatedOp (Op op) { createdOp=op; }

  public String getDatasourceType() { return datasourceType; }
  public void setDatasourceType(String s) { datasourceType=s; }
  public String getMetaDatasourceType() { return metaDatasourceType; }
  public void setMetaDatasourceType(String s) { metaDatasourceType=s; }
  public String getCtxDatasourceType() { return ctxDatasourceType; }
  public void setCtxDatasourceType(String s) { ctxDatasourceType=s; }
  public Op getDatasourceTypeOp() { return datasourceTypeOp; }
  public void setDatasourceTypeOp (Op op) { datasourceTypeOp=op; }

  public String getUid() { return uid; }
  public void setUid(String s) { uid=s; }
  public String getMetaUid() { return metaUid; }
  public void setMetaUid(String s) { metaUid=s; }
  public String getCtxUid() { return ctxUid; }
  public void setCtxUid(String s) { ctxUid=s; }
  public Op getUidOp() { return uidOp; }
  public void setUidOp (Op op) { uidOp=op; }
  
  private String name=null;
  private String metaName=null;
  private String ctxName=null;
  private Op nameOp=null;

  private String domainName=null;
  private String metaDomainName=null;
  private String ctxDomainName=null;
  private Op domainNameOp=null;

  private String serverName=null;
  private String metaServerName=null;
  private String ctxServerName=null;
  private Op serverNameOp=null;

  private String datasourceName=null;
  private String metaDatasourceName=null;
  private String ctxDatasourceName=null;
  private Op datasourceNameOp=null;

  private String datasourceType=null;
  private String metaDatasourceType=null;
  private String ctxDatasourceType=null;
  private Op datasourceTypeOp=null;

  private String created=null;
  private String metaCreated=null;
  private String ctxCreated=null;
  private Op createdOp=null;  
  
  private String uid=null;
  private String metaUid=null;
  private String ctxUid=null;
  private Op uidOp=null;  
}
