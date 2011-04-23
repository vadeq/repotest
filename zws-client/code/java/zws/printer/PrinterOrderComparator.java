package zws.printer; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Jul 6, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import java.util.Comparator;

public class PrinterOrderComparator implements Comparator{
    public int compare(Object o1, Object o2) { return compare((Printer)o1, (Printer)o2); }
    public int compare(Printer p1, Printer p2) {
      if (p1.getOrder()>p2.getOrder()) return 1;
      if (p1.getOrder()<p2.getOrder()) return -1;
      return 0;
    }
}
