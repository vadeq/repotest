<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/replication/index.do">
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="report"/>
<html:hidden property="criteria"/>

<%@ include file="/web/include/interactor.params"%>

<% String title="Replication"; %>
<%@ include file="/web/include/header.jsp"%>

<script type="text/javascript" language="JavaScript" src="/web/javascript/report/report.js"></script>

<table border="0" cellpadding="0" cellspacing="0">
<tr>
 <td valign="top" width="30%" align="left">

<!-- Policy Selector -->
<table class="form" border="0" cellpadding="0" cellspacing="0" align="left">
  <tr>
    <td class="formTitle"><nobr>&nbsp;&nbsp;Select Replication Policy&nbsp;&nbsp;</nobr></td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <bean:define id="selectedIERPolicyName" name="hiReplication" property="selectedIERPolicyName" type="java.lang.String"/>
        <logic:iterate id="policyName" name="hiReplication" property="policyNames" type="java.lang.String">  
        <%if(policyName.equals(selectedIERPolicyName)) { %>
        <tr><td class="selectedItem"><nobr>&nbsp;&nbsp;<bean:write name="policyName"/>&nbsp;&nbsp;</nobr></td></tr>
        <% } else { %>
        <tr><td class="item"><nobr>&nbsp;&nbsp;<a href="index.do?selectedIERPolicyName=<bean:write name="policyName"/>" class="button"><bean:write name="policyName"/></a>&nbsp;&nbsp;</nobr></td></tr>
        <% } %>
        </logic:iterate>
      </table>
    </td>
  </tr>
</tr>

<tr><td class="item">&nbsp;</td></tr>
<tr>
<td valign="top">
<!-- Policy Selector -->
<logic:present name="hiReplication" property="selectedSourceSpace">
         <table class="form" border="0" cellpadding="2" cellspacing="1">
          <tr><td class="selectedItem" colspan="3"><nobr>&nbsp;Policy: <bean:write name="hiReplication" property="selectedIERPolicyName"/>&nbsp;</nobr></td> </tr>
          <bean:define id="sourceSpace" name="hiReplication" property="selectedSourceSpace" type="zws.space.DataSpace"/>
          <tr>
           <td class="highlitedItem0"><nobr>&nbsp;Source&nbsp;</nobr></td>
           <td class="highlitedItem0"><nobr>&nbsp;<bean:write name="sourceSpace" property="datasourceName"/>&nbsp;</nobr></td>
           <td class="highlitedItem0"><nobr>&nbsp;</nobr></td>
          </tr>
          <logic:iterate id="targetSpace" name="hiReplication" property="selectedTargetSpaces" type="zws.space.DataSpace">
          <tr>
           <td class="prop"><nobr>&nbsp;Target&nbsp;</nobr></td>
           <td class="value"><nobr>&nbsp;<bean:write name="targetSpace" property="datasourceName"/>&nbsp;</nobr></td>
           <% if (targetSpace.getOverwriteConflicts()) { %>
           <td class="value"><nobr>&nbsp;<input type="checkbox" onClick='sendEventID("toggleOverwrite", "<bean:write name="targetSpace" property="name"/>")' checked/> Overwrite&nbsp;</nobr></td>
           <% } else { %>
           <td class="value"><nobr>&nbsp;<input type="checkbox" onClick='sendEventID("toggleOverwrite", "<bean:write name="targetSpace" property="name"/>")' /> Overwrite&nbsp;</nobr></td>
           <% }        %>
          </tr>
          </logic:iterate>
         </table>
</logic:present>

</td>
</tr>
</table>
</td>

<td valign="top" width="1%">&nbsp;</td>

<!-- Report -->
<td valign="top"><nobr>

