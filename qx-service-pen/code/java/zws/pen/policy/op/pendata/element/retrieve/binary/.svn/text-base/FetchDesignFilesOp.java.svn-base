package zws.pen.policy.op.pendata.element.retrieve.binary;

/*
 * DesignState - Design Compression Technology 
 * @author: Emmanuel Ankutse 
 * @version: 1.0 Created on Nov 15, 2007 
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.origin.Origin;
import zws.repository.source.RepositoryBinarySource;
////import zws.util.{}//Logwriter;
import zws.util.RemotePackage;

public class FetchDesignFilesOp extends FetchBase {

  protected RemotePackage download(RepositoryBinarySource binSource, Origin fileOrigin) throws Exception {
    
    try {
      RemotePackage binary = binSource.fetchDesignFiles(this.getQxCtx(), fileOrigin, this.getAuthentication());
      return binary;
    } catch (Exception e) {
      String notificationSubject = "PEN: DESIGN FETCH FAILED! "+getCurrentItem();
      String notificationMessage="WARNING: "+"Fetching design " + getCurrentItem() + " from source repository failed! "+e.toString();
      {}//Logwriter.printOnConsole(notificationMessage);
      {}//Logwriter.printOnConsole("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
      zws.Alert.notify(notificationSubject, notificationMessage);      
      return null;
    }
  
  }

}