package com.agile.sdo.cif;

import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.origin.AgileOrigin;
import zws.qx.QxContext;
import zws.recorder.util.RecorderUtil;
import zws.repository.agile.plm.api.AgilePLMAPIRepositoryBase;
import zws.security.Authentication;
import zws.util.FileUtil;
import zws.util.TimeUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.agile.plmapi.api.PlmAttribute;
import com.agile.plmapi.api.PlmData;
import com.agile.plmapi.api.PlmFactory;
import com.agile.plmapi.api.PlmFutureResponse;
import com.agile.plmapi.api.PlmObject;
import com.agile.plmapi.api.PlmRequest;
import com.agile.plmapi.api.PlmResponse;
import com.agile.plmapi.api.PlmSession;
import com.agile.plmapi.api.impl.PlmResponseHelper;
import com.agile.share.xml.XmlDocument;

/**
 * The Class CifClientUtils.
 */
public class AgileWSXItemSvc extends AgileWSXSvcBase{

  public AgileWSXItemSvc() {  }
  public AgileWSXItemSvc(String strURL, Authentication authID, AgilePLMAPIRepositoryBase rep, QxContext context) {
    super(strURL, authID, rep, context);
  }

  public Metadata createAgileObject(Metadata metadata, String source, Map optionsMap,
    boolean mappedflag, boolean dirtyflag,boolean asyncFlag, QxContext ctx) throws Exception {
    PlmSession session = getSession();
    PlmRequest request = createRequest(id.getUsername());
    Metadata data = createObject(request,metadata, source, optionsMap,mappedflag, dirtyflag,asyncFlag, ctx);
    releasePlmObject(metadata.getName(), session, request,asyncFlag, ctx);
    try {
      session.close();
    } catch (Exception e) {
      zws.Alert.notify("PEN Error", "Error while closing session" +  e.getMessage());
      e.printStackTrace();
    }
    return data;
 }

 protected Metadata createObject(PlmRequest request, Metadata metadata, String source, Map optionsMap,
                               boolean mappedflag, boolean dirtyflag,boolean asyncFlag, QxContext ctx) throws Exception {
    MetadataBase metadataObj      = null;
    if (null == metadata.get("number")) {
      metadata.set("number", metadata.getName());
    }
    String[] attributes = getStringArray(metadata.getAttributes());
    String[] options = getStringArray(optionsMap);
    PlmObject plmObject = createPLMObject("", metadata.getName(), source, options, attributes, mappedflag, dirtyflag);
    request.getData().addObject(plmObject);
    releasePlmObject(metadata.getName(), getSession(), request, asyncFlag, ctx);
    if (null != plmObject) {
      metadataObj = new MetadataBase(prepareMetadata(plmObject));
      metadataObj = new MetadataBase();
      String key = null;
      Map plmAttrMap = plmObject.getAttributes();
      Iterator itr = plmAttrMap.keySet().iterator();
      while (itr.hasNext()) {
        key = (String) itr.next();
        metadataObj.set(key, plmObject.getAttributeValue(key));
      }
    }
    AgileOrigin o = new AgileOrigin(repository.getDomainName(), repository.getServerName(),repository.getName(),
                                    plmObject.getId(),plmObject.getAttributeValue(com.agile.plmapi.api.PlmClassConstants.ATTRIB_CLASS_NAME), metadata.getName());
    o.setTimeOfCreationInMillis(System.currentTimeMillis());
    metadataObj.setOrigin(o);
    return metadataObj;
  }
/*
  protected PlmResponse releasePlmObject(PlmSession session, PlmRequest request) throws Exception {
    PlmResponse plmResponse = null;
    PlmRequest mappedrequest = null;
    String action = "mapping";
    session = getSession();
    dump(action, request);
    plmResponse = session.execute(action, request);
    checkErrors(action, session,plmResponse);

    XmlDocument rpnsDoc1 = new XmlDocument(PlmResponseHelper.marshal(plmResponse));
    {}//Logwriter.printOnConsole(rpnsDoc1.toFormattedString());

    action = "checkstatus";
    mappedrequest = PlmFactory.createRequest(plmResponse, action);
    dump(action, mappedrequest);
    plmResponse = session.execute(action, mappedrequest);
    checkErrors(action, session,plmResponse);

    rpnsDoc1 = new XmlDocument(PlmResponseHelper.marshal(plmResponse));
    {}//Logwriter.printOnConsole(rpnsDoc1.toFormattedString());


    action = "reserve";
    mappedrequest = PlmFactory.createRequest(plmResponse, action);
    dump(action, mappedrequest);
    plmResponse = session.execute(action, mappedrequest);
    checkErrors(action, session,plmResponse);

    /*
     * {} //System.out.println("validate...");
     * mappedrequest=PlmFactory.createRequest(plmResponse,"validate");
     * dump(action, mappedrequest);
     * plmResponse=session.execute("validate",mappedrequest);
     * if(plmResponse.getStatus().hasErrors() ||
     * plmResponse.getStatus().hasFatals()) errorout(session,plmResponse);
     */
  /*  action = "upload-files";
    mappedrequest = PlmFactory.createRequest(plmResponse, action);
    mappedrequest.getHeader().setParameter("recurse", "true"); // set to scan the whole request structure
    dump(action, mappedrequest);
    plmResponse = session.execute(action, mappedrequest);
    checkErrors(action, session,plmResponse);

    action = "update";
    mappedrequest = PlmFactory.createRequest(plmResponse, action);
    dump(action, mappedrequest);
    plmResponse = session.execute(action, mappedrequest);
    checkErrors(action, session,plmResponse);

    action = "release";
    mappedrequest = PlmFactory.createRequest(plmResponse, action);
    dump(action, mappedrequest);
    plmResponse = session.execute(action, mappedrequest);
    checkErrors(action, session,plmResponse);
    {} //System.out.println(PlmResponseHelper.marshal(plmResponse).toFormattedString());
    return plmResponse;
  }*/

