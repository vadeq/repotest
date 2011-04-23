/**
 * Developed by Swasen Inc
 */
package zws.repository.R8;
import java.io.StringReader;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.xerces.parsers.DOMParser;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import zws.data.Metadata;
import zws.data.MetadataBase;
/**
 * @author Senthil Swaminathan
 *
 */
public class R8ILSearchAgent { 

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		{} //System.out.println("Invoking....");
		queryMetaData();

	}
	//
	
	public static void queryMetaData() throws Exception {
		HashMap originHM = new HashMap();
		originHM.put("R8_WBSERVICE_URL", "http://plm.najanaja.com/Windchill/servlet/RPC?CLASS=com.infoengine.soap");
		originHM.put("R8_USERNAME", "ilinkadmin");
		originHM.put("R8_PASSWORD", "ilinkadmin");
		originHM.put("R8_OPERATION", "remote-invoke");
		
		HashMap dataHM = new HashMap();
		dataHM.put("method", 		"Query-Objects");
		dataHM.put("attribute", 	"name,number,versionInfo.identifier.versionId,state.state");
		dataHM.put("type", 		    "wt.epm.EPMDocument");
		dataHM.put("where", 		"name=Fitting");
		dataHM.put("frompage", "first");
		String xmlResult = R8Invoke.invokeMethod(originHM, dataHM);
		{} //System.out.println("The result is: " + xmlResult);
		
		materializeMetadata(xmlResult);
		
		//Document document = getDocumentByString(xmlResult);
		{} //System.out.println("The state is: " + getSingleValue(document, "/wc:COLLECTION/wt.epm.EPMDocument/wc:INSTANCE/state.state"));
	}
	
	/*	
	<wc:COLLECTION xmlns:wc="http://www.ptc.com/infoengine/1.0">
	<wt.epm.EPMDocument NAME="output" TYPE="Unknown" STATUS="0">
	  <wc:INSTANCE>
	    <obid>VR:wt.epm.EPMDocument:6377:906062161-1181842747437-835474-9-1-168-192@plm.najanaja.com</obid>
	    <class>WCTYPE|wt.epm.EPMDocument|com.najanaja.plm.DefaultEPMDocument</class>
	    <name>Fitting</name>
	    <versionInfo.identifier.versionId>A</versionInfo.identifier.versionId>
	    <number>10001.PRT</number>
	    <state.state>INWORK</state.state>
	  </wc:INSTANCE>
	</wt.epm.EPMDocument>
	</wc:COLLECTION>
	*/
	
	
	
	//windchill -classpath=C:\zws-dojo\Workspace\plugin-ilink8\out;C:\zws-dojo\Workspace\zws-client\out zws.repository.R8.R8ILSearchAgent
	  public static Metadata materializeMetadata(String xmlResult) throws Exception {
		  Document document = getDocumentByString(xmlResult);
		  NodeList nodeList = XPathAPI.selectNodeList(document, "/wc:COLLECTION//wc:INSTANCE");
		  
		  
		  HashMap hashMap = new HashMap();
		  for(int i=0; i < nodeList.getLength(); i++){
			  Node node = nodeList.item(i);
			  NodeList childNodeList = node.getChildNodes();
			  for(int j=0; j < childNodeList.getLength(); j++){
				  Node childNode = childNodeList.item(j);
				  if(childNode.getNodeType() == Node.TEXT_NODE){ continue; }
				  if(childNode.getFirstChild() != null){
					  hashMap.put(childNode.getNodeName(), childNode.getFirstChild().getNodeValue());
				  }
			  }
		  }
		  
		  String obid = (String) hashMap.get("obid");
		  MetadataBase m = new MetadataBase();
		  if(obid != null){
			  //Origin o = OriginMaker.materialize(domainName, serverName, Origin.FROM_ILINK_8, getRepositoryName(), null, obid, null, null);  
		  }
		  
		  String attributes = "name,number,versionInfo.identifier.versionId,state.state";
		  StringTokenizer tokens = new StringTokenizer(attributes, ",");
		  while(tokens.hasMoreTokens()){
			  String key = tokens.nextToken();
			  String value = (String) hashMap.get(key);
			  m.set(key, value);
		  }
		  {} //System.out.println(m.toString());
		  
		  return m;
	  }
	
	public static Document getDocumentByString(String xmlString) throws Exception {

        DOMParser parser = new DOMParser();        
        StringReader reader = new StringReader(xmlString);
        InputSource source = new InputSource(reader);
        parser.parse(source);
        Document document = parser.getDocument();	
        return document;
    }
	    
    public static String getSingleValue(Document document, String xpath) throws Exception { 	
        Node node = XPathAPI.selectSingleNode(document, xpath);
        try{ return node.getFirstChild().getNodeValue();  }catch(NullPointerException e){ }
        return null;
    }  	

}
