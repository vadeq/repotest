/*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Feb 22, 2007
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
package zws.repository.ilink3.test;


import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;
import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.data.MetadataSubComponentBase;

import java.util.Collection;
import java.util.Iterator;

public class I3Test {
  public I3Test() {
    //Properties.set(Names.SERVICE_FINDER_HOSTNAME, "zero-big");
    Properties.set(Names.SERVICE_FINDER_HOSTNAME, "designstate-0");
    repSvc = RepositoryClient.getClient();
    r = repSvc.findRepository("ilink");

  }

  public static void main(String[] args) throws Exception {
    I3Test x = new I3Test();
    Metadata mLatestData = null;
    Origin latestOrigin = null;
    String name = "123.asm";
    Authentication id = new Authentication("intralink", "zero0");
    QxContext ctx = x.materializeQxContext();
   // ctx.set(Names.SELECT_ATTRIBUTES,"issuppressed, dependencytype");
    RepositoryMetadataSource source = r.materializeMetadataSource();
    RepositoryStructureSource sSource = r.materializeStructureSource();
    mLatestData = source.findLatest(ctx, name, id);
    latestOrigin = mLatestData.getOrigin();
    
    System.out.println("");
    System.out.println("-findLatest-");
    System.out.println(mLatestData.toString());
    
    Collection dependencies= sSource.reportDependencies(ctx, mLatestData.getOrigin(), id);
    System.out.println("-reportDependencies-");
    System.out.println(dependencies);
    
    Collection latestDependencies = sSource.reportLatestDependencies(ctx, mLatestData.getOrigin(), id);
    System.out.println("-reportLatestDependencies-");
    System.out.println(latestDependencies);
    
    
    Collection firstLevelDependencies = sSource.reportFirstLevelDependencies(ctx, mLatestData.getOrigin(), id);
    System.out.println("-reportFirstLevelDependencies-");
    System.out.println(firstLevelDependencies);   
    
    
    Collection firstLevelLatestDependencies = sSource.reportFirstLevelLatestDependencies(ctx, latestOrigin, id);
    System.out.println("-reportFirstLevelLatestDependencies-");
    System.out.println(firstLevelLatestDependencies);   
    
    Metadata BOM = sSource.reportBOM(ctx, latestOrigin, id);
    System.out.println("-reportBOM-");
    if(null != BOM) System.out.println(BOM.toString());   
    
    
    Metadata latestBOM = sSource.reportLatestBOM(ctx, name, id);
    System.out.println("-reportLatestBOM-");
    if (null != latestBOM) System.out.println(latestBOM.toString());   
   }
 
  private QxContext materializeQxContext() {
    QxContext ctx = new QxContext();
    return ctx;
  }

  private  static RepositoryService repSvc = null;
  private  static Repository r = null;
}
