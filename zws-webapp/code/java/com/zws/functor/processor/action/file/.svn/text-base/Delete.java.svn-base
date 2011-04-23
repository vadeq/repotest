package com.zws.functor.processor.action.file;

import com.zws.application.Constants;
import com.zws.functor.processor.action.Action;
import com.zws.util.FileNameUtil;

import java.io.File;

public class Delete extends Action {

  public void execute() throws Exception {
    String file="";
    if (null!= fileNameMetaData) file= getDocument().get(fileNameMetaData);
    else file=fileName;
    if (null!=type) file = FileNameUtil.convertType(file, type);
    if (null!=file) file = Constants.FILE_SEPARATOR + file;
    if (null!= pathMetaData) file= getDocument().get(pathMetaData) + file;
    else file=path + file;
    File f = new File (file);
    com.zws.functor.util.file.Delete deleter = new com.zws.functor.util.file.Delete();
    if (f.exists()){
      deleter.setFile(f);
      deleter.setDeleteIfNotEmpty(getDeleteIfNotEmpty());
      deleter.execute();
    }
  }

  public String getFileName() { return fileName; }
  public void setFileName(String s) { fileName=s; }
  public String getPath() { return path; }
  public void setPath(String s) { path=s; }
  public String getFileNameMetaData() { return fileNameMetaData; }
  public void setFileNameMetaData(String s) { fileNameMetaData=s; }
  public String getPathMetaData() { return pathMetaData; }
  public void setPathMetaData(String s) { pathMetaData=s; }
  public String getType() { return type; }
  public void setType(String s) { type=s; }

  public boolean getDeleteIfNotEmpty() { return deleteIfNotEmpty; }
  public void setDeleteIfNotEmpty(boolean b) { deleteIfNotEmpty=b; }

  private String fileName="";
  private String path="";
  private String fileNameMetaData=null;
  private String pathMetaData=null;
  private String type=null;
  private boolean deleteIfNotEmpty = false;
}
