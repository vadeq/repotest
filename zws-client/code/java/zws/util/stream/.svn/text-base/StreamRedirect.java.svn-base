package zws.util.stream;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.*;

public class StreamRedirect extends Thread {
    InputStream in;
    PrintStream redirect = System.out;

    public void setRedirectStream( PrintStream s) { redirect = s; }
    public StreamRedirect(InputStream s){ in = s; }
    public StreamRedirect(InputStream s, PrintStream out){ in = s; redirect = out; }
    public void run() {
      try {
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);
        int data=0;
        while ( -1 < (data=br.read())) redirect.print(String.valueOf((new Character((char)data)).charValue()));
      }
      catch (IOException ioe) { ioe.printStackTrace(); }
    }
}

