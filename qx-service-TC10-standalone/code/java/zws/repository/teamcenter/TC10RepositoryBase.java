package zws.repository.teamcenter;
/*
 * DesignState - Design Compression Technology
 * @author: arbind @version: 1.0 Created on
 * May 23, 2007 10:48:58 AM Copywrite (c) 2007
 * Zero Wait-State Inc. All rights reserved
 */

import zws.application.Names;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataSubComponentBase;
import zws.data.tc10.TC10MetadataBinary;
import zws.exception.CanNotMaterialize;
import zws.exception.NotConnected;
import zws.origin.Origin;
import zws.origin.tc10.TC10IMANFileOrigin;
import zws.qx.QxContext;
import zws.qx.xml.QxXML;
import zws.repository.RepositoryBase;
import zws.repository.source.RepositoryBinarySource;
import zws.repository.source.RepositoryConfigurationSource;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStateSource;
import zws.repository.source.RepositoryStructureSource;
import zws.repository.source.RepositoryTemplateSource;
import zws.repository.target.RepositoryBinaryTarget;
import zws.repository.target.RepositoryConfigurationTarget;
import zws.repository.target.RepositoryMetadataTarget;
import zws.repository.target.RepositoryStateTarget;
import zws.repository.target.RepositoryStructureTarget;
import zws.repository.target.RepositoryTemplateTarget;
import zws.search.SearchAgent;
import zws.security.Authentication;
import zws.repository.teamcenter.target.TC10RepositoryBinaryTarget;
import zws.repository.teamcenter.target.TC10RepositoryMetadataTarget;
import zws.repository.teamcenter.target.TC10RepositoryStateTarget;
import zws.repository.teamcenter.target.TC10RepositoryStructureTarget;
import zws.repository.teamcenter.util.TC10Util;

import java.io.File;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.HashMap;

import com.ugsolutions.aif.AIFPortal;
import com.ugsolutions.aif.kernel.AIFComponentContext;
import com.ugsolutions.aif.kernel.AIFSessionManager;
import com.ugsolutions.aif.kernel.InterfaceAIFComponent;
import com.ugsolutions.iman.kernel.IMANComponent;
import com.ugsolutions.iman.kernel.IMANComponentDataset;
import com.ugsolutions.iman.kernel.IMANComponentImanFile;
import com.ugsolutions.iman.kernel.IMANComponentItem;
import com.ugsolutions.iman.kernel.IMANComponentItemRevision;
import com.ugsolutions.iman.kernel.IMANComponentRevisionRule;
import com.ugsolutions.iman.kernel.IMANComponentBOMLine;
import com.ugsolutions.iman.kernel.IMANComponentUser;
import com.ugsolutions.iman.kernel.IMANException;
import com.ugsolutions.iman.kernel.IMANSession;
import com.ugsolutions.iman.kernel.InterfaceServerConnection;
import com.ugsolutions.iman.kernel.TcServiceGatewayWebServiceConnection;
import com.ugsolutions.util.Registry;

/**
 * Logical representation of a data repository.
 */
public class TC10RepositoryBase extends RepositoryBase {
  public TC10RepositoryBase() {}

  public void activate () {}

  /**
   * Returns the type of data repository (e.g. Intralink, TeamCenter, Agile,
   * File System, etc.).
   *
   * @return The type of repository.
   */
  public String getRepositoryType() {
    return zws.origin.Origin.FROM_TEAMCENTER_10;
  }

  /**
   * Checks to see if a connection to the data repository can be established.
   *
   * @Throws NotConnected
   * Exception is thrown only if a connection can not be established.
   */


  public RepositoryMetadataSource materializeMetadataSource()
      throws CanNotMaterialize {
    TC10RepositoryMetadataSource source = new TC10RepositoryMetadataSource(
        getContext());
    source.configureTeamcenterSessions(getTeamcenterSessions());
    return source;
  }

  public RepositoryBinarySource materializeBinarySource()
      throws CanNotMaterialize {
    TC10RepositoryBinarySource source = new TC10RepositoryBinarySource(
        getContext());
    source.configureTeamcenterSessions(getTeamcenterSessions());
    return source;
  }

