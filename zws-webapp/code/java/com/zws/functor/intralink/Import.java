/*
 * Created on Sep 26, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor.intralink;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import com.zws.application.Properties;
import com.zws.functor.Functor;
import com.zws.functor.util.ExecShell;

import java.io.File;
import java.io.FileNotFoundException;

public class Import  extends Functor {
    
	public void execute() throws Exception{
		File f = new File(Properties.get(Properties.importToolkit));
		if (!f.exists()) throw new FileNotFoundException("executable file does not exist: " + f.getAbsolutePath());
			ExecShell shell=new ExecShell();
			shell.setExecutable(f.getAbsolutePath());
			shell.setWorkingDirectory(f.getParent());
			
			shell.addCommandLineArgument(getFilename());
			shell.addCommandLineArgument(getWorkspace());
			shell.addCommandLineArgument(getUsername());
			shell.addCommandLineArgument(getPassword());
			shell.addCommandLineArgument(proiTkEnv);
			shell.execute();
			setExitCode(shell.waitFor());
	}
	
	/*
	REM %1 -  -o filename
	REM %2 -  -w workspace
	REM %3 -  -u username
	REM %4 -  -p password
 	REM %5 - proiTkEnv
	
	 */
	 
	public String getUsername() { return username; }
	public void setUsername(String s) { username = s; }
	public String getPassword() { return password; }
	public void setPassword(String s) { password = s; } 
	public String getFilename() { return filename; }
	public void setFilename(String s) { filename=s; } 
	public String getWorkspace() { return workspace; }
	public void setWorkspace(String s) { workspace = s; }
	public String getEXEToolkitEnv() { return proiTkEnv; }
	public void setEXEToolkitEnv(String  s) { proiTkEnv = s; }
	
	public int getExitCode() { return exitCode; }
	private void setExitCode(int code) { exitCode = code; }

	private String filename=null;
	private String workspace=null;
	private String username=null;
	private String password=null;

  
	private String proiTkEnv=null;
	private int exitCode=0;
}



