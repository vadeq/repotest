package com.zws.functor.finder;

import com.zws.domo.Domo;
import com.zws.functor.Functor;

import java.io.InputStream;
import java.util.*;

public abstract class Finder extends Functor {
  public void execute() throws Exception { find(); setResult(openStream()); }
  public void bind(Domo d) { super.bind(d); setID(d.getID()); }
  public abstract void find() throws Exception;
  public void find(boolean autoSetContentType) throws Exception{
    find();
    if (autoSetContentType) automaticallySetContentType();
  }
  public abstract InputStream openStream() throws Exception;
  public abstract int getDataSize();

  public void automaticallySetContentType() {
    if (null==binary) return;
    int idx = binary.lastIndexOf('.');
    if( idx < 0 ) return;
    if (!(binary.length()>idx+1)) return;
    String ext = binary.substring(idx+1);
    setExtention(ext);
    setMedia(findMimeMediaType(ext));
    setContentType(findMimeContentType(ext));
  }

  public String getID() { return ID; }
  public void setID(String id) { ID=id; }

  public InputStream getStream() { return stream; }
  public void setStream(InputStream s) { stream = s; }
  public String getBinary() { return binary; }
  public void setBinary(String s) { binary = s; }
  public void setBinary(String s, boolean autoSetContentType) {
      setBinary(s);
      if (autoSetContentType) automaticallySetContentType();
  }
  public String getDisplayName() { if (null==displayName) displayName = binary; return displayName; }
  public void setDisplayName(String s) { displayName = s; }
  public String getLocation() { return location; }
  public void setLocation(String s) { location = s; }
  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName = s; }
  public String getMedia() { return media; }
  public void setMedia(String s) { media=s; }
  public String getContentType() { return contentType; }
  public void setContentType(String s) { contentType=s; }
  public String getExtention() { return extention; }
  public void setExtention(String s) { extention = s; }

  private static String findMimeMediaType(String ext) {
    String mimeType = (String) getMimeMappings().get(ext);
    if (null==mimeType) return DEFAULT_MEDIA;
    int idx = mimeType.indexOf("/");
    if (0>idx) return null;
    return mimeType.substring(0, idx).toLowerCase();
  }
  private static String findMimeContentType(String ext) {
    String mimeType = (String) getMimeMappings().get(ext);
    if (null==mimeType) return DEFAULT_CONTENT_TYPE;
    int idx = mimeType.indexOf("/");
    if (0>idx) return null;
    return mimeType.substring(idx+1).toLowerCase();
  }
  private static String findExtention(String mediaContentType) {
    Iterator i = getMimeMappings().entrySet().iterator();
    Map.Entry e;
    while (i.hasNext()) {
      e = (Map.Entry)i.next();
      if (e.getKey().toString().equalsIgnoreCase(mediaContentType)) return e.getValue().toString();
    }
    return null;
  }


  private String ID=null;
  private String binary=null;
  private String displayName=null;
  private String datasourceName=null;
  private String location=null;
  private String media="application";
  private String contentType="octet-stream";
  private String extention=null;
  private InputStream stream=null;
  private static Map mimeMappings = null;

  private static String DEFAULT_MEDIA="application";
  private static String DEFAULT_CONTENT_TYPE="octet-stream";

  private static Map getMimeMappings() {
    if (null==mimeMappings) initMimeMappings();
    return mimeMappings;
  }

