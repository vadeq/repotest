package zws.ilink8.server.listener;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.StringTokenizer;
import wt.events.KeyedEvent;
import wt.events.KeyedEventListener;
import wt.services.ManagerException;
import wt.services.ServiceEventListenerAdapter;
import wt.services.StandardManager;
import wt.util.WTException;
import zws.ilink8.server.util.PropertyManager;

public class StandardILService extends StandardManager implements ILService {

	private static final long serialVersionUID = 1L;

	private static final String CLASSNAME = StandardILService.class.getName();

	private KeyedEventListener listener;
	private KeyedEventListener listenermulti;
	public static HashMap oidToNameMap = new HashMap();
	public static HashMap stateMap = new HashMap();	

	public String getConceptualClassname() { return CLASSNAME; }
	public static StandardILService newStandardILService() throws WTException {
		StandardILService instance = new StandardILService();
		instance.initialize();
		return instance;
	} 

	protected void performStartupProcess() throws ManagerException {
		System.out.println("IL Listener Startup process");
		listener = new ILEventListener(getConceptualClassname());
		
		try{
			String ZWS_LISTENED_EVENTS =  PropertyManager.getPropertyValue("ZWS_LISTENED_EVENTS");
			System.out.println("The value of ZWS_LISTENED_EVENTS: " + ZWS_LISTENED_EVENTS);
			StringTokenizer tokens = new StringTokenizer(ZWS_LISTENED_EVENTS, ",");
			while(tokens.hasMoreTokens()){
				String eventString = tokens.nextToken();
			
				String eventClassString = PropertyManager.getPropertyValue("ZWS_EVENT_" + eventString + "_CLASS");
				//String eventTypeString =  PropertyManager.getPropertyValue("ZWS_EVENT_" + eventString + "_EVENT_TYPE");
				
				System.out.println("The value of eventClassString: " + eventClassString);
				//System.out.println("The value of eventTypeString: " + eventTypeString);
				
				Class eventClass = Class.forName(eventClassString);
				Method method = null;
				try {
					method = eventClass.getDeclaredMethod("generateEventKey", new Class[]{String.class});
				}catch(Exception e){
					method = eventClass.getMethod("generateEventKey", new Class[]{Class.class, String.class});
				}
				
				System.out.println("The method is: " + method);
				Field field = eventClass.getField(eventString);
				
				String value = (String) field.get(null);
				System.out.println("The field is: " + value);
				String eventkey = null;
				
				try{
					eventkey = (String) method.invoke(null, new Object[]{value});
				}catch(Exception e){
					eventkey = (String) method.invoke(null, new Object[]{eventClass, value});
				}
				
				getManagerService().addEventListener(listener, eventkey);
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	class ILEventListener extends ServiceEventListenerAdapter {
		public ILEventListener(String manager_name) { super(manager_name); }

		public void notifyVetoableEvent(Object event) throws Exception {
			try{
				if (!(event instanceof KeyedEvent)) { return; }
				KeyedEvent keyedevent = (KeyedEvent) event;
				new EventProcessor().processEvent(keyedevent); 
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
