<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/modelcheck/index.do" >
<html:hidden property="event" value="search"/>
<html:hidden property="ID"/>
<html:hidden property="interactor" value="report"/>

<%@ include file="/web/include/header.jsp"%>


<table class="form" border="0" cellpadding="0" cellspacing="0" width="95%">
  <tr>
    <td class="formTitle" align="left">
      <div align="left">
        <html:link href="javascript:send()" styleClass="formTitle">Search</html:link>:&nbsp;<html:text property="criteria" size="48" onkeypress="return autoSubmit(this, event)"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" name="Submit Search" value="Submit Search" onkeypress="return autoSubmit(this, event)" class="button2">
<!--
Put in the standard select type to use incase needed
-->
        &nbsp;&nbsp;Select Output Type>><html:select property="viewableType">
				  <option value="pdf" selected>PDF</option>
				  <option value="hpg" >HPGL</option>
				  <option value="cgm" >CGM</option>
        </html:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="Promote Files" value="Promote Files" onclick="javascript:sendEvent('modelCheck')" class="button3">
	  </div>
    </td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">

        <tr>
          <td class="listHeader">&nbsp;</td>
          <logic:iterate id="fieldName" name="hiModelcheck" property="metadataFields" type="java.lang.String">
            <td class="listHeader"><bean:write name="fieldName"/></td>
          </logic:iterate>
        </tr>
        <logic:iterate id="metadata" name="hiModelcheck" property="chosenItems" type="zws.hi.report.MetadataAdapter"> 
        <tr>
          <td class="listHeader"><input type="checkbox" onClick='sendEventID("deselect", "<bean:write name="metadata" property="origin"/>")' checked /></td>
          <logic:iterate id="field" name="metadata" property="values" type="java.lang.String"> 
        
				<td class="item"><bean:write name="field"/></td>

          </logic:iterate>
        </tr>
        </logic:iterate>

        <tr>
          <td class="item">&nbsp;</td>
          <logic:iterate id="fieldName" name="hiModelcheck" property="metadataFields" type="java.lang.String">
            <td class="item">&nbsp;</td>
          </logic:iterate>
        </tr>

        <logic:iterate id="metadata" name="hiModelcheck" property="items" type="zws.hi.report.MetadataAdapter">
        <tr>
          <td class="item" width="3">
            <input type="checkbox" onClick='sendEventID("select", "<bean:write name="metadata" property="origin"/>")' />
          </td>
          <logic:iterate id="field" name="metadata" property="values" type="java.lang.String">       
				<td class="item"><bean:write name="field"/></td>
          </logic:iterate>
        </tr>
        </logic:iterate>
      </table>
    </td>
  </tr>
</table>
<html:link href="javascript:sendEvent('modelCheck')" styleClass="button" title="model check">Promote Selected Documents</html:link></html:form>
<p><html:form action="/modelcheck/index.do" > </html:form></p>
</body>
</html:html>
