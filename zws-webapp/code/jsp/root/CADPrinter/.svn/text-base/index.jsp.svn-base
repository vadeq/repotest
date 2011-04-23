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
<html:form action="/CADPrinter/index.do">
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
<logic:present name="hiProcOnFly" property="printQueue">
refresh=1;
</logic:present>
<logic:present name="hiProcOnFly" property="snapshotQueue">
refresh=1;
</logic:present>
//--></script>

<table border="0" cellpadding="2" cellspacing="5">
 <tr>
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
               <bean:define id="PDFOrigin" name="metadata" property="PDFOrigin" type="zws.origin.Origin"/>
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
    <html:link href="#" onclick="javascript:sendEvent('printSelectedItems')" styleClass="button"><img gif="print"/></html:link>
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
		     <logic:equal name="hiProcOnFly" property="printingIsEnabled" value="true" scope="session">
             <td class="listHeader" width="24">&nbsp;</td>
             </logic:equal>
             <td class="listHeader">&nbsp;</td>
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
            <bean:define id="fileType" name="metadata" property="fileType" type="java.lang.String"/>
            <tr>
		     <logic:equal name="hiProcOnFly" property="printingIsEnabled" value="true" scope="session">
             <td class="item" width="14" align="center">
              <input type="checkbox" onClick='pick("<bean:write name="metadata" property="origin"/>")'/>
             </td>
             </logic:equal>
             <td class="item"><nobr>
              <% if ("drw".equalsIgnoreCase(fileType)) { %> 
               <logic:present name="metadata" property="PDFOrigin">
                <html:link href="/CADPrinter/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="PDFOrigin" title="Download PDF" styleClass="button">
                 <img gif="idownload_pdf"/>
                </html:link>
               </logic:present>
               <logic:notPresent name="metadata" property="PDFOrigin">
                <html:link href="/CADPrinter/index.do?event=generatePDFImageSnapshot" paramId="ID" paramName="metadata" paramProperty="origin" title="Generate PDF" styleClass="okButton">
                 <img gif="igenerate_pdf"/>
                </html:link>
               </logic:notPresent>

               <logic:present name="metadata" property="DWGOrigin">
                <html:link href="/CADPrinter/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="DWGOrigin" title="Download DWG" styleClass="button">
                 <img gif="idownload_dwg"/>
                </html:link>
               </logic:present>
               <logic:notPresent name="metadata" property="DWGOrigin">
                <html:link href="/CADPrinter/index.do?event=generateDWGImageSnapshot" paramId="ID" paramName="metadata" paramProperty="origin" title="Generate DWG" styleClass="okButton">
                 <img gif="igenerate_dwg"/>
                </html:link>
               </logic:notPresent>

               <logic:present name="metadata" property="DXFOrigin">
                <html:link href="/CADPrinter/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="DXFOrigin" title="Download DXF" styleClass="button">
                 <img gif="idownload_dxf"/>
                </html:link>
               </logic:present>
               <logic:notPresent name="metadata" property="DXFOrigin">
                <html:link href="/CADPrinter/index.do?event=generateDXFImageSnapshot" paramId="ID" paramName="metadata" paramProperty="origin" title="Generate DXF" styleClass="okButton">
                 <img gif="igenerate_dxf"/>
                </html:link>
               </logic:notPresent>

               <logic:present name="metadata" property="CGMOrigin">
                <html:link href="/CADPrinter/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="CGMOrigin" title="Download CGM" styleClass="button">
                 <img gif="idownload_cgm"/>
                </html:link>
               </logic:present>
               <logic:notPresent name="metadata" property="CGMOrigin">
                <html:link href="/CADPrinter/index.do?event=generateCGMImageSnapshot" paramId="ID" paramName="metadata" paramProperty="origin" title="Generate CGM" styleClass="okButton">
                 <img gif="igenerate_cgm"/>
                </html:link>
               </logic:notPresent>

               <logic:present name="metadata" property="HPGOrigin">
                <html:link href="/CADPrinter/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="HPGOrigin" title="Download HPGL" styleClass="button">
                 <img gif="idownload_hpg"/>
                </html:link>
               </logic:present>
               <logic:notPresent name="metadata" property="HPGOrigin">
                <html:link href="/CADPrinter/index.do?event=generateHPGImageSnapshot" paramId="ID" paramName="metadata" paramProperty="origin" title="Generate HPGL" styleClass="okButton">
                 <img gif="igenerate_hpg"/>
                </html:link>
               </logic:notPresent>

              <% } else if ("prt".equalsIgnoreCase(fileType) || "asm".equalsIgnoreCase(fileType)) { %> 
               <logic:present name="metadata" property="IGSOrigin">
                <html:link href="/CADPrinter/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="IGSOrigin" title="Download IGES" styleClass="button">
                 <img gif="idownload_igs"/>
                </html:link>
               </logic:present>
               <logic:notPresent name="metadata" property="IGSOrigin">
                <html:link href="/CADPrinter/index.do?event=generateIGSImageSnapshot" paramId="ID" paramName="metadata" paramProperty="origin" title="Generate IGES" styleClass="okButton">
                 <img gif="igenerate_igs"/>
                </html:link>
               </logic:notPresent>

               <logic:present name="metadata" property="STPOrigin">
                <html:link href="/CADPrinter/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="STPOrigin" title="Download Step" styleClass="button">
                 <img gif="idownload_stp"/>
                </html:link>
               </logic:present>
               <logic:notPresent name="metadata" property="STPOrigin">
                <html:link href="/CADPrinter/index.do?event=generateSTPImageSnapshot" paramId="ID" paramName="metadata" paramProperty="origin" title="Generate Step" styleClass="okButton">
                 <img gif="igenerate_stp"/>
                </html:link>
               </logic:notPresent>

               <logic:present name="metadata" property="NEUOrigin">
                <html:link href="/CADPrinter/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="NEUOrigin" title="Download Neutral" styleClass="button">
                 <img gif="idownload_neu"/>
                </html:link>
               </logic:present>
               <logic:notPresent name="metadata" property="NEUOrigin">
                <html:link href="/CADPrinter/index.do?event=generateNEUImageSnapshot" paramId="ID" paramName="metadata" paramProperty="origin" title="Generate Neutral" styleClass="okButton">
                 <img gif="igenerate_neu"/>
                </html:link>
               </logic:notPresent>
<!--
need to have a coordinate system in order for pro to generate IDF properly
               <logic:present name="metadata" property="IDFOrigin">
                <html:link href="/CADPrinter/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="IDFOrigin" title="Download IDF" styleClass="button">
                 <img gif="idownload_idf"/>
                </html:link>
               </logic:present>
               <logic:notPresent name="metadata" property="IDFOrigin">
                <html:link href="/CADPrinter/index.do?event=generateIDFImageSnapshot" paramId="ID" paramName="metadata" paramProperty="origin" title="Generate IDF" styleClass="okButton">
                 <img gif="igenerate_idf"/>
                </html:link>
               </logic:notPresent>
-->
              <% } %>
             </nobr></td>

             <%int col=0;%>
             <logic:iterate id="field" name="metadata" property="values" type="java.lang.String">
             <td class="item">
             <nobr>&nbsp;
              <% if (0==col++)   { %>
               &nbsp;
               <a href="/report/index.do?event=download&ID=<%=java.net.URLEncoder.encode(origin.toString(), "UTF-8")%>" class="button"> 
                 <bean:write name="field"/>
               </a>
              <% } else {%>
               &nbsp;<bean:write name="field"/>
              <% }%>
             </nobr>
             </td>
             </logic:iterate>
             <logic:equal name="__member" property="role" value="admin" scope="session">
              <td class="listHeader">
              <logic:present name="metadata" property="imageOrigins">
               <html:link href="/CADPrinter/index.do?event=clearLinks" paramId="ID" paramName="metadata" paramProperty="origin" title="Clear Images" styleClass="cancelButton"><img gif="iremove"/></html:link>
              </logic:present>
              <logic:notPresent name="metadata" property="imageOrigins">
              &nbsp;
              </logic:notPresent>
              </td>
             </logic:equal>
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

  <td width="33%" valign="top">

