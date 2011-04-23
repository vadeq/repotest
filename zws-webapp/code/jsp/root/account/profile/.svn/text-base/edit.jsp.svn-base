<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/account/profile/index.do" focus="firstName">
<html:hidden property="ID"/>
<html:hidden property="event" value="save"/>
<html:hidden property="interactor" value="profile.edit"/>
<html:hidden property="role"/>

<% String title="Update Profile"; %>
<%@ include file="/web/include/header.jsp"%>
 <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="80%">
  <tr> 
    <td class="formTitle" colspan="2">&nbsp;Profile: <bean:write name="hiUser" property="username"/>&nbsp;</td>
	</tr>
	  <tr>
		<td class="prop" width="15%">role</td>
		<td class="value"><bean:write name="hiUser" property="role" /></td>
	  </tr>
	  <tr>
		<td class="prop" width="15%">first name</td>
		<td class="value"><html:text property="firstName" size="16" onkeypress="tabOver(this,event)"/></td>
	  </tr>
	  <tr>
		<td class="prop" width="15%">last name</td>
		<td class="value"><html:text property="lastName" size="16" onkeypress="tabOver(this,event)"/></td>
	  </tr>
	  <tr>
		<td class="prop" width="15%">email</td>
		<td class="value"><html:text property="email" size="16" onkeypress="tabOver(this,event)"/></td>
	  </tr>
	  <tr>
		<td class="prop" width="15%">phone</td>
		<td class="value"><html:text property="phone" size="16" onkeypress="autoSubmit(this,event)"/></td>
	  </tr>

</table>
<table>
  <tr class="formMenu">
    <td>
    <table border="0" cellpadding="1" cellspacing="1" width="100%">
        <tr>
          <td class="saveButton"><html:link href="javascript:send();" styleClass="saveButton"><img gif="save" alt="Save"/></html:link></td>
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
