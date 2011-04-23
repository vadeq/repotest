package zws.repository.R8;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on May 23, 2007 10:48:58 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import zws.application.Names;
import zws.data.Metadata;
import zws.data.MetadataSubComponent;
import zws.data.MetadataSubComponentBase;
import zws.origin.Origin;
import zws.origin.R8Origin;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;

/**
 * The Class TC10RepositoryMetadataSource.
 */
public class R8RepositoryStructureSource extends R8RepositoryBase
implements RepositoryStructureSource {

	/**
	 * The Constructor.
	 * @param parent parentContext
	 */
	public R8RepositoryStructureSource(QxContext parent) {
		configureParentContext(parent);
	}

	public Metadata reportBOM(QxContext runningCtx, Origin o, Authentication id) throws Exception {
		return getBOM(runningCtx, o, "ASSTORED", id);
	}

  public Metadata reportLatestBOM(QxContext runningCtx, String name, Authentication id) throws Exception {
    RepositoryMetadataSource source = this.materializeMetadataSource();
    Metadata md = source.findLatest(runningCtx, name, id);
    return getBOM(runningCtx, md.getOrigin(), "LATEST", id);
  }

  public Collection reportDependencies(QxContext runningCtx, Origin o,  Authentication id) throws Exception {
    return reportDependencies(runningCtx, o,  "ASSTORED", id);
  }
  
  public Collection reportLatestDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    return reportDependencies(runningCtx, o,  "LATEST", id);
  }
  
	private Metadata getBOM(QxContext runningCtx, Origin o, String structureConfiguration, Authentication id) throws Exception {

		R8Origin origin = (R8Origin) o;
		HashMap originHM = this.makeOriginHM(id);
		String selectAttributes = getAttributes(runningCtx.get(Names.SELECT_ATTRIBUTES));
		String returnAttributes = this.translateReturnAttributes(selectAttributes);
		String ilinkDomain = this.getIlinkServerDomain();

    int depth = 88888;
    String d = runningCtx.get("depth");
    if (null!=d) {
      try {
        int dp = Integer.valueOf(d).intValue();
        if (dp > 0) depth = dp;
      }
      catch (Exception ignore) {
        ignore.printStackTrace();
      }
    }

		HashMap dataHM = new HashMap();
		dataHM.put("method", 		"Query-Tree");
		dataHM.put("attribute", 	returnAttributes);
		dataHM.put("type", 		    "wt.epm.structure.EPMMemberLink");
		dataHM.put("object_ref", 	origin.getOBID());
		dataHM.put("direction", 	"uses");
		dataHM.put("mode", 			"nested");
		dataHM.put("auto_navigate", "true");
		dataHM.put("depth", 		"" + depth);
		dataHM.put("output_type", 	"full");
		dataHM.put("selectby", 		structureConfiguration);
		String xmlResult = R8Invoke.invokeMethod(originHM, dataHM);
		// System.out.println("The result is: " + xmlResult);
		Collection m = (Collection) this.materializeMetadata(xmlResult, returnAttributes, false);
		Document document = getDocumentByString(xmlResult);
		String topLevelObid = XPathAPI.selectSingleNode(document, "/wc:COLLECTION/wt.epm.structure.EPMMemberLink/wc:INSTANCE/obid").getFirstChild().getNodeValue();

		Iterator iterator = m.iterator();
		HashMap hashMap = new HashMap();

		Metadata m1 = null;
		String obid = null;
		String quantity = null;
		String linkObid = null;
		String issuppressed = null;
		String dependencytype = null;

		while(iterator.hasNext()){
			m1 = (Metadata) iterator.next();
			obid = ((R8Origin)m1.getOrigin()).getOBID();
			quantity = m1.get("quantity");
			if (null==quantity) {
				quantity ="1";
				//FIX THE QUANTITY!!!!!!!!!!!!!!!!
				//throw new zws.exception.InvalidConfiguration("Can not determine Quantity using "+this.getIlinkServerDomain()+" as "+ QxContext.ILINK_SERVER_DOMAIN+"!");
			}
			linkObid = lookupLinkObid(m1);//m1.get("WCTYPE|wt.epm.structure.EPMMemberLink|" + ilinkDomain + ".DefaultEPMMemberLink.obid");
			issuppressed = m1.get("issuppressed");
			dependencytype = m1.get("dependencytype");

			if(!obid.equalsIgnoreCase(topLevelObid)){
				m1 = new MetadataSubComponentBase(m1);
				((MetadataSubComponentBase)m1).setQuantity(Integer.valueOf(quantity).intValue());
				if(issuppressed != null){
					((MetadataSubComponentBase)m1).set("issupressed", issuppressed);
				}
				if(dependencytype != null){
					if(dependencytype.equalsIgnoreCase("2")){ dependencytype = "memebership"; }
					else if(dependencytype.equalsIgnoreCase("268,435,456")){ dependencytype = "Skeleton Model"; }
					((MetadataSubComponentBase)m1).set("dependencytype", dependencytype);
				}

			} else {
				linkObid = "-";
			}

			hashMap.put(linkObid, m1);
		}

		Metadata root = null;
		String childObid = null;
		Node childNode = null;
		NodeList childNodeList = null;

		iterator = hashMap.keySet().iterator();
		while(iterator.hasNext()){
			linkObid = (String) iterator.next();
			m1 = (Metadata) hashMap.get(linkObid);
			obid = m1.get("obid");
			if(obid.equalsIgnoreCase(topLevelObid)){ root = m1; }

			String xPath = "/wc:COLLECTION//wc:INSTANCE[obid='" + obid + "']/wt.epm.structure.EPMMemberLink.uses/wc:INSTANCE/wc:Attribute[@NAME='WCTYPE|wt.epm.structure.EPMMemberLink|" + ilinkDomain + ".DefaultEPMMemberLink.obid']";
			childNodeList = XPathAPI.selectNodeList(document, xPath);

			for(int k=0; k < childNodeList.getLength(); k++){
				childNode = childNodeList.item(k);
				if (childNode.getNodeType() == Node.TEXT_NODE) {
					continue;
				}
				if (childNode.getFirstChild() != null) {
					childObid = childNode.getFirstChild().getNodeValue();
					m1.addSubComponent((MetadataSubComponent) hashMap.get(childObid));
				}
			}
		}
		{} //System.out.println(root);
		return root;
	}
  
	private Collection reportDependencies(QxContext runningCtx, Origin o,  String structureConfiguration, Authentication id) throws Exception {
		R8Origin origin = (R8Origin) o;
		HashMap originHM = this.makeOriginHM(id);
    String selectAttributes = getAttributes(runningCtx.get(Names.SELECT_ATTRIBUTES));
    String returnAttributes = this.translateReturnAttributes(selectAttributes);
		HashMap dataHM = new HashMap();
		dataHM.put("method", 		"Query-Links");
		dataHM.put("attribute", 	returnAttributes);
		dataHM.put("type", 		    "wt.epm.structure.EPMReferenceLink");
		dataHM.put("object_ref", 	origin.getOBID());
		dataHM.put("direction", 	"references");
		dataHM.put("auto_navigate", "true");
		dataHM.put("output_type", 	"full");
		dataHM.put("selectby",    structureConfiguration);		

		String xmlResult = R8Invoke.invokeMethod(originHM, dataHM);
		{} //System.out.println("The result is: " + xmlResult);
		Collection m = (Collection) materializeMetadata(xmlResult, returnAttributes, false);
		return m;
	}

	public Collection reportFirstLevelDependencies(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
		return reportDependencies(runningCtx, origin,  id);
	}

	public Collection reportFirstLevelLatestDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception {
		throw new zws.exception.UnsupportedOperation("reportFirstLevelLatestDependencies");
	}

	public Metadata reportWhereUsed(QxContext runningCtx, Origin o, Authentication id) throws Exception {
		throw new zws.exception.UnsupportedOperation("reportWhereUsed");
	}

  public Metadata reportBOMPlusDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    return null;
  }
  
  public Metadata reportLatestBOMPlusDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    return null;
  }

}
