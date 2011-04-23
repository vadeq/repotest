package zws.datasource.intralink;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 6, 2004, 4:50 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.attribute.Enumeration;
import zws.util.StringValue;

public class ReleaseScheme {
  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public Enumeration getReleaseSequence() { return releaseSequence; }
  public void setReleaseSequence(Enumeration s) { releaseSequence=s; }
  public void add(String releaseLevel) { releaseSequence.add(releaseLevel); }
  public void add(StringValue releaseLevel) { releaseSequence.add(releaseLevel.getValue()); }

  private String name=null;
  private Enumeration releaseSequence= new Enumeration();
}
