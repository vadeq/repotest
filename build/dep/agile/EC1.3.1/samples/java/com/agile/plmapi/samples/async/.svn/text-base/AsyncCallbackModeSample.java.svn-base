/*
 * $Id: AsyncCallbackModeSample.java,v 34.1 2007/10/05 12:40:34 wendta01 Exp $
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

package com.agile.plmapi.samples.async;

import com.agile.plmapi.api.PlmException;
import com.agile.plmapi.api.PlmFutureResponse;
import com.agile.plmapi.api.PlmRequest;
import com.agile.plmapi.api.PlmResponse;
import com.agile.plmapi.api.PlmResponseListener;
import com.agile.plmapi.api.PlmSession;
import com.agile.plmapi.api.om.PlmActionConstants;

/**
 * Sample for asynchronous mode with callback listener.
 * 
 * @author <a href="mailto:support@agile.com">SWE, Agile Software GmbH</a>
 * @since plmapi20sp0
 */
public final class AsyncCallbackModeSample extends BaseAsyncSample {

   /**
    * Default constructor.
    * 
    * @since plmapi20sp0
    */
   private AsyncCallbackModeSample() {
      // Nothing
   }

   /**
    * {@inheritDoc}
    * 
    * @since plmapi20sp0
    */
   protected PlmFutureResponse executeAsync(PlmSession session, String operation, PlmRequest request) throws PlmException {
      PlmResponseListener listener = new PlmResponseListener() {
         /** {@inheritDoc} */
         public void finished(PlmResponse response) {
            setResponse(response);
            getResponse().notify();
         }
      };

      PlmFutureResponse response = session.executeAsynch(PlmActionConstants.CREATE, request);
      response.setListener(listener);
      return response;
   }

   /**
    * {@inheritDoc}
    * 
    * @since plmapi20sp0
    */
   protected PlmResponse waitForResponse(PlmFutureResponse response) throws PlmException {
      try {
         getResponse().wait();
      }
      catch (InterruptedException e) {
         throw new PlmException("Unable to wait for response", e);
      }
      return getResponse();
   }

   /**
    * Main method.
    * 
    * @param args Command line arguments
    * @since plmapi20sp0
    */
   public static void main(String[] args) {
      AsyncCallbackModeSample app = new AsyncCallbackModeSample();
      try {
         app.run();
      }
      catch (PlmException e) {
         throw new RuntimeException("Sample failed", e);
      }
   }

}
