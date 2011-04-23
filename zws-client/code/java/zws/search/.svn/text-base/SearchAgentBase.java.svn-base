package zws.search; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.filter.ListFilter;
import zws.data.filter.UnitFilter;
import zws.data.util.MetadataTransformerBase;
import zws.exception.InvalidSyntax;
import zws.exception.UnsupportedOperation;
import zws.log.warning.Warning;
import zws.op.ThreadedOpBase;
import zws.search.criteria.Criteria;
import zws.search.criteria.modifier.CriteriaModifier;
import zws.search.criteria.parser.CriteriaParser;
import zws.search.criteria.parser.CriteriaParserBase;
import zws.security.Authentication;
import zws.util.*;

import java.util.*;

public abstract class SearchAgentBase extends ThreadedOpBase implements SearchAgent, Storable {
  public final void executeRun() throws Exception {
    initialize();
    search(); 
  }

  public void search() throws Exception {
    clearLogs();
    //if (null==getResults()) prepareStorage();
    //+++ todo create a TransformerTreeSet
    getResults().clear();
    if (modifiersEnabled) modifyCriteria();
    executeQuery();
    if (filtersEnabled) filterResults();
    if (transformersEnabled) transformResults();
    //{} //System.out.println(this);
  }

  public abstract void executeQuery() throws Exception;
  public abstract void addSystemAttributes(Metadata data); 
  
  public void store(Object o) throws Exception { store((Metadata) o); }
  public void store(Metadata data) throws Exception { 
    if (keep(data)) {
      addSystemAttributes(data);
      getResults().add(transform(data));
    }
  }
  public void store(Collection c) throws Exception { 
    Iterator i = c.iterator();
    while (i.hasNext()) store((Metadata)i.next());
  }

  private final boolean keep(Metadata data) {
    if(!filtersEnabled) return true;
    if (null==getUnitFilters()) return true;
    Iterator i = unitFilters.iterator();
    boolean keep = true;
    UnitFilter f=null;
    while (i.hasNext() && keep) {
      f = (UnitFilter)i.next();
      try { keep=f.keep(data); }
      catch (Exception e) {
        log(new Warning("warning.could.not.filter.data", f.getClass().getName(),  data.getOrigin().toString(), e));
        keep=false;
      }
    }
    return keep;
  }

  private final Object transform(Metadata data) {
    if (!transformersEnabled) return data;
    if (null==getUnitTransformers()) return data;
    Object o = data;
    Iterator i = unitTransformers.iterator();
    MetadataTransformerBase t=null;
    while (i.hasNext()) {
      t = (MetadataTransformerBase)i.next();
      try { o = t.transform(data); }
      catch (Exception e) {
        log(new Warning("warning.could.not.filter.data", t.getClass().getName(),  data.getOrigin().toString(), e));
      }
    }
    return o;
  }

  protected final void filterResults(){
    if (null==listFilters) return;
    //Collection filteredList=null;
    Iterator i = listFilters.iterator();
    ListFilter f = null;
    while (i.hasNext()){
      f = (ListFilter)i.next();
      try { f.filter(getResults()); }
      catch (Exception e) { 
        e.printStackTrace(); 
        log(new Warning("warning.cannot.filter.list", e)); 
      }
    }
  }

  protected final void transformResults(){
    if (null==listTransformers) return;
    Iterator i = listTransformers.iterator();
    ListTransformer t = null;
    while (i.hasNext()){
      t = (ListTransformer)i.next();
      try {
        t.adapt(getResults());
        Collection transformedList = t.transformList();
        resetStorage();
        initializeStorage(transformedList); 
      }
      catch (Exception e) { log(new Warning("warning.cannot.transform.list", e)); }
    }
  }
  
  public String getQueryTarget() { return queryTarget; }
  public void  setQueryTarget(String s) { queryTarget=s; }
  public Criteria getCriteria() { return criteria; }
  
  public void setCriteria(String s) throws InvalidSyntax {
    if (null==s) throw new InvalidSyntax("Criteria is null");
    CriteriaParser parser = new CriteriaParserBase();
    criteria=parser.parse(s);
  }
  
  public void setCriteria(Criteria c) { criteria=c; }
  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public String getDescription() { return description; }
  public void setDescription(String s) { description=s; }
  public String getSelect() { return select; }
  public void setSelect(String s) { select=s; }
  public String getOrderBy(){ return orderBy; }
  public void setOrderBy(String s) { orderBy=s; }
  public boolean getAscending() { return ascending; }
  public void setAscending(boolean b) { ascending=b; }
  public int getSkipCount() { return skipCount; }
  public void setSkipCount(int i) { if (0==i && 0<skipCount) return; skipCount=i; }
  public int getMaxCount() { return maxCount; }
  public void setMaxCount(int i) { if (0==i && 0< maxCount) return; maxCount=i; }

