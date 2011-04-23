<%@ page language="java" import="java.text.SimpleDateFormat;"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>

<%  // scriptlets are not the way to go, but we're on old struts and time is of the essence
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss"); 
%>

<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>
<script>
	function perform(action, params) {
		document.getElementById('event').value=action;
		
		
		if (action == "delete") {
			var agree = confirm('Are you sure you wish to delete ALL of these items?');
			if (agree) {
				document.getElementById('search').value=params;
				send();
			}
		} else {
			send();
		}
	}
</script>

<html>
<body topmargin="0" leftmargin="0" bgcolor="white">
	<% String title="Synchronization Administration"; %>
	<%@ include file="/web/include/header.jsp"%>


	<!-- ******* Begin SyncAdmin ******* -->
	
<html:form action="/synchronization/sync.do">

<div class="queList">
	<!--  search form here -->
	<input type="hidden" id="event" name="event" value="search"/>	
	<table border=0 width=100%>
     <tr>
       <td class="formTitle" align="left" colspan=2>
         <div align="left"><nobr>&nbsp;Search &nbsp;&nbsp;&nbsp;</nobr></div>
       </td>
     </tr><tr>
     	<td><br/></td>
     </tr><tr>
			<td width=40><input type="text" id="search" name="search" value="<bean:write name="hiSyncAdmin" property="search" />"/></td>
			<td><a href="javascript:perform('search','');" styleClass="saveButton"><img gif="search"/></a></td>
		</tr>
	</table>
		
	<br/>
	
	<!--  search results -->
	<bean:size id="numItems" name="hiSyncAdmin" property="listing" />
	<logic:notEqual name="numItems" value="0">
	
		<!--  delete link -->
		<a href="javascript:perform('delete','<bean:write name="hiSyncAdmin" property="criteria" />');" styleClass="saveButton"><img gif="iremove"/>
		<logic:notEqual name="numItems" value="1">
			Remove all <bean:write name="numItems" /> items from the synchronization log.
		</logic:notEqual>
		<logic:equal name="numItems" value="1">
			Remove <bean:write name="numItems" /> item from the synchronization log.
		</logic:equal>
		</a><br/><br/>
		
		<table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
			<!-- table header -->
			<tr>
				<td class="field_label">Datasource (A)</td> 
				<td class="field_label">Unique ID (A)</td>
				<td class="field_label">Datasource (B)</td> 
				<td class="field_label">Unique ID (B)</td>
				<td class="field_label">Timestamp</td>
			</tr>
			<!-- available fields				
			<tr>
				<td>DOMAINNAME0</td> 
				<td>SERVERNAME0</td>  
				<td>DATASOURCETYPE0</td> 
				<td>DATASOURCENAME0</td>
				<td>STAMP0</td>
				<td>UID0</td>
				<td>NAME0</td>
				<td>DOMAINNAMEA</td>
				<td>SERVERNAMEA</td>
				<td>DATASOURCETYPEA</td>
				<td>DATASOURCENAMEA</td>
				<td>STAMPA</td>
				<td>UIDA</td>
				<td>NAMEA</td>
			</tr>
			-->				
				
			<logic:iterate id="origin" name="hiSyncAdmin" property="listing" type="zws.synchronization.SynchronizationRecord" indexId="indexItem">
				<tr class="<%=(indexItem.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
					<td><bean:write name="origin" property="datasourceType0" /></td>
					<td><bean:write name="origin" property="UID0" /></td>
					<td><bean:write name="origin" property="datasourceTypeA" /></td> 
					<td><bean:write name="origin" property="UIDA" /></td>
					<td>
<%
					out.print( sdf.format(origin.getTimeOfCreation0InMillis()) );
%>					
					</td>
				</tr>
				
				<!-- available fields				
					<tr class="<%=(indexItem.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
						<td><bean:write name="origin" property="domainName0" /></td> 
						<td><bean:write name="origin" property="serverName0" /></td>  
						<td><bean:write name="origin" property="datasourceType0" /></td> 
						<td><bean:write name="origin" property="datasourceName0" /></td>
						<td><bean:write name="origin" property="timeOfCreation0InMillis" /></td>
						<td><bean:write name="origin" property="UID0" /></td>
						<td><bean:write name="origin" property="name0" /></td>
						<td><bean:write name="origin" property="domainNameA" /></td>
						<td><bean:write name="origin" property="serverNameA" /></td>
						<td><bean:write name="origin" property="datasourceTypeA" /></td>
						<td><bean:write name="origin" property="datasourceNameA" /></td>
						<td><bean:write name="origin" property="timeOfCreationAInMillis" /></td>
						<td><bean:write name="origin" property="UIDA" /></td>
						<td><bean:write name="origin" property="nameA" /></td>
					</tr>  
				-->					

			</logic:iterate>
		</table>
	</logic:notEqual>
	<logic:equal name="numItems" value="0">
		No Items found in search.	
	</logic:equal>	
</div>
</html:form>
	<!-- ******* End SyncAdmin ******* -->
	
	<%@ include file="/web/include/footer.jsp"%>
</body>
</html>