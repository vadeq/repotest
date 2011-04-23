package zws.service.chrysalis;

import java.io.Serializable;

/*

 import java.io.Serializable;
 DesignState - Design Compression Technology
 @author: eankutse
 @version: 1.0
 Created on Sep 5, 2007 3:43:15 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

/**
 * @author eankutse
 *
 */
public class ChrysalisRequestStatusInfo implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -3258662058903161729L;
  public static final String NO_TRANSLATION_RECORD_FOUND = "NoTranslationRequestRecordFound";
  public static final String ON_INPUT_QUEUE_STATUS = "OnInputQueue";
  public static final String TRANSLATION_IN_PROGRESS = "TranslationInProgress";
  public static final String DONE_STATUS_STR = "Done"; //translation done; output ready at Document Repository
  public static final String EXCEPTION_STATUS = "Exception - ";
  
  private String StatusString;
  private String resultsUrlStr;
  private String binaryFileName;
  /**
   * @param statusString
   * @param resultsUrlStr
   */
  public ChrysalisRequestStatusInfo() {
    super();
    StatusString = null;
    this.resultsUrlStr = null;
    binaryFileName = null;
  }
 
  /**
   * Is the processing of the request complete 
   * @return
   */
  public boolean statusIsDone()
  {
    return this.StatusString.equals(DONE_STATUS_STR);
  }
  
  public boolean statusIsException() {
    return this.StatusString.contains(EXCEPTION_STATUS);
  }
  
  public String toString() {
  
    StringBuffer buf = new StringBuffer();
    buf.append("STATUS INFO: ");
    //buf.append(super.toString() + " ");
    buf.append("StatusString=" + "'"+this.getStatus()+"'" + ", ");
    buf.append("resultsUrlStr=" + "'"+this.getResultsUrl()+"'");
    buf.append("binaryFileName=" + "'"+this.getBinaryFileName()+"'");
    return buf.toString();
  }
  
  public void setExceptionStatus(Exception e) {
    this.StatusString = ChrysalisRequestStatusInfo.EXCEPTION_STATUS + e.getMessage();
  }  
    
  public void setDoneStatus() {
    this.StatusString = ChrysalisRequestStatusInfo.DONE_STATUS_STR;
  }  
  
  public void setNoTranslationRecordFoundStatus() {
    this.StatusString = ChrysalisRequestStatusInfo.NO_TRANSLATION_RECORD_FOUND;    
  }
  
  public void setOnInputQueueStatus() {
    this.StatusString = ChrysalisRequestStatusInfo.ON_INPUT_QUEUE_STATUS;    
  }
  
  public void setTranslationInProgressStatus() {
    this.StatusString = ChrysalisRequestStatusInfo.TRANSLATION_IN_PROGRESS;    
  }

  
  public void setResultsUrl(String urlStr) {
    this.resultsUrlStr = urlStr;    
  }
  
  public String getStatus() {
    return this.StatusString;
  }
  
  public String getResultsUrl() {
    return this.resultsUrlStr;
  }
  
  public void setStatus(String status){
    this.StatusString = status;    
  }

  public void setUrlStr(String resZipUrl) {
    this.resultsUrlStr = resZipUrl;
  }

  public void setBinaryFileName(String binaryFileName) {
    this.binaryFileName = binaryFileName;
  }

  public String getBinaryFileName() {
    return binaryFileName;
  }

}