  public Comparator getSortComparator() { return sortComparator; }
  public void setSortComparator(Comparator c) { sortComparator=c; }
  
  public Collection getUnitTransformers(){ return unitTransformers; }
  public Collection getUnitFilters(){ return unitFilters; }
  public Collection getListFilters() { return listFilters; }
  public Collection getCriteriaModifiers() { return criteriaModifiers; }

  public final void initializeStorage(AdapterPrototype adapter) throws Exception {
    AdapterVector adapterVector = new AdapterVector(adapter);
    initializeStorage(adapterVector);
  }
 
  public void add(CriteriaModifier m) {
    if (null==criteriaModifiers) criteriaModifiers=new Vector();
    criteriaModifiers.add(m);
  }
  public final void add(UnitFilter f) {
    if (null==unitFilters) unitFilters=new Vector();
    unitFilters.add(f);
  }
  public final void add(ListFilter f) {
    if (null==listFilters) listFilters = new Vector();
    listFilters.add(f);
  }
  public void remove(CriteriaModifier m) {
    if (null==criteriaModifiers || null==m ) return;
    criteriaModifiers.remove(m);
  }
  public final void remove(UnitFilter f) {
    if (null==unitFilters || null==f) return;
    unitFilters.remove(f);
  }
  public final void remove(ListFilter f) {
    if (null==listFilters || null==f) return;
    listFilters.remove(f);
  }

  protected void modifyCriteria() throws Exception {
   if (null==criteriaModifiers) return;
   Iterator i = criteriaModifiers.iterator();
   CriteriaModifier modifier=null;
   while (i.hasNext()) {
     modifier = (CriteriaModifier) i.next();
     setCriteria(modifier.modify(getCriteria()).toString());
     {} //System.out.println("Criteria modified to: " + getCriteria());
   }
  }
  public Authentication getAuthenticationToken() { return authenticationToken; }
  public void setAuthenticationToken(Authentication a) { authenticationToken=a; }
  
  public boolean getAuthenticationRequired() { return authenticationRequired; }
  public void setAuthenticationRequired(boolean b) { authenticationRequired=b; }

  public void enableCache() throws UnsupportedOperation { throw new UnsupportedOperation("enableCache()", "caching not implemented for this search agent: "+getName()); }
  public void disableCache() throws UnsupportedOperation {throw new UnsupportedOperation("disableCache()", "caching not implemented for this search agent: "+getName());}

  public void setCriteriaModifiersEnabled(boolean b) { modifiersEnabled=b; }
  public void setFiltersEnabled(boolean b) { filtersEnabled=b; }
  public void setTransformersEnabled(boolean b) { transformersEnabled=b; }
  public boolean getChooseOnlyBinaries() { return chooseOnlyBinaries; }
  public void setChooseOnlyBinaries(boolean b) { chooseOnlyBinaries=b; }
  public boolean getIncludeHistory() { return includeHistory; }
  public void setIncludeHistory(boolean b) { includeHistory=b; }
  public boolean getIncludeDependencies() { return includeDependencies; }
  public void setIncludeDependencies(boolean b) { includeDependencies=b; }
  public String sgetDependencyConfiguration() { return dependencyConfiguration; }
  public void setDependencyConfiguration(String s) { dependencyConfiguration=s; }
  public String getDatedAfter() { return datedAfter; }
  public void setDatedAfter(String s) { datedAfter=s; }
  public String getDatedBefore() { return datedBefore; }
  public void setDatedBefore(String s) { datedBefore=s; }

//  public void setResults(Collection c) { resultList = c; }
//  public Collection getResults() { return resultList; }
//  private Collection resultList=null;

  
  public boolean getFiltersEnabled() { return filtersEnabled; }
  public boolean getModifiersEnabled() { return modifiersEnabled; }
  public boolean getTransformersEnabled() { return transformersEnabled; }
  
  private String queryTarget=null;
  private Criteria criteria=null;
  private boolean chooseOnlyBinaries;
  private boolean ascending=true;
  private int skipCount=0, maxCount=0;
  private String name=null, description=null, select=null, orderBy=null;
  private boolean authenticationRequired = false;
  private Authentication authenticationToken;
  private boolean modifiersEnabled=true;
  private boolean filtersEnabled=true;
  private boolean transformersEnabled=true;
  private boolean postFiltersEnabled=true;
  private Collection criteriaModifiers = null;
  private Collection unitFilters = null;
  private Collection unitTransformers = null;
  private Collection listFilters = null;
  private Collection listTransformers = null;
  private Comparator sortComparator=null;
  private boolean includeHistory=true;
  private boolean includeDependencies=false;
  private String dependencyConfiguration=AS_STORED;
  private String datedAfter=null;
  private String datedBefore=null;

  private static String AS_STORED="as-stored";
  private static String LATEST="latest";
}
