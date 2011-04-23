/*
 * Created on Oct 16, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package zws.action;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DummyAction extends ActionBase {


	public void execute() throws Exception {
		{} //System.out.println("ACTION : " + getSilliness());

	}
	
	private String silliness = "very dumb";
	
	public String getSilliness() { return silliness; }
	public void setSilliness(String s) { silliness = s; }
	
}
