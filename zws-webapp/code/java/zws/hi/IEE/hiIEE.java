package zws.hi.IEE; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 27, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.IntralinkClient;
import zws.exception.InitializationError;
import zws.folder.IntralinkFolder;
import zws.security.Authentication;

import java.util.Collection;

public class hiIEE {
  public static IntralinkClient getIntralinkClient(Authentication id) {
    IntralinkClient client = (IntralinkClient)ilinkClient.get();
    client.initialize(id);
    return client;
  }
  
  private static ThreadLocal ilinkClient = new ThreadLocal() {
    protected synchronized Object initialValue() {
      return new IntralinkClient();
    }
  };

  /*
  public Collection getServers() {
    return ((IntralinkRepositories)(ilinkRepositories.get())).listServers();
  }
  public Collection getRepositories() {
    return ((IntralinkRepositories)(ilinkRepositories.get())).listRepositories();
  }

  public String getSelectedServer() {
    return ((IntralinkRepositories)(ilinkRepositories.get())).getSelectedServer();
  }
  public void setSelectedServer(String s) throws InitializationError {
	  ((IntralinkRepositories)(ilinkRepositories.get())).setSelectedServer(s);
	}
  
  public String getSelectedRepository() {
    return ((IntralinkRepositories)(ilinkRepositories.get())).getSelectedRepository();
  }  
  public void setSelectedRepository(String s) throws InitializationError {
    ((IntralinkRepositories)(ilinkRepositories.get())).setSelectedRepository(s);
  }  

  public Collection listNames() throws Exception {
    return ((IntralinkRepositories)(ilinkRepositories.get())).listNames();      
  }

  public Collection getUserDefinedAttributes() throws Exception {
    return ((IntralinkRepositories)(ilinkRepositories.get())).getUserDefinedAttributes();      
  }

  public Collection getRevisionSequence() throws Exception {
    return ((IntralinkRepositories)(ilinkRepositories.get())).getRevisionSequence();      
  }

  public Collection getReleaseSequence(String releaseScheme) throws Exception {
    return ((IntralinkRepositories)(ilinkRepositories.get())).getReleaseSequence(releaseScheme);      
  }
  
  public IntralinkFolder getRootFolder() throws Exception {
    return ((IntralinkRepositories)(ilinkRepositories.get())).getRootFolder();      
  }
*/
  
  //private static hiIEE client = new hiIEE();
}
