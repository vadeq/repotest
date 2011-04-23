<%@ page language="java" %>
<%@ page import="zws.service.event.EventQueuePlugin"%>
<%@ page import="zws.security.Authentication"%>
<%@ page import="zws.qx.xml.QxXML"%>

<html>
<body>
<%
String s = null;
QxXML result = null;
try{
	
	if(null != request.getParameter("firedEvent")) {
	  s = request.getParameter("firedEvent");
	} else if(null != request.getAttribute("firedEvent")) {
	  s = (String) request.getAttribute("firedEvent");
	} 
	
	/*else {
	  //s = "<event to='pavan.toleti@gmail.com' time='2007.10.23.17.20.23' domain='zws-domain' server='designstate-0' name='Event-1' event-type='zws.qx.event.AgileCheckinHandler' author='pavan'/>";
	  //s="<event to='pavan.toleti@gmail.com' time='2007.10.25.10.39.11' domain='zws-domain' server='designstate-0' name='Event-1' event-type='zws.qx.event.handler.ilink.Intralink8CheckinHandler' author='pavan'><metadata origin='zws|node0|ilink|ilink|8|main|C|0|29-5001-01.prt|N/A|N/A|N/A|N/A' name='29-5001-01.prt' lib_x='888.88' number='29-5001-01.prt' description='Code Drop 3' attachment='C:\test.txt' author='Matt Mohr' version='B.8' dirtyFlag='true'/></event>";
	}
	*/
	
	
  EventQueuePlugin eventQ = new EventQueuePlugin();
  Authentication id = new Authentication("admin","agile");
  result = eventQ.fire(id, new QxXML(s));
} catch (Exception e) {
  result = new QxXML(e.getMessage());
  e.printStackTrace();
}
%>
</body>
<h4><%=result%></h4>
</html>
