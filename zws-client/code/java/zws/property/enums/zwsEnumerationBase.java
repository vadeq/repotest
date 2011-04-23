package zws.property.enums; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Nov 18, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.property.zwsPropertyBase;
import zws.exception.InvalidType;

import java.util.*;
import java.io.Serializable;

public abstract class zwsEnumerationBase implements zwsEnumeration, Serializable {
  public Collection getEnumerations() { return enumerations; }
  
  public void addChoice(String choice) throws InvalidType{  
	  zwsPropertyBase.convertType(choice, getPrimitiveType());
    enumerations.add(choice.trim());
  }
  
  public void addChoices(Collection choices) throws InvalidType{
    if (null==choices) return;
    Iterator i = choices.iterator();
    while (i.hasNext()) addChoice(i.next().toString());
  }
  
  public void removeChoice(String choice) {
    int idx = enumerations.indexOf(choice);
    if (-1==idx) return; // not in list
    enumerations.remove(idx);
  }

  public void moveUp(String choice) {
    int idx = enumerations.indexOf(choice);
    if (-1==idx) return; // not in list
    if (0==idx) return; // already top of list
    enumerations.remove(idx);
    idx--;
    enumerations.add(idx, choice);
  }

  public void moveDown(String choice) {
	  int idx = enumerations.indexOf(choice);
	  if (-1==idx) return; // not in list
	  int idxLast=enumerations.size()-1;
	  if ( idxLast==idx) return; // already bottom of list
	  enumerations.remove(idx);
	  idx++;
	  enumerations.add(idx, choice);
  }  
  
  public abstract String getPrimitiveType();

  public boolean getIsFree() { return isFree; }
  public boolean getIsChangeable() { return isChangeable; }

  public void setIsFree(boolean b){ isFree=b; }
  public void setIsChangeable(boolean b) { isChangeable=b; }

  private ArrayList enumerations = new ArrayList();
  private boolean isFree=false;
  private boolean isChangeable=false;
}
