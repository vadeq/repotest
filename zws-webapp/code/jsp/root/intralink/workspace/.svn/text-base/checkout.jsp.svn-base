<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/intralink/workspace/checkout.do">
<html:hidden property="event" value="checkout"/>
<html:hidden property="interactor" value="report"/>
<html:hidden property="criteria"/>

<%@ include file="/web/include/interactor.params"%>

<bean:define id="wsName" name="hiCheckout" property="workspaceName"/>
<% String title="Checkout to " + wsName; %>
<%@ include file="/web/include/header.jsp"%>

<script type="text/javascript" language="JavaScript" src="/web/javascript/report/report.js"></script>

<!-- ..Choosen Items -->
<logic:greaterThan name="hiCheckout" property="numberOfChosenItems" value="0">
<table border="0" cellpadding="2" cellspacing="5">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Selected Source Files&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%"
            <tr>
             <td class="listHeader">&nbsp;</td>
             <logic:iterate id="fieldName" name="hiCheckout" property="metadataFields" type="java.lang.String">
             <td class="listHeader"><nobr><bean:write name="fieldName"/></nobr></td>
             </logic:iterate>
            </tr>
            <logic:iterate id="metadata" name="hiCheckout" property="chosenItems" type="zws.hi.report.MetadataAdapter"> 
            <tr>
             <td class="item" width="14" align="center">
              <input type="checkbox" onClick='unpick("<bean:write name="metadata" property="origin"/>")' checked /></td>
             </td>
             <%int col=0;%>
             <bean:define id="origin" name="metadata" property="origin" type="zws.origin.Origin"/>
             <logic:iterate id="field" name="metadata" property="values" type="java.lang.String">
             <td class="item">
             <nobr>
              <% if (0==col++)   { %> 
                <html:link href="#" onclick="javascript:sendEvent('updateQueue')" title="Check for Updates" styleClass="okButton">
                 <bean:write name="field"/>
                </html:link>
              <% } else {%>
               <bean:write name="field"/>
              <% } %>
             </nobr>
             </td>
             </logic:iterate>
            </tr>
            </logic:iterate>
          </table>
        </td>
      </tr>
    </table>
    <html:link href="#" onclick="javascript:sendEvent('checkout')" styleClass="button"><img gif="checkout" alt="Checkout to workspace"/></html:link>
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
             <td class="listHeader" width="24">&nbsp;</td>
              <% int idx=0; %>
              <logic:iterate id="fieldName" name="hiCheckout" property="metadataFields" type="java.lang.String">
                <% String len=fieldName.length()+""; 
                   if (fieldName.equalsIgnoreCase("name")) len = "32";
                %>
                <script language="javascript">
                  critFields[<%=idx%>] = "<%=fieldName%>";
                </script>
                <td class="listHeader"><nobr><bean:write name="fieldName"/></nobr><br><input type="text" name="<%="criteria"+idx++%>" onkeypress="autoSubmitCriteria(this,'search');" size="<%=len%>"/></td>
              </logic:iterate>
            </tr>
            <logic:iterate id="metadata" name="hiCheckout" property="items" type="zws.hi.report.MetadataAdapter">
            <bean:define id="origin" name="metadata" property="origin" type="zws.origin.Origin"/>
            <tr>
             <td class="item" width="14" align="center">
              <input type="checkbox" onClick='pick("<bean:write name="metadata" property="origin"/>")'/>
             </td>
             <%int col=0;%>
             <bean:define id="origin" name="metadata" property="origin" type="zws.origin.Origin"/>
             <logic:iterate id="field" name="metadata" property="values" type="java.lang.String">
             <td class="item">
             <nobr>
              <% if (0==col++) { %> 
                <html:link href="/CADPrinter/index.do?event=generateImageSnapshot" paramId="ID" paramName="metadata" paramProperty="origin" title="Generate PDF" styleClass="okButton">
                 <bean:write name="field"/>
                </html:link>
              <% } else {%>
               <bean:write name="field"/>
              <% } %>
             </nobr>
             </td>
             </logic:iterate>
            </tr>
           </logic:iterate>
          </table>
        </td>
      </tr>
    </table>
    <html:link href="#" onclick="javascript:sendCriteria('search')" styleClass="button"><img gif="search" alt="Search"/></html:link>
  </td>
 </tr>
</table>
<!-- ..Search -->

</html:form>
<script language="JavaScript">
  populateCriteria();
</script>
<%@ include file="/web/include/footer.jsp"%>
</body>
</html:html>
