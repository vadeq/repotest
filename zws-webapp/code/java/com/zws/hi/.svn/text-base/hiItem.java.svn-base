package com.zws.hi;
import zws.util.AdapterPrototype;

public abstract class hiItem extends Interactor implements AdapterPrototype {
  public hiItem() { super(); }
  public hiItem(Object o) { super(o); }
  public void initHandling() throws Exception { bind(); }

  //default event handlers
  public String reload() { return ctrlOK; }
  
  public void adapt(Object domo) {}
  public Object copy() { if (supportsDeepCopy()) return deepCopy(); else return shallowCopy(); }
  public Object deepCopy() { throw new RuntimeException("Deep copy not supported"); }
  public Object shallowCopy() { try {return super.clone(); } catch (CloneNotSupportedException e) { throw new RuntimeException(e.getMessage()); } }
  public boolean supportsDeepCopy() { return false; }
  
  public void inactivate() {};
}
