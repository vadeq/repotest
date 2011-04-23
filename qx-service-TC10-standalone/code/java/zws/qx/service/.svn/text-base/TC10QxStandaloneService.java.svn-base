package zws.qx.service;

import zws.data.Metadata;
import zws.origin.tc10.TC10ItemRevOrigin;
import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.repository.teamcenter.TC10RepositoryBase;
import zws.repository.teamcenter.TC10RepositoryMetadataSource;
import zws.security.Authentication;
import zws.service.repository.RepositorySvc;
//import zws.util.{}//Logwriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
/**
 * The Class PingQxService.
 * @author ptoleti
 */
public class TC10QxStandaloneService implements Qx {

  /**
   * Webservice for PEN Policy.
   * @param dataInstruction QxXML
   * @param ctx QX context
   * @return QX MQL
   * @see zws.qx.Qx#executeQx(zws.qx.QxContext, zws.qx.xml.QxXML)
   */
  public QxXML executeQx(QxContext ctx, QxXML dataInstruction) {
    QxXML qxResult = null;
    try {
      {}//Logwriter.printOnConsole("TC10QxService invoked with dataInstruction :");
      {}//Logwriter.printOnConsole("" + dataInstruction);
      QxInstruction qxInstr = dataInstruction.toQxProgram();
      TC10Program program = new TC10Program(qxInstr);
      program.execute();
      Object o = program.getResult();
      QxXML resultXML = null;
      if (null==o) resultXML = new QxXML("<no-results/>");
      else {
        String s = "<results>";
        if(o instanceof Collection) {
          Object resultObj = null;
          Iterator itr = ((Collection)o).iterator();
          while(itr.hasNext()) {
            resultObj = itr.next();
            s+=resultObj.toString();
          }
        } else {
          s += o.toString();
        }
        s += "</results>";
        resultXML = new QxXML(s);
      }
      return resultXML;

      /*
      String method = qxInstr.getName();
      Metadata m = simulateFind(qxInstr);
      qxResult = new QxXML(m.toString());

      Collection c;
      QxInstruction sub=null;
      {} //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");


      while (sub!=qxInstr) {
        {} //System.out.println("Instruction name = " + qxInstr.getName());
        PrintUtil.print(qxInstr.getProperties());
        c = qxInstr.getSubInstructions();
        PrintUtil.print(c);
        if (null!=c && !c.isEmpty()) sub = (QxInstruction)c.iterator().next();
      }
      */
      /*
      if ("find".equalsIgnoreCase(method)) {
        String origin = qxInstr.get("origin");
    */
    }
    catch (Exception e) {
      try {
        File except = new File("exception.err");
        {} //System.out.println("########################################"+except.getAbsolutePath() + "#################################################");
        OutputStream errStream = new FileOutputStream(except);
        PrintStream err = new PrintStream(errStream);
        e.printStackTrace(err);
      }
      catch(Exception x) {
        e.printStackTrace();
        x.printStackTrace();
      }

      e.printStackTrace();
      {}//Logwriter.printOnConsole("Exception ");
      qxResult = new QxXML("<exception type='"+e.getClass().getName()+"' message='"+e.getMessage()+"'/>");
      return qxResult;
    }
  }

  public Metadata simulateFind(QxInstruction qxInstr) throws Exception {
    Metadata m=null;
    QxInstruction i=null;
    String originValue=null;
    /*
    Collection c = qxInstr.getSubInstructions();
    if (null!=c && !c.isEmpty()) i = (QxInstruction)c.iterator().next();
    c = qxInstr.getSubInstructions();
    if (null!=c&& !c.isEmpty()) i = (QxInstruction)c.iterator().next();
    if ("find".equalsIgnoreCase(i.getName())) originValue = i.get("origin");
  */
    try {
    TC10RepositoryBase rep = (TC10RepositoryBase) RepositorySvc.find("TC-10");
    QxContext runningCtx = new QxContext();
    TC10RepositoryMetadataSource source = (TC10RepositoryMetadataSource)rep.materializeMetadataSource();
    Authentication id = new Authentication("infodba", "infodba");
    //TC10ItemRevOrigin tcOrig = (TC10ItemRevOrigin)OriginMaker.materialize(originValue);
    String uniqueSequence = "RpHZ2FmLxKbabC|000001|A";
    TC10ItemRevOrigin tcOrig = new TC10ItemRevOrigin(
        rep.getDomainName(),
        rep.getServerName(),
        rep.getRepositoryName(),
        uniqueSequence,
        0,
        null);

     source.login(id);
     m = source.find(runningCtx, tcOrig, id);
     //source.logout(id);
    }
    catch(Exception e) { e.printStackTrace(); }
    return m;
  }

}
