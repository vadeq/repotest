package zws.data.filter.metadata;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.data.Metadata;
import zws.exception.InitializationError;
import zws.exception.InvalidSyntax;
import zws.logical.*;

import java.util.*;

// Filters Metadata based on a grammar: i.e.
// [ name=abc | name=cde | revision=A ] [ version=b ]
// will pass if  (name='abc' OR name='cde' OR revision='A') AND (version='b')
// AND blocks are contained between '[ ... ]' 
// AND blocks are anded.
// within AND blocks, criteria can be OR'ed using '|'
// Does not support nested AND blocks like '[ .. [ ...] .. ]' - so expand your criteria
public class CriteriaFilter extends MetadataUnitFilterBase {

  //criteria must be set before initializing
  public void initialize() throws InitializationError {
    try {
      Collection criteriaBlocks = getCriteriaBlocks(getCriteria().trim());
      logicTree = createLogicTree(criteriaBlocks);
    }
    catch(InvalidSyntax e) { throw new InitializationError("Invalid Syntax: " + e.getMessage()); }
    catch(Exception e) { throw new InitializationError(e.getClass().getName() + ": " + e.getMessage()); }
  }

  public boolean keep(Metadata data) throws Exception {
    if (null==getCriteria() || null==data) return false;
    bindLogicals(data);
    return logicTree.isTrue();
  }

  private void bindLogicals(Metadata data) throws Exception { 
    MetadataFieldEquals logical = null;
    Iterator i = metadataFieldLogicals.iterator();
    while (i.hasNext()) {
      logical = (MetadataFieldEquals) i.next();
      logical.bind(data);
    }
  }

  private Collection getCriteriaBlocks(String grammar) throws InvalidSyntax {
    Collection blocks = new Vector();
    if (!grammar.startsWith(GROUP_START) && !grammar.endsWith(GROUP_END)) {
      blocks.add(grammar);
      return blocks;
    } 
    StringTokenizer tokens = new StringTokenizer(grammar, GROUP_END);
    String block=null;
    while (tokens.hasMoreTokens()){
      block = tokens.nextToken().trim();
      if (!block.startsWith(GROUP_START)) throw new InvalidSyntax("Mismatched " + GROUP_START + "..."+ GROUP_END +": " +grammar);
      blocks.add(block.substring(1));
    }
    return blocks;
  }
  
  
  //conversts criteria grammar such as '[or-syntax-1] [or-syuntax-2]' to Logical AND:  (OR-1) AND (OR-2) 
  private LogicalOp createLogicTree(Collection blocks) throws Exception{
    Iterator i = blocks.iterator();
    if (blocks.isEmpty()) return null;
    if (1==blocks.size()) { return createLogicSet((String)i.next()); }
    And and = new And();
    while(i.hasNext()) and.add(createLogicSet((String)i.next()));
    return and;
  }

  //converts portion criteria grammar such as 'name=10032.prt | revision=A' to Logical OR for the 2 matches
  private LogicalOp createLogicSet(String items) throws Exception {
    StringTokenizer orTokens = new StringTokenizer(items, OR_DELIMITER);
    if (!orTokens.hasMoreTokens()) return createMetadataFieldEquals(items); 
    if (1==orTokens.countTokens()) return createMetadataFieldEquals(orTokens.nextToken()); 
    Or or= new Or();
    while (orTokens.hasMoreTokens()) or.add(createMetadataFieldEquals(orTokens.nextToken()));
    return or;
  }
  
  //converts a token such as name=10053.drw to a Logical to see if metadata field is equal to specified value
  private MetadataFieldEquals createMetadataFieldEquals(String match) throws InvalidSyntax {
    StringTokenizer tokens = new StringTokenizer(match, EQUALS);
    MetadataFieldEquals logical = new MetadataFieldEquals();
    if (tokens.hasMoreTokens()) logical.setFieldName(tokens.nextToken());
    else throw new InvalidSyntax(criteria);
    if (tokens.hasMoreTokens()) logical.setValue(tokens.nextToken());
    else throw new InvalidSyntax(criteria);
    metadataFieldLogicals.add(logical);
    return logical;
  }
    
  public String getCriteria() { return criteria; }
  public void setCriteria(String s) { criteria=s; }
  

  private String criteria=null;
  private LogicalOp logicTree = null;
  private Collection metadataFieldLogicals = new Vector();
  
  private static String EQUALS = Names.CRITERIA_EQUALS;
  private static String OR_DELIMITER = Names.CRITERIA_OR_OP;
  private static String GROUP_START = Names.CRITERIA_GROUP_START_BLOCK;
  private static String GROUP_END = Names.CRITERIA_GROUP_END_BLOCK;

}
