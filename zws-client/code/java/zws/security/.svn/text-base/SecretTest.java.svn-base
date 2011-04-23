package zws.security;
/*
 DesignState - Design Compression Technology
 @author: HP USER
 @version: 1.0
 Created on Apr 17, 2007 10:22:53 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class SecretTest {
  
  private static String prime512 = "10053776548662673179511759928845035994918254207026704727561457740539680980592162075014614679602073335037570753987727141658551858107297125544804362622016647";
  private static String base512 = "3633262221140915020982594780547472202048738122136571555432130525604794582430717778843610356665997813030045414179065116359415032719572377842202556707714818";
  private static int randomExp512 = 432;
  
  private static String prime1024 = "100213114278716568824123615835936278359210139657578045359023414779715720051987303300168495084039573811277882063779533774205945083198850524804681598646100262913769312439789617411750210270494160685905212842715350211783960825044038057404395077139556916318790839695923831973660202525094672754456307635210327376341";
  private static String base1024 = "89314146013974073167928821438611631168001156750237599217197501120472453802591601513747746578085299314484223515632357487034781761736966972282150652313921292665119493870062809160180904602469679234662338501036872199547260128476367082809500668920828738669771596563891059005760625808597429488380245544782573401420";
  private static int randomExp1024 = 1020;
  
  private static String clearTextMessage = "This is the response: <response mesg='successful' type='Encrypt'/>";
  
  
  private static Map secretMap = new HashMap();
  
  public static byte[] exchangeKey(byte[] pubKey) {
    Secret serverSecret = (Secret) secretMap.get("Server");
    serverSecret.configure(Secret.constructPublicKey(pubKey));
    return serverSecret.getLocalPublicKey().getEncoded();
  }
  
  public static String invokeServer(String encStr){
    Secret serverSecret = (Secret) secretMap.get("Server");
    return serverSecret.decrypt(encStr);    
  }
  
  public static void invokeServerDecryptFile(String inFile, String outFile){
    Secret serverSecret = (Secret) secretMap.get("Server");
    try {
      serverSecret.decryptFile(new FileInputStream(inFile), new FileOutputStream(outFile));
    } catch(Exception ex){
      ex.printStackTrace();
    }
  }
  
  
  public static void main(String[] args) throws Exception{
    
    Secret.generateDHParamSet("DH", 1024);
    
    Secret clientSecret = new Secret();
    
    Secret serverSecret = new Secret();
    
    secretMap.put("Server", serverSecret);
    secretMap.put("Client", clientSecret);
    
    byte[] serverPublicKey = exchangeKey(clientSecret.getLocalPublicKey().getEncoded());    
    clientSecret.configure(Secret.constructPublicKey(serverPublicKey));
    
    String encryptedStr = clientSecret.encrypt("<response message=\"success\"/>" + Names.NEW_LINE);
    
    
    String clearFile = "C:/clearFile.txt";
    String encFile = "C:/encFile.txt";
    String decFile = "C:/decFile.txt";
    
    clientSecret.encryptFile(new FileInputStream(clearFile), new FileOutputStream(encFile));
    
    invokeServerDecryptFile("C:/encFile.txt", "C:/decFile.txt");
    
    //Now testing images.
    clearFile = "C:/ilink-zws-agile.jpg";
    encFile = "C:/encImage.jpg";
    decFile = "C:/decImage.jpg";
    clientSecret.encryptFile(new FileInputStream(clearFile), new FileOutputStream(encFile));
    invokeServerDecryptFile(encFile, decFile);
    
    {} //System.out.println("Enc String: "+ encryptedStr);
    {} //System.out.println(invokeServer(encryptedStr));
    
   // Secret secret1 = Secret.getInstance();
   //secret1.configure("512", prime512, base512, randomExp512);
   // secret1.configure512("512");
   
   //test("512", secret1, 10000, 1);
   //test("512", secret1, 10000, 50);
   //test("512", secret1, 10000, 100);
   //test("512", secret1, 10000, 500);
  // test("512", secret1, 10000, 750);
  //  {} //System.out.println(new Random().nextInt(1024));
  //  {} //System.out.println(new Random().nextInt(1024));
    
    
   // Secret secret1 = Secret.getInstance();
    
   //  secret1.configure1024("1024");
     
   //  test("1024", secret1, 1, 1);
   //  {} //System.out.println(secret1.encrypt("1024", "mist"));
   //secret1.encrypt("1024", "<string_context password=\"HELLO\" queue-name=\"Test2 Queue\" soap-hostname=\"vm-ilink-1\" soap-service-operation=\"executeQx\" java-services-package=\"zws.qx.queue\" soap-service-name=\"QxWebService\" soap-services-path=\"ZeroWait-State/services\" java-service-classname=\"QxQueueService\" />");
   
    
    
   
   // test("1024", secret2, 10000, 50);
   // test("1024", secret2, 10000, 100);
   // test("1024", secret2, 10000, 500);
    //test("1024", secret2, 10000, 750);
    
  }
  
  private static void test(String id, Secret secret, int stringLen, int loopcount) {
    
    String encString[] = new String[loopcount];
   
    
    
    String clearText =  createString(stringLen);
      //"<string_context password=\"HELLO\" queue-name=\"Test2 Queue\" soap-hostname=\"vm-ilink-1\" soap-service-operation=\"executeQx\" java-services-package=\"zws.qx.queue\" soap-service-name=\"QxWebService\" soap-services-path=\"ZeroWait-State/services\" java-service-classname=\"QxQueueService\" />";
  //  {} //System.out.println("Created String: "+clearText.length());
  //  {} //System.out.println("----------------");
    long start = System.currentTimeMillis();
   // {} //System.out.println(start);
    
    for( int r=0; r< loopcount; r++) {
      //encString[r] = secret.encrypt(id, clearText);
    }
    
    long end = System.currentTimeMillis();
  //  {} //System.out.println(end);
     
  //  {} //System.out.println("Time taken for encrypting is: "+ (end-start));
    
    
    long startDe = System.currentTimeMillis();
   // {} //System.out.println(startDe);
   Secret secret4 = new Secret(); 
    for( int r=0; r< loopcount; r++) 
      secret4.decrypt( encString[r]);
    
    long endDe = System.currentTimeMillis();
  //  {} //System.out.println(endDe);
    
  //  {} //System.out.println("Time taken for decrypting is: "+ (endDe-startDe));
    
    {} //System.out.println(id+", "+stringLen+ ", "+loopcount+", "+ (end-start)+ ", "+(endDe-startDe));
    //s =create string
    //print s 
    //start = now
    //loop
    // encript
    //end = now
    // encript time = end-start/1000
    //start = now
    //loop
    //decript
    //end = now
    //decript tim e= end=start/1000
    //print: configCall, stringlen, loops, encript time, decript time
  }
  
  private static String createString(int len) {
    StringBuffer buff = new StringBuffer();
    for (int i =0; i < len; i++) {
      buff.append("<string_context password=\"HELLO\" queue-name=\"Test2 Queue\" soap-hostname=\"vm-ilink-1\" soap-service-operation=\"executeQx\" java-services-package=\"zws.qx.queue\" soap-service-name=\"QxWebService\" soap-services-path=\"ZeroWait-State/services\" java-service-classname=\"QxQueueService\" />");
    }
    {} //System.out.println(buff.toString());
    return buff.toString();
  }
   
}
