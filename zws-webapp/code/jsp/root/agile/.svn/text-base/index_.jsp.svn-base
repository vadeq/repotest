            
            <logic:iterate id="metadata" name="hiPublish2Agile" property="items" type="zws.hi.report.MetadataAdapter"> 
             <script language="javascript"> <!--
              if (rownumber % 2 == 0) { 
               currentcolor = 0; 
              } else { 
               currentcolor = 1; 
              } 
              rownumber = rownumber + 1; 
              document.write ("<tr class='item"+currentcolor+"'>");
              //-->
             </script>
             <script language="javascript"> <!--
              document.write ("<td align='center' class='item"+currentcolor+"'>");
              //-->
             </script><input type="checkbox" onClick='pickItem("<bean:write name="metadata" property="origin"/>")' /></td>

             <logic:iterate id="field" name="metadata" property="values" type="java.lang.String"> 
              <script language="javascript"> <!--
               document.write ("<td align='left' nowrap class='item"+currentcolor+"'>");
               //-->
              </script><bean:write name="field"/></td>
             </logic:iterate> </tr>
            </logic:iterate>











<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/include/head.html"%>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/procOnFly/index.do" >
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="report"/>

<%@ include file="/include/interactor.params"%>
<%@ include file="/include/header.jsp"%>



<table class="form" border="0" cellpadding="0" cellspacing="0" width="95%">
  <tr align="left"> 
    <td class="pageTitle" align="left">Publish To Agile</td>
  </tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="95%">
  <tr> 
    <td align="left"> <%@ include file="/include/search-table.html"%> 
      </td>
  </tr>
  <tr> 
    <td class="formTitle" align="left">Search and Select Files To Publish:</td>
  </tr>
  <tr> 
    <td> 

      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr> 
          <td class="listHeader">&nbsp;</td>
          <logic:iterate id="fieldName" name="hiPublish" property="metadataFields" type="java.lang.String"> 
          <td class="listHeader"><bean:write name="fieldName"/></td>
          </logic:iterate>
        </tr>

        <logic:iterate id="metadata" name="hiPublish" property="chosenItems" type="zws.hi.report.MetadataAdapter"> 
        <tr> 
          <td class="item" width="3" align="center"> 
            <input type="checkbox" onClick='unpickItem("<bean:write name="metadata" property="origin"/>")' checked />
          </td>
          <logic:iterate id="field" name="metadata" property="values" type="java.lang.String"> 
          <td class="item"><bean:write name="field"/></td>
          </logic:iterate>
        </tr>
        </logic:iterate> 

        <tr>
          <td class="item">&nbsp;</td>
          <logic:iterate id="fieldName" name="hiPublish" property="metadataFields" type="java.lang.String"> 
          <td class="item">&nbsp;</td>
          </logic:iterate>
        </tr>

       <logic:iterate id="metadata" name="hiPublish" property="items" type="zws.hi.report.MetadataAdapter"> 
        <script language="javascript"> <!--
 if (rownumber % 2 == 0) { 
   currentcolor = 0; 
 } else { 
   currentcolor = 1; 
 } 
 rownumber = rownumber + 1; 
 document.write ("<tr class='item"+currentcolor+"'>");
//-->
</script>
<script language="javascript"> <!--
 document.write ("<td align='center' class='item"+currentcolor+"'>");
//-->
</script><input type="checkbox" onClick='pickItem("<bean:write name="metadata" property="origin"/>")' /></td>

<logic:iterate id="field" name="metadata" property="values" type="java.lang.String"> 
<script language="javascript"> <!--
 document.write ("<td align='left' nowrap class='item"+currentcolor+"'>");
//-->
</script><bean:write name="field"/></td>
          </logic:iterate> </tr>
        </logic:iterate> 

      </table>
    </td>
  </tr>
</table>
<a href="javascript:sendEvent('next');" class="button">Next</a>
 </html:form></p>
</body>
</html:html>
