/*
 * Created on Oct 22, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor.processor.action.transformer;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import com.zws.datasource.DataSource;
import com.zws.domo.document.Document;
import com.zws.functor.processor.action.Action;

public abstract class Transformer extends Action{
	
	public String getTargetName() { return targetName; }
	public void setTargetName(String s) { targetName = s; }
	public String getDataSourceName() { return dataSourceName; }
	public void setDataSourceName(String s) { dataSourceName = s; }
	
	public void setSource(Document d) { source = d; }
	public Document getSource() { return source; }
	public Document getTarget() { return target; }
	public void setTarget(Document s) { target = s; }
	public DataSource getDataSource() { return dataSource; }
	public void setDataSource(DataSource s) { dataSource = s; }
	
	
    private String targetName = null;
	private Document target = null;
	private Document source = null;
	
	private String dataSourceName = null;
	private DataSource dataSource = null;
}
