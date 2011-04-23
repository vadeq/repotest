package zws.service.printer;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 14, 2004, 1:05 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Downloader;
import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.exception.DuplicateName;
import zws.exception.NameNotFound;
import zws.origin.Origin;
import zws.printer.Printer;
import zws.printer.PrinterOrderComparator;
import zws.service.PrototypeSvc;
import zws.util.*;

import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.Collection;
import java.util.SortedSet;
import zws.util.*;

public class PrinterSvc {
  public static String NAMESPACE = "zws-printer-service";
  public static String getNamespace() { return NAMESPACE; }
  public static Printer find(String name) throws NameNotFound { return (Printer)PrototypeSvc.lookup(NAMESPACE, name); }  
  public static Collection getPrinterNames() {
    Collection names = new Vector();
    Collection c = findAll();
    if(null==c) return names;
    Iterator i = c.iterator();
    String name;
    Printer p;
    while(i.hasNext()) {
      p = (Printer)i.next();
      name = p.getName();
      names.add(name);
    }
    return names;
  }
  public static PrototypeCollection findAll() {
    SortedSet s = new TreeSet(new PrinterOrderComparator());
    PrototypeCollection p = new PrototypeVector();
    s.addAll(PrototypeSvc.findAll(NAMESPACE));
    Iterator i = s.iterator();
    while (i.hasNext()) p.add(i.next());
    return p;    
  }
  public static void add(Printer named) throws DuplicateName{ PrototypeSvc.add(NAMESPACE, named); }
  public static void update(Printer named) { remove(named.getName()); try{add(named);} catch (Exception a) {} }
  public static void remove(Printer named) { remove(named.getName()); }
  public static void remove(String name) { PrototypeSvc.remove(NAMESPACE, name); }
  public static void unload() { PrototypeSvc.unload(NAMESPACE); }

  public static synchronized void print(Origin o, String printerAlias, int copyCount) throws Exception { 
    if (copyCount<1) return;
    String docType = FileNameUtil.getFileNameExtension(o.getName());
    if ("pdf".equalsIgnoreCase(docType)) printPDF(o, printerAlias, copyCount);
    else if ("ps".equalsIgnoreCase(docType)) printPS(o, printerAlias, copyCount);
  }

  public static synchronized void printPS(Origin o, String printerAlias, int copyCount) throws Exception { 
    String command = Properties.get(Names.PS_PRINT_APP);
    Printer printer = find(printerAlias);
    File printTemp = new File(Properties.get(Names.TEMP_DIR)+Names.PATH_SEPARATOR+"print-queue", "print-" + getNextCount());
    if (printTemp.exists()) FileUtil.delete(printTemp);
    printTemp.mkdirs();
    InputStream in = Downloader.download(o);
    File print = new File(printTemp, o.getName());
    if (print.exists()) print.delete();
    FileUtil.save(in, print, true);
    File work = new File(Properties.get(Names.WORKING_DIR)+Names.PATH_SEPARATOR+"print", "work-"+getNextCount());
    if (!work.exists()) work.mkdirs();
    int i;
    for (i=0; i < copyCount; i++) {
      ExecShell shell = new ExecShell();
      shell.setExecutable(command);
      shell.setWorkingDirectory(work);
      shell.addCommandLineArgument("/D:"+printer.getPath(), false);
      shell.addCommandLineArgument(print.getAbsolutePath(), true);
      {}//Logwriter.printOnConsole("sleeping for 2..");
      Thread.sleep(2000);
      {}//Logwriter.printOnConsole("waking up");
      shell.execute();
    }
  }
  
  public static synchronized void printPDF(Origin o, String printerAlias, int copyCount) throws Exception { 
    File exe = new File(Properties.get(Names.PDF_PRINT_APP));
    if (!exe.exists()) throw new Exception("PDF Print Application Does Not Exist: " + exe.getAbsolutePath());
    Printer printer = find(printerAlias);
    File printTemp = new File(Properties.get(Names.TEMP_DIR)+Names.PATH_SEPARATOR+"print-queue", "print-" + getNextCount());
    if (printTemp.exists()) FileUtil.delete(printTemp);
    printTemp.mkdirs();
    InputStream in = Downloader.download(o);
    File pdf = new File(printTemp, o.getName());
    if (pdf.exists()) pdf.delete();
    FileUtil.save(in, pdf, true);
    File work = new File(Properties.get(Names.WORKING_DIR)+Names.PATH_SEPARATOR+"print", "work-"+getNextCount());
    if (!work.exists()) work.mkdirs();
    int i;
    for (i=0; i < copyCount; i++) {
      ExecShell shell = new ExecShell();
      shell.setExecutable(exe.getAbsolutePath());
      shell.setWorkingDirectory(work);
      shell.addCommandLineArgument("/t", false);
      shell.addCommandLineArgument(pdf.getAbsolutePath(), true);
      shell.addCommandLineArgument(printer.getPath(), true);
      shell.addCommandLineArgument(printer.getDriverName(), true);
      shell.addCommandLineArgument(printer.getPort(), false);
      {}//Logwriter.printOnConsole("sleeping for 2..");
      Thread.sleep(2000);
      {}//Logwriter.printOnConsole("waking up");
      shell.execute();
    }
  }

  private static synchronized int getNextCount() { return count++; }
  private static int count=0;
}

