<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/process/index.do">
<html:hidden property="interactor" value="chronjob.list"/>

<%@ include file="/web/include/header.jsp"%>
<table border="0" cellpadding="0" cellspacing="0">
<tr><td>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">Scheduled Processes</td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
          <td class="listHeader">Process</td>
        </tr>
        <logic:iterate id="item" name="hiProcessList" property="items" type="zws.hi.scheduler.hiProcessor">
        <tr class="item">
          <td class="item"><html:link href="index.do?event=processNow" paramId="choice" paramName="item" paramProperty="name" styleClass="button"><bean:write name="item" property="name"/></html:link></td>
        </tr>
        </logic:iterate>
      </table>
    </td>
  </tr>
</table>
</td></tr>
</table>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>