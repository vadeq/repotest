package com.zws.hi;

import com.zws.functor.finder.Finder;

import java.util.HashMap;
import java.util.Map;


public class FinderRegistrar {

  private FinderRegistrar(){}

  private static Map sessions = new HashMap();
  public static FinderRegistrar getRegistrar(String memberID){
    FinderRegistrar r = (FinderRegistrar)sessions.get(memberID);
    if (null==r) {
      r= new FinderRegistrar();
      sessions.put(memberID, r);
    }
    return r;
  }


  public Finder find(String category, String finderID){
    Map m = (Map) getCategory(category);
    return (Finder) m.get(finderID);
  }

  public void register(String category, String id, Finder finder){  getCategory(category).put(id, finder); }

  private Map getCategory(String category) {
    Map finders = (Map)registrar.get(category);
    if (null==finders) { finders = new HashMap(); registrar.put(category, finders);}
    return finders;
  }

  private Map registrar = new HashMap();
  private static String KEY_FINDER = "FinderRegistrar";
}
