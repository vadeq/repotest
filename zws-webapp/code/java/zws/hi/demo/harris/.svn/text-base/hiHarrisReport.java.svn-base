package zws.hi.demo.harris; /*
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
import zws.application.Properties;
import zws.application.server.webapp.Names;
import zws.bill.intralink.BillOfMaterials;
import zws.hi.custom.cisco.CiscoMetadataAdapter;
import zws.hi.report.MetadataAdapter;
import zws.hi.report.hiReport;
import zws.synchronization.SynchronizationRecord;
import zws.util.*;
import zws.util.MapUtil;
import zws.util.StringPair;
import zws.util.comparator.metadata.PartNumberOrder;

import java.util.*;


public class hiHarrisReport extends hiReport {
  public String getSelectedReportName() { return Properties.get("demo-report-harris"); }
  protected MetadataAdapter createNewMetadataAdapter() { return new HarrisMetadataAdapter(); }

  public boolean removeItemWhenChosen() { return true; }

  public Comparator getComparator() {
	  PartNumberOrder c = new PartNumberOrder();
	  String sortKeyFields=Properties.get(Names.SORT_KEY_FIELDS);
	  if (sortKeyFields==null || "".equals(sortKeyFields.trim())) return null;
	  c.setKeyFields(sortKeyFields);
	  return c;
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

  public void createChosenItems(String agileClassName) throws Exception {
    AgileAccess x = zws.AgileAccess.getAccess("DesignState-node-0", Properties.get(Names.DEFAULT_AGILE_SOURCE), getAuthentication());
    Collection c = new Vector();
    Iterator i = getChosenItems().iterator();
    HarrisMetadataAdapter data;
    Collection renumberedData = new Vector();
    //do not create items that are already synchronized
    while(i.hasNext()) {
      data = (HarrisMetadataAdapter) i.next();
      if(data.getIsRenamed()) { renameInSyncLog(data); }
      if(data.getIsRenumbered()) { renumberedData.add(data); }
      c.add(data);
      //if (agileClassName.equals("CAD Document") && null == data.getCADDocumentOrigin()) c.add(data);
      //else if (agileClassName.equals("CAD Part") && null == data.getCADModelOrigin()) c.add(data);
    }
    //--x.create(map(c, agileClassName));
    //--renumberInAgile(x, renumberedData, agileClassName);
    Collection agileItems = map(c, agileClassName);
    if ("YES".equalsIgnoreCase(createNonExistingParts)) x.create(agileItems);
    else x.update(agileItems);

    if (agileClassName.equals("CAD Part") && ("YES".equalsIgnoreCase(getAttachNativeFile()) || "YES".equalsIgnoreCase(getAttachIGESImage()))) {
      Map agileMappings = MapUtil.getMapFromMap(hiHarrisDemo.agileMappings, agileClassName);
      StringPair mapping=(StringPair)agileMappings.get("number");
      String pnAttribute = "name";
      if (null!=mapping) pnAttribute = mapping.getString0();

	    i = getChosenItems().iterator();
	    String ws;
	    String location;
	    String partnumber;
	    while(i.hasNext()) {
        data = (HarrisMetadataAdapter)i.next();
        partnumber = data.get(pnAttribute);
	      ws=data.getName().replace('.', '_');
	      location = data.getName();
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

  public void renameInSyncLog(HarrisMetadataAdapter data) throws Exception {
    String newName;
    Origin old = null;// lookup old origin from synclog using harris origin
    Origin h = materializeHarrisOrigin(data);
    Collection c = sync.findMatches(h.getDomainName(), h.getServerName(), h.getDatasourceName(), h.getName());
    if (null==c) return;
    Iterator i = c.iterator();
    Origin o;
    while(old==null && i.hasNext()) {
      o = (Origin)i.next();
      if (o.getDatasourceType().equals(Origin.FROM_ILINK) && !o.getName().equals(data.getOrigin().getName())) old = o;
    }
    if (null==old) return;
    newName = data.getName();
    sync.rename(old.getDomainName(), old.getServerName(), old.getDatasourceName(), old.getName(), newName);
  }

  public void renumberInAgile(AgileAccess x, Collection renumberedData, String agileClassName) throws Exception{
    Iterator i = renumberedData.iterator();
    HarrisMetadataAdapter data;
    String oldNumber=null;
    String newNumber=null;
    while(i.hasNext()) {
      data = (HarrisMetadataAdapter)i.next();
      oldNumber = data.getOldPartNumber(); //;find old number using last ilink origin->last harris origin
      newNumber = data.getPartNumber();
      if (agileClassName.equals("CAD Document")) {
        oldNumber += "-doc";
        newNumber += "-doc";
      }
      x.replace(oldNumber, newNumber);
    }
  }

  public Collection loadBills(Collection metadataList, String agileClassName) throws Exception {
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
      else if (isDrawing(data)) {
          continue;
          //bill = y.reportDrawingModels(data.getOrigin(), getAuthentication());
      }
      else if (agileClassName.equals("CAD Document")) {
        continue;
      }
      else if (agileClassName.equals("CAD Part")) {
        bill = new BillOfMaterials(data);
      }
      bills.add(bill);
    }
    return bills;
  }

  public Collection linkHarrisModelToDocument(Collection bills) throws Exception {
	  BillOfMaterials bill;
	  Collection linkedBills = new Vector();
	  Iterator i = bills.iterator();
	  while (i.hasNext()) {
	    bill = (BillOfMaterials) i.next();
	    linkHarrisModelToDocument(bill);
	    linkedBills.add(bill);
	  }
	  return linkedBills;
  }

  public void linkHarrisModelToDocument(BillOfMaterials bill) throws Exception {
    linkHarrisModelToDocument(bill.getMetadata());
  }

  public void linkHarrisModelToDocument(Metadata data) throws Exception {
    MetadataBase doc = new MetadataBase();
    doc.set("number", data.get("number")+"-doc");
    doc.setName(doc.get("number"));
    doc.set(ATT_AGILE_CLASS_TYPE, "");
    MetadataSubComponentBase sub = new MetadataSubComponentBase(doc);
    if (data.hasSubComponents()) {
      Iterator i = data.getSubComponents().iterator();
      Metadata kid;
      while (i.hasNext()) {
      	kid = (Metadata) i.next();
      	linkHarrisModelToDocument(kid);
      }
    }
    data.addSubComponent(sub);
  }

  public Collection mapBills(Collection bills, String agileClassName) throws Exception {
	  BillOfMaterials bill;
	  Collection mappedBills = new Vector();
	  Iterator i = bills.iterator();
	  while (i.hasNext()) {
	    bill = (BillOfMaterials) i.next();
	    bill = map(bill, agileClassName);
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
    HarrisMetadataAdapter data;
    Metadata mappedData;
    while (i.hasNext()) {
      data = (HarrisMetadataAdapter) i.next();
      x.delete(map(data, agileClassName));
      data.refresh();
      if (data.getIsRenamed() || data.getIsRenumbered()) continue;
      if (data.getCADDocumentOrigin()==null && data.getCADModelOrigin()==null) {
        o = data.getOrigin();
        sync.purgeByUID(o.getDomainName(), o.getServerName(), o.getDatasourceName(), o.getUniqueID());
      }
    }
	}

  protected Metadata map(Metadata data, String agileClassName) throws Exception {
    MetadataBase m = mapMetadata(data, agileClassName);
    m.setOrigin(materializeHarrisOrigin(data));
    if (!data.hasSubComponents()) return m;

    MetadataSubComponent kid;
    Iterator i = data.getSubComponents().iterator();
    while (i.hasNext()) {
      kid = (MetadataSubComponent) i.next();
      //+++Hack - exclude list is used only for parts (need to make an exclude list for each doc type
      if (agileClassName.equals("CAD Part") && hiHarrisDemo.isExcluded(kid.getName())) continue;
      {} //System.out.println("Mapping subcomponent: " + kid.getName() + "["+kid.getQuantity()+"]");
      m.addSubComponent(mapSubComponent(kid, agileClassName));
    }
    return m;
  }

  private MetadataSubComponent mapSubComponent(MetadataSubComponent kid, String agileClassName) throws Exception {
    Metadata m = map(kid, agileClassName);
    MetadataSubComponentBase sub = new MetadataSubComponentBase(m);
    sub.setQuantity(kid.getQuantity());
    return sub;
  }

  protected MetadataBase mapMetadata(Metadata data, String agileClassName) throws Exception {
      MetadataBase m = new MetadataBase();
      m.set(ATT_AGILE_CLASS_TYPE, agileClassName);
      synchronizeHarrisOrigin(data);
      Iterator i = data.getAttributes().keySet().iterator();
      String key;
      StringPair mapping;
      Map ilinkMappings = MapUtil.getMapFromMap(hiHarrisDemo.ilinkMappings, agileClassName);
      mapping=(StringPair)ilinkMappings.get("name");
      if (null!=mapping) m.set(mapping.getString1(), data.getName());
      while (i.hasNext()) {
        key = i.next().toString();
        mapping = (StringPair) ilinkMappings.get(key.toLowerCase());
        if (null==mapping) continue;
        m.set(mapping.getString1(), data.get(key));
      }
      if (agileClassName.toLowerCase().indexOf("document") >=0) {
          m.set("number", m.get("number")+"-doc");
          m.setName(m.get("number"));
      }
      return m;
    }

	protected Collection map(Collection metadataList, String agileClassName) throws Exception {
	  Collection c = new Vector();
	  Iterator i = metadataList.iterator();
	  MetadataAdapter adapter;
	  Metadata data;
	  while (i.hasNext()) {
	    adapter = (MetadataAdapter)i.next();
	    if (!hiHarrisDemo.isExcluded(adapter.getName())) {
	      data = adapter;
	      if (isAssembly(adapter)) data = loadSubComponents(adapter);
	      c.add(map(data, agileClassName));
	    }
	  }
	  return c;
	}

  private BillOfMaterials map(BillOfMaterials bill, String agileClassName) throws Exception {
    Metadata data = bill.getMetadata();
    Metadata m = map(data, agileClassName);
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
	  HarrisMetadataAdapter data;
	  Iterator i = items.iterator();
	  while (i.hasNext()) {
	    data = (HarrisMetadataAdapter)i.next();
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

  private String attachNativeFile = "NO";
  private String attachIGESImage= "NO";
  private String createNonExistingParts = "YES";
}
