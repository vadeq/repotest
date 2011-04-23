package zws.service; /*
DesignState - Design Compression Technology.
@author: Jacob Yelizarov
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.TopologyClient;
import zws.application.Names;
import zws.topology.Node;

import java.io.InputStream;
import java.rmi.Remote;
import java.util.*;

import javax.ejb.EJBHome;
import javax.naming.*;

public abstract class EJBLocatorBase {
  private static Properties configuration = null;
  private static Map JNDIProps = new HashMap();
  private static Map ejbServices=new HashMap();
  private static Context ctx = null;

  
  private static Properties getJNDIProperties(String serverName) {
    if (null==JNDIProps) JNDIProps = new HashMap();
    return (Properties)JNDIProps.get(serverName);
  }
  
  private static synchronized void putJNDIProperties(String serverName, Properties p) {
    if (null==JNDIProps) JNDIProps = new HashMap();
    JNDIProps.put(serverName, p);
  }
  
  
  private static synchronized void removeJNDIProperties(String serverName) {
    if (null==JNDIProps) JNDIProps = new HashMap();
    if (JNDIProps.containsKey(serverName)) JNDIProps.remove(serverName);
  }
  
  protected static Remote find(String serverName, Locator locator)throws NamingException{
		Properties p=null;
		String key = serverName + Names.DASH + locator.getServiceName();
		if (ejbServices.containsKey(key)) return (Remote)ejbServices.get(key);

		p = getJNDIProperties(serverName);
		if (null==p) {
      try {
		    long number = Long.valueOf(serverName.substring(serverName.lastIndexOf("-")+1)).longValue();
	  	  TopologyClient c = TopologyClient.getClient(number, serverName);
	      Node n = c.ping();
			  p = n.getJNDIProperties();
			 putJNDIProperties(serverName, p);
		  }
		  catch (Exception ignore) { }
		}
		if (null!= p) ctx = new InitialContext(p);
		else ctx = new InitialContext();
		EJBHome serviceHome = (EJBHome)ctx.lookup(key);
		{} //System.out.println("Creating " + key + "\n");
	  Remote service = locator.createService(serviceHome);
		ejbServices.put(key, service);
		return service;
  }

  protected static Remote find(Node node, Locator locator)throws NamingException{
    String serverName = "DesignState-node-"+node.getNumber();
		String serviceName = serverName + "-" + locator.getServiceName();;
		if (ejbServices.containsKey(serviceName)) return (Remote) ejbServices.get(serviceName);

		Properties p = node.getJNDIProperties();
		putJNDIProperties(serverName, p);
		ctx = new InitialContext(p);
		{} //System.out.println("getting Home interface...");
		Object o = ctx.lookup(serviceName);
		{} //System.out.println("got a Home interface: " + o.getClass().getName());
		{} //System.out.println(o);
		EJBHome serviceHome = (EJBHome)o;
		{} //System.out.println("ServiceHOme: " + serviceHome.getClass().getName());
		{} //System.out.println("getting Remote interface...");
	  Remote service = locator.createService(serviceHome);
		ejbServices.put(serviceName, service);
		return service;
  }
  
  public static synchronized void clear(Node node) {
    String serverName = "DesignState-node-"+node.getNumber();
    
  	removeJNDIProperties(serverName);
  	Iterator i = ejbServices.keySet().iterator();
  	String k;
  	Collection obsoleteKeys = new Vector();
  	while (i.hasNext()) {
  	  k = (String)i.next();
  	  if (k.startsWith(serverName)) obsoleteKeys.add(k);
  	}
  	i = obsoleteKeys.iterator();
  	while (i.hasNext()) {
  	  k = (String)i.next();
   	  if (k.startsWith(serverName)) ejbServices.remove(k);
   	} 	
  }

  protected static Properties getConfiguration(Locator locator){
	 if (null==configuration) {
	   try {
	   {} //System.out.println("...........Reading /zws-jndi.properties");
		 InputStream in = (locator).getClass().getResourceAsStream("/zws-jndi.properties");
		 configuration = new Properties();
		 configuration.load(in);
		 in.close();
	   {} //System.out.println("...........Read /zws-jndi.properties");
	   {} //System.out.println(configuration);
	   {} //System.out.println("...........end properties");
	   }
	   catch (Exception e) {
		 {} //System.out.println("\nCould not load jndi.properties file from class path");
		 //e.printStackTrace();
	   }
	 }
	 return configuration;
  }

   /*protected static Context getHAContext()throws NamingException{
	  Properties p = new Properties();
	  p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
	  p.put(Context.URL_PKG_PREFIXES, "jboss.naming:org.jnp.interfaces");
	  p.put(Context.PROVIDER_URL, "localhost:1100");
	  return new InitialContext(p);
	}*/
  
}




