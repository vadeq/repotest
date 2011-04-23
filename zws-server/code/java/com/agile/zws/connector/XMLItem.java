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

import org.jdom.*;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;
import com.agile.api.*;

public class XMLItem {
	
	private String itemNumber = "";
	private String description = "";
	private String infoMessage = "";
	private String errorMessage = "";
	private String fileErrorMessage = "";
	private Integer quantity = new Integer(1);
	private String bomMessage = "";
	private boolean libPart = false;
	
	//part attributes
	private String proI_Version = "";
	private String proI_CreateOn = "";
	private String proI_CreateBy = "";
	private String proI_ReleaseLevel = "";
	private String proI_Desc = "";
	private String proI_DwgRev = "";
	
	//file attributes
	private String lib_Bb_Max_X = "";
	private String lib_Bb_Max_Y = "";
	private String lib_Bb_Min_X = "";
	private String lib_Bb_Min_Y = "";
	private String lib_Height = "";
	private String lib_Mkp1_X = "";
	private String lib_Mkp1_Y = "";
	private String lib_Mkp2_X = "";
	private String lib_Mkp2_Y = "";
	private String lib_Mkp3_X = "";
	private String lib_Mkp3_Y = "";
	private String lib_Mkp4_X = "";
	private String lib_Mkp4_Y = "";
	private String lib_Pin_Tech = "";
	private String lib_Units = "";
	
	private XMLItem[] children = null;
	private String revision = "";
	private String binaryPath = "";
	private HashMap attributes = null;
	private HashMap setAttributes = null;
	
	public XMLItem() {
		
	}
	
	public void buildAttributes () {
		attributes = new HashMap();
		String attrValue = "";
		
		attrValue = getItemNumber();
		attributes.put(ItemConstants.ATT_TITLE_BLOCK_NUMBER, attrValue);
		attrValue = getDescription();
		attributes.put(ItemConstants.ATT_TITLE_BLOCK_DESCRIPTION, attrValue);
		
	}
	
	public void setAttributes (Element itemElement) {
		//get attributes from userElement
		/*
		Element itemNumber = itemElement.getChild("itemNumber");
		setItemNumber(itemNumber.getText());
		Element itemDescription = itemElement.getChild("description");
		setDescription(itemDescription.getText());
		Element itemQuantity = itemElement.getChild("quantity");
		String qty = itemQuantity.getText();
		Integer setQty = Integer.getInteger(qty);
		setQuantity(setQty);
		Element itemRevision = itemElement.getChild("revision");
		setRevision(itemRevision.getText());
		*/
		
		String itemNumber = itemElement.getAttributeValue("Partnumber");
		setItemNumber(itemNumber);
		String itemDescription = itemElement.getAttributeValue("Description");
		setDescription(itemDescription);
		String itemQuantity = itemElement.getAttributeValue("Quantity");
		if (itemQuantity != null) {
			{}//Logwriter.printOnConsole("XML Item String Qty : " + itemQuantity);
			//String qty = itemQuantity.getText();
			//Integer setQty = Integer.getInteger(itemQuantity);
			Integer setQty = Integer.valueOf(itemQuantity);
			{}//Logwriter.printOnConsole("XML Item Integer Qty : " + setQty);
			setQuantity(setQty);
		}
		else {
			setQuantity(new Integer(0));
		}
		String itemRevision = itemElement.getAttributeValue("Revision");
		setRevision(itemRevision);
		String binaryPath = itemElement.getAttributeValue("binary-path");
		setBinaryPath(binaryPath);
		
	}
	
