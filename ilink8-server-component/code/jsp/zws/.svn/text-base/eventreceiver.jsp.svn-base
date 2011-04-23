<%
	System.out.println("---- Receiving Event ----");
	java.util.Enumeration keys = request.getParameterNames();
	while(keys.hasMoreElements()){
		java.lang.String key = (java.lang.String) keys.nextElement();
		java.lang.String value = (java.lang.String) request.getParameter(key);
		value = value.substring(0, value.indexOf("</event>") + 8); 

		org.apache.xerces.parsers.DOMParser parser = new org.apache.xerces.parsers.DOMParser();
		java.io.StringReader reader = new java.io.StringReader(value);
		org.xml.sax.InputSource source = new org.xml.sax.InputSource(reader);
		parser.parse(source);
		org.w3c.dom.Document document = parser.getDocument();
		
		org.w3c.dom.Node node = org.apache.xpath.XPathAPI.selectSingleNode(document,"/event/metadata");
		org.w3c.dom.NamedNodeMap namenodemap = node.getAttributes();
		for(int i=0; i < namenodemap.getLength(); i++){
		  org.w3c.dom.Node attnode = namenodemap.item(i);
		  System.out.println(attnode.getNodeName() + " - " + attnode.getNodeValue());
		}
	}
%>