/*
 * Created on Oct 22, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package zws.action.transformer;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import zws.action.ActionBase;
import zws.data.Metadata;
import zws.datasource.Datasource;

public abstract class Transformer extends ActionBase{
	
	public String getTargetName() { return targetName; }
	public void setTargetName(String s) { targetName = s; }
	public String getDataSourceName() { return dataSourceName; }
	public void setDataSourceName(String s) { dataSourceName = s; }
	
	//public void setSource(Metadata d) { source = d; }
	//public Metadata getSource() { return source; }
	public Metadata getTarget() { return target; }
	public void setTarget(Metadata s) { target = s; }
	public Datasource getDataSource() { return dataSource; }
	public void setDataSource(Datasource s) { dataSource = s; }
	
	
    private String targetName = null;
	private Metadata target = null;
	private Metadata source = null;
	
	private String dataSourceName = null;
	private transient Datasource dataSource = null;
}
