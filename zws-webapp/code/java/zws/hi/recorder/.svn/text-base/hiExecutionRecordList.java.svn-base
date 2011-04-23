package zws.hi.recorder;

import zws.IRecorderBO;
import zws.recorder.ExecutionRecord;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

import com.zws.hi.DelegateFactory;
import com.zws.hi.hiList;

/*
 * DesignState - Design Compression Technology. @author: Rahul Deshmukh Created
 * on Mar 6, 2007 @version: 1.0 Copywrite (c) 2002-2005 Zero Wait-State Inc. All
 * rights reserved
 */

public class hiExecutionRecordList extends hiList  {
    
    public void bind() {
      if (null==stack) stack = new Stack();
      if (null==recorderBO) recorderBO = (IRecorderBO) DelegateFactory.getBusinessObject( "zws.RecorderBO", IRecorderBO.class);
      if(!stack.isEmpty()) {
        selectedExecutionRecord = (ExecutionRecord) stack.peek();
      }else {
      try {
        if(!com.zws.util.StringUtil.isEmptyNullString(getID())) {  
                        selectedExecutionRecord = recorderBO.getRecording(Long.valueOf(getID()).longValue());
                    }else {
                        selectedExecutionRecord =null;
                    }    
                } catch (NumberFormatException e1) {
                    // +++ Auto-generated catch block
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // +++ Auto-generated catch block
                    e1.printStackTrace();
                }
            }
    }

    public Collection getNamespaces() {

        Collection c = null;
        try {
            c = recorderBO.getNamespaces();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == c)
            return new Vector();

        return c;
    }

    public String chooseNamespace() {
        selectedNamespace = getID();
        return ctrlListNames;
    }

    public Collection getNames() {
        Collection c = null;
        try {
            c = recorderBO.getNames(selectedNamespace);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == c)
            return new Vector();

        return c;
    }

    public String chooseName() {
        selectedName = getID();
        return ctrlListRecords;
    }

    public Collection getRecordings() {
        Collection c = null;
        try {
            c = recorderBO.getRecordings(selectedNamespace, selectedName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == c)
            return new Vector();
        return c;
    }

    public String chooseRecord() {
        stack.clear();
        ExecutionRecord r = null;
        try {
            r = recorderBO.getRecording(Long.valueOf(getID()).longValue());
            stack.push(r);
            selectedExecutionRecord = r;            
        } catch (Exception e) {
            // +++ Auto-generated catch block
            e.printStackTrace();
        }
        return ctrlViewExecutionRecord;
    }

    public String chooseChildRecord() {
        ExecutionRecord r = null;
        {} //System.out.println("Inside choose child record");
        try {
            r = recorderBO.getRecording(Long.valueOf(getID()).longValue());
        } catch (NumberFormatException e) {
            // +++ Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // +++ Auto-generated catch block
            e.printStackTrace();
        }
        stack.push(r);
        return ctrlViewExecutionRecord;
    }

    public String jumpToParent() {
        while(((ExecutionRecord)stack.peek()).getID() != Long.valueOf(getID()).longValue()) stack.pop();

        /*
        {} //System.out.println("Inside jumpToParent");
        {} //System.out.println("stack size" + stack.size());
        ExecutionRecord exrec = null;
        int stackSize = 0;
        ExecutionRecord exrecLast = (ExecutionRecord) stack.lastElement();
        ExecutionRecord temp = null;

        exrec = (ExecutionRecord) stack.peek();
        {} //System.out.println("selected id for exrec" + exrec.getID());
        {} //System.out.println("selected id " + getID());
        
        if (!(exrec.getID() == Long.valueOf(getID()).longValue())) {
            temp = (ExecutionRecord) stack.pop();
            while (temp.getID() != Long.valueOf(getID()).longValue()) {
                temp = (ExecutionRecord) stack.peek();
                stack.pop();
            }
        }
        */
        return ctrlViewExecutionRecord;
    }

    public Collection getChildren() {
        if (null == selectedExecutionRecord) return new Vector();
        else return selectedExecutionRecord.children();
    }

    public Collection getStackContents() {
        Vector contents = new Vector();
        Iterator i = stack.iterator();
        for (int idx = 0; idx < stack.size()-1; idx++) contents.add(i.next());        
        return contents;
    }
    
    public String chooseParentRecord() {
        {} //System.out.println("Inside choose parent record");
        if (!stack.isEmpty()) stack.pop();
        return ctrlViewExecutionRecord;
    }

    public long getSelectedID() { return selectedID; }
    public String getSelectedName() { return selectedName; }
    public String getSelectedNamespace() { return selectedNamespace; }
    public ExecutionRecord getSelectedExecutionRecord() { return selectedExecutionRecord; }
    
    Stack stack = new Stack();
    String selectedNamespace = null;
    String selectedName = null;
    long selectedID = -1;
    ExecutionRecord selectedExecutionRecord = null;
    IRecorderBO recorderBO = (IRecorderBO) DelegateFactory.getBusinessObject( "zws.RecorderBO", IRecorderBO.class);

}