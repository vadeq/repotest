package com.zws.functor.processor.action;

import com.zws.domo.document.Document;
import com.zws.exception.EntryNotFound;
import com.zws.functor.Functor;
import com.zws.functor.Getter;
import com.zws.functor.processor.action.log.ActionLog;

public abstract class Action extends Functor  {
  public Document getDocument() { return actionLog.getDocument(); }
  public void setDocument(Document doc) { actionLog.setDocument(doc); }
  public ActionLog getActionLog() { return actionLog; }
  public void setActionLog(ActionLog l) { actionLog=l; }

  public long getExitCode() { return exitCode; }
  public void setExitCode(long l) { exitCode=l; }

  private ActionLog actionLog = null;
  private long exitCode;

  public static String workspace = "workspace";
  public static String componentName = "component";
  public static String drawing = "drawing"; 
  public static String pdfFileName = "pdf-filename";
  public static String postscriptFileName = "postscript-filename";
  
  protected String getProperty(String propertyName){
  	Getter propGetter = new Getter();
	String property = null;
  	
	propGetter.setObject(this);
	propGetter.setPropertyName(propertyName);
	try{
		propGetter.execute();
		property = (String)propGetter.getResult();
		if(property == null)
			throw new EntryNotFound(propertyName);
	}catch(EntryNotFound enf){
		propGetter.setPropertyName(propertyName + "Metadata");
		try{
			propGetter.execute();
			String metadata = (String)propGetter.getResult();
			property = getDocument().get(metadata);
		}catch(Exception e){
			e.printStackTrace();
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return property;	
  }
}

/*aspect A{
	
	pointcut testPC(): execution(String Action.getProperty(String));
	//pointcut gooPC(): execution(void Test.goo());
	//pointcut printPC(): call(void java.io.PrintStream.println(String));

	before(): cflow(testPC()) {
		{} //System.out.println("ZZZZZ Action getProperty Called ZZZZZZ");
	}
}*/



