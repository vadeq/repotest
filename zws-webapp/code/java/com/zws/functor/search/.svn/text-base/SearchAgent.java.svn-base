package com.zws.functor.search;

import com.zws.application.Constants;
import com.zws.application.Properties;
import com.zws.datasource.DataSource;
import com.zws.domo.document.Document;
import com.zws.functor.ListFunctor;
import com.zws.functor.filter.DocumentUnitFilter;
import com.zws.functor.finder.Finder;
import com.zws.functor.report.MetaDataMapping;
import com.zws.service.config.ConfigurationNotFound;
import com.zws.service.config.DataSourceService;
import com.zws.util.FileNameUtil;
import com.zws.util.stream.StreamableCollection;

import java.util.*;

public abstract class SearchAgent extends ListFunctor implements Runnable, Cloneable  {
  public SearchAgent() {}
  public void bind(MetaDataMapping m) { reportMapping = m; }
  public final void run(){ exception = null; try{ execute(); } catch (Exception e) {exception=e; e.printStackTrace();} }

  public abstract Finder createFinder(Object data);

  public int add(Document doc) throws Exception {
    Iterator i = unitFilters.iterator();
    Document item=doc;
    DocumentUnitFilter filter;
    while (null!=doc && i.hasNext()){
      filter = (DocumentUnitFilter) i.next();
      filter.setDocument(item);
      filter.filter();
      item = (Document)filter.getResult();
    }
    int idx=-1;
    if (null!=item) {
      calculateOrigin(item);
      idx = ((StreamableCollection)getResults()).addItem(doc);
      item.setID(""+idx);
    }
    return idx;
  }

  public void calculateOrigin(Document doc) {
    //todo: this is messy, clean it && all origin related stuff - including using an OriginMaker functor;
    if (null==getOriginFields()) return;
    if (getOriginFields().equals(Constants.METADATA_ORIGIN) && null!=doc.getOrigin()) return;

    String field=null, delim=Constants.ORIGIN_DELIMITER;
    String origin=Properties.get(Constants.SERVICE_NAME) + delim + getDataSource().getName();

    StringTokenizer tok = new StringTokenizer(getOriginFields(), delim);
    if (!tok.hasMoreTokens())
      origin += delim + doc.get(getOriginFields());
    while (tok.hasMoreTokens()) {
      field = tok.nextToken();
      origin += delim + doc.get(field);
    }
    doc.setOrigin(origin);
    if (getIsOriginalSource()) doc.set(Constants.METADATA_SOURCEFILE_ORIGIN, doc.getOrigin());
  }

  public Document map(Document d0) {
    Document d1 = new Document();
    Iterator w;
    String metadata, valueRef;
    String fieldName;
    d0.set(Constants.METADATA_SERVICE_NAME,Properties.get(Constants.SERVICE_NAME));
    d0.set(Constants.METADATA_DATASOURCE,getDataSourceName());
    d0.set(Constants.METADATA_SEARCH_AGENT,getName());
    d0.set(Constants.METADATA_EXTENTION, FileNameUtil.getFileNameExtention(d0.getName()));
    d0.set(Constants.METADATA_TYPE, FileNameUtil.lookupFileType(d0.getName()));
    if (isOriginalSource) {
      d0.set(Constants.METADATA_SOURCEFILE_SERVICE_NAME, Properties.get(Constants.SERVICE_NAME));
      d0.set(Constants.METADATA_SOURCEFILE_DATASOURCE, getDataSourceName());
      d0.set(Constants.METADATA_SOURCEFILE_NAME, d0.getName());
      d0.set(Constants.METADATA_SOURCEFILE_EXTENTION, FileNameUtil.getFileNameExtention(d0.getName()));
      d0.set(Constants.METADATA_SOURCEFILE_TYPE, FileNameUtil.lookupFileType(d0.getName()));
    }
    d1.setName(d0.getName() );
    w = getReportMapping().getMetaDataMappings().keySet().iterator();
    while (w.hasNext()){
      metadata = w.next().toString();
      valueRef = getReportMapping().getFromAllMappings(metadata);
      if (null==valueRef) valueRef="";
      else{
        StringTokenizer tok = new StringTokenizer(valueRef,";");
        //d1.set(metadata, createValue(tok, d0));
        if(tok.countTokens() > 1)
			d1.set(metadata, createValue(tok, d0));
		else
			d1.set(metadata, createValue(tok, d0), d0.getDescriptor(valueRef));
      }
    }
    return d1;
  }

