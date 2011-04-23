package zws.event.custom.cisco;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 2:42 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import zws.origin.OriginMaker;

public class PublishLibraryItem extends CustomCiscoEventBase {
	public PublishLibraryItem() {
	  setAction(PUBLISH_LIB_ITEM); 	    
	}
	
	public PublishLibraryItem(Origin o) {
    setAction(PUBLISH_LIB_ITEM);
	  setOrigin(o); 
	}

  public Origin getOrigin() {
   String s = getString(ORIGIN);
   Origin o = null;
   try { o = OriginMaker.materialize(s); }
   catch(Exception e) { e.printStackTrace(); }
   return o;
  }

  public void setOrigin(Origin o) { set(ORIGIN, o); }

  private static String PUBLISH_LIB_ITEM="publish-library-item";
  private static String ORIGIN="origin";
}
