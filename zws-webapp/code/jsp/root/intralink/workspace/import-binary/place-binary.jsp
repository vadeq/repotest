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

<% String title="Set Destination Folder Location"; %>
<%@ include file="/web/include/header.jsp"%>


<script language="JavaScript">
  function selectFolder(folderPath) {
   document.forms[0].newBinaryFileFolder.value = folderPath;
   //if (15<folderPath.length()) document.forms[0].newBinaryFileFolder.size = folderPath.length()+5;
  }
</script>

<table>
  <tr> 
    <td align="center">
     Folder: <html:text name="hiPersonalWorkspace" property="newBinaryFileFolder" size="88"/>
    </td> 
  </tr>
  <tr> 
    <td align="right" colspan="2">
     <a href="javascript:sendEvent('back');" ><img gif="back" alt="Back to: Set Name"/></a> 
     <a href="javascript:sendEvent('next');" ><img gif="next" alt="Set Release Level Next"/></a> 
     <a href="javascript:sendEvent('cancelImport');"><img gif="cancel" alt="Cancel file import"/></a>
    </td>
  </tr>
</table>

<jsp:useBean id="hiPersonalWorkspace" type="zws.hi.intralink.workspace.hiPersonalWorkspace" scope="session"/>
<%=hiPersonalWorkspace.getFolderView()%>

<%@ include file="/web/include/footer.jsp"%>



</html:form>
</body>
</html:html>
