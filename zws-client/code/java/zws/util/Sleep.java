package zws.util; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Jul 20, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import java.lang.InterruptedException;

public class Sleep {
  public static void sleep(long millis) {
    try { Thread.sleep(millis); }
    catch (InterruptedException ignore) {}
  }
  
  public static void main(String[] args) {
    long time = 1000;
    if(null != args && args.length >0) {
    time = new Long(args[0]).longValue();
    }
    Sleep.sleep(time);
  }
}
