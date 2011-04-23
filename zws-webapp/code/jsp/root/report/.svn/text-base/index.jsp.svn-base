<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html> 
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/report/index.do" >
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="report"/>
<html:hidden property="criteria"/>


<% String title="Search"; %>
<%@ include file="/web/include/header.jsp"%>

<script type="text/javascript" language="JavaScript" src="/web/javascript/report/report.js"></script>

<table border="0" cellpadding="2" cellspacing="5" width="95%">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="2" cellspacing="1" width="95%">
      <tr>
        <td class="formTitle" align="left"><nobr>&nbsp;Report&nbsp;</nobr></td>
      </tr>
      <logic:iterate id="r" name="hiReport" property="presentationReports" type="java.lang.String">
      <tr>
        <td class="item">
         <html:link href="index.do"  paramId="selectedReportName" paramName="r" styleClass="button"><nobr><bean:write name="r"/></nobr></html:link>
        </td>
      </tr>
      </logic:iterate>
    </table>
  </td>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left">&nbsp;<bean:write name="hiReport" property="selectedReportName"/> &nbsp;&nbsp;&nbsp;&nbsp;</div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
              <% int idx=0; %>
              <logic:iterate id="fieldName" name="hiReport" property="metadataFields" type="java.lang.String">
                <% String len=fieldName.length()+""; 
                   if (fieldName.equalsIgnoreCase("name")) len = "32";
                %>
                <script language="javascript">
                  critFields[<%=idx%>] = "<%=fieldName%>";
                </script>
                <td class="listHeader"><nobr><bean:write name="fieldName"/></nobr><br><input type="text" name="<%="criteria"+idx++%>" size="<%=len%>"/></td>
              </logic:iterate>
            </tr>
            <logic:iterate id="metadata" name="hiReport" property="items" type="zws.hi.report.MetadataAdapter">
            <tr>
              <%int col=0;%>
              <bean:define id="origin" name="metadata" property="origin" type="zws.origin.Origin"/>
              <logic:iterate id="field" name="metadata" property="values" type="java.lang.String">
                <td class="item">
                  <% if (0==col)   { %> 
                  <a href="/report/index.do?event=download&ID=<%=java.net.URLEncoder.encode(origin.toString(), "UTF-8")%>" class="button"> 
                  <% } %>
                  <bean:write name="field"/>
                  <% if (0==col++) { %> 
                    </a>
                  <% } %>
                </td>
              </logic:iterate>
            </tr>
            </logic:iterate>
          </table>
        </td>
      </tr>
    </table>
    <input type="button" name="search" value="Search" onclick="javascript:sendCriteria('search')" class="button3">&nbsp;&nbsp;&nbsp;
 </td>
 </tr>
</table>

</html:form>
<script language="JavaScript">
  populateCriteria();
</script>
</body>
</html:html>
