<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>
<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/demo/I2A/publish/index.do">
<html:hidden property="event"/>
<html:hidden property="eventKey"/>

<%@ include file="/web/include/interactor.params"%>

<% String title="CAD Release Management"; %>
<%@ include file="/web/include/header.jsp"%>


<table border="0" cellpadding="0" cellspacing="0" width="88%">
 <tr>
  <td class="item" align="left">
  <table>
   <tr valign="top">
    <td  valign="top" align="left">

    <!-- CAD Releases -->
    <table class="form" border="0" cellpadding="2" cellspacing="1">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Publishing CAD Releases&nbsp;</nobr></div>
        </td>
      </tr>
            <tr>
              <td class="listHeader" width="20%"><nobr>&nbsp;CAD Release&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="wip.jsp" class="button">
             <font size="2">Publish Work In Progress</font></a>&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="prototype.jsp" class="button">
             <font size="2">Prototype Releases</font></a>&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="pilot.jsp" class="button">
             <font size="2">Pilot Releases</font></a>&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="active.jsp" class="button">
             <font size="2">Release For Volume Production</font></a>&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="inactive.jsp" class="button">
             <font size="2">Post Production</font></a>&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="library.jsp" class="button">
             <font size="2">Publish Standard Library Components</font></a>&nbsp;</nobr></td>
            </tr>
      </table>
    </td>
    <td  valign="top" align="left">


    <!-- Policy For Publishing -->
    <table class="form" border="0" cellpadding="0" cellspacing="0" align="left">
      <tr>
        <td class="formTitle">
          <div align="left"><nobr>&nbsp;
          Policy For Publishing<img src="/web/image/spacer.gif" width="15" height="2"/>
          <html:link href="/demo/I2A/ilinkMapping/index.do" styleClass="formTitleButton">Mappings</html:link><img src="/web/image/spacer.gif" width="15" height="2"/>
          <html:link href="/demo/I2A/excludeList/index.do" styleClass="formTitleButton">Exclude</html:link>
          </nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
              <td class="listHeader"><nobr>&nbsp;Repository&nbsp;</nobr></td>
              <td class="listHeader"><nobr>&nbsp;Type&nbsp;</nobr></td>
              <td class="listHeader"><nobr>&nbsp;URL&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr><b>&nbsp;CAD Source&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;<b>Intralink</b>&nbsp;</nobr></td>
             <td class="item"><nobr>&nbsp;<a href="/" class="okButton" target="_blank"/>zero-888.zerowait-state.com</a>&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr><b>&nbsp;Destination&nbsp;<b></nobr></td>
             <td class="item"><nobr>&nbsp;<b>Agile</b>&nbsp;</nobr></td>
             <td class="item"><nobr>&nbsp;<a href="http://frapp006.agilesoft.com/Agile/PLMServlet" class="okButton" target="_blank"/>frapp006.agilesoft.com</a>&nbsp;</nobr></td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="item" align="left">
          <div align="left"><img src="/web/image/spacer.gif" width="15" height="8"/></div>
        </td>
      </tr>
    </table>

    </td>
   </tr>
  </table>
  </td>
 </tr>
 <tr>
   <td class="item" align="left">
   <hr>
   </td>
 </tr>
 <tr>
   <td class="item" align="left">
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">

<!-- Publish Work In Progress -->
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Publish Work In Progress&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
              <td class="listHeader" width="20%"><nobr>&nbsp;Action&nbsp;</nobr></td>
              <td class="listHeader" width="21%"><nobr>&nbsp;Description&nbsp;</nobr></td>
              <td class="listHeader"><nobr>&nbsp;Activity&nbsp;</nobr></td>
              <td class="listHeader" width="1%"><nobr>&nbsp;Policy&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="javascript:sendEventKey('initPublishWIP','publishWIP')" class="button">
             <font size="2">Publish Work In Progress</font></a>&nbsp;</nobr></td>
             <td class="item"><nobr><b>&nbsp;Publish CAD design&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;Creates CAD Part and CAD Document structures in agile as <i>Preliminary</i>.&nbsp;</nobr></td>
             <td class="listHeader"><nobr>&nbsp;<a href="#" class="cancelButton">view</a>&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="javascript:sendEventKey('initUpdateWIP','updateWIP')" class="button">
             <font size="2">Update Work In Progress</font></a>&nbsp;</nobr></td>
             <td class="item"><nobr><b>&nbsp;Update CAD design&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;Updates <i>Preliminary</i> CAD Part and CAD Document structures that have already been published.&nbsp;</nobr></td>
             <td class="listHeader"><nobr>&nbsp;<a href="#" class="cancelButton">view</a>&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="javascript:sendEventKey('initReplaceWIP','replaceWIP')" class="button">
             <font size="2">Replace Work In Progress</font></a>&nbsp;</nobr></td>
             <td class="item"><nobr><b>&nbsp;Change Form Fit or Function&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;Establishes a new part number (<i>Preliminary</i>). All WIP references to old part number are updated in bulk.&nbsp;</nobr></td>
             <td class="listHeader"><nobr>&nbsp;<a href="#" class="cancelButton">view</a>&nbsp;</nobr></td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="item" align="left">
          <div align="left"><img src="/web/image/spacer.gif" width="15" height="28"/></div>
        </td>
      </tr>



