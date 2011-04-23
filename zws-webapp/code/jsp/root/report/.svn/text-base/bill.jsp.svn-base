<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/report/index.do" >
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="report"/>
<SCRIPT Language="javascript">
function selectFolder(folderCode) {}
function selectLeaf(title, origin) { }
</SCRIPT>


<bean:define id="hiReport" name="hiReport" type="zws.hi.report.hiReport"/>
<table class="form" border="0" cellpadding="2" cellspacing="1" width="95%">
  <tr>
    <td class="formTitle" align="left"><nobr>&nbsp;Bill Of Materials &nbsp;&nbsp; <a href="index.do?event=downloadBill">xml</a> &nbsp;&nbsp; <a href="index.do?event=downloadCSV">csv</a></nobr></td>
  </tr>
 <tr>
    <td class="item"><%=hiReport.getBillView()%></td>
  </tr>
</table>

</html:form>

</body>
</html:html>
