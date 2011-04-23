package zws.property.enums; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Nov 18, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidType;

import java.util.*;

public interface zwsEnumeration {
  public Collection getEnumerations();
  public void addChoice(String choice) throws InvalidType; 
  public void addChoices(Collection choices) throws InvalidType;
  public void removeChoice(String choice);

  public void moveUp(String choice);
  public void moveDown(String choice);

  public boolean getIsFree();
  public boolean getIsChangeable();
  public String getPrimitiveType();
 
}
