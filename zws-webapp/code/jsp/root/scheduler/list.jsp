<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/scheduler/index.do">
<html:hidden property="interactor" value="chronjob.list"/>

<% String title="Scheduler"; %>
<%@ include file="/web/include/header.jsp"%>
<table border="0" cellpadding="0" cellspacing="0">
<tr><td>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">Scheduled Jobs</td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
          <td class="listHeader">Job</td>
          <td class="listHeader">Process</td>
          <td class="listHeader">Start Time</td>
          <td class="listHeader">Frequency</td>
          <td class="listHeader">Status</td>
          <td class="listHeader">&nbsp;</td>
          <td class="listHeader">&nbsp;</td>
        </tr>
        <logic:iterate id="e" name="hiChronJobList" property="items" type="zws.hi.scheduler.hiChronJob">
        <bean:define id="jobName" name="e" property="jobName"/>
        <tr class="item">
          <td class="item"><html:link href="item.do?nav=edit" paramId="ID" paramName="e" paramProperty="jobName" styleClass="button"><bean:write name="e" property="jobName"/></html:link></td>
          <td class="item"><bean:write name="e" property="processName"/></td>
          <td class="item"><bean:write name="e" property="startHour"/>:<bean:write name="e" property="startMinute"/></td>
          <td class="item">every <bean:write name="e" property="periodHours"/> hours <bean:write name="e" property="periodMinutes"/> minutes</td>
          <td class="item">&nbsp;<html:link href="index.do?event=toggleJob" paramId="ID" paramName="e" paramProperty="jobName" styleClass="button" title="start/stop this process"><bean:write name="e" property="status"/></html:link>&nbsp;</td>
          <td class="item">&nbsp;<html:link href="index.do?event=processNow" paramId="ID" paramName="e" paramProperty="jobName" styleClass="button" title="run this process immediately">process now</html:link>&nbsp;</td>
          <td class="itemIcon" width="1%"><a onclick="if(confirm('OK to delete <%=jobName%>?')) return true; else return false;" href="index.do?event=delete&ID=<%=jobName%>" class="deleteButton" title="Delete job - <%=jobName%>"><img gif="itrash"/></a></td>
        </tr>
        </logic:iterate>
      </table>
    </td>
  </tr>
</table>
</td></tr>
  <tr class="formMenu">
   <td>
    <table border="0" cellpadding="1" cellspacing="0" width="100%">
     <tr>
      <td class="saveButton"><html:link href="item.do?nav=add" styleClass="button" title="add a new job"><img gif="add"/></html:link></td>
     </tr>
    </table>
   </td>
  </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>