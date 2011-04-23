package com.zws.hi.baseline;

import com.zws.hi.hiItem;

/**
 * <p>Title: DesignState</p>
 * <p>Description: Design Compression Technology</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Zero Wait-State</p>
 * @author not attributable
 * @version 1.0
 */

public abstract class hiBaseline extends hiItem {
}
/*
  private hiDocumentList fileListInteractor;
  private String name;
  private String filename;
  private String location;
  private String filelocation;
  private String fileRevision;
  private String fileVersion;
  private String fileBranch;
  
  private Folder rootFolder = null;
  private String fileindex;
  private String linkScript = TreeNode.DEFLINK;
  private BaselineService service;
  private Baseline baseline;
  private Document doc;
  private boolean clearSearch = true;

  public hiBaseline(){
  	super();
	service = BaselineService.getService();
  }

  public void registerRequiredFields(){
    require("add", "name");
  }

  public void refresh() {
    filename = "";
    filelocation = "";
  }
  
  public void startRequest() throws Exception {
  	if(STATE_CREATE_NEW.equals(getHiState()) && baseline != null){
  		baseline.setName(getName());
  		baseline.setLocation(getLocation());	
  	}
  }

  public void bind(){
    if (null==fileListInteractor){
      fileListInteractor = new hiDocumentList();
  	  // obsoleted fileListInteractor.setActiveReportName(Properties.get(Properties.baselineActiveReport));
    }      
    fileListInteractor.setSession(getSession());
    String id = getID();
    try{
		if (getID() == null || getID().trim().length() == 0) {
			if(baseline == null)
				baseline = new Baseline();
		}else{
			if(baseline == null){
				baseline = service.find(getID());
			}else if(!getID().equals(baseline.getName())){
				fileListInteractor = new hiDocumentList();
      	// obsoleted fileListInteractor.setActiveReportName(Properties.get(Properties.baselineActiveReport));
				doc = null;
				baseline = service.find(getID());
			}
			
		}
    }catch(Exception ex){
    	ex.printStackTrace();
    }
    /*1) find  (ID)
      a) (!baseline) -> find
      b) already cached -> 
      c) find different
    2) create (!ID)
      a) (baseline)
      b) (!baseline)* -/
    
    
    
    / - *if (getID() == null) {
      baseline = new Baseline();
       
      return;
    }else if(!getID().equals(baseline.getName())){
        fileListInteractor = new hiDocumentList();
       doc = null;
    }
    try{ baseline = service.find(getID()); } catch (Exception e){e.printStackTrace();}* -/
  }


  public void render() {
    if (null==baseline) {
		setName("");
		setLocation("");
    }else{
    	setName(baseline.getName());
    	setLocation(baseline.getLocation());
    }
  }

  public DataReport getReport() { return fileListInteractor.getReport(); }
  public void setFilename(String str) { filename = str; }
  public String getFilename() { return filename; }
  public void setFileindex(String str) { fileindex = str; }
  public String getFileindex() { return fileindex; }

  public void setFileRevision(String str) { fileRevision = str; }
  public String getFileRevision() { return fileRevision; }
  public void setFileVersion(String str) { fileVersion = str; }
  public String getFileVersion() { return fileVersion; }
  public void setFileBranch(String str) { fileBranch = str; }
  public String getFileBranch() { return fileindex; }
  
  
  public void setLinkScript(String str) { linkScript = str; }
  public String getLinkScript() { return linkScript; }
  public void setFilelocation(String str) { filelocation = str; }
  public String getFilelocation() { return filelocation; }
  public void setName(String s) { setID(s); }
  public String getName() { return getID(); }
  public void setLocation(String s) { location = s; }
  public String getLocation() { return location; }
  public String getCriteria() {return fileListInteractor.getCriteria();}
  public void setCriteria(String x) {fileListInteractor.setCriteria(x);}
  
  public Collection getLocations(){
  	Folder fld = null;
  	try{
		service.cacheFolders();
		
  	}catch(Exception ex){
  		ex.printStackTrace();
  	}
  	
	return fld.getChildernAsStrings();
  }
  
  public Collection getFiles() { return baseline.getFiles(); }
  public String addFile() {
    if (null==filename || filename.trim().length()<1) return ctrlVIEW;

    Fileentry file = new Fileentry();
    file.setName(filename);
    file.setOrigin(filelocation);
    file.setRevision(fileRevision);
    file.setVersion(fileVersion);
    file.setBranch(fileBranch);
    baseline.addFile(file);
    //org.apache.struts.action.ActionServlet servlet = this.getServlet();
    try{
      service.update(baseline);
    }catch(Exception e){

      e.printStackTrace();
    }
    return ctrlVIEW;
  }
  public String deleteFile() {
    if (null==filename || filename.trim().length()<1) return ctrlVIEW;
    //KeyValue file = new KeyValue(filename, filename);
      Fileentry file = baseline.getFile(filename);
      if(file != null)
        file.setDeleted(true);
    try{
      service.update(baseline);
    }catch(Exception e){

      e.printStackTrace();
    }
    return ctrlVIEW;
  }
  
  
  private BaselineFinder createFinder(){
	BaselineFinder finder = new BaselineFinder();
	IntralinkSource ds = this.service.getDataSource();
	finder.setDataSource(ds);
	finder.setBaselineName(baseline.getName());
	return finder;
  }
  
  
  synchronized public String download() {
  	
	{} //System.out.println("download was called");
/-*	Finder finder = (Finder)doc.getFinder();
	try {
	  finder.find(true);
	  hiFileDownloader.streamForViewing(getHttpResponse(), finder); return ctrlLIST;
	}
	catch (Exception e) { e.printStackTrace(); return this.ctrlSYSTEM_ERROR; }
*-/	
	BaselineFinder finder = createFinder();
	Iterator filesI = baseline.getFiles().iterator();
	while(filesI.hasNext()){
		String filename = ((Fileentry)filesI.next()).getName();
		Document doc = new Document();
		doc.setName(filename);
		finder.addDoc(doc);
	}
	
	{} //System.out.println("finder created");
	//String binary = finder.getBinary();
	try{
		
		finder.find(true);
		com.zws.hi.util.hiFileDownloader.streamForViewing(getHttpResponse(), finder); 
		return ctrlOK;

	}catch(Exception e){
		e.printStackTrace();
		return ctrlSYSTEM_ERROR;
	}

  }

  public String getActiveReportName() {
    return fileListInteractor.getActiveReportName();
  }
  public void setActiveReportName(String s) {
    fileListInteractor.setActiveReportName(s);
  }

  public boolean isBound() { return null!=baseline; }

   
  public String save(){
    baseline.setName(getName());
	//Folder loadedFolder = Folder.getFolderFromPath(rootFolder, location, "/");
    //baseline.setLocation(loadedFolder.getFQL("/"));
	baseline.setLocation(getLocation());
    try {
      service.save(baseline);
      //if (null!= getID() && getID().equals(keyMEMBER)) getMember().setUser(user);
      //logFormStatus("msg.item.saved", getName());
      return ctrlOK;
    }
    catch (Exception e) {
      e.printStackTrace();
      logFormError(keyRUNTIME_ERROR);
      return ctrlSYSTEM_ERROR;
    }
  }

  public hiDocumentList getFileListInteractor() { return fileListInteractor; }
  public void validateInputFields() {
    //if ("add".equals(getEvent()) && !getNewPassword().equals(getConfirmationPassword()))
    //  logFormError("err.mismatched.password");
  }

  public String add(){
    baseline.setName(getName());
 	setID(getName());
    baseline.setLocation(getLocation());
    try {
      service.add(baseline);
      setHiState(STATE_NORMAL);
      //logFormStatus("msg.item.added", getFirstName() + " " + getLastName());
      return ctrlVIEW;
    }
    catch (DuplicateEntry ex) {
      //logFormError("err.username.not.available", getUsername());
      return ctrlERROR;
    }
    catch (Exception e) {
      e.printStackTrace();
      //logFormError(this.keyRUNTIME_ERROR);
      return ctrlSYSTEM_ERROR;
    }
  }
  
public String createNew(){
	setName(null);
	setLocation(null);
	baseline=null;
	return ctrlOK;
 }

  public String getTreeviewScript() {

    TreeBuilder tb = new TreeBuilder(BaselineTreeContentHandler.class);
    linkScript = "item.do?event=addFile&ID=" + getID();
    String script = "";
    TreeRoot tree = null;
    //costructing tree from file
    /*try{
      tree = tb.createTreeFromFile("c:\\testtree.xml", linkScript);
      script = tree.toString();
    }catch(TreeBuilderException tbe){
      this.logFormError("err.treebuildeer.content.handler");
    }*-/
    //costructing tree from string
    /-*String xml = "<?xml version=\"1.0\" ?>" +
        "<metadata name=\"xxx-yyy-zzz\" origin=\"hell/10circle/fire\">" +
        "<revision value=\"0\" origin=\"hell/10circle/fire/0\">" +
        "<version value=\"3.0.0\" origin=\"hell/10circle/fire/0.1.0.0\"/>" +
        "</revision>" +
        "</metadata>";
    TreeRoot tree = tb.createTreeFromString(xml);*-/

    String defLink = TreeNode.DEFLINK;
    String link;
    if(doc == null)
      tree = new TreeRoot("none", defLink);
    else{
      link =  linkScript + "&filename=" + doc.getName() + "&filelocation=" +  doc.getOrigin() + 
                                         "&fileRevision=" + doc.getRevision() + "&fileVersion=" + doc.getVersion() +
                                         "&fileBranch=" + doc.get("branch");
      tree = new TreeRoot(doc.getName() + " Branch " + doc.get("Branch") +
				" Revision " + doc.get("revision") + " Version " + doc.get("version"), link);
                                        
      Collection histC = doc.getHistory();
      if(histC != null){
      	Iterator history = histC.iterator();

      	while (history.hasNext()) {
        	link = defLink;
        	String revision = "";
        	String branch = "";
        	String newRevision;
        	String newBranch;
        	TreeFolder rev = null;
        	TreeFolder br = null;
        	Document docInst = (Document) history.next();
        	newBranch = docInst.get("branch");
        	if(newBranch == null){
        		newBranch="";
        		br = tree;
        	}
			if (newBranch.compareToIgnoreCase(branch) != 0) { //new revision
				br = new TreeFolder("branch " + newBranch, defLink);
				tree.addNode(br);
			}
        	newRevision = docInst.get("revision");
			if(newRevision == null){
				newRevision="";
				rev = br;	
			}
        	if (newRevision.compareToIgnoreCase(revision) != 0) { //new revision
          		rev = new TreeFolder("revision " + newRevision, defLink);
          		br.addNode(rev);
        	}
        	link = linkScript + "&filename=" + doc.getName() + "&filelocation=" + docInst.getOrigin() + 
                "&fileRevision=" + newRevision + "&fileVersion=" + docInst.get("version") +
                "&fileBranch=" + docInst.get("branch");
        	TreeLeaf ver = new TreeLeaf("Version " + docInst.get("version"), link);
        	rev.addNode(ver);
        	//link + "&filename="  + metadata + "&filelocation=" + name

      	}
      }
    }


    return tree.toString();
  }

  public String search() throws Exception {   	
  	// obsoloeted fileListInteractor.setActiveReportName(Properties.get(Properties.baselineActiveReport));
  	fileListInteractor.search(); 
  	
  	return ctrlVIEW; 
  }
  public Collection getItems() { return fileListInteractor.getItems(); }
  public String populate() {
    Collection values =  fileListInteractor.getItems();
    //TreeSet set = new TreeSet(values);
    //set.comparator()
    
    if(values.size() > 0){
      Object[] docs = values.toArray();
      hiDocument hiDoc = (hiDocument)docs[Integer.valueOf(getFileindex()).intValue()];

      //hiDocument hiDoc = (hiDocument)fileListInteractor.getItems().toArray()[Integer.valueOf(getFileindex()).intValue()];
      doc = hiDoc.getDocument();
    }
    //String documentHistory = doc.asTree;

    return ctrlVIEW;
  }
  
  
  public String updateCache(){
	try{
		service.cacheFolders();
		rootFolder = service.findRootFolder();
		setLocation("");
	}catch(Exception e){
		{} //System.out.println("Unable to chache folders");
	}
  	
  	
  	return ctrlOK;
  }
  
  public String renderFolder(){
  	
  	  {} //System.out.println ("location : " + location);
  	  Folder loadedFolder = Folder.getFolderFromPath(rootFolder, location, "/");
  	  Folder fl = null;
  	  try{
  	  	fl = service.findFolderByPK(loadedFolder.getPrimaryKey());	
  	  }catch(Exception ex){
  	  	ex.printStackTrace();
  	  }  
	  loadedFolder.setChildren(fl.getChildren());
     
	  return ctrlOK;
	}
  
  public String getFolderviewScript() {

	  TreeBuilder tb = new TreeBuilder(BaselineTreeContentHandler.class);
	  //linkScript = "add.do?event=renderFolder";
	  linkScript = "javascript:setFolder(\"";
	  String script = "";
	  TreeRoot tree = null;
          
	  //costructing tree from file
	  /*try{
		tree = tb.createTreeFromFile("c:\\testtree.xml", linkScript);
		script = tree.toString();
	  }catch(TreeBuilderException tbe){
		this.logFormError("err.treebuildeer.content.handler");
	  }*-/
	  //costructing tree from string
	  /-*String xml = "<?xml version=\"1.0\" ?>" +
		  "<metadata name=\"xxx-yyy-zzz\" origin=\"hell/10circle/fire\">" +
		  "<revision value=\"0\" origin=\"hell/10circle/fire/0\">" +
		  "<version value=\"3.0.0\" origin=\"hell/10circle/fire/0.1.0.0\"/>" +
		  "</revision>" +
		  "</metadata>";
	  TreeRoot tree = tb.createTreeFromString(xml);*-/
	  Folder root = null;
	  


	  String defLink = TreeNode.DEFLINK;
	  String link;
	  String path = "";
	  if(rootFolder == null){
	  	try{
	  		rootFolder = service.findRootFolder(); //get root folder from cache
	  	}catch(Exception ex){
	  		ex.printStackTrace();	
	  	}
  	  }
	  	
	  if(rootFolder == null) //nothing in cache
		tree = new TreeRoot("update cache", "");
	  else{
	  	path = rootFolder.getName();
      	//link =  linkScript + "&location=" + path;
      	link = linkScript + path + "\")";        
	  	tree = new TreeRoot(rootFolder.getName(), link);                            
	  	renderLevel(tree, rootFolder, linkScript, path);
  	  } 
		
	  return tree.toString();

  }
   private void renderLevel(TreeFolder parent, Folder fParent, String script, String path){
	if(fParent.getChildren()!=null){                                
     	Iterator children = fParent.getChildren().iterator();

	
	    while (children.hasNext()) {  		
		  Folder child = (Folder) children.next();
		  String fqPath = path + "/" + child.getName();
		  //String link = script + "&location=" + fqPath;
		  String link = script + fqPath + "\")";
		  TreeFolder ch = new TreeFolder(child.getName(), link);
		  parent.addNode(ch);
		  renderLevel(ch, child, script, fqPath);
		}
	 }
   	
   }

	


  public Collection getReportNames() { return fileListInteractor.getReportNames(); }
}

*/