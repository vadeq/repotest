<jsp:useBean id="wtcontext" class="wt.httpgw.WTContextBean" scope="request"/>
<jsp:setProperty name="wtcontext" property="request" value="<%=request%>"/>
<jsp:useBean  id="url_factory" class="wt.httpgw.URLFactory" scope="request" >
	<% url_factory.setRequestURL(request.getScheme(), request.getHeader("HOST"), request.getRequestURI()); %>
</jsp:useBean>

<%
	response.setContentType( "application/vnd.ms-excel" );
	String number 	= request.getParameter("number");
	String name 	= request.getParameter("name");
	String version 	= request.getParameter("version");
	String className 	= request.getParameter("className");
	String oids 	= request.getParameter("oids");
	
	if(name == null){ name="files.zip"; }
	response.setHeader("Content-Disposition","attachment;filename=\"" + name + "\"");
		
	System.out.println("The number is " + number);
	System.out.println("The name is " + name);
	System.out.println("The version is " + version);
	System.out.println("The className is " + className);
	
	zws.ilink8.server.util.IntralinkUtility intralinkUtility = new zws.ilink8.server.util.IntralinkUtility();
	java.util.Vector applicationVector = null;
	if(oids != null){
		applicationVector = intralinkUtility.getApplicationData(oids);
		response.setContentType("application/zip");
		intralinkUtility.streamZip(applicationVector, response);
	} else {
		applicationVector = intralinkUtility.getApplicationData(number, name, version, className);
		java.util.HashMap hashMap = (java.util.HashMap)applicationVector.elementAt(0);
		String mimetype = (String)hashMap.get("mimetype");
		String contentname = (String)hashMap.get("filename");		
		String conentstring = (String)hashMap.get("conentstring");
		response.setContentType(mimetype);
		response.setHeader("Content-Disposition","attachment;filename=\"" + contentname + "\"");
		intralinkUtility.streamContent(conentstring, response);		
	}
%>