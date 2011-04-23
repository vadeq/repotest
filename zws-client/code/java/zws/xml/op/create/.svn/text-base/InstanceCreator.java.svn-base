package zws.xml.op.create; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0s
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.xml.op.call.CallException;
import zws.xml.op.call.PropertySetter;

import java.util.*;

public class InstanceCreator extends CreateFunctor {
  public InstanceCreator() { }
  public InstanceCreator(String fqcn) { setFqcn(fqcn); }
  public InstanceCreator(String fqcn, Map attributeValues) { setFqcn(fqcn); setAttributeValues(attributeValues); }

  public void initialize(Object arg1) throws Exception {
    try { initialize((Map)arg1); }
    catch (Exception e) { throw new CreateException("Could not initialize using object: " + arg1); }
  };

  public void initialize(Map attributes) { setAttributeValues(attributes); }

  public final Object create() throws CreateException { return createInstance(); }
  public Object createInstance() throws CreateException {
    if (null == getFqcn()) return null;
    Class c = classForFqn();
    Object instance = instanceOfClass(c);
    return initializeInstance(instance);
  }

  private Object instanceOfClass(Class c) throws CreateException {
    try { return c.newInstance(); }
    catch (Exception e) {e.printStackTrace(); throw new CreateException("Could not create instance fqcn=\""+getFqcn()+"\"!"); }
  }
  private Object initializeInstance(Object instance) throws CreateException {
    if (null==getAttributeValues()) return instance;
    //if (null!= getAttributeValues()) {} //System.out.println("Initializing object with: " + getAttributeValues().toString());
    Set entries = getAttributeValues().entrySet();
    Iterator i = entries.iterator();
    PropertySetter setter = new PropertySetter();
    setter.setTargetObject(instance);
    Map.Entry e=null;
    try {
      while (i.hasNext()){
        e = (Map.Entry)i.next();
        setAttribute(setter, e.getKey().toString(), e.getValue());
      }
    }
    catch (Exception ex) {
      CreateException exc = new CreateException("Unable to initialize object attribute ["+instance.getClass().getName()+"].["+e.getKey()+"="+e.getValue()+"] !");
      exc.setResult(instance);
      throw exc;
    }
    return instance;
  }

  private void setAttribute(PropertySetter setter, String name, Object value) throws CallException {
    setter.setPropertyName(name);
    setter.resetParameters(new Object[] {value});
    setter.set();
  }

  private Class classForFqn() throws CreateException{
    try {
      ClassCreator creator = new ClassCreator();
      creator.setFqcn(getFqcn());
      return creator.createClass();
    }
    catch (Exception e)
        { throw new CreateException("Could not load class fqcn=\""+getFqcn()+"\"!"); }
  }

  public String getFqcn(){ return fqcn; }
  public void setFqcn(String fqcn){ this.fqcn = fqcn; }
  public Map getAttributeValues(){ return attributeValues; }
  public void setAttributeValues(java.util.Map attributeValues){ this.attributeValues = attributeValues; }

  private String fqcn;
  private Map attributeValues;
}