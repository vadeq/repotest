/*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Feb 22, 2007
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
package zws.repository.R8;


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

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

public class R8Test1 {
  public R8Test1() {
    Properties.set(Names.SERVICE_FINDER_HOSTNAME, "plm-rtp-002-d");
    c = RepositoryClient.getClient();
    r = c.findRepository("ilink-8");

  }

  public static void main(String[] args) throws Exception {
    R8Test1 x = new R8Test1();
    Metadata mData = null;
    String name = "69-1776-01.prt";
    Authentication id = new Authentication("user81", "user81");
    QxContext ctx = x.materializeQxContext();
   ctx.set(Names.SELECT_ATTRIBUTES,"is-new,DESC");
    RepositoryMetadataSource source = r.materializeMetadataSource();
    RepositoryStructureSource sSource = r.materializeStructureSource();
    mData = source.findLatest(ctx, name, id);
    //zws.util.LogWriter.printOnConsole("find latest " +  mData);
    //x.getDeps(ctx, mData, id);
    System.out.println("");
    System.out.println("findLatest "+mData);
    name = "700-03908-01.prt";
    mData = source.findLatest(ctx, name, id);
    System.out.println("findLatest2 "+mData);
    //Collection c  = sSource.reportDependencies(ctx, mData.getOrigin(), id);
    //System.out.println("c  "+ c);
   }


  private void getDeps(QxContext ctx, Metadata mData, Authentication id) throws Exception{
    RepositoryStructureSource bomSource = r.materializeStructureSource();
    Metadata bill = bomSource.reportBOM(ctx, mData.getOrigin(), id);
    //zws.util.LogWriter.printOnConsole("bill  " +  bill);
    populateDependencies(ctx, bomSource, bill, id);
    {} //System.out.println("getDeps " + bill);
  }

  private void populateDependencies(QxContext ctx, RepositoryStructureSource bomSource, Metadata bill, Authentication id) throws Exception {
    Collection deps = bomSource.reportDependencies(ctx, bill.getOrigin(), id);
    if (null !=deps && !deps.isEmpty()) {
      Iterator i = deps.iterator();
      Metadata dep = null;
      MetadataSubComponentBase subDep = null;
      while (i.hasNext()) {
        dep = (Metadata)i.next();
        subDep = new MetadataSubComponentBase(dep);
        if(!bill.hasSubComponent(subDep.getName())) bill.addSubComponent(subDep);
      }
    }
    if(bill.hasSubComponents()) {
      Collection subs = bill.getSubComponents();
      if(null != subs && !subs.isEmpty()) {
        Iterator itr = subs.iterator();
        Metadata subData = null;
        while (itr.hasNext()) {
          subData = (Metadata)itr.next();
          populateDependencies(ctx, bomSource, subData, id);
        }
      }
    }
  }

  private QxContext materializeQxContext() {
    QxContext ctx = new QxContext();
    return ctx;
  }

  private  static RepositoryService c = null;
  private  static Repository r = null;
}
