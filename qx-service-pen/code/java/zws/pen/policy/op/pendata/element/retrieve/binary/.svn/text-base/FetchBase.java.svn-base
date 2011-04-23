package zws.pen.policy.op.pendata.element.retrieve.binary;

/*
 * DesignState - Design Compression Technology
 * @author: Emmanuel Ankutse
 * @version: 1.0 Created on Nov 15, 2007
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.data.Metadata;
import zws.data.MetadataBinary;
import zws.origin.Origin;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryBinarySource;
import zws.service.pen.PENDataElement;
////import zws.util.{}//Logwriter;
import zws.util.RemotePackage;

import java.util.Iterator;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public abstract class FetchBase extends PENDataOpBase {

  protected abstract RemotePackage download(RepositoryBinarySource binSource, Origin o) throws Exception ;

  public void execute() throws Exception {
    Metadata sourceData = null;
    Metadata metadataBinary = null;
    Origin fileOrigin = null;
    RemotePackage binary=null;
    String name = getCurrentItem();
    Repository repositoryObj = getPenPolicy().getSourceRepository();
    RepositoryBinarySource binSource = repositoryObj.materializeBinarySource();
    PENDataElement penElement = getPenData().lookupPENDataElement(name);
    sourceData = lookupSrcMetadata(name);
    if (null==sourceData) return;
    if (null != sourceData.getBinaries() && !sourceData.getBinaries().isEmpty()) {
      Iterator binItr = sourceData.getBinaries().iterator();
      while (binItr.hasNext()) {
        metadataBinary = (MetadataBinary) binItr.next();
        fileOrigin = metadataBinary.getOrigin();
        {}//Logwriter.printOnConsole("metadataBinary  " + metadataBinary + " -->fileOrigin  " + fileOrigin);
        binary = download(binSource, fileOrigin);
        if (null != binary)
          penElement.getSourceDataElement().addBinary(binary);
          RecorderUtil.logActivity(getQxCtx(), "Fetch binary",  "Item No:" + name + " binary:"+ binary.getName());
      }
    } else {
      fileOrigin = sourceData.getOrigin();
      {}//Logwriter.printOnConsole("fileOrigin1  " + fileOrigin);
      binary = download(binSource, fileOrigin);
      if (null != binary)      
        penElement.getSourceDataElement().addBinary(binary);
    }
  }
}
