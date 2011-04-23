<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/modelcheck/index.do" >
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="report"/>

<%@ include file="/web/include/header.jsp"%>


<table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
  <logic:iterate id="fieldName" name="hiModelcheck" property="selectedItems" type="zws.hi.report.MetadataAdapter"> 
    <tr>	            
      <td class="listItems"><bean:write name="fieldName" property="origin"/></td>
      <td>
      <!-- html:link href="index.do?event=delete" paramId="ID" paramName="fieldName" paramProperty="name" styleClass="button" title="remove">remove</html:link -->
      </td>
      <td><td/>
      <td><td/>
    </tr> 
  </logic:iterate>

</table>

<html:link href="javascript:sendEvent('promote')" styleClass="button" title="promote">promote</html:link>



</html:form>
</body>
</html:html>
