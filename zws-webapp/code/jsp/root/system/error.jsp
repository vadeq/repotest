<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<% String title="Unknown Error!"; %>
<%@ include file="/web/include/header.jsp"%>

<h2>Please navigate back and try the operation again.</h2>
<h3>If the problem persists, contact your administrator to help resolve the issue.</h3>

<%@ include file="/web/include/footer.jsp"%>
</body>
</html:html>
