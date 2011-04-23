package zws.datasource.intralink.xml; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.intralink.RTPForm;
import zws.log.failure.Failure;

import java.util.Iterator;

import org.xml.sax.Attributes;

public class PromoteFormSearchResultHandler extends IntralinkResultHandler {
  private RTPForm form;
  public void startElement (String uri, String name, String qName, Attributes atts) {
    try {
      if ( qName.equalsIgnoreCase("promotion-form")) { form = unmarshallRTPForm(atts); return; } 
      if ( qName.equalsIgnoreCase("metadata")) { 
        Metadata data = unmarshallComponent(atts);
        if (null==form.getPromoteTo()) form.setPromoteTo(data.get(PROMOTE_TO));
        if (!form.getPromoteTo().equals(data.get(PROMOTE_TO))) form.setPromoteTo(form.getPromoteTo() + "|"+data.get(PROMOTE_TO));
        form.add(data); return; 
      }
      if ( qName.equalsIgnoreCase("failed-to-authenticate")) { getStorable().log(new Failure("err.invalid.authentication")); return; }
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  public void endElement (String uri, String name, String qName) {
    try { 
      if ( qName.equalsIgnoreCase("promotion-form")) {
        RTPForm f= filter(form);
        if (null!=f) getStorable().store(f); 
      }
    }
    catch (Exception e) { e.printStackTrace(); }
  }   
  
  public RTPForm filter(RTPForm f) {
    if (0==f.getComponents().size()) return null;
    if (formIsApproved(f)) return f;
    return null;      
  }
  
  private boolean formIsApproved(RTPForm f) {
    //hack to see if objects in promote form have a releaselevel > the form's promote-to field
    //if all objects are above promot-to release level, then form may have been approved
    // this is not guarenteed. to guarentee form has been approved - toolkit should query status field of form
    boolean approved=true;
    Metadata data;
    Iterator i = f.getComponents().iterator();
    while (i.hasNext() && approved) {
      data = (Metadata)i.next();
      String releaseLevel = data.get(getDatasource().RELEASE_LEVEL);
      if ( getDatasource().getSequenceForReleaseLevel(releaseLevel) < 
           getDatasource().getSequenceForReleaseLevel(f.getPromoteTo()) ) approved=false;
    }
    return approved;
  }
  private static String PROMOTE_TO="promote-to";
}
