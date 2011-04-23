/*
 * Created on Aug 28, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor.pdfstamp;

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

public class PdfStamp extends Functor {
    
	public void execute() throws Exception{
		File f = new File(Properties.get(Properties.pdfStampToolkit));
		if (!f.exists()) throw new FileNotFoundException("executable file does not exist: " + f.getAbsolutePath());
			ExecShell shell=new ExecShell();
			shell.setExecutable(f.getAbsolutePath());
			shell.setWorkingDirectory(f.getParent());
			//shell.addCommandLineArgument(getX());
			//shell.addCommandLineArgument(getY());
			shell.addCommandLineArgument(getInputFile());
			shell.addCommandLineArgument(getOutputFile());
			shell.addCommandLineArgument(getX());
			shell.addCommandLineArgument(getY());
			shell.addCommandLineArgument(getStampText());
			shell.addCommandLineArgument(getFontSize());
			shell.addCommandLineArgument(getStampColor());
			shell.execute();
			setExitCode(shell.waitFor());
	}
	
	/*
	REM %1 - input file
	REM %2 - output file
	REM %3 - x
	REM %4 - y
	REM %5 - stamp Text
	REM %6 - font size
	REM %7 - color
	
	 */
	 
	public String getFontSize() { return fontSize; }
	public void setFontSize(String s) { fontSize=s; } 
	public String getInputFile() { return inputFile; }
	public void setInputFile(String s) { inputFile = s; }
	public String getOutputFile() { return outputFile; }
	public void setOutputFile(String s) { outputFile = s; }
	public String getStampText() { return stampText; }
	public void setStamptext(String s) { stampText = s; }
	public String getStampColor() { return stampColor; }
	public void setStampColor(String s) { stampColor = s; }
	public String getX() { return x; }
	public void setX(String s) { x = s; }
	public String getY() { return y; }
	public void setY(String s) { y = s; }
	
	public int getExitCode() { return exitCode; }
	private void setExitCode(int code) { exitCode = code; }

	private String fontSize=null;
	private String inputFile=null;
	private String outputFile=null;
	private String stampText=null;
	private String stampColor=null;
	private String x=null;
	private String y=null;
  
	private int exitCode=0;
}


