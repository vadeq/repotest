package zws.repository.R8;/*
                           * DesignState - Design Compression Technology @author: arbind @version: 1.0 Created on May 23, 2007 10:48:58 AM Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
                           */

import zws.application.Names;
import zws.application.Properties;
import zws.exception.UnsupportedOperation;

import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.source.RepositoryBinarySource;
import zws.security.Authentication;
import zws.service.file.depot.FileDepotClient;
import zws.util.FileUtil;
import zws.util.RemotePackage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Security;
import java.util.Collection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;

public class R8RepositoryBinarySource extends R8RepositoryBase implements RepositoryBinarySource {

  protected R8RepositoryBinarySource(QxContext parent) { configureParentContext(parent); }

  private File download(QxContext runningCtx, Origin origin, String location, Authentication id) throws Exception {
    String filePath = null;
    File fileDir = new File(location);
    if (!fileDir.exists()) {  fileDir.mkdirs(); }

    PostMethod filePost = new PostMethod(getDownloadServiceURL());
    NameValuePair numberPair = new NameValuePair("number", null);
    NameValuePair namePair = new NameValuePair("name", origin.getName());
    NameValuePair versionPair = new NameValuePair("version", null);
    NameValuePair classPair = new NameValuePair("className", "wt.epm.EPMDocument");
    filePost.setQueryString(new NameValuePair[] { numberPair, namePair, versionPair, classPair });

    HttpClient httpclient = new HttpClient();
    System.getProperties().put("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
    Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

    httpclient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(id.getUsername(), id.getPassword()));
    try {
      int result = httpclient.executeMethod(filePost);
      InputStream inputStream = filePost.getResponseBodyAsStream();
      String disposition = filePost.getResponseHeader("Content-Disposition").getValue();

      int startIndex = disposition.lastIndexOf("=");
      int endIndex = disposition.lastIndexOf("\"");
      String fileName = disposition.substring(startIndex + 2, endIndex);

      BufferedInputStream bufferedinputstream = new BufferedInputStream(inputStream);
      {} //System.out.println("location " + location);
      filePath = location + "/" + fileName;
      {} //System.out.println("fileName " + fileName);
      BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(new FileOutputStream(filePath));

      byte abyte0[] = new byte[8192];
      int i;
      while ((i = bufferedinputstream.read(abyte0)) > 0) {
        bufferedoutputstream.write(abyte0, 0, i);
      }
      bufferedoutputstream.flush();
      bufferedoutputstream.close();
      bufferedinputstream.close();

    } finally {
      filePost.releaseConnection();
    }
    File out = new File(filePath);
    {} //System.out.println("out " + out.getAbsolutePath());
    {} //System.out.println("out " + out.getName());
    if (!out.exists()) {
      {} //System.out.println("out not exits");
      out = null;
    }
    return out;

  }

  public RemotePackage fetchDesignFiles(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    String tempWorkAreaSubDir = "windchill-"+nextCount();
    File tempWorkArea = new File(Properties.get(Names.TEMP_DIR)+File.separator+tempWorkAreaSubDir);
    FileUtil.delete(tempWorkArea);
    File fetchDir = new File(tempWorkArea,"fetch-design");
    fetchDir.mkdirs();
    File fileDepotDir = new File(tempWorkArea,"file-depot");
    fileDepotDir.mkdirs();
    File file = download(runningCtx, origin, fetchDir.getAbsolutePath(), id);

    FileDepotClient depotClient = FileDepotClient.materializeClient(Properties.get(Names.FILE_DEPOT_HOSTNAME), fileDepotDir.getAbsolutePath());
    RemotePackage remotePkg = depotClient.storeDirectory(fetchDir, file.getName());
    FileUtil.delete(tempWorkArea);

    return remotePkg;
  }

  public RemotePackage fetchNativeFile(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    String tempWorkAreaSubDir = "windchill-"+nextCount();
    File tempWorkArea = new File(Properties.get(Names.TEMP_DIR)+File.separator+tempWorkAreaSubDir);
    FileUtil.delete(tempWorkArea);
    File fetchDir = new File(tempWorkArea,"fetch-native");
    fetchDir.mkdirs();
    File fileDepotDir = new File(tempWorkArea,"file-depot");
    File file = download(runningCtx, origin, fetchDir.getAbsolutePath(), id);

    FileDepotClient depotClient = FileDepotClient.materializeClient(Properties.get(Names.FILE_DEPOT_HOSTNAME), fileDepotDir.getAbsolutePath());
    RemotePackage remotePkg = depotClient.storeDirectory(fetchDir, file.getName());
    FileUtil.delete(tempWorkArea);

    return remotePkg;  }

  private static synchronized long nextCount() {
    return count++;
  }

  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryBinarySource#fetchDesignFilesLocally(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public Collection fetchDesignFilesLocally(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    throw new UnsupportedOperation("fetchDesignFilesLocally(QxContext runningCtx, Origin origin, Authentication id)");
  }

  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryBinarySource#fetchNativeFileLocally(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public File fetchNativeFileLocally(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    throw new UnsupportedOperation("fetchNativeFileLocally(QxContext runningCtx, Origin origin, Authentication id)");
  }

  private static long count = 0;

  /*public String downloadContent(String remoteURL, String number, String name, String version, String className, File toDir, Authentication id) throws Exception{

  	PostMethod filePost = new PostMethod(remoteURL);

  	NameValuePair numberPair = new NameValuePair("number", number);
  	NameValuePair namePair = new NameValuePair("name", name);
  	NameValuePair versionPair = new NameValuePair("version", version);
  	NameValuePair classPair = new NameValuePair("className", "wt.epm.EPMDocument");
  	filePost.setQueryString(  new NameValuePair[] {numberPair, namePair, versionPair, classPair});

        HttpClient httpclient = new HttpClient();
  	System.getProperties().put("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
  	Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        httpclient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(id.getUsername(), id.getPassword()));
        String filePath = null;
        try {
            int result = httpclient.executeMethod(filePost);
            InputStream inputStream = filePost.getResponseBodyAsStream();
            String disposition = filePost.getResponseHeader("Content-Disposition").getValue();

            int startIndex = disposition.lastIndexOf("=");
            int endIndex = disposition.lastIndexOf("\"");
            String fileName = disposition.substring(startIndex + 2, endIndex);

  		BufferedInputStream bufferedinputstream = new BufferedInputStream(inputStream);

  		filePath = toDir.getPath() + "/" + fileName;
  		BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(new FileOutputStream(filePath));

  		byte abyte0[] = new byte[8192];
  		int i;
  		while((i = bufferedinputstream.read(abyte0)) > 0){ bufferedoutputstream.write(abyte0, 0, i); }
  		bufferedoutputstream.flush();
  		bufferedoutputstream.close();
  		bufferedinputstream.close();

        } finally {
        	filePost.releaseConnection();
        }

        return filePath;
  }*/
}
