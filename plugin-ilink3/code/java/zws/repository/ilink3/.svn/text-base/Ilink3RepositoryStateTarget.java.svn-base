/*
 * DesignState - Design Compression Technology
 * @author: Arbind Thakur @version:
 * 1.0 Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */
package zws.repository.ilink3;

import zws.data.Metadata;
import zws.origin.IntralinkOrigin;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.repository.ilink3.qx.client.op.xml.SearchResultHandler;
import zws.repository.ilink3.qx.program.Demote;
import zws.repository.ilink3.qx.program.IlinkQxProgram;
import zws.repository.ilink3.qx.program.IntralinkPoet;
import zws.repository.ilink3.qx.program.Lock;
import zws.repository.ilink3.qx.program.OpenRepository;
import zws.repository.ilink3.qx.program.Promote;
import zws.repository.ilink3.qx.program.QxProgram;
import zws.repository.ilink3.qx.program.SetLifeCycleAttribute;
import zws.repository.ilink3.qx.program.Unlock;
import zws.security.Authentication;
//import zws.util.{}//Logwriter;
import zws.repository.target.RepositoryStateTarget;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * The Class Ilink3RepositoryMetadataSource.
 */
public class Ilink3RepositoryStateTarget extends Ilink3RepositoryBase
    implements RepositoryStateTarget {

  /**
   * The Constructor.
   * @param parent parentContext
   */
  public Ilink3RepositoryStateTarget(QxContext parent) {
    configureParentContext(parent);
  }

  public void setStateAttribute(QxContext runningCtx, Origin origin, String attribute, String newValue, Authentication id) throws Exception {}
  
  /**
   * Write state attribute.
   * @param attribValues the key-value pair of attributes and new values
   * @param origin the origin
   * @param runningCtx the running ctx
   * @param id the id
   * @throws Exception the exception
   */
  public void setStateAttributes(QxContext runningCtx, Origin origin,
      Map attribValues, Authentication id) throws Exception {

    // update the state attribute
    Metadata resultData       = null;
    QxInstruction qx          = null;
    QxInstruction ilinkQx     = null;
    QxInstruction openRep     = null;
    QxInstruction stAttribute  = null;
    IntralinkPoet ilinkPoet   = null;
    IntralinkOrigin iLinkOrigin = (IntralinkOrigin) origin;
    String attribute = null;
    String value = null;
    try {
      qx          = new QxProgram();
      ilinkQx     = new IlinkQxProgram();
      openRep     = new OpenRepository(id.getUsername(), id.getPassword());
      // preparte request instruction
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      // prepare lifecycle attribute update statements
      Iterator itr = attribValues.keySet().iterator();
      while (itr.hasNext()) {
        stAttribute = null;
        attribute = (String) itr.next();
        value = (String) attribValues.get(attribute);
        //SetLifeCycleAttribute(name, branch, revision, version,parameter, val)
        stAttribute  = new SetLifeCycleAttribute(iLinkOrigin.getName(),
                                                 iLinkOrigin.getBranch(),
                                                 iLinkOrigin.getRevision(),
                                                 String.valueOf(iLinkOrigin.getVersion()),
                                                 attribute, value);
        openRep.add(stAttribute);
      }

      // prepare ilinkPoet object with result handler
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
      ilinkPoet.setProgram(qx);
      ilinkPoet.setXMLResultHandler(new SearchResultHandler());
      // execute the instruction
      ilinkPoet.execute();
      Collection c = ilinkPoet.getResults();
      if (null == c || c.isEmpty()) {
        {}//Logwriter.printOnConsole("No results found in writeStateAttributes.");
        throw new Exception("No results found in writeStateAttributes");
      }
      resultData = (Metadata) c.iterator().next();
      {}//Logwriter.printOnConsole("Result data in Ilink3RepositoryMetadataSource");
      {}//Logwriter.printOnConsole(resultData.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    //popContext();
  }

  public void promoteLifeCycleReleaseState(QxContext runningCtx, Origin origin, String promoteTo, Authentication id) throws Exception {
    QxInstruction qx          = null;
    QxInstruction ilinkQx     = null;
    QxInstruction openRep     = null;
    QxInstruction promote = null;
    IntralinkPoet ilinkPoet   = null;
    IntralinkOrigin iLinkOrigin = (IntralinkOrigin) origin;
    try {
      qx          = new QxProgram();
      ilinkQx     = new IlinkQxProgram();
      openRep     = new OpenRepository(id.getUsername(), id.getPassword());
      promote = new Promote(origin, promoteTo, null, null, null);
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      openRep.add(promote);
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
      ilinkPoet.setProgram(qx);
      ilinkPoet.execute();
    } catch (Exception e) {
      e.printStackTrace();
    }    
  }

  public void demoteLifeCycleReleaseState(QxContext runningCtx, Origin origin, String demoteTo, Authentication id) throws Exception {
    QxInstruction qx          = null;
    QxInstruction ilinkQx     = null;
    QxInstruction openRep     = null;
    QxInstruction demote = null;
    IntralinkPoet ilinkPoet   = null;
    try {
      qx          = new QxProgram();
      ilinkQx     = new IlinkQxProgram();
      openRep     = new OpenRepository(id.getUsername(), id.getPassword());
      demote     = new Demote(origin, demoteTo, null, null, null);
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      openRep.add(demote);
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
      ilinkPoet.setProgram(qx);
      ilinkPoet.execute();
    } catch (Exception e) {
      e.printStackTrace();
    }    
  }


  public void lock(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    QxInstruction qx          = null;
    QxInstruction ilinkQx     = null;
    QxInstruction openRep     = null;
    QxInstruction lock = null;
    IntralinkPoet ilinkPoet   = null;
    try {
      qx          = new QxProgram();
      ilinkQx     = new IlinkQxProgram();
      openRep     = new OpenRepository(id.getUsername(), id.getPassword());
      Collection c = new Vector();
      c.add(origin);
      lock = new Lock(c);
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      openRep.add(lock);
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
      ilinkPoet.setProgram(qx);
      ilinkPoet.execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void lock(QxContext runningCtx, Collection origins, Authentication id) throws Exception {
    QxInstruction qx          = null;
    QxInstruction ilinkQx     = null;
    QxInstruction openRep     = null;
    QxInstruction lock = null;
    IntralinkPoet ilinkPoet   = null;
    try {
      qx          = new QxProgram();
      ilinkQx     = new IlinkQxProgram();
      openRep     = new OpenRepository(id.getUsername(), id.getPassword());
      lock = new Lock(origins);
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      openRep.add(lock);
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
      ilinkPoet.setProgram(qx);
      ilinkPoet.execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void unlock(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    QxInstruction qx          = null;
    QxInstruction ilinkQx     = null;
    QxInstruction openRep     = null;
    QxInstruction unlock = null;
    IntralinkPoet ilinkPoet   = null;
    IntralinkOrigin iLinkOrigin = (IntralinkOrigin) origin;
    try {
      qx          = new QxProgram();
      ilinkQx     = new IlinkQxProgram();
      openRep     = new OpenRepository(id.getUsername(), id.getPassword());
      Collection c = new Vector();
      c.add(origin);
      unlock = new Unlock(c);
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      openRep.add(unlock);
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
      ilinkPoet.setProgram(qx);
      ilinkPoet.execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void unlock(QxContext runningCtx, Collection origins, Authentication id) throws Exception {
    QxInstruction qx          = null;
    QxInstruction ilinkQx     = null;
    QxInstruction openRep     = null;
    QxInstruction unlock = null;
    IntralinkPoet ilinkPoet   = null;
    try {
      qx          = new QxProgram();
      ilinkQx     = new IlinkQxProgram();
      openRep     = new OpenRepository(id.getUsername(), id.getPassword());
      unlock = new Unlock(origins);
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      openRep.add(unlock);
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
      ilinkPoet.setProgram(qx);
      ilinkPoet.execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateRevision(QxContext runningCtx, Origin origin, String newRevision, Authentication id) throws Exception {}
}
