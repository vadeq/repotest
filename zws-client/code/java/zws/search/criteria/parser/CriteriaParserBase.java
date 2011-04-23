package zws.search.criteria.parser;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on June 29, 2004, 3:53 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.exception.InvalidSyntax;
import zws.op.OpBase;
import zws.search.criteria.*;
import zws.util.StringMapper;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.Vector;

// Simple parser 
// - no nested groups. 
// - groups separated by OR only  [ ...] | [ ... ] | [ ... ] ...
// - comparison separated by AND only:  [ f1=v1 & f2=v2 & ... & fn=vn ]
public class CriteriaParserBase extends OpBase implements CriteriaParser {
  public void reset() {
    orList.clear(); andList.clear();
    groups.clear(); 
    comparisons.clear(); 
  }
  public void execute() throws Exception { setResult(parse(getCriteria())); }
  
  /*it parses a string into a Criteria Tree 
   *A string like: "[A=1] & [c=3 | e=5 ] | [d=4 ] & [g=5 | f=6 & h=7] & [i=8 | j!= 9 | k=10] | [l=5] | [m=6 & n=7] "
   */
  public Criteria parse(String criteria) throws InvalidSyntax {
    Criteria crit = new Criteria(this);
    Expression X = null;
    LogicalOperator opX = null;
    Comparison comparisonX=null;
    Grouping groupX=null;

    LogicalOperator op=null, tmpOp=null;
    Comparison comparison=null;
    Grouping group=null;
    
    {} //System.out.println("EOS = " + EOS);
    {} //System.out.println("EOS = " + (int)EOS);
    StringBuffer fieldName;
    StringBuffer operator;
    StringBuffer rValue;
    StringReader reader = new StringReader(criteria);
    /*
      expression:  name='abc.prt',   rev > 'A'  release='pilot' name='123*'
    * ( name='abc.prt' )
    * ( name='abc.prt' + rev='b' )
    x ( name='abc.prt' ) | ( name='def.prt' )
    x ( name='abc.prt' + rev='b' ) | ( name='def.prt' + release='Pilot' )
    */
    
    try{
      char c=(char)reader.read();
      while (c!=EOS) {
        c = skipSpaces(reader, c);
        if (c==EOS) throw new InvalidSyntax(null);
        if (GROUP_START.charAt(0)==c) {
          comparisonX=null;
          group = registerNewGroup();
          c=(char)reader.read();
          c = skipSpaces(reader, c);
          if (X==null)X=group;
          groupX=null;
          op=null;
        }
//        if (groupX!=null && isValidLogicalOperatorChar(c)) throw new InvalidSyntax(reader.toString() + "<-");
        else if (groupX==null) {
          fieldName =new StringBuffer();
          operator = new StringBuffer();
          rValue=new StringBuffer();
          while (c!=EOS && isValidFieldNameChar(c)) {
            fieldName.append(c);
            c=(char)reader.read();
          }
          if (c==EOS) throw new InvalidSyntax(reader.toString());
          c = skipSpaces(reader, c);
          if (c==EOS) throw new InvalidSyntax(reader.toString());
          while (c!=EOS && isValidOperatorChar(c)) {
            operator.append(c);
            c=(char)reader.read();
          }
          if (c==EOS) throw new InvalidSyntax(reader.toString());
          if (!isValidOperator(operator.toString())) throw new InvalidSyntax(reader.toString() +": InValid Operator: " + operator);
          c = skipSpaces(reader, c);
          if (EOS==c) throw new InvalidSyntax(reader.toString());
          while (c!=EOS && isValidValueChar(c)) {
            rValue.append(c);
            c=(char)reader.read();
            {} //System.out.println((int)c); 
          }
          comparison = registerNewComparison();
          comparison.setFieldName(fieldName.toString().trim());
          comparison.setOperator(operator.toString().trim());
          comparison.setValue(rValue.toString().trim());
          if (null==comparisonX) comparisonX=comparison;
          if (X==null) X=comparison;
        }
        if (GROUP_END.charAt(0)==c) {
          groupX=group;
          if (null==op) group.setExpression(comparison);
          else {
            if (op!=null) {
              op.addExpression(comparison);
              group.setExpression(op);
              comparison=null;
              op=null;
            }
            else { group.setExpression(comparisonX); group=null; }
          }
          c = (char)reader.read();
          c = skipSpaces(reader, c);

          if (isValidLogicalOperatorChar(c)) {
            if (opX==null) {
              opX = createOperator(c);
              opX.addExpression(groupX);
              if (X==group) { X=opX;}
            }
            else if (opX.getOperator().charAt(0)!=c){
              opX.addExpression(group);
              tmpOp = createOperator(c);
              tmpOp.addExpression(opX);
              if (X==opX) X=tmpOp;
              opX=tmpOp;
              tmpOp=null;
            }
            else opX.addExpression(group);
            group=null;
          }
          else if (c==EOS) {
            if (opX!=null) opX.addExpression(group);
          }
          else throw new InvalidSyntax(reader.toString());
        }
        else if (isValidLogicalOperatorChar(c)) {
          if (op==null) {
            op = createOperator(c);
            op.addExpression(comparison);
            if (X==comparison) { X=op;}
          }
          else{
            op.addExpression(comparison);
            if (op.getOperator().charAt(0)!=c){
              tmpOp = createOperator(c);
              tmpOp.addExpression(op);
              if (X==op) { X=tmpOp; }
              op=tmpOp;
              tmpOp=null;
            }
          }
          c=(char)reader.read();
        }
        else if (c==EOS){
          if (null!=op) op.addExpression(comparison);
        }
      }
      crit.setExpression(X);
      return crit;
    }
    catch(InvalidSyntax e) { throw e; }
    catch(IOException e) { throw new InvalidSyntax(e.getMessage()); }
    catch(Exception e) { throw new InvalidSyntax(e.getMessage()); }
  }

