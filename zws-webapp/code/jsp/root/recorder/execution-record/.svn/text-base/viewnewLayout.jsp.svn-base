<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>

<%@ page import="com.zws.util.StringUtil"%>



<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>
<html:html>


<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/recorder/namespaces.do">
<html:hidden property="event" value=""/>
<html:hidden property="ID" value=""/>
<html:hidden property="activityType" value=""/>

<% String title="Publishing Log"; %>
<%@ include file="/web/include/header.jsp"%>

<!-- BEGIN: breadcrumb trail -->
<logic:present name="hiExecutionRecordList" property="stackContents">
	<ul id="breadCrumb">

	<li><a href="/publish/retrieveque.do">Publish Logs</a></li>

	<logic:iterate id="e"  name="hiExecutionRecordList" property="stackContents" type="zws.recorder.ExecutionRecord" indexId="index">

		<li><a  onMouseover="showtip(this,event,'Process Name : <%=e.getName()%>')"  onMouseout="hidetip()" href="javascript:sendEventID('jumpToParent', '<%=e.getID()%>')" class="saveButton">
				<%= StringUtil.truncateWPadding( e.getName()== null ? "": e.getName(), 25)%>
				
		</a></li>

	</logic:iterate>
	<li><bean:write name="hiExecutionRecordList" property="selectedExecutionRecord.name"/></li>
	</ul>
</logic:present>
<!-- END: breadcrumb trail -->

<table  border="0" cellpadding="2" cellspacing="2" width="100%">
   <tr>
       <td  align="left" cellpadding="10" cellspacing="10" valign="top">

<% String processNotes = null;%>
	<logic:present name="hiExecutionRecordList" property="selectedExecutionRecord">
	<input type="hidden" name="processID"    value=""/>

	<table border="0" cellpadding="0" cellspacing="0" width="80%" align="left" height="250px">

		  <tr height="10px">
        		<td class="formtitle">
				Log Entry
			</td>
		  </tr>

		<td>
		  <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center" height="100px">

			<tr>
			  <td class="field_label">Category : </td>
			<td class="field_label_val">
		<a href="javascript:sendEventID('chooseParentRecord', '<bean:write name="hiExecutionRecordList" property="selectedExecutionRecord.ID"/>')" class="saveButton">
		<bean:write name="hiExecutionRecordList" property="selectedExecutionRecord.namespace"/></a></td>
			   <td class="field_label">&nbsp;</td><td class="field_label_val">&nbsp;</td>
				<td class="field_label">&nbsp</td><td class="field_label_val">&nbsp</td>
			</tr>
			<tr>
			  <td class="field_label">Process Name:</td> <td class="field_label_val"><bean:write name="hiExecutionRecordList" property="selectedExecutionRecord.name"/></td>
			  <td class="field_label">Description:</td> <td class="field_label_val"><bean:write name="hiExecutionRecordList" property="selectedExecutionRecord.description"/></td>
			  <td class="field_label">&nbsp</td><td class="field_label_val">&nbsp</td>
			</tr>
			<tr>
			  <td class="field_label">Started:</td> <td class="field_label_val"><bean:write name="hiExecutionRecordList" property="selectedExecutionRecord.startTime"/></td>
 			  <td class="field_label">duration:</td> <td class="field_label_val"><bean:write name="hiExecutionRecordList" property="selectedExecutionRecord.duration"/></td>
			  <td class="field_label">Status:</td> <td class="field_label_val"><bean:write name="hiExecutionRecordList" property="selectedExecutionRecord.status"/></td>
			</tr>
		  </table>
	     	</td>
	  </tr>
		<tr>
		  <td valign="top" width="20%">

			<table border="1" cellpadding="10" cellspacing="10" width="100%" align="center" height="10px">
		  		<tr height="10px">
        				<td valign="top">
					<div style="overflow:auto; height:180px; width:100%;border:0">
						<table border="1" cellpadding="0" cellspacing="0" width="100%" height="10px">
						  <tr class="formtitle">Sub Process </tr>
						  <tr class="column_heading">
							<td>Name</td>
							<td>Status</td>
						    <td>Duration</td>
						  <tr>

					 <logic:present name="hiExecutionRecordList" property="children">
						<logic:iterate id="e" name="hiExecutionRecordList" property="children"  type="zws.recorder.ExecutionRecord" indexId="index">
					      <tr class="<%=(index.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
								<td width="20%">
								<a href="javascript:sendEventID('chooseChildRecord', '<%=e.getID()%>')" class="saveButton"><%=e.getName()%> </a></td>
								<td width="20%"><%=e.getStatus()%></td>
								<td width="20%" align="right"><%=e.getDuration()%></td>
						  </tr>

     					</logic:iterate>
					  </logic:present>
				</table>
					</td>
					<td valign="top" width="60%">
					<div style="overflow:auto; height:180px; width:100%;border:0">
						<table border="1" cellpadding="0" cellspacing="0" width="100%" height="10px">
						  <tr class="formtitle">Activity</tr>
						  <tr class="column_heading">
							<td>Type</td>
							<td>Timestamp</td>
							<td>Message</td>
						 </tr>


					 <logic:present name="hiExecutionRecordList" property="selectedExecutionRecord.activity">
						
					    <logic:iterate id="e"  name="hiExecutionRecordList" property="selectedExecutionRecord.activity" type="zws.recorder.ActivityRecordBase" indexId="index">
						<%
							String actID = String.valueOf(e.getID());
							String type = e.getType();
							String message =  e.getMessage();
							String messageNotes =  message;
							String messageTip = StringUtil.replace(e.getMessage(),"'", "\\\\'");
							String notes = e.getNotes();
							String notesID = "notes"+index;
							String msgID = "msg"+index;
							if(null == notes) notes = "";
							else {
								notes = StringUtil.replace(notes,"<", " &lt ");
								notes = StringUtil.replace(notes,">", " &gt ");
								//notes = StringUtil.replace(notes,"\"", "\\\\'");
							}
							if(null == messageNotes) messageNotes = "";
							else {
								messageNotes = StringUtil.replace(messageNotes,"<", " &lt ");
								messageNotes = StringUtil.replace(messageNotes,">", " &gt ");
							}
						%>
							<html:hidden property="<%=notesID%>" value="<%=notes%>"/>
							<html:hidden property="<%=msgID%>" value="<%=messageNotes%>"/>
        					 <tr class="<%=(index.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
								<td width="30%"
								<% if(null != notes && notes.length() >0) {%>
										onMouseover="this.className='highlightLink';showtip(this,event,'Click to see more details')"
										onMouseout="this.className='normalLink';hidetip()"
										onClick = "javascript:showNotes('<%=notesID%>','<%=type%>')">
									   <img src="/web/image/info.jpg"/>
								<%} else {%>
									>
								<%}%>
								<%=type%></nobr>
								</td>
								<td width="25%"><%= e.getTimestamp()%></nobr></td>
								<td width="45%" onMouseover="this.className='highlightLink';showtip(this,event,'<%=messageTip%>')"  
												onMouseout="this.className='normalLink';hidetip()"
												onClick = "javascript:showNotes('<%=msgID%>','<%=type%>')" >
									<%=StringUtil.truncateWPadding(message== null ? "": e.getMessage(), 45)%></nobr>
								</td>
					  	 </tr>
					    </logic:iterate>
					</logic:present>
					</table>
					</div>
					</td>
				</tr>
					<tr><td colspan="2"><div id="notes" /></td></tr>
			</table>
	        </td>
		</tr>
	</table>
	</logic:present>

