package com.zws.hi;

import zws.Server;
import zws.IntralinkClient;
import zws.ReplicationClient;
import zws.security.Authentication;

import com.zws.service.account.*;
import com.zws.service.account.InvalidPasswordException;
import com.zws.service.account.UserNotFoundException;
import com.zws.session.Member;
import com.zws.session.SessionData;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;

import com.zws.domo.account.User;

public abstract class Interactor extends ActionForm{
  public static final String CtrlLOGIN = "login";
  public static final String ctrlINDEX = "index";
  public static final String ctrlVIEW = "view";
  public static final String ctrlEDIT = "edit";
  public static final String ctrlADD = "add";
  public static final String ctrlBACK = "back";
  public static final String ctrlNEXT = "next";
  public static final String ctrlCANCEL = "cancel";
  public static final String ctrlLIST = "list";
  public static final String ctrlCHOOSE = "choose";
  public static final String ctrlPICK = "pick";
  public static final String ctrlUNPICK = "unpick";
  public static final String ctrlUPLOAD = "upload";
  public static final String ctrlUPDATE= "update";
  public static final String ctrlUPDATE_EXISTING_BINARY= "update-existing-binary";
  public static final String ctrlIMPORT_NEW_BINARY= "import-new-binary";

  public static final String ctrlSTREAMING_LIST = "streaming-list";
  public static final String ctrlOK = "ok";
  public static final String ctrlRUNNING = "running";
  public static final String ctrlDONE= "done";
  public static final String ctrlCOMPLETE= "complete";
  public static final String ctrlERROR  = "error";
  public static final String ctrlSYSTEM_ERROR = "systemError";

  public static final String ctrlNAME_CONFLICT = "name-conflict";


  // addded forwards for recorderservice UI

  public static final String ctrlListNames ="listNames";
  public static final String ctrlListRecords ="executionRecordList";
  public static final String ctrlViewExecutionRecord="viewExecutionRecord";
  // end of changes for recorder service UI












  //agile process extensions
  public static final String ctrlAGILE_PX_OK = "agile-px-ok";
  public static final String ctrlAGILE_PX_ERROR  = "agile-px-error";
  public static final String ctrlAGILE_PX_NO_USER  = "agile-px-no-user";
  public static final String ctrlAGILE_PX_BAD_LOGIN  = "agile-px-bad-login";
  public static final String ctrlAGILE_PX_DUPLICATE_NAME  = "agile-px-duplicate-name";

  //Event Service
  public static final String ctrlEVENT_OK = "event-ok";
  public static final String ctrlEVENT_FIRED = "event-fired";
  public static final String ctrlERR_EVENT_ERROR  = "event-error";
  public static final String ctrlERR_EVENT_NO_USER  = "event-no-user";
  public static final String ctrlERR_EVENT_BAD_LOGIN  = "event-bad-login";
  public static final String ctrlERR_EVENT_NOT_RECOGNIZED= "event-not-recognized";
  public static final String ctrlERR_EVENT_HANDLER_NOT_DEFINED = "event-handler-not-defined";
  public static final String ctrlERR_MISSING_REQUIRED_ATTRIBUTE = "missing-required-attribute";

  public static final String ctrlERR_NOT_SYNCHRONIZED= "err-not-synchronized";
  public static final String ctrlERR_NOT_UP_TO_DATE= "err-not-up-to-date";

  public Interactor(Object ignore){};

  public Interactor(){
    super();

  }

  //Parameter Passing (Thread Local) - pass data between form beans/wizards
  private static ThreadLocal hiParameters= new ThreadLocal() {
    protected synchronized Object initialValue() {
      return new HashMap();
    }
  };


  public Map getParameters() {
	  return ((Map)(hiParameters.get()));
	}
  public Object getParameter(Object key) {
	  return ((Map)(hiParameters.get())).get(key);
	}
  public void setParameter(Object key, Object value) {
    ((Map)(hiParameters.get())).put(key, value);
  }
  public void removeParameter(Object key) {
	  ((Map)(hiParameters.get())).remove(key);
	}

  //Convenience functions
  public Collection getServerList() { return Server.getServerList(); }

