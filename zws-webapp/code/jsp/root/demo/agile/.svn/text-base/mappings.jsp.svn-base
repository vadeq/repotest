<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html> 
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/i2a/mappings.do" >
<html:hidden property="ID"/>
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="report"/>
<html:hidden property="criteria"/>

<% String title="Mapping Configuration <i></i>"; %>
<%@ include file="/web/include/header.jsp"%>

<script type="text/javascript" language="JavaScript" src="/web/javascript/report/report.js"></script>
<div align="left">
<table border="0" cellpadding="2" cellspacing="5" align="left">
 <tr>
  <td valign="top" width="20%">
   <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
     <td class="formTitle" align="left"><div align="left">&nbsp;Intralink&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
    </tr>
    <tr>
     <td class="button">Attribute&nbsp;&nbsp;&nbsp;<a class="cancelButton" href="javascript:sendEvent('refreshIntralinkConfiguration');">(refresh)</a><td>
    </tr>
    <logic:iterate id="att" name="hiZWSAgileMapping" property="intralinkAttributes" type="java.lang.String">
    <tr>
     <td class="item"><nobr><a class="button" href="javascript:sendEventID('chooseIntralinkAttribute', '<bean:write name="att"/>');"><bean:write name="att"/></a></nobr></td>
    </tr>
    </logic:iterate>
   </table>
  </td>
 
  <td valign="top" width="20%">
   <table class="form" border="0" cellpadding="0" cellspacing="" width="100%">
    <tr>
     <td class="formTitle" align="left" colspan="2"><div align="left"><nobr>&nbsp;Agile - <bean:write name="hiZWSAgileMapping" property="agileClassName"/>  Attributes&nbsp;&nbsp;</nobr></div></td>
    </tr>
    <tr>
     <td class="button"><nobr>CAD Sub Class</nobr></td>
     <td class="button"></nobr>Attribute&nbsp;&nbsp;&nbsp;<a class="cancelButton" href="javascript:sendEvent('refreshAgileConfiguration');">(refresh)</a></nobr></td>
    </tr>
    <tr>
     <td class="item" valign="top">
      <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
       <logic:iterate id="classname" name="hiZWSAgileMapping" property="agileCADClasses" type="java.lang.String">
       <tr>
        <td class="item"><nobr><a class="button" href="mappings.do?agileClassName=<bean:write name="classname"/>"><bean:write name="classname"/></a></nobr></td>
       </tr>
       </logic:iterate>
      </table>
     </td>
     <td class="item" valign="top">
      <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
       <logic:iterate id="att" name="hiZWSAgileMapping" property="agileAttributes" type="java.lang.String">
       <tr>
        <td class="item"><nobr><a class="button" href="javascript:sendEventID('chooseAgileAttribute', '<bean:write name="att"/>');"><bean:write name="att"/></a></nobr></td>
       </tr>
       </logic:iterate>
      </table>
     </td>
    </tr>
   </table>
  </td>

  <td valign="top" width="10%">&nbsp;</td>
  <td valign="top" width="50%">
   <table class="form" border="0" cellpadding="0" cellspacing="0">
    <tr>
     <td colspan="3" class="formTitle" align="left">
       &nbsp;Mappings&nbsp;&nbsp;
     </td>
    </tr>
    <tr><td><b>&nbsp;Intralink</b></td><td><b>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="hiZWSAgileMapping" property="agileClassName"/></b></td></tr>
    <logic:iterate id="mapping" name="hiZWSAgileMapping" property="mappings" type="zws.util.StringPair">
    <tr>
     <td class="item"><nobr><b>&nbsp;<bean:write name="mapping" property="string0"/>&nbsp;</b></nobr></td>
     <td class="item"><nobr><b>&nbsp;<bean:write name="mapping" property="string1"/>&nbsp;</b></nobr></td>
     <td class="button"><a class="cancelButton" href="javascript:sendEventID('deleteMapping', '<bean:write name="mapping" property="string0"/>');">x</a></td>
    </tr>
    </logic:iterate>
    <tr>
     <td class="item"><div><font color="maroon"><nobr><b><i>&nbsp;<bean:write name="hiZWSAgileMapping" property="intralinkAttribute"/>&nbsp;</i></b></nobr></font></div></td>
     <td class="item"><div><font color="maroon"><nobr><b><i>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="hiZWSAgileMapping" property="agileAttribute"/>&nbsp;</i></b></nobr></font></div></td>
    </tr>
   </table>
  </td>

 </tr>
</table>
</div>

<%@ include file="/web/include/footer.jsp"%>
</html:form>
</body>
</html:html>
