package zws.recorder.util;

import zws.Server;
import zws.exception.zwsException;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.service.recorder.qx.RecorderClient;
import zws.service.recorder.qx.RecorderService;
//impoer zws.util.Logwriter;

import java.util.Collection;

public class RecorderUtil {

  public static QxContext startNewProcess(String nameSpace, String processName, String description) throws Exception {
    return startNewProcess(new QxContext(), nameSpace, processName, description);
  }
  public static QxContext startNewProcess(QxContext ctx, String nameSpace, String processName, String description) throws Exception {
    return startNewProcess(ctx, nameSpace, processName, description, null);
  }
  
  public static QxContext startNewProcess(QxContext ctx, String nameSpace, String processName, String description, String notes) throws Exception {
    QxContext tempQtx = new QxContext();
    tempQtx.configureParent(ctx);
    Long processID = getRecorder().recordStartTime(nameSpace, processName,zws.application.Names.STATUS_STARTED, description, notes);
    tempQtx.set(QxContext.PROCESS_ID, String.valueOf(processID));
    tempQtx.set(QxContext.PROCESS_NAME, processName);
    tempQtx.set(QxContext.PROCESS_NAME_SPACE, nameSpace);
    return tempQtx;
  }
  public static QxContext startSubProcess(QxContext ctx, String processName, String description) throws Exception {
    return startSubProcess(ctx, processName, description, null);
  }
  public static QxContext startSubProcess(QxContext ctx, String processName, String description, String notes) throws Exception {
    if (ctx.get(QxContext.PROCESS_ID) == null) throw new zwsException("Parent Context is not set");
    String nameSpace = ctx.get(QxContext.PROCESS_NAME_SPACE) + "." +ctx.get(QxContext.PROCESS_NAME);
    QxContext tempQtx = new QxContext();
    tempQtx.configureParent(ctx);
    Long parentProcessID = new Long(ctx.get(QxContext.PROCESS_ID));
    Long childProcessID = getRecorder().recordChildStartTime(parentProcessID, nameSpace, processName, zws.application.Names.STATUS_STARTED, description, notes);
    tempQtx.set(QxContext.PROCESS_ID, String.valueOf(childProcessID));
    tempQtx.set(QxContext.PROCESS_NAME, processName);
    tempQtx.set(QxContext.PROCESS_NAME_SPACE, nameSpace);
    return tempQtx;
  }

  public static void setStatus(QxContext ctx, String status) throws Exception {
    try {
      long pID = Long.valueOf(ctx.get(QxContext.PROCESS_ID)).longValue();
      getRecorder().recordStatus(new Long(pID), status);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
  public static void logActivity(QxContext ctx, String activity, String message) throws Exception {
    logActivity(ctx, activity, message, null);
  }
  public static void logActivity(QxContext ctx, String activity, String message, String notes) throws Exception {
    try {
      long pID = Long.valueOf(ctx.get(QxContext.PROCESS_ID)).longValue();
      getRecorder().recordActivity(new Long(pID), Server.getDomainName(), Server.getNode(), activity, message, notes);

    } catch (NumberFormatException ne) {
      throw new Exception("Process ID isnot set for recording service.");
    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception("Error in Recording Activity");
    }
  }


  public static void endRecProcess(QxContext ctx, String status) throws Exception {
    // Record end time
    Long processID = new Long(ctx.get(QxContext.PROCESS_ID));
    getRecorder().recordEndTime(processID, status);
    // Set the parent as null
  }
  public static QxContext createChildContext(QxContext ctx, String childProcessName, String status, String activity, String description) {
    return createChildContext(ctx, childProcessName, status, activity, description, null);
  }
  public static QxContext createChildContext(QxContext ctx, String childProcessName, String status, String activity, String description, String notes) {
    QxContext localCtx = null;
    try {
      localCtx = startSubProcess(ctx, childProcessName, description,notes);
      setStatus(localCtx, status);
      logActivity(localCtx, activity, description);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return localCtx;
}

  public static QxContext createChildContext(QxContext ctx, String childProcessName, String status, String activity, Origin origin) {
    QxContext localCtx = null;
    try {
      localCtx = startSubProcess(ctx, childProcessName, origin.getUniqueIDDisplay());
      setStatus(localCtx, status);
      logActivity(localCtx, activity, origin.getName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return localCtx;
}

  public static void removeChildContext(QxContext localCtx, String status) throws Exception{
    try {
      setStatus(localCtx, status);
      endRecProcess(localCtx, status);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Collection getRecordings(String namespace,String name) throws Exception {
    return getRecorder().getRecordings(namespace, name);
  }
  public static RecorderService getRecorder() {
    return RecorderClient.getClient();
  }
}

