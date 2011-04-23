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
<bean:define id="agileStartPart" name="hiCiscoStartPart" property="agileStartPart" type="zws.data.Metadata" /> <!-- atype="zws.hi.custom.cisco.AgileMetadataAdapter" -->
<bean:define id="origin" name="agileStartPart" property="origin" type="zws.origin.Origin"/>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">&nbsp;Number: <bean:write name="agileStartPart" property="name"/>&nbsp;</td>
  </tr>
  <tr>
    <td>
      <table class="formProps" border="0" cellpadding="0" cellspacing="1" width="100%">
        <tr>
          <td>
            <table class="formProps" border="0" cellpadding="4" cellspacing="0" width="100%">
              <logic:iterate id="field" name="agileStartPart" property="fieldNames" type="java.lang.String">
              <% String value = agileStartPart.get(field); %>
              <tr>
                <td class="prop"><bean:write name="field"/>:</td>
                <td class="value"><%=value%></td>
              </tr>
              </logic:iterate>
            </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr class="formMenu">
    <td>
    <table border="0" cellpadding="3" cellspacing="0" width="100%">
        <tr>
          <td class="button"><html:link href="#" onclick="javascript:sendEvent('createProEPart')" style="button">Create ProE Standard Part</html:link></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>
</html:form>
</body>
</html:html>