  //selected IEE repository (Thread Local Singleton)
  private static String IEE_CLIENT="IEE";
  public IntralinkClient getIEE() {
    IntralinkClient client = (IntralinkClient)getSession().getAttribute(IEE_CLIENT);
    if (null==client) {
      client = new IntralinkClient();
      client.initialize(getAuthentication());
      getSession().setAttribute(IEE_CLIENT,client);
    }
    return client;
    //return hiIEE.getIntralinkClient(getAuthentication());
  }

  //selected IER (Thread Local Singleton)
  private static String IER_CLIENT="IER";
  public ReplicationClient getIER() {
    ReplicationClient client = (ReplicationClient)getSession().getAttribute(IER_CLIENT);
	  if (null==client) {
	    client = new ReplicationClient();
      getSession().setAttribute(IER_CLIENT, client);
	  }
	  return client;
    //return hiIER.getReplicationClient();
  }

  /*
  public String getSelectedIEEServer() { return getIEE().getSelectedServer(); }
  public String getSelectedIEERepository() { return getIEE().getSelectedRepository(); }

  private String repositoryKey(String seed) { return getIEE().getSelectedServer() + "."+ getIEE().getSelectedRepository() + "." + seed; }

  public Collection getSelectedIEEUserDefinedAttributes() {
	  String key = repositoryKey("user-defined-attributes");
	  Collection userDefinedAttributes = (Collection) getParameter(key);
	  if (null==userDefinedAttributes) {
	    try {
	      userDefinedAttributes = getIEE().getUserDefinedAttributes(getAuthentication());
	      setParameter(key, userDefinedAttributes);
	    }
	    catch(Exception e) { e.printStackTrace(); }
	  }
	  return userDefinedAttributes;
	 }

	 public Collection getSelectedIEERevisionSequence() {
	   String key = repositoryKey("revision-sequence");
	   Collection revisions = (Collection) getParameter(key);
	   if (null==revisions) {
	     try {
	       revisions = getIEE().getRevisionSequence(getAuthentication());
	       setParameter(key, revisions);
	     }
	     catch(Exception e) { e.printStackTrace(); }
	   }
	   return revisions;
	 }

	 public Collection getSelectedIEEReleaseSequence(String releaseScheme) {
	   String key = repositoryKey("release-sequence."+releaseScheme);
	   Collection releases = (Collection) getParameter(key);
	   if (null==releases) {
	     try {
		     releases = getIEE().getReleaseSequence(releaseScheme, getAuthentication());
	       setParameter(key, releases);
	     }
	     catch(Exception e) { e.printStackTrace(); }
	   }
	   return releases;
	 }

	 public Collection getSelectedIEERevisionSequence(String startingRev) {
	   Collection c = getSelectedIEERevisionSequence();
	   return getSequenceStartingWith(c, startingRev);
	 }

	 public Collection getSelectedIEEReleaseSequence(String releaseScheme, String startingRelease) {
	   Collection c = getSelectedIEEReleaseSequence(releaseScheme);
	   return getSequenceStartingWith(c, startingRelease);
	 }

	 public Collection getSequenceStartingWith(Collection sequence, String startingValue) {
	   Collection list = new Vector();
	   if (null==sequence) return list;
	   Iterator i = sequence.iterator();
	   String s;
	   boolean foundStartingValue=false;
	   while(i.hasNext()) {
	     s = (String) i.next();
	     if (!foundStartingValue&& s.equals(startingValue)) foundStartingValue=true;
	     if (foundStartingValue) list.add(s);
	   }
	   return list;
	 }
	 */

  public void initialize() throws Exception {
    try {
      logger.clear();
      registerRequiredFields();
      registerEventHandlers();
      //refresh();
      bind();
      //render();
    }
    catch (Exception e) { e.printStackTrace(); }} //default implementation
  public void refresh() throws Exception {;} //default implementation
  public void bind() throws Exception {} //default implementation
  public boolean isBound() { return true; }
  public void render() throws Exception {;} //default implementation
  public void registerRequiredFields() {} //default implementation
  public void registerEventHandlers() {} //default implementation
  public void validateInputFields() {} //default implementation: add form errors to processingLog
  public void initHandling()throws Exception {}
  public void finishEventHandling(){};

