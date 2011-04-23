<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@page import="zws.application.Names"%>
<%@page import="com.zws.util.StringUtil"%>
<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>
<script type="text/javascript" language="JavaScript" src="/web/javascript/ajax.js"></script>
<script type="text/javascript">
setInterval("reload()",20000);
function reload()
{
	//alert("called");
	sendEventX('','/publish/retrieveque.do');
}
</script>
<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/publish/retrieveque.do">
<html:hidden property="event" value=""/>
<html:hidden property="ID" value=""/>
<%@ include file="/web/include/application-name.jsp"%>
<%@ include file="/web/include/header.jsp"%>
<c:set var="names" value="${zws.application.Names}" scope="application" />
<bean:size id="id1" name="hiPublishQueList" property="pendingRecords"/>
<fieldset class="publish" ><legend> Queue(<bean:write name="id1" />)</legend>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <table class="formProps" border="0" cellpadding="0" cellspacing="1" width="100%">
        <tr>
          <td>
          <span id="queue">
         <div class="queList">
          <table class="formProps" border="1" cellpadding="3" cellspacing="0" width="100%">
           <tr>
              <td class="formTitle">&nbsp;Description&nbsp;</td>
              <td class="formTitle">&nbsp;Request Time&nbsp;</td>
              <td class="formTitle">&nbsp;Duration&nbsp;</td>
            </tr>
  		  		 <logic:iterate property="pendingRecords" name="hiPublishQueList" id="queueListItem" type="zws.recorder.ExecutionRecord" indexId="index">
  		  		 <!-- <tr>
							<td><a href="/recorder/namespaces.do?event=chooseRecord&ID=<bean:write name="queueListItem" property="ID" />"><bean:write name="queueListItem" property="description" /></td>
              <td><bean:write name="queueListItem" property="startTime" /></td>
              <td><div id="dt<bean:write name="queueListItem" property="ID" />"><bean:write name="queueListItem" property="duration" filter="false"/></div></td>
            </tr> -->
		  <tr class="<%=(index.intValue() % 2 == 0) ? "rowdata1" : "rowdata2" %>">
				<%
				   if(queueListItem.getStatus()!=null && queueListItem.getStatus().equals("pending")){
					
				%>
				<td width="8%" onMouseover="showtip(this,event,'Namespace : <%=queueListItem.getNamespace()%>')"  onMouseout="hidetip()"><nobr><a href="/recorder/namespaces.do?event=chooseRecord&ID=<%=queueListItem.getID()%>"><%=queueListItem.getDescription()%> </a></nobr></td>
				<td><bean:write name="queueListItem" property="startTime" /></td>
				<td width="20%" align="right" onMouseover="showtip(this,event,'Process Duration : <%=queueListItem.getDuration().toString()%>')"  onMouseout="hidetip()">
				<%= queueListItem.getDuration().toString()%></td>
				
				<%
					}
				%>
				
		  </tr>
            </logic:iterate>
           </table>
           </div>
           </span>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</fieldset>
<fieldset class="publish" > <legend>Publish Status -<bean:write name="hiPublishQueList" property="statusCount" /></legend>
<table class="form" border="0" cellpadding="0" cellspacing="0">
   <tr>
    <td>
    <span id="publishstatus">
    <div class="queList">
      <table class="formProps" border="1" cellpadding="3" cellspacing="1" width="100%">
 				<tr>
              <td class="formTitle">&nbsp;Description&nbsp;</td>
              <td class="formTitle">&nbsp;Request Time&nbsp;</td>
              <td class="formTitle">&nbsp;Duration&nbsp;</td>
              <td class="formTitle">&nbsp;Status&nbsp;</td>
              <td class="formTitle">&nbsp;</td>
            </tr>

						<logic:iterate property="allPublishingRecords" name="hiPublishQueList" id="pubListItem">
							<tr>
							<td><a href="/recorder/namespaces.do?event=chooseRecord&ID=<bean:write name="pubListItem" property="ID" />">
							<bean:write name="pubListItem" property="description" /></td>
              <td><bean:write name="pubListItem" property="startTime" /></td>
              <td><div id="dt<bean:write name="pubListItem" property="ID" />">
              <bean:write name="pubListItem" property="duration" filter="false"/></div></td>
               <td><bean:write name="pubListItem" property="status" /></td>
               <td >&nbsp;
              	<bean:define name="hiPublishQueList" property="names" id="statusName"/>
								<c:choose>
									  <c:when test="${statusName.statusError == pubListItem.status}" >
									   <img src="/web/image/error2.gif" />
									  </c:when>
									  <c:when test="${statusName.statusComplete == pubListItem.status}" >
									   <img src="/web/image/complete.gif" />
									  </c:when>
									  <c:otherwise>
									    <img src="/web/image/publish.gif" />
									  </c:otherwise>
								</c:choose>
								&nbsp;
               </td>
            </tr>
            </logic:iterate>


      </table>
      </div>
      </span>
    </td>
  </tr>
</table>
</fieldset>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>