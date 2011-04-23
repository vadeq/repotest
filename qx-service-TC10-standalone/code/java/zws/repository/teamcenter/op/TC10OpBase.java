package zws.repository.teamcenter.op;
/**
 * 
 */
/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Aug 31, 2007 3:28:35 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.op.ListOpBase;
import zws.repository.teamcenter.TC10RepositoryBase;
import zws.security.Authentication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author arbind
 *
 */
public abstract class TC10OpBase extends ListOpBase implements TC10Op {

  public void add(TC10Op op) {
    if (null==kids) kids = new Vector();
    kids.add(op);
  }
  
  public void executeKids() throws Exception {
    TC10Op op;
    Object r=null;
    Collection results = new Vector();
    initializeStorage(results);

    if (null==kids) return;
    Iterator k = kids.iterator();
    while (k.hasNext()) {
      op = (TC10Op)k.next();
      op.initializeStorage();
      passConfiguration(op);
      op.execute();
      r = op.getResult();
      store(r);
    }
  }
  private void passConfiguration(TC10Op op) {
    op.setRepository(getRepository());    
    op.setAuthentication(getAuthentication());
  }
  
  
  public void debug(Object o, String msg) {
    String s = "[" + o.getClass().getName() + "] " + msg;
    debug(s);
  }
  
  public void debug(String msg) {
    try {
      File debug = new File("debug.txt");
      OutputStream debugStream = new FileOutputStream(debug);
      PrintStream s = new PrintStream(debugStream);
      char[] m = msg.toCharArray();
      for (int i=0; i<m.length; s.print(m[i++]));
    }
    catch(Exception x) {
      x.printStackTrace();
    }
    
  }
  public Authentication getAuthentication() { return id; }
  public void setAuthentication(Authentication a) { id = a; }
  public String getRepositoryName() { return repositoryName; }
  public void setRepositoryName(String s) { repositoryName=s; }
  public TC10RepositoryBase getRepository() { return rep; }
  public void setRepository(TC10RepositoryBase r) { rep = r; }
  private TC10RepositoryBase rep=null;
  private String repositoryName=null;
  private Authentication id=null;
  private Collection kids = null;
}
