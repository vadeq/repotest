package zws.util.comparator;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 31, 2004, 3:49 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.topology.Node;

import java.io.Serializable;
import java.util.Comparator;

public class TopologyNodeComparator implements Serializable, Comparator{
  
  public static void main(String[] args) {
    TopologyNodeComparator c = new TopologyNodeComparator();
    c.run();
  }
    
  public void run() {
    String a;
    String b;
    a = "ab-99";
    b = "ab_100";
    p(a,b);
  }

  public void p(String a, String b) {
    int c = compare(a,b);
    //if (c==0) {} //System.out.println(a + " = " +b);
    //else if (c<0) {} //System.out.println(a + " < " +b);
    //else if (c>0) {} //System.out.println(b + " < " + a);      
  }
  
  public boolean getAscendingOrder() { return ascendingOrder; }
  public void setAscendingOrder(boolean b) { ascendingOrder=b; }
  private int order(int z) { if (ascendingOrder) return z; else return (-1)*z; }
  //designed w/ ASCII char set in mind
  public int compare(Object x0, Object x1) {
    if (null==x0 && null==x1) return 0;
    if (null==x0) return order(-1);
    if (null==x1) return order(+1);
    if (x0.equals(x1)) return 0;
    return order(compare((Node)x0, (Node)x1));
  }

  public int compare(Node s0, Node s1) {
    if (null==s0 && null==s1)  return 0;
    else if (null==s0) return order(-1);
    else if (null==s1) return order(+1);
    else if (s0.equals(s1)) return 0;
    a0 = s0.getIPAddress().toCharArray();
    a1 = s1.getIPAddress().toCharArray();
    comparison=0;
    idx0=idx1=0;
    minimum = min(a0.length,a1.length);
    while (0==comparison && idx0<a0.length &&  idx1<a1.length) {
      while (idx0<a0.length &&  idx1<a1.length && a0[idx0]==a1[idx1] ){ idx0++; idx1++; }
      if (idx0==a0.length || idx1==a1.length) break;
      c0 = a0[idx0];
      c1 = a1[idx1];
      c0isNumber = isNumber(c0);
      c1isNumber = isNumber(c1);
      if (c0isNumber && !c1isNumber) {
        if (isLetter(c1) || isHighAlpha(c1)) return order(-1);
        else return order(+1);
      }
      if (!c0isNumber && c1isNumber) {
        if (isLetter(c0) || isHighAlpha(c0)) return order(+1);
        else return order(-1);
      }
      if (c0isNumber && c1isNumber) { //compare next sequence of numbers
        comparison = compareNumber(a0, idx0, a1, idx1);
        if (0!=comparison) return order(comparison);
        idx0 = nextNonNumericIndex(a0, idx0);
        idx1 = nextNonNumericIndex(a1, idx1);        
      }
      else { //compare alphas
        comparison = compareAlpha(c0, c1);
        if (0!=comparison) return order(comparison);
        idx0++;
        idx1++;
      }
    }
    if (0==comparison) {
      if (a0.length<a1.length) return order(-1);
      if (a0.length>a1.length) return order(+1);
    }
    return 0;
  }
  
  private int min(int n0, int n1) { if (n0<n1) return n0; else return n1; }

  public static boolean isNumber(char c) { return c >= '0' && c <= '9'; }
  public static boolean isLetter(char c) { return (c>='a'&&c<= 'z') || (c>='A'&&c<='Z'); }
  public static boolean isLowerCase(char c) { return (c>='a'&&c<= 'z'); }
  public static boolean isUpperCase(char c) { return (c>='A'&&c<='Z'); }
  
  private int compareNumber(char[] a0, int idx0, char[] a1, int idx1) {
    n0buffer = new StringBuffer();
    n1buffer = new StringBuffer();
    
    while (idx0<a0.length && isNumber(a0[idx0])) n0buffer.append(a0[idx0++]);
    while (idx1<a1.length && isNumber(a1[idx1])) n1buffer.append(a1[idx1++]);
    n0 = Long.parseLong(n0buffer.toString());
    n1 = Long.parseLong(n1buffer.toString());
    if (n0<n1) return -1;
    if (n0>n1) return 1;
    return 0;
  }

  private int nextNonNumericIndex(char[] a, int offset) {
    idx = offset;
    isNumber=true;
    while (isNumber && idx<a.length ) {
      c = a[idx];
      isNumber=(c>='0' && c<='9');
      if (isNumber) idx++;
    }
    return idx;
  }

  private int compareAlpha (char c0, char c1) { 
    if (c0==c1) return 0;
    
    c0isLowerCase = c0 >= 'a' && c0 <=  'z';
    c1isLowerCase = c1 >= 'a' && c1 <=  'z';
    c0isUpperCase = c0 >= 'A' && c0 <=  'Z';
    c1isUpperCase = c1 >= 'A' && c1 <=  'Z';
    c0isLetter = c0isLowerCase || c0isUpperCase;
    c1isLetter = c1isLowerCase || c1isUpperCase;

    if ( (c0isLowerCase&&c1isLowerCase) || (c0isUpperCase && c1isUpperCase) || (!c0isLetter && !c1isLetter) ){
      if (c0<c1) return -1;
      else return 1;
    }
    if (c0isLowerCase && c1isUpperCase) return -1;
    if (c0isUpperCase && c1isLowerCase) return 1;

    if (!c1isLetter){ //&& c0isLetter
      if (isHighAlpha(c1)) return -1;
      else return 1;
    }
    else {// (!c0isLetter && c1isLetter)
      if (isHighAlpha(c0)) return 1;
      else return -1;
    }
  }

  private boolean isHighAlpha(char c) {
    return (c=='|') || (c=='-') || (c=='_') || (c=='=') || (c=='~') || (c=='#');
  }
  private boolean c0isLowerCase, c1isLowerCase, c0isUpperCase, c1isUpperCase, c0isLetter, c1isLetter, isNumber, c0isNumber, c1isNumber;
  private int idx0=0, idx1=0, idx, comparison, minimum;
  private String val0, val1;
  private char[] a0, a1;
  private char c, c0, c1;
  private long n0, n1;
  private StringBuffer n0buffer, n1buffer;

  private boolean ascendingOrder=true;
}
