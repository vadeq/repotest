<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/intralink/baseline/add.do"  focus="name">
<html:hidden property="event" value="next"/>
<html:hidden property="interactor" value="hiBaseline"/>

<script language="JavaScript">
	function selectFolder(folderPath) {
     	document.forms[0].location.value = folderPath;
      if (15<folderPath.length()) document.forms[0].location.size = folderPath.length()+3;
    }
</script>


<%@ include file="/web/include/header.jsp"%>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">New Baseline</td>
  </tr>
  <tr>
    <td>
      <table class="formProps" border="0" cellpadding="0" cellspacing="1" width="100%">
        <tr>
          <td>
            <table class="formProps" border="0" cellpadding="2" cellspacing="0" width="100%">
              <tr>
                <td class="prop">name</td>
                <td class="value"><html:text property="name" size="16" onkeypress="tabOver(this,event)"/></td>
                <td class="propError">&nbsp;<html:errors property="name"/></td>
              </tr>
              <tr>
                <td class="prop" valign="top">location</td>
                <td class="value" rowspan="2">
<html:text property="location" size="16" onkeypress="autosubmit(this,event)" readonly="true"/><br>
<jsp:useBean id="hiBaseline" type="zws.hi.intralink.baseline.hiBaseline" scope="session"/>
<%=hiBaseline.getFolderView()%>
</td>
                <td class="propError" valign="top">&nbsp;<html:errors property="location"/></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr class="formMenu">
    <td>
    <table border="0" cellpadding="3" cellspacing="0" width="100%">
        <tr>
          <td class="saveButton"><html:link href="javascript:next()" styleClass="saveButton">add files</html:link></td>
          <td class="cancelButton"><html:link href="index.do" styleClass="cancelButton">cancel</html:link></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</html:form>
</body>
</html:html>
