package zws.data.filter;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.ListOp;

import java.util.Collection;


public interface ListFilter extends Filter, ListOp {
  public void filter(Collection c) throws Exception; //removes item from c if keep(item)==false;
  public boolean keep(Object o) throws Exception;
}