  public RepositoryStructureSource materializeStructureSource()
      throws CanNotMaterialize {
    TC10RepositoryStructureSource source = new TC10RepositoryStructureSource(
        getContext());
    source.configureTeamcenterSessions(getTeamcenterSessions());
    return source;
    // throw new CanNotMaterialize("StructureSource", "materialize method has
    // not been implemented", this);
  }

  public SearchAgent materializeSearchAgent() throws CanNotMaterialize {
    TC10RepositorySearchAgent agent = new TC10RepositorySearchAgent();
    agent.setRepository(this);
    {} //System.out.println("Returning TC10 search agent " + agent);
    return agent;
  }

  public RepositoryConfigurationSource materializeConfigurationSource()
      throws CanNotMaterialize {
    TC10RepositoryConfigurationSource source = new TC10RepositoryConfigurationSource(
        getContext());
    source.configureTeamcenterSessions(getTeamcenterSessions());
    return source;
  }

  public void verifyConnection() throws NotConnected {
  // / +++ ToDo
  }

  /**
   * Returns a pre-configured search agent that reports only the latest data
   * when queried.
   *
   * @return A Search Agent.
   */
  public SearchAgent materializeLatestSearchAgent() throws CanNotMaterialize {
    throw new CanNotMaterialize("LatestSearch Agent",
        "materialize method has not been implemented", this);
  }

  /**
   * Returns a pre-configured search agent that reports only the latest data for
   * each revision when queried.
   *
   * @return A Search Agent.
   */
  public SearchAgent materializeLatestRevSearchAgent() throws CanNotMaterialize {
    throw new CanNotMaterialize("LatestRevSearch Agent",
        "materialize method has not been implemented", this);
  }

