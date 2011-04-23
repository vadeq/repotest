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
<html:form action="/recorder/namespaces.do">
<html:hidden property="ID"/>
<html:hidden property="event" value="choose"/>


<% String title="Namespace"; %>
<%@ include file="/web/include/header.jsp"%>

<jsp:useBean id="hiExecutionRecordList" scope="session" type="zws.hi.recorder.hiExecutionRecordList"/>

<table class="form" border="1" cellpadding="0" cellspacing="0"  width="50%">
  <tr>
    <td class="formTitle">Namespaces</td>
  </tr>

  	<tr>	
    	<td valign="top">
      		
		<table class="formProps" border="0" cellpadding="0" cellspacing="0" width="100%">
        		<tr>
          			<td valign="top">
					    <table class="formProps" style="scrollbars="YES" border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr class="column_heading">
					    	<td>&nbsp;</td>
					    	</tr> 
     						
					    	<logic:iterate id="e"  name="hiExecutionRecordList" property="namespaces" indexId="index">
        					<tr class="<%=(index.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
							<td class="<%=(index.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>" width="20%">
							<!--
							<nobr><a href="javascript:choose('<%=e.toString()%>');"><%=e.toString()%> </a></nobr></td>
							-->
							<nobr><a href="javascript:sendEventID('chooseNamespace','<%=e.toString()%>');"><%=e.toString()%> </a></nobr></td>
							</td>
        					</tr>
     						</logic:iterate>
						</table>
						
					</td>
				</tr>		
			</table>
			
			
			
			
			
			
			
			
		 


		
</table>

<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>





