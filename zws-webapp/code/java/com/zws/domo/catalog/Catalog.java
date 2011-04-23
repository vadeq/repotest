package com.zws.domo.catalog;

//A Catalog is simply a top level category which has no parent.
public class Catalog extends Category {
  public Catalog() {}
  public Category getParent() { return null;}
  public void setParent() { throw new RuntimeException("A catalog can have no parent"); }
}
