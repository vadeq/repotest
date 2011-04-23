package zws.qx;

import zws.qx.xml.QxXML;
//impoer zws.util.Logwriter;
public class QxService implements Qx {

  
  
  public QxXML executeQx(QxContext ctx, QxXML dataInstruction){
    //check if request is comming from local machine.
    //if local -> executeLocalQx as below
    //if remote ->  executeRemoteQx as below
    return null;
  }
  
 public QxXML executeRemoteQx(QxContext ctx, QxXML dataInstruction){
   //create QxAxis2WebClient
   //client.executeQx();
   return null;
 }
 
  public QxXML executeLocalQx(QxContext ctx, QxXML dataInstruction){
    QxXML response = null;
    try {

      {}//Logwriter.printOnConsole("context " + ctx.toString());
      {}//Logwriter.printOnConsole(" data instruction "+dataInstruction);

      String fqcn = ctx.lookupJavaFQCN();

      Class c =  Class.forName(fqcn);

      {}//Logwriter.printOnConsole("creating instance of " + fqcn);
      Qx qx = (Qx) c.newInstance();
      {}//Logwriter.printOnConsole("invoking QxService");
      response = qx.executeQx(ctx, dataInstruction);

    } catch(RuntimeException ex ){
      {}//Logwriter.printOnConsole("Runtime Exception! "+ex.getMessage());
      ex.printStackTrace();
      response = new QxXML("<exception message='"+ex.getMessage()+"' />");

    } catch(Exception ex ){
      {}//Logwriter.printOnConsole("Exception "+ex.getMessage());
      ex.printStackTrace();
      response = new QxXML("<exception message='"+ex.getMessage()+"' />");

    }
    {}//Logwriter.printOnConsole(response);
    return response;
  }
}
