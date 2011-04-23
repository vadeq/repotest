package zws.datasource.filesystem;/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.Names;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.datasource.DatasourceBase;
import zws.datasource.filesystem.op.DWGMetadataExtractor;
import zws.datasource.pkg.PackageSource;
import zws.exception.*;
import zws.mapping.MetadataMappingSet;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.security.Authentication;
import zws.util.FileUtil;
////import zws.util.{}//Logwriter;

import java.io.*;
import java.util.Collection;
import java.util.Vector;

public class FileSystemSource extends DatasourceBase {
  public String getType() { return Origin.FROM_DISK; }
  
  
  public zws.search.SearchAgent materializeSearchAgent() {
    SearchAgent agent = new SearchAgent();
    agent.setDatasource(this);    
    return agent;
  }
  public zws.search.SearchAgent materializeLatestSearchAgent() { return materializeSearchAgent(); }
  public zws.search.SearchAgent materializeLatestRevSearchAgent() { return materializeSearchAgent(); }

  public boolean contains(String name, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".contains(origin, id)");
  }
  
  public void setRoot(String absolutePath) throws NotADirectory { 
    root = new File(absolutePath); 
    if (root.exists() && !root.isDirectory()) throw new NotADirectory(absolutePath); 
  }
  protected File getRoot() { 
    if (null==root) root = new File(Names.PATH_SEPARATOR);
    return root; 
  }  
  public String getLocation(File f) { 
    String path = f.getParentFile().getAbsolutePath();
    if (path.indexOf(getRoot().getAbsolutePath())<0) return null;
    if (path.equalsIgnoreCase(getRoot().getAbsolutePath())) return Names.PATH_SEPARATOR;
    return path.substring(getRoot().getAbsolutePath().length());
  }
  public String getLocation(Origin o) {
    if (o.getDatasourceType().equalsIgnoreCase(getType())) return o.getLocation();
    String sep = Names.PATH_SEPARATOR;
    String location = o.getDomainName()+sep+o.getServerName()+sep+o.getDatasourceName()+sep+o.getName()+sep+o.getUniqueSequence().replace(Origin.delim.charAt(0), sep.charAt(0));
    return location;
  }    
  protected File lookupFile(Origin o) throws NotAFile, Exception { 
    return lookupFile(getLocation(o), o.getName()); 
  }
  protected File lookupFile(String location, String name) throws NotAFile, Exception {
    String path = setNativePathSeparator(location);
    File f;
    if (Names.PATH_SEPARATOR.equals(path)) f = new File(getRoot(), encode(name));
    if (path.startsWith(Names.PATH_SEPARATOR)) f = new File(getRoot().getAbsolutePath()+path, encode(name));
    else f = new File(getRoot().getAbsolutePath() + Names.PATH_SEPARATOR + path, encode(name));
    if (f.exists() && f.isDirectory()) throw new NotAFile(getName(), location, name);
    return f;
  }
  protected File lookupDirectory(Origin o) throws NotADirectory { 
    return lookupDirectory(getLocation(o)); 
  }
  protected File lookupDirectory(String location) throws NotADirectory {
    String path = setNativePathSeparator(location);
    if (Names.PATH_SEPARATOR.equals(path)) return getRoot();
    File f;
    if (path.startsWith(Names.PATH_SEPARATOR)) f = new File(getRoot().getAbsolutePath()+path);
    else f = new File(getRoot().getAbsolutePath() + Names.PATH_SEPARATOR + path);
    if (f.exists() && f.isFile()) throw new NotADirectory(getName(), location);
    return f;
  }
  public String setNativePathSeparator(String path) {
    if (null==path) return Names.PATH_SEPARATOR;
    String p= path.trim();
    if ("".equals(p) || "\\".equals(p) || "/".equals(p)) return Names.PATH_SEPARATOR;
    if ("\\".equals(Names.PATH_SEPARATOR)) p = p.replace('/', Names.PATH_SEPARATOR.charAt(0));
    if ("/".equals(Names.PATH_SEPARATOR)) p = p.replace('\\', Names.PATH_SEPARATOR.charAt(0));
    return p;
  }
  protected String encode(String s) throws Exception { return s; }
  protected String decode(String s) throws Exception { return s;}
  protected String encode(Origin o) throws Exception { return encode(createFileName(o)); }
  protected String decode(Origin o) throws Exception { return decode(createFileName(o)); }
  protected String createFileName(Origin o) { return o.getName(); }  

  public Origin importFromPackage(PackageSource pkg, Metadata data, MetadataMappingSet mappings, Authentication id) throws Exception {
    Origin o = data.getOrigin();
    InputStream stream = pkg.findBinary(o,id);
    //long len = pkg.getBinaryLength(o,null);
    return storeBinary(o, stream, id);
  }
  public InputStream findBinary( Origin o, Authentication id ) throws Exception { return new FileInputStream(lookupFile(o)); }
  public long getBinaryLength(Origin o, Authentication id) throws Exception {
    File b = lookupFile(o);
    if (b.exists()) return b.length();
    return 0;
  }

  public Origin storeBinary(Origin target, File binary, Authentication id) throws Exception { return updateBinary(target, binary, id); }
  public Origin addBinary(Origin target, File binary, Authentication id) throws Exception {
    File out = lookupFile(target);
    File dir = out.getParentFile();
    //out = new File(dir, encode(out.getName()));
    if (!dir.exists()) dir.mkdirs();
    FileUtil.copy(binary, out);
    return OriginMaker.materialize(getName(), getRoot(), out);
  }

  public Origin updateBinary(Origin target, File binary, Authentication id) throws Exception {
	  File out = lookupFile(target);
	  File dir = out.getParentFile();
	  //out = new File (dir, encode(out.getName()));
	  if (out.exists()) out.delete();
	  return addBinary(target, binary, id);
	}

  
  public Origin storeBinary(Origin target, InputStream binary, Authentication id) throws Exception { return updateBinary(target, binary, id); }
  public Origin addBinary(Origin target, InputStream binary, Authentication id) throws Exception {
    File out = lookupFile(target);
    File dir = out.getParentFile();
    //out = new File(dir, encode(out.getName()));
    if (!dir.exists()) dir.mkdirs();
    FileOutputStream oStream = new FileOutputStream(out);
    int c = binary.read();
    while (c!=-1) {
      oStream.write(c);
      c = binary.read();
    }
    binary.close();
    oStream.close();
    return OriginMaker.materialize(getName(), getRoot(), out);
  }
  
  public Origin updateBinary(Origin target, InputStream binary, Authentication id) throws Exception {
    File out = lookupFile(target);
    File dir = out.getParentFile();
    //out = new File (dir, encode(out.getName()));
    if (out.exists()) out.delete();
    return addBinary(target, binary, id);
  }

  public File exportBinary(Origin o, File outputDir, Authentication id) throws Exception {
    if (null==outputDir) throw new InvalidValue("err.directory.is.null");
    if (!outputDir.exists()) outputDir.mkdirs();
    if (!outputDir.isDirectory()) throw new NotADirectory(outputDir);
    File f = lookupFile(o);
    File target= new File(outputDir, o.getName());
    FileUtil.copy(f, target);
    return target;
  }

  public void moveBinary(Origin o, String toLocation, Authentication id) throws Exception {
    File f = lookupFile(o);
    if (!f.exists()) throw new NotAFile(getName(), getLocation(o), o.getName());
    File dir = lookupDirectory(toLocation);
    if (!dir.exists()) dir.mkdirs();
    File rename = new File(dir, f.getName());
    f.renameTo(rename);
  }

  public boolean containsBinary(Origin o, Authentication id) throws Exception {
    File f = lookupFile(o);
    if (null==f) return false;
    return f.exists();
  }
  
  public Metadata findMetadata(Origin o, Authentication id) throws Exception {
    File f = lookupFile(o);
    if (null==f) throw new NameNotFound(o.getUniqueID());
    if (f.exists()) return unmarshallFile(f);
    else throw new NameNotFound(o.getUniqueID());
  }
  public void deleteBinary(Origin o, Authentication id) throws Exception {
    File f = lookupFile(o);
    if (null==f) throw new NameNotFound(o.getUniqueID());
    if (f.exists()) f.delete();
    else throw new NameNotFound(o.getUniqueID());    
  }
  public Collection listRootComponents(Authentication id) throws LocationDoesNotExist, Exception {
    return listComponents(id);
  }
  public Collection listComponents(Authentication id) throws LocationDoesNotExist, Exception {
    return listComponents(null, true, id);
  }
  public Collection listComponents(String location, Authentication id) throws LocationDoesNotExist, Exception {
    File dir = lookupDirectory(location);
    return unmarshallFiles(dir.listFiles());
  }
  public Collection listComponents(String location, boolean recursive, Authentication id) throws LocationDoesNotExist, Exception {
    File dir = lookupDirectory(location);
    return unmarshallFilesRecursively(dir.listFiles(), null);
  }

  public Collection unmarshallFiles(File[] fileList) throws CanNotMaterialize{
    Collection c = new Vector();
    for (int i = 0; i < fileList.length; i++) {
      if (fileList[i].isFile()) c.add(unmarshallFile(fileList[i]));
    }
    return c;
  }

  public Collection unmarshallFilesRecursively(File[] fileList, Collection c) throws CanNotMaterialize {
    Collection files = c;
    if (null==files) files = new Vector();
    for (int i = 0; i < fileList.length; i++) {
      if (fileList[i].isFile()) files.add(unmarshallFile(fileList[i]));
      else if (fileList[i].isDirectory()) unmarshallFilesRecursively(fileList[i].listFiles(), files);
    }
    return files;
  }

  public Metadata findMetadataForPackage(Metadata data, boolean includeHistory, Authentication id) throws Exception { 
    {}//Logwriter.printOnConsole("packaging " + data.get("path") + "\\" + data.getName());
    if (data.getName().toLowerCase().endsWith(".dwg")) {
      {}//Logwriter.printOnConsole("Extracting Metadata for AutoCAD Drawing..");
      DWGMetadataExtractor op = new DWGMetadataExtractor(); 
      op.setDatasource(this);
      op.setTargetAutoCADFile(data.get("path")+Names.PATH_SEPARATOR+data.getName());
      op.setMetadata((MetadataBase)data);
      try { op.execute(); }
      catch (Exception e) { {}//Logwriter.printOnConsole("No Metadata Extracted for " + data.getName() + "!"); 
        {}//Logwriter.printOnConsole(""); 
        } 
      //e.printStackTrace(); //+++log error
      }
    return data;
  }
  
  public Metadata unmarshallFile(File f) throws CanNotMaterialize {
    MetadataBase data= new MetadataBase();
//    data.setName(f.getName());
//    data.setOrigin(createOrigin(f.getParentFile().getAbsolutePath(),f.getName(),f.lastModified()));
    data.setOrigin(OriginMaker.materialize(getName(), getRoot(), f));
    String permission="";
    if (f.canRead() && !f.canWrite()) permission = "read-only";
    if (f.canWrite()) permission = "read-write";
    data.set(Names.METADATA_DOMAIN_NAME, Server.getDomainName());
    data.set(Names.METADATA_SERVER_NAME, Server.getName());
    data.set(Names.METADATA_FILE_LOCATION, getLocation(f));
    data.set(Names.METADATA_FILE_PERMISSIONS, permission);
    data.set("path", f.getParentFile().getAbsolutePath());
    data.set(Names.METADATA_FILE_LAST_MODIFIED, ""+f.lastModified());
    try {data.set("url", f.toURL().toString());}
    catch(Exception e) { data.set("url", "N/A");}
    data.set("uri", f.toURI().toString()); //File.toURI() not supported in jdk1.3.1!
    return data;
  }

  private File root=null;

  public static String NAME = "name";
  public static String FOLDER = "folder";
  public static String ROOT_FOLDER = "root-folder";
}
/*
  public Metadata find(String location) throws CanNotMaterialize {
    File f = lookupFile(location);
    if (f.exists()) return unmarshallFile(f);
    return null;
  }
*//*
  public Origin lookupOrigin(String uid, Authentication id) {
    File f = getBinaryPath(getPathFromUniqueID(uid));
    if (f.exists()) return createOrigin(f);
    return null;
  }
*/
  
