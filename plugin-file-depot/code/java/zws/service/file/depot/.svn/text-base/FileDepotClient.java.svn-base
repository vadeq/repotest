/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Dec 11, 2007 10:27:24 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.service.file.depot;

import zws.application.Names;
import zws.application.Properties;
import zws.exception.Duplicate;
import zws.exception.FailedToTransferFile;
import zws.exception.InitializationError;
import zws.exception.NameNotFound;
import zws.exception.NotADirectory;
import zws.exception.NotAFile;
import zws.util.RemotePackage;
import zws.util.ZipFileUtility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.StringTokenizer;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class FileDepotClient {

  public static FileDepotClient materializeClient(String localTempDirectory)  throws Exception {
    String fileDepotHostName = Properties.get(Names.FILE_DEPOT_HOSTNAME);
    if (null==fileDepotHostName) throw new zws.exception.InvalidConfiguration(Names.FILE_DEPOT_HOSTNAME + " property has not been set!");
    FileDepotClient c = new FileDepotClient(fileDepotHostName, localTempDirectory);
    return c;
  }  

  public static FileDepotClient materializeClient(String fileDepotHostname, String localTempDirectory)  throws Exception {
    FileDepotClient c = new FileDepotClient(fileDepotHostname, localTempDirectory);
    return c;
  }
  
  
  public static RemotePackage materializeRemotePackage(URL url, String primaryName) {
    RemotePackageBase r = new RemotePackageBase(primaryName);
    r.setUrl(url);
    r.setLocation(extractRemoteLocation(url.toString()));
    return r;
  }

  public static RemotePackage materializeRemotePackage(URL url, String primaryName, String location) {
    RemotePackageBase r = new RemotePackageBase(primaryName);
    r.setUrl(url);
    r.setLocation(location);
    return r;
  }

  public static RemotePackage materializeRemotePackage(String xml) {
    RemotePackageBase r = loadFromXML(xml);
    return r;
  }
  
  public RemotePackageBase materializeremotePackage(String primaryFileName, File[] localFiles, URL url, String location ) {
    RemotePackageBase rf = new RemotePackageBase(primaryFileName);
    for (int i = 0; i < localFiles.length; i++)  rf.addFile(localFiles[i].getName());
    rf.setLocation(location);
    rf.setUrl(url);
    return rf;   
  }

  private FileDepotClient(String fileDepotHost, String localTempDirectory)throws Exception  {
    uploadServiceUrl =  "http://"+fileDepotHost+"/FileDepot/servlet/FileDepot"; 
    downloadSrcDirUrl = "http://"+fileDepotHost+"/FileDepot/Repository/"; 
    deleteDirUrl =      "http://"+fileDepotHost+"/FileDepot/Repository/";
    tempDirectory = new File(localTempDirectory);
    File f = new File (localTempDirectory);
    if (!f.exists()) f.mkdirs();
    if (!f.exists()) throw new zws.exception.InitializationError("Do not have privilege to create temp directory: " + localTempDirectory);    
    if (!f.isDirectory()) throw new zws.exception.NotADirectory(f);
  }

  public File retrieve(RemotePackage remotePackage, File toDir) throws Exception {
    if (!toDir.exists()) toDir.mkdirs();
    if (!toDir.exists()) throw new zws.exception.InitializationError("Do not have privilege to create temp directory: " + toDir);
    if (!toDir.isDirectory()) throw new zws.exception.NotADirectory(toDir);

    File zip = null;
    zip = retrieveFile(remotePackage.getUrl().toString(), tempDirectory);
    if (!zip.exists()) throw new zws.exception.FailedToTransferFile(remotePackage.getName(), tempDirectory.getCanonicalPath());
    
    File[] fileList = new ZipFileUtility().unzip(zip, toDir.getAbsolutePath());
    File target = new File(toDir, remotePackage.getName());
    zip.delete();

    return target;
  }

  public RemotePackage storeFile(File localFile) throws Exception {
    if (!localFile.exists()) throw new NameNotFound(localFile.getAbsolutePath()); 
    if (!localFile.isFile())throw new NotAFile(localFile);
    File[] files = new File[] {localFile};
    RemotePackageBase rf = store(files, localFile.getName());    
    return rf;
  }
  public RemotePackage storeFile(File localFile, String location) throws Exception {
    if (!localFile.exists()) throw new NameNotFound(localFile.getAbsolutePath()); 
    if (!localFile.isFile())throw new NotAFile(localFile); 
    File[] files = new File[] {localFile};
    RemotePackageBase rf = store(files, localFile.getName(), location);    
    return rf;
  }
  public RemotePackage storeDirectory(File localDirectory, String primaryFileName) throws Exception {
    if (!localDirectory.exists()) throw new NameNotFound(localDirectory.getAbsolutePath()); 
    if (!localDirectory.isDirectory()) throw new NotADirectory(localDirectory);
    RemotePackageBase rf = store(localDirectory.listFiles(), primaryFileName);
    return rf;
  }
  public RemotePackage storeDirectory(File localDirectory, String primaryFileName, String remoteLocation) throws Exception {
    if (!localDirectory.exists()) throw new NameNotFound(localDirectory.getAbsolutePath()); 
    if (!localDirectory.isDirectory()) throw new NotADirectory(localDirectory); 
    RemotePackageBase rf = store(localDirectory.listFiles(), primaryFileName, remoteLocation);    
    return rf;
  }
  public RemotePackage storeFiles(File[] files, String primaryFileName) throws Exception {
    for (int i =0; i < files.length; i++) {
      if (!files[i].exists()) throw new NameNotFound(files[i].getAbsolutePath()); 
      if (!files[i].isFile())throw new NotAFile(files[i]);       
    }
    RemotePackageBase rf = store(files, primaryFileName);
    return rf;
  }
  public RemotePackage storeFiles(File[] files, String primaryFileName, String remoteLocation) throws Exception {
    for (int i =0; i < files.length; i++) {
      if (!files[i].exists()) throw new NameNotFound(files[i].getAbsolutePath()); 
      if (!files[i].isFile())throw new NotAFile(files[i]);       
    }
    RemotePackageBase rf = store(files, primaryFileName, remoteLocation); 
    return rf;
  }


  private RemotePackageBase store(File[] localFiles, String primaryFileName) throws Exception {
    File zippedFiles = zipFiles(localFiles, primaryFileName);
    URL url = storeZIPFile(zippedFiles);
    
    String location = extractRemoteLocation(url.toString());
    RemotePackageBase rf= this.materializeremotePackage(primaryFileName, localFiles, url, location);
    return rf;
  }

  private RemotePackageBase store(File[] localFiles, String primaryFileName, String remoteLocation) throws Exception {
    File zippedFiles = zipFiles(localFiles, primaryFileName);
    URL url = storeZIPFile(zippedFiles, remoteLocation);

    RemotePackageBase rf= this.materializeremotePackage(primaryFileName, localFiles, url, remoteLocation);
    return rf;
  }

  private File zipFiles(File[] localFiles, String zipName ) throws Exception {
    File zip = createZipFile(zipName );
    new ZipFileUtility().zipFlat(zip.getPath(), localFiles);
    return zip;
  }
  
  private File createZipFile(String zipName) throws InitializationError, Duplicate {
    String zipFileName = zipName + ".zip";
    File zip  = new File (tempDirectory, zipFileName);
    if (zip.exists()) zip.delete();    
    if (zip.exists()) throw new Duplicate(zip.getAbsolutePath());
    try { zip.createNewFile(); } 
    catch (IOException e) { throw new InitializationError("Can not create file["+zipFileName+"] in "+tempDirectory.getAbsolutePath()); }
    return zip;
  }

  
  private URL storeZIPFile(File zip) throws Exception {
    URL url = null;

    HttpClient client = new HttpClient();
    client.getHttpConnectionManager().getParams().setConnectionTimeout(CONNECTION_TIMEOUT);
    PostMethod method = new PostMethod(uploadServiceUrl);    
    
    //providing custom retry handler is necessary
    method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

    Part[] parts = new Part[1];
    try {
      parts[0] = new FilePart(zip.getName(), zip); 
      method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));
      int statusCode = client.executeMethod(method);

      if (statusCode != HttpStatus.SC_OK) {System.err.println("Method failed: " + method.getStatusLine());}

      //read the response body.             
      InputStream in = method.getResponseBodyAsStream();
      byte[] buffer = new byte[10000];
      int len ;
      ByteArrayOutputStream out = new ByteArrayOutputStream();        
      while ((len = in.read(buffer)) > 0) {
         out.write(buffer, 0, len);
      }
      in.close();
      out.close();
  
      String responseBodyStr = new String(out.toByteArray());
      {} //System.out.println("  UploadFiles Response Body: " + responseBodyStr);
      
      //create URL object out of the response url string.
      if (null != responseBodyStr && !("\n".equals(responseBodyStr)) && !(" ".equals(responseBodyStr)))
        url = new URL(responseBodyStr);
    } catch (HttpException e) {
      System.err.println("Fatal protocol violation: " + e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("Fatal transport error: " + e.getMessage());
      e.printStackTrace();
    } finally {
      // Release the connection.
      method.releaseConnection(); 
    }
    return url;
  }

  
  private URL storeZIPFile(File zip, String toLocation) throws Exception {
    URL url = null;

    HttpClient client = new HttpClient();
    client.getHttpConnectionManager().getParams().setConnectionTimeout(CONNECTION_TIMEOUT);
    PostMethod method = new PostMethod(uploadServiceUrl);    
    
    //providing custom retry handler is necessary
    method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

    //pass along the location on the server
    NameValuePair[] data = { new NameValuePair(DOCUMENT_LOCATION_PARAM_NAME, toLocation),}; 
    method.setQueryString(data);    

    Part[] parts = new Part[1];
    try {
      parts[0] = new FilePart(zip.getName(), zip); 
      method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));
      int statusCode = client.executeMethod(method);

      if (statusCode != HttpStatus.SC_OK) {System.err.println("Method failed: " + method.getStatusLine());}

      //read the response body.             
      InputStream in = method.getResponseBodyAsStream();
      byte[] buffer = new byte[10000];
      int len ;
      ByteArrayOutputStream out = new ByteArrayOutputStream();        
      while ((len = in.read(buffer)) > 0) {
         out.write(buffer, 0, len);
      }
      in.close();
      out.close();
  
      String responseBodyStr = new String(out.toByteArray());
      {} //System.out.println("  UploadFiles Response Body: " + responseBodyStr);
      
      //create URL object out of the response url string.
      if (null != responseBodyStr && !("\n".equals(responseBodyStr)) && !(" ".equals(responseBodyStr)))
        url = new URL(responseBodyStr);
    } catch (HttpException e) {
      System.err.println("Fatal protocol violation: " + e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("Fatal transport error: " + e.getMessage());
      e.printStackTrace();
    } finally {
      //release the connection.
      method.releaseConnection(); 
    }
    return url;
  }

  private File retrieveFile(String url, File toDir) throws Exception {
    File dstFile = null;    
 
    HttpClient client = new HttpClient();
    client.getHttpConnectionManager().getParams().setConnectionTimeout(CONNECTION_TIMEOUT);
    GetMethod method = new GetMethod(url);
    
    //providing custom retry handler is necessary
    method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

    InputStream in = null;
    FileOutputStream out=null; 
    try {
      int statusCode = client.executeMethod(method);
      if (statusCode != HttpStatus.SC_OK) {System.err.println("Method failed: " + method.getStatusLine());}

      //read the response body.
      String destFileName = extractPrimaryName(url);
      dstFile = new File (toDir.getPath() + File.separator + destFileName);
      out = new FileOutputStream(dstFile);
        
      in = method.getResponseBodyAsStream();
       byte[] buffer = new byte[10000];
       int len ;
       while ((len = in.read(buffer)) > 0) {
          out.write(buffer, 0, len);
       }
    } catch (Exception e) {
      e.printStackTrace();
      throw new zws.exception.FailedToTransferFile(url, dstFile.getAbsolutePath(), e.getMessage());
    } finally {
      //release the connection.
      try { if (null!=in) in.close(); } catch(Exception ignore) {ignore.printStackTrace(); }
      try { if (null!=out) out.close(); } catch(Exception ignore) {ignore.printStackTrace(); }
      method.releaseConnection();         
    }
    if (null==dstFile) throw new FailedToTransferFile(url, dstFile.getAbsolutePath());
    
    return dstFile;    
  }
  
  
  public static String extractRemoteLocation(String url) { 
    String remoteLocation = null;
 
    //substring between the second-to-last occurance of "/" and last occurance of "/" of url string
    remoteLocation = url.substring(0, url.lastIndexOf("/"));
    int startIdx = remoteLocation.lastIndexOf("/");
    remoteLocation = remoteLocation.substring(startIdx+1);
      
    return remoteLocation; 
  }
  
  public String extractPrimaryName(String url) {
    String fileName = null;
    
    //start with substring between last occurance of "/" and end of path string
    //and trim off the fragment if any
    fileName = url.substring(url.lastIndexOf("/")+1, url.length());
    
    int refIdx = fileName.indexOf("#");
    if (refIdx > 0)
      fileName = fileName.substring(0, refIdx);
 
    return fileName;
  }  
 
  private static RemotePackageBase loadFromXML(String xml) {
    RemotePackageXMLHandler xmlHandler = new RemotePackageXMLHandler ();
    try {
      XMLReader xr = XMLReaderFactory.createXMLReader();
      xr.setContentHandler (xmlHandler);
      StringReader reader = new StringReader(xml);
      InputSource source = new InputSource(reader);
      xr.parse(source);
    } catch(Exception ex){
      {} //System.out.println("Exception message: "+ex.getMessage() );
    }
    return xmlHandler.getResult();
  }
  
  
  private static final int CONNECTION_TIMEOUT = 300000; //300000ms (five minutes)
  private static final String DOCUMENT_LOCATION_PARAM_NAME = "uniqueId";  
  private static int nextCount() { return nextCounter++; }  
  private static int nextCounter = 1;
  private String uploadServiceUrl=null; 
  private String downloadSrcDirUrl=null; 
  private String deleteDirUrl=null;    
  private File tempDirectory=null;    
}

