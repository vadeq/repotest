package zws.data.configuration;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 4, 2004, 3:07 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.data.Metadata;
import zws.data.configuration.comparator.BranchComparator;

import java.util.*;

public class BranchBase extends ConfigurationNodeBase implements Branch {

  public String getBranchName() { return getBranchFromKey(getKey()); }  
  
  public Metadata getFirst() {
    if (null!=history) return history.getFirst();
    if (null==getChildren()) return null;
    Iterator i = getChildren().iterator();
    Metadata m;
    while (i.hasNext()) {
      m = ((Branch)i.next()).getFirst();
      if (null!=m) return m;
    }
    return null;
  }
  
  public History getHistory() {return history; }

  public Collection getRevisions() { return history.getRevisions(); }
  public Collection getVersions(String revision) { return history.getVersions(revision); }
  public SortedSet getBranches() { 
    SortedSet s = new TreeSet(getComparator());
    s.add(getName());
    if (null==getChildren()) return s;
    Iterator i = getChildren().iterator();
    while (i.hasNext()) s.addAll(((Branch)i.next()).getBranches());
    return s;
  }
  public SortedSet getSubBranches() { return getChildren(); }

  public String getBranchFromKey (String branchKey) { return branchKey.substring(branchKey.lastIndexOf(Names.DELIMITER)+1); }
  
  public boolean keyMatchesConfiguration(Metadata data) { return matchesRootBranch(data); }

  //all branch tokens of a parent must be identical to the first branch tokens of a child
  private boolean isParent(StringTokenizer parent, StringTokenizer child) {
    if (child.countTokens() < parent.countTokens()) return false;
    while(parent.hasMoreTokens()) { if (!child.nextToken().equals(parent.nextToken())) return false; }
    return true;
  }

  public boolean keyMatchesChild(Metadata data) { 
    StringTokenizer dataBranches = getBranchTokens(getBranchFromKey(makeKey(data)));
    StringTokenizer branches=getBranchTokens(getBranchFromKey(getKey()));
    return isParent(branches, dataBranches);
  }

  public boolean keyMatchesAncestor(Metadata data) {
    StringTokenizer dataBranches = getBranchTokens(getBranchFromKey(makeKey(data)));
    StringTokenizer branches=getBranchTokens(getBranchFromKey(getKey()));
    return isParent(dataBranches, branches);
  }
  
  public boolean matchesRootBranch(Metadata data) { 
    String dataRoot = getBranchTokens(getBranchFromKey(makeKey(data))).nextToken();
    String rootBranch = getBranchTokens(getBranchFromKey(getKey())).nextToken();
    return rootBranch.equals(dataRoot);    
  }
  
  public String getRootBranchName(String key) { return getBranchTokens(key).nextToken();  }
  public String getBranchDelimiter() { return branchDelimiter; }
  public void setBranchDelimiter(String s) { branchDelimiter=s; }
  public boolean isOnRootBranch(Metadata data) { 
    StringTokenizer tokens = getBranchTokens(makeKey(data));
    if (tokens.countTokens() > 1) return false;
    return tokens.nextToken().equals(getRootBranchName(getKey()));
  }  
  
  public StringTokenizer getBranchTokens(String branchName) { 
    if (-1==branchName.indexOf(branchDelimiter)) return new StringTokenizer(branchName+branchDelimiter, branchDelimiter); 
    else return new StringTokenizer(branchName, branchDelimiter); 
  }
   
  protected BranchBase loadBranch(String branchPath) throws Exception {
    BranchBase target = ((BranchBase)getRootNode()); 
    if (target.getBranchName().equals(branchPath)) return target;
    target = (BranchBase)target.findSubBranch(branchPath);
    if (null==target) target=createSubBranches(branchPath);
    return target;
  }
  
  public Branch findSubBranch(String branchPath){
    if (null==getChildren() || 0==getChildren().size()) return null;
    BranchBase parent=this;
    BranchBase child = this;
    StringTokenizer branches = getBranchTokens(branchPath);
    branches.nextToken();
    while (child!=null && branches.hasMoreTokens()) {
      child = parent.findChildBranch(branches.nextToken());
      parent=child;
    }
    return child;
  }

