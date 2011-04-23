package zws.datasource.intralink;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 22, 2004, 4:52 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.bill.intralink.BillOfMaterials;
import zws.datasource.agile.AgileSource;
import zws.datasource.filesystem.op.DWG2PDF;
import zws.datasource.filesystem.op.DWGLayouts2PDF;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.service.datasource.DatasourceSvc;
import zws.util.FileNameUtil;
//import zws.util.{}//Logwriter;
import zws.util.PrintUtil;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Test {
  public static void main(String[] args) {
  Test t = new Test();
  {}//Logwriter.printOnConsole("starting..");
  t.run();
  {}//Logwriter.printOnConsole("done!");
  }

  public void run() {
	  try {
	    zws.application.Configurator.load();
	    File outDir = new File("C:\\zws\\zws");
	    //if (!outDir.exists()) outDir.mkdirs();
	    String n = "12258-01.dwg";

	    File sourceDWG = new File(outDir, n);
	    DWGLayouts2PDF converter = new DWGLayouts2PDF();
	    converter.setDatasource(null);
	    converter.setAuthentication(null);
	    converter.setSourceAutoCADFile(sourceDWG);
	    //converter.setOutputDirectory(outDir);
	    converter.execute();
	  }
	  catch(Exception e) { e.printStackTrace(); }
	}

  public void run2() {
    try {
      zws.application.Configurator.load();
      Origin o = OriginMaker.materialize("zwait|DesignState-node-0|ilink|dep2-ilink|1093165500000|main|F|0|569-230-a12000.asm");
      {}//Logwriter.printOnConsole(o);
      IntralinkSource source = (IntralinkSource)DatasourceSvc.find(o.getDatasourceName());
      BillOfMaterials m = source.reportBill(o, null);
      AgileSource agile = new AgileSource();
      {}//Logwriter.printOnConsole(agile.publish(m.getMetadata(), null));
    }
    catch(Exception e) { e.printStackTrace(); }
  }

  public void run1() {
    try {
      zws.application.Configurator.load();
      Origin o = OriginMaker.materialize("zwait|DesignState-node-0|ilink|dep2-ilink|1093165500000|main|C|0|gearbox.asm");
//      Origin o = OriginMaker.materialize("zwait|DesignState-node-0|ilink|dep2-ilink|1093165500000|main|F|0|569-230-a12030.asm");
      {}//Logwriter.printOnConsole(o);
      IntralinkSource source = (IntralinkSource)DatasourceSvc.find(o.getDatasourceName());
//      PrintUtil.print(source.getDataServer().findLatestPIVForName("gearbox_motor.prt"));
//      PrintUtil.print(source.getDataServer().findPIVsForPI(6822));
      Calendar d;
      d = new GregorianCalendar(2004,Calendar.AUGUST,22,15,07,41);
      PrintUtil.println(d.getTimeInMillis());
      //PrintUtil.print(source.getDataServer().findLatestPIVForPI(6822, d));

//      source.getBill(o).structuredXML();
//      {}//Logwriter.printOnConsole();
      //IntralinkAccess ilink = IntralinkAccess.getAccess();
      {}//Logwriter.printOnConsole(ilink.getBill(o).structuredXML());
    }
    catch(Exception e) { e.printStackTrace(); }
  }
}
