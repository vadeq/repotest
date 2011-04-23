package com.zws.functor.search;

import com.zws.application.*;
import com.zws.application.Properties;
import com.zws.domo.document.Document;
import com.zws.functor.filter.file.SimpleFileFilter;
import com.zws.functor.finder.FileFinder;
import com.zws.functor.finder.Finder;
import com.zws.functor.util.file.FileMetadataSetter;
import com.zws.util.FileNameUtil;

import java.io.File;
import java.util.*;

public class FileSystemAgent extends SearchAgent {

  public void execute() throws Exception {
    String crit;
    StringTokenizer tok = new StringTokenizer(getCriteria(), " ");
    while (tok.hasMoreTokens()) executeSearch(tok.nextToken());
  }

  public void executeSearch(String criteria) throws Exception {
    Collection c=new Vector();
    StringTokenizer tok = new StringTokenizer(getDirectoryPaths(), ";");
    while (tok.hasMoreTokens()) {
      File repository=new File(tok.nextToken().trim());
      searchDirectory(repository, criteria);
    }
  }

  private void searchDirectory(File dir, String criteria) throws Exception {
    if (!dir.exists() || dir.isFile()) return;
    File[] list;
    filter.setSearchCriteria(criteria);
    list = dir.listFiles(filter);
    for (int idx=0; idx<list.length; idx++)
      if (list[idx].isFile()) adaptToResults(list[idx]);
    if (searchRecursively) searchSubdirectories(dir, criteria);
  }

  private void searchSubdirectories(File dir, String criteria) throws Exception {
    if (!dir.exists() || dir.isFile()) return;
    File[] list = dir.listFiles();
    for (int idx=0; idx<list.length; idx++)
      if (list[idx].isDirectory()) searchDirectory(list[idx], criteria);
  }

  public void adaptToResults(Collection files) throws Exception {
    Iterator i = files.iterator();
    while (i.hasNext()) adaptToResults((File)i.next());
  }

  public void adaptToResults(File f) throws Exception {
    Document d;
    d = new Document();
    d.setName(f.getName() );

    FileMetadataSetter setter = new FileMetadataSetter();
    setter.setFile(f);
    setter.setDocument(d);
    setter.execute();

    d.set(Constants.METADATA_SERVICE_NAME,Properties.get(Constants.SERVICE_NAME));
    d.set(Constants.METADATA_DATASOURCE,getDataSourceName());
    d.set(Constants.METADATA_SEARCH_AGENT,getName());
    if (getIsOriginalSource()) {
      d.set(Constants.METADATA_SOURCEFILE_SERVICE_NAME, Properties.get(Constants.SERVICE_NAME));
      d.set(Constants.METADATA_SOURCEFILE_DATASOURCE, getDataSourceName());
      d.set(Constants.METADATA_SOURCEFILE_NAME, f.getName());
      d.set(Constants.METADATA_SOURCEFILE_EXTENTION, FileNameUtil.getFileNameExtention(f.getName()));
      d.set(Constants.METADATA_SOURCEFILE_TYPE, FileNameUtil.lookupFileType(f.getName()));
    }

    d.setFinder(createFinder(f));
    adaptMappings(d);
  }

  public Finder createFinder(Object file) {
    FileFinder finder = new FileFinder();
    File f= (File)file;
    finder.setBinary(f.getName());
    finder.setLocation(f.getParentFile().getPath());
    return finder;
  }

  public void adaptMappings(Document doc) throws Exception {
    Document d = new Document();
    d.setName(doc.getName());
    d.setFinder(doc.getFinder());
    String metadata, valueRef;
    Iterator w = getReportMapping().getMetaDataMappings().keySet().iterator();
    while (w.hasNext()){
      metadata = w.next().toString();
      valueRef = getReportMapping().getFromAllMappings(metadata);
      if (null==valueRef) valueRef="";
      else{
          StringTokenizer tok = new StringTokenizer(valueRef,";");
          d.set(metadata, createValue(tok, doc));
      }
    }
    add(d);
//    int idx = ((StreamableCollection)getResults()).addItem(d);
//    d.setID(""+idx);
  }


  public String getDirectoryPaths() { return directoryPaths; }
  public void setDirectoryPaths(String s) {
    directoryPaths=s;
    if (directoryPaths.startsWith(Config.FILE_SEPARATOR))
      directoryPaths = directoryPaths.substring(1);
  }
  public boolean getSearchRecursively() { return searchRecursively; }
  public void setSearchRecursively(boolean b) { searchRecursively=b; }

  public SimpleFileFilter getFilter() { return filter; } //todo: change interface type to Filter
  public void setFilter(SimpleFileFilter f) { filter=f; } //todo: change interface type to Filter

  private SimpleFileFilter filter=new SimpleFileFilter(); //todo: change interface type to Filter
  private String directoryPaths=null;
  private boolean searchRecursively=true;
}
