<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/intralink/workspace/import.do" method="post" enctype="multipart/form-data">
<html:hidden property="event"/>

<%@ include file="/web/include/interactor.params"%>

<% String title="File Update"; %>
<%@ include file="/web/include/header.jsp"%>

<table>
<tr>
<td align="center" colspan="2">

<font size="4">Update <bean:write name="hiPersonalWorkspace" property="selectedItemName"/></font>
</tr>

<tr>
<td align="left" colspan="2">
<font color="red"><html:errors/></font>
</tr>

<tr>
 <td align="right">Upload Path: </td>
 <td align="left"><html:file property="uploadedFile00"/></td>
</tr>

<tr>
<td align="center" colspan="2">
<html:submit>Upload File</html:submit>

 <td align="right" colspan="2">
 <a href="index.do"><img gif="cancel" alt="Cancel file import"/></a>
 </td>

</td>
</tr>
</table>

<%@ include file="/web/include/footer.jsp"%>
</html:form>
</body>
</html:html>