  protected BranchBase findChildBranch(String childName){
    if (null==getChildren()) return null;
    Iterator i = getChildren().iterator();
    BranchBase b;
    while (i.hasNext()) {
      b = (BranchBase)i.next();
      if (b.getBranchName().equals(getBranchName()+branchDelimiter+childName)) return b;
    }
    return null;
  }

  protected BranchBase createSubBranches(String branchPath){
    BranchBase parent=(BranchBase)getRootNode();
    BranchBase child = null;
    StringTokenizer branches = getBranchTokens(branchPath);
    String childName = branches.nextToken();
    while (branches.hasMoreTokens()) {
      childName=branches.nextToken();
      child = parent.findChildBranch(childName);
      if (null==child) child = parent.createChildBranch(childName);
      parent=child;
    }
    return child;
  }

  protected BranchBase createChildBranch(String childName) {
    BranchBase child = new BranchBase();
    child.defineKeyFields(getParentLevelKeyFields(), getNodeLevelKeyFieldname(), getChildLevelKeyFieldname());
    child.setKey(getKey() + branchDelimiter + childName);
    child.setParent(this);
    getChildren().add(child);
    return child;
  }
  
  
  public void placeInConfiguration(Metadata data) throws Exception {
    BranchBase target = (BranchBase)loadBranch(getBranchFromKey(makeKey(data)));
    target.placeInHistoryConfiguration(data);
    return;
  }
    
  protected void placeInHistoryConfiguration(Metadata data) throws Exception {
    if (null==history) {
      history=new HistoryBase();
      history.defineKeyFields(getParentLevelKeyFields(), getNodeLevelKeyFieldname(), getChildLevelKeyFieldname());
      history.defineKey(data);
    }
    history.place(data);
    return;
  }
  
  protected Comparator getComparator() {  comparator.setAscendingOrder(true); return comparator; }
  
  public String toString() {
    String s;
    if (null==getChildren() && null==history) return "<branch name=\""+getBranchName()+"\" level=\""+getHeight()+"\"/>" +NEWLINE;
    s="<branch name=\""+getBranchName()+"\" level=\""+getLevel()+"\">" +NEWLINE;
    if (null!=history) s+=history.toString();
    if (null!=getChildren()) {
      Iterator i = getChildren().iterator();
      while(i.hasNext()) s+=i.next();
    }
    s+="</branch>"+NEWLINE;
    return s;
  }

  protected void nullify() { 
    super.nullify(); 
    history=null; 
  }

  
  protected void setHistory(History h) { history=h; }
  private BranchComparator comparator = new BranchComparator();
  protected String BRANCH="Branch";
  protected String VERSION="Version";
  private static String NEWLINE=zws.application.Names.NEW_LINE;
  private History history = null;
  private String branchDelimiter ="/";
  private String branchName=null;
}

/*
   
  protected void createNewChild(Metadata data) throws Exception {
    BranchBase child = new BranchBase();
    child.defineKeyFields(getParentLevelKeyFields(), getNodeLevelKeyFieldname(), getChildLevelKeyFieldname());
    child.defineKey(data);
    placeInNewChild(child, data);
  }

  protected void swap(Metadata data) throws Exception {
    BranchBase node = new BranchBase();
    node.setParent(this);
    node.defineKeyFields(getParentLevelKeyFields(), getNodeLevelKeyFieldname(), getChildLevelKeyFieldname());
    node.setKey(getKey());
    node.BRANCH=BRANCH;
    node.VERSION=VERSION;
    node.setHistory(history);
    node.setMetadata(getMetadata());
    node.setChildren(getChildren());
    node.setConfigurationNodes(getConfigurationNodes());
    
    TreeNode parent = getParent();
    nullify();
    setParent(parent);
    defineKeyFields(node.getParentLevelKeyFields(), node.getNodeLevelKeyFieldname(), node.getChildLevelKeyFieldname());
    defineKey(data);
    //setChildren(new TreeSet(getComparator()));
    getChildren().add(node);
    place(data);
  }

   protected void swap(BranchBase node) {
     super.swap(node);
     String b=BRANCH;
     String v=VERSION;
     History h=history;
     String delimiter = branchDelimiter;

     BRANCH=node.BRANCH;
     VERSION=node.VERSION;
     history=node.getHistory();
     branchDelimiter=node.getBranchDelimiter();

     node.BRANCH=b;
     node.VERSION=v;
     node.setHistory(h);
     node.setBranchDelimiter(delimiter);
  }

 */