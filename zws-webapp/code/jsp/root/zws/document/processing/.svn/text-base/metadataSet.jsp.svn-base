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
<html:hidden property="interactor" value="document.metadataSet"/>

<%@ include file="/web/include/header.jsp"%>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">&nbsp;Report Metadata:
      <html:select name="metadataSet" property="activeReportName" styleClass="input" onkeypress="tabOver(this,event)" onchange="sendEvent('reload')">
        <html:options name="docList" property="reportNames"/>
      </html:select>&nbsp;&nbsp;
    </td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
          <td class="listHeader">show</td>
          <td class="listHeader">name</td>
          <!-- delete from configuration not yet supported -->
          <!--
          <td width="10" class="listHeader">&nbsp;</td>
          -->
        </tr>
        <logic:iterate id="i" name="metadataSet" property="items" type="com.zws.functor.report.DisplayField">
        <tr class="item">
          <td class="item"><html:multibox onclick="javascript:sendEvent('save');" property="visibleAttributes"><bean:write name="i" property="name"/></html:multibox></td>
          <td class="item"><bean:write name="i" property="name"/></td>
          <!-- delete from configuration not yet supported -->
          <!--
          <td class="deleteItem"><html:link href="metadataSet.do?event=delete" paramId="attributeName" paramName="i" paramProperty="name" styleClass="deleteItem" title="Delete this attribute!">x</html:link></td>
          -->
        </tr>
          </logic:iterate>
      </table>
    </td>
  </tr>
  <!-- add to configuration not yet supported -->
  <!--
  <tr class="formMenu">
    <td>
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td>
  <html:text property="attributeName" styleClass="input"  onkeypress="autoSubmit(this,event)"/><html:link href="javascript:sendEvent('add');" styleClass="button">add</html:link>
         </td>
        </tr>
      </table>
    </td>
  </tr>
  -->
</table>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>
