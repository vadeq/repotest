<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<html>
<head>

	<link rel="stylesheet" href="/web/css/style.css" type="text/css"/>  

	<style type="text/css">
		@import "/web/javascript/dojo-toolkit/dijit/themes/tundra/tundra.css";
		@import "/web/javascript/dojo-toolkit/dojo/resources/dojo.css"
	</style>

	<script type="text/javascript" src="/web/javascript/dojo-toolkit/dojo/dojo.js" 
		djConfig="parseOnLoad: true"></script>


	<script type="text/javascript">
		dojo.require("dojo.parser");
		dojo.require("dijit.form.DateTextBox");
	</script>

	<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>


</head>


<body topmargin="0" leftmargin="0" bgcolor="white"  class="tundra">
	<%@ include file="/web/include/panel-bar.jsp"%>

	<% String title="System Maintenance - Data Archival"; %>
	<%@ include file="/web/include/header.jsp"%>


	<!-- ******* Table Export ******* -->

	<table border=0 width=90%>
		<tr>
			<td class="formTitle" align="left" colspan=2>
			 <div align="left"><nobr>&nbsp;Data Extraction&nbsp;&nbsp;&nbsp;</nobr></div>
			</td>
		</tr><tr>
			<td><br/>
     		The following tables will have data extracted to the <bean:write name="DataArchive" property="backupLocation" /> directory with the date appended to the file name<br/>
				<ul>
					<logic:iterate id="table" name="DataArchive" property="affectedTables">
						<li><bean:write name="table" /></li>
					</logic:iterate> 
				</ul><br/>
				
				<!--  Action Form -->
				<form name="archive" action="/system/table_export.do">
				<input type="hidden" id="event" name="event" value="archiveData"/>	
					<img src="/web/image/download.jpg"/>
					<a href="javascript:document.archive.submit();" styleClass="saveButton">&nbsp;Extract the database content to the backup directory.</a>
				</form>
			</td>     
		</tr>		
	<tr><td>
		<bean:size id="numItems" name="DataArchive" property="messages" />
		<logic:notEqual name="numItems" value="0">
			<br/>
			Results:<br/><ul>
				<!--  Action Results -->
				<logic:iterate id="message" name="DataArchive" property="messages">
					<li><bean:write name="message" /></li>
				</logic:iterate>
				</ul>
		</logic:notEqual>								
	</td></tr>		
	</table>		
	<br/>     	
	<!-- ******* End Table Export ******* -->
	
	
	<%@ include file="/web/include/footer.jsp"%>
</body>
</html>