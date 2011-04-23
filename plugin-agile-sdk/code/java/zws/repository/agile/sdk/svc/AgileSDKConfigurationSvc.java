package zws.repository.agile.sdk.svc;
/*
 * DesignState - Design Compression Technology @author: ptoleti @version: 1.0
 * Created on Jul 11, 2007 5:39:31 PM Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */

import zws.exception.NameNotFound;
import zws.util.MapUtil;
import zws.util.comparator.ObjectComparator;
import zws.xml.util.XMLString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.Vector;

import java.util.Collection;

import com.agile.api.APIException;
import com.agile.api.IAdmin;
import com.agile.api.IAgileClass;
import com.agile.api.IAgileSession;
import com.agile.api.IProperty;
import com.agile.api.ITableDesc;
import com.agile.api.PropertyConstants;
import com.agile.api.TableTypeConstants;
import com.agile.api.IAttribute;



/**
 * The Class AgileSDKConfiguration.
 *
 * @author ptoleti
 */
public class AgileSDKConfigurationSvc { 

  /**
   * The Constructor.
   *
   * @param sessionUtil the session util
   */
  public AgileSDKConfigurationSvc(AgileSDKSessionSvc sessionUtil) {
    sessions = sessionUtil;
  }

  /**
   * Load agile classes.
   *
   * @throws Exception the exception
   */
  private synchronized void loadAgileClasses() throws Exception {
    if (null != agileClasses) {
      agileClasses.clear();
    } else {
      agileClasses = new HashMap();
    }
    IAgileSession session = sessions.systemLogin();
    IAgileClass[] classes = session.getAdminInstance().getAgileClasses(IAdmin.CONCRETE);
    // if (null==classes) logg(getDefaultAuthentication(), "classes are null");
    // else logg(getDefaultAuthentication(), "found "+ classes.length +
    // "classes");
    for (int idx = 0; idx < classes.length; idx++) {
      if (null != classes[idx]) {
        agileClasses.put(classes[idx].getName(), classes[idx]);
      }
    }
  }

  /**
   * List classes.
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  public Collection listClasses() throws Exception {
    try {
      sessions.systemLogin();
      return listClassNames();
    } catch (Exception e) {
      throw e;
    }
  }

  // Agile Configuration

  // Agile Classes
  /**
   * List class names.
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  private Collection listClassNames() throws Exception {
    Collection c = new Vector();
    c.addAll(getAgileClssesMap().keySet());
    return c;
  }

  /**
   * Refresh attributes.
   *
   * @param agileClassName the agile class name
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  public Collection refreshAttributes(String agileClassName) throws Exception {
    try {
      sessions.systemLogin();
      loadAttributes(agileClassName);
      return listAttributeNames(agileClassName);
    } catch (APIException e) {
      // e.getCause().printStackTrace();
      // LogWriter.printOnConsole(e.getRootCause().getMessage());
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * Refresh configuration.
   *
   * @throws Exception the exception
   */
  public synchronized void refreshConfiguration() throws Exception {
    agileClasses = null;
    classAttributes = null;
    classAttributes = new HashMap();
    loadAgileClasses();
  }

  /**
   * Find visible attributes.
   *
   * @param c IAgileClass
   *
   * @return Collection
   *
   * @throws Exception exception
   */
  protected Collection findVisibleAttributes(IAgileClass c) throws Exception {
    ArrayList visibleAtts = new ArrayList();
    // if (c.isAbstract()) return visibleAtts;
    visibleAtts.addAll(findVisibleAttributes(c.getTableDescriptor(TableTypeConstants.TYPE_PAGE_ONE)));
    visibleAtts.addAll(findVisibleAttributes(c.getTableDescriptor(TableTypeConstants.TYPE_PAGE_TWO)));
    visibleAtts.addAll(findVisibleAttributes(c.getTableDescriptor(TableTypeConstants.TYPE_PAGE_THREE)));
    return visibleAtts;
  }

  /**
   * Find visible attributes.
   *
   * @param table ITableDesc
   *
   * @return Collection
   *
   * @throws Exception exception
   */
  private Collection findVisibleAttributes(ITableDesc table) throws Exception {
    Collection visibleAtts = new ArrayList();
    IAttribute[] atts = null;
    if (null != table) {
      atts = table.getAttributes();
      for (int idx = 0; idx < atts.length; idx++) {
        if (isVisible(atts[idx])) {
          visibleAtts.add(atts[idx]);
        }
      }
    }
    return visibleAtts;
  }

  /**
   * Checks if is visible.
   *
   * @param att IAttribute
   *
   * @return boolean
   *
   * @throws APIException exception
   */
  private boolean isVisible(IAttribute att) throws APIException {
    boolean isVisible = false;
    IProperty visible = att.getProperty(PropertyConstants.PROP_VISIBLE);
    if (null != visible) {
      Object v = visible.getValue();
      if (null != v) {
        isVisible = v.toString().equalsIgnoreCase("Yes");
      }
    }
    return isVisible;
  }

