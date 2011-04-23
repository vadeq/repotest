<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>
<script type="text/javascript" language="JavaScript" src="/web/javascript/treeview/treeview.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/event/service/fire.do">
<html:hidden property="event"/>

<%@ include file="/web/include/interactor.params"%>

<% String title="Event Simulator"; %>
<%@ include file="/web/include/header.jsp"%>

<html:text name="hiEventService" property="username"/>
<html:password name="hiEventService" property="password"/>
<html:text name="hiEventService" property="ev"/>
<a href="javascript:sendEvent('fire')">fire</a>

<%@ include file="/web/include/footer.jsp"%>
</html:form>
</body>
</html:html>
