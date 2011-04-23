package zws.repository.target;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.security.Authentication;


import java.util.Collection;
import java.util.Map;


/**
 * The Interface RepositoryWorkflowTarget.
 */
public interface RepositoryECOTarget extends Repository {

String createECO(QxContext runningCtx, String agileClass, Authentication id) throws Exception;
String createECO(QxContext runningCtx, Map ecoAttributes, Authentication id) throws Exception;
void undoPriorRedlines(QxContext runningCtx, String ecoNumber, String itemNumber, Authentication id)  throws Exception;
void addAffectedItem(QxContext runningCtx, String ecoNumber, Metadata affectedItem, Authentication id)  throws Exception;
void addAffectedItem(QxContext runningCtx, String ecoNumber, String itemNumber, Authentication id)  throws Exception;
void addAffectedItems(QxContext runningCtx, String ecoNumber, Collection itemNumbers, Authentication id)  throws Exception;
void update(QxContext runningCtx, String ecoNumber, Metadata data, Authentication id) throws Exception;
void redlineAdd(QxContext runningCtx, String ecoNumber, String itemNumber, String subComponentNumber, int qry, QxContext bomAttributes, Authentication id)  throws Exception;
void redlineModify(QxContext runningCtx, String ecoNumber, String itemNumber, String subComponentNumber, QxContext bomAttributes, Authentication id)  throws Exception;
void redlineDelete(QxContext runningCtx, String ecoNumber, String itemNumber, String subComponentNumber, Authentication id)  throws Exception;
void redlineQty(QxContext runningCtx, String ecoNumber, String itemNumber, String subComponentNumber, int qry, Authentication id)  throws Exception;
void removeAttachment(QxContext runningCtx, String ecoNumber, String attachmentName, String itemNumber, Authentication id) throws Exception;
void removeAttachments(QxContext runningCtx, String ecoNumber,  String itemNumber, Authentication id) throws Exception;
void deleteEmptyECO(QxContext runningCtx, String ecoNumber, boolean truncateFlag, Authentication id) throws Exception;
}
