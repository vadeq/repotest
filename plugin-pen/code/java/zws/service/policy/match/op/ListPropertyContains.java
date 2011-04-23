package zws.service.policy.match.op;
/*
 * DesignState - Design Compression Technology
 * @author:ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.application.Properties;
import zws.data.Metadata;
//impoer zws.util.{}//Logwriter;

import java.util.Collection;
import java.util.Iterator;

/**
 * The Class CompareAttributeOP.
 *
 * @author ptoleti
 */
public class ListPropertyContains extends PolicyMatchOpBase {

  /**
   * @throws Exception
   * Exception
   * @see zws.pen.policy.op.PENDataOpBase#execute()
   */
  public void execute() throws Exception {

    Collection haystack = Properties.getCollection(getProperty());
    boolean found = false;
    String data = null;
    
    try {

      Metadata metaData = getMetaData();
      data = metaData.get(getAttribute());
      
      if (data != null) {
        
        Iterator iterator = haystack.iterator();
        Object item;
        String value;

        if ( isIgnoreCase() ) data = data.toLowerCase().trim();
        
        while(iterator.hasNext()) {        
          item = iterator.next();
          if (item == null) continue;
          if (isIgnoreCase()) value = item.toString().toLowerCase();
          else value = item.toString();
     
          value.trim();
          
          if (value.equals(data)) {
            found = true;
            break;
          }
        }        
      }
      setResult( new Boolean(found).toString() );
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }    
  }

  private String property = null;
  private String attribute = null;
  private boolean ignoreCase = false;
  
  public String getProperty()             { return property; }
  public void setProperty(String value)   { property = value; }

  public String getAttribute()            { return attribute; }
  public void setAttribute(String value)  { attribute = value; }
  
  public boolean isIgnoreCase()           { return ignoreCase; }
  public void setIgnoreCase(boolean b)    { ignoreCase = b; }   
}
