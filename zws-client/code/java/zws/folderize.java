/*
 * folderize.java
 *
 * Created on May 17, 2004, 7:31 PM
 */

package zws;

/**
 *
 * @author  thakarbi
 */
import zws.xml.xslt.Stylizer;
public class folderize {
    
    /** Creates a new instance of folderize */
    public folderize() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        Stylizer s = new Stylizer();
        s.addProcessingInstruction("D:\\zws\\DesignState\\xslt\\treeview\\folderize.xslt");
        String xmlPath = "D:\\zws-dev-dojo\\code\\java\\zws-web\\defaultroot\\intralink\\baseline\\folder.xml";
        {} //System.out.println(s.styleXML(xmlPath).toString());
        }
        catch (Exception e) {e.printStackTrace();}
    }
    
}
