package zws.hi.scheduler; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.scheduler.Task;

import com.zws.hi.hiItem;

public class hiTask extends hiItem {
  public void adapt(Object job) { task=(Task)job; }
  public hiTask() { super(); }
  public hiTask(Task job) { super(); adapt(job); }

  public String getName() { return task.getName(); }
  public void setName(String s) { task.setName(s); }

  private Task task=null;
}
