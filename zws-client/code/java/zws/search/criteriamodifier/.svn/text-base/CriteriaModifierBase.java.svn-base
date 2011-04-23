package zws.search.criteriamodifier;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 26, 2004, 2:58 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.OpBase;

public abstract class CriteriaModifierBase extends OpBase implements CriteriaModifier {
  
  public void execute() throws Exception { setResult(modify(getCriteria())); }
  
  public abstract String modify(String crit) throws Exception;
  
  public String getCriteria() { return criteria; }
  public void setCriteria(String s) { criteria=s; }
  private String criteria=null;
}
