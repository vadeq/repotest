package zws.datasource; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.exception.NameNotFound;
import zws.exception.UnsupportedOperation;
import zws.search.SearchAgentBase;
import zws.service.datasource.DatasourceSvc;
import zws.util.FileNameUtil;

public abstract class DatasourceSearchAgentBase extends SearchAgentBase implements DatasourceSearchAgent {
    public boolean supportsDeepCopy() { return false; }

    public abstract void executeQuery() throws Exception;

/*
  public void store(Metadata data) throws Exception {
    setSystemAttributes(data);
    super.store(data);
    boolean keep = true;
    if (null!=getUnitFilters()) {
      Iterator i = getUnitFilters().iterator();
      UnitFilter f=null;
      while (i.hasNext() && keep) {
        f = (UnitFilter)i.next();
        try { keep=f.keep(data); }
        catch (Exception e) {
          log(new Warning("warning.could.not.filter.data", f.getClass().getName(),  data.getOrigin().toString(), e));
          keep=false;
        }
      }
    }
    if (keep) getResults().add(data);
  }
 */

  public void addSystemAttributes(Metadata data){
    String type, extension;
    extension = FileNameUtil.getFileNameExtension(data.getName());
    type = FileNameUtil.lookupFileType(data.getName());
    if (!"".equals(extension)) data.set(Names.METADATA_FILE_TYPE, extension);
    if (!"".equals(type)) data.set(Names.METADATA_TYPE, type);
    data.set(Names.METADATA_DOMAIN_NAME, Server.getDomainName());
    data.set(Names.METADATA_SERVER_NAME, Server.getName());
    data.set(Names.METADATA_DATASOURCE_NAME, datasource.getName());
    data.set(Names.METADATA_DATASOURCE_SEARCH_AGENT_NAME, getName());
    if (isOriginalSource) {
      data.set(Names.METADATA_SOURCEFILE_NAME, data.getName());
      if (!"".equals(type)) data.set(Names.METADATA_SOURCEFILE_TYPE, type);
      if (!"".equals(extension)) data.set(Names.METADATA_SOURCEFILE_EXTENSION, extension);
      data.set(Names.METADATA_SOURCEFILE_SERVER_NAME, Properties.get(Names.SERVER_NAME));
      data.set(Names.METADATA_SOURCEFILE_DATASOURCE_NAME, datasource.getName());
      data.set(Names.METADATA_SOURCEFILE_SEARCH_AGENT_NAME, getName());
    }
  }

  public void setDatasource(String name) throws NameNotFound { datasource = DatasourceSvc.find(name); }
  public void setDatasource(Datasource d) { datasource = d; }
  public Datasource getDatasource() { return datasource; }

  public boolean getIsOriginalSource() { return isOriginalSource; }
  public void setIsOriginalSource(boolean b) { isOriginalSource=b; }
  public void setCache(boolean b) { if (b) enableCache(); else disableCache(); }
  public void enableCache() throws UnsupportedOperation { throw new UnsupportedOperation("enableCache()", "caching not implemented for this search agent: "+getName()); }
  public void disableCache(){throw new UnsupportedOperation("disableCache()", "caching not implemented for this search agent: "+getName());}

  private Datasource datasource=null;
  private boolean isOriginalSource = false;
}