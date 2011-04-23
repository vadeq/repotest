package zws.qx.service;

import java.io.StringReader;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class ServiceParser extends DefaultHandler {
  ServiceRecord serviceRecord = null;
  public ServiceParser() {serviceRecord = new ServiceRecord();}
  public void startElement(String uri, String localName, String qName, Attributes attribs) {
    try {
      if ("service".equalsIgnoreCase(localName)) {
        prepareServiceRecord(attribs);
      } 
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void endElement(String uri, String localName, String qName) { }
  private void prepareServiceRecord(Attributes attribs)
      throws Exception {
    String key, value;
    for (int idx = 0; idx < attribs.getLength(); idx++) {
      key = attribs.getQName(idx);
      value = attribs.getValue(idx);
      serviceRecord.set(key, value);
    }
    {} //System.out.println(" record... " + serviceRecord.toXML());
  }
  
  public ServiceRecord  getServiceRecord() {
    return serviceRecord;
  }

  public static void main(String[] args) {
    InputSource src;
    try { 
    //  src = new InputSource(new FileInputStream(file));
      StringReader strRdr = new StringReader("<service-config><service name='recorderService' protocol='http' host='designstate-0'/></service-config>");
      //InputSource wrapping a StringReader wrapping your String,
      src = new InputSource(strRdr);
      ServiceParser insParser = new ServiceParser();
      XMLReader rdr = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
      rdr.setContentHandler(insParser);
      rdr.setFeature("http://xml.org/sax/features/validation", false);
      rdr.parse(src); 
      ServiceRecord recordings = insParser.getServiceRecord();
      {} //System.out.println(recordings.get("name"));
    } catch (Exception e) {
        e.printStackTrace();
    }
  }

}
