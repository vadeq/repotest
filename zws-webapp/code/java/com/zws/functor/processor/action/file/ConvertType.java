package com.zws.functor.processor.action.file;

import com.zws.functor.processor.action.Action;
import com.zws.util.FileNameUtil;

public class ConvertType extends Action {
  public void execute() {
    setResult(FileNameUtil.convertType(getDocument().getName(), getType()));
  }

  public String getType() { return type; }
  public void setType(String s) { type=s; }

  private String type=null;
}
