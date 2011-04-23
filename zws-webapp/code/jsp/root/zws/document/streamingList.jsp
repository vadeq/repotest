<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/document/index.do" focus="criteria">
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="document-list"/>
<%@ include file="/web/include/plainHeader.jsp"%>

<table class="form" border="0" cellpadding="0" cellspacing="0" align="center" width="900">
  <tr>
    <td class="formTitle" align="left">
      <div align="left">
        &nbsp;Search:&nbsp;<html:text property="criteria" size="24" onkeypress="return autoSubmit(this, event)"/>
        Report:
        <html:select name="docList" property="activeReportName" styleClass="input" onkeypress="tabOver(this,event)">
         <html:options name="docList" property="reportNames"/>
        </html:select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <span align="right" >
          <html:link href="index.do?event=toggleStreaming" styleClass="setting-on" title="Toggle on/off data streaming">Streaming Data...</html:link>
        </span>
      </div>
    </td>
  </tr>
</table>
<table class="listItems" border="0" cellpadding="2" cellspacing="1" align="center" width="900">
 <tr>
  <td class="listHeader" width="150">&nbsp;Name&nbsp</td>
   <logic:iterate id="h" name="docList" property="visibleDisplayFields" type="com.zws.functor.report.DisplayField">
    <td width="150" class="listHeader">&nbsp;<bean:write name="h" property="name"/>&nbsp</td>
   </logic:iterate>
   <td class="listHeader">&nbsp;</td>
 </tr>
</table>

<logic:iterate id="doc" name="docList" property="items" type="com.zws.hi.document.hiDocument" >
 <logic:present name="doc" scope="page">
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" align="center" name="searchResults" id=searchResults width="900">
        <tr>
          <td class="item" width="150"><html:link href="/document/index.do?event=download" paramId="ID" paramName="doc" paramProperty="ID" styleClass="button"><bean:write name="doc" property="name"/></html:link></td>
          <logic:iterate id="att" name="doc" property="visibleAttributes" type="com.zws.util.KeyValue">
            <td class="item" width="150"><nobr><bean:write name="att" property="value"/></nobr></td>
          </logic:iterate>
          <td class="itemMenu"><html:link href="/document/info/index.do?nav=view" paramId="ID" paramName="doc" paramProperty="ID" target="_blank" styleClass="button">Info</html:link></td>
        </tr>
      </table>
 </logic:present>
</logic:iterate>

<br>
<p>
<center><%@ include file="/web/include/plainFooter.jsp"%></center>

<script language="JavaScript">
  //random number included to helps browsers which cache index.do for some reason
  function tidy(){ document.location.href="index.do?event=searchCompleteNotification&ran=" + Math.floor(Math.random()*88888888); }
  function delayedTidy(){ window.setTimeout('tidy()', 2000);  }
  delayedTidy();
</script>

</html:form>
</body>
</html:html>
