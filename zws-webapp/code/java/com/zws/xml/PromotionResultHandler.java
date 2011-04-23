/*
 * Created on Oct 14, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.xml;

import com.zws.application.Properties;
import com.zws.domo.baseline.Baseline;
import com.zws.domo.baseline.Fileentry;
import com.zws.service.baseline.BaselineService;
import com.zws.util.GenericSAXHandler;

import org.xml.sax.Attributes;
/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PromotionResultHandler extends GenericSAXHandler {
	
	BaselineService service = BaselineService.getService();
	Baseline baseline = null;

	public void startElement( String uri, String localName, String qName, Attributes attribs ) {

		
		String form_name = "";
		String folder = "";
		String description = "";
		
		String metadata_name = "";
		String branch = "";
		String revision = "";
		String version = "";
		String release_level = "";
		
		
		
		if( localName.equals( "promotion-form" ) ) {
		  	form_name = resolveAttrib( uri, "Name", attribs, "" );
		  	//folder = resolveAttrib( uri, "Folder", attribs, "" );
		  	description = resolveAttrib( uri, "Description", attribs, "" );
		  	
		  	//TODO get real basline name
		  	//String baseLineName = form_name;
		  	String baseLineName = createBaselineName(form_name, description);
		  	
		  	
		  	if(baseLineName != null && baseLineName.trim().length() > 0){
		  		baseline = new Baseline();
		  		baseline.setName(baseLineName);
		  		folder = Properties.get(Properties.promoBaselineFolder);
		  		baseline.setLocation(folder);
		  		
		  	}
		  
		    {} //System.out.println("promotion-form : " + form_name + " " + folder + " " + description);

		}else if( localName.equals( "metadata" ) ) {
			
			metadata_name = resolveAttrib( uri, "Name", attribs, "" );
			branch = resolveAttrib( uri, "Branch", attribs, "" );
		  	revision = resolveAttrib( uri, "Revision", attribs, "" );
		  	version = resolveAttrib( uri, "Version", attribs, "" );
			release_level = resolveAttrib( uri, "Release-Level", attribs, "" );

		  	Fileentry entry = new Fileentry();
		  	entry.setName(metadata_name);
		  	entry.setBranch(branch);
		  	entry.setRevision(revision);
		  	entry.setVersion(version);
		  	
		  	baseline.addFile(entry);
		  	
			{} //System.out.println("metadata : " + metadata_name + " " +
			//                    branch + " " + revision + " " + version);

		}
	  }
		  // -----

	  public void endElement( String uri, String localName, String qName ) {
/*	  	
		if( localName.equals( "promotion-form" ) ) {
			try{	 
				try{
					{} //System.out.println(">>>> merging " + baseline.getName());
					baseline = service.merge(baseline);
					service.save(baseline);
					{} //System.out.println("<<<< done " + baseline.getName());
					
				}catch(InvalidEntry ie){
					{} //System.out.println(">>>> adding " + baseline.getName());
					service.add(baseline);
					{} //System.out.println("updating " + baseline.getName());
					service.update(baseline);
					service.save(baseline);
					{} //System.out.println("<<<< done " + baseline.getName());
				}
			}catch(Exception e){
				e.printStackTrace();	
			}
			baseline = null;
		}else if( localName.equals( "metadata" ) ) {
		  		  
		}
 */
	  }
	  
	  private String createBaselineName(String promotionFormName, String description){
	  	
	  	if(description != null && description.trim().length() > 0){
	  		
	  		
	  		return description;
	  	}else
	  		return promotionFormName;
	  }
}


