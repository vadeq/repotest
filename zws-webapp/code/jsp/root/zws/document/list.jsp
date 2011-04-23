<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/document/index.do" focus="criteria">
<html:hidden property="event" value="search"/>
<html:hidden property="interactor" value="document-list"/>

<%@ include file="/web/include/header.jsp"%>
<script language="javascript"> <!--
function setDataType(cValue)
{
    // THIS FUNCTION CONVERTS DATES AND NUMBERS FOR PROPER ARRAY
    // SORTING WHEN IN THE SORT FUNCTION
    var isDate = new Date(cValue);
    if (isDate == "NaN")
      {
        if (isNaN(cValue))
          {
            // THE VALUE IS A STRING, MAKE ALL CHARACTERS IN
            // STRING UPPER CASE TO ASSURE PROPER A-Z SORT
            cValue = cValue.toUpperCase();
            return cValue;
          }
        else
          {
            // VALUE IS A NUMBER, TO PREVENT STRING SORTING OF A NUMBER
            // ADD AN ADDITIONAL DIGIT THAT IS THE + TO THE LENGTH OF
            // THE NUMBER WHEN IT IS A STRING
            var myNum;
            myNum = String.fromCharCode(48 + cValue.length) + cValue;
            return myNum;
          }
        }
  else
      {
        // VALUE TO SORT IS A DATE, REMOVE ALL OF THE PUNCTUATION AND
        // AND RETURN THE STRING NUMBER
        //BUG - STRING AND NOT NUMERICAL SORT .....
        // ( 1 - 10 - 11 - 2 - 3 - 4 - 41 - 5  etc.)
        var myDate = new String();
        myDate = isDate.getFullYear() + " " ;
        myDate = myDate + isDate.getMonth() + " ";
        myDate = myDate + isDate.getDate(); + " ";
        myDate = myDate + isDate.getHours(); + " ";
        myDate = myDate + isDate.getMinutes(); + " ";
        myDate = myDate + isDate.getSeconds();
        //myDate = String.fromCharCode(48 + myDate.length) + myDate;
        return myDate ;
      }
}

function sortTable(col, tableToSort)
{
    var iCurCell = col + tableToSort.cols;
    var totalRows = tableToSort.rows.length;
    var bSort = 0;
    var colArray = new Array();
    var oldIndex = new Array();
    var indexArray = new Array();
    var bArray = new Array();
    var newRow;
    var newCell;
    var i;
    var c;
    var j;
    // ** POPULATE THE ARRAY colArray WITH CONTENTS OF THE COLUMN SELECTED
    for (i=1; i < tableToSort.rows.length; i++)
      {
        colArray[i - 1] = setDataType(tableToSort.cells(iCurCell).innerText);
        iCurCell = iCurCell + tableToSort.cols;
      }
    // ** COPY ARRAY FOR COMPARISON AFTER SORT
    for (i=0; i < colArray.length; i++)
      {
        bArray[i] = colArray[i];
      }
    // ** SORT THE COLUMN ITEMS
    //alert ( colArray );
    colArray.sort();
    //alert ( colArray );
    for (i=0; i < colArray.length; i++)
      { // LOOP THROUGH THE NEW SORTED ARRAY
        indexArray[i] = (i+1);
        for(j=0; j < bArray.length; j++)
          { // LOOP THROUGH THE OLD ARRAY
            if (colArray[i] == bArray[j])
              {  // WHEN THE ITEM IN THE OLD AND NEW MATCH, PLACE THE
                // CURRENT ROW NUMBER IN THE PROPER POSITION IN THE
                // NEW ORDER ARRAY SO ROWS CAN BE MOVED ....
                // MAKE SURE CURRENT ROW NUMBER IS NOT ALREADY IN THE
                // NEW ORDER ARRAY
                for (c=0; c<i; c++)
                  {
                    if ( oldIndex[c] == (j+1) )
                    {
                      bSort = 1;
                    }
                      }
                      if (bSort == 0)
                        {
                          oldIndex[i] = (j+1);
                        }
                          bSort = 0;
                        }
          }
    }
  // ** SORTING COMPLETE, ADD NEW ROWS TO BASE OF TABLE ....
  for (i=0; i<oldIndex.length; i++)
    {
      newRow = tableToSort.insertRow();
      for (c=0; c<tableToSort.cols; c++)
        {
          newCell = newRow.insertCell();
          newCell.innerHTML = tableToSort.rows(oldIndex[i]).cells(c).innerHTML;
          newCell.className="item";
        }
      }
  //MOVE NEW ROWS TO TOP OF TABLE ....
  for (i=1; i<totalRows; i++)
    {
      tableToSort.moveRow((tableToSort.rows.length -1),1);
    }
  //DELETE THE OLD ROWS FROM THE BOTTOM OF THE TABLE ....
  for (i=1; i<totalRows; i++)
    {
      tableToSort.deleteRow();
    }
}
// -->
</script>

