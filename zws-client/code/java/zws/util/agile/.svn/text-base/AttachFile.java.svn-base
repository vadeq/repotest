package zws.util.agile;
// ==============================================================
// Copyright (C) 1995-2005 by Agile Software Corporation
// All Rights Reserved.
//
// This is unpublished proprietary source code of Agile Software
// Corporation. The copyright notice above does not evidence
// any actual or intended publication of such source code. Any
// use or copying or backup of this source code without the
// express written consent of Agile Software Corporation is
// strictly prohibited and punishable by law.
// ==============================================================

import zws.application.Names;
import zws.exception.NameNotFound;
import zws.security.Authentication;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;

import com.agile.api.*;
import com.agile.api.AgileSessionFactory;
import com.agile.api.IAgileSession;
import com.agile.api.IAdmin;
import com.agile.api.IChange;
import com.agile.api.ITable;

public class AttachFile {

	static IAgileSession session = null;
	static IAdmin admin = null;
	
	public static void main(String[] args) {
    {} //System.out.println(args.length +"."+args[0] + "." +args[1] + "." +args[2] + "." +args[3] + "." +args[4]);
    if (args.length < 5 || args.length > 6) {
      {} //System.out.println("Usage:  AttachFile url username password partnumber file [econumber]");
      System.exit(0);
    }
    try {
      String url = args[0];
      String username = args[1];
      String password= args[2];
      String partnumber = args[3];
      File attachment = new File(args[4]);
      String econumber = null;
      if (args.length==6) econumber= args[5];

      HashMap params = new HashMap();

      params.put(AgileSessionFactory.USERNAME, username);
      params.put(AgileSessionFactory.PASSWORD, password);
      AgileSessionFactory factory = AgileSessionFactory.getInstance(url);
      session = factory.createSession(params);
      admin = session.getAdminInstance();

      if (null==econumber) attach(session, partnumber, attachment);
      if (null!=econumber) attach(session, partnumber, econumber, attachment);
    } 
    catch (Exception e) {
      e.printStackTrace(); 
    } 
    finally {
        if (session != null) { session.close(); }
    }
	}
	
	public static void attach(IAgileSession session, String partnumber, File attachment) throws Exception {
	  IItem item = (IItem)session.getObject(IItem.OBJECT_TYPE, partnumber);
	  if (item == null) throw new NameNotFound(partnumber);
	  ITable table = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
	  try { attach(table, attachment); }
	  catch (Exception e) { logg("Exception " + e.getMessage()); }
	}
	
	public static void attach(IAgileSession session, String partnumber, String ecoNumber, File attachment) throws Exception {
	  IItem item = (IItem)session.getObject(IItem.OBJECT_TYPE, partnumber);
	  if (item == null) throw new NameNotFound(partnumber);
	  IChange eco = (IChange)session.getObject(IChange.OBJECT_TYPE, ecoNumber);
	  if (eco== null) throw new NameNotFound(ecoNumber);
	  item.setRevision(eco);
	  ITable table = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
	  try { attach(table, attachment); }
	  catch (Exception e) { logg("Exception " + e.getMessage()); }
	}

	//From Jason Brown
	private static void attach(ITable table, File attachment) throws Exception {
	  logg("Attaching file");
	  String value=null;
	  Object o;
    IRow row=null;
	  IRow returnRow = null;
	  boolean replacedFile = false;
    Iterator it = table.iterator();
	  while (it.hasNext()) {
      row = ((IRow) it.next());
	    o = row.getValue(ItemConstants.ATT_ATTACHMENTS_FILE_NAME);
	    if (null!=o) value = o.toString();
		  logg("Examining " +value);
	    if (value.equalsIgnoreCase(attachment.getName())) {
    	  logg("found match");
        returnRow = row;
        IFileFolder fileFolder = (IFileFolder)returnRow.getReferent();
    	  logg("checking out folder");
	      fileFolder.checkOutEx();
    	  logg("folder checked out ");
	      ITable files = fileFolder.getTable(FileFolderConstants.TABLE_FILES);
	      if (files != null) {
	        IRow fileRow = (IRow)files.iterator().next();
	    	  logg("updating content");
	        fileRow.setValue(FileFolderConstants.ATT_FILES_CONTENT, attachment);
	    	  logg("content updated");
	        replacedFile = true;
	      }
	      if (replacedFile) {
      	  logg("checking in folder");
	        fileFolder.checkIn();
      	  logg("folder checked in ");
	      }
	      else {
      	  logg("canceling check out ");
	        fileFolder.cancelCheckout();
	      }
	    }
	  }
	  if (null==returnRow) returnRow=table.createRow(attachment);

	  IFileFolder pubFileFolder = (IFileFolder)returnRow.getReferent();
	  String pubFileFolderNum = pubFileFolder.getName();
	  pubFileFolder = (IFileFolder)session.getObject(FileFolderConstants.CLASS_FILE_FOLDER_BASE_CLASS, pubFileFolderNum);
	  
	  String currentFFVersion = ""+pubFileFolder.getValue(FileFolderConstants.ATT_TITLE_BLOCK_VERSION);
	  logg("current folder version " + currentFFVersion);
	  try {
	    double d = Double.valueOf(currentFFVersion).doubleValue();
	    int ffv=(int)d;
		  logg("updating folder version to " + ffv);
		  logg("updating folder version to " + ffv);
		  if (null==returnRow) logg("return row is null!!");
      returnRow.setValue(ItemConstants.ATT_ATTACHMENTS_FOLDER_VERSION, new Integer(ffv));
		  logg("folder version updated ");
	  }
	  catch (APIException e) {
	    logg("API Exception " + e.getMessage());
 	    if (null!=e.getRootCause()) logg("API Exception " + e.getRootCause().getMessage());
      throw e;
	  }
	  catch (RuntimeException t) {
      logg("throwable Exception " + t.getClass().getName() +": " + t.getMessage());
      throw t;
	  }
  }
	
  
  private static void logg(String s) throws Exception {
    FileWriter logger = new FileWriter (Names.PATH_SEPARATOR + "agile.log", true);
    logger.write(s + Names.NEW_LINE);
    logger.flush();
    logger.close();
  }
  
  private static void logg(Authentication id, String s) throws Exception {
	  FileWriter logger = new FileWriter(Names.PATH_SEPARATOR + "agile.log", true);
	  logger.write(id.getUsername() + ": " + s + Names.NEW_LINE);
    logger.flush();
    logger.close();
	}
	
}
