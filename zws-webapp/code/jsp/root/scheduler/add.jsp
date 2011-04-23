<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/scheduler/add.do" focus="jobName">
<html:hidden property="ID"/>
<html:hidden property="event" value="add"/>
<html:hidden property="interactor" value="scheduler.add"/>

<% String title="Schedule New Job"; %>
<%@ include file="/web/include/header.jsp"%>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">&nbsp;Define Job&nbsp;</td>
  </tr>
  <tr>
    <td>
      <table class="formProps" border="0" cellpadding="0" cellspacing="1" width="100%">
        <tr>
          <td>
            <table class="formProps" border="0" cellpadding="2" cellspacing="0" width="100%">
              <tr>
                <td class="prop">Job Name:</td>
                <td class="value">
                  <html:text property="jobName" styleClass="input" onkeypress="tabOver(this,event)"/>
                </td>
              </tr>
              <tr>
                <td class="prop">process:</td>
                <td class="value">
                  <html:select property="processName" styleClass="input" onkeypress="tabOver(this,event)">
                    <html:options property="availableProcessors"/>
                  </html:select>
                </td>
              </tr>
              <tr>
                <td class="prop">start time:</td>
                <td class="value">
                  <html:select property="startHour" styleClass="input" onkeypress="tabOver(this,event)">
                    <html:options property="hoursInDay"/>
                  </html:select>:<html:select property="startMinute" styleClass="input" onkeypress="tabOver(this,event)">
                    <html:options property="minutesInHour"/>
                  </html:select>
                </td>
              </tr>
              <tr>
                <td class="prop">run after every:</td>
                <td class="value">
                  <html:select property="periodHours" styleClass="input" onkeypress="tabOver(this,event)">
                    <html:options property="hoursInPeriod"/> hours
                  </html:select>:<html:select property="periodMinutes" styleClass="input" onkeypress="autoSubmit(this,event)">
                    <html:options property="minutesInHour"/> minutes
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
          <td class="saveButton"><html:link href="javascript:send();" styleClass="saveButton"><img gif="save"/></html:link></td>
          <td class="cancelButton"><html:link href="index.do" styleClass="cancelButton"><img gif="cancel"/></html:link></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>
