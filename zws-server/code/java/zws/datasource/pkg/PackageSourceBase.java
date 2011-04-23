package zws.datasource.pkg;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 6:30 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.data.Metadata;
import zws.datasource.filesystem.FileSystemSource;
import zws.exception.NameNotFound;
import zws.exception.UnsupportedOperation;
import zws.origin.Origin;
import zws.security.Authentication;
import zws.util.*;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Vector;

import javax.xml.parsers.*;

import org.xml.sax.*;

public class PackageSourceBase extends FileSystemSource implements PackageSource {
  public String getType() { return Origin.FROM_PACKAGE; }
  public zws.search.SearchAgent getSearchAgent() { return null; }
  
  protected File lookupFile(String location, String name) { throw new UnsupportedOperation("PackageSource: lookupFile(location, name)"); }
  protected File lookupDirectory(Origin o) { throw new UnsupportedOperation("PackageSource: lookupDirectory(origin)"); }
  protected File lookupDirectory(String location) { throw new UnsupportedOperation("PackageSource: lookupDirectory(location)"); }
  public void moveBinary(Origin o, String toLocation, Authentication id) { throw new UnsupportedOperation("PackageSource: moveBinary(origin, toLocation, id)"); }
  public Collection listComponents(String location, Authentication id) { throw new UnsupportedOperation("PackageSource: listComponents(location, id)"); }
  public Collection listComponents(String location, boolean recursive, Authentication id) { throw new UnsupportedOperation("PackageSource: listComponents(location, recursive, id)"); }
  public String getLocation(File f) { throw new UnsupportedOperation("PackageSource: getLocation(file)"); } //packages are flat - just binaries and metadatas and events
  public String getLocation(Origin o) { throw new UnsupportedOperation("PackageSource: getLocation(origin)"); }
   
  public void setRoot(String absolutePath) { ; } //ignore and override with packageRoot
  protected File getRoot() { return getPackageRoot(); }  //override with packageRoot
  public File getPackageRoot() {
    if (null==packageRoot) {
      packageRoot = new File(Properties.get(Names.PATH_PACKAGE_ROOT), getName());
      if (!packageRoot.exists()) packageRoot.mkdirs();
    }
    return packageRoot;
  }

  protected File getBinaryRoot() { 
    if (null==binaryRoot) {
      binaryRoot = new File(getPackageRoot(), BINARY_LOCATION);
      if (!binaryRoot.exists()) binaryRoot.mkdirs();
    }
    return binaryRoot;
  }
  
  protected File getMetadataRoot() { 
    if (null==metadataRoot) {
      metadataRoot = new File(getPackageRoot(), METADATA_LOCATION);
      if (!metadataRoot.exists()) metadataRoot.mkdirs();
    }
    return metadataRoot;
  }
  
  public void clear() throws Exception { 
    FileUtil.deleteContents(getPackageRoot()); 
    FileUtil.delete(getPackageRoot().getAbsolutePath()+".tar"); 
    FileUtil.delete(getPackageRoot().getAbsolutePath()+".tar.gz"); 
  }

  protected String encode(String s) throws Exception { return URLEncoder.encode(s, "UTF-8"); }
  protected String decode(String s) throws Exception { return URLDecoder.decode(s, "UTF-8"); }
  protected String createFileName(Origin o) {
    StringBuffer name = new StringBuffer();
    name.append(o.getDomainName()).append(o.getServerName()).append(o.getDatasourceName()).append(o.getUniqueID());
    return name.toString(); 
  }
  
  
  public File lookupFile(Origin o ) {
    try { return new File(getBinaryRoot(), encode(o)); }
    catch (Exception e) { e.printStackTrace(); return null;}
  }    
  public File lookupMetadata(Origin o ) {
    try { return new File(getMetadataRoot(), encode(o)); }
    catch (Exception e) { e.printStackTrace(); return null;}
  }    
  
