
import zws.application.Configurator;
import zws.repository.Repository;
//import zws.util.{}//Logwriter;

import java.util.Iterator;




// TODO: Auto-generated Javadoc
/**
 * The Class test.
 */
public class test {


  /**
   * The main method.
   * 
   * @param args the args
   */
  public static void main(String[] args) {

    {}//Logwriter.printOnConsole("domain-name="
     //   + zws.application.Properties.get("domain-name"));
   /* try {
      Configurator.reinitialize();
      Configurator.load();
      {}//Logwriter.printOnConsole("domain-name="
       //   + zws.application.Properties.get("domain-name"));
    } catch (Exception e) {
      e.printStackTrace();
      {}//Logwriter.printOnConsole(e.getMessage());
    }*/
    try {
    	/*
    }
      java.util.Collection c = zws.service.repository.RepositorySvc.getPrototypeNames();
      zws.util.PrintUtil.print(c);

      java.util.Collection rps = zws.service.repository.RepositorySvc.findAll();
      Repository x;
      Iterator i = rps.iterator();
      {}//Logwriter.printOnConsole("---------------------------");
      while (i.hasNext()) {
        x = (Repository) i.next();
        x.getContext().dump(System.out);
      }
      {}//Logwriter.printOnConsole("---------------------------");

      Repository r = zws.service.repository.RepositorySvc.find("ilink");
      if (null == r) {} {}//Logwriter.printOnConsole("ilink-demo is null!");
      if (null != r) {} {}//Logwriter.printOnConsole("ilinkpassword :" + r.getSystemPassword());

      Repository agileRep = zws.service.repository.RepositorySvc.find("agile-cif");
      if (null == agileRep) { } {}//Logwriter.printOnConsole("agile-demo is null!");
      if (null != agileRep) { } {}//Logwriter.printOnConsole("agile password :"   + agileRep.getSystemPassword());
      */
    	String ELIPSES = "...";
    	String attValue = "Agile part is Similar to 999-9999 but not the same."; 
      if(50 > 0) {
          if (attValue.length()>50) {
            attValue = attValue.substring(0, 50-ELIPSES.length()) + ELIPSES;
          }
          System.out.println("attValue :" +attValue);
          System.out.println("attValue :" +attValue.length());
      }
          
    } catch (Exception e) {
      e.printStackTrace();
      {}//Logwriter.printOnConsole(e.getMessage());
    }
  }

}
