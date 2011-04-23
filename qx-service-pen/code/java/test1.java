import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;

import org.xml.sax.InputSource;

/*
 * DesignState - Design Compression Technology @author:
 * arbind @version: 1.0 Created on Mar 30, 2007 11:23:20
 * AM Copywrite (c) 2007 Zero Wait-State Inc. All rights
 * reserved
 */
/*
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on Jun 7, 2007 9:45:12 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

/**
 * @author ptoleti
 *
 */
public class test1 {

  /**
   * @param args
   */
  public static void main(String[] args) {
    File file = new File("C:/zws-dojo/workspace/qx-service-common/doc/runtime-instruction.xml");
    //new FileInputStream(file)
    StringBuffer contents = new StringBuffer();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      while (reader.ready()) {
        contents.append(reader.readLine());
        contents.append("\n"); // Throw away LF chars, and just replace CR
      }
      {} //System.out.println(contents.toString());
      {} //System.out.println(test1.class);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


  }

}
