package zws.repository.target;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.bill.intralink.BillOfMaterials;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.util.Routed;
import zws.util.Prototype;

import java.io.Serializable;




/**
 * The Interface RepositoryStructureTarget.
 */
public interface RepositoryStructureTarget extends Prototype, Routed, Serializable  {

  //Metadata createAndStructureBill(QxContext runningCtx, BillOfMaterials bill, Authentication id) throws Exception;
  void createAndStructureBill(QxContext runningCtx, BillOfMaterials bill, Authentication id) throws Exception;
  void structureBill(QxContext runningCtx, BillOfMaterials bill, Authentication id) throws Exception;
}
