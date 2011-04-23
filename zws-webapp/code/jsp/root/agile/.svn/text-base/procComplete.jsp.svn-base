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
    <td class="pageTitle" align="left">Requested (<bean:write name="hiProcOnFly" property="viewableType"/>) Viewable Generation for the Following Files: </td>
  </tr>
</table>

<table class="listItems" border="0" cellpadding="2" cellspacing="1" width="95%">

    <tr>	            
      
          <logic:iterate id="fieldName" name="hiProcOnFly" property="metadataFields" type="java.lang.String">
            <td class="listHeader"><bean:write name="fieldName"/></td>
          </logic:iterate>
    </tr>
	<logic:iterate id="metadata" name="hiProcOnFly" property="chosenItems" type="zws.hi.report.MetadataAdapter"> 
        <tr>
          
          <logic:iterate id="field" name="metadata" property="values" type="java.lang.String"> 
        
				<td class="item"><bean:write name="field"/></td>

          </logic:iterate>
        </tr> 
	 </logic:iterate>

</table>





</html:form>
</body>
</html:html>
