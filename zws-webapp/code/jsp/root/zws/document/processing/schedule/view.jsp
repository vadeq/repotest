<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/document/processing/schedule/index.do">
<html:hidden property="ID"/>
<html:hidden property="nav" value="edit"/>
<html:hidden property="interactor" value="document.processing.schedule.view"/>

<%@ include file="/web/include/header.jsp"%>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">Document Processing</td>
  </tr>
  <tr>
    <td>
      <table class="formProps" border="0" cellpadding="0" cellspacing="1" width="100%">
        <tr>
          <td>
            <table class="formProps" border="0" cellpadding="2" cellspacing="0" width="100%">
              <tr>
                <td class="listHeader">#</td>
                <td class="listHeader">schedule</html:link></td>
                <td class="listHeader">status</td>
              </tr>
              <tr>
                <td class="item">1</td>
                <td class="item"><html:link href="edit.jsp" styleClass="editValue"><bean:write name="scheduler" property="startingHour1"/>:<bean:write name="scheduler" property="startingMinute1"/></html:link></td>
                <td class="item"><html:link href="index.do?event=toggleProcess1" styleClass="editValue"><bean:write name="scheduler" property="status1"/></html:link></td>
              </tr>
              <tr>
                <td class="item">2</td>
                <td class="item"><html:link href="edit.jsp" styleClass="editValue"><bean:write name="scheduler" property="startingHour2"/>:<bean:write name="scheduler" property="startingMinute2"/></html:link></td>
                <td class="item"><html:link href="index.do?event=toggleProcess2" styleClass="editValue"><bean:write name="scheduler" property="status2"/></html:link></td>
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
          <td class="editButton"><html:link href="edit.jsp" styleClass="editButton">Edit</html:link></td>
          <td class="cancelButton"><html:link href="index.do?event=processNow" styleClass="editButton">Process Now</html:link></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>
