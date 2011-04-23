package zws.qx.service;

import zws.application.Names;
import zws.application.Properties;
import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.QxTransferBase;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.service.file.depot.FileDepotClient;
import zws.util.Counter;
import zws.util.RemotePackage;
import zws.util.ExecShell;
import zws.util.FileUtil;
import zws.exception.NotAFile;

import java.io.File;
import java.util.Collection;

public class QxInstructionFileClient  extends QxTransferBase implements Qx {
  private static QxInstructionFileClient qxClient = new QxInstructionFileClient();
  private QxInstructionFileClient() {}

  public static QxInstructionFileClient materializeClient() { return qxClient; }

  public static void main(String[] args) {

	  Properties.set(Names.WORKING_DIR, Names.PATH_SEPARATOR + "temp"+Names.PATH_SEPARATOR+ "tc10");
    QxInstructionFileClient client = QxInstructionFileClient.materializeClient();

    QxContext ctx = new QxContext();
    ctx.set(ctx.USERNAME, "infodba");
    ctx.set(ctx.PASSWORD, "infodbapwd");
    ctx.set(ctx.DOMAIN_NAME, "zws");
    ctx.set(ctx.SERVER_NAME, "node-0");
    ctx.set(ctx.REPOSITORY_NAME, "TC-QA-server");
    ctx.set(ctx.HOST_NAME, "zws-tc-svr");
    ctx.set(ctx.PORT, "8090");
    ctx.set(ctx.SERVICES_PATH, "tc/services/PLMGatewayService");

    QxXML ins = new QxXML("<tc10-qx>");
    ins.write("<open-repository name="+ctx.get(ctx.REPOSITORY_NAME)+">");
    ins.write("<find origin=\".....\"/>");
    ins.write("<open-repository/>");
    ins.write("<tc10-qx>");

    QxXML r = client.executeQx(ctx, ins);
    {} //System.out.println(r);
  }

  public QxXML executeQx(QxContext ctx, QxXML dataInstruction) {
    QxXML result = null;
    boolean isFetchInstr = false;
    File workDir = createWorkingDirectory();
    File contextFile = new File(workDir, "context.xml");
    File instructionFile = new File(workDir, "instruction.xml");
    File resultFile = new File(workDir, "result.xml");
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.service");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "TC10QxService");

    try {
      FileUtil.save(ctx.toString(), contextFile, true);
      FileUtil.save(dataInstruction.toString(), instructionFile, true);
      ExecShell shell = new ExecShell();
      String tc10QxServiceBatPath = Properties.get("tc10-qx-service-exe");
      File tc10QxServiceBat = new File(tc10QxServiceBatPath);
      if (!tc10QxServiceBat.exists()) throw new NotAFile(tc10QxServiceBat);
      //shell.setExecutable("C:\\zws\\env\\ugs\\tc-10\\bin\\tc_client.bat");
      File tc10EnvDir = tc10QxServiceBat.getParentFile().getParentFile();
      shell.setExecutable(tc10QxServiceBatPath);
      shell.setWorkingDirectory(workDir);
      shell.addCommandLineArgument(tc10EnvDir.getAbsolutePath());
      shell.addCommandLineArgument(workDir.getAbsolutePath());
      shell.execute();
      shell.waitFor();
      String s = zws.util.FileUtil.read(resultFile);
      isFetchInstr = checkForFetchInstr(dataInstruction);
      // if instruction is fetch store the files in the FileDepot
      if(isFetchInstr) result = storeFile(s);
      else result = new QxXML(s);
    }
    catch(Exception e) {
      result = new QxXML("<exception type='"+e.getClass().getName()+"' msg='"+e.getMessage()+"'/>");
    }

    {} //System.out.println(">>>>>>QxXML executeQx<<<<<<<<");
    {} //System.out.println(result.toString());
    {} //System.out.println(">>>>>>QxXML executeQx<<<<<<<<");

    return result;
  }

  private boolean checkForFetchInstr(QxXML dataInstruction) {
    boolean b = false;
    QxInstruction qxInstr = dataInstruction.toQxProgram();
    Collection c = qxInstr.getSubInstructions();
    // navigate to fetch instruction
    while(null != c && !c.isEmpty()) {
      qxInstr = (QxInstruction)c.iterator().next();
      c = qxInstr.getSubInstructions();
    }
    if(qxInstr.getName().toLowerCase().startsWith("fetch-")) b= true;
    return b;
  }
  private QxXML storeFile(String result) throws Exception {
    StringBuffer resultBfr = new StringBuffer("<results>");
    QxXML resultXml = new QxXML(result);
    QxInstruction qxInstr = resultXml.toQxProgram();
    Collection c = qxInstr.getSubInstructions();
    // store the file in to filedepot
    if(null!=c && !c.isEmpty()){
      qxInstr = (QxInstruction)c.iterator().next();
      String filePath = qxInstr.get("path");
      File file = new File(filePath);
      if(file.exists()) {
        String tempDir = Properties.get(Names.TEMP_DIR) + Names.PATH_SEPARATOR + "TC-10-service" + Counter.nextCount();
        String fileDepotTempDir= tempDir + Names.PATH_SEPARATOR + "file-depot";
        FileDepotClient fileDepotClient = FileDepotClient.materializeClient(fileDepotTempDir);
        RemotePackage r = fileDepotClient.storeDirectory(file.getParentFile(), file.getName());
        resultBfr.append(r.toString());
      }
    }

    resultBfr.append("</results>");
    return new QxXML(resultBfr.toString());
  }
  private File createWorkingDirectory() {
    String path = Properties.get(Names.WORKING_DIR);
    if (null==path) path = Names.PATH_SEPARATOR + "zws" + Names.PATH_SEPARATOR + "log" + Names.PATH_SEPARATOR + "work";
    path =path + Names.PATH_SEPARATOR + "TC10" + Names.PATH_SEPARATOR +"work-"+nextCount();
    File wd = new File(path);

    try { if (wd.exists()) FileUtil.deleteContents(wd); }
    catch(Exception e) { {} //System.out.println("Warning! Could not delete contents of working directory: " + wd.getAbsolutePath()); 
    }

    wd.mkdirs();
    return wd;
  }
  private static long nextCount() { return count++; }
  private static long count=0;

}
