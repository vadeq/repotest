<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/intralink/workspace/index.do">
<html:hidden property="event"/>

<%@ include file="/web/include/interactor.params"%>

<% String title="Personal Workspaces"; %>
<%@ include file="/web/include/header.jsp"%>


<bean:define id="ws" name="hiPersonalWorkspace" property="selectedWorkspace" type="zws.data.workspace.Workspace"/>
<bean:define id="wsName" name="ws" property="name"/>
<bean:define id="item" name="hiPersonalWorkspace" property="selectedItem" type="zws.data.workspace.IlinkWorkspaceItem"/>

<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle"><bean:write name="item" property="name"/>&nbsp;</td>
  </tr>
  <tr>
    <td>
      <table class="formProps" border="0" cellpadding="0" cellspacing="1" width="100%">
        <tr>
          <td>
            <table class="formProps" border="0" cellpadding="4" cellspacing="0" width="100%">
              <tr>
                <td class="prop">Branch</td>
                <td class="value"><bean:write name="item" property="branch"/></td>
              </tr>
              <tr>
                <td class="prop">Revision</td>
                <td class="value"><bean:write name="item" property="revision"/></td>
              </tr>
              <tr>
                <td class="prop">Version</td>
                <td class="value"><bean:write name="item" property="version"/></td>
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
          <td class="editButton"><html:link href="index.do?event=save" styleClass="actionButton"><img gif="save" alt="Save changes"/></html:link></td>
          <td class="actionButton"><html:link href="index.do?nav=view-item" styleClass="actionButton"><img gif="cancel" alt="Cancel changes"/></html:link></td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<%@ include file="/web/include/footer.jsp"%>
</html:form>
</body>
</html:html>
