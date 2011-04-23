/*
 DesignState - Design Compression Technology
 @author: arbthaku
 @version: 1.0
 Created on Dec 8, 2007 3:51:55 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.service.file.depot.test;

import java.io.File;

import zws.service.file.depot.FileDepotClient;
import zws.util.RemotePackage;

public class Test {

  public static void main(String[] args) {
    Test t = new Test();
    if (args.length!=1) showUsage();
    String remoteHost = args[0];
    try { t.run(remoteHost); }
    catch(Exception e) { e.printStackTrace(); }
  }

  private void run(String remoteHost) throws Exception {
    FileDepotClient client = FileDepotClient.materializeClient(remoteHost, "C:\\temp\\file-depot");
    File localFile= new File("C:\\temp\\file-depot\\store\\file\\1file.txt");
    File localDirectory= new File("C:\\temp\\file-depot\\store\\directory");
    int count = localDirectory.listFiles().length + 1;
    File localFiles[] = new File[count];
    
    for(int i = 0; i < count-1; i++) localFiles[i]=localDirectory.listFiles()[i];
    localFiles[count-1] = localFile;

    RemotePackage rFile = client.storeFile(localFile);
    {} //System.out.println("Stored: " + rFile.getUrl());
    RemotePackage rDirectory = client.storeDirectory(localDirectory, "Storage-Directory-Name");
    {} //System.out.println("Stored: " + rDirectory .getUrl());
    RemotePackage rFiles = client.storeFiles(localFiles, "Storage-File-List-Name");
    {} //System.out.println("Stored: " + rFiles.getUrl());

    File retrievedFileToDir= new File("C:\\temp\\file-depot\\retrieve\\file");
    File retrievedFilesToDir= new File("C:\\temp\\file-depot\\retrieve\\files");
    File retrievedDirectoryToDir= new File("C:\\temp\\file-depot\\retrieve\\directory");

    client.retrieve(rFile, retrievedFileToDir);
    client.retrieve(rFiles, retrievedFilesToDir);
    client.retrieve(rDirectory, retrievedDirectoryToDir);
    
    {} //System.out.println("File: " + retrievedFileToDir.list());
    {} //System.out.println("Files: " + retrievedFilesToDir.list());
    {} //System.out.println("Directory: " +retrievedDirectoryToDir.list());
  }
  
  private static void showUsage() {
    {} //System.out.println("usage: java zws.service.file.depot.test.Test [remote file-depot hostname]");
    System.exit(1);
  }
}
