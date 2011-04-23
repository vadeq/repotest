package zws.util; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Mar 29, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import java.util.*;

public class PartNumberUtil {

  public int compare(String pn0, String pn1, boolean ignoreCase) {
    //see if part numbers are equal
    if (ignoreCase) { if (pn0.equalsIgnoreCase(pn1)) return 0;}
    else { if (pn0.equals(pn1)) return 0; }
    //partnumbers are not equal, find which one is greater..are.
    List t0 = tokenizePartNumber(pn0);
    List t1 = tokenizePartNumber(pn1);
    int compare;
    int tokens = t0.size();
    if (t1.size()<tokens) tokens = t1.size();
    for (int idx=0; idx < tokens; idx++) {
      compare = compareTokens(t0.get(idx).toString(), t1.get(idx).toString(), ignoreCase);
      if (compare!=0) return compare;
    }
    if (t0.size()<t1.size()) return 1;
    if (t0.size()>t1.size()) return -1;
    return 0;
  }

  public boolean isAlphabetical(char c) {
    if(c>='a' && c <='z') return true;
    if(c>='A' && c <='Z') return true;
    return false;
  }

  public boolean isNumerical(char c) {
    if(c>='0' && c <='9') return true;
    return false;
  }
  
  public int compareNumbers(String n0, String n1) {
    int i0 = Integer.valueOf(n0).intValue();
    int i1 = Integer.valueOf(n1).intValue();
    if (i0==i1) return 0;
    if (i0>i1) return 1;
    return -1;
  }
  
  private int compareTokens(String t0, String t1, boolean ignoreCase) {
    if (ignoreCase) { if (t0.equalsIgnoreCase(t1)) return 0;}
    else { if (t0.equals(t1)) return 0; }
      
    char c0 = t0.charAt(0);
    char c1 =t1.charAt(1);
    if (isAlphabetical(c0) && isAlphabetical(c1)) return t0.compareTo(t1);
    if (isNumerical(c0) && isNumerical(c1)) return compareNumbers(t0, t1);
    if(isAlphabetical(c0)) return 1;
    return -1;
  }
    
  private List tokenizePartNumber(String s) {
      char c;
      char mode='-';
      List tokens = new ArrayList();
      StringBuffer token = new StringBuffer();
      int idx;
      for (idx=0; idx<s.length(); idx++) {
        c = s.charAt(idx);
        if (isAlphabetical(c)) {
          if (mode=='#') {
           if (token.length()>0) tokens.add(token.toString());
           token = new StringBuffer();
          }
          mode='a';
          token.append(c);
        }
        else if (isNumerical(c)) {
          if ( mode=='a') {
            if (token.length()>0) tokens.add(token.toString());
            token = new StringBuffer();
          }
        mode='#';
        token.append(c);
        }
        //else skip the character if it is not a number or a letter
      }
      if (token.length()>0) tokens.add(token.toString());
      return tokens;
    }

}
