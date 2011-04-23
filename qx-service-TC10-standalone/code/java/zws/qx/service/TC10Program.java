/**
 * 
 */
package zws.qx.service;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Aug 31, 2007 5:36:30 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.data.Metadata;
import zws.op.OpBase;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.qx.program.QxInstruction;
import zws.repository.teamcenter.op.RepositoryOpener;
import zws.repository.teamcenter.op.TC10AttachmentAdder;
import zws.repository.teamcenter.op.TC10AttachmentUpdater;
import zws.repository.teamcenter.op.TC10BOMReporter;
import zws.repository.teamcenter.op.TC10BinaryAdder;
import zws.repository.teamcenter.op.TC10BinaryUpdater;
import zws.repository.teamcenter.op.TC10Creater;
import zws.repository.teamcenter.op.TC10FetchDesignFiles;
import zws.repository.teamcenter.op.TC10FetchNativeFile;
import zws.repository.teamcenter.op.TC10Finder;
import zws.repository.teamcenter.op.TC10LatestFinder;
import zws.repository.teamcenter.op.TC10Op;
import zws.repository.teamcenter.op.TC10Renamer;
import zws.repository.teamcenter.op.TC10Searcher;
import zws.repository.teamcenter.op.TC10Updater;
import zws.repository.teamcenter.TC10Constants;
import zws.security.Authentication;
import zws.security.CryptoUtil;
import zws.util.PrintUtil;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

class TC10Program extends OpBase {
  TC10Program(QxInstruction root) throws Exception { setRootInstruction(root); }
  
  public void execute() throws Exception {
    {} //System.out.println("XxxxxxxxxxxLoading " );
    load(getRootInstruction());
    {} //System.out.println("XxxxxxxxxxxExecuting " );
    opTree.execute();
    {} //System.out.println("XxxxxxxxxxxDone " );
    setResult(opTree.getResult());
  }

  private void load(QxInstruction i) throws Exception {
    TC10Op op = lookupOp(i);
    if (null!=op) {
      {} //System.out.println("OOOOOOOOOO Pushing " + op.getClass().getName());
      stack.push(op);
    }
    Collection c = i.getSubInstructions();
    if (null!=c && !c.isEmpty()) {
      Iterator t = c.iterator();
      QxInstruction sub=null;
      while(t.hasNext()) {
        sub = (QxInstruction)t.next();
        load(sub);
      }
    }
    if (null==op) return;

    stack.pop();
    TC10Op parent=null;
    if (!stack.isEmpty()) {
      parent = (TC10Op)stack.peek();
      if (null!=parent)  {} //System.out.println("OOOOOOOOOO Peeking" + parent.getClass().getName());
      else  {} //System.out.println("OOOOOOOOOO Peeking NULL" );
    }

    if (null!=parent) {
      {} //System.out.println("OOOOOOOOOO Adding " + op.getClass().getName() + " INTO " + parent.getClass().getName() );
      parent.add(op);
    }
    else{
      {} //System.out.println("OOOOOOOOO SETTING OPTREE" + op.getClass().getName());
      opTree = op;
    }
  }
  
  private TC10Op lookupOp(QxInstruction i) throws Exception {
    TC10Op op=null;
    // Source Module
    if (TC10Constants.TAG_OPEN_REPOSITORY.equals(i.getName())) op = materializeRepositoryOpener(i);
    if (TC10Constants.TAG_FIND.equals(i.getName())) op = materializeFinder(i);
    if (TC10Constants.TAG_FIND_LATEST.equals(i.getName())) op = materializeLatestFinder(i);
    if (TC10Constants.TAG_SEARCH.equals(i.getName()))op = materializeSearcher(i);
    if (TC10Constants.TAG_FETCH_NATIVE_FILE.equals(i.getName()))op = materializeFetchNativeFile(i);
    if (TC10Constants.TAG_FETCH_DESIGN_FILES.equals(i.getName()))op = materializeFetchDesignFiles(i);
    if (TC10Constants.TAG_REPORT_BOM.equals(i.getName()))op = materializeBOMReporter(i);
    // Target Module
    if (TC10Constants.TAG_CREATE.equals(i.getName()))op = materializeCreater(i);
    if (TC10Constants.TAG_UPDATE.equals(i.getName()))op = materializeUpdater(i);
    if (TC10Constants.TAG_RENAME.equals(i.getName()))op = materializeRenamer(i);
    if (TC10Constants.TAG_ADD_BINARY.equals(i.getName()))op = materializeBinaryAdder(i);
    if (TC10Constants.TAG_UPDATE_BINARY.equals(i.getName()))op = materializeBinaryUpdater(i);
    if (TC10Constants.TAG_ADD_ATTACHMENT.equals(i.getName()))op = materializeAttachmentAdder(i);
    if (TC10Constants.TAG_UPDATE_ATTACHMENT.equals(i.getName()))op = materializeAttachmentUpdater(i);
    return op;
  }

  private TC10Op materializeRepositoryOpener(QxInstruction i) throws Exception {
    RepositoryOpener op = new RepositoryOpener();
    String username = i.get(TC10Constants.ATT_USER);
    //String password = i.get(TC10Constants.ATT_PASSWORD);
    String encryptedPassword = i.get(TC10Constants.ATT_ENCRYPTED_PASSWORD);
    String repositoryName = i.get(TC10Constants.ATT_REPOSITORY_NAME);
    CryptoUtil encryptutil = new CryptoUtil();
    String password =  encryptutil.decrypt(encryptedPassword);
    
    Authentication id = new Authentication(username, password);

    op.setRepositoryName(repositoryName);
    op.setAuthentication(id);
    
    {} //System.out.println("YYYYYYYYYYY i");
    PrintUtil.print("properties from TC10Program.materializeRepositoryOpener " +i.getProperties());
    
    {} //System.out.println("YYYYYYYYYYY repository-name" + repositoryName);
    {} //System.out.println("YYYYYYYYYYY repository" + op.getRepositoryName());
    {} //System.out.println("YYYYYYYYYYY user" + op.getAuthentication().getUsername());
    {} //System.out.println("YYYYYYYYYYY password" + op.getAuthentication().getPassword());
    return op;
  }

