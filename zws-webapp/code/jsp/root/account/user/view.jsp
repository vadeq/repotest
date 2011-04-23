<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/account/user/index.do">
<html:hidden property="ID"/>
<html:hidden property="interactor" value="userview"/>
<html:hidden property="ID"/>

<% String title="Account Profile"; %>
<%@ include file="/web/include/header.jsp"%>
 <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="80%">
  <tr>
    <td class="formTitle" colspan="2">&nbsp;Profile: <bean:write name="hiUser" property="username"/>&nbsp;</td>
  </tr>
  <tr>
	<td class="prop" width="20%">role</td>
	<td class="value"><bean:write name="hiUser" property="role"/></td>
  </tr>
  <tr>
	<td class="prop" width="20%">first name</td>
	<td class="value"><bean:write name="hiUser" property="firstName"/></td>
  </tr>
  <tr>
	<td class="prop" width="20%">last name</td>
	<td class="value"><bean:write name="hiUser" property="lastName"/></td>
  </tr>
  <tr>
	<td class="prop" width="20%">email</td>
	<td class="value"><bean:write name="hiUser" property="email"/></td>
  </tr>
  <tr>
	<td class="prop" width="20%">phone</td>
	<td class="value"><bean:write name="hiUser" property="phone"/></td>
  </tr>
</table>
<table>
  <tr class="formMenu">
    <td>
     <table border="0" cellpadding="1" cellspacing="0" width="100%">
      <tr>
       <td class="editButton"><html:link href="item.do?nav=edit" paramId="ID" paramName="hiUser" paramProperty="username" styleClass="editButton"><img gif="edit"/></html:link></td>
       <td class="actionButton"><img src="/web/image/spacer.gif" width="8" border="0"/></td>
       <td class="editButton"><html:link href="item.do?nav=newPassword" paramId="ID" paramName="hiUser" paramProperty="username" styleClass="editButton"><img gif="new_password"/></html:link></td>
       <td class="actionButton"><img src="/web/image/spacer.gif" width="28" border="0"/></td>
       <td class="cancelButton"><html:link href="index.do" styleClass="actionButton"><img gif="close"/></html:link></td>
      </tr>
     </table>
    </td>
  </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>