  private LogicalOperator createOperator(char c) {
    LogicalOperator op = null;
    if (AND_OP.charAt(0)==c || PLUS_OP.charAt(0)==c) op=registerNewAND();
    else if (OR_OP.charAt(0)==c) op=registerNewOR();
    return op;
  }
  public Comparison parseComparison(StringReader reader) throws InvalidSyntax {
    StringBuffer fieldName=new StringBuffer();
    StringBuffer operator = new StringBuffer();
    StringBuffer rValue=new StringBuffer();
    Comparison comparison;
    try{
      char c=(char)reader.read();
      c = skipSpaces(reader, c);
      if (c==EOS) throw new InvalidSyntax(null);
      while (c!=EOS && isValidFieldNameChar(c)) {
        fieldName.append(c);
        c=(char)reader.read();
      }
      if (c==-1) throw new InvalidSyntax(reader.toString());
      c = skipSpaces(reader, c);
      if (c==-1) throw new InvalidSyntax(reader.toString());
      while (c!=EOS && isValidOperatorChar(c)) {
        operator.append(c);
        c=(char)reader.read();
      }
      if (-1==c) throw new InvalidSyntax(reader.toString());
      c = skipSpaces(reader, c);
      if (!isValidOperator(operator.toString())) throw new InvalidSyntax(reader.toString() +": InValid Operator: " + operator);
      c = skipSpaces(reader, c);
      if (-1==c) throw new InvalidSyntax(reader.toString());
      while (c!=EOS && isValidValueChar(c)) {
        rValue.append(c);
        c=(char)reader.read();
        {} //System.out.println((int)c); 
      }
      comparison = registerNewComparison();
      comparison.setFieldName(fieldName.toString().trim());
      comparison.setOperator(operator.toString().trim());
      comparison.setValue(rValue.toString().trim());
      return comparison;
    }
    catch(IOException e) { throw new InvalidSyntax(e.getMessage()); }
  }

  private boolean isValidOperator(String op) {
    if (op.equals(EQUALS) ||op.equals(NOT_EQUAL) ||op.equals(LESS_THAN) ||op.equals(NOT_LESS_THAN) ||op.equals(GREATER_THAN) ||op.equals(NOT_GREATER_THAN) || op.equals(LESS_THAN_OR_EQUAL) ||op.equals(GREATER_THAN_OR_EQUAL)) return true;
    return false;
  }
  private boolean isValidFieldNameChar(char c) {
    if (c==' ' || !isValidValueChar((char)c)) return false;
    return true;
  }
  private boolean isValidValueChar(char c) { 
    if (isValidOperatorChar(c)) return false;
    if (isValidLogicalOperatorChar(c)) return false;
    if (c=='[' || c==']' || c=='(' || c==')') return false;
    return true;
  }
  private boolean isValidOperatorChar(char c) { 
    {} //System.out.println("{"+c+"}");
    if (c==EQUALS.charAt(0) || c==NOT_EQUAL.charAt(0) || c==LESS_THAN.charAt(0) || c==GREATER_THAN.charAt(0)) return true;
    return false;
  }
  
