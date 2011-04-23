package zws.data.filter;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;

public interface Filter extends Serializable {
  public Object filter(Object o) throws Exception;
  public boolean keep(Object o) throws Exception;
}