<!-- Pre-Production Releases -->

<!-- Prototype Releases -->
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Prototype Releases (ECO)&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
              <td class="listHeader" width="20%"><nobr>&nbsp;Action&nbsp;</nobr></td>
              <td class="listHeader" width="21%"><nobr>&nbsp;Description&nbsp;</nobr></td>
              <td class="listHeader"><nobr>&nbsp;Activity&nbsp;</nobr></td>
              <td class="listHeader" width="1%"><nobr>&nbsp;Policy&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="javascript:sendEventKey('initRelease2Prototype','release2prototype')" class="button">
             <font size="2">Release For Prototyping</font></a>&nbsp;</nobr></td>
             <td class="item"><nobr><b>&nbsp;Promotes WIP design to <i>Un-Released</i>&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;Adds WIP design structure to an ECO. Life Cycle Phase set to <i>Un-Released</i>.</nobr></td>
             <td class="listHeader"><nobr>&nbsp;<a href="#" class="cancelButton">view</a>&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="javascript:sendEventKey('initPrototypeChange','prototypeChange')" class="button">
             <font size="2">Change Prototype Design</font></a>&nbsp;</nobr></td>
             <td class="item"><nobr><b>&nbsp;Change a prototype design&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;Adds an prototype design structure to an ECO and redlines changes.</nobr></td>
             <td class="listHeader"><nobr>&nbsp;<a href="#" class="cancelButton">view</a>&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="javascript:sendEventKey('initProductionReplace','prototypeReplace')" class="button">
             <font size="2">Replace Prototype Design</font></a>&nbsp;</nobr></td>
             <td class="item"><nobr><b>&nbsp;Change Form Fit or Function&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;Establishes and redlines a new part number (<i>Un-Released</i>). Red-lines all references where old part was used.&nbsp;</nobr></td>
             <td class="listHeader"><nobr>&nbsp;<a href="#" class="cancelButton">view</a>&nbsp;</nobr></td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="item" align="left">
          <div align="left"><img src="/web/image/spacer.gif" width="15" height="28"/></div>
        </td>
      </tr>


<!-- Pilot Releases -->
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Pilot Releases (ECO)&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
              <td class="listHeader" width="20%"><nobr>&nbsp;Action&nbsp;</nobr></td>
              <td class="listHeader" width="21%"><nobr>&nbsp;Description&nbsp;</nobr></td>
              <td class="listHeader"><nobr>&nbsp;Activity&nbsp;</nobr></td>
              <td class="listHeader" width="1%"><nobr>&nbsp;Policy&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="javascript:sendEventKey('initRelease2Pilot','release2pilot')" class="button">
             <font size="2">Release For Pilot Production</font></a>&nbsp;</nobr></td>
             <td class="item"><nobr><b>&nbsp;Promotes design to <i>Pre-Released</i>&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;Adds WIP or Un-Released design structure to ECO and redlines changes. Life Cycle Phase set to <i>Pre-Released</i>.</nobr></td>
             <td class="listHeader"><nobr>&nbsp;<a href="#" class="cancelButton">view</a>&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="javascript:sendEventKey('initPilotChange','pilotChange')" class="button">
             <font size="2">Change Pilot Design</font></a>&nbsp;</nobr></td>
             <td class="item"><nobr><b>&nbsp;Change a pilot design&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;Adds an pilot design structure to an ECO and redlines changes.</nobr></td>
             <td class="listHeader"><nobr>&nbsp;<a href="#" class="cancelButton">view</a>&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="javascript:sendEventKey('initPilotReplace','pilotReplace')" class="button">
             <font size="2">Replace Prototype Design</font></a>&nbsp;</nobr></td>
             <td class="item"><nobr><b>&nbsp;Change Form Fit or Function&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;Establishes and redlines a new part number (<i>Pre-Released</i>). Redlines all references where old part was used.&nbsp;</nobr></td>
             <td class="listHeader"><nobr>&nbsp;<a href="#" class="cancelButton">view</a>&nbsp;</nobr></td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="item" align="left">
          <div align="left"><img src="/web/image/spacer.gif" width="15" height="28"/></div>
        </td>
      </tr>



