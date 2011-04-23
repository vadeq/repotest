package zws.service.document; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Downloader;
import zws.Server;
import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.converter.PS2PDF;
import zws.converter.StampPDF;
import zws.security.Authentication;
import zws.datasource.Datasource;
import zws.datasource.intralink.IntralinkSource;
import zws.exception.DuplicateName;
import zws.exception.PathDoesNotExist;
import zws.origin.Origin;
import zws.service.datasource.DatasourceSvc;
import zws.util.*;

import java.io.*;
import java.net.*;

public class DocumentSvc {
  private static long count = 0;
  private static synchronized long getNewCount() { return ++count; }

  public static void print(Origin o, String printerName, int quantity) throws Exception {
    {}//Logwriter.printOnConsole("printing " + quantity + " copies of " + o.getUniqueID() + "to " + printerName);
  }

  public static URL downloadTarball(File sourcePath) throws Exception {
{}//Logwriter.printOnConsole("Attempting to prepare tarball for Download");
{}//Logwriter.printOnConsole("  1");
    URL url=null;
    String filename;
    String tempDirName;
    File binary;
    tempDirName = "file_" + getNewCount();
    File tempDownloadDir = new File(repository + Names.PATH_SEPARATOR + tempDirName);
    {}//Logwriter.printOnConsole("  2: temp Download DIR: " + tempDownloadDir.getAbsolutePath());
    if (tempDownloadDir.exists()) {
    {}//Logwriter.printOnConsole("  3: Cleaning Temp Download Dir");
      DeleteFile cleaner = new DeleteFile();
      cleaner.setFile(tempDownloadDir);
      cleaner.setDeleteIfNotEmpty(true);
      cleaner.execute();
    }
    {}//Logwriter.printOnConsole("  4: Creating Temp Download Dir" );
    createDirectory(tempDownloadDir);
    File tarball = new File(sourcePath.getParentFile(), sourcePath.getName()+".tar.gz");
    {}//Logwriter.printOnConsole("  5: Tarball" + tarball.getAbsolutePath());
    {}//Logwriter.printOnConsole("  6: Checking to see if Tarball Exists:");
    if (!tarball.exists()) {
      {}//Logwriter.printOnConsole("  7: NO!! Tarball does not exist:" +tarball.getAbsolutePath());
      throw new PathDoesNotExist(tarball.getAbsolutePath()); 
    }
    {}//Logwriter.printOnConsole("  8: YES Tarball Exists");
    filename = URLEncoder.encode(tarball.getName(), "UTF-8");
    binary = new File(tempDownloadDir, filename);
    tarball.renameTo(binary);
    {}//Logwriter.printOnConsole("  8: tarball renamed " + binary.getAbsoluteFile());
    url = new URL(protocol+"://"+Server.getIPAddress()+":"+port+"/"+path+"/"+tempDirName+"/"+filename);
    {}//Logwriter.printOnConsole("  Download URL: " + url);
    return url;
  }

  public static URL zipForDownloading(File sourcePath) throws Exception {
    URL url=null;
    String filename;
    String tempDirName;
    File binary;
    tempDirName = "file_" + getNewCount();
    File tempDownloadDir = new File(repository + Names.PATH_SEPARATOR + tempDirName);
    if (tempDownloadDir.exists()) {
      DeleteFile cleaner = new DeleteFile();
      cleaner.setFile(tempDownloadDir);
      cleaner.setDeleteIfNotEmpty(true);
      cleaner.execute();
    }
    createDirectory(tempDownloadDir);
    filename = URLEncoder.encode(sourcePath.getName(), "UTF-8");
    binary = new File(tempDownloadDir, filename);
    ZipWriter zipper = new ZipWriter();
    zipper.setZipFilename(binary.getAbsolutePath());
    zipper.setSource(sourcePath);
    zipper.execute();
    url = new URL(protocol+"://"+Server.getIPAddress()+":"+port+"/"+path+"/"+tempDirName+"/"+filename);
    return url;
  }

