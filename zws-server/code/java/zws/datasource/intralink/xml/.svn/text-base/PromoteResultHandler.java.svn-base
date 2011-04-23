package zws.datasource.intralink.xml;
import zws.exception.MetadataException;
import zws.log.failure.Failure;
import zws.log.warning.Warning;

import org.xml.sax.Attributes;

public class PromoteResultHandler extends IntralinkResultHandler {
	public void startElement( String uri, String localName, String qName, Attributes atts ) {
    try { 
  	  if(qName.equals("lock-detected")) getStorable().log(new Failure("lock.detected"));
      else if(qName.equals("not-authorized")) getStorable().log(new Failure("not.authorized"));
      else if(qName.equals("object-ignored")) getStorable().log(new Warning("object.ignored", new MetadataException(unmarshallComponent(atts), "object.ignored")));
      else if(qName.equals("object")) getStorable().store(unmarshallComponent(atts));
    }
    catch (Exception e) { e.printStackTrace(); }
  }
}
