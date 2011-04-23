<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<script language="JavaScript">
    function selectLeaf(title, code) {
    	document.forms[0].name.value = title;
    	document.forms[0].code.value=code;
    }

	function selectFolder(folderCode) {
    	document.forms[0].name.value = "";
    	document.forms[0].code.value=folderCode;
    }

</SCRIPT>
<html>
<html:form action="/treeview.do">
<input type="text" name="name" size="18"/>
<input type="text" name="code" size="20"/>

<jsp:useBean id="hiTreeview" type="zws.hi.prototype.treeview.hiTreeview" scope="request"/>
<%=hiTreeview.getTree()%>


</html:form>

