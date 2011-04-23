/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */
package zws.pen.policy.op.pendata.custom.harris;

import zws.data.Metadata;
import zws.pen.policy.op.pendata.PENDataOpBase;



public class GetNewPRNotes extends PENDataOpBase {

  public void execute() {
    String newNotes = "";
    loadPrNotes();
    loadSimilarTo();
    
    if(prNotes.length() == 0 &&  similarTo.length() ==0 ) {					// case 1
    	newNotes = "";

    } else if(prNotes.length() != 0 && similarTo.length() == 0) {			// case 2
    	newNotes = prNotes.trim();
    
    } else if(prNotes.indexOf(str_similar_To) != -1 && similarTo.length() != 0) {	// case 3
    	newNotes = replaceOldPRNotes();
    	
    } else if(prNotes.length() == 0 && similarTo.length() != 0) {			// case4
    	newNotes = str_similar_To.trim() + space +  similarTo.trim();
    	
    } else if(prNotes.trim().equalsIgnoreCase(str_similar_To) && similarTo.length() != 0) {	// case 5
    	newNotes = prNotes.trim() + space +  similarTo.trim();
   	
    } else if(prNotes.indexOf(str_similar_To) == -1 && similarTo.length() != 0) {	// case 6
    	newNotes = prNotes.trim() + space + str_similar_To.trim() + space + similarTo.trim();
    }
    
    setResult(newNotes);
  }
  
  private String replaceOldPRNotes() {
	  // similarTo = AAAAAAA
	  // OLD PR Notes: xxxxxxxxxxxx Similar to ZZZZZZ xxxxxxxxxx
	  // NEW PR Notes: xxxxxxxxxxxx Similar to AAAAAA xxxxxxxxxx
	  
	  String firstEnd = prNotes.substring(0,prNotes.indexOf(str_similar_To));
	  String lastEnd = prNotes.substring(prNotes.indexOf(str_similar_To)+str_similar_To.length(), prNotes.length()).trim();
	  lastEnd = lastEnd.substring(lastEnd.indexOf(space)+1, lastEnd.length());
	  return firstEnd.trim() + space +  str_similar_To.trim() + space + similarTo.trim() + space + lastEnd.trim();
  }

  //		<get-target-value attribute="pr notes" /> 
	//<get-source-value attribute="Similar_To" />
  public void loadPrNotes() {
	  Metadata targetData = lookupTargetMetadata(getCurrentItem());
	  if(null != targetData){
		  prNotes = targetData.get("pr notes");
	  }
	    if(null == prNotes) {prNotes = "";}
	    prNotes = prNotes.trim();
  }
  public void loadSimilarTo() {
	  Metadata srcData = lookupSrcMetadata(getCurrentItem());
	  if(null != srcData){
		  similarTo = srcData.get("Similar_To");
	  }
	    if(null == similarTo) {similarTo = "";}	    
	    similarTo = similarTo.trim();	  	  
  }

  private String prNotes = null;
  private String similarTo = null;
  static String str_similar_To = "Similar to";
  static String space = " ";

}
