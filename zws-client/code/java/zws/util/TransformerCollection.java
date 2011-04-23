package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;
import java.util.Collection;

public interface TransformerCollection extends Collection, Serializable {
  public void bind(Collection c);
  public Transformer getTransformer();
  public void setTransformer(Transformer t);
}