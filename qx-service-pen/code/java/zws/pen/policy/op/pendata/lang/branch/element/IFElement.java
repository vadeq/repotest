/* DesignState - Design Compression Technology
 * @author: ptoleti, arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.branch.element;

import zws.exception.InvalidConfiguration;
import zws.pen.policy.op.pendata.PENDataOp;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;

public class IFElement extends PENDataOpBase {

  public void execute() throws Exception {
    boolean condition = evaluateIF();
    if (isInverted()) condition = !condition;
    if (condition) runThenOps();
    else runElseOps();
  }

  protected boolean evaluateIF() throws Exception {
    if (null!=getElementStatusName()) return evaluateElementStatus();
    else if (null!=getCondition()) return evaluateCondition();
    else throw new InvalidConfiguration("No Status or condition has been defined!");
  }
  
  protected boolean evaluateElementStatus() throws Exception {
    String statusValue = getPenData().getStatus(getElementStatusName());
    if(null==statusValue) return false;
    boolean result = new Boolean(statusValue).booleanValue();
    return result;
  }
  
  protected boolean evaluateCondition() throws Exception {
    ConditionOPBase op = getCondition();
    passConfiguration(op);
    op.execute();
    boolean r = op.isTrue().booleanValue();
    return r;
  }

  private void runThenOps() throws Exception {
    PENDataOp execOp = getThenOp();
    runOp(execOp);
  }

  private void runElseOps() throws Exception {
    PENDataOp execOp = getElseOp();
    runOp(execOp);
  }

  private void runOp(PENDataOp execOp) throws Exception {
    if (null == execOp) return;
    passConfiguration(execOp);
    execOp.execute();
  }
  
  public void add(ConditionOPBase op) throws Exception {
    if (null!=condition) {
      String c0 = condition.getClass().getName();
      String c1 = op.getClass().getName();
      throw new InvalidConfiguration("Condition already set to " + c0 + "! attempting to set it again to " + c1);
    }
    condition = op;
  }

  public ConditionOPBase getCondition() {
    return condition;
  }

  public String getElementStatusName() { return elementStatusName;}
  public void setElementGlobalStatusName(String s) { elementStatusName = s;}

  public void setThenOp(PENDataOp op) throws Exception {
    if (null!=thenOp) {
      throw new InvalidConfiguration("Then clause already defined! Can not to set it again!");
    }
    thenOp = op;
  }

  public PENDataOp getThenOp() {
    return thenOp;
  }

  public void setElseOp(PENDataOp op) throws Exception {
    if (null!=elseOp ) {
      throw new InvalidConfiguration("Else clause already defined! Can not to set it again!");
    }
    elseOp = op;
  }

  public PENDataOp getElseOp() throws Exception {
    return elseOp;
  }

  public boolean isInverted() {
    return inverted;
  }

  public void setInverted(boolean b) {
    this.inverted = b;
  }

  private ConditionOPBase condition = null;
  private PENDataOp thenOp = null;
  private PENDataOp elseOp = null;
  private boolean inverted = false;
  
  private String elementStatusName = null;
}
  
