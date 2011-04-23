package zws.search.criteria.modifier;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on June 30, 2004, 2:33 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.Op;
import zws.search.criteria.Criteria;

public interface CriteriaModifier extends Op {
  public Criteria modify(Criteria c) throws Exception;
}
