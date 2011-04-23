<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page import="java.util.ArrayList"%>
<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>
<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">

<% String title="Names"; %>
<%@ include file="/web/include/header.jsp"%>



<html:form action="/recorder/namespaces.do">
<html:hidden property="ID" value=""/>
<html:hidden property="event" value="chooseName"/>




<table  border="1" cellpadding="0" cellspacing="0" width="50%">
  <tr>
    <td class="formTitle">Namespace:<bean:write name="hiExecutionRecordList" property="selectedNamespace"/></td>
  </tr>
  	<tr>
    	<td>
      		<table border="0" cellpadding="0" cellspacing="1" width="100%">
        		<tr>
          			<td>

						<div style="overflow:auto; height:180px; width:100%;">
  						<table class="formProps" style="scrollbars="YES" border="0" cellpadding="2" cellspacing="0" width="100%">

					        <table class="formProps" style="scrollbars="YES" border="0" cellpadding="2" cellspacing="0" width="100%">
					    	<tr class="column_heading">
					    	<td>Names</td>
					    	</tr> 
     						<logic:iterate id="e"  name="hiExecutionRecordList" property="names" indexId="index">
        					<tr class="<%=(index.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
							<td class="<%=(index.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>" width="20%" onMouseover="showtip(this,event,'<%=e.toString()%>')"  onMouseout="hidetip()">
							<!--
							<nobr><a href="javascript:choose('<%=e.toString()%>');"><%=e.toString()%> </a></nobr></td>
							-->
							<nobr><a href="javascript:sendEventID('chooseName','<%=e.toString()%>');"><%=e.toString()%> </a></nobr></td>
							</td>
        					</tr>
     						</logic:iterate>
     						
     						
							</table>
						</div>
					</td>
				</tr>		
			</table>
		</td>
	</tr>		
</table>


<!--
<tr><td>
			<a href="javascript:sendEvent('RecordTestdata');">Record test data</a>
			</td></tr>
-->			

<%@ include file="/web/include/footer.jsp"%>


</body>
</html:form>
</html:html>














