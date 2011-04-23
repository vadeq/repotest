<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/login.do" focus="username">
<html:hidden property="event" value="authenticate"/>
<html:hidden property="interactor" value="login"/>
<html:hidden property="interactor2" value="login"/>

<%@ include file="/web/include/application-name.jsp"%>
<%@ include file="/web/include/header.jsp"%>


<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">&nbsp;Login</td>
  </tr>
  <tr>
    <td>
      <table class="formProps" border="0" cellpadding="0" cellspacing="1" width="100%">
        <tr>
          <td>
          <table class="formProps" border="0" cellpadding="1" cellspacing="0" width="100%">
            <tr>
              <td class="prop">username</td>
              <td class="value"><html:text property="username" size="16" styleClass="input" onkeypress="tabOver(this, event);"/></td>
              <td valign="top" class="propError"><nobr><html:errors property="username"/><img src="/web/image/spacer.gif" height="1" width="1"/></nobr></td>
            </tr>
            <tr>
              <td class="prop">password</td>
              <td class="value"><html:password property="password" size="16" styleClass="input" onkeypress="autoSubmit(this, event);"/></td>
              <td valign="top" class="propError"><nobr><html:errors property="password"/><img src="/web/image/spacer.gif" height="1" width="1"/></nobr></td>
            </tr>
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
          <td class="editButton"><html:link href="#" onclick="send();" styleClass="okButton"><img gif="login"/></html:link></td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>
