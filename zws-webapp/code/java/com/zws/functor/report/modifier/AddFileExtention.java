package com.zws.functor.report.modifier;

import java.util.StringTokenizer;

public class AddFileExtention extends CriteriaModifier {

  public String modifyCriteriaToken(String token) {
    String newCriteria ="";
    StringTokenizer fileTypes = new StringTokenizer(getFileTypeExtentions(), getDelimiter());
    String extention=getFileTypeExtentions();
    if (fileTypes.hasMoreTokens())
      newCriteria = addFileTypeExtention(token, fileTypes.nextToken());
    while (fileTypes.hasMoreTokens()) {
      newCriteria += getDelimiter() + addFileTypeExtention(token, fileTypes.nextToken());
    }
    return newCriteria;
  }

  public String addFileTypeExtention(String criteria, String extention) {
    if (criteria.endsWith(extention)) return criteria;
    if (criteria.endsWith(getWildCardCharacter())) return criteria+extention;
    else return criteria + getWildCardCharacter() + extention;
  }

  public String getFileTypeExtentions() { return fileTypeExtentions; }
  public void setFileTypeExtentions(String s) { fileTypeExtentions = s; }

  private String fileTypeExtentions="";
}
