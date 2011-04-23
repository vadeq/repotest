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

	<% String title="System Maintenance - Remove Temp/Work Files"; %>
	<%@ include file="/web/include/header.jsp"%>


	<!-- ******* File Cleanup ******* -->

	<table border=0 width=90%>
		<tr>
			<td class="formTitle" align="left" colspan=2>
			 <div align="left"><nobr>&nbsp;Remove Temp/Work Files&nbsp;&nbsp;&nbsp;</nobr></div>
			</td>
		</tr><tr>
			<td><br/>
     		The following directories will have contents older than today removed.<br/>
				<ul>
					<logic:iterate id="location" name="FileCleanup" property="fileLocations">
						<li><bean:write name="location" /></li>
					</logic:iterate> 
				</ul><br/>
				
				<!--  Action Form -->
				<form name="archive" action="/system/file_cleanup.do">
				<input type="hidden" id="event" name="event" value="cleanupFiles"/>	
					<img src="/web/image/download.jpg"/>
					<a href="javascript:document.archive.submit();" styleClass="saveButton">&nbsp;Remove old files.</a>
				</form>
			</td>     
		</tr>		
	<tr><td>
		<bean:size id="numItems" name="FileCleanup" property="messages" />
		<logic:notEqual name="numItems" value="0">
			<br/>
			Results:<br/><ul>
				<!--  Action Results -->
				<logic:iterate id="message" name="FileCleanup" property="messages">
					<li><bean:write name="message" /></li>
				</logic:iterate>
				</ul>
		</logic:notEqual>								
	</td></tr>		
	</table>		
	<br/>     	
	<!-- ******* End File Cleanup ******* -->
	
	
	<%@ include file="/web/include/footer.jsp"%>
</body>
</html>