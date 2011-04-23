package zws.converter;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 20, 2004, 9:03 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.util.ExecShell;

import java.io.File;

public class StampPDF extends ConverterBase {
  protected String getOpType() { return "pdf-stamp"; }
  protected String getEXEName() { return "stamp.bat"; }
  protected String getBinPath() { return Properties.get(Names.APP_DIR) + Names.PATH_SEPARATOR +"pdf-everywhere"; }

  protected void finishExecution() throws Exception {  
    File out = new File(sourceFilePath);
    if (!out.exists()) throw new Exception ("Failed To Stamp: " + out.getAbsolutePath());
  }
  
  protected void setArguments(ExecShell shell) {
    initConfiguration();
    shell.addCommandLineArgument(sourceFilePath);
		shell.addCommandLineArgument(getText());
		shell.addCommandLineArgument(""+getFontSize(), false);
		shell.addCommandLineArgument(getColor(), true);
		shell.addCommandLineArgument(""+getXPosition(), false);
		shell.addCommandLineArgument(""+getYPosition(), false);
		shell.addCommandLineArgument(""+getAngle(), false);
		shell.addCommandLineArgument(""+getOpacity(), false);
  }

	public String getSourceFilePath() { return sourceFilePath; }
	public void setSourceFilePath(String s) { sourceFilePath = s; }
	public String getText() { return text; }
	public void setText(String s) { text = s; }
	public String getColor() { return color; }
	public void setColor(String s) { color = s; }
	public int getFontSize() { return fontSize; }
	public void setFontSize(int i) { fontSize = i; } 
	public int getXPosition() { return xPosition; }
	public void setXPosition(int i) { xPosition = i; }
	public int getYPosition() { return yPosition; }
	public void setYPosition(int i) { yPosition = i; }
	public int getAngle() { return angle; }
	public void setAngle(int i) { angle = i; }
	public int getOpacity() { return opacity; }
	public void setOpacity(int i) { opacity = i; }

  private void initConfiguration() {
    if (null==color) color = Properties.get(Names.PDFSTAMP_FONT_COLOR);
    if (-8==fontSize) fontSize = Integer.valueOf(Properties.get(Names.PDFSTAMP_FONT_SIZE)).intValue();
    if (-8==xPosition) xPosition = Integer.valueOf(Properties.get(Names.PDFSTAMP_X_POSITION)).intValue();
    if (-8==yPosition) yPosition = Integer.valueOf(Properties.get(Names.PDFSTAMP_Y_POSITION)).intValue();
    if (-8==angle) angle = Integer.valueOf(Properties.get(Names.PDFSTAMP_ANGLE)).intValue();
    if (-8==opacity) opacity= Integer.valueOf(Properties.get(Names.PDFSTAMP_OPACITY)).intValue();
  }

	private String sourceFilePath=null;
	private String text=null;
	private String color=null;
	private int fontSize=-8;
	private int xPosition=-8;
	private int yPosition=-8;
	private int angle=-8;
	private int opacity=-8;
}
