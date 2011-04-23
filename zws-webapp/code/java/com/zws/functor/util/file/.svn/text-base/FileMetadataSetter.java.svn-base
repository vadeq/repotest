package com.zws.functor.util.file;

import com.zws.application.Constants;
import com.zws.domo.document.Document;
import com.zws.functor.Functor;
import com.zws.util.FileNameUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileMetadataSetter extends Functor {

  public void execute(){
    String permissions,path,type,size,lastModifiedDateTime, lastModifiedDate, lastModifiedTime, ext;
    //todo: turn these into token lookups for internationalization
    if (getFile().canWrite()) permissions="read/write";
    else permissions="read only";
    //path=FileNameUtil.escapePath(getFile().getParentFile().getPath());
    path=getFile().getParentFile().getPath();
    type = FileNameUtil.lookupFileType(getFile().getName());
    ext = FileNameUtil.getFileNameExtention(getFile().getName());
    long fileSize= getFile().length();
    if (fileSize < 1000) size = fileSize + " B";
    else if (fileSize < 1024 * 1024) size= Math.round(10*fileSize/1024)/10 +" KB";
    else size= Math.round(10 * fileSize/1024/1024)/10 +" MB";
    SimpleDateFormat year =new SimpleDateFormat("yyyy");
    SimpleDateFormat month =new SimpleDateFormat("MM");
    SimpleDateFormat day =new SimpleDateFormat("dd");
    SimpleDateFormat hour =new SimpleDateFormat("KK");
    SimpleDateFormat min =new SimpleDateFormat("mm");
    SimpleDateFormat sec =new SimpleDateFormat("ss");
    SimpleDateFormat ampm =new SimpleDateFormat("aa");
    Date date = new Date(getFile().lastModified());

    lastModifiedDateTime = month.format(date)+"/"+day.format(date)+"/"+year.format(date)+" "+ hour.format(date) + ":" +min.format(date) +":"+ sec.format(date)+" " + ampm.format(date);
    lastModifiedDate = month.format(date)+"/"+day.format(date)+"/"+year.format(date);
    lastModifiedTime = hour.format(date) + ":" +min.format(date) +":"+ sec.format(date)+" " + ampm.format(date);

    doc.set(Constants.METADATA_PATH,path);
    doc.set(Constants.METADATA_EXTENTION,ext);
    doc.set(Constants.METADATA_TYPE,type);

    doc.set(Constants.METADATA_FILE_PERMISSIONS,permissions);
    doc.set(Constants.METADATA_FILE_SIZE,size);
    doc.set(Constants.METADATA_FILE_DATE_LAST_MODIFIED,lastModifiedDate);
    doc.set(Constants.METADATA_FILE_TIME_LAST_MODIFIED,lastModifiedTime);
    doc.set(Constants.METADATA_FILE_LAST_MODIFIED,lastModifiedDateTime);
  }

  public File getFile() { return file; }
  public void setFile(File f) { file = f; }
  public Document getDocument() { return doc; }
  public void setDocument(Document d) { doc = d; }

  private File file=null;
  private Document doc=null;
}