<!-- Release for Production -->
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Release For Volume Production (ECO)&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
              <td class="listHeader" width="20%"><nobr>&nbsp;Action&nbsp;</nobr></td>
              <td class="listHeader" width="21%"><nobr>&nbsp;Description&nbsp;</nobr></td>
              <td class="listHeader"><nobr>&nbsp;Activity&nbsp;</nobr></td>
              <td class="listHeader" width="1%"><nobr>&nbsp;Policy&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="javascript:sendEventKey('initRelease2Production','release2production')" class="button">
             <font size="2">Release For Production</font></a>&nbsp;</nobr></td>
             <td class="item"><nobr><b>&nbsp;Promote design to <i>Active</i>&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;Adds design structure to an ECO and redlines changes. Life Cycle Phase set to <i>Active</i>.</nobr></td>
             <td class="listHeader"><nobr>&nbsp;<a href="#" class="cancelButton">view</a>&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="javascript:sendEventKey('initProductionChange','productionChange')" class="button">
             <font size="2">Change Active Design</font></a>&nbsp;</nobr></td>
             <td class="item"><nobr><b>&nbsp;Change a production design&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;Adds an Active design structure to an ECO and redlines changes.</nobr></td>
             <td class="listHeader"><nobr>&nbsp;<a href="#" class="cancelButton">view</a>&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="javascript:sendEventKey('initProductionReplace','productionReplace')" class="button">
             <font size="2">Replace Active Design</font></a>&nbsp;</nobr></td>
             <td class="item"><nobr><b>&nbsp;Change Form Fit or Function&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;Establishes and redlines a new part number (<i>Active</i>). Red-lines all references where old part was used.&nbsp;</nobr></td>
             <td class="listHeader"><nobr>&nbsp;<a href="#" class="cancelButton">view</a>&nbsp;</nobr></td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="item" align="left">
          <div align="left"><img src="/web/image/spacer.gif" width="15" height="28"/></div>
        </td>
      </tr>


<!-- Post Production -->
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Post Production (ECO)&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
              <td class="listHeader" width="20%"><nobr>&nbsp;Action&nbsp;</nobr></td>
              <td class="listHeader" width="21%"><nobr>&nbsp;Description&nbsp;</nobr></td>
              <td class="listHeader"><nobr>&nbsp;Activity&nbsp;</nobr></td>
              <td class="listHeader" width="1%"><nobr>&nbsp;Policy&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="javascript:sendEventKey('initDeactivate','deactivate')" class="button">
             <font size="2">Deactivate Design</font></a>&nbsp;</nobr></td>
             <td class="item"><nobr><b>&nbsp;Remove a design from production&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;Adds design (not structure) to an ECO. Life Cycle Phase set to <i>Inactive</i>.</nobr></td>
             <td class="listHeader"><nobr>&nbsp;<a href="#" class="cancelButton">view</a>&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="javascript:sendEventKey('initMarkObsolete','markObsolete')" class="button">
             <font size="2">Mark Obsolete</font></a>&nbsp;</nobr></td>
             <td class="item"><nobr><b>&nbsp;Remove availability of a design&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;Adds design (not structure) to an ECO. Life Cycle Phase set to <i>Obsolete</i>.</nobr></td>
             <td class="listHeader"><nobr>&nbsp;<a href="#" class="cancelButton">view</a>&nbsp;</nobr></td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="item" align="left">
          <div align="left"><img src="/web/image/spacer.gif" width="15" height="28"/></div>
        </td>
      </tr>



<!-- Standard Library -->
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Publish Standard Library Components&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td>
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
              <td class="listHeader" width="20%"><nobr>&nbsp;Action&nbsp;</nobr></td>
              <td class="listHeader" width="21%"><nobr>&nbsp;Description&nbsp;</nobr></td>
              <td class="listHeader"><nobr>&nbsp;Activity&nbsp;</nobr></td>
              <td class="listHeader" width="1%"><nobr>&nbsp;Policy&nbsp;</nobr></td>
            </tr>
            <tr>
             <td class="item"><nobr>&nbsp;<a href="javascript:sendEventKey('initPublishLibrary','publishLibrary');" class="button">
             <font size="2">Publish Standard Library</font></a>&nbsp;</nobr></td>
             <td class="item"><nobr><b>&nbsp;Release Standard Library Components&nbsp;</b></nobr></td>
             <td class="item"><nobr>&nbsp;Publishes updated or new Standard Library Components to Agile.</nobr></td>
             <td class="listHeader"><nobr>&nbsp;<a href="#" class="cancelButton">view</a>&nbsp;</nobr></td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="item" align="left">
          <div align="left"><img src="/web/image/spacer.gif" width="15" height="28"/></div>
        </td>
      </tr>

    </table>

   </td>
 </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>
</html:form>
</body>
</html:html>
