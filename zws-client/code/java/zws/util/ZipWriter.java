package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.op.OpBase;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipWriter extends OpBase {

  public void execute() throws Exception {
    if (source.isDirectory()) packageDirectory(source);
    else if (source.isFile()) packageFile(source);
    setResult(getZipFilename());
  }

  public void packageDirectory(File f) throws Exception {
    FileOutputStream outputFile =new FileOutputStream(getZipFilename());
    ZipOutputStream zipFile = new ZipOutputStream(outputFile);
    packageDirectory(null, f, zipFile);
    zipFile.close();
    outputFile.close();
  }
  
  public void packageDirectory(String path, File f, ZipOutputStream zipFile) throws Exception {
    String folder;
    File[] files = f.listFiles();
    int idx=0;
    while (idx < files.length) {
      if (files[idx].isFile()) writeZipEntry(path, files[idx], zipFile);
      else if (files[idx].isDirectory()) {
        folder = path;
        if (null==folder) folder = files[idx].getName();
        else folder += Names.PATH_SEPARATOR + files[idx].getName();
        packageDirectory(folder, files[idx], zipFile);
      }
      idx++;
    }
  }
  
  /*

  public void packageDirectory(File f) throws Exception {
    int idx =0;
    File[] files = f.listFiles();
    FileOutputStream outputFile =new FileOutputStream(getZipFilename());
    ZipOutputStream zipFile = new ZipOutputStream(outputFile);
    while (idx < files.length) writeZipEntry(files[idx++], zipFile);
    zipFile.close();
    outputFile.close();
  }
*/
  
  public void packageFile(File f) throws Exception {
    FileOutputStream outputFile =new FileOutputStream(getZipFilename());
    ZipOutputStream zipFile = new ZipOutputStream(outputFile);
    writeZipEntry(null, f, zipFile);
    zipFile.close();
    outputFile.close();
  }

  public void packageFiles(File[] f) throws Exception {
    FileOutputStream outputFile =new FileOutputStream(getZipFilename());
    ZipOutputStream zipFile = new ZipOutputStream(outputFile);
    for (int i=0; i<f.length; i++) writeZipEntry(null, f[i], zipFile);
    zipFile.close();
    outputFile.close();
  }

  private void writeZipEntry(String path, File f, ZipOutputStream zipFile) throws Exception {
    int idx=0;
    int offset=0;
    int data=-1;
    String name;
    if (null==path) name = f.getName();
    else name = path+Names.PATH_SEPARATOR+f.getName();
    ZipEntry e = new ZipEntry(name);
    zipFile.putNextEntry(e);
    BufferedInputStream iStream= new BufferedInputStream(new FileInputStream(f.getAbsoluteFile()));
    data = iStream.read();
    while(data>-1) {
      zipFile.write(data);
      data = iStream.read();
    }
    zipFile.closeEntry();
  }

  public File getSource() { return source; }
  public void setSource(File f) {source=f; }
  public String getZipFilename() { return zipFilename; }
  public void setZipFilename(String s) { zipFilename = s; }

  private File source = null;
  private String zipFilename=null;

  private static int DATA_BUFFER_SIZE=1000;
}
