<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/baseline/add.do" focus="name">
<html:hidden property="event" value="add"/>
<html:hidden property="interactor" value="baseline.add"/>
<html:hidden property="hiState" value="create-new"/>


<script language="JavaScript">
	function selectFolder(folderName) {
     	document.forms[0].location.value = folderName;
      if (32<folderName.length()) document.forms[0].location.size = folderName.length()+3;
    }
</script>

<%@ include file="/web/include/header.jsp"%>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">Baseline</td>
  </tr>
  <tr>
    <td>
      <table class="formProps" border="0" cellpadding="0" cellspacing="1" width="100%">
        <tr>
          <td>

          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr class="formMenu">
    <td>
    <table border="0" cellpadding="3" cellspacing="0" width="100%">
	<tr>
          <td class="prop">Name</td>
          <td class="value"><html:text property="name" size="32" /></td>
          <td class="propError">&nbsp;<html:errors property="name"/></td>
	</tr>
	
	<tr>
          <td class="prop">Folder</td>
          <td class="value">
<html:text property="location" size="32" readonly="true" onkeypress="autoSubmit(this,event)"/><br>
<jsp:useBean id="hiBaseline" type="zws.hi.intralink.baseline.hiBaseline" scope="session"/>
<%=hiBaseline.getFolderView()%>
</td>
          <td class="propError">&nbsp;<html:errors property="name"/></td>

		
	</tr>
        <tr>
         
	</tr>
	<tr>
          <td class="saveButton"><html:link href="index.do" styleClass="saveButton">cancel</html:link></td>
          <td class="saveButton"><html:link href="javascript:send();" styleClass="saveButton">save</html:link></td>

	</tr>

       
	<%@ include file="folderview.jsp"%>
	<tr><td>
		<html:link href="add.do?event=updateCache" styleClass="button" title="update cache">synchronize Intralink</html:link>
	 </td></tr>	
	
      </table>
    </td>
  </tr>
</table>

<%@ include file="/web/include/footer.jsp"%>


</html:form>
</body>
</html:html>
