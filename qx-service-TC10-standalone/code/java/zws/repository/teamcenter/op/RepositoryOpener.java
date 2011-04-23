/**
 * 
 */
package zws.repository.teamcenter.op;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Aug 31, 2007 3:28:35 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.repository.teamcenter.TC10RepositoryBase;
//import zws.service.repository.RepositorySvc;
import zws.service.repository.RepositorySvc;

/**
 * @author arbind
 *
 */
public class RepositoryOpener extends TC10OpBase {

  public void execute() throws Exception {
    debug(this, "opening repository " + getRepositoryName());
    TC10RepositoryBase rep = (TC10RepositoryBase)RepositorySvc.find(getRepositoryName());
    debug(this, "found repository " + getRepositoryName());
    setRepository(rep);
    debug(this, "logging in" + getAuthentication().getUsername() +":"+getAuthentication().getPassword());
    rep.login(getAuthentication());
    debug(this, "executing sub ops");
    executeKids();
    debug(this, "executing sub ops");
    rep.logout(getAuthentication());
  }
}
