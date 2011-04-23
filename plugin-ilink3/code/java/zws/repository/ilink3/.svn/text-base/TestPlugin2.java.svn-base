package zws.repository.ilink3;
/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Mar 18, 2007 6:52:38 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.application.Configurator;
import zws.data.Metadata;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.repository.source.RepositoryBinarySource;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;
import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
//import zws.util.{}//Logwriter;

import java.util.Collection;

/**
 * The Class TestPlugin.
 * some new text
 */
public class TestPlugin2 {

  /**
   * The Constructor.
   */
  private TestPlugin2() { }

  /**
   * The main method.
   *
   * @param args the args
   */
  public static void main(String[] args) {
    TestPlugin2 t = new TestPlugin2();
    t.runAsPublishingEngine();
  }

  /**
   * Run as publishing engine.
   */
  public void runAsPublishingEngine() {
    String name = null;
    QxContext ctx = new QxContext();
    try {
      Authentication id = new Authentication("admin", "agile");
      Configurator.reinitialize();
      Configurator.load();
      //name = "200-1501.drw";
      name = "000-100.asm";
      //name = "line_roller.prt";
      Origin o = OriginMaker.materialize("cicso", "node-zero",
          Origin.FROM_ILINK, "ilink", 1234567, "main|a|1|" + name, "", "");
      RepositoryService c = RepositoryClient.getClient();
      Repository r = c.findRepository("ilink");

      RepositoryBinarySource downloader = r.materializeBinarySource();
      RepositoryMetadataSource metadataSrc = r.materializeMetadataSource();
      //metadataSrc.findLatest(ctx, "29-5001-01.prt", id);
      RepositoryStructureSource t1 = r.materializeStructureSource();
      Origin origin = OriginMaker.materialize("cicso", "node-zero", Origin.FROM_ILINK, "ilink", 1, "main|A|0|frame.asm", "", "");
      {}//Logwriter.printOnConsole("Done.........!");

      RepositoryMetadataSource src = r.materializeMetadataSource();
      Metadata srcItem = src.findLatest(ctx, name, id);

      RepositoryStructureSource mSource = r.materializeStructureSource();
    /*  Metadata m = mSource.reportLatestBill(ctx, srcItem.getName(), id);
      {}//Logwriter.printOnConsole("get latest bill-----");
      {}//Logwriter.printOnConsole(m);

      m = mSource.reportBill(ctx, srcItem.getOrigin(), id);
      {}//Logwriter.printOnConsole("get bill-----");
      {}//Logwriter.printOnConsole(m);*/
      Collection items=null;
      mSource.reportBOM(ctx, srcItem.getOrigin(), id);
      mSource.reportLatestBOM(ctx, srcItem.getOrigin().getName(), id);
      //items = mSource.reportDependencies(ctx, srcItem.getOrigin(), id);
      items = mSource.reportFirstLevelDependencies(ctx, srcItem.getOrigin(), id);
      items = mSource.reportFirstLevelLatestDependencies(ctx, srcItem.getOrigin(), id);

      {}//Logwriter.printOnConsole("reportDependencies-----");
      {}//Logwriter.printOnConsole(items);

      //items = mSource.reportLatestDependencies(ctx, srcItem.getOrigin(), id);
      {}//Logwriter.printOnConsole("reportLatestDependencies-----");
      {}//Logwriter.printOnConsole(items);

      //Collection items = mSource.getFirstLevelItems(ctx, srcItem.getOrigin(), id);
      //Metadata m = mSource.find(o, id);
      {}//Logwriter.printOnConsole("Done.........!");

    } catch (Exception e) { e.printStackTrace(); }
  }

  /*
  IntralinkOrigin o;
  o = new IntralinkOrigin();
  o.setBranch("br");
  o.setReleaseLevel("Released");
  o.setDatasourceName("ilink-00");
  o.setDomainName("zws");
  o.setServerName("DesignState-node-0");
  o.setName("abc.prt");
  o.setRevision("G");
  o.setVersion(8);
*/
}
