
import zws.util.StringUtil;

import java.util.HashMap;
import java.util.Iterator;

public class Test
{
   public static void main(String[] params)
   {
    //  Pattern pattern = Pattern.compile(getValidCharset());
     // String marchStr = "!@#$%^&*()_+-={}\\[\\]|\\\\";
      //marchStr += ";"; //:?.<>~";
     /* Matcher matcher = pattern.matcher(marchStr);
      if(matcher.matches())
      {
         System.out.print("matched...");

      }
      else
         System.out.print("No match");*/
     //{} //System.out.println(System.getenv("path"));
     //{} //System.out.println(System.getenv("ant_home"));
     /*Test t = new Test();
     String s = "a,b,c";
     try {
      s = t.prepareSelectAttributes(s);
    } catch (Exception e) {
      e.printStackTrace();
    }
     {} //System.out.println("result " + s);*/
     String s = "testing123";
     String s1=s;
     for(int i=0;i<1000;i++) {
       s1= s1+ "-" + s;
     }
     System.out.println(StringUtil.truncateWithIndicator(s, 3999));
     System.out.println(StringUtil.truncateWithIndicator(s1, 3999));

   }

   public static String getValidCharset() {
     String validCharset = null;   // example [a-zA-Z0-9!@#$%^&*()_+-={}\\[\\]|\\\\][^;:?.<>~]*
     String validChars = "!@#$%^&*()_+-={}[]|\\";
     String invalidChars = ";:?.<>~";

    // String str = "This is an example.";
     char[] array = validChars.toCharArray();

     for(int idx = 0; idx < array.length; idx++) {
       char c = array[idx];
       String unsignedHex = Integer.toHexString(c);
       {} //System.out.println("> "+unsignedHex);
       validCharset = validCharset + "\\x" + unsignedHex + validCharset;
     }

     System.out.print("validCharset... " + validCharset);

     return validCharset;
     }
   
   
}

