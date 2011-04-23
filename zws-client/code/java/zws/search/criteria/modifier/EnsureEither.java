package zws.search.criteria.modifier; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on May 23, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.search.criteria.*;
import zws.search.criteria.parser.CriteriaParser;
import zws.search.criteria.parser.CriteriaParserBase;

import java.util.ArrayList;


public class EnsureEither extends CriteriaModifierBase {
  public Criteria modify(Criteria c) throws Exception {
    ArrayList newCriteria = new ArrayList();
    Criteria x;
    CriteriaModifier m;
    for (int idx = 0; idx < modifiers.size(); idx++) {
     x = c.copy();
     m = (CriteriaModifier) modifiers.get(idx);
     x = m.modify(x);
     newCriteria.add(x);
    }
    String crit;
    StringBuffer s = new StringBuffer();
    x = (Criteria)newCriteria.get(0);
    crit = x.toString();
    if (!crit.startsWith(GROUP_START)) crit =GROUP_START+" " + crit +" " + GROUP_END;  
    s.append(crit);
    for (int idx = 1; idx < newCriteria.size(); idx++) {
      x = (Criteria)newCriteria.get(idx);
      crit = x.toString();
      if (!crit.startsWith(GROUP_START)) crit = GROUP_START + " " + crit + " " + GROUP_END;
      {} //System.out.println(crit +": " + s.toString());
      s.append(" | " ).append(crit);
    }
    CriteriaParser parser = new CriteriaParserBase();
    x=parser.parse(s.toString());
    return x;
  }

  public void add(CriteriaModifier m) { modifiers.add(m); }
  
  private ArrayList modifiers = new ArrayList();
  private static String GROUP_START = Names.CRITERIA_GROUP_START_BLOCK;
  private static String GROUP_END = Names.CRITERIA_GROUP_END_BLOCK;
}
