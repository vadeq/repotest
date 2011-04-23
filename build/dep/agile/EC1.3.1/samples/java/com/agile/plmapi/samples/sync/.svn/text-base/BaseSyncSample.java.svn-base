/*
 * $Id: BaseSyncSample.java,v 34.1 2007/10/05 12:41:04 wendta01 Exp $
 * 
 * Copyright (c) 2007 by Agile Software GmbH.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of
 * Agile Software GmbH ("Confidential Information").  
 * You shall not disclose such Confidential Information and shall use 
 * it only in accordance with the terms of the license agreement you 
 * entered into with Agile Software.
 */

package com.agile.plmapi.samples.sync;

import com.agile.plmapi.api.PlmConstants;
import com.agile.plmapi.api.PlmData;
import com.agile.plmapi.api.PlmException;
import com.agile.plmapi.api.PlmFactory;
import com.agile.plmapi.api.PlmObject;
import com.agile.plmapi.api.PlmRequest;
import com.agile.plmapi.api.PlmResponse;
import com.agile.plmapi.api.PlmSession;
import com.agile.plmapi.api.om.PlmActionConstants;
import com.agile.plmapi.api.om.PlmDocumentType;
import com.agile.plmapi.samples.api.BaseSample;

import java.util.Collection;
import java.util.Map;

/**
 * Sample for synchronous mode with polling.
 * 
 * @author <a href="mailto:support@agile.com">SWE, Agile Software GmbH</a>
 * @since plmapi20sp0
 */
abstract class BaseSyncSample extends BaseSample {

   /** Response. */
   private PlmResponse mResponse = null;

   /**
    * Default constructor.
    * 
    * @since plmapi20sp0
    */
   protected BaseSyncSample() {
      // Nothing
   }

   /**
    * Run method.
    * 
    * @throws PlmException if sample failed
    * @since plmapi20sp0
    */
   protected void run() throws PlmException {
      String baseURL = "http://linuxwas/Agile";
      boolean useClient = false;
      boolean showDialog = false;

      Map params = getLoginParams(baseURL, useClient, showDialog);

      PlmSession session = PlmFactory.getSession();
      session.open(params);

      PlmRequest request = createRequest(session);

      // Do not call any setExecute*() method before to run synchronously
      mResponse = execute(session, PlmActionConstants.CREATE, request);

      Collection objs = mResponse.getData().getObjects();
      PlmObject document = (PlmObject) objs.iterator().next();
      System.out.println(document.getAttributeValue(PlmDocumentType.ATTRIB_NUMBER));
   }

   /**
    * Executes the operation.
    * 
    * @param session PlmSession
    * @param operation Operation name
    * @param request PlmRequest
    * @return PlmResponse
    * @throws PlmException if execution failed
    * @since plmapi20sp0
    */
   protected abstract PlmResponse execute(PlmSession session, String operation, PlmRequest request) throws PlmException;

   /**
    * Creates a document.
    * 
    * @param session PlmSession
    * @return Document number
    * @throws PlmException if creation failed
    * @since plmapi20sp0
    */
   private PlmRequest createRequest(PlmSession session) throws PlmException {
      PlmObject document = PlmFactory.createObject(PlmDocumentType.OBJECT_TYPE);
      document.setAttributeValue(PlmDocumentType.ATTRIB_NUMBER, PlmConstants.AUTO_NUMBER_VALUE);
      document.setAttributeValue(PlmDocumentType.ATTRIB_DESCRIPTION, "PLM API Sample");
      document.setAttributeValue(PlmDocumentType.ATTRIB_FRAME_ID, "1234");
      PlmRequest request = PlmFactory.createRequest();
      PlmData data = request.getData();
      data.addObject(document);
      return request;
   }

}
