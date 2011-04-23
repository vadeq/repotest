package zws.repository.ilink3.qx.client.op.xfer;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.repository.ilink3.qx.client.op.IntralinkOpBase;
import zws.util.DeleteFile;
//import zws.util.{}//Logwriter;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class IntralinkXferOp extends IntralinkOpBase {

  protected boolean usePersonalWorkspace() { return usingPersonalWorkspace; }
  public void setUsingPersonalWorkspace(boolean b) { usingPersonalWorkspace=b; }

  public File getLDBLocation() {
   //if (ldbLocation!=null) return ldbLocation;
   //if (usePersonalWorkspace()) ldbLocation = getIlink3Client().getPersonalLDBLocation(getUsername());
   //else ldbLocation = getIlink3Client().getTransitoryLDBLocation(getUsername(), getWorkspaceName());
   return ldbLocation;
   /*
   String sep = Names.PATH_SEPARATOR;
   String user = getUsername();
   String repository = getRepositoryName();
   String ws = getWorkspaceName();
   String userspaces = Properties.get(Names.USER_SPACE);
   if (null==userspaces || "".equals(userspaces))
     userspaces = Properties.get(Names.DATA_DIR) + sep + "space" + sep + "user";
   String ldbPath = userspaces + sep + user + sep + "workspace" + sep + repository;
   if (usePersonalWorkspace()) ldbPath += sep + "personal";
   else ldbPath += sep + "transitory";
   ldbPath += sep + ws;
   File ldb = new File(ldbPath);
   ldbLocation=ldb;
   File dotProI = new File(ldbPath, ".proi");
   if (!dotProI.exists()) dotProI.mkdirs();
   return ldbLocation;
   */
  }

  protected void resetLDB() throws Exception {
    //clear previous workspace
    File dotProI = new File(getLDBLocation(), ".proi");
    if (dotProI.list().length == 0) return;
    {}//Logwriter.printOnConsole("Deleting previous workspace at " + getLDBLocation().getAbsolutePath());
    DeleteFile deleter = new DeleteFile();
    deleter.setDeleteIfNotEmpty(true);
    deleter.setFile(getLDBLocation());
    deleter.execute();
    if (Boolean.FALSE == (Boolean)deleter.getResult()) {}//Logwriter.printOnConsole("Could not delete LDB dir: " + getLDBLocation().getAbsolutePath());
    ldbLocation = null; //reset ldbLocation
  }

  protected void deleteLDB() {
   DeleteFile deleter = new DeleteFile();
   if (!getLDBLocation().exists()) return;
   deleter.setDeleteIfNotEmpty(true);
   try {
     //delete workspace dir
     deleter.setFile(getLDBLocation());
     deleter.execute();
   }
   catch(Exception e) { e.printStackTrace(); }
  }

  protected Map findWriteableAttributes(Map attributes) {
    String field=null;
    boolean fieldIsWriteable;
    Map writeableFields = new HashMap();
    Iterator i = attributes.keySet().iterator();
    while (i.hasNext()) {
      fieldIsWriteable = true;
      field = i.next().toString();
      if (field.equalsIgnoreCase("branch")) fieldIsWriteable = false;
      else if (field.equalsIgnoreCase("generic")) fieldIsWriteable = false;
      else if (field.equalsIgnoreCase("version")) fieldIsWriteable = false;
      else if (field.equalsIgnoreCase("created-on")) fieldIsWriteable = false;
      else if (field.equalsIgnoreCase("instance")) fieldIsWriteable = false;
      else if (field.equalsIgnoreCase("origin")) fieldIsWriteable = false;
      if (fieldIsWriteable) writeableFields.put(field, attributes.get(field));
    }
    return writeableFields;
  }

  public String getWorkspaceName() { return workspaceName; }
  public void setWorkspaceName(String s) { workspaceName=s; }

  protected File ldbLocation=null;
  private String workspaceName=null;
  private boolean usingPersonalWorkspace=false;
}
