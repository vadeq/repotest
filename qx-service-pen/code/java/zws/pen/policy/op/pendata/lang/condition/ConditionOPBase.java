package zws.pen.policy.op.pendata.lang.condition;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/



import zws.pen.policy.op.pendata.PENDataOpBase;

public abstract class ConditionOPBase extends PENDataOpBase {

  public abstract Boolean evaluateCondition() throws Exception ;

  public void execute() throws Exception {
    Boolean r = evaluateCondition();
    if (isInverted()) r = new Boolean (!r.booleanValue());
    setResult(r);
  }

  public Boolean isTrue() { return (Boolean)getResult(); }

  //public Boolean True() { return  new Boolean(true); }
  //public Boolean False() { return new Boolean(false); }

  public boolean isInverted() {
    return inverted;
  }
  public void setInverted(boolean b) {
    this.inverted = b;
  }

  private boolean inverted = false;
}
