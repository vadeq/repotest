package com.zws.functor.processor.action.file;

import com.zws.application.Constants;
import com.zws.functor.processor.action.Action;
import com.zws.util.FileNameUtil;

import java.io.File;
import java.io.FileNotFoundException;

public class Rename extends Action {
  public void execute() throws Exception  {
    String filename=null, toName=null;
    if (null!= fromFilename) filename=fromFilename;
    else if (null != fromFilenameMetadata) filename= getDocument().get(fromFilenameMetadata);

    if (null!=fromType) filename = FileNameUtil.convertType(filename, fromType);
    if (null!= fromPath) filename= fromPath + Constants.FILE_SEPARATOR + filename;
    else if (null!= fromPathMetadata) filename= getDocument().get(fromPathMetadata) + Constants.FILE_SEPARATOR + filename;
    File from = new File (filename);

    if (null!=toFilename) toName = toFilename;
    else if (null!=toFilenameMetadata) toName = getDocument().get(toFilenameMetadata);

    if (null!=toType) toName = FileNameUtil.convertType(toName, toType);
    if (null!= toPathMetadata) toName= getDocument().get(toPathMetadata) + Constants.FILE_SEPARATOR + toName;
    else if (null!= toPath) toName= toPath + Constants.FILE_SEPARATOR + toName;
    else toName= from.getParentFile().getAbsolutePath() + Constants.FILE_SEPARATOR + toName;

    File to = new File(toName);
    if (!from.exists()) throw new FileNotFoundException(from.getAbsolutePath());
    from.renameTo(to);
  }

  public String getFromPath() { return fromPath; }
  public void setFromPath(String s) { fromPath=s; }
  public String getFromPathMetadata() { return fromPathMetadata; }
  public void setFromPathMetadata(String s) { fromPathMetadata=s; }

  public String getFromFilename() { return fromFilename; }
  public void setFromFilename(String s) { fromFilename=s; }
  public String getFromFilenameMetadata() { return fromFilenameMetadata; }
  public void setFromFilenameMetadata(String s) { fromFilenameMetadata=s; }


  public String getToPath() { return toPath; }
  public void setToPath(String s) { toPath=s; }
  public String getToPathMetadata() { return toPathMetadata; }
  public void setToPathMetadata(String s) { toPathMetadata=s; }

  public String getToFilename() { return toFilename; }
  public void setToFilename(String s) { toFilename=s; }
  public String getToFilenameMetadata() { return toFilenameMetadata; }
  public void setToFilenameMetadata(String s) { toFilenameMetadata=s; }

  public String getFromType() { return fromType; }
  public void setFromType(String s) { fromType=s; }
  public String getToType() { return toType; }
  public void setToType(String s) { toType=s; }

  private String fromPath=null;
  private String fromPathMetadata=null;
  private String toPath=null;
  private String toPathMetadata=null;

  private String fromFilename=null;
  private String fromFilenameMetadata=null;

  private String toFilename=null;
  private String toFilenameMetadata=null;

  private String fromType=null;
  private String toType=null;
}
