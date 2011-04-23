package zws.datasource.intralink.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.*;
import java.io.File;
import java.io.FileWriter;

public class GetFolderListing extends IntralinkRepositoryOp {

  protected void writeRepositoryInstruction() throws IOException {
    openTag("get-folder-listing");
    writeParameter("path", getPath());
	  writeParameter("list-folders", listFolders);
	  writeParameter("list-components", listComponents);
	  if (listComponents) writeParameter("file-based", listFileBasedComponents);
	  if (listFolders) writeParameter("recursive", listRecursively); 
	  if (null!=selectedAttributes) writeParameter("select", selectedAttributes);
	  closeTag();
  }
	public String getPath() { return path; }
	public void setPath(String s) { path= s; }
  public boolean getListFolders() { return listFolders; }
	public void setListFolders(boolean b) { listFolders=b; }
  public boolean getListComponents() { return listComponents; }
  public void setListComponents(boolean b) { listComponents=b; }
  public boolean getListRecursively() { return listRecursively; }
  public void setListRecursively(boolean b) { listRecursively=b; }
  public String getSelectedAttributes() { return selectedAttributes; }
  public void setSelectedAttributes(String s) { selectedAttributes=s; }
  
	private String path=null;
  private boolean listFolders=true;
  private boolean listComponents=true;
  private boolean listFileBasedComponents=false;
  private boolean listRecursively=true;
  private String selectedAttributes=null;
}
