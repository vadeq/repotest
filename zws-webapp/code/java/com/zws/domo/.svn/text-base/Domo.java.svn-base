package com.zws.domo;
import com.zws.service.IDGenerator;

public class Domo {
  public Domo(){ } //blank domo
  public Domo(boolean ignore)  { setNewID(); } //domo with id set automatically
  public String getID() { return ID; }
  public void setID(String s) { ID=s; }
  public boolean isValid() { return true; }
  private String setNewID(){ setID(IDGenerator.getnewID()); return getID(); }
  private String ID;
}
