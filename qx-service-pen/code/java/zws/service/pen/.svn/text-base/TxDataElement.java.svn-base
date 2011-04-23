/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on May 25, 2007 12:09:48 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.service.pen;

import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataSubComponent;
import zws.data.MetadataSubComponentBase;
import zws.exception.InvalidName;
import zws.qx.QxContext;
//import zws.util.{}//Logwriter;
import zws.util.RemotePackage;


import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class TxDataElement {
  public TxDataElement(PENDataElement e) { penDataElement = e; }

  public Metadata getTxData() { return transformedData; }

  public void addSubcomponent(String subcomponentName,int qty, Map bomAttributes) throws InvalidName {
   // if (!getPENDataElement().getPENData().containsReference(subcomponentName)) throw new InvalidName(subcomponentName);
    if (containsSubcomponent(subcomponentName)) {
      //aready set as a subcomponent - just update quantity
      QxContext c = (QxContext) bomAttributeMap.get(subcomponentName);
      String strQty = c.get(PENDataNames.QUANTITY);
      int q = 1;
      try { q = Integer.valueOf(strQty).intValue(); }
      catch (Exception ignore) { ignore.printStackTrace(); }
      q += qty;
      c.set(PENDataNames.QUANTITY, String.valueOf(q));
      return;
    }
    //new subcomponent
    subcomponents.add(subcomponentName);
    QxContext ctx = new QxContext();
    ctx.setAll(bomAttributes);
    ctx.set(PENDataNames.QUANTITY, String.valueOf(qty));
    bomAttributeMap.put(subcomponentName, ctx);
    {}//Logwriter.printOnConsole(subcomponentName + " added to " + getTxData());
    getPENData().lookupPENDataElement(subcomponentName).increaseReferenceCount();
  }

  public void removeSubcomponent(String subcomponentName) throws Exception{
    if (!containsSubcomponent(subcomponentName)) return;
    bomAttributeMap.remove(subcomponentName);
    String name = null;
    Iterator i = subcomponents.iterator();
    while(i.hasNext()) {
      name  = (String) i.next();
      if (name .equalsIgnoreCase(subcomponentName)) {
        i.remove();
        {}//Logwriter.printOnConsole(name + " removed  from subcomponent list" );
      }
    }
    getPENData().lookupPENDataElement(subcomponentName).decreaseReferenceCount();
  }

  public void removeAllSubcomponents() throws Exception {
    String name = null;
    ArrayList temList = new ArrayList();
    temList.addAll(subcomponents);
    Iterator i = temList.iterator();
    while(i.hasNext()) {
      name  = (String) i.next();
      removeSubcomponent(name);
    }
  }

  public QxContext getBOMAttributes(String subcomponentName) throws InvalidName {
     if (!containsSubcomponent(subcomponentName)) return  null;
     QxContext ctx = (QxContext)bomAttributeMap.get(subcomponentName);
     return ctx;
   }

  public Collection getSubComponents() throws Exception{
    int qty;
    Collection subComponents = new ArrayList();
    MetadataSubComponentBase subItem = null;
    Metadata txMetadta = null;
    String name = null;
    QxContext ctx = null;
    Iterator itr = subcomponents.iterator();
    while(itr.hasNext()) {
      name  = (String) itr.next();
      ctx = (QxContext)bomAttributeMap.get(name);
      qty = ctx.getInt(PENDataNames.QUANTITY);
      txMetadta = getPENData().lookupTxMetaData(name);
      //txMetadta = getTxData();
      if(null != txMetadta) {
        subItem= new MetadataSubComponentBase(txMetadta);
        subItem.setQuantity(qty);
        subComponents.add(subItem);
      } else {
        {}//Logwriter.printOnConsole("txMetadta is not found for " + name);
      }
    }

    return subComponents;
  }


  public MetadataSubComponent getSubComponent(String childName) throws Exception{
    int qty;
    MetadataSubComponentBase subItem = null;
    Metadata txMetadta = null;
    QxContext ctx = null;
    if (!bomAttributeMap.containsKey(childName)) return null;
    ctx = (QxContext)bomAttributeMap.get(childName);
    qty = ctx.getInt(PENDataNames.QUANTITY);
    txMetadta = getPENData().lookupTxMetaData(childName);
    if(null != txMetadta) {
      subItem= new MetadataSubComponentBase(txMetadta);
      subItem.setQuantity(qty);
    }
    return subItem;
  }


  public Collection getSubComponentRefNames() {
    Collection subComponentNames = new ArrayList();
    Iterator itr = subcomponents.iterator();
    while(itr.hasNext()) {
      subComponentNames.add(itr.next());
    }
    return subComponentNames;
  }

  public boolean containsSubcomponent(String subcomponentName) {
    return bomAttributeMap.containsKey(subcomponentName);
  }

  public void addBinaryFile (RemotePackage binary) { this.binaryFiles.add(binary); }
  public ArrayList getBinaryFiles() { return binaryFiles; }

  public void addBinaryFileContext (String key, QxContext ctx) { binaryFilesContexts.put(key, ctx); }
  public void addBinaryFileContext (RemotePackage rf, QxContext ctx) { 
    String key = makeHashKey(rf); 
    binaryFilesContexts.put(key, ctx); 
  }   
  public QxContext getBinaryFileContext(String key) {return (QxContext)binaryFilesContexts.get(key);} 
  public HashMap getBinaryFilesContexts() { return (HashMap)binaryFilesContexts ; }   
  public QxContext getBinaryFileContext(RemotePackage rf) {
    String key = makeHashKey(rf); 
    return (QxContext)binaryFilesContexts.get(key);
  }
  public QxContext removeBinaryFileContext(RemotePackage rf) {
    String key = makeHashKey(rf);     
    return  (QxContext)binaryFilesContexts.remove(key);
  }  
  private String makeHashKey(RemotePackage rf) {return  rf.getName()+rf.getUrl().toString();}

  public void removeBinaryFilesAndContexts() {
    int count = getBinaryFiles().size();
    for (int i=0; i<count; i++) {
      RemotePackage rf = (RemotePackage)getBinaryFiles().remove(i);
      removeBinaryFileContext(rf);    
    }
  }  
  
  public void setPENDataElement(PENDataElement e) { penDataElement=e; }
  public PENDataElement getPENDataElement() { return penDataElement; }
  public int getBinaryFilesAdoptionCount() {return binaryFilesAdoptionCount;}
  public void setBinaryFilesAdoptionCount(int i) {binaryFilesAdoptionCount=i;}
  
  public PENData getPENData() { return penDataElement.getPENData(); }
  private Metadata transformedData = new MetadataBase();
  private ArrayList binaryFiles = new ArrayList();
  private Map binaryFilesContexts = new HashMap();  
  private ArrayList subcomponents = new ArrayList();
  private Map bomAttributeMap = new HashMap();
  private PENDataElement penDataElement = null;
  private int binaryFilesAdoptionCount=0;
 }

