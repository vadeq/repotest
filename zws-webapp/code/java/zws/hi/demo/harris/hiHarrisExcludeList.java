package zws.hi.demo.harris; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.data.*;
import zws.origin.*;
import zws.application.Properties;
import zws.AgileAccess;
import zws.IntralinkAccess;
import zws.application.Names;
import zws.application.Properties;
import zws.hi.report.MetadataAdapter;

import java.util.*;

public class hiHarrisExcludeList extends hiHarrisReport {
  public String getSelectedReportName() { return Properties.get("demo-report-harris"); }
  public Collection getChosenItems() { return getExcludeList(); }
  private Collection getExcludeList() { return hiHarrisDemo.excludeList; }
}
