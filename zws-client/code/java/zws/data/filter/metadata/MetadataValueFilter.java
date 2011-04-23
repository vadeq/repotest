package zws.data.filter.metadata;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.op.Op;
import zws.util.comparator.StringComparator;

public class MetadataValueFilter extends MetadataUnitFilterBase {

  public void initializeCompareToValue() throws Exception {
    if (null==compareToOp) return;
    compareToOp.execute();
    setCompareToValue((String)compareToOp.getResult());
  }

  public boolean keep(Metadata data) throws Exception {
    initializeCompareToValue();
    StringComparator comparator = new StringComparator();
    comparator.setData(data.get(getMetadataField()));
    if (getComparison().equals(OP_EQUAL)) return comparator.isEqual(getCompareToValue());
    else if (getComparison().equals(OP_EQ)) return comparator.isEqual(getCompareToValue());
    else if (getComparison().equals(OP_NOT_EQUAL)) return comparator.isNotEqual(getCompareToValue());
    else if (getComparison().equals(OP_NOT_EQ)) return comparator.isNotEqual(getCompareToValue());
    else if (getComparison().equals(OP_LESS_THAN)) return comparator.isLessThan(getCompareToValue());
    else if (getComparison().equals(OP_GREATER_THAN)) return comparator.isGreaterThan(getCompareToValue());
    else if (getComparison().equals(OP_LESS_THAN_OR_EQUAL)) return comparator.isLessThanOrEqual(getCompareToValue());
    else if (getComparison().equals(OP_EQUAL_OR_LESS_THAN)) return comparator.isLessThanOrEqual(getCompareToValue());
    else if (getComparison().equals(OP_GREATER_THAN_OR_EQUAL)) return comparator.isGreaterThanOrEqual(getCompareToValue());
    else if (getComparison().equals(OP_EQUAL_OR_GREATER_THAN)) return comparator.isGreaterThanOrEqual(getCompareToValue());
    else throw new Exception("Specified an unknown comparison: " + getComparison() + "[metadata="+getMetadataField()+" type="+getType()+" value=" + getCompareToValue()+"]");
  }

  public void add(Op op) { setCompareToOp(op); }
  public Op getCompareToOP(){ return compareToOp; }
  public void setCompareToOp(Op op) { compareToOp=op; }
  public String getMetadataField() { return metadataField; }
  public void setMetadataField(String s) { metadataField=s; }
  public String getCompareToValue() { return compareToValue; }
  public void setCompareToValue(String s) { compareToValue=s; }
  public String getType() { return type; }
  public void setType(String s) { type=s; }
  public boolean getIgnoreCase() { return ignoreCase; }
  public void setIgnoreCase(boolean b) { ignoreCase=b; }
  public String getComparison() { return comparison; }
  public void setComparison(String s) { comparison=s; }

  private String metadataField=null;
  private String compareToValue=null;
  private Op compareToOp=null;
  private String type="string";
  public boolean ignoreCase=true;
  
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