  private boolean isValidLogicalOperatorChar(char c) { return (AND_OP.charAt(0)==c || PLUS_OP.charAt(0)==c || OR_OP.charAt(0)==c); }

  private char skipSpaces(StringReader reader, char c) throws IOException { 
    char x=c;
    while (x==' ') x=(char)reader.read(); 
    return x;
  }
  
  
  public String getCriteria() { return criteria; }
  public void setCriteria(String s) { criteria=s; }

  public Collection getORList() { return orList; }
  public Collection getANDList() { return andList; }
  public Collection getGroups() { return groups; }
  public Collection getComparisons() { return comparisons; }

  private Grouping registerNewGroup() {
    Grouping g = new Grouping();
    groups.add(g);
    return g;
  }

  private Comparison registerNewComparison() {
    Comparison c = new Comparison();
    comparisons.add(c);
    c.valueDisplayedInQuotes(isValueDisplayedInQuotes());
    c.setFieldNameMapper(getFieldNameMapper());
    return c;
  }

  private Comparison registerNewComparison(String fieldName, String operator, String value) {
    Comparison c = new Comparison();
    comparisons.add(c);
    c.setFieldName(fieldName); c.setOperator(operator); c.setValue(value);
    c.valueDisplayedInQuotes(isValueDisplayedInQuotes());
    c.setFieldNameMapper(getFieldNameMapper());
    return c;
  }
  
  private OROperator registerNewOR() {
    OROperator or = new OROperator();
    or.setOperatorDisplay(getOROperatorDisplay());
    orList.add(or);
    return or;
  }
  
  private ANDOperator registerNewAND() {
    ANDOperator and = new ANDOperator();
    and.setOperatorDisplay(getANDOperatorDisplay());
    andList.add(and);
    return and;
  }
    
  public boolean supportsDeepCopy() { return true; }
  public Object deepCopy() {
    CriteriaParserBase copy = new CriteriaParserBase();
    copy.setCriteria(getCriteria());
    copy.setResult(getResult());
    copy.orList.addAll(orList);
    copy.andList.addAll(andList);
    copy.groups.addAll(groups);
    copy.comparisons.addAll(comparisons);
    return copy;
  }  


  /**
   * @return the aNDOperatorDisplay
   */
  public String getANDOperatorDisplay() {
    return ANDOperatorDisplay;
  }
  /**
   * @param operatorDisplay the aNDOperatorDisplay to set
   */
  public void displayAsANDOperator(String operatorDisplay) {
    ANDOperatorDisplay = operatorDisplay;
  }
  /**
   * @return the oROperatorDisplay
   */
  public String getOROperatorDisplay() {
    return OROperatorDisplay;
  }
  /**
   * @param operatorDisplay the oROperatorDisplay to set
   */
  public void displayAsOROperator(String operatorDisplay) {
    OROperatorDisplay = operatorDisplay;
  }  
  /**
   * @return the displayValuesInQuotes
   */
  public boolean isValueDisplayedInQuotes() {
    return displayValueInQuotes;
  }
  /**
   * @param displayValuesInQuotes the displayValuesInQuotes to set
   */
  public void displayValueInQuotes(boolean displayInQuotes) {
    displayValueInQuotes = displayInQuotes;
  }
  
  public StringMapper getFieldNameMapper() {
    return fieldNameMapper;
  }
  
  public void setFieldNameMapper(StringMapper mappingOp) {
    fieldNameMapper = mappingOp;
  }
  
  private String criteria=null;
  protected Collection orList = new Vector();
  protected Collection andList = new Vector();
  protected Collection groups = new Vector();
  protected Collection comparisons = new Vector();
  
