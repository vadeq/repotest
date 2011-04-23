<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<span id="report">
<logic:present property="metaReportRecords" name="hiSearch">
<table class="form" border="0" cellpadding="0" cellspacing="0" align=center>
   <tr>
    <td>
			<div class="resultList">
      <table class="formPropsData" border="1" cellpadding="0" cellspacing="1" width="100%">
      	<tr>
   				 <td ><input type="checkbox" name="allResults" value="0" onclick="resultAll(this.form)" />All</td>
						<logic:iterate name="hiSearch" property="reportHeader" id= "header" type="java.lang.String">
   				  <td align=center class="formTitle">&nbsp;<bean:write name="header"/>&nbsp;</td>
   				 </logic:iterate>
   				  <td align=center class="formTitle">&nbsp;Publish Status&nbsp;</td>
  			</tr>
					 <logic:iterate property="metaReportRecords" name="hiSearch" id="PublishMetadataAdapter" type="zws.hi.report.PublishMetadataAdapter">
        <tr>
          <td><input type=checkbox name="selectedOrigins" value="<bean:write name="PublishMetadataAdapter" property="origin"/>" /></td>
          <logic:iterate id="field" name="PublishMetadataAdapter" property="values" type="java.lang.String">
                <td class="item">
                  &nbsp;<bean:write name="field" filter="false"/>&nbsp;
                </td>
           </logic:iterate>
   				 <td align=center>
   				 <logic:equal name="PublishMetadataAdapter" property="publishState"  value="false">
               <img src="/web/image/arrow_split.gif" />
               </logic:equal>
           <logic:equal name="PublishMetadataAdapter" property="publishState"  value="true">
               <img src="/web/image/arrow_join.gif" />
               </logic:equal>
            </td>
        </tr>
        </logic:iterate>

        <tr>
          <td>&nbsp;</td>
           <td >&nbsp;</td>
   				 <td>&nbsp;</td>
   				 <td >&nbsp;</td>
   				 <td >&nbsp;</td>
        </tr>
      </table>
			</div>
    </td>
  </tr>
  <tr class="formMenu">
    <td>
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td ><html:button onclick="validateSendMove(this.form,'addSelectedOrigins','/publish/search.do');"   property="save"  value="Move to cart"/></td>
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
    &nbsp;
 </span>