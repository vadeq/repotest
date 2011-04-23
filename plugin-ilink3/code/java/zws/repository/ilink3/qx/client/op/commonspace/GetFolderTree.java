package zws.repository.ilink3.qx.client.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.folder.IntralinkFolder;
import zws.repository.ilink3.qx.client.op.xml.FolderTreeHandler;
import zws.repository.ilink3.qx.client.op.xml.IntralinkResultHandler;


public class GetFolderTree extends IntralinkRepositoryOp {
  public IntralinkResultHandler getXMLResultHandler() { return new FolderTreeHandler(); }
    
  protected void createOpInstructionXML(){
    openTag("get-folder-tree");
	  closeTag();
  }

  protected void storeResult(IntralinkResultHandler handler) throws Exception {
    rootFolder = ((FolderTreeHandler)handler).getRootFolder();
  }

  public IntralinkFolder getRootFolder() { return rootFolder; }
  
  private IntralinkFolder rootFolder=null;
}
