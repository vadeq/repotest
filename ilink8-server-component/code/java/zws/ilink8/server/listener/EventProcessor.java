package zws.ilink8.server.listener;

import java.lang.reflect.Method;
import java.security.Security;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;

import wt.admin.DomainAdministered;
import wt.enterprise.RevisionControlled;
import wt.events.KeyedEvent;
import wt.fc.Persistable;
import wt.vc.Iterated;
import wt.vc.VersionControlHelper;
import wt.vc.Versioned;
import wt.vc.wip.WorkInProgressHelper;
import zws.ilink8.server.util.PropertyManager;
import zws.security.CryptoUtil;

public class EventProcessor{
	
	public static HashMap preDataMap = new HashMap();
	
	public void processEvent(KeyedEvent keyedevent) throws Exception{
		Object target = keyedevent.getEventTarget();
		if(target instanceof Iterated && keyedevent.getEventType().startsWith("POST")){
			System.out.println("=====>Getting Latest");
			target = VersionControlHelper.getLatestIteration((Iterated) target, true);
		}
		System.out.println("=============> " + keyedevent.getEventType() + "-" + target + " and class is " + target.getClass());
		System.out.println("=====>Prev Map " + preDataMap);
		HashMap propertyMap = new HashMap(); 
		String eventname = (String) keyedevent.getClass().getMethod("getEventType", new Class[]{}).invoke(keyedevent, new Object[]{});			
		
		HashMap resultMap = getObjectValues(target, eventname);
		resultMap.put("eventname", eventname);
		propertyMap.putAll(resultMap);
		
		String postEvent = null;
		String preEvent = null;
		try{ postEvent =  PropertyManager.getPropertyValue("ZWS_EVENT_" + eventname + "_POST_EVENT"); } catch(Exception e){ }
		try{ preEvent  =  PropertyManager.getPropertyValue("ZWS_EVENT_" + eventname + "_PRE_EVENT"); } catch(Exception e){ }
			
		if(postEvent != null && postEvent != ""){
			System.out.println("In pre event");
			preDataMap.put(target.toString(), resultMap);
		} else {
			System.out.println("In post event");
			HashMap preValueMap = (HashMap) preDataMap.get(target.toString());
			System.out.println("The prev value is " + preValueMap);
			if(preValueMap != null){
				propertyMap.put("preveventmap", preValueMap);
				preDataMap.remove(target.toString());
			}
			
			System.out.println("=====>Current Map " + propertyMap);
			System.out.println("");
			System.out.println("");
			System.out.println(">>>>>PUBLISHING");			
			String xmlResult = buildXML(propertyMap);
			System.out.println(xmlResult);
			postEvent(xmlResult);			
		} 

		System.out.println("----------------------------------------------------------------------");		
	}
	
	public HashMap getObjectValues(Object target, String eventname) throws Exception{
		HashMap propertyMap = new HashMap(); 
		String methods = PropertyManager.getPropertyValue("ZWS_EVENT_" + eventname + "_RESULT_METHODS");
		System.out.println("The methods are: " + methods);
		StringTokenizer tokens = new StringTokenizer(methods, ",");
		while(tokens.hasMoreTokens()){
			String methodNameString = tokens.nextToken();
			StringTokenizer methodToken = new StringTokenizer(methodNameString, ":");
			
			methodNameString = methodToken.nextToken();
			String attributeName = null;
			if(methodToken.hasMoreTokens()){ 
				attributeName = methodToken.nextToken(); 
			} else {
				attributeName = methodNameString;
			}
			Method method = null;
			Object instance = null;
			Object[] argsValue = null;
			Class[] argsType = null;
			
			try{
				if(methodNameString.toLowerCase().indexOf("zws") != -1){
					System.out.println("Getting zws method: " + methodNameString);
					argsType = new Class[]{Object.class};
					argsValue = new Object[]{target};
					instance = this;
					method = EventProcessor.class.getMethod(methodNameString, argsType);
				} else {
					System.out.println("Getting wc method: " + methodNameString);
					instance = target;
					method = target.getClass().getMethod(methodNameString, null);
				}
				method.setAccessible(true);
				System.out.println("The method is: " + method);
				try{
					Object result = (Object) method.invoke(instance, argsValue);
					System.out.println("The result is: " + result);
					if(result instanceof HashMap){
						propertyMap.putAll((Map) result);
					} else {
						HashMap subMap = new HashMap();
						subMap.put(attributeName, result);
						propertyMap.putAll((Map) subMap);
					}
				}catch(Exception e){ e.printStackTrace(); }
			}catch(NoSuchMethodException e){
				System.out.println("The method " + methodNameString + " does not exist. Skipping");
			}
		}
		return propertyMap;
	}
	
	
	public HashMap getZwsDomainName(Object object) throws Exception{
		HashMap hashMap = new HashMap();
		try{
			DomainAdministered da = (DomainAdministered) object;
			hashMap.put("domainname", da.getDomainRef().getName());
		}catch(Exception e){ e.printStackTrace(); }
		return hashMap;
	}

