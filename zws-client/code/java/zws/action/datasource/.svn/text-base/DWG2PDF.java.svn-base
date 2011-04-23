package zws.action.datasource;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 12, 2004, 2:39 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.DocumentConverter;
import zws.util.FileNameUtil;

public class DWG2PDF extends WorkspaceAction {
  public void execute() throws Exception { //Should craeate a version of this that is independent of workspace
    String name = FileNameUtil.convertType(grabMetadata().getName(), "pdf");
    DocumentConverter.convertDWG2PDF(grabOrigin(), getRequiredString("workspace"), getRequiredString("location"), name, null);
  }
}
