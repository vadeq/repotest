package zws.qx;
/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 26, 2005, 1:23 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.op.OpBase;
import zws.qx.xml.QxXML;
//impoer zws.util.Logwriter;

public abstract class QxOp extends OpBase implements Qx {
  
  private QxContext qxContext = null;
  private QxXML dataInstruction = null;
  
  private QxXML responseXML = null;
  
  public final void execute() throws Exception
  {
    responseXML = executeQx(getQxContext(), getDataInstruction());
    
    if( responseXML != null ) 
      {}//Logwriter.printOnConsole("Response returned is: "+ responseXML.toString());
    else 
      {}//Logwriter.printOnConsole("Couldnt get response");
  }
  
  public abstract QxXML executeQx(QxContext ctx, QxXML dataInstruction);

  public final QxXML getDataInstruction() {
    return dataInstruction;
  }

  public final void setDataInstruction(QxXML dataInstruction) {
    this.dataInstruction = dataInstruction;
  }

  public final QxContext getQxContext() {
    return qxContext;
  }

  public final void setQxContext(QxContext qxContext) {
    this.qxContext = qxContext;
  }
  
  public final Object getResult() {
    return getDataInstruction();
  }

  public final QxXML getResponseXML() {
    return responseXML;
  }

  public final void setResponseXML(QxXML responseXML) {
    this.responseXML = responseXML;
  }  
  
}
