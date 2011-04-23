/*
 * $Id: AsyncListenerServlet.java,v 34.1 2007/10/05 12:40:34 wendta01 Exp $
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
import com.agile.plmapi.api.PlmResponse;
import com.agile.plmapi.api.impl.PlmResponseHelper;
import com.agile.share.xml.XmlDocument;
import com.agile.share.xml.XmlException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract base class for listener servlets for asynchronous operations of the
 * PLM API.
 * 
 * @author <a href="mailto:support@agile.com">SWE, Agile Software GmbH</a>
 * @since plmapi20sp0
 */
public abstract class AsyncListenerServlet extends HttpServlet {

   /**
    * {@inheritDoc}
    * 
    * @since plmapi20sp0
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      XmlDocument document = null;
      try {
         document = new XmlDocument(request.getInputStream());
      }
      catch (XmlException e) {
         throw new ServletException("Unable to get XML from HTTP request", e);
      }

      PlmResponse plmResponse = null;
      try {
         plmResponse = PlmResponseHelper.unmarshal(document);
      }
      catch (PlmException e) {
         throw new ServletException("Unable to create a PlmResponse", e);
      }
      catch (XmlException e) {
         throw new ServletException("Unable to create a PlmResponse", e);
      }

      onResponse(plmResponse);
   }

   /**
    * Needs to be overwritten by subclasses to process the received PlmResponse.
    * 
    * @param response PlmResponse
    * @since plmapi20sp0
    */
   protected abstract void onResponse(PlmResponse response);

}
