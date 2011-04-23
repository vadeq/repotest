<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/synchronizer/index.do" focus="name">
<html:hidden property="ID"/>
<html:hidden property="event" value="none"/>
<html:hidden property="interactor" value="synchronization.service"/>

<%@ include file="/web/include/header.jsp"%>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">Synchronization Log Util</td>
  </tr>
  <tr>
    <td>
      <table class="formProps" border="0" cellpadding="0" cellspacing="1" width="100%">
        <tr>
          <td>
            <table class="formProps" border="0" cellpadding="2" cellspacing="0" width="100%">
              <tr>
                <td class="prop">Name:</td>
                <td class="value"><html:text property="name" /></td>
                <td class="value"><html:link href ="#" onclick="javascript:sendEvent('purgeName');" styleClass="cancelButton">purge</html:link></td>
              </tr>
              <tr>
                <td class="prop">Unique ID:</td>
                <td class="value"><html:text property="uniqueID"/></td>
                <td class="value"><html:link href ="#" onclick="javascript:sendEvent('purgeUID');" styleClass="cancelButton">purge</html:link></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>
