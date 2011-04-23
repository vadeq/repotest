package zws.datasource.intralink.op.workspace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 1, 2004, 3:40 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;

import java.io.*;
import java.io.File;
import java.io.FileWriter;

public class Checkin extends IntralinkWorkspaceOp {
  protected void writeWorkspaceInstruction() throws IOException {
    openTag("checkin");
    closeTag();
  }
}