  public boolean sessionIsExpired(){
    HttpSession session=getSession();
    Member m=null;
    User u=null;
    if (null==session) {
      {} //System.out.println("No active Session!");
      return true;
    }

    {} //System.out.println("Session is active " + session.getId());
    m = getMember();
    if (null==m) {
      {} //System.out.println("Member not found in session!");
      return true;
    }

    {} //System.out.println("Located active Member");
    u = m.getUser();
    if (null==u) {
      {} //System.out.println("Member not bound to a user!");
      return true;
    }

    {} //System.out.println("Sesion retrieved for " + u.getUsername());
    return false;
  }

  public final void initRequest(HttpServletRequest request, HttpServletResponse response) {
    setHttpRequest(request);
    setHttpResponse(response);
//    setSession(request.getSession());
//    SimpleDateFormat f = new SimpleDateFormat();
//    String date = f.format(new Date());
//    response.setHeader("Last-Modified", date);  // http/1.1
    response.setHeader("Expires", "Tue, 08 Feb 1972 08:30:00 GMT");
    response.setHeader("cache-control",  " no-store, no-cache, must-revalidate");
    response.setHeader("cache-control", " post-check=0, pre-check=0"); // http/1.0
    response.setHeader("Pragma", " no-cache");
  }
  public void startRequest() throws Exception {;}
  public final void require(String fieldName){ require(keyALL_EVENTS, fieldName); }
  public final void require(String event, String fieldName){
    Collection requirements;
    requirements = (Collection) requiredFields.get(event);
    if (null == requirements) {
      requirements = new Vector();
      requiredFields.put(event, requirements);
    }
    requirements.add(fieldName);
  }
  public final void setID(String s) {ID = s;}
  public final String getID(){return ID;}
  public final void setInteractor(String s) {interactorName = s;}
  public final String getInteractor(){ return interactorName;}
  public final void setEvent(String s) {event = s;}
  public final String getEvent(){return event;}
  public final void setEventKey(String s) {eventKey = s;}
  public final String getEventKey(){return eventKey;}
  public final void setNav(String s) {nav = s;}
  public final String getNav(){return nav;}
  public final void setHiState(String s) {hiState = s;}
  public final String getHiState(){return hiState;}
//  public void setSession(HttpSession sess) { session=sess; }
  public final HttpSession getSession() { return SessionData.getSession(); }
  public final void setHttpRequest(HttpServletRequest r) { httpRequest=r; }
  public final HttpServletRequest getHttpRequest() { return httpRequest; }
  public final void setHttpResponse(HttpServletResponse r) { httpResponse=r; }
  public final HttpServletResponse getHttpResponse() { return httpResponse; }

  public final String handleEvent(){
    try {
      String result;
      Method handler = findHandler();
      Object[] noArgs = null;
      result = handler.invoke(this, noArgs ).toString();
      return result;
    }
    catch (NoSuchMethodException e) {
      e.printStackTrace();
      logFormError(keyEVENT_NOT_FOUND, getEvent(), e.getMessage(), e.toString());
      return ctrlSYSTEM_ERROR;
    }
    catch (Exception x){
      x.printStackTrace();
      logFormError( keyEVENT_ERROR, getEvent(), x.getMessage(), x.toString());
      return ctrlSYSTEM_ERROR;
    }
  }
  public final void registerHandler(String eventName, String methodName){ handlerMapping.put(eventName, methodName); }
  private final Method findHandler() throws NoSuchMethodException, Exception{
    String methodName;
    if (handlerMapping.containsKey(getEvent()))
      methodName = handlerMapping.get(getEvent()).toString();
    else methodName = getEvent();
    Class[] noArgTypes = null;
    return getClass().getMethod(methodName, noArgTypes);
  }
  public final ActionErrors validate(ActionMapping mapping, HttpServletRequest request){
    logger.clear();
    httpRequest = request;
    validateRequiredInputFields();
    if (logger.empty()) validateInputFields();
    return logger;
  }
  public final void validateRequiredInputFields(){
    String fieldName; //, displayName;
    Collection r, requirements;
    requirements = new Vector();
    r = (Collection)requiredFields.get(keyALL_EVENTS);
    if (null!= r) requirements.addAll(r);
    r = (Collection)requiredFields.get(getEvent());
    if (null!= r) requirements.addAll(r);
    Iterator i = requirements.iterator();
    boolean missingFields = false;
    while (i.hasNext()){
      fieldName = i.next().toString();
      //displayName = looklup field name;
      if (fieldIsBlank(fieldName, httpRequest)) {
        logFieldError(fieldName, keyFIELD_REQUIRED);
        missingFields = true;
      }
    }
    if (missingFields) logFormError(keyREQUIRED_FIELD_BLANK);
  }

