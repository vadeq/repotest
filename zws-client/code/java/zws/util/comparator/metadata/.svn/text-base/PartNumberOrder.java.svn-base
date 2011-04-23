package zws.util.comparator.metadata; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on May 4, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.util.comparator.AlphaNumericComparator;
import java.util.Comparator;
import zws.application.Names;
import java.util.StringTokenizer;

public class PartNumberOrder implements Comparator {

  public int compare(Object data0, Object data1) { return compare ((Metadata)data0, (Metadata)data1); }

  public int compare(Metadata data0, Metadata data1) {
    String s0 = makeKey(data0);
    String s1 = makeKey(data1);
    {} //System.out.println("comparing "+s0+" to "+s1);
    return comparator.compare(s0, s1);
  }

  private String makeKey(Metadata data) {
    if(keyFields==null || "".equals(keyFields.trim())) {
      {} //System.out.println("**************************************");   
      {} //System.out.println("Key Fields not set for PartNumberOrder");   
      {} //System.out.println("*************************************");   
     keyFields="name";
    }
    StringTokenizer tokens = new StringTokenizer(keyFields, Names.DELIMITER);
    if (tokens.countTokens()<1) return data.get(keyFields.trim());
    String s="";
    while(tokens.hasMoreTokens()) {
     s+=data.get(tokens.nextToken().trim());
    }
    return s;
  }
  
  public String getKeyFields() { return keyFields; }
  public void setKeyFields(String s) { keyFields=s; }

  public void setAscendingOrder(boolean b) { comparator.setAscendingOrder(b); }
  public boolean getAscendingOrder() { return comparator.getAscendingOrder(); }
  
  private String keyFields="name"; 
  private static AlphaNumericComparator comparator = new AlphaNumericComparator();
}
