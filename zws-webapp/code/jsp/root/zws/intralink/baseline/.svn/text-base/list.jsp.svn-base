<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/intralink/baseline/index.do">
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="baseline.list"/>
<html:hidden property="hiState" value="list"/>

<%@ include file="/web/include/header.jsp"%>
<SCRIPT Language="javascript">
// This function must be implemented bc it is called by DHTML Treeview everytime
// you click on a leave
function selectLeaf(title, code) {
    // No implementation
}
function selectFolder(folderCode) {
    // To update on the server the state of the treeview, submits the code from an hidden frame
    parent.frames[1].document.forms[0].folderCode.value = folderCode;
    parent.frames[1].document.forms[0].submit();
}
</SCRIPT>

<table border="0" cellpadding="0" cellspacing="8">
<tr>
<td valign="top" align="right">
<table border="0" cellpadding="0" cellspacing="0">
 <tr>
  <td>
   <table class="form" border="0" cellpadding="0" cellspacing="0">
    <tr> <td class="formTitle">Baselines</td> </tr>
    <tr>
     <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
       <tr> <td class="listHeader">name</td> <td class="listHeader">location</td> </tr>
       <logic:iterate id="e" name="hiBaselineList" property="items" type="zws.hi.intralink.baseline.hiBaseline">
       <tr class="item">
        <td class="item">
         <a href="index.do?event=choose&ID=<%=e.getName()%>" class="button" title="view baseline">
         <bean:write name="e" property="name"/>
         </a>
        </td>
        <td class="item"><bean:write name="e" property="location"/></td>
     	 </tr>
       </logic:iterate>
      </table>
     </td>
    </tr>
   </table>
  </td>
 </tr>
 <tr><td><html:link href="javascript:add()" styleClass="button" title="add a new baseline">add</html:link></td></tr>
</table>
</td>
<logic:present name="hiBaselineList" property="chosenItem">
<td valign="top" align="left">
<bean:define id="chosenItem" name="hiBaselineList" property="chosenItem" type="zws.hi.intralink.baseline.hiBaseline"/>
<table border="0" cellpadding="0" cellspacing="0">
 <tr>
  <td>
   <table class="chosen-form" border="0" cellpadding="0" cellspacing="0">
    <tr> <td class="chosen-formTitle"><bean:write name="chosenItem" property="name"/></td> </tr>
    <tr>
     <td>
      <table class="chosen-listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
       <tr> <td class="chosen-listHeader">Component</td> <td class="chosen-listHeader">Branch</td> <td class="chosen-listHeader">Configuration</td> </tr>
       <logic:present name="chosenItem" property="files">
       <logic:iterate id="component" name="chosenItem" property="files" type="com.zws.domo.baseline.Fileentry">
       <tr class="chosen-item">
        <td class="chosen-item"> <bean:write name="component" property="name"/> </td>
        <td class="chosen-item"> <bean:write name="component" property="branch"/> </td>
        <td class="chosen-item"> <bean:write name="component" property="revision"/>&nbsp;&nbsp;<bean:write name="component" property="version"/> </td>
     	 </tr>
       </logic:iterate>
       </logic:present>
       <logic:notPresent name="chosenItem" property="files">
       <tr class="chosen-item">
        <td class="chosen-item" colspan="3"> Baseline has no files.</td>
       </tr>
       </logic:notPresent>
      </table>
     </td>
    </tr>
   </table>
  </td>
 </tr>
 <tr>
  <td>
   <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
     <td class="editButton"> <html:link href="index.do?nav=edit" paramId="choice" paramName="component" paramProperty="name" styleClass="editButton" title="edit baseline">edit</html:link></td>
     <logic:equal name="__member" property="role" value="admin" scope="session">
      <td class="deleteButton"><html:link href="index.do?event=delete" paramId="ID" paramName="chosenItem" paramProperty="name" styleClass="deleteButton" title="delete baseline">delete</html:link></td>
     </logic:equal>
     <logic:notEqual name="__member" property="role" value="admin" scope="session">
      <td class="button">&nbsp;</td>
     </logic:notEqual>
    </tr>
   </table>
  </td>
 </tr>
</table>
</logic:present>
</td>
</tr>
</table>
</html:form>


<%@ include file="/web/include/footer.jsp"%>
</body>
</html:html>
