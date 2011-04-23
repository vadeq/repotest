package zws.datasource.agile;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 15, 2004, 11:06 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.bill.intralink.BillOfMaterials;
import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.data.*;
import zws.datasource.DatasourceBase;
import zws.datasource.agile.op.PublishBill;
import zws.exception.NameNotFound;
import zws.exception.UnsupportedOperation;
import zws.search.SearchAgent;
import zws.security.Authentication;
//import zws.util.{}//Logwriter;
import zws.util.PrintUtil;
import zws.util.StringUtil;
import zws.xml.xslt.Stylizer;

import java.rmi.RemoteException;
import java.util.*;
import java.io.ByteArrayInputStream;

import com.agile.api.*;
import com.agile.zws.connector.AgileConnectorPub;

public class AgileSource01 extends DatasourceBase {
  public String getType() { return "agile"; }

  public static void main(String[] args) {
      AgileSource01 s = new AgileSource01();
      s.run();
  }

  public void run() {
      Authentication id = new Authentication();
      setURI("http://zero-888" );
      setPort(8888);
      id.setUsername("pm");
      id.setPassword("agile");
      try {
        {}//Logwriter.printOnConsole("starting");
        login(id);
        IItem root = findItem("12345");
        {}//Logwriter.printOnConsole(root.getName() + "[" + root.getRevision() + "]");
        Map revs = root.getRevisions();
        Iterator i = revs.keySet().iterator();
        while (i.hasNext()) {
            {}//Logwriter.printOnConsole(revs.get(i.next()));
        }
        protosetLifecyclePhase(root);
        //root.setRevision("A");
        {}//Logwriter.printOnConsole(root.getName() + "[" + root.getRevision() + "]");
        {}//Logwriter.printOnConsole("done");
      }
      catch(Exception e) {
        e.printStackTrace();
      }
      finally {
        logout(id.getUsername());
      }
    }
  
  private void protosetLifecyclePhase(IItem item) throws APIException {
   ICell cell = item.getCell(ItemConstants.ATT_TITLE_BLOCK_LIFECYCLE_PHASE);
   IAgileList values = cell.getAvailableValues();
   {}//Logwriter.printOnConsole("*" + values.toString());
   values.setSelection(new Object[] { new Integer(35426)} );
   //printList(values, 0);
   {}//Logwriter.printOnConsole("*" + values.toString());
   cell.setValue(values);
  }  
  
  private void protoprintList(IAgileList list, int level) throws APIException {
    if (list != null ) {
      {}//Logwriter.printOnConsole(list.getLevelName() + ":" + list.getValue() + ":" + list.getId());
      Object[] children = list.getChildren();
      if (children != null) {
        for (int i = 0; i < children.length; ++i) {
          protoprintList((IAgileList)children[i], level + 1);
        }
      }
    }
  }
  
  public void run_replace() {
      Authentication id = new Authentication();
      setURI("http://zero-888" );
      setPort(8888);
      id.setUsername("pm");
      id.setPassword("agile");
      try {
        {}//Logwriter.printOnConsole("starting");
        replacePart("1234512-002", "newRef", id);
        {}//Logwriter.printOnConsole("done");
      }
      catch(Exception e) {
        e.printStackTrace();
      }
      finally {
        logout(id.getUsername());
      }
    }
  public void run_delete() {
      Authentication id = new Authentication();
      setURI("http://zero-888" );
      setPort(8888);
      id.setUsername("pm");
      id.setPassword("agile");
      try {
        {}//Logwriter.printOnConsole("starting");
        login(id);
        loadAttributes();
        IItem root = findItem("1234510-101");
        root.delete();
        {}//Logwriter.printOnConsole("done");
      }
      catch(Exception e) {
        e.printStackTrace();
      }
      finally {
        logout(id.getUsername());
      }
    }

