/*
 * Created on Sep 30, 2003
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
import com.zws.domo.document.Document;
import com.zws.functor.Functor;
import com.zws.functor.util.ExecShell;
import com.zws.util.KeyValue;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AttribSet extends Functor {
    
	public void execute() throws Exception{
		File f = new File(Properties.get(Properties.attrsetToolkit));
		if (!f.exists()) throw new FileNotFoundException("executable file does not exist: " + f.getAbsolutePath());
			ExecShell shell=new ExecShell();
			shell.setExecutable(f.getAbsolutePath());
			shell.setWorkingDirectory(f.getParent());
			
			String instrFile = shell.getWorkingDirectory() + File.separator + "in.xml";
			String outFile = shell.getWorkingDirectory() + File.separator + "out.xml";
			createInstructionFile(instrFile);
			/*shell.addCommandLineArgument(instrFile);
			
			shell.addCommandLineArgument(outFile);
			shell.addCommandLineArgument(proiTkEnv);
			shell.execute();
			setExitCode(shell.waitFor()); */
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
		  outFile.write("<set-attribute ");
		  outFile.write("workspace=\"" + getWorkspace() + "\" ");
		  if (null!=getName()) outFile.write("name=\"" + getName()+"\" ");
		  
		  extractAttributeValues(target.getAttributes());
		  outFile.write("attributeValues=\"" + getAttributeValues() +"\" ");
		  
		  outFile.write("user=\"" + username +"\" ");
		  outFile.write("password=\"" +  password +"\" ");
		  outFile.write("/>");
		  //outFile.write(Names.NEW_LINE);
		  outFile.close();
		}
		catch (Exception e) { e.printStackTrace(); }
	} 
	 
	private void extractAttributeValues(Collection attr){
		//String folderAttributeName = Properties.get(Properties.folderAttributeName);
		
		setAttributeValues("");
		if(attr != null){
			String pair = null;
			Iterator attrI = attr.iterator();
			while(attrI.hasNext()){
				KeyValue entry = (KeyValue)attrI.next();
				if(getAttributeValues().length() > 0)
					attributeValues += getDelimiter();
			    
				pair = entry.getKey() + "=" + (String)entry.getValue();
				attributeValues += pair;
				
			}
			
		}
	}
	 
	//public Collection getAttributes() {return attributes; }
	/*@ expects a collection of KeyValue attributes
	 * 
	*/
	public void setTarget(Document t){ target = t; }
	
	//public void setAttributes(Collection s) {attributes = s; } 
	
	public String getName() { return name; }
	public void setName(String s) { name=s; } 
	public String getAttributeValues() { return attributeValues; }
	public void setAttributeValues(String s) { attributeValues = s; }
	public String getWorkspace() { return workspace; }
	public void setWorkspace(String s) { workspace = s; }
	public String getDelimiter() { return delimiter; }
	public void setDelimiter(String s) { delimiter = s; }
	
	public String getUsername() { return username; }
	public void setUsername(String s) { username = s; }
	public String getPassword() { return password; }
	public void setPassword(String s) { password = s; }
	
	public String getEXEToolkitEnv() { return proiTkEnv; }
	public void setEXEToolkitEnv(String  s) { proiTkEnv = s; }
	
	public int getExitCode() { return exitCode; }
	private void setExitCode(int code) { exitCode = code; }

	//private Collection attributes = null;
	private Document target = null; 
	
	private String name=null;
	private String attributeValues=null;
	private String workspace=null;
	private String delimiter="|";
  
    private String folder=null;
    private String username=null;
    private String password=null;
	private String proiTkEnv=null;
	private int exitCode=0;
}


	
	
	