  /**
   * Load attributes.
   *
   * @param agileClassName the agile class name
   *
   * @throws Exception the exception
   */
  private void loadAttributes(String agileClassName) throws Exception {
    Map definedAttributes = MapUtil.getMapFromMap(classAttributes, agileClassName);
    Map definedXMLAttributes = MapUtil.getMapFromMap(classAttributes, agileClassName + "-xml");
    definedAttributes.clear();
    Collection c = null;
    Collection atts = findVisibleAttributes(findAgileClass(agileClassName));
    c = new Vector();
    Iterator i = atts.iterator();
    IAttribute att = null;
    String key;
    while (i.hasNext()) {
      att = (IAttribute) i.next();
      c.add(att.getName());
      key = att.getName().toLowerCase();
      // if( key is ignored attribute) continue;
      definedAttributes.put(key, att);
      definedXMLAttributes.put(XMLString.asXMLName(key), att);
    }
  }

  

  /**
   * Load BOM attributes.
   *
   * @param agileClassName the agile class name
   *
   * @throws Exception the exception
   */
  private void loadBOMAttributes(String agileClassName) throws Exception {
    
    //+++++++++++++++++++++++
    Map definedAttributes = MapUtil.getMapFromMap(bomAttributes, agileClassName);
    Map definedXMLAttributes = MapUtil.getMapFromMap(bomAttributes, agileClassName + "-xml");
    definedAttributes.clear();
    Collection c = null;
    Collection atts = findVisibleAttributes(findAgileClass(agileClassName));
    c = new Vector();
    Iterator i = atts.iterator();
    IAttribute att = null;
    String key;
    while (i.hasNext()) {
      att = (IAttribute) i.next();
      c.add(att.getName());
      key = att.getName().toLowerCase();
      // if( key is ignored attribute) continue;
      definedAttributes.put(key, att);
      definedXMLAttributes.put(XMLString.asXMLName(key), att);
    }
  }

  
  /**
   * Checks if is required.
   *
   * @param att the att
   *
   * @return true, if is required
   *
   * @throws APIException the API exception
   */
  public boolean isRequired(IAttribute att) throws APIException {
    if (!isVisible(att)) { return false; }
    IProperty required = att.getProperty(PropertyConstants.PROP_REQUIRED);
    if (null == required) { return false; }
    Object v = required.getValue();
    if (null == v) { return false; }
    return v.toString().equalsIgnoreCase("Yes");
  }

  // Agile Attributes
  /**
   * List attribute names.
   *
   * @param agilesClassName the agiles class name
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  private Collection listAttributeNames(String agilesClassName)
      throws Exception {
    Collection c = new TreeSet(new ObjectComparator());
    Map atts = findAttributes(agilesClassName);
    if (null == atts || atts.isEmpty()) {
      return c;
    }
    Iterator i = atts.keySet().iterator();
    while (i.hasNext()) {
      c.add(i.next().toString());
    }
    return c;
  }

  /**
   * Find attributes.
   *
   * @param agileClassName the agile class name
   *
   * @return the map
   *
   * @throws Exception the exception
   */
  public Map findAttributes(String agileClassName) throws Exception {
    if (null == classAttributes.get(agileClassName)) {
      loadAttributes(agileClassName);
    }
    return (Map) classAttributes.get(agileClassName);
  }


  /**
   * Find attributes.
   *
   * @param agileClassName the agile class name
   *
   * @return the map
   *
   * @throws Exception the exception
   */
  public Map findBOMAttributes(String agileClassName) throws Exception {
    if (null == bomAttributes.get(agileClassName)) {
      loadBOMAttributes(agileClassName);
    }
    return (Map) classAttributes.get(agileClassName);
  }

  
  
  /**
   * List attributes.
   *
   * @param agileClassName the agile class name
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  public Collection listAttributes(String agileClassName) throws Exception {
    try {
      sessions.systemLogin();
      return listAttributeNames(agileClassName);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Find agile class.
   *
   * @param agileClassName the agile class name
   *
   * @return the i agile class
   *
   * @throws Exception the exception
   */
  public IAgileClass findAgileClass(String agileClassName) throws Exception {
    return (IAgileClass) getAgileClssesMap().get(agileClassName);
  }

  /**
   * Find agile class ID.
   *
   * @param agileClassName the agile class name
   *
   * @return the integer
   *
   * @throws Exception the exception
   */
  public Integer findAgileClassID(String agileClassName) throws Exception {
    IAgileClass c = (IAgileClass) getAgileClssesMap().get(agileClassName);
    if (null == c) { throw new NameNotFound("Class " + agileClassName); }
    return (Integer) c.getId();
  }

  /**
   * Find XML attributes.
   *
   * @param agileClassName the agile class name
   *
   * @return the map
   *
   * @throws Exception the exception
   */
  public Map findXMLAttributes(String agileClassName) throws Exception {
    if (null == classAttributes.get(agileClassName + "-xml")) {
      loadAttributes(agileClassName);
    }
    return (Map) classAttributes.get(agileClassName + "-xml");
  }

  /**
   * Gets the agile clsses map.
   *
   * @return the agile clsses map
   *
   * @throws Exception the exception
   */
  private Map getAgileClssesMap() throws Exception {
    if (null == agileClasses) {
      loadAgileClasses();
    }
    return agileClasses;
  }

  /** The agile classes. */
  private Map agileClasses = null;

  /** The class attributes. */
  private Map classAttributes = new HashMap();
  private Map bomAttributes = new HashMap();

  /** The sessions. */
  private AgileSDKSessionSvc sessions = null;

}
