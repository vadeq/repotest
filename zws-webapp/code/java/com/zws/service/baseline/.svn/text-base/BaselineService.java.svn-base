package com.zws.service.baseline;

import zws.database.DB;
import zws.database.Database;
import zws.exception.*;

import zws.IntralinkAccess;
import zws.application.Names;
import zws.application.Properties;
import zws.security.Authentication;

import com.zws.domo.baseline.Baseline;
import com.zws.domo.baseline.Fileentry;
import com.zws.exception.*;
//import com.zws.service.Database;
import com.zws.util.UniqueID;

import java.sql.*;
import java.util.*;


public class BaselineService {
  private static BaselineService service = null; //singleton
  private static String sqlDataSourceName = Properties.get(Properties.DesignStateDatabase);
  //private static String intralinkDataSourceName = Properties.get(Properties.baselineDatasource);
  private UniqueID idgen = new UniqueID();
  private BaselineService() {
      
  }
  public static BaselineService getService(){ if (service==null) service=new BaselineService(); return service; }

  public Baseline find(String name) throws EntryNotFound, Exception {
    Baseline baseline = null;
    //Connection connection = Database.connect(sqlDataSourceName);
    //Statement statement = connection.createStatement();
    String sql = "SELECT * FROM baseline WHERE name='"+ name +"'";
    PreparedStatement s = database().prepareStatement(sql);
    ResultSet resultSet = database().executeQuery(s);
    //ResultSet resultSet = statement.executeQuery("SELECT * FROM baseline WHERE name='"+ name +"'");

    if (resultSet.next()) baseline = unmarshallBaseline(resultSet);
    resultSet.close();
    if(baseline == null){
	    //Database.release(connection);
      throw new EntryNotFound("unable to find baseline " + name);
    }
    
    sql = "SELECT * FROM baselinefile WHERE baseline_fk='"+ baseline.getPrimaryKey() +"'";
    s = database().prepareStatement(sql);
    resultSet = database().executeQuery(s);
    //resultSet = statement.executeQuery("SELECT * FROM baselinefile WHERE baseline_fk='"+ baseline.getPrimaryKey() +"'");
    while(resultSet.next()){
      Fileentry file = unmarshallBaselinefile(resultSet);
      baseline.addFile(file);
    }
    //Database.release(connection);
    return baseline;
  }

  public Collection findAll() throws Exception{
    Baseline baseline = null;
    //Connection connection = Database.connect(sqlDataSourceName);
    //Statement statement = connection.createStatement();
    String sql = "SELECT * FROM baseline";
    PreparedStatement s = database().prepareStatement(sql);
    ResultSet resultSet = database().executeQuery(s);
    //ResultSet resultSet = statement.executeQuery("SELECT * FROM baseline");
    ArrayList entries = new ArrayList();
    ResultSet fileSet=null;
    while (resultSet.next()) {
       baseline = unmarshallBaseline(resultSet);
       entries.add(baseline);
       sql = "SELECT * FROM baselinefile WHERE baseline_fk='"+ baseline.getPrimaryKey() +"'";
       s = database().prepareStatement(sql);
       fileSet = database().executeQuery(s);

       //Statement fileSt = connection.createStatement();
       //ResultSet fileSet = fileSt.executeQuery("SELECT * FROM baselinefile WHERE baseline_fk='"+ baseline.getPrimaryKey() +"'");
       while(fileSet.next()){
         Fileentry file = unmarshallBaselinefile(fileSet);
         baseline.addFile(file);
       }
      fileSet.close();
    }
    resultSet.close();
    //Database.release(connection);
    return entries;
  }


  public Baseline merge(Baseline b, Authentication id) throws DuplicateEntry, InvalidEntry, Exception {
    if(b == null && b.getName()== null && b.getName().trim().length() < 1) throw new InvalidEntry("Baseline");

    Baseline bDb = null;
    try { bDb = find(b.getName()); }
    catch(EntryNotFound enf){ throw new InvalidEntry(b.getName()); }	

    b.setPrimaryKey(bDb.getPrimaryKey());
    Iterator files = bDb.getFiles().iterator();
    Fileentry file; 
    while(files.hasNext()){
      file = (Fileentry)files.next();
      if (null==b.getFile(file.getName())) b.addFile(file);
    }
  	save(b, id);
  	return b;
  	 
  }
  
  public void save(Baseline b, Authentication id) throws SQLException,  Exception {
    try { delete(b.getName(), id); }
    catch(EntryNotFound enf){ }
    add(b, id);
    b.setDirty(false);
  }

  public synchronized Baseline delete(String name, Authentication id) throws EntryNotFound, Exception, SQLException {
      /*
    if (null==name) throw new NullPointerException("Tried to delete using null name!");
    Baseline b;
    //Connection connection = null;
    b = find(name);
    connection = Database.connect(sqlDataSourceName);
    deleteFilelist(b, connection);
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("DELETE FROM baseline WHERE baseline_pk='"+b.getPrimaryKey()+"'");
    Database.release(connection);
//    intralinkUpdate(b, true);
    String serverName = Properties.get(Names.CENTRAL_SERVER);
    String sourceName = Properties.get(Names.DEFAULT_DATASOURCE_NAME);
    IntralinkAccess.getAccess().deleteBaseline(serverName, sourceName, name, id);
    return b;
    */
      return null;
  }