  private boolean fieldIsBlank(String fieldName, HttpServletRequest request)
  { return (null==request.getParameter(fieldName) || "".equals(request.getParameter(fieldName))); }

  //Some helpfull logging calls
  public final void logFieldError(String fieldName, String key) {logger.log(fieldName, new Log(key)); }
  public final void logFormStatus(String key) {logger.log(keyFORM_STATUS, new Log(key)); }
  public final void logFormStatus(String key, Object arg1){logger.log(keyFORM_STATUS, new Log(key, arg1)); }
  public final void logFormStatus(String key, Object arg1, Object arg2){logger.log(keyFORM_STATUS, new Log(key, arg1, arg2)); }
  public final void logFormStatus(String key, Object arg1, Object arg2, Object arg3){logger.log(keyFORM_STATUS, new Log(key, arg1, arg2, arg3)); }
  public final void logFormWarning(String key) {logger.log(keyFORM_WARNING, new Log(key)); }
  public final void logFormWarning(String key, Object arg1) {logger.log(keyFORM_WARNING, new Log(key, arg1)); }
  public final void logFormWarning(String key, Object arg1, Object arg2) {logger.log(keyFORM_WARNING, new Log(key, arg1, arg2)); }
  public final void logFormWarning(String key, Object arg1, Object arg2, Object arg3) {logger.log(keyFORM_WARNING, new Log(key, arg1, arg2, arg3)); }
  public final void logFormError(String key) {logger.log(keyFORM_ERROR, new Log(key)); }
  public final void logFormError(String key, Object arg1) {logger.log(keyFORM_ERROR, new Log(key, arg1)); }
  public final void logFormError(String key, Object arg1, Object arg2) {logger.log(keyFORM_ERROR, new Log(key, arg1, arg2)); }
  public final void logFormError(String key, Object arg1, Object arg2, Object arg3) {logger.log(keyFORM_ERROR, new Log(key, arg1, arg2, arg3)); }
  public final void logFormHelp(String key) {logger.log(keyFORM_HELP, new Log(key)); }
  public final void logFormHelp(String key, Object arg1) {logger.log(keyFORM_HELP, new Log(key, arg1)); }
  public final void logFormHelp(String key, Object arg1, Object arg2) {logger.log(keyFORM_HELP, new Log(key, arg1, arg2)); }
  public final void logFormHelp(String key, Object arg1, Object arg2, Object arg3) {logger.log(keyFORM_HELP, new Log(key, arg1, arg2, arg3)); }

  protected ActionErrors getActionErrors() { return logger; }

  public String fileUpload() {
    {} //System.out.println("#####################"+uploadedFiles.size() + " Files were uploaded");
    return ctrlOK;
  }

