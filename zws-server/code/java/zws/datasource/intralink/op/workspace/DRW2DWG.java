package zws.datasource.intralink.op.workspace; 

import zws.application.server.datasource.Names;
import zws.datasource.intralink.op.workspace.DRW2DXF.DXFFilter;
import zws.util.ZipWriter;

import java.io.File;
import java.io.FileFilter;

/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class DRW2DWG extends ProEConverter {  
  protected String getTypeExtention() { return "dwg"; }
  protected String getTargetOutputType() { return "DWG"; }


  protected void finishExecution() throws Exception {  //+++ make sure to zip up all output files
    FileFilter filter = new DWGFilter();
    File[] files = workingDir.listFiles(filter);
    ZipWriter zipper = new ZipWriter();
    zipper.setZipFilename(workingDir + Names.PATH_SEPARATOR + "dwg.zip");
    zipper.packageFiles(files);
    File dumpFile = new File(workingDir + Names.PATH_SEPARATOR + "dwg.zip");
    if (!dumpFile.exists()) throw new Exception("Conversion to " + getTypeExtention() + "Failed");
    File outFile = new File(getBinaryOutputPath(), getBinaryFilename());
    dumpFile.renameTo(outFile);
  }


  public class DWGFilter implements FileFilter {
    public DWGFilter(){}
    public boolean accept(File pathname) {
      if (pathname.getName().toLowerCase().endsWith(".dwg")) return true;
      return false;
    }
  }

}
