/**
 *
 */
package zws.hi.publish;

import java.sql.Timestamp;


/* DesignState - Design Compression Technology
 @author: D Reddy
 @version: 1.0
 Created on Jun 8, 2007 10:11:23 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

/**
 * @author D Reddy
 *
 */
public class publishDo {

  private String nameId=null;
  private Timestamp reqTime= null;
  private StringBuffer duration=null;
  private String status= null;

  /**
   * @return the nameId
   */
  public String getNameId() {
    return nameId;
  }
  /**
   * @param nameId the nameId to set
   */
  public void setNameId(String nameId) {
    this.nameId = nameId;
  }
  /**
   * @return the reqTime
   */
  public Timestamp getReqTime() {
    return reqTime;
  }
  /**
   * @param reqTime the reqTime to set
   */
  public void setReqTime(Timestamp reqTime) {
    this.reqTime = reqTime;
  }
  /**
   * @return the status
   */
  public String getStatus() {
    return status;
  }
  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }
  /**
   * @return the duration
   */
  public StringBuffer getDuration() {
    return duration;
  }
  /**
   * @param duration the duration to set
   */
  public void setDuration(StringBuffer duration) {
    this.duration = duration;
  }


}