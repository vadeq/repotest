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
import zws.util.PrintUtil;
import zws.util.StringUtil;
import zws.xml.xslt.Stylizer;

import java.rmi.RemoteException;
import java.util.*;
import java.io.ByteArrayInputStream;

import com.agile.api.*;
import com.agile.api.APIException;
import com.agile.api.IAgileSession;
import com.agile.zws.connector.AgileConnectorPub;

public class HarrisAgileSource {
    /*
}
public class HarrisAgileSource extends DatasourceBase {
  public String getType() { return "agile"; }

  public static void main(String[] args) {
      HarrisAgileSource s = new HarrisAgileSource();
      s.run();
  }
  public void run() {
      Authentication id = new Authentication();
      setURI("http://zero-888" );
      setPort(8888);
      id.setUsername("pm");
      id.setPassword("agile");
      try {
        rename("1234511-101", "1234511-new", id);
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }
  

  public void run_rev() {
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
        
        //IDataObject aRoot = createCADDocument(root.get("number"));
        //IDataObject aKid0 = createCADDocument(kid0.get("number"));
        //IDataObject aKid1 = createCADDocument(kid1.get("number"));
        //IDataObject aKid2 = createCADDocument(kid2.get("number"));
        
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
  
  
  public void rename(String name, String newName, Authentication id) throws Exception {
    try {
      login(id);
      loadAttributes();
      IItem root = findItem(name);
      IItem doc = findItem(getCADDocumentName(name));
      IAttribute att = (IAttribute) definedAttributes.get("number"); 
      Integer attID = (Integer)att.getId();
      if (null!=root) root.setValue(attID, newName);
      if (null!=doc) doc.setValue(attID, getCADDocumentName(newName));
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
      if (null==root) root = (IItem)createCADModel(r.get("number"));
      updateCADModel(root, r);
      storeBill(root, r.getSubComponents());
    }
    catch(Exception e) { e.printStackTrace(); }
    finally { logout(id.getUsername()); }
  }
  
  private void storeBill(IItem root, Collection c) throws Exception {
    IItem cadDoc = findItem(getCADDocumentName(root.getName()));
    clearSubComponents(root);
    if (null==c || c.size()==0) return;
    addSubComponent(root, getCADDocumentName(root.getName()),1);
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
      if (null==k) k = createCADModel(kid.get("number"));
      updateCADModel(k, kid);        
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
      if (null!=part && 
           (part.getAgileClass().getName().equalsIgnoreCase("CAD Model") || 
            part.getAgileClass().getName().equalsIgnoreCase("CAD Document"))) {
        deletes.add(row);
      }
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
    if (null==k) k = createCADModel(kid.get("number"));
    updateCADModel(k, kid);        
    Map p = new HashMap();
    p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, kid.get("number"));
    p.put(ItemConstants.ATT_BOM_QTY, ""+kid.getQuantity());
    bomTable.createRow(p);     
  }
  
  private void addSubComponent(IItem root, String kid, int quantity) throws Exception {
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    IItem k = findItem(kid);
    if (null==k) k = (IItem)createCADModel(kid);
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
  
  public void createCADModel(Collection documents, Authentication id) throws Exception {
      //load attributes
      Metadata data;
      try {
        login(id);
        if (null==definedAttributes) loadAttributes(); //+++cleanup - break getAttributes into 2 - load and get
        Iterator i = documents.iterator();
        while(i.hasNext()) {
          data = (Metadata)i.next();
          createCADModel(data);
        }
      }
      catch(Exception e) { e.printStackTrace(); }
      finally { logout(id.getUsername()); }
    }
    
  
  public Collection storeCADDocuments(Collection documents, Authentication id) throws Exception {
    Collection origins = new Vector();
    //load attributes
    Metadata data;
    try {
      login(id);
      if (null==definedAttributes) loadAttributes(); //+++cleanup - break getAttributes into 2 - load and get
      Iterator i = documents.iterator();
      while(i.hasNext()) {
        data = (Metadata)i.next();
        origins.add(storeCADDocument(data));
      }
    }
    catch(Exception e) { e.printStackTrace(); }
    finally { logout(id.getUsername()); }
    return origins;
  }
    
  public void storeCADModels(Collection documents, Authentication id) throws Exception {
      //load attributes
      Metadata data;
      try {
        login(id);
        if (null==definedAttributes) loadAttributes(); //+++cleanup - break getAttributes into 2 - load and get
        Iterator i = documents.iterator();
        while(i.hasNext()) {
          data = (Metadata)i.next();
          storeCADModel(data);
        }
      }
      catch(Exception e) { e.printStackTrace(); }
      finally { logout(id.getUsername()); }
    }
  
  private IItem findItem(Metadata m) throws Exception {
    if (null==m) return null;
    return findItem(m.get("number"));
  }
  
  private IItem findItem(String number) throws Exception {
    IItem x = (IItem)session.getObject(IItem.OBJECT_TYPE, number);
    return x;
  }
  
  public CADDocumentOrigin storeCADDocument(Metadata data) throws Exception {
    IDataObject x = findItem(data.get("number"));
    if (null==x) x = createCADDocument(data.get("number"));
    CADDocumentOrigin o = new CADDocumentOrigin(x.getObjectId().toString(), new GregorianCalendar());
    o.setDatasourceName(getName());
    update(x, data);
    return o;
  }

  public CADDocumentOrigin createCADDocument(Metadata data) throws Exception {
    return storeCADDocument(data);
  }

  public void updateCADDocument(Metadata data) throws Exception {
    IDataObject x = findItem(getCADDocumentName(data.get("number")));
    if (null==x) throw new NameNotFound(getCADDocumentName(data.get("number")));
    update(x, data);
  }

  private void updateCADModel(IDataObject o, Metadata data) throws Exception {
    updateCADDocument(data);
    update(o, data);
  }

  private void update(IDataObject o, Metadata data) throws Exception {
    Iterator i = data.getAttributes().keySet().iterator();
    String key;
    IAttribute att;
    Integer attID;
    while (i.hasNext()) {
      key =i.next().toString();
      if ("number".equalsIgnoreCase(key)) continue;
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
    IDataObject x = (IDataObject)session.createObject(getCADDocumentClassID(), getCADDocumentName(name));
    return x;
  }

  public IDataObject createCADDocument(Map attributes) throws Exception {
    IDataObject x = (IDataObject)session.createObject(getCADDocumentClassID(), attributes);
    return x;
  }

  
  public CADModelOrigin storeCADModel(Metadata data) throws Exception {
      IDataObject x = findItem(data.get("number"));
      if (null==x) x = createCADModel(data.get("number"));
      CADModelOrigin o = new CADModelOrigin(x.getId().toString(), new GregorianCalendar());
      updateCADModel(x, data);
      return o;
    }

    public CADModelOrigin createCADModel(Metadata data) throws Exception {
      return storeCADModel(data);
    }

    public void updateCADModel(Metadata data) throws Exception {
      IDataObject x = findItem(data.get("number"));
      if (null==x) throw new NameNotFound(data.get("number"));
      updateCADModel(x, data);
    }

    public IDataObject createCADModel(String name) throws Exception {
      IDataObject x = (IDataObject)session.createObject(getCADModelClassID(), name);
      IDataObject doc = findItem(getCADDocumentName(name));
      if (null==doc) createCADDocument(name);
      return x;
    }

    public IDataObject createCADModel(Map attributes) throws Exception {
      IDataObject x = (IDataObject)session.createObject(getCADModelClassID(), attributes);
      IDataObject doc = findItem(getCADDocumentName(x.getName()));
      if (null==doc) createCADDocument(x.getName());
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

  public void delete(BillOfMaterials bill, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole("deleting Bill: " + Names.NEW_LINE +  bill.toString());
    try {
      login(id);
      Metadata m = bill.getMetadata();
      delete(m);
    }
    catch(Exception e) { e.printStackTrace(); }
    finally { logout(id.getUsername()); }
  }

  public void delete(Metadata m) throws Exception {
      if (null==m) return;
      IItem root = findItem(m);
      delete(root);
      delete(findItem(getCADDocumentName(m)));
      if (!m.hasSubComponents()) return;
      Iterator i = m.getSubComponents().iterator();
      while(i.hasNext()) delete((Metadata)i.next());
  }

  public void delete(String partNumber, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole("deleting " + partNumber);
    try {
      login(id);
      IItem root = findItem(partNumber);
      if (null!=root) {
        if (!root.getAgileClass().getName().equalsIgnoreCase("CAD Model")) return;
        delete(root);
      }
      root = findItem(getCADDocumentName(partNumber));
      if (null!=root) {
        if (!root.getAgileClass().getName().equalsIgnoreCase("CAD Document")) return;
        delete(root);
      }
    }
    catch(Exception e) { e.printStackTrace(); }
    finally { logout(id.getUsername()); }
  }

  public void delete(IItem root ) throws Exception {
    if (null!=root) {
        if (! (root.getAgileClass().getName().equalsIgnoreCase("CAD Model") || 
               root.getAgileClass().getName().equalsIgnoreCase("CAD Document"))) return;
      root.delete();
      root.delete();
    }
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
      if (null!=errorMessage || null==result) {}//Logwriter.printOnConsole("Error Message: " + errorMessage);
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
    if (null==CADDocument_CLASS_ID) {
      IAgileClass[] classes = session.getAdminInstance().getAgileClasses(IAdmin.CONCRETE);
      for (int idx=0; idx<classes.length; idx++) {
        if (classes[idx].getName().equalsIgnoreCase("CAD Document")) CADDocument_CLASS_ID = (Integer)classes[idx].getId();
        //if (classes[idx].getName().equalsIgnoreCase("CADDoc")) CADDocument_CLASS_ID = (Integer)classes[idx].getId();
      }
    }
    return CADDocument_CLASS_ID;
  }

  private String getCADDocumentName(Metadata m) { return getCADDocumentName(m.get("number")); }
  private String getCADDocumentName(String name) { return name+"-doc"; }
  
  private Integer getCADModelClassID() throws Exception {
    if (null==CADModel_CLASS_ID) {
      IAgileClass[] classes = session.getAdminInstance().getAgileClasses(IAdmin.CONCRETE);
      for (int idx=0; idx<classes.length; idx++) {
        if (classes[idx].getName().equalsIgnoreCase("CAD Model")) CADModel_CLASS_ID = (Integer)classes[idx].getId();
        //if (classes[idx].getName().equalsIgnoreCase("CADDoc")) CADDocument_CLASS_ID = (Integer)classes[idx].getId();
      }
    }
    return CADModel_CLASS_ID;
  }

  private static Integer CADDocument_CLASS_ID = null;
  private static Integer CADModel_CLASS_ID = null;

  private void publish(String username, String password, String billSXMLpath){}  //parts/ assemblies

  public SearchAgent getSearchAgent() { return null; }
  
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
  */
}
