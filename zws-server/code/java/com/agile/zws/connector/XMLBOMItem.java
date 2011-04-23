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
import java.util.HashMap;
import com.agile.api.*;

public class XMLBOMItem {
	
	private String userID = "";
	private String employeeNumber = "";
	private String userFName = "";
	private String userLName = "";
	private String userMName = "";
	private String userEmail = "";
	private String userStatus = "";
	private String userDeptNumber = "";
	private String mgrEmployeeNumber = "";
	private String userBusinessUnit = "";
	private String businessUnitDeptNumber = "";	
	private String userTechnologyGroup = "";	
	private String technologyGroupNumber = "";	
	private HashMap attributes = null;
	
	public XMLBOMItem() {
		
	}
	
	public void buildAttributes () {
		attributes = new HashMap();
		String attrValue = "";
		
		attrValue = getUserFName();
		attributes.put(UserConstants.ATT_GENERAL_INFO_FIRST_NAME, attrValue);
		attrValue = getUserLName();
		attributes.put(UserConstants.ATT_GENERAL_INFO_LAST_NAME, attrValue);
		attrValue = getUserMName();
		attributes.put(UserConstants.ATT_GENERAL_INFO_MIDDLE_NAME, attrValue);
		attrValue = getUserEmail();
		attributes.put(UserConstants.ATT_GENERAL_INFO_EMAIL, attrValue);
		attrValue = getEmployeeNumber();
		attributes.put(UserConstants.ATT_PAGE_TWO_TEXT11, attrValue);
		attrValue = getUserDeptNumber();
		attributes.put(UserConstants.ATT_PAGE_TWO_TEXT12, attrValue);
		attrValue = getMgrEmployeeNumber();
		attributes.put(UserConstants.ATT_PAGE_TWO_TEXT13, attrValue);
		attrValue = getUserBusinessUnit();
		attributes.put(UserConstants.ATT_PAGE_TWO_TEXT14, attrValue);
		attrValue = getBUDeptNumber();
		attributes.put(UserConstants.ATT_PAGE_TWO_TEXT15, attrValue);
		attrValue = this.getUserTechnologyGroup();
		attributes.put(UserConstants.ATT_PAGE_TWO_TEXT16, attrValue);
		attrValue = this.getTechnologyGroupNumber();
		attributes.put(UserConstants.ATT_PAGE_TWO_TEXT17, attrValue);
	}
	
	public void setAttributes (Element userElement) {
		//get attributes from userElement
		Element employeeNumber = userElement.getChild("EMPLOYEE_NUMBER");
		setEmployeeNumber(employeeNumber.getText());
		Element email = userElement.getChild("EMAIL");
		setUserEmail(email.getText());
		Element firstName = userElement.getChild("FIRST_NAME");
		setUserFName(firstName.getText());
		Element lastName = userElement.getChild("LAST_NAME");
		setUserLName(lastName.getText());
		Element middleName = userElement.getChild("MIDDLE_NAME");
		setUserMName(middleName.getText());
		Element status = userElement.getChild("STATUS");
		setUserStatus(status.getText());
		Element departmentNumber = userElement.getChild("DEPT_NUMBER");
		setUserDeptNumber(departmentNumber.getText());
		Element managerEmpNumber = userElement.getChild("MANAGER_EMP_NUMBER");
		setMgrEmployeeNumber(managerEmpNumber.getText());
		Element businessUnit = userElement.getChild("BU");
		setUserBusinessUnit(businessUnit.getText());
		Element buDeptNumber = userElement.getChild("BU_DEPT_NUMBER");
		setBusUnitDeptNumber(buDeptNumber.getText());
		Element technologyGroup = userElement.getChild("TG");
		setUserTechnologyGroup(technologyGroup.getText());
		Element tgNumber = userElement.getChild("TG_DEPT_NUMBER");
		setTechnologyGroupNumber(tgNumber.getText());
	}
	
	//setters
	public void setUserID (String userid) {
		this.userID = userid;
	}
	public void setEmployeeNumber (String empNum) {
		this.employeeNumber = empNum;
	}
	public void setUserFName (String fname) {
		this.userFName = fname;
	}
	public void setUserLName (String lname) {
		this.userLName = lname;
	}
	public void setUserMName (String mname) {
		this.userMName = mname;
	}
	public void setUserEmail (String email) {
		this.userEmail = email;
	}
	public void setUserStatus (String status) {
		this.userStatus = status;
	}
	public void setUserDeptNumber (String dptNum) {
		this.userDeptNumber = dptNum;
	}
	public void setMgrEmployeeNumber (String mgrNum) {
		this.mgrEmployeeNumber = mgrNum;
	}
	public void setUserBusinessUnit (String bu) {
		this.userBusinessUnit = bu;
	}	
	public void setBusUnitDeptNumber (String buNum) {
		this.businessUnitDeptNumber = buNum;
	}
	public void setUserTechnologyGroup (String tg) {
		this.userTechnologyGroup = tg;
	}
	public void setTechnologyGroupNumber (String tgNum) {
		this.technologyGroupNumber = tgNum;
	}
	
	

	//getters
	public String getUserID () {
		return this.userID;
	}
	public String getEmployeeNumber () {
		return this.employeeNumber;
	}
	public String getUserFName () {
		return this.userFName;
	}
	public String getUserLName () {
		return this.userLName;
	}
	public String getUserMName () {
		return this.userMName;
	}
	public String getUserEmail() {
		return this.userEmail;
	}
	public HashMap getUserAttributes () {
		return this.attributes;
	}
	public String getUserStatus () {
		return this.userStatus;
	}
	public String getUserDeptNumber () {
		return this.userDeptNumber;
	}
	public String getMgrEmployeeNumber () {
		return this.mgrEmployeeNumber;
	}
	public String getUserBusinessUnit () {
		return this.userBusinessUnit;
	}
	public String getBUDeptNumber () {
		return this.businessUnitDeptNumber;
	}
	public String getUserTechnologyGroup () {
		return this.userTechnologyGroup;
	}
	public String getTechnologyGroupNumber () {
		return this.technologyGroupNumber;
	}
}