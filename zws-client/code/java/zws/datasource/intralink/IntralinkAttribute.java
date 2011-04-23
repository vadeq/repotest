package zws.datasource.intralink; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;

public class IntralinkAttribute implements Serializable {
  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public String getType() { return type; }
  public void setType(String s) { type=s; }
  public String getDescription() { return description; }
  public void setDescription(String s) { description =s; }
  public boolean getIsUserDefined() { return isFileBased; }
  public void setIsUserDefined(boolean b) { isUserDefined= b; }
  public boolean getIsFileBased() { return isUserDefined; }
  public void setIsFileBased(boolean b) { isFileBased = b; }
  public boolean getIsLifeCycle() { return isLifeCycle; }
  public void setIsLifeCycle(boolean b) { isLifeCycle = b; }
  public boolean getIsVersioned() { return isVersioned; }
  public void setIsVersioned(boolean b) { isVersioned= b; }
  public boolean getIsIndexed() { return isIndexed; }
  public void setIsIndexed(boolean b) { isIndexed= b; }
  private String name;
  private String type;
  private String description;
  private boolean isFileBased=false;
  private boolean isLifeCycle=false;
  private boolean isVersioned=false;
  private boolean isIndexed=false;
  private boolean isUserDefined=false;
}
