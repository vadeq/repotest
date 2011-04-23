/*
 * $Id: CommonSample.java,v 34.3 2007/03/02 13:28:36 wendta01 Exp $
 * 
 * Created on 16.03.2006
 *
 * Copyright (c) 2005 by Agile Software. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile Software ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Agile Software.
 */
package com.agile.plmapi.samples.api;

import java.util.Collection;
import java.util.Map;

import com.agile.plmapi.api.PlmConstants;
import com.agile.plmapi.api.PlmData;
import com.agile.plmapi.api.PlmException;
import com.agile.plmapi.api.PlmFactory;
import com.agile.plmapi.api.PlmObject;
import com.agile.plmapi.api.PlmRelation;
import com.agile.plmapi.api.PlmRequest;
import com.agile.plmapi.api.PlmResponse;
import com.agile.plmapi.api.PlmSession;
import com.agile.plmapi.api.PlmStatus;
import com.agile.plmapi.api.om.PlmDocumentFileRelation;
import com.agile.plmapi.api.om.PlmDocumentType;
import com.agile.share.io.FileHelper;

/**
 * Sample that demonstrates the use of the Common PLM API.
 * 
 * @author <a href="mailto:support@agile.com">SWE, Agile Software</a>
 * @since plmapi10sp0
 */
public final class CommonSample extends BaseSample {

   /**
    * Constructor.
    * 
    * @since plmapi10sp0
    */
   private CommonSample() {
      super();
   }

   /**
    * Runs the sample code.
    * 
    * @param args
    *                Command line arguments
    * @throws RuntimeException
    *                 if something failed
    * @since plmapi10sp0
    */
   private void run(String[] args) throws RuntimeException {
      String hostName = "localhost";
      if (1 == args.length) {
         hostName = args[0];
      }
      String baseURL = "http://" + hostName + "/Agile";

      boolean useClient = true;
      boolean showLoginDialog = false;
      Map params = getLoginParams(baseURL, useClient, showLoginDialog);
      PlmSession session = PlmFactory.getSession();
      try {
         session.open(params);
      }
      catch (Exception e) {
         throw new RuntimeException("Unable to open session", e);
      }
      if (!session.isValid()) {
         return;
      }

      String documentNumber = null;
      try {
         documentNumber = createDocument(session);
      }
      catch (Exception e) {
         throw new RuntimeException("Unable to create document", e);
      }
      PlmObject document = null;
      try {
         document = getDocument(session, documentNumber);
      }
      catch (Exception e) {
         throw new RuntimeException("Unable to get document", e);
      }
      String fileName = "C:\\temp\\test.txt";
      try {
         checkinFile(session, document, fileName);
      }
      catch (Exception e) {
         throw new RuntimeException("Unable to check-in file", e);
      }

      session.close();
   }

   /**
    * Creates a document.
    * 
    * @param session
    *                PlmSession
    * @return Document number
    * @throws PlmException
    *                 if creation failed
    * @since plmapi10sp0
    */
   private String createDocument(PlmSession session) throws PlmException {
      PlmObject document = PlmFactory.createObject(PlmDocumentType.OBJECT_TYPE);
      document.setAttributeValue(PlmDocumentType.ATTRIB_NUMBER, PlmConstants.AUTO_NUMBER_VALUE);
      document.setAttributeValue(PlmDocumentType.ATTRIB_DESCRIPTION, "PERFORMANCE TEST");
      document.setAttributeValue(PlmDocumentType.ATTRIB_FILE_PATH, "PERFORMANCE TEST");
      document.setAttributeValue(PlmDocumentType.ATTRIB_FILE_NAME, "PERFORMANCE TEST");
      document.setAttributeValue(PlmDocumentType.ATTRIB_FRAME_ID, "1234");
      PlmRequest request = PlmFactory.createRequest();
      PlmData data = request.getData();
      data.addObject(document);
      PlmResponse response = session.execute(PlmDocumentType.ACTION_CREATE, request);
      PlmStatus status = response.getStatus();
      if (status.isError()) {
         throw new PlmException("Document creation failed: " + status.toString());
      }
      data = response.getData();
      Collection objs = data.getObjects();
      document = (PlmObject) objs.iterator().next();
      return document.getAttributeValue(PlmDocumentType.ATTRIB_NUMBER);
   }

   /**
    * Retrieves a document.
    * 
    * @param session
    *                PlmSession
    * @param number
    *                Document number
    * @return Document object
    * @throws PlmException
    *                 if getting failed
    * @since plmapi10sp0
    */
   private PlmObject getDocument(PlmSession session, String number) throws PlmException {
      PlmObject document = PlmFactory.createObject(PlmDocumentType.OBJECT_TYPE);
      document.setAttributeValue(PlmDocumentType.ATTRIB_NUMBER, number);
      PlmRequest request = PlmFactory.createRequest();
      PlmData data = request.getData();
      data.addObject(document);
      PlmResponse response = session.execute(PlmDocumentType.ACTION_GET_DOCUMENT, request);
      PlmStatus status = response.getStatus();
      if (status.isError()) {
         throw new PlmException("Document retrieval failed: " + status.toString());
      }
      data = response.getData();
      Collection objs = data.getObjects();
      document = (PlmObject) objs.iterator().next();
      return document;
   }

   /**
    * Checks-in a file.
    * 
    * @param session
    *                PlmSession
    * @param document
    *                Document object
    * @param filename
    *                File name
    * @return File object
    * @throws PlmException
    *                 if checking-in failed
    * @since plmapi10sp0
    */
   private PlmObject checkinFile(PlmSession session, PlmObject document, String filename) throws PlmException {
      PlmRelation relation = document.createRelation(PlmDocumentFileRelation.OBJECT_TYPE, null);
      String fileNameStr = FileHelper.getFilename(filename);
      String filePathStr = FileHelper.getDirectory(filename);
      relation.setAttributeValue(PlmDocumentFileRelation.ATTRIB_FILE_NAME, fileNameStr);
      relation.setAttributeValue(PlmDocumentFileRelation.ATTRIB_FILE_PATH, filePathStr);
      PlmRequest request = PlmFactory.createRequest();
      PlmData data = request.getData();
      data.addObject(document);
      PlmResponse response = session.execute(PlmDocumentType.ACTION_CHECK_IN, request);
      PlmStatus status = response.getStatus();
      if (status.isError()) {
         throw new PlmException("Document check-in failed: " + status.toString());
      }
      return document;
   }

   /**
    * Main method.
    * 
    * @param args
    *                Command line arguments (there is one optional argument: the
    *                host name, default is localhost)
    * @since plmapi10sp0
    */
   public static void main(String[] args) {
      CommonSample app = new CommonSample();
      app.run(args);
   }

}
