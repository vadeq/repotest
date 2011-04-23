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
<html:form action="/intralink/listener/index.do">
<html:hidden property="event"/>

<%@ include file="/web/include/interactor.params"%>

<% String title="Intralink Watcher"; %>
<%@ include file="/web/include/header.jsp"%>

<bean:define id="IEE" name="hiIntralinkListener"  property="IEE" type="zws.IntralinkClient"/>
<table border="0" cellpadding="0" cellspacing="0">
<tr>

<!-- IEE Selectors -->
<td valign="top" width="33%" align="right">
<table border="0" cellpadding="0" cellspacing="0">
<tr>

<!-- IEE Selector -->
<td>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">Select IEE Repository</td>
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
           <bean:define id="selectedIEEServer" name="hiIntralinkListener" property="selectedIEEServer"/>
           <logic:iterate id="server" name="IEE" property="serverList" type="java.lang.String">  
           <%if(server.equals(selectedIEEServer)) { %>
           <tr><td class="selectedItem"><nobr>[<bean:write name="server"/>]</nobr></td></tr>
           <% } else { %>
           <tr><td class="item"><nobr><a href="index.do?selectedIEEServer=<bean:write name="server"/>" class="button"><bean:write name="server"/></a></nobr></td></tr>
           <% } %>
           </logic:iterate>  
          </table border="0" cellpadding="0" cellspacing="0">
         </td>
         <td valign="top">
          <table border="0" cellpadding="0" cellspacing="0">
           <bean:define id="selectedIEERepository" name="hiIntralinkListener" property="selectedIEERepository"/>
           <logic:present name="hiIntralinkListener" property="IEERepositoryList">  
           <logic:iterate id="repository" name="hiIntralinkListener" property="IEERepositoryList" type="java.lang.String">  
           <%if(repository.equals(selectedIEERepository)) { %>
            <tr><td class="selectedItem"><nobr>[<bean:write name="repository"/>]</nobr></td></tr>
           <% } else { %>
            <tr><td class="item"><nobr ><a href="index.do?selectedIEERepository=<bean:write name="repository"/>" class="button"><bean:write name="repository"/></a></nobr></td></tr>
           <% } %>
           </logic:iterate>  
           </logic:present>  
          </table border="0" cellpadding="0" cellspacing="0">
         </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</td>
<!-- IEE Selector -->

</tr>
</table>

</td>
<!-- IEE Selectors -->

<td valign="top" width="1%">

<!-- IEE Watcher -->
<td valign="top" width="30%" align="left">

<logic:present name="hiIntralinkListener" property="selectedIEERepository">  
<table border="0" cellpadding="0" cellspacing="0">
<tr><td>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">IEE Watcher</td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
          <td class="prop"><nobr>&nbsp;Server</nobr></td>
          <td class="selectedItem"><nobr>&nbsp;<bean:write name="hiIntralinkListener" property="selectedIEEServer"/>&nbsp;</nobr></td>
          <td class="value"><nobr>&nbsp;</nobr></td>
        </tr>
        <tr>
          <td class="prop"><nobr>&nbsp;IEE Repository</nobr></td>
          <td class="selectedItem"><nobr>&nbsp;<bean:write name="hiIntralinkListener" property="selectedIEERepository"/>&nbsp;</nobr></td>
          <td class="value"><nobr>&nbsp;</nobr></td>
        </tr>
        <tr>
        <bean:define id="state" name="hiIntralinkListener" property="runningState"/>
          <td class="prop"><nobr>&nbsp;Run State</nobr></td>
          <td class="value"><nobr>
          <bean:define id="runningState" name="hiIntralinkListener" property="runningState"/>
          <bean:write name="hiIntralinkListener" property="runningState"/>
          <% if (!"running".equals(runningState)) { %>
          <img src="/web/image/alert.gif" alt="Not looking for events!"/>
          <% }  %>
          </nobr></td>
          <td class="value"><nobr>
            <% if ("no target".equals(state)) { %>
            <img gif="play" id="play1" inactive="true" message="At least one queue target must be selected!" alt="Inactive"/>
            <% } else if ("running".equals(state) || "paused".equals(state)) { %>
            <a href="javascript:sendEvent('stopListener')"><img gif="stop" alt="Stop listening for <bean:write name="hiIntralinkListener" property="selectedIEERepository"/> IEE events"/></a>
            <% } else { %>
            <a href="javascript:sendEvent('startListener')"><img gif="play" alt="Start lfor <bean:write name="hiIntralinkListener" property="selectedIEERepository"/> IEE events"/></a>
            <% } %>
