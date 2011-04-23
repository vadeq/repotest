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

<% String title="Set File Name"; %>
<%@ include file="/web/include/header.jsp"%>

<table>
  <tr> 
    <td align="center">
     Name: <html:text name="hiPersonalWorkspace" property="newBinaryFileName"/>
    </td> 
  </tr>
  <tr> 
    <td align="right" colspan="2">
     <a href="javascript:sendEvent('next');" ><img gif="next" alt="Set Folder Location Next"/></a> 
     <a href="javascript:sendEvent('cancelImport');"><img gif="cancel" alt="Cancel file import"/></a>
    </td>
  </tr>
</table>

<%@ include file="/web/include/footer.jsp"%>


</html:form>
</body>
</html:html>
