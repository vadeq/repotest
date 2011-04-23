package com.zws.hi.document;
import com.zws.hi.hiList;


public abstract  class hiDocumentList extends hiList {
}
    /*
  private StreamableCollection searchResults = new StreamableCollection();

  private static int tmpidx=10;
  public String test() {
    try {
      com.zws.datasource.SQLServerSource s = new com.zws.datasource.SQLServerSource();
      s.setName("tejas");
      s.setUsername("cfusion");
      s.setPassword("cfusion");
      s.setDatabaseName("ern");
      FileOutputStream out = new FileOutputStream("C:\\testout.pdf");
      s.writeBinary(Properties.get("test-value-1"), out);
    }
    catch (Exception e) { e.printStackTrace(); }
    return ctrlLIST;
  }

  public String download() {
    hiDocument hiDoc = (hiDocument)searchResults.item(Integer.valueOf(getID()).intValue());
    Document doc = hiDoc.getFromHistory(getRev(), getVer());
    Finder finder = (Finder)doc.getFinder();
    try {
      finder.find(true);
      hiFileDownloader.streamForViewing(getHttpResponse(), finder); return ctrlLIST;
    }
    catch (Exception e) { e.printStackTrace(); return ctrlSYSTEM_ERROR; }
  }
  public int getCols(){ return getReport().getVisibleDisplayFields().size()+1; }
  public Collection getVisibleDisplayFields(){ return getReport().getVisibleDisplayFields(); }

  public String searchCompleteNotification() {
    try { getReport().filter(); }
    catch (Exception e) {
      logFormError(errREPORT_FILTER, e.getMessage());
      e.printStackTrace();
      return ctrlLIST;
    }
    setItems(getReport().getResults());
    if (0==getItems().size()) logFormStatus(statFOUND_ZERO_RESULTS);
    else if (1==getItems().size()) logFormStatus(statFOUND_ONE_RESULT);
    else logFormStatus(statFOUND_RESULTS, ""+getItems().size());
    return ctrlLIST;
  }

  public int getResultCount(){ return searchResults.size(); }
  public String toggleStreaming() {
    if (getStreaming()) setStreaming(false);
    else setStreaming(true);
    return ctrlLIST;
  }

  public String search() throws Exception {
    logFormStatus(statSEARCHING, getCriteria());
    if (null==getCriteria() || "".equals(getCriteria())) return ctrlLIST;
    searchResults.clear();
    setItems(searchResults);
    hiDocument adapter = new hiDocument();
    adapter.setSession(getSession());
    adapter.setReportName(getActiveReportName());
    searchResults.setAdapter(adapter);
    searchResults.open();
    getReport().setCriteria(getCriteria());
    getReport().setMemberID(getMember().getUser().getUsername());
    getReport().setResultBuffer(searchResults);
    getReport().setStreaming(getStreaming());
    getReport().generate();
    if (getReport().getStreamingEnabled() && getStreaming()) return ctrlSTREAMING_LIST;
    else {
      getReport().filter();
      setItems(getReport().getResults());
      return ctrlLIST;
    }
  }
  public String getCriteria() {return criteria;}
  public void setCriteria(String x) {criteria=x;}
  public boolean getStreamingEnabled() { return getReport().getStreamingEnabled(); }
  public boolean getStreaming() { return streaming; }
  public void setStreaming(boolean b) { streaming=b; }
  public String getActiveReportName() {
    if (null==reportName) {
      Iterator i = DataReportService.getDisplayReportNames().iterator();
      if (i.hasNext()) setActiveReportName(i.next().toString());
    }
    return reportName;
  }
  public void setActiveReportName(String s) {
    reportName=s; 
    try { report=(DataReport)DataReportService.findDisplayReport(reportName).copy(); }
    catch (Exception e) { e.printStackTrace(); } //no data reports have been configured
  }
  public Collection getReportNames() { return DataReportService.getDisplayReportNames(); }
  public DataReport getReport() { return report; }

  public String getRev() { return rev; }
  public void setRev(String s) { rev=s; }
  public String getVer() { return ver; }
  public void setVer(String s) { ver=s; }

  private String reportName=null;
  private DataReport report=null;
  private String criteria = null;
  private boolean streaming=false;
  private String rev=null;
  private String ver=null;
}
*/
/*
  public class ReadInput implements Runnable{
    private Collection c;
    public ReadInput(Collection col) { c=col; }
    public void run() {
      KeyValue k;
      char datum;
      while (true){
        try {
          datum = (char)System.in.read();
          if (10==datum) continue;
          k = new KeyValue("",String.valueOf(datum));
          c.add(k);
        }
        catch (Exception e) {e.printStackTrace();}
      }
    }
  }
  private Collection stream = new Vector();
  public hiDocumentList() {
    super();
    Thread t = new Thread(new ReadInput(stream));
    t.start();
  }
  public String clearStream(){ stream.clear(); return ctrlLIST; }
  public Collection getStream() {
    if (stream.size()<4) this.getHttpResponse().setHeader("REFRESH", "2; URL=/document/index.do");
    return stream;
  }
*/
