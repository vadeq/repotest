package com.zws.util.stream;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StreamableIterator implements Iterator {
  protected StreamableIterator(StreamableCollection s) { stream=s; }

  private Object getCurrent() { return stream.item(idx); }
  public boolean hasNext() {
    if (stream.isOpen() || idx<stream.size()) return true; else return false;
  }
  public Object next() throws NoSuchElementException {
    if (!stream.isOpen() && stream.size()==idx) throw new NoSuchElementException();
    int size = stream.size();
    try { while(stream.size()<=idx && stream.isOpen()) Thread.sleep(200); } catch (Exception e) {e.printStackTrace();}
    return stream.item(idx++);
  }
  public void remove(){ stream.remove(getCurrent()); }
  private StreamableCollection stream=null;
  private int idx=0;
}
