package com.agile.zws.connector;

//===========================================================================
//Copyright 1995-2003 Agile Software Corporation, 6373 San Ignacio Avenue,
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.jdom.*;
import org.jdom.input.*;

public class XMLMapping {
	
	String mappingFile = "";
	String parseMode = "";
	AgileConnector connector;
	SAXBuilder builder;
	Document xmlDoc;
	HashMap attributeMapping;
	HashMap constantMapping;
	XMLAttribute[] xmlPartAtts = null;
	XMLAttribute[] xmlFileAtts = null;
	Vector xmlConstants = null;
	
	public XMLMapping(String mapFile) {
		
		this.mappingFile = mapFile;
		builder = new SAXBuilder();
	}
	
	public void loadAttrMapping() {
		try {
			XMLAttribute xmlAtt = null;
			xmlDoc = builder.build(mappingFile);
			Element dataSet = xmlDoc.getRootElement();
			Element attSet = dataSet.getChild("part-data");
			List attList = attSet.getChildren();
			Iterator attIter = attList.iterator();
			if (!(attList.isEmpty())) {
				//there are attributes
				xmlConstants = new Vector();
				int attCount = attList.size();
				xmlPartAtts = new XMLAttribute[attCount];
				attributeMapping = new HashMap();
				int attIndex = 0;
				while (attIter.hasNext()) {
					xmlAtt = new XMLAttribute();
					Element att = (Element)attIter.next();
					String attName = att.getAttributeValue("name");
					{}//Logwriter.printOnConsole("attName : " + attName);
					xmlAtt.setAttrName(attName);
					Element attProIName = att.getChild("proi-name");
					xmlAtt.setAttrProiName(attProIName.getTextTrim());
					Element attAgileName = att.getChild("agile-name");
					xmlAtt.setAttrProiName(attProIName.getTextTrim());
					Element attAgileID = att.getChild("agile-constantid");
					String strAttAgileID = attAgileID.getTextTrim();
					if (strAttAgileID != null) {
						Integer intAttAgileID = Integer.valueOf(strAttAgileID);
						xmlAtt.setAttrAgileConstant(intAttAgileID);
					}
					xmlAtt.setAttrProiName(attProIName.getTextTrim());
					Element attAgileEditable = att.getChild("agile-editable");
					String attEditable = attAgileEditable.getTextTrim();
					if (attEditable.equals("YES")) {
						xmlAtt.setAttrEditable(true);
					}
					else {
						xmlAtt.setAttrEditable(false);
					}
					Element attAgileConstant = att.getChild("agile-constant");
					String strAttAgileConstant = attAgileConstant.getTextTrim();
					if (strAttAgileConstant.equals("YES")) {
						xmlConstants.add(xmlAtt);
					}
					xmlPartAtts[attIndex] = xmlAtt;
					attIndex++;
				}
				
			}
			
			//set part atts
			setXMLPartAttributes(xmlPartAtts);
			
			//get file attributes
			Element fileAttSet = dataSet.getChild("file-data");
			List fileAttList = fileAttSet.getChildren();
			Iterator fileAttIter = fileAttList.iterator();
			if (!(fileAttList.isEmpty())) {
				//there are file attributes
				if (xmlConstants == null) {
					//no constants have been created yet
					xmlConstants = new Vector();
				}
				int attCount = fileAttList.size();
				xmlFileAtts = new XMLAttribute[attCount];
				attributeMapping = new HashMap();
				int attFileIndex = 0;
				while (fileAttIter.hasNext()) {
					xmlAtt = new XMLAttribute();
					Element att = (Element)fileAttIter.next();
					String attName = att.getAttributeValue("name");
					xmlAtt.setAttrName(attName);
					Element attProIName = att.getChild("proi-name");
					xmlAtt.setAttrProiName(attProIName.getTextTrim());
					Element attAgileName = att.getChild("agile-name");
					xmlAtt.setAttrProiName(attProIName.getTextTrim());
					Element attAgileID = att.getChild("agile-constantid");
					String strAttAgileID = attAgileID.getTextTrim();
					if (strAttAgileID != null) {
						Integer intAttAgileID = Integer.valueOf(strAttAgileID);
						xmlAtt.setAttrAgileConstant(intAttAgileID);
					}
					xmlAtt.setAttrProiName(attProIName.getTextTrim());
					Element attAgileEditable = att.getChild("agile-editable");
					String attEditable = attAgileEditable.getTextTrim();
					if (attEditable.equals("YES")) {
						xmlAtt.setAttrEditable(true);
					}
					else {
						xmlAtt.setAttrEditable(false);
					}
					Element attAgileConstant = att.getChild("agile-constant");
					String strAttAgileConstant = attAgileConstant.getTextTrim();
					if (strAttAgileConstant.equals("YES")) {
						xmlConstants.add(xmlAtt);
					}
					xmlFileAtts[attFileIndex] = xmlAtt;
					attFileIndex++;
				}
				//set File atts
				setXMLFileAttributes(xmlFileAtts);
	
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
			{}//Logwriter.printOnConsole("loadMapping error : " + ex.getMessage());
		}
	}
	
	public void setXMLPartAttributes (XMLAttribute[] atts) {
		this.xmlPartAtts = atts;
	}
	
	public XMLAttribute[] getXMLPartAttributes() {
		return this.xmlFileAtts;
	}
	
	public void setXMLFileAttributes (XMLAttribute[] atts) {
		this.xmlFileAtts = atts;
	}

	public XMLAttribute[] getXMLFileAttributes() {
		return this.xmlPartAtts;
	}
	
	public void setXMLConstants (Vector consts) {
		this.xmlConstants = consts;
	}

	public Vector getXMLConstants() {
		return this.xmlConstants;
	}
	
	public XMLAttribute getXMLPartAttribute (String attName) {
		XMLAttribute xmlAtt = null;
		try {
			int attCount = this.xmlPartAtts.length;
			for (int i = 0; i < attCount; i++) {
				XMLAttribute xmlAttSel = xmlPartAtts[i];
				String xmlAttName = xmlAttSel.getAttrName();
				if (xmlAttName.equals(xmlAtt)) {
					//we have the XML Attribute
					xmlAtt = xmlAttSel;
					break;
				}
			}
			return xmlAtt;
		}
		catch (Exception ex) {
			{}//Logwriter.printOnConsole("getXMLAttribute error : " + ex.getMessage());
			return null;
		}
	}
	
	public void loadConstantMapping() {
		try {
		
			xmlDoc = builder.build(mappingFile);
			Element attSet = xmlDoc.getRootElement();
			List attList = attSet.getChildren();
			Iterator attIter = attList.iterator();
			if (!(attList.isEmpty())) {
				//there are attributes
				constantMapping = new HashMap();
				while (attIter.hasNext()) {
					Integer attAgileConstantInteger = null;
					Element att = (Element)attIter.next();
					String attName = att.getName();
					Element attAgileConstant = att.getChild("agile-constant");
					String attAgileConstantString = attAgileConstant.getTextTrim();
					if (attAgileConstantString != null) {
						attAgileConstantInteger = Integer.valueOf(attAgileConstantString);
					}
					constantMapping.put(attName, attAgileConstantInteger);
				}
			}
		
		}
		catch (Exception ex) {
			ex.printStackTrace();
			{}//Logwriter.printOnConsole("loadMapping error : " + ex.getMessage());
		}
	}
	
	public HashMap getAttributeMapping () {
		return this.attributeMapping;
	}
	
	public HashMap getConstantMapping () {
		return this.constantMapping;
	}
}