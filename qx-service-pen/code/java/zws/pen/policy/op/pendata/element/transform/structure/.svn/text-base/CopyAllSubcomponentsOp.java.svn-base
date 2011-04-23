package zws.pen.policy.op.pendata.element.transform.structure;
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
import zws.qx.QxContext;
import zws.recorder.util.RecorderUtil;
import zws.service.pen.TxDataElement;
//import zws.util.{}//Logwriter;
import zws.pen.policy.op.pendata.PENDataOp;
import java.util.Iterator;
import java.util.Map;

/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class CopyAllSubcomponentsOp extends TransformStructureOpBase{

  public void transformStructure(Metadata sourceData, TxDataElement txDataElement) throws Exception{
    QxContext tempCtx = null;
    RecorderUtil.logActivity(getQxCtx(), "copy sub-components",  sourceData.getName());
    if (null != sourceData && null != sourceData.getSubComponents()) {
      tempCtx = RecorderUtil.startSubProcess(getQxCtx(), sourceData.getName(),"Copy sub-components");
      MetadataSubComponent subComponent = null;
      Iterator objItr = sourceData.getSubComponents().iterator();
      Map bomAttributes=null;
      while(objItr.hasNext()) {
        subComponent = (MetadataSubComponent)objItr.next();
        if(!skip(sourceData, subComponent, txDataElement) &&  include(sourceData, subComponent, txDataElement)) {}
        bomAttributes = doOpsAsAttributes();
        txDataElement.addSubcomponent(subComponent.getName(),subComponent.getQuantity(),bomAttributes);
        RecorderUtil.logActivity(tempCtx, "copy sub-component",  txDataElement.getTxData().getName() + " has " + subComponent.getName());
      }
    }
    if(null != tempCtx)RecorderUtil.endRecProcess(tempCtx, Names.STATUS_COMPLETE);
  }

  private boolean skip(Metadata sourceData, MetadataSubComponent subComponent, TxDataElement txDataElement) {
    return false; //+++ todo evaluate skipSubcomponentOp
  }

  private boolean include(Metadata sourceData, MetadataSubComponent subComponent, TxDataElement txDataElement) {
    return true; //+++ todo evaluate includeSubcomponentOp
  }

  PENDataOp skipSubcomponentOp=null;
  PENDataOp includeSubcomponentOp=null;
}


/*
<copy-all-subcomponents>
  <skip-subcomponents>
    <name-equals "skel.prt"/>
  </skip-subcomponents>
  <include-subcomponents>
    <name-equals "*"/>
  </include-subcomponents>
  <bom-property attribute="Usage Type" value="Std"/>
  <bom-property attribute="Trace Code" value="Null"/>
  <bom-property attribute="CAD Source" value="Intralink"/>
</copy-all-subcomponents>
*/