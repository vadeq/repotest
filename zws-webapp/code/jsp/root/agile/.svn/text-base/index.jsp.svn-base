<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/agile/index.do" >
<html:hidden property="ID" value=""/>
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="report"/>
<html:hidden property="criteria"/>

<% String title="Publish To Agile"; %>
<%@ include file="/web/include/header.jsp"%>
<script type="text/javascript" language="JavaScript" src="/javascript/report/report.js"></script>

<SCRIPT Language="javascript">
  function selectFolder(folder , code) {}
  function selectLeaf(title, origin) {}
</SCRIPT>

<bean:define id="hiPublish2Agile" name="hiPublish2Agile" type="zws.hi.intralink.agile.hiPublish2Agile"/>

<%if (null!=hiPublish2Agile.getPublishResult()) {%>
<hr><%=hiPublish2Agile.getPublishResult()%><hr>
<%}%>
<table border="0" cellpadding="2" cellspacing="5" width="95%">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="2" cellspacing="1" width="95%">
      <tr>
        <td class="formTitle" align="left"><nobr>&nbsp;Report&nbsp;</nobr></td>
      </tr>
      <logic:iterate id="r" name="hiPublish2Agile" property="presentationReports" type="java.lang.String">
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
          <div align="left">&nbsp;<bean:write name="hiPublish2Agile" property="selectedReportName"/> &nbsp;&nbsp;&nbsp;&nbsp;</div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
              <td class="listHeader">&nbsp;</td>
              <% int idx=0; %>
              <logic:iterate id="fieldName" name="hiPublish2Agile" property="metadataFields" type="java.lang.String">
                <% String len=fieldName.length()+""; 
                   if (fieldName.equalsIgnoreCase("name")) len = "32";
                %>
                <script language="javascript">
                  critFields[<%=idx%>] = "<%=fieldName%>";
                </script>
                <td class="listHeader"><nobr><bean:write name="fieldName"/></nobr><br><input type="text" name="<%="criteria"+idx++%>" size="<%=len%>"/></td>
              </logic:iterate>
            </tr>
        <logic:iterate id="metadata" name="hiPublish2Agile" property="chosenItems" type="zws.hi.report.MetadataAdapter"> 
        <tr> 
          <td class="item" width="3" align="center"> 
            <input type="checkbox" onClick='unpick("<bean:write name="metadata" property="origin"/>")' checked />
          </td>
          <logic:iterate id="field" name="metadata" property="values" type="java.lang.String"> 
           <td class="item"><bean:write name="field"/></td>
          </logic:iterate> </tr>
        </logic:iterate> 

        <tr> 
          <logic:iterate id="fieldName" name="hiPublish2Agile" property="metadataFields" type="java.lang.String"> 
           <td class="item">&nbsp;</td>
          </logic:iterate>
           <td class="item">&nbsp;</td>
        </tr>



            <logic:iterate id="metadata" name="hiPublish2Agile" property="items" type="zws.hi.report.MetadataAdapter">
            <tr>
<td class="item"><input type="checkbox" onClick='pick("<bean:write name="metadata" property="origin"/>")' />
</td>
              <%int col=0;%>
              <bean:define id="origin" name="metadata" property="origin" type="zws.origin.Origin"/>
              <logic:iterate id="field" name="metadata" property="values" type="java.lang.String">
                <td class="item">
                  <bean:write name="field"/>
                </td>
              </logic:iterate>
            </tr>
            </logic:iterate>



          </table>
        </td>
      </tr>
      <tr>
       <td class="item">
<input type="button" name="search" value="Search" onclick="javascript:sendCriteria('search')" class="button3">&nbsp;&nbsp;&nbsp;
<%
  zws.hi.intralink.agile.hiPublish2Agile frmbeen = (zws.hi.intralink.agile.hiPublish2Agile)session.getAttribute("hiPublish2Agile");
 if (null!=frmbeen.getChosenItems() && frmbeen.getChosenItems().size()>0) {
%>
 <input type="button" name="Publish To Agile" value="Publish To Agile" onclick="javascript:sendEvent('publishToAgile')" class="button3">&nbsp;&nbsp;&nbsp;
 <input type="button" name="Generate IDF" value="Generate IDF" onclick="javascript:sendEvent('generateIDF')" class="button3">
<%
 }
%>
       </td>
      </tr>
    </table>
  </td>
 </tr>
</table>
</html:form>
<script language="JavaScript">
  populateCriteria(); 
</script>

</body>
</html:html>
