<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/publish/cisco.do">
<html:hidden property="event" value="verifyAll"/>
<html:hidden property="ID" value=""/>
<html:hidden property="publishPending"/>

<% String title="Detect Missing CPN"; %>
<%@ include file="/web/include/header.jsp"%>


<table border="0" cellpadding="0" cellspacing="0" class="form">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Cisco Part Numbers&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
	<tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
          <td class="listHeader">&nbsp</td>
          <td class="listHeader">PDM Name</td>
          <td class="listHeader">Cisco Part Number</td>
          <td class="listHeader"><nobr>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</nobr></td>
          <td class="listHeader">Description</td>
        </tr>
				<logic:iterate property="cpnItems" name="hiSearch" id="cpn" type="zws.hi.publish.CPNMetadata">
				<tr>
				   <td class="item">
				    <nobr>
				    <logic:equal name="cpn" property="statusCPN" value="cpn-synchronized">
					   <img length="15" width="15" title="<bean:write name="cpn" property="statusDescription"/>" src="/web/image/ok-grey.gif"/>
				    </logic:equal>
				    <logic:equal name="cpn" property="statusCPN" value="cpn-error">
					   <img length="15" width="15" title="<bean:write name="cpn" property="statusDescription"/>" src="/web/image/error.gif"/>
				    </logic:equal>
				    <logic:equal name="cpn" property="statusCPN" value="cpn-missing">
					   <img length="15" width="15" title="<bean:write name="cpn" property="statusDescription"/>" src="/web/image/alert.gif"/>
				    </logic:equal>
				    <logic:equal name="cpn" property="statusCPN" value="cpn-ready">
					   <img length="15" width="15" title="<bean:write name="cpn" property="statusDescription"/>" src="/web/image/ok.gif"/>
				    </logic:equal>
				    <logic:equal name="cpn" property="statusCPN" value="cpn-needs-verification">
					   <img length="15" width="15" title="<bean:write name="cpn" property="statusDescription"/>" src="/web/image/question.gif"/>
				    </logic:equal>
				    <logic:equal name="cpn" property="statusCPN" value="unknown">
					   <img length="15" width="15" title="<bean:write name="cpn" property="statusDescription"/>" src="/web/image/question.gif"/>
				    </logic:equal>
				    <logic:equal name="cpn" property="statusCPN" value="cpn-temporary">
					   <img length="15" width="15" title="<bean:write name="cpn" property="statusDescription"/>" src="/web/image/ok-orange.gif"/>
				    </logic:equal>
				    </nobr>
				   </td>
				   <td class="item"><bean:write name="cpn" property="name"/></td>
				   <td class="item">
				    <logic:equal name="cpn" property="mode" value="edit">
				     <input type="text" name="newCPNList" value="<bean:write name="cpn" property="targetCPN"/>" onkeypress="tabOver(this, event);"/>
				    </logic:equal>
				    <logic:equal name="cpn" property="mode" value="saved">
					   <a href="#" onclick="sendEventID('edit', '<bean:write name="cpn" property="name"/>');"  class="okButton">
					   <bean:write name="cpn" property="targetCPN"/>
					   </a>
				    </logic:equal>
				    <logic:equal name="cpn" property="mode" value="locked">
				     <bean:write name="cpn" property="targetCPN"/>
				    </logic:equal>
				   </td>
				   <td class="item">
				    <logic:equal name="cpn" property="mode" value="edit">
					   <a href="#" onclick="sendEventID('verifyCPN', '<bean:write name="cpn" property="name"/>');"  class="okButton">
					   <img gif="verify"/></a>
				    </logic:equal>
				   </td>
				   <td class="item"><bean:write name="cpn" property="description"/></td>
				</tr>
				</logic:iterate>
				<tr>
				   <td class="item" colspan="2">
				    <html:link href="#" onclick="sendEvent('verifyAll');" styleClass="okButton"><img gif="verify_all"/></html:link>
				   </td>
				   <td class="item"><nobr><a href="#" onclick="sendEvent('editAll');"  class="okButton"><img gif="edit_all"/></a></nobr></td>
				   <td class="item"><nobr>&nbsp;</nobr></td>
				   <td class="item"><nobr>&nbsp;</nobr></td>
				</tr>
      </table>
    </td>
  </tr>
</table>
</td></tr>
  <tr class="formMenu">
   <td>
    <table border="0" cellpadding="1" cellspacing="0" width="100%">
     <tr>
      <td class="saveButton"><html:link href="#" onclick="sendEvent('cpnPublish');" styleClass="okButton"><img gif="publish"/></html:link></td>
      <td class="cancelButton"><html:link href="cisco.do" styleClass="cancelButton"><img gif="cancel"/></html:link></td>
     </tr>
    </table>
   </td>
  </tr>
</table>

     <!--
  	    	<td class="clear">&nbsp;&nbsp;&nbsp;</td>
			    <td valign="top">
					<nobr>Search for CPN: <input type="text" name="criteriaCPN" value="<bean:write name="hiSearch" property="criteriaCPN"/>"/>
					<html:link href="#" onclick="sendEvent('cpnSearch');" styleClass="okButton"><img gif="search"/></html:link></nobr><br>
					<hr>
					<logic:present property="searchResultsCPN" name="hiSearch">
					<logic:iterate property="searchResultsCPN" name="hiSearch" id="cpn" type="java.lang.String">
					   <bean:write name="cpn"/><br>
					</logic:iterate>
					</logic:present>
					<hr>
		    </td>
	   -->


<%@ include file="/web/include/footer.jsp"%>
</html:form>
</body>
</html:html>
