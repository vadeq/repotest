package com.zws.struts;

import java.util.*;

public class ItemService {
  public static final String ID = "id";
  public static final String NAME = "name";
  public static final String DESCRIPTION = "description";
  public static final String EXTRA = "extra";

  public ItemService() { }
  private static Collection items = new Vector();

  public static Collection findAll() {
    //Enter code to return all items
    return getItems();
  }

  public static Collection findAll(String sortField) {
    //Enter code to return all items sorted by sortField
    return getItems();
  }

  public static Collection findSubset(int count, int offset, String sortField) {
    //Enter code to lookup count items sorted by sortField starting at offset.
    return findAll(); //non-working example
  }

  public static Item findItem(String id) {
    //Enter code to lookup item with specific id
    //The following is a non-working example
    if (null == id || id.equals("")) return null;
    Item item = null;
    Iterator i = getItems().iterator();
    while (i.hasNext()){
      item = (Item)i.next();
      if (id.equals(item.getID())) return item;
    }
    return null;
  }

  public static void save(Item item) {
    if (null==item || item.getID().equals("")) return;
    delete(item.getID());
    getItems().add(item);
  }

  public static void delete(String[] selectedIDs) {
    if (null==selectedIDs) return;
    for (int i = 0; i < selectedIDs.length; i++) delete(selectedIDs[i]);
  }

  public static void delete(String itemID) {
    Iterator iterator;
    Item item = null;
    Item deletedItem = null;

    Collection items = getItems();
    iterator = getItems().iterator();
    while (iterator.hasNext()){
      item = (Item) iterator.next();
      if (itemID.equals(item.getID())) { deletedItem = item; break; }
    }
    if (null != deletedItem) getItems().remove(deletedItem);
    //else throw exception
  }

  public static void add(){ getItems().add(new Item()); }

  private static Collection getItems() { {if(items.isEmpty()) initItems();} return items; }
  private static void initItems(){
    items.add(new Item());
    items.add(new Item());
    items.add(new Item());
    items.add(new Item());
    items.add(new Item());
    items.add(new Item());
    items.add(new Item());
  }
}
