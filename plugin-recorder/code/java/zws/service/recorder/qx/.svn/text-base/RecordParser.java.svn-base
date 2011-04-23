package zws.service.recorder.qx;

import zws.recorder.ActivityRecordBase;
import zws.recorder.ExecutionRecord;
import zws.recorder.ExecutionRecordBase;
import zws.recorder.RecordComparator;
import zws.time.DurationBase;
import zws.time.TimeBase;

//import java.io.StringReader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
//import org.xml.sax.InputSource;
//import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
//import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * The Class InstructionParser.
 */
public class RecordParser extends DefaultHandler {

  /**  */
  private TreeSet recordings = null;

  /**   */
  private Stack stack;

  /**
   * The Constructor.
   */
  public RecordParser() {
    recordings = new TreeSet(new RecordComparator());
    stack = new Stack();
  }

  /**
   * startElement.
   *
   * @param uri uri
   * @param localName localName
   * @param qName qName
   * @param attribs attribs
   * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
   * java.lang.String, java.lang.String, org.xml.sax.Attributes)
   */
  public void startElement(String uri, String localName, String qName,
      Attributes attribs) {
    try {
      if ("result".equals(localName)) {
        return;
      } else if (RecorderClient.sEXECUTION_RECORD.equals(localName)) {
        stack.push(prepareExecutionRecord(attribs));
      } else if (RecorderClient.sACTIVITY.equals(localName)) {
        stack.push(prepareActivityRecord(attribs));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * endElement.
   *
   * @param uri uri
   * @param localName localName
   * @param qName  qName
   * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
   * java.lang.String, java.lang.String)
   */
  public void endElement(String uri, String localName, String qName) {
    ExecutionRecordBase record = null;
    ExecutionRecordBase parent = null;
    ActivityRecordBase activity = null;

    if ("result".equals(localName)) {
      return;
    } else if (RecorderClient.sEXECUTION_RECORD.equals(localName)) {
      record = (ExecutionRecordBase) stack.pop();
      if (stack.size() == 0) {
        recordings.add((Object) record);
      } else {
        parent = (ExecutionRecordBase) stack.peek();
        parent.add(record);
      }
    } else if (RecorderClient.sACTIVITY.equals(localName)) {
      activity = (ActivityRecordBase) stack.pop();
      parent = (ExecutionRecordBase) stack.peek();
      parent.add(activity);
    }
  }

  /**
   * @param attribs attributes
   * @return executionRecord
   * @throws Exception exception
   */
  private ExecutionRecord prepareExecutionRecord(Attributes attribs)
      throws Exception {
    DurationBase db = null;
    String key, value;
    ExecutionRecordBase data = new ExecutionRecordBase();
    data.setDuration(new DurationBase());
    for (int idx = 0; idx < attribs.getLength(); idx++) {
      db = (DurationBase)data.getDuration();
      key = attribs.getQName(idx);
      value = attribs.getValue(idx);
      if(null == value) {
        continue;
      } else if (RecorderClient.sID.equals(key)) {
        data.setID(new Long(value).longValue());
      } else if (RecorderClient.sNAMESPACE.equals(key)) {
        data.setNamespace(value);
      } else if (RecorderClient.sNAME.equals(key)) {
        data.setName(value);
      } else if (RecorderClient.sDESCRIPTION.equals(key)) {
        data.setDescription(value);
      } else if (RecorderClient.sSTATUS.equals(key)) {
        data.setStatus(value);
      } else if (RecorderClient.sSTAMP_START_TIME.equals(key)) {
        Calendar c = new GregorianCalendar();
        c.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(value));
        db.setStartTime(new TimeBase(c.getTime()));
      } else if (RecorderClient.sSTAMP_END_TIME.equals(key)) {
        Calendar c = new GregorianCalendar();
        c.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(value));
        db.setEndTime(new TimeBase(c.getTime()));
      }
    }
    return data;
  }

  /**
   * @param attribs attributes
   * @return executionRecord
   * @throws Exception exception
   */
  private ActivityRecordBase prepareActivityRecord(Attributes attribs)
      throws Exception {
    String key, value;
    ActivityRecordBase activity = new ActivityRecordBase();
    for (int idx = 0; idx < attribs.getLength(); idx++) {
      key = attribs.getQName(idx);
      value = attribs.getValue(idx);
      if (RecorderClient.sID.equals(key)) {
        activity.setID(new Long(value).longValue());
      } else if (RecorderClient.sMSGTYPE.equals(key)) {
        activity.setType(value);
      } else if (RecorderClient.sDOMAIN.equals(key)) {
        activity.setDomain(value);
      } else if (RecorderClient.sNODE.equals(key)) {
        activity.setNodeName(value);
      } else if (RecorderClient.sMSG.equals(key)) {
        activity.setMessage(value);
      } else if (RecorderClient.sNOTES.equals(key)) {
        activity.setNotes(value);
      } else if (RecorderClient.sTIME_STAMP.equals(key)) {
        Calendar c = new GregorianCalendar();
        c.setTime(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(value));
        activity.setTimestamp(c.getTime());
      }
    }
    return activity;
  }

  /**
   * The main method.
   *
   * @param args the args
   */

  public static void main(String[] args) {
    InputSource src;
    try { //
    //  src = new InputSource(new FileInputStream(file));
     // StringReader strRdr = new StringReader("<result><record id=\"112\" name=\"recordStartTime\"namespace=\"test-name-space2\" description=\"test-description2\"status=\"test-status2\" startTime=\"5/15/2007 2:6:15\" duration=\"2s\" ><activityid=\"112\" type=\"test-type\" domain=\"test-domain\" nodeName=\"test-node\"message=\"test message3\" timeStamp=\"5/15/2007 2:6:16\" /><activityid=\"112\" type=\"test-type\" domain=\"test-domain\" nodeName=\"test-node\"message=\"test message2\" timeStamp=\"5/15/2007 2:6:16\" /><activityid=\"112\" type=\"test-type\" domain=\"test-domain\" nodeName=\"test-node\"message=\"test message1\" timeStamp=\"5/15/2007 2:6:16\" /><recordid=\"116\" name=\"child4\" namespace=\"child-name-space\"description=\"sub-process\" status=\"null\" startTime=\"5/15/2007 2:6:16\"duration=\"1s\" ></record><record id=\"115\" name=\"child3\"namespace=\"child-name-space\" description=\"sub-process\" status=\"null\"startTime=\"5/15/2007 2:6:16\" duration=\"1s\" ></record><recordid=\"114\" name=\"child2\" namespace=\"child-name-space\"description=\"sub-process\" status=\"null\" startTime=\"5/15/2007 2:6:16\"    duration=\"1s\" ></record><record id=\"113\" name=\"child1\"namespace=\"child-name-space\" description=\"sub-process\" status=\"null\"startTime=\"5/15/2007 2:6:16\" duration=\"1s\" ></record></record></result>");
      StringReader strRdr = new StringReader("<result></result>");
      //InputSource wrapping a StringReader wrapping your String,
      src = new InputSource(strRdr);
      RecordParser insParser = new RecordParser();
      XMLReader rdr = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
      rdr.setContentHandler(insParser);
      rdr.setFeature("http://xml.org/sax/features/validation", false);
      rdr.parse(src); 
      //SortedSet recordings = insParser.getRecordings();
      {} //System.out.println(recordings.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
  }

  /**
   * @return the recordings
   */
  public SortedSet getRecordings() {
    return recordings;
  }

  /**
   * @param recordingSet the recordings to set
   */
  public void setRecordings(TreeSet recordingSet) {
    this.recordings = recordingSet;
  }
}