<!--
<-logic:iterate id="e" name="docList" property="stream" type="com.zws.util.KeyValue" >
<-bean:write name="e" property="value"/><-/html:link>
<-/logic:iterate>
-->
<table class="form" border="0" cellpadding="0" cellspacing="0" width="95%">
  <tr>
    <td class="formTitle" align="left">
      <div align="left">
        &nbsp;Search:&nbsp;<html:text property="criteria" size="24" onkeypress="return autoSubmit(this, event)"/>
        Report:
        <html:select name="docList" property="activeReportName" styleClass="input" onchange="send()" onkeypress="autoSubmit(this,event)">
         <html:options name="docList" property="reportNames"/>
        </html:select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <logic:equal name="docList" property="streamingEnabled" value="true">
        <span align="right" >
          <logic:equal name="docList" property="streaming" value="true"><html:link href="index.do?event=toggleStreaming" styleClass="setting-on" title="Toggle on/off data streaming">Real-Time Data Streaming On</html:link></logic:equal>
          <logic:equal name="docList" property="streaming" value="false"><html:link href="index.do?event=toggleStreaming" styleClass="setting-off" title="Toggle on/off data streaming">Real-Time Data Streaming Off</html:link></logic:equal>
        </span>
        </logic:equal>
        <logic:equal name="docList" property="streamingEnabled" value="false">
          <span class="setting-off">Real-Time Data Streaming Disabled</span>
        </logic:equal>
      </div>
    </td>
  </tr>
<!--
  <tr>
    <td class="formMenu"><html:link href="javascript:send()" styleClass="button">search</html:link>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:link href="javascript:sendEvent('showAll')" styleClass="button">show all</html:link>
    <hr>
    </td>
-->
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%" name="searchResults" id=searchResults cols='<bean:write name="docList" property="cols"/>'>
        <tr>
          <td class="listHeader">&nbsp;<a href="javascript:sortTable(0, searchResults);"><span class="listHeader">Name</span></a>&nbsp</td>
          <% int idx=1; %>
          <logic:iterate id="h" name="docList" property="visibleDisplayFields" type="com.zws.functor.report.DisplayField">
            <td class="listHeader"><nobr>&nbsp<a href="javascript:sortTable(<%=idx++%>, searchResults);"><span class="listHeader"><bean:write name="h" property="name"/><span></a>&nbsp</nobr></td>
          </logic:iterate>
          <td class="listHeader">&nbsp;</td>
        </tr>
        <logic:iterate id="doc" name="docList" property="items" type="com.zws.hi.document.hiDocument">
        <tr class="item">
          <td class="item">
            <html:link href="/document/index.do?event=download" paramId="ID" paramName="doc" paramProperty="ID" styleClass="button"><bean:write name="doc" property="name"/></html:link>
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

<%@ include file="/web/include/footer.jsp"%>

</html:form>
</body>
</html:html>
