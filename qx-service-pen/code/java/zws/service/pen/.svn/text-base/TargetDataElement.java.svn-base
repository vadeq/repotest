package zws.service.pen;
/*
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on May 25, 2007 12:09:48 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataSubComponentBase;
import zws.exception.InvalidName;
import zws.qx.QxContext;
//import zws.util.{}//Logwriter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TargetDataElement {
  public TargetDataElement(PENDataElement e) { penDataElement = e; }

  public void setTargetData(Metadata data) { 
    if(null==data) return;
    targetData = data;
    this.getPENData().setTargetReference(getPenDataElement());
    }
  public Metadata getTargetData() { return targetData;  }

  public PENData getPENData() { return penDataElement.getPENData(); }
  public PENDataElement getPenDataElement() { return penDataElement; }
  
   public Collection getSubComponents() throws Exception{
     if (null==targetData) return new ArrayList();
     return targetData.getSubComponents();
   }

   public Collection getSubComponentRefNames() {
     Collection subComponentNames = new ArrayList();
     if (null==targetData) return subComponentNames;
     Iterator itr = targetData.getSubComponents().iterator();
     Metadata sub=null;
     while(itr.hasNext()) {
       sub = (Metadata)itr.next();
       subComponentNames.add(sub.getName());
     }
     return subComponentNames;
   }

   public boolean containsSubcomponent(String subcomponentName) {
     if (null==targetData) return false;
     return targetData.hasSubComponent(subcomponentName);
  }
  

  private Metadata targetData = null;
  private PENDataElement penDataElement = null;
  //private ArrayList subcomponents = new ArrayList();
  //private Map subcomponentContexts = new HashMap();  
}
