<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white" onLoad="tID = setTimeout('executeTimer()', refreshInterval)" onMouseMove="resetTimer()">
<html:form action="/demo/start-part/index.do">
<html:hidden property="event" value="startPart"/>
<html:hidden property="interactor" value="startPart"/>

<%@ include file="/web/include/interactor.params"%>

<% String title="Agile - ProE Start Part"; %>
<%@ include file="/web/include/header.jsp"%>

<script type="text/javascript" language="JavaScript" src="/web/javascript/report/report.js"></script>

<table border="0" cellpadding="2" cellspacing="5">
 <tr>
  <td valign="top" class="agileProperty">Agile Part Number:</td>
  <td valign="top" class="agileValue">
	<html:text property="number" size="32" styleClass="input" onkeypress="autoSubmit(this, event);"/>
  </td>
  <td>
<!--
    <html:link href="#" onclick="javascript:sendEvent('startPart')" styleClass="button">start-part</html:link>
-->
  </td>
 </tr>
</table>

<%@ include file="/web/include/footer.jsp"%>
</html:form>
<script language="JavaScript">
  populateCriteria();
</script>
</body>
</html:html>
