package zws.search.criteria.parser;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on June 28, 2004, 5:24 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidSyntax;
import zws.op.Op;
import zws.search.criteria.Criteria;
import zws.util.StringMapper;

import java.util.Collection;

public interface CriteriaParser extends Op {
  public Criteria parse(String criteria) throws InvalidSyntax;

  public String getCriteria();
  public void setCriteria(String s);
  
  public Collection getORList();
  public Collection getANDList();
  public Collection getGroups();
  public Collection getComparisons();
    
  
  //what displayed parameters
  public String getANDOperatorDisplay();
  public void displayAsANDOperator(String operatorDisplay);
  public StringMapper getFieldNameMapper();
  public void setFieldNameMapper(StringMapper mappingOp);
  
  public String getOROperatorDisplay();
  public boolean isValueDisplayedInQuotes();
  public void displayValueInQuotes(boolean displayInQuotes);
}
