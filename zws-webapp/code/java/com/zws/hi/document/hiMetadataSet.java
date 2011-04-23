package com.zws.hi.document;
import com.zws.functor.report.DataReport;
import com.zws.functor.report.DisplayField;
import com.zws.hi.hiList;
import com.zws.service.config.DataReportService;
import com.zws.service.repository.MetadataConfigurator;

import java.util.Collection;
import java.util.Iterator;

public class hiMetadataSet extends hiList {
  public void refresh() { getItems().clear(); }
  public void bind() throws Exception {
    loadReport();
    getItems().addAll(getReport().getDisplayFields());
    loadVisibleAttributes();
  }

  public void loadVisibleAttributes(){
    DisplayField f;
    Iterator i = getReport().getVisibleDisplayFields().iterator();
    String[] atts = new String [getReport().getVisibleDisplayFields().size()];
    int idx=0;
    while (i.hasNext()) {
      f = (DisplayField)i.next();
      atts[idx++] = f.getName();
    }
    setVisibleAttributes(atts);
  }
  public void render() { setAttributeName(""); }
//  public void registerRequiredFields() { require("add", "attributeName"); }

  public String addAttribute() throws Exception {
    //Todo: error if attribute name exists
    if (null==getAttributeName() || "".equals(getAttributeName())) return ctrlLIST;
    /* temporarily disabled
    MetadataConfigurator.setVisibleAttributes(getVisibleAttributes());
    MetadataConfigurator.addAttribute(getAttributeName());
    */
    return ctrlLIST;
  }

  public String save() throws Exception{
    int idx=0;
    getReport().clearVisibleDisplayFields();
    for (idx=0; idx < getVisibleAttributes().length; idx++)
      getReport().setVisible(getVisibleAttributes()[idx]);
    return ctrlLIST;
  }

  public String delete() throws Exception {
    validateRequiredInputFields();
    /* temporarily disabled
    MetadataConfigurator.removeAttribute(getAttributeName());
    */
    return ctrlLIST;
  }

  public String[] getVisibleAttributes() { return visibleAttributes; }
  public void setVisibleAttributes(String[] atts) { visibleAttributes = atts; }
  public String getAttributeName() { return attributeName; }
  public void setAttributeName(String name) { attributeName=name; }


  public String getActiveReportName() {
    if (null==reportName) {
      Iterator i = DataReportService.getDisplayReportNames().iterator();
      if (i.hasNext()) reportName=i.next().toString();
    }
    return reportName;
  }
  public void setActiveReportName(String s) { reportName=s; loadReport(); }

  private void loadReport() {
    try { report=(DataReport)DataReportService.findDisplayReport(getActiveReportName()).copy(); }
    catch (Exception e) { e.printStackTrace(); } //no data reports have been configured
  }

  public Collection getReportNames() { return DataReportService.getDisplayReportNames(); }
  public DataReport getReport() { return report; }

  private String reportName=null;
  private DataReport report=null;
  private String attributeName;
  private String[] visibleAttributes;
}
