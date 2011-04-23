/*
 * DesignState - Design Compression Technology @author: Arbind Thakur @version:
 * 1.0 Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */
package zws.repository.ilink3;


import zws.data.Metadata;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.repository.ilink3.qx.client.op.xml.SearchResultHandler;
import zws.repository.ilink3.qx.program.Find;
import zws.repository.ilink3.qx.program.IlinkQxProgram;
import zws.repository.ilink3.qx.program.IntralinkPoet;
import zws.repository.ilink3.qx.program.OpenRepository;
import zws.repository.ilink3.qx.program.QxProgram;
import zws.security.Authentication;
//import zws.util.{}//Logwriter;
import zws.repository.ilink3.qx.program.FindLatest;
import zws.repository.source.RepositoryMetadataSource;

import java.util.Collection;


/**
 * The Class Ilink3RepositoryMetadataSource.
 */
public class Ilink3RepositoryMetadataSource extends Ilink3RepositoryBase
    implements RepositoryMetadataSource {

  /**
   * The Constructor.
   * @param parent parentContext
   */
  public Ilink3RepositoryMetadataSource(QxContext parent) {
    configureParentContext(parent);
  }

  /**
   * contains.
   * @param runningCtx QxContext
   * @param origin origin
   * @param id authentication
   * @return boolean
   * @see zws.repository.source.RepositoryMetadataSource#contains(zws.origin.Origin,
   * zws.security.Authentication)
   */
  public final boolean contains(QxContext runningCtx, Origin origin, Authentication id) {
    return false;
  }

  // each method here will make a WEBService call (Client implementation)
  /**
   * find object(s) laterst revision.
   * @param runtimeCtx QxContext
   * @param name object name
   * @param id authentication
   * @throws Exception exception
   * @return Metadata result data
   * @see zws.repository.source.RepositoryMetadataSource#findLatest(java.lang.String,
   * zws.security.Authentication)
   */
  public final Metadata findLatest(final QxContext runtimeCtx, String name, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole("runtimeCtx 1 " + runtimeCtx);
    //pushContext(runtimeCtx);
    {}//Logwriter.printOnConsole("Using UPDATED  ilink environment: " + getEnvRoot());
    {}//Logwriter.printOnConsole("runtimeCtx 2 " + runtimeCtx);
    Metadata resultData       = null;
    QxInstruction qx          = null;
    QxInstruction ilinkQx     = null;
    QxInstruction openRep     = null;
    QxInstruction findLatest  = null;
    IntralinkPoet ilinkPoet   = null;

//getContext().get(PROCESS_ID)

    try {
      qx          = new QxProgram();
      ilinkQx     = new IlinkQxProgram();
      openRep     = new OpenRepository(id.getUsername(), id.getPassword());
      findLatest  = new FindLatest(name);
      // preparte request instruction
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      openRep.add(findLatest);
      // prepare ilinkPoet object with result handler
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
      ilinkPoet.setProgram(qx);
      ilinkPoet.setXMLResultHandler(new SearchResultHandler());
      // execute the instruction
      ilinkPoet.execute();
      Collection c = ilinkPoet.getResults();
      if (null == c || c.isEmpty()) {
        {}//Logwriter.printOnConsole("No results found in Findlatest.");
        //throw new Exception("No results found in Findlatest");
      }
      resultData = (Metadata) c.iterator().next();
      {}//Logwriter.printOnConsole("Result data in Ilink3RepositoryMetadataSource");
      {}//Logwriter.printOnConsole(resultData.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    //popContext();
    {}//Logwriter.printOnConsole("runtimeCtx 3 " + runtimeCtx);
    return resultData;
  }

  /**
   * find object(s) laterst revision.
   * @param runningCtx QxContext
   * @param origin origin object
   * @param id authentication
   * @throws Exception exception
   * @return Metadata result data
   * @see zws.repository.source.RepositoryMetadataSource#find(zws.origin.Origin, zws.security.Authentication)
   */
  public final Metadata find(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole("runtimeCtx 1 " + runningCtx);
    //pushContext(runtimeCtx);
    {}//Logwriter.printOnConsole("Using UPDATED  ilink environment: " + getEnvRoot());
    {}//Logwriter.printOnConsole("runtimeCtx 2 " + runningCtx);
    Metadata resultData       = null;
    QxInstruction qx          = null;
    QxInstruction ilinkQx     = null;
    QxInstruction openRep     = null;
    QxInstruction find  = null;
    IntralinkPoet ilinkPoet   = null;

//getContext().get(PROCESS_ID)

    try {
      qx          = new QxProgram();
      ilinkQx     = new IlinkQxProgram();
      openRep     = new OpenRepository(id.getUsername(), id.getPassword());
      find = new Find(origin);
      // preparte request instruction
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      openRep.add(find);
      // prepare ilinkPoet object with result handler
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
      ilinkPoet.setProgram(qx);
      ilinkPoet.setXMLResultHandler(new SearchResultHandler());
      // execute the instruction
      ilinkPoet.execute();
      Collection c = ilinkPoet.getResults();
      if (null == c || c.isEmpty()) {
        {}//Logwriter.printOnConsole("No results found in Findlatest.");
        //throw new Exception("No results found in Findlatest");
      }
      resultData = (Metadata) c.iterator().next();
      {}//Logwriter.printOnConsole("Result data in Ilink3RepositoryMetadataSource");
      {}//Logwriter.printOnConsole(resultData.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    //popContext();
    {}//Logwriter.printOnConsole("runtimeCtx 3 " + runningCtx);
    return resultData;
  }

  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryMetadataSource#searchLatest(zws.qx.QxContext, java.lang.String, zws.security.Authentication)
   */
  public Collection searchLatest(QxContext runtimeCtx, String name, Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

}
