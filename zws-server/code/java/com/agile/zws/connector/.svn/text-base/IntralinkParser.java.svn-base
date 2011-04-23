package com.agile.zws.connector;

//===========================================================================
//Copyright  1995-2003 Agile Software Corporation, 6373 San Ignacio Avenue,
//San Jose, California 95119-1200 U.S.A.; Telephone 408.284.4000, Facsimile
//408.284.4002, or <http://www.agile.com/>. All rights reserved.
//
//This file, which has been provided by Agile Software Corporation as part of
//an Agile Software product for use ONLY by licensed users of the product,
//includes CONFIDENTIAL and PROPRIETARY information of Agile Software
//Corporation.
//
//USE OF THIS SOFTWARE IS GOVERNED BY THE TERMS AND CONDITIONS OF THE LICENSE
//AGREEMENT AND LIMITED WARRANTY FURNISHED WITH THE PRODUCT.
//
//IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD AGILE SOFTWARE CORPORATION, ITS
//RELATED COMPANIES AND ITS SUPPLIERS, HARMLESS FROM AND AGAINST ANY CLAIMS
//OR LIABILITIES ARISING OUT OF THE USE, REPRODUCTION, OR DISTRIBUTION OF
//YOUR PROGRAMS, INCLUDING ANY CLAIMS OR LIABILITIES ARISING OUT OF OR
//RESULTING FROM THE USE, MODIFICATION, OR DISTRIBUTION OF PROGRAMS OR FILES
//CREATED FROM, BASED ON, AND/OR DERIVED FROM THIS SAMPLE SOURCE CODE FILE.
//===========================================================================


//import zws.util.{}//Logwriter;

import java.io.*;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.jdom.*;
import org.jdom.input.*;

import com.agile.api.*;

public class IntralinkParser {

	Logger logger;
	String xmlFile = "";
	String parseMode = "";
	AgileConnector connector;
	InputStream xmlStream;
	SAXBuilder builder;
	Document xmlDoc;
	XMLMapping mapper = null;
	
	public IntralinkParser (String mode, String file, Logger log, AgileConnector ac) {
		this.xmlFile = file;
		this.parseMode = mode;
		logger = log;
		connector = ac;
		builder = new SAXBuilder();
	}
	
	public IntralinkParser (String file, Logger log, AgileConnector ac) {
		this.xmlFile = file;
		logger = log;
		connector = ac;
		builder = new SAXBuilder();
	}
	
	public IntralinkParser (Logger log, AgileConnector ac) {
		logger = log;
		connector = ac;
		builder = new SAXBuilder();
	}
	
	public IntralinkParser (String file, Logger log, AgileConnector ac, XMLMapping map) {
		this.xmlFile = file;
		logger = log;
		connector = ac;
		builder = new SAXBuilder();
		mapper = map;
	}
	
	public IntralinkParser (Logger log, AgileConnector ac, XMLMapping map) {
		logger = log;
		connector = ac;
		builder = new SAXBuilder();
		mapper = map;
	}
	
	public XMLItem[] parseItems () {
		boolean itemExists = false;
		XMLItem[] publishedItems = null;;
		try {
			xmlDoc = builder.build(xmlFile);
			Element itemSet = xmlDoc.getRootElement();
			List itemList = itemSet.getChildren();
			int itemListSize = itemList.size();
			//logger.info("itemListSize  : " + itemListSize);
			publishedItems = new XMLItem[itemListSize];
			Iterator itemIter = itemList.iterator();
			int pubIndex = 0;
			while (itemIter.hasNext()) {
				itemExists = false;
				Element item = (Element)itemIter.next();
				//XMLItem publishedItem = recurseItems(item);
				XMLItem publishedItem = recurseXMLItems(item);
				publishedItems[pubIndex] = publishedItem;
				//logger.info("pubIndex  : " + pubIndex);
				pubIndex++;
			}
			
			//print out report
			//logger.info("printing out report...");
			//printOverallReport(publishedItems);
			
			return publishedItems;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			{}//Logwriter.printOnConsole("parseItems error : " + ex.getMessage());
			return null;
		}

	}
	
