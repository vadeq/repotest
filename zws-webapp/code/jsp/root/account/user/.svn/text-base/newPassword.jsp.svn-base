<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/account/user/newPassword.do" focus="newPassword">
<html:hidden property="ID"/>
<html:hidden property="event" value="setNewPassword"/>
<html:hidden property="interactor" value="profile.changePassword"/>

<% String title="New Password"; %>
<%@ include file="/web/include/header.jsp"%>
 <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="80%">
  <tr>
    <td class="formTitle" colspan="2">&nbsp;<bean:write name="hiUser" property="username"/> Password&nbsp;</td>
  </tr>
  <tr>
	<td class="prop" width="20%">new password</td>
	<td class="value"><html:password property="newPassword" size="16" onkeypress="tabOver(this,event)"/></td>
  </tr>
  <tr>
	<td class="prop" width="20%">confirm password</td>
	<td class="value"><html:password property="confirmationPassword" size="16" onkeypress="autoSubmit(this,event)"/></td>
  </tr>

</table>
<table>
  <tr class="formMenu">
    <td>
    <table border="0" cellpadding="1" cellspacing="0" width="100%">
        <tr>
          <td class="saveButton"><html:link href="javascript:send();" styleClass="saveButton"><img gif="save"/></html:link></td>
          <td class="cancelButton"><html:link href="item.do?nav=view" paramId="ID" paramName="hiUser" paramProperty="username" styleClass="cancelButton"><img gif="cancel"/></html:link></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>
