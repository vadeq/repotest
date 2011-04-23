<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>


<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/replication/report-duplicates/index.do">
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="report"/>

<%@ include file="/web/include/interactor.params"%>

<% String title="Report Duplicate Names"; %>
<%@ include file="/web/include/header.jsp"%>

<table border="0" cellpadding="0" cellspacing="0">
<tr>

<!-- IEE Selectors -->
<td valign="top">
<table border="0" cellpadding="0" cellspacing="0">
<tr>

<!-- IEE Selector 1 -->
<td>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">Select IEE Repository 1</td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
          <td class="listHeader">Node</td>
          <td class="listHeader">Repository</td>
        </tr>
        <tr class="item">
         <td valign="top">
          <table border="0" cellpadding="0" cellspacing="0">
           <bean:define id="selectedIEEServer0" name="hiReportDuplicates" property="selectedIEEServer0"/>
           <logic:iterate id="server0" name="hiReportDuplicates" property="serverList" type="java.lang.String">  
           <%if(server0.equals(selectedIEEServer0)) { %>
           <tr><td class="selectedItem"><nobr>[<bean:write name="server0"/>]</nobr></td></tr>
           <% } else { %>
           <tr><td class="item"><nobr><a href="index.do?selectedIEEServer0=<bean:write name="server0"/>" class="button"><bean:write name="server0"/></a></nobr></td></tr>
           <% } %>
           </logic:iterate>  
          </table border="0" cellpadding="0" cellspacing="0">
         </td>
         <td valign="top">
          <table border="0" cellpadding="0" cellspacing="0">
           <bean:define id="selectedIEERepository0" name="hiReportDuplicates" property="selectedIEERepository0"/>
           <logic:iterate id="repository0" name="hiReportDuplicates" property="repositoryList0" type="java.lang.String">  
           <%if(repository0.equals(selectedIEERepository0)) { %>
            <tr><td class="selectedItem"><nobr>[<bean:write name="repository0"/>]</nobr></td></tr>
           <% } else { %>
            <tr><td class="item"><nobr ><a href="index.do?selectedIEERepository0=<bean:write name="repository0"/>" class="button"><bean:write name="repository0"/></a></nobr></td></tr>
           <% } %>
           </logic:iterate>  
          </table border="0" cellpadding="0" cellspacing="0">
         </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</td>
<!-- IEE Selector 1 -->

</tr>
<tr><td>&nbsp;</td></tr>
<tr>


<!-- IEE Selector 2 -->
<td>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">Select IEE Repository 2</td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
          <td class="listHeader">Node</td>
          <td class="listHeader">Repository</td>
        </tr>
        <tr class="item">
         <td valign="top">
          <table border="0" cellpadding="0" cellspacing="0">
           <bean:define id="selectedIEEServer1" name="hiReportDuplicates" property="selectedIEEServer1"/>
           <logic:iterate id="server1" name="hiReportDuplicates" property="serverList" type="java.lang.String">  
           <%if(server1.equals(selectedIEEServer1)) { %>
           <tr><td class="selectedItem"><nobr>[<bean:write name="server1"/>]</nobr></td></tr>
           <% } else { %>
           <tr><td class="item"><nobr><a href="index.do?selectedIEEServer1=<bean:write name="server1"/>" class="button"><bean:write name="server1"/></a></nobr></td></tr>
           <% } %>
           </logic:iterate>  
          </table border="0" cellpadding="0" cellspacing="0">
         </td>
         <td valign="top">
          <table border="0" cellpadding="0" cellspacing="0">
           <bean:define id="selectedIEERepository1" name="hiReportDuplicates" property="selectedIEERepository1"/>
           <logic:iterate id="repository1" name="hiReportDuplicates" property="repositoryList1" type="java.lang.String">  
           <%if(repository1.equals(selectedIEERepository1)) { %>
            <tr><td class="selectedItem"><nobr>[<bean:write name="repository1"/>]</nobr></td></tr>
           <% } else { %>
            <tr><td class="item"><nobr ><a href="index.do?selectedIEERepository1=<bean:write name="repository1"/>" class="button"><bean:write name="repository1"/></a></nobr></td></tr>
           <% } %>
           </logic:iterate>  
          </table border="0" cellpadding="0" cellspacing="0">
         </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</td>
<!-- IEE Selector 2 -->

</tr>
  <tr class="formMenu">
   <td>
    <table border="0" cellpadding="1" cellspacing="0" width="100%">
     <tr>
      <td class="cancelButton"><a href="javascript:sendEvent('reportDuplicates')"><img gif="report_duplicates" alt="Report Duplicate Files"/></a></td>
     </tr>
    </table>
   </td>
  </tr>
</table>

</td>
<!-- IEE Selectors -->

<td valign="top">&nbsp;</td>

<!-- Duplicate Results -->
<td valign="top">

<logic:notEqual name="hiReportDuplicates" property="duplicateCount" value="0">
<table border="0" cellpadding="0" cellspacing="0">
<tr><td>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">Duplicate Files</td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
         <td class="listHeader">&nbsp;<bean:write name="hiReportDuplicates" property="duplicateCount"/> Duplicates</td>
         <td colspan="2" class="listHeader"><nobr>
         <bean:write name="hiReportDuplicates" property="activeIEERepository0"/> [<bean:write name="hiReportDuplicates" property="activeIEEServer0"/>]
         &nbsp;</nobr></td>
         <td colspan="2" class="listHeader"><nobr>
         <bean:write name="hiReportDuplicates" property="activeIEERepository1"/> [<bean:write name="hiReportDuplicates" property="activeIEEServer1"/>]
         &nbsp;</nobr></td>
        </tr>
        <logic:iterate id="item" name="hiReportDuplicates" property="items" type="zws.hi.intralink.duplicates.DuplicateNameAdapter">
        <tr>
         <td class="highlitedItem"><nobr><bean:write name="item" property="name"/></nobr></td>
         <logic:equal name="item" property="mostRecent" value="0">
         <td class="highlitedItem0"><nobr><bean:write name="item" property="author0"/></nobr></td>
         <td class="highlitedItem0"><nobr><bean:write name="item" property="timeOfLastModification0"/></nobr></td>
         <td class="item1"><nobr><bean:write name="item" property="author1"/></nobr></td>
         <td class="item1"><nobr><bean:write name="item" property="timeOfLastModification1"/></nobr></td>
         </logic:equal>

         <logic:notEqual name="item" property="mostRecent" value="0">
         <td class="item0"><nobr><bean:write name="item" property="author0"/></nobr></td>
         <td class="item0"><nobr><bean:write name="item" property="timeOfLastModification0"/></nobr></td>
         <td class="highlitedItem1"><nobr><bean:write name="item" property="author1"/></nobr></td>
         <td class="highlitedItem1"><nobr><bean:write name="item" property="timeOfLastModification1"/></nobr></td>
         </logic:notEqual>

        </tr>
        </logic:iterate>  
      </table>
    </td>
  </tr>
</table>
</td></tr>
</table>
</logic:notEqual>
<br>&nbsp;
</td>
<!-- Duplicate Results -->

<td valign="top">&nbsp;</td>

<td valign="top">
<logic:notEqual name="hiReportDuplicates" property="duplicateCount" value="0">
  <a href="javascript:sendEvent('clearItems')"><img gif="clear" alt="Clear Results"/></a>
</logic:notEqual>
<br>&nbsp;
</td>

</tr>
</table>
</html:form> 
<%@ include file="/web/include/footer.jsp"%>
</body>
</html:html>
