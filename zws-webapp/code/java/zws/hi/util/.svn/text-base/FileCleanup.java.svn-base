/* DesignState - Design Compression Technology
 * @author: Rodney
 * @version: 1.0
 * Created on Apr 28, 2008 8:35:48 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.hi.util;

import com.zws.hi.Interactor;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import zws.application.Properties;

import com.zws.hi.Interactor;

public class FileCleanup extends Interactor {

  public static final String KEY_BACKUP_PATH = "zws-backup";
  public static final String KEY_BACKUP_LOCATIONS = "zws-backup-locations";
  private List messages = new ArrayList();
  private long maxDate = -1;
  private int removed = 0;
  
  public synchronized String cleanupFiles() {	  
	  
	  messages.clear(); 
	  setMaxDate();
	  File loc;
	  String[] locations = getFileLocations();

	  try {		  
		  for (int i=0; i<locations.length; i++) {
			  loc = new File(locations[i]);
			  if (!loc.exists()) {
				  messages.add("Location does not exist, check configuration: " + loc);
				  continue;
			  }			  
			  deleteOldFiles(loc);
		  }
		  messages.add(Integer.toString(removed) + " items removed.");
	  } catch (Exception e) {
	  	messages.add("Error processing request " + e.getMessage());
	  	return "index";
	  }		  
    
    return "index";
  }


  private void deleteOldFiles(File directory) {
	  
	  String[] files = directory.list();
	  
	  // remove files at this level
	  for(int i=0; i<files.length; i++) {
		  File f = new File(directory.getAbsolutePath() + File.separator + files[i]);
		  
		  // if we're working on a single file, check the date and delete it if its old
		  // if its a directory, go into the directory and evaluate it (recursive)
		  if (f.isFile()) {
			  // if the modification date is too new, move on
			  if ( f.lastModified() <= maxDate) {
				  messages.add("Deleted file " + f.getAbsolutePath());
				  f.delete();
				  removed++;
			  }
		  } else {
			  deleteOldFiles(f);
			  
			  // if the directory is now empty, delete it
			  if (f.list().length == 0) {
				  messages.add("Deleted directory " + f.getAbsolutePath()); 
				  f.delete();
				  removed++;
			  }
		  }
	  }
  }
  
  private void setMaxDate() {
	  Calendar calendar = new GregorianCalendar();
	  calendar.set(Calendar.HOUR_OF_DAY, 0);
	  calendar.set(Calendar.MINUTE, 0);
	  calendar.set(Calendar.SECOND, 0);
	  calendar.set(Calendar.MILLISECOND, 1);
	  maxDate = calendar.getTimeInMillis();
  }
  
  public String[] getFileLocations() {
	  ArrayList list = new ArrayList(Properties.getCollection(KEY_BACKUP_LOCATIONS));
	  String[] locations = new String[list.size()];
	  list.toArray(locations);
	  return locations;
  }
 
  public void startRequest() throws Exception { messages.clear();}
  public List getMessages()           { return messages; }

}
