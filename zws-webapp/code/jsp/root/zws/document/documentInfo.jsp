<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/document/processing/metadataSet.do">
<html:hidden property="event" value="add"/>

<%@ include file="/web/include/header.jsp"%>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">Metadata Attributes</td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
          <td class="listHeader">Name</td>
          <td width="10" class="listHeader">Value</td>
        </tr>
        <logic:iterate id="i" name="metadataSet" property="items" type="com.zws.util.KeyValue">
        <tr class="item">
          <td class="item"><bean:write name="i" property="key"/></td>
          <td class="item">{value}</td>
        </tr>
          </logic:iterate>
      </table>
    </td>
  </tr>
  <tr class="formMenu">
    <td>
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td><html:link href="javascript:window.close()" styleClass="button">close</html:link></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>
