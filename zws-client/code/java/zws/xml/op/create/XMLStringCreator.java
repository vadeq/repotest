package zws.xml.op.create; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0s
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.*;

public class XMLStringCreator extends CreateFunctor {
  public XMLStringCreator() { }

  public final Object create() throws CreateException { return createXMLString(); }
  public String createXMLString() throws CreateException {
    if (!getIsTag()) return getData();
    if (!getIsOpenTag()) return "</"+getData()+">";
    String xml;
    xml = "<"+getData();
    Set atts = getAttributes().entrySet();
    Iterator i = atts.iterator();
    while (i.hasNext()){
      Map.Entry e = (Map.Entry)i.next();
      xml += " " + e.getKey().toString() + "=\""+e.getValue().toString()+"\"";
    }
    xml += ">";
    return xml;
  }

  public String getData() { return data; }
  public void setData(String data) { this.data = data; }
  public Map getAttributes() { return attributes; }
  public void setAttributes(Map attributes) { this.attributes = attributes; }
  public boolean getIsTag(){ return isTag; }
  public void setIsTag(boolean isTag){ this.isTag = isTag; }
  public boolean getIsOpenTag(){ return isOpenTag; }
  public void setIsOpenTag(boolean isOpenTag){ this.isOpenTag = isOpenTag; }

  private String data;
  private Map attributes = null;
  private boolean isTag = false;
  private boolean isOpenTag = false;
}