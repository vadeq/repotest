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
<body topmargin="0" leftmargin="0" bgcolor="white" onLoad="tID = setTimeout('executeTimer()', refreshInterval)" onMouseMove="resetTimer()">
<html:form action="/snapshot/index.do">
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="report"/>
<html:hidden property="criteria"/>
<input type="hidden" name="selectedPrinterNames"/>
<input type="hidden" name="selectedPrintQuantities"/>

<%@ include file="/web/include/interactor.params"%>

<% String title="Viewables"; %>
<%@ include file="/web/include/header.jsp"%>

<script type="text/javascript" language="JavaScript" src="/web/javascript/report/report.js"></script>


<script type="text/javascript"  language="javascript"> <!--
/*
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
*/
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
<logic:present name="hiProcOnFly" property="printQueue">
refresh=1;
</logic:present>
<logic:present name="hiProcOnFly" property="snapshotQueue">
refresh=1;
</logic:present>
//--></script>

<table border="0" cellpadding="2" cellspacing="5">
 <tr>
  <td width="33%" valign="top">

<!-- Snapshot Queue.. -->
<table border="0" cellpadding="2" cellspacing="5" width="100%">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Image Queue&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
             <td class="listHeader"><nobr>Source File</nobr></td>
            </tr>
            <logic:present name="hiProcOnFly" property="snapshotQueue">
            <logic:iterate id="origin" name="hiProcOnFly" property="snapshotQueue" type="zws.origin.Origin">
            <tr>
             <td class="item">
              <nobr><b>
               <html:link href="#" onclick="javascript:sendEvent('updateQueue')" title="Check for Updates" styleClass="okButton">
                <img src="/web/image/no-pdficon.gif" width="14" height="13" border="0"/> <bean:write name="origin" property="uniqueID"/>
               </html:link>
              </b>&nbsp;</nobr>
             </td>
            </tr>
            </logic:iterate>
            </logic:present>
            <logic:notPresent name="hiProcOnFly" property="snapshotQueue">
            <tr>
             <td class="item"><center><nobr>&nbsp;</nobr></center></td>
            </tr>
            </logic:notPresent>
          </table>
        </td>
      </tr>
    </table>
  </td>
 </tr>
</table>
<!-- ..Snapshot Queue -->
<hr>
  <nobr>&nbsp;&nbsp;<html:link href="#" onclick="javascript:sendEvent('updateQueue')" title="Check for Updates" styleClass="okButton">Update</html:link></nobr>
  </td>

  <td valign="top">
<!-- ..Choosen Items -->
<logic:greaterThan name="hiProcOnFly" property="numberOfChosenItems" value="0">
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
             <logic:iterate id="fieldName" name="hiProcOnFly" property="metadataFields" type="java.lang.String">
             <td class="listHeader"><nobr><bean:write name="fieldName"/></nobr></td>
             </logic:iterate>
             <td class="listHeader">
              <select name="defaultPrinter" onchange='setAllPrinterNames(this)'>
               <logic:iterate id="printer" name="hiProcOnFly" property="printerNames" type="java.lang.String">
                <option value="<%=printer%>"><%=printer%>&nbsp;</option>
               </logic:iterate>
              </select>
             </td>
             <td class="item" align="center"><input type="text" name="defaultQuantity" value="1" size="2" onKeyUp='setAllPrintQuantities(this, event)'/></td>
            </tr>
            <logic:iterate id="metadata" name="hiProcOnFly" property="chosenItems" type="zws.hi.intralink.proconfly.PDFMetadataAdapter"> 
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
               <logic:present name="metadata" property="PDFOrigin">
                <html:link href="/CADPrinter/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="PDFOrigin" title="Download PDF" styleClass="button">
                 <img src="/web/image/pdficon.gif" width="14" height="13" border="0"/> <bean:write name="field"/>
                </html:link>
               </logic:present>
               <logic:notPresent name="metadata" property="PDFOrigin">
                <html:link href="#" onclick="javascript:sendEvent('updateQueue')" title="Check for Updates" styleClass="okButton">
                 <img src="/web/image/no-pdficon.gif" width="14" height="13" border="0"/> <bean:write name="field"/>
                </html:link>
               </logic:notPresent>
              <% } else {%>
               <bean:write name="field"/>
              <% } %>
             </nobr>
             </td>
             </logic:iterate>
             <bean:define id="q" name="metadata" property="printQuantity"/>
             <bean:define id="p" name="metadata" property="printerName"/>
             <td class="item" width="3" align="center">
              <select name="selectedPrinterNames">
               <logic:iterate id="printer" name="hiProcOnFly" property="printerNames" type="java.lang.String">
                <% if (printer.equals(p)) { %>
                <option value="<%=printer%>" selected="true"><%=printer%>&nbsp;</option>
                <% } else { %>
                <option value="<%=printer%>"><%=printer%>&nbsp;</option>
                <% }%>
               </logic:iterate>
              </select>
             </td>
             <td class="item" width="3" align="center"><input type="text" name="selectedPrintQuantities" value="<%=q%>" size="2"/></td>
            </tr>
            </logic:iterate>
          </table>
        </td>
      </tr>
    </table>
    <html:link href="#" onclick="javascript:sendEvent('printSelectedItems')" styleClass="button">Print</html:link>
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
              <logic:iterate id="fieldName" name="hiProcOnFly" property="metadataFields" type="java.lang.String">
                <% String len=fieldName.length()+""; 
                   if (fieldName.equalsIgnoreCase("name")) len = "32";
                %>
                <script language="javascript">
                  critFields[<%=idx%>] = "<%=fieldName%>";
                </script>
                <td class="listHeader"><nobr><bean:write name="fieldName"/></nobr><br><input type="text" name="<%="criteria"+idx++%>" onkeypress="autoSubmitCriteria(this,'search');" size="<%=len%>"/></td>
              </logic:iterate>
              <logic:equal name="__member" property="role" value="admin" scope="session">
              <td class="listHeader">&nbsp;</td>
              </logic:equal>
            </tr>
            <logic:iterate id="metadata" name="hiProcOnFly" property="items" type="zws.hi.intralink.proconfly.PDFMetadataAdapter">
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
               <logic:present name="metadata" property="PDFOrigin">
                <html:link href="/CADPrinter/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="PDFOrigin" title="Download PDF" styleClass="button">
                 <img src="/web/image/pdficon.gif" width="14" height="13" border="0"/> <bean:write name="field"/>
                </html:link>
               </logic:present>
               <logic:notPresent name="metadata" property="PDFOrigin">
                <html:link href="/CADPrinter/index.do?event=generateImageSnapshot" paramId="ID" paramName="metadata" paramProperty="origin" title="Generate PDF" styleClass="okButton">
                 <img src="/web/image/no-pdficon.gif" width="14" height="13" border="0"/> <bean:write name="field"/>
                </html:link>
               </logic:notPresent>
              <% } else {%>
               <bean:write name="field"/>
              <% } %>
             </nobr>
             </td>
             </logic:iterate>
             <logic:equal name="__member" property="role" value="admin" scope="session">
              <logic:present name="metadata" property="PDFOrigin">
              <td class="listHeader"><html:link href="/CADPrinter/index.do?event=clearPDF" paramId="ID" paramName="metadata" paramProperty="origin" title="Clear PDF" styleClass="cancelButton">x</html:link></td>
              </logic:present>
              <logic:notPresent name="metadata" property="PDFOrigin">
              <td class="listHeader">&nbsp;</td>
              </logic:notPresent>
             </logic:equal>
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

</html:form>
<script language="JavaScript">
  populateCriteria();
</script>
</body>
</html:html>
