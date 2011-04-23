package zws.repository.ilink3;
/*
 * DesignState - Design Compression Technology.
 * @author: Arbind Thakur @version: 1.0 Copywrite
 * (c) 2003 Zero Wait-State Inc. All rights
 * reserved
*/


import zws.origin.Origin;
import zws.repository.ilink3.qx.client.op.commonspace.Search;
import zws.repository.search.RepositorySearchAgentBase;
//import zws.util.{}//Logwriter;
import zws.data.Metadata;


/**
 * The Class Ilink3RepositorySearchAgent.
 */
public class Ilink3RepositorySearchAgent extends RepositorySearchAgentBase {

  public Ilink3RepositorySearchAgent() {
    this.setName(Origin.FROM_ILINK);
  }
  /**
   * @throws Exception exception
   * @see zws.repository.search.RepositorySearchAgentBase#executeQuery()
   */
  public void executeQuery() throws Exception {
    Search seeker = new Search();
    if (null == getCriteria() || 0 == getCriteria().toString().length()) {
      return;
    }
    // String c = prepareCriteria();

    String c = getCriteria().toString();
    seeker.setCriteria(c);
    seeker.setSkipInstances(getChooseOnlyBinaries());
    seeker.setIncludeHistory(getIncludeHistory());
    seeker.setIncludeDependencies(getIncludeDependencies());
    seeker.setDatedAfter(getDatedAfter());
    seeker.setDatedBefore(getDatedBefore());
    String s = getSelect();
    if (null == s || "".equals(s)) {
      s = NAME + "," + BRANCH + "," + REVISION + "," + VERSION + "," + CREATED_ON;
    }
    if (-1 == s.indexOf(NAME)) {
      s += "," + NAME;
    }
    if (-1 == s.indexOf(BRANCH)) {
      s += "," + BRANCH;
    }
    if (-1 == s.indexOf(REVISION)) {
      s += "," + REVISION;
    }
    if (-1 == s.indexOf(VERSION)) {
      s += "," + VERSION;
    }
    if (-1 == s.indexOf(CREATED_ON)) {
      s += "," + CREATED_ON;
    }

    // Ilink3RepositoryBase ilink3Client = new Ilink3RepositoryBase();
    // ilink3Client.setRepositoryName("ilink-1");

    seeker.setRepository((Ilink3RepositoryBase) getRepository());
    seeker.setSelect(s);
    seeker.setOrderBy(getOrderBy());
    seeker.setAscending(getAscending());
    seeker.setMaxCount(getMaxCount());
    seeker.setSkipCount(getSkipCount());
    seeker.setAuthentication(getAuthenticationToken()); // ++ convert token to
                                                        // actual authentication
    // seeker.setDatasource((IntralinkSource)getDatasource());
    seeker.setStorable(this);
    seeker.execute();

  }

  /**
   * Prepare criteria.
   *
   * @return the string
   */
  private String prepareCriteria() {
    // Intralink can not handle queries for alphanumeric versions (intralink
    // versions are ints)
    // so [ Name=zws* & Version=1 ] | [ Name=zws* & Version=A ] errors out.
    // but Version=A to Version=A*, the query is accepted.
    // so all alpha numeric versions have an "*" asteriks appended
    // this perserve the rest of the criteria - which should return valid
    // results
    String s = getCriteria().toString();
    if (-1 == s.indexOf("Version=")) {
      return s;
    }
    int idx = s.indexOf("Version=");
    String c = s.substring(0, idx + 8);
    String val;
    int idx2 = -1;
    while (0 <= idx) {
      idx += 8;
      idx2 = s.indexOf(" ", idx);
      val = s.substring(idx, idx2);
      try {
        c += Integer.valueOf(val);
      } catch (Exception e) {
        c += val + "*";
        {}//Logwriter.printOnConsole("Version=" + val + " -> Version=" + val + "*");
      } // +++ need to add * to inside of quotes '0*' instead of '0'*
      idx = s.indexOf("Version=", idx2);
      if (-1 == idx) {
        c += s.substring(idx2);
      } else {
        c += s.substring(idx2, idx + 8);
      }
    }
    return c;
  }

  /**
   * Sets the delete output.
   *
   * @param b the delete output
   */
  public void setDeleteOutput(boolean b) {
    deleteOutput = b;
  }

  /**
   * Gets the delete output.
   *
   * @return the delete output
   */
  public boolean getDeleteOutput() {
    return deleteOutput;
  }

  /**
   * Compact criteria.
   *
   * @param criteria the criteria
   *
   * @return the string
   */
  private String compactCriteria(String criteria) { // kill all this criteria
                                                    // stuff once criteria model
                                                    // is in place.
    char[] crit = criteria.trim().toCharArray();
    StringBuffer b = new StringBuffer();
    for (int i = 0; i < crit.length; i++) {
      if (' ' != crit[i]) {
        b.append(crit[i]);
      }
    }

    crit = b.toString().toCharArray();
    b = new StringBuffer();

    char c;
    String mode = null;
    boolean stared = false;
    for (int i = 0; i < crit.length; i++) {
      c = crit[i];
      if ('[' == c) {
        b.append("[ ");
      } else if (']' == c) {
        b.append(" ]");
      } else if ('&' == c) {
        b.append(" & ");
      } else if ('|' == c) {
        b.append(" | ");
      } else if ('=' == c) {
        mode = "value";
        stared = false;
        b.append("=");
      } else if ("value".equals(mode)) {
        if (!stared && (' ' == c || ']' == c || '&' == c)) {
          stared = true;
          b.append('*');
          mode = "normal";
        }
        if (']' == c) {
          b.append(" ]");
        } else if ('&' == c) {
          b.append(" &");
        } else {
          b.append(c);
        }
      } else {
        b.append(c);
      }
    }
    {}//Logwriter.printOnConsole("compacting " + criteria + " to " + b.toString());
    return b.toString();
  }

  /**
   * @param data metadata
   * @see zws.repository.search.RepositorySearchAgentBase#addSystemAttributes(zws.data.Metadata)
   */
  public void addSystemAttributes(Metadata data) {
    super.addSystemAttributes(data);
    // data.set(Names.METADATA_RELEASE_LEVEL_SEQUENCE, "" +
    // ilink3.getSequenceForReleaseLevel(data.get(RELEASE_LEVEL)));
    // data.set(Names.METADATA_REVISION_SEQUENCE, "" +
    // ilink3.getSequenceForRevision(data.get(REVISION)));
  }

  /** The delete output. */
  private boolean deleteOutput = true;

  /** The NAME. */
  private static String NAME = Ilink3Constants.NAME;

  /** The BRANCH. */
  private static String BRANCH = Ilink3Constants.BRANCH;

  /** The REVISION. */
  private static String REVISION = Ilink3Constants.REVISION;

  /** The VERSION. */
  private static String VERSION = Ilink3Constants.VERSION;

  /** The CREATE d_ ON. */
  private static String CREATED_ON = Ilink3Constants.CREATED_ON;

  /** The RELEAS e_ LEVEL. */
  //private static String RELEASE_LEVEL = Ilink3Constants.RELEASE_LEVEL;
}
