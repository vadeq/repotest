<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/publish/search.do?menuItem=0.1">
<html:hidden property="event" value="search"/>
<html:hidden property="criteria" value=""/>

<%@ include file="/web/include/interactor.params"%>

<% String title="PEN Search"; %>
<%@ include file="/web/include/header.jsp"%>

<script type="text/javascript" language="JavaScript" src="/web/javascript/report/report.js"></script>





<!-- ..Choosen Items -->
<logic:greaterThan name="hiSearch" property="numberOfChosenItems" value="0">
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
             <td class="listHeader" align="center">
             Sync
             </td>
             <logic:iterate id="fieldName" name="hiSearch" property="metadataFields" type="java.lang.String">
             <td class="listHeader"><nobr>&nbsp;<bean:write name="fieldName"/>&nbsp;&nbsp;</nobr></td>
             </logic:iterate>
            </tr>
            <logic:iterate id="metadata" name="hiSearch" property="chosenItems" type="zws.hi.report.MetadataAdapter">
            <tr>
             <td class="item" width="14" align="center">
              <input type="checkbox" onClick='unpick("<bean:write name="metadata" property="origin"/>")' checked />
             </td>
             <td class="item" align="center">
               <logic:equal name="metadata" property="synchronizationStatus" value="synchronized">
                 <img src="/web/image/synchronized-green.gif" width="14" height="13" border="0" title="Published"/>
               </logic:equal >
               <logic:equal name="metadata" property="synchronizationStatus" value="not-synchronized">
                 <img src="/web/image/unsynchronized.gif" width="14" height="13" border="0" title="Never Published"/>
               </logic:equal>
               <logic:equal name="metadata" property="synchronizationStatus" value="previously-synchronized">
                 <img src="/web/image/unsynchronized-red.gif" width="14" height="13" border="0" title="Publish previously - Now Updated"/>
               </logic:equal>
             </td>
             <%int col=0;%>
             <bean:define id="origin" name="metadata" property="origin" type="zws.origin.Origin"/>
             <logic:iterate id="field" name="metadata" property="values" type="java.lang.String">
             <td class="item">
             <nobr>
<!--
              <% if (0==col++)   { %>
               <a href="/report/index.do?event=download&ID=<%=java.net.URLEncoder.encode(origin.toString(), "UTF-8")%>" class="button">
               &nbsp;<bean:write name="field"/>
               </a>
              <% } else {%>
               &nbsp;<bean:write name="field"/>
              <% }%>
-->
			  &nbsp;<bean:write name="field"/>
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
<html:link href="#" onclick="javascript:sendEvent('publish')" styleClass="button"><img gif="publish"/></html:link>
&nbsp;&nbsp;</nobr>
  </td>
 </tr>

</table>
</logic:greaterThan>
<!-- ..Choosen Items -->














<!-- Search.. -->
 <table border="0" cellpadding="0" cellspacing="0" class="form" valign="top">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Search &nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
		  <tr class="formMenu">
		   <td>
		    <table border="0" cellpadding="1" cellspacing="0">
		     <tr>
		      <td class="ok"><html:link href="#" onclick="javascript:sendCriteria('search')" styleClass="button"><img gif="search"/></html:link></td>
					  <logic:iterate property="repositories" name="hiSearch" id="repository">
					  <td class="okButton"><nobr>
					   <html:multibox property="selectedRepositories">
					    <bean:write name="repository" property="key"/>
					   </html:multibox>
					   <bean:write name="repository" property="value"/>&nbsp;&nbsp;&nbsp;
					   </nobr></td>
					  </logic:iterate>
		      </td>
		     </tr>
		    </table>
		   </td>
		  </tr>

      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
             <td class="listHeader" width="24">
              <br><input type="checkbox" onClick="sendEvent('pickAll')"/>
             </td>
             <td class="listHeader" align="center">
             Sync
             </td>
              <% int idx=0; %>
              <logic:iterate id="fieldName" name="hiSearch" property="metadataFields" type="java.lang.String">
                <% String len=fieldName.length()+"";
                   if (fieldName.equalsIgnoreCase("name")) len = "18";
                %>
                <script language="javascript">
                  critFields[<%=idx%>] = "<%=fieldName%>";
                </script>
                <td class="listHeader">
                <nobr>&nbsp;<bean:write name="fieldName"/>&nbsp;&nbsp;</nobr><br>
                <input type="text" name="<%="criteria"+idx++%>" onkeypress="autoSubmitCriteria(this,'search');" size="<%=len%>"/></td>
              </logic:iterate>
              <logic:equal name="__member" property="role" value="admin" scope="session">
              </logic:equal>
            </tr>
            <logic:iterate id="metadata" name="hiSearch" property="items" type="zws.hi.report.PublishMetadataAdapter">
            <bean:define id="origin" name="metadata" property="origin" type="zws.origin.Origin"/>
            <tr>
             <td class="item" width="14" align="center">
              <input type="checkbox" onClick='pick("<bean:write name="metadata" property="origin"/>")'/>
             </td>
             <td class="item" align="center">
               <logic:equal name="metadata" property="synchronizationStatus" value="synchronized">
                 <img src="/web/image/synchronized-green.gif" width="14" height="13" border="0" title="Published"/>
               </logic:equal >
               <logic:equal name="metadata" property="synchronizationStatus" value="not-synchronized">
                 <img src="/web/image/unsynchronized.gif" width="14" height="13" border="0" title="Never Published"/>
               </logic:equal>
               <logic:equal name="metadata" property="synchronizationStatus" value="previously-synchronized">
                 <img src="/web/image/unsynchronized-red.gif" width="14" height="13" border="0" title="Publish previously - Now Updated"/>
               </logic:equal>
             </td>
             <%int col=0;%>
             <bean:define id="origin" name="metadata" property="origin" type="zws.origin.Origin"/>
             <logic:iterate id="field" name="metadata" property="values" type="java.lang.String">
             <td class="item">
             <nobr>&nbsp;
			 <!--
              <% if (0==col++)   { %>
               <a href="/report/index.do?event=download&ID=<%=java.net.URLEncoder.encode(origin.toString(), "UTF-8")%>" class="button">
               &nbsp;<bean:write name="field"/>
               </a>
              <% } else {%>
               &nbsp;<bean:write name="field"/>
              <% }%>
			  -->
			  &nbsp;<bean:write name="field"/>
             </nobr>
             </td>
             </logic:iterate>
            </tr>
           </logic:iterate>
          </table>
        </td>
      </tr>
    </table>
    <html:link href="#" onclick="javascript:sendCriteria('search')" styleClass="button"><img gif="search" title="Run search"/></html:link>
  </td>
 </tr>
</table>
<!-- ..Search -->

<%@ include file="/web/include/footer.jsp"%>
</html:form>
<script language="JavaScript">
  populateCriteria();
</script>
</body>
</html:html>
