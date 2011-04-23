<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>


<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/publish/search.do">
<html:hidden property="event" value="searchCriteria"/>
<html:hidden property="ID" value=""/>

<%@ include file="/web/include/application-name.jsp"%>
<%@ include file="/web/include/header.jsp"%>


<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">&nbsp;Search</td>
  </tr>
  <tr>
    <td>
      <table class="formProps" border="0" cellpadding="0" cellspacing="1" width="100%">
        <tr>
          <td>
          <table class="formProps" border="0" cellpadding="1" cellspacing="0" width="100%">
            <tr>
              <td>Criteria:</td>
              <td><input type="text" name="criteria" value="test"/> </td>
            </tr>
            <tr>

           </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr class="formMenu">
    <td>
    <table border="0" cellpadding="1" cellspacing="0" width="100%">
        <tr>
          <td class="editButton"><html:link href="#" onclick="send();" styleClass="okButton">Search</html:link></td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>
