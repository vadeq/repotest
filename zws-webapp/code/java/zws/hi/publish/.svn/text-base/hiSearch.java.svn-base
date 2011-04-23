package zws.hi.publish;

import zws.Server;
import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.data.transformer.metadata.MetadataRemappingSpec;
import zws.exception.InitializationError;
import zws.hi.report.MetadataAdapter;
import zws.hi.report.PublishMetadataAdapter;
import zws.hi.report.hiReport;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.recorder.util.RecorderUtil;
import zws.report.Report;
import zws.report.ReportBase;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;
import zws.search.SearchAgentBase;
import zws.service.pen.PenQueuePlugin;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
import zws.util.AdapterCollection;
import zws.util.AdapterTreeSet;
import zws.util.AdapterVector;
import zws.util.FileNameUtil;
import zws.util.KeyValue;
import zws.util.PrintUtil;
import zws.util.StringUtil;
import zws.util.TransformerCollection;
import zws.util.TransformerTreeSet;
import zws.util.TransformerVector;
import zws.util.WaitForThreads;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class hiSearch.
 *
 * @author D Reddy
 */
public class hiSearch extends hiReport {

  public void initialize() throws Exception {
    if (null == getItems()) setItems(new Vector());
  }

  /** The test. */
  private String test = "test";

  /** The criteria. */
  private String criteria = null;

  /** The present. */
  private boolean present = true;

  /** The agent. */
  private SearchAgentBase agent = null;

  /** The repositories. */
  private Map repositories = new HashMap();

  /** The source. */
  private String source = null;

  /** The selected repositories. */
  private String[] selectedRepositories = null;

  /** The selected origins. */
  private String[] selectedOrigins = null;

  private String[] intent = null;

  private String[] target = null;

  /** The meta records. */
  List metaRecords = null;

  /** The meta report records. */
  List metaReportRecords = null;

  /** The cart meta records. */
  List cartMetaRecords = null;

  /** The selected cart origins. */
  private String[] selectedCartOrigins = null;

  /** The published origins. */
  private Map publishedOrigins = null;

  /** The pub do. */
  private publishDo pubDo = null;

  /** The queue list. */
  private List queueList = null;

  /** The publish queue. */
  private LinkedList publishQueue = null;

  /** The repostry. */
  private Repository repostry = null;

  /** The repostry clt. */
  private RepositoryService repostryClt = null;

  /** The publish report. */
  private ReportBase publishReport = null;

  /** The origin. */
  private Origin origin = null;

  /** The is first. */
  private static boolean isFirst = true;

  /** The is first. */
  private static String publishPending = "false";



  public String getPublishPending() {
    return publishPending;
  }


  public void setPublishPending(String s) {
    publishPending = s;
  }


  /**
   * Gets the pub do.
   *
   * @return the pubDo
   */
  public publishDo getPubDo() {
    return pubDo;
  }

  /**
   * Sets the pub do.
   *
   * @param pubDo the pubDo to set
   */
  public void setPubDo(publishDo pubDo) {
    this.pubDo = pubDo;
  }

  /**
   * Gets the publish queue.
   *
   * @return the publishQueue
   */
  public LinkedList getPublishQueue() {
    return publishQueue;
  }

  /**
   * Sets the publish queue.
   *
   * @param publishQueue the publishQueue to set
   */
  public void setPublishQueue(LinkedList publishQueue) {
    this.publishQueue = publishQueue;
  }

  /**
   * Gets the queue list.
   *
   * @return the queueList
   */
  public List getQueueList() {
    return queueList;
  }

  /**
   * Sets the queue list.
   *
   * @param queueList the queueList to set
   */
  public void setQueueList(List queueList) {
    this.queueList = queueList;
  }

  /**
   * Gets the criteria.
   *
   * @return the criteria
   */
  public String getCriteria() {
    return criteria;
  }

  /**
   * Sets the criteria.
   *
   * @param criteria the criteria to set
   */
  public void setCriteria(String criteria) {
    this.criteria = criteria;
  }

