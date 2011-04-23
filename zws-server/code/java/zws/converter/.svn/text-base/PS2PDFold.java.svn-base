package zws.converter;

import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.op.OpBase;
import zws.util.ExecShell;

import java.io.File;
import java.io.FileNotFoundException;

public class PS2PDFold extends OpBase{ //+++ todo: make this threadsafe! (own working directory)
	public void execute() throws Exception {
		File exePdf = new File(Properties.get(Names.GS_PS2PDF_EXE));
		if (!exePdf.exists()) throw new FileNotFoundException ("executable file does not exist: " + exePdf.getAbsolutePath());
    
		File f = new File(getInputFileName());
		if (!f.exists()) throw new FileNotFoundException ("Input file does not exist: " + f.getPath());

    File exe = new File(Properties.get(Names.GS_PS2PDF_EXE));
		if (!exe.exists()) throw new FileNotFoundException ("PS2PDF executable file does not exist: " + exe.getAbsolutePath());

		ExecShell shell=new ExecShell();
		shell.setExecutable(exe.getAbsolutePath());
		shell.setWorkingDirectory(exe.getParentFile());
		shell.addCommandLineArgument(exePdf.getAbsolutePath());
		shell.addCommandLineArgument(getInputFileName());
		shell.addCommandLineArgument(getOutputFileName());
		if (getInColor())
		  shell.addCommandLineArgument(Properties.get(Properties.colorPDF));
		else
		  shell.addCommandLineArgument(Properties.get(Properties.blackWhitePDF));
		shell.execute();
		setExitCode(shell.waitFor());
    if (deleteInput) f.delete();

    //make output name dynamic once toolkit code is updated to dynamically accept output file
		//outputFileName = ""+Names.PATH_SEPARATOR+FileNameUtil.getBaseName(getComponentName());
		//String outputType = Properties.get(Properties.drw2psType);
		//if (null!=outputType && !"".equals(outputType)) outputFileName += "." + outputType;
	}

	public boolean getInColor() { return inColor; }
	public void setInColor(boolean b) { inColor=b; }
	public String getInputFileName() { return inputFileName; }
	public void setInputFileName(String s) { inputFileName = s; }
	public String getOutputFileName() { return outputFileName; }
	public void setOutputFileName(String s) { outputFileName=s; }
  public boolean getDeleteInput() { return deleteInput; }
  public void setDeleteInput(boolean b) { deleteInput=b; }
	public int getExitCode() { return exitCode; }
	private void setExitCode(int code) { exitCode = code; }

	private String inputFileName=null;
	private String outputFileName=null;
	private boolean inColor=false;
	private int exitCode=0;
  private boolean deleteInput = true;
}
  