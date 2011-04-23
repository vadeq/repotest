package zws.handler;
import zws.Emailer;
import zws.email.EmailMessage;
import zws.util.RoutedEventBase;

public class TestHandler extends HandlerBase {
  public Class getEventClass() { return null; }
  public String getEventType() { return "test"; }
  
  public boolean handles(RoutedEventBase event){
   if(rightType(event) && validEvent(event)){
    return true;
   }else{
     return false;
   }
 }

  private boolean rightType(RoutedEventBase event){ 
    if("test".equals(event.getName())){
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
      EmailMessage myMessage = new EmailMessage();  
      myMessage.setBody((String)event.get(BODY));
      myMessage.addRecipient((String)event.get(RECIPIENT));
      myMessage.setFrom((String)event.get(FROM));
      myMessage.setSubject((String)event.get(SUBJECT));
      Emailer.send("DesignState-node-0", myMessage);
      {} //System.out.println("Email Sent");     
    }catch( Exception e ) { e.printStackTrace(); }

  }  
  public static String BODY = "BODY";
  public static String RECIPIENT = "RECIPIENT";
  public static String FROM = "FROM";
  public static String SUBJECT = "SUBJECT";
}

