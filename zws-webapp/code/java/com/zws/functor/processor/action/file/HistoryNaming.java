package com.zws.functor.processor.action.file;

import com.zws.application.Constants;
import com.zws.functor.processor.action.Action;
import com.zws.util.FileNameUtil;

import java.util.Vector;

public class HistoryNaming extends Action {

  public void execute() {
    if (operation.equals(ADD_HISTORY)) addHistory();
    else stripHistory();
  }

  private void addHistory() {
    Vector identifiers = new Vector();
    identifiers.add(getDocument().get(revisionMetadata));
    identifiers.add(getDocument().get(versionMetadata));
    setResult(FileNameUtil.addIdentifiers(getDocument().get(getNameMetadata()), identifiers, getDelimiter()));
  }

  private void stripHistory(){
    setResult(FileNameUtil.removeIdentifiers(getDocument().get(getNameMetadata()), getDelimiter()));
  }

  public String getNameMetadata() { return nameMetadata; }
  public void setNameMetadata(String s) { nameMetadata=s; }
  public String getRevisionMetadata() { return revisionMetadata; }
  public void setRevisionMetadata(String s) { revisionMetadata=s; }
  public String getVersionMetadata() { return versionMetadata; }
  public void setVersionMetadata(String s) { versionMetadata=s; }
  public String getDelimiter() { return delimiter; }
  public void setDelimiter(String s) { delimiter=s; }
  public String getOperation() { return operation; }
  public void setOperation(String s) { operation=s; }

  private String operation = ADD_HISTORY;
  private String nameMetadata ="name";
  private String revisionMetadata=null;
  private String versionMetadata=null;
  private String delimiter = Constants.FILENAME_SEPARATOR;

  private static String STRIP_HISTORY ="strip-history";
  private static String ADD_HISTORY = "add-history";

}
