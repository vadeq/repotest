<table border="0" cellpadding="0" cellspacing="0" width="100%">
 <tr>
  <script language="javascript">
    document.writeln('<td height="'+(canvasHeight-28)+'"><img "border="0" src="/web/image/spacer.gif" width="1" height="'+(canvasHeight-28)+'"/></td>');
  </script>
  <td valign="top">
   <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <%@ include file="/web/include/logo-title.jsp"%>
    <tr>
     <td valign="top" width="1%"><img width="8" src="/web/image/spacer.gif" border="0"/></td> 
     <logic:present name="__member" scope="session">
      <bean:define id="pb" name="panel-bar" scope="session" type="zws.hi.application.panelbar.hiPanelBar"/>
      <logic:present name="panel-bar" scope="session" >
       <td valign="top" width="1%"><%=pb.getPanelBarCode()%></td> 
      </logic:present>
      <logic:notPresent name="panel-bar" scope="session" >
        <td valign="top" width="1%">&nbsp;</td> 
      </logic:notPresent>
     </logic:present>
     <logic:notPresent name="__member" scope="session">
      <td valign="top" width="1%">&nbsp;</td> 
     </logic:notPresent>
     <td valign="top" width="1%"><img width="8" src="/web/image/spacer.gif" border="0"/></td> 
     <td align="left" valign="top"  width="100%"> 
