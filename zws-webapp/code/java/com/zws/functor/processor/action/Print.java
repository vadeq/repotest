package com.zws.functor.processor.action;

public class Print extends Action {

  public void execute() { {} //System.out.println(getLine()); }
    
    
  }

  public String getLine() { return line; }
  public void setLine(String s) { line=s; }

  private String line;
}