class RemotePackageXMLHandler extends DefaultHandler {
  RemotePackageXMLHandler() { }
  public void startElement(String namespaceURI, String localName, String qName, Attributes atts)  {
    String url=null;
    String location=null;
    String primaryName = null;
  
    int length = atts.getLength();
    String name = null;
    String value = null;
    String fileList = null;
    for (int i=0; i<length; i++) {
      name = atts.getQName(i);
      value = atts.getValue(i);
      if (RemotePackage.PRIMARY_NAME.equals(name)) primaryName = value;
      if (RemotePackage.LOCATION.equals(name)) location = value;     
      if (RemotePackage.URL.equals(name)) url = value;
      if (RemotePackage.FILE_LIST.equals(name)) fileList = value;
    }
    if (null!=primaryName && null!=url) {
      try {
        URL u = new URL(url);
        remotePackage = new RemotePackageBase(primaryName);
        remotePackage.setUrl(u);
        if (null==location) location = FileDepotClient.extractRemoteLocation(url);
        remotePackage.setLocation(location);
        StringTokenizer tokens = new StringTokenizer(fileList, RemotePackageBase.DELIMITER);
        String fileName=null;
        while (tokens.hasMoreTokens()) {
          fileName = tokens.nextToken();
          remotePackage.addFile(fileName);
        }
      }
      catch(Exception e) {
        {} //System.out.println("Could not materialize RemotePackage!");
        e.printStackTrace();
        remotePackage = null;
      }
    }
  }

  public RemotePackageBase getResult() { return remotePackage; }
  private RemotePackageBase  remotePackage = null;
}//End Class RemoteFileXMLHandler
