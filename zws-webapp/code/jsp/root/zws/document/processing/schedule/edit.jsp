<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/document/processing/schedule/index.do" focus="startingHour1">
<html:hidden property="ID"/>
<html:hidden property="event" value="update"/>
<html:hidden property="interactor" value="document.processing.schedule.edit"/>

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
                <td class="prop">process time 1: </td>
                <td class="value">
                  <html:select property="startingHour1" styleClass="input" onkeypress="tabOver(this,event)">
                    <html:options property="hours"/>
                  </html:select>:<html:select property="startingMinute1" styleClass="input" onkeypress="tabOver(this,event)">
                    <html:options property="minutes"/>
                  </html:select>
                </td>
              </tr>
              <tr>
                <td class="prop">process time 2: </td>
                <td class="value">
                  <html:select property="startingHour2" styleClass="input" onkeypress="tabOver(this,event)">
                    <html:options property="hours"/>
                  </html:select>:<html:select property="startingMinute2" styleClass="input" onkeypress="autoSubmit(this,event)">
                    <html:options property="minutes"/>
                  </html:select>
                </td>
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
          <td class="saveButton"><html:link href="javascript:send();" styleClass="saveButton">Save</html:link></td>
          <td class="cancelButton"><html:link href="view.jsp" styleClass="cancelButton">Cancel</html:link></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>
