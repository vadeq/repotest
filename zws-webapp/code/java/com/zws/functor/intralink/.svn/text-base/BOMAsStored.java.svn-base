package com.zws.functor.intralink;

import com.zws.application.Constants;
import com.zws.application.Properties;
import com.zws.domo.document.DocumentInterface;
import com.zws.domo.document.OriginMaker;
import com.zws.functor.Functor;
import com.zws.functor.util.ExecShell;
import com.zws.functor.util.file.UTF8Tidy;
import com.zws.util.AutoIncrement;
import com.zws.xml.BOMHandler;

import java.io.*;

import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.*;

public class BOMAsStored extends Functor  {
  public void execute() throws Exception {
    File f = new File(Properties.get(Properties.iLinkGetBOM));
    if (!f.exists()) throw new FileNotFoundException("executable file does not exist! " + f.getAbsoluteFile());
    String outputFileName = getOutputFileName(f);
    ExecShell shell = new ExecShell();
    shell.setExecutable(f.getAbsolutePath());
    shell.setWorkingDirectory(f.getParent());
    shell.addCommandLineArgument(getAssemblyName());
    shell.addCommandLineArgument(getRevision());
    shell.addCommandLineArgument(getVersion());
    shell.addCommandLineArgument(getUsername());
    shell.addCommandLineArgument(getPassword());
    shell.addCommandLineArgument(outputFileName);
    shell.addCommandLineArgument(getEXEToolkitEnv());
    shell.execute();
    exitCode = shell.waitFor();

    UTF8Tidy tidy = new UTF8Tidy();
    tidy.setFilename(outputFileName);
    tidy.execute();

    XMLReader xr = new SAXParser();
    BOMHandler handler = new BOMHandler();
    handler.setOriginMaker(getOriginMaker());
    xr.setContentHandler(handler);
    xr.setErrorHandler(handler);
    File xml = new File(outputFileName);
    FileReader r = new FileReader(xml);
    xr.parse(new InputSource(r));
    setResult(handler.getDocument());
    if (getDeleteOutputFile()) xml.delete();
  }

  private String getOutputFileName(File f) { return Properties.get(Properties.tempDirectory)+Constants.FILE_SEPARATOR+outputName+"."+getMemberID()+"."+AutoIncrement.getNext()+".xml"; }

  public String getMemberID() { return memberID; }
  public void setMemberID(String s) { memberID=s; }
  public void setOutputName(String  s) { outputName = s; }
  public String getAssemblyName() { return assemblyName; }
  public String getRevision() { return revision; }
  public void setAssemblyName(String  s) { assemblyName = s; }
  public void setRevision(String  s) { revision = s; }
  public String getVersion() { return version; }
  public void setVersion(String  s) { version = s; }
  public String getUsername() { return username; }
  public void setUsername(String s) {  username= s; }
  public String getPassword() { return password; }
  public void setPassword(String s) {  password = s; }
  public String getEXEToolkitEnv() { return proiTkEnv; }
  public void setEXEToolkitEnv(String  s) { proiTkEnv = s; }
  public OriginMaker getOriginMaker() { return originMaker; }
  public void setOriginMaker(OriginMaker m) { originMaker=m; }

  public void setContentHandler(ContentHandler h) { contentHandler = h; }
  public void setErrorhandler(ErrorHandler h) { errorHandler = h; }
  public int getExitCode() { return exitCode; }
  public DocumentInterface getDocument() { return document; }
  public void setDocument(DocumentInterface d) { document = d; }
  public Object getResult() { return document; }
  public void setResult(Object o) { document = (DocumentInterface) o; }
  public void setDeletOutputFile(boolean b) { deleteOutputFile=b; }
  public boolean getDeleteOutputFile () { return deleteOutputFile; }

  private String memberID, outputName, assemblyName, revision, version, username, password, proiTkEnv;
  private int exitCode = -999;
  private ContentHandler contentHandler;
  private ErrorHandler errorHandler;
  private DocumentInterface document=null;
  private OriginMaker originMaker = null;
  private boolean deleteOutputFile=true;
}
