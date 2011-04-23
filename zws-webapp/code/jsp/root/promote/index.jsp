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
<html:form action="/promote/index.do">
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="report"/>
<html:hidden property="criteria"/>
<html:hidden property="autoSnapshot" value="false"/>
<input type="hidden" name="selectedPrinterNames"/>
<input type="hidden" name="selectedPrintQuantities"/>

<%@ include file="/web/include/interactor.params"%>

<% String title="Document Release"; %>
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


<!-- Promote Log.. -->
<table border="0" cellpadding="2" cellspacing="5" width="100%">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Promotion Log&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <logic:present name="hiProcOnFly" property="promotionLog">
            <tr>
             <td class="listHeader"><nobr>Source File</nobr></td>
             <td class="listHeader"><nobr>Release</nobr></td>
             <td class="listHeader"><nobr>Promoted To</nobr></td>
            <tr>
            <logic:iterate id="metadata" name="hiProcOnFly" property="promotionLog" type="zws.hi.intralink.proconfly.PDFMetadataAdapter">
            <%int val=0;%>
            <bean:define id="origin" name="metadata" property="origin" type="zws.origin.Origin"/>
             <td class="item"><nobr><b><bean:write name="origin" property="uniqueIDDisplay"/></b></nobr></td>
             <td class="item"><nobr><b><i><strike><bean:write name="metadata" property="promotedFrom"/></strike></i></b></nobr></td>
             <td class="item"><nobr><b><bean:write name="metadata" property="promoteTo"/></b></nobr></td>
            </tr>

            </logic:iterate>
            </logic:present>
            <logic:notPresent name="hiProcOnFly" property="promotionLog">
            <tr>
             <td colspan="2" class="item"><left><nobr><b>No Files Promoted</b></nobr></center></td>
            </tr>
            </logic:notPresent>
          </table>
        </td>
      </tr>
    </table>
  </td>
 </tr>
 <logic:present name="hiProcOnFly" property="promotionLog">
 <tr>
  <td class="button"><nobr><html:link href="#" onclick="javascript:sendEvent('clearPromotionLog')" title="Clear Promotion Log" styleClass="okButton">Clear Log</html:link></nobr></td>
 </tr>
 </logic:present>
</table>
<!-- ..Promote Log -->
<hr>
<!-- Mocel Check Log.. -->
<table border="0" cellpadding="2" cellspacing="5" width="100%">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Model Check Results&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <logic:present name="hiProcOnFly" property="modelcheckLog">
            <tr>
             <td class="listHeader"><nobr>Source File</nobr></td>
             <td class="listHeader"><nobr>Status</nobr></td>
            <tr>
            <logic:iterate id="metadata" name="hiProcOnFly" property="modelcheckLog" type="zws.hi.intralink.proconfly.PDFMetadataAdapter">
            <%int val=0;%>
            <bean:define id="origin" name="metadata" property="origin" type="zws.origin.Origin"/>
             <td class="item"><nobr><b><bean:write name="origin" property="uniqueIDDisplay"/></b></nobr></td>
             <td class="item"><nobr><b><bean:write name="metadata" property="verificationStatus"/></b></nobr></td>
            </tr>
            </logic:iterate>
            </logic:present>
            <logic:notPresent name="hiProcOnFly" property="modelcheckLog">
            <tr>
             <td colspan="2" class="item"><left><nobr><b>No Files Verified</b></nobr></center></td>
            </tr>
            </logic:notPresent>
          </table>
        </td>
      </tr>
    </table>
  </td>
 </tr>
 <logic:present name="hiProcOnFly" property="modelcheckLog">
 <tr>
  <td class="button"><nobr><html:link href="#" onclick="javascript:sendEvent('clearModelcheckLog')" title="Clear Model Check Log" styleClass="okButton">Clear results</html:link></nobr></td>
 </tr>
 </logic:present>
