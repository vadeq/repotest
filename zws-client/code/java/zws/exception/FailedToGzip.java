package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 2, 2004, 4:28 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.File;

public class FailedToGzip extends Exception {
 public FailedToGzip(File workingDirectory, String target) {
   super("Failed to gzip " + target + " from " + workingDirectory.getAbsolutePath()); 
 }
}