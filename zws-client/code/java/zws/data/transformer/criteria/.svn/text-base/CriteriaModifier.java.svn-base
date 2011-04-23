package zws.data.transformer.criteria; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.OpBase;

import java.util.StringTokenizer;

public abstract class CriteriaModifier extends OpBase {

  public final void execute() throws Exception { modify(); }

  public final void modify () throws Exception {
    StringTokenizer tok = new StringTokenizer(getCriteria(), getDelimiter());
    String token = getCriteria();;
    String newCriteria="";
    if (tok.hasMoreTokens()) token = tok.nextToken();
    newCriteria = modifyCriteriaToken(token);
    while (tok.hasMoreTokens())
      newCriteria += getDelimiter() + modifyCriteriaToken(tok.nextToken());
    setCriteria(newCriteria);
  }

  public abstract String modifyCriteriaToken(String token);

  public CriteriaModifier() {}
  public void bind(Object o) { setCriteria(criteria=o.toString()); }
  public Object getResult() { return getCriteria(); }

  public String getCriteria() { return criteria; }
  public void setCriteria(String s) { criteria = s; }

  public String getDelimiter() { return delimiter; }
  public void setDelimiter(String s) { delimiter = s; }

  public String getWildCardCharacter() { return wildCardCharacter; }
  public void setWildCardCharacter(String s) { wildCardCharacter = s; }

  private String criteria;
  private String delimiter = " ";
  private String wildCardCharacter = "*";
}
