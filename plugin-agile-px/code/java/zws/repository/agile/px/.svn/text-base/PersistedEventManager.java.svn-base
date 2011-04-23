package zws.repository.agile.px;

/*
	DesignState - Design Compression Technology.
	@author: Rodney McCabe
	Created on Mar 13, 2008
	@version: 1.0
	Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved
*/

import zws.repository.agile.event.EventClient;
import java.util.Iterator;
import java.util.List;

public class PersistedEventManager implements Runnable {

	private static PersistedEventManager manager=null;
	private static Thread thread=null;
	private boolean sleeping = false;
	private Object lock = new Object();

	private PersistedEventManager() {
		//singleton!
	}

  static String DESIGNSTATE_QUEUE_RETRY_MIN="px.queue.retry.min";

  protected int getRetryMinutes() {
    String retryMins = PxActionBase.getProperty(DESIGNSTATE_QUEUE_RETRY_MIN);
    int retryMinutes = Integer.parseInt(retryMins);
    return retryMinutes;
  }


	public static synchronized PersistedEventManager getInstance() {
		if (manager == null) {
			manager = new PersistedEventManager();
			thread = new Thread(manager);
			thread.start();
		}

		return manager;
	}
  static String DESIGNSTATE_QUEUE_FILE="px.queue.file";

	/*  I dont really know what the agile PX interface looks like .... but you should get the idea

		1.  Get any items that may be persisted
		2.  Add the current request to the list
		3.  We have a list of notifications to go out ... try to send them
				A.	If the notification fails, Stop processing
		4.	Remove it from the list and move on
		5.  If items are left, an error occurred, persist them
	*/
	public synchronized void sendEvent(String message) {

		PersistedEvent pxFile = new PersistedEvent(PxActionBase.getProperty(DESIGNSTATE_QUEUE_FILE));
		List items = pxFile.getItems();
		String event;

		if (message!=null && message.trim().length()>1)
			items.add(message);

		Iterator iterator = items.iterator();

		try {
			EventClient client = PxActionBase.materiazlizeEventClient();

			while (iterator.hasNext()) {

				event = (String) iterator.next();
				PxActionBase.debug("Sending event: " + event);

				if ( !client.fireEvent(event) ) {
					PxActionBase.log("Endpoint down, caching event information");
					break;
				} else {
					iterator.remove();
				}
			}
		} catch (Exception e) {
			//System.out.println("Error creating and using the client: " + e.getMessage());
			PxActionBase.log("Error creating and using the client: " + e.getMessage());
			PxActionBase.debug(e.getMessage());
		}

		pxFile.removeFile();

		if (items.size() > 0) {
			pxFile.persistItems(items);

			synchronized (lock) {
					if (!sleeping) {
						thread = new Thread(this);
						thread.start();
					}
			}
		}
	}


	public void run() {

		try {

			// go to sleep
			synchronized (lock) {	sleeping = true; }
			PxActionBase.debug("Going to sleep ...");
			Thread.sleep(getRetryMinutes() * 60* 1000);

			// wake up
			synchronized (lock) { sleeping = false; }
			PxActionBase.debug("Woke up, looking for events to process ...");
			sendEvent(null);
			thread = null;

		} catch (Exception e) {
			// do nothing
		}

	}
}