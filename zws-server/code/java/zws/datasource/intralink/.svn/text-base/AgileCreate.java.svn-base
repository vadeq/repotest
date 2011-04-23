package zws.datasource.intralink;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 22, 2004, 12:46 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
//import com.agile.api.*;
//import com.agile.api.APIException;
//import com.agile.api.AgileSessionFactory;
//import com.agile.api.IAgileSession; 

import java.io.File;
import java.io.FileReader;

import javax.xml.parsers.*;

import org.xml.sax.*;

public class AgileCreate {
	public static void main(String[] args) {
    AgileCreate creator = new AgileCreate();
    try { creator.loadBill(); }
    catch (Exception e) { e.printStackTrace(); }
  }
  
  public void loadBill() throws Exception {
    BillHandler handler = new BillHandler();
    XMLReader xr = getParser(false).getXMLReader();
    xr.setContentHandler(handler);
    xr.setErrorHandler(handler);
    File xml = new File("C:\\bill.xml");
    FileReader r = new FileReader(xml);
    xr.parse(new InputSource(r));
  }
  
  
  
  protected static SAXParser getParser(boolean validate) throws SAXException, ParserConfigurationException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(validate);
    return factory.newSAXParser();
  }

/*  
  public void firstTest() {
		String item;
//		String url = args[0];
		boolean connected = false;
		//String user = args[1];
		//String pass = args[2];
		//test login
    String user = "INTRALINK";
    String pass= "INTRALINK";
    try {
      connected = IntralinkSource.AgileConnect(null, user, pass);
      {}//Logwriter.printOnConsole("Connected : " + connected);
      IItem o=(IItem)IntralinkSource.createAgileObject("xyz");
      {}//Logwriter.printOnConsole(o.getValue(ItemConstants.ATT_TITLE_BLOCK_NUMBER).toString());
      IntralinkSource.disconnect();
    }
    catch (Exception e) { e.printStackTrace(); }
	}   // main
 */
}
