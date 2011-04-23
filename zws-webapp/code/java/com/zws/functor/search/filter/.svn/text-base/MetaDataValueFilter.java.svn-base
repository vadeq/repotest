package com.zws.functor.search.filter;

import com.zws.domo.document.Document;
import com.zws.functor.Functor;
import com.zws.functor.filter.DocumentUnitFilter;
import com.zws.util.comparator.StringComparator;

public class MetaDataValueFilter extends DocumentUnitFilter {
  public void initialize() throws Exception {
    if (null==target) return;
    target.execute();
    setValue((String)target.getResult());
  }

  public boolean keep(Document doc) throws Exception {
    StringComparator comparator = new StringComparator();
    comparator.setData(doc.get(getMetaData()));
    if (getComparison().equals(OP_EQUAL)) return comparator.isEqual(getValue());
    else if (getComparison().equals(OP_EQ)) return comparator.isEqual(getValue());
    else if (getComparison().equals(OP_NOT_EQUAL)) return comparator.isNotEqual(getValue());
    else if (getComparison().equals(OP_NOT_EQ)) return comparator.isNotEqual(getValue());
    else if (getComparison().equals(OP_LESS_THAN)) return comparator.isLessThan(getValue());
    else if (getComparison().equals(OP_GREATER_THAN)) return comparator.isGreaterThan(getValue());
    else if (getComparison().equals(OP_LESS_THAN_OR_EQUAL)) return comparator.isLessThanOrEqual(getValue());
    else if (getComparison().equals(OP_EQUAL_OR_LESS_THAN)) return comparator.isLessThanOrEqual(getValue());
    else if (getComparison().equals(OP_GREATER_THAN_OR_EQUAL)) return comparator.isGreaterThanOrEqual(getValue());
    else if (getComparison().equals(OP_EQUAL_OR_GREATER_THAN)) return comparator.isGreaterThanOrEqual(getValue());
    else throw new Exception("Specified an unknown comparison: " + getComparison() + "[metadata="+getMetaData()+" type="+getType()+" value=" + getValue()+"]");
  }


  public void add(Functor f) { setTarget(f); }
  public Functor getTarget(){ return target; }
  public void setTarget(Functor f) { target=f; }
  public String getMetaData() { return metadata; }
  public void setMetaData(String s) { metadata=s; }
  public String getValue() { return value; }
  public void setValue(String s) { value=s; }
  public String getType() { return type; }
  public void setType(String s) { type=s; }
  public boolean getIgnoreCase() { return ignoreCase; }
  public void setIgnoreCase(boolean b) { ignoreCase=b; }
  public String getComparison() { return comparison; }
  public void setComparison(String s) { comparison=s; }

  private Functor target=null;
  private String metadata=null;
  private String value=null;
  public boolean ignoreCase=true;
  private String type="string";
  private String comparison=OP_EQUAL;

  public static String OP_EQUAL="==";
  public static String OP_NOT_EQUAL="<>";
  public static String OP_EQ="=";
  public static String OP_NOT_EQ="!=";
  public static String OP_GREATER_THAN=">";
  public static String OP_LESS_THAN="<";
  public static String OP_GREATER_THAN_OR_EQUAL=">=";
  public static String OP_LESS_THAN_OR_EQUAL="<=";
  public static String OP_EQUAL_OR_GREATER_THAN="=>";
  public static String OP_EQUAL_OR_LESS_THAN="=<";
}
