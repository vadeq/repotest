package zws.qx.service;


import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.service.chrysalis.ChrysalisSvc;
//import zws.util.{}//Logwriter;
/**
 * The Class ChrysalisQxService.
 * @author eankutse
 */
public class ChrysalisQxService implements Qx {
 
  /**
   * Webservice for PEN Policy.
   * @param dataInstruction QxXML
   * @param ctx QX context
   * @return QX MQL
   * @see zws.qx.Qx#executeQx(zws.qx.QxContext, zws.qx.xml.QxXML)
   */
  public QxXML executeQx(QxContext ctx, QxXML dataInstruction) {
    
    QxXML result = null;
    try {
      {}//Logwriter.printOnConsole("ChrysalisQxService invoked with dataInstruction :");     
      {}//Logwriter.printOnConsole("" + dataInstruction);
      QxInstruction qxInstr = dataInstruction.toQxProgram();
      String method = qxInstr.getName(); 
       
      if ("translate".equalsIgnoreCase(method)) {
        ChrysalisSvc.translate(ctx, dataInstruction);
        result = new QxXML("<translation/>");
      }
      else if ("requestStatus".equalsIgnoreCase(method)) {
        result = ChrysalisSvc.requestStatus(ctx, dataInstruction);
      }
      else if ("removeStatus".equalsIgnoreCase(method)) {
        result = ChrysalisSvc.removeStatus(ctx, dataInstruction);
      }       
    } catch (Exception e) {
          e.printStackTrace();
          {}//Logwriter.printOnConsole("Exception ");
    }
    return result;
  }
}