  public RepositoryStateSource materializeStateSource()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("StateSource",
        "materialize method has not been implemented", this);
  }

  public RepositoryTemplateSource materializeTemplateSource()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("TemplateSource",
        "materialize method has not been implemented", this);
  }

  public RepositoryMetadataTarget materializeMetadataTarget()
      throws CanNotMaterialize {
    TC10RepositoryMetadataTarget target = new TC10RepositoryMetadataTarget(
        getContext());
    target.configureTeamcenterSessions(getTeamcenterSessions());
    return target;
  }

  public RepositoryBinaryTarget materializeBinaryTarget()
      throws CanNotMaterialize {
    TC10RepositoryBinaryTarget target = new TC10RepositoryBinaryTarget(
        getContext());
    target.configureTeamcenterSessions(getTeamcenterSessions());
    return target;
  }

  public RepositoryStructureTarget materializeStructureTarget()
      throws CanNotMaterialize {
    TC10RepositoryStructureTarget target = new TC10RepositoryStructureTarget(
        getContext());
    target.configureTeamcenterSessions(getTeamcenterSessions());
    return target;
  }

  public RepositoryStateTarget materializeStateTarget()
      throws CanNotMaterialize {
    TC10RepositoryStateTarget target = new TC10RepositoryStateTarget(
        getContext());
    target.configureTeamcenterSessions(getTeamcenterSessions());
    return target;
  }

  public RepositoryConfigurationTarget materializeConfigurationTarget()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("ConfigurationTarget",
        "materialize method has not been implemented", this);
  }

  public RepositoryTemplateTarget materializeTemplateTarget()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("TemplateTarget",
        "materialize method has not been implemented", this);
  }

  public String objectName(String name) {
    if (getLowerCasedFilenames()) return name.toLowerCase();
    else if (getUpperCasedFilenames()) return name.toUpperCase();
    else return name;
  }


  public long parseDate(String formattedDate) {
    long d = 0;
    if (null == formattedDate || "".equals(formattedDate)) return 0;
    if (formattedDate.indexOf(".") > 0) d = parseDottedDate(formattedDate);
    else d = parseSimpleDate(formattedDate);
    return d;
  }

  private long parseDottedDate(String dottedDate) {
    int year = 0, month = 0, day = 0, hour = 0, min = 0, sec = 0;
    StringTokenizer tokens = new StringTokenizer(dottedDate, ".");
    if (tokens.hasMoreTokens()) year = Integer.valueOf(tokens.nextToken())
        .intValue();
    if (tokens.hasMoreTokens()) month = Integer.valueOf(tokens.nextToken())
        .intValue();
    if (tokens.hasMoreTokens()) day = Integer.valueOf(tokens.nextToken())
        .intValue();
    if (tokens.hasMoreTokens()) hour = Integer.valueOf(tokens.nextToken())
        .intValue();
    if (tokens.hasMoreTokens()) min = Integer.valueOf(tokens.nextToken())
        .intValue();
    if (tokens.hasMoreTokens()) sec = Integer.valueOf(tokens.nextToken())
        .intValue();
    Calendar c = new GregorianCalendar(year, month - 1, day, hour, min, sec);
    {}//Logwriter.printOnConsole("Dotted Date: " + c.getTimeInMillis());
    return c.getTimeInMillis();
  }

  private long parseSimpleDate(String formattedDate) {
    if (null == formattedDate || "".equals(formattedDate)) return 0;
    SimpleDateFormat dateFormatter = new SimpleDateFormat(getDateFormat());
    String d = formattedDate.replace(',', ' ');
    return dateFormatter.parse(d, new ParsePosition(0)).getTime();
  }

  protected Map getTeamcenterSessions() {
    return teamcenterSessions;
  }

  protected void configureTeamcenterSessions(Map sessions) {
    teamcenterSessions = sessions;
  }

  public IMANSession login(Authentication id) {
    // login(user,pwd) : return user's session.
    // first look in hashmap, create session if necessary + put in hash map
    // also check if session is valid, if not then remove from hashmap and
    // create new session
    // always return a valid session for (user,pswd)
    {} //System.out.println("logging in....");
    String user = id.getUsername();
    String password = id.getPassword();
    String group = null;

    {} //System.out.println("User:" + user);
    {} //System.out.println("Password:" + password);


    // Connect to the IMAN server.
    Registry registry = Registry.getRegistry("com.ugsolutions.aif.aif");
    {} //System.out.println("registry .... " + registry);
    String transportInUse = ""; // registry.getString("portalCommunicationTransport",
                                // "iiop");

    IMANSession imanSession = null;

    try {
      // Check HashMap teamcenterSessions
      imanSession = (IMANSession) teamcenterSessions.get(user);
      {} //System.out.println("imanSession: " + imanSession);
      if (imanSession == null) {
        // Get the IMAN session object reference.
        portal = new AIFPortal(false);
        AIFSessionManager sessionManager = portal.getKernel().getSessionManager();
        imanSession = (IMANSession) sessionManager.getASession("com.ugsolutions.iman.kernel.IMANSession", user);
        {} //System.out.println("\nSession ID: " + imanSession.getSessionID());
        if (imanSession == null) {
          System.err.println("Error in obtaining the IMAN session, exiting.");

        }
      }

      // Connect to the server.
      InterfaceServerConnection serverConnection = null;
      /*
      if (transportInUse.compareTo("iiop") == 0) {
        {} //System.out.println("\n2-Tier - Rich Client"); //prototyping
        TcServiceGatewayCorbaConnection gatewayConnection = new TcServiceGatewayCorbaConnection( getHostName(), getServerMarker());
        gatewayConnection.setPort(Integer.parseInt(getPort()));
        serverConnection = gatewayConnection;
      } else {
      }
      */
      System.out.print("\n4-Tier - Thin Client - [");
      StringBuffer url = new StringBuffer();
      url.append("http://");
      url.append(getHostName()).append(":");
      url.append(getPort()).append("/");
      url.append(getServicesPath());
      {} //System.out.println(url.toString() + "]");

      TcServiceGatewayWebServiceConnection gatewayConnection = new TcServiceGatewayWebServiceConnection( url.toString(), "PLMGetwayService");
      serverConnection = gatewayConnection;
      {} //System.out.println("serverConnection " + serverConnection);
      if (serverConnection == null || serverConnection.connect() == false) {
        System.err.println("Unable to connect to the server.");

      }

      if (imanSession.isLoggedIn() == false) {
      // Login to IMAN.
      imanSession.login(serverConnection, user, password, "", "");


        //imanSession.login();
      }
      teamcenterSessions.put(user, imanSession);
      // {} //System.out.println("\nNumber of Sessions: " + teamcenterSessions.size()
      // );

    } catch (Exception e) {
      {} //System.out.println("Unexpected exception during login.");
      {} //System.out.println(e.toString());
      e.printStackTrace();
    }
    catch (Throwable t) {
    {} //System.out.println("Unexpected Throwable during login: " + t.getMessage());
    {} //System.out.println(t.toString());
    t.printStackTrace();
  }

    return imanSession;
  }

  public void logout(Authentication id) {
    try {
      IMANSession imanSession = (IMANSession) teamcenterSessions.get(id
          .getUsername());
      if (imanSession.isLoggedIn()) {
        imanSession.logout();
        teamcenterSessions.remove(id.getUsername());
      }

    } catch (Exception e) {
      {} //System.out.println("Unexpected exception during logout.");
      {} //System.out.println(e.toString());
      e.printStackTrace();

    }
  }

  public void reportServerInformation(Authentication id) {
    IMANSession imanSession = (IMANSession) teamcenterSessions.get(id.getUsername());
    StringBuffer sb = new StringBuffer();
    try {
      // Report server host information.
      sb
          .append("Server Host Name		: " + imanSession.getServerHostname()
              + crlf);
      sb.append("Server Host Platform Name	: "
          + imanSession.getServerPlatformName() + crlf);

      // Report TeamCenter Engineering server information.
      sb.append("IMAN Server Version		: " + imanSession.getServerVersion()
          + crlf);
      sb.append("IMAN Server ID 		: " + imanSession.getServerID() + crlf);
      sb.append("IMAN Server Description	: "
          + imanSession.getServerDescription() + crlf);

      // Report session information.
      sb
          .append("IMAN Session User Name 	: " + imanSession.getUserName()
              + crlf);
      sb.append("IMAN Session Description	: " + imanSession.getDescription()
          + crlf);
      sb.append("Currently Logged In		: " + imanSession.getLoginState() + crlf);

    } catch (Exception e) {
      {} //System.out.println("Unexpected exception in reportServerInformation()");
      {} //System.out.println(e.toString());
      e.printStackTrace();

    }
    {} //System.out.println(sb.toString());
  }

  protected Collection extractBinary(IMANComponentDataset ds, String fileName, String dir) {
    Collection c = new ArrayList();
	  try {
		  IMANComponentImanFile[] files = ds.getImanFiles();
		  {} //System.out.println("Number of files: "+files.length);
		  for (int i = 0; i < files.length; i++) {
        File f = null;
			  MetadataBase m = getProperties(files[i]);
			  String originalName = files[i].getProperty("original_file_name");
			  if (originalName.equalsIgnoreCase(fileName)) {
				  if (dir == null) f = files[i].getFile(".\\");
				  else {
            boolean exists = (new File(dir)).exists();
            if(exists) f = files[i].getFile(dir);
            else {
              boolean success = (new File(dir)).mkdirs();
              if(success) f = files[i].getFile(dir);
              else throw new zws.exception.CanNotDo("Make directory ", dir);
            }
          }
			  }
        if(null!= f) c.add(f);
		  }
      return c;
	  } catch (Exception e) {
		  {} //System.out.println("Unexpected exception during file retrieval.");
		  {} //System.out.println(e.toString());
		  e.printStackTrace();
	  }
	  return null;
  }

  protected MetadataBase addBinaries(IMANComponentDataset dsComp, MetadataBase m) {
    try {
      IMANComponentImanFile[] binaries = dsComp.getImanFiles();
      for (int i = 0; i < binaries.length; i++) {
        MetadataBase binaryBase = getProperties(binaries[i]);
        TC10MetadataBinary bm = new TC10MetadataBinary(binaryBase);

        String itemId = m.get(TC10Constants.ITEM_ID);
        String revision = m.get(TC10Constants.REVISION);
        String datasetName = dsComp.getProperty("object_name");
        String datasetType = dsComp.getProperty("object_type");

        String state = TC10Util.materializeState(m);
        Date creationDate = dsComp.getDateProperty("creation_date");

        String fileName = bm.get("original_file_name");
        String binaryUid = TC10Util.materializeIMANFileUniqueID(
            binaries[i].getUid(), itemId, revision, datasetType, datasetName,
            fileName);
        TC10IMANFileOrigin binaryOrig = new TC10IMANFileOrigin(getDomainName(),
            getServerName(), getRepositoryName(), binaryUid, creationDate
                .getTime(), state);

        // {} //System.out.println("binaryOrig: " + binaryOrig.toString() );

        bm.setOrigin(binaryOrig);
        m.addBinary(bm);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return m;
  }

  protected MetadataBase addBinaries(IMANComponentItemRevision revComp,
      MetadataBase m) {
    try {
      AIFComponentContext children[] = revComp.getChildren();
      for (int k = 0; k < children.length; k++) {
        if (children[k].getComponent() instanceof IMANComponentDataset) {
          // Add Binaries
          IMANComponentDataset dsComp = (IMANComponentDataset) children[k]
              .getComponent();
          m = addBinaries(dsComp, m);
        }
      }
    } catch (IMANException e) {
      e.printStackTrace();
    }
    return m;
  }

  protected IMANComponentItem findItem(IMANSession session, String itemId) {
    try {

      String[] entryNames = { "Item ID" };
      String[] entryValues = { itemId };

      InterfaceAIFComponent res[] = session.search("Item ID", entryNames, entryValues);
      if (res == null || res.length == 0) {
        return null;
      } else {
        return (IMANComponentItem) res[0];
      }

    } catch (IMANException iex) {
      {} //System.out.println("TC ERROR: " + iex.toString());
      iex.printStackTrace();
    } catch (Exception ex) {
      {} //System.out.println(ex.toString());
      ex.printStackTrace();
    }
    return null;
  }

  protected IMANComponentItemRevision findItemRevision(IMANSession session,
      String itemId, String revision) {
    try {
      String[] entryNames = { "Item ID", "Revision" };
      String[] entryValues = { itemId, revision };

      InterfaceAIFComponent res[] = session.search("Item Revision...",
          entryNames, entryValues);
      if (res == null || res.length == 0) {
        return null;
      } else {
//{} //System.out.println("Number of Item Revs: "+res.length);
        return (IMANComponentItemRevision) res[0];
      }

    } catch (IMANException iex) {
      {} //System.out.println("TC ERROR: " + iex.toString());
      iex.printStackTrace();
    } catch (Exception ex) {
      {} //System.out.println(ex.toString());
      ex.printStackTrace();
    }
    return null;
  }

  protected IMANComponentImanFile findImanFile(IMANSession session,
      String filename) {
    try {
      String[] entryNames = { "Original File Name" };
      String[] entryValues = { filename };

      InterfaceAIFComponent res[] = session.search("ImanFile...", entryNames,
          entryValues);
      if (res == null || res.length == 0) {
        return null;
      } else {
        return (IMANComponentImanFile) res[0];
      }

    } catch (IMANException iex) {
      {} //System.out.println("TC ERROR: " + iex.toString());
      iex.printStackTrace();
    } catch (Exception ex) {
      {} //System.out.println(ex.toString());
      ex.printStackTrace();
    }
    return null;
  }

  protected IMANComponentDataset findDataset(
      IMANComponentItemRevision itemRevision, String datasetName,
      String datasetType) {
    try {
      AIFComponentContext comps[] = itemRevision.getChildren();
//{} //System.out.println("Number of Children: "+comps.length);
      for (int i = 0; i < comps.length; i++) {
        if (comps[i].getComponent() instanceof IMANComponentDataset) {
          IMANComponentDataset ds = (IMANComponentDataset) comps[i]
              .getComponent();
          if (ds.getProperty("object_type").equalsIgnoreCase(datasetType)) if (ds
              .getProperty("object_name").equalsIgnoreCase(datasetName)) return ds;
        }
      }
    } catch (IMANException iex) {
      {} //System.out.println("TC ERROR: " + iex.toString());
      iex.printStackTrace();
    } catch (Exception ex) {
      {} //System.out.println(ex.toString());
      ex.printStackTrace();
    }
    return null;
  }

  protected IMANComponentUser findUser(IMANSession session, String userId) {
    try {

      String[] entryNames = { "User Id" };
      String[] entryValues = { userId };

      InterfaceAIFComponent res[] = session.search("__WEB_find_user", entryNames, entryValues);
      if (res == null || res.length == 0) {
        return null;
      } else {
        return (IMANComponentUser) res[0];
      }

    } catch (IMANException iex) {
      {} //System.out.println("TC ERROR: " + iex.toString());
      iex.printStackTrace();
    } catch (Exception ex) {
      {} //System.out.println(ex.toString());
      ex.printStackTrace();
    }
    return null;
  }

  protected MetadataBase getProperties(IMANComponent obj) {
    try {
      Hashtable props = new Hashtable();

      if( obj instanceof IMANComponentBOMLine )
      {
        IMANComponentBOMLine bl = (IMANComponentBOMLine)obj;
        bl.pack();
        props.put("bl_rev_item_revision_id", bl.getProperty("bl_rev_item_revision_id") );
        props.put("bl_quantity",  bl.getProperty("bl_quantity") );
        props.put("bl_real_quantity",  bl.getProperty("bl_real_quantity") );
        props.put("bl_sequence_no",  bl.getProperty("bl_sequence_no") );
        props.put("bl_item_object_type",  bl.getProperty("bl_item_object_type") );
        props.put("bl_item_ics_subclass_name",  bl.getProperty("bl_item_ics_subclass_name") );
        props.put("bl_object_name",  bl.getProperty("bl_object_name") );
        props.put("bl_item_id",  bl.getProperty("bl_item_id") );
        props.put("bl_item_object_desc",  bl.getProperty("bl_item_object_desc") );
        props.put("bl_has_children",  bl.getProperty("bl_has_children") );
      }
      else {props = obj.getProperties(); }

      MetadataBase m = new MetadataBase();
      String key, value;

      Iterator i = props.keySet().iterator();
      while (i.hasNext()) {
        key = (String) i.next();
        value = (String) props.get(key);
        setMetadataValue(m,key,value);
        //m.set(key, value);
        // {} //System.out.println(key + ": " + value);
      }
      m.setName(m.get("ItemID"));
      m.set(Names.METADATA_DOMAIN_NAME, getDomainName());
      m.set(Names.METADATA_SERVER_NAME, getServerName());
      m.set(Names.METADATA_REPOSITORY_NAME, getName());
      return m;
    }
    catch (IMANException iex) {
      {} //System.out.println("TC ERROR: " + iex.toString());
      iex.printStackTrace();
    }
    return null;
  }

  private void setMetadataValue(Metadata m, String tcKey, String value) {
	  String v = value;
      if(v==null) v="";
	  String zwsKey = (String)TC10Constants.fromTC10ToZwsMap.get(tcKey);
	  if (null==zwsKey) zwsKey = tcKey;
	  m.set(zwsKey,v);
  }

  protected MetadataSubComponentBase getSubComponentProperties(IMANComponent obj, MetadataBase m) {
    try
    {
      Hashtable props = new Hashtable();

      if( obj instanceof IMANComponentBOMLine )
      {
        IMANComponentBOMLine bl = (IMANComponentBOMLine)obj;
        bl.pack();
        props.put("bl_rev_item_revision_id", bl.getProperty("bl_rev_item_revision_id") );
        props.put("bl_quantity",  bl.getProperty("bl_quantity") );
        props.put("bl_real_quantity",  bl.getProperty("bl_real_quantity") );
        props.put("bl_sequence_no",  bl.getProperty("bl_sequence_no") );
        props.put("bl_item_object_type",  bl.getProperty("bl_item_object_type") );
        props.put("bl_item_ics_subclass_name",  bl.getProperty("bl_item_ics_subclass_name") );
        props.put("bl_object_name",  bl.getProperty("bl_object_name") );
        props.put("bl_item_id",  bl.getProperty("bl_item_id") );
        props.put("bl_item_object_desc",  bl.getProperty("bl_item_object_desc") );
        props.put("bl_has_children",  bl.getProperty("bl_has_children") );
      }
      else {props = obj.getProperties(); }

      MetadataSubComponentBase subm = new MetadataSubComponentBase(m);
      String key, value;

      Iterator i = props.keySet().iterator();
      while (i.hasNext())
      {
        key = (String) i.next();
        value = (String) props.get(key);
        setMetadataValue(subm,key,value);
        //subm.set(key, value);

// {} //System.out.println("SUB: " + key + ": " + value);

      }
      return subm;
    }
    catch (IMANException iex)
    {
      {} //System.out.println("TC ERROR: " + iex.toString());
      iex.printStackTrace();
    }

    return null;
  }

  protected IMANComponentRevisionRule getRevisionRule(IMANSession session,
      String revisionRuleString) {
    try {
      String[] entryNames = { "Name", "Type" };
      String[] entryValues = { revisionRuleString, "RevisionRule" };

      InterfaceAIFComponent res[] = session.search("General...", entryNames,
          entryValues);
      if (res == null || res.length == 0) {
        return null;
      } else {
        return (IMANComponentRevisionRule) res[0];
      }

    } catch (IMANException iex) {
      {} //System.out.println("TC ERROR: " + iex.toString());
      iex.printStackTrace();
    } catch (Exception ex) {
      {} //System.out.println(ex.toString());
      ex.printStackTrace();
    }
    return null;
  }

  public QxXML toXML () {
    /*<intralink-8 name="ilink-8" system-password="****" system-username="****"
      domain-name="zws" server-name="node-0" protocol="http"
      host-name="vm-ilink-8" port="80" instance-name="zws" remote-method="zws-ilink-interface"
            download-services-path="Windchill/netmarkets/jsp/zws/downloadpdmcontent.jsp"
            services-path="Windchill/servlet/RPC?CLASS=com.infoengine.soap"/>*/

    StringBuffer xmlString = new StringBuffer();

    xmlString.append(START_TAG).append(this.getRepositoryType());
    xmlString.append(prepareArg(QxContext.NAME, this.getRepositoryName()));
    xmlString.append(prepareArg(QxContext.DOMAIN_NAME, this.getDomainName()));
    xmlString.append(prepareArg(QxContext.SERVER_NAME, this.getServerName()));
    xmlString.append(prepareArg(QxContext.PROTOCOL, this.getProtocol()));
    xmlString.append(prepareArg(QxContext.HOST_NAME, this.getHostName()));
    xmlString.append(prepareArg(QxContext.PORT, this.getPort()));
    xmlString.append(prepareArg(QxContext.SERVICES_PATH, this.getServicesPath()));
    xmlString.append(prepareArg(QxContext.DESCRIPTION, this.getDescription()));

    //xmlString.append(prepareArg(QxContext.SERVER_MARKER, this.getServerMarker()));
    //xmlString.append(prepareArg(QxContext.TC_SERVER_NAME, this.getTCServerName()));

    xmlString.append(END_TAG);

    return new QxXML(xmlString.toString());
  }

  public boolean getLowerCasedFilenames() {
    String s = getContext().get(TC10Constants.LOWER_CASED_FILENAMES,
        Boolean.valueOf(true).toString());
    return Boolean.valueOf(s).booleanValue();
  }

  public void setLowerCasedFilenames(boolean b) {
    getContext().set(TC10Constants.LOWER_CASED_FILENAMES,
        Boolean.valueOf(b).toString());
  }

  public boolean getUpperCasedFilenames() {
    String s = getContext().get(TC10Constants.UPPER_CASED_FILENAMES,
        Boolean.valueOf(false).toString());
    return Boolean.valueOf(s).booleanValue();
  }

  public void setUpperCasedFilenames(boolean b) {
    getContext().set(TC10Constants.UPPER_CASED_FILENAMES,
        Boolean.valueOf(b).toString());
  }

  public long getConnectionTimeout() {
    String s = getContext().get(TC10Constants.CONNECTION_TIMEOUT, "30");
    return Long.valueOf(s).longValue();
  }

  public void setConnectionTimeout(long l) {
    getContext().set(TC10Constants.CONNECTION_TIMEOUT, "" + l);
  }

  public String getDateFormat() {
    return getContext().get(TC10Constants.DATE_FORMAT, TC10Constants.DEFAULT_DATE_FORMAT);
  }

  public void setDateFormat(String s) {
    getContext().set(TC10Constants.DATE_FORMAT, s);
  }

  /*
  public String getServerMarker() {
    return getContext().get(QxContext.SERVER_MARKER);
  }
  public String getTCServerName() {
    return getContext().get(QxContext.TC_SERVER_NAME);
  }

  public void setServerMarker(String s) {
    getContext().set(QxContext.SERVER_MARKER, s);
  }
  public void setTCServerName(String s) {
    getContext().set(QxContext.TC_SERVER_NAME, s);
  }
*/
  // default configurations
  private static String d = Origin.delim;

  private static final String crlf = System.getProperty("line.separator");

  private Map teamcenterSessions = new HashMap();

  private static AIFPortal portal;
}
