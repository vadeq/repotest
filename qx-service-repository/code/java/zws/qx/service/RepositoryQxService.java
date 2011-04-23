package zws.qx.service;

/*
 * DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Jun 7, 2007 9:35:20 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.repository.Repository;
import zws.service.repository.RepositorySvc;
//impoer zws.util.Logwriter;

import java.util.Collection;

/**
 * The Class RepositoryQxService.
 *
 * @author ptoleti
 */
public class RepositoryQxService implements Qx {

  /**
   * The main method.
   *
   * @param dataInstruction the data instruction
   * @param ctx the ctx
   *
   * @return the qx XML
   */
  public QxXML executeQx(QxContext ctx, QxXML dataInstruction) {
    QxXML result = null;
    try {

      QxInstruction qxInst = dataInstruction.toQxProgram();
      {}//Logwriter.printOnConsole(" qxInst " + qxInst);
      Collection repositoryNames = null;
      Repository repository = null;
      // origin will be in XML string, prepare Origin object
      String repositoryName = qxInst.get("repositoryName");
      if ("getList".equals(repositoryName)) {
        StringBuffer repNames = new StringBuffer();
        repositoryNames = RepositorySvc.getPrototypeNames();
        {}//Logwriter.printOnConsole("---------names-------");
        //zws.util.PrintUtil.print(repositoryNames);
        // convert collection to comma separated values
        if (null != repositoryNames) {
          Object[] repList = repositoryNames.toArray();
          for (int idx = 0; idx < repList.length; idx++) {
            repNames.append((String) repList[idx]);
            if (idx < repList.length - 1) {
              repNames.append(",");
            }
          }
        }
        // result = new QxXML("Agile, ilink3, TC-10, Test1, Test2");
        result = new QxXML("<repositories repList=\"" + repNames.toString() + "\"/>");
        {}//Logwriter.printOnConsole(result);
      } else {
        {}//Logwriter.printOnConsole(" repositoryName " + repositoryName);
        repository = RepositorySvc.find(repositoryName);
        // convert the repository to XML
        // result = "ilink";
        result = repository.toXML();
        {}//Logwriter.printOnConsole("");
        {}//Logwriter.printOnConsole(result);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

}
