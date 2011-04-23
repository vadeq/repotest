package com.zws.functor.util.file;

import com.zws.functor.Functor;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipWriter extends Functor {

  public void execute() throws Exception {
    File f = new File(getSource());
    if (f.isDirectory()) packageDirectory(f);
    else if (f.isFile()) packageFile(f);
    setResult(getZipFileName());
  }

  public void packageDirectory(File f) throws Exception {
    int idx =0;
    File[] files = f.listFiles();
    FileOutputStream outputFile =new FileOutputStream(getZipFileName());
    ZipOutputStream zipFile = new ZipOutputStream(outputFile);
    while (idx < files.length) writeZipEntry(files[idx++], zipFile);
    zipFile.close();
    outputFile.close();
  }

  public void packageFile(File f) throws Exception {
    FileOutputStream outputFile =new FileOutputStream(getZipFileName());
    ZipOutputStream zipFile = new ZipOutputStream(outputFile);
    writeZipEntry(f, zipFile);
    zipFile.close();
    outputFile.close();
  }

  private void writeZipEntry(File f, ZipOutputStream zipFile) throws Exception {
    int idx=0;
    int offset=0;
    int data=-1;
    ZipEntry e = new ZipEntry(f.getName());
    zipFile.putNextEntry(e);
    BufferedInputStream iStream= new BufferedInputStream(new FileInputStream(f.getAbsoluteFile()));
    data = iStream.read();
    while(data>-1) {
      zipFile.write(data);
      data = iStream.read();
    }
    zipFile.closeEntry();
  }

  public String getSource() { return source; }
  public void setSource(String s) {source=s; }
  public String getZipFileName() { return zipFileName; }
  public void setZipFileName(String s) { zipFileName = s; }

  private String source = null;
  private String zipFileName=null;

  private static int DATA_BUFFER_SIZE=1000;
}
