<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>
<html:html>

<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/recorder/namespaces.do">
<html:hidden property="event" value=""/>
<html:hidden property="ID" value=""/>

<% String title="Execution Record"; %>
<%@ include file="/web/include/header.jsp"%>


<table  border="0" cellpadding="0" cellspacing="0" width="100%">
   <tr>
    <td colspan="2" width="40%">
     	<table border="1" cellpadding="0" cellspacing="0" width="100%" height="220px">    
      	  <tr>
        		<td>
				
        			<div style="overflow:auto; height:200px; width:100%;border:0">
						  
					        <table class="formProps" style="scrollbars="YES" border="0" cellpadding="2" cellspacing="0" width="100%">
								<tr class="formTitle" >
									<td>Process Log<td>
  								</tr>

					      		<tr class="column_heading" >
								<td colspan="3">&nbsp;</td>
  								</tr>
									<tr><td width="40%" class="prop">Namespace</td><td class="value">
									<a href="javascript:sendEventID('chooseParentRecord', '<bean:write name="hiExecutionRecordList" property="selectedExecutionRecord.ID"/>')" class="saveButton">
									<bean:write name="hiExecutionRecordList" property="selectedExecutionRecord.namespace"/></td></tr
									<tr><td width="40%" class="prop">Name</td><td class="value"><bean:write name="hiExecutionRecordList" property="selectedExecutionRecord.name"/></td></tr>
						  			<tr><td width="40%" class="prop">Status</td><td class="value"><bean:write name="hiExecutionRecordList" property="selectedExecutionRecord.status"/></td></tr>
						  			<tr><td width="40%" class="prop">Start Time</td><td class="value"><bean:write name="hiExecutionRecordList" property="selectedExecutionRecord.startTime"/></td></tr>
						  			<tr><td width="40%" class="prop">Duration</td><td class="value"><bean:write name="hiExecutionRecordList" property="selectedExecutionRecord.duration"/></td></tr> 
									<tr><td width="40%" class="prop">ID</td><td class="value"><bean:write name="hiExecutionRecordList" property="selectedExecutionRecord.ID"/></td></tr>
							</table>
				</div>
        		</td>
      	   </tr>
      		<tr>
       			<td>
       				<div style="overflow:auto; height:180px; width:100%;">
				<table style="scrollbars="YES" border="0" cellpadding="2" cellspacing="0" width="100%">
					<tr >
						<td class="formTitle"> Sub Processes </td>
  					</tr>
			</table>
			<table style="scrollbars="YES" border="0" cellpadding="2" cellspacing="0" width="100%">				  	
					<tr class="column_heading">
						  <td width="20%">Name</td>
						  <td width="20%">Status</td>
					</tr>
					<logic:present name="hiExecutionRecordList" property="children">
						<logic:iterate id="e" name="hiExecutionRecordList" property="children"  type="zws.recorder.ExecutionRecord" indexId="index">
					      <tr class="<%=(index.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
								<td width="20%">
								<a href="javascript:sendEventID('chooseChildRecord', '<%=e.getID()%>')" class="saveButton"><%=e.getName()%> </a></td>
								<td width="20%"><%=e.getStatus()%></td>
						  </tr>
     					</logic:iterate>
					  </logic:present>
				</table>

     				 </div>
       			</td>
      		</tr>
       </table>
    </td>
     <td valign="top" width="100%">
     	<table border="1" cellpadding="0" cellspacing="0" width="100%"  height="200px" >
      		<tr>
        		<td valign="top">
        			<div style="overflow:auto; height:180px; width:100%;valign:top;">
  						<table class="formProps" style="scrollbars="YES" border="0" cellpadding="2" cellspacing="0" width="100%">
						<tr>
							<td class="formTitle" valign="top">Activity</td>
  						</tr>
						</table>
					<table style="scrollbars="YES" border="0" cellpadding="2" cellspacing="0" width="100%">				  	
					<tr class="column_heading">
					      <td width="20%">Type</td>
						  <td width="20%">Message</td>
					</tr>
					
						<logic:present name="hiExecutionRecordList" property="selectedExecutionRecord.activity">
					    <logic:iterate id="e"  name="hiExecutionRecordList" property="selectedExecutionRecord.activity" type="zws.recorder.ActivityRecordBase" indexId="index">
        				  <tr class="<%=(index.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
							
								<td width="20%"><%=e.getType()%></nobr></td>
								<td width="20%"><%=e.getMessage()%></td>
								
        				  </tr>
     					</logic:iterate>
					</logic:present>
     				</table>
					</div>
        		</td>
      	   </tr>
      	</table>
      </td>
   </tr>
   </table>




















  	<%--
  	<tr>
	   	<td>
	   		
						<div style="overflow:auto; height:180px; width:100%;">
					        <table style="scrollbars="YES" border="0" cellpadding="2" cellspacing="0" width="100%">
					            <tr class="column_heading">
					            	<td width="20%">ID</td>
									<td width="20%">Name</td>
						  			<td width="20%">Status</td>
						  			<td width="20%">Start Time</td>
						  			<td width="20%">Duration</td>
								</tr>
					            <tr class="rowdata2">
					            <td width="20%"><bean:write name="hiExecutionRecord" property="id"/></td>
								<td width="20%"><bean:write name="hiExecutionRecord" property="name"/></td>
								<td width="20%"><bean:write name="hiExecutionRecord" property="status"/></td>
								<td width="20%"><bean:write name="hiExecutionRecord" property="startTime"/></td>
								<td width="20%"><bean:write name="hiExecutionRecord" property="duration"/></td>
								</tr>
							</table>
						</div>
					<div style="overflow:auto; height:180px; width:100%;">
  						<table class="formProps" style="scrollbars="YES" border="0" cellpadding="2" cellspacing="0" width="100%">
						<tr>
							<td class="formTitle">Activity</td>
  						</tr>
						</table>
					<table style="scrollbars="YES" border="0" cellpadding="2" cellspacing="0" width="100%">				  	
					<tr class="column_heading">
					      <td width="20%">Type</td>
						  <td width="20%">Message</td>
					</tr>
					
						<logic:present name="hiExecutionRecord" property="activities">
					    <logic:iterate id="e"  name="hiExecutionRecord" property="activities" type="zws.recorder.ActivityRecordBase" indexId="index">
        				  <tr class="<%=(index.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
							
								<td width="20%"><%=e.getType()%></nobr></td>
								<td width="20%"><%=e.getMessage()%></td>
								
        				  </tr>
     					</logic:iterate>
					</logic:present>
     				</table>
					</div>

				<div style="overflow:auto; height:180px; width:100%;">
	<table style="scrollbars="YES" border="0" cellpadding="2" cellspacing="0" width="100%">
					<tr >
						<td class="formTitle"> Sub Processes </td>
  					</tr>
	</table>
	<table style="scrollbars="YES" border="0" cellpadding="2" cellspacing="0" width="100%">				  	
					<tr class="column_heading">
						<%--
					      <td width="20%">Namespace</td>
					      
					      <td width="20%">ID</td>
						  <td width="20%">Name</td>
						  <td width="20%">Status</td>
						  <td width="20%">Start Time</td>
						  <td width="20%">Duration</td>
					</tr>
					<logic:present name="hiExecutionRecord" property="children">
						<logic:iterate id="e" name="hiExecutionRecord" property="children"  type="zws.recorder.ExecutionRecord" indexId="index">
					      <tr class="<%=(index.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
					      		<%--
								<td width="20%"><a href="view.do?nav=viewExecutionRecord&ID=<%=e.getNamespace()%>" ><%=e.getNamespace()%></a></nobr></td>
							
								
								<td width="20%"><a href="view.do?nav=viewExecutionRecord&ID=<%=e.getNamespace()%>" ><%=e.getID()%></a></td>
								<td width="20%"><%=e.getName()%></td>
								<td width="20%"><%=e.getStatus()%></td>
								<td width="20%"><%=e.getStartTime()%></td>
								<td width="20%"><%=e.getDuration()%></td>
						  </tr>
     					</logic:iterate>
					  </logic:present>
	</table>

     				 </div>
				   </td>
				</tr>		
				
</table>

<table border="0" cellpadding="1" cellspacing="0" width="100%">
    <tr>
		<td class="saveButton"><html:link href="javascript:send('getRecordedNameSpaces');" styleClass="saveButton"><img gif="save"/></html:link></td>
    </tr>
</table>
    
--%>

<%@ include file="/web/include/footer.jsp"%>


</html:form>
</body>
</html:html>

