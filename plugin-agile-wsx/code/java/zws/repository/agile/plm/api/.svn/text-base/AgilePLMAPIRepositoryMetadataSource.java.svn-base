package zws.repository.agile.plm.api;


import zws.data.Metadata;

import zws.repository.source.RepositoryMetadataSource;
import zws.origin.Origin;
import zws.security.Authentication;
import zws.qx.QxContext;

import java.util.Collection;

import com.agile.plmapi.api.PlmAttributeCriteria;
import com.agile.plmapi.api.PlmExpressionCriteria;
import com.agile.plmapi.api.PlmFactory;
import com.agile.plmapi.api.PlmQuery;
import com.agile.plmapi.api.PlmRequest;
import com.agile.plmapi.api.PlmResponse;
import com.agile.plmapi.api.PlmSession;
import com.agile.sdo.cif.AgileWSXItemSvc;

/**
 * The Class AgileSDKRepositoryMetadataSource.
 */
public class AgilePLMAPIRepositoryMetadataSource extends AgilePLMAPIRepositoryBase
    implements RepositoryMetadataSource {


  /**
   * The Constructor.
   * @param parent the parent
   * @param sdkSvc the sdkSvc
   */
  protected AgilePLMAPIRepositoryMetadataSource(QxContext parent) {
    configureParentContext(parent);
  }


  /**
   * Contains.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param o origin
   *
   * @return boolean contains
   *
   * @see zws.repository.source.RepositoryMetadataSource#contains(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public boolean contains(QxContext runningCtx, Origin o, Authentication id) {
    try {
      Metadata m = find(runningCtx, o, id);
      return (null!=m);
    }
    catch(Exception e) {
      return false;
    }
  }

  /**
   * Find.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param o origin
   *
   * @return boolean contains
   *
   * @throws Exception exception
   *
   * @see zws.repository.source.RepositoryMetadataSource#find(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public Metadata find(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    PlmResponse response = null;
    String subclass ="Items";
    PlmQuery documentQuery = PlmFactory.createQuery(subclass);
    PlmAttributeCriteria attributeCriteriaPart =
      PlmFactory.createQueryAttributeCriteria(subclass, "Number", PlmAttributeCriteria.OP_EQUAL, origin.getName());
    PlmExpressionCriteria criteriaPart = PlmFactory.createQueryExpression(subclass, attributeCriteriaPart);
    documentQuery.setExpression(criteriaPart);
    PlmRequest request = PlmFactory.createRequest();
    request.addQuery(documentQuery);
    AgileWSXItemSvc itemSvc = new AgileWSXItemSvc(getURL(), id, this, runningCtx);
    PlmSession session = null;
    Metadata m=null;
    session = itemSvc.login(getURL(), id);
    session.setExecuteTimeout(30*1000);
    response = session.execute("search", request);
    m = extractMetadataResult(response);
    return m;

  }

  /**
   * Find latest.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param name name
   *
   * @return boolean contains
   *
   * @throws Exception exception
   *
   * @see zws.repository.source.RepositoryMetadataSource#findLatest(zws.qx.QxContext, java.lang.String, zws.security.Authentication)
   */
  public Metadata findLatest(QxContext runningCtx, String name, Authentication id) throws Exception {
    PlmResponse response = null;
    String subclass ="Items";
    PlmQuery documentQuery = PlmFactory.createQuery(subclass);
    PlmAttributeCriteria attributeCriteriaPart =
      PlmFactory.createQueryAttributeCriteria(subclass, "Number", PlmAttributeCriteria.OP_EQUAL, name);
    PlmExpressionCriteria criteriaPart = PlmFactory.createQueryExpression(subclass, attributeCriteriaPart);
    documentQuery.setExpression(criteriaPart);
    PlmRequest request = PlmFactory.createRequest();
    request.addQuery(documentQuery);
    AgileWSXItemSvc itemSvc = new AgileWSXItemSvc(getURL(), id, this, runningCtx);
    PlmSession session = null;
    Metadata m=null;
    session = itemSvc.login(getURL(), id);
    session.setExecuteTimeout(30*1000);
    response = session.execute("search", request);
    m = extractMetadataResult(response);
    return m;

  }


  /**
   * Search latest.
   *
   * @param id Authentication
   * @param name name
   * @param runtimeCtx runtimeCtx
   *
   * @return boolean contains
   *
   * @throws Exception exception
   *
   * @see zws.repository.source.RepositoryMetadataSource#searchLatest(zws.qx.QxContext, java.lang.String, zws.security.Authentication)
   */
  public Collection searchLatest(QxContext runningCtx, String name, Authentication id) throws Exception {
    PlmResponse response = null;
    String subclass ="Items";
    PlmQuery documentQuery = PlmFactory.createQuery(subclass);
    PlmAttributeCriteria attributeCriteriaPart =
      PlmFactory.createQueryAttributeCriteria(subclass, "Number", PlmAttributeCriteria.OP_CONTAINS, name);
    PlmExpressionCriteria criteriaPart = PlmFactory.createQueryExpression(subclass, attributeCriteriaPart);
    documentQuery.setExpression(criteriaPart);
    PlmRequest request = PlmFactory.createRequest();
    request.addQuery(documentQuery);
    AgileWSXItemSvc itemSvc = new AgileWSXItemSvc(getURL(), id, this, runningCtx);
    PlmSession session = itemSvc.login(getURL(), id);
    response = session.execute("search", request);
    Metadata m = extractMetadataResult(response);
    return null;
  }

  // public Origin findOrigin(String uid, Authentication id) throws Exception;
  // public Collection reportAllNames(Authentication id) throws Exception;
  // public Collection reportLatest(String path, Authentication id) throws
  // PathDoesNotExist, Exception;
}
