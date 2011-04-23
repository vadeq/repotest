package zws.queue;

import java.util.Comparator;
import java.io.Serializable;

public class QxElementComparator implements Comparator, Serializable {
  public QxElementComparator() {}

  public int compare(Object obj1, Object obj2) {
    if (obj1 instanceof QxElementRecord && obj2 instanceof QxElementRecord) { 
      return compare((QxElementRecord) obj1, (QxElementRecord) obj2); 
    }
    return 1;
  }

  public int compare(QxElementRecord er1, QxElementRecord er2) {
    long p1 = 0, p2 = 0;
    if (er1 != null) { p1 = er1.getPriority(); }
    if (er2 != null) { p1 = er2.getPriority(); }
    return (p1 < p2 ? 1 : -1);
  }
}