	public XMLItem[] parseItems (InputStream xmlStream) {
		boolean itemExists = false;
		XMLItem[] publishedItems = null;;
		try {
			//xmlDoc = builder.build(xmlFile);
			xmlDoc = builder.build(xmlStream);
			Element itemSet = xmlDoc.getRootElement();
			List itemList = itemSet.getChildren();
			int itemListSize = itemList.size();
			//logger.info("itemListSize  : " + itemListSize);
			publishedItems = new XMLItem[itemListSize];
			Iterator itemIter = itemList.iterator();
			int pubIndex = 0;
			while (itemIter.hasNext()) {
				itemExists = false;
				Element item = (Element)itemIter.next();
				//XMLItem publishedItem = recurseItems(item);
				XMLItem publishedItem = recurseXMLItems(item);
				publishedItems[pubIndex] = publishedItem;
				//logger.info("pubIndex  : " + pubIndex);
				pubIndex++;
			}
		
			//print out report
			//logger.info("printing out report...");
			//printOverallReport(publishedItems);
		
			return publishedItems;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			{}//Logwriter.printOnConsole("parseItems error : " + ex.getMessage());
			return null;
		}

	}
	
	public XMLItem recurseItems (Element item) {
		IItem agileItem = null;
		boolean itemExists = false;
		Vector childNumbers = null;
		XMLItem pubItem = null;
		XMLItem[] pubItemChildren = null;
		try {
			String itemNumber = item.getAttributeValue("Partnumber");
			itemExists = connector.checkItemExistance(itemNumber);
			//agileItem = connector.agileInitPart(itemNumber);
			if (!itemExists) {
				//String itemDescription = item.getAttributeValue("Description");
				//create Item
				//agileItem = connector.agileCreatePart(itemNumber, null, itemDescription);
			}
			else {
				//part exists load it
				agileItem = connector.agileInitPart(itemNumber);
				//set all the attributes
			}
			if (agileItem != null) {
				//load XMLItem
				pubItem = new XMLItem();
				pubItem.setAttributes(item);
				//get children
				List childrenList = item.getChildren();
				int itemListSize = childrenList.size();
				logger.info("pubItem children count itemListSize : " + itemListSize);
				pubItemChildren = new XMLItem[itemListSize];
				Iterator itemIter = childrenList.iterator();
				childNumbers = new Vector();
				int childrenIndex = 0;
				while (itemIter.hasNext()) {
					XMLItem pubChild = null;
					Element childItem = (Element)itemIter.next();
					pubChild = recurseItems(childItem);
					String childNumber = childItem.getAttributeValue("Partnumber");
					childNumbers.add(childNumber);
					pubItemChildren[childrenIndex] = pubChild;
					logger.info("childrenIndex : " + childrenIndex);
					childrenIndex++;
				}
				//set children
				if (pubItemChildren.length > 0) {
					logger.info("there are children to add to : " + itemNumber);
					//add children to pubItem
					pubItem.setChildren(pubItemChildren);
				}
				//get parent revision
				String revision = item.getAttributeValue("Revision");
				//publish BOM
				//agileItem = connector.agilePublishBOM(itemNumber, revision, childNumbers);
				agileItem = connector.agilePublishBOM(pubItem, agileItem);
				//add attachment to element
				//connector.addFileFolder(agileItem, "D:\\temp", "test3.txt");
			}
			else {
				//part does not exist
				pubItem = new XMLItem();
				pubItem.setAttributes(item);
				pubItem.setErrorMessage("Does not exist in Agile.");
				pubItem.setErrorMessage("Part does not exist in Agile, not able to to create part, out of scope.");
			}
			return pubItem;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			{}//Logwriter.printOnConsole("recurseItems error : " + ex.getMessage());
			return null;
		}
		
	}
	