/*
  public void importPackage(PackageSource pkg) throws Exception {
    Collection c = pkg.listComponents(null, null);
    if (null==c || c.size()<1) return;
    Iterator i = c.iterator();
    Metadata m;
    Origin o;
    long len;
    InputStream stream;
    while (i.hasNext()) {
      m = (Metadata)i.next();
      o = m.getOrigin();
      stream = pkg.findBinary(o,null);
      len = pkg.getBinaryLength(o,null);
      storeBinary(o, stream, len, null);
    }
  }
*/
  /*
  public Collection synchronizePackage(PackageSource pkg) throws Exception {
    Collection syncRecords = new Vector();
    Collection c = pkg.listComponents(null, null);
    if (null==c || c.size()<1) return syncRecords;
    Iterator i = c.iterator();
    Metadata m;
    Origin source, target=null;
    long len;
    InputStream stream;
    while (i.hasNext()) {
      m = (Metadata)i.next();
      source = m.getOrigin();
      if (!SynchronizationSvc.isSynchronizedToDatasource(source, this) || !containsBinary(source, null) ) {
        if (!SynchronizationSvc.isIndirectlySynchronizedToDatasource(source, this)) {
          stream = pkg.findBinary(source,null);
          len = pkg.getBinaryLength(source,null);
          target = storeBinary(source, stream, len, null);
          {}//Logwriter.printOnConsole("updating " + getName() + ": " + target.getUniqueID());
        }
        if (null==target) target = lookupOrigin(source.getUniqueID());
        if (null!=target) syncRecords.add(SynchronizationSvc.record(source,target)); //+++ use the client!!
      }
    }
    return syncRecords;
  }
*/
  
  /*
  public Origin storeBinary(Origin source, InputStream binary, long len, Authentication id) throws Exception { return updateBinary(source, binary,len, id); }
  public Origin addBinary(Origin source, InputStream binary, long len, Authentication id) throws Exception {
    File out = getBinaryPath(source);
    File dir = out.getParentFile();
    //out = new File(dir, encode(out.getName()));
    if (!dir.exists()) dir.mkdirs();
    FileOutputStream oStream = new FileOutputStream(out);
    int c;
    for (int i = 0; i < len; i++){
      c = binary.read();
      oStream.write(c);
    }
    binary.close();
    oStream.close();
    return createOrigin(out);
  }  
  public Origin updateBinary(Origin source, InputStream binary, long len, Authentication id) throws Exception {
    File out = getBinaryPath(source);
    File dir = out.getParentFile();
    //out = new File (dir, encode(out.getName()));
    if (out.exists()) out.delete();
    return addBinary(source, binary,len, id);
  }
  */
  /*
  public Collection listSubFolders(String path, boolean recursive, Authentication id) throws PathDoesNotExist, Exception {
    throw new UnsupportedDatasourceOperation(this, "listSubFolders(path, recursive, id)");
  }
  public Collection getFolderListing(String path, boolean recursive, Authentication id) throws PathDoesNotExist, Exception {
    throw new UnsupportedDatasourceOperation(this, "getFolderListing(path, ecursive, id)");
  }
  public void createFolder(String name, String path, Authentication id) throws InvalidName, PathDoesNotExist, DuplicateName, Exception {
    throw new UnsupportedDatasourceOperation(this, "createFolder(name, path, id) ");
  }
  public void deleteFolder(String path, Authentication id) throws PathDoesNotExist, NotEmpty, Exception {
    throw new UnsupportedDatasourceOperation(this, "deleteFolder(path, id) ");
  }

  public Origin createOrigin(File f){
    String path = f.getParentFile().getAbsolutePath();
    String name = f.getName();
    long lastModified = f.lastModified();
    return createOrigin(path, name, lastModified);
  }

  public Origin createOrigin(String path, String fileName, long lastModified){
    String location = path.trim();
    int idx = path.indexOf(getRootPath());
    String rootPath = getRootPath();
    if (location.equalsIgnoreCase(rootPath)) location =Names.PATH_SEPARATOR;
    else if (null!=rootPath && idx>-1) location=location.substring(idx + rootPath.length()+1);
    Origin o = new Origin(Properties.get(Names.SERVER_NAME), getName(), Origin.FILESYSTEM, lastModified, location+Names.ORIGIN_DELIMITER +fileName+Names.ORIGIN_DELIMITER+lastModified);
    o.setName(fileName);
    return o;
    return null;
  }

  public Origin createOrigin(Metadata m){
    String server = Server.getName();
    String location = m.get(Names.METADATA_FILE_LOCATION);
    if (null==location || "".equals(location.trim())) location=Names.PATH_SEPARATOR;
    long lastModified = Long.valueOf(m.get(Names.METADATA_FILE_LAST_MODIFIED)).longValue();
    String uid = location + Names.ORIGIN_DELIMITER + m.getName() + Names.ORIGIN_DELIMITER + lastModified;
    Origin o = new Origin(server, getName(), getType(), lastModified, uid);
    o.setName(m.getName());
    return o;
    return null;
  }
   
  */
