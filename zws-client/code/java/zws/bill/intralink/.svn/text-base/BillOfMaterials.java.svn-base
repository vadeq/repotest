package zws.bill.intralink;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 15, 2004, 12:56 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.xml.xslt.Stylizer;
import zws.application.Properties;
import zws.application.Names;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

public class BillOfMaterials implements Serializable {
  public BillOfMaterials() {}
  public BillOfMaterials(Metadata data) { metadata=data; }
  
  public Metadata getMetadata() { return metadata; }
  public void setMetadata(Metadata data) { metadata=data; }
  public String structuredXML () throws Exception {
    {} //System.out.println(metadata);
    {} //System.out.println("Styling with: " + STRUCTURED_XML);
    byte[] xml = metadata.toString().getBytes(); 
    Stylizer stylizer = new Stylizer();
    stylizer.addProcessingInstruction(STRUCTURED_XML);
    ByteArrayInputStream source = new ByteArrayInputStream(xml);
    return stylizer.styleXML(source).toString();
  }
  public String flatXML () throws Exception {
    byte[] xml = metadata.toString().getBytes(); 
    Stylizer stylizer = new Stylizer();
    stylizer.addProcessingInstruction(FLAT_XML);
    ByteArrayInputStream source = new ByteArrayInputStream(xml);
    return stylizer.styleXML(source).toString();
  }  
  public String flatCSV () throws Exception {
    byte[] xml = metadata.toString().getBytes(); 
    Stylizer stylizer = new Stylizer();
    stylizer.addProcessingInstruction(FLAT_CSV);
    ByteArrayInputStream source = new ByteArrayInputStream(xml);
    return stylizer.styleXML(source).toString();
  }
  
  private Metadata metadata;
  private String stylesheet=STRUCTURED_XML;
  private static String STRUCTURED_XML  = Properties.get(Names.XSLT_DIR) + Names.PATH_SEPARATOR+"bill"+Names.PATH_SEPARATOR+"intralink"+Names.PATH_SEPARATOR+"metadata2structuredXML.xslt";
  private static String FLAT_XML = Properties.get(Names.XSLT_DIR) + Names.PATH_SEPARATOR+"intralink"+Names.PATH_SEPARATOR+"metadata2flatXML.xslt";
  private static String FLAT_CSV = Properties.get(Names.XSLT_DIR) + Names.PATH_SEPARATOR+"bill"+Names.PATH_SEPARATOR+"intralink"+Names.PATH_SEPARATOR+"metadata2CSV.xslt";
}
