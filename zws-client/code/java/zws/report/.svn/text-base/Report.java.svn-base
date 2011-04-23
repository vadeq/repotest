package zws.report;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidSyntax;
import zws.search.SearchAgent;
import zws.search.criteria.Criteria;

/** A Report generates results based on the given criteria and search parameters.
 * Results will contain values for the metadata fields defined for the report.
 */
public interface Report extends SearchAgent {
  /** Generates the report.
   * @throws Exception if an error ocurres while generating the report.
   */  
  public void generate() throws Exception ;

//  public void add(SearchAgent agent); //add a report-private search agent
//  public void add(String agent) throws NameNotFound; //add a report-private search agent

  /** returns the metadata fields defined for this report
   * @return Array of the metadata fields defined for the report.
   */  
  public String[] getMetadataFields();
  
  /** Name of report
   * @return name of report
   */  
  public String getName();
  
  /** Search criteria
   * @return the search criteria
   */  
  public Criteria getCriteria();
  
  /** search criteria
   * @param s search criteria
   */  
  public void setCriteria(String s) throws InvalidSyntax;
  
  /** the field that the results should be sorted by.
   * @return Field name to sort by. (Null if there is no sort order).
   */  
  public String getOrderBy();
  
  /** the field that the results should be sorted by. (Null if there is no sort order).
   * @param s fieldname to sort by.
   */  
  public void setOrderBy(String s);
  
  /** specifies sort order
   * @return true if results should be sorted in ascending order. fals if results shold be
   * sorted in descending order.
   */  
  public boolean getAscending();
  
  /** specifies sort order
   * @param b true if results should be sorted in ascending order.
   */  
  public void setAscending(boolean b);
  
  /** number of results to skip
   * @return offset
   */  
  public int getSkipCount();
  
  /** number of results to skip
   * @param i offset
   */  
  public void setSkipCount(int i);
  
  /** max number of results to return
   * @return max number of results to return
   */  
  public int getMaxCount();
  
  /** max number of results to return
   * @param i max number of results to return
   */  
  public void setMaxCount(int i);
  
  /** XML representation of the report.
   * @return XML representation of the report.
   */  
  public String toXML();
}
