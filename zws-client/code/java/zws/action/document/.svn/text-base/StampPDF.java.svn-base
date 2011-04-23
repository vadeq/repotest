package zws.action.document;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 20, 2004, 3:03 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.DocumentConverter;
import zws.action.ActionBase;
import zws.data.Metadata;
import zws.op.Op;
import zws.util.FileNameUtil;

public class StampPDF extends ActionBase{

  public void execute() throws Exception {
    Metadata data = grabMetadata();
    String server = getString("serverName");
    if (null==server) server = data.getOrigin().getServerName();
    String file = getString("filename");
    if (null==file) file = FileNameUtil.convertType(data.getName(), "pdf");
    String location = getRequiredString("location");
    String outputLocation = getString ("outputLocation");
    String outputFile = getString("outputFilename");
    if (null==outputLocation) outputLocation=location;
    if (null==outputFile) outputFile=file;
    int a = Integer.valueOf(getRequiredString("angle")).intValue();
    int o = Integer.valueOf(getRequiredString("opacity")).intValue();
    int x = Integer.valueOf(getRequiredString("xPosition")).intValue();
    int y = Integer.valueOf(getRequiredString("yPosition")).intValue();
    int s = Integer.valueOf(getRequiredString("fontSize")).intValue();
    String c = getRequiredString("color");
    DocumentConverter.stampPDF(server, location, file, getRequiredString("text"), s, c, x, y, a, o);
  }

  public String getServerName() { return serverName; }
  public void setServerName(String s) { serverName=s; }
  public String getMetaServerName() { return metaServerName; }
  public void setMetaServerName(String s) { metaServerName=s; }
  public String getCtxServerName() { return ctxServerName; }
  public void setCtxServerName(String s) { ctxServerName=s; }
  public Op getServerNameOp() { return serverNameOp; }
  public void setServerNameOp(Op op) { serverNameOp=op; }
  public String getCtxDefaultServerName() { return ctxDefaultServerName; }
  public void setCtxDefaultServerName(String s) { ctxDefaultServerName=s; }
  
  public String getLocation() { return location; }
  public void setLocation(String s) { location=s; }
  public String getMetaLocation() { return metaLocation; }
  public void setMetaLocation(String s) { metaLocation=s; }
  public String getCtxLocation() { return ctxLocation; }
  public void setCtxLocation(String s) { ctxLocation=s; }
  public Op getLocationOp() { return locationOp; }
  public void setLocationOp(Op op) { locationOp=op; }
  public String getCtxDefaultLocation() { return ctxDefaultLocation; }
  public void setCtxDefaultLocation(String s) { ctxDefaultLocation=s; }
  
  public String getFilename() { return filename; } 
  public void setFilename(String s) { filename=s; }
  public String getMetaFilename() { return metaFilename; }
  public void setMetaFilename(String s) { metaFilename=s; }
  public String getCtxFilename() { return ctxFilename; }
  public void setCtxFilename(String s) { ctxFilename=s; }
  public Op getFilenameOp() { return filenameOp; }
  public void setFilenameOp(Op op) { filenameOp=op; }
  public String getCtxDefaultFilename() { return ctxDefaultFilename; }
  public void setCtxDefaultFilename(String s) { ctxDefaultFilename=s; }

  public String getText() { return text; }
  public void setText(String s) { text=s; }
  public String getMetaText() { return metaText; }
  public void setMetaText(String s) { metaText=s; }
  public String getCtxText() { return ctxText; }
  public void setCtxText(String s) { ctxText=s; }
  public Op getTextOp() { return textOp; }
  public void setTextOp(Op op) { textOp=op; }
  public String getCtxDefaultText() { return ctxDefaultText; }
  public void setCtxDefaultText(String s) { ctxDefaultText=s; }
  
  public String getColor() { return color; }
  public void setColor(String s) { color=s; }
  public String getMetaColor() { return metaColor; }
  public void setMetaColor(String s) { metaColor=s; }
  public String getCtxColor() { return ctxColor; }
  public void setCtxColor(String s) { ctxColor=s; }
  public Op getColorOp () { return colorOp; }
  public void setColorOp(Op op) { colorOp=op; }
  public String getCtxDefaultColor() { return ctxDefaultColor; }
  public void setCtxDefaultColor(String s) { ctxDefaultColor=s; }
  
  public String getFontSize() { return fontSize; }
  public void setFontSize(String s) { fontSize=s; }
  public String getMetaFontSize() { return metaFontSize; }
  public void setMetaFontSize(String s) { metaFontSize=s; }
  public String getCtxFontSize() { return ctxFontSize; }
  public void setCtxFontSize(String s) { ctxFontSize=s; }
  public Op getFontSizeOp () { return fontSizeOp; }
  public void setFontSizeOp (Op op) { fontSizeOp=op; }
  public String getCtxDefaultFontSize() { return ctxDefaultFontSize; } 
  public void setCtxDefaultFontSize(String s) { ctxDefaultFontSize=s; }

