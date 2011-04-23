package com.zws.util;

import java.util.*;

public class FileNameUtil {

  public static String pdfFileName(String filename) { return filename.substring(0, filename.lastIndexOf(".")) + ".pdf"; }

  public static String addIdentifiers(String fileName, Collection identifiers, String delimiter){
    String newName = getBaseName(fileName);
    String extention = getFileNameExtention(fileName);
    if (null==extention) extention = "";
    else extention = DOT + extention;
    Iterator i = identifiers.iterator();
    while (i.hasNext()) {
      newName += delimiter+(String)i.next();
    }
    return newName+extention;
  }

  public static String removeIdentifiers(String fileName, String delimiter){
    String extention = getFileNameExtention(fileName);
    String strippedFileName = fileName;
    if (null==extention) extention = "";
    else extention = DOT + extention;
    StringTokenizer tok = new StringTokenizer(fileName, delimiter);
    if (tok.hasMoreTokens()) strippedFileName = tok.nextToken();
    return strippedFileName + extention;
  }

  public static String escapePath(String path) {
    String newPath="";
    for (int i=0; i< path.length(); i++) {
      if ("\\".equals(""+path.charAt(i))) newPath += "\\\\";
      else newPath += path.charAt(i);
    }
    return newPath;
  }

  public static String lookupFileType(String filename) {
    String ext = getFileNameExtention(filename);
    String type = (String)getFileTypes().get(ext);
    if (null==type) type = ext + " File"; //todo: key this string
    return type;
  }

  public static String convertType(String file, String type) { return getBaseName(file) + "." + type; }

  public static String getBaseName(String filename) {
    String base = filename;
    int slash, dot;
    if ((slash = base.lastIndexOf(System.getProperty("file.separator")))>=0)
      base = base.substring(slash+1);
    dot = base.lastIndexOf(".");
    if (0>dot) return base;
    return base.substring(0,dot);
  }

  public static String getFileNameExtention(String filename) {
    int dot = filename.lastIndexOf(DOT);
    if (0>dot) return null;
    return filename.toLowerCase().substring(dot+1);
  }

  private static Map getFileTypes() {
    if (null==fileTypes) initializeFileTypes();
    return fileTypes;
  }

  private static void initializeFileTypes() {
    fileTypes= new HashMap();
    fileTypes.put("doc", "MS Word");
    fileTypes.put("xls", "MS Excel");
    fileTypes.put("ppt", "MS Power Point");
    fileTypes.put("mdb", "MS Access Database");
    fileTypes.put("txt", "Text");
    fileTypes.put("prt", "Part");
    fileTypes.put("asm", "Assembly");
    fileTypes.put("pdf", "PDF");
    fileTypes.put("drw", "Pro/E Drawing");
    fileTypes.put("dwg", "AutoCAD Drawing");
    fileTypes.put("htm", "HTML");
    fileTypes.put("html", "HTML");
    fileTypes.put("xml", "XML");
    //Todo: add more!!
  }

  private static Map fileTypes = null;
  private static String DOT = ".";
  private static String FILENAME_SEPARATOR="_";
}