  public static URL getURL(Origin origin) throws Exception {
    URL url=null;
    String filename;
    String tempDirName;
    File binary;
    FileOutputStream downloadFile;
    Datasource datasource = DatasourceSvc.find(origin.getDatasourceName());
    //if invalid origin throw exception
    //datasource.saveBinary(Origin o, downloadPath, filename);
    //URL url = new URL (create url appropriately here) (ftp? http? filesystem?);

    tempDirName = "file_" + getNewCount();
    File tempDownloadDir = new File(repository + Names.PATH_SEPARATOR + tempDirName);
    if (tempDownloadDir.exists()) {
      DeleteFile cleaner = new DeleteFile();
      cleaner.setFile(tempDownloadDir);
      cleaner.setDeleteIfNotEmpty(true);
      cleaner.execute();
    }
    createDirectory(tempDownloadDir);
    
    try { binary = datasource.exportBinary(origin, tempDownloadDir, null); }
    catch (Exception e) { e.printStackTrace(); return null; }
    filename = binary.getName().replaceAll(" ", "-");
    //filename = URLEncoder.encode(binary.getName());
    if (!filename.equals(binary.getName())) {
      File f = binary;
      binary = new File (f.getParentFile(), filename);
      f.renameTo(binary);
    }
/*
    filename = System.currentTimeMillis() + "_" + getCount() + "_" + URLEncoder.encode(origin.getName()); //, "UTF-8");
    downloadFile = new FileOutputStream(repository+Names.PATH_SEPARATOR+filename );
    try {
      int b = binary.read();
      if (-1==b) downloadFile.close();
      while(b > -1) {
        downloadFile.write(b);
        b = binary.read();
      }
      File f = new File(repository, filename);
      while(!f.exists()) ; //hack: wait till os detects that the file is there.
      //++this prevents client from using the returned URL before the os is aware the new file in the download repository.
      //++this is dangerous, but resolves a timing issue.
      //++is there a better way to wait till the OS is aware that the download file has been created?
      url = new URL(protocol+"://"+host+":"+port+"/"+path+"/"+tempDirName+"/"+filename);
    }
    catch(IOException io) { throw io; }
    finally { binary.close(); downloadFile.close(); }
 */
    //url = new URL(protocol+"://"+host+":"+port+"/"+path+"/"+tempDirName+"/"+filename);
    url = new URL(protocol+"://"+Server.getIPAddress()+":"+port+"/"+path+"/"+tempDirName+"/"+URLEncoder.encode(filename, "UTF-8"));
    return url;
  }

  public static void saveBinaryFile(Origin origin, String key) throws Exception {
    URL url=null;
    if (null==origin) throw new NullPointerException("origin is null");
    if (null==key) throw new NullPointerException("key is null");
    File tempDir = new File(getTempDirName(key));
    createDirectory(tempDir);
    url = Downloader.getURL(origin);
    String urlString = url.toString();
    String filename = URLDecoder.decode(urlString.substring(urlString.lastIndexOf("/")+1), "UTF-8");
    {}//Logwriter.printOnConsole("Saving " + origin + " to " + filename + " from " + urlString); 
    save(url.openStream(), tempDir.getAbsolutePath(), filename);
  }

  public static URL getBinaryFiles(String key) throws Exception {
    File binary=null;
    String filename=null;
    File inputDir = new File(getTempDirName(key));
    String tempDirName = "file_" + getNewCount();
    File tempDownloadDir = new File(repository + Names.PATH_SEPARATOR + tempDirName);
    if (!tempDownloadDir.exists()) tempDownloadDir.mkdir();
    if (1 == inputDir.listFiles().length) {
      binary = new File(tempDownloadDir.getAbsolutePath(), inputDir.listFiles()[0].getName());
      File delDir = inputDir.getParentFile();
      inputDir.listFiles()[0].renameTo(binary);
      delDir.delete();
    }
    else {
      binary = new File(tempDownloadDir.getAbsolutePath(), "binary-" + getNewCount() + ".zip");
      ZipWriter zip = new ZipWriter();
      zip.setSource(inputDir);
      zip.setZipFilename(binary.getAbsolutePath());
      zip.execute();
    }
    filename = URLEncoder.encode(binary.getName(), "UTF-8");
    URL url = new URL(protocol+"://"+Server.getIPAddress()+":"+port+"/"+path+"/"+tempDirName+"/"+filename);
    return url;
  }
  
  private static String getTempDirName(String key) {return tempDir + Names.PATH_SEPARATOR + key; }
  
  private static synchronized void createDirectory(File dir) { if (!dir.exists()) dir.mkdir(); }  

  private static void save(InputStream binary, String directory, String filename) throws IOException, DuplicateName {
    File f = new File(directory, filename);
    if (f.exists()) throw new DuplicateName("File already exists: " + filename);
    FileOutputStream downloadFile = new FileOutputStream(f);
    try {
      int b = binary.read();
      if (-1==b) downloadFile.close();
      while(b > -1) {
        downloadFile.write(b);
        b = binary.read();
      }
      while(!f.exists()) ; //hack: wait till os detects that the file is there.
      //++this prevents client from using the returned URL before the os is aware the new file in the download repository.
      //++this is dangerous, but resolves a timing issue.
      //++is there a better way to wait till the OS is aware that the download file has been created?
    }
    catch(IOException io) { throw io; }
    finally { binary.close(); downloadFile.close(); }
  }    
  
