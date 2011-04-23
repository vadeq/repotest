package zws.datasource.pkg;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 7:29 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.data.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Vector;

public class PkgTest {
  public static void main(String[] args) {
   PkgTest test = new PkgTest();
   test.run();
  }
  
  
  public void run(){
    try{
     Properties.set(Names.PATH_PACKAGE_ROOT, "\\zws\\data\\pkg");
     
     PackageSourceBase source = new PackageSourceBase();
     source.setName("pkg1");
     MetadataBase data = createMetadata("newbinaryfile.txt");
     Collection c = new Vector();
     c.add(createMetadataSubComponent("c1"));
     c.add(createMetadataSubComponent("c2"));
     data.setSubcomponents(c);
     Collection f = new Vector();
     f.add(createMetadataFamilyInstance("i1"));
     f.add(createMetadataFamilyInstance("i2"));
     f.add(createMetadataFamilyInstance("i3"));
     data.setFamilyInstances(f);
     //data.setName("new name");
     data.set("Revision", "R2");
     data.set("Version", "D2");
     File in = new File("\\ZWS\\data\\inbinary.txt");
     long len = in.length();
     FileInputStream iStream = new FileInputStream(in);
     source.addBinary(data.getOrigin(), iStream, null);
     source.addMetadata(data, null);
     //source.moveBinary("newbinaryfile.txt", "new-place", null);
     //source.moveMetadata("newbinaryfile.txt", "new-place", null);
    }
    catch (Exception e) { e.printStackTrace(); }
  }
    
  private MetadataBase createMetadata(String name) {
    /*
     Origin o = new Origin(null, null, null, 0, name);
     o.setName(name);
     MetadataBase data = new MetadataBase();
     data.setOrigin(o);
     return data;
     */
    return null;
  }
  private MetadataSubComponentBase createMetadataSubComponent(String name) {
     MetadataSubComponentBase data = new MetadataSubComponentBase(createMetadata(name));
     return data;
  }
  private MetadataFamilyInstanceBase createMetadataFamilyInstance(String name) {
     MetadataFamilyInstanceBase data = new MetadataFamilyInstanceBase(createMetadata(name));
     return data;
  }
}
