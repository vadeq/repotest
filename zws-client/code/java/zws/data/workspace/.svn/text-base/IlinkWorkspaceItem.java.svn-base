package zws.data.workspace; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Jan 5, 2006
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.data.IlinkMetadata;
import zws.data.Metadata;

import java.util.*;

public class IlinkWorkspaceItem extends WorkspaceItemBase {
    public void setMetadata(Metadata m) { setIlinkMetadata((IlinkMetadata)m); }
    public void setIlinkMetadata(IlinkMetadata m) { metadata=m; }

    public String getBranch() { 
      if (null==ilinkMetadata().getBranch()) return "main"; 
      if ("".equals(ilinkMetadata().getBranch().trim())) return "?";
      return ilinkMetadata().getBranch(); 
    }
    public String getRevision() {
      if (null==ilinkMetadata().getRevision()) return "?"; 
      if ("".equals(ilinkMetadata().getRevision().trim())) return "?";
      return ilinkMetadata().getRevision(); 
    }
    public String getVersion() {
      if (null==ilinkMetadata().getVersion()) return "?";
      if ("".equals(ilinkMetadata().getVersion().trim())) return "?";
      if ("-1".equals(ilinkMetadata().getVersion().trim())) return "?";
      return ilinkMetadata().getVersion();
    }
    public String getRelease() { 
      if (null==ilinkMetadata().getRevision()) return "?"; 
      if ("".equals(ilinkMetadata().getRevision().trim())) return "?";
      return ilinkMetadata().getRelease(); 
    }
    public String getFolder() { return ilinkMetadata().getFolder(); }
    public String getCreatedOn() { return ilinkMetadata().getCreatedOn(); }
    public String getCreatedBy() { return ilinkMetadata().getCreatedBy(); }

    public Collection getUserAttributes() { return ilinkMetadata().getUserAttributes(); }

    public IlinkMetadata ilinkMetadata() { return (IlinkMetadata)metadata; } 
}
