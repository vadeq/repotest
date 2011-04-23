import zws.qx.xml.QxXML;
import zws.security.Authentication;
import zws.service.event.processor.EventProcessorClient;

import java.security.NoSuchAlgorithmException;

/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Nov 6, 2007 10:59:06 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

public class TestEvent {

  public static void main(String[] args) {
    
    String event = "<event event-type='item_creation' name='000040' ><metadata origin='Cisco|node-0|TC-10|TC-10|xVAdCq1hxWJ9VB|000040|A|01-Nov-2007 07:45|N/A|N/A|infodba (infodba)' IMAN_master_form_rev='000040/A' acl_bits='0' checked_out=' ' checked_out_change_id=' ' checked_out_user=' ' creation_date='01-Nov-2007 07:45' current_id='000040' current_name='pavan-test3' current_revision_id='A' expl_checkout=' ' is_modifiable='Y' item_id='000040' item_revision_id='A' items_tag='000040-pavan-test3' last_mod_date='01-Nov-2007 07:45' last_mod_user='infodba' object_application='Teamcenter Engineering' object_name='pavan-test3' object_string='000040/A-pavan-test3' object_type='ItemRevision' owning_group='dba' owning_user='infodba (infodba)' pid='105' project_ids=' ' projects_list=' ' protection='RWDC--cOPSXI-iwAr-' revision_limit='1' revision_number=' ' timestamp='xVCdCq1hxWJ9VB' /></event>";
    Authentication authID;
    try {
      authID = new Authentication("admin", "agile");
      EventProcessorClient epClient = new EventProcessorClient();
      QxXML finalResult = epClient.process(new QxXML(event), authID);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    
    		
  }
}
