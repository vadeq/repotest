package zws.repository.teamcenter.util;

import zws.op.OpBase;
import zws.repository.teamcenter.TC10Constants;
import zws.util.StringMapper;

public class ZWS2TC10AttributeMapper extends OpBase implements StringMapper {

	  public String getValue() { return value; }
	  public void setValue(String s) { value = s; }
	  
	  public String getMappedValue() { return (String)getResult(); }
	  
	  public void execute() 
	  {		  
	    String mappedValue = null; 
	    if (null==value) return;
	    mappedValue = (String) TC10Constants.fromZwsToTC10Map.get(getValue());
	    setResult(mappedValue);
	  }
	  private String value=null;
}