</table>
<!-- ..Model Check Log -->
<hr>
<!-- Snapshot Queue.. -->
<table border="0" cellpadding="2" cellspacing="5" width="100%">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Image Processing&nbsp;&nbsp;&nbsp;</nobr></div>
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
              <nobr>&nbsp;<b>
               <html:link href="#" onclick="javascript:sendEvent('updateQueue')" title="Check for Updates" styleClass="okButton">
                <bean:write name="origin" property="uniqueIDDisplay"/>
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
 <tr>
  <td class="button"><nobr><html:link href="#" onclick="javascript:sendEvent('updateQueue')" title="Check for Updates" styleClass="okButton">Refresh</html:link></nobr></td>
 </tr>
</table>
<!-- ..Snapshot Queue -->
<hr>
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
          <div align="left"><nobr>&nbsp;Intralink Files Selected&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%"
            <tr>
             <td class="listHeader">&nbsp;</td>
             <td class="listHeader">&nbsp;</td>
             <logic:iterate id="fieldName" name="hiProcOnFly" property="metadataFields" type="java.lang.String">
             <td class="listHeader"><nobr><bean:write name="fieldName"/></nobr></td>
             </logic:iterate>
             <td class="listHeader"><nobr>Verify</nobr></td>
             <td class="listHeader"><nobr>Promote To</nobr></td>
             <td class="listHeader"><nobr>Viewable</nobr></td>
            </tr>
            <logic:iterate id="metadata" name="hiProcOnFly" property="chosenItems" type="zws.hi.intralink.proconfly.PDFMetadataAdapter"> 
            <tr>
             <td class="item" width="14" align="center">
              <input type="checkbox" onClick='unpick("<bean:write name="metadata" property="origin"/>")' checked /></td>
             </td>
             <td class="item">
             <nobr>

               <!-- PDF ICON -->
               <logic:present name="metadata" property="PDFOrigin">
                <html:link href="/promote/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="PDFOrigin" title="Download PDF Image" styleClass="button">
                 <img src="/web/image/pdficon.gif" width="14" height="13" border="0"/>
                </html:link>
               </logic:present>
               <!--
               <logic:notPresent name="metadata" property="PDFOrigin">
                 <img src="/web/image/no-pdficon.gif" width="14" height="13" border="0"/>
               </logic:notPresent>
               -->

               <!-- HPGL ICON -->
               <logic:present name="metadata" property="HPGLOrigin">
                <html:link href="/promote/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="HPGLOrigin" title="Download HPGL Image" styleClass="button">
                 <img src="/web/image/hpglicon.gif" width="34" height="13" border="0"/>
                </html:link>
               </logic:present>
               <!--
               <logic:notPresent name="metadata" property="HPGLOrigin">
                 <img src="/web/image/no-hpglicon.gif" width="34" height="13" border="0"/>
               </logic:notPresent>
               -->

               <!-- CGM ICON -->
               <logic:present name="metadata" property="CGMOrigin">
                <html:link href="/promote/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="CGMOrigin" title="Download CGM Image" styleClass="button">
                 <img src="/web/image/cgmicon.gif" width="28" height="13" border="0"/>
                </html:link>
               </logic:present>
               <!--
               <logic:notPresent name="metadata" property="CGMOrigin">
                 <img src="/web/image/no-cgmicon.gif" width="28" height="13" border="0"/>
               </logic:notPresent>
               -->
              </nobr>
             </td>

             <%int col=0;%>
             <logic:iterate id="field" name="metadata" property="values" type="java.lang.String">
             <td class="item">
              <nobr>
              <% if (0==col++) { %> 
               <b><bean:write name="field"/></b>
              <% } else {%>
               <bean:write name="field"/>
              <% } %>
             </nobr>
             </td>
             </logic:iterate>
             <bean:define id="v" name="metadata" property="verification"/>
             <td class="item" width="3" align="center">
              <select name="selectedVerifications">
               <logic:iterate id="verify" name="metadata" property="verificationOptions" type="java.lang.String">
                <% if (verify.equals(v)) { %>
                <option value="<%=verify%>" selected="true"><%=verify%>&nbsp;</option>
                <% } else { %>
                <option value="<%=verify%>"><%=verify%>&nbsp;</option>
                <% }%>
               </logic:iterate>
              </select>
             </td>
             <bean:define id="p" name="metadata" property="promoteTo"/>
             <td class="item" width="3" align="center">
              <select name="selectedReleaseLevels">
               <logic:iterate id="release" name="metadata" property="promotionLevels" type="java.lang.String">
                <% if (release.equals(p)) { %>
                <option value="<%=release%>" selected="true"><%=release%>&nbsp;</option>
                <% } else { %>
                <option value="<%=release%>"><%=release%>&nbsp;</option>
                <% }%>
               </logic:iterate>
              </select>
             </td>
             <bean:define id="v" name="metadata" property="imageType"/>
             <td class="item" width="3" align="center">
              <select name="selectedImageTypes">
               <logic:iterate id="viewable" name="metadata" property="imageTypes" type="java.lang.String">
                <% if (viewable.equals(v)) { %>
                <option value="<%=viewable%>" selected="true"><%=viewable%>&nbsp;</option>
                <% } else { %>
                <option value="<%=viewable%>"><%=viewable%>&nbsp;</option>
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
    <html:link href="#" onclick="javascript:sendEvent('processSelectedItems')" styleClass="button">Submit Files</html:link>
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
          <div align="left"><nobr>&nbsp;Intralink Files&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
             <td class="listHeader" width="24">&nbsp;</td>
             <td class="listHeader" width="10">&nbsp;</td>
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
             <td class="item">
             <nobr>
               <!-- PDF ICON -->
               <logic:present name="metadata" property="PDFOrigin">
                <html:link href="/promote/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="PDFOrigin" title="Download PDF Image" styleClass="button">
                 <img src="/web/image/pdficon.gif" width="14" height="13" border="0"/>
                </html:link>
               </logic:present>
               <!--
               <logic:notPresent name="metadata" property="PDFOrigin">
                 <img src="/web/image/no-pdficon.gif" width="14" height="13" border="0"/>
               </logic:notPresent>
               -->

               <!-- HPGL ICON -->
               <logic:present name="metadata" property="HPGLOrigin">
                <html:link href="/promote/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="HPGLOrigin" title="Download HPGL Image" styleClass="button">
                 <img src="/web/image/hpglicon.gif" width="34" height="13" border="0"/>
                </html:link>
               </logic:present>
               <!--
               <logic:notPresent name="metadata" property="HPGLOrigin">
                 <img src="/web/image/no-hpglicon.gif" width="34" height="13" border="0"/>
               </logic:notPresent>
               -->

               <!-- CGM ICON -->
               <logic:present name="metadata" property="CGMOrigin">
                <html:link href="/promote/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="CGMOrigin" title="Download CGM Image" styleClass="button">
                 <img src="/web/image/cgmicon.gif" width="28" height="13" border="0"/>
                </html:link>
               </logic:present>
               <!--
               <logic:notPresent name="metadata" property="CGMOrigin">
                 <img src="/web/image/no-cgmicon.gif" width="28" height="13" border="0"/>
               </logic:notPresent>
               -->
               </nobr>
             </td>

             <%int col=0;%>
             <logic:iterate id="field" name="metadata" property="values" type="java.lang.String">
             <td class="item">
              <nobr>
              <% if (0==col++) { %> 
               <b><bean:write name="field"/></b>
              <% } else { %>
               <bean:write name="field"/>
              <% } %>
              </nobr>
             </td>
             </logic:iterate>
             <logic:equal name="__member" property="role" value="admin" scope="session">
              <logic:present name="metadata" property="PDFOrigin">
              <td class="listHeader"><html:link href="/promote/index.do?event=clearPDF" paramId="ID" paramName="metadata" paramProperty="origin" title="Clear PDF" styleClass="cancelButton">x</html:link></td>
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
<!--
    <html:link href="#" onclick="javascript:sendCriteria('search')" styleClass="button">Search</html:link>
-->
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