  public void bind() {
    if (null == repostryClt) {
      bindSearchAgent();
    }
    if (isFirst) {
      QxWebClient webClient = QxWebClient.materializeClient();
      QxContext keyCtx = materializeQxContext();
      webClient.clearKeys(keyCtx);
      webClient.exchangeKeys(keyCtx);
      isFirst = false;
    }

  }

  /**
   * Gets the report.
   *
   * @return the report
   */
  public Report getReport() {
    initializeReport();
    return publishReport;
  }

  /**
   * Initialize report.
   */
  private void initializeReport() {
    publishReport = new ReportBase();
    try {
      publishReport.initialize();
    } catch (InitializationError e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // initialise metadta fields
    publishReport.add("Name");
    publishReport.add("Number");
    publishReport.add("Rev");
    publishReport.add("Iteration");
    publishReport.add("CADName");
    publishReport.add("Release");
    publishReport.add("Location");
    publishReport.add("System");
    publishReport.add("Description");
    publishReport.add("Author");
    publishReport.add("Date");
  }

  /**
   * Bind search agent.
   */
  private void bindSearchAgent() {
    try {
      repostryClt = RepositoryClient.getClient();
      ArrayList list = (ArrayList) repostryClt.listRepositories();
      repositories = new HashMap();
      if (list != null) {
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
          String element = itr.next().toString();
          if (element.startsWith("agile")) continue;
          repositories.put(element, element);
        }
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /* (non-Javadoc)
   * @see com.zws.hi.Interactor#registerRequiredFields()
   */
  public void registerRequiredFields() {

  }

  public void registerEventHandlers() {}

  public boolean sessionIsExpired() {
    if ("authenticate".equals(getEvent())) return false;
    return super.sessionIsExpired();
  }

  public String searchCriteria() {
    try {
      metaReportRecords = null;
      if (getSelectedRepositories() != null) {
        for (int i = 0; i <= getSelectedRepositories().length - 1; i++) {
          repostry = repostryClt.findRepository(getSelectedRepositories()[i]);
          if (null != repostry) {
            agent = (SearchAgentBase) repostry.materializeSearchAgent();
            if (null != agent) {
              agent.setName(repostry.getRepositoryType());
              generateReport(agent);
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if(null == getMetaReportRecords()) setMetaReportRecords(new ArrayList());
    logFormStatus("status.found.search.results", "" + getMetaReportRecords().size());
    return "index";
  }

  public String search() {
    if (null == getCriteria() || "".equals(getCriteria().trim())) {
      logFormWarning("warn.no.criteria");
      return ctrlINDEX;
    }
    if (null == getSelectedRepositories() || 0 == getSelectedRepositories().length) {
      logFormWarning("warn.no.repository.selected");
      return ctrlINDEX;
    }
    try {
      metaReportRecords = null;
      if (getSelectedRepositories() != null) {
        for (int i = 0; i <= getSelectedRepositories().length - 1; i++) {
          repostry = repostryClt.findRepository(getSelectedRepositories()[i]);
          if (null != repostry) {
            agent = (SearchAgentBase) repostry.materializeSearchAgent();
            if (null != agent) {
              agent.setName(repostry.getRepositoryType());
              generateReport(agent);
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if(null == getMetaReportRecords()) setMetaReportRecords(new ArrayList());
    logFormStatus("status.found.search.results", "" + getMetaReportRecords().size());
    return "index";
  }

  private void generateReport(SearchAgentBase agent) {
    Collection records = null;
    try {
      MetadataRemappingSpec mapping = getMapping(agent.getName());
      ReportBase repBase = (ReportBase) getReport();
      repBase.validate(mapping);
      repBase.getAgentMappings().put(agent.getName(), mapping);
      TransformerCollection set = null;
      if (null != agent.getSortComparator()) {
        set = new TransformerTreeSet(agent.getSortComparator());
      } else {
        set = new TransformerVector();
      }
      set.setTransformer(mapping);
      agent.resetStorage();
      agent.initializeStorage(set);
      String crit = getCriteria();
      if (null == crit) crit = "";
      crit = crit.trim();
      String criteriaGrammar = crit; //"(Number='" + crit + "')";
      if (!criteriaGrammar.trim().startsWith("(")) {
        criteriaGrammar = "(Number='" + criteriaGrammar + "')";
      }
      repBase.setCriteria(criteriaGrammar); // "(name='29-*.prt')");
      repBase.add(agent);
      repBase.setAuthenticationToken(this.getAuthentication());
      repBase.generate();
      WaitForThreads w = new WaitForThreads();
      w.setSleepInterval(5000);
      w.addThread(agent.getThread());
      w.execute();
      w.getThread().join();
      while (null == repBase.getResults()) {
        continue;
      }
      records = adapt(repBase);
      if (null == getMetaReportRecords()) {
        metaReportRecords = new ArrayList();
      }
      getMetaReportRecords().addAll(records);
      updateRecordStatus();
      setItems(getMetaReportRecords());
      if (null == getItems()) setItems(new Vector());
      //logFormStatus("status.found.search.results", ""+getItems().size());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private MetadataRemappingSpec getMapping(String repository) {
    MetadataRemappingSpec mapping = new MetadataRemappingSpec();
    if ("ilink-8".equals(repository)) {
      mapping.add(new KeyValue("Number", "number"));
      mapping.add(new KeyValue("Name", "name"));
      mapping.add(new KeyValue("Iteration", "iteration"));
      mapping.add(new KeyValue("Rev", "version"));
      mapping.add(new KeyValue("Location", "container"));
      mapping.add(new KeyValue("Description", "DESC"));
      mapping.add(new KeyValue("Date", "created-on"));
      mapping.add(new KeyValue("CADName", "CADName"));
      mapping.add(new KeyValue("Author", "creator"));
      mapping.add(new KeyValue("System", "zws-repository"));
      mapping.add(new KeyValue("Release", "state"));

    } else if ("ilink".equals(repository)) {
      mapping.add(new KeyValue("Number", "Part_Number"));
      mapping.add(new KeyValue("Name", "name"));
      mapping.add(new KeyValue("Iteration", "ver"));
      mapping.add(new KeyValue("Rev", "rev"));
      mapping.add(new KeyValue("Location", "folder"));
      mapping.add(new KeyValue("Description", "Description"));
      mapping.add(new KeyValue("Date", "created-on"));
      mapping.add(new KeyValue("CADName", "name"));
      mapping.add(new KeyValue("Author", "created-by"));
      mapping.add(new KeyValue("System", "zws-repository"));
      mapping.add(new KeyValue("Release", "release-level"));
    } else if ("TC-10".equals(repository)) {
      mapping.add(new KeyValue("Number", "ItemID"));
      mapping.add(new KeyValue("Name", "ItemID"));
      mapping.add(new KeyValue("Iteration", "iteration"));
      mapping.add(new KeyValue("Rev", "Revision"));
      mapping.add(new KeyValue("Location", ""));
      mapping.add(new KeyValue("Description", "Description"));
      mapping.add(new KeyValue("Date", "CreationDate"));
      mapping.add(new KeyValue("CADName", "name"));
      mapping.add(new KeyValue("Author", "OwningUser"));
      mapping.add(new KeyValue("System", "zws-repository"));
      mapping.add(new KeyValue("Release", "ReleaseStatus"));
    }
    return mapping;
  }

  /**
   * Adapt.
   *
   * @param r the r
   * @return the collection
   */
  public Collection adapt(Report r) {
    PublishMetadataAdapter prototype = (PublishMetadataAdapter) createNewMetadataAdapter();
    if (null == prototype) return r.getResults();

    AdapterCollection c;
    if (null == getComparator()) c = new AdapterVector();
    else c = new AdapterTreeSet(getComparator());
    prototype.setMetadataFields(r.getMetadataFields());
    c.setAdapterPrototype(prototype);
    c.addAll(r.getResults());
    return c;
  }

  private void updateRecordStatus() {
    if ((null == getMetaReportRecords() || (getMetaReportRecords().size() == 0))) {
      setMetaReportRecords(null);
      setPresent(false);
    } else {
      setPresent(true);
    }
  }

  /**
   * Adds the selected origins.
   *
   * @return the string
   */
  public String addSelectedOrigins() {
    try {
      if (null == cartMetaRecords) {
        cartMetaRecords = new ArrayList();
      }
      for (int i = 0; i <= getSelectedOrigins().length - 1; i++) {

        Iterator itrMeta = metaReportRecords.iterator();
        PublishMetadataAdapter pubMetaAdapter = null;
        while (itrMeta.hasNext()) {
          pubMetaAdapter = (PublishMetadataAdapter) itrMeta.next();
          if (URLDecoder.decode(getSelectedOrigins()[i], "UTF-8").equals(pubMetaAdapter.getOrigin().toString())) {
            if (!cartMetaRecords.contains(pubMetaAdapter) && !pubMetaAdapter.isSynchronized()) {
              cartMetaRecords.add(pubMetaAdapter);
              if(pubMetaAdapter.isSynchronized()) {
                logFormStatus("status.found.search.results", pubMetaAdapter.getOrigin().getName() + " is already synchronized. ");
              }
            }
          }
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return "index";
  }

  /**
   * Removes the selected origins.
   *
   * @return the string
   */
  /*
  public String removeSelectedOrigins() {
    try {
      List tempRecords = new ArrayList();
      tempRecords.addAll(cartMetaRecords);
      Iterator itrMeta = null;
      {}//Logwriter.printOnConsole("getSelectedCartOrigins size "
          + getSelectedCartOrigins().length);
      for (int i = 0; i <= getSelectedCartOrigins().length - 1; i++) {
        itrMeta = tempRecords.iterator();
        {}//Logwriter.printOnConsole("Selected values"
            + URLDecoder.decode(getSelectedCartOrigins()[i], "UTF-8"));
        while (itrMeta.hasNext()) {
          PublishMetadataAdapter pubMetaAdapter = (PublishMetadataAdapter) itrMeta.next();
          {}//Logwriter.printOnConsole("getSelectedCartOrigins()[i]"
              + getSelectedCartOrigins()[i]);
          {}//Logwriter.printOnConsole("metaAdapter.getOrigin().toString()"
              + pubMetaAdapter.getOrigin().toString());
          if (URLDecoder.decode(getSelectedCartOrigins()[i], "UTF-8").equals(
              pubMetaAdapter.getOrigin().toString())) {
            {}//Logwriter.printOnConsole("Found a match");
            cartMetaRecords.remove(pubMetaAdapter);
            {}//Logwriter.printOnConsole("Cart Meta Recorsd size"
                + cartMetaRecords.size());
          }
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "index";
  }
  */
  /**
   * Back to search.
   *
   * @return the string
   */
  public String backToSearch() {
    return "back";
  }

  public String publish() throws Exception {
    PenQueuePlugin penQ = new PenQueuePlugin();
    QxContext userCtx = null;
    Origin publishOrigin = null;
    String processStatus = null;
    String payload = null;
    Collection originsToPublish = new Vector();
    Metadata data = null;
    try {
      QxWebClient webClient = QxWebClient.materializeClient();
      QxContext keyCtx = materializeQxContext();
      webClient.clearKeys(keyCtx);
      webClient.exchangeKeys(keyCtx);
      userCtx = RecorderUtil.startNewProcess(Names.PEN_NAMESPACE, Names.PEN_PUBLISH, "publishing parts");
      RecorderUtil.logActivity(userCtx, "PublishedBy", getAuthentication().getUsername());

      Iterator i = getChosenItems().iterator();
      while (i.hasNext()) {
        data = (Metadata) i.next();
        publishOrigin = data.getOrigin();
        originsToPublish.add(publishOrigin);
        if (null == payload) payload = publishOrigin.getName();
        else payload += ", " + publishOrigin.getName();
        RecorderUtil.logActivity(userCtx, QxContext.NAME, publishOrigin.getName());
      }
      RecorderUtil.setStatus(userCtx, Names.STATUS_PROCESSING);
      //penQ.publish(originsToPublish, "publish|promote|review", "1", "agile-sdk", null, userCtx, getAuthentication());
      userCtx.set(Names.SUMMARY, "Published from user search.");
      penQ.publish(originsToPublish, "publish", "1", "agile-sdk", null, userCtx, getAuthentication());
      processStatus = Names.STATUS_COMPLETE;
      logFormStatus("msg.item.published", payload);
      getChosenItems().clear();
    } catch (Exception e) {
      processStatus = zws.application.Names.STATUS_ERROR;
      e.printStackTrace();
      logFormError("err.unexpected", e.getMessage());
    } finally {
      RecorderUtil.setStatus(userCtx, processStatus);
      RecorderUtil.endRecProcess(userCtx, processStatus);

    }
    return "complete";
  }

  protected boolean pickItem(String s) {
    if ("".equals(s)) return false;
    Iterator i = getItems().iterator();
    Object item = null;
    Object o;
    while (null==item && i.hasNext()) {
      o = i.next();
      if(idChoosesItem(s, o)) {
        item=o;
        if (removeItemWhenChosen()) i.remove();
      }
    }
    if (null==item) return false;
    boolean isSynchronized = false;
    String itemName = ((MetadataAdapter)item).getOrigin().getName();
    isSynchronized = ((PublishMetadataAdapter)item).isSynchronized();
    if(isSynchronized) {
      logFormWarning(statALREADY_PUBLISHED, itemName);
      return false;
    }
    getChosenItems().add(item);
    return true;
  }

  public String pickAll() {
    boolean isSynchronized = false;
    String itemName  = null;
    Iterator i = getItems().iterator();
    Object item = null;
    Object o;
    while (i.hasNext()) {
      o = i.next();
      item=o;
      if (null!=item) {
        itemName = ((MetadataAdapter)item).getOrigin().getName();
        isSynchronized = ((PublishMetadataAdapter)item).isSynchronized();
        if(!isSynchronized) {
          i.remove();
          getChosenItems().add(item);
        }
      }
    }
    return ctrlINDEX;
  }

  public boolean okToPublishAll() {
    Iterator i = cpnItems.iterator();
    CPNMetadata m;
    while (i.hasNext()) {
      m = (CPNMetadata) i.next();
      if (!m.okToPublish()) return false;
    }
    return true;
  }

  public String cpnPublish() throws Exception {
    verifyAll();
    if (!okToPublishAll()) {
      logFormError("err.data.not.verified");
      return "missing-cpn-detected";
    }
    PenQueuePlugin penQ = new PenQueuePlugin();
    QxContext userCtx = null;
    String payload = null;
    Origin publishOrigin = null;
    String processStatus = null;
    Collection originsToPublish = new Vector();
    Metadata data;
    try {
      QxWebClient webClient = QxWebClient.materializeClient();
      userCtx = RecorderUtil.startNewProcess(Names.PEN_NAMESPACE, Names.PEN_PUBLISH, "publishing parts");
      userCtx.set(QxContext.PUBLISH_PENDING, getPublishPending());
      RecorderUtil.logActivity(userCtx, "PublishedBy", getAuthentication().getUsername());
      RecorderUtil.logActivity(userCtx, "Publish Pending", getPublishPending());
      QxContext keyCtx = materializeQxContext();
      webClient.clearKeys(keyCtx);
      webClient.exchangeKeys(keyCtx);
      Iterator i = getChosenItems().iterator();
      while (i.hasNext()) {
        data = (Metadata) i.next();
        publishOrigin = data.getOrigin();
        if (null == payload) payload = publishOrigin.getName();
        else payload += ", " + publishOrigin.getName();
        originsToPublish.add(publishOrigin);
        RecorderUtil.logActivity(userCtx, QxContext.NAME, publishOrigin.getName());
      }
      RecorderUtil.setStatus(userCtx, Names.STATUS_PROCESSING);

      Iterator p = cpnItems.iterator();
      CPNMetadata m;
      String name, cpn;
      String itemType = null;
      while (p.hasNext()) {
        m = (CPNMetadata) p.next();
        name = m.getName();
        itemType = FileNameUtil.getFileNameExtension(name).toLowerCase();
        cpn = m.getTargetCPN();
        if (!cpn.toLowerCase().endsWith(itemType)) cpn = cpn + "." + itemType;
        userCtx.set(partNumberAsXMLAttribute(name), "number=" + cpn + ", agile-subclass=" + lookupAgileSubclass(name));
      }
      String intent = "publish";
      if(null != getPublishPending()) {
        boolean isPublishPending = new Boolean(getPublishPending()).booleanValue();
        if(isPublishPending) {
          intent = QxContext.PUBLISH_ECPN;
          penQ.publish(originsToPublish, intent, "1", "agile-sdk", null, userCtx, getAuthentication());
          intent = QxContext.PUBLISH_PENDING;
        }
      }
      penQ.publish(originsToPublish, intent, "1", "agile-sdk", null, userCtx, getAuthentication());
      processStatus = Names.STATUS_COMPLETE;
      logFormStatus("msg.item.published", payload);
    } catch (Exception e) {
      processStatus = Names.STATUS_ERROR;
      e.printStackTrace();
      logFormError("err.unexpected", e.getMessage());
    } finally {
      RecorderUtil.setStatus(userCtx, processStatus);
      RecorderUtil.endRecProcess(userCtx, processStatus);
    }
    getCpnItems().clear();
    getChosenItems().clear();
    return "index";
  }

  private String lookupAgileSubclass(String name) {
    return "08-Asic";
  }

  private void loadItems(Map itemMap, Metadata m) {
    if (!itemMap.containsKey(m.getName())) itemMap.put(m.getName(), m);
    if (!m.hasSubComponents()) return;
    Iterator s = m.getSubComponents().iterator();
    Metadata sub;
    while (s.hasNext()) {
      sub = (Metadata) s.next();
      loadItems(itemMap, sub);
    }
  }

  private String partNumberAsXMLAttribute(String attName) {
    String s = QxContext.NAME + "-'" + attName + "'";
    s = StringUtil.name2xmlAttribute(s);
    return s;
  }

  /**
   * Materialize qx context.
   *
   * @return qxcontext
   */
  private QxContext materializeQxContext() {
    QxContext ctx = new QxContext();
    ctx.set(QxContext.SOAP_HOSTNAME, Properties.get(Names.SERVICE_FINDER_HOSTNAME));
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    return ctx;
  }

  /**
   * Gets the all process logs.
   *
   * @return the all process logs
   */
  public String getAllProcessLogs() {
    return "complete";
  }

  /**
   * Gets the published origins.
   *
   * @return the publishedOrigins
   */
  public Map getPublishedOrigins() {
    return publishedOrigins;
  }

  /**
   * Sets the published origins.
   *
   * @param publishedOrigins the publishedOrigins to set
   */
  public void setPublishedOrigins(Map publishedOrigins) {
    this.publishedOrigins = publishedOrigins;
  }

  /**
   * Gets the selected origins.
   *
   * @return the selectedOrigins
   */
  public String[] getSelectedOrigins() {
    return selectedOrigins;
  }

  /**
   * Sets the selected origins.
   *
   * @param selectedOrigins the selectedOrigins to set
   */
  public void setSelectedOrigins(String[] selectedOrigins) {
    this.selectedOrigins = selectedOrigins;
  }

  /**
   * Gets the source.
   *
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * Sets the source.
   *
   * @param source the source to set
   */
  public void setSource(String source) {
    this.source = source;
  }

  /**
   * Gets the selected cart origins.
   *
   * @return the selectedCartOrigins
   */

  public String[] getSelectedCartOrigins() {
    return selectedCartOrigins;
  }

  public void setSelectedCartOrigins(String[] cartOriginList) {
    this.selectedCartOrigins = cartOriginList;
  }

  /**
   * Gets the repositories.
   *
   * @return the repositories
   */
  public Map getRepositories() {
    return repositories;
  }

  /**
   * Sets the repositories.
   *
   * @param repositories the repositories to set
   */
  public void setRepositories(Map repositories) {
    this.repositories = repositories;
  }

  /**
   * Gets the test.
   *
   * @return the test
   */
  public String getTest() {
    return test;
  }

  /**
   * Sets the test.
   *
   * @param test the test to set
   */
  public void setTest(String test) {
    this.test = test;
  }

  /**
   * Gets the selected repositories.
   *
   * @return the selectedRepositories
   */
  public String[] getSelectedRepositories() {
    return selectedRepositories;
  }

  /**
   * Sets the selected repositories.
   *
   * @param selectedRepositories the selectedRepositories to set
   */
  public void setSelectedRepositories(String[] selectedRepositories) {
    this.selectedRepositories = selectedRepositories;
  }

  /**
   * Gets the cart meta records.
   *
   * @return the cartMetaRecords
   */
  public List getCartMetaRecords() {
    return cartMetaRecords;
  }

  /**
   * Sets the cart meta records.
   *
   * @param cartMetaRecords the cartMetaRecords to set
   */
  public void setCartMetaRecords(List cartMetaRecords) {
    this.cartMetaRecords = cartMetaRecords;
  }

  /**
   * Gets the meta records.
   *
   * @return the metaRecords
   */
  public List getMetaRecords() {
    return metaRecords;
  }

  /**
   * Sets the meta records.
   *
   * @param metaRecords the metaRecords to set
   */
  public void setMetaRecords(List metaRecords) {
    this.metaRecords = metaRecords;
  }

  /**
   * Gets the comparator.
   *
   * @return the comparator
   */
  protected Comparator getComparator() {
    return null;
  }

  /**
   * Creates the new metadata adapter.
   *
   * @return the publish metadata adapter
   */
  protected MetadataAdapter createNewMetadataAdapter() {
    return new PublishMetadataAdapter();
  }

  /**
   * Checks if is present.
   *
   * @return the present
   */
  public boolean isPresent() {
    return present;
  }

  /**
   * Sets the present.
   *
   * @param present the present to set
   */
  public void setPresent(boolean present) {
    this.present = present;
  }

  /**
   * Gets the meta report records.
   *
   * @return the metaReportRecords
   */
  public List getMetaReportRecords() {
    return metaReportRecords;
  }

  /**
   * Sets the meta report records.
   *
   * @param metaReportRecords the metaReportRecords to set
   */
  public void setMetaReportRecords(List metaReportRecords) {
    this.metaReportRecords = metaReportRecords;
  }

  /**
   * Gets the report header.
   *
   * @return the reportHeader
   */
  public String[] getReportHeader() {
    return publishReport.getMetadataFields();
  }

  /**
   * @return the intent
   */
  public String[] getIntent() {
    return intent;
  }

  /**
   * @param intent the intent to set
   */
  public void setIntent(String[] intent) {
    this.intent = intent;
  }

  /**
   * @return the target
   */
  public String[] getTarget() {
    return target;
  }

  /**
   * @param target the target to set
   */
  public void setTarget(String[] target) {
    this.target = target;
  }

  public void setNewCPNList(String[] cpns) {
    Iterator i = getCpnItems().iterator();
    CPNMetadata m;
    int idx = 0;
    while (i.hasNext()) {
      m = (CPNMetadata) i.next();
      if (!CPNMetadata.EDIT.equals(m.getMode())) continue;
      m.setTargetCPN(cpns[idx++]);
    }
  }

  public String detectMissingCPN() {
    try {
      RepositoryClient c = RepositoryClient.getClient();
      Repository sourceRepository = null;
      RepositoryStructureSource structureSource = null;
      Metadata bill;
      Collection bills = new Vector();
      QxContext ctx = new QxContext();
      ctx.set(Names.SELECT_ATTRIBUTES, "DESC");

      Collection deps = null;
      Metadata m;
      Iterator i = getChosenItems().iterator();
      while (i.hasNext()) {
        m = (Metadata) i.next();
        origin = m.getOrigin();
        sourceRepository = c.findRepository(origin.getDatasourceName());
        structureSource = sourceRepository.materializeStructureSource();
        bill = structureSource.reportBOM(ctx, origin, getAuthentication());
        bills.add(bill);
        deps = structureSource.reportDependencies(ctx, origin, getAuthentication());
        if (null != deps) bills.addAll(deps);
      }
      Repository targetRepository = c.findRepository("agile-wsx");
      RepositoryMetadataSource destinationRepository = targetRepository.materializeMetadataSource();
      Iterator b = bills.iterator();
      Map itemMap = new HashMap();
      while (b.hasNext()) {
        bill = (Metadata) b.next();
        loadItems(itemMap, bill);
      }
      Iterator k = itemMap.keySet().iterator();
      String name = null;

      Metadata data;
      CPNMetadata cpnData;
      cpnItems.clear();
      while (k.hasNext()) {
        name = (String) k.next();
        data = (Metadata) itemMap.get(name);
        cpnData = new CPNMetadata();
        cpnData.adapt(data, destinationRepository, getAuthentication());
        cpnItems.add(cpnData);
      }
    } catch (Exception e) {
      logFormError("err.unexpected", e.getMessage());
      e.printStackTrace();
      return ctrlERROR;
    }
    return "missing-cpn-detected";
  }

  public String edit() {
    String name = getID();
    Iterator i = getCpnItems().iterator();
    CPNMetadata m;
    //int idx=0;
    while (i.hasNext()) {
      m = (CPNMetadata) i.next();
      if (!name.equals(m.getName())) continue;
      m.toggleEditMode();
    }
    return "missing-cpn-detected";
  }

  public String editAll() {
    Iterator i = getCpnItems().iterator();
    CPNMetadata m;
    while (i.hasNext()) {
      m = (CPNMetadata) i.next();
      if (CPNMetadata.SAVED.equals(m.getMode())) m.toggleEditMode();
    }
    return "missing-cpn-detected";
  }

  public String verifyCPN() {
    String name = getID();
    Iterator i = getCpnItems().iterator();
    CPNMetadata m;
    while (i.hasNext()) {
      m = (CPNMetadata) i.next();
      if (!name.equals(m.getName())) continue;
      try {
        m.verifyCPN();
        String stat = m.getStatusDescription();
        if (CPNMetadata.CPN_MISSING.equals(m.getStatusCPN())) logFormError("msg.item.verified", getID(), stat);
        else if (CPNMetadata.CPN_ERROR.equals(m.getStatusCPN())) logFormError("msg.item.verified", getID(), stat);
        else if (CPNMetadata.CPN_TEMPORARY.equals(m.getStatusCPN())) logFormWarning("msg.item.verified", getID(), stat);
        else logFormStatus("msg.item.verified", getID(), stat);
      } catch (Exception e) {
        logFormError("err.unexpected", e.getMessage());
        return ctrlERROR;
      }
    }
    return "missing-cpn-detected";
  }

  public String verifyAll() {
    try {
      Iterator i = cpnItems.iterator();
      CPNMetadata m;
      while (i.hasNext()) {
        m = (CPNMetadata) i.next();
        m.verifyCPN();
      }
    } catch (Exception e) {
      logFormError("err.unexpected", e.getMessage());
    }
    return "missing-cpn-detected";
  }

  public String cpnSearch() {
    try {
      searchResultsCPN = null;
      if (null == getCriteriaCPN() || "".equals(getCriteriaCPN())) return "missing-cpn-detected";

      RepositoryClient client = RepositoryClient.getClient();
      Repository target = client.findRepository("agile-wsx");
      RepositoryMetadataSource source = target.materializeMetadataSource();
      Collection metadataList = source.searchLatest(new QxContext(), getCriteriaCPN(), getAuthentication());
      //PrintUtil.print(metadataList);
      if (null != metadataList) {
        Iterator i = metadataList.iterator();
        searchResultsCPN = new Vector();
        Metadata m;
        while (i.hasNext()) {
          m = (Metadata) i.next();
          searchResultsCPN.add(m.getName());
        }
      }
    } catch (Exception e) {
      logFormError("Search ERROR! " + e.getMessage());
      e.printStackTrace();
    }
    return "missing-cpn-detected";
  }

  public Collection getSearchResultsCPN() {
    return searchResultsCPN;
  }

  public void setSearchResultsCPN(Collection c) {
    searchResultsCPN = c;
  }

  public Collection getCpnItems() {
    return cpnItems;
  }

  public String getCriteriaCPN() {
    return criteriaCPN;
  }

  public void setCriteriaCPN(String s) {
    criteriaCPN = s;
  }

  Collection searchResultsCPN = null;

  Collection cpnItems = new Vector();

  String criteriaCPN = null;
}