 /*protected PlmResponse releasePlmObject(PlmSession session, PlmRequest request) throws Exception {
   return releasePlmObject(session, request,false);
 }*/


 protected PlmResponse releasePlmObject(String itemName, PlmSession session, PlmRequest request,boolean asyncFlag, QxContext ctx) throws Exception {
    boolean autoRelase = true;
    // write the request/ response to a file in zws-temp/itemName_time.txt
    String folderName = Properties.get(Names.TEMP_DIR) + Names.PATH_SEPARATOR + itemName + Names.PATH_SEPARATOR + TimeUtil.now();
    if(null != ctx.get(QxContext.AUTO_RELASE)) {
      autoRelase = (new Boolean(ctx.get(QxContext.AUTO_RELASE))).booleanValue();
    }
    PlmResponse plmResponse = null;
    session = getSession();
    QxContext releaseCtx = RecorderUtil.startSubProcess(ctx, "PLM-API calls", Names.STATUS_STARTED, getString(request));
    try {
    RecorderUtil.logActivity(releaseCtx, "Async Publish",  asyncFlag+"");
    RecorderUtil.logActivity(releaseCtx, "Release Item",  autoRelase+"");
    //actions = "mapping"->"checkstatus"->"reserve"->"validate"->"upload-files"->"update"->"release".
    plmResponse = execute(asyncFlag,session, MAPPING, request, releaseCtx, folderName);
    plmResponse = execute(CHECKSTATUS,plmResponse, session, asyncFlag, releaseCtx, folderName);
    PlmResponse tempResponse = plmResponse;
    try {
    plmResponse = execute(RESERVE,plmResponse, session, asyncFlag, releaseCtx, folderName);
    } catch (Exception e) {
      String tempMessage = "PLM-API call Reserve failed. So, retrying the request.";
      tempMessage =  tempMessage + Names.NEW_LINE + e.getMessage();
      zws.Alert.notify(itemName + " Reserve call failed, retrying", tempMessage);
      session = getSession();
      plmResponse = execute(RESERVE,tempResponse, session, asyncFlag, releaseCtx, folderName);
    }
  //plmResponse = execute(VALIDATE,plmResponse, session, asyncFlag, releaseCtx, folderName);
    plmResponse = execute(UPLOAD_FILES,plmResponse, session, asyncFlag, releaseCtx, folderName);
    plmResponse = execute(UPDATE,plmResponse, session, asyncFlag, releaseCtx,folderName);
    plmResponse = execute(RELEASE,plmResponse, session, asyncFlag, releaseCtx, folderName);

    RecorderUtil.endRecProcess(releaseCtx, Names.STATUS_COMPLETE);
    } catch (Exception e) {
      RecorderUtil.endRecProcess(ctx, Names.STATUS_ERROR);
      e.printStackTrace();
      StringWriter sWriter = new StringWriter();
      PrintWriter pWriter = new PrintWriter(sWriter);
      e.printStackTrace(pWriter);
      FileUtil.appendInNewLine(sWriter.toString(), folderName + Names.PATH_SEPARATOR + "error.txt", true);
      throw e;
    }
    return plmResponse;
  }
  private PlmResponse execute(String action, PlmResponse plmResponse, PlmSession session,
                              boolean asyncFlag, QxContext releaseCtx, String folderName) throws Exception{
    PlmRequest mappedrequest = PlmFactory.createRequest(plmResponse, action);
    if(UPLOAD_FILES.equalsIgnoreCase(action)) {
      mappedrequest.getHeader().setParameter("recurse", "true"); // set to scan the whole request structure
    }
    return execute(asyncFlag, session,action, mappedrequest, releaseCtx, folderName);
  }

