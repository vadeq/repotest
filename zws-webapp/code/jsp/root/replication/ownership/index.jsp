<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<script language="JavaScript"><!--
///Timer initializations;
var tID = '';
var refresh=0;
var refreshInterval=8888;
//--></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<!--
these may conflict w/ panel bar load stuff....
may need to add a javascript function that will call both..
onLoad="tID = setTimeout('executeTimer()', refreshInterval)" onMouseMove="resetTimer()"
-->
<html:form action="/replication/ownership/index.do">
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="report"/>
<html:hidden property="criteria"/>
<html:hidden property="autoSnapshot" value="true"/>
<input type="hidden" name="selectedPrinterNames"/>
<input type="hidden" name="selectedPrintQuantities"/>

<%@ include file="/web/include/interactor.params"%>

<% String title="Search"; %>
<%@ include file="/web/include/header.jsp"%>

<script type="text/javascript" language="JavaScript" src="/web/javascript/report/report.js"></script>


<script type="text/javascript"  language="javascript"> <!--
  function setAllPrintQuantities(formElement, e) {
   q = parseInt(formElement.value)
   if (!(q>0 && q<100)) q=1;
   for (i=1; i<document.forms[0].selectedPrintQuantities.length; i++) {
     document.forms[0].selectedPrintQuantities[i].value=q;
   }
  }

  function setAllPrinterNames(formElement) {
   n = formElement.value
   for (i=1; i<document.forms[0].selectedPrinterNames.length; i++) {
     document.forms[0].selectedPrinterNames[i].value=n;
   }
  }

//--></script>


<!-- Auto Refresh Timer -->
<script language="JavaScript"><!--
//ref: http://www.jdstiles.com/java/idleredirect.html

if (document.layers) window.captureEvents(Event.MOUSEMOVE);


function stopTimer() {
  clearTimeout(tID); // reset the timer
  tID = '';
}

function resetTimer(e) {
  clearTimeout(tID); // reset the timer
  if (refresh==1) tID = setTimeout('executeTimer()', refreshInterval);
  else stopTimer();
}

function executeTimer() {
  if (refresh==1) sendEvent('updateQueue');
  else stopTimer();
}

window.onMouseMove = resetTimer;
<logic:present name="hiOwnership" property="printQueue">
refresh=1;
</logic:present>
<logic:present name="hiOwnership" property="snapshotQueue">
refresh=1;
</logic:present>
//--></script>

<table border="0" cellpadding="2" cellspacing="5">
 <tr>
  <td valign="top">
<!-- ..Choosen Items -->
<logic:greaterThan name="hiOwnership" property="numberOfChosenItems" value="0">
<table border="0" cellpadding="2" cellspacing="5">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Ownership Transfer&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%"
            <tr>
             <td class="listHeader">&nbsp;</td>
              <td class="listHeader"><nobr>&nbsp;Name&nbsp;</nobr></td>
              <td valign="top" class="listHeader"><nobr>&nbsp;Ownership Site&nbsp;</nobr><br></td>
              <td valign="top" class="listHeader"><nobr>&nbsp;User Lock&nbsp;</nobr><br></td>
              <td valign="top" class="listHeader"><nobr>&nbsp;Email&nbsp;</nobr><br></td>
              <td valign="top" class="listHeader"><nobr>&nbsp;Time&nbsp;</nobr><br></td>
             <td class="listHeader"><nobr>Transfer To</nobr></td>
            </tr>
            <logic:iterate id="metadata" name="hiOwnership" property="chosenItems" type="zws.hi.IER.ownership.OwnershipMetadataAdapter">
            <tr>
             <td class="item" width="14" align="center">
              <input type="checkbox" onClick='unpick("<bean:write name="metadata" property="name"/>")' checked /></td>
             </td>
             <td class="item">&nbsp;<bean:write name="metadata" property="name"/></td>
             <td class="item">&nbsp;<b><bean:write name="metadata" property="siteOwner"/></b></td>
             <td class="item">&nbsp;<bean:write name="metadata" property="lockedBy"/></td>
             <td class="item">&nbsp;<bean:write name="metadata" property="lockEmail"/></td>
             <td class="item">&nbsp;<bean:write name="metadata" property="lockTime"/></td>
             <bean:define id="p" name="metadata" property="transferToSite"/>
             <td class="item" width="3" align="center">
              <select name="selectedOwnershipSites">
               <logic:iterate id="site" name="metadata" property="ownershipTransferSites" type="java.lang.String">
                <% if (site.equals(p)) { %>
                <option value="<%=site%>" selected="true"><%=site%>&nbsp;</option>
                <% } else { %>
                <option value="<%=site%>"><%=site%>&nbsp;</option>
                <% }%>
               </logic:iterate>
              </select>
             </td>
            </tr>
            </logic:iterate>
          </table>
        </td>
      </tr>
    </table>
    <html:link href="#" onclick="javascript:sendEvent('transferOwnership')" styleClass="button"><img gif="transfer_ownership" alt="Transfer Site Ownershipt"/></html:link>
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
          <div align="left"><nobr>&nbsp;Ownership&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
             <td class="listHeader" width="24">&nbsp;</td>
              <script language="javascript">
                critFields[0] = "Name";
              </script>
              <td class="listHeader"><nobr>&nbsp;Name&nbsp;</nobr><br><input type="text" name="criteria0" onkeypress="autoSubmitCriteria(this,'search');" size="32"/></td>
              <td valign="top" class="listHeader"><nobr>&nbsp;Ownership Site&nbsp;</nobr><br></td>
              <td valign="top" class="listHeader"><nobr>&nbsp;User Lock&nbsp;</nobr><br></td>
              <td valign="top" class="listHeader"><nobr>&nbsp;Email&nbsp;</nobr><br></td>
              <td valign="top" class="listHeader"><nobr>&nbsp;Time&nbsp;</nobr><br></td>
              <td valign="top" class="listHeader"><nobr>&nbsp;Synchronized Sites&nbsp;</nobr><br></td>
            </tr>
            <logic:iterate id="metadata" name="hiOwnership" property="items" type="zws.hi.IER.ownership.OwnershipMetadataAdapter">
            <bean:define id="origin" name="metadata" property="origin" type="zws.origin.Origin"/>
            <tr>
             <td class="item" width="14" align="center">
             <bean:define id="lockUser" name="metadata" property="lockedBy" type="java.lang.String"/>
		     <%
                if (null==lockUser || "".equals(lockUser.trim())) {
             %>
              <input type="checkbox" onClick='pick("<bean:write name="metadata" property="name"/>")'/>
		     <%
                } else {
             %>
              <input type="checkbox" onClick='pick("<bean:write name="metadata" property="name"/>")' DISABLED/>
		     <%
                }
             %>
             </td>
             <td class="item">&nbsp;<bean:write name="metadata" property="name"/></td>
             <td class="item">&nbsp;<b><bean:write name="metadata" property="siteOwner"/></b></td>
             <td class="item">&nbsp;<bean:write name="metadata" property="lockedBy"/></td>
             <td class="item">&nbsp;<bean:write name="metadata" property="lockEmail"/></td>
             <td class="item">&nbsp;<bean:write name="metadata" property="lockTime"/></td>
             <td class="item">&nbsp;<bean:write name="metadata" property="dataSources"/></td>
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
<!-- ..Search -->
  </td>
 </tr>
</table>

</html:form>
<script language="JavaScript">
  populateCriteria();
</script>
<%@ include file="/web/include/footer.jsp"%>
</body>
</html:html>
