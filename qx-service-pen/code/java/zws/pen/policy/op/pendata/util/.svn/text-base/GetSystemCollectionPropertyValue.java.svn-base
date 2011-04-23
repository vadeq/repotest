/* DesignState - Design Compression Technology
 * @author: Rodney
 * @version: 1.0
 * Created on Mar 20, 2008 9:19:03 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.util;

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;
import java.util.Collection;

public class GetSystemCollectionPropertyValue extends PENDataOpBase{

  private static final long serialVersionUID = 0;
  private Collection collection = null;
  private String name = null;
  
  public void execute() throws Exception {      
    collection = zws.application.Properties.getCollection(getProperty());
  }

  public Object getResult() { return collection; }

  public String getProperty()       { return name; }
  public void setProperty(String value) { name = value;}  
}
