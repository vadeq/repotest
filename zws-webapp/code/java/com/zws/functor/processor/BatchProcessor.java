package com.zws.functor.processor;

import com.zws.application.Constants;
import com.zws.domo.document.Document;
import com.zws.functor.processor.action.Action;
import com.zws.functor.processor.action.log.ActionLog;
import com.zws.functor.report.DataReport;
import com.zws.functor.util.FunctorIterator;
import com.zws.service.config.DataReportService;
import com.zws.util.stream.StreamableCollection;

import java.util.Collection;
import java.util.Iterator;

public class BatchProcessor extends Processor {
  public void processActions(){
    StreamableCollection searchResults = new StreamableCollection();
    report.setResultBuffer(searchResults);
    report.setStreamingEnabled(false);
    report.execute();
    setResult(report.getResults());
    if(null==getResult()) return;
    if (getProcessDeep()) performActionsDeep();
    else performActionsWide();
    report.getResults().clear();
  }

  private void performActionsDeep() {
    Document doc;
    Action action;
    FunctorIterator a;
    Iterator i;
    ActionLog actionLog;
    i = ((Collection)getResult()).iterator();
    while (i.hasNext()){
      doc = (Document)i.next();
      a = getActions().copyIterator();
      actionLog = new ActionLog();
      actionLog.setDocument(doc);
      boolean cancelActions=false;
      while (a.hasNext() && !cancelActions) {
        action = (Action)a.copyNext();
        action.setActionLog(actionLog);
        try {
          action.execute();
          actionLog=action.getActionLog();
          if (Constants.PROCESS_TIMED_OUT==action.getExitCode()){
            {} //System.out.println("***Action timed out on document " +                               action.getDocument().getName() + ":" +                              action.getClass().getName());
            if (getHaltOnTimeout())
              return;
            if (!getContinueOnTimeout())
              break;
          }
        } catch (Exception e) {e.printStackTrace(); actionLog.log("Exception["+e.getClass().getName()+"]: " + e.getMessage()); cancelActions=true;}
      }
      getActionLog().addAll(actionLog.getLog());
    }
  }

  private void performActionsWide() {
    Document doc;
    Action action;
    FunctorIterator a;
    ActionLog actionLog;
    Iterator i;
    a = getActions().copyIterator();
    while (a.hasNext()){
      action = (Action)a.copyNext();
      i = ((Collection)getResult()).iterator();
      while (i.hasNext()) {
        doc = (Document)i.next();
        actionLog = new ActionLog();
        actionLog.setDocument(doc);
        try {
          action.execute();
          actionLog = action.getActionLog();
          if (Constants.PROCESS_TIMED_OUT == action.getExitCode()) {
            {} //System.out.println("***Action timed out on document " +                               action.getDocument().getName() + ":" +                               action.getClass().getName());
            if (getHaltOnTimeout())
              return;
            if (!getContinueOnTimeout())
              break;
          }
        } catch (Exception e) { e.printStackTrace(); actionLog.log("Exception: " + e.getMessage()); getActionLog().addAll(actionLog.getLog()); }
      }
    }
  }

  public DataReport getReport() { return report; }
  public void setReport(DataReport r) { report=r; }
  public void setReport(String s) {
    try { report = (DataReport)DataReportService.find(s).copy(); }
    catch (Exception e) { e.printStackTrace(); }
  }

  public boolean getProcessDeep() { return processDeep; }
  public void setProcessDeep(boolean b) { processDeep = b; }
  private DataReport report;
  private boolean processDeep = true;  
}
