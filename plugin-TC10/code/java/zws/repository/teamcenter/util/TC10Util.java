package zws.repository.teamcenter.util;

import zws.data.MetadataBase;
import zws.repository.teamcenter.TC10Constants;

public class TC10Util {

	public static String materializeState(MetadataBase m) {

		String releaseStatus = "N/A";//last_release_status
		if (m.get(TC10Constants.RELEASE_LEVEL) != null
				&& !m.get(TC10Constants.RELEASE_LEVEL).trim().equals(""))
			releaseStatus = m.get(TC10Constants.RELEASE_LEVEL);

		String lockStatus = "N/A";//checked_out
		if (m.get(TC10Constants.LOCK_STATUS) != null
				&& !m.get(TC10Constants.LOCK_STATUS).trim().equals(""))
			lockStatus = TC10Constants.LOCKED;

		String owner = "N/A";//owning_user
		if (m.get(TC10Constants.OWNER) != null
				&& !m.get(TC10Constants.OWNER).trim().equals(""))
			owner = m.get(TC10Constants.OWNER);

		return releaseStatus + TC10Constants.delim + lockStatus + TC10Constants.delim + owner;
	}

	  /**
	   * Returns the type of data repository (e.g. Intralink, TeamCenter, Agile,
	   * File System, etc.).
	   *
	   * @return The type of repository.
	   */
	  public static String materializeItemUniqueID(String uid, String itemId) {
	    return uid + TC10Constants.delim + itemId;
	  }

	  public static String materializeItemRevUniqueID(String uid, String itemId,
	      String revision) {
	    return uid + TC10Constants.delim + itemId + TC10Constants.delim + revision + TC10Constants.delim;
	  }

	  public static String materializeDatasetUniqueID(String uid, String itemId,
	      String revision, String datasetType, String datasetName) {
	    return uid + TC10Constants.delim + itemId + TC10Constants.delim + revision + TC10Constants.delim + datasetType + TC10Constants.delim + datasetName;
	  }

	  public static String materializeIMANFileUniqueID(String uid, String itemId,
	      String revision, String datasetType, String datasetName, String fileName) {
	    return uid + TC10Constants.delim + itemId + TC10Constants.delim + revision + TC10Constants.delim + datasetType + TC10Constants.delim + datasetName
	        + TC10Constants.delim + fileName + TC10Constants.delim;
	  }
}
