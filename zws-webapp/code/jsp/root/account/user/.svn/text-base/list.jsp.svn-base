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
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="user.list"/>

<% String title="Manage Accounts"; %>
<%@ include file="/web/include/header.jsp"%>
<table border="0" cellpadding="0" cellspacing="0" width="80%">
<tr><td>
<table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td class="formTitle">Account Profiles</td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
          <td class="listHeader">username</td>
          <td class="listHeader">name</td>
          <td class="listHeader">phone</td>
          <td class="listHeader">email</td>
          <td class="listHeader">role</td>
          <td class="listHeader">&nbsp;</td>
        </tr>
        <logic:iterate id="e" name="hiUserList" property="items" type="com.zws.domo.account.User">
        <bean:define id="userName" name="e" property="username"/>
        <tr class="item">
          <td class="item"><nobr><html:link href="item.do" paramId="ID" paramName="e" paramProperty="username" styleClass="button"><bean:write name="e" property="username"/></html:link></nobr></td>
          <td class="item"><nobr><bean:write name="e" property="firstName"/> <bean:write name="e" property="lastName"/></nobr></td>
          <td class="item"><nobr><bean:write name="e" property="phone"/></nobr></td>
          <td class="item"><nobr><bean:write name="e" property="email"/></nobr></td>
          <td class="item"><nobr>&nbsp;<html:link href="index.do?event=changeRole" paramId="ID" paramName="e" paramProperty="username" styleClass="button" title="change this user's role"><bean:write name="e" property="role"/></html:link>&nbsp;</nobr></td>
          <td class="itemIcon" width="1%"><a onclick="if(confirm('OK to delete <%=userName%>?')) return true; else return false;" href="index.do?event=delete&ID=<%=userName%>" class="deleteButton" title="Delete user - <%=userName%>"><img gif="itrash"/></a></td>
        </tr>
        </logic:iterate>
      </table>
    </td>
  </tr>
</table>
</td></tr>
  <tr class="formMenu">
   <td>
    <table border="0" cellpadding="1" cellspacing="0" width="100%">
     <tr>
      <td class="saveButton"><html:link href="add.do?nav=add" styleClass="button" title="add a new user"><img gif="add"/></html:link></td>
     </tr>
    </table>
   </td>
  </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>











