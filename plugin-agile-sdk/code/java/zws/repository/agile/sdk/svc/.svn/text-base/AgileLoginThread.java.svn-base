/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on May 16, 2008 12:10:37 PM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.repository.agile.sdk.svc;

import zws.security.Authentication;

import java.util.HashMap;

import com.agile.api.AgileSessionFactory;
import com.agile.api.IAgileSession;

public class AgileLoginThread extends zws.op.ThreadedOpBase {
  

  public AgileLoginThread(Authentication authID, String strURL) {
    id = authID;
    url = strURL;
  }
  
  public void executeRun() throws Exception {
    try {
        HashMap params = new HashMap();
        params.put(AgileSessionFactory.USERNAME, id.getUsername());
        params.put(AgileSessionFactory.PASSWORD, id.getPassword());
        params.put(AgileSessionFactory.URL, url);
        session = AgileSessionFactory.createSessionEx(params); // login
        if(null != session && session.isOpen()) {
          System.out.println(id.getUsername() + " Logged in to Agile");        
        } else {
          throw new Exception(id.getUsername() + " Failed to login to Agile");
        }
    } catch (Exception e) {
      isCompleted = true;
      throw e;
    } finally {
      isCompleted = true;
    }
  }


  public Object getResult() {
    return session ;
  }
  
  public boolean isCompleted() {
    return isCompleted;
  }

  private Authentication id = null;
  private String url = null;
  private boolean isCompleted = false;
  private IAgileSession session = null;
}
