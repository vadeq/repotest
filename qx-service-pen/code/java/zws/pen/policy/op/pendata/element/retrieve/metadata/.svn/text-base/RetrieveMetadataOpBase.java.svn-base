package zws.pen.policy.op.pendata.element.retrieve.metadata;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.application.Names;
import zws.data.Metadata;
import zws.data.MetadataSubComponent;
import zws.data.MetadataSubComponentBase;
import zws.exception.FailedToRetrieveMetadata;
import zws.origin.Origin;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.security.Authentication;
import zws.service.pen.PENDataElement;
//impoer zws.util.Logwriter;
import java.util.Collection;
import java.util.Iterator;

/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public abstract class RetrieveMetadataOpBase extends PENDataOpBase {

  //returns collection of metatdate
  public abstract Collection retrieveData(Repository sourceRepository, Origin sourceOrigin, Authentication id) throws Exception;

  /**
   * @throws Exception Exception
   * @see zws.pen.policy.old.op.PENDataOpBase#execute()
   */
  public void execute() throws Exception {
    {}//Logwriter.printOnConsole(this, "execute");
    getQxCtx().set(Names.SELECT_ATTRIBUTES, this.getSelectAttributes());    
    Metadata sourceData = null;
    Repository sourceRepository = getPenPolicy().getSourceRepository();
    Collection originList = getOriginsToPublish();
    if(null == originList) return;
    Iterator itr = originList.iterator();
    Origin origin = null;
    Collection dataList;
    Iterator i;
    PENDataElement penDataElement;
    while(itr.hasNext()) {
      origin = (Origin) itr.next();
      dataList = retrieveData(sourceRepository, origin, getAuthentication());
      if (null==dataList || dataList.isEmpty()) {
        //re-try retrieval
        RecorderUtil.logActivity(getQxCtx(), origin.getName(), "Re-try Metadata Retrieval",  "Initial try returned null or empty collection");        
        dataList = retrieveData(sourceRepository, origin, getAuthentication());
        if (null==dataList || dataList.isEmpty()) throw new FailedToRetrieveMetadata(origin); 
      }
      i= dataList.iterator();
      while (i.hasNext()) {
        sourceData = (Metadata) i.next();
        populateRefMap(sourceData);
      }
      penDataElement = lookupPENDataElement(origin.getName());
      if (null!=penDataElement) penDataElement.getStatusElement().setItemStatus(STAT_ITEM_IS_DESIGN_ROOT, TRUE);
      RecorderUtil.logActivity(getQxCtx(), origin.getName(), "Retrieved Metadata successfully",  sourceData.toString());      
    }
  }

  /**
   * @param parent parent data
   * @param penRefMap reference map
   */
  private void populateRefMap(Metadata parent) {
    {}//Logwriter.printOnConsole(parent.getName());
    //if (!getPenData().containsReference(parent.getName())) {
     if (!getPenData().containsReference(parent.getName()) ||
         (getPenData().containsReference(parent.getName()) && parent.hasSubComponents())) {
      PENDataElement penDataElement = new PENDataElement();
      penDataElement.setItemName(parent.getName());
      penDataElement.getSourceDataElement().setSourceData(parent);
      penDataElement.getStatusElement().setItemStatus(STAT_ITEM_IS_DESIGN_ROOT, FALSE);
      getPenData().add(penDataElement, true);
    }
    Collection childrenLst = parent.getSubComponents();
    if (null != childrenLst) {
      Iterator itr = childrenLst.iterator();
      MetadataSubComponent temp = null;
      while (itr.hasNext()) {
        temp = (MetadataSubComponent) itr.next();
        populateRefMap(temp);
      }
    }
  }

  protected void keepOnlyFirstLevel(Metadata bill) {
    if (!bill.hasSubComponents()) return;
    Iterator i = bill.getSubComponents().iterator();
    MetadataSubComponentBase kid;
    while (i.hasNext()) {
      kid = (MetadataSubComponentBase) i.next();
      kid.getMetadataBase().setSubcomponents(null);
    }    
  }
  
  public String getSelectAttributes() {return selectAttributes; }
  public void setSelectAttributes(String s) {selectAttributes = s;}
  private String selectAttributes = null;  
}
