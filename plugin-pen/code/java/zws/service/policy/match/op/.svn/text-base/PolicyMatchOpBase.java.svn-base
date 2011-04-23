package zws.service.policy.match.op;

/*
 * DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 26, 2007 12:03:51 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.data.Metadata;
import zws.op.OpBase;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
//impoer zws.util.Logwriter;

import java.util.Collection;
import java.util.Vector;


/**
 * The Class PolicyMatchOpBase.
 * @author ptoleti
 */
public abstract class PolicyMatchOpBase extends OpBase implements PolicyMatchOp {

  public void setQxCtx(QxContext ctx) {this.qxContext = ctx;}
  public QxContext getQxCtx() {return this.qxContext;}
  public abstract void execute() throws Exception;
  public void add(PolicyMatchOp op) {opList.add(op);}
  public Collection getOps() {return opList;}
  public Authentication getAuthentication() {return authID;}
  public void setAuthentication(Authentication authId) {authID = authId; }
  public Origin getOrigin() {return origin;}
  public void setOrigin(Origin originObj) {this.origin = originObj;}
  public void passConfiguration(PolicyMatchOp op) {
      op.setContext(getContext());
      op.setQxCtx(getQxCtx());
      op.setOrigin(getOrigin());
      op.setContext(getContext());
  }



    public Metadata getMetaData() throws Exception {
      {} //System.out.println("context " + getContext());
      if(getContext().get(getOrigin().toString()) != null) {
        metaData = (Metadata) getContext().get(getOrigin().toString());
      } else {
        Repository repositoryObj = null;
        RepositoryService r = RepositoryClient.getClient();
        repositoryObj = r.findRepository(getOrigin().getRepositoryName());
        RepositoryMetadataSource metadataSourceObj = repositoryObj.materializeMetadataSource();
        {} //System.out.println("ctx " + getQxCtx());
        {} //System.out.println("getOrigin().getName()  " + getOrigin().getName());
        {}//Logwriter.printOnConsole("getAuthentication()  " + getAuthentication());
        //metaData =  metadataSourceObj.findLatest(getQxCtx(), getOrigin().getName(), getAuthentication());
        metaData =  metadataSourceObj.find(getQxCtx(), getOrigin(), getAuthentication());
        setMetaData(metaData);
      }
      return metaData;
    }

    public void setMetaData(Metadata metaDataObj) {
      getContext().set(getOrigin().toString(),metaDataObj);
    }

  private Authentication authID = null;
  private Collection opList = new Vector();
  private QxContext qxContext = null;
  private Origin origin = null;
  private Metadata metaData = null;
}