  public Exception getException() { return exception; }

  public final void configureSearch(StreamableCollection resultBuffer, String searchCriteria)
  { configureSearch(resultBuffer, searchCriteria, getOffset(), getMaxCount()); }
  public void configureSearch(StreamableCollection resultBuffer, String searchCriteria, Long offset, Long count) {
    setCriteria(searchCriteria);
    setOffset(offset);
    setMaxCount(count);
    setResult(resultBuffer);
  }

  public String getOriginFields() { return originFields; }
  public void setOriginFields(String s) { originFields=s; }
  public String getMemberID() { return memberID; }
  public void setMemberID(String s) { memberID = s; }
  public String getName() { return name; }
  public void setName(String s) { name = s; }
  public void setCriteria(String s) { searchCriteria = s; }
  public String getCriteria() { return searchCriteria; }
  public void setOffset(Long l) { offset = l; }
  public Long getOffset() { return offset; }
  public void setMaxCount(Long l) { maxCount = l; }
  public Long getMaxCount() { return maxCount; }
  public void setSortBy(String s) { sortBy = s; }
  public String getSortBy() { return sortBy; }
  public void setSortAscending(boolean b) { sortAscending=b; }
  public boolean getSortAscending() { return sortAscending; }
  public MetaDataMapping getReportMapping() { return reportMapping; }
  public void setReportMapping(MetaDataMapping m) { reportMapping=m; }
  public String getDataSourceName() { return dataSourceName; }
  public void setDataSourceName(String s) {
    dataSourceName = s;
    try { setDataSource(DataSourceService.find(s)); }
    catch (ConfigurationNotFound e) { e.printStackTrace(); }
  }

  public void setDataSource(String s) {
    setDataSourceName(s);
    try { setDataSource(DataSourceService.find(s)); }
    catch (ConfigurationNotFound e) { e.printStackTrace(); }
  }

  public void setDataSource(DataSource s) { dataSource=s; }
  public DataSource getDataSource() { return dataSource; }

  public boolean getCacheEnabled() { return cacheEnabled; }
  public void setCacheEnabled(boolean b) { cacheEnabled = b; }

  public Finder getFinderPrototype() { return finderPrototype; }
  public void setFinderPrototype(Finder f) { finderPrototype = f; }

  public String createValue(StringTokenizer tok, Document source) {
    String val = "";
    String key, token, s;
    while (tok.hasMoreTokens()){
      token=tok.nextToken();
      key=token.trim();
      if(key.startsWith("'") && key.endsWith("'")) val += key.substring(1,key.length()-1);
      else if (key.startsWith("[")&& key.endsWith("]")){
        key = key.substring(1,key.length()-1).trim();
        val+= source.get(getReportMapping().getFromAllMappings(key));
      }
      else {
        s = source.get(key);
        if (null==s) s="";
        val += s;
      }
    }
    return val;
  }

  public void add(DocumentUnitFilter f) { unitFilters.add(f); }
  public boolean getIsOriginalSource() { return isOriginalSource; }
  public void setIsOriginalSource(boolean b) { isOriginalSource=b; }

  private String originFields=null;
  private String name=null;
  private String dataSourceName=null;
  private DataSource dataSource=null;
  private String sortBy=null;
  private String searchCriteria=null;
  private Long offset=new Long(0);
  private Long maxCount=new Long(Long.MAX_VALUE);
  private boolean sortAscending=true;
  private Finder finderPrototype;
  private boolean isOriginalSource = true;

  private MetaDataMapping reportMapping=null;
  private Exception exception=null;
  private String memberID=null;
  private boolean cacheEnabled=false;  //todo: implement caching
  private Collection unitFilters = new Vector();
}
