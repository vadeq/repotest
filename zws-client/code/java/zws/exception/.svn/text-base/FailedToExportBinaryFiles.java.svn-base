package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 2, 2004, 4:28 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.File;
import java.util.*;

import zws.data.Metadata;
import zws.util.comparator.AlphaNumericComparator;
import zws.application.Names;

public class FailedToExportBinaryFiles extends Exception {
    
 public FailedToExportBinaryFiles(String datasourceName, Collection attemptList, File[] exportedList) {
     repository = datasourceName;
     attemptedMetadataList = attemptList;
     exportedFiles = exportedList;
 }
 
 public String getMessage() {
   Collection attemptList = new TreeSet(new AlphaNumericComparator());
   Collection exportList = new TreeSet(new AlphaNumericComparator());
   Map attemptMap = new HashMap();
   Map exportMap = new HashMap();
   
   Metadata m;
   Iterator i = attemptedMetadataList.iterator();
   while (i.hasNext()) {
     m = (Metadata) i.next();
     attemptList.add(m.getName());
     exportMap.put(m.getName(), "T");
   }
   
   int idx=0;
   for (idx=0; idx < exportedFiles.length; idx++) {
     exportList.add(exportedFiles[idx].getName());
     exportMap.put(exportedFiles[idx].getName(), "T");
   }

   String key;
   Collection missing = new TreeSet(new AlphaNumericComparator());
   i = attemptMap.keySet().iterator();
   while (i.hasNext()) {
     key = (String)i.next();
     if (!exportMap.containsKey(key)) missing.add(key);
   }
   
   String msg = "Could not export the following binaries from " + repository + ":" + Names.NEW_LINE;
   i = missing.iterator();
   while (i.hasNext()) {
     msg += i.next() + Names.NEW_LINE;     
   }

   msg +=  Names.NEW_LINE + Names.NEW_LINE;
   msg += "Attempted to transfer:"  + Names.NEW_LINE;
   i = attemptList.iterator();
   while (i.hasNext()) {
     msg += i.next() + Names.NEW_LINE;     
   }
   
   return msg;
 }

  private String repository="";
  private Collection attemptedMetadataList=null;
  private File[] exportedFiles = null; 
}
