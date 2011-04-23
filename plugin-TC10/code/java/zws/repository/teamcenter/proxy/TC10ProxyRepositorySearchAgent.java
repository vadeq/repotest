package zws.repository.teamcenter.proxy;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on May 23, 2007 10:48:58 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.repository.search.RepositorySearchAgentBase;
import zws.repository.teamcenter.TC10Constants;
import zws.repository.teamcenter.proxy.xml.SearchResultHandler;
import zws.repository.teamcenter.proxy.xml.TC10ResultHandler;
import zws.repository.teamcenter.qx.program.TC10Poet;
import zws.security.CryptoUtil;

import java.util.Collection;


public class TC10ProxyRepositorySearchAgent extends RepositorySearchAgentBase
{
	public void executeQuery() throws Exception {
    QxInstruction root = new QxInstruction(TC10Constants.TAG_QX);
    QxInstruction open = new QxInstruction(TC10Constants.TAG_OPEN_REPOSITORY);
    QxInstruction search = new QxInstruction(TC10Constants.TAG_SEARCH);

    open.set(TC10Constants.ATT_REPOSITORY_NAME, getRepository().getRepositoryName());
    search.set(TC10Constants.ATT_CRITERIA, getCriteria().toString());    
    open.set(TC10Constants.ATT_USER,  getAuthenticationToken().getUsername());
    //open.set(TC10Constants.ATT_PASSWORD,  getAuthenticationToken().getPassword());
    CryptoUtil encryptutil = new CryptoUtil();
    String encryptedPassword = encryptutil.encrypt(getAuthenticationToken().getPassword());
    open.set(TC10Constants.ATT_ENCRYPTED_PASSWORD, encryptedPassword);    


    root.add(open);          
    open.add(search);
    
    QxXML dataInstruction = new QxXML(root.toString());
    QxContext ctx = new QxContext();
   
    TC10Poet poet = TC10Poet.materializePoet(getTC10Repository());
    QxXML result = poet.executeQx(ctx, dataInstruction);

    //QxInstructionFileClient qxClient = QxInstructionFileClient.materializeClient();
    //QxXML result = qxClient.executeQx(ctx, dataInstruction);

    {} //System.out.println(">>>>>>Proxy Search.execute<<<<<<<<");
    {} //System.out.println(result.toString());
    {} //System.out.println(">>>>>>Proxy Search.execute<<<<<<<<");    
    
    TC10ResultHandler handler = new SearchResultHandler();
    getTC10Repository().handleResponse(result, handler);
    Collection c = handler.getResults();
    
    {} //System.out.println(">>>>>>Collection Size: "+c.size()+"<<<<<<<<");
    {} //System.out.println(c.toString());
    {} //System.out.println(">>>>>>Collection<<<<<<<<");
    store(c);
    //storeAll(c);
	}
  
  public TC10ProxyRepositoryBase getTC10Repository() { return (TC10ProxyRepositoryBase)getRepository(); }
  public void setTC10Repository(TC10ProxyRepositoryBase r) { setRepository(r); }  
}
