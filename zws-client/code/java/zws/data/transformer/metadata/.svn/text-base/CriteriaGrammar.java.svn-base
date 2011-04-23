package zws.data.transformer.metadata;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.exception.InvalidSyntax;

import java.util.*;

public class CriteriaGrammar {

  public static String reverseMapCriteria(String criteria, MetadataRemappingSpec mappingSpec) throws InvalidSyntax {
    StringBuffer reverseMappedCriteria = new StringBuffer();
    Collection ands = reverseMap(getCriteriaBlocks(criteria.trim()), mappingSpec);
    Iterator i = ands.iterator();
    if (i.hasNext())
      reverseMappedCriteria.append(AND_START+" ").append((String)i.next()).append(" " + AND_END);
    while (i.hasNext())
      reverseMappedCriteria.append(" "+OR_DELIMITER+" ").append(AND_START + " ").append((String)i.next()).append(" " + AND_END);
    {} //System.out.println("Reverse Mapped criteria to: " + reverseMappedCriteria.toString());
    return reverseMappedCriteria.toString();
  }

  private static Collection getCriteriaBlocks(String criteria) throws InvalidSyntax {
    String block=null;
    Collection blocks = new Vector();
    block = criteria.trim();
    if (block.startsWith(OR_DELIMITER) || block.startsWith(AND_DELIMITER)) throw new InvalidSyntax("Can not start criteria with an operation: " + criteria);
    if (block.startsWith(AND_START) && !block.endsWith(AND_END)) throw new InvalidSyntax("Mismatched " + AND_START + "..."+ AND_END +": " +criteria);
    if (!block.startsWith(AND_START) && !block.endsWith(AND_END)) {
      blocks.add(block);
      return blocks;
    }  
    StringTokenizer tokens = new StringTokenizer(criteria, AND_END);
    if (tokens.hasMoreTokens()){
      block = tokens.nextToken().trim();
      if (!block.startsWith(AND_START)) throw new InvalidSyntax("Mismatched " + AND_START + "..."+ AND_END +": " +criteria);
      blocks.add(block.substring(1));
    }
    while (tokens.hasMoreTokens()){
      block = tokens.nextToken().trim();
      if (!block.startsWith(OR_DELIMITER)) throw new InvalidSyntax("Groups can only be OR'ed together" +criteria);
      block = block.substring(1).trim();
      if (!block.startsWith(AND_START)) throw new InvalidSyntax("Mismatched " + AND_START + "..."+ AND_END +": " +criteria);
      blocks.add(block.substring(1));
    }
    return blocks;
  }
  
  private static Collection reverseMap(Collection blocks, MetadataRemappingSpec mappingSpec) throws InvalidSyntax {
    Collection c = new Vector();
    String match;
    String reverseMappedCriteria = "";
    StringTokenizer andTokens=null;
    Iterator i = blocks.iterator();
    String item;
    while (i.hasNext()) {
      reverseMappedCriteria = "";
      item = (String)i.next();
      andTokens = new StringTokenizer(item, AND_DELIMITER);
      if (!andTokens.hasMoreTokens()) c.add(reverseMapItem(item, mappingSpec)); 
      else if (1==andTokens.countTokens()) {
        match = reverseMapItem(andTokens.nextToken(), mappingSpec);
        if (null!=match) c.add(match);
      }
      else {
        match = reverseMapItem(andTokens.nextToken(), mappingSpec);
        if (null!=match) reverseMappedCriteria = match;
        while (andTokens.hasMoreTokens()) {
          match = reverseMapItem(andTokens.nextToken(), mappingSpec);
          if (null!=match) {
              if (!"".equals(reverseMappedCriteria)) reverseMappedCriteria += AND_DELIMITER;
              reverseMappedCriteria += match;
          }
        }
        if (!"".equals(reverseMappedCriteria)) c.add(reverseMappedCriteria);
      }
    }
    return c;
  }
  
  private static String reverseMapItem(String match, MetadataRemappingSpec mappingSpec) throws InvalidSyntax {
    String lValue = null, reverseLValue=null, rValue = null;
    StringTokenizer tokens = new StringTokenizer(match, EQUALS);
    if (tokens.hasMoreTokens()) lValue=tokens.nextToken().trim();
    else throw new InvalidSyntax(match);
    if (tokens.hasMoreTokens()) rValue = tokens.nextToken().trim();
    else throw new InvalidSyntax(match);
    reverseLValue = mappingSpec.getMapping(lValue);
//    if (null==reverseLValue || "".equals(reverseLValue)) return lValue + EQUALS + rValue;
    if (null==reverseLValue || "".equals(reverseLValue)) return null;
    else return reverseLValue + EQUALS + rValue;
  }

  private static String EQUALS = Names.CRITERIA_EQUALS;
  private static String OR_DELIMITER = Names.CRITERIA_OR_OP;
  private static String AND_DELIMITER = Names.CRITERIA_AND_OP;
  private static String AND_START = Names.CRITERIA_GROUP_START_BLOCK;
  private static String AND_END = Names.CRITERIA_GROUP_END_BLOCK;
}
