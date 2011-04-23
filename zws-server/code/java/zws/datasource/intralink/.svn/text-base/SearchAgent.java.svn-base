package zws.datasource.intralink; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.data.Metadata;
import zws.datasource.DatasourceSearchAgentBase;
import zws.datasource.intralink.op.commonspace.Search;
//impoer zws.util.{}//Logwriter;

public class SearchAgent extends DatasourceSearchAgentBase {
  public void executeQuery() throws Exception {
/*
    CriteriaFilter filter = new CriteriaFilter();
    filter.setCriteria(getCriteria());
    filter.initialize();
    add(filter);
    //++ move above code into filesystem-agent()
*/
    Search seeker = new Search();
    IntralinkSource datasource= (IntralinkSource)getDatasource();
    if (null==getCriteria() || 0==getCriteria().toString().length()) return;
//    seeker.setCriteria(compactCriteria(getCriteria()));
    
    //String c = prepareCriteria();
    String c = getCriteria().toString();
    seeker.setCriteria(c);
    seeker.setSkipInstances(getChooseOnlyBinaries());
    seeker.setIncludeHistory(getIncludeHistory());
    seeker.setIncludeDependencies(getIncludeDependencies());
    seeker.setDatedAfter(getDatedAfter());
    seeker.setDatedBefore(getDatedBefore());
    String s = getSelect();
    if (null==s || "".equals(s)) s = NAME+","+BRANCH+","+REVISION+","+VERSION+","+CREATED_ON;
    if (-1==s.indexOf(NAME)) s += ","+NAME;
    if (-1==s.indexOf(BRANCH)) s += ","+BRANCH;
    if (-1==s.indexOf(REVISION)) s += ","+REVISION;
    if (-1==s.indexOf(VERSION)) s += ","+VERSION;
    if (-1==s.indexOf(CREATED_ON)) s += ","+CREATED_ON;
    seeker.setDatasource(datasource); 
    seeker.setSelect(s);
    seeker.setOrderBy(getOrderBy());    
    seeker.setAscending(getAscending());
    seeker.setMaxCount(getMaxCount());
    seeker.setSkipCount(getSkipCount());
    seeker.setAuthentication(getAuthenticationToken());  //++ convert token to actual authentication
    //seeker.setDatasource((IntralinkSource)getDatasource());
    seeker.setStorable(this);
    seeker.execute();
  }

  
  private String prepareCriteria() {
    //Intralink can not handle queries for alphanumeric versions (intralink versions are ints)
    // so [ Name=zws* & Version=1 ] | [ Name=zws* & Version=A ] errors out.
    // but Version=A to Version=A*, the query is accepted.
    // so all alpha numeric versions have an "*" asteriks appended
    // this perserve the rest of the criteria - which should return valid results
   String s = getCriteria().toString(); 
   if (-1 == s.indexOf("Version=")) return s;
   int idx = s.indexOf("Version=");
   String c = s.substring(0,idx+8);
   String val;
   int idx2=-1;
   while (0<=idx) {
     idx += 8;
     idx2 = s.indexOf(" ", idx);
     val = s.substring(idx, idx2);
     try { c+=Integer.valueOf(val); }
     catch (Exception e) { c+=val+"*"; {}//Logwriter.printOnConsole("Version=" +val+ " -> Version=" +val+"*");}
     //+++ need to add * to inside of quotes '0*' instead of '0'*
     }
     idx = s.indexOf("Version=",idx2);
     if (-1==idx) c += s.substring(idx2);
     else c += s.substring(idx2,idx+8);
   }
   return c;
  }
  
  public void setDeleteOutput(boolean b) { deleteOutput=b; }
  public boolean getDeleteOutput() { return deleteOutput; }

  private String compactCriteria(String criteria) { //kill all this criteria stuff once criteria model is in place.
    char[] crit = criteria.trim().toCharArray();
    StringBuffer b = new StringBuffer();
    for (int i = 0; i < crit.length; i++)
      if (' ' != crit[i]) b.append(crit[i]);
    
    crit = b.toString().toCharArray();
    b = new StringBuffer();
    
    char c;
    String mode=null;
    boolean stared = false;
    for (int i = 0; i < crit.length; i++){
      c = crit[i];
      if ('[' == c) b.append("[ ");
      else if (']' == c) b.append(" ]");
      else if ('&' == c) b.append(" & ");
      else if ('|' == c) b.append(" | ");
      else if ('=' == c) {
        mode="value";
        stared=false;
        b.append("=");
      }
      else if ("value".equals(mode)) {
        if (!stared && (' '==c || ']'==c || '&'==c)) {
          stared=true;
          b.append('*');
          mode = "normal";
        }
        if (']'==c) b.append(" ]");
        else if ('&'==c) b.append(" &");
        else b.append(c);
      }
      else b.append(c);
    } 
    {}//Logwriter.printOnConsole("compacting " + criteria + " to " + b.toString());
    return b.toString();
  }

  public void addSystemAttributes(Metadata data) {
    super.addSystemAttributes(data);
    IntralinkSource source = (IntralinkSource)getDatasource();
    data.set(Names.METADATA_RELEASE_LEVEL_SEQUENCE, "" + source.getSequenceForReleaseLevel(data.get(RELEASE_LEVEL)));
    data.set(Names.METADATA_REVISION_SEQUENCE, "" + source.getSequenceForRevision(data.get(REVISION)));
  }
  
  private boolean deleteOutput = true;
  private static String NAME = IntralinkSource.NAME;
  private static String BRANCH = IntralinkSource.BRANCH;
  private static String REVISION = IntralinkSource.REVISION;
  private static String VERSION = IntralinkSource.VERSION;
  private static String CREATED_ON = IntralinkSource.CREATED_ON;
  private static String RELEASE_LEVEL = IntralinkSource.RELEASE_LEVEL;
}
