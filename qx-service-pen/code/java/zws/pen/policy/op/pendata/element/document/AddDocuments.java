/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved*/
package zws.pen.policy.op.pendata.element.document;

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.service.pen.DocumentElement;
import zws.service.pen.PENDataElement;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class AddDocuments extends PENDataOpBase{

  public void execute() throws Exception {
    if (null==getOps()) return;
    
    Map bomAttributes = doOpsAsAttributes();    
    Collection documents = lookupLogicalNames();
    if (null==documents) return;
    Iterator i = documents.iterator();
    Object o;
    Iterator x;
    String documentName;
    while (i.hasNext()) {
      o = i.next();
      
      if (null!=o && o instanceof String) {
        documentName = (String)o;
        addDocument(documentName, bomAttributes);
      }      
      else if (o instanceof Collection) {
        x = ((Collection)o).iterator();
        while (x.hasNext()) {
          documentName = (String)x.next();
          addDocument(documentName, bomAttributes);
        }
      }
    }
    setResult(documents);
  } 
  
  private void addDocument(String documentName, Map bomAttributes) throws Exception {
    PENDataElement penData = lookupPENDataElement(getCurrentItem());
    DocumentElement docElement = penData.getDocumentElement();
    docElement.addDocument(documentName, 1, bomAttributes);
  }
    
}


