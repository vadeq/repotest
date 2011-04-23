/*
 * DesignState - Design Compression Technology
 * @author: Arbind Thakur
 * @version: 1.0
 * Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */
package zws.repository.ilink3;

import zws.data.Metadata;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.repository.ilink3.qx.client.op.xml.GetBillHandler;
import zws.repository.ilink3.qx.client.op.xml.GetDependenciesHandler;
import zws.repository.ilink3.qx.program.IlinkQxProgram;
import zws.repository.ilink3.qx.program.IntralinkPoet;
import zws.repository.ilink3.qx.program.OpenRepository;
import zws.repository.ilink3.qx.program.QxProgram;
import zws.repository.ilink3.qx.program.ReportFirstLevelDependencies;
import zws.repository.ilink3.qx.program.ReportFirstLevelLatestDependencies;
import zws.repository.ilink3.qx.program.ReportLatestBill;
import zws.security.Authentication;
//import zws.util.{}//Logwriter;
import zws.repository.ilink3.qx.program.ReportBill;
import zws.repository.ilink3.qx.program.ReportDependencies;
import zws.repository.ilink3.qx.program.ReportLatestDependencies;
import zws.repository.source.RepositoryStructureSource;

import java.util.Collection;


/**
 * The Class Ilink3RepositoryMetadataSource.
 */
