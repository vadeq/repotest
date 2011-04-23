<logic:present name="__member" scope="session">
 <bean:define id="pb" name="panel-bar" scope="session" type="zws.hi.application.panelbar.hiPanelBar"/>
 <logic:present name="panel-bar" scope="session">
  <style type="text/css">
   <%=pb.getStyleSheetCode()%>
  </style>
  <script language="JavaScript" type="text/javascript">
   <%=pb.getJavaScriptCode()%>
  </script>
  <body topmargin="0" leftmargin="0" bgcolor="white" onLoad="<%=pb.getOnLoadEvent()%>;<%=session.getAttribute("menuItem")%>" onResize="<%=pb.getOnResizeEvent()%>" />
 </logic:present>
 <logic:notPresent name="panel-bar" scope="session" >
 <body topmargin="0" leftmargin="0" bgcolor="white" />  <!-- need a body tag to get client height. -->
 </logic:notPresent>
</logic:present>

<logic:notPresent name="__member" scope="session">
 <body topmargin="0" leftmargin="0" bgcolor="white" />  <!-- need a body tag to get client height. -->
</logic:notPresent>
