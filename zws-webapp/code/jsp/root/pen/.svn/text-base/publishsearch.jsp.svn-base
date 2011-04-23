<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>
<script type="text/javascript" language="JavaScript" src="/web/javascript/ajax.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/publish/search.do?menuItem=0.1">
<html:hidden property="event" value=""/>
<html:hidden property="ID" value=""/>

<%@ include file="/web/include/application-name.jsp"%>
<%@ include file="/web/include/header.jsp"%>
<fieldset class="search" ><legend class="legendTitle"> Search</legend>
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <table class="formProps" border="0" cellpadding="0" cellspacing="1" width="100%">
        <tr>
          <td>
          <table class="formProps" border="0" cellpadding="1" cellspacing="0" width="100%">

           <tr>
						 <logic:iterate property="repositories" name="hiSearch" id="repository">
              <td> <html:multibox property="selectedRepositories">
               <bean:write name="repository" property="key"/>   </html:multibox>
               <bean:write name="repository" property="value"/></td>
               </logic:iterate>
            </tr>
             <tr>
              <td colspan=1>Criteria:</td>
               <td colspan=3><html:text property="criteria" /></td>
            </tr>
            <tr>
           </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr class="formMenu">
    <td>
    <table border="0" cellpadding="1" cellspacing="0" width="100%">
        <tr>
          <td><html:button onclick="validateSendSearch(this.form,'searchCriteria','/publish/search.do');"  property="search" styleClass="okButton" value="Search"/></td>
          <td><html:reset styleClass="okButton"  value="Clear"/></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</fieldset>
<fieldset class="search" ><legend class="legendTitle">Report</legend>
 <div id="responsestatus" align="center" >&nbsp;</div>
<span id="report">
<div id="details">
<logic:present property="metaReportRecords" name="hiSearch">
<table class="form" border="0" cellpadding="0" cellspacing="0" align=center>
   <tr>
    <td>
		<div id="tbl-container">
     <table border="1" cellpadding="0" cellspacing="1" width="100%" class="tbl" >

      	<tr>
   				 <th class="formTitle"><input type="checkbox" name="allResults" value="0" onclick="resultAll(this.form)" />All</th>
						<logic:iterate name="hiSearch" property="reportHeader" id= "header" type="java.lang.String">
   				  <th align=center class="formTitle">&nbsp;<bean:write name="header"/>&nbsp;</th>
   				 </logic:iterate>
   				  <th  align=center  class="formTitle" >&nbsp;Publish Status&nbsp;</th>
  			</tr>

  			<tbody class="scrollContent" >
					 <logic:iterate property="metaReportRecords" name="hiSearch" id="PublishMetadataAdapter" type="zws.hi.report.PublishMetadataAdapter">
        <tr>
          <td class="clear"><input type=checkbox name="selectedOrigins" value="<bean:write name="PublishMetadataAdapter" property="origin"/>" /></td>
          <logic:iterate id="field" name="PublishMetadataAdapter" property="values" type="java.lang.String">
                <td class="item">
                  &nbsp;<bean:write name="field" filter="false"/>&nbsp;
                </td>
           </logic:iterate>
   				 <td align=center class="clear">
   				 <logic:equal name="PublishMetadataAdapter" property="publishState"  value="false">
               <img src="/web/image/arrow_split.gif" />
               </logic:equal>
           <logic:equal name="PublishMetadataAdapter" property="publishState"  value="true">
               <img src="/web/image/arrow_join.gif" />
               </logic:equal>
            </td>
        </tr>
        </logic:iterate>
				</tbody>
      </table>
			</div>
    </td>
  </tr>
  <tr class="formMenu">
    <td>
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td ><html:button onclick="validateSendMove(this.form,'addSelectedOrigins','/publish/search.do');"   property="save"  value="Select"/></td>
          <td ><html:reset  value="Clear"/></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</logic:present>
 	<logic:equal property="present" name="hiSearch" value="false">
   <table class="form" border="0" cellpadding="0" cellspacing="0" align=center>
   <tr>
    <td><bean:message key="status.found.no.search.results"/>&nbsp;</td>
    <tr>
    </table>
    </logic:equal>
    </div>
   </span>
   &nbsp;
</fieldset>

<fieldset class="search" ><legend class="legendTitle" >Publish</legend>
 <div id="pubstatus" align="center" >&nbsp;</div>
 <span id="publish">
 <logic:present property="cartMetaRecords" name="hiSearch">
<table class="form" border="0" cellpadding="0" cellspacing="0">

  <tr>
    <td>
			<div id="pub-container">
      <table  border="1" cellpadding="0" cellspacing="1" width="100%" class="tbl">
      	<tr>
   				 <th class="formTitle"><input type="checkbox" name="allResults" value="0" onclick="resultAll(this.form)" />All</th>
						<logic:iterate name="hiSearch" property="reportHeader" id= "header" type="java.lang.String">
   				  <th align=center class="formTitle">&nbsp;<bean:write name="header"/>&nbsp;</th>
   				 </logic:iterate>
   				  <th align=center class="formTitle" >&nbsp;Publish Status&nbsp;</th>
   				  <th align=center class="formTitle">&nbsp;Target&nbsp;</th>
   				 <th align=center class="formTitle">&nbsp;Action&nbsp;</th>
  			</tr>
				<tbody class="scrollContent">
  			 <logic:iterate property="cartMetaRecords" name="hiSearch" id="cartMetaAdapter">
        <tr>
          <td class="clear"><input type=checkbox name="selectedCartOrigins" checked="true" value="<bean:write name="cartMetaAdapter" property="origin"/>" /></td>
          <logic:iterate id="field" name="cartMetaAdapter" property="values" type="java.lang.String">
                <td class="item">
                  &nbsp;<bean:write name="field" filter="false"/>&nbsp;
                </td>
           </logic:iterate>
   				 <td align=center class="clear">
   				 <logic:equal name="PublishMetadataAdapter" property="publishState"  value="false">
               <img src="/web/image/arrow_split.gif" />
               </logic:equal>
           <logic:equal name="PublishMetadataAdapter" property="publishState"  value="true">
               <img src="/web/image/arrow_join.gif" />
               </logic:equal>
            </td>
   				 <td ><select name="Target">
   				 <option value="a">Agile</option>
   				 <option value="b">PTC</option>
   				 </select>
   				 </td>
   				 <td><select name="Action">
   				 <option value="01">Review</option>
   				 <option value="02">Update review</option>
   				 <option value="03">Release</option>
   				 <option value="04">Rev Update</option>
   				 </select></td>
        </tr>
        </logic:iterate></tbody>
      </table>
      </div>
    </td>
  </tr>
  <tr class="formMenu">
    <td>
    <table border="0" cellpadding="1" cellspacing="0" width="100%">
        <tr>
          <td><html:button onclick="validateSendPublish(this.form,'publish');"  property="publish" styleClass="okButton" value="Publish"/></td>
           <td><html:button onclick="validateSendRemove(this.form,'removeSelectedOrigins','/publish/search.do');"   property="remove" styleClass="okButton" value="Remove"/></td>
          <td><html:reset styleClass="okButton"  value="Clear"/></td>
        </tr>
      </table>
    </td>
  </tr>
 </table>
  </logic:present>
   	  </span>
  &nbsp;
</fieldset>

<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>
<script type="text/javascript" >
stripedTable();
</script>