  public Metadata findMetadata(Origin o, Authentication id) throws Exception {
    File f = lookupMetadata(o);
    if (null==f) throw new NameNotFound(o.getUniqueID());
    if (f.exists()) return unmarshallFile(f);
    else throw new NameNotFound(o.getUniqueID());
  }

  public void deleteMetadata(Origin o, Authentication id) throws Exception {
    File f = lookupMetadata(o);
    if (null==f) throw new NameNotFound(o.getUniqueID());
    if (f.exists()) f.delete();
    else throw new NameNotFound(o.getUniqueID());    
  }
  
  public void storeMetadata(Metadata data, Authentication id) throws Exception { addMetadata(data,id); }
  public void addMetadata(Metadata data, Authentication id) throws Exception {
    String name = encode(data.getOrigin().getUniqueID()+".metadata.xml");
    File out = new File(getMetadataRoot(), name);
    FileOutputStream oStream = new FileOutputStream(out);
    byte[] bytes = data.toString().getBytes();
    oStream.write(bytes);
    oStream.close();
  }
  
  public Collection listRootComponents(Authentication id) throws Exception {
    return listComponents(true, id);
  }

  public Collection listComponents(Authentication id) throws Exception {
    return listComponents(false, id);
  }
  
  public Collection listComponents(boolean readRootComponentsOnly, Authentication id) throws Exception {
    File dir = getMetadataRoot();
    if (!dir.exists() || !dir.isDirectory()) return null;
    return unmarshallMetadataList(dir.listFiles(), readRootComponentsOnly);
  }
  public Collection unmarshallMetadataList(File[] files, boolean readRootComponentsOnly) throws Exception {
    Collection c = new Vector();
    for (int i = 0; i < files.length; i++) {
      if (files[i].isFile()) c.add(unmarshallMetadata(files[i], readRootComponentsOnly));
    }
    return c;
  }
  
  private static int count = 0;
  private synchronized int getNextCount() { return count++; }
  
  public File tarball(String name) throws Exception {
    String targetFileName = name;
    if (null==name || "".equals(name.trim())) targetFileName = packageRoot.getName();
    String time = StringUtil.getTime();
    //targetFileName = time + "-" + targetFileName + "-" + getNextCount();
    //+++ add time to target filename and propagate tarball name when uploading package
    //+++ and also postfix w/ getnext count
    targetFileName = targetFileName;
    Zpack op = new Zpack();
    op.setDeleteTarget(true);
    op.setWorkingDirectory(packageRoot.getParentFile());
    op.setTarget(packageRoot.getName());
    op.setTarballName(targetFileName);
    op.execute();
    return new File(packageRoot.getParentFile(), op.getZpackFilename());
  }
  
  public void zipTo(File destination) throws Exception {
   ZipWriter zipper = new ZipWriter();
   zipper.setZipFilename(destination.getAbsolutePath());
   zipper.setSource(getPackageRoot());
   zipper.execute();   
  }

  public Metadata unmarshallMetadata(File xml, boolean readRootComponentOnly) throws Exception {
    MetadataHandler handler = new MetadataHandler();
    handler.setReadRootComponentsOnly(readRootComponentOnly);
    XMLReader xr = getParser(false).getXMLReader();
    xr.setContentHandler(handler);
    xr.setErrorHandler(handler);
    FileReader r = new FileReader(xml);
    xr.parse(new InputSource(r));
    if (handler.getResults().size()==1) return (Metadata)handler.getResults().toArray()[0];
    return null;
  } 

  protected static SAXParser getParser(boolean validate) throws SAXException, ParserConfigurationException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(validate);
    return factory.newSAXParser();
  }

  private File packageRoot=null;
  private File binaryRoot = null;
  private File metadataRoot = null;
  
  private static String PACKAGE_ROOT_PATH = "\\zws\\data\\package"; //+++move to properties file
  private static String BINARY_LOCATION="binary";
  private static String METADATA_LOCATION="metadata";
}