package zws.hi.demo.kla; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.application.Properties;

import java.util.Collection;

public class hiKLAExcludeList extends hiKLAReport {
  public String getSelectedReportName() { return Properties.get("demo-report-harris"); }
  public Collection getChosenItems() { return getExcludeList(); }
  private Collection getExcludeList() { return hiKLADemo.excludeList; }
}
