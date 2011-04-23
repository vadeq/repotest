package zws.search.criteria;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on June 29, 2004, 5:31 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.search.criteria.modifier.*;
import zws.search.criteria.parser.CriteriaParserBase;

public class Test {
  private  Test() { }
  public static void main(String[] args) {
    ANDOperator op = new ANDOperator();
    Test t = new Test();
    t.run();
  }

  public void run()  {
    testModifier2();
    //testSimpleCriteria();
    //testMultiCriteria();
    //testCompoundCriteria();
    //{} //System.out.println("");
    //testModifier();
    //{} //System.out.println("..done.");
  }

  public void testModifier2(){
    try {
        String criteria;
        Criteria c;
        criteria = "name=12345";
        c= parser.parse(criteria);
        {} //System.out.println(criteria + "=>" + c.toString());
        EnsureComparison e = new EnsureComparison();
        e.setFieldName("name");
        e.setOperator("=");
        e.setValue("*.dwg");
        
        EnsureComparison f = new EnsureComparison();
        f.setFieldName("name");
        f.setOperator("=");
        f.setValue("*.drw");
        
        EnsureEither t = new EnsureEither();
        t.add(e);
        t.add(f);
        Criteria x;
        x = t.modify(c);
        {} //System.out.println(x.toString());
    }
    catch (Exception e) { e.printStackTrace(); }
  }
  
  
  public void testSimpleCriteria(){
    try { 
      String criteria;
      Criteria c;
      criteria = "name=*.drw";
      c= parser.parse(criteria);
      {} //System.out.println(criteria + "=>" + c.toString());
      
      criteria = "[name=*.drw]";
      c= parser.parse(criteria);
      {} //System.out.println(criteria + "=>" + c.toString());

      criteria = "[   name  =  *.drw   ]";
      c= parser.parse(criteria);
      {} //System.out.println(criteria + "=>" + c.toString());
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  public void testMultiCriteria(){
    try {       
      String criteria;
      Criteria c;
      criteria = "name=*.drw & Folder=*CAD";
      c= parser.parse(criteria);
      {} //System.out.println(criteria + "=>" + c.toString());
      
      criteria = "[name=*.drw & Folder=*CAD & Release Level= Released & Created On = Admin]";
      c= parser.parse(criteria);
      {} //System.out.println(criteria + "=>" + c.toString());
      
      criteria = "   name  =  *.drw   &   Folder =  *CAD   &    Release Level =   Released    &    Created On   =   Admin ";
      c= parser.parse(criteria);
      {} //System.out.println(criteria + "=>" + c.toString());
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  public void testCompoundCriteria(){
    try {       
      String criteria;
      Criteria c;
      criteria = "[name=*.drw & Folder=*CAD] | [name=*.drw & Folder=Auto*]";
      c= parser.parse(criteria);
      {} //System.out.println(criteria + "=>" + c.toString());
      
      criteria = "[name=*.drw & Folder=*CAD] | [name=*.drw & Folder=Auto*] | [name=*.drw & Folder=*CAD & Release Level= Released & Created On = Admin]";
      c= parser.parse(criteria);
      {} //System.out.println(criteria + "=>" + c.toString());
      
      criteria = "  [  name = *.drw  &   Folder = *CAD0   ]     |    [ name = *.drw   &  Folder = Auto*  ]   |   [   name =  *.drw   &   Folder=*CAD1    &    Release Level= Released     &     Created On    =    Admin    ]    ";
      c= parser.parse(criteria);
      {} //System.out.println(criteria + "=>" + c.toString());      
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  public void testModifier() {
   
    try {       
      String criteria;
      Criteria c;
      criteria = "[name=*.drw & Folder=*CAD] | [name=*.drw & Folder=Auto*] | [name=*.drw & Folder=*CAD & Release Level= Released & Created On = Admin]";
//    criteria = "[name=*.drw & Folder=root] | [name=*.drw & Folder=roots]";
      c= parser.parse(criteria);
      {} //System.out.println(criteria + "=>" + c.toString());

      EnsureComparison ensure = new EnsureComparison();
      ensure.setFieldName("ensure");
      ensure.setOperator("!!");
      ensure.setValue("VAL");
      c = ensure.modify(c);
      {} //System.out.println(criteria + "~>" + c.toString());

      Postfix postfix = new Postfix();
      postfix.setFieldNames(" Name | Ensure ");
      postfix.setPostfixValue("*");
      postfix.setCaseSensitive(false);
      c = postfix.modify(c);
      {} //System.out.println(criteria + "~>" + c.toString());

      RemapFieldNames mapping = new RemapFieldNames();
      zws.util.StringKeyedHashMap m = new zws.util.StringKeyedHashMap();
      m.setCaseSensitive(true);
      m.put("Name", "OBJECT");
      m.put("Folder", "LOCATION");
      m.put("Release Level", "RELEASE");
//    m.put("ensure", "INCLUDE");
      mapping.setMapping(m);
      c = mapping.modify(c);
      {} //System.out.println(criteria + "~>" + c.toString());
/*
      Comparison comp = new Comparison();
      comp.setField("field");
      comp.setOperator("6>");
      comp.setValue("value");
      ((AND)(c.getParser().getANDList().toArray()[0])).addExpression(comp);
      c.getParser().getComparisons().add(comp);
 */
    }
    catch (Exception e) { e.printStackTrace(); }
  }

 CriteriaParserBase parser = new CriteriaParserBase();
}
