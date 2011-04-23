package zws.pen.policy.op.pendata.element.publish;

/*
 * DesignState - Design Compression Technology @author: ptoleti @version: 1.0 Created on Aug 16, 2007 11:40:55 AM Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.Server;
import zws.data.Metadata;
import zws.data.MetadataSubComponentBase;
import zws.origin.Origin;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;
import zws.service.pen.PENDataElement;
import zws.service.synchronization.qx.SynchronizationClient;
import zws.service.synchronization.qx.SynchronizationService;
//import zws.util.{}//Logwriter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class DetectDirtyFlags extends PENDataOpBase {
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
      HashMap srcMap = constructSubComponetMap(txSubComponents);
      HashMap targetMap = constructSubComponetMap(targetSubComponents);
      //srcMap = transformToTargetNames(srcMap);
      detectNewElements(name,srcMap, targetMap);
      detectRemovedElements(name,srcMap, targetMap);
      detectModifiedElements(name,srcMap, targetMap);
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

  private void detectNewElements(String parentName, HashMap srcSubComponents, HashMap targetSubComponents) throws Exception {
    String srcItemName = null;
   // String refKeyName = null;
    Iterator srcItr = srcSubComponents.keySet().iterator();
    while (srcItr.hasNext()) {
      srcItemName = (String) srcItr.next();
      //refKeyName = this.getSrcKey(parentName);
      if (!targetSubComponents.containsKey(srcItemName)) {
        // srcItemName is deleted
        if(null != lookupPENDataElement(parentName)) {
          //lookupRedlineElement(parentName).redlineAdd(srcItemName);
          lookupTxMetadata(srcItemName).set(DIRTY_FLAG, TRUE);
          RecorderUtil.logActivity(getQxCtx(), "Dirty flag is TRUE for",  srcItemName);
        }
      }
    }
  }

  private void detectRemovedElements(String parentName,HashMap srcSubComponents, HashMap targetSubComponents) throws Exception {
    String targetItemName = null;
    //String refKeyName = null;
    PENDataElement p = null;
    Iterator targetItr = targetSubComponents.keySet().iterator();
    while (targetItr.hasNext()) {
      targetItemName = (String) targetItr.next();
      //refKeyName = this.getSrcKey(parentName);
      if (!srcSubComponents.containsKey(targetItemName)) {
        // targetItemName is added
        p = lookupPENDataElement(parentName);
        if(null != p) {
        //p.redlineDelete(targetItemName);
          lookupTxMetadata(parentName).set(DIRTY_FLAG, TRUE);
          RecorderUtil.logActivity(getQxCtx(), "Dirty flag is TRUE for",  parentName);
        }
      }
    }
  }

  private void detectModifiedElements(String parentName, HashMap srcSubComponents, HashMap targetSubComponents) throws Exception {
    String srcItemName = null;
    //String refKeyName = null;
    MetadataSubComponentBase srcSubComponent = null;
    MetadataSubComponentBase targetSubComponent = null;
    Iterator srcItr = srcSubComponents.keySet().iterator();
    while (srcItr.hasNext()) {
      srcItemName = (String) srcItr.next();
      if (targetSubComponents.containsKey(srcItemName)) {
        srcSubComponent = (MetadataSubComponentBase) srcSubComponents.get(srcItemName);
        targetSubComponent = (MetadataSubComponentBase) targetSubComponents.get(srcItemName);
        if (srcSubComponent.getQuantity() != targetSubComponent.getQuantity()) {
          if(null != lookupPENDataElement(parentName)) {
            lookupRedlineElement(parentName).redlineModify(srcItemName);
            lookupTxMetadata(parentName).set(DIRTY_FLAG, TRUE);
            RecorderUtil.logActivity(getQxCtx(), "Dirty flag is TRUE for",  parentName);
          }
        }
      }
    }
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

  public static final String DIRTY_FLAG ="dirtyFlag";
}
