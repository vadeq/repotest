package zws.hi.custom.cisco; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.Synchronizer;
import zws.data.*;
import zws.origin.*;
import zws.AgileAccess;
import zws.IntralinkAccess;
import zws.application.Names;
import zws.application.Properties;
import zws.hi.demo.kla.KLAMetadataAdapter;
import zws.hi.demo.kla.hiKLAPublish2Agile;
import zws.exception.DuplicateName;

import java.net.URL;
import zws.util.*;
import com.zws.service.account.InvalidPasswordException;
import com.zws.service.account.UserNotFoundException;

public class hiCiscoStartPart  extends hiCiscoReport {
  //CAD PARTS

  public String startAPC() {
    String x;
    {} //System.out.println("loading " + number + " from agile..");
    {} //System.out.println("username=" + getUsername()+ " password="+getPassword());
    try { login(getUsername(), getPassword()); }
    catch(UserNotFoundException e) {logout(getUsername()); return ctrlAGILE_PX_NO_USER;}
    catch (InvalidPasswordException e ) {logout(getUsername()); return ctrlAGILE_PX_BAD_LOGIN;}
    catch (Exception e ) {setErrorMessage(e.getMessage()); return ctrlAGILE_PX_ERROR;}
    x= startPart();
    if (!ctrlVIEW.equals(x)) {
      {} //System.out.println("..Error loading " + number + " from agile!");
      return ctrlAGILE_PX_ERROR;
    }
    {} //System.out.println(number + " loaded from agile");
    {} //System.out.println("Starting ProE Auto Part Creation for " + number + "..");
    x = createProEPart();
    if (ctrlAGILE_PX_DUPLICATE_NAME.equals(x)) {
	    {} //System.out.println("Detected Duplicate");
	    logout(getUsername());
	    return x;
	  }
    else if (!ctrlOK.equals(x)) {
	    {} //System.out.println("..Error Auto Creating ProE part: " + number + "!");
	    logout(getUsername());
	    return ctrlAGILE_PX_ERROR;
	  }
    logout(getUsername());
    return ctrlAGILE_PX_OK;
  }

  public String startPart() {
    String agilesource = Properties.get(Names.DEFAULT_AGILE_SOURCE);
    if (null==number || "".equals(number.trim())) {
	    logFormError("err.missing.part.number");
	    return ctrlERROR;
    };
    Metadata agileData = null;
    try {
	    agileStartPart=agile().find(number);
	    {} //System.out.println(agileStartPart);
      return ctrlVIEW;
    }
    catch(Exception e) {
      logFormError("err.no.such.name", number, "Agile");
      return ctrlERROR;
    }
	}

