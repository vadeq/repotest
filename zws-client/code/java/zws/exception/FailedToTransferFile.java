package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 2, 2004, 4:28 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.RemotePackage;

import java.io.File;

public class FailedToTransferFile extends Exception {
 public FailedToTransferFile(File file, String target) {
   super("Failed to transfer " + file.getAbsolutePath()+ " to " + target); 
 }
 public FailedToTransferFile(String fileName, String target) {
     super("Failed to transfer " + fileName+ " to " + target); 
   }
 public FailedToTransferFile(String fileName, String target, String msg) {
   super("Failed to transfer " + fileName+ " to " + target + ": " + msg); 
 }
 public FailedToTransferFile(RemotePackage remotePackage, File target, String msg) {
   super("Failed to transfer " + remotePackage.getUrl() + " to " + target + ": " + msg); 
 }

 public FailedToTransferFile(RemotePackage remotePackage, File target) {
   super("Failed to transfer " + remotePackage.getUrl() + " to " + target); 
 }
}
