package com.agile.zws.connector;

//import zws.util.{}//Logwriter;

import java.util.*;
import java.io.*;
import java.util.ResourceBundle;

import com.agile.api.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;


public class AgileConnectorPub {
	
	//Logger
	private static Logger logger = Logger.getLogger(AgileConnector.class);
	private static ResourceBundle myResource = null;
	
	IAgileSession session;
	IChange overallChange = null;
	
	String UserName = "";
	String agileUser = "";
	String appServerURL = "";
	String Password = "";
	String wipDirectory = "";
	String zwsFileLocation = "";
	String zwsBinaryFileLocation = "";
	String mappingFileName = "";
	String errorMessage = "";
	
	//New flag to determine if create parts is turned on
	String createPartsFlag = "";
	
	boolean alreadyPending = false;
	//Mapping
	XMLMapping mapper = null;
	Vector agileConstants = null;
	
	//Constants
	Integer itemNumberConstant = ItemConstants.ATT_TITLE_BLOCK_NUMBER;
	Integer itemDescriptionConstant = ItemConstants.ATT_TITLE_BLOCK_DESCRIPTION;
	Integer itemRevisionConstant = ItemConstants.ATT_TITLE_BLOCK_REV;
	Integer itemConstant = null;
	Integer bomQuantityConstant = ItemConstants.ATT_BOM_QTY;
	Integer bomFlagConstant = null;
	
	//Change constants
	Integer changeClass = null;
	
	//part constants
	Integer verifiedFlag = null;
	Integer proI_Version = null;
	Integer bom_Flag = null;
	Integer proI_CreateOn = null;
	Integer proI_CreateBy = null;
	Integer proI_ReleaseLevel = null;
	Integer proI_Desc = null;
	Integer proI_DwgRev = null;
	
	//file folder constants
	Integer lib_Bb_Max_X = null;
	Integer lib_Bb_Max_Y = null;
	Integer lib_Bb_Min_X = null;
	Integer lib_Bb_Min_Y = null;
	Integer lib_Height = null;
	Integer lib_Mkp1_X = null;
	Integer lib_Mkp1_Y = null;
	Integer lib_Mkp2_X = null;
	Integer lib_Mkp2_Y = null;
	Integer lib_Mkp3_X = null;
	Integer lib_Mkp3_Y = null;
	Integer lib_Mkp4_X = null;
	Integer lib_Mkp4_Y = null;
	Integer lib_Pin_Tech = null;
	Integer lib_Units = null;
	
	private static String fileLocation = "";	
	private static String fileSep = System.getProperty("file.separator");
	
	public AgileConnectorPub() {
	
		logger.info("Starting AgileConnector...");
		//DOMConfigurator.configure("log.xml");
		/*
		try {
	
			Locale defaultLocale = Locale.getDefault();
			myResource  = ResourceBundle.getBundle("AgileConnector",defaultLocale);
	
			//get connection parameters
			appServerURL = myResource.getString("url");
			UserName = myResource.getString("user");
			Password = myResource.getString("password");
			fileLocation = myResource.getString("fileLocation");
			wipDirectory = myResource.getString("wipDirectory");
		}
		catch (MissingResourceException ex )
		{
			{}//Logwriter.printOnConsole("missing resource " +ex);

		}
		*/
		//mappingFileName = "D:\\temp\\AttributeMapping_v1_0.xml";
		//mapper = new XMLMapping(mappingFileName);
		//mapper.loadAttrMapping();
		//agileConstants = mapper.getXMLConstants();
		//this.loadConstants(agileConstants);

	}
	
	public AgileConnectorPub(String test) {
	
		logger.info("Starting AgileConnector...");
		try {

			Locale defaultLocale = Locale.getDefault();
			myResource  = ResourceBundle.getBundle("AgileConnector",defaultLocale);

			//get connection parameters
			appServerURL = myResource.getString("url");
			UserName = myResource.getString("user");
			Password = myResource.getString("password");
			fileLocation = myResource.getString("fileLocation");
			wipDirectory = myResource.getString("wipDirectory");
		}
		catch (MissingResourceException ex )
		{
			{}//Logwriter.printOnConsole("missing resource " +ex);

		}

	}
	
	public AgileConnectorPub(String user, String pass, String xmlFile) {
	
		logger.info("Starting AgileConnector...");

		try {

			Locale defaultLocale = Locale.getDefault();
			myResource  = ResourceBundle.getBundle("AgileConnector",defaultLocale);

			//get connection parameters
			appServerURL = myResource.getString("url");
			UserName = user;
			Password = pass;
			zwsFileLocation = xmlFile;
			fileLocation = myResource.getString("fileLocation");
			wipDirectory = myResource.getString("wipDirectory");
			mappingFileName = myResource.getString("mappingFileName");
		}
		catch (MissingResourceException ex )
		{
			{}//Logwriter.printOnConsole("missing resource " +ex);

		}
		
		//mapper = new XMLMapping(mappingFileName);

	}
	public boolean connect (String uri, String port, String user, String pass) {
		boolean connected = false;
		
		this.appServerURL = uri + ":" + port + "/Agile";
		this.UserName = user;
		this.Password = pass;
		
		connected = init();
		
		return connected;
		
	}
	
	public boolean connect (String user, String pass) {
		boolean connected = false;
	
		loadProperties();
		this.UserName = user;
		this.Password = pass;
	
		connected = init();
	
		return connected;
	
	}
	
