package zws.data.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 31, 2004, 3:05 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.data.Metadata;
import zws.op.OpBase;

import java.util.StringTokenizer;

public class MetadataKeyMaker extends OpBase {
  public void execute() { setResult(makeKey((Metadata)getBinding())); }

  public String makeKey(Metadata data) {
    if (null==keyFields || "".equals(keyFields)) return data.getOrigin().toString();
    StringTokenizer keyFieldTokens = new StringTokenizer(keyFields, Names.DELIMITER);
    if (2 > keyFieldTokens.countTokens()) return data.get(keyFields);
    String key=data.get(keyFieldTokens.nextToken().trim());
    while (keyFieldTokens.hasMoreTokens()) key += Names.DELIMITER + data.get(keyFieldTokens.nextToken().trim());
    return key;
  }
  public String getKeyFields() { return keyFields; }
  public void setKeyFields(String s) { keyFields=s; }

  private String keyFields=null;
}