// set product view express to use: .prt .asm .drw .lay .frm .dft .dgm .rep .1-.99
// what is appropriate content tyupe for Product View Express - text/plain ?

  private static void initMimeMappings() {
    mimeMappings = new HashMap();
    mimeMappings.put("abs", "audio/x-mpeg");
    mimeMappings.put("ai", "application/postscript");
    mimeMappings.put("aif", "audio/x-aiff");
    mimeMappings.put("aifc", "audio/x-aiff");
    mimeMappings.put("aiff", "audio/x-aiff");
    mimeMappings.put("aim", "application/x-aim");
    mimeMappings.put("art", "image/x-jg");
    mimeMappings.put("asf", "video/x-ms-asf");
    mimeMappings.put("asx", "video/x-ms-asf");
    mimeMappings.put("au", "audio/basic");
    mimeMappings.put("avi", "video/x-msvideo");
    mimeMappings.put("avx", "video/x-rad-screenplay");
    mimeMappings.put("bcpio", "application/x-bcpio");
    mimeMappings.put("bin", "application/octet-stream");
    mimeMappings.put("bmp", "image/bmp");
    mimeMappings.put("body", "text/html");
    mimeMappings.put("cdf", "application/x-cdf");
    mimeMappings.put("cer", "application/x-x509-ca-cert");
    mimeMappings.put("class", "application/java");
    mimeMappings.put("cpio", "application/x-cpio");
    mimeMappings.put("csh", "application/x-csh");
    mimeMappings.put("css", "text/css");
    mimeMappings.put("dib", "image/bmp");
    mimeMappings.put("doc", "application/msword");
    mimeMappings.put("dtd", "text/plain");
    mimeMappings.put("dv", "video/x-dv");
    mimeMappings.put("dvi", "application/x-dvi");
    mimeMappings.put("eps", "application/postscript");
    mimeMappings.put("etx", "text/x-setext");
    mimeMappings.put("exe", "application/octet-stream");
    mimeMappings.put("gif", "image/gif");
    mimeMappings.put("gtar", "application/x-gtar");
    mimeMappings.put("gz", "application/x-gzip");
    mimeMappings.put("hdf", "application/x-hdf");
    mimeMappings.put("hqx", "application/mac-binhex40");
    mimeMappings.put("htc", "text/x-component");
    mimeMappings.put("htm", "text/html");
    mimeMappings.put("html", "text/html");
    mimeMappings.put("hqx", "application/mac-binhex40");
    mimeMappings.put("ief", "image/ief");
    mimeMappings.put("jad", "text/vnd");
    mimeMappings.put("jar", "application/java-archive");
    mimeMappings.put("java", "text/plain");
    mimeMappings.put("jnlp", "application/x-java-jnlp-file");
    mimeMappings.put("jpe", "image/jpeg");
    mimeMappings.put("jpeg", "image/jpeg");
    mimeMappings.put("jpg", "image/jpeg");
    mimeMappings.put("js", "text/javascript");
    mimeMappings.put("kar", "audio/x-midi");
    mimeMappings.put("latex", "application/x-latex");
    mimeMappings.put("m3u", "audio/x-mpegurl");
    mimeMappings.put("mac", "image/x-macpaint");
    mimeMappings.put("man", "application/x-troff-man");
    mimeMappings.put("me", "application/x-troff-me");
    mimeMappings.put("mid", "audio/x-midi");
    mimeMappings.put("midi", "audio/x-midi");
    mimeMappings.put("mif", "application/x-mif");
    mimeMappings.put("mov", "video/quicktime");
    mimeMappings.put("movie", "video/x-sgi-movie");
    mimeMappings.put("mp1", "audio/x-mpeg");
    mimeMappings.put("mp2", "audio/x-mpeg");
    mimeMappings.put("mp3", "audio/x-mpeg");
    mimeMappings.put("mpa", "audio/x-mpeg");
    mimeMappings.put("mpe", "video/mpeg");
    mimeMappings.put("mpeg", "video/mpeg");
    mimeMappings.put("mpega", "audio/x-mpeg");
    mimeMappings.put("mpg", "video/mpeg");
    mimeMappings.put("mpv2", "video/mpeg2");
    mimeMappings.put("ms", "application/x-wais-source");
    mimeMappings.put("nc", "application/x-netcdf");
    mimeMappings.put("oda", "application/oda");
    mimeMappings.put("pbm", "image/x-portable-bitmap");
    mimeMappings.put("pct", "image/pict");
    mimeMappings.put("pdf", "application/pdf");
    mimeMappings.put("pgm", "image/x-portable-graymap");
    mimeMappings.put("pic", "image/pict");
    mimeMappings.put("pict", "image/pict");
    mimeMappings.put("pls", "audio/x-scpls");
    mimeMappings.put("png", "image/png");
    mimeMappings.put("pnm", "image/x-portable-anymap");
    mimeMappings.put("pnt", "image/x-macpaint");
    mimeMappings.put("ppm", "image/x-portable-pixmap");
    mimeMappings.put("ps", "application/postscript");
    mimeMappings.put("psd", "image/x-photoshop");
    mimeMappings.put("qt", "video/quicktime");
    mimeMappings.put("qti", "image/x-quicktime");
    mimeMappings.put("qtif", "image/x-quicktime");
    mimeMappings.put("ras", "image/x-cmu-raster");
    mimeMappings.put("rgb", "image/x-rgb");
    mimeMappings.put("rm", "application/vnd.rn-realmedia");
    mimeMappings.put("roff", "application/x-troff");
    mimeMappings.put("rtf", "application/rtf");
    mimeMappings.put("rtx", "text/richtext");
    mimeMappings.put("sh", "application/x-sh");
    mimeMappings.put("shar", "application/x-shar");
    mimeMappings.put("smf", "audio/x-midi");
    mimeMappings.put("snd", "audio/basic");
    mimeMappings.put("src", "application/x-wais-source");
    mimeMappings.put("sv4cpio", "application/x-sv4cpio");
    mimeMappings.put("sv4crc", "application/x-sv4crc");
    mimeMappings.put("swf", "application/x-shockwave-flash");
    mimeMappings.put("t", "application/x-troff");
    mimeMappings.put("tar", "application/x-tar");
    mimeMappings.put("tcl", "application/x-tcl");
    mimeMappings.put("tex", "application/x-tex");
    mimeMappings.put("texi", "application/x-texinfo");
    mimeMappings.put("texinfo", "application/x-texinfo");
    mimeMappings.put("tif", "image/tiff");
    mimeMappings.put("tiff", "image/tiff");
    mimeMappings.put("tr", "application/x-troff");
    mimeMappings.put("tsv", "text/tab-separated-values");
    mimeMappings.put("txt", "text/plain");
    mimeMappings.put("ulw", "audio/basic");
    mimeMappings.put("ustar", "application/x-ustar");
    mimeMappings.put("xbm", "image/x-xbitmap");
    mimeMappings.put("xpm", "image/x-xpixmap");
    mimeMappings.put("xwd", "image/x-xwindowdump");
    mimeMappings.put("wav", "audio/x-wav");
    mimeMappings.put("wav", "audio/x-wav");
    mimeMappings.put("wbmp", "image/vnd.wap.wbmp");
    mimeMappings.put("wml", "text/vnd.wap.wml");
    mimeMappings.put("wmlc", "application/vnd.wap.wmlc");
    mimeMappings.put("wmls", "text/vnd.wap.wmls");
    mimeMappings.put("wmlscriptc", "application/vnd.wap.wmlscriptc");
    mimeMappings.put("wrl", "x-world/x-vrml");
    mimeMappings.put("Z", "application/x-compress");
    mimeMappings.put("z", "application/x-compress");
    mimeMappings.put("zip", "application/zip");
  }
}