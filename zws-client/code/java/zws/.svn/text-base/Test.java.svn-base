package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 1, 2004, 3:48 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.topology.NodeBase;

import zws.util.comparator.AlphaNumericComparator;
import zws.security.Authentication;

import zws.service.test.EJBLocator;
import zws.service.test.TestService;


import java.util.*;



import zws.application.Names;
import zws.origin.Origin;


public class Test {
  public Test() { } 
  private static void showUsage() {
    {} //System.out.println("Client: Usage:");
    {} //System.out.println("Client: test [Agile URL] [username] [password]");
    System.exit(1);
  }
  public static void main(String[] args) {
    if (3!=args.length) showUsage();
    String url = args[0];
    String user = args[1];
    String psswd = args[2];
    {} //System.out.println("Client: " + url + "  " + user +":"+psswd);
    Test t = new Test();
    try {
      t.runWebSphereTest(url, user, psswd);
      //t.renameTest();
      //t.runTopologyTest(); 
    }
    catch (Exception e) { e.printStackTrace(); }
 }
 
  public void runWebSphereTest(String url, String user, String psswd) {
    String s = "Client App" + Names.NEW_LINE; 
	  try {
	   /*
	   Properties p = new Properties();
	   p.put("java.naming.provider.url", "jnp://DesignState-node-0:1099");
	   p.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
	   p.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
		 Context ctx = new InitialContext(p);
		 EJBHome serviceHome = (EJBHome)ctx.lookup("DesignState-node-0-test-service");
		 //{} //System.out.println("ServiceHome: " + serviceHome.getClass().getName());
	   TestServiceRemote x = null;
		 try{ x=((TestServiceRemoteHome)serviceHome).create(); }
	   catch(javax.ejb.CreateException ce){ ce.printStackTrace(); }
	   catch(RemoteException re){ re.printStackTrace(); }
     */
	   {} //System.out.println("Client: Starting Test2");
	   TestService x = EJBLocator.findService("DesignState-node-0");
	   s += x.runTest(url, user, psswd) + Names.NEW_LINE;
	  }
	  catch (Exception e) {
	    s += " !!!!!Exception thrown!!!!!" + Names.NEW_LINE;
	    s += e.getMessage();
	    e.printStackTrace();
	  }
	  {} //System.out.println("Client: " + s);
  }
  
  public void renameTest() throws Exception {
    Synchronizer sync = Synchronizer.getClient("DesignState-node-0");
    sync.rename("zwait", "DesignState-node-0", "zws-nt4", "123", "aa59126-2601.prt");
    Origin o = sync.lastSynchronization("zwait", "DesignState-node-0", "zws-nt4", "1234512-002");
    {} //System.out.println(o.toString());
    Collection c = sync.findMatches("zwait", "DesignState-node-0", "zws-nt4", "aa59126-2601.prt");
    Iterator i = c.iterator();
    {} //System.out.println("=========================");
    while(i.hasNext()) {
      o = (Origin)i.next();
      {} //System.out.println(o.toString());
    }
    {} //System.out.println("=========================");
  }

  public void runAgileTest() throws Exception {
    Authentication id = new Authentication();
    id.setUsername("pm");
    id.setPassword("agile");
    AgileAccess x = AgileAccess.getAccess("DesignState-node-0", "zero-888", id);
    Collection c = x.listAttributes("Part");
    Iterator i = c.iterator();
    while(i.hasNext()) {
      {} //System.out.println(i.next().toString());
    }
    {} //System.out.println("done");    
  }

  public void runTopologyTest() throws Exception {
    NodeBase n0 = new NodeBase();
    n0.initialize();
    //n0.setAlias("myNode");
    //n0.setHostName("localhost");

    NodeBase n1 = new NodeBase();
    n1.initialize();
    //n1.setAlias("yourNode");
    //n1.setHostName("zws-nt4");

    TopologyClient client0 = TopologyClient.getClient(n0);

    {} //System.out.println(client0.getTopology());
    //client0.register(n1);
    {} //System.out.println(client0.getTopology());
  }
  
  public void run() {
  AlphaNumericComparator c = new AlphaNumericComparator();
  c.setAscendingOrder(true);
  TreeSet t= new TreeSet(c);
  t.add("zws_test_pub2.dwg");
  t.add("zws_test_pub.dwg");
  t.add("zws_test_pub2.dwg");
  t.add("zws_test_pub.dwg");
  t.add("zws_test_pub2.dwg");  
  print(t);
  }
 
  public void print(Collection c) { 
    Iterator i = c.iterator();
    while (i.hasNext()) {} {} //System.out.println(i.next());
  }
}
