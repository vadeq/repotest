package zws.search;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.filter.ListFilter;
import zws.data.filter.UnitFilter;
import zws.exception.InvalidSyntax;
import zws.exception.UnsupportedOperation;
import zws.op.ThreadedOp;
import zws.search.criteria.Criteria;
import zws.search.criteria.modifier.CriteriaModifier;
import zws.security.Authentication;
import zws.util.AdapterPrototype;
import zws.util.Named;

import java.util.Collection;
import java.util.Comparator;


public interface SearchAgent extends ThreadedOp, Named {
  public void search() throws Exception;
  public Collection getResults();

  public String getName();
  public String getDescription();

  public String getQueryTarget();
  public Criteria getCriteria();
  public void setCriteria(String s) throws InvalidSyntax;
  public void setCriteria(Criteria c);
  public String getSelect();
  public void setSelect(String s);
  public String getOrderBy();
  public void setOrderBy(String s);
  public boolean getAscending();
  public void setAscending(boolean b);
  public int getSkipCount();
  public void setSkipCount(int i);
  public int getMaxCount();
  public void setMaxCount(int i);
  public Authentication getAuthenticationToken();
  public void setAuthenticationToken(Authentication token);
  public boolean getAuthenticationRequired();
  public void setAuthenticationRequired(boolean b);
  public boolean getChooseOnlyBinaries();
  public void setChooseOnlyBinaries(boolean b);
  public boolean getIncludeHistory();
  public void setIncludeHistory(boolean b); 
  public boolean getIncludeDependencies();
  public void setIncludeDependencies(boolean b);
  public String getDatedAfter();
  public void setDatedAfter(String s);
  public String getDatedBefore();
  public void setDatedBefore(String s);  
  public Comparator getSortComparator();
  public void setSortComparator(Comparator c);

  public void setFiltersEnabled(boolean b);
  public void setTransformersEnabled(boolean b);
  public void setCriteriaModifiersEnabled(boolean b);
  
  public void add(UnitFilter f) throws UnsupportedOperation;
  public void add(ListFilter f) throws UnsupportedOperation;
  public void add(CriteriaModifier m) throws UnsupportedOperation;

  public Collection getUnitFilters();
  public Collection getCriteriaModifiers();
  public Collection getListFilters();

  public void remove(UnitFilter f) throws UnsupportedOperation;
  public void remove(ListFilter f) throws UnsupportedOperation;
  public void initializeStorage(AdapterPrototype adapter) throws Exception ;

  public void enableCache() throws UnsupportedOperation;
  public void disableCache() throws UnsupportedOperation;
}
