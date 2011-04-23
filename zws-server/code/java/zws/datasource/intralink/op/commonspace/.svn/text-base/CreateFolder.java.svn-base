package zws.datasource.intralink.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.IOException;

public class CreateFolder extends IntralinkRepositoryOp {
    
  protected void writeRepositoryInstruction() throws IOException {
    openTag("create-folder");
		  writeParameter("name", getName());
		  writeParameter("parent", getParent());
		  if (null!=releaseScheme) writeParameter("release-scheme", getReleaseScheme());
		  if(null!=fileSpace) writeParameter("file-space", getFileSpace());
		  closeTag();
	}

	 public String getName() { return name; }
	 public void setName(String s) { name=s; }
	 public String getParent() { return parent; }
	 public void setParent(String s) { parent= s; }

  public String getReleaseScheme() { return releaseScheme; }
  public void setReleaseScheme(String s) { releaseScheme=s; }
  public String getFileSpace() { return fileSpace; }
  public void setFileSpace(String s) { fileSpace=s; }

 	private String name=null;
 	private String parent=null;
  private String releaseScheme=null;
  private String fileSpace=null;
}
