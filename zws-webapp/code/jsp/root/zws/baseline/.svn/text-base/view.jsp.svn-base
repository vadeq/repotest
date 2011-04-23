<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/baseline/item.do">
<html:hidden property="ID"/>
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="userview"/>
<html:hidden property="hiState" value="view"/>

<%@ include file="/web/include/header.jsp"%>


<table border=2>
<tr><td colspan=2 class="formTitle">Baseline <bean:write name="baselineForm" property="name"/> (<bean:write name="baselineForm" property="location"/>)</td></tr>
<tr>
  <td valign="top">
    <table>
      <tr class="item">
        <td class="formTitle">File Name</td>
	<td class="formTitle">Branch</td>
    	<td class="formTitle">Revision</td>
    	<td class="formTitle">Version</td>
	<td class="formTitle">Delete</td>
      </tr>
      <logic:iterate id="e" name="baselineForm" property="files" type="com.zws.domo.baseline.Fileentry">
      <tr class="item">
        <td class="item"><bean:write name="e" property="name"/> </td>
	<td class="item"><bean:write name="e" property="branch"/> </td>
	<td class="item"><bean:write name="e" property="revision"/> </td>
	<td class="item"><bean:write name="e" property="version"/> </td>
	<td><a href='item.do?event=deleteFile&ID=<bean:write name="baselineForm" property="ID"/>&filename=<bean:write name="e" property="name"/>' styleClass="button" title="delete file">x</a></td>
      </tr>
      </logic:iterate>
      <tr class="formMenu">
        <td COLSPAN="5">
          <table border="0" cellpadding="3" cellspacing="0">
            <tr>
              <td class="editButton"><html:link href="javascript:sendEvent('save')" styleClass="button">save</html:link></td>
              <td class="actionButton"><html:link href="/baseline/item.do?event=download" paramId="ID" paramName="baselineForm" paramProperty="ID" styleClass="button">download</html:link></td>
              <td class="cancelButton"><html:link href="index.do" styleClass="actionButton">close</html:link></td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </td>
  <td valign="top">
    <table border=1/>
      <tr><td valign="top"><%@ include file="treeview.jsp"%></td></tr>
      <tr>
        <td>
          <table class="form" border="0" cellpadding="0" cellspacing="0" >  
            <tr>
              <td class="formTitle" align="left">
                <div align="left">
               &nbsp;Search:&nbsp;<html:text property="criteria" size="24" onkeypress="return autoSubmit(this, event)"/>
                
                </div>
              </td>
            </tr>
            <tr>
              <td>
              <bean:define id="docList" name="baselineForm" property="fileListInteractor"/>
              <table class="listItems" border="2" cellpadding="2" cellspacing="1" name="searchResults" id=searchResults cols='<bean:write name="docList" property="cols"/>'>
                <tr>
                  <td class="listHeader">&nbsp;<a href="javascript:sortTable(0, searchResults);"><span class="listHeader">Name</span></a>&nbsp</td>
                  <%int idx=1; %>
                  <logic:iterate id="h" name="docList" property="visibleDisplayFields" type="com.zws.functor.report.DisplayField">
                  <td class="listHeader"><nobr>&nbsp<a href="javascript:sortTable(<%=idx++%>, searchResults);"><span class="listHeader"><bean:write name="h" property="name"/></span></a>&nbsp</nobr></td>
                  </logic:iterate>
                  <td class="listHeader">&nbsp;----</td>
                </tr>
                <% idx=0; %>
                <logic:iterate id="doc" name="baselineForm" property="items" type="com.zws.hi.document.hiDocument">
                <tr class="item">
                  <td class="item">
                      <a href='/baseline/item.do?event=populate&ID=<bean:write name="baselineForm" property="ID"/>&fileindex=<%=idx++%>'><bean:write name="doc" property="name"/></a>
                  </td> 
                  <logic:iterate id="att" name="doc" property="visibleAttributes" type="com.zws.util.KeyValue">
                  <td class="item"><bean:write name="att" property="value"/></td>
                  </logic:iterate>
                  <td class="itemMenu"><html:link href="/document/info/index.do?nav=view" paramId="ID" paramName="doc" paramProperty="ID" target="_blank" styleClass="button">Info</html:link></td>
                </tr>
                </logic:iterate>
              </table>
            </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </td>
</table>

<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>
