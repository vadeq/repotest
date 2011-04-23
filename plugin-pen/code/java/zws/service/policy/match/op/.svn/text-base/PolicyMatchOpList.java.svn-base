package zws.service.policy.match.op;

/*
 * DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 26, 2007 12:03:51 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */


//impoer zws.util.Logwriter;
import java.util.Iterator;

/**
 * The Class PolicyMatchOpList.
 * @author ptoleti
 */
public class PolicyMatchOpList extends PolicyMatchOpBase {

  /**
   * execute.
   *
   * @throws Exception
   * exception
   * @see zws.op.OpBase#execute()
   */
  public void execute() throws Exception {
    PolicyMatchOp op = null;
    boolean opResult = true;
    String decision = NO_DECISION;
    Iterator itr = getOps().iterator();
    while (itr.hasNext()) {
      op = (PolicyMatchOp) itr.next();
      op.setQxCtx(getQxCtx());
      op.setOrigin(getOrigin());
      op.setAuthentication(getAuthentication());
      op.setContext(getContext());
      op.execute();
      // AND condition for all the OPs
      opResult = opResult && new Boolean((String) op.getResult()).booleanValue();
    }
    if(opResult && !isIgnored) decision = INCLUDE_POLICY;
    if (opResult && isIgnored) decision = EXCLUDE_POLICY;

    setResult(String.valueOf(decision));
    {}//Logwriter.printOnConsole("PolicyMatchOpList isIgnored " + isIgnored);
    {}//Logwriter.printOnConsole("PolicyMatchOpList opResult  " + opResult);
    {}//Logwriter.printOnConsole("PolicyMatchOpList decision  " + decision);
  }

  /**
   * @return the isIgnored
   */
  public boolean getIsIgnored() {
    return isIgnored;
  }
  /**
   * @param bIsIgnored the isIgnored to set
   */
  public void setIsIgnored(boolean bIsIgnored) {
    this.isIgnored = bIsIgnored;
  }

  /** if isIgnored is true and op resulte is true don't select the policy.   */
  private boolean isIgnored = false;
}
