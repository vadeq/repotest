/*
 * Created on Sep 26, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor.intralink;

/*
 * Created on Sep 26, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import com.zws.application.Properties;
import com.zws.functor.Functor;
import com.zws.functor.util.ExecShell;

import java.io.*;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Link extends Functor {
    
	public void execute() throws Exception{
		File f = new File(Properties.get(Properties.linkToolkit));
		if (!f.exists()) throw new FileNotFoundException("executable file does not exist: " + f.getAbsolutePath());
			ExecShell shell=new ExecShell();
		    shell.setExecutable(f.getAbsolutePath());
		    shell.setWorkingDirectory(f.getParent());
			
		    String instrFile = shell.getWorkingDirectory() + File.separator + "in.xml";
		    String outFile = shell.getWorkingDirectory() + File.separator + "out.xml";
		    createInstructionFile(instrFile);
		    shell.addCommandLineArgument(instrFile);
			
		    shell.addCommandLineArgument(outFile);
			
		    shell.addCommandLineArgument(proiTkEnv);
		    shell.execute();
		    setExitCode(shell.waitFor());
	}
	
	/*
	REM %1 input instruction file
	REM %2 output result file
	REM %3 toolkit env.bat filename
	
	 */
	 
	protected void createInstructionFile(String filename) {
		File instructionFile = new File(filename);
		instructionFile.delete();
		String username = getUsername();
		String password = getPassword();
		
		try{
		  instructionFile.createNewFile();
		  FileWriter outFile = new FileWriter(instructionFile);
		  outFile.write("<link ");
		  outFile.write("workspace=\"" + getWorkspaceName() + "\" ");
		  outFile.write("parent=\"" + getComponent()+"\" ");
		  
		  //extractAttributeValues(getAttributes());
		  outFile.write("children=\"" + getDependant() +"\" ");
		  
		  outFile.write("user=\"" + username +"\" ");
		  outFile.write("password=\"" +  password +"\" ");
		  outFile.write("/>");
		  //outFile.write(Names.NEW_LINE);
		  outFile.close();
		}
		catch (Exception e) { e.printStackTrace(); }
	} 
	 
	public String getUsername() { return username; }
	public void setUsername(String s) { username = s; }
	public String getPassword() { return password; }
	public void setPassword(String s) { password = s; } 
	public String getComponent() { return component; }
	public void setComponent(String s) { component=s; } 
	public String getDependant() { return dependant; }
	public void setDependant(String s) { dependant = s; }
	public String getWorkspaceName() { return workspaceName; }
	public void setWorkspaceName(String s) { workspaceName = s; }
	
	public String getEXEToolkitEnv() { return proiTkEnv; }
    public void setEXEToolkitEnv(String  s) { proiTkEnv = s; }
	
	public int getExitCode() { return exitCode; }
	private void setExitCode(int code) { exitCode = code; }

	private String component=null;
	private String dependant=null;
	private String workspaceName=null;
	private String username=null;
	private String password=null;
  
	private String proiTkEnv=null;
	private int exitCode=0;
}