<logic:equal name="hiProcOnFly" property="printingIsEnabled" value="true" scope="session">
 
<!-- Print Queue.. -->
<table border="0" cellpadding="2" cellspacing="5" width="100%">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Print Queue&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
             <td class="listHeader" width="2%"><nobr>&nbsp;#</nobr><br></td>
             <td class="listHeader" width="49%"><nobr>File</nobr><br></td>
             <td class="listHeader" width="49%"><nobr>Printer</nobr><br></td>
            </tr>
            <logic:present name="hiProcOnFly" property="printQueue">
            <logic:iterate id="metadata" name="hiProcOnFly" property="printQueue" type="zws.hi.intralink.proconfly.PDFMetadataAdapter">
            <tr>
             <td class="item"><nobr><b><bean:write name="metadata" property="printQuantity"/></b></nobr></td>
             <td class="item">
              <nobr><b>
               <logic:present name="metadata" property="PDFOrigin">
                <html:link href="/CADPrinter/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="PDFOrigin" title="Download PDF" styleClass="button">
                 <img src="/web/image/pdficon.gif" width="14" height="13" border="0"/> <bean:write name="metadata" property="name"/>
                </html:link>
               </logic:present>
               <logic:notPresent name="metadata" property="PDFOrigin">
                <html:link href="#" onclick="javascript:sendEvent('updateQueue')" title="Check for Updates" styleClass="okButton">
                 <img src="/web/image/no-pdficon.gif" width="14" height="13" border="0"/> <bean:write name="metadata" property="name"/>
                </html:link>
               </logic:notPresent>
              </b>&nbsp;</nobr>
             </td>
             <td class="item"><nobr><b><bean:write name="metadata" property="printerName"/></b></nobr></td>
            </tr>
            </logic:iterate>
            </logic:present>
            <logic:notPresent name="hiProcOnFly" property="printQueue">
            <tr>
             <td colspan="3" class="item"><center><nobr>&nbsp;</nobr></center></td>
            </tr>
            </logic:notPresent>
          </table>
        </td>
      </tr>
    </table>
  </td>
 </tr>
