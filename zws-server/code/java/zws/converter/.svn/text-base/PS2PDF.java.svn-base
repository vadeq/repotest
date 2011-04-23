package zws.converter;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 20, 2004, 9:56 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.util.ExecShell;

import java.io.File;

public class PS2PDF extends ConverterBase {
  protected String getEXEName() { return "convertPS2PDF.bat"; }
  protected void finishExecution() throws Exception {  if (getRemoveInput()) (new File(getInputFilename())).delete();  }
  protected void setArguments(ExecShell shell) throws Exception {
    File exe= new File(Properties.get(Names.GS_PS2PDF_EXE));
    if (!exe.exists()) { throw new Exception("Path to executable not found: " + Names.GS_PS2PDF_EXE); }
		shell.addCommandLineArgument(exe.getAbsolutePath());
		shell.addCommandLineArgument(getInputFilename());
		shell.addCommandLineArgument(getOutputFilename());
		if (getInColor()) shell.addCommandLineArgument(Properties.get(Properties.colorPDF));
		else shell.addCommandLineArgument(Properties.get(Properties.blackWhitePDF));
	}

	public String getInputFilename() { return inputFilename; }
	public void setInputFilename(String s) { inputFilename = s; }
	public String getOutputFilename() { return outputFilename; }
	public void setOutputFilename(String s) { outputFilename=s; }
	public boolean getInColor() { return inColor; }
	public void setInColor(boolean b) { inColor=b; }
  public boolean getRemoveInput() { return removeInput; }
  public void setRemoveInput(boolean b) { removeInput=b; }

	private String inputFilename=null;
	private String outputFilename=null;
	private boolean inColor=false;
  private boolean removeInput = true;
}
  