<!-- ..Choosen Items -->
<logic:present name="hiReplication" property="selectedSourceSpace">
<logic:greaterThan name="hiReplication" property="numberOfChosenItems" value="0">
<table border="0" cellpadding="0" cellspacing="0" class="form">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Selected Files&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%"
            <tr>
             <td class="listHeader">
              <input type="checkbox" onClick="sendEvent('unpickAll')" checked="true"/>
             </td>
             <logic:iterate id="fieldName" name="hiReplication" property="metadataFields" type="java.lang.String">
             <td class="listHeader"><nobr>&nbsp;<bean:write name="fieldName"/>&nbsp;&nbsp;</nobr></td>
             </logic:iterate>
            </tr>
            <logic:iterate id="metadata" name="hiReplication" property="chosenItems" type="zws.hi.report.MetadataAdapter"> 
            <tr>
             <td class="item" width="14" align="center">
              <input type="checkbox" onClick='unpick("<bean:write name="metadata" property="origin"/>")' checked />
             </td>



             <%int col=0;%>
             <bean:define id="origin" name="metadata" property="origin" type="zws.origin.Origin"/>
             <logic:iterate id="field" name="metadata" property="values" type="java.lang.String">
             <td class="item">
             <nobr>
              <% if (0==col++)   { %>
              <!--
               -logic:present name="metadata" property="CADModelOrigin"-
                 <img src="/web/image/synchronized-red.gif" width="14" height="13" border="0"/-
               -/logic:present>
               -logic:notPresent name="metadata" property="CADModelOrigin"-
                 <img src="/web/image/unsynchronized.gif" width="14" height="13" border="0"/-
               -/logic:notPresent-
               -->
               <a href="/report/index.do?event=download&ID=<%=java.net.URLEncoder.encode(origin.toString(), "UTF-8")%>" class="button"> 
               &nbsp;<bean:write name="field"/>
               </a>
              <% } else {%>
               &nbsp;<bean:write name="field"/>
              <% }%>
             </nobr>
             </td>
             </logic:iterate>
            </tr>
            </logic:iterate>
          </table>
        </td>
      </tr>
    </table>
  </td>
 </tr>
 <tr class="formMenu">
  <td class="formMenu">  </td>
 </tr>
 <tr>
  <td class="formMenu" align="left"><nobr>&nbsp;&nbsp;
<html:link href="#" onclick="javascript:sendCriteria('replicate')" styleClass="button"><img gif="replicate" alt="Replicate Images"/></html:link>
&nbsp;&nbsp;</nobr>
  </td>
 </tr>
  
</table>
</logic:greaterThan>
<!-- ..Choosen Items -->
<!-- Search.. -->
<table border="0" cellpadding="2" cellspacing="5" valign="top">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;<bean:write name="hiReplication" property="selectedIERPolicyName"/> &nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
             <td class="listHeader" width="24">
              <br><input type="checkbox" onClick="sendEvent('pickAll')"/>
             </td>
              <% int idx=0; %>
              <logic:iterate id="fieldName" name="hiReplication" property="metadataFields" type="java.lang.String">
                <% String len=fieldName.length()+""; 
                   if (fieldName.equalsIgnoreCase("name")) len = "32";
                %>
                <script language="javascript">
                  critFields[<%=idx%>] = "<%=fieldName%>";
                </script>
                <td class="listHeader"><nobr>&nbsp;<bean:write name="fieldName"/>&nbsp;&nbsp;</nobr><br><input type="text" name="<%="criteria"+idx++%>" onkeypress="autoSubmitCriteria(this,'search');" size="<%=len%>"/></td>
              </logic:iterate>
              <logic:equal name="__member" property="role" value="admin" scope="session">
              </logic:equal>
            </tr>
            <logic:iterate id="metadata" name="hiReplication" property="items" type="zws.hi.report.MetadataAdapter">
            <bean:define id="origin" name="metadata" property="origin" type="zws.origin.Origin"/>
            <tr>
             <td class="item" width="14" align="center">
              <input type="checkbox" onClick='pick("<bean:write name="metadata" property="origin"/>")'/>
             </td>
             <%int col=0;%>
             <bean:define id="origin" name="metadata" property="origin" type="zws.origin.Origin"/>
             <logic:iterate id="field" name="metadata" property="values" type="java.lang.String">
             <td class="item">
             <nobr>&nbsp;
              <% if (0==col++)   { %>
              <!--
               -logic:present name="metadata" property="CADModelOrigin"-
                 <img src="/web/image/synchronized-red.gif" width="14" height="13" border="0"/>
               -/logic:present-
               -logic:notPresent name="metadata" property="CADModelOrigin"-
                 <img src="/web/image/unsynchronized.gif" width="14" height="13" border="0"/>
               -/logic:notPresent-
               -->
               <a href="/report/index.do?event=download&ID=<%=java.net.URLEncoder.encode(origin.toString(), "UTF-8")%>" class="button"> 
               &nbsp;<bean:write name="field"/>
               </a>
              <% } else {%>
               &nbsp;<bean:write name="field"/>
              <% }%>
             </nobr>
             </td>
             </logic:iterate>
            </tr>
           </logic:iterate>
          </table>
        </td>
      </tr>
    </table>
    <html:link href="#" onclick="javascript:sendCriteria('search')" styleClass="button"><img gif="search" alt="Run search"/></html:link>
  </td>
 </tr>
</table>
</logic:present>
<!-- ..Search -->
</nobr>


  </td>
 </tr>
</table>




<%@ include file="/web/include/footer.jsp"%>
</html:form>
<script language="JavaScript">
  populateCriteria();
</script>
</body>
</html:html>