</table>
<!-- ..Print Queue -->

<!-- Print Log.. -->
<table border="0" cellpadding="2" cellspacing="5" width="100%">
 <tr>
  <td valign="top">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Print Log&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
             <td class="listHeader" width="2%"><nobr>&nbsp;#</nobr><br></td>
             <td class="listHeader" width="49%"><nobr>File</nobr><br></td>
             <td class="listHeader" width="49%"><nobr>Printer</nobr><br></td>
            </tr>
            <logic:present name="hiProcOnFly" property="printLog">
            <logic:iterate id="metadata" name="hiProcOnFly" property="printLog" type="zws.hi.intralink.proconfly.PDFMetadataAdapter">
            <tr>
             <td class="item"><nobr><b><bean:write name="metadata" property="printQuantity"/></b></nobr></td>
             <td class="item"><nobr><b>
               <logic:present name="metadata" property="PDFOrigin">
                <html:link href="/CADPrinter/index.do?event=download" paramId="ID" paramName="metadata" paramProperty="PDFOrigin" title="Download PDF" styleClass="button">
                 <img src="/web/image/pdficon.gif" width="14" height="13" border="0"/> <bean:write name="metadata" property="PDFName"/>
                </html:link>
               </logic:present>
               <logic:notPresent name="metadata" property="PDFOrigin">
                <img src="/web/image/no-pdficon.gif" width="14" height="13" border="0"/> <S><bean:write name="metadata" property="name"/></S>
               </logic:notPresent>
              </b>&nbsp;</nobr></td>
             <td class="item"><nobr><b><bean:write name="metadata" property="printerName"/></b></nobr></td>
            </tr>
            </logic:iterate>
            </logic:present>
            <logic:notPresent name="hiProcOnFly" property="printLog">
            <tr>
             <td colspan="3" class="item"><center><nobr><b>No Files Printed</b></nobr></center></td>
            </tr>
            </logic:notPresent>
          </table>
        </td>
      </tr>
    </table>
  </td>
 </tr>
</table>
<!-- ..Print Log -->
 </logic:equal>

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
            <logic:iterate id="pair" name="hiProcOnFly" property="snapshotQueue" type="zws.util.Pair">
            <bean:define id="origin" name="pair" property="object0" type="zws.origin.Origin"/>
            <bean:define id="type" name="pair" property="object1" type="java.lang.String"/>
            <tr>
             <td class="item" valign="bottom"><nobr><b>
               <html:link href="#" onclick="javascript:sendEvent('updateQueue')" title="Check for Updates" styleClass="okButton">
                <img gif="igenerate_<%=type%>" valing="bottom"/> <bean:write name="origin" property="uniqueIDDisplay"/> : <bean:write name="origin" property="datasourceName"/>
               </html:link>
              </b>&nbsp;
            </nobr></td>
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
  <tr class="formMenu">
   <td>
    <table border="0" cellpadding="1" cellspacing="0" width="100%">
     <tr>
      <td class="editButton"><html:link href="#" onclick="javascript:sendEvent('updateQueue')" title="Check for Updates" styleClass="okButton"><img gif="update" alt="Check for Updates"/></html:link></td>
     </tr>
    </table>
   </td>
 </tr>
    </table>
  </td>
 </tr>
</table>
<!-- ..Snapshot Queue -->
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
