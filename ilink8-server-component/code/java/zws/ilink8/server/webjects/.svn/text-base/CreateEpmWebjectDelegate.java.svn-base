package zws.ilink8.server.webjects;

import java.io.ByteArrayInputStream;
import wt.content.ApplicationData;
import wt.content.ContentHelper;
import wt.content.ContentHolder;
import wt.content.ContentRoleType;
import wt.epm.EPMApplicationType;
import wt.epm.EPMAuthoringAppType;
import wt.epm.EPMDocument;
import wt.epm.EPMDocumentMaster;
import wt.epm.EPMDocumentType;
import wt.fc.PersistenceHelper;
import wt.fc.QueryResult;
import wt.folder.Folder;
import wt.folder.FolderHelper;
import wt.inf.container.WTContainerHelper;
import wt.inf.container.WTContainerRef;
import wt.pom.Transaction;
import wt.series.MultilevelSeries;
import wt.util.WTException;
import wt.vc.VersionControlHelper;
import wt.vc.VersionIdentifier;
import wt.vc.wip.WorkInProgressHelper;
import zws.ilink8.server.util.Base64;
import zws.ilink8.server.util.IntralinkUtility;

import com.infoengine.object.factory.Group;
import com.infoengine.object.factory.Task;
import com.infoengine.object.factory.Webject;
import com.ptc.core.adapter.server.impl.AbstractWebjectDelegate;


public class CreateEpmWebjectDelegate extends AbstractWebjectDelegate{
	
	static final long serialVersionUID = 1L;
	
    public CreateEpmWebjectDelegate() { }

    public Task invoke(Task task) throws WTException {
    	preset(task);
    	Webject webject = task.getWebject();
    	
    	String type 			= webject.paramValue("TYPE");
    	String docType 			= webject.paramValue("DOCTYPE");
    	String authApp 			= webject.paramValue("AUTHAPP");
    	String appType 			= webject.paramValue("APPTYPE");
    	String folderPath 		= webject.paramValue("FOLDERPATH");
    	String containerPath 	= webject.paramValue("CONTAINERPATH");
    	String number 			= webject.paramValue("NUMBER");
    	String name 			= webject.paramValue("NAME");
    	String version 			= webject.paramValue("VERSION");
    	String cadName 			= webject.paramValue("CADNAME");
    	String content 			= webject.paramValue("CONTENT");
    	
		if(version.equalsIgnoreCase("")){ version = null; }
    	Transaction tran = null;
    	try{
    		tran = new Transaction();
    		tran.start();
	        	EPMDocument epmdocument = constructEPMDocument( number, name, cadName, docType, authApp, appType, folderPath, containerPath, version);
	        	epmdocument = (EPMDocument)PersistenceHelper.manager.save(epmdocument);
	        	updateContent(epmdocument, content, cadName);
			tran.commit();
			tran = null;
    	}catch(Exception e){    
    		e.printStackTrace();
    		throw new WTException(e);
    	} finally {
    		if(tran != null){ tran.rollback(); }
    	}
		
        Group groupOut = new Group("output");
        groupOut.setClassName(type);
        Task response = new Task();
        response.addVdb(groupOut);
        return response;
    }
    

    private static EPMDocument constructEPMDocument( String number, String name, String cadName, String docType, String authApp, String appType, String folderPath, String containerPath, String version) throws Exception {
        EPMDocument document = (EPMDocument) new IntralinkUtility().getObject(wt.epm.EPMDocument.class, number, null, null);
        WTContainerRef wtcontainerref = (WTContainerRef) WTContainerHelper.service.getByPath(containerPath);
    	Folder folder = FolderHelper.service.getFolder(folderPath, wtcontainerref);
        if(document == null) {         	
            document = EPMDocument.newEPMDocument();
            document.setNumber(number);
            document.setName(name);
            document.setCADName(cadName);
            document.setDocType(EPMDocumentType.toEPMDocumentType(docType));
            document.setCADName(cadName);
            ((EPMDocumentMaster)document.getMaster()).setAuthoringApplication(EPMAuthoringAppType.toEPMAuthoringAppType(authApp));
            ((EPMDocumentMaster)document.getMaster()).setOwnerApplication(EPMApplicationType.toEPMApplicationType(appType));
        	if(version != null){
        		MultilevelSeries mls = MultilevelSeries.newMultilevelSeries("wt.vc.VersionIdentifier", version);
        		VersionIdentifier vid = VersionIdentifier.newVersionIdentifier(mls);
        		VersionControlHelper.setVersionIdentifier(document, vid);
        	}
        }
        else {
              if (WorkInProgressHelper.isCheckedOut(document)) {}
              else if(version.equals(VersionControlHelper.getVersionIdentifier(document).getValue())){
                 EPMDocument temp = (EPMDocument)VersionControlHelper.service.newIteration(document, true);
                 document = (EPMDocument)PersistenceHelper.manager.refresh(document);
                 document = (EPMDocument)VersionControlHelper.service.supersede(document,temp); 
              }
              else {
            	  document = (EPMDocument)VersionControlHelper.service.newVersion(document);
            	  if(!version.equalsIgnoreCase("next")){
	            	  MultilevelSeries mls = MultilevelSeries.newMultilevelSeries("wt.vc.VersionIdentifier", version);
	            	  VersionIdentifier vid = VersionIdentifier.newVersionIdentifier(mls);            	  
	            	  VersionControlHelper.setVersionIdentifier(document, vid);
            	  }
              }
        }
        return document;
     }
   
    
	public void updateContent(EPMDocument wtdocument, String content, String cadName) throws Exception {
		ContentHolder holder = ContentHelper.service.getContents(wtdocument);
		QueryResult queryResultPrimary = ContentHelper.service.getContentsByRole(holder, ContentRoleType.PRIMARY);
		ApplicationData applicationdata =ApplicationData.newApplicationData(holder);
		applicationdata.setRole(ContentRoleType.PRIMARY);
		applicationdata.setFileName(cadName);
		ByteArrayInputStream inputstream = new ByteArrayInputStream(Base64.decode(content));	
	}	
	    

}