package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 17, 2004, 9:45 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.exception.FailedToTransferFile;
import zws.exception.NotADirectory;

import java.io.*;
import java.nio.channels.*;

public class FileUtil {

  public void writeFile(File out, String txt) throws Exception {
    if (out.exists()) throw new zws.exception.Duplicate(out.getAbsolutePath());
    OutputStream ostream = new java.io.FileOutputStream(out);
    ostream.write(txt.getBytes());
    ostream.close();
  }

  public static void save(InputStream iStream, File out, boolean closeInputWhenComplete) throws FileNotFoundException, IOException{
   File parent = out.getParentFile();
   if (!parent.exists()) parent.mkdirs();
   OutputStream oStream = new FileOutputStream(out);
   for (int c = iStream.read(); c!=-1; c = iStream.read()) oStream.write(c);
   if (closeInputWhenComplete) iStream.close();
   oStream.close();
  }

  public static void save(String data, File out, boolean closeInputWhenComplete) throws FileNotFoundException, IOException{
    File parent = out.getParentFile();
    if (!parent.exists()) parent.mkdirs();
    Writer w = new FileWriter(out);
    w.write(data);
    if (closeInputWhenComplete) w.close();
   }

  public static void appendInNewLine(String data, String fileName, boolean closeWhenComplete) throws FileNotFoundException, IOException{
    File file = new File(fileName);
    append(Names.NEW_LINE + data, file, closeWhenComplete);
   }
  
  public static void appendWithNewLine(String data, String fileName, boolean closeWhenComplete) throws FileNotFoundException, IOException{
    File file = new File(fileName);
    append(data + Names.NEW_LINE , file, closeWhenComplete);
   }

  public static void append(String data, String fileName, boolean closeWhenComplete) throws FileNotFoundException, IOException{
    File file = new File(fileName);
    append(data, file, closeWhenComplete);
   }

  public static void append(String data, File out, boolean closeWhenComplete) throws FileNotFoundException, IOException{
    File parent = out.getParentFile();
    if (!parent.exists()) parent.mkdirs();
    Writer w = new FileWriter(out, true);
    w.write(data);
    if (closeWhenComplete) w.close();
   }

  /*
  public static void copy(File source, File target) throws FileNotFoundException, IOException {
   FileInputStream iStream = new FileInputStream(source);
   save(iStream, target, true);
  }
  */

  public static void copy(File source, File target) throws IOException, FailedToTransferFile {
    //copy files from one directory to another
    if (source.isDirectory()) {
      if (!target.isDirectory()) throw new FailedToTransferFile(source.getAbsolutePath(), target.getAbsolutePath(), "source is a directory and target is a file!");
      File[]files = source.listFiles();
      File t;
      File f;
      for (int i = 0; i < files.length; i++) {
        f = files[i];
        if (!f.isFile()) continue;
        t = new File(target, f.getName());
        copy (f, t);
      }
      return;
    }

    //copy a file
		// get channels
		FileInputStream fis =  new FileInputStream(source);
		FileOutputStream fos = new FileOutputStream(target);
		FileChannel fcin = fis.getChannel();
		FileChannel fcout = fos.getChannel();

		// do the file copy
		fcin.transferTo(0, fcin.size(), fcout);

		// finish up
		fcin.close();
		fcout.close();
		fis.close();
		fos.close();
	}

  /*
  public static void load(File source, File target) throws FileNotFoundException, IOException {
   FileInputStream iStream = new FileInputStream(source);
   save(iStream, target, true);
  }
  */
  public static String read(File source) throws IOException {
    if (!source.exists()) return null;
    FileInputStream fis =  new FileInputStream(source);
    byte[] bytes;
    bytes = new byte[(int)source.length()];
    fis.read(bytes);
    fis.close();
    return new String(bytes);
  }

  //BufferedReader in = new BufferedReader(new FileReader("foo.in"));
  public String readLine(BufferedReader input) throws IOException {
    String line = input.  readLine();
    return line;
  }

  public static void deleteContents(File dir) throws NotADirectory, Exception {
    if (!dir.exists() || !dir.isDirectory()) throw new NotADirectory(dir);
    DeleteFile op = new DeleteFile();
    op.setDeleteIfNotEmpty(true);
    File[] list = dir.listFiles();
    for (int i=0; i<list.length; i++) {
      op.setFile(list[i]);
      op.execute();
    }
  }

  public static void deleteFiles(File dir) throws NotADirectory, Exception {
    if (!dir.exists() || !dir.isDirectory()) throw new NotADirectory(dir);
    File[] list = dir.listFiles();
    for (int i=0; i<list.length; i++) {
      if (!list[i].isFile()) continue;
      list[i].delete();
    }
  }

  public static void delete(String targetPath) throws Exception {
    File target = new File(targetPath);
    delete(target);
  }

  public static void delete(File target) throws Exception {
    if (!target.exists()) return;
    if (target.isFile()) target.delete();
    else {
      DeleteFile op = new DeleteFile();
      op.setDeleteIfNotEmpty(true);
      op.setFile(target);
      op.execute();
    }
  }

  public static void move(File file, File target) throws Exception {
    if (!target.exists() || !file.exists()) return;
    if (!file.isFile() || !target.isDirectory()) return;
    else {
      CopyFile op = new CopyFile();
      op.setSource(file);
      op.setToDir(target);
      op.execute();

      DeleteFile delOP = new DeleteFile();
      delOP.setDeleteIfNotEmpty(true);
      delOP.setFile(file);
      delOP.execute();
    }
  }

}
