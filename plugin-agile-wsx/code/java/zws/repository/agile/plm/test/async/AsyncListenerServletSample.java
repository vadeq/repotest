/*
 * $Id: AsyncListenerServletSample.java,v 34.1 2007/10/05 12:40:34 wendta01 Exp $
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

import com.agile.plmapi.api.PlmResponse;

import javax.servlet.ServletContext;

/**
 * Sample listener servlet for asynchronous mode.
 * 
 * @author <a href="mailto:support@agile.com">SWE, Agile Software GmbH</a>
 * @since plmapi20sp0
 */
public class AsyncListenerServletSample extends AsyncListenerServlet {

   /**
    * {@inheritDoc}
    * 
    * @since plmapi20sp0
    */
   protected void onResponse(PlmResponse response) {
      // Get a reference to the original request (or any other link to the part
      // where the request was issued)
      ServletContext sc = getServletContext();
      Object reference = sc.getAttribute("request");

      // Now process the response (eventually with respect to the request)

   }
}