public class Ilink3RepositoryStructureSource extends Ilink3RepositoryBase
    implements RepositoryStructureSource {

  /**
   * The Constructor.
   * @param parent parentContext
   */
  public Ilink3RepositoryStructureSource(QxContext parent) {
    configureParentContext(parent);
  }

  /**
   * contains.
   * @param id authentication
   * @param runningCtx the running ctx
   * @param origin origin
   * @return boolean
   * @see zws.repository.source.RepositoryMetadataSource#contains(zws.origin.Origin,
   * zws.security.Authentication)
   */
  public final boolean contains(QxContext runningCtx, Origin origin,
      Authentication id) {
    return false;
  }

  // each method here will make a WEBService call (Client implementation)

  public final Metadata reportBOM(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole("Using ilink environment: " + getEnvRoot());
    Metadata resultData = null;
    QxInstruction qx = null;
    QxInstruction ilinkQx = null;
    QxInstruction openRep = null;
    QxInstruction reportBill = null;
    IntralinkPoet ilinkPoet = null;
    try {
      qx = new QxProgram();
      ilinkQx = new IlinkQxProgram();
      openRep = new OpenRepository(id.getUsername(), id.getPassword());
      reportBill = new ReportBill(origin);
      // preparte request instruction
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      openRep.add(reportBill);
      // prepare ilinkPoet object with result handler
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
      ilinkPoet.setProgram(qx);
      ilinkPoet.setXMLResultHandler(new GetBillHandler());
      // execute the instruction
      ilinkPoet.execute();
      Collection c = ilinkPoet.getResults();
      if (null == c || c.isEmpty()) {
        {}//Logwriter.printOnConsole("No bill results found in reportBill.");
        throw new Exception("No bill results found in reportBill");
      }
      resultData = (Metadata) c.iterator().next();
      {}//Logwriter.printOnConsole("Result Bill in Ilink3RepositoryMetadataSource");
      {}//Logwriter.printOnConsole(resultData.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultData;
  }

  public final Metadata reportLatestBOM(QxContext runningCtx, String name, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole("Using ilink environment: " + getEnvRoot());
    Metadata resultData = null;
    QxInstruction qx = null;
    QxInstruction ilinkQx = null;
    QxInstruction openRep = null;
    QxInstruction reportLatestBill = null;
    IntralinkPoet ilinkPoet = null;
    try {
      qx = new QxProgram();
      ilinkQx = new IlinkQxProgram();
      openRep = new OpenRepository(id.getUsername(), id.getPassword());
      reportLatestBill = new ReportLatestBill(name);
      // preparte request instruction
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      openRep.add(reportLatestBill);
      // prepare ilinkPoet object with result handler
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
      ilinkPoet.setProgram(qx);
      ilinkPoet.setXMLResultHandler(new GetBillHandler());
      // execute the instruction
      ilinkPoet.execute();
      Collection c = ilinkPoet.getResults();
      if (null == c || c.isEmpty()) {
        {}//Logwriter.printOnConsole("No bill results found in reportLatestBill.");
        throw new Exception("No bill results found in reportLatestBill");
      }
      resultData = (Metadata) c.iterator().next();
      {}//Logwriter.printOnConsole("Result LatestBill in Ilink3RepositoryMetadataSource");
      {}//Logwriter.printOnConsole(resultData.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultData;
  }
  public Collection reportDependencies(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    Collection firstLevelItems = null;
    {}//Logwriter.printOnConsole("Using ilink environment: " + getEnvRoot());
    QxInstruction qx = null;
    QxInstruction ilinkQx = null;
    QxInstruction openRep = null;
    QxInstruction dependencies = null;
    IntralinkPoet ilinkPoet = null;
    try {
      qx = new QxProgram();
      ilinkQx = new IlinkQxProgram();
      openRep = new OpenRepository(id.getUsername(), id.getPassword());
      dependencies = new ReportDependencies(origin);
      // preparte request instruction
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      openRep.add(dependencies);
      // prepare ilinkPoet object with result handler
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
      ilinkPoet.setProgram(qx);
      ilinkPoet.setXMLResultHandler(new GetDependenciesHandler());
      // execute the instruction
      ilinkPoet.execute();
      firstLevelItems = ilinkPoet.getResults();
      {}//Logwriter.printOnConsole("latestDependencies in Ilink3RepositoryMetadataSource");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return firstLevelItems;
  }

  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryStructureSource#reportAllDependencies(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public Collection reportLatestDependencies(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    Collection firstLevelItems = null;
    {}//Logwriter.printOnConsole("Using ilink environment: " + getEnvRoot());
    QxInstruction qx = null;
    QxInstruction ilinkQx = null;
    QxInstruction openRep = null;
    QxInstruction latestDependencies = null;
    IntralinkPoet ilinkPoet = null;
    try {
      qx = new QxProgram();
      ilinkQx = new IlinkQxProgram();
      openRep = new OpenRepository(id.getUsername(), id.getPassword());
      latestDependencies = new ReportLatestDependencies(origin);
      // preparte request instruction
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      openRep.add(latestDependencies);
      // prepare ilinkPoet object with result handler
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
      ilinkPoet.setProgram(qx);
      ilinkPoet.setXMLResultHandler(new GetDependenciesHandler());
      // execute the instruction
      ilinkPoet.execute();
      firstLevelItems = ilinkPoet.getResults();
      {}//Logwriter.printOnConsole("latestDependencies in Ilink3RepositoryMetadataSource");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return firstLevelItems;
  }

  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryStructureSource#reportAllLatestDependencies(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public Collection reportAllLatestDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }
  
  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryStructureSource#reportWhereUsed(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public Metadata reportWhereUsed(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryStructureSource#reportFirstLevelLatestDependencies(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public Collection reportFirstLevelLatestDependencies(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    Collection firstLevelItems = null;
    {}//Logwriter.printOnConsole("Using ilink environment: " + getEnvRoot());
    QxInstruction qx = null;
    QxInstruction ilinkQx = null;
    QxInstruction openRep = null;
    QxInstruction firstLevelDependencies = null;
    IntralinkPoet ilinkPoet = null;
    try {
      qx = new QxProgram();
      ilinkQx = new IlinkQxProgram();
      openRep = new OpenRepository(id.getUsername(), id.getPassword());
      firstLevelDependencies  = new ReportFirstLevelLatestDependencies(origin);
      // preparte request instruction
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      openRep.add(firstLevelDependencies);
      // prepare ilinkPoet object with result handler
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
      ilinkPoet.setProgram(qx);
      ilinkPoet.setXMLResultHandler(new GetDependenciesHandler());
      // execute the instruction
      ilinkPoet.execute();
      firstLevelItems = ilinkPoet.getResults();
      {}//Logwriter.printOnConsole("latestFirstLevelDependencies in Ilink3RepositoryMetadataSource");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return firstLevelItems;
  }

  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryStructureSource#reportFirstLevelDependencies(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public Collection reportFirstLevelDependencies(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    Collection firstLevelItems = null;
    {}//Logwriter.printOnConsole("Using ilink environment: " + getEnvRoot());
    QxInstruction qx = null;
    QxInstruction ilinkQx = null;
    QxInstruction openRep = null;
    QxInstruction firstLevelDependencies = null;
    IntralinkPoet ilinkPoet = null;
    try {
      qx = new QxProgram();
      ilinkQx = new IlinkQxProgram();
      openRep = new OpenRepository(id.getUsername(), id.getPassword());
      firstLevelDependencies  = new ReportFirstLevelDependencies(origin);
      // preparte request instruction
      qx.add(ilinkQx);
      ilinkQx.add(openRep);
      openRep.add(firstLevelDependencies);
      // prepare ilinkPoet object with result handler
      ilinkPoet = new IntralinkPoet();
      ilinkPoet.setRepository(this);
      ilinkPoet.setProgram(qx);
      ilinkPoet.setXMLResultHandler(new GetDependenciesHandler());
      // execute the instruction
      ilinkPoet.execute();
      firstLevelItems = ilinkPoet.getResults();
      {}//Logwriter.printOnConsole("latestFirstLevelDependencies in Ilink3RepositoryMetadataSource");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return firstLevelItems;
  }

  public Metadata reportBOMPlusDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    return null;
  }

  public Metadata reportLatestBOMPlusDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    return null;
  }
}