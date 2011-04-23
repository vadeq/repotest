package zws.pen.policy.op.pendata.element.retrieve.binary;

/*
 * DesignState - Design Compression Technology @author: ptoleti @version: 1.0 Created on Apr 27, 2007 9:58:02 AM Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.data.MetadataBinary;
import zws.origin.Origin;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.repository.Repository;
import zws.repository.source.RepositoryBinarySource;
//impoer zws.util.Logwriter;

import java.io.File;
import java.util.Iterator;

/**
 * The Class UpdateStateAttributeOp.
 * 
 * @author ptoleti
 */
public class RetrieveBinaryOp extends PENDataOpBase {

  /**
   * @throws Exception Exception
   * @see zws.pen.policy.old.op.PENDataOpBase#execute()
   */
  public void execute() throws Exception {
    String name = null;
    String location = null;
    String fileName = null;
    String[] fileNames = null;
    File binFile = null;
    File folder = null;
    Metadata sourceData = null;
    MetadataBinary metadataBinary = null;    
    Origin fileOrigin = null;
    final String outDir = Properties.get(Names.TEMP_DIR) + Names.PATH_SEPARATOR + "bin-";
    try {
      Repository repositoryObj = getPenPolicy().getSourceRepository();
      RepositoryBinarySource binSource = repositoryObj.materializeBinarySource();
      Iterator itr = getPenData().materializeIterator();
      {}//Logwriter.printOnConsole("Execute in GetSourceBinaryOp ");
      // iterate thru ref map and get binary and transform data from source data table.
      while (itr.hasNext()) {
        name = (String) itr.next();
        sourceData = lookupSrcMetadata(name);
        if (null != sourceData) {
          location = outDir + nextLocationCount();
          {}//Logwriter.printOnConsole("location " + location);
          if (null != sourceData.getBinaries() && !sourceData.getBinaries().isEmpty()) {
            Iterator binItr = sourceData.getBinaries().iterator();
            // iterate thru all the binray elements
            while (binItr.hasNext()) {
              metadataBinary = (MetadataBinary) binItr.next();
              fileOrigin = metadataBinary.getOrigin();
              {}//Logwriter.printOnConsole("metadataBinary  " + metadataBinary + " -->fileOrigin  " + fileOrigin);
              //binSource.download(getQxCtx(), fileOrigin, location, this.getAuthentication());
            }
          } else {
            fileOrigin = sourceData.getOrigin();
            {}//Logwriter.printOnConsole("fileOrigin1  " + fileOrigin);
            //binSource.download(getQxCtx(), fileOrigin, location, this.getAuthentication());
          }
          folder = new File(location);
          fileNames = folder.list();
          // get all the files in the the folder and validate
          for (int idx = 0; idx < fileNames.length; idx++) {
            fileName = fileNames[idx];
            {}//Logwriter.printOnConsole("fileName ....." + fileName);
            binFile = new File(location, fileName);
            {}//Logwriter.printOnConsole("bin file is ....." + binFile.getAbsolutePath());
            if (validateFile(binFile)) {
              {}//Logwriter.printOnConsole("Origin    " + fileOrigin + " binFile " + binFile.getName());
 //TODO fix this             lookupSourceDataElement(name).addBinary(binFile);
            } else {
              {}//Logwriter.printOnConsole(binFile + " does not exists");
            }
          }
        } else {
          {}//Logwriter.printOnConsole("Source Data is null in GetSourceBinaryOp");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  private boolean validateFile(File file) {
    boolean validFile = true;
    if (null == file || !file.exists()) {
      validFile = false;
    } else {
      {}//Logwriter.printOnConsole(file.getAbsolutePath());
    }
    return validFile;
  }

 private static synchronized long nextLocationCount() {return locationCount++;}
 private static long locationCount = 0;
}