	public void setAttributes (Element itemElement, XMLMapping attrMap) {
		//get attributes from userElement
		
		/*
		List itemAttributes = itemElement.getAttributes();
		Iterator attrIter = itemAttributes.iterator();
		setAttributes = new HashMap();
		while (attrIter.hasNext()) {
			//get attributes
			Attribute itemAtt = (Attribute)attrIter.next();
			String itemAttName = itemAtt.getName();
			HashMap mappedAttributes = attrMap.getAttributeMapping();
			Iterator mapAttIter = mappedAttributes.keySet().iterator();
			while (mapAttIter.hasNext()) {
				//get id
				//attrID = userAttributes.get(i);
				String attrName = (String)mapAttIter.next();
				if (itemAttName.equals(attrName)) {
					//get value to match attr name
					Integer constant = (Integer)attributes.get(attrName);
					setAttributes.put(constant, attrName);
				}
			}
		}
		setSetAttributes(setAttributes);
		*/
		String itemNumber = itemElement.getAttributeValue("number");
		{}//Logwriter.printOnConsole("parse setAttributes itemNumber : " + itemNumber);
		setItemNumber(itemNumber);
		
		//check to see if libPart
		if (itemNumber.indexOf("27-") > -1) {
			//this is libPart
			this.setLibPart(true);
		}
		
		//String itemDescription = itemElement.getAttributeValue("Description");
		//setDescription(itemDescription);
		String itemQuantity = itemElement.getAttributeValue("Quantity");
		if (itemQuantity != null) {
			{}//Logwriter.printOnConsole("XML Item String Qty : " + itemQuantity);
			//String qty = itemQuantity.getText();
			//Integer setQty = Integer.getInteger(itemQuantity);
			Integer setQty = Integer.valueOf(itemQuantity);
			{}//Logwriter.printOnConsole("XML Item Integer Qty : " + setQty);
			setQuantity(setQty);
		}
		else {
			setQuantity(new Integer(1));
		}
		String itemRevision = itemElement.getAttributeValue("revision");
		setRevision(itemRevision);
		String binaryPath = itemElement.getAttributeValue("binary-path");
		setBinaryPath(binaryPath);
		
		//set part attributes
		String relLevel = itemElement.getAttributeValue("Release-Level");
		this.setProIReleaseLevel(relLevel);
		String desc = itemElement.getAttributeValue("Desc");
		this.setProIDesc(desc);
		String dwgRevision = itemElement.getAttributeValue("Dwg_Rev");
		this.setProIDrawingRev(dwgRevision);
		String createdBy = itemElement.getAttributeValue("Created-By");
		this.setProiCreateBy(createdBy);
		String createdOn = itemElement.getAttributeValue("Created-On");
		this.setProiCreateOn(createdOn);
		String version = itemElement.getAttributeValue("version");
		this.setProIVersion(version);
		
		//set file attributes
		String val = "";
		val = itemElement.getAttributeValue("Lib_Bb_Max_X");
		this.setLibMaxX(val);
		val = itemElement.getAttributeValue("Lib_Bb_Max_Y");
		this.setLibMaxY(val);
		val = itemElement.getAttributeValue("Lib_Bb_Min_X");
		this.setLibMinX(val);
		val = itemElement.getAttributeValue("Lib_Bb_Min_Y");
		this.setLibMinY(val);
		val = itemElement.getAttributeValue("Lib_Height");
		this.setLibHeight(val);
		val = itemElement.getAttributeValue("Lib_Mkp1_X");
		this.setLibMkp1_X(val);
		val = itemElement.getAttributeValue("Lib_Mkp1_Y");
		this.setLibMkp1_Y(val);
		val = itemElement.getAttributeValue("Lib_Mkp2_X");
		this.setLibMkp2_X(val);
		val = itemElement.getAttributeValue("Lib_Mkp2_Y");
		this.setLibMkp2_Y(val);
		val = itemElement.getAttributeValue("Lib_Mkp3_X");
		this.setLibMkp3_X(val);
		val = itemElement.getAttributeValue("Lib_Mkp3_Y");
		this.setLibMkp3_Y(val);
		val = itemElement.getAttributeValue("Lib_Mkp4_X");
		this.setLibMkp4_X(val);
		val = itemElement.getAttributeValue("Lib_Mkp4_Y");
		this.setLibMkp4_Y(val);
		val = itemElement.getAttributeValue("Lib_Pin_Tech");
		this.setLibPinTech(val);
		val = itemElement.getAttributeValue("Lib_Units");
		this.setLibUnits(val);
	}
	
	//setters
	public void setItemNumber (String number) {
		this.itemNumber = number;
	}
	public void setDescription (String desc) {
		this.description = desc;
	}
	public void setQuantity (Integer qty) {
		this.quantity = qty;
	}
	public void setRevision (String rev) {
		this.revision = rev;
	}
	public void setBomMessage (String bomMsg) {
		this.bomMessage = bomMsg;
	}
	public void setInfoMessage (String msg) {
		this.infoMessage = msg;
	}
	public void setErrorMessage (String err) {
		this.errorMessage = err;
	}
	public void setFileErrorMessage (String fileErr) {
		this.fileErrorMessage = fileErr;
	}
	public void setChildren (XMLItem[] childs) {
		this.children = childs;
	}
	public void setSetAttributes (HashMap attr) {
		this.setAttributes = attr;
	}
	public void setBinaryPath (String binPath) {
		this.binaryPath = binPath;
	}
	public void setLibPart (boolean lib) {
		this.libPart = lib;
	}
	
