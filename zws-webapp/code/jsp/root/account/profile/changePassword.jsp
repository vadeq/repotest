<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/account/profile/changePassword.do" focus="password">
<html:hidden property="ID"/>
<html:hidden property="event" value="changePassword"/>
<html:hidden property="interactor" value="profile.changePassword"/>

<% String title="Change Password"; %>
<%@ include file="/web/include/header.jsp"%>
 <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="80%">
  <tr> 
    <td class="formTitle" colspan="2">&nbsp;<bean:write name="hiUser" property="username"/>&nbsp; Password&nbsp;</td>
  </tr>
	  <tr>
		<td class="prop" width="15%">current password</td>
		<td class="value"><html:password property="password" size="16"  onkeypress="tabOver(this,event)"/></td>
	  </tr>
	  <tr>
		<td class="prop" width="15%">new password</td>
		<td class="value"><html:password property="newPassword" size="16" onkeypress="tabOver(this,event)"/></td>
	  </tr>
	  <tr>
		<td class="prop" width="15%">confirm password</td>
		<td class="value"><html:password property="confirmationPassword" size="16" onkeypress="autoSubmit(this,event)"/></td>
  </tr>
</table>
<table>
  <tr class="formMenu">
    <td>
    <table border="0" cellpadding="1" cellspacing="0" width="100%">
        <tr>
          <td class="saveButton"><html:link href="javascript:send();" styleClass="saveButton"><img gif="save" alt="Change Password"/></html:link></td>
          <td class="cancelButton"><html:link href="index.do?nav=view" styleClass="cancelButton"><img gif="cancel" alt="Cancel"/></html:link></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>
