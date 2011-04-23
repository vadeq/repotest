package zws.repository.ilink3.qx.client.op.commonspace; /*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on June 7, 2004, 1:34 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class Lock extends IntralinkRepositoryOp {
    
  protected void createOpInstructionXML(){
    openTag("lock");
    endUnaryTag(); 
    
    Iterator j = componentNames.iterator();
    String name;
    while (j.hasNext()) {
      openTag("metadata");
      name = (String)j.next();
      writeParameter("name", name);
      writeParameter("branch", getBranch());
      writeParameter("table", lockFamilyMembers);
      closeTag();
    }
    closeTag("lock");
  }

  public void add(String name) { componentNames.add(name); }
  public void add(Collection names) { componentNames.addAll(names); }
  
  public String getBranch() { return branch; }
  public void setBranch(String s) { branch=s; }
  public boolean getLockAllFamilyMembers() { return lockFamilyMembers; }
  public void setLockAllFamilyMembers(boolean b) { lockFamilyMembers=b; }

  private String branch="main";
  private Collection componentNames = new Vector();
  private boolean lockFamilyMembers = true;
}
