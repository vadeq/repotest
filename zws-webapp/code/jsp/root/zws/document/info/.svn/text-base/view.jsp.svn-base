<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/document/info/index.do">
<html:hidden property="event" value="edit"/>
<html:hidden property="rev" value="revision"/>
<html:hidden property="ver" value="version"/>

<%@ include file="/web/include/header.jsp"%>

<table border="0" cellpadding="3" cellspacing="3">
 <tr>
  <td valign="top">
   <logic:present name="docForm" property="history">
   <!-- todo: break this table into a separate include file -->
   <table class="form" border="0" cellpadding="0" cellspacing="0">
    <tr><td class="formTitle">&nbsp;History&nbsp;</td></tr>
    <tr>
     <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
       <tr class="item">
        <td class="item" align="center">
         <a href='/document/info/index.do?ID=<bean:write name="docForm" property="ID"/>'>Latest</a><br>
         <logic:iterate id="i" name="docForm" property="history" type="com.zws.domo.document.DocumentInterface">
          <a href='/document/info/index.do?ID=<bean:write name="docForm" property="ID"/>&rev=<bean:write name="i" property="revision"/>&ver=<bean:write name="i" property="version"/>'>
           <bean:write name="i" property="revision"/>
           <bean:write name="i" property="version"/>
          </a><br>
         </logic:iterate>
        </td>
       </tr>
      </table>
     </td>
    </tr>
   </table>
   </logic:present>
  </td>
  <td valign="top">

  <!-- todo: break this table into a separate include file -->
   <table class="form" border="0" cellpadding="0" cellspacing="0">
     <tr>
       <td class="formTitle"><bean:write name="docForm" property="name"/> <bean:write name="docForm" property="revision"/> <bean:write name="docForm" property="version"/></td>
     </tr>
     <logic:equal name="docForm" property="hiState" value="BOM">
     <tr><td><table border="0" cellpadding="0" cellspacing="0" bgcolor="white">
        <tr class="item"><td class="item"><%@ include file="treeview.jsp"%></td></tr>
     </table></td></tr>
     </logic:equal>

     <logic:equal name="docForm" property="hiState" value="Info">
     <tr>
       <td>
         <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
           <tr>
             <td class="listHeader">attribute</td>
             <td width="10" class="listHeader">value</td>
           </tr>
           <logic:iterate id="i" name="docForm" property="attributes" type="com.zws.util.KeyValue">
           <tr class="item">
             <td class="item"><bean:write name="i" property="key"/></td>
             <td class="item"><bean:write name="i" property="value"/></td>
           </tr>
           </logic:iterate>
         </table>
       </td>
     </tr>
     </logic:equal>


     <tr class="formMenu">
       <td>
         <table border="0" cellpadding="0" cellspacing="0" width="100%">
           <tr>
             <td>
              <a class="button" href='/document/index.do?event=download&ID=<bean:write name="docForm" property="ID"/>&rev=<bean:write name="docForm" property="revision"/>&ver=<bean:write name="docForm" property="version"/>'>
               download
              </a>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <logic:equal name="docForm" property="hiState" value="Info">
              <a class="button" href='/document/info/index.do?nav=view&hiState=BOM&ID=<bean:write name="docForm" property="ID"/>&hiState=<bean:write name="docForm" property="hiState"/>'>view BOM</a>
              </logic:equal>
              <logic:equal name="docForm" property="hiState" value="BOM">
              <a class="button" href='/document/info/index.do?nav=view&hiState=Info&ID=<bean:write name="docForm" property="ID"/>&hiState=<bean:write name="docForm" property="hiState"/>'>view Info</a>
              </logic:equal>
             </td>
             <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
             <td class="cancelButton"><html:link href="javascript:window.close()" styleClass="cancelButton">close</html:link></td>
           </tr>
         </table>
       </td>
     </tr>
   </table>

  </td>
 </tr>
</table>

<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>
