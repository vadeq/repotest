/*
 * $Id: BaseSample.java,v 34.4 2007/03/02 13:28:36 wendta01 Exp $
 * 
 * Created on 31.07.2006
 *
 * Copyright (c) 2006 by Agile Software. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile Software ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Agile Software.
 */
package com.agile.plmapi.samples.api;

import java.util.HashMap;
import java.util.Map;

import com.agile.plmapi.api.PlmSession;

/**
 * Base class for samples.
 * 
 * @author <a href="mailto:support@agile.com">SWE, Agile Software</a>
 * @since plmapi10sp0
 */
public class BaseSample {

   /**
    * Constructor.
    * 
    * @since plmapi10sp0
    */
   protected BaseSample() {
      super();
   }

   /**
    * Returns the login parameters.
    * 
    * @param baseURL
    *                Base URL
    * @param useClient
    *                Flag if to use client
    * @param showDialog
    *                Flag if to show login dialog in client
    * @return Parameters
    * @since plmapi20sp0
    */
   protected Map getLoginParams(String baseURL, boolean useClient, boolean showDialog) {
      Map params = new HashMap();
      if (useClient) {
         if (showDialog) {
            params.put(PlmSession.KEY_PROTOCOL, PlmSession.PROTOCOL_TCP_IP);
            params.put(PlmSession.KEY_HOST, "localhost");
            params.put(PlmSession.KEY_RESOURCE, "19990");
         }
         else {
            params.put(PlmSession.KEY_PROTOCOL, PlmSession.PROTOCOL_TCP_IP);
            params.put(PlmSession.KEY_HOST, "localhost");
            params.put(PlmSession.KEY_RESOURCE, "19990");
            params.put(PlmSession.KEY_USER, "admin");
            params.put(PlmSession.KEY_PASSWORD, "agile");
            params.put(PlmSession.KEY_URL, baseURL + "/integration/ws");
            params.put(PlmSession.KEY_APPLICATION, baseURL);
            params.put(PlmSession.KEY_SYSTEM, PlmSession.SYSTEM_SDK);
         }
      }
      else {
         params.put(PlmSession.KEY_PROTOCOL, PlmSession.PROTOCOL_WEBSERVICE);
         params.put(PlmSession.KEY_USER, "admin");
         params.put(PlmSession.KEY_PASSWORD, "agile");
         params.put(PlmSession.KEY_URL, baseURL + "/integration/ws");
         params.put(PlmSession.KEY_APPLICATION, baseURL);
         params.put(PlmSession.KEY_SYSTEM, PlmSession.SYSTEM_SDK);
      }
      return params;
   }

}
