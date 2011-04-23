/*
 * $Id: SyncPollingModeSample.java,v 34.1 2007/10/05 12:41:04 wendta01 Exp $
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

import com.agile.plmapi.api.PlmException;
import com.agile.plmapi.api.PlmRequest;
import com.agile.plmapi.api.PlmResponse;
import com.agile.plmapi.api.PlmSession;
import com.agile.plmapi.api.om.PlmActionConstants;

/**
 * Sample for synchronous mode with polling.
 * 
 * @author <a href="mailto:support@agile.com">SWE, Agile Software GmbH</a>
 * @since plmapi20sp0
 */
public final class SyncPollingModeSample extends BaseSyncSample {

   /**
    * Default constructor.
    * 
    * @since plmapi20sp0
    */
   private SyncPollingModeSample() {
      // Nothing
   }

   /**
    * Run method.
    * 
    * @throws PlmException if sample failed
    * @since plmapi20sp0
    */
   protected PlmResponse execute(PlmSession session, String operation, PlmRequest request) throws PlmException {
      // The following line will force the session to run asynchronously
      // internal, but return synchronously
      session.setExecuteModePolling(true);
      return session.execute(PlmActionConstants.CREATE, request);
   }

   /**
    * Main method.
    * 
    * @param args Command line arguments
    * @since plmapi20sp0
    */
   public static void main(String[] args) {
      SyncPollingModeSample app = new SyncPollingModeSample();
      try {
         app.run();
      }
      catch (PlmException e) {
         throw new RuntimeException("Sample failed", e);
      }
   }
}
