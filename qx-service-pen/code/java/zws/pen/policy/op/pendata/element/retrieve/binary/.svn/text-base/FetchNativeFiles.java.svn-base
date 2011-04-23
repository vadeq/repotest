package zws.pen.policy.op.pendata.element.retrieve.binary;

/*
 * DesignState - Design Compression Technology 
 * @author: Emmanuel Ankutse 
 * @version: 1.0 Created on Nov 15, 2007 
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.origin.Origin;
import zws.repository.source.RepositoryBinarySource;
import zws.util.RemotePackage;


public class FetchNativeFiles extends FetchBase {
  protected RemotePackage download(RepositoryBinarySource binSource, Origin fileOrigin) throws Exception {
    RemotePackage binary = binSource.fetchNativeFile(getQxCtx(), fileOrigin, getAuthentication());
    return binary;
  }

}
