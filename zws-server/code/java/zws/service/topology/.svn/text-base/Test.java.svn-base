package zws.service.topology;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 21, 2004, 7:10 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.TopologyClient;
import zws.topology.Node;
import zws.topology.NodeBase;
//import zws.util.{}//Logwriter;

public class Test {
  public Test() { }
  public static void main(String[] args) { 
    Test t = new Test();
    t.run();
  }

  public void run() {
    {}//Logwriter.printOnConsole("Starting..");
    try {
    Node n0 = newNode("n0", "zero-8");
    Node n1 = newNode("n1", "zws-nt4");
    Node n2 = newNode("n2", "zero-dep2");
    {}//Logwriter.printOnConsole(n0);
    {}//Logwriter.printOnConsole(n1);
    {}//Logwriter.printOnConsole(n2);
    TopologyClient n0Service= TopologyClient.getClient(n0);
    TopologyClient n1Service= TopologyClient.getClient(n1);
    TopologyClient n2Service= TopologyClient.getClient(n2);

    n0 = n0Service.ping();
    n1 = n1Service.ping();
    n2 = n2Service.ping();
    
    //n0Service.register(n1);
    //n0Service.register(n2);
    
    n2Service.unregister(n0.getHostName());
    //n2Service.unregister(n0.getHostName());
    //n0Service.synchronizeToRemoteTopology(n1);
    
    {}//Logwriter.printOnConsole("n0------------------------");
    {}//Logwriter.printOnConsole(n0Service.getTopology());
    {}//Logwriter.printOnConsole("n1------------------------");
    {}//Logwriter.printOnConsole(n1Service.getTopology());
    {}//Logwriter.printOnConsole("n2------------------------");
    {}//Logwriter.printOnConsole(n2Service.getTopology());
      
      
//      ping();
    
//      clear();
//      TopologySvc.findTopologyService(n0).register(n2);
//      TopologySvc.findTopologyService(n0).register(n3);
//      TopologySvc.findTopologyService(n0).register(n4);
//      TopologySvc.findTopologyService(n0).register(n5);
//      TopologySvc.findTopologyService(n0).register(n6);
//      TopologySvc.findTopologyService(n0).register(n7);
//      TopologySvc.findTopologyService(n1).register(n2);
      
      //Node n8 = TopologySvc.ping();
      {}//Logwriter.printOnConsole(n8);
      {}//Logwriter.printOnConsole(TopologySvc.findNode(n8.getJNDIProperties()));
    }
    catch(Exception e) { e.printStackTrace(); }
    {}//Logwriter.printOnConsole("Done..");
  }
  /*
    Node n0 = null;
    Node n1 = null;
    Node n2 = null;
    Node n3 = null;
    Node n4 = null;
    Node n5 = null;
    Node n6 = null;
    Node n7 = null;
  */
  private void init() throws Exception {
    /*
    n2 = newNode("dep2", "zero-dep2");
    n3 = newNode("3", "zero-3");
    n4 = newNode("4", "zero-4");
    n5 = newNode("5", "zero-5");
    n6 = newNode("6", "zero-6");
    n7 = newNode("7", "zero-7");
     */
  }

  /*
  private void ping() throws Exception  {
    TopologySvc.findTopologyService(n0).ping();
    TopologySvc.findTopologyService(n1).ping();
    TopologySvc.findTopologyService(n2).ping();
  }
  
  private void clear() throws Exception  {
    //TopologySvc.findTopologyService(n0).clear();
    //TopologySvc.findTopologyService(n1).clear();
    //TopologySvc.findTopologyService(n2).clear();   
  }
  */
  private Node newNode(String alias, String host) {
    NodeBase n = new NodeBase();
    n.initialize();
    //n.setAlias(alias);
    //n.setHostName(host);
    return n;
  }
}
