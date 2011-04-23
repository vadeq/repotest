/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Sep 27, 2007 3:35:19 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.element.transform.structure;

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.util.PrintUtil;

import java.util.Collection;

public class ListParents extends PENDataOpBase {
  
  public void execute() {
    Collection c = getPenData().reportWhereUsedInSource(getCurrentItem());
    {} //System.out.println("Parent List::::::::::::::::::::::::::::::::::");
    //PrintUtil.print(c);
    {} //System.out.println("::::::::::::::::::::::::::::::::::::::");
    
    setResult(c);
  }

}
