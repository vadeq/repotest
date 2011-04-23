package zws.finder;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 6, 2003, 6:03 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.OpBase;

import java.net.MalformedURLException;
import java.net.URL;

public class FTP extends OpBase {
  
  public void execute() throws Exception { setResult(find()); }  
  public URL find() throws MalformedURLException { 
    if (null!=username && 0 < username.length() && null != password && 0 < password.length() )
      return new URL("ftp://" + username + ":" + password + "@" + host + ":" + port + "/"+filename); 
    else
      return new URL("ftp://" + host + ":" + port + "/"+filename);       
  }
  
  public String getUsername() { return username; }
  public void setUsername(String s) { username=s; }
  public String getPassword() { return password; }
  public void setPassword(String s) { password=s; }
  public String getHost() { return host; }
  public void setHost(String s) { host=s; }
  public int getPort() { return port; }
  public void setPort(int i) { port=i; }
  public String getFilename() { return filename; }
  public void setFilename(String s) { filename=s; }
  
  private String username;
  private String password;
  private String host;
  private int port=21;
  private String filename;
}
