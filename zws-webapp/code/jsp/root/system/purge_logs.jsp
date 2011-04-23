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

	<% String title="System Maintenance - Purge Publish Logs"; %>
	<%@ include file="/web/include/header.jsp"%>

	
	<!-- ******* Begin Trim Publish Logs ******* -->	
	
	<table border=0 width=90%>
     <tr>
       <td class="formTitle" align="left" colspan=2>
         <div align="left"><nobr>&nbsp;Publish Logs&nbsp;&nbsp;&nbsp;</nobr></div>
       </td>
     </tr><tr>
     	<td><br/>
			<img src="/web/image/alert.gif"/>&nbsp;&nbsp;It is strongly recommended that you backup the tables before trimming the publish logs.<br/>
			<br />
			
			<!--  Action Form -->
			<form name="trim" action="/system/purge_logs.do">
				<input type="hidden" id="event" name="event" value="trimLogs"/>	
				
				First date to retain information for: 
				<input type="text" name="purgeDate" value=""
						dojoType="dijit.form.DateTextBox"
						constraints="{datePattern: 'yyyy-MM-dd'}" required="true" promptMessage="yyyy-MM-dd" invalidMessage="Invalid date, please use yyyy-MM-dd format." />
                
                <br /><br />
				<img src="/web/image/treeview/trash.gif"/>
				<a href="javascript:document.trim.submit();" styleClass="saveButton">&nbsp;Permanently remove publish log content.</a>
			</form>			
		</td>     
	</tr>		
	<tr><td>
		<bean:size id="numLogMsgs" name="TrimPublishLogs" property="messages" />
		<logic:notEqual name="numLogMsgs" value="0">
			<br/>
			Results:<br/><ul>
				<!--  Action Results -->
				<logic:iterate id="message" name="TrimPublishLogs" property="messages">
					<li><bean:write name="message" /></li>
				</logic:iterate>
				</ul>
		</logic:notEqual>		
	</td></tr>		
	</table>	

	<!-- ******* End Trim Publish Logs ******* -->
	
	<%@ include file="/web/include/footer.jsp"%>
</body>
</html>