package zws.qx.queue.admin.test;
/**
 * DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 5, 2007 4:29:44 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.application.Names;
import zws.qx.QxContext;
import zws.qx.QxOp;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.time.Time;
import zws.time.TimeBase;
import zws.util.FileUtil;

import java.io.File;
import java.util.Calendar;


/**
 * @author ptoleti
 */
public class TestQueueHandler extends QxOp {

  public final QxXML executeQx(final QxContext ctx, final QxXML dataInstruction) {
    QxXML  finalResult = null;
    try {
    String fileName = "c:\\test.txt";
    QxInstruction testInstr = dataInstruction.toQxProgram();
    String paramValue = testInstr.get("testParam");
    String strWaitTime = testInstr.get("wait-time");
    fileName = testInstr.get("file-name");
    File f = new File(fileName);
    log(Names.NEW_LINE, f);
    if(null != strWaitTime && strWaitTime.length() >0) {
      waitTime = Integer.valueOf(strWaitTime).intValue();
    }
    Time t = new TimeBase(Calendar.getInstance());
    
    log(t.toString()+ ": " + paramValue, f);
    for(int i=0;i<waitTime;i++) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      log(".", f);
    }
    log("!", f);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return finalResult;
  }
  
  private void log (String s, File f) throws Exception {
    System.out.print(s);
    FileUtil.append(s, f, true);
  }
  private int waitTime = 5;
  private static int count = 0;
}
