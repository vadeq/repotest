<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>
<script type="text/javascript">

	function monitor(action, queue) {
		document.getElementById('queue').value=queue;
		document.getElementById('event').value=action;

		if (action == "purge") {
			var agree = confirm('Are you sure you wish to delete ALL items in the queue?');
			if (agree) {
				send();
			}
		} else {
			send();
		}		
	}

</script>

<html>
<body topmargin="0" leftmargin="0" bgcolor="white">
	<% String title="Queue Monitor"; %>
	<%@ include file="/web/include/header.jsp"%>


	<!-- ******* Queue Monitor ******* -->
<html:form action="/queue/monitor.do">
	<input type="hidden" id="event" name="event" value=""/>
	<input type="hidden" id="queue" name="queue" value=""/>

	<logic:iterate id="monitorInfo" name="hiQueueMonitor" property="monitorInfo" type="zws.hi.queue.MonitoredQueue" indexId="index">
		<bean:size id="numQueueItems" name="monitorInfo" property="queueItems" />
		<bean:define name="monitorInfo" property="state" id="state" />

		<table class="formProps" border="1"  cellpadding="3" cellspacing="1" width="90%"> 

			<!-- table header -->
			<tr >
				<td colspan="2"><table border=0 cellpadding="0" cellspacing="0" width="100%" >
					<tr ><td align="left">
						<bean:write name="monitorInfo" property="name" />&nbsp;&nbsp;
						<c:choose>
							<c:when test="${state == true}">(Active)</c:when>
							<c:otherwise>(Inactive)</c:otherwise>
						</c:choose>
					</td><td align="right">
						<a href="javascript:monitor('start','<bean:write name="monitorInfo" property="name" />');" styleClass="saveButton"><img gif="start_1"/></a>
						<a href="javascript:monitor('stop','<bean:write name="monitorInfo" property="name" />');" styleClass="saveButton"><img gif="stop_1"/></a>
						<a href="javascript:monitor('purge','<bean:write name="monitorInfo" property="name" />');" styleClass="saveButton"><img gif="delete"/></a>
						
					</td></tr>
				</table></td>
			</tr>					

			<logic:notEqual name="numQueueItems" value="0">
				<tr class="formTitle"><td width="50">Priority</td><td>Summary</td></tr>	

				<logic:iterate id="queueItem" name="monitorInfo" property="queueItems" type="zws.hi.queue.MonitoredQueueItem" indexId="indexItem">
				<tr class="<%=(indexItem.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
					<td><bean:write name="queueItem" property="priority" /></td>
					<td>
						<bean:write name="queueItem" property="summary" /><br><br>
						Instruction: <bean:write name="queueItem" property="instruction" /><hr>
						Id: <bean:write name="queueItem" property="id" />
					</td>
				</tr>
				</logic:iterate>
			</logic:notEqual>
			<logic:equal name="numQueueItems" value="0">
				<tr class="rowdata1"><td colspan="2" align="center">The queue is empty.</td></tr>
			</logic:equal>
		</table>
		<br/>

	</logic:iterate>
</html:form>
	<!-- ******* End Queue Monitor ******* -->
	
	<%@ include file="/web/include/footer.jsp"%>
</body>
</html>