package zws.datasource.intralink.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.intralink.xml.IntralinkResultHandler;
import zws.datasource.intralink.xml.SearchResultHandler;

import java.io.*;

public class Search extends IntralinkRepositoryOp {
  public IntralinkResultHandler getXMLResultHandler() { return new SearchResultHandler(); }
  protected void writeRepositoryInstruction() throws IOException {
    openTag("search");
    if (null!=getSelect()) writeParameter("select", getSelect());
    writeParameter("criteria", getCriteria());
    writeParameter("include-history", getIncludeHistory());
    writeParameter("include-dependencies", getIncludeDependencies());
    if (null!=getDatedAfter()) writeParameter("dated-after", getDatedAfter());
    if (null!=getDatedBefore()) writeParameter("dated-before", getDatedBefore());
    if (null!=getOrderBy()) {
      writeParameter("order-by", getOrderBy());
      writeParameter("ascending", ascending);        
    }
    if (0<getMaxCount()) writeParameter("max-count", getMaxCount());
    writeParameter("skip-instances", skipInstances);
    closeTag();
  }

  // public void setOutputName(String  s) { outputName = s; }
  public String getCriteria() { return criteria; }
  public void setCriteria(String s) { criteria=s; }
  public long getMaxCount() { return maxCount; }
  public void setMaxCount(long l) { maxCount = l; }
  public String getOrderBy() { return orderBy; }
  public void setOrderBy(String s) { orderBy=s; }
  public boolean getAscending() { return ascending; }
  public void setAscending(boolean b) { ascending=b; }
  public long getSkipCount() { return skipCount; }
  public void setSkipCount(long l) { skipCount = l; }
  public String getSelect() { return select; }
  public void setSelect(String s) { select=s; }

  public boolean getSkipInstances() { return skipInstances; }
  public void setSkipInstances(boolean b) { skipInstances=b; }
  public boolean getIncludeHistory() { return includeHistory; }
  public void setIncludeHistory(boolean b) { includeHistory=b; }
  public boolean getIncludeDependencies() { return includeDependencies; }
  public void setIncludeDependencies(boolean b) { includeDependencies=b; }
  public String getDatedAfter() { return datedAfter; }
  public void setDatedAfter(String s) { datedAfter=s; }
  public String getDatedBefore() { return datedBefore; }
  public void setDatedBefore(String s) { datedBefore=s; }

  private String criteria=null;
  private boolean skipInstances=false;
  private String select=null;
  private String orderBy=null;
  private boolean ascending;
  private long maxCount=0;
  private long skipCount=0;
  private boolean includeHistory=true;
  private boolean includeDependencies=false;
  private String datedAfter=null;
  private String datedBefore=null;
}
