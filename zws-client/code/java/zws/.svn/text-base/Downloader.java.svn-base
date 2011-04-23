package zws; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.ServerNotFound;
import zws.op.ThreadedOpBase;
import zws.origin.Origin;
import zws.service.document.DocumentServiceRemote;
import zws.service.document.EJBLocator;
import zws.util.WaitForThreads;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;

public class Downloader {
  private Downloader() {}
/**
   * Returns a {@link URL} to a binary file corresponding to a file or 
   * set of files specifed by the origin argument.
   * If more than one file is associated with the given origin, the corresponding 
   * fileset is zipped into a single file.
   * <p>
   * The file or set of files may have to be extracted from a data repository
   * before it is available. The function waits until the file(s) are extracted.
   * @param  origin  an Origin that refers to the exact location and name of the file or file set
   * @return a URL to the binary 
   * @throws ServerNotFound if the servername specified in the origin is not found
   * @throws exception if an error occures while retrieving the associated files
   */  
  public static URL getURL( Origin origin ) throws Exception {
    DocumentServiceRemote service;
    try { service = EJBLocator.findService(origin.getServerName()); }
    catch (Exception e) { e.printStackTrace(); throw new ServerNotFound(origin.getServerName()); }
    return service.getURL(origin);
  }

/**
   * Returns a {@link InputStream} corresponding to a file or 
   * set of files specifed by the origin argument.
   * If the given origin specifies more than one file, the corresponding 
   * fileset is zipped into a single file.
   * <p>
   * The file or set of files may have to be extracted from a data repository
   * before it is available. The function waits until the file(s) are extracted.
   * @param  origin  an Origin that refers to the exact location and name of the file or file set
   * @return an InputStream to the binary 
   * @throws ServerNotFound if the servername specified in the origin is not found
   * @throws exception if an error occures while retrieving the associated files
   */  
  public static InputStream download( Origin origin ) throws Exception {
    URL url = getURL(origin);
    if (null!=url) return url.openStream();
    else return null;
  }

/**
   * Returns a {@link URL} to a zip file corresponding to the collection of origins. 
   * All origins in the collection are extracted in parallel, before being zipped into 
   * a single binary. (If the collection contains only one origin, the a URL to just that 
   * origin's binary is returned). 
   *
   * @param  origins a collection of Origin to be extracted
   * @return a URL to a zip file containing the binaries associated with the origins
   * @throws exception if any remote exception occures while retrieving the associated files
   */
  public static URL getURL(Collection origins) throws Exception {
    Downloader downloader = new Downloader();
    return downloader.getURLForCollection(origins);
  }

  private static int count=0;
  private static synchronized String getKey() { return "binary-" + System.currentTimeMillis()+count++; }

  private URL getURLForCollection(Collection origins) throws Exception {
    Origin origin;
    DocumentServiceRemote service=null;
    String key=getKey();
    if (null==origins) throw new NullPointerException("Collection of origins is null");
    if (0==origins.size()) throw new NullPointerException("Collection of origins empty");
    Iterator i = origins.iterator();
    SaveBinaryFile fileSaver=null;
    WaitForThreads waiter = new WaitForThreads();
    while (i.hasNext()){
      origin = (Origin) i.next();
      if (null==service) {
        try { service = EJBLocator.findService(origin.getServerName()); }
        catch (Exception e) { e.printStackTrace(); throw new ServerNotFound(origin.getServerName()); }
      }
      fileSaver = new SaveBinaryFile();
      fileSaver.setService(service);
      fileSaver.setKey(key);
      fileSaver.setOrigin(origin);
      fileSaver.execute();
      waiter.addThread(fileSaver.getThread());
    }
    {} //System.out.println("waiting..");
    waiter.execute();
    waiter.getThread().join();
    return service.getBinaryFiles(key);
  }

  public class SaveBinaryFile extends ThreadedOpBase {
    public void executeRun() throws Exception { service.saveBinaryFile(origin, key); }
    
    public void setService(DocumentServiceRemote s) { service=s; }
    public void setOrigin(Origin o) { origin=o; }
    public void setKey(String s) { key=s; }

    private DocumentServiceRemote service=null;
    private Origin origin=null;
    private String key=null;
  }
}
