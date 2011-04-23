package zws.service.webapp; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Jan 4, 2006
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.util.FileUtil;
import zws.application.Properties;
import zws.application.server.webapp.Names;
import zws.exception.NotADirectory;

import java.io.*;
import java.net.URL;

public class TransferService {

  public static URL storeFileForDownload(InputStream data) throws Exception {
    File root= new File( webappRoot);
    if (!root.exists()) throw new NotADirectory("WebApp Root:" + webappRoot);
    
    File transferDIR = new File( root ,TRANSFER);
    if (!transferDIR.exists()) transferDIR.mkdirs();
    
    String tmpFileName = "tmp-"+nextCount();
    File f =  new File(transferDIR, tmpFileName);
    FileUtil.save(data, f, true);
    URL url = new URL(getURLPath() + "/" + tmpFileName);
    return url;
  }

  public static void clearFile(URL url) throws Exception{
    String path  = url.getPath();
    String tmpFileName = path.substring(path.lastIndexOf('/')+1);
    
    File transferDIR = new File(webappRoot, TRANSFER);
    File f =  new File(transferDIR, tmpFileName);
    FileUtil.delete(f);
  }

  private static String getURLPath() { return "http://" + Server.getHostName() + "/" + TRANSFER; }
  
  private static synchronized int nextCount() { return count++; }
  private static int count=0;

  private static String TRANSFER = "transfer";
  private static String sep = Names.PATH_SEPARATOR;
  private static String webappRoot = Properties.get(Names.dirWEBAPP_ROOT);
}