  public void run_deleteBOM() {
      Authentication id = new Authentication();
      setURI("http://zero-888" );
      setPort(8888);
      id.setUsername("pm");
      id.setPassword("agile");
      Collection deletes = new Vector();
      try {
        {}//Logwriter.printOnConsole("starting");
        login(id);
        loadAttributes();
        IItem root = findItem("1234510-101");
        ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
        IRow row;
        ITwoWayIterator i =bomTable.getTableIterator();
        String partNumber;
        IItem part;
        while(i.hasNext()) {
          row = (IRow)i.next();
          partNumber = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
          part = findItem(partNumber);
          {}//Logwriter.printOnConsole(getCADDocumentClassID());
          {}//Logwriter.printOnConsole("ID: " + part.getId());
          {}//Logwriter.printOnConsole("OID: " + part.getObjectId());
          {}//Logwriter.printOnConsole("Type: " + part.getType());
          {}//Logwriter.printOnConsole("Class: " + part.getAgileClass().getName());
          {}//Logwriter.printOnConsole(row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER));
          deletes.add(row);
        }
        Iterator j = deletes.iterator();
        
        while(j.hasNext()) {
          row = (IRow) j.next();
          bomTable.removeRow(row);            
        }
        
        {}//Logwriter.printOnConsole("done");
      }
      catch(Exception e) {
        e.printStackTrace();
      }
      finally {
        logout(id.getUsername());
      }
    }

