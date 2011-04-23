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
<bean:define id="item" name="hiPersonalWorkspace" property="selectedItem" type="zws.hi.intralink.workspace.IlinkWorkspaceItemAdapter"/>

<table border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top">

<!-- System attributes -->
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle"><bean:write name="item" property="name"/>&nbsp;</td>
  </tr>
  <tr>
    <td>
      <table class="formProps" border="0" cellpadding="2" cellspacing="1" width="100%">
       <tr>
        <td class="prop">Configuration</td>
        <td class="value"><nobr>
         <bean:write name="item" property="branch"/> <bean:write name="item" property="revision"/>.<bean:write name="item" property="version"/>
        </nobr></td>
       </tr>
       <tr>
        <td class="prop">Release Level</td>
        <td class="value"><nobr>
         <bean:write name="item" property="release"/>
        </nobr></td>
       </tr>
       <tr>
        <td class="prop">Author</td>
        <td class="value"><nobr>
         <bean:write name="item" property="createdBy"/>
        </nobr></td>
       </tr>
       <tr>
        <td class="prop">Date</td>
        <td class="value"><nobr>
         <bean:write name="item" property="createdOn"/>
        </nobr></td>
       </tr>
       <tr>
        <td class="prop">Folder</td>
        <td class="value"><nobr>
         <bean:write name="item" property="folder"/>
        </nobr></td>
       </tr>
      </table>
    </td>
  </tr>
</table>
<!-- System attributes -->

</td>
<td>
<img src="/web/image/spacer.gif" border="0" width="20"/>
</td>
<td valign="top">

<!-- user defined attributes -->
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">Attributes&nbsp;</td>
  </tr>
  <tr>
    <td>
      <table class="formProps" border="0" cellpadding="2" cellspacing="1" width="100%">
       <tr>
        <td class="prop"><nobr>Release Level</nobr></td>
        <td class="value" width="80"><nobr><bean:write name="item" property="release"/></nobr></td>
        <td class="value"><img gif="iedit" alt="Modify Release Level"/><nobr></td>
       </tr>
       <tr>
        <td class="prop">Revision</td>
        <td class="value"><nobr><bean:write name="item" property="revision"/></nobr></td>
        <td class="value"><img gif="iedit" alt="Modify Revision"/></td>
       </tr>
       <logic:iterate id="pair" name="item" property="userAttributes" type="zws.util.Pair">
       <tr>
        <bean:define id="prop" name="pair" property="object0"/>
        <td class="prop"><bean:write name="pair" property="object0"/></td>
        <td class="value"><bean:write name="pair" property="object1"/></td>
        <td class="value"><img gif="iedit" alt="Modify <%=prop%>"/></td>
       </tr>
       </logic:iterate>
      </table>
    </td>
  </tr>
  <tr class="formMenu">
    <td>
    <table border="0" cellpadding="2" cellspacing="0" width="100%">
        <tr>
          <td class="editButton"><html:link href="#" styleClass="actionButton"><img gif="save" alt="Save"/></html:link></td>
          <td class="actionButton"><html:link href="index.do?nav=index" styleClass="actionButton"><img gif="close" alt="Back to workspaces"/></html:link></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<!-- user defined attributes -->


    </td>
  </tr>
</table>

<%@ include file="/web/include/footer.jsp"%>
</html:form>
</body>
</html:html>
