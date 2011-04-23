package zws.action.condition.comparison;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 28, 2004, 11:06 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.action.condition.Conditional;
import zws.op.Op;


public abstract class Comparison extends Conditional {
  public static String STRING="string";
  public static String INT="int";

  protected abstract boolean compare(String value1, String value2) throws Exception;
  protected abstract boolean compare(int value1, int value2) throws Exception;

  public final boolean isTrue() throws Exception {
    String val = getRequiredString("value");
    String compareToVal = getRequiredString("compareTo");
    if (isInt()) return compare(asInt(val), asInt(compareToVal));
    else return compare(val, compareToVal);
  }

  private int asInt(String s) { return Integer.valueOf(s).intValue(); }

  public String getValue() { return value; }
  public void setValue(String s) { value=s; }
  public String getMetaValue() { return metaValue; }
  public void setMetaValue(String s) { metaValue=s; }
  public String getCtxValue() { return ctxValue; }
  public void setCtxValue(String s) { ctxValue=s; }
  public Op getValueOp() { return valueOp; }
  public void setValueOp(Op op) { valueOp=op; }
  public String getCtxDefaultValue() { return ctxDefaultValue; }
  public void setCtxDefaultValue(String s) { ctxDefaultValue=s; }

  public String getCompareTo() { return compareTo; }
  public void setCompareTo(String s) { compareTo=s; }
  public String getMetaCompareTo() { return metaCompareTo; }
  public void setMetaCompareTo(String s) { metaCompareTo=s; }
  public String getCtxCompareTo() { return ctxCompareTo; }
  public void setCtxCompareTo(String s) { ctxCompareTo=s; }
  public Op getCompareToOp() { return compareToOp; }
  public void setCompareToOp(Op op) { compareToOp=op; }
  public String getCtxDefaultCompareTo() { return ctxDefaultCompareTo; }
  public void setCtxDefaultCompareTo(String s) { ctxDefaultCompareTo=s; }

  public boolean getIgnoreCase() { return ignoreCase; }
  public void setIgnoreCase(boolean b) { ignoreCase=b; }

  public void add(Op op) {
    if (null==value && null==metaValue && null==ctxValue && null==valueOp && null==ctxDefaultValue) valueOp=op;
    else compareToOp=op;
  }
  public String getType() { return type; }
  public void setType(String s) throws Exception {
    type=s.trim().toLowerCase();
    if (!STRING.equals(type) && !type.startsWith(INT)) throw new Exception("Type " + s + " is not yet supported for class: " + getClass().getName());
  }

  public boolean isInt() { return type.startsWith(INT); }

  private String value=null;
  private String metaValue=null;
  private String ctxValue=null;
  private Op valueOp=null;
  private String ctxDefaultValue=null;

  private String compareTo=null;
  private String metaCompareTo=null;
  private String ctxCompareTo=null;
  private Op compareToOp=null;
  private String ctxDefaultCompareTo=null;

  private String type = STRING;
  private boolean ignoreCase=true;
}
