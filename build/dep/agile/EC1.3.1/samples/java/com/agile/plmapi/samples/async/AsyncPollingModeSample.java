/*
 * $Id: AsyncPollingModeSample.java,v 34.1 2007/10/05 12:40:34 wendta01 Exp $
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
import com.agile.plmapi.api.PlmSession;
import com.agile.plmapi.api.om.PlmActionConstants;

/**
 * Sample for asynchronous mode with polling.
 * 
 * @author <a href="mailto:support@agile.com">SWE, Agile Software GmbH</a>
 * @since plmapi20sp0
 */
public final class AsyncPollingModeSample extends BaseAsyncSample {

   /**
    * Default constructor.
    * 
    * @since plmapi20sp0
    */
   private AsyncPollingModeSample() {
      // Nothing
   }

   /**
    * {@inheritDoc}
    * 
    * @since plmapi20sp0
    */
   protected PlmFutureResponse executeAsync(PlmSession session, String operation, PlmRequest request) throws PlmException {
      return session.executeAsynch(PlmActionConstants.CREATE, request);
   }

   /**
    * {@inheritDoc}
    * 
    * @since plmapi20sp0
    */
   protected PlmResponse waitForResponse(final PlmFutureResponse response) throws PlmException {
      Thread thread = new Thread(new Runnable() {
         public void run() {
            try {
               synchronized (getResponse()) {
                  setResponse((PlmResponse) response.getResult());
               }
            }
            catch (InterruptedException e) {
               setException(new PlmException("Unable to get response", e));
            }
            catch (Throwable e) {
               setException(new PlmException("Unable to get response", e));
            }
         }
      });
      try {
         thread.join(getDefaultWait());
      }
      catch (InterruptedException e) {
         // Just continue
      }
      if (hasException()) {
         throw getException();
      }
      return null;
   }

   /**
    * Main method.
    * 
    * @param args Command line arguments
    * @since plmapi20sp0
    */
   public static void main(String[] args) {
      AsyncPollingModeSample app = new AsyncPollingModeSample();
      try {
         app.run();
      }
      catch (PlmException e) {
         throw new RuntimeException("Sample failed", e);
      }
   }

}
