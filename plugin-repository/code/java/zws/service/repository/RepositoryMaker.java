/**
 *
 */
package zws.service.repository;

/*
 * DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Jun 12, 2007 9:39:13 AM Copywrite (c) 2007
 * Zero Wait-State Inc. All rights reserved
 */

import zws.qx.xml.QxXML;
import zws.origin.Origin;
import zws.qx.program.QxInstruction;
import zws.repository.RepositoryBase;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class RepositoryMaker.
 *
 * @author ptoleti
 */
public class RepositoryMaker {

  /**
   * Gets the repository.
   *
   * @param strRepository the str repository
   *
   * @return the repository
   */
  public RepositoryBase getRepository(QxXML strRepository) throws Exception {
    QxInstruction result = strRepository.toQxProgram();
    {} //System.out.println("============find-repository xml instruction result=============================");
    {} //System.out.println(strRepository);
    {} //System.out.println("=========================================");
    

    String repositoryType = result.getName();
    RepositoryBase repBase = null;
    {} //System.out.println("RepositoryMaker.getRepository returns " + repositoryType);

    {} //System.out.println("materializing repository for " + repositoryType);
    repBase = materializeRepository(repositoryType);
    //String repositoryName = result.get("name");
    //if(null!=repositoryName) repBase.setName(repositoryName);
    repBase.getContext().properties.putAll(result.getProperties());
    
    {} //System.out.println("============find-repository xml materialized object=============================");
    //zws.util.PrintUtil.print(repBase.toXML());
    {} //System.out.println("=========================================");
    return repBase;
  }
  
  private RepositoryBase materializeRepository(String type) throws Exception {
    RepositoryBase r = null;
    String fqcn = (String)repositoryFQCNs.get(type);
    if (null==fqcn) throw new zws.exception.NoSuchType(type);
    Class c = Class.forName(fqcn);
    r = (RepositoryBase)c.newInstance();
    return r;
  }
  
  private static Map repositoryFQCNs = new HashMap();
  static {
    repositoryFQCNs.put(Origin.FROM_AGILE, "zws.repository.agile.plm.api.AgilePLMAPIRepositoryBase");
    repositoryFQCNs.put(Origin.FROM_AGILE_SDK, "zws.repository.agile.sdk.AgileSDKRepositoryBase");
    repositoryFQCNs.put(Origin.FROM_ILINK, "zws.repository.ilink3.Ilink3RepositoryBase");
    repositoryFQCNs.put(Origin.FROM_ILINK_8, "zws.repository.R8.R8RepositoryBase");
    repositoryFQCNs.put(Origin.FROM_TEAMCENTER_10, "zws.repository.teamcenter.proxy.TC10ProxyRepositoryBase");
  }
}
