package zws.datasource.intralink; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import org.xml.sax.helpers.DefaultHandler;

//import com.agile.api.*; 
//import com.agile.api.APIException; 
//import com.agile.api.AgileSessionFactory; 
//import com.agile.api.IAgileSession; 

public class BillHandler extends DefaultHandler {
  /*
  public void startDocument() { 
    String user = "INTRALINK";
    String pass= "INTRALINK";
    boolean connected = IntralinkSource.AgileConnect(null, user, pass);
    {}//Logwriter.printOnConsole("Connected : " + connected);
  }

  public void endDocument() { 
    {}//Logwriter.printOnConsole("end of doc");
    createAssembly("asm2"); 
    mode=NORMAL;
    kids.clear();
    IntralinkSource.disconnect();
  }
  
  
  public void startElement (String uri, String name, String qName, Attributes atts) { 
    {}//Logwriter.printOnConsole("start of " + qName);
    if (mode == NORMAL && qName.equalsIgnoreCase("part")) createPart(atts.getValue("name"));
    if (mode == BILL && qName.equalsIgnoreCase("part")) {
      {}//Logwriter.printOnConsole("adding part" + qName);
      kids.add(atts.getValue("name"));
    }
    if (qName.equalsIgnoreCase("assembly")) mode=BILL;
  }

  public void endElement(String uri, String name, String qName, Attributes atts) { 
  }
  
  
  public void createPart(String name) {
    try {
      {}//Logwriter.printOnConsole("creating part");
      IItem o=(IItem)IntralinkSource.createAgileObject(name);
      {}//Logwriter.printOnConsole(o.getValue(ItemConstants.ATT_TITLE_BLOCK_NUMBER).toString());
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  public void createAssembly(String name) {
    try {
      {}//Logwriter.printOnConsole("creating asm");
      IntralinkSource.agilePutChildren (name, kids);
    }
    catch (Exception e) { e.printStackTrace(); }
  }  

  private Vector kids = new Vector();
  
  private static int BILL=1;
  private static int NORMAL=2;
  private static int mode=NORMAL;
  
*/
}
