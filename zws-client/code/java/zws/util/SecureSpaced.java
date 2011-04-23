package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 22, 2004, 5:56 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public interface SecureSpaced extends Namespaced, Named {
  public String getName();
  public Namespace getNamespace();
  public String getDescription();
  public String getType();
}
