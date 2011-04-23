package zws.repository.teamcenter.proxy.xml;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataBinaryBase;

import java.io.StringReader;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SearchResultHandler extends TC10ResultHandler {
  public SearchResultHandler() {
    stack = new Stack();
  }
  public void startElement (String uri, String name, String qName, Attributes atts) {
    try {
      Metadata data = null;
      {} //System.out.println("qName " + qName);
      {} //System.out.println("FLAG " + flag);
      if(qName.equalsIgnoreCase("binaries")) {
        flag=true;
      }
      if(flag && qName.equalsIgnoreCase("binary-data")) {
          MetadataBase  binaryData = (MetadataBase)unmarshallComponent(atts);
          data = (Metadata) stack.peek();
          data.addBinary(new MetadataBinaryBase(binaryData));
        }
      if (!flag && qName.equalsIgnoreCase("metadata")) {
          data = unmarshallComponent(atts);
          stack.add(data);
          }

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    //add parsing of exceptions
    /*
    try {
      if ( qName.equalsIgnoreCase("failed-to-authenticate")) { getStorable().log(new Failure("err.invalid.authentication")); return; }
    }
    catch (Exception e) { e.printStackTrace(); }
     */
    //ignoring all other tags  for now
    // need to add ability to materialize IMANFileOrigins and add to metadata
  }

  public void endElement(String uri, String localName, String qName) {
    if ("binaries".equals(qName)) {
      flag = false;
    }
    if ("metadata".equals(qName)) {
      if (!flag) {
        Metadata m = (Metadata) stack.pop();
        {} //System.out.println("final result.....");
        {} //System.out.println(m);
        getResults().add(m);
      }
    }
  }


/*  private void add(Attributes atts) {
//    for (int i = 0; i < atts.getLength(); {}//Logwriter.printOnConsole(atts.getQName(i) +"="+atts.getValue(i++)));
    try {getResults().add(unmarshallComponent(atts));}
    catch (Exception e) { e.printStackTrace(); //++ log this error
    }
  }*/

  private boolean flag = false;
  private Stack stack;

  public static void main(String[] args) {
  {} //System.out.println("testing...");

    InputSource src;
  try { //
  //  src = new InputSource(new FileInputStream(file));
    StringReader strRdr = new StringReader("<results><metadata origin=\"cisco|node-0|tc-10-rev|TC-10|BiEZZKOtxWJ9VB|700-10957-01_ASM_w_conn-000152|001|1184463327000|N/A|N/A|infodba (infodba)\" name=\"700-10957-01_ASM_w_conn-000152\" IMAN_UG_wave_geometry=\"\" TC_CAE_Target=\"\" checked_out_change_id=\" \" MEProcess=\"\" project_list=\"\" item_revision=\"\" EC_affected_item_rel=\"\" has_variant_module=\"\" TC_CAE_Defining=\"\" checked_out_user=\" \" id_dispdefault=\"\" IMAN_UG_wave_position=\"\" IMAN_MEWorkInstruction=\"\" items_tag=\"700-10957-01_ASM_w_conn-000152-700-10957-01_ASM_w_conn\" IMAN_UG_wave_part_link=\"\" gc_updated_from_object_string=\"\" last_mod_date=\"14-Jul-2007 21:35\" process_stage=\"\" IMAN_aliasid=\"\" EC_reference_item_rel=\"\" IMAN_UG_promotion=\"\" has_module=\"\" IMAN_snapshot=\"\" altid_list=\"\" backup_date=\"\" owning_site=\"\" owning_group=\"dba\" object_desc=\"\" last_release_status=\"\" IMAN_Rendering=\"\" IMAN_specification=\"700-10957-01_ASM_w_conn\" process_stage_list=\"\" archive_date=\"\" IMAN_classification=\"\" proj_assign_mod_date=\"\" date_released=\"\" based_on=\"\" OwningUser=\"infodba (infodba)\" EC_problem_item_rel=\"\" acl_bits=\"0\" publication_sites=\"\" current_revision_id=\"001\" reservation=\"\" TC_CAE_Source=\"\" IMAN_3D_snap_shot=\"\" item_master_tag=\"700-10957-01_ASM_w_conn-000152/0\" effectivity_text=\"\" TC_WorkContext_Relation=\"\" external_apps=\"\" variant_expression_block=\"\" last_mod_user=\"infodba\" IMAN_UG_udf=\"\" revision_number=\" \" intent_text=\"\" CreationDate=\"14-Jul-2007 21:35\" TC_ProductManual=\"\" current_job=\"\" is_frozen=\"\" Type=\"ItemRevision\" structure_revisions=\"\" release_statuses=\"\" is_vi=\"\" IMAN_Simulation=\"\" declared_options=\"\" object_string=\"700-10957-01_ASM_w_conn-000152/001-700-10957-01_ASM_w_conn\" mvl_text=\"\" CAEAnalysis=\"\" current_id=\"700-10957-01_ASM_w_conn-000152\" current_desc=\"\" export_sites=\"\" is_modifiable=\"\" object_name=\"700-10957-01_ASM_w_conn\" ics_subclass_name=\"\" revision_limit=\"1\" projects_list=\" \" ics_classified=\"\" used_options=\"\" timestamp=\"BuHZZKOtxWJ9VB\" IMAN_UG_scenario=\"\" change=\"\" ItemID=\"700-10957-01_ASM_w_conn-000152\" wso_thread=\"\" EC_solution_item_rel=\"\" TC_CAE_Results=\"\" Revision=\"001\" IMAN_Motion=\"\" object_application=\"Teamcenter Engineering\" IMAN_reference=\"\" view=\"\" IMAN_master_form_rev=\"700-10957-01_ASM_w_conn-000152/0\" release_status_list=\"\" expl_checkout=\" \" IMAN_UG_expression=\"\" TC_Generic_Architecture=\"\" gde_bvr_list=\"\" IMAN_UG_altrep=\"\" IMAN_MEMfgModel=\"\" EC_addressed_by_rel=\"\" MESetup=\"\" checked_out=\" \" checked_out_date=\"\" has_variants=\"\" EC_snapshot_rel=\"\" IMAN_requirement=\"\" IMAN_manifestation=\"\" pid=\"105\" current_name=\"700-10957-01_ASM_w_conn\" project_ids=\" \" IMAN_external_object_link=\"\" protection=\"R-----c---XI-iwAr-\" zws-domain=\"cisco\" zws-server=\"node-0\" zws-repository=\"TC-10\" zws-datasource-search-agent-name=\"\"> <binaries count=\"2\">    <binary-data origin=\"cisco|node-0|tc-10-imanfile|TC-10|BqCZZKOtxWJ9VB|null|null|IdeasDrawing|700-10957-01_ASM_w_conn|700-10957-01_ASM_w_conn_0079.mdf|1184463327000|N/A|N/A|infodba (infodba)\" name=\"null\" IMAN_UG_wave_geometry=\"\" TC_CAE_Target=\"\" checked_out_change_id=\"\" MEProcess=\"\" project_list=\"\" item_revision=\"\" EC_affected_item_rel=\"\" has_variant_module=\"\" TC_CAE_Defining=\"\" checked_out_user=\"\" id_dispdefault=\"\" IMAN_UG_wave_position=\"\" IMAN_MEWorkInstruction=\"\" items_tag=\"\" IMAN_UG_wave_part_link=\"\" gc_updated_from_object_string=\"\" last_mod_date=\"14-Jul-2007 21:35\" process_stage=\"\" IMAN_aliasid=\"\" EC_reference_item_rel=\"\" IMAN_UG_promotion=\"\" has_module=\"\" IMAN_snapshot=\"\" altid_list=\"\" backup_date=\"\" owning_site=\"\" owning_group=\"dba\" object_desc=\"\" last_release_status=\"\" IMAN_Rendering=\"\" IMAN_specification=\"\" process_stage_list=\"\" archive_date=\"\" IMAN_classification=\"\" proj_assign_mod_date=\"\" date_released=\"\" based_on=\"\" OwningUser=\"infodba\" EC_problem_item_rel=\"\" acl_bits=\"0\" publication_sites=\"\" current_revision_id=\"\" reservation=\"\" TC_CAE_Source=\"\" IMAN_3D_snap_shot=\"\" item_master_tag=\"\" effectivity_text=\"\" TC_WorkContext_Relation=\"\" external_apps=\"\" variant_expression_block=\"\" last_mod_user=\"infodba\" IMAN_UG_udf=\"\" revision_number=\"\" intent_text=\"\" CreationDate=\"14-Jul-2007 21:35\" TC_ProductManual=\"\" current_job=\"\" is_frozen=\"\" Type=\"ImanFile\" structure_revisions=\"\" release_statuses=\"\" is_vi=\"\" IMAN_Simulation=\"\" declared_options=\"\" object_string=\"\" mvl_text=\"\" CAEAnalysis=\"\" current_id=\"\" current_desc=\"\" export_sites=\"\" is_modifiable=\"\" object_name=\"\" ics_subclass_name=\"\" revision_limit=\"\" projects_list=\"\" ics_classified=\"\" used_options=\"\" timestamp=\"BqEZZKOtxWJ9VB\" IMAN_UG_scenario=\"\" change=\"\" ItemID=\"\" wso_thread=\"\" EC_solution_item_rel=\"\" TC_CAE_Results=\"\" Revision=\"\" IMAN_Motion=\"\" object_application=\"\" IMAN_reference=\"\" view=\"\" IMAN_master_form_rev=\"\" release_status_list=\"\" expl_checkout=\"\" IMAN_UG_expression=\"\" TC_Generic_Architecture=\"\" gde_bvr_list=\"\" IMAN_UG_altrep=\"\" IMAN_MEMfgModel=\"\" EC_addressed_by_rel=\"\" MESetup=\"\" checked_out=\"\" checked_out_date=\"\" has_variants=\"\" EC_snapshot_rel=\"\" IMAN_requirement=\"\" IMAN_manifestation=\"\" pid=\"30\" current_name=\"\" project_ids=\"\" IMAN_external_object_link=\"\" protection=\"\" zws-domain=\"\" zws-server=\"\" zws-repository=\"\" zws-datasource-search-agent-name=\"\"/>  <binary-data origin=\"cisco|node-0|tc-10-imanfile|TC-10|BqLZZKOtxWJ9VB|null|null|IdeasDrawing|700-10957-01_ASM_w_conn|700-10957-01__000_4607589f.cpd|1184463327000|N/A|N/A|infodba (infodba)\" name=\"null\" IMAN_UG_wave_geometry=\"\" TC_CAE_Target=\"\" checked_out_change_id=\"\" MEProcess=\"\" project_list=\"\" item_revision=\"\" EC_affected_item_rel=\"\" has_variant_module=\"\" TC_CAE_Defining=\"\" checked_out_user=\"\" id_dispdefault=\"\" IMAN_UG_wave_position=\"\" IMAN_MEWorkInstruction=\"\" items_tag=\"\" IMAN_UG_wave_part_link=\"\" gc_updated_from_object_string=\"\" last_mod_date=\"14-Jul-2007 21:35\" process_stage=\"\" IMAN_aliasid=\"\" EC_reference_item_rel=\"\" IMAN_UG_promotion=\"\" has_module=\"\" IMAN_snapshot=\"\" altid_list=\"\" backup_date=\"\" owning_site=\"\" owning_group=\"dba\" object_desc=\"\" last_release_status=\"\" IMAN_Rendering=\"\" IMAN_specification=\"\" process_stage_list=\"\" archive_date=\"\" IMAN_classification=\"\" proj_assign_mod_date=\"\" date_released=\"\" based_on=\"\" OwningUser=\"infodba\" EC_problem_item_rel=\"\" acl_bits=\"0\" publication_sites=\"\" current_revision_id=\"\" reservation=\"\" TC_CAE_Source=\"\" IMAN_3D_snap_shot=\"\" item_master_tag=\"\" effectivity_text=\"\" TC_WorkContext_Relation=\"\" external_apps=\"\" variant_expression_block=\"\" last_mod_user=\"infodba\" IMAN_UG_udf=\"\" revision_number=\"\" intent_text=\"\" CreationDate=\"14-Jul-2007 21:35\" TC_ProductManual=\"\" current_job=\"\" is_frozen=\"\" Type=\"ImanFile\" structure_revisions=\"\" release_statuses=\"\" is_vi=\"\" IMAN_Simulation=\"\" declared_options=\"\" object_string=\"\" mvl_text=\"\" CAEAnalysis=\"\" current_id=\"\" current_desc=\"\" export_sites=\"\" is_modifiable=\"\" object_name=\"\" ics_subclass_name=\"\" revision_limit=\"\" projects_list=\"\" ics_classified=\"\" used_options=\"\" timestamp=\"BqNZZKOtxWJ9VB\" IMAN_UG_scenario=\"\" change=\"\" ItemID=\"\" wso_thread=\"\" EC_solution_item_rel=\"\" TC_CAE_Results=\"\" Revision=\"\" IMAN_Motion=\"\" object_application=\"\" IMAN_reference=\"\" view=\"\" IMAN_master_form_rev=\"\" release_status_list=\"\" expl_checkout=\"\" IMAN_UG_expression=\"\" TC_Generic_Architecture=\"\" gde_bvr_list=\"\" IMAN_UG_altrep=\"\" IMAN_MEMfgModel=\"\" EC_addressed_by_rel=\"\" MESetup=\"\" checked_out=\"\" checked_out_date=\"\" has_variants=\"\" EC_snapshot_rel=\"\" IMAN_requirement=\"\" IMAN_manifestation=\"\" pid=\"30\" current_name=\"\" project_ids=\"\" IMAN_external_object_link=\"\" protection=\"\" zws-domain=\"\" zws-server=\"\" zws-repository=\"\" zws-datasource-search-agent-name=\"\"/></binaries></metadata></results>");
    {} //System.out.println("1");
    //InputSource wrapping a StringReader wrapping your String,
    src = new InputSource(strRdr);
    {} //System.out.println("2");
    SearchResultHandler s = new SearchResultHandler();
    {} //System.out.println("3");
    XMLReader rdr = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
    {} //System.out.println("4");
    rdr.setContentHandler(s);
    {} //System.out.println("5");
    rdr.setFeature("http://xml.org/sax/features/validation", false);
    rdr.parse(src);
    {} //System.out.println("6");
  } catch (Exception e) {
      e.printStackTrace();
  }
}
}
