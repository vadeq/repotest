package zws.repository.agile.event;
/*
DesignState - Design Compression Technology.
@author: Arbind Thakur, Jason Brown @agile.com
Created on Mar 3, 2006
@version: 1.0
Copywrite (c) 2006 Zero Wait-State Inc. All rights reserved */

import zws.repository.agile.px.PxActionBase;

import java.security.Security;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;


public class HTTPEventClient extends PxActionBase implements EventClient {

  public boolean fireEvent(String msg) throws Exception {
    System.out.println("fire event px....");
    PostMethod filePost = null;
    int result = 0;

    try {
			//filePost = new PostMethod("http://designstate-0/eventProcessor/fireEvent.jsp");
			filePost = new PostMethod(getProperty(DESIGNSTATE_EVENT_SERVICE_URL));
			NameValuePair[] msgPair = new NameValuePair[2];
			msgPair[0] = new NameValuePair("event", "fire");
			msgPair[1] = new NameValuePair("firedEvent", msg);
			filePost.setQueryString(msgPair);
			HttpClient httpclient = new HttpClient();
			System.getProperties().put("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			httpclient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(getDefaultUsername(), getDefaultPassword()));
			result = httpclient.executeMethod(filePost);
		} catch (Exception e) {
			// Expect IOException or HTTPException from a close socket
			System.out.println("Unable to complete connection/request: " + e.getMessage());
			debug(e.getMessage());
			return false;
		} finally {
			filePost.releaseConnection();
		}

		if (result != 200)
			return false;
		else
			return true;
  }
}

