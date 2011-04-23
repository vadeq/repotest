package zws.mapping;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 19, 2004, 12:48 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.mapping.attribute.*;

public class Test {
  public static void main(String[] a) {
   Test t = new Test();
   t.run();
  }
  
  public void run(){
    try {
      Metadata data = new MetadataBase();
      Metadata target = new MetadataBase();
      data.set("name", "fooo");
      data.set("folder", "/root/a place/here");
      data.set("revision", "E");
      data.set("title", "Bar");
      data.set("desc", " foo bar");
      {} //System.out.println(data);
      MetadataMappingInstructions maps = new MetadataMappingInstructions();
      RenameAttribute fMap = new RenameAttribute();
      fMap.setFieldName("title");
      fMap.setNewFieldName("top");
      maps.add(fMap);
      
      EnumerationMapping eMap = new EnumerationMapping();
      eMap.setFieldName("revision");
      eMap.addValueMapping("A", "XO");
      eMap.addValueMapping("B", "YO");
      eMap.addValueMapping("C ", "ZO");
      eMap.addValueMapping("D", "WO");
      eMap.addValueMapping(" E  ", "TRex");
      eMap.setNewFieldName("rev");
      eMap.setCopyIfUnmapped(true);
      maps.add(eMap);
      
      LocationMapping lMap = new LocationMapping();
      lMap.setFieldName("folder");
      lMap.setNewFieldName("location");
      lMap.addAbsoluteLocationMapping("g/root/a place/here","/newplace0");
      lMap.addAbsoluteLocationMapping("a/root/a place/here","/newplace1");
      lMap.addAbsoluteLocationMapping("a/root/a place/here","/newplace2");
      lMap.addRootLocationMapping("/root", "/newRoot");
      lMap.addRootLocationMapping("/root/a place", "/newRoot3");
      lMap.addRootLocationMapping("/root/a place/here/a", "/newRoot3");
      lMap.setCopyLocationIfMappingIsNotDefined(true);
      maps.add(lMap);
      
      maps.setSource(data);
      maps.setTarget(target);
      maps.map();
      {} //System.out.println(target);
    }
    catch(Exception e) { e.printStackTrace(); }
  }
}
