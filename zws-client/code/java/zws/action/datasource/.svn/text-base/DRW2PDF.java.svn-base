package zws.action.datasource;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 12, 2004, 2:39 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.DocumentConverter;
import zws.util.FileNameUtil;

public class DRW2PDF extends WorkspaceAction {
  public void execute() throws Exception {
    String name = FileNameUtil.convertType(grabMetadata().getName(), "pdf");
    DocumentConverter.convertDRW2PDF(grabOrigin(), getRequiredString("workspace"), getRequiredString("location"), name, null);
  }
}
