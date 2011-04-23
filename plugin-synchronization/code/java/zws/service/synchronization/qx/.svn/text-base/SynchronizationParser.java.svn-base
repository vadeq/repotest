package zws.service.synchronization.qx;

import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.synchronization.SynchronizationRecordBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


/**
 * The Class InstructionParser.
 */
public class SynchronizationParser extends DefaultHandler {

  /**  */
  private HashMap dataMap = null;
  /** */
  private Collection collection = null;
  /** */
  String elementName  = null;
  /**
   * The Constructor.
   */
  public SynchronizationParser() {
    dataMap = new HashMap();
    collection = new ArrayList();
  }

  /**
   * startElement.
   * @param uri uri
   * @param localName localName
   * @param qName qName
   * @param attribs attribs
   * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
   * java.lang.String, java.lang.String, org.xml.sax.Attributes)
   */
  public void startElement(String uri, String localName, String qName, Attributes attribs) {
    try {
      if ("result".equals(localName) || "syncService".equals(localName)) {
        return;
      } else if ("methodData".equals(localName)) {
        dataMap.put(SynchronizationClient.METHOD, attribs.getValue("name"));
        String key, value;
        for (int idx = 0; idx < attribs.getLength(); idx++) {
          key = attribs.getQName(idx);
          value = attribs.getValue(idx);
          dataMap.put(key, value);
        }
      } else if ("data".equals(localName)) {
        collection = new ArrayList();
        elementName = attribs.getValue("elementName");
      } else if ("origin".equals(localName)) {
        //collection.add(attribs.getValue("value"));
        String strOrigin = attribs.getValue("value");
        collection.add(OriginMaker.materialize(strOrigin));
      } else if ("syncRecord".equals(localName)) {
        SynchronizationRecordBase syncRecord = new SynchronizationRecordBase();
        Origin origin0 = OriginMaker.materialize((String) attribs.getValue("origin0"));
        Origin originA = OriginMaker.materialize((String) attribs.getValue("originA"));
        syncRecord.setData(origin0, originA);
        collection.add(syncRecord);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * endElement.
   *
   * @param uri  uri
   * @param localName localName
   * @param qName qName
   * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
   * java.lang.String, java.lang.String)
   */
  public void endElement(String uri, String localName, String qName) {
    if ("data".equals(localName)) {
      dataMap.put(elementName, collection);
    }
  }

  /**
   * @param attribs attributes
   * @return executionRecord
   * @throws Exception exception
   */
  private Origin prepareOrigin(Attributes attribs) throws Exception {
    return OriginMaker.materialize(attribs.getValue("value"));
  }



  /**
   * The main method.
   * @param args the args
   */
 public static void main(String[] args) {
   /* InputSource src;
    try {
      // src = new InputSource(new FileInputStream(file));
      //StringReader strRdr = new StringReader("<syncService><methodData name=\"test\" arg1=\"value1\" arg2=\"value2\"/><data elementName=\"originList\"><origin value=\"zws|node0|ilink|ilink|8|branch1|rev1|0|name1|N/A|N/A|N/A|N/A\"/><origin value=\"zws|node0|ilink|ilink|8|branch2|rev2|0|name2|N/A|N/A|N/A|N/A\"/><origin value=\"zws|node0|ilink|ilink|8|branch3|rev2|0|name3|N/A|N/A|N/A|N/A\"/><origin value=\"zws|node0|ilink|ilink|8|branch4|rev2|0|name4|N/A|N/A|N/A|N/A\"/><origin value=\"zws|node0|ilink|ilink|8|branch5|rev2|0|name5|N/A|N/A|N/A|N/A\"/></data></syncService>");
      StringReader strRdr = new StringReader("<result><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch1|rev1|0|name1|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch2|rev2|0|name2|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch1|rev1|0|name1|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch3|rev2|0|name3|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch1|rev1|0|name1|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch4|rev2|0|name4|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch1|rev1|0|name1|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch5|rev2|0|name5|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch1|rev1|0|name1|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch2|rev2|0|name2|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch2|rev2|0|name2|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch3|rev2|0|name3|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch2|rev2|0|name2|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch4|rev2|0|name4|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch2|rev2|0|name2|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch5|rev2|0|name5|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch1|rev1|0|name1|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch3|rev2|0|name3|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch2|rev2|0|name2|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch3|rev2|0|name3|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch3|rev2|0|name3|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch4|rev2|0|name4|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch3|rev2|0|name3|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch5|rev2|0|name5|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch1|rev1|0|name1|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch4|rev2|0|name4|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch2|rev2|0|name2|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch4|rev2|0|name4|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch3|rev2|0|name3|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch4|rev2|0|name4|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch4|rev2|0|name4|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch5|rev2|0|name5|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch1|rev1|0|name1|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch5|rev2|0|name5|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch2|rev2|0|name2|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch5|rev2|0|name5|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch3|rev2|0|name3|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch5|rev2|0|name5|N/A|N/A|N/A|N/A\" /><syncRecord origin0=\"zws|node0|ilink|ilink|8|branch4|rev2|0|name4|N/A|N/A|N/A|N/A\" originA=\"zws|node0|ilink|ilink|8|branch5|rev2|0|name5|N/A|N/A|N/A|N/A\" /></result>");
      // InputSource wrapping a StringReader wrapping your String,
      src = new InputSource(strRdr);
      SynchronizationParser insParser = new SynchronizationParser();
      XMLReader rdr = XMLReaderFactory
          .createXMLReader("org.apache.xerces.parsers.SAXParser");
      rdr.setContentHandler(insParser);
      rdr.setFeature("http://xml.org/sax/features/validation", false);
      rdr.parse(src);
      Collection recordings = insParser.getCollection();
      {} //System.out.println("---------5-------");
      {} //System.out.println(recordings.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }*/
  }

  /**
   * @return the recordings
   */
  public Collection getCollection() {
    return collection;
  }

  /**
   * @param recordingSet the recordings to set
   */
  public void setCollection(Collection recordingSet) {
    this.collection = recordingSet;
  }

  /**
   * @return the dataMap
   */
  public HashMap getDataMap() {
    return dataMap;
  }

  /**
   * @param data the dataMap to set
   */
  public void setDataMap(HashMap data) {
    this.dataMap = data;
  }
}
