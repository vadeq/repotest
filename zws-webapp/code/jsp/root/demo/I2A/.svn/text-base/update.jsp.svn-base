<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white" onLoad="tID = setTimeout('executeTimer()', refreshInterval)" onMouseMove="resetTimer()">
<html:form action="/demo/I2A/update.do">
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="report"/>
<html:hidden property="criteria"/>

<%@ include file="/web/include/interactor.params"%>

<% String title="Update Intralink Changes to Agile"; %>

<%@ include file="/web/include/header.jsp"%>

<script type="text/javascript" language="JavaScript" src="/web/javascript/report/report.js"></script>

<table border="0" cellpadding="2" cellspacing="5">
 <tr>
  <td valign="top">
<!-- ..Choosen Items -->
<logic:greaterThan name="hiKLAPublish2Agile" property="numberOfChosenItems" value="0">
<table border="0" cellpadding="0" cellspacing="0" class="form">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;CAD Documents&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%"
            <tr>
             <td class="listHeader">
              <input type="checkbox" onClick="sendEvent('unpickAll')" checked="true"/>
             </td>
             <logic:iterate id="fieldName" name="hiKLAPublish2Agile" property="metadataFields" type="java.lang.String">
             <td class="listHeader"><nobr>&nbsp;<bean:write name="fieldName"/>&nbsp;&nbsp;</nobr></td>
             </logic:iterate>
            </tr>
            <logic:iterate id="metadata" name="hiKLAPublish2Agile" property="chosenItems" type="zws.hi.demo.kla.KLAMetadataAdapter"> 
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
               <logic:present name="metadata" property="CADDocumentOrigin">
                 <img src="/web/image/synchronized-green.gif" width="14" height="13" border="0"/> 
               </logic:present>
               <logic:notPresent name="metadata" property="CADDocumentOrigin">
                 <img src="/web/image/unsynchronized.gif" width="14" height="13" border="0"/>
               </logic:notPresent>
               <logic:present name="metadata" property="CADModelOrigin">
                 <img src="/web/image/synchronized-red.gif" width="14" height="13" border="0"/>
               </logic:present>
               <logic:notPresent name="metadata" property="CADModelOrigin">
                 <img src="/web/image/unsynchronized.gif" width="14" height="13" border="0"/>
               </logic:notPresent>
               <a href="/report/index.do?event=download&ID=<%=java.net.URLEncoder.encode(origin.toString(), "UTF-8")%>" class="button"> 
               &nbsp;<bean:write name="field"/>
               </a>
               <logic:equal name="metadata" property="isRenumbered" value="true"><img src="/web/image/new-number.gif" width="14" height="13" border="0"/></logic:equal>
               <logic:equal name="metadata" property="isRenamed" value="true"><img src="/web/image/new-name.gif" width="14" height="13" border="0"/></logic:equal>
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
<!-- <html:link href="#" onclick="javascript:sendEvent('publishCADDocuments')" styleClass="button"><img src="/web/image/synchronized-green.gif" width="14" height="13" border="0"/> Publish CAD Documents</html:link>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
   <html:link href="#" onclick="javascript:sendEvent('revUpdate')" styleClass="button"><img src="/web/image/synchronized-green.gif" width="14" height="13" border="0"/>&nbsp;<img src="/web/image/synchronized-red.gif" width="14" height="13" border="0"/>
    Publish Intralink Updates
   </html:link>
&nbsp;&nbsp;
<!--
	Create New Part: 
   <html:select name="hiKLAPublish2Agile" property="createNonExistingParts">
     <option value="YES">YES</option>
     <option value="NO">NO</option>
   </html:select>
   &nbsp;&nbsp;&nbsp;&nbsp;
   -->
   &nbsp;&nbsp;&nbsp;&nbsp;
   Create CAD Documents: 
   <html:select name="hiKLAPublish2Agile" property="createCADDocuments">
     <option value="NO">NO</option>
     <option value="YES">YES</option>
   </html:select>
   &nbsp;&nbsp;&nbsp;&nbsp;
   Attach Native File: 
   <html:select name="hiKLAPublish2Agile" property="attachNativeFile">
     <option value="NO">NO</option>
     <option value="YES">YES</option>
   </html:select>
   &nbsp;&nbsp;&nbsp;&nbsp;
   Attach IGES Image: 
   <html:select name="hiKLAPublish2Agile" property="attachIGESImage">
     <option value="NO">NO</option>
     <option value="YES">YES</option>
   </html:select>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <html:link href="#" onclick="javascript:sendEvent('delete')" styleClass="cancelButton">Delete</html:link>&nbsp;&nbsp;&nbsp;&nbsp;
   <html:link href="#" onclick="javascript:sendEvent('purgeCADParts')" styleClass="cancelButton"><font size="-2"><i>!!!Purge CAD Parts!!</i></font></html:link>
<!-- Uncomment for Admin// Comment when DEMOing
-->
<nobr>
  </td>



 </tr>
  
</table>
</logic:greaterThan>
<!-- ..Choosen Items -->

<!-- Search.. -->
<table border="0" cellpadding="2" cellspacing="5">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Source Files&nbsp;&nbsp;&nbsp;</nobr></div>
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
              <logic:iterate id="fieldName" name="hiKLAPublish2Agile" property="metadataFields" type="java.lang.String">
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
            <logic:iterate id="metadata" name="hiKLAPublish2Agile" property="items" type="zws.hi.demo.kla.KLAMetadataAdapter">
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
               <logic:present name="metadata" property="CADDocumentOrigin">
                 <img src="/web/image/synchronized-green.gif" width="14" height="13" border="0"/>
               </logic:present>
               <logic:notPresent name="metadata" property="CADDocumentOrigin">
                 <img src="/web/image/unsynchronized.gif" width="14" height="13" border="0"/>
               </logic:notPresent>
               <logic:present name="metadata" property="CADModelOrigin">
                 <img src="/web/image/synchronized-red.gif" width="14" height="13" border="0"/>
               </logic:present>
               <logic:notPresent name="metadata" property="CADModelOrigin">
                 <img src="/web/image/unsynchronized.gif" width="14" height="13" border="0"/>
               </logic:notPresent>
               <a href="/report/index.do?event=download&ID=<%=java.net.URLEncoder.encode(origin.toString(), "UTF-8")%>" class="button"> 
               &nbsp;<bean:write name="field"/>
               </a>
               <logic:equal name="metadata" property="isRenumbered" value="true"><img src="/web/image/new-number.gif" width="14" height="13" border="0"/></logic:equal>
               <logic:equal name="metadata" property="isRenamed" value="true"><img src="/web/image/new-name.gif" width="14" height="13" border="0"/></logic:equal>
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
    <html:link href="#" onclick="javascript:sendCriteria('search')" styleClass="button">Search</html:link>
  </td>
 </tr>
</table>
<!-- ..Search -->
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
