package zws.qx.test;

/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Mar 25, 2007 11:06:40 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.QxTransferBase;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
//import zws.util.{}//Logwriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

public class TestQxService extends QxTransferBase implements Qx {
  
 public QxXML executeQx(QxContext ctx, QxXML instruction) {
   // create zws.qx.op.QxInstruction by readig in instructionXML
   //QxInstruction qxInstruction = new QxInstruction();
   
   // if instruction == delete file then get filename and call deletebinaryfile(filename)
   // instruction = tagname
   // properties = tag attributes
   QxInstruction p = instruction.toQxProgram();
   int number = Integer.parseInt(p.getProperties().get("number").toString());
   QxXML response = new QxXML("<square number='"+number*number+"'/>");
   
   return response;
 }

 public QxXML transferQx(QxContext ctx, QxXML instruction, DataHandler binary) {
   QxXML response = null;
   File outFile = new File("/qxbinary.dat");
   if (outFile.exists()) {
     response = new QxXML("<exception message='file already exists' path='"+outFile.getAbsolutePath()+"'/>");
     return response;
   }
   FileOutputStream out = null;
   try {
     out = new FileOutputStream(outFile);
     {}//Logwriter.printOnConsole("Writing to file");
     binary.writeTo(out);
     {}//Logwriter.printOnConsole("Completed writing to file");
     response = new QxXML("<saved path='"+outFile.getAbsolutePath()+"' size='"+outFile.length()+"'/>");
  } catch (IOException e) {
      response = new QxXML("<exception message='"+e.getMessage()+"' path='"+outFile.getAbsolutePath()+"' size='"+outFile.length()+"'/>");
      e.printStackTrace();
  }finally {
    if( out != null ){
      try {
        {}//Logwriter.printOnConsole("Releasing resources.");
        out.close();
        {}//Logwriter.printOnConsole("Closed outputStream");
        binary.getInputStream().close();
        {}//Logwriter.printOnConsole("Closed InputStream");
      } catch(Exception ex){
        {}//Logwriter.printOnConsole("ExCeption Message: "+ ex.getMessage());
      }
    }
  }
  {}//Logwriter.printOnConsole("At END, the response is: "+ response);
   return response;
 }

 public DataHandler transferQx(QxContext ctx, QxXML instruction) {
   DataHandler binary=null;
   /*
    * retrieve filename from ctx
    * find filename
    * throw exception if not found
    * create file data source for datahandler
    * return datahandler
    */
   File outFile = new File("/qxbinary.dat");
   if( ! outFile.exists() ) {
     System.err.println("File does not exist");
    // throw new Exception("Expected File does not exist ");
   }
   FileDataSource outDataSource = new FileDataSource(outFile);
   binary = new DataHandler(outDataSource);
   /*try{
     outDataSource.getOutputStream().close();
   } catch(Exception ex){
     {}//Logwriter.printOnConsole(ex.getMessage());
   }*/
   return binary;
 }

 
 private void deleteBinaryFile(String filename) {
   
 }
 
}