	public XMLItem recurseXMLItems (Element item) {
		IItem agileItem = null;
		boolean itemExists = false;
		Vector childNumbers = null;
		XMLItem pubItem = null;
		XMLItem[] pubItemChildren = null;
		try {
			String itemNumber = item.getAttributeValue("number");
			logger.info("publishing : " + itemNumber);
			itemExists = connector.checkItemExistance(itemNumber);
			//agileItem = connector.agileInitPart(itemNumber);
			if (!itemExists) {
				//String itemDescription = item.getAttributeValue("Description");
				//create Item
				//agileItem = connector.agileCreatePart(itemNumber, null, itemDescription);
			}
			else {
				//part exists load it
				agileItem = connector.agileInitPart(itemNumber);
				//set all the attributes
			}
			if (agileItem != null) {
				//load XMLItem
				pubItem = new XMLItem();
				pubItem.setAttributes(item, mapper);
				//get children
				List childrenList = item.getChildren();
				int itemListSize = childrenList.size();
				if (itemListSize > 0) {
					//there are children
					
				}
				logger.info("pubItem children count itemListSize : " + itemListSize);
				pubItemChildren = new XMLItem[itemListSize];
				Iterator itemIter = childrenList.iterator();
				childNumbers = new Vector();
				int childrenIndex = 0;
				while (itemIter.hasNext()) {
					XMLItem pubChild = null;
					Element childItem = (Element)itemIter.next();
					pubChild = recurseXMLItems(childItem);
					pubItemChildren[childrenIndex] = pubChild;
					//logger.info("childrenIndex : " + childrenIndex);
					childrenIndex++;
				}
				//set children
				if (pubItemChildren.length > 0) {
					logger.info("there are children to add to : " + itemNumber);
					//add children to pubItem
					pubItem.setChildren(pubItemChildren);
				}
				//publish BOM
				//agileItem = connector.agilePublishBOM(itemNumber, revision, childNumbers);
				//agileItem = connector.agilePublishBOM(pubItem);
				agileItem = connector.agilePublishBOM(pubItem, agileItem);
				//add attachment to element
				//connector.addFileFolder(agileItem, "D:\\temp", "test3.txt");
			}
			else {
				//part does not exist
				pubItem = new XMLItem();
				pubItem.setAttributes(item, mapper);
				pubItem.setErrorMessage("Part does not exist in Agile.");
				pubItem.setInfoMessage("Out of Scope.");
				pubItem.setBomMessage("IGNORE");
			}
			return pubItem;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			{}//Logwriter.printOnConsole("recurseItems error : " + ex.getMessage());
			return null;
		}
	
	}
	
	public void printOverallReport (XMLItem[] publishedItems) {
		
		int pubItemsCount = publishedItems.length;
		for (int i = 0; i < pubItemsCount; i++) {
			XMLItem pubItem = publishedItems[i];
			printReport(pubItem);
		}
		
		
	}
	
	public void printReport (XMLItem pubItem) {
		
		String spacer = "";
		
		logger.info("Pub Item : " + pubItem.getItemNumber());
		logger.info("InfoMessage : " + pubItem.getInfoMessage());
		logger.info("ErrorMessage : " + pubItem.getErrorMessage());
		XMLItem[] pubChildren = pubItem.getChildren();
		if (pubChildren != null) {
			int pubChildrenCount = pubChildren.length;
			logger.info("Pub Item Child count : " +pubChildrenCount);
			logger.info("Begin BOM...");
			for (int i = 0; i < pubChildrenCount; i++) {
				XMLItem child = pubChildren[i];
				logger.info("Pub Child Item : " + child.getItemNumber());
				logger.info("BOM Message : " + child.getBomMessage());
				logger.info("ErrorMessage : " + child.getErrorMessage());
				printReport (child);
			}
			logger.info("End BOM...");
		}
	}

	
}
