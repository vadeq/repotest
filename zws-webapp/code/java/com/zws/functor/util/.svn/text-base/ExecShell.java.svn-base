package com.zws.functor.util;

import com.zws.application.Constants;
import com.zws.functor.Functor;
import com.zws.util.stream.StreamRedirect;

import java.io.File;
import java.util.*;

public class ExecShell extends Functor {
  public ExecShell(){}

  public void execute() throws Exception {
    String exec = getSignature()[0];
    exec = exec.substring(exec.lastIndexOf(Constants.FILE_SEPARATOR));
    dump(getSignature());
    process = Runtime.getRuntime().exec(getSignature(), null, new File(getWorkingDirectory()));
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
    if (null!= timeoutFunctor) timeoutFunctor.execute();
    return Constants.PROCESS_TIMED_OUT;
  }

  public int waitFor() throws Exception {
    String exec = getSignature()[0];
    exec = exec.substring(exec.lastIndexOf(Constants.FILE_SEPARATOR));
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
  public String getWorkingDirectory() { return workingDirectory.getAbsolutePath(); }
  public void setWorkingDirectory(String path) { workingDirectory = new File(path); }
  public StreamRedirect getStandardOut() { return standardOut; }
  public void setStandardOut(StreamRedirect s) { standardOut=s; }
  public StreamRedirect getStandardError() { return standardError; }
  public void setStandardError(StreamRedirect s) { standardError=s; }
  public boolean getQuoteArguments() { return quoteArguments; }
  public void setQuoteArguments(boolean quoteArgs) { quoteArguments=quoteArgs; }
  private void dump(String[] args){   }
  
 
  public long getSleepPeriod() { return sleepPeriod; }
  public void setSleepPeriod(long l) { sleepPeriod=l; }
  public Functor getTimeoutFunctor() { return timeoutFunctor; }
  public void setTimeoutFunctor(Functor f) { timeoutFunctor = f; }

  private Process process=null;
  private long sleepPeriod = 200; //ms
  private Functor timeoutFunctor = null;
  private String executable=null;
  public File workingDirectory = null;
  private Collection arguments = new Vector();
  private boolean quoteArguments = true;
  StreamRedirect standardError = null;
  StreamRedirect standardOut = null;

  private static String quote = Constants.QUOTE;
}
