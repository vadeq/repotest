package zws.datasource.intralink;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 14, 2004, 12:43 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.data.*;
import zws.database.Oracle8i;
import zws.exception.NameNotFound;
import zws.origin.*;

import java.io.File;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataServer {
    /*
  public DataServer(){};
  public DataServer(IntralinkSource source) { intralinkSource = source; }
  private int toInt(String s) { return Integer.valueOf(s).intValue(); }
  
  public long lookupTimeOfCreation(IntralinkOrigin o) throws Exception {
    String sql="SELECT PIV.CREATEDON FROM PDM.PDM_PRODUCTITEM PI, PDM.PDM_BRANCH BR, PDM.PDM_PRODUCTITEMVERSION PIV where PI.PIID=BR.PIID AND BR.BRID=PIV.BRID AND PI.PINAME=? AND BR.BRPATH=? AND PIV.PIVREV=? AND PIV.PIVVER=?";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 0");    
    PreparedStatement s = db().prepareStatement(sql);
    s.setString(1,o.getName());
    s.setString(2,o.getBranch());
    s.setString(3,o.getRevision());
    s.setInt(4,o.getVersion());
    ResultSet r = s.executeQuery();
    if (!r.next()) return o.getTimeOfCreationInMillis();
    String date = r.getString(CREATEDON);
    db().release(s);
    return parseDate(date);
  }
  
  public Metadata find(Origin o) throws Exception { return find(o.getName(), ((IntralinkOrigin)o).getBranch(), ((IntralinkOrigin)o).getRevision(), ((IntralinkOrigin)o).getVersion()); }
  public Metadata find(String name, String branch, String revision, int version) throws Exception {
    String sql="select pi.piid as pi, piv.pivid as piv, pi.pistatus as "+STAT+", pi.piname as "+NAME+", br.brpath as "+BR+", piv.pivrev as "+REV+", piv.pivver as "+VER+", rl.rlname as "+RELEASELEVEL+", fol.folname as "+FOLDERNAME+", fol.folpath as "+FOL+", piv.createdby as "+CREATEDBY+", piv.createdon as "+CREATEDON+", piv.srcpivid as parent, piv.primtxnid as TXNID from pdm.pdm_folder fol, pdm.pdm_productitem pi, pdm.pdm_branch br, pdm.pdm_productitemversion piv, pdm.pdm_releaselevel rl where pi.folid=fol.folid and pi.piid=br.piid AND br.brid=piv.brid and piv.rlid=rl.rlid and PI.PINAME=? AND BR.BRPATH=? AND PIV.PIVREV=? AND PIV.PIVVER=?";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 1");    
    {}//Logwriter.printOnConsole(sql + name + branch + revision + version);
    PreparedStatement s = db().prepareStatement(sql);
    s.setString(1,name);
    s.setString(2,branch);
    s.setString(3,revision);
    s.setInt(4,version);
    ResultSet r = s.executeQuery();
    if (!r.next()) throw new NameNotFound(name + "["+branch+":"+revision+version+"]");
    Metadata m = unmarshall(r);
    db().release(s);
    return m;
  }

  public boolean mayHaveSubComponents(Metadata data) {
    String name;
    try {name = data.getName(); } //temp hack
    catch (Exception e) { return false; }
    if (name==null || "".equals(name)) return false;
    name = name.toLowerCase();
    if (name.endsWith(".prt")) return false;
    return true;
  }
  public boolean hasSubComponents(Metadata parent) throws Exception {
    if (null==parent.get(PIV)) {
      Metadata parentPIV = find(parent.getOrigin()); 
      parent.merge(parentPIV, false);
    }
    String sql = "select g.piid as "+PIID+", g.dgdepcount as "+COUNT+" from PDM.PDM_DEPENDENCYGRAPH G where G.DGDEPTYPE=2 AND G.PIVID=?";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 2");    
    PreparedStatement s = db().prepareStatement(sql);
    int parentPIV = Integer.valueOf(parent.get(PIV)).intValue();
    s.setInt(1, parentPIV);
    ResultSet r = s.executeQuery();
    boolean hasKids= false;
    if (r.next()) hasKids = true;
    db().release(s);
    return hasKids;
  }
  
  public Metadata bindAsStoredSubComponents(Origin o) throws Exception { return bindSubComponents(o, true); }
  public Metadata bindLatestSubComponents(Origin o) throws Exception { return bindSubComponents(o, false); }
  public Metadata bindSubComponents(Origin o, boolean asStored) throws Exception {
    Metadata parent = find(o);
    bindSubComponents(parent, asStored);
    return parent;
  }
  public void bindSubComponents(Metadata parent, boolean asStored) throws Exception {
    
    bindFirstLevelSubComponents(parent, asStored);
    //  Need to detect cycle
    //if (parent.hasSubComponents()) {
    //  Iterator i = parent.getSubComponents().iterator();
    //  MetadataSubComponent sub;
    //  while (i.hasNext()) {
    //    sub = (MetadataSubComponent)i.next();
    //    if (mayHaveSubComponents(sub)) bindSubComponents(sub, asStored);
    //  }
   // }
    
  }
  public void bindFirstLevelSubComponents(Metadata parent, boolean asStored) throws Exception {
    Collection kids;
    if (asStored) kids = findAsStoredSubComponents(parent);
    else kids = findLatestSubComponents(parent);
    Iterator i = kids.iterator();
    MetadataSubComponent kid;
    while (i.hasNext()) {
      kid = (MetadataSubComponent) i.next();
      if (!parent.hasAncestorNamed(kid.getName())) parent.addSubComponent(kid);
      else {}//Logwriter.printOnConsole("Discovered cycle for subcomponent: " + kid);
    }
  }
  
  public Collection findAsStoredSubComponents(Metadata parent) throws Exception {
    if (null==parent.get(PIV) || null==parent.get(TXNID)){
      Metadata parentPIV = find(parent.getOrigin()); 
      parent.merge(parentPIV, false);
    }
    Collection kids = findSubComponentsInTransaction(parent);
    String sql = "SELECT unique g.dgdepcount as "+COUNT+", pi.piid as "+PI+", piv.pivid as "+PIV+", piv.primtxnid as "+TXNID+", PIV.CREATEDON as "+CREATEDON+", br.brpath as "+BRANCH+", PI.PINAME as "+NAME+", PIV.PIVREV as "+REV+", PIV.PIVVER as "+VER+", fol.folpath as "+FOL+", rl.rlname as "+RELEASELEVEL+", pi.pistatus as "+STAT+", piv.createdby as "+CREATEDBY+" FROM pdm.pdm_folder fol, pdm.pdm_releaselevel rl, PDM.PDM_DEPENDENCYGRAPH G, pdm.pdm_pivsectxn tx, PDM.PDM_PRODUCTITEM PI, PDM.PDM_BRANCH BR, PDM.PDM_PRODUCTITEMVERSION PIV where G.dgdeptype=2 and tx.pivid=piv.pivid AND PI.PIID=BR.PIID AND BR.BRID=PIV.BRID AND pi.folid=fol.folid AND piv.rlid=rl.rlid AND g.pivid=? AND tx.pivtxnid=?"; 
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 3");    
    PreparedStatement s = db().prepareStatement(sql);
    int parentPIV = Integer.valueOf(parent.get(PIV)).intValue();
    int txnID = Integer.valueOf(parent.get(TXNID)).intValue();
    s.setInt(1, parentPIV);
    s.setInt(2, txnID);
    ResultSet r = s.executeQuery();
    MetadataSubComponentBase dep=null;
    Metadata data;
    while (r.next()) {
      data = unmarshall(r);
      if (!data.getName().equals(parent.getName())) {
        dep = new MetadataSubComponentBase(data);
        dep.setQuantity(Integer.valueOf(data.get(MetadataSubComponent.QUANTITY)).intValue());
        kids.add(dep);
      }
    }
    db().release(s);
    //if dependency count < kids.count, fill missing kids automatically
    return kids;
  }
  
  public Collection findLatestSubComponents(Metadata parent) throws Exception {
    if (null==parent.get(PIV)) {
      Metadata parentPIV = find(parent.getOrigin()); 
      parent.merge(parentPIV, false);
    }
    String sql = "select g.piid as "+PIID+", g.dgdepcount as "+COUNT+" from PDM.PDM_DEPENDENCYGRAPH G where G.DGDEPTYPE=2 AND G.PIVID=?";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 4");    
    PreparedStatement s = db().prepareStatement(sql);
    int parentPIV = Integer.valueOf(parent.get(PIV)).intValue();
    s.setInt(1, parentPIV);
    ResultSet r = s.executeQuery();
    Map piValues = new HashMap();
    String pi, count;
    while (r.next()) {
      pi=""+r.getInt(PIID);
      count=""+r.getInt(COUNT);
      piValues.put(pi, count);
    }
    db().release(s);
    
    Iterator i = piValues.keySet().iterator();
    MetadataSubComponentBase dep=null;
    Metadata data;
    int piv;
    Collection kids = new Vector();
    while (i.hasNext()) {
      pi = (String)i.next();
      count = (String)piValues.get(pi);
      piv = findLatestPIVForPI(Integer.valueOf(pi).intValue());
      data = find(piv);
      if (!data.getName().equals(parent.getName())) {
        dep = new MetadataSubComponentBase(data);
        dep.setQuantity(Integer.valueOf(count).intValue());
        kids.add(dep);
      }
    }
    return kids;
  }
  
  private Collection findSubComponentsInTransaction(Metadata parent) throws Exception {
    String sql= "select unique g.dgdepcount as "+COUNT+", pi.piid as pi, piv.pivid as piv, pi.pistatus as "+STAT+", pi.piname as "+NAME+", br.brpath as "+BR+", piv.pivrev as "+REV+", piv.pivver as "+VER+", rl.rlname as "+RELEASELEVEL+", fol.folname as "+FOLDERNAME+", fol.folpath as "+FOL+", piv.createdby as "+CREATEDBY+", piv.createdon as "+CREATEDON+", piv.srcpivid as parent, piv.primtxnid as TXNID from PDM.PDM_DEPENDENCYGRAPH G, pdm.pdm_folder fol, pdm.pdm_productitem pi, pdm.pdm_branch br, pdm.pdm_productitemversion piv, pdm.pdm_releaselevel rl where pi.folid=fol.folid and pi.piid=br.piid AND br.brid=piv.brid and piv.rlid=rl.rlid and pi.piid=g.piid and piv.primtxnid=? and piv.pivid!=?";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 5");    
    PreparedStatement s = db().prepareStatement(sql);
    int parentPIV = Integer.valueOf(parent.get(PIV)).intValue();
    int txnID = Integer.valueOf(parent.get(TXNID)).intValue();
    s.setInt(1, txnID);
    s.setInt(2, parentPIV);
    ResultSet r = s.executeQuery();
    MetadataSubComponentBase dep=null;
    Metadata data;
    Collection kids = new Vector();
    while (r.next()) {
      data = unmarshall(r);
      dep = new MetadataSubComponentBase(data);
      dep.setQuantity(Integer.valueOf(data.get(MetadataSubComponent.QUANTITY)).intValue());
      kids.add(dep);
    }
    db().release(s);
    //if dependency count < kids.count, fill missing kids automatically
    return kids;
  }

  public Metadata find(int piv) throws Exception {
    String sql="select pi.piid as pi, piv.pivid as piv, pi.pistatus as "+STAT+", pi.piname as "+NAME+", br.brpath as "+BR+", piv.pivrev as "+REV+", piv.pivver as "+VER+", rl.rlname as "+RELEASELEVEL+", fol.folname as "+FOLDERNAME+", fol.folpath as "+FOL+", piv.createdby as "+CREATEDBY+", piv.createdon as "+CREATEDON+", piv.srcpivid as parent, piv.primtxnid as TXNID from pdm.pdm_folder fol, pdm.pdm_productitem pi, pdm.pdm_branch br, pdm.pdm_productitemversion piv, pdm.pdm_releaselevel rl where pi.folid=fol.folid and pi.piid=br.piid AND br.brid=piv.brid and piv.rlid=rl.rlid and piv.pivid=?";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 6");    
    {}//Logwriter.printOnConsole(sql + piv);
    PreparedStatement s = db().prepareStatement(sql);
    s.setInt(1,piv);
    ResultSet r = s.executeQuery();
    if (!r.next()) throw new NameNotFound("PIV="+piv);
    Metadata m = unmarshall(r);
    db().release(s);
    return m;
  }
  public int findPI(String name) throws Exception {
    String sql="SELECT PIID FROM PDM.PDM_PRODUCTITEM PI WHERE PI.PINAME=?";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 7");    
    {}//Logwriter.printOnConsole(sql + name);
    PreparedStatement s = db().prepareStatement(sql);
    s.setString(1,name);
    ResultSet r = s.executeQuery();
    if (!r.next()) throw new NameNotFound(name);
    int pi = r.getInt(PIID);
    db().release(s);
    return pi;
  }
  public int findPIV(Origin o) throws Exception { return findPIV(o.getName(), ((IntralinkOrigin)o).getBranch(), ((IntralinkOrigin)o).getRevision(), ((IntralinkOrigin)o).getVersion()); }
  public int findPIV(String name, String branch, String revision, int version) throws Exception {
    String sql="SELECT PIV.PIVID as PIVID FROM PDM.PDM_PRODUCTITEM PI, PDM.PDM_BRANCH BR, PDM.PDM_PRODUCTITEMVERSION PIV where PI.PIID=BR.PIID AND BR.BRID=PIV.BRID AND PI.PINAME=? AND BR.BRPATH=? AND PIV.PIVREV=? AND PIV.PIVVER=?";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 8");    
    PreparedStatement s = db().prepareStatement(sql);
    s.setString(1,name);
    s.setString(2,branch);
    s.setString(3,revision);
    s.setInt(4,version);
    ResultSet r = s.executeQuery();
    if (!r.next()) throw new NameNotFound(name + "["+branch+":"+revision+version+"]");
    int id = r.getInt(PIVID);
    db().release(s);
    return id;
  }
  public ArrayList findPIVsForName(String name) throws Exception {
    String sql="SELECT PIV.PIVID as PIVID FROM PDM.PDM_PRODUCTITEM PI, PDM.PDM_BRANCH BR, PDM.PDM_PRODUCTITEMVERSION PIV where PI.PIID=BR.PIID AND BR.BRID=PIV.BRID AND PI.PINAME=?";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 9");    
    PreparedStatement s = db().prepareStatement(sql);
    s.setString(1,name);
    ResultSet r = s.executeQuery();
    ArrayList pivs=new ArrayList();
    while(r.next())pivs.add(new Integer(r.getInt(PIVID)));
    db().release(s);
    return pivs;
  }
  
  public ArrayList findPIVsForPI(int pi) throws Exception {
    String sql="SELECT PIV.PIVID as PIVID FROM PDM.PDM_PRODUCTITEM PI, PDM.PDM_BRANCH BR, PDM.PDM_PRODUCTITEMVERSION PIV where PI.PIID=BR.PIID AND BR.BRID=PIV.BRID AND PI.PIID=?";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 10");    
    PreparedStatement s = db().prepareStatement(sql);
    s.setInt(1,pi);
    ResultSet r = s.executeQuery();
    ArrayList pivs=new ArrayList();
    while(r.next())pivs.add(new Integer(r.getInt(PIVID)));
    db().release(s);
    return pivs;
  }
  public int findLatestPIVForPI(int pi) throws Exception {
    String sql="select * from (SELECT PIV.CREATEDON, PI.PIID, PIV.PIVID as PIVID FROM PDM.PDM_PRODUCTITEM PI, PDM.PDM_BRANCH BR, PDM.PDM_PRODUCTITEMVERSION PIV where PI.PIID=BR.PIID AND BR.BRID=PIV.BRID AND PI.PIID=? order by PIV.CREATEDON desc) where rownum=1";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 11");    
    //ORACLE DATE SYNTAX (FYI):TO_DATE('2004-08-22 16:00:00','YYYY-MM-DD HH24:MI:SS'
    PreparedStatement s = db().prepareStatement(sql);
    s.setInt(1,pi);
    ResultSet r = s.executeQuery();
    if (!r.next()) throw new NameNotFound("PI = "+ pi);
    int id = r.getInt(PIVID);
    db().release(s);
    return id;
  }
  public int findLatestPIVForPI(int pi, Calendar uptoDate) throws Exception {
    Calendar tillDate = new GregorianCalendar();
    tillDate.setTimeInMillis(uptoDate.getTimeInMillis()+8);//help handle milliseond resulution
    String date = tillDate.get(Calendar.YEAR)+"-"+ (tillDate.get(Calendar.MONTH)+1) +"-"+tillDate.get(Calendar.DATE)+" "+tillDate.get(Calendar.HOUR_OF_DAY)+":"+tillDate.get(Calendar.MINUTE)+":"+tillDate.get(Calendar.SECOND);
    String sql="select * from (SELECT PIV.CREATEDON, PI.PIID, PIV.PIVID as PIVID FROM PDM.PDM_PRODUCTITEM PI, PDM.PDM_BRANCH BR, PDM.PDM_PRODUCTITEMVERSION PIV where PI.PIID=BR.PIID AND BR.BRID=PIV.BRID AND PI.PIID=? AND PIV.CREATEDON<TO_DATE('"+date+"','YYYY-MM-DD HH24:MI:SS') order by PIV.CREATEDON desc) where rownum=1";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 12");    
    PreparedStatement s = db().prepareStatement(sql);
    s.setInt(1,pi);
    ResultSet r = s.executeQuery();
    if (!r.next()) throw new NameNotFound("PI = "+ pi);
    int id = r.getInt(PIVID);
    db().release(s);
    return id;
  }
  public int findLatestPIVForName(String name) throws Exception {
    String sql="select * from (SELECT PIV.CREATEDON, PI.PIID, PIV.PIVID as PIVID FROM PDM.PDM_PRODUCTITEM PI, PDM.PDM_BRANCH BR, PDM.PDM_PRODUCTITEMVERSION PIV where PI.PIID=BR.PIID AND BR.BRID=PIV.BRID AND PI.PINAME=? order by PIV.CREATEDON desc) where rownum=1";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 13");    
    //ORACLE DATE SYNTAX (FYI):TO_DATE('2004-08-22 16:00:00','YYYY-MM-DD HH24:MI:SS'
    PreparedStatement s = db().prepareStatement(sql);
    s.setString(1,name);
    ResultSet r = s.executeQuery();
    if (!r.next()) throw new NameNotFound(name);
    int id = r.getInt(PIVID);
    db().release(s);
    return id;
  }
  public int findLatestPIVForName(String name, Calendar uptoDate) throws Exception {
    //ORACLE DATE SYNTAX (FYI):TO_DATE('2004-08-22 16:00:00','YYYY-MM-DD HH24:MI:SS'
    Calendar tillDate = new GregorianCalendar();
    tillDate.setTimeInMillis(uptoDate.getTimeInMillis()+8);//help handle milliseond resulution
    String date = tillDate.get(Calendar.YEAR)+"-"+ (tillDate.get(Calendar.MONTH)+1) +"-"+tillDate.get(Calendar.DATE)+" "+tillDate.get(Calendar.HOUR_OF_DAY)+":"+tillDate.get(Calendar.MINUTE)+":"+tillDate.get(Calendar.SECOND);
    String sql="select * from (SELECT PIV.CREATEDON, PI.PIID, PIV.PIVID as PIVID FROM PDM.PDM_PRODUCTITEM PI, PDM.PDM_BRANCH BR, PDM.PDM_PRODUCTITEMVERSION PIV where PI.PIID=BR.PIID AND BR.BRID=PIV.BRID AND PI.PINAME=? AND PIV.CREATEDON<TO_DATE('"+date+"','YYYY-MM-DD HH24:MI:SS') order by PIV.CREATEDON desc) where rownum=1";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 14");    
    PreparedStatement s = db().prepareStatement(sql);
    s.setString(1,name);
    ResultSet r = s.executeQuery();
    if (!r.next()) throw new NameNotFound(name);
    int id = r.getInt(PIVID);
    db().release(s);
    return id;
  }
  private Metadata getMetadataForPIV(int piv) throws Exception {
    String sql = "select pi.piid as pi, piv.pivid as piv, pi.pistatus as "+STAT+", pi.piname as "+NAME+", br.brpath as "+BR+", piv.pivrev as "+REV+", piv.pivver as "+VER+", rl.rlname as "+RELEASE_LEVEL+", fol.folname as "+FOLDERNAME+", fol.folpath as "+FOL+", piv.createdby as "+CREATEDBY+", piv.createdon as "+CREATEDON+", piv.srcpivid as parent from pdm.pdm_folder fol, pdm.pdm_productitem pi, pdm.pdm_branch br, pdm.pdm_productitemversion piv, pdm.pdm_releaselevel rl where pi.folid=fol.folid and pi.piid=br.piid AND br.brid=piv.brid and piv.rlid=rl.rlid and piv.pivid=?";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 15");    
    {}//Logwriter.printOnConsole(sql + piv);
    PreparedStatement s = db().prepareStatement(sql);
    s.setInt(1, piv);
    ResultSet r = s.executeQuery();
    if (!r.next()) throw new NameNotFound("PIV="+piv);
    Metadata m = unmarshall(r);
    db().release(s);
    return m;
  }
  private int getTransactionIDForPIV(int piv) throws Exception {
    String sql="select piv.primtxnid as TXNID from pdm.pdm_productitemversion piv where piv.pivid=?";
{}//Logwriter.printOnConsole ("@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql+" 16");    
    {}//Logwriter.printOnConsole(sql + piv);
    PreparedStatement s = db().prepareStatement(sql);
    s.setString(1,""+piv);
    ResultSet r = s.executeQuery();
    if (!r.next()) throw new NameNotFound("PIV="+piv);
    int id =  r.getInt(TXNID);
    db().release(s);
    return id;
  }
  public String getTNSNamesPath(){ return tnsNamesOraPath; }
  public void setTNSNamesPath(String s) throws Exception { tnsNamesOraPath=s; }
  public String getUsername() { return username; }
  public void setUsername(String s) throws Exception { username=s; }
  public String getPassword() { return password; }
  public void setPassword(String s) throws Exception { password=s; }
  
  public void unmountDataServer() { 
    try {db().closeAllConnections();} catch (Exception e) {;} 
    dataServerDB=null; 
  }

  private Oracle8i db() throws Exception {
    if (null==dataServerDB) mountDataServer();
    return dataServerDB;
  }
  private synchronized void mountDataServer() throws Exception {
    File f = new File(tnsNamesOraPath);
    char buffer[] = new char[2048];
    FileReader tnsNames = new FileReader(f);
	  tnsNames.read(buffer);
	  tnsNames.close();
    
    String dbConfig = new String(buffer);
    dbConfig = dbConfig.toUpperCase();
    int hostIdx = dbConfig.indexOf('=', dbConfig.indexOf(HOST))+1;
    int portIdx= dbConfig.indexOf('=', dbConfig.indexOf(PORT))+1;
    int sidIdx= dbConfig.indexOf('=', dbConfig.indexOf(SID))+1;
    String host = dbConfig.substring(hostIdx,dbConfig.indexOf(')',hostIdx+1)).trim();
    String port = dbConfig.substring(portIdx,dbConfig.indexOf(')',portIdx+1)).trim();
    String sid = dbConfig.substring(sidIdx,dbConfig.indexOf(')',sidIdx+1)).trim();
    dataServerDB = new Oracle8i();
    dataServerDB.setName(intralinkSource.getName());
    dataServerDB.setUsername(username);
    dataServerDB.setPassword(password);
    dataServerDB.setHost(host);
    dataServerDB.setPort(port);
    dataServerDB.setSID(sid);
  }
  
  private Collection unmarshallList(ResultSet r)  throws Exception {
    Collection c = new Vector();
    while (r.next()) c.add(unmarshall(r));
    return c;
  }
  
  public String getDateFormat() { return dateFormat; }
  public void setDateFormat(String s) { dateFormat=s; }
  
  public long parseDate(String formattedDate) {
    if (null==formattedDate || "".equals(formattedDate)) return 0;
    SimpleDateFormat dateFormatter = new SimpleDateFormat(getDateFormat());
    String d = formattedDate.replace(',', ' ');
    return dateFormatter.parse(d, new ParsePosition(0)).getTime();
  }
  
  private MetadataBase unmarshall(ResultSet r) throws Exception {
    MetadataBase data = new MetadataBase();
    String field, val;
    int count = r.getMetaData().getColumnCount();
    for (int i = 1; i <=count; i++){
      field = r.getMetaData().getColumnName(i);
      if (r.getMetaData().getColumnType(i)==java.sql.Types.INTEGER) data.set(field, ""+r.getInt(i));
      else {
        val = r.getString(i);
        if (null==val || "null".equals(val.trim().toLowerCase())) val="";
        else val=val.trim();
        if (NAME.equalsIgnoreCase(field)) field=NAME;
        if (BR.equalsIgnoreCase(field)) field=BRANCH;
        if (REV.equalsIgnoreCase(field)) field=REVISION;
        if (VER.equalsIgnoreCase(field)) field=VERSION;
        if (CREATEDON.equals(field)) field=CREATED_ON; 
        if (RELEASELEVEL.equals(field)) field=RELEASE_LEVEL;
        if (FOL.equalsIgnoreCase(field)) field=FOLDER;
        if (STAT.equalsIgnoreCase(field)) field=STATUS;
        if (CREATEDBY.equals(field)) field=CREATED_BY; 
        if (FOLDERNAME.equals(field)) field="Folder-Name";
        if (COUNT.equalsIgnoreCase(field)) field=MetadataSubComponent.QUANTITY;
        data.set(field, val);
      }
    }
    StringBuffer s = new StringBuffer();
    s.append(Server.getDomainName()).append(delim).append(Server.getName()).append(delim).append(Origin.FROM_ILINK).append(delim).append(intralinkSource.getName()).append(delim).append(parseDate(data.get(CREATED_ON))).append(delim).append(data.get(BRANCH)).append(delim).append(data.get(REVISION)).append(delim).append(data.get(VERSION)).append(delim).append(data.get(NAME));
    Origin o = OriginMaker.materialize(s.toString());
    data.setOrigin(o);
    return data;
  }

  private Oracle8i dataServerDB=null;
  private String tnsNamesOraPath=null;
  private String username="system";
  private String password="manager";
  private IntralinkSource intralinkSource = null;
  private String dateFormat = "y-M-d H:m:s.S";
  
  private static String delim=Origin.delim;
  private static String BLANK = "";

  private static String HOST = "HOST";
  private static String PORT = "PORT";
  private static String SID = "SID";

  private static String PI = "PI";
  private static String PIV = "PIV";
  private static String PIID = "PIID";
  private static String PIVID = "PIVID";
  private static String TXNID = "TXNID";
  private static String PARENT ="PARENT";
  private static String CREATED ="CREATED";
  
  public static String NAME = IntralinkSource.NAME;
  public static String BRANCH = IntralinkSource.BRANCH;
  public static String REVISION = IntralinkSource.REVISION;
  public static String VERSION = IntralinkSource.VERSION;
  public static String CREATED_ON = IntralinkSource.CREATED_ON;
  public static String CREATED_BY = IntralinkSource.CREATED_BY;
  public static String RELEASE_LEVEL = IntralinkSource.RELEASE_LEVEL;
  public static String FOLDER = IntralinkSource.FOLDER;
  public static String GENERIC = IntralinkSource.GENERIC;
  public static String INSTANCE = IntralinkSource.INSTANCE;
  public static String STATUS= "Status";
  private static String DOT = ".";

  public static String BR= "BRANCH";
  public static String REV= "REVISION";
  public static String VER= "VERSION";
  public static String FOL= "FOLDER";
  public static String CREATEDON = "CREATEDON";
  public static String CREATEDBY = "CREATEDBY";
  public static String RELEASELEVEL = "RELEAESELEVEL";
  public static String FOLDERNAME= "FOLDERNAME";  
  public static String STAT= "STATUS";
  public static String COUNT= "COUNT";
  */
}
/*
  public Metadata getTopLevelSubComponents(Origin o) throws Exception {
    Metadata parent = find(o); 
    Iterator i = getDependencyPIs(parent).values().iterator();
    while (i.hasNext()) parent.addSubComponent((MetadataSubComponent)i.next());
    return parent;
  }
  private Collection getMetadataDependenciesForTransaction(int parentPIV, int parentTXNID) throws Exception {
    String sql = "select pi.piid as pi, piv.pivid as piv, pi.pistatus as "+STAT+", pi.piname as "+NAME+", br.brpath as "+BR+", piv.pivrev as "+REV+", piv.pivver as "+VER+", rl.rlname as "+RELEASELEVEL+", fol.folname as "+FOLDERNAME+", fol.folpath as "+FOL+", piv.createdby as "+CREATEDBY+", piv.createdon as "+CREATEDON+", piv.srcpivid as parent, dep.dgtopistoredname as storedname, dep.dgdepcount as "+COUNT+", dep.dgdepclass, dep.dgdeptype, piv.primtxnid as TXNID from pdm.pdm_folder fol, pdm.pdm_dependencygraph dep, pdm.pdm_productitem pi, pdm.pdm_branch br, pdm.pdm_productitemversion piv, pdm.pdm_releaselevel rl where pi.folid=fol.folid AND pi.piid=br.piid AND br.brid=piv.brid and dep.dgdeptype=2 and dep.piid=pi.piid and piv.rlid=rl.rlid and dep.pivid=? and piv.primtxnid=?";
    {}//Logwriter.printOnConsole(sql + parentPIV + parentTXNID);
    Connection c = dataServerDB.connect();
    PreparedStatement statement = c.prepareStatement(sql);
    statement.setInt(1, parentPIV);
    statement.setInt(2, parentTXNID);
    ResultSet r = statement.executeQuery();
    Collection res = unmarshallList(r);
    r.close();
    dataServerDB.release(c);
    return res;
  }
  private Map getDependencyPIs(Metadata parent) throws Exception {
    String sql = "select piid from pdm.pdm_dependencygraph where dgdeptype=2 AND pivid=?";
    Connection con = dataServerDB.connect();
    PreparedStatement statement = con.prepareStatement(sql);
    int parentPIV = Integer.valueOf(parent.get(PIV)).intValue();
    {}//Logwriter.printOnConsole(sql + parentPIV);
    statement.setInt(1, parentPIV);
    ResultSet r = statement.executeQuery();
    MetadataSubComponentBase dep=null;
    Map deps = new HashMap();
    int pi;
    while (r.next()) { //prepare list of subcomponents - indexed by PI
      pi = r.getInt(PIID);
      dep = (MetadataSubComponentBase)deps.get(""+pi);
      if (null!=dep) dep.setQuantity(dep.getQuantity()+1);
      else {
        dep = new MetadataSubComponentBase();
        dep.setQuantity(1);
      }
      deps.put(""+pi, dep);
    }
    r.close();
    dataServerDB.release(con);  //top level dependencies have been indexed and an empty MetadataSubComponent is ready to be filled in with the proper component
    Collection c;
    Metadata p = parent;
    String sourcePIV=p.get(PIV);
    String parentTXNID;
    do {
      if (!"".equals(sourcePIV)&& !sourcePIV.equals(p.get(PIV))) p = find(Integer.valueOf(sourcePIV).intValue());
      parentTXNID = p.get(TXNID);
      c=getMetadataDependenciesForTransaction(Integer.valueOf(sourcePIV).intValue(), Integer.valueOf(parentTXNID).intValue());
      fillAsStoredDependencies(deps, c); //print deps here - should be replaced
      sourcePIV = p.get(PARENT);
      if ("".equals(sourcePIV)) sourcePIV=null;
    }
    while (null!=sourcePIV && !dependenciesComplete(deps));
    Iterator i =deps.keySet().iterator();
    Metadata data;
    String key;
    while (i.hasNext()) {
      key = (String)i.next();
      pi = Integer.valueOf(key).intValue();
      dep = (MetadataSubComponentBase)deps.get(key);
      if (null==dep.getComponent()){
        data = find(findLatestPIVForPI(pi, parent.getOrigin().getTimeOfCreation()));
        if (data==null){
          data = new MetadataBase();
          data.set(NAME, "MissingComponent");
        }
        dep.setComponent(data);
      }
      //else if (dep.get(PIV)!=null && !dep.get(PIV).equals(""+findLatestPIVForPI(pi, parent.getOrigin().getTimeOfCreation()))) {
      //  data = find(findLatestPIVForPI(pi, parent.getOrigin().getTimeOfCreation()));
      //  dep.setComponent(data);
      //}
    }
    return deps;
  }
  private void fillAsStoredDependencies(Map deps, Collection data) throws Exception {
    if (null==data) return;
    MetadataSubComponentBase sub;
    Metadata dep;
    Iterator i = data.iterator();
    {}//Logwriter.printOnConsole("+++++++++++++++++");
    while (i.hasNext()) {
      dep = (Metadata)i.next();
      sub = (MetadataSubComponentBase)deps.get(dep.get(PI));
      if (null!=sub)sub.setComponent(dep);
      else throw new Exception("FATAL: PI not found in dependencies!");
      {}//Logwriter.printOnConsole("filling " + dep.getName());
    }
  }
  private boolean dependenciesComplete(Map deps) throws Exception {
    MetadataSubComponentBase sub;
    Iterator i = deps.values().iterator();
    while(i.hasNext()) {
      sub = (MetadataSubComponentBase)i.next();
      if (null==sub.getComponent()) return false;
    }
    return true;
  }
*/