	public String buildXML(HashMap eventMap) throws Exception{
		String xmlString = "";
		String eventname = (String)eventMap.get("eventname");
		String eventType = PropertyManager.getPropertyValue("ZWS_EVENT_" + eventname + "_TYPE");
		
		xmlString = xmlString + "<event event-type=\"" + eventType + "." + eventname.toLowerCase() + "\" domain-name=\"" + getDomainName() + "\" server-name=\"" + getServerName() + "\" repository-type=\"" + getRepositoryType() + "\" repositry-name=\"" + getRepositoryName() + "\" time=\"" + new Date() + "\" >";
			HashMap preveventmap = (HashMap) eventMap.get("preveventmap");
			if(preveventmap != null){ eventMap.remove("preveventmap");} 
						
			xmlString = xmlString + "<metadata";
			Iterator iterator = eventMap.keySet().iterator();
			while(iterator.hasNext()){
				String key = iterator.next().toString();
				String value = eventMap.get(key).toString();
				xmlString = xmlString + " " + key + "=\"" + value + "\"";
			}
			xmlString = xmlString + "/>";
			
			if(preveventmap != null){
				eventMap = preveventmap;
				xmlString = xmlString + "<metadata";
				iterator = eventMap.keySet().iterator();
				while(iterator.hasNext()){
					String key = iterator.next().toString();
					String value = eventMap.get(key).toString();
					xmlString = xmlString + " " + key + "=\"" + value + "\"";
				}	
				xmlString = xmlString + "/>";
			}
		xmlString = xmlString + "</event>";
		return xmlString;
	}	
	
	public HashMap getZwsOrigin(Object object) throws Exception {
		HashMap hashMap = new HashMap();
		try{
			String oid = object.toString();		
			String number = null;
			String name = null;
			String cadName = null;
			String version = null;
			String iteration = null;
			String createdOn = null;
			try{ createdOn = String.valueOf(((Persistable)object).getPersistInfo().getCreateStamp().getTime()); } catch(Exception e){}
			try{ number = (String)object.getClass().getMethod("getNumber", null).invoke(object, null); } catch(Exception e){}
			try{ name = (String)object.getClass().getMethod("getName", null).invoke(object, null); } catch(Exception e){}
			try{ cadName = (String)object.getClass().getMethod("getCADName", null).invoke(object, null); } catch(Exception e){}
			
			if(object instanceof RevisionControlled){
				version = VersionControlHelper.getVersionIdentifier((Versioned) object).getValue();
				iteration = VersionControlHelper.getIterationIdentifier((Versioned) object).getValue();
			}
			//String origin = "zws|node-0|ilink-8|PDMLink|-1|" + oid + "|" + number + "|" + cadName + "|" + version + "|" + iteration + "|" + name + "|" + createdOn + "|N/A|N/A|N/A";

			String origin =  getOriginPrefix() + "|" + getDomainName() + "|" + getServerName() + "|" + getRepositoryName() + "|" + createdOn + "|" + oid + "|" + number + "|" + cadName + "|" + version + "|" + iteration + "|" + name  + "|N/A|N/A|N/A";
			
			hashMap.put("origin", origin);	
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return hashMap;
	}
	
	public String getRepositoryType() throws Exception{
		return PropertyManager.getPropertyValue("ZWS_REPOSITORY_TYPE");
	}
	
	public String getOriginPrefix() throws Exception{
		return PropertyManager.getPropertyValue("ZWS_ORIGIN_PREFIX");
	}
	
	public String getDomainName() throws Exception{
		return PropertyManager.getPropertyValue("ZWS_DOMAIN_NAME");
	}
	
	public String getServerName() throws Exception{
		return PropertyManager.getPropertyValue("ZWS_SERVER_NAME");
	}
	
	public String getRepositoryName() throws Exception{
		return PropertyManager.getPropertyValue("ZWS_REPOSITORY_NAME");
	}
	
	public static void postEvent(String msg) throws Exception{
		PostMethod filePost = new PostMethod(PropertyManager.getPropertyValue("ZWS_EVENT_LISTENER_URL"));
		NameValuePair[] msgPair = new NameValuePair[2];
		msgPair[0] = new NameValuePair("event", "fire");
		msgPair[1] = new NameValuePair("firedEvent", msg);
		filePost.setQueryString(msgPair);
		HttpClient httpclient = new HttpClient();
		System.getProperties().put("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		CryptoUtil crypoUtil = new CryptoUtil();
		String password = crypoUtil.decrypt(PropertyManager.getPropertyValue("ZWS_EVENT_LISTENER_ENCRYPTED_PASSWORD"));
		String userID = PropertyManager.getPropertyValue("ZWS_EVENT_LISTENER_USERNAME");
		httpclient.getState().setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(userID, password));
		try {
			int result = httpclient.executeMethod(filePost);
		} finally {
			filePost.releaseConnection();
		}
	}	
	
}