<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/topology/index.do" >
<html:hidden property="event" value="ping"/>
<html:hidden property="ID" value=""/>
<html:hidden property="eventKey" value=""/>
<html:hidden property="interactor" value="topology"/>

<% String title="Node Topology"; %>
<%@ include file="/web/include/header.jsp"%>
<table border="0" cellpadding="0" cellspacing="0">
<tr><td>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle"><nobr>Nodes List</td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
    <tr>
     <td class="listHeader">Realm</td>
     <td colspan="3" class="listHeader">Node</td>
     <td class="listHeader">Status</td>
     <td class="listHeader">Ping</td>
     <td colspan="3" class="listHeader">Host</td>
     <td class="listHeader">Links</td>
     <td colspan="3" class="listHeader">Contact</td>
     <td colspan="4" class="listHeader">Location</td>
     <td class="listHeader">Description</td>
     <td class="listHeader">Reload</td>
     <td class="listHeader">&nbsp</td>
    </tr>
	<logic:present name="hiTopology" property="nodeList">
    <logic:iterate id="nn" name="hiTopology" property="nodeList" type="zws.hi.topology.NodeAdapter">
    <tr>
     <td class="item"><nobr><bean:write name="nn" property="realm"/></nobr></td>

     <td class="item"><nobr><bean:write name="nn" property="number"/></nobr></td>
     <td class="item"><nobr><bean:write name="nn" property="alias"/></nobr></td>
     <td class="item"><nobr><nobr><bean:write name="nn" property="type"/></nobr></td>

     <td class="item"><nobr><bean:write name="nn" property="status"/></nobr>
     </td>
     <td class="item"><nobr>
       <html:link href="index.do?event=ping" paramId="ID" paramName="nn" paramProperty="IPAddress" styleClass="button" title="Ping this node">
        <bean:write name="nn" property="pingSpeed"/>
       </html:link>
      </nobr>
     </td>
     <td class="item"><nobr><bean:write name="nn" property="hostName"/></nobr></td>
     <td class="item"><nobr><bean:write name="nn" property="domainName"/></nobr></td>
     <td class="item"><nobr><bean:write name="nn" property="IPAddress"/></nobr></td>
     <td class="item"><nobr>0<!-- bean:write name="nn" property="connectionCount"/ --></nobr></td>
     <td class="item"><nobr><bean:write name="nn" property="contactName"/></nobr></td>
     <td class="item"><nobr><bean:write name="nn" property="contactNumber"/></nobr></td>
     <td class="item"><nobr><bean:write name="nn" property="contactEmail"/></nobr></td>
     <td class="item"><nobr><bean:write name="nn" property="city"/></nobr></td>
     <td class="item"><nobr><bean:write name="nn" property="state"/></nobr></td>
     <td class="item"><nobr><bean:write name="nn" property="country"/></nobr></td>
     <td class="item"><nobr><bean:write name="nn" property="location"/></nobr></td>
     <td class="item"><nobr><bean:write name="nn" property="description"/></nobr></td>
     <td class="item">
      <html:link href="index.do?event=reload" paramId="ID" paramName="nn" paramProperty="hostName" styleClass="button" title="Relead Node's Configuration">reload</nobr></html:link> &nbsp;
     </td>
     <td class="item">
       <html:link href="index.do?event=removeNode" paramId="ID" paramName="nn" paramProperty="hostName" styleClass="button" title="Remove Node">x</html:link>
    </td>
    </tr>
    </logic:iterate>
    </logic:present>
   </table>
    </td>
  </tr>
</table>
</td></tr>
  <tr class="formMenu">
   <td>
    <table border="0" cellpadding="1" cellspacing="0" width="100%">
     <tr>
      <td class="saveButton">
        New Node #
        <html:select name="hiTopology" property="newNodeNumber">
          <html:options property="nodeNumbers"/>
        </html:select>&nbsp;
        IP/Hostname
        <html:text name="hiTopology" property="newIPAddress" size="22" onkeypress="autoSubmit(this,event)"/>
        Port:
        <html:text name="hiTopology" property="newPort" size="5" onkeypress="autoSubmit(this,event)"/>
        <a href="javascript:sendEvent('addNewNode')" class="button"><img gif="add"/></a></td>
     </tr>
    </table>
   </td>
  </tr>
</table>


<hr>
<logic:present name="hiTopology" property="nodeList">
<table border="0" cellpadding="0" cellspacing="0">
<tr><td>
<table class="form" border="0" cellpadding="0" cellspacing="0">
 <tr>
  <td class="formTitle"><nobr>Network Topology</td>
 </tr>
 <tr>
  <td>
   <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
    <tr>
     <td class="listHeader">Node</td>
     <logic:iterate id="nn" name="hiTopology" property="nodeList" type="zws.hi.topology.NodeAdapter">
       <td class="listHeader"><nobr>&nbsp;<bean:write name="nn" property="alias"/>&nbsp;</nobr></td>
     </logic:iterate>
    </tr>
    <logic:iterate id="nn" name="hiTopology" property="nodeList" type="zws.hi.topology.NodeAdapter">
     <tr>
      <td class="listHeader"><nobr>&nbsp;<bean:write name="nn" property="alias"/>&nbsp;</nobr></td>
     <logic:iterate id="con" name="nn" property="nodeConnections" type="zws.hi.topology.NodeConnection">
	  <logic:equal name="con" property="connectionStatus" value="self">
      <td class="item" align="center"><nobr>&nbsp;--------&nbsp;</nobr></td>
      </logic:equal>
	  <logic:equal name="con" property="connectionStatus" value="on-line">
      <td class="item" align="center"><nobr>&nbsp;<a href="javascript:sendEventIDKey('unregisterConnection', '<bean:write name="con" property="sourceIPAddress"/>', '<bean:write name="con" property="targetIPAddress"/>')"><bean:write name="con" property="targetAlias"/></a>&nbsp;</nobr></td>
      </logic:equal>
	  <logic:equal name="con" property="connectionStatus" value="off-line">
      <td class="item" align="center"><nobr>&nbsp;<a href="javascript:sendEventIDKey('registerConnection', '<bean:write name="con" property="sourceIPAddress"/>', '<bean:write name="con" property="targetIPAddress"/>')">(link)</a>&nbsp;</nobr></td>
      </logic:equal>
     </logic:iterate>
    </tr>
    </logic:iterate>  
   </table>
    </td>
  </tr>
</table>
</td></tr>
  <tr class="formMenu">
   <td>
    <table border="0" cellpadding="1" cellspacing="0" width="100%">
     <tr>
      <td class="saveButton"><nobr><a href="#">Rescan All Network Nodes</nobr></a></td>
     </tr>
    </table>
   </td>
  </tr>
</table>
</logic:present>

<%@ include file="/web/include/footer.jsp"%>
</html:form>
</body>
</html:html>
