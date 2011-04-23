package zws.pen.policy.op.pendata.element.publish;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.application.Names;
import zws.application.Properties;
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.origin.Origin;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.target.RepositoryMetadataTarget;
import zws.repository.target.RepositoryStructureTarget;
import zws.service.file.depot.FileDepotClient;
import zws.service.pen.StatusElement;
import zws.service.pen.TxDataElement;
import zws.util.FileNameUtil;
import zws.util.RemotePackage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CreateStructureOP extends PENDataOpBase{
  public void execute() throws Exception {
    {}//Logwriter.printOnConsole(this,"process","start");

    //set the dirty flag on each item:
    Iterator pen = getPenData().materializeIterator();
    StatusElement statusElement = null;
    String source;
    boolean createStatus = false;
    boolean isdirty;
    while (pen.hasNext()) {
      source = (String) pen.next();
      statusElement = lookupStatusElement(source);
      isdirty = TRUE.equalsIgnoreCase(statusElement.getItemStatus(ACTION_ITEM_NEEDS_TO_BE_CREATED));
      isdirty = createStatus || TRUE.equalsIgnoreCase(statusElement.getItemStatus(ACTION_METADATA_NEEDS_TO_BE_UPDATED));
      if (isdirty) lookupTxMetadata(source).set(DIRTY_FLAG, TRUE);
      else lookupTxMetadata(source).set(DIRTY_FLAG, FALSE);
    }

    Collection originsToPublish = this.getOriginsToPublish();
    if(null ==originsToPublish) throw new Exception("No origins to publish");
    Origin origin = null;
    Metadata structureData = null;
    Repository targetRepository = null;
    RepositoryMetadataTarget target = null;
    RepositoryStructureTarget structureTarget = null;
    loadAttachments();
    Iterator itr = originsToPublish.iterator();
    while(itr.hasNext()) {
      origin = (Origin) itr.next();
      statusElement = lookupStatusElement(origin.getName());
      createStatus = TRUE.equalsIgnoreCase(statusElement.getItemStatus(ACTION_ITEM_NEEDS_TO_BE_CREATED));
      createStatus = createStatus || TRUE.equalsIgnoreCase(statusElement.getItemStatus(ACTION_METADATA_NEEDS_TO_BE_UPDATED));
      if(!createStatus) continue;
      structureData = getPenData().retrieveTxStructure(origin.getName());
      boolean isNew = false;
      isNew = OPT_NEW_ITEM.equalsIgnoreCase(statusElement.getItemStatus(STAT_SYNC_STATUS));
      structureData.set("isNew", new Boolean(isNew).toString());
      targetRepository = getPenPolicy().getTargetRepository();
      target = targetRepository.materializeMetadataTarget();
      structureTarget = target.materializeStructureTarget();
      //bill  = structureTarget.createAndStructureBill(getQxCtx(), new BillOfMaterials(structureData), getAuthentication());
      structureTarget.createAndStructureBill(getQxCtx(), new BillOfMaterials(structureData), getAuthentication());
      //updateTargetOrigins(bill);
      loadTargetSynchronizationOrigin();
    }

  }

  private void loadAttachments() throws Exception{
    Metadata penItemData = null;
    String fileName = null;
    String itemName = null;
    File penWorkArea = null;
    String penDirName = "create-structure-" + nextCount();
    File penDir = new File(Properties.get(Names.TEMP_DIR)+ File.separator + penDirName);
    File penFileDepotArea = new File(penDir, "file-depot");
    Iterator penItr = getPenData().materializeIterator();
    while(penItr.hasNext()) {
      penWorkArea = new File(penDir, "fetch-"+ nextCount());
      itemName = (String) penItr.next();
      penItemData = this.lookupTxMetadata(itemName);
      fileName = getRemoteFile(itemName,penWorkArea,penFileDepotArea);
      penItemData.set("attachment", fileName);
      //penItemData.set("attachment", "C:\\test.txt");
    }
  }

  private String getRemoteFile(String itemName, File penWorkArea,File penFileDepotArea) throws Exception {
    RemotePackage remoteFile= null;
    FileDepotClient depotClient = null;
    File file= null;
    TxDataElement txDataElement = lookupTxDataElement(itemName);
    ArrayList binaries = txDataElement.getBinaryFiles();
    if(null == binaries || binaries.size() == 0 ) return null;
    remoteFile= (RemotePackage) binaries.get(0);
    depotClient = FileDepotClient.materializeClient(Properties.get(Names.FILE_DEPOT_HOSTNAME), penFileDepotArea.getAbsolutePath());
    file = depotClient.retrieve(remoteFile, penWorkArea.getAbsoluteFile());
    return file.getAbsolutePath();
  }

  private void loadTargetSynchronizationOrigin() throws Exception{
    String srcItemName = null;
    String txItemName = null;
    Metadata txData = null;
    Metadata targetData = null;
    StatusElement statusElement =null;
    String syncStatus=null;
    Origin targetOrigin = null;
    boolean isSynchronized=false;
    Iterator itr = getPenData().materializeIterator();
    String suffix=null;
    while(itr.hasNext()) {
      srcItemName = (String) itr.next();
      statusElement = lookupStatusElement(srcItemName);
      syncStatus=statusElement.getItemStatus(STAT_SYNC_STATUS);
      if (null==syncStatus) syncStatus = "";
      isSynchronized = (OPT_SYNCHRONIZED.equalsIgnoreCase(syncStatus));
      if (isSynchronized) continue; //no need to load target
      txData = lookupTxMetadata(srcItemName);
      txItemName = txData.get("number");
      suffix = FileNameUtil.getFileNameExtension(txItemName);

      if (null==suffix || "".equals(suffix.trim())) {
        txItemName = txData.get("CADName");
        suffix = FileNameUtil.getFileNameExtension(txItemName);
      }

      if (null!=suffix && !"".equals(suffix.trim())) suffix = "_" + suffix.toCharArray()[0];
      txItemName = FileNameUtil.getBaseName(txItemName) + suffix;
      txItemName = txItemName.toUpperCase();
      targetData = findInTarget("MEP_"+txItemName);
      if (null==targetData) targetData = findInTarget("MEU_"+txItemName);
      if (null!=targetData)
        targetOrigin = targetData.getOrigin();
        if (null!=targetOrigin) {
          statusElement.setItemStatus(DATA_TARGET_ORIGIN, targetOrigin.toString());
          RecorderUtil.logActivity(getQxCtx(), "Target Item created",  targetOrigin.getName());
        }
    }
  }


  private Metadata findInTarget(String name) throws Exception {
    Repository repository = getPenPolicy().getTargetRepository();
    RepositoryMetadataSource source = repository .materializeMetadataSource();
    Metadata data=null;
    try {
      data = source.findLatest(getQxCtx(), name, getAuthentication());
      return data;
    }
    catch (Exception e) { getPenData().recordErrorMessage(e.getMessage()); }
    //retry
    try {
      data = source.findLatest(getQxCtx(), name, getAuthentication());
      return data;
    }
    catch (Exception e) { getPenData().recordErrorMessage(e.getMessage()); }
    //this is important, retry again!
    try {
      data = source.findLatest(getQxCtx(), name, getAuthentication());
      return data;
    }
    catch (Exception e) { getPenData().recordErrorMessage(e.getMessage()); }
    return null;
  }

  private static final String DIRTY_FLAG ="dirtyFlag";
}

