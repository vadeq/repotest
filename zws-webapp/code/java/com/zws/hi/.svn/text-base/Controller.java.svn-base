package com.zws.hi;

import zws.Server;
import zws.application.Configurator;

import com.zws.application.Properties;
import com.zws.domo.account.User;
//import com.zws.service.Database;
import com.zws.service.account.AccountService;
import com.zws.session.Member;
import com.zws.session.SessionData;
import com.zws.util.Profiler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class Controller extends Action {
  private static String dataSourceName = null;
  private static boolean authenticate = false;
  public ActionForward perform(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    Interactor frm;
    String showNext;
    String handlerResult;
    //Database.bind(servlet);
    frm = (Interactor) form;
    String menu=frm.setCurrentMenu(request.getParameter("menuItem"));
    {} //System.out.println("value="+request.getParameter("menuItem"));
    request.getSession().setAttribute("menuItem", menu);
    try {
      frm.initialize();
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    Profiler profiler = new Profiler();
    profiler.start("event-loop", "event-loop");
//todo:handle null form
//profiler.start("event-loop", "initializing-form");
    try {
      Configurator.load();
      if (null==dataSourceName) dataSourceName = Properties.get(Properties.DesignStateDatabase);
      if ("true".equalsIgnoreCase(Properties.get(Properties.enableAuthentication))) authenticate=true;
    }
    catch (Exception e) {
      e.printStackTrace();
      showNext = Interactor.ctrlSYSTEM_ERROR;
      frm.logFormError(Interactor.keyCONFIGURATION_ERROR);
      return mapping.findForward(showNext);
    }
    SessionData.setSession(request.getSession());
    if (Server.debugMode()) {} //System.out.println("storing session: " + request.getSession().getId());
    frm.initRequest(request, response);
    if (authenticate && frm.sessionIsExpired()) return mapping.findForward(Interactor.CtrlLOGIN);
    else if (!authenticate && frm.sessionIsExpired()) { //create a guest user
      try{
      User user = AccountService.getService().authenticate("guest", "guest");
      Member member = new Member(user);
      frm.getSession().setAttribute(SessionData.keyMEMBER, member);
      frm.getSession().setAttribute("authenticating", new Object());
      }
      catch (Exception e) { e.printStackTrace(); throw new RuntimeException(e.getMessage()); }
    }
//profiler.stop("event-loop", "initializing-form");
//profiler.start("event-loop", "starting-request");
    try { frm.startRequest(); }
    catch (Exception e) { e.printStackTrace(); } //clean up later .. should not continue here
//profiler.stop("event-loop", "starting-request");
    showNext = Interactor.ctrlINDEX;
    if (null!= frm.getNav() && !"".equals(frm.getNav())) {
      showNext = frm.getNav();
      //frm.setNav(null);
    }
    if(request.getAttribute("event")!=null)
    {
      frm.setEvent(request.getAttribute("event").toString());
    }
    if (null!= frm.getEvent() && !"".equals(frm.getEvent())) {
      try {
//profiler.start("event-loop", "initializing-handler");
        frm.initHandling();
//profiler.stop("event-loop", "initializing-handler");
profiler.start("event-loop", "handling-event");
        handlerResult = frm.handleEvent();
profiler.stop("event-loop", "handling-event");
        frm.finishEventHandling();
        frm.setEvent(null);
//profiler.start("event-loop", "getting-next-page");
        showNext = findNextPage(frm, handlerResult);
//profiler.stop("event-loop", "getting-next-page");
      }
      catch(Exception e) {
        e.printStackTrace();
        showNext = Interactor.ctrlSYSTEM_ERROR;
        frm.logFormError(Interactor.keyHANDLER_INIT_ERROR);
      }
    }

/*
      todo: solve problem for <html:link href="list.do?event=delete" paramId="attributeName" paramName="i" paramProperty="key">
      where javascript can not be called to set the attributeName (or some eventKey);
      Need this problem to be solved in order for page flow mapping to work: [Interactor, event, handlerResult] -> nextPage
      (need to be able to call the handler and set the event, the interactorname, and possibly a key (to delete, for example)
      dynamically - say when iterating through a list of items - without have a contorted href with embedded javascript/tags)
      then add this code:
        if (null==frm.getInteractor()) { // events should have coresponding page mappings
        frm.logFormError(Interactor.keyINTERACTOR_NAME_MISSING, frm.getNav() + " " + frm.getEvent() +" url goes here");
        showNext = Interactor.ctrlSYSTEM_ERROR;
      }
      else (
        frm.initHandling();
        handlerResult = frm.handleEvent();
        showNext = findNextPage(frm, handlerResult);
      }
*/
//profiler.start("event-loop", "refreshing-form");
    try {frm.refresh(); } catch (Exception e) { e.printStackTrace(); }
//profiler.stop("event-loop", "refreshing-form");
//profiler.start("event-loop", "binding-form");
    try {frm.bind();} catch (Exception e) {showNext = Interactor.ctrlSYSTEM_ERROR; e.printStackTrace();}
//profiler.stop("event-loop", "binding-form");
    if (frm.isBound()) {
//profiler.start("event-loop", "rendering-form");
      try {frm.render(); }
      catch (Exception e) { showNext = Interactor.ctrlSYSTEM_ERROR; e.printStackTrace(); }
//profiler.stop("event-loop", "rendering-form");
    }
    else { frm.logFormError(Interactor.keyUNBOUND_FORM); }
//profiler.start("event-loop", "saving-errors");
    saveErrors(request, frm.getActionErrors());
//profiler.stop("event-loop", "saving-errors");

{} //System.out.println("showNext="+showNext);
profiler.stop("event-loop", "event-loop");
profiler.dump(System.out);
    return mapping.findForward(showNext);
  }

  private String findNextPage(Interactor frm, String handlerResult){
    String nextPage=null;
    //todo; create mapping: [form, event, result] -> form
    //nextPage = lookup(frm.getInteractor(), frm.getEvent(), handlerResult);
    nextPage = handlerResult; //temporarily, until page flow mapping is completed.
    if (null==nextPage) {
      frm.logFormError(Interactor.keyUNMAPPED_PAGE_FLOW, frm.getInteractor(), frm.getEvent(), handlerResult);
      return Interactor.ctrlSYSTEM_ERROR;
    }
    return handlerResult;
  }
}