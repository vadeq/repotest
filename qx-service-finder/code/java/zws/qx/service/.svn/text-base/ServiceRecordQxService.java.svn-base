package zws.qx.service;

import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.service.finder.qx.ServiceRecordSvc;
//impoer zws.util.{}//Logwriter;


public class ServiceRecordQxService implements Qx {
  public QxXML executeQx(QxContext ctx, QxXML dataInstruction) {
    QxXML result = null;
    try {
      QxInstruction qxInst = dataInstruction.toQxProgram();
      String serviceName = qxInst.get("service");
      ServiceRecord serviceRecord = ServiceRecordSvc.find(serviceName);
      result = serviceRecord.toXML();
    } catch (Exception e) {
      e.printStackTrace();
    }
    {}//Logwriter.printOnConsole(" result in ServiceRecordQxService.executeQx"+ result);
    return result;
  }
}
