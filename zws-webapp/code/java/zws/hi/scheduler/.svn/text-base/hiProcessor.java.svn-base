package zws.hi.scheduler; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.processor.Processor;

import com.zws.hi.hiItem;

public class hiProcessor extends hiItem {
  public void adapt(Object process) { processor=(Processor)process; }
  public hiProcessor() { super(); }
  public hiProcessor(Processor process) { super(); adapt(process); }

  public String getName() { return processor.getName(); }
  public Processor getProcessor() { return processor; }
  
  private Processor processor=null;
}
