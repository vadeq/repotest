package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;


public class DomainContext implements Serializable {
  public String getString(String property) { return (String) get(property); }
  public Object get(String property) {
    Object value=null;
    DomainContext ctx = this;
    while (null==value && null!=ctx && !ctx.properties.containsKey(property)) ctx = ctx.getParent();
    if (null==ctx) return null;
    else return ctx.properties.get(property);
  }
  
	public void set(String property, Object value) { properties.put(property, value); }
	public void set(KeyValue pair){ set(pair.getKey(), pair.getValue()); }

	public boolean contains(String property){
		DomainContext ctx=this;
    boolean containsProperty=false;
    while(!containsProperty && null!=ctx){
			containsProperty=ctx.properties.containsKey(property);
			ctx = ctx.getParent();
		}
		return containsProperty; 
	}

    
  public DomainContext getParent() { return parentContext; }
  public void setParent(DomainContext c) { parentContext = c; }

  
  //some utility Functions  
  public RoutedData getSourceRouting() {
    RoutedDataBase r= new RoutedDataBase();
    r.setDomainName(getString(SOURCE_DOMAIN_NAME));
    r.setServerName(getString(SOURCE_SERVER_NAME));
    r.setDatasourceName(getString(SOURCE_REPOSITORY_NAME));
    return r;
  }  
  public void setSourceRouting(RoutedData r) {
    set(SOURCE_DOMAIN_NAME,r.getDomainName());
    set(SOURCE_SERVER_NAME,r.getServerName());
    set(SOURCE_REPOSITORY_NAME,r.getDatasourceName());
  }  
  public RoutedData getTargetRouting() {
    RoutedDataBase r= new RoutedDataBase();
    r.setDomainName(getString(TARGET_DOMAIN_NAME));
    r.setServerName(getString(TARGET_SERVER_NAME));
    r.setDatasourceName(getString(TARGET_REPOSITORY_NAME));
    return r;
  }
  public void setTargetRouting(RoutedData r) {
    set(TARGET_DOMAIN_NAME,r.getDomainName());
    set(TARGET_SERVER_NAME,r.getServerName());
    set(TARGET_REPOSITORY_NAME,r.getDatasourceName());
  }  
  
  
  public void dump(PrintStream stream){
    Iterator i = properties.keySet().iterator();
    String field;
    while (i.hasNext()) {
      field = (String) i.next();
      stream.println(field + "=" + properties.get(field));
    }
  }
  
  protected Map properties = new HashMap();
	private DomainContext parentContext = null;
  public static String NAME="name";
  public static String DOMAIN_NAME="domain-name";
  public static String SERVER_NAME="server-name";
  public static String REPOSITORY="repository-name";
  public static String SERVICE_NAME="service-name";
  public static String METHOD_NAME="method-name";
  public static String DATA_SPACE_NAME="data-space-name";
  
  public static String SOURCE_NAME="source-name";
  public static String SOURCE_DOMAIN_NAME="source-domain-name";
  public static String SOURCE_SERVER_NAME="source-server-name";
  public static String SOURCE_REPOSITORY_NAME="source-repository-name";
  public static String SOURCE_SERVICE_NAME="source-service-name";
  public static String SOURCE_DATA_SPACE_NAME="source-data-space-name";
  
  public static String TARGET_NAME="target-name";
  public static String TARGET_DOMAIN_NAME="target-domain-name";
  public static String TARGET_SERVER_NAME="target-server-name";
  public static String TARGET_REPOSITORY_NAME="target-repository-name";
  public static String TARGET_SERVICE_NAME="target-service-name";
  public static String TARGET_DATA_SPACE_NAME="target-data-space-name";
}
