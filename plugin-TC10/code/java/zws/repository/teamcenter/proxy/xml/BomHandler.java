package zws.repository.teamcenter.proxy.xml;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 15, 2004, 8:53 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataBinaryBase;
import zws.data.MetadataFamilyInstance;
import zws.data.MetadataSubComponent;
import zws.data.MetadataSubComponentBase;

import java.util.Collection;
import java.util.Stack;
import java.util.Vector;

import org.xml.sax.Attributes;

public class BomHandler extends TC10ResultHandler {

  public void startElement (String uri, String name, String qName, Attributes atts) {
        if ( !binaryMode && qName.equalsIgnoreCase("metadata")) { pushComponent(atts); return; }
        if ( !binaryMode && qName.equalsIgnoreCase("sub-component")) { pushSubComponent(atts); return; }

        if(qName.equalsIgnoreCase("binaries"))  binaryMode=true;
        if(binaryMode && qName.equalsIgnoreCase("binary-data")) {
            MetadataBase binaryData;
            try {
              binaryData = (MetadataBase)unmarshallComponent(atts);
              Metadata data = (Metadata) stack.peek();
              data.addBinary(new MetadataBinaryBase(binaryData));
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        //add parsing of exceptions
        /*
        try {
          if ( qName.equalsIgnoreCase("failed-to-authenticate")) { getStorable().log(new Failure("err.invalid.authentication")); return; }
        }
        catch (Exception e) { e.printStackTrace(); }
         */
        //ignoring all other tags  for now
        // need to add ability to materialize IMANFileOrigins and add to metadata
      }

      public void endElement (String uri, String name, String qName) {
        if ("binaries".equals(qName)) {
          binaryMode = false;
          return;
        }
        if (!(qName.equalsIgnoreCase("metadata") ||
               qName.equalsIgnoreCase("instance") ||
               qName.equalsIgnoreCase("sub-component"))) return;
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
      private boolean binaryMode = false;
}
