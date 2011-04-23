package zws.datasource.datashare;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.filter.file.SimpleFileFilter;
import zws.datasource.DatasourceSearchAgentBase;
import zws.search.criteria.Grouping;

import java.io.File;
import java.util.*;

public class SearchAgent extends DatasourceSearchAgentBase {  

  public void executeQuery() throws Exception {
    if (null!=getResults()) getResults().clear();
    Collection groups=getCriteria().getParser().getGroups();
    if (groups==null) return;
    Grouping g;
    SimpleFileFilter filter;
    Iterator f;
    String location;
    File folderToSearch;
    Iterator i = groups.iterator();
    List folders, rootFolders;
    while (i.hasNext()) {
      g = (Grouping) i.next();
      filter = new SimpleFileFilter();
      filter.define(g);
      folders = filter.getAbsoluteFoldersToSearch();
      if (null!=folders) {
        f = folders.iterator();
        while(f.hasNext()) {
          location = f.next().toString();
          folderToSearch = ((DataShareSource)getDatasource()).lookupDirectory(location);
          searchDirectory(folderToSearch, filter, false);
        }
      }
      rootFolders = filter.getRootFoldersToSearch();
      if (null!=rootFolders) {
        f = rootFolders.iterator();
        while(f.hasNext()) {
          location = (String)f.next();
          folderToSearch = ((DataShareSource)getDatasource()).lookupDirectory(location);
          searchDirectory(folderToSearch, filter, true);
        }
      }
      if (null!=folders && folders.size()==0 && null!=rootFolders && rootFolders.size()==0) {
          folderToSearch = ((DataShareSource)getDatasource()).getRoot();
          searchDirectory(folderToSearch, filter, getSearchRecursively());
      }
    }
  }
  
  private void searchDirectory(File dir, SimpleFileFilter filter, boolean searchRecursively) throws Exception {
    if (!dir.exists() || dir.isFile()) return;
    File[] list;
    list = dir.listFiles(filter);
    for (int idx=0; idx<list.length; idx++)
      if (list[idx].isFile()) store(((DataShareSource)getDatasource()).unmarshallFile(list[idx]));
    if (searchRecursively) searchSubdirectories(dir, filter, searchRecursively);
  }

  private void searchSubdirectories(File dir, SimpleFileFilter filter, boolean searchRecursively) throws Exception {
    if (!dir.exists() || dir.isFile()) return;
    File[] list = dir.listFiles();
    for (int idx=0; idx<list.length; idx++)
      if (list[idx].isDirectory()) searchDirectory(list[idx], filter, searchRecursively);
  }

  
  public boolean getSearchRecursively() { return searchRecursively; }
  public void setSearchRecursively(boolean b) { searchRecursively=b; }
  
  private boolean searchRecursively=true;
}
/*
  public void executeQuery_() throws Exception {
    if (null!=getResults()) getResults().clear();
    String crit=getCriteria().toString();
    crit = crit.substring(crit.indexOf('=')+1, crit.length()-2).trim(); //interim hack. fyi.
    {}//Logwriter.printOnConsole("searching " + getDirectoryPaths() + " for " + crit);
    StringTokenizer tok = new StringTokenizer(crit, " ");
    while (tok.hasMoreTokens()) executeSearch(tok.nextToken()); //++add orderBy, ascending, offset & maxCount
    if (null==getResults()) {}//Logwriter.printOnConsole("No documents found");
    {}//Logwriter.printOnConsole("found " + getResults().size());
  }

  public Collection executeSearch(String criteria) throws Exception { //++add orderBy, ascending, offset & maxCount
    Collection results=new Vector();
    StringTokenizer tok = new StringTokenizer(getDirectoryPaths(), Names.DELIMITER);
    while (tok.hasMoreTokens()) {
      File repository=new File(tok.nextToken().trim());
      searchDirectory(repository, criteria); //++security: check datasource to make sure we have access to this directory
    }
    return results;
  }

  private void searchDirectory(File dir, String criteria) throws Exception {
    if (!dir.exists() || dir.isFile()) return;
    File[] list;
//    filter.setSearchCriteria(criteria.toString());
    list = dir.listFiles(filter);
    //list = dir.listFiles(); //++remove once filter is added back in
    for (int idx=0; idx<list.length; idx++)
      if (list[idx].isFile()) store(((FileSystemSource)getDatasource()).unmarshallFile(list[idx]));
    if (searchRecursively) searchSubdirectories(dir, criteria);
  }

  private void searchSubdirectories(File dir, String criteria) throws Exception {
    if (!dir.exists() || dir.isFile()) return;
    File[] list = dir.listFiles();
    for (int idx=0; idx<list.length; idx++)
      if (list[idx].isDirectory()) searchDirectory(list[idx], criteria);
  }

  public SimpleFileFilter getFilter() { return filter; } //todo: change interface type to Filter
  public void setFilter(SimpleFileFilter f) { filter=f; } //todo: change interface type to Filter

  public String getDirectoryPaths() { return directoryPaths; }
  public void setDirectoryPaths(String s) { directoryPaths=s; }
*/