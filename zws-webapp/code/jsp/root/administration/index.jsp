<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/administration/index.do" >
<html:hidden property="event" value="reload"/>
<html:hidden property="interactor" value="administration"/>

<% String title="Administration"; %>
<%@ include file="/web/include/header.jsp"%>
<table class="form" border="0" cellpadding="0" cellspacing="0" width="95%" align="left">
  <tr>
    <td class="formTitle" align="left">
      <div align="left">
        &nbsp;Administration
      </div>
    </td>
  </tr>
  <tr>
    <td>
      Server: <html:text property="serverName"/>&nbsp;
      <html:link href="javascript:sendEvent('load')" styleClass="button">Load</html:link>&nbsp;
      <html:link href="javascript:send()" styleClass="button">Reload</html:link>&nbsp;
    </td>
  </tr>
</table>

</html:form>
<%@ include file="/web/include/footer.jsp"%>
</body>
</html:html>
