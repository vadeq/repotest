package com.zws.hi.util;

import com.zws.functor.finder.Finder;
import com.zws.hi.Interactor;

import java.io.*;

import javax.servlet.http.HttpServletResponse;

public class hiFileDownloader extends Interactor {
  public static String FORCE_SAVE_AS_CONTENT_TYPE = "application/stream";

  public static void streamForDownloading(HttpServletResponse response, Finder finder)  throws Exception {
    response.setContentType(FORCE_SAVE_AS_CONTENT_TYPE);
    response.setHeader("Content-Disposition","attachment; filename="+finder.getBinary());
    int dataLen = finder.getDataSize();
    if (0<dataLen) response.setContentLength(dataLen);
    streamData(response, finder.openStream());
  }

  public static void streamForViewing(HttpServletResponse response, Finder finder)  throws Exception {
    String contents=finder.getMedia()+"/"+finder.getContentType();
    String disposition = "attachment; filename="+finder.getDisplayName();
    response.setContentType(contents);
    response.setHeader("Content-Disposition", disposition);
    int dataLen = finder.getDataSize();
    if (0<dataLen) response.setContentLength(dataLen);
    streamData(response, finder.openStream());
  }

  private static void streamData(HttpServletResponse response, InputStream stream) throws Exception {
    if (null==stream) throw new NullPointerException("Data stream is null");
    try {
      OutputStream oStream = response.getOutputStream();
      BufferedInputStream iStream= new BufferedInputStream(stream);
      int data;
      while((data = iStream.read())!=-1) oStream.write(data);
      iStream.close();
      oStream.close();
    }
    catch (Exception e) { e.printStackTrace(); }
  }
}

