/**
 * 
 */
package zws.util;

/*
 DesignState - Design Compression Technology
 @author: eankutse
 @version: 1.0
 Created on Aug 27, 2007 7:23:51 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * @author eankutse
 *
 */
public class ZipFileUtility {
  
  
  private void addFilesAndDirsToZip(File dir, ZipOutputStream out) 
  throws FileNotFoundException, IOException   {
    byte[] buffer = new byte[18024];
    File[] filesToZip = dir.listFiles();
    // iterate through the array of files, adding each to the zip file
    try {
  for (int i = 0; i < filesToZip.length; i++) {
   File currFile = filesToZip[i];

      if (currFile.isDirectory())
       addFilesAndDirsToZip(currFile, out);
      else {        
          // Add ZIP entry to output stream.
          out.putNextEntry(new ZipEntry(currFile.getPath()));

       // Associate a file input stream for the current file
       FileInputStream in = new FileInputStream(currFile);

       int len;
       while ((len = in.read(buffer)) > 0)
       {
         out.write(buffer, 0, len);
       }
       // Close the current file input stream
       in.close();
      }
      // Close the current entry
      out.closeEntry();
  }//for
   } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    throw e;
   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    throw e;
   }
 }//addFilesAndDirsToZip

  
  private void addFilesOnlyToZip(File dir, ZipOutputStream out) throws FileNotFoundException, IOException {
    addFilesOnlyToZip(dir.listFiles(), out);
  }
  
  private void addFilesOnlyToZip(File[] filesToZip, ZipOutputStream out) throws FileNotFoundException, IOException {
    byte[] buffer = new byte[18024];
    // iterate through the array of files, adding each to the zip file
    try {
  for (int i = 0; i < filesToZip.length; i++) {
   File currFile = filesToZip[i];

      if (!currFile.isDirectory()) {        
       // Add ZIP entry to output stream.
       out.putNextEntry(new ZipEntry(currFile.getName()));

       // Associate a file input stream for the current file
       FileInputStream in = new FileInputStream(currFile);

       int len;
       while ((len = in.read(buffer)) > 0)
       {
         out.write(buffer, 0, len);
       }
       // Close the current file input stream
       in.close();
      }
      // Close the current entry
      out.closeEntry();
  }//for
   } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    throw e;
   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    throw e;
   }
 }//addFilesOnlyToZip
  
/**
 * Zip entire directory structure with the files
 */
 public void zipHierarchical(String zipFileFullPathName, String fullPathParentDirOfFiles) 
 throws IllegalArgumentException, FileNotFoundException, IOException  {
   try {
     ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileFullPathName));
     File currDir = new File(fullPathParentDirOfFiles);

     // Set the compression ratio
     out.setLevel(Deflater.DEFAULT_COMPRESSION);
  
     addFilesAndDirsToZip(currDir, out);
  
     //close the ZipOutPutStream
        out.close();
    }
    catch (IllegalArgumentException iae) {
      iae.printStackTrace();
      throw iae;
    }
    catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
      throw fnfe;
    }
    catch (IOException ioe)
    {
    ioe.printStackTrace();
    throw ioe;
    }
  }//zip

 /**
  * Zip only the files without directory struture
  */
 public void zipFlat(String zipFileFullPathName, String fullPathParentDirOfFiles)  throws IllegalArgumentException, FileNotFoundException, IOException {
   zipFlat(zipFileFullPathName, new File (fullPathParentDirOfFiles));
  }//zip
 
 
 public void zipFlat(String zipFileFullPathName, File file) throws IllegalArgumentException, FileNotFoundException, IOException {
   if (file.isFile()) zipFlat(zipFileFullPathName, new File[] {file});   
   else zipFlat(zipFileFullPathName, file.listFiles());   
 }

 public void zipFlat(String zipFileFullPathName, File[] files) throws IllegalArgumentException, FileNotFoundException, IOException {
   try {
     ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileFullPathName));

     // Set the compression ratio
     out.setLevel(Deflater.DEFAULT_COMPRESSION);
  
     addFilesOnlyToZip(files, out);   
  
     //close the ZipOutPutStream
        out.close();
    }
    catch (IllegalArgumentException iae) {
      iae.printStackTrace();
      throw iae;
    }
    catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
      throw fnfe;
    }
    catch (IOException ioe)
    {
    ioe.printStackTrace();
    throw ioe;
    }
   
 }

/**
 * Extract the contents of zipFile into location destDir
 */
 public File[] unzip(File zipFile, String destDir) throws IOException{  
   try {
     int numFilesInJar = 0;
     {}//Logwriter.printOnConsole("");
     {}//Logwriter.printOnConsole("Extracting contents of " + zipFile.getPath() + "...");

     java.util.jar.JarFile jar = new java.util.jar.JarFile(zipFile);
     numFilesInJar = jar.size();

     {}//Logwriter.printOnConsole("("+numFilesInJar+" files total" + ")");
     
     File[] fileList = new File[numFilesInJar]; 
     int idx = 0;
     
     java.util.Enumeration e = jar.entries();
     while (e.hasMoreElements()) {
       java.util.jar.JarEntry file = (java.util.jar.JarEntry) e.nextElement();
       java.io.File f = new java.io.File(destDir + java.io.File.separator + file.getName());
       
       fileList[idx++] = f;
       
       if (file.isDirectory()) { // if its a directory, create it
         {}//Logwriter.printOnConsole("  [" +idx+" of "+numFilesInJar + "]:   " + file.getName() + " (Directory)");         
         f.mkdir();
         continue;
       }
       
       {}//Logwriter.printOnConsole("  [" +idx+" of "+numFilesInJar + "]:   " + file.getName() + " (" + file.getSize()/1025 +" Kb"+ ")");
       
       java.io.InputStream is = jar.getInputStream(file); // get the input stream
       java.io.FileOutputStream fos = new java.io.FileOutputStream(f);
       
       int avail = 0;
       while ((avail=is.available()) > 0) {  // write contents of 'is' to 'fos'
         byte [] data = new byte[avail];
         int rd = is.read(data, 0, avail);
         fos.write(data, 0, rd);
       }
       fos.close();
       is.close();      
     } 
     jar.close(); 

     {}//Logwriter.printOnConsole("DONE Extracting contents of " + zipFile.getPath() + "("+numFilesInJar+" files total" + ")");
     {}//Logwriter.printOnConsole("");
     
     return fileList;
   }catch(IOException e) { 
     e.printStackTrace(); 
     throw e;
   }
 }
  
}