  public FormFile getUploadedFile00() { return getUploadedFile(0); }
  public FormFile getUploadedFile01() { return getUploadedFile(1); }
  public FormFile getUploadedFile02() { return getUploadedFile(2); }
  public FormFile getUploadedFile03() { return getUploadedFile(3); }
  public FormFile getUploadedFile04() { return getUploadedFile(4); }
  public FormFile getUploadedFile05() { return getUploadedFile(5); }
  public FormFile getUploadedFile06() { return getUploadedFile(6); }
  public FormFile getUploadedFile07() { return getUploadedFile(7); }
  public FormFile getUploadedFile08() { return getUploadedFile(8); }
  public FormFile getUploadedFile09() { return getUploadedFile(9); }
  public FormFile getUploadedFile10() { return getUploadedFile(10); }
  public FormFile getUploadedFile11() { return getUploadedFile(11); }
  public FormFile getUploadedFile12() { return getUploadedFile(12); }
  public FormFile getUploadedFile13() { return getUploadedFile(13); }
  public FormFile getUploadedFile14() { return getUploadedFile(14); }
  public FormFile getUploadedFile15() { return getUploadedFile(15); }
  public FormFile getUploadedFile16() { return getUploadedFile(16); }
  public FormFile getUploadedFile17() { return getUploadedFile(17); }
  public FormFile getUploadedFile18() { return getUploadedFile(18); }
  public FormFile getUploadedFile19() { return getUploadedFile(19); }
  public FormFile getUploadedFile20() { return getUploadedFile(20); }

  public void setUploadedFile00(FormFile f) { setUploadedFile(f, 0); }
  public void setUploadedFile01(FormFile f) { setUploadedFile(f, 1); }
  public void setUploadedFile02(FormFile f) { setUploadedFile(f, 2); }
  public void setUploadedFile03(FormFile f) { setUploadedFile(f, 3); }
  public void setUploadedFile04(FormFile f) { setUploadedFile(f, 4); }
  public void setUploadedFile05(FormFile f) { setUploadedFile(f, 5); }
  public void setUploadedFile06(FormFile f) { setUploadedFile(f, 6); }
  public void setUploadedFile07(FormFile f) { setUploadedFile(f, 7); }
  public void setUploadedFile08(FormFile f) { setUploadedFile(f, 8); }
  public void setUploadedFile09(FormFile f) { setUploadedFile(f, 9); }
  public void setUploadedFile10(FormFile f) { setUploadedFile(f, 10); }
  public void setUploadedFile11(FormFile f) { setUploadedFile(f, 11); }
  public void setUploadedFile12(FormFile f) { setUploadedFile(f, 12); }
  public void setUploadedFile13(FormFile f) { setUploadedFile(f, 13); }
  public void setUploadedFile14(FormFile f) { setUploadedFile(f, 14); }
  public void setUploadedFile15(FormFile f) { setUploadedFile(f, 15); }
  public void setUploadedFile16(FormFile f) { setUploadedFile(f, 16); }
  public void setUploadedFile17(FormFile f) { setUploadedFile(f, 17); }
  public void setUploadedFile18(FormFile f) { setUploadedFile(f, 18); }
  public void setUploadedFile19(FormFile f) { setUploadedFile(f, 19); }
  public void setUploadedFile20(FormFile f) { setUploadedFile(f, 20); }

  public FormFile getUploadedFile(int idx) {
    if (null==uploadedFiles) return null;
    if (idx>=uploadedFiles.size()) return null;
    return (FormFile)uploadedFiles.get(idx);
  }
  public synchronized void setUploadedFile(FormFile f, int idx) {
    if (null==uploadedFiles) uploadedFiles = new ArrayList();
    uploadedFiles.add(f);
    try {
      String contentType = f.getContentType();
      String fileName    = f.getFileName();
      int fileSize       = f.getFileSize();
      byte[] fileData    = f.getFileData();
      {} //System.out.println("contentType: " + contentType);
      {} //System.out.println("File Name: " + fileName);
      {} //System.out.println("File Size: " + fileSize);
    }
    catch(Exception e) { e.printStackTrace(); }
    handleUploadedFile(f, idx);
  }

  public Collection getUploadedFiles() { return uploadedFiles; }
  public void clearUploadedFiles() { uploadedFiles=null; }

  public void handleUploadedFile(FormFile f, int idx) { return ; } //default handler

  private List uploadedFiles = null;

  public Member getMember() {
    if (null==getSession()) return null;
    return (Member)getSession().getAttribute(SessionData.keyMEMBER);
  }

  public Authentication getAuthentication() {
    try { if (null==authentication) authentication = new Authentication(getMember().getUser().getUsername(), getMember().getUser().getPassword()); }
    catch (java.security.NoSuchAlgorithmException e) { e.printStackTrace(); }
    return authentication;
  }

