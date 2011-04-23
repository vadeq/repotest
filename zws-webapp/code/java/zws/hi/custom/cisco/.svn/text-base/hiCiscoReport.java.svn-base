package zws.hi.custom.cisco; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.Synchronizer;
import zws.data.*;
import zws.origin.*;
import zws.application.Properties;
import zws.AgileAccess;
import zws.IntralinkAccess;
import zws.application.server.webapp.Names;
import zws.application.Properties;
import zws.bill.intralink.BillOfMaterials;
import zws.hi.report.MetadataAdapter;
import zws.hi.report.hiReport;
import zws.synchronization.SynchronizationRecord;
import zws.util.*;
import zws.util.MapUtil;
import zws.util.StringPair;
import zws.util.comparator.metadata.PartNumberOrder;

import java.util.*;



public class hiCiscoReport extends hiReport {
  public String getSelectedReportName() { return Properties.get("custom-report-cisco-library"); }
  protected MetadataAdapter createNewMetadataAdapter() { return new CiscoMetadataAdapter(); }

  public boolean removeItemWhenChosen() { return true; }

  public Comparator getComparator() {
	  PartNumberOrder c = new PartNumberOrder();
	  String sortKeyFields=Properties.get(Names.SORT_KEY_FIELDS);
	  if (sortKeyFields==null || "".equals(sortKeyFields.trim())) return null;
	  c.setKeyFields(sortKeyFields);
	  return c;
	}

  //Intralink library parts and Agile class types are categorized by the prefix of their name
  //first sequence of digits before the "-" in the name defines the part's category:
  //ilink Name: "29-11103.prt"  => "29 - Connectors, adapters":Agile Class
  private void loadClassCategories() throws Exception {
    classCategories = new HashMap();
	  AgileAccess x = AgileAccess.getAccess("DesignState-node-0", Properties.get(Names.DEFAULT_AGILE_SOURCE), getAuthentication());
	  Collection classNames = x.listClasses();
	  String category;
	  String agileClass;
	  Iterator i = classNames.iterator();
	  while (i.hasNext()) {
	    agileClass = i.next().toString();
	    category = getCategory(agileClass);
	    classCategories.put(category, agileClass);
	  }
	}

  private String getCategory(String name) {
    String category = null;
    int pos = name.indexOf('-');
    if (pos>-1) category = name.substring(0, pos).trim();
    {} //System.out.println(name+":"+category);
    return category;
  }

  protected String categorizeAsAgileClass(Metadata data) throws Exception {
    String agileClass = "CAD Part";
    if (null==classCategories) loadClassCategories();
    String category = getCategory(data.getName());
    if (null==category) return agileClass;
    if (classCategories.containsKey(category)) agileClass = (String)classCategories.get(category);
    return agileClass;
  }

  public Map getCiscoLibraryAttributeMappping() {
    Map mapping = new HashMap();
    StringPair p;
    p = new StringPair("name","number");
    mapping.put("name", p);
    p = new StringPair("Description","Description");
    mapping.put("description", p);
    p = new StringPair("Rev","MCAD Revision");
    mapping.put("rev", p);
    p = new StringPair("Ver","MCAD Version");
    mapping.put("ver", p);
    p = new StringPair("Created On","MCAD Created On");
    mapping.put("created on", p);
    p = new StringPair("Created-By","MCAD Created By");
    mapping.put("created-by", p);
    return mapping;
  }

  public boolean idChoosesItem(String id, Object item) {
    String o = ((MetadataAdapter)item).getOrigin().toString();
    return id.equals(o);
  }

  public boolean isChosen(Object o) {
    Origin origin = ((MetadataAdapter)o).getOrigin();
    Iterator i = getChosenItems().iterator();
    while (i.hasNext()) if( ((MetadataAdapter)i.next()).getOrigin().isTheSameAs(origin)) return true;
    return false;
  }

