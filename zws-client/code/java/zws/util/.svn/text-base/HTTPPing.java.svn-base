package zws.util; 
import zws.op.ThreadedOpBase;

import java.net.HttpURLConnection;
import java.net.URL;

/*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Sep 14, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

public class HTTPPing extends ThreadedOpBase {
	public HTTPPing(String urlAddress) { url=urlAddress; }
	
	public void executeRun() {
	  try {
	    URL httpurl = new URL(url);
	    HttpURLConnection conn = (HttpURLConnection)httpurl.openConnection();
	    code = conn.getResponseCode();
	    length = conn.getContentLength();
	    message = conn.getResponseMessage();
	    {} //System.out.println("Response Size: " + length);
	    {} //System.out.println("Response Size: " + message);
	  }
	  catch (Exception e) { code=600;} 
	}
	
	public int getResponseCode() { return code; }
	public long getResponseLength() { return length; }
	public String getResponseMessage() { return message; }
	
	private String url=null;
	private int code=600; //initialized to made up code for can not connect - 601
	private long length=-1;
	private String message=null;
}
