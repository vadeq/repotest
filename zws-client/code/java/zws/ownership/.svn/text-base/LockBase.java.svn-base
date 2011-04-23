package zws.ownership; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 15, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;

public class LockBase implements Lock, Serializable {
  public LockBase() {}
  public LockBase(String itemName, String ownedBy) { name=itemName; owner=ownedBy; }
  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public String getOwner() { return owner; }
  public void setOwner(String s) { owner=s; }
  
  private String name=null;
  private String owner=null;
}
