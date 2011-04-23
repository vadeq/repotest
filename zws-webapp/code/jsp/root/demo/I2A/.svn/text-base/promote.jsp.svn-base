<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/demo/I2A/promote.do">
<html:hidden property="event" value="ECOSearch"/>
<html:hidden property="interactor" value="report"/>

<%@ include file="/web/include/interactor.params"%>

<% String title="Promote"; %>
<%@ include file="/web/include/header.jsp"%>


<table border="0" cellpadding="2" cellspacing="5" valign="top" width="95%">
 <tr valign="top">
  <td valign="top" width="25%">
<!-- ECO Search.. -->
<table border="0" cellpadding="2" cellspacing="5" valign="top" >
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;ECO&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
              <td class="listHeader"><nobr>&nbsp;ECO Number&nbsp;&nbsp;</nobr><br><html:text property="ECONumberCriteria"/></td>
              <td class="listHeader" valign="top"><nobr>&nbsp;Status&nbsp;&nbsp;</nobr><br></td>
              <td class="listHeader" valign="top"><nobr>&nbsp;#</nobr><br></td>
            </tr>
            <logic:iterate id="eco" name="hiKLAPublish2Agile" property="ECOResults" type="zws.data.agile.eco.AgileECO">
            <bean:define id="number" name="eco" property="number"/>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="#" onclick="javascript:chooseFrom('ECO', '<%=number%>')" class="button"><bean:write name="eco" property="number"/></a>&nbsp;</nobr></td>
             <td class="item"><nobr>&nbsp;<bean:write name="eco" property="status"/>&nbsp;</nobr></td>
             <td class="item"><nobr>&nbsp;<bean:write name="eco" property="numberOfAffectedItems"/></nobr>&nbsp;</td>
            </tr>
           </logic:iterate>
          </table>
        </td>
      </tr>
    </table>
    <html:link href="#" onclick="javascript:sendEvent('createNewECO')" styleClass="button">Create New ECO</html:link>
    <!--
    <html:link href="#" onclick="javascript:sendEvent('ECOSearch')" styleClass="button">Search ECO's</html:link>
    -->
  </td>
 </tr>
</table>
<!-- ..ECO Search -->
  </td>

  <!-- Chosen ECO -->
  <td valign="top"><logic:present name="hiKLAPublish2Agile" property="selectedECO">
  <!-- Chosen ECO -->
<bean:define id="xeco" name="hiKLAPublish2Agile" property="selectedECO" type="zws.data.agile.eco.AgileECO"/>
<table border="0" cellpadding="2" cellspacing="5"  valign="top">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;ECO <bean:write name="xeco" property="number"/>&nbsp;&nbsp;-&nbsp;&nbsp;<bean:write name="xeco" property="status"/>&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
              <td colspan="4" class="listHeader"><nobr>&nbsp;Affected Items [<bean:write name="xeco" property="numberOfAffectedItems"/>]&nbsp;&nbsp;<html:link href="/demo/I2A/promote-eco-add.jsp" styleClass="button">add<%if (0<xeco.getNumberOfAffectedItems()) {%> more<%}%> changes</html:link>&nbsp;&nbsp;&nbsp;</nobr></td>
            </tr>
            <logic:present name="xeco" property="affectedItems">
            <tr>
             <td class="listHeader"><nobr>&nbsp;Number&nbsp;</nobr></td>
             <td class="listHeader"><nobr>&nbsp;Rev&nbsp;</nobr></td>
             <td class="listHeader"><nobr>&nbsp;Life Cycle Phase&nbsp;</nobr></td>
             <td class="listHeader"><nobr>&nbsp;Description&nbsp;</nobr></td>
            </tr>
            <logic:iterate id="item" name="xeco" property="affectedItems" type="zws.data.agile.eco.AffectedItem">
            <tr>
             <td class="item"><nobr>&nbsp;<bean:write name="item" property="itemNumber"/>&nbsp;</nobr></td>
             <td class="item"><nobr>&nbsp;[&nbsp;<strike><bean:write name="item" property="oldRev"/></strike>&nbsp;]&nbsp;=>&nbsp;[&nbsp;<bean:write name="item" property="newRev"/>&nbsp;]&nbsp;</nobr></td>
             <td class="item"><nobr>&nbsp;[&nbsp;<strike><bean:write name="item" property="oldLifeCyclePhase"/></strike>&nbsp;]&nbsp;=>&nbsp;[&nbsp;<bean:write name="item" property="lifeCyclePhase"/>&nbsp;]&nbsp;</nobr></td>
             <td class="item">&nbsp;<bean:write name="item" property="description"/>&nbsp;</td>
            </tr>
           </logic:iterate>
           </logic:present>
          </table>
        </td>
      </tr>
    </table>
  </td>
 </tr>
</table>
<!-- ..Choosen ECO -->
</logic:present>
  </td>
 </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>
</html:form>
</body>
</html:html>
