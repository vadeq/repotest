package zws.repository.ilink3.qx.client.op.xml;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 15, 2004, 8:53 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.*;
import zws.log.failure.Failure;
import zws.bill.intralink.BillOfMaterials;

import java.util.*;

import org.xml.sax.Attributes;

public class GetBillHandler extends IntralinkResultHandler {

  public void startElement (String uri, String name, String qName, Attributes atts) { 
        if ( qName.equalsIgnoreCase("metadata")) { pushComponent(atts); return; }
        if ( qName.equalsIgnoreCase("instance")) { pushInstance(atts); return; }
        if ( qName.equalsIgnoreCase("sub-component")) { pushSubComponent(atts); return; }
        try { 
          if ( qName.equalsIgnoreCase("failed-to-authenticate")) { getStorable().log(new Failure("err.invalid.authentication")); return; } 
        }
        catch (Exception e) { e.printStackTrace(); }
      }
      
      public void endElement (String uri, String name, String qName) {
        if ( !(qName.equalsIgnoreCase("metadata") || qName.equalsIgnoreCase("instance") || qName.equalsIgnoreCase("sub-component"))) return; 
        Metadata parent=null;
        Metadata m = (Metadata)stack.pop();
        if (!stack.isEmpty()) parent = (Metadata)stack.peek();
        if (null==parent) components.add(m);
        else if (m instanceof zws.data.MetadataFamilyInstance) parent.addFamilyInstance((MetadataFamilyInstance)m);      
        else if (m instanceof zws.data.MetadataSubComponent) parent.addSubComponent((MetadataSubComponent)m);      
      }

      private void pushComponent(Attributes atts) {
        try {
          Metadata data = unmarshallComponent(atts);
          stack.push(data);
        }
        catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
      }

      private void pushInstance(Attributes atts) {
          try {
            Metadata data = unmarshallComponent(atts);
            MetadataFamilyInstanceBase member = new MetadataFamilyInstanceBase(data);
            stack.push(member);
          }
          catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
        }

      private void pushSubComponent(Attributes atts) {
          try {
            Metadata data = unmarshallComponent(atts);
            MetadataSubComponentBase sub = new MetadataSubComponentBase(data);
            stack.push(sub);
          }
          catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
        }

      public BillOfMaterials getBillOfMaterials(){
        if (components.size()!=1) return null; 
        Metadata m = (Metadata) components.toArray()[0];
        BillOfMaterials bill = new BillOfMaterials(m);
        return bill;
      }

      public Collection getResults() { return components; }
      
      private Collection components = new Vector();
      private Stack stack = new Stack();
}
