package zws.qx.service;

import zws.qx.Qx;
import zws.qx.xml.QxXML;
import zws.qx.QxContext;
/**
 * The Class PingQxService.
 * @author ptoleti
 */
public class TC10QxWebService implements Qx {

  /**
   * Webservice for PEN Policy.
   * @param dataInstruction QxXML
   * @param ctx QX context
   * @return QX MQL
   * @see zws.qx.Qx#executeQx(zws.qx.QxContext, zws.qx.xml.QxXML)
   */
  public QxXML executeQx(QxContext ctx, QxXML dataInstruction) {
    QxXML qxResult = null;
    try {  
      QxInstructionFileClient client = QxInstructionFileClient.materializeClient();
      qxResult = client.executeQx(ctx, dataInstruction);
      {} //System.out.println(qxResult);
    }
    catch (Exception e) {
        e.printStackTrace();
        qxResult = new QxXML("<exception type='"+e.getClass().getName()+"' message='"+e.getMessage()+"'/>");
    }
    return qxResult;
  }
}