  public void login(String username, String psswd) throws UserNotFoundException, InvalidPasswordException, Exception  {
	  if (Server.debugMode()) {}{} //System.out.println("Authenticating " + username);
	  User user = AccountService.getService().authenticate(username, psswd);
	  if (Server.debugMode()) {}{} //System.out.println("Authenticated " + username);
	  Member member = new Member(user);
	  if (Server.debugMode()) {}{} //System.out.println("Storing to session: " + username);
	  getSession().setAttribute(SessionData.keyMEMBER, member);
	  getSession().setAttribute("authenticating", new Object());
	  if (Server.debugMode()) {}{} //System.out.println("session: " + getSession().getId());
	  logFormStatus("msg.welcome", user.getFirstName());
	  //return ctrlOK;
  }


  public void logout(String username) {
	  //+++;
  }


  protected void streamDataToResponse(InputStream stream) throws Exception {
    if (null==stream) throw new NullPointerException("Data stream is null");
    try {
      OutputStream oStream = getHttpResponse().getOutputStream();
      BufferedInputStream iStream= new BufferedInputStream(stream);
      int data;
      while((data = iStream.read())!=-1) oStream.write(data);
      iStream.close();
      oStream.flush();
      oStream.close();
    }
    catch (Exception e) { e.printStackTrace(); }
  }


  /*
  public Object copy() {
    try {return super.clone(); }
    catch (CloneNotSupportedException e){throw new RuntimeException(e.getMessage()); }
  }
*/
  private String ID, event, eventKey, nav, hiState, interactorName;
  private Logger logger = new Logger();
  private Map requiredFields = new HashMap();
  private Map handlerMapping = new HashMap();
  private HttpServletRequest httpRequest;
  private Authentication authentication=null;
//  private HttpSession session;
  private HttpServletResponse httpResponse;

  private static final String keyALL_EVENTS = "_all.events";

  protected static final String STATE_CREATE_NEW ="create-new";
  protected static final String STATE_NORMAL = "normal";

  public static final String keyFORM_ERROR = "form.error";
  public static final String keyCONFIGURATION_ERROR = "system.err.configuration";
  public static final String keyFORM_WARNING = "form.warning";
  public static final String keyFORM_STATUS = "form.status";
  public static final String keyFORM_HELP = "form.help";
  public static final String keyFIELD_REQUIRED = "err.input.required";
  public static final String keyREQUIRED_FIELD_BLANK = "err.missing.required.input";
  public static final String keyEVENT_ERROR = "system.err.event";
  public static final String keyEVENT_NOT_FOUND = "system.err.event.not.found";
  public static final String keySYSTEM_ERROR = "system.err";
  public static final String keyRUNTIME_ERROR = "run.err";
  public static final String keyUNBOUND_FORM = "run.err.unbound.form";
  public static final String keyINTERACTOR_NAME_MISSING = "system.err.interactor.name.missing";
  public static final String keyUNMAPPED_PAGE_FLOW ="system.err.unmapped.page.flow";
  public static final String keyHANDLER_INIT_ERROR = "system.err.handler.init.err";
  public void pp(String s) { {} //System.out.println(s); }
    
  }

  public static String statSEARCHING="status.searching";
  public static String warnNO_CRITERIA="warn.no.criteria";
  public static String statFOUND_RESULTS="status.found.search.results";
  public static String statALREADY_PUBLISHED="status.already.published";
  public static String statFOUND_ONE_RESULT = "status.found.one.search.result";
  public static String statFOUND_ZERO_RESULTS = "status.found.no.search.results";
  public static String statFOUND_ZERO_OWNED_FILES= "status.found.no.owned.files";

  public static String errREPORT_FILTER = "form.error.report.filter";

  public static String CATEGORY_DOCUMENT="document";

  /**
   * @param menu the menu to set
   */
  public String setCurrentMenu(String menu) {
    String jmethod=null;
    if(menu!=null)
    {
     jmethod="cspbShowSection("+menu.substring(0, menu.indexOf("."))+");selectedMenuItem('m"+menu+"');";
    }
    return jmethod;
  }
}
