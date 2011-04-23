/*
 * DesignState - Design Compression Technology
 * @author: athakur @version: 1.0
 * Created on February 14, 2005, 12:21 AM
 * Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */
package zws.repository.ilink3.qx.program;

import zws.util.TimeUtil;
import zws.qx.program.QxInstruction;

import java.util.Calendar;

/**
 * FindLatest instruction.
 *
 * @author ptoleti
 */

public class ListEvents extends QxInstruction {
  
  public ListEvents(String firedAfter) {
    setName("list-event-history");
    set("fired-after", firedAfter);
  }

  public ListEvents(String firedAfter, String firedBefore) {
    setName("list-event-history");
    set("fired-after", firedAfter);
    set("fired-before", firedBefore);
  }

  public ListEvents(Calendar firedAfter) {
    setName("list-event-history");
    set("fired-after", TimeUtil.asString(firedAfter));
  }

  public ListEvents(Calendar firedAfter, Calendar firedBefore) {
    setName("list-event-history");
    set("fired-after", TimeUtil.asString(firedAfter));
    set("fired-before", TimeUtil.asString(firedBefore));
  }  
}