  private TC10Op materializeFinder(QxInstruction i) throws Exception {
    TC10Finder op = new TC10Finder();
    String originValue = i.get(TC10Constants.ATT_ORIGIN);
    Origin o = OriginMaker.materialize(originValue);
    op.setOrigin(o);
    return op;
  }
  
  private TC10Op materializeLatestFinder(QxInstruction i) throws Exception {
    TC10LatestFinder op = new TC10LatestFinder();
    String itemIdValue = i.get(TC10Constants.ATT_ITEM_ID);
    op.setItemId(itemIdValue);
    return op;
  }  
  
  private TC10Op materializeSearcher(QxInstruction i) {
    TC10Searcher op = new TC10Searcher();
    String criteria = i.get(TC10Constants.ATT_CRITERIA);
    op.setCriteria(criteria);
    return op;
  }

  private TC10Op materializeFetchNativeFile(QxInstruction i) throws Exception {
    TC10FetchNativeFile op = new TC10FetchNativeFile ();
    String originValue = i.get(TC10Constants.ATT_ORIGIN);
    Origin o = OriginMaker.materialize(originValue);
    op.setOrigin(o);
    return op;
  }
  
  private TC10Op materializeFetchDesignFiles(QxInstruction i) throws Exception {
    TC10FetchDesignFiles op = new TC10FetchDesignFiles ();
    String originValue = i.get(TC10Constants.ATT_ORIGIN);
    Origin o = OriginMaker.materialize(originValue);
    op.setOrigin(o);
    return op;
  }


  private TC10Op materializeBOMReporter(QxInstruction i) throws Exception {
    TC10BOMReporter op = new TC10BOMReporter();
    String originValue = i.get(TC10Constants.ATT_ORIGIN);
    String config = i.get(TC10Constants.ATT_CONFIGURATION);
    Origin o = OriginMaker.materialize(originValue);
    op.setOrigin(o);
    op.setConfiguration(config);
    return op;
  }
  
  private TC10Op materializeCreater(QxInstruction i) throws Exception {
    TC10Creater op = new TC10Creater();
    String metadataValue = i.get(TC10Constants.ATT_METADATA);
    Metadata data = op.getMetadata(); //metadataValue
    op.setMetadata(data);
    return op;
  }
  
  private TC10Op materializeUpdater(QxInstruction i) throws Exception {
    TC10Updater op = new TC10Updater();
    String fileValue = i.get(TC10Constants.ATT_ABS_PATH);
    String metadataValue = i.get(TC10Constants.ATT_METADATA);
    File file = new File(fileValue);
    Metadata data = op.getMetadata(); //metadataValue
    op.setFile(file);
    op.setMetadata(data);
    return op;
  }
  
  private TC10Op materializeRenamer(QxInstruction i) throws Exception {
    TC10Renamer op = new TC10Renamer();
    String originValue = i.get(TC10Constants.ATT_ORIGIN);
    String newNameValue = i.get(TC10Constants.ATT_NAME);
    Origin o = OriginMaker.materialize(originValue);
    op.setOrigin(o);
    op.setNewName(newNameValue);
    return  op;
  }
  
  private TC10Op materializeBinaryAdder(QxInstruction i) throws Exception {
    TC10BinaryAdder op = new TC10BinaryAdder();
    String fileValue = i.get(TC10Constants.ATT_ABS_PATH);
    File file = new File(fileValue);
    op.setFile(file);
    return  op;
  }
  
  private TC10Op materializeBinaryUpdater(QxInstruction i) throws Exception {
    TC10BinaryUpdater op = new TC10BinaryUpdater();
    String fileValue = i.get(TC10Constants.ATT_ABS_PATH);
    File file = new File(fileValue);
    op.setFile(file);
    return  op;
  }
  
  private TC10Op materializeAttachmentAdder(QxInstruction i) throws Exception {
    TC10AttachmentAdder op = new TC10AttachmentAdder();
    String fileValue = i.get(TC10Constants.ATT_ABS_PATH);
    String originValue = i.get(TC10Constants.ATT_ORIGIN);
    File file = new File(fileValue);
    Origin o = OriginMaker.materialize(originValue);
    op.setFile(file);
    op.setOrigin(o);
    return op;
  }
  
  private TC10Op materializeAttachmentUpdater(QxInstruction i) throws Exception {
    TC10AttachmentUpdater op = new TC10AttachmentUpdater();
    String fileValue = i.get(TC10Constants.ATT_ABS_PATH);
    String originValue = i.get(TC10Constants.ATT_ORIGIN);
    File file = new File(fileValue);
    Origin o = OriginMaker.materialize(originValue);
    op.setFile(file);
    op.setOrigin(o);
    return op;
  }  
  
  private QxInstruction getRootInstruction() { return root; }
  private void setRootInstruction(QxInstruction r) { root=r; }
  
  private TC10Op opTree=null;
  private QxInstruction root = null;
  private Stack stack = new Stack();
}