  private static char EOS = (char)-1;
  private static String EQUALS = Names.CRITERIA_EQUALS;
  private static String NOT_EQUAL= Names.CRITERIA_NOT_EQUAL;
  private static String LESS_THAN = Names.CRITERIA_LESS_THAN;
  private static String NOT_LESS_THAN = Names.CRITERIA_NOT_LESS_THAN;
  private static String LESS_THAN_OR_EQUAL = Names.CRITERIA_LESS_THAN_OR_EQUAL;
  private static String GREATER_THAN = Names.CRITERIA_GREATER_THAN;
  private static String NOT_GREATER_THAN = Names.CRITERIA_NOT_GREATER_THAN;
  private static String GREATER_THAN_OR_EQUAL = Names.CRITERIA_GREATER_THAN_OR_EQUAL;
  
  private static String OR_OP = Names.CRITERIA_OR_OP;
  private static String AND_OP = Names.CRITERIA_AND_OP;
  private static String PLUS_OP = Names.CRITERIA_PLUS_OP;
  private static String GROUP_START = Names.CRITERIA_GROUP_START_BLOCK;
  private static String GROUP_END = Names.CRITERIA_GROUP_END_BLOCK;
  
  
  private String OROperatorDisplay;  
  private String ANDOperatorDisplay;
  private boolean displayValueInQuotes = true;
  StringMapper fieldNameMapper = null;
}
  /*
  public Criteria parse(String criteria) throws InvalidSyntax {
    reset();
    Criteria c = new Criteria(this);
    Collection g = getCriteriaGroups(criteria);
    if (null==g || 0==g.size()) throw new InvalidSyntax(criteria);
    else if (1==g.size()) c.setExpression((Group)g.toArray()[0]);
    else {
      OROperator operator = registerNewOR();
      operator.addExpressions(g);
      c.setExpression(operator);
    }
    return c;
  }
  
  private Collection getCriteriaGroups(String criteria) throws InvalidSyntax {
    String block=null;
    Collection blocks = new Vector();
    block = criteria.trim();
    if (block.startsWith(OR_OP) || block.startsWith(AND_OP)) throw new InvalidSyntax("Can not start criteria with an operation: " + criteria);
    if (block.startsWith(GROUP_START) && !block.endsWith(GROUP_END)) throw new InvalidSyntax("Mismatched " + GROUP_START + "..."+ GROUP_END +": " +criteria);
    if (!block.startsWith(GROUP_START) && !block.endsWith(GROUP_END)) {
      blocks.add(parseGroup(block));
      return blocks;
    }
    StringTokenizer tokens = new StringTokenizer(block, GROUP_END);
    if (tokens.hasMoreTokens()){
      block = tokens.nextToken().trim();
      if (!block.startsWith(GROUP_START)) throw new InvalidSyntax("Mismatched " + GROUP_START + "..."+ GROUP_END +": " +criteria);
      blocks.add(parseGroup(block.substring(1)));
    }
    while (tokens.hasMoreTokens()){
      block = tokens.nextToken().trim();
      //if (!block.startsWith(OR_DELIMITER)) throw new InvalidSyntax("Groups can only be OR'ed together for now" +criteria);
      block = block.substring(1).trim();
      if (!block.startsWith(GROUP_START)) throw new InvalidSyntax("Mismatched " + GROUP_START + "..."+ GROUP_END +": " +criteria);
      blocks.add(parseGroup(block.substring(1)));
    }
    return blocks;
  }
  private Group parseGroup(String crit) throws InvalidSyntax {
    Group g = registerNewGroup();
    if (crit.indexOf('&') == -1) {
      g.setExpression(parseComparison(crit));
      return g;
    }
    StringTokenizer andTokens=null;
    andTokens = new StringTokenizer(crit.trim(), AND_OP);
    ANDOperator ands = registerNewAND();
    g.setExpression(ands);
    while (andTokens.hasMoreTokens()) ands.addExpression(parseComparison(andTokens.nextToken().trim()));
    return g;
  }
  private Comparison parseComparison(String crit) throws InvalidSyntax {
    String lValue = null, rValue = null;
    StringTokenizer tokens = new StringTokenizer(crit, EQUALS);
    if (tokens.hasMoreTokens()) lValue=tokens.nextToken().trim();
    else throw new InvalidSyntax(crit);
    if (tokens.hasMoreTokens()) rValue = tokens.nextToken().trim();
    else throw new InvalidSyntax(crit);
    Comparison c = registerNewComparison(lValue, EQUALS, rValue);
    return c;
  }
   */
