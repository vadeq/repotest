<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html locale="true">

<%@ include file="/web/include/application-name.jsp"%>
<%@ include file="/web/include/header.jsp"%>

<head>
<title>Struts File Upload Example</title>
<html:base/>
</head>
<body bgcolor="white">
<html:form action="/prototype/file-upload/index" method="post" enctype="multipart/form-data">
<html:hidden property="event" value="fileUpload"/>
<table>
<tr>
<td align="center" colspan="2">

<font size="4">Please Enter the Following Details</font>
</tr>

<tr>
<td align="left" colspan="2">
<font color="red"><html:errors/></font>
</tr>



<tr>
 <td align="right">File Name: </td>
 <td align="left"><html:file property="uploadedFile00"/></td>
</tr>
<tr>
 <td align="right">File Name: </td>
 <td align="left"><html:file property="uploadedFile01"/></td>
</tr>
<tr>
 <td align="right">File Name: </td>
 <td align="left"><html:file property="uploadedFile02"/></td>
</tr>

<tr>
<td align="center" colspan="2">
<html:submit>Upload File</html:submit>
</td>
</tr>
</table>

<logic:present name="hiUploader" property="uploadedFiles">
 <logic:iterate id="file" name="hiUploader" property="uploadedFiles" type="org.apache.struts.upload.FormFile">
  <bean:write name="file" property="fileName"/><br>
 </logic:iterate>
</logic:present>


</html:form>
</body>
</html:html>
