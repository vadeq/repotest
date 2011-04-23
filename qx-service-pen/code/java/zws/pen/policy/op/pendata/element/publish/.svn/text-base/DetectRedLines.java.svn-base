package zws.pen.policy.op.pendata.element.publish;

/*
 * DesignState - Design Compression Technology @author: ptoleti @version: 1.0 Created on Aug 16, 2007 11:40:55 AM Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.application.Names;
import zws.data.Metadata;
import zws.data.MetadataSubComponentBase;
import zws.origin.Origin;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.qx.QxContext;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;
import zws.service.pen.PENDataElement;
import zws.service.pen.SourceDataElement;
import zws.service.pen.TxDataElement;
import zws.service.synchronization.qx.SynchronizationClient;
import zws.service.synchronization.qx.SynchronizationService;
//import zws.util.{}//Logwriter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class DetectRedLines extends PENDataOpBase {
  public void execute() throws Exception {
    {}//Logwriter.printOnConsole(this, "execute", "start");

    //0. all parts must already be created in agile

    //1. For each item, load target data(if necessary)
    //1.1 For each item, load target first level bom(if necessary)
    //2. For each item, compare xfer subcomponents(ref) to target subcomponent(actual)
    //3. For each element populate redline element
    //4. For each element - execute redlining
    //   - utility - lookupTxElement(name);

    /*  
      > add targetReferenceTable(Map[targetname, PENDataElement])
      - load target data op will populate this map

      > add redline element
        - collection of added subcomponents (use target-ref-name)
        - collection of deleted subcomponents (use target-ref-name)
        - collection of modified subcomponents (use target-ref-name)

      > modify add-affected-items/update to execute redlining

      > add redline tag
        - <redline/>
     */

    loadTargetStructure();

    String name = null;
    Collection txSubComponents = null;
    Collection targetSubComponents = null;
    Iterator itr = getPenData().materializeIterator();
    while (itr.hasNext()) {
      name = (String) itr.next();
      txSubComponents = lookupTxDataElement(name).getSubComponents();
      targetSubComponents = lookupTargetDataElement(name).getSubComponents();
      HashMap txMap = constructSubComponetMap(txSubComponents);
      HashMap targetMap = constructSubComponetMap(targetSubComponents);
      //srcMap = transformToTargetNames(srcMap);
      detectNewElements(name,txMap, targetMap);
      detectRemovedElements(name,txMap, targetMap);
      detectModifiedElements(name,txMap, targetMap);
    }
    
  }
  private void loadTargetStructure() throws Exception {
    Iterator itr = getPenData().getReferenceTableCopy().keySet().iterator();
    String itemName = null;
    while (itr.hasNext()) {
      itemName = (String) itr.next();
      loadTargetMetadata(itemName);
      loadFirstlevelTargetSubComponents(itemName);
    }
  }  
  


  private void loadTargetMetadata(String itemName) throws Exception {
    Metadata targetData = null;
    targetData = lookupTargetMetadata(itemName);
    if(null != targetData) return;
    Repository targetRepository = getPenPolicy().getTargetRepository();
    Collection c = new Vector();
    RepositoryMetadataSource metadataSource = targetRepository.materializeMetadataSource();
    // lookup last previously synchronized origin to find the target data
    String elementName = lookupTxMetadata(itemName).getName();
    targetData = metadataSource.findLatest(getQxCtx(), elementName, getAuthentication());
    lookupTargetDataElement(itemName).setTargetData(targetData);
}
  
  private void loadFirstlevelTargetSubComponents(String itemName) throws Exception {
    Repository targetRepository = getPenPolicy().getTargetRepository();
    Metadata targetData = lookupTargetMetadata(itemName);
    if(null == targetData) return;
    RepositoryStructureSource bomSource = targetRepository.materializeStructureSource();
    Collection deps = bomSource.reportFirstLevelDependencies(getQxCtx(),targetData.getOrigin(), getAuthentication());
    Iterator itr = deps.iterator();
    while(itr.hasNext()) {
      Metadata firstLevelItem = (Metadata) itr.next();
      if(targetData.hasSubComponent(firstLevelItem.getName())) continue; 
      MetadataSubComponentBase child = new MetadataSubComponentBase(firstLevelItem);
      targetData.addSubComponent(child);
    }
  }

  private void detectNewElements(String parentName, HashMap txSubComponents, HashMap targetSubComponents) throws Exception {
    String txItemName = null;
    String syncStatus = null;
   // String refKeyName = null;
    this.getPenData();
    Iterator srcItr = txSubComponents.keySet().iterator();
    while (srcItr.hasNext()) {
      txItemName = (String) srcItr.next();
      syncStatus = getSyncStatusFromTarget(txItemName);
      //refKeyName = this.getSrcKey(parentName);
      /*if (!OPT_MODIFIED.equalsIgnoreCase(syncStatus) && 
          (OPT_NEW_ITEM.equalsIgnoreCase(syncStatus) ||
          !targetSubComponents.containsKey(txItemName))) {*/
      if (!targetSubComponents.containsKey(txItemName)) {      
        // srcItemName is deleted
        if(null != lookupPENDataElement(parentName)) {
          lookupRedlineElement(parentName).redlineAdd(txItemName);
          RecorderUtil.logActivity(getQxCtx(), "Redline add",  txItemName);
        }
      }
    }
  }

  private void detectRemovedElements(String parentName,HashMap txSubComponents, HashMap targetSubComponents) throws Exception {
    String targetItemName = null;
    //String refKeyName = null;
    PENDataElement p = null;
    Iterator targetItr = targetSubComponents.keySet().iterator();
    while (targetItr.hasNext()) {
      targetItemName = (String) targetItr.next();
      //refKeyName = this.getSrcKey(parentName);
      if (!txSubComponents.containsKey(targetItemName)) {
        // targetItemName is added
        p = lookupPENDataElement(parentName);
        if(null != p) {
        p.redlineDelete(targetItemName);
        RecorderUtil.logActivity(getQxCtx(), "Redline delete",  targetItemName);
        }
      }
    }
  }

  private void detectModifiedElements(String parentName, HashMap txSubComponents, HashMap targetSubComponents) throws Exception {
    String txItemName = null;
    String syncStatus = null;
    PENDataElement pElement = null;
    SourceDataElement srcDataElement = null;
    Metadata srcData = null;
    QxContext bomAttributes = null;
    String txFindNum = null;
    String tgtFindNum = null;    
    //String refKeyName = null;
    MetadataSubComponentBase txSubComponent = null;
    MetadataSubComponentBase targetSubComponent = null;
    TxDataElement txDataElement = lookupTxDataElement(parentName);    
    Iterator srcItr = txSubComponents.keySet().iterator();
    while (srcItr.hasNext()) {
      txItemName = (String) srcItr.next();
      if (targetSubComponents.containsKey(txItemName)) {
        txSubComponent = (MetadataSubComponentBase) txSubComponents.get(txItemName);
        targetSubComponent = (MetadataSubComponentBase) targetSubComponents.get(txItemName);        

        // get the source data using the txItemName and retieve "Find number" from bom attributes 
        pElement = getPenData().lookupPENDataElementByTargetName(txItemName);
        srcDataElement = pElement.getSourceDataElement();
        srcData = srcDataElement.getSourceData(); 
        bomAttributes = txDataElement.getBOMAttributes(srcData.getName());
        txFindNum = bomAttributes.get(Names.FIND_NUMBER);
        if(null==txFindNum) txFindNum="";
        tgtFindNum = targetSubComponent.get(Names.METADATA_FIND_NUMBER);
        syncStatus = getSyncStatusFromTarget(txItemName);

        if ((OPT_MODIFIED.equalsIgnoreCase(syncStatus) ||
            (OPT_NEW_ITEM.equalsIgnoreCase(syncStatus) && txSubComponent.getQuantity() != targetSubComponent.getQuantity())) ||
            (OPT_NEW_ITEM.equalsIgnoreCase(syncStatus) && !txFindNum.equalsIgnoreCase(tgtFindNum))) {
          if(null != lookupPENDataElement(parentName)) {
          lookupRedlineElement(parentName).redlineModify(txItemName);
          RecorderUtil.logActivity(getQxCtx(), "Redline modify",  txItemName);
          }
        }
      }
    }
  }
  

  private String getSyncStatusFromTarget(String targetName) {
    String syncStatus = null;
    PENDataElement penElement = getPenData().lookupPENDataElementByTargetName(targetName);
    if(null == penElement) return null;
    syncStatus = penElement.getStatusElement().getItemStatus(this.STAT_SYNC_STATUS);
    return syncStatus;
  }

  private HashMap constructSubComponetMap(Collection subComponents) {
    MetadataSubComponentBase subComponent = null;
    HashMap componentMap = new HashMap();
    if (null==subComponents) return componentMap;
    Iterator itr = subComponents.iterator();
    while (itr.hasNext()) {
      subComponent = (MetadataSubComponentBase) itr.next();
      componentMap.put(subComponent.getName(), subComponent);
    }
    return componentMap;
  }

  private Origin getSyncOrigin(Origin origin, String domainName, String serverName, String repositoryName) throws Exception{
    // get target item name from the sync log
    SynchronizationService r = SynchronizationClient.getClient();
    Origin syncOrigin = r.findSynchronization(origin, domainName, serverName, repositoryName);
    {}//Logwriter.printOnConsole("getSyncOrigin " + syncOrigin);
    return syncOrigin;
  }
  
}

