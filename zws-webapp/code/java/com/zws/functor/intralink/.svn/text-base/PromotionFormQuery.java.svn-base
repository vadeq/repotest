/*
 * Created on Oct 10, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor.intralink;

import com.zws.application.Properties;
import com.zws.functor.Functor;
import com.zws.functor.util.ExecShell;
import com.zws.functor.util.file.UTF8Tidy;
import com.zws.util.GenericParser;
import com.zws.xml.PromotionResultHandler;

import java.io.*;
import java.util.Collection;
/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PromotionFormQuery extends Functor {
    
	public void execute() throws Exception{
		File f = new File(Properties.get(Properties.promotionFormToolkit));
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
			
			//clean up illegal characters from intralink output
			UTF8Tidy tidy = new UTF8Tidy();
			tidy.setFilename(outFile);
			tidy.execute();

			GenericParser parser = new GenericParser(PromotionResultHandler.class);
			parser.parse(outFile);
			
			//result = ((GetFoldersContentHandler)parser.getHandler()).getFolder();
 
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
		  outFile.write("<query ");
		  outFile.write("date=\"" + getDate() + "\" ");
		  outFile.write("Release-Level=\"" + getReleaseLevel() + "\" ");
		  outFile.write("user=\"" + username +"\" ");
		  outFile.write("password=\"" +  password +"\" ");
		  outFile.write("/>");
		  //outFile.write(Names.NEW_LINE);
		  outFile.close();
		}
		catch (Exception e) { e.printStackTrace(); }
	} 
	 
	
	public String getDate() { return date; }
	public void setDate(String s) { date = s; }
	public String getReleaseLevel() { return releaseLevel; }
	public void setReleaseLevel(String s) { releaseLevel = s; }
	
	public String getUsername() { return username; }
	public void setUsername(String s) { username = s; }
	public String getPassword() { return password; }
	public void setPassword(String s) { password = s; }
	
	public String getEXEToolkitEnv() { return proiTkEnv; }
	public void setEXEToolkitEnv(String  s) { proiTkEnv = s; }
	
	public int getExitCode() { return exitCode; }
	private void setExitCode(int code) { exitCode = code; }

	private Collection attributes = null;
	
	private String name=null;
	private String date=null;
	private String releaseLevel=null;
  
	private String username=null;
	private String password=null;
	private String proiTkEnv=null;
	private int exitCode=0;
}


	
	
	



