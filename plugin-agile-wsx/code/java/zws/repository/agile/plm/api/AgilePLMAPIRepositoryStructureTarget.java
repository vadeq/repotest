package zws.repository.agile.plm.api;


import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.repository.target.RepositoryStructureTarget;
import com.agile.sdo.cif.AgileWSXStructureSvc;




/**
 * The Class Ilink3RepositoryMetadataSource.
 */
public class AgilePLMAPIRepositoryStructureTarget extends AgilePLMAPIRepositoryBase
    implements RepositoryStructureTarget {

  /**
   * The Constructor.
   */
  public AgilePLMAPIRepositoryStructureTarget() {
  }

  /**
   * The Constructor.
   * @param sdkBase sdkBase
   * @param parent parentContext
   */
  public AgilePLMAPIRepositoryStructureTarget(QxContext parent) {
    configureParentContext(parent);
  }

  /**
   * contains.
   *
   * @param id authentication
   * @param runningCtx the running ctx
   * @param origin origin
   *
   * @return boolean
   *
   * @see zws.repository.source.RepositoryMetadataSource#contains(zws.origin.Origin,
   * zws.security.Authentication)
   */
  public final boolean contains(QxContext runningCtx, Origin origin, Authentication id) {
    return false;
  }

  /**
   * createBill.
   *
   * @param id authentication
   * @param runningCtx the running ctx
   * @param bill bill
   *
   * @return Metadata result data
   *
   * @throws Exception exception
   *
   * @see zws.repository.source.RepositoryMetadataSource#findLatest(java.lang.String,
   * zws.security.Authentication)
   */

  public final void createAndStructureBill(QxContext runningCtx, BillOfMaterials bill, Authentication id) throws Exception {
    //String url="http://pwebdev.cisco.com/pls/services";
    String url=getProtocol() + "://" + getHostName() + "/" + getServicesPath();
    {} //System.out.println("URL is ---> " + url);
    boolean mappedFlag = true;
    boolean asyncFlag = false;
    String source = null;
    if(null != runningCtx.get(AgilePLMAPIConstants.MAPPED_FLAG)) {
      mappedFlag = new Boolean(runningCtx.get(AgilePLMAPIConstants.MAPPED_FLAG)).booleanValue();
    }
    if(null != runningCtx.get(AgilePLMAPIConstants.ASYNC_FLAG)) {
      asyncFlag = new Boolean(runningCtx.get(AgilePLMAPIConstants.ASYNC_FLAG)).booleanValue();
    }
    AgileWSXStructureSvc structureSvc = new AgileWSXStructureSvc(url,id, this,runningCtx);
    Metadata billData = bill.getMetadata();
    if(null == billData.get("attachment")) {
      throw new Exception("Attachment was not set for " + billData.getName());
    }
    //Metadata c = structureSvc.createStructure(billData, source, null, null, null, mappedFlag);
    structureSvc.createStructure(billData, source, null, null, null, mappedFlag, asyncFlag, runningCtx);
    //RepositoryMetadataSource metadataSource = this.materializeMetadataSource();
    //c= metadataSource.findLatest(getContext(), c.getName(), id);
    return; //c;
  }

  // each method here will make a WEBService call (Client implementation)
  /**
   * find object(s) laterst revision.
   *
   * @param id authentication
   * @param runningCtx the running ctx
   * @param origin origin
   *
   * @return Metadata result data
   *
   * @throws Exception exception
   *
   * @see zws.repository.source.RepositoryMetadataSource#findLatest(java.lang.String,
   * zws.security.Authentication)
   */

  public final Metadata reportBill(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    return null;
  }

  /**
   * find object(s) laterst revision.
   *
   * @param id authentication
   * @param runningCtx the running ctx
   * @param origin origin object
   *
   * @return Metadata result data
   *
   * @throws Exception exception
   *
   * @see zws.repository.source.RepositoryMetadataSource#find(zws.origin.Origin,
   * zws.security.Authentication)
   */
  public final Metadata find(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    return null;
  }

  /**
   * Report all dependencies.
   *
   * @param structureConfiguration structure Configuration
   * @param id authentication
   * @param runningCtx qxContext
   * @param o origin
   *
   * @return Metadata
   *
   * @throws Exception exception
   */
  public Metadata reportAllDependencies(QxContext runningCtx, Origin o, String structureConfiguration, Authentication id) throws Exception {
    return null;
  }

  /**
   * report Design Dependencies.
   *
   * @param structureConfiguration structure Configuration
   * @param id authentication
   * @param runningCtx qxContext
   * @param o origin
   *
   * @return Metadata
   *
   * @throws Exception exception
   */
  public Metadata reportDesignDependencies(QxContext runningCtx, Origin o, String structureConfiguration, Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * report WhereUsed.
   *
   * @param structureConfiguration structure Configuration
   * @param id authentication
   * @param runningCtx qxContext
   * @param o origin
   *
   * @return Metadata
   *
   * @throws Exception exception
   */
  public Metadata reportWhereUsed(QxContext runningCtx, Origin o, String structureConfiguration, Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see zws.repository.target.RepositoryStructureTarget#structureBill(zws.qx.QxContext, zws.bill.intralink.BillOfMaterials, zws.security.Authentication)
   */
  public void structureBill(QxContext runningCtx, BillOfMaterials bill, Authentication id) throws Exception {
    // TODO Auto-generated method stub

  }


}