  public void createChosenItems() throws Exception {
    AgileAccess x = zws.AgileAccess.getAccess("DesignState-node-0", Properties.get(Names.DEFAULT_AGILE_SOURCE), getAuthentication());
    Collection c = new Vector();
    Iterator i = getChosenItems().iterator();
    CiscoMetadataAdapter data;
    Collection renumberedData = new Vector();
    //do not create items that are already synchronized
    while(i.hasNext()) {
      data = (CiscoMetadataAdapter) i.next();
      c.add(data);
    }
    if ("YES".equalsIgnoreCase(createNonExistingParts)) x.create(map(c));
    else x.update(map(c));

    if ("YES".equalsIgnoreCase(getAttachNativeFile()) || "YES".equalsIgnoreCase(getAttachIGESImage())) {
	    i = getChosenItems().iterator();
	    String ws;
	    String location;
	    String partnumber;
	    while(i.hasNext()) {
	      data = (CiscoMetadataAdapter)i.next();
	      ws=data.getName().replace('.', '_');
	      location = data.getName();
	      partnumber = FileNameUtil.getBaseName(data.getName());
			  try {
			    IntralinkAccess y = IntralinkAccess.getAccess();
			    Origin o = data.getOrigin();
			    y.createWorkspace(o.getServerName(),o.getDatasourceName(), ws, getAuthentication());
			    y.checkout(o, ws, getAuthentication());
			    if ("YES".equalsIgnoreCase(getAttachNativeFile())) {
			     y.export(o, ws, location, getAuthentication());
			     x.attachFile(location, data.getName(), partnumber);
			    }
			    if ("YES".equalsIgnoreCase(getAttachIGESImage())) {
			      String igesName = y.convertToIGES(o, ws, location, getAuthentication());
			      x.attachFile(location, igesName, partnumber);
			    }
			    y.destroyWorkspace(o.getServerName(),o.getDatasourceName(), ws, getAuthentication());
			  }
			  catch(Exception e) { e.printStackTrace(); }
      }
    }
	}

  public Collection loadBills(Collection metadataList) throws Exception {
    BillOfMaterials bill=null;
    Collection bills = new Vector();
    IntralinkAccess y = IntralinkAccess.getAccess();
    Metadata data;
    Iterator i = metadataList.iterator();
    while (i.hasNext()) {
      data = (Metadata) i.next();
      if (data.hasSubComponents()) {
          bill = new BillOfMaterials(data);
      }
      else if (isAssembly(data)) {
        bill = y.reportBill(data.getOrigin(), getAuthentication());
      }
      /*
      else if (data.getName().startsWith("69")) {
        bill = y.reportBill(data.getOrigin(), getAuthentication());
      }
      */
      else if (isDrawing(data)) {
          continue;
          //bill = y.reportDrawingModels(data.getOrigin(), getAuthentication());
      }
      if (null!=bill) bills.add(bill);
    }
    return bills;
  }

  public Collection mapBills(Collection bills) throws Exception {
	  BillOfMaterials bill;
	  Collection mappedBills = new Vector();
	  Iterator i = bills.iterator();
	  while (i.hasNext()) {
	    bill = (BillOfMaterials) i.next();
	    bill = map(bill);
	    mappedBills.add(bill);
	  }
	  return mappedBills;
	}

  public void storeBills(Collection bills) throws Exception {
    if (null==bills || bills.size()==0) return;
    AgileAccess x = zws.AgileAccess.getAccess("DesignState-node-0", Properties.get(Names.DEFAULT_AGILE_SOURCE), getAuthentication());
    x.storeBill(bills);
	}

  public void deleteChosenItems(String agileClassName) throws Exception{
	  Collection c;
	  Iterator j;
	  SynchronizationRecord r;
    AgileAccess x = zws.AgileAccess.getAccess("DesignState-node-0", Properties.get(Names.DEFAULT_AGILE_SOURCE), getAuthentication());
    Iterator i = getChosenItems().iterator();
    Origin o;
    CiscoMetadataAdapter data;
    Metadata mappedData;
    while (i.hasNext()) {
      data = (CiscoMetadataAdapter) i.next();
      x.delete(map(data));
      data.refresh();
      if (data.getIsRenamed() || data.getIsRenumbered()) continue;
      if (data.getCADDocumentOrigin()==null && data.getCADModelOrigin()==null) {
        o = data.getOrigin();
        sync.purgeByUID(o.getDomainName(), o.getServerName(), o.getDatasourceName(), o.getUniqueID());
      }
    }
	}

  protected Metadata map(Metadata data) throws Exception {
    MetadataBase m = mapMetadata(data);
    m.setOrigin(data.getOrigin());
    if (!data.hasSubComponents()) return m;

    MetadataSubComponent kid;
    Iterator i = data.getSubComponents().iterator();
    while (i.hasNext()) {
      kid = (MetadataSubComponent) i.next();
      //+++Hack - exclude list is used only for parts (need to make an exclude list for each doc type
      {} //System.out.println("Mapping subcomponent: " + kid.getName() + "["+kid.getQuantity()+"]");
      m.addSubComponent(mapSubComponent(kid));
    }
    return m;
  }