  private File extractBinary(Origin origin, File directory) throws Exception {
    Datasource datasource = DatasourceSvc.find(origin.getDatasourceName());
    return datasource.exportBinary(origin, directory, null);
  }
  
  public static void convertDRW2PDF(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    IntralinkSource datasource = (IntralinkSource)DatasourceSvc.find(origin.getDatasourceName());
    String psName = FileNameUtil.convertType(outputName, "ps");
    datasource.convert2PS(origin, workspace, outputPath, psName, id);
    convertPS2PDF(outputPath, psName, outputPath, outputName, true);
  }
  
  public static void convertDRW2PS(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    IntralinkSource datasource = (IntralinkSource)DatasourceSvc.find(origin.getDatasourceName());
    datasource.convert2PS(origin, workspace, outputPath, outputName, id);
  }
  
  public static void convertDRW2DWG(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    String name = outputName;
    if (!name.toLowerCase().endsWith(".zip")) name += ".zip"; 
    IntralinkSource datasource = (IntralinkSource)DatasourceSvc.find(origin.getDatasourceName());
    datasource.convert2DWG(origin, workspace, outputPath, name, id);
  }
  
  public static void convertDRW2HPGL(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    IntralinkSource datasource = (IntralinkSource)DatasourceSvc.find(origin.getDatasourceName());
    datasource.convert2HPGL(origin, workspace, outputPath, outputName, id);
  }

  public static void convertDRW2CGM(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    String name = outputName;
    if (!name.toLowerCase().endsWith(".zip")) name += ".zip"; 
    IntralinkSource datasource = (IntralinkSource)DatasourceSvc.find(origin.getDatasourceName());
    datasource.convert2CGM(origin, workspace, outputPath, name, id);
  }

  public static void convertDRW2DXF(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    String name = outputName;
    if (!name.toLowerCase().endsWith(".zip")) name += ".zip"; 
    IntralinkSource datasource = (IntralinkSource)DatasourceSvc.find(origin.getDatasourceName());
    datasource.convert2DXF(origin, workspace, outputPath, name, id);
  }

  
  public static void convertDWG2PDF(Origin origin, String workspace, String outputPath, String outputName, Authentication id) throws Exception {
    IntralinkSource datasource = (IntralinkSource)DatasourceSvc.find(origin.getDatasourceName());
    datasource.convertDWG2PDF(origin, workspace, outputPath, outputName, id);
  }
  
  public static void convertPS2PDF(String path, String name, String outputPath, String outputName, boolean removeInput) throws Exception  {
    PS2PDF converter = new PS2PDF();
    converter.setInputFilename(path + Names.PATH_SEPARATOR + name);
    converter.setOutputFilename(outputPath + Names.PATH_SEPARATOR + outputName);
    converter.setRemoveInput(removeInput);
    converter.execute();
  }

  public static void stampPDF(String path, String name, String text, int fontSize, String color, int xPosition, int yPosition, int angle, int opacity) throws Exception  {
    StampPDF stamp = new StampPDF();
    stamp.setSourceFilePath(path + Names.PATH_SEPARATOR + name);
    stamp.setText(text);
    stamp.setXPosition(xPosition);
    stamp.setYPosition(yPosition);
    stamp.setFontSize(fontSize);
    stamp.setColor(color);
    stamp.setAngle(angle);
    stamp.setOpacity(opacity);
    stamp.execute();
  }
  
  public static void stampPDF(File pdf, String text) throws Exception  {
    StampPDF stamp = new StampPDF();
    stamp.setSourceFilePath(pdf.getAbsolutePath()); 
    stamp.setText(text);
    stamp.execute();
  }
  
  private static String repository = Properties.get(Names.DOWNLOAD_REPOSITORY);
  private static String tempDir = Properties.get(Properties.tempDirectory);
  private static String protocol = Properties.get(Names.DOWNLOAD_SERVER_PROTOCOL);
  //private static String host = Properties.get(Names.DOWNLOAD_SERVER_HOSTNAME);
  private static String port =Properties.get(Names.DOWNLOAD_SERVER_PORT);
  private static String path =Properties.get(Names.DOWNLOAD_SERVER_PATH);
}
