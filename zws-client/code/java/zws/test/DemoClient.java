/**
 * DemoClient -- demonstrates using a java application to talk to
 *               a minimal minimal stateless session bean
 */

package zws.test;

import zws.data.Metadata;
import zws.report.Report;
import zws.service.report.EJBLocator;
import zws.service.report.ReportServiceRemote;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * DemoClient demostrates using a minimal stateless session bean
 *
 */



public class DemoClient {
  public static String xmlize(Collection c){
    String result="";
    Iterator i = c.iterator();
    Metadata data;
    while (i.hasNext()) {
      data=(Metadata)i.next();
      result += "\n" + data.toString();
    }
    return result;
  }
  public static void main(String[] args) {
    try {
      ReportServiceRemote service = EJBLocator.findService("designstate");
      Report report = service.getReport("intralink");
      report.generate();
      {} //System.out.println(xmlize(report.getResults()));
      {} //System.out.println("----------------------------------------------");
      report = service.getReport("sql");
      report.generate();
      {} //System.out.println(xmlize(report.getResults()));
      {} //System.out.println("----------------------------------------------");
      report = service.getReport("all");
      report.generate();
      {} //System.out.println(xmlize(report.getResults()));
      {} //System.out.println("----------------------------------------------");
    }
    catch (Exception e) { e.printStackTrace(); }
/*
    {} //System.out.println("\nBegin statelessSession DemoClient...\n");

    parseArgs(args);

    try {
      // Create A Demo object, in the server
      // Note: the name of the class corresponds to the JNDI
      // property declared in the DeploymentDescriptor
      // From DeploymentDescriptor ...
      // beanHomeName              demo.DemoHome
      Context ctx = new InitialContext(); //getInitialContext();
      DemoHome dhome = (DemoHome) ctx.lookup("Demo");

      if (null==dhome) {} //System.out.println("home object is null");
//      Object home = ctx.lookup("Demo");
//      DemoHome demoHome = PortableRemoteObject.narrow(home, DemoHome.class);
      {} //System.out.println("Creating Demo\n");
      Demo demo = dhome.create();

      // Here is the call that executes the method on the
      // server side object
      {} //System.out.println("The result is " + demo.demoSelect());


    }
    catch (Exception e) {
      {} //System.out.println(":::::::::::::: Error :::::::::::::::::");
      e.printStackTrace();
    }
 */
  }

  static void parseArgs(String args[]) {
    if ((args == null) || (args.length == 0))
      return;
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-url"))
        Integer.parseInt(args[++i]);
      else if (args[i].equals("-user"))
        user = args[++i];
      else if (args[i].equals("-password"))
        password = args[++i];
    }
  }

  static String user     = null;
  static String password = null;
  static String url      = "localhost:1099";

  /**
   * Gets an initial context.
   *
   * @return                  Context
   * @exception               java.lang.Exception if there is
   *                          an error in getting a Context
   */
  static public Context getInitialContext() throws Exception {
    Properties p = new Properties();
    p.put(Context.INITIAL_CONTEXT_FACTORY,
          "org.jnp.interfaces.NamingContextFactory ");
    p.put(Context.PROVIDER_URL, url);
    if (user != null) {
      {} //System.out.println ("user: " + user);
      p.put(Context.SECURITY_PRINCIPAL, user);
      if (password == null)
        password = "";
      p.put(Context.SECURITY_CREDENTIALS, password);
    }
    return new InitialContext(p);
  }
  
  
}


