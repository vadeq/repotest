package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 18, 2004, 9:21 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class UploadPackage {
  public static void main(String[] args) { 
    try {
     // int sourceNode = Integer.getInteger(args[0]).intValue();
      int targetNode = Integer.getInteger(args[1]).intValue();
      Replicator rep = Replicator.getClient("DesignState-node-"+targetNode);
      
    }
    catch(Exception e) { e.printStackTrace(); }
      /*
      try { client.reloadConfiguration(NODE_2); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
      try { client.reloadConfiguration(NODE_3); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
      try { client.reloadConfiguration(NODE_4); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
      try { client.reloadConfiguration(NODE_5); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
      try { client.reloadConfiguration(NODE_6); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
      try { client.reloadConfiguration(NODE_7); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
      try { client.reloadConfiguration(NODE_8); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
      try { client.reloadConfiguration(NODE_9); } catch (NameNotFoundException e) {} catch (Exception e) { e.printStackTrace(); }
    */
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
