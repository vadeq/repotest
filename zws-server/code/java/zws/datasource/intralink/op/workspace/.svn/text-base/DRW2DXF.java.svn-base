package zws.datasource.intralink.op.workspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.util.ZipWriter;

import java.io.File;
import java.io.FileFilter;

public class DRW2DXF extends ProEConverter {  
  protected String getTypeExtention() { return "dxf"; }
  protected String getTargetOutputType() { return "DXF"; } 

  protected void finishExecution() throws Exception {  //+++ make sure to zip up all output files
    FileFilter filter = new  DXFFilter();
    File[] files = workingDir.listFiles(filter);
    ZipWriter zipper = new ZipWriter();
    zipper.setZipFilename(workingDir + Names.PATH_SEPARATOR + "dxf.zip");
    zipper.packageFiles(files);
    File dumpFile = new File(workingDir + Names.PATH_SEPARATOR + "dxf.zip");
    if (!dumpFile.exists()) throw new Exception("Conversion to " + getTypeExtention() + "Failed");
    File outFile = new File(getBinaryOutputPath(), getBinaryFilename());
    dumpFile.renameTo(outFile);
  }


  public class DXFFilter implements FileFilter {
    public DXFFilter(){}
    public boolean accept(File pathname) {
      if (pathname.getName().toLowerCase().endsWith(".dxf")) return true;
      return false;
    }
  }
}
