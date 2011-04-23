package com.zws.hi.document;



import com.zws.domo.document.DocumentInterface;
import com.zws.hi.hiItem;

public abstract class hiDocument extends hiItem implements DocumentInterface {
    /*
  private static String STATE_BOM="BOM";
  private static String STATE_INFO="Info";
  private Document doc;

  private String reportName;

  public hiDocument() { super(); setHiState(STATE_INFO); }
  public hiDocument(Document d) { super(null); doc = d; setHiState(STATE_INFO); }
  public Document getDocument() { return doc; }
  public void bind() throws Exception {
    if (null==getID() || null==getSession()) return;
    Member m = (Member)getSession().getAttribute(SessionData.keyMEMBER);
    Finder f = FinderRegistrar.getRegistrar(m.getUser().getUsername()).find(CATEGORY_DOCUMENT, getID());
    if (null!=f) doc = (Document)f.getBinding();
  }

  public void adapt(Object d) {
    doc=(Document)d;
    doc.set(Constants.METADATA_REPORT, getReportName());
    doc.getFinder().setID(getID());
    Member m = (Member)getSession().getAttribute(SessionData.keyMEMBER);
    FinderRegistrar.getRegistrar(m.getUser().getUsername()).register(CATEGORY_DOCUMENT, getID(), doc.getFinder());
  }

  public String toggleState() {
    if (STATE_BOM.equals(getHiState())) setHiState(STATE_INFO);
    else setHiState(STATE_BOM);
    return ctrlOK;
  }

  public void setHistoryComparator(HistoryComparator c) { getDocument().setHistoryComparator(c); }
  public void addToHistory(DocumentInterface doc) { getDocument().addToHistory(doc); }
  public Collection getHistory() { return ListUtil.reverse(getDocument().getHistory()); }
  public Document getFromHistory(String rev, String ver){
    if (null==rev || null==ver) return getDocument();
    Iterator i = getHistory().iterator();
    while (i.hasNext()){
      Document doc = (Document) i.next();
      if (rev.equals(doc.getRevision()) && ver.equals(doc.getVersion())) return doc;
    }
    return getDocument();
  }

  public void add(Reference r) { getDocument().add(r); }
  public Collection getChildren() {
  //    return getDocument().getChildren();
    Document d = getDocument();
    Collection c = d.getChildren();
    return c;
  }
  public void setChildren(Collection c) { getDocument().setChildren(c); }


  public void render() { }

  public boolean isBound() { return null!=doc; }
  private Document selectedDoc() {
    Document doc = getDocument();;
    if (null== getRev()) return doc;
    if (null == getVer()) return doc;
    if (null==doc.getHistory()) return doc;
    Iterator h = doc.getHistory().iterator();
    while (h.hasNext()){
      doc = (Document) h.next();
      if (getRev().equals(doc.getRevision()) & getVer().equals(doc.getVersion())) return doc;
    }
    return doc;
  }

  public String getOrigin() { return selectedDoc().getOrigin(); }
  public String getName() { return selectedDoc().getName(); }
  public String getPath() { return selectedDoc().getPath(); }
  public String getRevision(){ return selectedDoc().getRevision(); }
  public String getVersion(){ return selectedDoc().getVersion(); }
  public String get(String metadataField) { return selectedDoc().get(metadataField); }
  //public Metadata getMetadata(){ return selectedDoc().getMetadata(); }
  public Finder getFinder() { return selectedDoc().getFinder(); }

  public Collection getAttributes() {
    Collection c = new Vector();
    DisplayField f;
    KeyValue att;
    Iterator i = getReport().getDisplayFields().iterator();
    while (i.hasNext()){
      f = (DisplayField)i.next();
      att = selectedDoc().lookupKeyValue(f.getName());
      if (null==att) att = new KeyValue(f.getName(), "");
      c.add(att);
    }
    return c;
  }
  public Collection getVisibleAttributes() {
    Collection c = new Vector();
    Iterator i = getReport().getVisibleDisplayFields().iterator();
    KeyValue att;
    String name;
    while (i.hasNext()) {
      name = ((DisplayField)i.next()).getName();
      att = selectedDoc().lookupKeyValue(name);
      if (null==att) att= new KeyValue(name, "");
      c.add(att);
    }
    return c;
  }
  public DataReport getReport() {
    try {return DataReportService.findDisplayReport(doc.get(Constants.METADATA_REPORT));}
    catch (Exception e) { e.printStackTrace(); return null; }
  }

  public String getRev() { return rev; }
  public void setRev(String s) { rev=s; }
  public String getVer() { return ver; }
  public void setVer(String s) { ver=s; }

  public String toXML() { return getDocument().toXML(); }
  public String getTreeviewScript() {
    if (null==getChildren()) return "";
    //yea, i know this should be done in jsp somewhere - <while-loop/> available?
    String parent = "foldersTree";
    String script="";
    Reference ref = new Reference();
    ref.setOrigin(getDocument().getOrigin());
    ref.setCount(1);
    try {script = getComponentTreeviewScript(ref, parent, 1);}
    catch (Exception e) { e.printStackTrace(); }
    return script;
  }

  public String getComponentTreeviewScript(Reference ref, String parent, int level) throws Exception{
    Document d = ref.getDocument();
    if (null==d) return "insDoc("+parent+", gLnk('S', '["+ref.getCount()+"] x ["+stripName(ref.getOrigin())+"]', 'javascript:undefined'));"+ln;
    if (null!=d.getChildren()) return getAssemblyTreeviewScript(ref, parent, level);
    else return getPartTreeviewScript(ref,parent);
  }
  private String getAssemblyTreeviewScript(Reference ref, String parent, int level) throws Exception {
    Document d = ref.getDocument();
    String child = "aux"+level;
    String script;
    if (1==level) script = child +" = insFld("+parent+", gFld('["+ref.getDocument().getName()+"]', 'javascript:undefined'));"+ln;
    else script = child +" = insFld("+parent+", gFld('["+ref.getCount()+"] x ["+ref.getDocument().getName()+"]', 'javascript:undefined'));"+ln;
    Iterator i = d.getChildren().iterator();
    try {
      while(i.hasNext()) script += getComponentTreeviewScript((Reference)i.next(), child, ++level);
    }
    catch (Exception e) {e.printStackTrace();}

    return script;
  }

  private String getPartTreeviewScript(Reference ref, String parent) throws Exception{
    Document d = ref.getDocument();
    if (null==d) return "insDoc("+parent+", gLnk('S', '["+ref.getCount()+"] x ["+stripName(ref.getOrigin())+"]', 'javascript:undefined'));"+ln;
    return "insDoc("+parent+", gLnk('S', '["+ref.getCount()+"] x [#"+ref.getDocument().getName()+"]', 'javascript:undefined'));"+ln;
  }

  private String stripName(String origin) {
    StringTokenizer tok = new StringTokenizer(origin,Constants.ORIGIN_DELIMITER);
    if (!tok.hasMoreTokens()) return origin;
    tok.nextToken();
    if (!tok.hasMoreTokens()) return origin;
    tok.nextToken();
    if (!tok.hasMoreTokens()) return origin;
    return tok.nextToken();
  }
  public String getReportName() { return reportName; }
  public void setReportName(String s) { reportName=s; }
  private Collection history = new Vector();

  private String rev;
  private String ver;
  private static String ln = Constants.LINE_SEPARATOR;
     */
}
