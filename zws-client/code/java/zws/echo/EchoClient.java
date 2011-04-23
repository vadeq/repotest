package zws.echo;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on November 10, 2004, 7:31 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class EchoClient {
  public EchoClient() { }
  public static void main(String[] args) {
    try {
      Socket echoSocket = new Socket("zero-88", 8887);
      OutputStream os = echoSocket.getOutputStream();
      DataInputStream is = new DataInputStream(echoSocket.getInputStream());
      BufferedReader d = new BufferedReader(new InputStreamReader(is));      
      int c;
      String responseLine;

      while ((c = System.in.read()) != '.') {
        os.write((byte)c);
        if (c == '\n') {
          os.flush();
          responseLine = d.readLine();
          {} //System.out.println("echo: " + responseLine);
        }
      }
      {} //System.out.println("recieved end signal: ");
      os.close();
      is.close();
      d.close();
      echoSocket.close();
    } 
    catch (Exception e) {
      System.err.println("Exception:  " + e);
    }
  }
}


