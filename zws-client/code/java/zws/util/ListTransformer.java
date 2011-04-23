package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Collection;

public interface ListTransformer extends Transformer, ListAdapter {
  public void adapt(Collection c) throws Exception;
  public Collection transformList() throws Exception;
}
