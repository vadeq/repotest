package com.zws.functor.processor.action.logic;

import com.zws.functor.processor.action.Action;

public abstract class Logical extends Action {

  public final Boolean getAnswer() { return (Boolean)getResult(); }
  public final void setAnswer(boolean b){
    if (invert) setResult(new Boolean (!b));
    else setResult(new Boolean (b));
  }

  public final void invert() { invert=true; }
  public final void setInvert(boolean b) { invert=true; }
  public final boolean getInvert() { return invert; }
  boolean invert=false;
}