  private PlmResponse execute(boolean asyncFlag, PlmSession session,String action,
                              PlmRequest request, QxContext releaseCtx, String folderName) throws Exception{
    request.getHeader().setParameter(PlmSession.OPTION_DEEP_STACKTRACE, Boolean.TRUE.toString());
    PlmFutureResponse plmFutureResponse = null;
    PlmResponse plmResponse = null;
    String fileName = folderName + Names.PATH_SEPARATOR + action + "_request.txt";
    RecorderUtil.logActivity(releaseCtx, action,  REQUEST, getString(request));
    FileUtil.appendInNewLine(getString(request), fileName, true);
    if(asyncFlag) {
      session.setExecuteModePolling(true);
      plmFutureResponse = session.executeAsynch(action, request);
      plmResponse = waitForResponse(plmFutureResponse);
    } else {
      plmResponse = session.execute(action, request);
    }
    checkErrors(action, session,plmResponse, releaseCtx);
    RecorderUtil.logActivity(releaseCtx, action,  RESPONSE, getString(plmResponse));
    fileName = folderName + Names.PATH_SEPARATOR + action + "_response.txt";
    FileUtil.appendInNewLine(getString(plmResponse), fileName, true);
    return plmResponse;
  }

  protected PlmResponse waitForResponse(final PlmFutureResponse response) throws Exception {
    try {
      return (PlmResponse) response.getResult();
    } catch (Throwable e) {
      e.printStackTrace();
      throw new Exception("Error in waitForResponse");
    }
 }


  public void checkErrors(String action, PlmSession session, PlmResponse response, QxContext ctx) throws Exception {
    if (null == response) {
      RecorderUtil.logActivity(ctx, action, "Response is null");
      throw new Exception ("Agile Response is null");
    }
    if (null == response.getStatus()) {
      RecorderUtil.logActivity(ctx, action, "Response has no status");
      throw new Exception ("Agile Response has no status.");
    }
    boolean isValid = true;
    Object err;
    String key = null;
    Collection list = null;
    Iterator msgItr = null;
    String msg = "ERROR: " + action + ": " + Names.NEW_LINE;
    HashMap messageMap = new HashMap();
    List errors = response.getStatus().getErrors();
    List fatals = response.getStatus().getFatals();
    if (null!=fatals) messageMap.put(FATAL,fatals);
    if (null!=errors) messageMap.put(ERROR, errors);
    Iterator itr = messageMap.keySet().iterator();
    while(itr.hasNext()) {
      key = (String) itr.next();
      list = (Collection) messageMap.get(key);
      msgItr = list.iterator();
      while(msgItr.hasNext()) {
        err = msgItr.next();
        msg = msg + Names.NEW_LINE + err.toString();
        RecorderUtil.logActivity(ctx, key, err.toString());
        isValid = false;
      }
    }
    if (isValid) { return; } //no errors, response is valid
    else {
      if(null != session)    session.close();
      RecorderUtil.logActivity(ctx, action,  RESPONSE, getString(response));
      RecorderUtil.logActivity(ctx, action, "failed.");
      throw new Exception (msg+ Names.NEW_LINE);
    }

  }
}
