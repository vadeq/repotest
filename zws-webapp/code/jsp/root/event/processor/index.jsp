<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>
<script type="text/javascript" language="JavaScript" src="/web/javascript/treeview/treeview.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/event/processor/index.do">
<html:hidden property="event"/>

<%@ include file="/web/include/interactor.params"%>

<% String title="Event Processor"; %>
<%@ include file="/web/include/header.jsp"%>

<table border="0" cellpadding="0" cellspacing="0">
<tr>

<td valign="top" width="30%" align="right">

<!-- Server Selector -->
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle"><nobr>&nbsp;Selected Server&nbsp</nobr></td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
          <td class="listHeader">Node</td>
        </tr>
        <tr class="item">
         <td valign="top">
          <table border="0" cellpadding="0" cellspacing="0">
           <bean:define id="selectedServer" name="hiEventProcessor" property="selectedServer"/>
           <logic:iterate id="server" name="hiEventProcessor" property="serverList" type="java.lang.String">  
           <%if(server.equals(selectedServer)) { %>
           <tr><td class="selectedItem"><nobr>[<bean:write name="server"/>]</nobr></td></tr>
           <% } else { %>
           <tr><td class="item"><nobr><a href="index.do?selectedServer=<bean:write name="server"/>" class="button"><bean:write name="server"/></a></nobr></td></tr>
           <% } %>
           </logic:iterate>  
          </table border="0" cellpadding="0" cellspacing="0">
         </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<!-- Server Selector -->

</td>

<td valign="top" width="1%">&nbsp;</td>

<!-- Event Queue -->
<td valign="top" width="30%" align="left">

<logic:present name="hiEventProcessor" property="selectedServer">  
<table border="0" cellpadding="0" cellspacing="0">
<tr><td>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">Event Queue</td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
          <td class="prop"><nobr>&nbsp;Server</nobr></td>
          <td class="selectedItem"><nobr>&nbsp;<bean:write name="hiEventProcessor" property="selectedServer"/>&nbsp;</nobr></td>
          <td class="value"><nobr>&nbsp;</nobr></td>
        </tr>
        <tr>
        <bean:define id="state" name="hiEventProcessor" property="runningState"/>
          <td class="prop"><nobr>&nbsp;Run State</nobr></td>
          <td class="value"><nobr>
          <bean:define id="runningState" name="hiEventProcessor" property="runningState"/>
          <bean:write name="hiEventProcessor" property="runningState"/>
          <% if (!"running".equals(runningState)) { %>
          <img src="/web/image/alert.gif" alt="Not looking for events!"/>
          <% }  %>
          </nobr></td>
          <td class="value"><nobr>

          <% if ("running".equals(state) || "paused".equals(state)) { %>
          <a href="javascript:sendEvent('stopProcessor')"><img gif="stop"/></a>
          <% } else { %>
          <a href="javascript:sendEvent('startProcessor')"><img gif="play"/></a>
          <% } %>

          <!--
          <% if ("running".equals(state)) { %>
          <a href="javascript:sendEvent('pauseProcessor')"><img gif="pause"/></a>
          <% } else if ("paused".equals(state)) { %>
          <a href="javascript:sendEvent('resumeProcessor')"><img gif="resume"/></a>
          <% } else { %>
          <a href="javascript:sendEvent('pauseProcessor')"><img gif="pause" inactive="true" message="The Event Processor is already stopped"/></a>
          <% } %>
          -->
          </nobr></td>
        </tr>
        <tr>
          <bean:define id="handlingState" name="hiEventProcessor" property="handlingState"/>
          <td class="prop"><nobr>&nbsp;Incoming Events</nobr></td>
          <td class="value"><nobr>
          <% if (!"running".equals(runningState)) { %>
          <bean:write name="hiEventProcessor" property="runningState"/>
          <% } else {  %>
          <bean:write name="hiEventProcessor" property="handlingState"/>
            <% if ("ignored".equals(handlingState)) { %>
            <img src="/web/image/alert.gif" alt="Events are not processed!"/>
            <% }  %>
          <% } %>
          </nobr></td>
          <td class="value"><nobr>
            <% if (!"running".equals(runningState)) { %>
             <img gif="play" id="play2" inactive="true" message="watcher is not looking for <bean:write name="hiEventProcessor" property="selectedServer"/> IEE events" alt="Inactive!"/>
            <% } else if ("processing".equals(handlingState)) { %>
            <a href="javascript:sendEvent('ignoreEvents')"><img gif="stop"/></a>
            <% } else if ("ignored".equals(handlingState)) { %>
            <a href="javascript:sendEvent('handleEvents')"><img gif="play"/></a>
            <% }  %>
          </nobr></td class="value">
        </tr>
        <tr>
          <bean:define id="logDuration" name="hiEventProcessor" property="historyLogDuration"/>
          <td class="prop"><nobr>&nbsp;History Log</nobr></td>
          <td class="value"><nobr>&nbsp;
           <html:select name="hiEventProcessor" property="historyLogDuration" onchange="javascript:send();">
            <logic:iterate id="duration" name="hiEventProcessor" property="historyLogDurationOptions" type="java.lang.String">
            <% if (duration.equals(logDuration)) { %>
            <option value="<%=duration%>" selected="true"><%=duration%>&nbsp;</option>
            <% } else { %>
            <option value="<%=duration%>"><%=duration%>&nbsp;</option>
            <% }%>
            </logic:iterate>
           </html:select> hours
          </nobr></td>
          <td class="value"><nobr>&nbsp;</nobr></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</td></tr>
</table>
</logic:present>  

<br>&nbsp;
</td>
<!-- Event Queue ->

<td valign="top" width="1%">&nbsp;</td>
<td valign="top" width="30%">&nbsp;</td>

</tr>
</table>






<%@ include file="/web/include/footer.jsp"%>
</html:form>
</body>
</html:html>