<!--
            <% if ("running".equals(state)) { %>
            <a href="javascript:sendEvent('pauseListener')"><img gif="pause" alt="Suspend looking for IEE events"/></a>
            <% } else if ("paused".equals(state)) { %>
            <a href="javascript:sendEvent('resumeListener')"><img gif="resume" alt="Resume looking for IEE events"/></a>
            <% } %>
-->
          </nobr></td>
        </tr>
        <tr>
          <bean:define id="firingState" name="hiIntralinkListener" property="firingState"/>
          <td class="prop"><nobr>&nbsp;Event Status</nobr></td>
          <td class="value"><nobr>
          <% if (!"running".equals(runningState)) { %>
          <bean:write name="hiIntralinkListener" property="runningState"/>
          <% } else {  %>
          <bean:write name="hiIntralinkListener" property="firingState"/>
            <% if ("ignored".equals(firingState)) { %>
            <img src="/web/image/alert.gif" alt="Events are being logged but not fired!"/>
            <% }  %>
          <% } %>
          </nobr></td>
          <td class="value"><nobr>
            <% if (!"running".equals(runningState)) { %>
             <img gif="play" id="play2" inactive="true" message="watcher is not looking for <bean:write name="hiIntralinkListener" property="selectedIEERepository"/> IEE events" alt="Inactive!"/>
            <% } else if ("firing".equals(firingState)) { %>
            <a href="javascript:sendEvent('ignoreEvents')"><img gif="stop" alt="Ignore <bean:write name="hiIntralinkListener" property="selectedIEERepository"/> IEE events found"/></a>
            <% } else if ("ignored".equals(firingState)) { %>
            <a href="javascript:sendEvent('fireEvents')"><img gif="play" alt="Fire <bean:write name="hiIntralinkListener" property="selectedIEERepository"/> IEE events to targeted Queues"/></a>
            <% }  %>
          </nobr></td class="value">
        </tr>
        <tr>
          <td class="prop"><nobr>&nbsp;Poll Every</nobr></td>
          <td class="value"><nobr>&nbsp;
          <bean:define id="runPeriod" name="hiIntralinkListener" property="runPeriod"/>
            <html:select name="hiIntralinkListener" property="runPeriod" onchange="javascript:send();">
             <logic:iterate id="period" name="hiIntralinkListener" property="runPeriodOptions" type="java.lang.String">
             <% if (period.equals(runPeriod)) { %>
             <option value="<%=period%>" selected="true"><%=period%>&nbsp;</option>
             <% } else { %>
             <option value="<%=period%>"><%=period%>&nbsp;</option>
             <% }%>
             </logic:iterate>
            </html:select> seconds
          </nobr></td>
          <td class="value"><nobr>&nbsp;</nobr></td>
        </tr>
        <tr>
          <bean:define id="logDuration" name="hiIntralinkListener" property="historyLogDuration"/>
          <td class="prop"><nobr>&nbsp;History Log</nobr></td>
          <td class="value"><nobr>&nbsp;
           <html:select name="hiIntralinkListener" property="historyLogDuration" onchange="javascript:send();">
            <logic:iterate id="duration" name="hiIntralinkListener" property="historyLogDurationOptions" type="java.lang.String">
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
        <tr>
          <td class="prop" colspan="3" align="center">

<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">Targeted Queues</td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
          <td class="listHeader">Node</td>
          <td class="listHeader" width="1%">&nbsp;</td>
          <td class="listHeader">Targets</td>
        </tr>
        <tr class="item">
         <td valign="top">
          <table border="0" cellpadding="0" cellspacing="0">
           <logic:iterate id="server" name="IEE" property="serverList" type="java.lang.String">  
           <tr><td class="item"><nobr><a href="javascript:sendEventID('addTargetQueueNode', '<bean:write name="server"/>');" class="button"><bean:write name="server"/></a></nobr></td></tr>
           </logic:iterate>  
          </table border="0" cellpadding="0" cellspacing="0">
         </td>
         <td>&nbsp;</td>
         <td valign="top">
          <table border="0" cellpadding="2" cellspacing="0">
           <logic:iterate id="server" name="hiIntralinkListener" property="targetQueueNodes" type="java.lang.String">  
           <tr>
           <td class="value"><nobr><bean:write name="server"/></nobr></td>
           <td class="item"><nobr><a href="javascript:sendEventID('removeTargetQueueNode', '<bean:write name="server"/>')" class="button"> <img gif="iremove" alt="Remove as Target Event Queue"/></a></nobr></td>
           </tr>
           </logic:iterate>  
          </table border="0" cellpadding="0" cellspacing="0">
         </td>
        </tr>
      </table>
    </td>
  </tr>
</table>



          </td>
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
<!-- IEE Watcher -->

<td valign="top" width="1%">
<td valign="top" width="30%" align="left">

</tr>
</table>



<%@ include file="/web/include/footer.jsp"%>
</html:form>
</body>
</html:html>
