/*
 * Created on Oct 10, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor.processor.action.intralink;


import com.zws.datasource.IntralinkSource;
import com.zws.functor.processor.action.Action;
import com.zws.service.config.DataSourceService;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PromotionFormQuery extends Action {
	public void execute () throws Exception {
		
		IntralinkSource datasource = (IntralinkSource)DataSourceService.find(getDatasourceName());
	
		Exception ex=null;
		com.zws.functor.intralink.PromotionFormQuery action = new com.zws.functor.intralink.PromotionFormQuery();
		
		
		if(intervalDays == null && intervalHours == null)
			action.setDate(getProperty("date"));
		else
			action.setDate(calculateDate(intervalDays, intervalHours));	
			
		action.setReleaseLevel(getProperty("releaseLevel"));
		
		action.setUsername(datasource.getUsername());
		action.setPassword(datasource.getPassword());
		action.setEXEToolkitEnv(datasource.getEXEToolkitEnv());
		
		try {
		  action.execute();
		}
		catch(Exception e) {
		  getActionLog().log("Failed: promoting " + ex);
		  ex = e;
		}
		if (null!=ex) throw ex;
	  }
	  
	  private String calculateDate(String iDays, String iHours){
	  	
	  	int iIDays=0, iIHours=0;
	  	if(iDays != null && iDays.trim().length() > 0)
	  	 	iIDays = Integer.parseInt(iDays);
		if(iHours != null && iHours.trim().length() > 0)
			iIHours = Integer.parseInt(iHours);
		String retdate = null;
	  	GregorianCalendar cal = new GregorianCalendar();
	  		
	  	//int extraDays = iIHours/24;
	  	//iIHours = iIHours%24;	
	  		
		cal.add(Calendar.DAY_OF_MONTH, -iIDays);
		cal.add(Calendar. HOUR_OF_DAY, -iIHours);
	  	//10.06.2003.11:32

	  	retdate = (cal.get(Calendar.MONTH)+ 1) + "." + cal.get(Calendar.DAY_OF_MONTH) + "." + 
	  	                 (cal.get(Calendar.YEAR)) + "." +
	  	                 cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
	  	
	  	return retdate;
	  }

		  
	  public String getDate() { return date; }
	  public void setDate(String s) { date = s; }
	  public String getIntervalDays() { return intervalDays; }
	  public void setIntervalDays(String s) { intervalDays = s; }
	  public String getIntervalHours() { return intervalHours; }
	  public void setIntervalHours(String s) { intervalHours = s; }
	  public String getReleaseLevel() { return releaseLevel; }
	  public void setReleaseLevel(String s) { releaseLevel = s; }
	  
	  public String getDatasourceName() { return datasourceName; }
	  public void setDatasourceName(String s) { datasourceName=s; }

	  private String name=null;
	  private String date=null;
	  private String intervalDays=null;
	  private String intervalHours=null;
	  private String releaseLevel=null;

	  
	  private String datasourceName="";
	  
}









