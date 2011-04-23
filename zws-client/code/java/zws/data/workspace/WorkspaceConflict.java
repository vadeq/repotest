package zws.data.workspace; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 15, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import java.util.*;

public interface WorkspaceConflict {
  public String getName();
  public String getConflictKey();
  public Map getParameters();
  public String get(String parameter);
}
