package zws.action.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 22, 2004, 2:43 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.action.ActionBase;
import zws.op.Op;
import zws.util.FileNameUtil;

public class ConvertFilename extends ActionBase {
  public void execute() throws Exception {
    String file = getString("filename");
    if (null==file) file=grabMetadata().getName();

    String prefix=getString("prefixTokens");
    String postfix=getString("postfixTokens");
    String type = getString("fileType");
    String ext = FileNameUtil.getFileNameExtension(file);
    if (null==ext) ext="";
    if (file.lastIndexOf(".") == file.length()-ext.length()-1) ext = "." + ext;
    if (null!= prefix) file = parse(prefix) + file;
    if (null!= postfix) file = FileNameUtil.getBaseName(file) + parse(postfix) + ext;
    if (null!=fileType) file = FileNameUtil.convertType(file, type);
    setResult(file);
  }

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

  public String getFileType() { return fileType; }
  public void setFileType(String s) { fileType=s; }
  public String getMetaFileType() { return metaFileType; }
  public void setMetaFileType(String s) { metaFileType=s; }
  public String getCtxFileType() { return ctxFileType; }
  public void setCtxFileType(String s) { ctxFileType=s; }
  public Op getFileTypeOp() { return fileTypeOp; }
  public void setFileTypOp(Op op) { fileTypeOp=op; }
  public String getCtxDefaultFileType() { return ctxDefaultFileType; }
  public void setCtxDefaultFileType(String s) { ctxDefaultFileType=s; }
  
  public String getPostfixTokens() { return postfixTokens; }
  public void setPostfixTokens(String s) { postfixTokens=s; }
  public String getMetaPostfixTokens() { return metaPostfixTokens; }
  public void setMetaPostfixTokens(String s) { metaPostfixTokens=s; }
  public String getCtxPostfixTokens() { return ctxPostfixTokens; }
  public void setCtxPostfixTokens(String s) { ctxPostfixTokens=s; }
  public Op getPostfixTokensOp() { return postfixTokensOp; }
  public void setPostfixTokensOp(Op op) { postfixTokensOp=op; }
  public String getCtxDefaultPostfixTokens() { return ctxDefaultPostfixTokens; }
  public void setCtxDefaultPostfixTokens(String s) { ctxDefaultPostfixTokens=s; }
  
  public String getPrefixTokens() { return prefixTokens; }
  public void setPrefixTokens(String s) { prefixTokens=s; }
  public String getMetaPrefixTokens() { return metaPrefixTokens; }
  public void setMetaPrefixTokens(String s) { metaPrefixTokens=s; }
  public String getCtxPrefixTokens() { return ctxPrefixTokens; }
  public void setCtxPrefixTokens(String s) { ctxPrefixTokens=s; }
  public Op getPrefixTokensOp() { return prefixTokensOp; }
  public void setPrefixTokensOp(Op op) { prefixTokensOp=op; }
  public String getCtxDefaultPrefixTokens() { return ctxDefaultPrefixTokens; }
  public void setCtxDefaultPrefixTokens(String s) { ctxDefaultPrefixTokens=s; }
  
  private String filename=null;
  private String metaFilename=null; 
  private String ctxFilename=null;
  private Op filenameOp=null;
  private String ctxDefaultFilename=null;
  
  private String fileType=null;
  private String metaFileType=null;
  private String ctxFileType=null;
  private Op fileTypeOp=null;
  private String ctxDefaultFileType=null;
  
  private String postfixTokens=null;
  private String metaPostfixTokens=null;
  private String ctxPostfixTokens=null;
  private Op postfixTokensOp=null;
  private String ctxDefaultPostfixTokens=null;
  
  private String prefixTokens=null;
  private String metaPrefixTokens=null;
  private String ctxPrefixTokens=null;
  private Op prefixTokensOp=null;
  private String ctxDefaultPrefixTokens=null;

}
