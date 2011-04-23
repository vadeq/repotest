<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/procOnFly/index.do" >
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="report"/>

<%@ include file="/include/header.jsp"%>

<table class="form" border="1" cellpadding="0" cellspacing="0" width="95%">
  <tr align="left">
    <td class="pageTitle" align="left">Publishing Complete:</td>
  </tr>
</table>

<table class="listItems" border="0" cellpadding="2" cellspacing="1" width="95%">
    <tr>	   
	<td class="listHeader">Save</td>         
          <logic:iterate id="fieldName" name="hiPublish" property="metadataFields" type="java.lang.String">
            <td class="listHeader"><bean:write name="fieldName"/></td>
          </logic:iterate>
    </tr>
	<logic:iterate id="metadata" name="hiPublish" property="items" type="zws.hi.report.MetadataAdapter"> 
        <tr>
          <td class="item" width="1" align="center"> <nobr> <html:link href="/report/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="origin" styleClass="button"><img border="0" src="/image/download.jpg" width="18" height="16" alt="Save Local Copy of File"/></html:link>&nbsp; 
            <!-- <html:link href="/report/index.do?event=info" paramId="ID" paramName="metadata" paramProperty="origin" styleClass="button"><img border="0" src="/image/info.jpg" width="18" height="17"/></html:link> --> 
            </nobr> </td>
          <logic:iterate id="field" name="metadata" property="values" type="java.lang.String"> 
        
				<td class="item"><bean:write name="field"/></td>

          </logic:iterate>
        </tr> 
	 </logic:iterate>

</table>


</html:form>
</body>
</html:html>
