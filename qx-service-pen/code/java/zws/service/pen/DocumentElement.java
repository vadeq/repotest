/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on May 25, 2007 12:09:48 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.service.pen;

import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataSubComponentBase;
import zws.exception.InvalidName;
import zws.exception.NameNotFound;
import zws.qx.QxContext;
//import zws.util.{}//Logwriter;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class DocumentElement {
  public DocumentElement(PENDataElement e) { penDataElement = e; }

  public void addDocument(String documentName, int qty, Map bomAttributes) throws InvalidName {
    if (containsDocument(documentName)) return;
    documents.add(documentName);
    QxContext ctx = new QxContext();
    ctx.setAll(bomAttributes);
    ctx.set(PENDataNames.QUANTITY, String.valueOf(qty));
    
    bomAttributeMap.put(documentName, ctx);
    getPENData().lookupPENDataElement(documentName).increaseReferenceCount();
  }

  public void removeDocument(String documentName) throws Exception{
    if (!containsDocument(documentName)) return;
    bomAttributeMap.remove(documentName);
    String name = null;
    Iterator i = documents.iterator();
    while(i.hasNext()) {
      name  = (String) i.next();
      if (name .equalsIgnoreCase(documentName)) {
        i.remove();
        {}//Logwriter.printOnConsole(name + " removed  from document list" );
      }
    }
    getPENData().lookupPENDataElement(documentName).decreaseReferenceCount();        
  }

  public void removeAllDocuments() throws Exception {
    String name = null;
    ArrayList temList = new ArrayList();
    temList.addAll(documents);
    Iterator i = temList.iterator();
    while(i.hasNext()) {
      name  = (String) i.next();
      removeDocument(name);
    }
  }

  public Collection getDocuments() throws Exception{
    int qty;
    Collection subComponents = new ArrayList();
    MetadataSubComponentBase subItem = null;
    Metadata txMetadta = null;
    String name = null;
    QxContext ctx = null;
    Iterator itr = documents.iterator();
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

  public Collection getDocumentRefNames() {
    Collection subComponentNames = new ArrayList();
    Iterator itr = documents.iterator();
    while(itr.hasNext()) {
      subComponentNames.add(itr.next());
    }
    return subComponentNames;
  }

  public boolean containsDocument(String subdocumentName) {
    return bomAttributeMap.containsKey(subdocumentName);
  }

  public QxContext getBOMAttributes(String subdocumentName) throws InvalidName {
    if (!containsDocument(subdocumentName)) return  null;
    QxContext ctx = (QxContext)bomAttributeMap.get(subdocumentName);
    return ctx;
  }
  
  public void setPENDataElement(PENDataElement e) { penDataElement=e; }
  public PENDataElement getPENDataElement() { return penDataElement; }

  public PENData getPENData() { return penDataElement.getPENData(); }

  private ArrayList documents= new ArrayList();
  private Map bomAttributeMap = new HashMap();
  private PENDataElement penDataElement = null;
}
