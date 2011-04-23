package zws.repository.teamcenter.op;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Aug 31, 2007 3:28:35 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.op.ListOp;
import zws.repository.teamcenter.TC10RepositoryBase;
import zws.security.Authentication;

public interface TC10Op extends ListOp {
  public void add(TC10Op op);
  
  Authentication getAuthentication();
  void setAuthentication(Authentication a);

  String getRepositoryName();
  void setRepositoryName(String s);

  TC10RepositoryBase getRepository();
  void setRepository(TC10RepositoryBase r);
}
