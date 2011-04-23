package zws.handler;
import zws.util.RoutedEventBase;

public class TestPrintHandler extends HandlerBase{
  public Class getEventClass() { return null; }
   
  public String getEventType() { return "test"; }
  
  public boolean handles(RoutedEventBase event) {
   if(rightType(event) && validEvent(event)){
    return true;
   }else{
     return false;
   }
 }  
   
  private boolean rightType(RoutedEventBase event){ 
    if("event.print".equals(event.getName())){
     {} //System.out.println("Decided to Process.");
      return true;
    }else{
      {} //System.out.println("Decided NOT to Process.");
      return false;
    }
  }  
  private boolean validEvent(RoutedEventBase event){
    return true;
  }
  
  public void handle(RoutedEventBase event){
    try{
      {} //System.out.println((String)event.get(this.TEXT));
    }catch( Exception e ) { e.printStackTrace(); }

  }  
  public static String TEXT = "TEXT";
}

