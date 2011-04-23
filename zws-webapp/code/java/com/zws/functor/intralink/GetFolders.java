/*
 * Created on Sep 3, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor.intralink;


import com.zws.application.Properties;
import com.zws.domo.baseline.Folder;
import com.zws.functor.ListFunctor;
import com.zws.functor.util.ExecShell;
import com.zws.util.GenericParser;

import java.io.File;
import java.io.FileNotFoundException;
//import java.util.Collection;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetFolders extends ListFunctor {
    
public void execute() throws Exception{
	File f = new File(Properties.get(Properties.iLinkGetFolders));
	if (!f.exists()) throw new FileNotFoundException("executable file does not exist: " + f.getAbsolutePath());
	ExecShell shell=new ExecShell();
	shell.setExecutable(f.getAbsolutePath());
	shell.setWorkingDirectory(f.getParent());
	
	shell.addCommandLineArgument(proiTkEnv);
	shell.addCommandLineArgument(getUsername());
	shell.addCommandLineArgument(getPassword());
	shell.addCommandLineArgument(getOutputFile());
	//shell.addCommandLineArgument(getStartFolder());
	
	shell.execute();
	setExitCode(shell.waitFor());
	
	folderTree = parseOutput(getOutputFile());
}

/*
 *REM %1 PROITKENV
REM %2 USERNAME
REM %3 PASSWORD
REM %4 OUTFILE
REM %5 FOLDERNAME
 */
  /*private Collection parseOutput(String outFile){
	ArrayList locs = new ArrayList();
	
	try{
	
	File f = new File(getOutputDir() + "\\" + getOutputFile());
	
	BufferedReader br = new BufferedReader(new FileReader(f)); 
	
	String folders = br.readLine();
	StringTokenizer st = new StringTokenizer(folders, ",", false);
	while (st.hasMoreTokens()){
			locs.add(st.nextToken().trim());
	}
	f.delete();
	
  }catch(Exception ex){
  	ex.printStackTrace();	
  }
	return locs;
  	
  }*/
  private Folder parseOutput(String outFile){
  	Folder result = null;
	try{
	
		File f = new File(getOutputDir() + "\\" + getOutputFile());
  		GenericParser parser = new GenericParser(GetFoldersContentHandler.class);
		parser.parse(f.getPath());
		result = ((GetFoldersContentHandler)parser.getHandler()).getFolder();
		//f.delete();
	
  	}catch(Exception ex){
		ex.printStackTrace();	
  	}
  	return result;
  	 
  }
 
  //public String getStartFolder() { return startFolder; }
  //public void setStartFolder(String s) { startFolder = s; }
  private String getOutputFile() { 
  	  	return "folds.xml"; 
  }
  private String getOutputDir() { 
  	
	String outFilename = Properties.get(Properties.foldersToolkit);
	return outFilename; 
  }
  
  public Folder getFolderTree() { return folderTree; }
  public String getUsername() { return username; }
  public void setUsername(String s) {  username= s; }
  public String getPassword() { return password; }
  public void setPassword(String s) {  password = s; }
  
  public String getEXEToolkitEnv() { return proiTkEnv; }
  public void setEXEToolkitEnv(String  s) { proiTkEnv = s; }
  public int getExitCode() { return exitCode; }
  private void setExitCode(int code) { exitCode = code; }

  //private String startFolder=null;
  //private String outputFile="";
  private Folder folderTree = null;
  private String username=null;
  private String password=null;
  
  private String proiTkEnv=null;
  private int exitCode=0;
}

