<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/intralink/baseline/add.do" >
<html:hidden property="ID" value="search"/>
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="hiBaseline"/>

<%@ include file="/web/include/header.jsp"%>
<bean:define id="hiBaseline" name="hiBaseline" type="zws.hi.intralink.baseline.hiBaseline"/>
<SCRIPT Language="javascript">

function selectFolder(folder , code) {}
function selectLeaf(title, origin) { sendEventID('addFile', origin); }

</SCRIPT>

<center><font size="+1"><b>Baseline: <bean:write name="hiBaseline" property="name"/></b></font></center><p/>

<center>
<table border="0" cellpadding="2" cellspacing="2" >
<tr>

<td valign="top" align="right">
<table border="0" cellpadding="0" cellspacing="0">
 <tr>
  <td>
   <table class="chosen-form" border="0" cellpadding="0" cellspacing="0">
    <tr> <td class="chosen-formTitle"><bean:write name="hiBaseline" property="name"/></td> </tr>
    <tr>
     <td>
      <table class="chosen-listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
       <tr>
        <td class="chosen-listHeader">Component</td>
        <td class="chosen-listHeader">Configuration</td>
        <logic:equal name="__member" property="role" value="admin" scope="session">
         <td class="chosen-listHeader">&nbsp;</td>
        </logic:equal>
       </tr>
       <logic:present name="hiBaseline" property="files">
       <logic:iterate id="component" name="hiBaseline" property="files" type="com.zws.domo.baseline.Fileentry">
       <tr class="chosen-item">
        <td class="chosen-item" valign="top"> <bean:write name="component" property="name"/> </td>
        <td class="chosen-item" valign="top"> <bean:write name="component" property="branch"/>&nbsp;&nbsp;<bean:write name="component" property="revision"/>&nbsp;&nbsp;<bean:write name="component" property="version"/> 
        <logic:present name="hiBaseline" property="chosenItem">
        <% if (component.getName().equals(((zws.hi.report.MetadataAdapter)hiBaseline.getChosenItem()).getName())) {%>
        <hr><%=hiBaseline.getFigTree()%><hr>
        <%}%>
        </logic:present>
        </td>
        <logic:equal name="__member" property="role" value="admin" scope="session">
         <td class="chosen-item" valign="top"><a href="javascript:sendEventID('removeFile', '<%=component.getName()%>')" class="button">x</a></td>
        </logic:equal>
     	 </tr>
       </logic:iterate>
       </logic:present>
       <logic:notPresent name="hiBaseline" property="files">
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
    <tr><td class="saveButton"><a href="edit.do?event=save" class="saveButton" title="Save baseline">save</a></td> <td class="cancelButton"> <a href="index.do?event=clear" paramId="choice" class="cancelButton" title="Cancel changes">cancel</a></td> </tr>
   </table>
  </td>
 </tr>
</table>

</td>

<td valign="top" align="left" >
<table class="form" border="0" cellpadding="0" cellspacing="0" width="95%">
  <tr> <td class="formTitle" align="left">&nbsp;<html:link href="javascript:send()" styleClass="formTitle">Component Search</html:link>&nbsp; </td> </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
          <logic:iterate id="fieldName" name="hiBaseline" property="metadataFields" type="java.lang.String">
            <% String len=fieldName.length()+""; 
               if (fieldName.equalsIgnoreCase("name")) len = "18";%>
            <td class="listHeader"><nobr><bean:write name="fieldName"/></nobr><br><html:text name="hiBaseline" property="<%=fieldName.toLowerCase().replace('-', '_').replace(' ', '_')+"Criteria"%>" size="<%=len%>" onkeypress="return autoSubmit(this, event)"/></td>
          </logic:iterate>
        </tr>
        <logic:present name="hiBaseline" property="items">
        <logic:iterate id="metadata" name="hiBaseline" property="items" type="zws.hi.report.MetadataAdapter">
        <tr>
          <%int col=0;%>
          <logic:iterate id="field" name="metadata" property="values" type="java.lang.String">
            <bean:define id="origin" name="metadata" property="origin" type="zws.origin.Origin"/>
            <td class="item">
              <% if (0==col)   { %> 
                <a href="javascript:sendEventID('chooseFile', '<%=metadata.getOrigin()%>')" class="button"> 
              <% } %>
              <bean:write name="field"/>
              <% if (0==col++) { %> </a> <% } %>
            </td>
          </logic:iterate>
        </tr>
        </logic:iterate>
        </logic:present>
        <logic:notPresent name="hiBaseline" property="items" >Enter Search Criteria</td></tr>
        </logic:notPresent>
      </table>
    </td>
  </tr>
</table>
</td>
</tr>
</table>
</html:form>
</center>

</body>
</html:html>
