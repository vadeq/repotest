/* DesignState - Design Compression Technology
 * @author: Rodney
 * @version: 1.0
 * Created on Mar 5, 2008 1:17:09 PM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.hi.queue;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import java.util.*;

public class QueueHandler extends DefaultHandler {
  StringBuffer summary = new StringBuffer();
  StringBuffer instruction = new StringBuffer();

  boolean inSummary = false;
  boolean inInstruction = false;

  ArrayList list = new ArrayList();

  MonitoredQueue[] queues;
  MonitoredQueue queue;
  MonitoredQueueItem item;

  // The respose to the parsing effort will be an
  // array of MonitorQueue objects
/*  public QueueHandler(MonitoredQueue[] queues) {
    this.queues = queues;
  }
*/
  public MonitoredQueue[] getQueues() { return queues; }

  public void endDocument() {

    // build out the result array now that processing is complete
    queues = new MonitoredQueue[list.size()];
    list.toArray(queues);
    //getXMLReader().setProperty("queues", queues);
  }

  public void characters(char[] ch, int start, int length) {

    // the only node text we care about is for summary information
    if (inSummary) summary.append(new String(ch, start, length).trim());
    if (inInstruction) instruction.append(new String(ch, start, length).trim());
  }

  public void startElement(String ns, String sname, String qName, Attributes atts) {

    if (inInstruction) {
      instruction.append("<").append(qName);

      for (int i=0;i<atts.getLength(); i++) {
        instruction.append(" ").append(atts.getQName(i));

        if (atts.getValue(i).toLowerCase().equals("password")) {

					// if this is a password, dont display it in the console
					instruction.append("=\'");
					for (int p=0; p<atts.getValue(i).length(); p++) instruction.append("*");
					instruction.append("\'");

				} else {
      	  instruction.append("=\'").append(atts.getValue(i)).append("\'");
				}
      }

      instruction.append(">");
    }

    if( qName.equals("queue-status")){

      // a new queue is created and its attributes captured
      queue = new MonitoredQueue();
      queue.setName(atts.getValue("name").trim());
      queue.setState(atts.getValue("active").trim());

    } else if(qName.equals("element")) {

      // a new queue item is created and its attributes captured
      item = new MonitoredQueueItem();
      //item.setFilename(atts.getValue("file").trim());
      item.setId(atts.getValue("id").trim());
      item.setPriority(atts.getValue("priority").trim());

    } else if(qName.equals("summary")) {

      // we are now on a new summary node, so all text needs to be
      // cleared out and further text processing designated as beloning
      // to this summary
      summary = new StringBuffer();
      inSummary = true;

    } else if (qName.equals("instruction")) {

      instruction = new StringBuffer();
      inInstruction = true;
    }
  }

  public void endElement(String ns, String sname, String qName){

    if (qName.equals("queue-status")) {

      // once done processing a queue node, add it to the list of queues
      list.add(queue);

    } else if (qName.equals("element")) {

      // when done with a queue item (or element), it needs to be
      // added to the queue that it exists in
      queue.addQueueItem(item);

    } else if (qName.equals("summary")) {

      // update the summary for the given queue item and turn off
      // the summary mechanism that stores characters for the summary
      item.setSummary(summary.toString());
      inSummary = false;

    } else if (qName.equals("instruction")) {
      inInstruction = false;
      item.setInstruction(instruction.toString());
      //System.out.println("Instruction --> "+instruction);
    }

    if (inInstruction) {
      instruction.append("</").append(qName).append(">");
    }
  }
}

