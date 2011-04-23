package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 17, 2004, 11:05 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.exception.NotADirectory;
import zws.op.OpBase;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipReader extends OpBase {

  public void execute() throws Exception {
    if (getExportDirectory().exists() && getExportDirectory().isFile()) throw new NotADirectory(getExportDirectory());
    if (!getExportDirectory().exists()) getExportDirectory().mkdirs();
    unpack(getZipFile(), getExportDirectory());
    setResult(getExportDirectory());
  }

  public void unpack(File zipFile, File toDirectory) throws Exception {
    ZipEntry entry ;
    ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile));
    while ((entry=zin.getNextEntry()) != null ) extract(zin, entry, toDirectory);
    zin.close();
  }
  
  private void extract(ZipInputStream zin, ZipEntry entry, File root) throws FileNotFoundException, IOException {
    File out = new File (root.getAbsolutePath() + Names.PATH_SEPARATOR + entry.getName());
    FileUtil.save(zin, out, false);
  }
  
  public File getZipFile() { return zipFile; }
  public void setZipFile(File f) {zipFile=f; }
  public File getExportDirectory() { return exportDirectory; }
  public void setExportDirectory(File f) { exportDirectory=f; }

  private File zipFile = null;
  private File exportDirectory=null;
  private static int DATA_BUFFER_SIZE=1000;
}
