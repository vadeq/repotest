import java.security.Security;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;

/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Nov 12, 2007 5:05:19 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

public class TestHttp {

  public static void main(String[] args) {
    try {
      postEvent("<event eco_number='1234567890'/>");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private static void postEvent(String msg) throws Exception{
    System.out.println("post event px....");
    PostMethod filePost = null;
    try {
     filePost = new PostMethod("http://designstate-0/eventProcessor/fireEvent.jsp");
     NameValuePair[] msgPair = new NameValuePair[2];
     msgPair[0] = new NameValuePair("event", "fire");
     msgPair[1] = new NameValuePair("firedEvent", msg);
     filePost.setQueryString(msgPair);
     HttpClient httpclient = new HttpClient();
     System.getProperties().put("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
     Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
     httpclient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("admin", "agile"));
       int result = httpclient.executeMethod(filePost);
     } finally {
       filePost.releaseConnection();
     }
  }
}
