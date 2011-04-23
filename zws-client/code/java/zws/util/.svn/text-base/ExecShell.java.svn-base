package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.op.Op;
import zws.op.OpBase;
import zws.util.stream.StreamRedirect;

import java.io.File;
import java.util.*;

public class ExecShell extends OpBase {
  public ExecShell(){}

  public void execute() throws Exception {
    String exec = getSignature()[0];
    if (-1<exec.lastIndexOf(Names.PATH_SEPARATOR))exec = exec.substring(exec.lastIndexOf(Names.PATH_SEPARATOR));
    dump(getSignature());
    process = Runtime.getRuntime().exec(getSignature(), null, getWorkingDirectory());
    if (null==getStandardOut()) setStandardOut(new StreamRedirect(process.getInputStream()));
    if (null==getStandardError()) setStandardError(new StreamRedirect(process.getErrorStream()));
    getStandardError().start();
    getStandardOut().start();
  }

  public Object getResult() { return new Integer(getExitCode()); }
  public int getExitCode() { return process.exitValue(); }
  public void destroy() { process.destroy();}

  public synchronized int timeout(long duration) throws Exception {
    int idx=-1000;
    long waited=0;
    while (waited<duration){
      try{
        idx = process.exitValue();
        process.destroy();
        return idx;
      }
      catch (IllegalThreadStateException  e){
        try { wait(getSleepPeriod()); waited+=getSleepPeriod(); }
        catch (InterruptedException ex) { waited=duration; }
        catch (Exception r) { r.printStackTrace(); }
      }
    }
    //process not finished. time it out
    process.destroy();
    if (null!= timeoutOp) timeoutOp.execute();
    return Names.keyPROCESS_TIMED_OUT;
  }

  public int waitFor() throws Exception {
    String exec = getSignature()[0];
    if (-1<exec.lastIndexOf(Names.PATH_SEPARATOR))exec = exec.substring(exec.lastIndexOf(Names.PATH_SEPARATOR));
    int idx=-1000;
    idx = process.waitFor();
    process.destroy();
    return idx;
  }

  public void addCommandLineArgument(String s) { //quote command-line arguments
    if (null==s) arguments.add(quote+quote);
    else if (0==s.length()) arguments.add(quote+quote);
    else if (s.startsWith(quote) && s.endsWith(quote)) arguments.add(s);
    else if (getQuoteArguments()) arguments.add(quote+s+quote);
    else arguments.add(s);
  }

  public void addCommandLineArgument(String s, boolean quoteArg) { //quote command-line arguments
    if (null==s || 0==s.length()) arguments.add(quote+quote);
    else if (s.startsWith(quote) && s.endsWith(quote)) arguments.add(s);
    else if (quoteArg) arguments.add(quote+s+quote);
    else arguments.add(s);
  }

  private String[] getSignature(){
    String[] signature = new String[getCommandLineArguments().size() +1];
    signature[0] = getExecutable();
    int idx = 1;
    Iterator i = getCommandLineArguments().iterator();
    while (i.hasNext()) signature[idx++] = i.next().toString();
    return signature;
  }

  public Collection getCommandLineArguments() { return arguments; }
  public String getExecutable() { return executable; }
  public void setExecutable(String s) { executable = s; }
  public File getWorkingDirectory() { return workingDirectory; }
  public void setWorkingDirectory(File dir) { workingDirectory = dir; }
  public StreamRedirect getStandardOut() { return standardOut; }
  public void setStandardOut(StreamRedirect s) { standardOut=s; }
  public StreamRedirect getStandardError() { return standardError; }
  public void setStandardError(StreamRedirect s) { standardError=s; }
  public boolean getQuoteArguments() { return quoteArguments; }
  public void setQuoteArguments(boolean quoteArgs) { quoteArguments=quoteArgs; }
  private void dump(String[] args){ 
    String s="shell: ";
    for (int idx = 0; idx < args.length; s += args[idx++]+" "); 
    {} //System.out.println(s);
  }

  public long getSleepPeriod() { return sleepPeriod; }
  public void setSleepPeriod(long l) { sleepPeriod=l; }
  public Op getTimeoutFunctor() { return timeoutOp; }
  public void setTimeoutOp(Op f) { timeoutOp = f; }

  private Process process=null;
  private long sleepPeriod = 200; //ms
  private Op timeoutOp = null;
  private String executable=null;
  public File workingDirectory = null;
  private Collection arguments = new Vector();
  private boolean quoteArguments = true;
  StreamRedirect standardError = null;
  StreamRedirect standardOut = null;

  private static String quote = Names.QUOTE;
}
