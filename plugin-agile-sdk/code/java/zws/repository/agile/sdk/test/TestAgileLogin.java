package zws.repository.agile.sdk.test;

import zws.repository.agile.sdk.svc.AgileLoginThread;
import zws.security.Authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.agile.api.AgileSessionFactory;
import com.agile.api.IAdmin;
import com.agile.api.IAgileClass;
import com.agile.api.IAgileSession;

public class TestAgileLogin {

  public static void main(String[] args) {
    try {
      TestAgileLogin t = new TestAgileLogin();
      System.out.println("Start......");
      t.testLogin();
      System.out.println("END......");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testLogin() {
      String url ="http://vm-oas9213.dev.zerowait-state.com/Agile";
    try {
      Collection c = new ArrayList();
      c.add(startThreadedSession("intralink", "zero0", url));
      c.add(startThreadedSession("intralink", "zero0", url));
      c.add(startThreadedSession("admin", "agile", url));    
      c.add(startThreadedSession("intralink", "zero0", url));
      c.add(startThreadedSession("admin", "agile", url));    
      c.add(startThreadedSession("intralink", "zero0", url));
      c.add(startThreadedSession("admin", "agile", url));
      Thread.sleep(10000);
      Iterator itr = c.iterator();
      while(itr.hasNext()) {
        IAgileSession session = (IAgileSession) itr.next();
        IAgileClass[] classes = session.getAdminInstance().getAgileClasses(IAdmin.CONCRETE);
        System.out.println("classes " + classes.length);        
        System.out.println("session is open " + session.isOpen());
      }

    } catch (Exception e) {
      e.printStackTrace();
    } 
  }

  private synchronized void startSession(String u, String p, String url) throws Exception {
    System.out.println("start session...");
    IAgileSession session = null;
    HashMap params = new HashMap();

    params.put(AgileSessionFactory.USERNAME, "admin");
    params.put(AgileSessionFactory.PASSWORD, "agile");

    params.put(AgileSessionFactory.URL, url);
    session = AgileSessionFactory.createSessionEx(params); // login

    System.out.println("admin logged in...");

    params = new HashMap();
    params.put(AgileSessionFactory.USERNAME, "intralink");
    params.put(AgileSessionFactory.PASSWORD, "zero0");
    params.put(AgileSessionFactory.URL, url);
    session = AgileSessionFactory.createSessionEx(params); // login

    System.out.println("intralink logged in...");

     session.close();
  }

  private synchronized IAgileSession startThreadedSession(String u, String p, String url) throws Exception {
    AgileLoginThread loginThread = new AgileLoginThread(new Authentication(u, p), url);
    loginThread.execute();
    while(!loginThread.isCompleted()) {
      Thread.sleep(100);
    }
    IAgileSession session = (IAgileSession)loginThread.getResult();
    loginThread.clearThread();
    System.gc();
    return session;
  }
}
