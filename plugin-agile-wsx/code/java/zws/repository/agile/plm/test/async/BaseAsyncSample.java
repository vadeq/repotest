/*
 * $Id: BaseAsyncSample.java,v 34.1 2007/10/05 12:40:34 wendta01 Exp $
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

package zws.repository.agile.plm.test.async;

import com.agile.plmapi.api.PlmData;
import com.agile.plmapi.api.PlmException;
import com.agile.plmapi.api.PlmFactory;
import com.agile.plmapi.api.PlmFutureResponse;
import com.agile.plmapi.api.PlmObject;
import com.agile.plmapi.api.PlmRequest;
import com.agile.plmapi.api.PlmResponse;
import com.agile.plmapi.api.PlmSession;
import com.agile.plmapi.api.om.PlmActionConstants;
import com.agile.plmapi.api.om.PlmDocumentType;

import java.util.Collection;
import java.util.Map;

/**
 * Base class for synchronous mode.
 *
 * @author <a href="mailto:support@agile.com">SWE, Agile Software GmbH</a>
 * @since plmapi20sp0
 */
abstract class BaseAsyncSample extends BaseSample {

   /** Default wait time in millis for reponse thread. */
   private static final long DEFAULT_WAIT = 60000;

   /** Last exception. */
   private PlmException mException = null;

   /** Response. */
   private PlmResponse mResponse = null;

   /**
    * Default constructor.
    *
    * @since plmapi20sp0
    */
   protected BaseAsyncSample() {
      // Nothing
   }

   /**
    * Run method.
    *
    * @throws PlmException if sample failed
    * @since plmapi20sp0
    */
   protected void run() throws PlmException {
      //String baseURL = "http://pwebdev.cisco.com/pls/services";
      String baseURL = "http://pwebdev.cisco.com";
      boolean useClient = false;
      boolean showDialog = false;

      Map params = getLoginParams(baseURL, useClient, showDialog);

      PlmSession session = PlmFactory.getSession();
      session.open(params);

      PlmRequest request = createRequest(session);

      // Do not call any setExecute*() method before to run synchronously
      mResponse = executeAndWait(session, PlmActionConstants.CREATE, request);

      if (null != mResponse) {
         Collection objs = mResponse.getData().getObjects();
         PlmObject document = (PlmObject) objs.iterator().next();
         {} //System.out.println(document.getAttributeValue(PlmDocumentType.ATTRIB_NUMBER));
      }
   }

   /**
    * Executes and waits for response. It calls
    * {@link #executeAsync(PlmSession, String, PlmRequest)} and then
    * {@link #waitForResponse(PlmFutureResponse)}.
    *
    * @param session PlmSession
    * @param operation Operation name
    * @param request PlmRequest
    * @return PlmResponse
    * @throws PlmException if execution failed
    * @since plmapi20sp0
    */
   private PlmResponse executeAndWait(PlmSession session, String operation, PlmRequest request) throws PlmException {
      PlmFutureResponse futureResponse = executeAsync(session, operation, request);
      return waitForResponse(futureResponse);
   }

   /**
    * Executes the asynchronous operation.
    *
    * @param session PlmSession
    * @param operation Operation name
    * @param request PlmRequest
    * @return PlmFutureResponse
    * @throws PlmException if execution failed
    * @since plmapi20sp0
    */
   protected abstract PlmFutureResponse executeAsync(PlmSession session, String operation, PlmRequest request) throws PlmException;

   /**
    * Waits for the response.
    *
    * @param response PlmFutureResponse
    * @return PlmResponse
    * @throws PlmException if waiting failed
    * @since plmapi20sp0
    */
   protected abstract PlmResponse waitForResponse(final PlmFutureResponse response) throws PlmException;

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
      document.setAttributeValue(PlmDocumentType.ATTRIB_NUMBER, "123-456");
      document.setAttributeValue(PlmDocumentType.ATTRIB_DESCRIPTION, "PLM API Sample");
      document.setAttributeValue(PlmDocumentType.ATTRIB_FRAME_ID, "1234");
      PlmRequest request = PlmFactory.createRequest();
      PlmData data = request.getData();
      data.addObject(document);
      return request;
   }

   /**
    * Sets the exception.
    *
    * @param e PlmException
    * @since plmapi20sp0
    */
   protected void setException(PlmException e) {
      synchronized (mException) {
         mException = e;
      }
   }

   /**
    * Returns if an exception occurred.
    *
    * @return Flag if exception occurred
    * @since plmapi20sp0
    */
   protected boolean hasException() {
      synchronized (mException) {
         return null != mException;
      }
   }

   /**
    * Returns the last exception.
    *
    * @return PlmException
    * @since plmapi20sp0
    */
   protected PlmException getException() {
      synchronized (mException) {
         return mException;
      }
   }

   /**
    * Returns the default wait time.
    *
    * @return Default wait time
    * @since plmapi20sp0
    */
   protected static long getDefaultWait() {
      return DEFAULT_WAIT;
   }

   /**
    * Returns the response.
    *
    * @return PlmResponse
    * @since plmapi20sp0
    */
   protected PlmResponse getResponse() {
      return mResponse;
   }

   /**
    * Sets the response.
    *
    * @param response PlmResponse
    * @since plmapi20sp0
    */
   protected void setResponse(PlmResponse response) {
      mResponse = response;
   }

}
