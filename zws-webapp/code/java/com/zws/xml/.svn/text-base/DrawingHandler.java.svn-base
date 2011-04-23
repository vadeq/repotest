package com.zws.xml;

import com.zws.application.Config;
import com.zws.application.Constants;
import com.zws.domo.document.Document;
import com.zws.functor.PDFGenerator;
import com.zws.functor.util.file.FileMetadataSetter;
import com.zws.service.repository.RepositoryService;
import com.zws.util.FileNameUtil;

import java.io.File;
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class DrawingHandler extends DefaultHandler {
  private Map components = new HashMap();
  public boolean generatePDFs = false;

  public Map getResults(){ return getComponents(); }

  public Map getComponents() { return components; }
  public boolean getGeneratePDFs() {return generatePDFs; }
  public void setGeneratePDFs(boolean b) { generatePDFs = b; }

  public void startElement (String uri, String name, String qName, Attributes atts) {
    if ( qName.equalsIgnoreCase("drawing") || qName.equalsIgnoreCase("object")) add(atts);
  }
  public void endDocument (){
    Map component;
    Map latestComponent;
    Collection latestComponents = new Vector();
    Iterator i = getResults().values().iterator();
    while (i.hasNext()) {
      component = (Map)i.next();
      latestComponent = findLatest(component);
      latestComponents.add(latestComponent);
    }

    boolean createPDF;
    boolean addToDatabase = false;
    boolean updateDatabase = false;
    String newDocURI;
    Map atts;
    Document storedDoc, newDoc;
    String newDocRev=null, newDocVer=null, storedDocRev=null, storedDocVer=null, pdfPath=null, newDocName=null, newDocPDFName=null;
    i = latestComponents.iterator();
    while (i.hasNext()) {
      createPDF = getGeneratePDFs();
      atts = (Map) i.next();
      try {
        newDocName=atts.get("name").toString();
        newDocRev = atts.get(Config.getProperty(Config.ATTRIBUTE_REVISION)).toString();
        newDocVer = atts.get(Config.getProperty(Config.ATTRIBUTE_VERSION)).toString();
        newDocPDFName=FileNameUtil.pdfFileName(newDocName);
        pdfPath = Config.getProperty(Config.REPOSITORY_PDF);
        newDocURI = pdfPath + "\\" + newDocPDFName;
        newDoc = loadDocument(newDocPDFName, newDocName, pdfPath, atts);
        addToDatabase = false;
        updateDatabase = false;
        storedDoc = RepositoryService.findDocument(newDocPDFName);
        if (storedDoc == null) addToDatabase=true;
        else {
          storedDocRev = storedDoc.get(Config.getProperty(Config.ATTRIBUTE_REVISION));
          storedDocVer = storedDoc.get(Config.getProperty(Config.ATTRIBUTE_VERSION));
/*
          //see if there is any update
          if (storedDocRev.equals(newDocRev) && storedDocVer.equals(newDocVer)) createPDF=false;

          //see of latest version of doc has been deleted from repository
          if(!isSameOrLaterRevision(newDocRev, storedDocRev)) createPDF=false; //earlier revision
          else if (storedDocRev.equals(newDocRev) && isLaterVersion(storedDocVer, newDocVer)) createPDF = false;
*/
          //see if there is a newer version of doc than what is already stored
          if(!isSameOrLaterRevision(storedDocRev,newDocRev)) updateDatabase=true; //earlier revision
          else if (storedDocRev.equals(newDocRev) && isLaterVersion(newDocVer, storedDocVer)) updateDatabase = true;
        }

        File generatedFile = new File(newDocURI);
        if (getGeneratePDFs() && (updateDatabase || addToDatabase)) createPDF=true;
        else createPDF=false;

        if (getGeneratePDFs() && !createPDF) {
          //also regenerate pdf if corrupt or missing
          if (!generatedFile.exists() ) createPDF = true; //pdf was deleted
          long fileSize = generatedFile.length();
          if (0 < fileSize && fileSize < 500 ) { //found corrupted pdf (sort of-guessing by file size)
            generatedFile.delete();
            createPDF = true;
          }
        }

        if(createPDF) {
          createPDF(newDocPDFName, generatedFile.getAbsolutePath());
          generatedFile = new File(newDocURI);
          FileMetadataSetter setter = new FileMetadataSetter();
          setter.setFile(generatedFile);
          setter.setDocument(newDoc);
          setter.execute();
        }
        if (addToDatabase) RepositoryService.addDocument(newDoc);
        else if (updateDatabase) RepositoryService.updateDocument(newDoc);
      }
      catch (Exception e) { e.printStackTrace(); }
    }
  }

  private static void createPDF(String pdfName, String outputFileName) {
    PDFGenerator pdfGen = new PDFGenerator();
    pdfGen.setObjectName(pdfName);
    pdfGen.setOutputFileName(outputFileName);
    pdfGen.setUsername(Config.getProperty(Config.USERNAME_INTRALINK));
    pdfGen.setPassword(Config.getProperty(Config.PASSWORD_INTRALINK));
    pdfGen.setProCommMessageExecutable(Config.getProperty(Config.EXE_PRO_COMM_MESSAGE));
    pdfGen.setEXEToolkitEnv(Config.getProperty(Config.EXE_PROI_TOOLKIT_ENVIRONMENT));

    try { pdfGen.execute(); }
    catch (Exception e) { e.printStackTrace(); }
  }

  private Document loadDocument(String name, String sourceDocumentName, String path, Map atts) {
    Document d = new Document();
    d.setName(name);
    d.set(Constants.METADATA_PATH, path);
    d.set(Constants.METADATA_SOURCEFILE_DATASOURCE, "obsolete");
    d.set(Constants.METADATA_SOURCEFILE_NAME, sourceDocumentName);
    d.set(Constants.METADATA_SOURCEFILE_EXTENTION, FileNameUtil.getFileNameExtention(sourceDocumentName));
    d.set(Constants.METADATA_SOURCEFILE_TYPE, FileNameUtil.lookupFileType(sourceDocumentName));
    Iterator i = atts.keySet().iterator();
    String att;
    while (i.hasNext()) {
      att = i.next().toString();
      if ("name".equalsIgnoreCase(att)) continue;
      d.set(att, atts.get(att).toString());
    }
    return d;
  }

  private Map findLatest(Map componentSet) {
    Iterator i = componentSet.values().iterator();
    Map component = null;
    String revision;
    Map latestComponent=null;
    String latestRevision = null;
    String version=null;
    String latestVersion=null;

    while (i.hasNext()) {
      component = (Map) i.next();
      if (null==latestComponent) {
        latestComponent = component;
        latestRevision = component.get(Config.getProperty(Config.ATTRIBUTE_REVISION)).toString();
        latestVersion = component.get(Config.getProperty(Config.ATTRIBUTE_VERSION)).toString();
        if (null==latestRevision) latestRevision = "-";
        if (null==latestVersion) latestVersion = "0";
      }
      else {
        revision = component.get(Config.getProperty(Config.ATTRIBUTE_REVISION)).toString();
        version = component.get(Config.getProperty(Config.ATTRIBUTE_VERSION)).toString();
        if (null==revision) revision = "-";
        if (null==version) version = "0";
        if(isSameOrLaterRevision(revision, latestRevision)) {
          if (revision.equals(latestRevision) && isLaterVersion(latestVersion, version)) continue;
          latestComponent = component;
          latestRevision = revision;
          latestVersion = version;
        }
      }
    }
    return latestComponent;
  }

  private void add(Attributes atts) {
    String name = atts.getValue("name");
    Map m = getComponentSet(name);

    //use a functor to create key
    String key = getKey(name , atts.getValue(Config.getProperty(Config.ATTRIBUTE_REVISION)), atts.getValue(Config.getProperty(Config.ATTRIBUTE_VERSION)));
    //once functor is in place, eliminate the key uniqifier:
    int i=0;
    String k = key;
    while (m.containsKey(k)) k = key + String.valueOf(++i);
    key = k;
    //end key uniqifier
    m.put(key, mapAttributes(atts));
    components.put(name, m);
  }

  private Map getComponentSet(String key) {
    Map m  = (Map)components.get(key);
    if (null==m) m = new HashMap();
    return m;
  }

  private boolean isSameOrLaterRevision(String later, String earlier) {
    if (earlier.equals(later)) return true;
    if (earlier.equals("-")) return true;
    if (earlier.length() < later.length()) return true;
    if (earlier.length() > later.length()) return false;
    if (earlier.compareTo(later) < 0 ) return true;
    return false;
  }

  private boolean isLaterRevision(String later, String earlier) {
    if (earlier.equals("-") && !later.equals("-")) return true;
    if (earlier.length() < later.length()) return true;
    if (earlier.length() > later.length()) return false;
    if (earlier.compareTo(later) < 0 ) return true;
    return false;
  }

  private boolean isLaterVersion(String later, String earlier) {
    int e, l;
    e = Integer.valueOf(earlier).intValue();
    l = Integer.valueOf(later).intValue();
    return l > e;
  }

  private Map mapAttributes(Attributes atts) {
    Map a = new HashMap();
    for (int idx = 0; idx < atts.getLength(); a.put(atts.getLocalName(idx), atts.getValue(idx++)));
    return a;
  }

  private String getKey (String x, String y, String z) { return x + "." + y + "." + z; }

}
