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





<% String title="List"; %>
<%@ include file="/web/include/header.jsp"%>

<table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td class="formTitle">Process Logs</td>
  </tr>
  <tr>
    <td>
      <table class="formProps" border="0" cellpadding="0" cellspacing="1" width="100%">
        <tr>
          <td>
			<div style="overflow:auto; height:180px; width:100%;">


<!--13 March Changes  -->
						<div style="overflow:auto; height:180px; width:100%;">
					      <table style="scrollbars="YES" border="0" cellpadding="2" cellspacing="0" width="100%">
					            <tr class="column_heading">
					            	<td width="20%">ID</td>
									<td width="20%">Name</td>
						  			<td width="20%">Status</td>
						  			<td width="20%">Start Time</td>
						  			<td width="20%">Duration</td>
								</tr>
					            
						<logic:iterate id="e" name="hiExecutionRecordList" property="recordings"  type="zws.recorder.ExecutionRecord" indexId="index">
					      <tr class="<%=(index.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
								<td width="20%" onMouseover="showtip(this,event,'Namespace : <%=e.getNamespace()%>')"  onMouseout="hidetip()"><nobr><a href="javascript:sendEventID('chooseRecord','<%=e.getID()%>');"><%=e.getID()%> </a></nobr></td>
								<td width="20%"><%=e.getName()%></td>
								<td width="20%"><%=e.getStatus()%></td>
								<td width="20%"><%=e.getStartTime()%></td>
								<td width="20%"><%=e.getDuration()%></td>
						  </tr>
     					</logic:iterate>
								
								
								
							</table>
						</div>

<!-- end of changes 13 march-->


		</div>
	
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr class="formMenu">
    
  </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>














