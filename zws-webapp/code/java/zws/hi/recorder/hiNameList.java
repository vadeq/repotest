package zws.hi.recorder;

import zws.IRecorderBO;
import zws.Recorder;
import zws.application.server.webapp.Names;
//import zws.hi.recorder.old.RecorderTest;

import com.zws.application.Properties;
import com.zws.hi.DelegateFactory;
import com.zws.hi.hiList;

import java.util.Collection;

/*
 * DesignState - Design Compression Technology. 
 * @author: Rahul Deshmukh
 * Created on Mar 6, 2007 @version: 1.0 Copywrite (c) 2002-2005 Zero Wait-State Inc. All
 * rights reserved
 */

public class hiNameList extends hiList {
    public void bind() {
        
        
        //new code testing
       try { 
        IRecorderBO recorderBO = (IRecorderBO)DelegateFactory.getBusinessObject("zws.RecorderBO", IRecorderBO.class);
       }catch(Exception e) {
           e.printStackTrace();
       }
        //recorderBO.recordStartTime(new Namespace(),"test");
        
        //end of code
        
        
      recorder = Recorder.getClient(Properties.get(Names.CENTRAL_SERVER));
      if (null==getSelectedNamespace()) setSelectedNamespace(getID());            
      try {
        Collection c = recorder.getNames(getID());
        if (null!=c) setItems(c);
      }
      catch (Exception e) {
          e.printStackTrace();
      }
    }
    /*
    public void RecordTestdata() {
        {} //System.out.println("RecordTestdata");
        Recorder recorder = Recorder.getClient("DesignState-node-0");
        RecorderTest t = new RecorderTest();
        t.run();
    }*/

    public boolean idChoosesItem(String id, Object o) {
      return id.equals(o.toString());
    }
    Recorder recorder = null;
    
    /**
     * @return Returns the selectedNamespace.
     */
    public String getSelectedNamespace() {
        return selectedNamespace;
    }
    /**
     * @param selectedNamespace The selectedNamespace to set.
     */
    public void setSelectedNamespace(String selectedNamespace) {
        this.selectedNamespace = selectedNamespace;
    }
    public String selectedNamespace=null;
}