package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.OpBase;

import java.io.*;

//eliminates invalid unicode Chars
public class UTF8Tidy extends OpBase {

  public UTF8Tidy() {}
  public UTF8Tidy(InputStreamReader in, OutputStreamWriter out) { setInputStream(in); setOutputStream(out); }

  public void execute() throws Exception {
    File f0 = null;
    File f1 = null;
    if (null!=getFilename()){
      FileInputStream in = null;
      FileOutputStream out = null;
      InputStreamReader reader = null;
      OutputStreamWriter writer = null;
      f0 = new File(getFilename()+"_temp");
      f1 = new File(getFilename());
      f1.renameTo(f0);
      in = new FileInputStream(f0);
      reader = new InputStreamReader(in);
      out = new FileOutputStream(f1);
      writer = new OutputStreamWriter(out);
      setInputStream(reader);
      setOutputStream(writer);
    }
    tidy();
    if (null!=f0 && f0.exists()) f0.delete();
  }

  private void tidy() throws Exception {
    BufferedReader reader = new BufferedReader( getInputStream() );
    int ch = 0;
    while( -1 < ch ){
      ch = reader.read();
      if (32 > ch && ch != 10 && ch !=13 && ch > -1) ch = 0x20;
      if ('&' == ch) {
        getOutputStream().write('&');
        getOutputStream().write('a');
        getOutputStream().write('m');
        getOutputStream().write('p');
        getOutputStream().write(';');
      }
      else if (-1 < ch) getOutputStream().write(ch);
    }
    reader.close();
    getOutputStream().close();
  }

  public String getFilename() { return filename; }
  public void setFilename(String s) { filename=s; }

  public InputStreamReader getInputStream() { return inputStream; }
  public void setInputStream(InputStreamReader r) { inputStream = r; }
  public OutputStreamWriter getOutputStream() { return outputStream; }
  public void setOutputStream(OutputStreamWriter w) { outputStream = w; }

  private String filename=null;
  private InputStreamReader inputStream;
  private OutputStreamWriter outputStream;
}
