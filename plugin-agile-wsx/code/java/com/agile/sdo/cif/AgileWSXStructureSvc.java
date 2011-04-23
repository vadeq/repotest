package com.agile.sdo.cif;


import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.data.MetadataSubComponentBase;

import zws.qx.QxContext;
import zws.repository.agile.plm.api.AgilePLMAPIRepositoryBase;
import zws.security.Authentication;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.agile.plmapi.api.PlmAttribute;
import com.agile.plmapi.api.PlmObject;
import com.agile.plmapi.api.PlmRelation;
import com.agile.plmapi.api.PlmRequest;
import com.agile.plmapi.api.PlmResponse;
import com.agile.plmapi.api.PlmSession;


/**
 * The Class CifClientUtils.
 */
public class AgileWSXStructureSvc extends AgileWSXItemSvc {

  public AgileWSXStructureSvc() {}

  public AgileWSXStructureSvc(String strURL, Authentication authID,
      AgilePLMAPIRepositoryBase rep, QxContext context) {
    super(strURL, authID, rep, context);
  }

  public void createStructure(Metadata metadata, String source, Map optionsMap, Map relOptionsMap, Map relAttributes,
                                  boolean mappedflag, boolean asyncFlag, QxContext ctx) throws Exception {
    //Metadata data = null;
    PlmSession session = getSession();
    try {
        //zws.util.{}//Logwriter.printToFile(new File(Properties.get(Names.TEMP_DIR)+Names.PATH_SEPARATOR+metadata.getName()), metadata.toString());
        if(null == metadata)  return; // null;
        PlmRequest request = createRequest(id.getUsername());
        String[] options = getStringArray(optionsMap);
        boolean dirtyflag = true;
        if(null != metadata.get("dirtyFlag")) {
          dirtyflag = new Boolean(metadata.get("dirtyFlag")).booleanValue();
          }
        String[] attributes = getStringArray(metadata.getAttributes());
        PlmObject plmParent = createPLMObject(metadata.getName(), metadata.getName(), source, options, attributes, mappedflag, dirtyflag);
        request.getData().addObject(plmParent);

        constructStructure(request, plmParent, metadata.getSubComponents(), source,optionsMap, relOptionsMap,relAttributes, mappedflag);
        PlmResponse response = releasePlmObject(metadata.getName(), session, request,asyncFlag, ctx);
        //data = getMetadataFromResponse(response);
    } finally {session.close(); }
    //return data;
  }

  public void constructStructure(PlmRequest request, PlmObject plmParent,Collection subComponents, String source,
                                  Map optionsMap, Map relOptionsMap, Map relAttributes, boolean mappedflag)
                                  throws Exception {
    MetadataSubComponentBase subComponent = null;
    String[] attributes;
    PlmObject plmChild = null;
    PlmRelation relation = null;
    int quantity = 0;
    if(null ==relAttributes ) relAttributes= new HashMap();
    //String[] relationAttribs = getStringArray(relAttributes);
    String[] relationOptions = getStringArray(relOptionsMap);
    String[] options = getStringArray(optionsMap);
    boolean isDirty = true;
    if(null == subComponents || subComponents.size() ==0) return;
      Iterator itr = subComponents.iterator();
      while(itr.hasNext()) {
        subComponent = (MetadataSubComponentBase) itr.next();
        //subComponent.set("Qty", subComponent.getQuantity());
        Map attrMap = subComponent.getAttributes();
        quantity = subComponent.getQuantity();
        if(attrMap.containsKey(MetadataSubComponentBase.QUANTITY)) {
          attrMap.remove(MetadataSubComponentBase.QUANTITY); // qty goes on relation not on object
        }
        if(attrMap.containsKey("Qty")) {
          attrMap.remove("Qty"); // qty goes on relation not on object
        }
        attributes = getStringArray(attrMap);

        if(null != subComponent.get("dirtyFlag")) {
          isDirty= new Boolean(subComponent.get("dirtyFlag")).booleanValue();
          }
        plmChild = createPLMObject(subComponent.getName(), subComponent.getName(), source, options, attributes, mappedflag, isDirty);
        /*if(!request.getData().getObjects().contains(plmChild)) {
          request.getData().addObject(plmChild);
        }*/
        constructStructure(request,plmChild , subComponent.getSubComponents(), source,optionsMap,relOptionsMap, relAttributes, mappedflag);

        //relAttributes.put(MetadataSubComponentBase.QUANTITY, quantity+"");
        relAttributes.put("Qty", subComponent.getQuantity()+"");
        //relAttributes.put("BOM.Qty", subComponent.getQuantity()+"");

        relation = createRelation("object-object-relation",plmParent.getKey(), subComponent.getName(),
                                  plmParent, plmChild, source,
                                  relationOptions, relAttributes,mappedflag, true);
        {} //System.out.println(" relation ship created " + relation);
      }
  }


  public PlmRelation createRelation(String relName, String parentKey, String childKey,
                                    PlmObject plmParent, PlmObject plmChild,
                                    String source, String[] options, Map attributes,
                                    boolean mappedflag, boolean dirtyflag) throws Exception {
    {} //System.out.println("creating relation");
    String[] relationAttribs = getStringArray(attributes);
    PlmRelation rel = plmParent.createRelation(relName, plmChild);
    {} //System.out.println("relation created");
    rel.setDirty(dirtyflag);
    rel.setMapped(mappedflag);
    rel.setSource(source);
    rel.setKeyParent(parentKey);
    rel.setKeyChild(childKey);
    if (attributes != null) {
      for (int i = 0; i < relationAttribs.length - 1; i = i + 2) {
        PlmAttribute attr = rel.setAttributeValue(relationAttribs[i], relationAttribs[i + 1]);
        attr.setSource(source);
        attr.setDirty(dirtyflag);
        attr.setMapped(mappedflag);
      }
    }
    if (options != null) {
      for (int i = 0; i < options.length - 1; i = i + 2) {
        rel.setOptionValue(options[i], options[i + 1]);
      }
    }
    {} //System.out.println("creating relation done " + rel.getId());
    return rel;
  }
}
