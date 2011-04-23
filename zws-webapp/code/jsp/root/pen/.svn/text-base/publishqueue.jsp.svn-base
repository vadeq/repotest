<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="zws.application.Names"%>
<%@page import="com.zws.util.StringUtil"%>
<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript"
	src="/web/javascript/DesignState.js"></script>

<script type="text/javascript" language="JavaScript"
	src="/web/javascript/ajax.js"></script>

<script type="text/javascript">
// setInterval("reload()",20000);
// function reload()
// {
	//alert("called");
//	sendEventX('','/publish/retrieveque.do');
//}
</script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/publish/retrieveque.do">
	<html:hidden property="event" value="" />
	<html:hidden property="ID" value="" />
	<%@ include file="/web/include/application-name.jsp"%>
	<%@ include file="/web/include/header.jsp"%>
	<c:set var="names" value="${zws.application.Names}" scope="application" />
	<bean:size id="id1" name="hiPublishQueList" property="pendingRecords" />
	<bean:size id="currentRecords" name="hiPublishQueList" property="currentPublishingRecords" />

	<logic:notEqual name="currentRecords" value="0">
	<fieldset class="publish"><legend>Current Publish Status - <bean:write name="hiPublishQueList" property="currentCount" /></legend>
	<table class="form" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td><span id="publishstatus">
			<div class="queList">
				<table class="formProps" border="1" cellpadding="3" cellspacing="1" width="100%">
					<tr>
						<td colspan="3">&nbsp;Now Publishing&nbsp;</td>
					</tr>
					<tr>
						<td width="50%" class="formTitle">&nbsp;Description&nbsp;</td>
						<td width="25%" class="formTitle">&nbsp;Request Time&nbsp;</td>
						<td width="25%" class="formTitle">&nbsp;Duration&nbsp;</td>
					</tr>
					<logic:iterate id="pubListItem" name="hiPublishQueList" property="currentPublishingRecords" type="zws.recorder.ExecutionRecord" indexId="index">
						<tr class="<%=(index.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
							<td width="50%" onMouseover="showtip(this,event,'<%=pubListItem.getStatus()%>::<%=pubListItem.getDescription()%>')" onMouseout="hidetip()">
								<img src="/web/image/publish.gif" />&nbsp;
								<a href="/recorder/namespaces.do?event=chooseRecord&ID=<bean:write name="pubListItem" property="ID" />">
								<%=StringUtil.truncateWPadding(pubListItem.getDescription() == null ? "" : pubListItem.getDescription(), 50)%></nobr>
							</td>
							<td width="25%"><div id="dt<bean:write name="pubListItem" property="ID" />"><%=pubListItem.getStartTime()%></div></td>
							<td width="25%"><%=pubListItem.getDuration()%></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
			</span></td>
		</tr>
	</table>
	</fieldset>
	</logic:notEqual>

	<logic:notEqual name="id1" value="0">
		<fieldset class="publish"><legend> Queue(<bean:write name="id1" />)</legend>
		<table class="form" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				<table class="formProps" border="0" cellpadding="0" cellspacing="1" width="100%">
					<tr>
						<td><span id="queue">
						<div class="queListScroll">
						<table class="formProps" border="1" cellpadding="3" cellspacing="0" width="100%">
							<tr>
								<td colspan="3">&nbsp;Waiting to be Published&nbsp;</td>
							</tr>
							<tr>
								<td width="50%" class="formTitle">&nbsp;Description&nbsp;</td>
								<td width="25%" class="formTitle">&nbsp;Request Time&nbsp;</td>
								<td width="25%" class="formTitle">&nbsp;Duration&nbsp;</td>
							</tr>
							<logic:iterate property="pendingRecords" name="hiPublishQueList" id="queueListItem" type="zws.recorder.ExecutionRecord" indexId="index">
								<tr class="<%=(index.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
									<%
									        if (queueListItem.getStatus() != null && queueListItem.getStatus().equals("pending")) {
									        //if(queueListItem.getStatus()!=null && !queueListItem.getStatus().equals("complete")){
									%>
									<td width="50%"
										onMouseover="showtip(this,event,'<%=queueListItem.getDescription()%>')"
										onMouseout="hidetip()"><nobr> <a
										href="/recorder/namespaces.do?event=chooseRecord&ID=<%=queueListItem.getID()%>">
									<%=StringUtil.truncateWPadding(queueListItem.getDescription() == null ? "" : queueListItem.getDescription(), 50)%></a></nobr>
									</td>
									<td width="25%"><%=queueListItem.getStartTime()%></td>
									<td width="25%" align="right"
										onMouseover="showtip(this,event,'<%=queueListItem.getDuration().toString()%>')"
										onMouseout="hidetip()"><%=queueListItem.getDuration().toString()%></td>

									<%
									}
									%>

								</tr>
							</logic:iterate>
						</table>
						</div>
						</span></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</fieldset>
	</logic:notEqual>

	<fieldset class="publish"><legend>Publish Status -<bean:write name="hiPublishQueList" property="statusCount" /></legend>
	<table class="form" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td><span id="publishstatus">
			<div class="queListScroll" >
			<table class="formProps" border="1" cellpadding="3" cellspacing="1" width="100%">
				<tr>
					<td colspan="3">&nbsp;Publish History&nbsp;</td>
				</tr>
				<tr>
					<td width="50%" class="formTitle">&nbsp;Description&nbsp;</td>
					<td width="25%" class="formTitle">&nbsp;Request Time&nbsp;</td>
					<td width="25%" class="formTitle">&nbsp;Duration&nbsp;</td>
				</tr>
				<logic:iterate id="pubListItem" name="hiPublishQueList" property="allPublishingRecords" type="zws.recorder.ExecutionRecord" indexId="index">
					<tr class="<%=(index.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
						<td width="50%"
							onMouseover="showtip(this,event,'<%=pubListItem.getStatus()%>::<%=pubListItem.getDescription()%>')"
							onMouseout="hidetip()"><bean:define name="hiPublishQueList"
							property="names" id="statusName" /> <c:choose>
							<c:when test="${statusName.statusCancel == pubListItem.status}">
								<img src="/web/image/cancel.gif" />
							</c:when>
							<c:when test="${statusName.statusError == pubListItem.status}">
								<img src="/web/image/error2.gif" />
							</c:when>
							<c:when test="${statusName.statusComplete == pubListItem.status}">
								<img src="/web/image/complete.gif" />
							</c:when>
							<c:otherwise>
								<img src="/web/image/publish.gif" />
							</c:otherwise>
						</c:choose> &nbsp; <a
							href="/recorder/namespaces.do?event=chooseRecord&ID=<bean:write name="pubListItem" property="ID" />">
						<%=StringUtil.truncateWPadding(pubListItem.getDescription() == null ? "" : pubListItem.getDescription(), 50)%></nobr></td>

						<td width="25%">
						<div id="dt<bean:write name="pubListItem" property="ID" />">
						<%=pubListItem.getStartTime()%></div>
						</td>
						<td width="25%"><%=pubListItem.getDuration()%></td>
					</tr>
				</logic:iterate>


			</table>
			</div>
			</span></td>
		</tr>
	</table>
	</fieldset>
	<br>
	<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>