	//part attribute setters
	public void setProiCreateOn (String create) {
		this.proI_CreateOn = create;
	}
	public void setProiCreateBy (String createBy) {
		this.proI_CreateBy = createBy;
	}
	public void setProIReleaseLevel (String rel) {
		this.proI_ReleaseLevel = rel;
	}
	public void setProIDesc (String proDesc) {
		this.proI_Desc = proDesc;
	}
	public void setProIDrawingRev (String dwgRev) {
		this.proI_DwgRev = dwgRev;
	}
	public void setProIVersion (String ver) {
		this.proI_Version = ver;
	}
	
	//file attribute setters
	public void setLibMaxX (String maxx) {
		this.lib_Bb_Max_X = maxx;
	}
	public void setLibMaxY (String maxy) {
		this.lib_Bb_Max_Y = maxy;
	}
	public void setLibMinX (String minx) {
		this.lib_Bb_Min_X = minx;
	}
	public void setLibMinY (String miny) {
		this.lib_Bb_Min_Y = miny;
	}
	public void setLibHeight (String height) {
		this.lib_Height = height;
	}
	public void setLibMkp1_X (String mk1x) {
		this.lib_Mkp1_X = mk1x;
	}
	public void setLibMkp1_Y (String mk1y) {
		this.lib_Mkp1_Y = mk1y;
	}
	public void setLibMkp2_X (String mk2x) {
		this.lib_Mkp2_X = mk2x;
	}
	public void setLibMkp2_Y (String mk2y) {
		this.lib_Mkp2_Y = mk2y;
	}
	public void setLibMkp3_X (String mk3x) {
		this.lib_Mkp3_X = mk3x;
	}
	public void setLibMkp3_Y (String mk3y) {
		this.lib_Mkp3_Y = mk3y;
	}
	public void setLibMkp4_X (String mk4x) {
		this.lib_Mkp4_X = mk4x;
	}
	public void setLibMkp4_Y (String mk4y) {
		this.lib_Mkp4_Y = mk4y;
	}
	public void setLibPinTech (String ptech) {
		this.lib_Pin_Tech = ptech;
	}
	public void setLibUnits (String units) {
		this.lib_Units = units;
	}

	//getters
	public String getItemNumber () {
		return this.itemNumber;
	}
	public String getDescription () {
		return this.description;
	}
	public Integer getQuantity () {
		return this.quantity;
	}
	public String getRevision () {
		return this.revision;
	}
	public String getErrorMessage () {
		return this.errorMessage;
	}
	public String getFileErrorMessage() {
		return this.fileErrorMessage;
	}
	public String getInfoMessage () {
		return this.infoMessage;
	}
	public String getBomMessage () {
		return this.bomMessage;
	}
	public String getBinaryPath () {
		return this.binaryPath;
	}
	public XMLItem[] getChildren () {
		return this.children;
	}
	public HashMap getSetAttributes() {
		return this.setAttributes;
	}
	public boolean getLibPart () {	
		return this.libPart;	
	}
	//part attribute getters
	public String getProICreatOn () {
		return this.proI_CreateOn;
	}
	public String getProICreatBy () {
		return this.proI_CreateBy;
	}
	public String getProIReleaseLevel () {
		return this.proI_ReleaseLevel;
	}
	public String getProIDesc () {
		return this.proI_Desc;
	}
	public String getProIDrwgRevision () {
		return this.proI_DwgRev;
	}
	public String getProIVersion () {
		return this.proI_Version;
	}
	
	//file attribute getters
	public String getLibMaxX () {
		return this.lib_Bb_Max_X;
	}
	public String getLibMaxY () {
		return this.lib_Bb_Max_Y;
	}
	public String getLibMinX () {
		return this.lib_Bb_Min_X;
	}
	public String getLibMinY () {
		return this.lib_Bb_Min_Y;
	}
	public String getLibHeight () {
		return this.lib_Height;
	}
	public String getLibMkp1X () {
		return this.lib_Mkp1_X;
	}
	public String getLibMkp1Y () {
		return this.lib_Mkp1_Y;
	}
	public String getLibMkp2X () {
		return this.lib_Mkp2_X;
	}
	public String getLibMkp2Y () {
		return this.lib_Mkp2_Y;
	}
	public String getLibMkp3X () {
		return this.lib_Mkp3_X;
	}
	public String getLibMkp3Y () {
		return this.lib_Mkp3_Y;
	}
	public String getLibMkp4X () {
		return this.lib_Mkp4_X;
	}
	public String getLibMkp4Y () {
		return this.lib_Mkp4_Y;
	}
	public String getLibPinTech() {
		return this.lib_Pin_Tech;
	}
	public String getLibUnits() {
		return this.lib_Units;
	}
}