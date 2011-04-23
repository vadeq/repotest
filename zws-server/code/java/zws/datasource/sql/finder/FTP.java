package zws.datasource.sql.finder;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 6, 2003, 7:43 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.sql.SQLSource;
import zws.op.OpBase;
import zws.origin.Origin;
////import zws.util.{}//Logwriter;

import java.io.UnsupportedEncodingException;
import java.net.*;

public class FTP extends OpBase {

public void execute() throws Exception { setResult(find()); }
public URL find() throws MalformedURLException { 
  finder.setUsername(getValue(username));
  finder.setPassword(getValue(password));
  finder.setHost(getValue(host)); 
  finder.setPort(Integer.valueOf(getValue(port)).intValue());
  try {
    finder.setFilename(URLEncoder.encode(LITERAL + getValue(filename) + LITERAL, "UTF-8"));
    {}//Logwriter.printOnConsole("***** In FTP.find() = *" + finder.find() + "*");
    return finder.find();
  }
  catch (UnsupportedEncodingException e) {
    e.printStackTrace();
    return null;
  }
}

public Origin getOrigin() { return origin; }
public void setOrigin(Origin o ) { origin=o; }

public void setDatasource(SQLSource d) { datasource = d; }

public String getUsername() { return username;  }
public void setUsername(String s) { username=s; }
public String getPassword() { return password;}
public void setPassword(String s) { password=s; }
public String getHost() { return host;}
public void setHost(String s) { host=s; }
public String getPort() { return port; }
public void setPort(String s) { port=s; }
public String getFilename() { return filename; }
public void setFilename(String s) { filename=s; }

private String getValue(String s) { 
  String ref;
  if (-1==s.indexOf(START)) return s;
  ref = s.substring(s.indexOf(START) + 1, s.indexOf(END));
  return datasource.getValue(origin, ref);
}

private SQLSource datasource;
private Origin origin=null;
private zws.finder.FTP finder = new zws.finder.FTP();
private static String START = "{";
private static String END = "}";
private String username;
private String password; 
private String host;
private String port;
private String filename;
private static String LITERAL = "'";
}
