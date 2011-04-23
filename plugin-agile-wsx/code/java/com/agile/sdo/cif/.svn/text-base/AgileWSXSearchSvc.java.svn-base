package com.agile.sdo.cif;

import zws.data.Metadata;
import zws.qx.QxContext;
import zws.repository.agile.plm.api.AgilePLMAPIRepositoryBase;
import zws.security.Authentication;



import com.agile.plmapi.api.PlmAttributeCriteria;
import com.agile.plmapi.api.PlmData;
import com.agile.plmapi.api.PlmExpressionCriteria;
import com.agile.plmapi.api.PlmFactory;
import com.agile.plmapi.api.PlmObject;
import com.agile.plmapi.api.PlmQuery;
import com.agile.plmapi.api.PlmRequest;
import com.agile.plmapi.api.PlmResponse;
import com.agile.plmapi.api.PlmSession;


/**
 * The Class CifClientUtils.
 */
public class AgileWSXSearchSvc extends AgileWSXSvcBase{
  public AgileWSXSearchSvc() {  }
  public AgileWSXSearchSvc(String strURL, Authentication authID, AgilePLMAPIRepositoryBase rep, QxContext context) {
    super(strURL, authID, rep, context);
  }

  public Metadata searchDocument(String documentSubClass, String criteria, String attrName, String attrValue) throws Exception {
    PlmResponse response = null;
    PlmSession session = getSession();
    PlmQuery documentQuery = PlmFactory.createQuery(documentSubClass);
    PlmAttributeCriteria attributeCriteriaPart = PlmFactory.createQueryAttributeCriteria(documentSubClass, attrName, criteria,attrValue);
    PlmExpressionCriteria criteriaPart = PlmFactory.createQueryExpression(documentSubClass, attributeCriteriaPart);
    documentQuery.setExpression(criteriaPart);
    PlmRequest request = PlmFactory.createRequest();
    request.addQuery(documentQuery);
    response = session.execute("search", request);
    PlmData data = response.getData();
    session.close();
    return getObjectByAttribute(data, attrName, attrValue);
  }
  
  public Metadata findLatest(String objectClass, String number) throws Exception {
      return find(objectClass, number, "LATESTPENDING");
  }
  
  public Metadata find(String objectClass, String number, String revType) throws Exception {
    if(null == objectClass || null == number) return null;
    Metadata metadata = null;
    PlmSession session = getSession();
    PlmObject object = PlmFactory.createObject(objectClass);
    object.setAttributeValue(ATT_PART_NUMBER, number);
    if(null != revType) object.setAttributeValue(ATT_REVISION, revType);
    PlmRequest request = PlmFactory.createRequest();
    request.getData().addObject(object);
    PlmResponse response = session.execute("get-object-detail", request);
    {} //System.out.println("response.getData() " + response.getData());
    metadata = prepareMetadata(getPlmObject(response.getData(),number));
    {} //System.out.println("result metadata" + metadata);
    session.close();
    return metadata;
  }
}
