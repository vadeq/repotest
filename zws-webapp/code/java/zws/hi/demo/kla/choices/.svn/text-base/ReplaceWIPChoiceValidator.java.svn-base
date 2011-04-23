package zws.hi.demo.kla.choices; 
/*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Nov 14, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.hi.demo.kla.hiKLAReport;
import zws.hi.demo.kla.KLAMetadataAdapter;

public class ReplaceWIPChoiceValidator extends ChoiceValidatorBase {

  public boolean isValidChoice(KLAMetadataAdapter adapter, hiKLAReport report) {
    if (!adapter.getWasPreviouslyPublished()) {
      report.logFormWarning("warn.not.ever.published", adapter.getName());
      return false;
    }
    if (adapter.getIsPublished()) {
	    report.logFormWarning("warn.already.published", adapter.getOrigin().getUniqueID());
	    return false;
	  }
    
    return true;
  }
}
