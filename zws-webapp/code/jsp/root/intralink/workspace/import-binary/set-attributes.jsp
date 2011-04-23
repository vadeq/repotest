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

<% String title="Set Attributes"; %>
<%@ include file="/web/include/header.jsp"%>

<table>
  <tr> 
    <td align="center">
     atts
    </td> 
  </tr>
  <tr> 
    <td align="right" colspan="2">
     <a href="javascript:sendEvent('back');" ><img gif="back" alt="Back to: Set Attributes"/></a> 
     <a href="javascript:sendEvent('next');" ><img gif="finish" alt="Finish importing file"/></a> 
     <a href="javascript:sendEvent('cancelImport');"><img gif="cancel" alt="Cancel and use default attribute values"/></a>
    </td>
  </tr>
</table>


<p>&nbsp;</p>
<p> 

</p>
<p>

</p>
<%@ include file="/web/include/footer.jsp"%>


</html:form>
</body>
</html:html>
