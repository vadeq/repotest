/*
 * Created on Aug 28, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.jni.toolkit;


import com.zws.application.Properties;
/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ToolkitLoader {

	/*static{
		
	}*/

	public ToolkitLoader(){
		//{} //System.out.println("java path " + java.library.path);
		String libPath = Properties.get(Properties.docBase) + "\\executables\\custom\\";		{} //System.out.println(libPath);
		System.loadLibrary("interlinkkit.dll");
		
		load("modules.xml");
	}
	
	public String executeCommand(String module, String command){
		
		String execResult = "";
		try{
	
			command = "<search criteria=\"Name=*.prt\" select=\"Name, Folder\"	sort-by=\"Name\"	skip=\"0\"	max=\"5\" user=\"INTRALINK\" password=\"INTRALINK\"/>";

			execResult = execute(module, command);
			{} //System.out.println(execResult);
		
			//execResult = kit.execute("search", command);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return execResult;
	}
	
	public native String load(String s);
	public native String execute(String module, String command);
}

	
	
	
	
	
	
