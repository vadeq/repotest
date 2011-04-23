package zws.pen.policy.op.pendata;

import zws.pen.policy.PENPolicy;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.service.pen.PENData;
import zws.exception.InvalidName;
import zws.op.Op;
import zws.origin.Origin;

import java.util.Collection;
import java.util.Iterator;


/*
 * DesignState - Design Compression Technology @author: ptoleti @version: 1.0
 * Created on Apr 26, 2007 12:09:08 PM Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */

/**
 * The Interface PENDataOp.
 * @author ptoleti
 */
public interface PENDataOp extends Op {

  QxContext getQxCtx();
  void setQxCtx(QxContext ctx);

  PENData getPenData();
  void setPenData(PENData penData);

  Collection getOriginsToPublish();
  void setOriginsToPublish(Collection origins);

  PENPolicy getPenPolicy();
  void setPenPolicy(PENPolicy penPolicy);

  Authentication getAuthentication();
  void setAuthentication(Authentication authID);

  void initializeRuntime(QxContext qxctx, Collection originsToPublish, PENPolicy penPolicy, PENData penData, Authentication id);
  void add(PENDataOp op) throws Exception;

  String getCurrentItem();
  void setCurrentItem(String item) throws InvalidName;

  Iterator getCurrentIterator();
  void setCurrentIterator(Iterator itr);

  // Build in Variables
  public static final String STAT_SYNC_STATUS="sync-status";
  public static final String OPT_SYNCHRONIZED ="synchronized";
  public static final String OPT_NEW_ITEM ="new-item";
  public static final String OPT_MODIFIED ="modified";
  public static final String OPT_RENUMBERED ="renumbered";

  public static final String STAT_ITEM_IS_VALID="item-is-valid";
  public static final String STAT_FILTER_OUT="filter-out";

  public static final String STAT_ITEM_HAS_NAME_CONFLICT ="item-has-name-conflict";
  public static final String DATA_NAME_CONFLICT_ORIGIN="name-conflict-origin";

  public static final String STAT_ITEM_HAS_SYNCHRONIZATION_CONFLICT ="item-has-synchronization-conflict";
  public static final String DATA_SYNCHRONIZATION_CONFLICT_UID="sync-conflict-uid";

  public static final String STAT_ITEM_HAS_PENDING_ECO ="item-has-pending-eco";
  public static final String DATA_PENDING_ECO_NUMBER ="pending-eco-number";
  public static final String STAT_ITEM_HAS_MULTIPLE_PENDING_ECOs ="item-has-mulitiple-pending-ecos";

  public static final String STAT_ITEM_IS_ON_OPEN_ECO ="item-has-pending-eco";
  public static final String STAT_TARGET_IS_ALREADY_RELEASED ="target-is-already-released";

  public static final String DATA_TARGET_ECO ="target-eco";

  //publishing pattern actions
  public static final String ACTION_ITEM_NEEDS_TO_BE_CREATED ="item-needs-to-be-created";
  public static final String ACTION_METADATA_NEEDS_TO_BE_UPDATED ="metadata-needs-to-be-updated";
  public static final String ACTION_STRUCT_NEEDS_TO_BE_UPDATED ="struct-needs-to-be-updated";
  public static final String ACTION_FILE_NEEDS_TO_BE_UPDATED ="file-needs-to-be-updated";
  public static final String ACTION_ITEM_NEEDS_TO_BE_SYNCHRONIZED ="item-needs-to-be-synchronized";
  public static final String ACTION_DOC_NEEDS_TO_BE_ADDED ="doc-needs-to-be-added";
  public static final String DATA_TARGET_ORIGIN ="target-origin";
  public static final String DATA_TARGET_NAME ="target-name";


  public static final String STAT_ITEM_NEED_TO_BE_REPUBLISHED ="item-needs-to-be-republished";

  //public static final String STAT_IS_CANCELLED="cancelled";

  public static final String STAT_ITEM_IS_DESIGN_ROOT="item-is-design-root";
  //public static final String STAT_IS_SUB_COMPONENT="item-is-sub-component";

  public static final String TRUE ="true";
  public static final String FALSE ="false";
}