<!-- not present -->

<logic:notPresent name="hiExecutionRecordList" property="selectedExecutionRecord">

	<table border="1" cellpadding="0" cellspacing="0" width="80%" align="left" height="250px">

		  <tr height="10px">
        		<td class="formtitle">
				Log Entry : Log Stack is Empty.Process logs are not available.
			</td>
		  </tr>

		<td>
		  <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center" height="100px">

			<tr>
			  <td class="field_label" >Category : </td>
			<td class="field_label_val">&nbsp;
			</td>
			   <td class="field_label_val">&nbsp;</td>
			   <td class="field_label_val">&nbsp;</td>
			</tr>
			<tr>
			  <td class="field_label" width="20%">Process Name:</td> <td class="field_label_val"></td>
			  <td class="field_label" width="20%">Status:</td> <td class="field_label_val"></td>
			</tr>
			<tr>
			  <td class="field_label" width="20%">Started:</td> <td class="field_label_val"></td>
 			  <td class="field_label" width="20%">duration:</td> <td class="field_label_val"></td>
			</tr>
		  </table>
	     	</td>
	  </tr>
		<tr>
		  <td valign="top" width="20%">

			<table border="1" cellpadding="10" cellspacing="10" width="100%" align="center" height="10px">
		  		<tr height="10px">
        				<td valign="top">
					<div style="overflow:auto; height:180px; width:100%;border:0">
						<table border="1" cellpadding="0" cellspacing="0" width="100%" height="10px">
						  <tr class="formtitle">Sub Process </tr>
						  <tr class="column_heading">
							<td>Name</td>
							<td>Status</td>
							<td>Duration</td>
						  <tr>



					      <tr class="rowdata2">
								<td width="20%">
								</td>
								<td width="20%"></td>
								<td width="20%"></td>
						  </tr>



				</table>


					</td>
					<td valign="top" width="60%">
					<div style="overflow:auto; height:180px; width:100%;border:0">
						<table border="1" cellpadding="0" cellspacing="0" width="100%" height="10px">
						  <tr class="formtitle">Activity</tr>
						  <tr class="column_heading">
							<td>Type</td>
							<td>Timestamp</td>
							<td>Message</td>
						 </tr>
						 <tr class="rowdata2">
								<td width="20%"></td>
								<td width="20%"></td>
								<td width="20%"></td>
					  	 </tr>


					</table>
					</div>
					</td>
				</tr>
			</table>
	        </td>
		</tr>

	   </table>
	</logic:notPresent>
<!-- not present ends here -->
      </td>

    </tr>
 </table>

<%@ include file="/web/include/footer.jsp"%>


</html:form>
</body>
</html:html>

