/**
 *
 */
package zws.repository.agile.sdk.svc;

import zws.exception.InvalidName;
import zws.exception.NameNotFound;
import zws.repository.agile.sdk.AgileSDKRepositoryBase;

import com.agile.api.IAgileClass;
import com.agile.api.IAgileSession;
/*
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on Jul 12, 2007 1:21:39 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import com.agile.api.IAutoNumber;


/**
 * The Class AgileSDKPartNumberSvc.
 *
 * @author ptoleti
 */
public class AgileSDKPartNumberSvc extends AgileSDKSvcBase {

  /**
   * The Constructor.
   *
   * @param repository the repository
   * @param configUtil the config util
   * @param sessionUtil the session util
   */
  protected AgileSDKPartNumberSvc(AgileSDKSessionSvc sessionUtil,
                                  AgileSDKConfigurationSvc configUtil,
                                  AgileSDKRepositoryBase repository) {
    super(sessionUtil, configUtil, repository);
    // TODO Auto-generated constructor stub
  }

  /**
   * Generate next part number.
   *
   * @param cls the cls
   * @param autoNumberSourceName the auto number source name
   *
   * @return the string
   *
   * @throws Exception the exception
   */
  public String generateNextPartNumber(IAgileClass cls,
      String autoNumberSourceName) throws Exception {
    IAutoNumber source;
    IAutoNumber[] sourceList;
    String nextAutoNumber;
    sourceList = cls.getAutoNumberSources();
    source = null;
    if (sourceList.length > 1
        && (null == autoNumberSourceName || "".equals(autoNumberSourceName
            .trim()))) { throw new InvalidName(autoNumberSourceName); }
    if (sourceList.length == 1
        && (null == autoNumberSourceName || "".equals(autoNumberSourceName
            .trim()))) {
      source = sourceList[0];
    } else {
      for (int i = 0; i < sourceList.length; i++) {
        source = sourceList[i];
        if (autoNumberSourceName.equalsIgnoreCase(source.getName())) {
          break;
        }
      }
    }
    if (null == source) { throw new NameNotFound(autoNumberSourceName); }
    nextAutoNumber = source.getNextNumber();
    return nextAutoNumber;
  }

  /**
   * Generate next part number.
   *
   * @param autoNumberSourceName the auto number source name
   * @param agileClass the agile class
   *
   * @return the string
   *
   * @throws Exception the exception
   */
  public String generateNextPartNumber(String agileClass,
      String autoNumberSourceName) throws Exception {
    IAgileClass cls;
    IAgileSession session = sessions.systemLogin();
    session.getAdminInstance();
    cls = config.findAgileClass(agileClass);
    return generateNextPartNumber(cls, autoNumberSourceName);
  }

}
