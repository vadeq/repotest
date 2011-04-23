<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/procOnFly/index.do" >
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="report"/>

<%@ include file="/include/interactor.params"%>
<%@ include file="/include/header.jsp"%>



<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr align="left"> 
    <td class="pageTitle" align="left">Publish To Agile</td>
  </tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td class="formTitle" align="left">Select Publishing Options:</td>
  </tr>
  <tr>
    <td> 
      <table class="listItems" border="0" cellpadding="2" cellspacing="1">
        <tr> 
          <td class="listHeader">&nbsp;</td>
          <logic:iterate id="fieldName" name="hiPublish" property="metadataFields" type="java.lang.String"> 
          <td class="listHeader"><bean:write name="fieldName"/></td>
          </logic:iterate>
          <td class="listHeader">Promote To</td>
          <td class="listHeader">Viewable Type</td>
          <td class="listHeader">Bill Of Materials</td>
        </tr>
        <logic:iterate id="metadata" name="hiPublish" property="chosenItems" type="zws.hi.report.MetadataAdapter"> 
        <bean:define id="origin" name="metadata" property="origin" type="zws.data.Origin"/>
        <tr> 
          <td class="item" width="3" align="center"> 
            <input type="checkbox" onClick='unpickItem("<bean:write name="metadata" property="origin"/>")' checked />
          </td>
          <logic:iterate id="field" name="metadata" property="values" type="java.lang.String"> 
          <td class="item"><bean:write name="field"/></td>
          </logic:iterate>
          <td class="item">

		<select name="p">
		<option value="1">--</Option>
		<option value="1">In Progress</Option>
		<option value="1">In Approval</Option>
		<option value="1">Released</Option>
		<option value="1">Archived</Option>
		</select>

	  </td>
          <td class="item" >
	    <logic:equal name="metadata" property="hasViewableOptions" value="true">
	      <html:select name="metadata" property="viewableType"><html:options name="metadata" property="viewableOptions"/></html:select>
	    </logic:equal>
	  </td>
          <td class="item" >
           <logic:equal name="metadata" property="billIsAvailable" value="true">
             <html:checkbox name="metadata" property="includeBill"/><a href="/publish/agile/index.do?nav=bill&ID=<%=origin%>" target="<%=origin%>" class="button">&nbsp;Bill Of Materials</a>

           </logic:equal>
          </td>
        </tr>
        </logic:iterate> 
      </table>
    </td>
  </tr>
</table>
<a href="javascript:sendEvent('back')" class="button">Back</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:sendEvent('publish')" class="button">Publish</a>
 </html:form></p>
</body>
</html:html>