  public void run_create_bom() {
      Authentication id = new Authentication();
      setURI("http://zero-888" );
      setPort(8888);
      id.setUsername("pm");
      id.setPassword("agile");
      try {
        {}//Logwriter.printOnConsole("starting");
        login(id);
        loadAttributes();
        MetadataBase root = new MetadataBase();
        MetadataBase kid0= new MetadataBase();
        MetadataBase kid1= new MetadataBase();
        MetadataBase kid2= new MetadataBase();
        root.set("number", "zwsbill-root");
        kid0.set("number", "zwsbill-kid-0");
        kid1.set("number", "zwsbill-kid-1");
        kid2.set("number", "zwsbill-kid-2");
        /*
        IDataObject aRoot = createCADDocument(root.get("number"));
        IDataObject aKid0 = createCADDocument(kid0.get("number"));
        IDataObject aKid1 = createCADDocument(kid1.get("number"));
        IDataObject aKid2 = createCADDocument(kid2.get("number"));
        */
        IItem aRoot = findItem(root.get("number"));
        IItem aKid0 = findItem(kid0.get("number"));
        IItem aKid1 = findItem(kid1.get("number"));
        IItem aKid2 = findItem(kid2.get("number"));
        
        ITable bomTable = aKid0.getTable(ItemConstants.TABLE_BOM);
        Map p = new HashMap();
        p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, kid1.get("number"));
        p.put(ItemConstants.ATT_BOM_QTY, "1");
        IRow row = bomTable.createRow(p);
        
        {}//Logwriter.printOnConsole("done");
      }
      catch(Exception e) {
        e.printStackTrace();
      }
      finally {
        logout(id.getUsername());
      }
    }
  
  public void run_attributes() {
      Authentication id = new Authentication();
      setURI("http://zero-888" );
      setPort(8888);
      id.setUsername("pm");
      id.setPassword("agile");
      try {
        login(id);
        //IItem x = (IItem)session.getObject(IItem.OBJECT_TYPE, "ZWdS_PISTON_ASSM");
        //if (null!=x){}//Logwriter.printOnConsole(x.getName() + " " + x.getId().toString());
        //else {}//Logwriter.printOnConsole("No such part");
        //createPart("");
        Collection c = getVisibleAttributes(getPartClass());
        Iterator i = c.iterator();
        IAttribute a=null;
        while(i.hasNext()) {
          a = (IAttribute)i.next();
          {}//Logwriter.printOnConsole(a.getName() + " ["+a.getFullName().substring(0, a.getFullName().indexOf("."))+"]" + " ["+a.getId()+"]");
        }
        {}//Logwriter.printOnConsole("done");
      }
      catch(Exception e) {
        e.printStackTrace();
      }
      finally {
        logout(id.getUsername());
      }
    }
  
  
  public void store(BillOfMaterials bill, Authentication id) throws Exception {
    try {
      login(id);
      if (null==definedAttributes) loadAttributes(); //+++cleanup - break getAttributes into 2 - load and get
      Metadata r = bill.getMetadata();
      IItem root = findItem(r.get("number"));
      if (null==root) root = (IItem)createCADDocument(r.get("number"));
      updateCADDocument (root, r);
      storeBill(root, r.getSubComponents());
    }
    catch(Exception e) { e.printStackTrace(); }
    finally { logout(id.getUsername()); }
  }
  
  private void storeBill(IItem root, Collection c) throws Exception {
    clearSubComponents(root);
    if (null==c || c.size()==0) return;
    addSubComponents(root, c);
    Iterator i = c.iterator();
    MetadataSubComponent k;
    IItem kid;
    while (i.hasNext()) {
      k = (MetadataSubComponent) i.next();
      kid = findItem(k.get("number"));
      storeBill(kid, k.getSubComponents());
    }
  }
  
  private void addSubComponents(IItem root, Collection kids) throws Exception {
    if (null==kids || kids.size()==0) return;
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    Map p;
    MetadataSubComponent kid;
    IDataObject k;
    Iterator i = kids.iterator();
    while (i.hasNext()) {
      kid = (MetadataSubComponent) i.next();
      k = (IDataObject)findItem(kid.get("number"));
      if (null==k) k = createCADDocument(kid.get("number"));
      updateCADDocument(k, kid);        
      p = new HashMap();
      p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, kid.get("number"));
      p.put(ItemConstants.ATT_BOM_QTY, ""+kid.getQuantity());
      bomTable.createRow(p);
    }    
  }
  
  public void clearSubComponents(IItem root) throws Exception {
    Collection deletes = new Vector();
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    IRow row;
    ITwoWayIterator i =bomTable.getTableIterator();
    String partNumber;
    IItem part;
    while(i.hasNext()) {
      row = (IRow)i.next();
      partNumber = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
      part = findItem(partNumber);
      if (null!=part && part.getAgileClass().getName().equalsIgnoreCase("CAD Document")) deletes.add(row);
    }
    Iterator j = deletes.iterator();
      
    while(j.hasNext()) {
      row = (IRow) j.next();
      bomTable.removeRow(row);            
    }
  }

  

  private void addSubComponent(IItem root, MetadataSubComponent kid) throws Exception {
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    IDataObject k = (IDataObject)findItem(kid.get("number"));
    if (null==k) k = createCADDocument(kid.get("number"));
    updateCADDocument(k, kid);        
    Map p = new HashMap();
    p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, kid.get("number"));
    p.put(ItemConstants.ATT_BOM_QTY, ""+kid.getQuantity());
    bomTable.createRow(p);     
  }
  
  private void addSubComponent(IItem root, String kid, int quantity) throws Exception {
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    IItem k = findItem(kid);
    if (null==k) k = (IItem)createCADDocument(kid);
    Map p = new HashMap();
    p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, kid);
    p.put(ItemConstants.ATT_BOM_QTY, ""+quantity);
    bomTable.createRow(p);     
  }
  
  public void createCADDocuments(Collection documents, Authentication id) throws Exception {
    //load attributes
    Metadata data;
    try {
      login(id);
      if (null==definedAttributes) loadAttributes(); //+++cleanup - break getAttributes into 2 - load and get
      Iterator i = documents.iterator();
      while(i.hasNext()) {
        data = (Metadata)i.next();
        createCADDocument(data);
      }
    }
    catch(Exception e) { e.printStackTrace(); }
    finally { logout(id.getUsername()); }
  }
  
  public void storeCADDocuments(Collection documents, Authentication id) throws Exception {
    //load attributes
    Metadata data;
    try {
      login(id);
      if (null==definedAttributes) loadAttributes(); //+++cleanup - break getAttributes into 2 - load and get
      Iterator i = documents.iterator();
      while(i.hasNext()) {
        data = (Metadata)i.next();
        storeCADDocument(data);
      }
    }
    catch(Exception e) { e.printStackTrace(); }
    finally { logout(id.getUsername()); }
  }
    
  private IItem findItem(String number) throws Exception {
    IItem x = (IItem)session.getObject(IItem.OBJECT_TYPE, number);
    return x;
  }
  
  public void storeCADDocument(Metadata data) throws Exception {
    IDataObject x = findItem(data.get("number"));
    if (null==x) x = createCADDocument(data.get("number"));
    updateCADDocument(x, data);
  }

  public void createCADDocument(Metadata data) throws Exception {
    storeCADDocument(data);
  }

  public void updateCADDocument(Metadata data) throws Exception {
    IDataObject x = findItem(data.get("number"));
    if (null==x) throw new NameNotFound(data.get("number"));
    updateCADDocument(x, data);
  }

  private void updateCADDocument(IDataObject o, Metadata data) throws Exception {
    Iterator i = data.getAttributes().keySet().iterator();
    String key;
    IAttribute att;
    Integer attID;
    while (i.hasNext()) {
      key =i.next().toString();
      att = (IAttribute) definedAttributes.get(key.toLowerCase()); 
      {}//Logwriter.printOnConsole("att: " + key);
      if (null== att) continue;
      {}//Logwriter.printOnConsole("setting " + att.getName() + "=" + data.get(key) + "["+(Integer)att.getId()+"]");
      //add type checking - set value for each type of att
      attID = (Integer)att.getId();
      o.setValue(attID, data.get(key));
    }
  }
  
  public IDataObject createCADDocument(String name) throws Exception {
    IDataObject x = (IDataObject)session.createObject(getCADDocumentClassID(), name);
    return x;
  }

  public IDataObject createCADDocument(Map attributes) throws Exception {
    IDataObject x = (IDataObject)session.createObject(getCADDocumentClassID(), attributes);
    return x;
  }

  public IDataObject createPart(String name) throws Exception {
    IDataObject x = (IDataObject)session.createObject(ItemConstants.CLASS_PART, name);
    return x;
  }
  
  private void updatePart(IDataObject part, Integer id, String value) throws Exception {
    part.setValue(id, value);
  }
    
  private void updatePart(IDataObject part, Integer id, Integer value) throws Exception {
    part.setValue(id, value);      
  }

  public void replacePart(String partNumber, String newPartNumber, Authentication id) throws Exception {
    try {
      login(id);
      IDataObject part = findItem(partNumber);
      IDataObject newPart = findItem(newPartNumber);
      replacePart(part, newPart);
    }
    catch(Exception e) { e.printStackTrace(); }
    finally { logout(id.getUsername()); }
  }

  private void replacePart(IDataObject part, IDataObject newPart) throws Exception {
    ITable whereUsedTable = part.getTable(ItemConstants.TABLE_WHEREUSED);
    IRow row;
    ITwoWayIterator i = whereUsedTable.getTableIterator();
    String partNumber;
    IItem root;
    while(i.hasNext()) {
      row = (IRow)i.next();
      partNumber = row.getValue(ItemConstants.ATT_WHERE_USED_ITEM_NUMBER).toString();
      root = findItem(partNumber);
      replaceReference(root, part, newPart);
    }
  }

  private void replaceReference(IDataObject root, IDataObject ref, IDataObject newRef) throws Exception {
    Collection replacements = new Vector();
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    IRow row;
    ITwoWayIterator i = bomTable.getTableIterator();
    String partNumber;
    while(i.hasNext()) {
      row = (IRow)i.next();
      partNumber = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
      if (partNumber.equals(ref.getName())) replacements.add(row);
    }
    Iterator j = replacements.iterator();
    String quantity;
    Map p = new HashMap();
    while(j.hasNext()) {
      row = (IRow)j.next();
      quantity = row.getValue(ItemConstants.ATT_BOM_QTY).toString();
      bomTable.removeRow(row);
      p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, newRef.getName());
      p.put(ItemConstants.ATT_BOM_QTY, quantity);
      bomTable.createRow(p);           
    }
  }
  
  
  public void delete(String partNumber, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole("deleting " + partNumber);
    try {
      login(id);
      IItem root = findItem(partNumber);
      if (null==root) return;
      if (!root.getAgileClass().getName().equalsIgnoreCase("CAD Document")) return;
      root.delete();
      root.delete();
    }
    catch(Exception e) { e.printStackTrace(); }
    finally { logout(id.getUsername()); }
  }
  
  public Collection getAttributes(Authentication id) {
    Collection c = null;
    try {
      login(id);
      c = loadAttributes();
    }
    catch(Exception e) { e.printStackTrace(); }
    finally { logout(id.getUsername()); }
    return c;
  }
  
  public Collection loadAttributes() {
    if (null!=definedAttributes) definedAttributes.clear();
    definedAttributes = new HashMap();
    Collection c=null;
    try {
      Collection atts = getVisibleAttributes(getPartClass());
      c = new Vector();
      Iterator i = atts.iterator();
      IAttribute att=null;
      String key;
      while(i.hasNext()) {
        att = (IAttribute) i.next();
        c.add(att.getName());
        key = att.getName();
        definedAttributes.put(key.toLowerCase(), att);
      }
    }
    catch(Exception e) { e.printStackTrace(); }
    return c;
  }
  
  private IAgileClass getItemClass() throws Exception { 
      IAdmin admin = session.getAdminInstance();
      return admin.getAgileClass(ItemConstants.CLASS_ITEM_BASE_CLASS);
    }
    
  private IAgileClass getPartClass() throws Exception { 
    IAdmin admin = session.getAdminInstance();
    return admin.getAgileClass(ItemConstants.CLASS_PARTS_CLASS);
  }
    
  private Collection getVisibleAttributes(IAgileClass c) throws Exception {
    ArrayList visibleAtts = new ArrayList();
    //if (c.isAbstract()) return visibleAtts;
    visibleAtts.addAll(getVisibleAttributes(c.getTableDescriptor(TableTypeConstants.TYPE_PAGE_ONE)));
    visibleAtts.addAll(getVisibleAttributes(c.getTableDescriptor(TableTypeConstants.TYPE_PAGE_TWO)));
    visibleAtts.addAll(getVisibleAttributes(c.getTableDescriptor(TableTypeConstants.TYPE_PAGE_THREE)));
    return visibleAtts;
  }
  
  private Collection getVisibleAttributes(ITableDesc table) throws Exception {
    Collection visibleAtts = new ArrayList();
    IAttribute[] atts = null;
    if (null==table) return visibleAtts;
    atts = table.getAttributes();
    for (int idx=0; idx < atts.length; idx++) {
      if (isVisible(atts[idx])) visibleAtts.add(atts[idx]);
    }
    return visibleAtts;
  }
  
  private boolean isVisible(IAttribute att) throws APIException {
    IProperty visible = att.getProperty(PropertyConstants.PROP_VISIBLE);
    if (null==visible) return false;
    Object v = visible.getValue();
    if (null==v) return false;
    return v.toString().equalsIgnoreCase("Yes");
  }

  private boolean isRequired(IAttribute att) throws APIException {
    if(!isVisible(att)) return false;
    IProperty required = att.getProperty(PropertyConstants.PROP_REQUIRED);
    if (null==required) return false;
    Object v = required.getValue();
    if (null==v) return false;
    return v.toString().equalsIgnoreCase("Yes");
  }
  
  private void login(Authentication id) throws Exception {
  		AgileSessionFactory factory = AgileSessionFactory.getInstance(getURL());
  		HashMap params = new HashMap();
  		params.put(AgileSessionFactory.USERNAME, id.getUsername());
  		params.put(AgileSessionFactory.PASSWORD, id.getPassword());
  		session = factory.createSession(params);
  		//++ create a session for each user of this agile source
  }
  
  private void logout(String user) {
    session.close();
		//++ close a session for each user of this agile source
  }
  
  public boolean contains(String name, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".contains(origin, id)");
  }
  
  
  public String publish(Collection metadataList, Authentication id) throws Exception {
    if (null==metadataList || 0==metadataList.size()) return null;
    zws.util.PrintUtil.println("##################");
    zws.util.PrintUtil.println(id.getUsername());
    zws.util.PrintUtil.println("##################");
    zws.util.PrintUtil.println(id.getPassword());
    zws.util.PrintUtil.println("##################");
    //zws.util.PrintUtil.println(metadataList);
    Iterator i = metadataList.iterator();
    Metadata m;
    String s=null;
    while (i.hasNext()) {
      m = (Metadata) i.next();
      s = publish(m, id);
      zws.util.PrintUtil.println("-----------");
      zws.util.PrintUtil.println(s);
    }
    return s;
  }
  
  public String publish(Metadata m, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole(m);
    String xmlIn = m.toString();
    {}//Logwriter.printOnConsole("INPUT------------------------");
    {}//Logwriter.printOnConsole(xmlIn);
    {}//Logwriter.printOnConsole("INPUT------------------------");
    Stylizer styler = new Stylizer();
    styler.addProcessingInstruction(Properties.get(Names.xsltAGILE_DATA));
    ByteArrayInputStream inStream = new ByteArrayInputStream(xmlIn.getBytes("UTF-8"));
    String agileData = styler.styleXML(inStream).toString();
    PrintUtil.println("Publishing BOM into agile: ---------------");
    PrintUtil.println(agileData);
    PrintUtil.println("------------------------------------------");
    if ("true".equalsIgnoreCase(Properties.get(Names.PUBLISH_TO_AGILE_ENABLED))) {
      ByteArrayInputStream agileDataStream= new ByteArrayInputStream(agileData.getBytes("UTF-8"));
      AgileConnectorPub ac = new AgileConnectorPub();
      String errorMessage=null;
      StringBuffer result=null;
      boolean connected = ac.connect(id.getUsername(), id.getPassword());
      if (connected) {
        result = ac.publish(agileDataStream);
        if (result==null) errorMessage=ac.getErrorMessage();
      }
      else {
        errorMessage = ac.getErrorMessage();
      }
      if (null!=errorMessage || null==result) {}{}//Logwriter.printOnConsole("Error Message: " + errorMessage);
      return result.toString();
    }
    else if ("exec".equalsIgnoreCase(Properties.get(Names.PUBLISH_TO_AGILE_ENABLED))) {
      PublishBill publisher = new PublishBill();
      publisher.setBillXML(agileData);
      publisher.setAuthentication(id);
      publisher.execute();
      return publisher.getResponseXML();
    }
    else {
      {}//Logwriter.printOnConsole("-->Publishing to Agile is Disabled!");
      return  "<agile-data><metadata number=\"Publishing\" revision=\"XX\" message=\"Disabled\" message-type=\"error\"><metadata number=\"th125-04\" revision=\"XX\" message=\"th125-04 published.\" message-type=\"information\"/><metadata number=\"pwr-plate-191-04\" revision=\"XX\" message=\"pwr-plate-191-04 published.\" message-type=\"information\"/><metadata number=\"471-00013-04\" revision=\"XX\" message=\"471-00013-04 published.\" message-type=\"information\"/><metadata number=\"28-7305-04\" revision=\"XX\" message=\"28-7305-04 published.\" message-type=\"information\"/><metadata number=\"29-1564-04\" revision=\"XX\" message=\"29-1564-04 published.\" message-type=\"information\"/></metadata></agile-data>";
    }
  }

  private Integer getCADDocumentClassID() throws Exception {
      CADDocument_CLASS_ID = null;
    if (null==CADDocument_CLASS_ID) {
      IAgileClass[] classes = session.getAdminInstance().getAgileClasses(IAdmin.CONCRETE);
      for (int idx=0; idx<classes.length; idx++) {
        if (classes[idx].getName().equalsIgnoreCase("CAD Document")) CADDocument_CLASS_ID = (Integer)classes[idx].getId();
        //if (classes[idx].getName().equalsIgnoreCase("CADDoc")) CADDocument_CLASS_ID = (Integer)classes[idx].getId();
      }
    }
    return CADDocument_CLASS_ID;
  }

  private static Integer CADDocument_CLASS_ID = null;
  
  private void publish(String username, String password, String billSXMLpath){}  //parts/ assemblies

  public SearchAgent materializeSearchAgent() { return null; }
  public SearchAgent materializeLatestSearchAgent() { return null; }
  public SearchAgent materializeLatestRevSearchAgent() { return null; }
  
  public Metadata findMetadataForPackage(Metadata data, boolean includeHistory, Authentication id) throws Exception { return data; }
  
  public String getURL() { return uri+":"+port+"/Agile"; }
  
  public String getURI() { return uri; }
  public void setURI(String s) { uri=s; }
  public int getPort() { return port; }
  public void setPort(int i) { port=i; }
  
  public String getDefaultUsername() { return defaultUsername; }
  public void setDefaultUsername(String s) { defaultUsername=s; }
  public String getDefaultPassword() { return defaultPassword; }
  public void setDefaultPassword(String s) { defaultPassword=s; } 

  private IAgileSession session=null;

  private String uri=null;
  private int port=8888;
  private String defaultPassword=null;
  private String defaultUsername=null;
  private Map definedAttributes = null;
}
