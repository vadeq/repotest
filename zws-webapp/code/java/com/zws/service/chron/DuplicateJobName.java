package com.zws.service.chron;
public class DuplicateJobName extends Exception {
  public DuplicateJobName() {super("Chron job already exists with this name");}
  public DuplicateJobName(String name) { super("Chron job called "+name+" already exists"); }
}