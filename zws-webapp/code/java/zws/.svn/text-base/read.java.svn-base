/*
 * read.java
 *
 * Created on May 17, 2004, 5:39 PM
 */

package zws;

/**
 *
 * @author  thakarbi
 */
import java.io.BufferedReader;
import java.io.FileReader;
public class read {
    
    /** Creates a new instance of read */
    public read() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
try 
{     
   String line = null; 
   BufferedReader br = new BufferedReader(new FileReader("\\\\tvs-enforcer\\ptc_config\\zws\\ecn_instructions\\promote_trigger_ecn.txt"));
         
   while ((line = br.readLine()) != null) { 
         {} //System.out.println("<"+line+">");
   } 
  
   br.close();                 
   br = null; 
         
} catch (java.io.FileNotFoundException ex) { 
   {} //System.out.println("File '" + args[0] + "' does not exist. ");         
} catch (java.io.IOException ex) { 
   ex.printStackTrace(); 
} 
        
        
        
    }
    
}
