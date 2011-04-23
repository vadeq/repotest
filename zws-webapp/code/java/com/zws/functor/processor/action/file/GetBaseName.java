package com.zws.functor.processor.action.file;

import com.zws.functor.processor.action.Action;
import com.zws.util.FileNameUtil;

public class GetBaseName extends Action {
  public void execute() {
    setResult(FileNameUtil.getBaseName(getDocument().getName()));
  }
}
