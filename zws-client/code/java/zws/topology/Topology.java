package zws.topology;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 21, 2004, 3:06 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.NameNotFound;

import java.util.Collection;

public interface Topology {
  public boolean contains(Node n);
  public boolean containsIP(String ip);
  public boolean containsHost(String hostName);
  public Node find(Node n) throws NameNotFound;
  public Node findIP(String ip) throws NameNotFound;
  public Node findHost(String hostName) throws NameNotFound;
  public Collection getNodes();
  public String toXML();
}
