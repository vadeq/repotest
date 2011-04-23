package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 26, 2004, 1:47 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;

public class NotUpToDate extends Exception {
	public NotUpToDate(Origin outOfDateVersion) {
    super(outOfDateVersion.toString());
    oldVersion=outOfDateVersion;
  }
	public NotUpToDate(Origin outOfDateVersion, Origin goodVersion) {
    super(outOfDateVersion.toString() + " is out of date from " + goodVersion);
    oldVersion=outOfDateVersion;
    newVersion=goodVersion;
  }
	public Origin getOldVersion() { return oldVersion; }
	public Origin getNewVersion() { return newVersion; }
	
	private Origin oldVersion=null;
	private Origin newVersion=null;
}
