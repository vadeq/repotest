package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 18, 2004, 3:18 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.service.EJBLocator;
import zws.service.PrototypeService;

import javax.naming.NameNotFoundException;

public class DesignStateClient {
  public DesignStateClient() { }
  
  public static void main(String[] args) {
    DesignStateClient client = new DesignStateClient();
    if (args.length > 0) {
      for (int idx=0; idx< args.length; idx++) {
        try {
          System.out.print("DesignState-node-"+args[idx]+": ");
          client.reloadConfiguration("DesignState-node-"+args[idx]);
          {} //System.out.println("reloaded");
        }
        catch (NameNotFoundException e) { {} //System.out.println("Node not found"); }
          
        }
        catch (Exception e) {
          {} //System.out.println(e.getMessage()); 
          e.printStackTrace(); }
      }
    }
    else client.reloadAll();
  }

  public void reloadAll() {
    try { reloadConfiguration(NODE_0); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
    try { reloadConfiguration(NODE_1); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
    try { reloadConfiguration(NODE_2); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
    try { reloadConfiguration(NODE_3); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
    try { reloadConfiguration(NODE_4); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
    try { reloadConfiguration(NODE_5); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
    try { reloadConfiguration(NODE_6); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
    try { reloadConfiguration(NODE_7); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
    try { reloadConfiguration(NODE_8); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
    try { reloadConfiguration(NODE_9); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
  }
 
  public static void reloadConfiguration(int node) throws Exception {
    reloadConfiguration("DesignState-node-"+node);
  }

  public static void reloadConfiguration(String serverName) throws Exception {
    PrototypeService service = EJBLocator.findService(serverName);
    service.reload();
  }

  public static String NODE_0 = "DesignState-node-0";
  public static String NODE_1 = "DesignState-node-1";
  public static String NODE_2 = "DesignState-node-2";
  public static String NODE_3 = "DesignState-node-3";
  public static String NODE_4 = "DesignState-node-4";
  public static String NODE_5 = "DesignState-node-5";
  public static String NODE_6 = "DesignState-node-6";
  public static String NODE_7 = "DesignState-node-7";
  public static String NODE_8 = "DesignState-node-8";
  public static String NODE_9 = "DesignState-node-9";
}