  public synchronized void add(Baseline b, Authentication id) throws DuplicateEntry, InvalidEntry, Exception, SQLException {
      /*
    if(b == null && b.getName()== null && b.getName().trim().length() < 1) throw new InvalidEntry("Baseline");
    Connection connection = null;
    try { find(b.getName()); throw new DuplicateEntry(b.getName()); }
    catch(EntryNotFound enf){}
    
    b.setPrimaryKey(idgen.generateID());
    try{
     	connection = Database.connect(sqlDataSourceName);
     	Statement statement = connection.createStatement();
     	String sql = "insert INTO baseline VALUES(";
     	sql += "'"+b.getPrimaryKey()+"',";
     	sql += "'"+b.getName().trim()+"',";
     	sql += "'"+b.getLocation().trim()+"')";
     	ResultSet resultSet = statement.executeQuery(sql);
     }
    catch(SQLException sqle){ sqle.printStackTrace(); }
  	addFilelist(b, connection);
    Database.release(connection);
    String serverName = Properties.get(Names.CENTRAL_SERVER);
    String sourceName = Properties.get(Names.DEFAULT_DATASOURCE_NAME);
    String release = Properties.get("default-baseline-release-level");
    //String release = ;
    String location="Root Folder/Baselines";
    IntralinkAccess.getAccess().createBaseline(serverName, sourceName, b.getName(), location, release, b.getOrigins(serverName, sourceName), id);
    */
  }
  
  
  private void intralinkUpdate(Baseline b, boolean deleteOnly){
  return;
  /*
    File  p, d = null , f = null;  
    
    try{
        DeleteBaseline deleteBaselineFunctor = new DeleteBaseline();
        deleteBaselineFunctor.setBaselineName(b.getName());
      
        //String intralinkDataSourceName = Properties.get(Properties.baselineDatasource);
        //IntralinkSource ds = (IntralinkSource)DataSourceService.find(intralinkDataSourceName);
   
		IntralinkSource ds = getDataSource();
        //get these from the datasource
        deleteBaselineFunctor.setUsername(ds.getUsername());
        deleteBaselineFunctor.setPassword(ds.getPassword());
        deleteBaselineFunctor.setEXEToolkitEnv(ds.getEXEToolkitEnv());
        deleteBaselineFunctor.execute();
        
        if(deleteOnly)
            return;
        
        String path = Properties.get(Properties.baselineToolkit) ;
        p = new File(path);
        
//        d =  File.createTempFile ("work", null, p);
        
        d = new File (p.getAbsolutePath() + File.separator + "work_" + AddBaseline.getNewCount());
        d.mkdir();
        
        
        f  = new File(d.getAbsolutePath(), "files.txt");
        f.createNewFile();
        
        FileWriter fw = new FileWriter(f);
        Iterator  files = b.getFiles().iterator();
        while(files.hasNext()){
            Fileentry  fileEntry = (Fileentry)files.next();
            if(!fileEntry.isDeleted()){
                String fileString = fileEntry.getName() + "," + fileEntry.getBranch() + "," 
                                         + fileEntry.getRevision() + "," + fileEntry.getVersion() + " \r\n"; 
                fw.write(fileString);
            }
        }
        fw.close();
 
        AddBaseline addBaselineFunctor = new AddBaseline();
        addBaselineFunctor.setBaselineName(b.getName());
        //addBaselineFunctor.setBaselineLocation("Root Folder/AutoCAD");   
		addBaselineFunctor.setBaselineLocation(b.getLocation());
        addBaselineFunctor.setReleaseLevel(Properties.get(Properties.baselineDefaultRelease));
        addBaselineFunctor.setRtBaselineFilesList(f.getAbsolutePath());
       
        //get these from the datasource
        addBaselineFunctor.setUsername(ds.getUsername());
        addBaselineFunctor.setPassword(ds.getPassword());
        addBaselineFunctor.setEXEToolkitEnv(ds.getEXEToolkitEnv());
        addBaselineFunctor.execute();
        
        
    }catch(Exception e){
       
      e.printStackTrace();  
    }finally{ //TODO uncomment
        //if(f!=null )
        //   f.delete();
        //if(d!=null)
        //   d.delete();*/
    }
  /*
  }

   /*
    *
    */ 
  private synchronized void deleteFilelist(Baseline b, Connection connection) throws SQLException{
    Statement statement = connection.createStatement();
    String sql = "DELETE FROM baselinefile ";
    sql += "WHERE baseline_fk='"+b.getPrimaryKey()+"'";
    statement.execute(sql);
  }

  private synchronized void addFilelist(Baseline b, Connection connection) throws SQLException{
    Statement statement = connection.createStatement();
    Iterator bI = b.getFiles().iterator();
    while(bI.hasNext()){
      Fileentry file = (Fileentry)bI.next();
      String file_pk = idgen.generateID();
      String sql = "insert INTO baselinefile VALUES(";
      sql += "'"+file_pk+"',";
      sql += "'"+file.getName()+"',";
      sql += "'"+b.getPrimaryKey()+"',";
      sql += "'"+file.getOrigin()+"',";
      sql += "'"+file.getRevision()+"',";
      sql += "'"+file.getVersion()+"',";
      sql += "'"+file.getBranch()+"')";
      statement.execute(sql);
    }
  }

  private Baseline unmarshallBaseline(ResultSet r) throws SQLException {
    Baseline b = new Baseline();
    b.setPrimaryKey(r.getString("baseline_pk"));
    b.setName(r.getString("name"));
    b.setLocation(r.getString("location"));
    return b;
  }

  private Fileentry unmarshallBaselinefile(ResultSet r) throws SQLException {  
      
  Fileentry file = new Fileentry();
  file.setPrimaryKey(r.getString("baselinefile_pk"));
  file.setName(r.getString("filename"));
  file.setOrigin(r.getString("origin"));
  file.setRevision(r.getString("revision"));
  file.setVersion(r.getString("version"));
  file.setBranch(r.getString("branch"));
  return file;
  }
  
  private Database database()  throws Exception { return DB.source(sqlDataSourceName); }
}

