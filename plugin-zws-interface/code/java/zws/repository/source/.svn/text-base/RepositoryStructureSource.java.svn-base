package zws.repository.source;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.util.Routed;
import zws.util.Prototype;

import java.io.*;
import java.util.Collection;



/**
 * The Interface RepositoryStructureSource.
 */
public interface RepositoryStructureSource extends Prototype, Routed, Serializable  {

  /** The EXAC t_ STRUCTUR e_ CONFIGURATION. */
  static String EXACT_STRUCTURE_CONFIGURATION = "exact";

  /** The LATES t_ STRUCTUR e_ CONFIGURATION. */
  static String LATEST_STRUCTURE_CONFIGURATION = "latest";

  /**
   * Report BOM.
   *
   * @param o the o
   * @param runningCtx the running ctx
   * @param structureConfiguration the structure configuration
   * @param id the id
   *
   * @return the metadata
   *
   * @throws Exception the exception
   */
  Metadata reportBOM(QxContext runningCtx, Origin o, Authentication id) throws Exception;

  /**
   * Report latest bill.
   *
   * @param runningCtx the running ctx
   * @param origin the origin
   * @param id the id
   *
   * @return the metadata
   *
   * @throws Exception the exception
   */
  Metadata reportLatestBOM(QxContext runningCtx, String name, Authentication id)  throws Exception;

  /**
   * Report where used.
   *
   * @param o the o
   * @param runningCtx the running ctx
   * @param structureConfiguration the structure configuration
   * @param id the id
   *
   * @return the metadata
   *
   * @throws Exception the exception
   */
  Metadata reportWhereUsed(QxContext runningCtx, Origin o, Authentication id) throws Exception;

  /**
   * get mulit-level-dependencies.
   *
   * @param runningCtx the running ctx
   * @param origin the origin
   * @param id the id
   *
   * @return the metadata
   *
   * @throws Exception the exception
   */
  Collection reportDependencies(QxContext runningCtx, Origin origin, Authentication id)  throws Exception;

  /**
   * Report ulti-level-dependencies.
   *
   * @param o the o
   * @param runningCtx the running ctx
   * @param structureConfiguration the structure configuration
   * @param id the id
   *
   * @return the metadata
   *
   * @throws Exception the exception
   */
  Collection reportLatestDependencies(QxContext runningCtx, Origin o,  Authentication id) throws Exception;

  
  /**
   * get mulit-level-dependencies.
   *
   * @param runningCtx the running ctx
   * @param origin the origin
   * @param id the id
   *
   * @return the metadata
   *
   * @throws Exception the exception
   */
  Collection reportFirstLevelDependencies(QxContext runningCtx, Origin origin, Authentication id)  throws Exception;

  /**
   * Report ulti-level-dependencies.
   *
   * @param o the o
   * @param runningCtx the running ctx
   * @param structureConfiguration the structure configuration
   * @param id the id
   *
   * @return the metadata
   *
   * @throws Exception the exception
   */
  Collection reportFirstLevelLatestDependencies(QxContext runningCtx, Origin o,  Authentication id) throws Exception;
  

  /**
   * Report BOMAndDependencies.
   *
   * @param o the o
   * @param runningCtx the running ctx
   * @param structureConfiguration the structure configuration
   * @param id the id
   *
   * @return the bom and dependencies
   *
   * @throws Exception the exception
   */
  Metadata reportBOMPlusDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception;
  

  /**
   * Report BOMAndDependencies.
   *
   * @param o the o
   * @param runningCtx the running ctx
   * @param structureConfiguration the structure configuration
   * @param id the id
   *
   * @return the bom and dependencies
   *
   * @throws Exception the exception
   */
  Metadata reportLatestBOMPlusDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception;
    
}
