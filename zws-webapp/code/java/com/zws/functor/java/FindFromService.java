package com.zws.functor.java;


public class FindFromService extends JavaMethod  {
  public void execute()  throws Exception {
    getService = new JavaMethod();
    getService.setArguments(getArguments());
    getService.bind(getJavaClass());
    getService.setMethodName(getServiceMethodName());
    getService.execute();
    findMethod = new JavaMethod();
    findMethod.bind(getService.getResult());
    findMethod.setMethodName(getFindMethodName());
    findMethod.addArgument(getLookupName());
    findMethod.execute();
    setResult(findMethod.getResult());
  }
  public String getMethodName() { return serviceMethodName; }
  public void setMethodName(String s) { serviceMethodName = s; }

  public String getServiceMethodName() { return getMethodName(); }
  public void setServiceMethodName(String s) { setMethodName(s); }

  public String getFindMethodName() { return findMethodName; }
  public void setFindMethodName(String s) { findMethodName = s; }

  public String getLookupName() { return lookupName; }
  public void setLookupName(String s) { lookupName = s; }

  private String serviceMethodName = "getService";
  private String findMethodName = "find";
  private String lookupName = null;
  private JavaMethod getService;
  private JavaMethod findMethod;
}