  public String getXPosition() { return xPosition; }
  public void setXPosition(String s) { xPosition=s; }
  public String getMetaXPosition() {return metaXPosition; }
  public void setMetaXPosition(String s) { metaXPosition=s; }
  public String getCtxXPosition() {return ctxXPosition; }
  public void setCtxXPosition(String s) { ctxXPosition=s; }
  public Op getXPositionOp() { return xPositionOp; }
  public void setXPositionOp(Op op) { xPositionOp=op; }
  public String getCtxDefaultXPosition() { return ctxDefaultXPosition; }
  public void setCtxDefaultXPosition(String s) { ctxDefaultXPosition=s; }
  
  public String getYPosition() { return yPosition; }
  public void setYPosition(String s) { yPosition=s; }
  public String getMetaYPosition() {return metaYPosition; }
  public void setMetaYPosition(String s) { metaYPosition=s; }
  public String getCtxYPosition() {return ctxYPosition; }
  public void setCtxYPosition(String s) { ctxYPosition=s; }
  public Op getYPositionOp() { return yPositionOp; }
  public void setYPositionOp(Op op) { yPositionOp=op; }
  public String getCtxDefaultYPosition() { return ctxDefaultYPosition; }
  public void setCtxDefaultYPosition(String s) { ctxDefaultYPosition=s; }
  
  public String getAngle() { return angle; }
  public void setAngle(String s) { angle=s; }
  public String getMetaAngle() {return metaAngle; }
  public void setMetaAngle(String s) { metaAngle=s; }
  public String getCtxAngle() {return ctxAngle; }
  public void setCtxAngle(String s) { ctxAngle=s; }
  public Op getAngleOp() { return angleOp; }
  public void setAngleOp(Op op) { angleOp=op; }
  public String getCtxDefaultAngle() { return ctxDefaultAngle; }
  public void setCtxDefaultAngle(String s) { ctxDefaultAngle=s; }
  
  public String getOpacity() { return opacity; }
  public void setOpacity(String s) { opacity=s; }
  public String getMetaOpacity() {return metaOpacity; }
  public void setMetaOpacity(String s) { metaOpacity=s; }
  public String getCtxOpacity() {return ctxOpacity; }
  public void setCtxOpacity(String s) { ctxOpacity=s; }
  public Op getOpacityOp() { return opacityOp; }
  public void setOpacityOp(Op op) { opacityOp=op; }
  public String getCtxDefaultOpacity() { return ctxDefaultOpacity; }
  public void setCtxDefaultOpacity(String s) { ctxDefaultOpacity=s; }  
  
  public boolean getRemoveSourcefile() {return removeSourcefile; }
  public void setRemoveSourcefile(boolean b) { removeSourcefile=b; }

  private String serverName=null;
  private String metaServerName=null;
  private String ctxServerName=null; 
  private Op serverNameOp=null;
  private String ctxDefaultServerName = "server-name";
  
  private String location=null;
  private String metaLocation=null;
  private String ctxLocation=null;
  private Op locationOp=null;
  private String ctxDefaultLocation="location";
  
  private String filename=null;
  private String metaFilename=null; 
  private String ctxFilename=null;
  private Op filenameOp=null;
  private String ctxDefaultFilename=null;

  private String text=null;
  private String metaText=null;
  private String ctxText=null;
  private Op textOp=null;
  private String ctxDefaultText=null;
  
  private String color=null;
  private String metaColor=null;
  private String ctxColor=null;
  private Op colorOp=null;
  private String ctxDefaultColor=null;
  
  private String fontSize=null;
  private String metaFontSize=null;
  private String ctxFontSize=null;
  private Op fontSizeOp=null;
  private String ctxDefaultFontSize=null;

  private String xPosition=null;
  private String metaXPosition=null;
  private String ctxXPosition=null;
  private Op xPositionOp=null;
  private String ctxDefaultXPosition=null;
  
  private String yPosition=null;
  private String metaYPosition=null;
  private String ctxYPosition=null;
  private Op yPositionOp=null;
  private String ctxDefaultYPosition=null;

  private String angle=null;
  private String metaAngle=null;
  private String ctxAngle=null;
  private Op angleOp=null;
  private String ctxDefaultAngle=null;

  private String opacity=null;
  private String metaOpacity=null;
  private String ctxOpacity=null;
  private Op opacityOp=null;
  private String ctxDefaultOpacity=null;
  
  private boolean removeSourcefile = false;
}
