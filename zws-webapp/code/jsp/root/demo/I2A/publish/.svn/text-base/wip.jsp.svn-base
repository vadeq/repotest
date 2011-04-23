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


    </table>

   </td>
 </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>
</html:form>
</body>
</html:html>
