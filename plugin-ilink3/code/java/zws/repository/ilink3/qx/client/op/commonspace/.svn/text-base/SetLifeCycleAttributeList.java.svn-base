package zws.repository.ilink3.qx.client.op.commonspace; /*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 29, 2004, 4:47 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.IntralinkOrigin;
import zws.util.MapUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class SetLifeCycleAttributeList extends IntralinkRepositoryOp {
	public void handleResponse(String outputFilename) throws Exception {}

	//Takes a map of attributes keyed by origin.
  protected void createOpInstructionXML(){
    openTag("set-life-cycle-attributes");
    endUnaryTag();
    Iterator j = attributeList.keySet().iterator();
    Map attributes;
    String field, value, name;
    IntralinkOrigin o;
    while (j.hasNext()) {
      o = (IntralinkOrigin)j.next();
      attributes = (Map)attributeList.get(o);
      value = null;
      field = null;
      Iterator i = attributes.keySet().iterator();     
      openTag("metadata");
      writeParameter("name", o.getName());
      writeParameter("branch", o.getBranch());
      writeParameter("revision", o.getRevision());
      writeParameter("version", o.getVersion());
      while (i.hasNext()) {
        field = i.next().toString();
        if (!attributes.containsKey(field)) continue;
        value = attributes.get(field).toString();
        writeParameter(field, value);
      }
      closeTag();
    }
    closeTag("set-life-cycle-attributes");
  }
  public void setAttributes(String name, Map atts) {
    Map m = MapUtil.getMapFromMap(attributeList, name);
    m.putAll(atts);
  }

  public String getWorkspaceName() { return workspaceName; }
	public void setWorkspaceName(String s) { workspaceName = s; }

  private Map attributeList = new HashMap();
	private String workspaceName=null;
}
