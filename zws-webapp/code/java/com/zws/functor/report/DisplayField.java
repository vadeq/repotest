package com.zws.functor.report;

public class DisplayField {

  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public boolean getVisible() { return visible; }
  public void setVisible(boolean b) { visible=b; }

  private String name=null;
  private boolean visible=true;
}
