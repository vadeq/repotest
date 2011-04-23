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

import java.util.Iterator;
import java.util.HashMap;
import com.agile.api.*;

public class XMLAttribute {
	
	private String attrName = "";
	private String attrProiName = "";
	private String attrAgileName = "";
	private Integer attrAgileConstantID = null;
	private boolean attrEditable = false;
	private String attrValue = "";
	private String bomMessage = "";
	private XMLItem[] children = null;
	private String revision = "";
	private String binaryPath = "";
	private HashMap attributes = null;
	private HashMap setAttributes = null;
	
	public XMLAttribute() {
		
	}
	
	public XMLAttribute(XMLAttribute copyAtt) {
		XMLAttribute newAtt = new XMLAttribute();
		newAtt.setAttrName(copyAtt.getAttrName());
		newAtt.setAttrAgileName(copyAtt.getAttrAgileName());
	}
	
	
	//setters
	public void setAttrName (String name) {
		this.attrName = name;
	}
	public void setAttrProiName (String proi) {
		this.attrProiName = proi;
	}
	public void setAttrAgileName (String agile) {
		this.attrAgileName = agile;
	}
	public void setAttrAgileConstant (Integer constant) {
		this.attrAgileConstantID = constant;
	}
	public void setAttrEditable (boolean edit) {
		this.attrEditable = edit;
	}
	public void setAttrValue (String val) {
		this.attrValue = val;
	}

	//getters
	public String getAttrName () {
		return this.attrName;
	}
	public String getAttrProiName () {
		return this.attrProiName;
	}
	public String getAttrAgileName () {
		return this.attrAgileName;
	}
	public Integer getAttrAgileConstant () {
		return this.attrAgileConstantID;
	}
	public boolean getAttrEditable () {
		return this.attrEditable;
	}
	public String getAttrValue () {
		return this.attrValue;
	}
	
	
}