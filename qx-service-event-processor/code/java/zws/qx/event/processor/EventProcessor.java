/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Oct 23, 2007 10:02:35 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.qx.event.processor;


import zws.qx.QxContext;
import zws.qx.event.handler.QxHandler;
import zws.qx.event.registry.HandlerRegistry;
//import zws.util.{}//Logwriter;
import zws.util.Pair;
import zws.util.RoutedEventBase;
import java.util.Collection;
import java.util.Iterator;
public class EventProcessor {
  
  public static final void processEvent(final QxContext ctx, final RoutedEventBase event) throws Exception {
    QxHandler handler = null;
      //registerHandlers();
      {}//Logwriter.printOnConsole("event.getEventType() --->  " + event.getEventType());
      Collection handlers = registry.getHandlers(ctx, event);
      if(null== handlers) {
        {}//Logwriter.printOnConsole("<No Handlers are defined for the event '" + event.toString() + "'/>");
        return;
      } else {
      Iterator hItr = handlers.iterator();
      while (hItr.hasNext()) {
        handler = (QxHandler) hItr.next();
        {}//Logwriter.printOnConsole("handler.... " + handler.toString());
        if (handler.handles(ctx, event)) {
            handler.handle(ctx, event);
            {}//Logwriter.printOnConsole("event");
            {}//Logwriter.printOnConsole(event.toString());
        }
      }
      }
  }
  
  public static void register(QxHandler handler) {
    registry.register(handler);
  }

  public static void register(Pair eventTypeHandlerPair) throws Exception {
    String eventType = eventTypeHandlerPair.getObject0().toString();
    String handlerFQCN = eventTypeHandlerPair.getObject1().toString();
    Class handlerClass = Class.forName(handlerFQCN);
    QxHandler handler = (QxHandler) handlerClass.newInstance();
    handler.setEventType(eventType);
    register(handler);
  }

 /* public static void registerHandlers() {
    registerHandler(new AgileCheckinHandler());
    registerHandler(new AgileRenameHandler());
    registerHandler(new Intralink8CheckinHandler());
    registerHandler(new Intralink8RenameHandler());
    registerHandler(new TC10CheckinHandler());
    registerHandler(new TC10RenameHandler());
  }*/
  private static HandlerRegistry registry = HandlerRegistry.getRegistry();
}
