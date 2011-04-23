/**
 *
 */
package zws.service.policy.match.op;
/*
 * DesignState - Design Compression Technology
 * @author:ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.data.Metadata;
import zws.util.FileNameUtil;
//import zws.util.{}//Logwriter;

/**
 * The Class CompareAttributeOP.
 *
 * @author ptoleti
 */
public class CompareItemTypeOP extends PolicyMatchOpBase {

  /**
   * @throws Exception
   * Exception
   * @see zws.pen.policy.op.PENDataOpBase#execute()
   */
  public void execute() throws Exception {
    {}//Logwriter.printOnConsole("CompareAttributeOP execute....");
    String finalString = new Boolean(false).toString();
    try {
      finalString = new Boolean(false).toString();
      Metadata metaData = getMetaData();
      //printValues(metaData);
      String itemType = FileNameUtil.getFileNameExtension(metaData.getName());
      String CADNameType = FileNameUtil.getFileNameExtension(metaData.get("CADName"));
      String compareTo = getCheckFor();
      if (null==itemType || "".equals(itemType.trim())) itemType="";
      if (null==CADNameType || "".equals(CADNameType.trim())) CADNameType="";
      if (null==compareTo || "".equals(compareTo.trim())) compareTo="";
      if(itemType.equalsIgnoreCase(compareTo) ||
         CADNameType.equalsIgnoreCase(compareTo)) {
        finalString = new Boolean(true).toString();
      }
      setResult(finalString);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }



  public String getCheckFor() {return checkFor;}
  public void setCheckFor(String s) {checkFor = s;}


  private String checkFor= null;


  /**
   * @param metaData metadata
   */
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
