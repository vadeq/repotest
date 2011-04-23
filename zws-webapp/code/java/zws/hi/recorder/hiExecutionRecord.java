package zws.hi.recorder; 
 /*
  * DesignState - Design Compression Technology.
  * @author: Rahul Deshmukh Created on Mar 6, 2007
  * @version: 1.0 Copywrite (c) 2002-2005 Zero
  * Wait-State Inc. All rights reserved
  */

import zws.Recorder;
import zws.application.server.webapp.Names;
import zws.recorder.ActivityRecordBase;
import zws.recorder.ExecutionRecord;
import zws.time.Time;

import com.zws.application.Properties;
import com.zws.hi.hiItem;

import java.util.SortedSet;
import java.util.TreeSet;

public class hiExecutionRecord extends hiItem {
    Recorder recorder = null;

    private ExecutionRecord executionRecord;

    public void bind() {
        recorder = Recorder.getClient(Properties.get(Names.CENTRAL_SERVER));

        try {
            
            {} //System.out.println(">>>>>>>INSIDE HIEXECUTION RECORD GETID" + getID());
            if(getID()!=null)
            {
                executionRecord = recorder.getLastRecording(getID());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void render() throws Exception {

        setNamespace(executionRecord.getNamespace());
        setId(executionRecord.getID());
        setDuration(executionRecord.getDuration().toString());
        setStartTime(executionRecord.getStartTime());
        setName(executionRecord.getName());
        setStatus(executionRecord.getStatus());
        setActivities(executionRecord.getActivity());
        setChildren(executionRecord.children());

        if (null == executionRecord.children()) {
            SortedSet v = new TreeSet();
            v.add(recorder.getLastRecording("queue.publishing.0.I2A"));
            setChildren(v);
            {} //System.out.println(getChildren().size());

        }
        
        if (null == executionRecord.getActivity()) {
            SortedSet v = new TreeSet();
            ActivityRecordBase act1 = new ActivityRecordBase();
            act1.setMessage("Activity1");
            act1.setType("ActivityType1");
            ActivityRecordBase act2 = new ActivityRecordBase();
            act2.setMessage("Activity2");
            act2.setType("ActivityType2");
            v.add(act1);
            setActivities(v);
            {} //System.out.println(getActivities().size());
        }

    }

    
    public String selectSubProcess() {
      
        {} //System.out.println("Inside selectsubprocess");
        if(executionRecord !=null) {
         setParentId(executionRecord.getID());
        }
        
      return ctrlOK;
    }
    
    
    
    public String selectParentProcess() {
        
        
        {} //System.out.println("Inside selectParentProcess");
        if(executionRecord !=null) {
         setParentId(executionRecord.getID());
        }
       
       return ctrlOK;
    
    }
    
    
    
    
    public boolean idChoosesItem(String id, Object o) {
        return id.equals(o.toString());
    }

    public String Namespace;

    public String Name;

    public String Status;

    public SortedSet activities;

    public SortedSet children;
    
    public long id;
    public String startTime;
    public String duration;
    public String parentId ;
    public String childId;
    

    /**
     * @return Returns the namespace.
     */
    public String getNamespace() {
        return Namespace;
    }

    /**
     * @param namespace
     *            The namespace to set.
     */
    public void setNamespace(String namespace) {
        Namespace = namespace;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return Name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(String status) {
        Status = status;
    }

    /**
     * @return Returns the activities.
     */
    public SortedSet getActivities() {
        return activities;
    }

    /**
     * @param activities
     *            The activities to set.
     */
    public void setActivities(SortedSet activities) {
        this.activities = activities;
    }

    /**
     * @return Returns the children.
     */
    public SortedSet getChildren() {
        return children;
    }

    /**
     * @param children
     *            The children to set.
     */
    public void setChildren(SortedSet children) {
        this.children = children;
    }
    /**
     * @param id The id to set.
     */
    public void setId(long id) {
        this.id = id;
    }
   
    /**
     * @return Returns the id.
     */
    public long getId() {
        return id;
    }
    
    /**
     * @return Returns the startTime.
     */
    public String getStartTime() {
        return startTime;
    }
    /**
     * @param time The startTime to set.
     */
    public void setStartTime(Time time) {
        this.startTime = time.toString();
    }
    /**
     * @return Returns the duration.
     */
    public String getDuration() {
        return duration;
    }
    /**
     * @param duration The duration to set.
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    /**
     * @return Returns the childId.
     */
    public String getChildId() {
        return childId;
    }
    /**
     * @param childId The childId to set.
     */
    public void setChildId(String childId) {
        this.childId = childId;
    }
    /**
     * @return Returns the parentId.
     */
    public String getParentId() {
        return parentId;
    }
    /**
     * @param l The parentId to set.
     */
    public void setParentId(long l) {
        this.parentId = String.valueOf(l);
    }
}