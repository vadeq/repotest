package com.zws.hi;
import com.zws.session.SessionData;

import zws.Server;
import zws.application.Properties;
import zws.application.server.webapp.Names;

import java.util.*;


//todo: break into 4 implementations-single & multi-select x bounded,unbounded
public abstract class hiList extends Interactor {
  public hiList() { super(); }
  public hiList(Object o) { super(o); }
  public void initialize() throws Exception{ if (null==items) items = new Vector(); }
  public void render() throws Exception {
    if (null==getChosenItems() || null==getItems()) return;
    Iterator i = getItems().iterator();
    Object o;
    while (i.hasNext()) {
      o = i.next();
      if (isChosen(o))i.remove();
    }
  }

  public void adaptItems(Collection c) throws Exception { setItems(c); } //default - no adpatation

  public int getNumberOfItems() {
    if (null==getItems()) return 0;
    return getItems().size();
  }
  public final Collection getItems() { return items; }
  public final void setItems(Collection c) { items = c; }
  public final String clearItems() { items = null; return ctrlOK; }

  // identifies the chosen one
  protected boolean chooseItem(String s) {
    if ("".equals(s)) return false;
    Iterator i = getItems().iterator();
    Object item = null;
    Object o;
    while (null==item && i.hasNext()) {
      o = i.next();
      if(idChoosesItem(s, o)) item=o;
    }
    if (null==item) return false;
    setChosenItem(item);
    return true;
  }

  protected Object findItemByID(String s) {
    if (null==s || "".equals(s)) return null;
    Iterator i = getItems().iterator();
    Object item = null;
    Object o;
    while (null==item && i.hasNext()) {
      o = i.next();
      if(!idChoosesItem(s, o)) continue;
      item=o;
    }
    return item;
  }

  //pick adds another to the list
  protected boolean pickItem(String s) {
    if ("".equals(s)) return false;
    Iterator i = getItems().iterator();
    Object item = null;
    Object o;
    while (null==item && i.hasNext()) {
      o = i.next();
      if(idChoosesItem(s, o)) {
        item=o;
        if (removeItemWhenChosen()) i.remove();
      }
    }
    if (null==item) return false;
    getChosenItems().add(item);
    if (Server.debugMode()) {}{} //System.out.println("Picked: " +s);
    return true;
  }

  public boolean removeItemWhenChosen() {
    String remove = Properties.get(Names.REMOVE_ITEM_WHEN_CHOSEN);
    if ("true".equalsIgnoreCase(remove)) return true;
    else return false;
  }

  protected boolean unpickItem(String s) {
    if ("".equals(s)) return false;
    Iterator i = getChosenItems().iterator();
    Object item = null;
    Object o;
    while (null==item && i.hasNext()) {
      o = i.next();
      if(idChoosesItem(s, o)) {
        i.remove();
        //if (removeItemWhenChosen()) getItems().add(o);
        getItems().add(o);
        return true;
        }
    }
    return false;
  }

  public boolean idChoosesItem(String id, Object o) { return false; }
  public boolean isChosen(Object o) {
    if (null==getChosenItems()) return false;
    return getChosenItems().contains(o);
  }

  public final String choose() {
    if(chooseItem(getID())) return ctrlCHOOSE;
    logFormError("err.can.not.choose.item", getID());
    return ctrlINDEX;
  }

  public String pick() {
    //if (Server.debugMode()) {} //System.out.println("*******Picking: " + getID());
    if (pickItem(getID())) return ctrlPICK;
    logFormError("err.can.not.pick.item", getID());
    return ctrlINDEX;
  }

  public String pickAll() {
      Iterator i = getItems().iterator();
      Object item = null;
      Object o;
      while (i.hasNext()) {
        o = i.next();
        item=o; i.remove();
        if (null!=item) getChosenItems().add(item);
      }
      return ctrlINDEX;
    }

  public final String unpick() {
    if (unpickItem(getID())) return ctrlUNPICK;
    logFormError("err.can.not.unpick.item", getID());
    return ctrlINDEX;
  }

  public String unpickAll() {
      Iterator i = getChosenItems().iterator();
      Object item = null;
      Object o;
      while (i.hasNext()) {
        o = i.next();
        item=o; i.remove();
        if (null!=item) getItems().add(item);
      }
      return ctrlINDEX;
    }

  public final String[] getChoices() { return choices; }
  public final void setChoices(String[] x) { choices= x; }

  public Object getChosenItem() { return chosenItem; }
  public void setChosenItem(Object o) {
    chosenItem=o;
    SessionData.getSession().setAttribute(getItemFormName(), chosenItem);
  }

  public String add() {
    if (null!=SessionData.getSession())
      SessionData.getSession().removeAttribute(getItemFormName());
    return ctrlADD;
  }

  public String getItemFormName() {
    String itemForm = getClass().getName();
    if (-1==itemForm.lastIndexOf("List")) return itemForm;
    itemForm=itemForm.substring(itemForm.lastIndexOf('.')+1,itemForm.lastIndexOf("List"));
    return itemForm;
  }

  public int getNumberOfChosenItems() { return getChosenItems().size(); }
  public Collection getChosenItems() { return chosenItems; }
  protected void setChosenItems(Collection c) { chosenItems=c; }

  //default event handlers
  public String reload() { return ctrlLIST; }

  private Collection items;
  private String choice = null;
  private Object chosenItem=null;
  private String pick=null;
  private String unpick=null;
  private String choices[] = null;
  private Collection chosenItems = new Vector();
}