  public String createProEPart() {
	  if (null==agileStartPart) {
	    logFormError("Agile Start Part not Selected");
	    return ctrlERROR;
	  }
	  String server = "DesignState-node-0";
	  String datasource = Properties.get(Names.DEFAULT_DATASOURCE_NAME);
	  IntralinkAccess ilink = IntralinkAccess.getAccess();
	  try {
	    agileStartPart.set("type", agileStartPart.get("agile-class"));
	    agileStartPart.set("new-name", agileStartPart.getName());
	    OriginBase o=null;
	    o = (OriginBase)ilink.createNewProEModel(server, datasource, agileStartPart, getAuthentication());

	    Synchronizer sync = Synchronizer.getClient("DesignState-node-0");
	    {} //System.out.println("synchronizing");
	    {} //System.out.println(agileStartPart.getOrigin());
	    {} //System.out.println(o);
	    sync.record(agileStartPart.getOrigin(), o);


		 hiKLAPublish2Agile form = new hiKLAPublish2Agile();
		 KLAMetadataAdapter source = null;
		 try { source = form.findSourceDesign(agileStartPart); }
		 catch (Exception e) { {} //System.out.println(e.getMessage()); }
		   
		 }
		 if (null==source) return ctrlERR_NOT_SYNCHRONIZED;
	   form.getChosenItems().add(source);
		 form.publishWIP();

     //URL url = new URL("http://vm-ilink-0/report/index.do?event=download&ID="+o.toString());
	   //agile().attachURL(url, o.getName(), number);

     attachFile(number, o);

	   String basename = FileNameUtil.getBaseName(o.getName());
	   o.setName(basename +".drw");
	   URL url = new URL("http://"+zws.Server.getHostName()+"/report/index.do?event=download&ID="+o.toString());
	   agile().attachURL(url, o.getName(), number);

	    /*
	    {} //System.out.println("3");
      String ws=number.replace('.', '_');
      String location = number;
	    {} //System.out.println("4:" + ws);
	    ilink.createWorkspace(o.getServerName(),o.getDatasourceName(), ws, getAuthentication());
	    {} //System.out.println("5");
	    ilink.checkout(o, ws, getAuthentication());

	    {} //System.out.println("6" + location);
	    o.setName(basename +".drw");
	    ilink.export(o, ws, location, getAuthentication());
	    {} //System.out.println("7" + location);
	    try { agile().attachFile(location, o.getName(), number); }
	    catch (Exception e) {
  	    Throwable x;
  	    x = e;
  	    if (null!=e.getCause()) x= e.getCause();
  	    if (null!=x.getCause()) x= x.getCause();
        x.printStackTrace();
	      {} //System.out.println(e.getMessage());
	      throw e;
	    }
	    {} //System.out.println("7");
	    o.setName(basename +".prt");
	    ilink.export(o, ws, location, getAuthentication());
	    agile().attachFile(location, o.getName(), number);
      String igesName = ilink.convertToIGES(o, ws, location, getAuthentication());
      agile().attachFile(location, igesName, number);
	    ilink.destroyWorkspace(o.getServerName(),o.getDatasourceName(), ws, getAuthentication());
	    */
	    return ctrlOK;
	  }
	  catch(Exception e) {
	    Throwable x;
	    if (null!=e.getCause().getCause()) x = e.getCause().getCause();
	    else if (null!=e.getCause()) x = e.getCause();
	    else x = e;
      if (x instanceof DuplicateName) {
   	    {} //System.out.println("Found a duplicate name: " + ctrlAGILE_PX_DUPLICATE_NAME);
        return ctrlAGILE_PX_DUPLICATE_NAME;
      }
      x.printStackTrace();
      {} //System.out.println("Exception!!!!!!!!!!!!!!!");
	    return ctrlERROR;
	  }
	}

	private void attachFile(String agilePart, Origin o) throws Exception {
    if (null==agilePart) return;
    if (null==o) return;
	  IntralinkAccess ilink = IntralinkAccess.getAccess();
    String ws=agilePart.replace('.', '_');
    String location = agilePart;
    ilink.createWorkspace(o.getServerName(),o.getDatasourceName(), ws, getAuthentication());
    ilink.checkout(o, ws, getAuthentication());
    ilink.export(o, ws, location, getAuthentication());
    agile().attachFile(location, o.getName(), agilePart);
	}

  private AgileAccess agile() throws Exception {
    return AgileAccess.getAccess("DesignState-node-0", Properties.get(Names.DEFAULT_AGILE_SOURCE), getAuthentication());
  }

  public String getNumber() { return number; }
  public void setNumber(String s) { number=s; }
  public String getUsername() { return username; }
  public void setUsername(String s) { username=s; }
  public String getPassword() { return password; }
  public void setPassword(String s) { password=s; }

  public Metadata getAgileStartPart() { return agileStartPart; }
  public Metadata getProEStartPart() { return proEStartPart; }
  public Metadata getNewProEPart() { return newProEPart; }


  public String getErrorMessage() { return errorMessage; }
  public void setErrorMessage(String s) { errorMessage=s; }

  private String errorMessage=null;

  private transient Synchronizer synkService = Synchronizer.getClient("DesignState-node-0");
  private String number=null;
  private Metadata agileStartPart = null;
  private Metadata proEStartPart = null;
  private Metadata newProEPart = null;

  private String username=null;
  private String password=null;
  AgileAccess agile = null;
}
