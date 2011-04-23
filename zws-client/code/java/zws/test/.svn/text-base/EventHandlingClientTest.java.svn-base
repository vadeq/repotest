package zws.test;

import zws.util.RoutedEventBase;
import zws.handler.TestPrintHandler;
import zws.service.event.EJBLocator;
import zws.service.event.EventHandlingServiceRemote;
//import zws.email.EmailMessage;
public class EventHandlingClientTest {
  
  public static void main(String args[]) {
    try{
      /*
    if(args.length > 0){
      if(args[0].equals("email")){
        TestEvent aTestEvent = new TestEvent();
        EventHandlingServiceRemote rs = EJBLocator.findService("DesignState-node-0");
        TestHandler testHandler = new TestHandler();
        rs.register(aTestEvent.getType(), (Handler) testHandler);     
        aTestEvent.set(testHandler.FROM, "grobinson@zerowait-state.com");
        aTestEvent.set(testHandler.RECIPIENT, "athakur@zerowait-state.com");
        aTestEvent.set(testHandler.SUBJECT, "This is a test of the event handler");
        aTestEvent.set(testHandler.BODY, "This is the test Body");
        rs.handle((Event) aTestEvent);
        rs.unRegister(aTestEvent.getType(), (Handler) testHandler);     
      }else{
      }
    }else{
      }
       **/
        EventHandlingServiceRemote rs = EJBLocator.findService("DesignState-node-0");
        RoutedEventBase ev = new RoutedEventBase();
        ev.setName("test.print");
        TestPrintHandler testPrintHandler = new TestPrintHandler();
        rs.register(testPrintHandler);     
        ev.set(testPrintHandler.TEXT, "This is the sample text I want the handler to print");
        //rs.handle(ev);
        rs.unregister(testPrintHandler);

        
        zws.handler.HandlerRegistry reg = new zws.handler.HandlerRegistry();
        RoutedEventBase ev2 = new RoutedEventBase();
        ev2.setName("test.print");
        ev2.set(testPrintHandler.TEXT, "Standalone Registry");
        reg.register(testPrintHandler);
        //reg.fire(ev2);
    }
    catch( Exception e ) { e.printStackTrace(); }
  }
}

