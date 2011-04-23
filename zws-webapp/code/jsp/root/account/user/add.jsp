<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/account/user/add.do" focus="username">
<html:hidden property="event" value="add"/>
<html:hidden property="interactor" value="user.edit"/>

<% String title="New Account"; %>
<%@ include file="/web/include/header.jsp"%>
 <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="80%">
  <tr>
    <td class="formTitle" colspan="2">&nbsp;Define Profile&nbsp;</td>
  </tr>
	  <tr>
		<td class="prop" width="20%">role</td>
		<td class="value">
		  <html:select property="role" onkeypress="tabOver(this,event)">
			<html:option value="user">user</html:option> <!-- todo: dynamically get this from database -->
			<html:option value="engineer">engineer</html:option>
			<html:option value="admin">admin</html:option>
		  </html:select>&nbsp;<html:errors property="role"/>
		</td>
	  </tr>
	  <tr>
		<td class="prop" width="20%">username</td>
		<td class="value"><html:text property="username" size="16" onkeypress="tabOver(this,event)"/>&nbsp;<html:errors property="username"/></td>
	  </tr>
	  <tr>
		<td class="prop" width="20%">new password</td>
		<td class="value"><html:password property="newPassword" size="16" onkeypress="tabOver(this,event)"/>&nbsp;<html:errors property="newPassword"/></td>
	  </tr>
	  <tr>
		<td class="prop" width="20%">confirm password</td>
		<td class="value"><html:password property="confirmationPassword" size="16" onkeypress="tabOver(this,event)"/>&nbsp;<html:errors property="confirmationPassword"/></td>
	  </tr>
	  <tr>
		<td class="prop" width="20%">first name</td>
		<td class="value"><html:text property="firstName" size="16" onkeypress="tabOver(this,event)"/>&nbsp;<html:errors property="firstName"/></td>
	  </tr>
	  <tr>
		<td class="prop" width="20%">last name</td>
		<td class="value"><html:text property="lastName" size="16" onkeypress="tabOver(this,event)"/>&nbsp;<html:errors property="lastName"/></td>
	  </tr>
	  <tr>
		<td class="prop" width="20%">email</td>
		<td class="value"><html:text property="email" size="16" onkeypress="tabOver(this,event)"/>&nbsp;<html:errors property="email"/></td>
	  </tr>
	  <tr>
		<td class="prop" width="20%">phone</td>
		<td class="value"><html:text property="phone" size="16" onkeypress="autoSubmit(this,event)"/>&nbsp;<html:errors property="phone"/></td>
	  </tr>
</table>
<table>
  <tr class="formMenu">
   <td>
    <table border="0" cellpadding="1" cellspacing="0" width="100%">
     <tr>
      <td class="saveButton"><html:link href="javascript:send();" styleClass="saveButton"><img gif="save"/></html:link></td>
      <td class="cancelButton"><html:link href="index.do" paramId="ID" paramName="hiUser" paramProperty="username" styleClass="cancelButton"><img gif="cancel"/></html:link></td>
     </tr>
    </table>
   </td>
  </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>
