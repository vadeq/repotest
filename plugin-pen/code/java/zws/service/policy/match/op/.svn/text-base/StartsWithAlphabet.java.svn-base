package zws.service.policy.match.op;
/*
 * DesignState - Design Compression Technology
 * @author:ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.data.Metadata;
//impoer zws.util.{}//Logwriter;

/**
 * The Class CompareAttributeOP.
 *
 * @author ptoleti
 */
public class StartsWithAlphabet extends PolicyMatchOpBase {

  /**
   * @throws Exception
   * Exception
   * @see zws.pen.policy.op.PENDataOpBase#execute()
   */
  public void execute() throws Exception {
    {}//Logwriter.printOnConsole("CompareAttributeOP execute....");
    String finalString = null;
    boolean isOK= false;
    String metaDataAttrValue = null;
    Metadata metaData = getMetaData();
    //printValues(metaData);
    metaDataAttrValue = metaData.get(getAttributeName());
    {}//Logwriter.printOnConsole("Attribute name .... " + getAttributeName());
    {}//Logwriter.printOnConsole("Attribute value .... " + metaDataAttrValue);
    if (null==metaDataAttrValue) metaDataAttrValue = "";
    if(metaDataAttrValue.length()!=0) {
      char c[] = metaDataAttrValue.toCharArray();
      char firstChar = c[0];
      if((firstChar >= 'A' && firstChar <= 'Z') || 
          (firstChar >= 'a' && firstChar <= 'z')) {
        isOK = true;
      }
    }
    finalString = new Boolean(isOK).toString();
    {}//Logwriter.printOnConsole("finalString in StartsWithNumberAlphabet.... " + finalString);
    setResult(finalString);
  }

  
  public String getAttributeName() {return attributeName;}
  public void setAttributeName(String s) {attributeName = s;}

  private String attributeName = null;

  
  private void printValues(Metadata metaData) {
    {}//Logwriter.printOnConsole("meta data" + metaData.toString());
    {}//Logwriter.printOnConsole("DOMAIN_NAME " + metaData.get(Metadata.DOMAIN_NAME));
    {}//Logwriter.printOnConsole("SERVER_NAME " + metaData.get(Metadata.SERVER_NAME));
    {}//Logwriter.printOnConsole("REPOSITORY_NAME " + metaData.get(Metadata.REPOSITORY_NAME));
    {}//Logwriter.printOnConsole("BRANCH  " + metaData.get(Metadata.BRANCH));
    {}//Logwriter.printOnConsole("REVISION " + metaData.get(Metadata.REVISION));
    {}//Logwriter.printOnConsole("VERSION " + metaData.get(Metadata.VERSION));
    {}//Logwriter.printOnConsole("CREATED_ON " + metaData.get(Metadata.CREATED_ON));
    {}//Logwriter.printOnConsole("CREATED_BY " + metaData.get(Metadata.CREATED_BY));
    {}//Logwriter.printOnConsole("FILE_TYPE " + metaData.get(Metadata.FILE_TYPE));
    {}//Logwriter.printOnConsole("LOCATION " + metaData.get(Metadata.LOCATION));
    {}//Logwriter.printOnConsole("TIME_OF_CREATION " + metaData.get(Metadata.TIME_OF_CREATION));
    {}//Logwriter.printOnConsole("RELEASE_LEVEL " + metaData.get(Metadata.RELEASE_LEVEL));
    {}//Logwriter.printOnConsole("LOCK_STATUS " + metaData.get(Metadata.LOCK_STATUS));
    {}//Logwriter.printOnConsole("OWNER " + metaData.get(Metadata.OWNER));
    {}//Logwriter.printOnConsole("ORIGIN " + metaData.get(Metadata.ORIGIN));
    {}//Logwriter.printOnConsole("NAME " + metaData.get(Metadata.NAME));
    {}//Logwriter.printOnConsole("COUNT " + metaData.get(Metadata.COUNT));
  }

}
