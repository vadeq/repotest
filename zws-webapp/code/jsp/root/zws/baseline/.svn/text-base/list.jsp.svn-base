<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/baseline/index.do">
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="baseline.list"/>
<html:hidden property="hiState" value="list"/>

<%@ include file="/web/include/header.jsp"%>
<table border="0" cellpadding="0" cellspacing="0">
<tr><td>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">Baselines</td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
          <td class="listHeader">name</td>
          <td class="listHeader">location</td>
          <td class="listHeader">&nbsp;</td>
        </tr>
        <logic:iterate id="e" name="baselineListForm" property="items" type="com.zws.domo.baseline.Baseline">
        <tr class="item">
          <td class="item">
<html:link href="item.do" paramId="ID" paramName="e" paramProperty="name" styleClass="button" title="view baseline">
<bean:write name="e" property="name"/> </html:link></td>
          <td class="item"><bean:write name="e" property="location"/> </td>
<td>
<html:link href="index.do?event=delete" paramId="ID" paramName="e" paramProperty="name" styleClass="button" title="delete baseline">delete</html:link>
</td>
	</tr>
        </logic:iterate>
      </table>
    </td>
  </tr>
</table>
</td></tr>
<tr><td><html:link href="add.do?event=createNew" styleClass="button" title="add a new baseline">add</html:link>
</td></tr>


</table>
<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>











