package zws.datasource.intralink.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.intralink.xml.IntralinkResultHandler;
import zws.datasource.intralink.xml.ListAttributesHandler;
import zws.folder.IntralinkFolder;
import zws.datasource.intralink.xml.FolderTreeHandler;

import java.io.IOException;

public class GetFolderTree extends IntralinkRepositoryOp {
  public IntralinkResultHandler getXMLResultHandler() { return new FolderTreeHandler(); }
    
  protected void writeRepositoryInstruction() throws IOException {
    openTag("get-folder-tree");
	  closeTag();
  }

  protected void storeResult(IntralinkResultHandler handler) throws Exception {
    rootFolder = ((FolderTreeHandler)handler).getRootFolder();
  }

  public IntralinkFolder getRootFolder() { return rootFolder; }
  
  private IntralinkFolder rootFolder=null;
}