	public StringBuffer publish (String user, String pass, String xmlFile) {
		try {
			StringBuffer xmlOutput = new StringBuffer("");
			XMLItem[] pubItems = null;
			boolean connected = false;
			UserName = user;
			Password = pass;
			zwsFileLocation = xmlFile;
			
			//load properties
			loadProperties();
			
			//load constants
			//loadConstants(mapper.getConstantMapping());
			
			//connect
			connected = init();
			
			if (connected) {
				//publish
				{}//Logwriter.printOnConsole("Beginning Publish of XML file....");
				pubItems = processXML(xmlFile);
				{}//Logwriter.printOnConsole("Done Publishing XML File....");
				if (pubItems != null) {
					//generate XML from pubItems
					{}//Logwriter.printOnConsole("Beginning XML Output....");
					xmlOutput.append("<agile-data>");
					StringBuffer xmlOutput2 = createOutputXML(pubItems);
					xmlOutput.append(xmlOutput2);
					xmlOutput.append("</agile-data>");
				}
			}
			else {
				//failed to connect
				xmlOutput = new StringBuffer(errorMessage);
			}
					
			return xmlOutput;
		}
		catch (Exception ex) {
			logger.info("publish error : " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}
	
	public StringBuffer publish (String xmlFile) {
			try {
			StringBuffer xmlOutput = new StringBuffer("");
			XMLItem[] pubItems = null;
			zwsFileLocation = xmlFile;

			//load constants
			//loadConstants(mapper.getConstantMapping());
			
			//check to see if session is connected
			if (session != null) {
				if (session.isOpen()) {
					//publish
					{}//Logwriter.printOnConsole("Beginning Publish of XML file....");
					pubItems = processXML(xmlFile);
					{}//Logwriter.printOnConsole("Done Publishing XML File....");
					if (pubItems != null) {
						//generate XML from pubItems
						{}//Logwriter.printOnConsole("Beginning XML Output....");
						xmlOutput.append("<agile-data>");
						StringBuffer xmlOutput2 = createOutputXML(pubItems);
						xmlOutput.append(xmlOutput2);
						xmlOutput.append("</agile-data>");
					}
				}
	
			}
			else {
				//failed to connect
				xmlOutput = new StringBuffer("");
				xmlOutput.append("<agile-data>");
				xmlOutput.append(errorMessage);
				xmlOutput.append("</agile-data>");
			}
				
			return xmlOutput;
		}
		catch (Exception ex) {
			logger.info("publish error : " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}
	
	public void printOutInputStream (InputStream xmlStream) {
		
		OutputStream os;
		FileOutputStream fos;
		byte[] buffer = new byte[4096];
		int c;
		
		
		try {
				
			fos = new FileOutputStream("D:\\zws\\AgileConnector\\inputXML.xml");
			while ((c = xmlStream.read()) != -1) {
			   fos.write(c);
			}
			xmlStream.close();
			fos.close();
		}
		catch (IOException ioEx) {
			logger.error("ioEx : " + ioEx.getMessage());
		}
	}
	
	public StringBuffer publish (InputStream xmlStream) {
			try {
			StringBuffer xmlOutput = new StringBuffer("");
			XMLItem[] pubItems = null;
			
			
			//xmlStream.
			
			//load constants
			//loadConstants(mapper.getConstantMapping());
		
			//check to see if session is connected
			if (session != null) {
				if (session.isOpen()) {
					//publish
					{}//Logwriter.printOnConsole("Beginning Publish of XML file....");
					pubItems = processXML(xmlStream);
					{}//Logwriter.printOnConsole("Done Publishing XML File....");
					if (pubItems != null) {
						//generate XML from pubItems
						{}//Logwriter.printOnConsole("Beginning XML Output....");
						xmlOutput.append("<agile-data>");
						StringBuffer xmlOutput2 = createOutputXML(pubItems);
						if (xmlOutput2 != null) {
							xmlOutput.append(xmlOutput2);
						}
						else {
							xmlOutput.append(errorMessage);
						}
						xmlOutput.append("</agile-data>");
					}
				}

			}
			else {
				//failed to connect
				xmlOutput = new StringBuffer("");
				xmlOutput.append("<agile-data>");
				xmlOutput.append(errorMessage);
				xmlOutput.append("</agile-data>");
			}
			
			return xmlOutput;
		}
		catch (Exception ex) {
			logger.info("publish error : " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}
	
	public void loadProperties () {
		try {
			
			Locale defaultLocale = Locale.getDefault();
			myResource  = ResourceBundle.getBundle("AgileConnector",defaultLocale);
			appServerURL = myResource.getString("url");
			this.wipDirectory = myResource.getString("wipDirectory");
			
			//new get create parts flag
			createPartsFlag = myResource.getString("createPartsFlag");
			
			String val;
			//val = myResource.getString("Change_Class");
			//if (val != null) {
			//	changeClass = Integer.valueOf(val);
			//}
			val = myResource.getString("ProI_Version");
			if (val != null) {
				proI_Version = Integer.valueOf(val);
			}
			val = myResource.getString("Verified_Flag");
			if (val != null) {
				verifiedFlag = Integer.valueOf(val);
			}
			val = myResource.getString("BOM_Flag");
			if (val != null) {
				bom_Flag = Integer.valueOf(val);
			}
			val = myResource.getString("ProI_CreateOn");
			if (val != null) {
				proI_CreateOn = Integer.valueOf(val);
			}
			val = myResource.getString("ProI_CreateBy");
			if (val != null) {
				proI_CreateBy = Integer.valueOf(val);
			}
			val = myResource.getString("ProI_ReleaseLevel");
			if (val != null) {
				proI_ReleaseLevel = Integer.valueOf(val);
			}
			val = myResource.getString("ProI_Desc");
			if (val != null) {
				proI_Desc = Integer.valueOf(val);
			}
			val = myResource.getString("ProI_DwgRev");
			if (val != null) {
				proI_DwgRev = Integer.valueOf(val);
			}
			
			val = myResource.getString("Lib_Bb_Max_X");
			if (val != null) {
				lib_Bb_Max_X = Integer.valueOf(val);
			}
			val = myResource.getString("Lib_Bb_Max_Y");
			if (val != null) {
				lib_Bb_Max_Y = Integer.valueOf(val);
			}
			val = myResource.getString("Lib_Bb_Min_X");
			if (val != null) {
				lib_Bb_Min_X = Integer.valueOf(val);
			}
			val = myResource.getString("Lib_Bb_Min_Y");
			if (val != null) {
				lib_Bb_Min_Y = Integer.valueOf(val);
			}
			val = myResource.getString("Lib_Height");
			if (val != null) {
				lib_Height = Integer.valueOf(val);
			}
			val = myResource.getString("Lib_Mkp1_X");
			if (val != null) {
				lib_Mkp1_X = Integer.valueOf(val);
			}
			val = myResource.getString("Lib_Mkp1_Y");
			if (val != null) {
				lib_Mkp1_Y = Integer.valueOf(val);
			}
			val = myResource.getString("Lib_Mkp2_X");
			if (val != null) {
				lib_Mkp2_X = Integer.valueOf(val);
			}
			val = myResource.getString("Lib_Mkp2_Y");
			if (val != null) {
				lib_Mkp2_Y = Integer.valueOf(val);
			}
			val = myResource.getString("Lib_Mkp3_X");
			if (val != null) {
				lib_Mkp3_X = Integer.valueOf(val);
			}
			val = myResource.getString("Lib_Mkp3_Y");
			if (val != null) {
				lib_Mkp3_Y = Integer.valueOf(val);
			}
			val = myResource.getString("Lib_Mkp4_X");
			if (val != null) {
				lib_Mkp4_X = Integer.valueOf(val);
			}
			val = myResource.getString("Lib_Mkp4_Y");
			if (val != null) {
				lib_Mkp4_Y = Integer.valueOf(val);
			}
			val = myResource.getString("Lib_Pin_Tech");
			if (val != null) {
				lib_Pin_Tech = Integer.valueOf(val);
			}
			val = myResource.getString("Lib_Units");
			if (val != null) {
				lib_Units = Integer.valueOf(val);
			}
			
		}
		catch (MissingResourceException ex )
		{
			{}//Logwriter.printOnConsole("missing resource " +ex);
			this.setErrorMessage(ex.getMessage());

		}
		
	}
	
	
	public boolean init() {
		try
		{
			IAgileSession s = createSession( appServerURL, UserName, Password);	
			if (s==null )
				return false;
			else
				return true;
		} catch(APIException apie) 
		{
   
			logger.error("API Ex : " + apie.getMessage());
			Throwable apiRootCause = apie.getRootCause();
			logger.error("API Root Cause : " + apiRootCause.getMessage());
			setErrorMessage(apiRootCause.getMessage());
			return false;
		}
	
		
	}
	
	public boolean init(String user, String pass) {
		try
		{
			this.UserName = user;
			this.Password = pass;
			IAgileSession s = createSession( appServerURL, UserName, Password);	
			if (s==null )
				return false;
			else
				return true;
		} catch(APIException apie) 
		{
   
			logger.error(apie.getMessage());
			Throwable apiRootCause = apie.getRootCause();
			setErrorMessage(apiRootCause.getMessage());
			return false;
		}
	
		
	}
	
	public IAgileSession createSession(String url, String user, String pass) throws APIException {
	
		//logger.info("Create Session with : " +url +" " + user +" " + pass);
		AgileSessionFactory factory = AgileSessionFactory.getInstance(url);
		HashMap map = new HashMap();
		map.put(AgileSessionFactory.USERNAME, user);
		map.put(AgileSessionFactory.PASSWORD, pass);
		session = factory.createSession(map);
		//logger.info("Create Session Return : "+ session);
		return session;
	}
	
	public boolean agileConnect(String url, String user, String pass) {
		
		String testUrl = "http://plm.agilesoft.com/Agile";
		try {
			logger.info("url : " + url);
			AgileSessionFactory factory = AgileSessionFactory.getInstance(url);
			//test factory
			//AgileSessionFactory factory = AgileSessionFactory.getInstance(testUrl);
			HashMap map = new HashMap();
			map.put(AgileSessionFactory.USERNAME, user);
			map.put(AgileSessionFactory.PASSWORD, pass);
			session = factory.createSession(map);
			boolean connected = false;
			connected = session.isOpen();
			return connected;
		}
		catch (APIException apiEx) {
			logger.error("Login error : " + apiEx.getMessage());
			Throwable apiRootCause = apiEx.getRootCause();
			setErrorMessage(apiRootCause.getMessage());
			return false;
		}
	}
	
	public void disconnect () {
		
		boolean connected;
		
		try {
			session.close();
			connected = session.isOpen();
			logger.info("Connected : " + connected);
			
		}
		catch (APIException apiEx){
			logger.error("Disconnect error : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
		}
	}
	
	public XMLItem[] processXML (String xmlFile) {
		XMLItem[] pubItems = null;
		IntralinkParserPub parser = new IntralinkParserPub(xmlFile, logger, this);
		//IntralinkParser parser = new IntralinkParser(xmlFile, logger, this, mapper);
		pubItems = parser.parseItems(this.createPartsFlag);
		return pubItems;
	}
	
	public XMLItem[] processXML (InputStream xmlStream) {
		XMLItem[] pubItems = null;
		IntralinkParserPub parser = new IntralinkParserPub(logger, this);
		//IntralinkParser parser = new IntralinkParser(xmlFile, logger, this, mapper);
		pubItems = parser.parseItems(xmlStream, this.createPartsFlag);
		return pubItems;
	}
	
	public boolean checkItemExistance(String itemNumber) {
		
		boolean exists = false;
		try {
			IItem agileUpdateItem = (IItem)session.getObject(IItem.OBJECT_TYPE, itemNumber);
			if (agileUpdateItem != null) {
				//logger.info("item : " + itemNumber + " exists...");
				exists = true; 
			}
			else {
				logger.info("item " + itemNumber + " does not exist...");
				exists = false;
			}
		}
		catch (APIException apiEx) {
			logger.error("checkItemExistance : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
			return false;
		}
		return exists;
	}
	
	public IItem agileInitPart (String num){
	
		try {
			//load part
			IItem newPart = (IItem)session.getObject(ItemConstants.CLASS_PART, num);
			
			if (newPart == null) {
				//part not loaded create part
				newPart = (IItem)session.createObject(ItemConstants.CLASS_PART, num);
			}
			//logger.info("loaded item : " + num);
			return newPart;
		}
		catch (APIException apiEx) {
			logger.error("agileInitPart : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
			return null;
		}	
		
	}
	
	public IItem agileCreatePart (String num, String sub, String desc){
		IItem newPart = null;
		Integer subClass = null;
		try {
			Map params = new HashMap();
			params.put(ItemConstants.ATT_TITLE_BLOCK_NUMBER, num);
			params.put(ItemConstants.ATT_TITLE_BLOCK_DESCRIPTION, desc);
			
			//Get the Resistor subclass ID
			if (sub != null) {
				IAdmin admin = session.getAdminInstance();
				IAgileClass[] classes = admin.getAgileClasses(IAdmin.CONCRETE);
				for (int i = 0; i < classes.length; i++) {
					if (classes[i].getName().equals(sub)) {
						subClass = (Integer)classes[i].getId();
					}
				}
			}
			
			if (subClass != null) {
				newPart = (IItem)session.createObject(subClass, params);
			}
			else {
				newPart = (IItem)session.createObject(ItemConstants.CLASS_PART, params);
			}
			return newPart;
		}
		catch (APIException apiEx) {
			logger.error("agileCreatePart : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
			return null;
		}	
	
	}
	
	public IItem agileInitPart (String num, String rev){
	
		try {
			//load part
			IItem newPart = (IItem)session.getObject(ItemConstants.CLASS_PART, num);
		
			if (newPart != null) {
				//part loaded set revision
				newPart.setRevision(rev);
			}
			return newPart;
		}
		catch (APIException apiEx) {
			logger.error("agileInitPart with Rev : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
			return null;
		}	
	
	}
	
	public IItem agileInitPendingPart (String num){
	
		try {
			//load part
			IItem newPart = (IItem)session.getObject(ItemConstants.CLASS_PART, num);
	
			if (newPart != null) {
				//part loaded set to first pending revision, does not work for multiple pendin revs
				ITable pendingChanges = newPart.getTable(ItemConstants.TABLE_PENDINGCHANGES);
				boolean isEmpty = pendingChanges.isEmpty();
				if (!isEmpty) {
					//pending changes exist
					Iterator pendIter = pendingChanges.iterator();
				    IRow pendingChangeRow = (IRow)pendIter.next();
				    IChange pendingChange = (IChange)pendingChangeRow.getReferent();
					newPart.setRevision(pendingChange);
				}
			}
			return newPart;
		}
		catch (APIException apiEx) {
			logger.error("agileInitPendingPart : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
			return null;
		}	

	}
	
	public IItem setItemRevision (IItem item, IChange revChange){
	
		try {
			item.setRevision(revChange);
			
			return item;
		}
		catch (APIException apiEx) {
			logger.error("setItemRevision : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
			return null;
		}	
	
	}
	
	public IChange getChangeForRev (IItem item, String rev, boolean pending) {
		
		IChange theChange = null;
		ITable changes = null;
		String changeRev = "";
		
		try {
			//logger.info("getChangeForRev Pending : " + pending);
			if (pending) {
				changes = item.getTable(ItemConstants.TABLE_PENDINGCHANGES);
			} 
			else {
				changes = item.getTable(ItemConstants.TABLE_CHANGEHISTORY);
				
			}
			if (changes != null) {
				boolean isEmpty = changes.isEmpty();
				if (!isEmpty) {
					//logger.info("There are rows in table...");
					//there are released changes
					//sort table by change released date 
					if (pending) {
						//there are pending changes
						alreadyPending = true;
					}
					Iterator changesIter = changes.iterator();
					while (changesIter.hasNext()) {
						IRow changeRow = (IRow)changesIter.next();
						if (!pending) {
							changeRev = (String)changeRow.getValue(ItemConstants.ATT_CHANGE_HISTORY_REV);
						} 
						else {
							changeRev = (String)changeRow.getValue(ItemConstants.ATT_PENDING_CHANGES_PROPOSED_REV);
							int changeRevLen = changeRev.length();
							changeRev = changeRev.substring(1,changeRevLen-1);
						}
						//logger.info("change rev : " + changeRev);
						if (changeRev.equals(rev)) {
							//the Change matches the rev
							theChange = (IChange)changeRow.getReferent();
							break;
						}
					}
					return theChange;
				}
				else {
					//no relesed changes
					if (pending) {
						alreadyPending = false;
					}
					return null;
				}
			}
			else {
				//change table not loaded
				return null;
			}
		}
		catch (APIException apiEx) {
			logger.error("getChangeForRev : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
			return null;
		}
	
		
	}
	
	public IChange[] getPendingChanges (IItem item) {
	
		String pendingChangeNumber = "";
		IChange[] pendingChanges = null;
		
		try {
			
			//get Pending Change Table
			ITable pendingChangeTable = item.getTable(ItemConstants.TABLE_PENDINGCHANGES);
			boolean isEmpty = pendingChangeTable.isEmpty();
			int i = 0;
			if (!isEmpty){
				//there are pending changes
				alreadyPending = true;
				Iterator pendingIter = pendingChangeTable.iterator();
				while (pendingIter.hasNext()) {
					IRow pendingRow = (IRow)pendingIter.next();
					IChange pendingChange = (IChange)pendingRow.getReferent();
					pendingChanges[i] = pendingChange;
					i++;
				}
				//logger.info("Pending Changes Count : " + pendingChanges.length);
			}
			else {
				//no pending changes
				alreadyPending = false;
				return null;
			}
			return pendingChanges;
		}
		catch (APIException apiEx) {
			logger.error("getPendingChanges : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
			return null;
		}		
		
	}
	
	public IItem agilePublishBOM (String parentNum, String parentRevision, Vector childNums) {
		IItem parentItem;
		IChange parentChange = null;
		ITable changeTable;
		boolean hasPendingChange = false;
		boolean alreadyOnSpecifiedChange = false;
		boolean createChange = false;
		boolean isReleased = false;
		
		try {
			//load Parent
			parentItem = agileInitPart(parentNum);
			
			logger.info("Overall Change : " + overallChange);
			
			if (parentItem == null){
				//parent does not exist create it
				logger.error("AgilePublishBOM : Parent does not exist and could not be created, exiting process...");
				return null;
			}
			else {
				logger.info("Begin Publish BOM....");
				
				//get change for rev
				//first check released
				parentChange = this.getChangeForRev(parentItem, parentRevision, false);
				if (parentChange == null) {
					//no released change
					//check pending
					parentChange = this.getChangeForRev(parentItem, parentRevision, true);
					if (parentChange == null) {
						//need to create change
						createChange = true;
						isReleased = false;
					}
					else {
						//have pending Change
						createChange = false;
						isReleased = false;
						alreadyOnSpecifiedChange = true;
					}
				}
				else {
					//have change but is released so error
					createChange = false;
					isReleased = true;
					logger.info("Rev " + parentRevision + " for item " + parentNum + " is released.");
					return null;
				}
				
				
				//check revision
				logger.info("Part " + parentItem.getName() + " current rev is : " + parentItem.getRevision());
				logger.info("createChange : " + createChange);
				logger.info("isReleased : " + isReleased);
				logger.info("alreadyOnSpecifiedChange : " + alreadyOnSpecifiedChange);
				
				//if createChange is true then check for existing change
				if (createChange) {
					logger.info("might need to create change...");
					//check to see if parentChange is not null because it was passed to function
					if (overallChange == null) {
						logger.info("no overall change exists so I am creating one....");
						//need to create new change
						parentChange = agileCreateChange(null, "ECO");
						logger.info("setting overall Change to : " + parentChange);
						this.overallChange = parentChange;
						addAffectedItem(parentChange, parentItem, parentRevision);
					}	
					else {
						//we already created an ECO so use it
						//add parent as affected item
						addAffectedItem(overallChange, parentItem, parentRevision);
					}
					
				}
				else {
					//already on pending change
					
				}
				logger.info("ParentChange : " + parentChange);
				
				//set revision of parent item
				if (parentChange != null) {
					logger.info("setRevision of parentItem to : " + parentChange);
					parentItem.setRevision(parentChange);
				}
				
				//add redlines
				parentItem.setManufacturingSite(ManufacturingSiteConstants.COMMON_SITE);
				
				agilePutChildren(parentItem, childNums);
				
				//return;
				/*
				
				//put item on affected item of change
				
				*/
				
				/*
				String parentItemRevision = parentItem.getRevision();
				if (parentItemRevision.equals("Introductory")){
				
					//check if change already exists
					if (publishChange == null) {
						//need to create new change
						publishChange = agileCreateChange("C00999");	
					}	
				}
				*/
			}
			
			//return parentChange;
			return parentItem;
			
		}
		catch (Exception ex) {
			logger.error("AgilePublishBOM Java Error : " + ex.getMessage());
			setErrorMessage(ex.getMessage());
			return null;
		}	
		
		
	}
	
	public IItem agilePublishBOM (XMLItem pubItem, IItem parentItem) {
		//IItem parentItem;
		IChange parentChange = null;
		ITable changeTable;
		boolean hasPendingChange = false;
		boolean alreadyOnSpecifiedChange = false;
		boolean createChange = false;
		boolean isReleased = false;
		alreadyPending = false;
		String errMsg = null;
	
		try {
			//get logged in user
			//this.agileUser = session.getCurrentUser().getName();
			
			//load Parent
			String parentNum = pubItem.getItemNumber();
			//parentItem = agileInitPart(parentNum);
			String parentRevision = pubItem.getRevision();
			String parentVersion = pubItem.getProIVersion();
			logger.info("pubItem Number : " + parentNum);
			logger.info("pubItem revision : " + parentRevision);
			logger.info("pubItem version : " + parentVersion);
			//logger.info("Overall Change : " + overallChange);
		
			if (parentItem == null){
				//parent does not exist create it
				logger.error("AgilePublishBOM : Parent does not exist and could not be created, exiting process...");
				return null;
			}
			else {
				//logger.info("Begin Publish BOM....");
				
				//get version from Agile Item
				
			
				//get change for rev
				//first check released
				parentChange = this.getChangeForRev(parentItem, parentRevision, false);
				if (parentChange == null) {
					//no released change
					//check pending
					parentChange = this.getChangeForRev(parentItem, parentRevision, true);
					if (parentChange == null) {
						//need to create change
						createChange = true;
						isReleased = false;
					}
					else {
						//have pending Change
						createChange = false;
						isReleased = false;
						alreadyOnSpecifiedChange = true;
						
						//need to compare version to see if publish is needed
						String agileItemVersion = (String)parentItem.getValue(proI_Version);
						if (agileItemVersion != null) {
							logger.info("agileItemVersion : " + agileItemVersion);
							if (agileItemVersion.equalsIgnoreCase(parentVersion)) {
								//version and rev match so do not publish
								logger.info("Revision and Version match, do not publish...");
								pubItem.setErrorMessage("Revision and Version match in Agile, cannot publish.");
								//return null;
							}
						}
					}
				}
				else {
					//have change but is released so error
					createChange = false;
					isReleased = true;
					logger.info("Rev " + parentRevision + " for item " + parentNum + " is released.");
					pubItem.setErrorMessage("Part/Rev is not Pending, Redlining and File Mgmt not supported.");
					return null;
				}
			
				//check for muliple pending changes
				//logger.info("alreadyPending : " + alreadyPending);
				if (alreadyPending) {
					//there is already a pending change
					if (createChange) {
						//would be putting the item on multiple pending changes
						//pubItem.setErrorMessage("Part already on a Pending Change with different pending revision. Cannot redline.");
						//return null;
						//create another ECO
					}
				}
				
				//check revision
				logger.info("Part " + parentItem.getName() + " current rev is : " + parentItem.getRevision());
				//logger.info("createChange : " + createChange);
				//logger.info("isReleased : " + isReleased);
				//logger.info("alreadyOnSpecifiedChange : " + alreadyOnSpecifiedChange);
			
				//if createChange is true then check for existing change
				if (createChange) {
					logger.info("might need to create change...");
					//check to see if parentChange is not null because it was passed to function
					if (overallChange == null) {
						logger.info("no overall change exists so I am creating one....");
						//need to create new change
						parentChange = agileCreateChange(null, "ECO");
						//logger.info("setting overall Change to : " + parentChange);
						this.overallChange = parentChange;
						addAffectedItem(parentChange, parentItem, parentRevision);
					}	
					else {
						//we already created an ECO so use it
						//add parent as affected item
						addAffectedItem(overallChange, parentItem, parentRevision);
					}
				
				}
				else {
					//already on pending change
				
				}
				//logger.info("ParentChange : " + parentChange);
			
				//set revision of parent item
				if (parentChange != null) {
					//logger.info("setRevision of parentItem to : " + parentChange);
					parentItem.setRevision(parentChange);
				}
				else {
					//parentChange is null and we have used overall change
					parentItem.setRevision(overallChange);
				}
				logger.info("Part " + parentItem.getName() + " current rev now is : " + parentItem.getRevision());
				
				//update Part User Attributes
				updatePartAttributes(parentItem, pubItem);
				
				//add redlines
				parentItem.setManufacturingSite(ManufacturingSiteConstants.COMMON_SITE);
				XMLItem[] pubItemChildren = pubItem.getChildren();
				if (pubItemChildren != null) {
					int childrenCount = pubItemChildren.length;
					//logger.info("childrenCount : " + childrenCount);
					if (childrenCount > 0) {
						logger.info("there are children to put....");
						try {
							agilePutChildren(parentItem, pubItemChildren);
						}
						catch (Exception ex) {
							logger.error("called agilePutChildren error " + ex.getMessage());
							setErrorMessage(ex.getMessage());
						}
					}
					else {
						//logger.info("no children...");
					}
				}
				else {
					//logger.info("no children...");
				}
				
				//set attributes
				//HashMap setAttributes = pubItem.getSetAttributes();
				
				//add file attachment
				
				try {
					String binaryPath = pubItem.getBinaryPath();
					if (binaryPath != null) {
					
						if ((!binaryPath.equals(""))) {
							{}//Logwriter.printOnConsole("publishBOM adding File....");
							File checkInFile = new File(binaryPath);
							String fileName = checkInFile.getName();
							logger.info("fileName : " + fileName);
							addFileFolder(parentItem, binaryPath, fileName, pubItem);
						}
						
					}
				}
				catch (APIException apiEx) {
					{}//Logwriter.printOnConsole("publishBOM addFileFolder APIException : " + apiEx.getMessage());
					logger.error("add File Attachment error : " + apiEx.getMessage());
					Throwable rootCause = apiEx.getRootCause();
					if (rootCause != null) {
						logger.error("add File Attachment rootCause : " + rootCause.getMessage());
					}
				}
				
				
				//return;
				
				pubItem.setInfoMessage(parentNum + " published.");
			}
		
			//return parentChange;
			String pubItemErrorMsg = getErrorMessage();
			if (pubItemErrorMsg != null) {
				if (pubItemErrorMsg.length() > 0) {
					pubItem.setErrorMessage(pubItemErrorMsg);
				}
			}
			return parentItem;
		
		}
		catch (Exception ex) {
			logger.error("AgilePublishBOM Java Error : " + ex.getMessage());
			pubItem.setErrorMessage(ex.getMessage());
			setErrorMessage(ex.getMessage());
			return null;
		}	
	
	
	}
	
	private boolean AgileCheckComponentNumber (Vector ChildrenList, String Number) {
		Iterator iFields;
		boolean BOMNumberCheck = true;
		int childrenCount = ChildrenList.size();
		for (int i = 0; i < childrenCount; i++) {
			String childNumber = (String)ChildrenList.get(i);
			if (childNumber.equalsIgnoreCase(Number)) {
				//oField.setName("0");
				ChildrenList.set(i,"IGNORE");
				//the BOM component number matches a number from Catia
				BOMNumberCheck = false;
				return BOMNumberCheck;
			}
		}
		return BOMNumberCheck;
	}
	
	private boolean AgileCheckComponentNumber (XMLItem[] ChildrenList, String Number) {
		try {
			XMLItem child = null;
			boolean BOMNumberCheck = true;
			//logger.info("ChildrenList : " + ChildrenList);
			if (ChildrenList != null) {
				int childrenCount = ChildrenList.length;
				for (int i = 0; i < childrenCount; i++) {
					child = ChildrenList[i];
					//logger.info("child : " + child);
					if (child != null) {
						String childNumber = child.getItemNumber();
						//logger.info("childNumber : " + childNumber);
						//logger.info("Number : " + Number);
						if (childNumber.equalsIgnoreCase(Number)) {
							//oField.setName("0");
							child.setBomMessage("UPDATE");
							//ChildrenList.set(i,"IGNORE");
							//the BOM component number matches a number from Pro I
							BOMNumberCheck = false;
							return BOMNumberCheck;
						}
					}
				}
			}
			return BOMNumberCheck;
		}
		catch (Exception ex) {
			logger.error("AgileCheckComponentNumber error : " + ex.getMessage());
			//ex.printStackTrace();
			setErrorMessage(ex.getMessage());
			//System.exit(0);
			return false;
		}
	}
	
	private Integer AgileGetComponentQuantity (XMLItem[] ChildrenList, String Number) {
		Iterator iFields;
		Integer componentQuantity = new Integer(0);
		int childrenCount = ChildrenList.length;
		for (int i = 0; i < childrenCount; i++) {
			XMLItem child = ChildrenList[i];
			String childNumber = child.getItemNumber();
			if (childNumber.equalsIgnoreCase(Number)) {
				//oField.setName("0");
				componentQuantity = child.getQuantity();
				return componentQuantity;
			}
		}
		return componentQuantity;
	}
	
	public void updatePartAttributes(IItem agileItem, XMLItem item) {
		
		try {
			//logger.info("begin update of Part attributes....");
			//set
			String val = null;
			//logger.info("getting XML attribute....");
			val = item.getProIReleaseLevel();
			//logger.info("received XML attribute....");
			updateAgileObject(agileItem, proI_ReleaseLevel, val);
			val = item.getProIDesc();
			updateAgileObject(agileItem, proI_Desc, val);
			val = item.getProIDrwgRevision();
			updateAgileObject(agileItem, proI_DwgRev, val);
			val = item.getProICreatOn();
			updateAgileObject(agileItem, proI_CreateOn, val);
			val = item.getProICreatBy();
			updateAgileObject(agileItem, proI_CreateBy, val);
			val = item.getProIVersion();
			updateAgileObject(agileItem, proI_Version, val);
			
			//set verified
			boolean libPart = item.getLibPart();
			if (libPart) {
				logger.info(item.getItemNumber() + " is a lib part....");
				updateAgileObject(agileItem, verifiedFlag, "Not Verified");
			}
			//logger.info("end of update of Part attributes....");
						
		}
		catch (Exception ex) {
			logger.error("updatePartAttributes : " + ex.getMessage());
			setErrorMessage(ex.getMessage());
		}
	}
	
	public void updateAgileObject (IDataObject agileObject, Integer id, String val) {
		try {
			//set
			if (val != null) {
				if (!(val.equals(""))) {
					//logger.info("updating Agile attribute : " + id);
					agileObject.setValue(id, val);		
					//logger.info("updated Agile attribute : " + id);
				}
			}
		}
		catch (APIException apiEx) {
			logger.error("updateAgileObject : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
		}
	}
	
	public void updateFolderAttributes(IFileFolder agileFolder, XMLItem item) {
		
		try {
			//logger.info("begin update of FileFolder attributes....");
			//set
			String val = null;
			val = item.getLibMaxX();
			updateAgileObject(agileFolder, lib_Bb_Max_X, val);
			val = item.getLibMaxY();
			updateAgileObject(agileFolder, lib_Bb_Max_Y, val);
			val = item.getLibMinX();
			updateAgileObject(agileFolder, lib_Bb_Min_X, val);
			val = item.getLibMinY();
			updateAgileObject(agileFolder, lib_Bb_Min_Y, val);
			val = item.getLibHeight();
			updateAgileObject(agileFolder, lib_Height, val);
			val = item.getLibMkp1X();
			updateAgileObject(agileFolder, lib_Mkp1_X, val);
			val = item.getLibMkp1Y();
			updateAgileObject(agileFolder, lib_Mkp1_Y, val);
			val = item.getLibMkp2X();
			updateAgileObject(agileFolder, lib_Mkp2_X, val);
			val = item.getLibMkp2Y();
			updateAgileObject(agileFolder, lib_Mkp2_Y, val);
			val = item.getLibMkp3X();
			updateAgileObject(agileFolder, lib_Mkp3_X, val);
			val = item.getLibMkp3Y();
			updateAgileObject(agileFolder, lib_Mkp3_Y, val);
			val = item.getLibMkp4X();
			updateAgileObject(agileFolder, lib_Mkp4_X, val);
			val = item.getLibMkp4Y();
			updateAgileObject(agileFolder, lib_Mkp4_Y, val);
			val = item.getLibPinTech();
			updateAgileObject(agileFolder, lib_Pin_Tech, val);
			val = item.getLibUnits();
			updateAgileObject(agileFolder, lib_Units, val);
			//logger.info("end of update of FileFolder attributes....");
		}
		catch (Exception ex) {
			logger.error("updateFolderAttributes : " + ex.getMessage());
			setErrorMessage(ex.getMessage());
		}
	}
	
	public void putChild (ITable redlineBOMTable, String itemNumber) {
	
		try {
			//loop through table to see if item already on table but redlined
			boolean isEmpty = redlineBOMTable.isEmpty();
			logger.info("Check for redundant redlines....");
			if (!isEmpty) {
				logger.info("There are redline rows....");
				Iterator redlineIter = redlineBOMTable.iterator();
				while (redlineIter.hasNext()) {
					IRow redlineRow = (IRow)redlineIter.next();
					String redlineItemNum = (String)redlineRow.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER);
					if (redlineItemNum.equals(itemNumber)) {
						logger.info("Item : " + itemNumber + " already on bom table.");
						return;
						//see if row is redlined
						//boolean isRedlinedRemoved = redlineRow.isFlagSet(ItemConstants.FLAG_IS_REDLINE_REMOVED);
						//boolean isRedlinedUpdated = redlineRow.isFlagSet(ItemConstants.FLAG_IS_REDLINE_MODIFIED);
						//boolean isRedlinedAdded = redlineRow.isFlagSet(ItemConstants.FLAG_IS_REDLINE_ADDED);
						//logger.info("Item : " + itemNumber + " is redlined???");
						//logger.info("isRedlinedRemoved : " + isRedlinedRemoved);
						//logger.info("isRedlinedUpdated : " + isRedlinedUpdated);
						//logger.info("isRedlinedAdded : " + isRedlinedAdded);
						//if (isRedlinedRemoved) {
							//undo redline
						//}
					}
				}
			}
			HashMap map = new HashMap();
			map.put(ItemConstants.ATT_BOM_ITEM_NUMBER, itemNumber);
			map.put(ItemConstants.ATT_BOM_BOM_NOTES, "ZWS-PROI");
			//map.put(bomFlagConstant, "ZWS-PROI");
			redlineBOMTable.createRow(map);
		}
		catch (APIException apiEx) {
			logger.error("putChild : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
		}	
		
		
	}
	
	public void putChild (ITable redlineBOMTable, String itemNumber, Integer qty) throws APIException {
	
		try {
			//loop through table to see if item already on table but redlined
			boolean isEmpty = redlineBOMTable.isEmpty();
			logger.info("Check for redundant redlines....");
			if (!isEmpty) {
				logger.info("There are redline rows....");
				Iterator redlineIter = redlineBOMTable.iterator();
				/*
				while (redlineIter.hasNext()) {
					IRow redlineRow = (IRow)redlineIter.next();
					String redlineItemNum = (String)redlineRow.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER);
					if (redlineItemNum.equals(itemNumber)) {
						logger.info("Item : " + itemNumber + " already on bom table.");
						return;
						//see if row is redlined
						//boolean isRedlinedRemoved = redlineRow.isFlagSet(ItemConstants.FLAG_IS_REDLINE_REMOVED);
						//boolean isRedlinedUpdated = redlineRow.isFlagSet(ItemConstants.FLAG_IS_REDLINE_MODIFIED);
						//boolean isRedlinedAdded = redlineRow.isFlagSet(ItemConstants.FLAG_IS_REDLINE_ADDED);
						//logger.info("Item : " + itemNumber + " is redlined???");
						//logger.info("isRedlinedRemoved : " + isRedlinedRemoved);
						//logger.info("isRedlinedUpdated : " + isRedlinedUpdated);
						//logger.info("isRedlinedAdded : " + isRedlinedAdded);
						//if (isRedlinedRemoved) {
							//undo redline
						//}
					}
				}
				*/
			}
			HashMap map = new HashMap();
			map.put(ItemConstants.ATT_BOM_ITEM_NUMBER, itemNumber);
			map.put(ItemConstants.ATT_BOM_QTY, qty);
			//map.put(ItemConstants.ATT_BOM_BOM_NOTES, "ZWS-PROI");
			map.put(bom_Flag, "ProI");
			//map.put(bomFlagConstant, "ZWS-PROI");
			redlineBOMTable.createRow(map);
		}
		catch (APIException apiEx) {
			logger.error("putChild : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
		}	
	
	
	}
	
	public void agileUpdateChild (IRow bomRow, HashMap values) {
		
		try {
			boolean isRedlineRemoved = false;
			isRedlineRemoved = bomRow.isFlagSet(ItemConstants.FLAG_IS_REDLINE_REMOVED);
			if (isRedlineRemoved) {
				logger.info("bomRow needs to be unredlined....");
				//child has been removed so we need to unredline it
				IRedlinedRow rRow = (IRedlinedRow)bomRow;
				rRow.undoRedline();
				bomRow.setValues(values);
				
			}
			else {
				//update values
				bomRow.setValues(values);
			}
			
		}
		catch (APIException apiEx) {
			logger.error("agileUpdateChild error : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
			//child.setErrorMessage(apiEx.getMessage());
	
		}
	}
	
	public void agileUpdateChild (IRow bomRow, Integer valID, Object value) {
		
		try {
			boolean isRedlineRemoved = false;
			isRedlineRemoved = bomRow.isFlagSet(ItemConstants.FLAG_IS_REDLINE_REMOVED);
			if (isRedlineRemoved) {
				//child has been removed so we need to unredline it
				logger.info("bomRow needs to be unredlined....");
				//child has been removed so we need to unredline it
				IRedlinedRow rRow = (IRedlinedRow)bomRow;
				rRow.undoRedline();
				bomRow.setValue(valID, value);
			
			}
			else {
				//update values
				bomRow.setValue(valID, value);
			}
		
		}
		catch (APIException apiEx) {
			logger.error("agileUpdateChild error : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
			//child.setErrorMessage(apiEx.getMessage());

		}
	}
	
	
	public boolean agilePutChildren (IItem parentItem, Vector childNums) {
		
		//IItem parentItem;
		IRow childRow;
		ITable bomTable;
		HashMap params;
		Vector redlineBOMList = null;
		int childSize = 0;
		boolean redline = false;
	
		try {
			childSize = childNums.size();
			logger.info("Child Item List before ....");
			for (int i = 0; i < childSize; i++) {
				String childNum = (String)childNums.get(i);
				logger.info("Child Item : " + childNum);
			}
		
			if (parentItem != null) {
				//load redline bom table 
				ITable redlineBOMTable = parentItem.getTable(ItemConstants.TABLE_REDLINEBOM);
				boolean isEmpty = redlineBOMTable.isEmpty();
				//perform updates and deletes
				if (!isEmpty) {
					logger.info("there are redling bom rows...");
					redlineBOMList = new Vector();
					Iterator redlineIter = redlineBOMTable.iterator();
					while (redlineIter.hasNext()) {
						IRow redlineRow = (IRow)redlineIter.next();
						IItem redlineChild = (IItem)redlineRow.getReferent();
						String redlineChildNumber = (String)redlineChild.getValue(ItemConstants.ATT_TITLE_BLOCK_NUMBER);
						redline = AgileCheckComponentNumber(childNums, redlineChildNumber);
						//if true then we need to redline the item
						if (redline) {
							logger.info("redlining row : " + redlineChildNumber);
							redlineBOMTable.removeRow(redlineRow);
						}
						else {
							logger.info("updating row : " + redlineChildNumber);
							//update quantity | bom component value |
							redlineRow.setValue(ItemConstants.ATT_BOM_BOM_NOTES, "ZWS-PROI");
							//redlineRow.setValue(bomFlagConstant, "ZWS-PROI");
						}
						
					}
				}
				//now add new items to redline bom table
				childSize = childNums.size();
				logger.info("Child Item after ...");
				for (int i = 0; i < childSize; i++) {
					String childNum = (String)childNums.get(i);
					logger.info("Child Item : " + childNum);
					if (!(childNum.equals("IGNORE"))) {
						//need to add this to BOM Table
						putChild(redlineBOMTable, childNum);
					}
				}
				return true;
			}
			else {
				//Item still does not exist so there must be an error
				logger.error("Parent Item load/creation problem");
				return false;
			}
		}
		catch (APIException apiEx) {
			logger.error("PutChildren error : " + apiEx.getMessage());	
			setErrorMessage(apiEx.getMessage());
			return false;
		}	 
	
	}
	
	public boolean agilePutChildren (IItem parentItem, XMLItem[] parentChildren) {
		
		//IItem parentItem;
		IRow childRow;
		ITable bomTable;
		HashMap params;
		Vector redlineBOMList = null;
		int childSize = 0;
		boolean redline = false;

		try {
			childSize = parentChildren.length;
			/*
			logger.info("Child Item List before ....");
			for (int i = 0; i < childSize; i++) {
				XMLItem child = parentChildren[i];
				String childNum = child.getItemNumber();
				Integer childQTY = child.getQuantity();
				String childInfoMessage = child.getInfoMessage();
				logger.info("Child Item : " + childNum);
				//logger.info("Child Item Qty : " + childQTY);
				logger.info("Child Item Msg : " + childInfoMessage);
			}
			*/
			if (parentItem != null) {
				//load redline bom table 
				ITable redlineBOMTable = parentItem.getTable(ItemConstants.TABLE_REDLINEBOM);
				int BomLines = redlineBOMTable.size();
				boolean isEmpty = redlineBOMTable.isEmpty();
				//perform updates and deletes
				if (!isEmpty) {
					//logger.info("there are redline bom rows...");
					redlineBOMList = new Vector();
					int n=BomLines;
					Iterator redlineIter = redlineBOMTable.iterator();
					//while (redlineIter.hasNext()) {
					while (n>0) {
						//new code to fix error
						n=n-1;
						//logger.info("next while loop : " + n);
						
						//String mCADAttr = (String)redlineRow.getValue(ItemConstants.ATT_BOM_BOM_NOTES);
						//String mCADAttr = (String)redlineRow.getValue(ItemConstants.ATT_BOM_ITEM_DESCRIPTION);
						//String mCADAttr = (String)redlineRow.getValue(bomFlagConstant);
						
						//put all the code in a try to prevent issues
						try {
							IRow redlineRow = (IRow)redlineIter.next();
							//logger.info("got next row...");
							//new code to see if row is redlined removed
							
							
							//IRedlineRow rRow = null;
							//if (!isRedlinedRemoved) {
							
								//logger.info("bom_Flag : " + bom_Flag);
								ICell cell = redlineRow.getCell(bom_Flag);
								IAgileList cl = (IAgileList)cell.getValue();
								// Get the current value from the list
								String mCADAttr = null;
								IAgileList[] selected = cl.getSelection();
								if (selected != null && selected.length > 0) {
									mCADAttr = (String)selected[0].getValue();
								}
								
								if (mCADAttr == null) {
									mCADAttr = "NOT SELECTED";
								}
								//logger.info("mCADAttr : " + mCADAttr);
								if (mCADAttr.equals("ProI")) {
									logger.info("this is a MCAD component...");
									//this is a MCAD child so deal with it
									IItem redlineChild = (IItem)redlineRow.getReferent();
									//logger.info("got item referrent....");
									String redlineChildNumber = (String)redlineChild.getValue(ItemConstants.ATT_TITLE_BLOCK_NUMBER);
									//logger.info("got item referrent number : " + redlineChildNumber);
									redline = AgileCheckComponentNumber(parentChildren, redlineChildNumber);
									//if true then we need to redline the item
									if (redline) {
										logger.info("redline component : " + redlineChildNumber);
										//redlineBOMTable.removeRow(redlineRow);
										///logger.info("after redline component : " + redlineChildNumber);
										
										//check to see if child is already removed
										boolean isRedlinedRemoved = redlineRow.isFlagSet(ItemConstants.FLAG_IS_REDLINE_REMOVED);
										String redlineRowItemNumber = (String)redlineRow.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER);
										logger.info(redlineRowItemNumber + " isRedlinedRemoved : " + isRedlinedRemoved);
										
										//if already redlined no need to remove it
										if (!isRedlinedRemoved) {
											redlineBOMList.add(redlineRow);	
										}
									}
									else {
										//update quantity
										logger.info("update component : " + redlineChildNumber);
										Integer qty = AgileGetComponentQuantity(parentChildren, redlineChildNumber);
										//logger.info("qty : " + qty);
										//redlineRow.setValue(ItemConstants.ATT_BOM_QTY, qty);
										//redlineRow.setValue(ItemConstants.ATT_BOM_BOM_NOTES, "ZWS-PROI");
										agileUpdateChild(redlineRow, ItemConstants.ATT_BOM_QTY, qty);
									}
								}
								else {
									//attribute is not set see if we need to set it
									logger.info("this is a not MCAD component in Agile...");
									//this is a MCAD child so deal with it
									IItem redlineChild = (IItem)redlineRow.getReferent();
									//logger.info("got item referrent....");
									String redlineChildNumber = (String)redlineChild.getValue(ItemConstants.ATT_TITLE_BLOCK_NUMBER);
									//logger.info("got item referrent number : " + redlineChildNumber);
									redline = AgileCheckComponentNumber(parentChildren, redlineChildNumber);
									//if true then we need to redline the item
									if (!redline) {
										logger.info("update component : " + redlineChildNumber);
										Integer qty = AgileGetComponentQuantity(parentChildren, redlineChildNumber);
										//logger.info("qty : " + qty);
										//redlineRow.setValue(ItemConstants.ATT_BOM_QTY, qty);
										//redlineRow.setValue(ItemConstants.ATT_BOM_BOM_NOTES, "ZWS-PROI");
										
										HashMap updateMap = new HashMap();
										updateMap.put(ItemConstants.ATT_BOM_QTY, qty);
										//updateMap.put(ItemConstants.ATT_BOM_BOM_NOTES, "ZWS-PROI");
										updateMap.put(bom_Flag, "ProI");
										//redlineRow.setValues(updateMap);
										//redlineRow.setValue(updateMap);
										agileUpdateChild(redlineRow, updateMap);
										
									
									}
									else {
										//update quantity
										logger.info("cannot redline component - not MCAD component....");
										//redlineBOMTable.removeRow(redlineRow);	
									}
								}
							//}
							//else {
								//undo redline
								//redlineBOMTable.add()
								//redlineBOMTable.add(redlineRow);
								//logger.info("cannot unredline component");
							//}
							//logger.info("end of redlining....");
						}
						catch (APIException apiEx) {
							logger.error("Redline BOM caught error : " + apiEx.getMessage());
							setErrorMessage(apiEx.getMessage());
							//child.setErrorMessage(apiEx.getMessage());
							
						}
						catch (Exception ex) {
							logger.error("Redline BOM caught exception : " + ex.getMessage());
							setErrorMessage(ex.getMessage());
							//child.setErrorMessage(apiEx.getMessage());
	
						}
					}
				}
			
				
				//new code to delete rows from bom table
				try {
					//New code to delete rows from BOM
					int redlineBOMListSize = 0;
					if (redlineBOMList != null) {
						redlineBOMListSize = redlineBOMList.size();
						if (redlineBOMListSize > 0) {
							//there are rows to remove
							//loop through redlineBOMList in reverse order
							logger.info("BOM Removal of " + redlineBOMListSize + " rows...");
							for (int redIter = redlineBOMListSize-1; redIter >=0; redIter--) {
								//turn Object into int
								
								IRow redlineBOMRow = (IRow)redlineBOMList.get(redIter);
								redlineBOMTable.removeRow(redlineBOMRow);
								logger.info("removed row...");
								//code to reduce the Table row count when not redlining 
								
							}
						}
					}
				}
				catch (APIException apiEx) {
					errorMessage = apiEx.getMessage();
					logger.info("Delete BOM apiEx : ");
					logger.info(apiEx);
				}
				catch (Exception ex) {
					errorMessage = ex.getMessage();
					logger.info("Delete BOM ex :");
					logger.info(ex);
				}
				
				logger.info("Child Item after ...");
				for (int i = 0; i < childSize; i++) {
					XMLItem child = parentChildren[i];
					//String childNum = (String)childNums.get(i);
					String childNum = child.getItemNumber();
					String childMsg = child.getInfoMessage();
					Integer childQty = child.getQuantity();
					String childBomMessage = child.getBomMessage();
					logger.info("Child Item : " + childNum);
					//logger.info("Child Qty : " + childQty);
					//logger.info("Child Msg : " + childMsg);
					//logger.info("Child Item Bom Msg : " + childBomMessage);
					if (!(childBomMessage.equals("UPDATE"))) {
						if (!(childBomMessage.equals("IGNORE"))) {
							//need to add this to BOM Table
							try {
								putChild(redlineBOMTable, childNum, childQty);
								child.setBomMessage("Added to BOM of " + parentItem.getValue(ItemConstants.ATT_TITLE_BLOCK_NUMBER));
							}
							catch (APIException apiEx) {
								logger.error("PutChild caught error : " + apiEx.getMessage());
								setErrorMessage(apiEx.getMessage());
								child.setErrorMessage(apiEx.getMessage());
							}
						}
					}
				}
				return true;
			}
			else {
				//Item still does not exist so there must be an error
				logger.error("Parent Item load/creation problem");
				return false;
			}
		}
		catch (APIException apiEx) {

			logger.error("PutChildren error : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());	
			return false;
		}
		catch (Exception ex) {
			logger.error("PutChildren exception : " + ex.getMessage());
			setErrorMessage(ex.getMessage());
			//child.setErrorMessage(apiEx.getMessage());
			return false;
	
		}	 

	}
	
	public boolean agilePutChildren (IItem parentItem, String parentNum, String parentRevision, Vector childNums) {
		
		//IItem parentItem;
		IRow childRow;
		ITable bomTable;
		HashMap params;
		int childSize = 0;
		
		try {
			
			//parentItem = (IItem)session.getObject(ItemConstants.CLASS_PART, parentNum);
			//check to see if parent exists
			if (parentItem == null) {
				//parent item does not exist so create one
				parentItem = (IItem)session.createObject(ItemConstants.CLASS_PART, parentNum);
			}
			if (parentItem != null) {
				//load changes table
				
				//set revision
				
				//load BOM table
				bomTable = (ITable)parentItem.getTable(ItemConstants.TABLE_BOM);
				//int count = bomTable.get
				childSize = childNums.size();
				for (int i = 0; i < childSize; i++) {
					params = new HashMap();
					String childNum = (String)childNums.get(i);
					params.put(ItemConstants.ATT_BOM_ITEM_NUMBER, childNum);
					params.put(ItemConstants.ATT_BOM_QTY, "1");
					parentItem.setManufacturingSite(ManufacturingSiteConstants.COMMON_SITE);
					IRow row = bomTable.createRow(params);	
				}
				return true;
			}
			else {
				//Item still does not exist so there must be an error
				logger.error("Parent Item load/creation problem");
				return false;
			}
		}
		catch (APIException apiEx) {
		
			logger.error("PutChildren error : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());	
			return false;
		}	 
		
	}
	
	private IRow findFileInTable(ITable FileTable, Integer FieldId, String Select) throws APIException {
		IRow RetRow = null;
		Iterator ActRow = FileTable.iterator();
		while (ActRow.hasNext()) {
			IRow ChkRow = (IRow) ActRow.next();
		  	if (Select.length() == 0) {
				RetRow = ChkRow;
				return RetRow;
		  	} 
		  	String ChkStr = ChkRow.getValue(FieldId).toString().toLowerCase();
		  	if (ChkStr.endsWith(Select.toLowerCase())) {
				RetRow = ChkRow;
		  	}
		}
		return RetRow;
	}
	
	public void addFileFolder (IItem item, String filePath, String fileName, XMLItem fileItem) throws Exception{
		
		IFileFolder fileFolder = null;
		boolean folderAlreadyExists = false;
		boolean foundFile = false;
		boolean createFileFolder = true;
		boolean folderCheckedOut = false;
		boolean folderCheckedOutByUser = false;
		boolean replaceFile = false;
		String CkoUsr = "";
		
		int fileIndex = 0;
		
		try {
			
			logger.info("adding file attachment....");
			//load file folders table
			//ITable attTable = item.getAttachments();
			//logger.info("AgileItem : " + item.getValue(ItemConstants.ATT_TITLE_BLOCK_NUMBER));
			//logger.info("revision : " + item.getRevision());
			
			//check to see if Item is incorporated
			boolean incorporated = item.isIncorporated();
			ITable attTable = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
			if (!incorporated) {
				//you can check out and replace the attachments
				
				//check to see if file already exists on itme
				boolean isEmpty = attTable.isEmpty();
				if (!isEmpty) {
					logger.info("there are attachments....");
					//there are file folders on the item
					Iterator attTableIter = attTable.iterator();
					try {
						while (attTableIter.hasNext()) {
							folderCheckedOut = false;
							logger.info("inside file folder loop....");
							IRow fileFolderRow = (IRow)attTableIter.next();
							fileFolder = (IFileFolder)fileFolderRow.getReferent();
			
							//set fileD
							//check to see if filefolder is checked out by use
							if (fileFolder.isCheckedOut()) {
								folderCheckedOut = true;
								this.agileUser = session.getCurrentUser().getName();
								//file folder already checked out see if it is this user
								CkoUsr = fileFolder.getValue(FileFolderConstants.ATT_TITLE_BLOCK_CHECKOUT_USER).toString();
								if (agileUser.compareToIgnoreCase(CkoUsr) != 0) {
									//this is checked out by our user
									//allow mods
									folderCheckedOutByUser = false;
								}
								else {
									//not checked out
									folderCheckedOutByUser = true;
									//do not continue
									//fileItem.setFileErrorMessage("File Folder is already checked out by " + CkoUsr + " cannot version file.");
									//break;
								}
				
							}
							if (!folderCheckedOut) {
								folderCheckedOutByUser = true;
								//for some reason I have to check out the folder
								fileFolder.checkOutEx();
							}
							ITable filesTable = fileFolder.getTable(FileFolderConstants.TABLE_FILES);
							Iterator fileIter = filesTable.iterator();
							fileIndex = 0;
							replaceFile = false;
							while (fileIter.hasNext()) {
								IRow fr = (IRow) fileIter.next();
								String attFileName = "";
								attFileName = (String)fr.getValue(FileFolderConstants.ATT_FILES_FILE_NAME);
								if (attFileName.equals(fileName)) {
									//check to see if checked out by user
									if (folderCheckedOutByUser) {
										logger.info("found existing file....");
										//set create folder to false;
										createFileFolder = false;
										InputStream stream = ((IAttachmentFile)fr).getFile();
										//CopyStream(stream);
										HashMap map = new HashMap();
										map.put(FileFolderConstants.ATT_FILES_FILE_NAME, fileName);
										map.put(FileFolderConstants.ATT_FILES_CONTENT, new File(filePath));
										fr.setValues(map);
						
										//set replaceFile to true
										replaceFile = true;
						
										//update File Folder Attributes
										updateFolderAttributes(fileFolder, fileItem);
						
										//brealk from while loop
										break;
									}
									else {
										//checked out but not by this user
										fileItem.setFileErrorMessage("File Folder is already checked out by " + CkoUsr + " cannot version file.");
										//exit
										return;
									}
					
								}
							}
							logger.info("replaceFile : " + replaceFile);
							if (fileFolder.isCheckedOut()) {
								//always check in folder
								if (folderCheckedOutByUser) {
									//check to see if check in or cancel check out is needed
									if (replaceFile) {
										fileFolder.checkIn();
									}
									else {
										//cancel check out
										fileFolder.cancelCheckout();
									}
					
								}
							}
							//find file folder row remove old reference and add new reference
							//logger.info("CAX Code....");
							IRow FRow = this.findFileInTable(filesTable,FileFolderConstants.ATT_FILES_FILE_NAME,fileName);
							if (FRow != null && fileFolder.isCheckedOut()==false) {
								//found the file so we need to set it to the current version
								attTable.createRow(FRow);
								attTable.removeRow(fileFolderRow);
				
								//break from while loop
								break;
							}
							//logger.info("End CAX Code....");
			
							logger.info("foundFile : " + foundFile);
			
							//add code back
							//if (foundFile) {}
			
						}
					}
					catch (APIException ex) {
						if (fileFolder.isCheckedOut()) {
							//cancel check out
							fileFolder.cancelCheckout();
						}
						fileItem.setErrorMessage(ex.getMessage());
						setErrorMessage(ex.getMessage());
						logger.error("while Att Table error : " + ex.getMessage());
						Throwable rootCause = ex.getRootCause();
						if (rootCause != null) {
							setErrorMessage(rootCause.getMessage());
							logger.error("while Att Table root cause : " + rootCause.getMessage());
						}
					}
					//logger.info("end of Attachments table....");
				}
			}
			else {
				//you cannot modify the files because it is incorporated
				fileItem.setFileErrorMessage("File Folder is incorporated cannot version or add a file.");
				return;
			}
			
			
			
			//logger.info("end of there are attachments....");
			
			//check to see if file folder needs to be created
			logger.info("createFileFolder : " + createFileFolder);
			if (createFileFolder) {
				logger.info("creating file folder....");
				try {
					{}//Logwriter.printOnConsole("creating file folder...");
					//String newFileFolderPath = filePath + fileSep + fileName;
					logger.info("filePath : " + filePath);
					File newFileFolderFile = new File (filePath);
					HashMap map = new HashMap();
					map.put(ItemConstants.ATT_ATTACHMENTS_FILE_NAME, fileName);
					map.put(ItemConstants.ATT_ATTACHMENTS_CONTENT, newFileFolderFile);			
					IRow newFileFolderRow = attTable.createRow(map);		

					//IRow newFileFolderRow = attTable.createRow(filePath);
					//IRow newFileFolderRow = attTable.createRow(file3);
					IFileFolder newFileFolder = (IFileFolder)newFileFolderRow.getReferent();
					//update file folder attributes
					updateFolderAttributes(newFileFolder, fileItem);
				}
				catch (APIException apiEx) {
					fileItem.setErrorMessage(apiEx.getMessage());
					logger.error("add file folder row error : " + apiEx.getMessage());
					setErrorMessage(apiEx.getMessage());
					Throwable rootCause = apiEx.getRootCause();
					if (rootCause != null) {
						logger.error("add file folder root cause : " + rootCause.getMessage());
					}
				}
				/*
				try {
					logger.info("new add File Folder...");
					ITable modAttTable = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
					String file3 = "d:\\temp\\test3.txt";
					logger.info("file3 : " + file3);
					IRow newFileFolderRow = modAttTable.createRow(file3);
				}
				catch (APIException apiEx) {
					logger.error("add file folder row error : " + apiEx.getMessage());
				}	
				*/
			}
		}
		catch (APIException apiEx) {
			{}//Logwriter.printOnConsole("Caught API Exception...");
			logger.error("addFileFolder : " + apiEx.getMessage());
			{}//Logwriter.printOnConsole("addFileFolder APIException : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
		}	
		/*
		catch (IOException ioEx) {
			{}//Logwriter.printOnConsole("addFileFolder IOException : " + ioEx.getMessage());
			logger.error("io exception : " + ioEx.getMessage());
			setErrorMessage(ioEx.getMessage());
		}
		*/	
		catch (Exception ex) {
			{}//Logwriter.printOnConsole("Caught Exception...");
			{}//Logwriter.printOnConsole("addFileFolder Exception : " + ex.getMessage());
			setErrorMessage(ex.getMessage());
		}
	
	
	}
	
	public boolean isAutoNumberRequired(IAgileClass cls) throws APIException {
		if (cls.isAbstract()) {
			return false;
		}
		IProperty p = ((INode)cls).getProperty(PropertyConstants.PROP_AUTONUMBER_REQUIRED);
		if (p != null) {
			IAgileList value = (IAgileList)p.getValue();
			return ((Integer)(value.getSelection()[0]).getId()).intValue() == 1;
		}
		return false;
	}
	
	public IChange agileCreateChange (String changeNumber, String agileSubClassName) {
		
		IAdmin admin;
		IAgileClass[] subClasses;
		IAgileClass agileClass;
		IAutoNumber[] numSources;
		String nextAutoNumber;
		IChange change = null;
		IAgileClass agileSubClass = null;
		
		try {
			
			//if changeNumber is null use autonumber
			logger.info("agileCreateChange changeNumber : " + changeNumber);
			if (changeNumber == null) {
				
				admin = session.getAdminInstance();
				agileClass = admin.getAgileClass(ChangeConstants.CLASS_CHANGE_ORDERS_CLASS);
				subClasses = agileClass.getSubclasses();
				for (int i = 0; i < subClasses.length; i++) {
					IAgileClass subClass = subClasses[i];
					String subClassName = subClass.getName();
					logger.info("subClassName : " + subClassName);
					if (subClassName.equals(agileSubClassName)) {
						agileSubClass = subClass;
					}
				}
				if (agileSubClass != null) {
					
					logger.info("loaded agile class....: " + agileSubClass.getName());
					//Check if AutoNumber is required
					if (isAutoNumberRequired(agileSubClass)) {
						logger.info("autonumber required....");
						numSources = agileSubClass.getAutoNumberSources();
						nextAutoNumber = numSources[0].getNextNumber();
						logger.info("agileCreateChange nextAutoNumber : " + nextAutoNumber);
						change = (IChange)session.createObject(agileSubClass.getId(), nextAutoNumber);
					}
					else {
						logger.info("autonumber not required....");
						numSources = agileSubClass.getAutoNumberSources();
						logger.info("number of auto number sources : " + numSources.length);
						logger.info("name of auto number source : " +  numSources[0].getName());
						nextAutoNumber = numSources[0].getNextNumber();
						logger.info("agileCreateChange nextAutoNumber : " + nextAutoNumber);
						change = (IChange)session.createObject(agileSubClass.getId(), nextAutoNumber);
					}
				}
			}
			else {
				//create change
				change = (IChange)session.createObject(ChangeConstants.CLASS_ECO, changeNumber);
			}
			//check for change
			if (change == null) {
				logger.error("Change creation failed");
				return null;
			}
			else {
				
				//change created
				
				//Get all available workflows
			 	IWorkflow[] wfs = change.getWorkflows();
			 	
				//set workflow to first workflow for now
				change.setWorkflow(wfs[0]);
				return change;
			}			
			
		}
		catch (APIException apiEx) {
			logger.error("createChange error : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
			return null;
		}
	}
	
	public void addAffectedItem (IChange change, IItem item, String newRev) {
		
		try {
			//new code to disable warning for multiple pending changes
			//change.disableWarnign
			session.disableWarning(ExceptionConstants.APDM_PENDINGCHANGE_ITEM_WARNING);
			
			ITable affectedItems = change.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
			//add new row
			IRow affRow = (IRow)affectedItems.createRow(item);
			//set new rev value
			affRow.setValue(ChangeConstants.ATT_AFFECTED_ITEMS_NEW_REV, newRev);
		}
		catch (APIException apiEx) {
			logger.error("addAffectedItem error : " + apiEx.getMessage());
			setErrorMessage(apiEx.getMessage());
		}
	}
	
	public void loadConstants (Vector constants) {
		//iterate through keys
		
		/*
		Integer itemNumberConstant = null;
		Integer itemDescriptionConstant = null;
		Integer itemRevisionConstant = null;
		Integer itemConstant = null;
		Integer bomQuantityConstant = ItemConstants.ATT_BOM_QTY;
		Integer bomFlagConstant = null;
		*/
		
	  	if (constants != null) {
	  		int constantsCount = constants.size();
	  		for (int i = 0; i < constantsCount; i++) {
	  			XMLAttribute constant = (XMLAttribute)constants.get(i);
	  			String attName = constant.getAttrName();
	  			logger.info("const att name : " + attName);
				//logger.info("const att agile name : " + constant.getAttrAgileName());
				//logger.info("const att proi name : " + constant.getAttrProiName());
	  			if (attName.equals("CAD Flag")) {
					bomFlagConstant = constant.getAttrAgileConstant();
	  			}
	  		}
	  	}
	}
	
	static void copyStreamToFile(InputStream in, File file) throws IOException {
	  	FileOutputStream out = new FileOutputStream(file);

	  	try {
		 	copyStreamToStream(in, out);
	  	} finally {
		 	out.close();
		}
   	}

   	static void copyStreamToStream(InputStream in, OutputStream out)
						   throws IOException {
	  	byte[] buf = new byte[4096];
	  	int    amt;

	  	while ((amt = in.read(buf)) > 0) {
		 	out.write(buf, 0, amt);
	  	}
   	}
   	
   	public void setErrorMessage (String err) {
   		this.errorMessage = err;
   	}
   	
   	public String createXMLErrorMessage (String errMsg) {
   		String xmlErrorMessage = "";
   		return xmlErrorMessage;
   	}
   	
	public String getErrorMessage () {
		return this.errorMessage;
	}
	
	public String getXMLErrorMessage () {
		String errMessage = "<Agile><Errors><Error>";
		errMessage = errMessage + errorMessage;
		errMessage = errMessage + "</Error></Errors></Agile>";
		return errMessage;
		
	}
	
	public StringBuffer createOutputXML (XMLItem[] pubItems) {
		
		//String xmlOutput = "<agile-data>";
		{}//Logwriter.printOnConsole("Beginning createOutputXML....");
		StringBuffer xmlOutput = new StringBuffer ("");
		try {
			
			//xmlOutput.append("</agile-data>");
			int pubItemsCount = pubItems.length;
			for (int i = 0; i < pubItemsCount; i++) {
				XMLItem pubItem = pubItems[i];
				StringBuffer pubItemXML = recurseXMLOutput (pubItem);
				xmlOutput.append(pubItemXML);
			}
			return xmlOutput;	
		}
		catch (Exception ex) {
			logger.error("createOutputXML error : " + ex.getMessage());
			xmlOutput = new StringBuffer("ERROR");
			ex.printStackTrace();
			return xmlOutput;
		}
			
	}
	
	public StringBuffer recurseXMLOutput (XMLItem pubItem) {
		
		{}//Logwriter.printOnConsole("Beginning recurseXMLOutput for : " + pubItem.getItemNumber());
		XMLItem[] childItems = null;
		XMLItem childItem = null;
		StringBuffer xml = new StringBuffer ("");
		
		try {
			childItems = pubItem.getChildren();
			if (childItems != null) {
				//there could be children
				int childrenCount = childItems.length;
				{}//Logwriter.printOnConsole("childrenCount : " + childrenCount);
				if (childrenCount > 0) {
					{}//Logwriter.printOnConsole(pubItem.getItemNumber() + " has children...");
					//there are children
					xml.append("<metadata number=\"" + pubItem.getItemNumber() + "\" revision=\"" + 
								pubItem.getRevision() + "\"");
					String errorMessage = pubItem.getErrorMessage();
					if (errorMessage.length() > 0) {
						//there was an error
						xml.append(" message=\"" + errorMessage + "\" message-type=\"error\">");
					}
					else {
						//check for file error
						String fileErrorMessage = pubItem.getFileErrorMessage();
						if (fileErrorMessage.length() > 0) {
							xml.append(" message=\"" + fileErrorMessage + "\" message-type=\"error\">");
						}
						else {
							xml.append(" message=\"" + pubItem.getInfoMessage() + "\" message-type=\"information\">");
						}
					}
					//loop through children
					{}//Logwriter.printOnConsole("Looping through children of " + pubItem.getItemNumber());
					for (int i = 0; i < childrenCount; i++) {
						childItem = childItems[i];
						{}//Logwriter.printOnConsole("sending " + childItem.getItemNumber() + " to recurseXMLOutput...");
						StringBuffer pubChildXML = recurseXMLOutput(childItem);	
						{}//Logwriter.printOnConsole("adding child XML....");
						xml.append(pubChildXML);
					}
					xml.append("</metadata>");
				}
			}
			else {
				{}//Logwriter.printOnConsole(pubItem.getItemNumber() + " has no children...");
				//there are no children
				xml.append("<metadata number=\"" + pubItem.getItemNumber() + "\" revision=\"" + 
							pubItem.getRevision() + "\"");
				String errorMessage = pubItem.getErrorMessage();
				if (errorMessage.length() > 0) {
					//there was an error
					xml.append(" message=\"" + errorMessage + "\" message-type=\"error\"/>");
				}
				else {
					//check for file error message
					String fileErrorMessage = pubItem.getFileErrorMessage();
					if (fileErrorMessage.length() > 0) {
						xml.append(" message=\"" + fileErrorMessage + "\" message-type=\"error\"/>");
					}
					else {
						xml.append(" message=\"" + pubItem.getInfoMessage() + "\" message-type=\"information\"/>");
					}
					//xml.append(" message=\"" + pubItem.getInfoMessage() + "\" message-type=\"information\"/>");
				}
			}
			{}//Logwriter.printOnConsole("returning XML to CreateOutput....");
			return xml;
		}
		catch (Exception ex) {
			logger.error("createOutputXML error : " + ex.getMessage());
			xml.append("ERROR");
			ex.printStackTrace();
			return xml;
		}
	}
   	
   	public void tester () {
		try {
			IItem agileItem = (IItem)session.getObject(IItem.OBJECT_TYPE, "456489");
			ITable attTable = agileItem.getTable(ItemConstants.TABLE_ATTACHMENTS);
			String file3 = "d:\\temp\\test3.txt";
			logger.info("file3 : " + file3);
			IRow newFileFolderRow = attTable.createRow(file3);
		}
		catch (APIException apiEx) {
			logger.error("tester error : " + apiEx.getMessage());
		}	
   		
   	}
   	
	public void testAttachments() {
		
		try {
			IItem item = (IItem) session.getObject(IItem.OBJECT_TYPE, "P00024");
			IChange change = (IChange)session.getObject(IChange.OBJECT_TYPE, "C00088");
			item.setRevision(change);
			ITable attachments = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
			IRow row = (IRow) attachments.iterator().next();
			IFileFolder ff = (IFileFolder) row.getReferent();
			logger.info("checking out folder...");
			ff.checkOutEx();
	
			for (int i = 0; i < 1; ++i) {
				ITable files = ff.getTable(FileFolderConstants.TABLE_FILES);
				IRow fr = (IRow) files.iterator().next();
				logger.info("got Attachment File...");
				InputStream stream = ((IAttachmentFile)fr).getFile();
				logger.info("replace file...");
				HashMap map = new HashMap();
				map.put(FileFolderConstants.ATT_FILES_FILE_NAME, "users_list.xls");
				map.put(FileFolderConstants.ATT_FILES_CONTENT, new File("D:\\temp\\users_list.xls"));
				fr.setValues(map);
			}
			logger.info("check in folder...");
			ff.checkIn();
		}
		catch (APIException apiEx) {
			logger.error("testAttachments api ex : " + apiEx.getMessage());
		}

	}

   	
	public void attachmentTester () {
		try {
			IItem agileItem = (IItem)session.getObject(IItem.OBJECT_TYPE, "P00024");
			IChange agileChange = (IChange)session.getObject(IChange.OBJECT_TYPE, "C00064");
			//set revision
			agileItem.setRevision(agileChange);
			ITable attTable = agileItem.getTable(ItemConstants.TABLE_ATTACHMENTS);
			Iterator attTableIter = attTable.iterator();
			String file3 = "users.xml";
			logger.info("file3 : " + file3);
			while (attTableIter.hasNext()) {
				//logger.info("inside file folder loop....");
				IRow fileFolderRow = (IRow)attTableIter.next();
				ICell fileName = fileFolderRow.getCell(ItemConstants.ATT_ATTACHMENTS_FILE_NAME);
				IAgileList fileNameValue = (IAgileList)fileName.getValue();
				logger.info("Got value....");
				IAgileList[] currentFileNameValue = fileNameValue.getSelection();
				logger.info("Got current value....");
				String folderFileName = "";
				logger.info("Current File Folder version is : ");
				for (int i = 0; i < currentFileNameValue.length; i++) {
					logger.info(currentFileNameValue[i].getValue());
					folderFileName = (String)currentFileNameValue[i].getValue();
				}
				logger.info("folder file name : " + folderFileName);
				if (folderFileName.equals(file3)) {
					//have row
					ICell currentVer = fileFolderRow.getCell(ItemConstants.ATT_ATTACHMENTS_FOLDER_VERSION);
					logger.info("Got cell...." + currentVer.isReadOnly());
					//Get available list values for File Version

					IAgileList value = (IAgileList)currentVer.getValue();
					logger.info("Got value....");
					IAgileList[] currentValue = value.getSelection();
					logger.info("Got current value....");
					logger.info("Current File Folder version is : ");
					for (int i = 0; i < currentValue.length; i++) {
						logger.info(currentValue[i].getValue());
					}

					IAgileList values = currentVer.getAvailableValues();
					Object[] versionValues = values.getChildren();

					logger.info("All available version values:");
					IAgileList selVersion = null;
					String selVersionVal = "";
					Integer setVersion = null;
					Integer setVersionID = null;
					Object objectVersion = null;
					int versionIndex = 0;
					for (int i = 0; i < versionValues.length; i++) {
						{}//Logwriter.printOnConsole(i + ", " +										((IAgileList)versionValues[i]).getValue());
						selVersion = (IAgileList)versionValues[i];
						logger.info("selVersion id : " + selVersion.getId());
						setVersionID = (Integer)selVersion.getId();
						setVersionID.toString();
						selVersionVal = (String)selVersion.getValue();
						objectVersion = selVersion.getValue();
						logger.info("objectVersion type : " + objectVersion.getClass());
						if (selVersionVal.equals("14")) {
							logger.info("Matched Version : " + versionIndex);
							break;
						}
						versionIndex++;
					}
					//Set the value to Electrical
					/*
					if (selVersionVal != null) {
						setVersion = Integer.valueOf(selVersionVal);
						values.setSelection(new Object[] { setVersion });	
					}

					if (objectVersion != null) {
						logger.info("setting file version to object...");
						values.setSelection(new Object[] { objectVersion });	
					}
					*/
					//logger.info("setting file version to object array...");
					//logger.info("version : " + ((IAgileList)versionValues[versionIndex]).getValue());
					logger.info("version id : " + setVersionID);
					values.setSelection(new Object[] { setVersionID });
					//values.setSelection(new Object[] { versionValues[versionIndex] });
					//logger.info("setting file object version : " + objectVersion.toString());
					currentVer.setValue(values);
				}
			
			}
			//IRow newFileFolderRow = attTable.createRow(file3);
		}
		catch (APIException apiEx) {
			logger.error("attachmentTester error : " + apiEx.getMessage());
		}	
	
	}
	
	public static void main(String[] args) 
	{
		
		String item;
		boolean connected = false;
		String fileName = "";
		//test login
		if (args.length == 1) { 
			fileName = args[0];
			{}//Logwriter.printOnConsole("mode 1.......");
			//System.exit(0);
			AgileConnectorPub ac = new AgileConnectorPub();
			connected = ac.init();
			{}//Logwriter.printOnConsole("Connected : " + connected);
			if ( !connected )
				System.exit(-1);
			
			String xmlFile = fileLocation + fileSep + fileName;
			{}//Logwriter.printOnConsole("xmlFile : " + xmlFile);
			//load and parse XML e
			IntralinkParserPub parser = new IntralinkParserPub(xmlFile, logger, ac);
			parser.parseItems("YES");
			//ac.tester();
			ac.disconnect();
		}
		else if (args.length == 3) {
			{}//Logwriter.printOnConsole("mode 3.......");
			fileName = args[2];
			String userName = args[0];
			String password = args[1];
			{}//Logwriter.printOnConsole("password : " + password);
			{}//Logwriter.printOnConsole("username :" + userName);
			InputStream stream = null;
			
			//AgileConnector ac = new AgileConnector(userName, password, fileName);
			AgileConnectorPub ac = new AgileConnectorPub();
			connected = ac.connect(userName, password);
			{}//Logwriter.printOnConsole("Connected : " + connected);
			if ( !connected )
				System.exit(-1);
			
			String xmlFile = fileName;
			{}//Logwriter.printOnConsole("xmlFile : " + xmlFile);
			
			File actualFile = new File(xmlFile);
			/*
			try {
				FileInputStream actualFileStream = new FileInputStream(actualFile);
				stream = (InputStream)actualFileStream;
				
			}
			catch (IOException ioEx){
				{}//Logwriter.printOnConsole("ioEx : " + ioEx.getMessage());
			}
			//load and parse XML e
			//IntralinkParser parser = new IntralinkParser(fileName, logger, ac);
			//StringBuffer output = ac.publish(userName, password, xmlFile);
			//StringBuffer output = ac.publish(xmlFile);
			{}//Logwriter.printOnConsole("Printing out stream...");
			ac.printOutInputStream(stream);
			*/
			try {
				FileInputStream actualFileStream = new FileInputStream(actualFile);
				stream = (InputStream)actualFileStream;
	
			}
			catch (IOException ioEx){
				{}//Logwriter.printOnConsole("ioEx : " + ioEx.getMessage());
			}
			
			{}//Logwriter.printOnConsole("Using stream instead...");
			StringBuffer output = ac.publish(stream);
			{}//Logwriter.printOnConsole("Done with publish call.....");
			//parser.parseItems();
			{}//Logwriter.printOnConsole(output);
			//ac.tester();
			ac.disconnect();
			
		}
		else if (args.length == 4) {
			{}//Logwriter.printOnConsole("mode 4.......");
			fileName = args[2];
			String userName = args[0];
			String password = args[1];
			String print = args[3];
			{}//Logwriter.printOnConsole("password : " + password);
			{}//Logwriter.printOnConsole("username :" + userName);
			{}//Logwriter.printOnConsole("print :" + print);
			InputStream stream = null;
	
			//AgileConnector ac = new AgileConnector(userName, password, fileName);
			AgileConnectorPub ac = new AgileConnectorPub();
			connected = ac.connect(userName, password);
			{}//Logwriter.printOnConsole("Connected : " + connected);
			if ( !connected )
				System.exit(-1);
	
			String xmlFile = fileName;
			{}//Logwriter.printOnConsole("xmlFile : " + xmlFile);
	
			File actualFile = new File(xmlFile);
			try {
				FileInputStream actualFileStream = new FileInputStream(actualFile);
				stream = (InputStream)actualFileStream;
		
			}
			catch (IOException ioEx){
				{}//Logwriter.printOnConsole("ioEx : " + ioEx.getMessage());
			}
			//load and parse XML e
			//IntralinkParser parser = new IntralinkParser(fileName, logger, ac);
			//StringBuffer output = ac.publish(userName, password, xmlFile);
			//StringBuffer output = ac.publish(xmlFile);
			{}//Logwriter.printOnConsole("Printing out stream...");
			ac.printOutInputStream(stream);
			{}//Logwriter.printOnConsole("Using stream instead...");
			StringBuffer output = ac.publish(stream);
			{}//Logwriter.printOnConsole("Done with publish call.....");
			//parser.parseItems();
			{}//Logwriter.printOnConsole("publish Output : " + output);
			//ac.tester();
			ac.disconnect();
	
		}
		else if (args.length == 5) {
			{}//Logwriter.printOnConsole("mode 5.......");
			fileName = args[4];
			String userName = args[2];
			String password = args[3];
			String uri = args[0];
			String port = args[1];
	
			//AgileConnector ac = new AgileConnector(userName, password, fileName);
			AgileConnectorPub ac = new AgileConnectorPub();
			connected = ac.connect(uri, port, userName, password);
			{}//Logwriter.printOnConsole("Connected : " + connected);
			if ( !connected )
				System.exit(-1);
	
			String xmlFile = fileName;
			{}//Logwriter.printOnConsole("xmlFile : " + xmlFile);
			//load and parse XML e
			//IntralinkParser parser = new IntralinkParser(fileName, logger, ac);
			//StringBuffer output = ac.publish(userName, password, xmlFile);
			StringBuffer output = ac.publish(xmlFile);
			{}//Logwriter.printOnConsole("Done with publish call.....");
			//parser.parseItems();
			{}//Logwriter.printOnConsole("publish Output : " + output);
			//ac.tester();
			ac.disconnect();
	
		}
		else {
			
		}
		System.exit(0);
	}   
	
}