  private MetadataSubComponent mapSubComponent(MetadataSubComponent kid) throws Exception {
    Metadata m = map(kid);
    MetadataSubComponentBase sub = new MetadataSubComponentBase(m);
    sub.setQuantity(kid.getQuantity());
    return sub;
  }


  protected MetadataBase mapMetadata(Metadata data) throws Exception {
      MetadataBase m = new MetadataBase();
      m.setName(data.getName());
      m.set(ATT_AGILE_CLASS_TYPE, categorizeAsAgileClass(data));
      Iterator i = data.getAttributes().keySet().iterator();
      String key;
      StringPair mapping;
      Map ilinkMappings = getCiscoLibraryAttributeMappping();
      mapping=(StringPair)ilinkMappings.get("name");
      if (null!=mapping) m.set(mapping.getString1(), FileNameUtil.getBaseName(data.getName()));
      while (i.hasNext()) {
        key = i.next().toString();
        if("name".equals(key)) continue;
        mapping = (StringPair) ilinkMappings.get(key.toLowerCase());
        if (null==mapping) continue;
        m.set(mapping.getString1(), data.get(key));
      }
      return m;
    }

	protected Collection map(Collection metadataList) throws Exception {
	  Collection c = new Vector();
	  Iterator i = metadataList.iterator();
	  MetadataAdapter adapter;
	  Metadata data;
	  while (i.hasNext()) {
	    adapter = (MetadataAdapter)i.next();
      data = adapter;
	    if (isAssembly(adapter)) data = loadSubComponents(adapter);
	    c.add(map(data));
	  }
	  return c;
	}

  private BillOfMaterials map(BillOfMaterials bill) throws Exception {
    Metadata data = bill.getMetadata();
    Metadata m = map(data);
    return new BillOfMaterials(m);
  }

  private boolean isAssembly(Metadata data) {
    return data.getName().toLowerCase().endsWith(".asm");
  }

  private boolean isDrawing(Metadata data) {
    return data.getName().toLowerCase().endsWith(".drw");
  }

  private Metadata loadSubComponents (Metadata data) throws Exception {
	  Metadata structure=null;
	  BillOfMaterials bill;
	  IntralinkAccess y = IntralinkAccess.getAccess();
	  bill = y.reportBill(data.getOrigin(), getAuthentication());
	  return bill.getMetadata();
  }

  private void synchronizeHarrisOrigin(Metadata data) throws Exception {
	  Origin o, harrisOrigin;
	  o = data.getOrigin();
	  harrisOrigin = materializeHarrisOrigin(data);
	  if (null!=harrisOrigin) sync.record(o, harrisOrigin);
	  /*
	  if (!data.hasSubComponents()) return;
	  Iterator i = data.getSubComponents().iterator();
	  while(i.hasNext()) {
	    data = (Metadata)i.next();
	    synchronizeHarrisOrigins(sync, data);
	  }
	  */
	}

  public void synchronizeMetadata(Collection items) {
	  if (null==items) return;
	  CiscoMetadataAdapter data;
	  Iterator i = items.iterator();
	  while (i.hasNext()) {
	    data = (CiscoMetadataAdapter)i.next();
	    data.refresh();
	  }
	}



  public Origin materializeHarrisOrigin(Metadata data) {
    IntralinkOrigin o = (IntralinkOrigin)data.getOrigin();
    String partnumber=data.get("Part_Number");
    if (null==partnumber || "".equals(partnumber)) return null;
    HarrisOrigin h = new HarrisOrigin(o, partnumber);
    {} //System.out.println(h);
    return h;
  }

	public void render() {
	  synchronizeMetadata(getItems());
	  synchronizeMetadata(getChosenItems());
	}

	public String getAttachNativeFile() { return attachNativeFile; }
	public void setAttachNativeFile(String s) { attachNativeFile=s; }
	public String getAttachIGESImage() { return attachIGESImage; }
	public void setAttachIGESImage(String s) { attachIGESImage = s; }
	public String getCreateNonExistingParts() { return createNonExistingParts; }
	public void setCreateNonExistingParts(String s) { createNonExistingParts=s; }

  private static String ATT_AGILE_CLASS_TYPE="agile-class";
  private static Synchronizer sync = Synchronizer.getClient("DesignState-node-0");
  private Map classCategories = null;
  private String attachNativeFile = "NO";
  private String attachIGESImage= "NO";
  private String createNonExistingParts = "YES";
}
