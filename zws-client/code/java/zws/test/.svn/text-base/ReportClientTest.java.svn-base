package zws.test;

import zws.origin.Origin;
import zws.report.Report;
import zws.service.report.EJBLocator;
import zws.service.report.ReportServiceRemote;

import java.util.StringTokenizer;

public class ReportClientTest {
  
  public static void main(String args[]) {
    try{
      ReportServiceRemote rs = EJBLocator.findService("DesignState");
//      Report disk = rs.getReport("disk");
//      disk.setCriteria("[ Name=* ]");
//      sql.setCriteria("[ ID=*in & Folder=*ne ]");
//      sql.setCriteria("[ID=*in]");
//      report.setCriteria(" [ name = 100399.drw | name = 100388.drw] [Ver=0 | version = 2 ] ");
//      report.setCriteria(" [ name = 100399.drw | name = 100388.drw] [version=0 | version = 2 ] ");
//      report.setCriteria("[name = 100399.drw]");
//      report.setCriteria("[Rev = 2]");
//      disk.generate();
//      {} //System.out.println(disk.toString());

//      intralink.setCriteria("[ Name=h175759* & Revision=1 ] | [ Name=an123128* ]");

        Report intralink0 = rs.getReport("intralink");
        intralink0.setCriteria("[ Name=h175759* ]");
//        intralink0.setCriteria("[ Name=b1* ]");
        intralink0.generate();
        {} //System.out.println(intralink0.toString());

        Report intralink1 = rs.getReport("intralink1");
        intralink1.setCriteria("[ Name=m139733* ]");
//        intralink1.setCriteria("[ Name=b1* ]");
        intralink1.generate();
        {} //System.out.println(intralink1.toString());

        Report intralinkp = rs.getReport("intralink-parallel");
        intralinkp.setCriteria("[ Name=h175759* ] | [ Name=m139733* ]");
//        intralinkp.setCriteria("[ Name=b1* ] ");
        intralinkp.generate();
        {} //System.out.println(intralinkp.toString());
      /*
      Report sql = rs.getReport("sql");
      sql.setCriteria("[ ID=*in & blabla=slkdjf ] | [ Folder=*ne ]");
      sql.generate();
      {} //System.out.println(sql.toString());
*/
//      Report drp = rs.getReport("drp");
//      drp.setCriteria("[ PART_NUMBER=400R ]");
//      drp.generate();
//      {} //System.out.println(drp.toString());
/*      
      Collection results = report.getResults();
      Iterator i = results.iterator();
      while( i.hasNext() ) {
        Metadata data = (Metadata) i.next();
        {} //System.out.println(data.getOrigin().getValue());
      }
*/
/*
      Collection c = new Vector();
      c.addAll(disk.getResults());
      c.addAll(intralink.getResults());
      Iterator iterator = c.iterator();
      InputStream binary=null;
      FileOutputStream outputStream=null;
      Metadata metadata = null;
      while( iterator.hasNext() ) {
        metadata = (Metadata)iterator.next();
        binary = null;
        try { 
          binary = Downlader.download(metadata.getOrigin()); 
         String output = "c:\\zws\\data\\temp\\"+getFileName(metadata.getOrigin());
          outputStream = new FileOutputStream(output);
          int b=binary.read();
          while(b>-1) {
            outputStream.write(b);
            b = binary.read();
          }
        }
        catch (Exception e) { {} //System.out.println(e.getMessage()); }
        finally { outputStream.close(); if (null!=binary) binary.close(); }
      }
 */
    }
    catch( Exception e ) { e.printStackTrace(); }
    {} //System.out.println("complete");
  }
  
  public static String getFileName(Origin o) {
    StringTokenizer tokens = new StringTokenizer(o.getUniqueID(), "|");
    if (tokens.hasMoreTokens()) tokens.nextToken();
    return tokens.nextToken();
  }
}

