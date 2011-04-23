package zws.hi.recorder;

import zws.Recorder;
import zws.application.server.webapp.Names;


import com.zws.application.Properties;
import com.zws.hi.hiList;

import java.util.Collection;
import java.util.Vector;

/*
 * DesignState - Design Compression Technology. 
 * @author: Rahul Deshmukh 
 * Created on Mar 6, 2007 @version: 1.0 Copywrite (c) 2002-2005 Zero Wait-State Inc. All
 * rights reserved
 */

public class hiNamespaceList extends hiList {
    public void bind() {
        recorder = Recorder.getClient(Properties.get(Names.CENTRAL_SERVER));
    }

    public void render() {
        try {
            Collection c = recorder.getNamespaces();
            if (null==c) c = new Vector();
            setItems(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean idChoosesItem(String id, Object o) {
        return id.equals(o.toString());
    }

    Recorder recorder = null;
    
    
    
    
    
    
    
    
}