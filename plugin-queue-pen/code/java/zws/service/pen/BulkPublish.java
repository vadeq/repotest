package zws.service.pen;
/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Apr 5, 2007 2:28:32 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */
import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import java.io.*;
import java.text.SimpleDateFormat;

public class BulkPublish {

  private Authentication id; 
  private QxContext context;
  private PenQueuePlugin queue;
  private Repository repository;
  private java.util.Properties config;
  private PrintStream logger;
  private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss: ");
  
  public boolean init(File configuration) {

    try {
      
      config = new java.util.Properties();
      config.load(new FileInputStream(configuration));

      System.out.println("Sending output to " + get(CFG_LOG));
      logger = new PrintStream( get(CFG_LOG) );
      
      Properties.set( Names.SERVICE_FINDER_HOSTNAME, get(CFG_SERVER) );
      id = new Authentication( get(CFG_USERNAME), get(CFG_PASSWORD) );
      
      RepositoryService repositoryService = RepositoryClient.getClient();
      repository = repositoryService.findRepository( get(CFG_SOURCE) );
      if (repository == null) throw new Exception ("Connection to repository failed");
      
      queue = new PenQueuePlugin();
      
    } catch (IOException io) {
      log("Cannot find and read from configuration file " + configuration.getName());
      return false;
      
    } catch (Exception e) {
      log("Error initializing client or establishing a connection: " + e.getMessage());     
      return false;
    }
    
    return true;
  }
  
  public void process() {

    BufferedReader input = null;
    String line;
    
    try {
      
      log("Opening file " + get(CFG_FILE));
      input = new BufferedReader( new FileReader( get(CFG_FILE) ));
      
      while ( (line=input.readLine()) != null) {
        log("Processing " + line + " ... ");  
        publish(line.trim());
      }
    
    } catch (IOException io) {      
      log("Error reading from file " + get(CFG_FILE));
      log("Error: " + io.getMessage());
    } finally {
      
      try { 
        if (input != null) {
          input.close();
          log("Closed " + get(CFG_FILE));
        }
      } catch (Exception e) { }       
    }
  }

  private void publish(String partName) {
    
    try {

      context = RecorderUtil.startNewProcess(get(CFG_NAMESPACE), partName, get(CFG_DESCRIPTION));
      
      RepositoryMetadataSource metadataSrc = repository.materializeMetadataSource();
      Metadata m = metadataSrc.findLatest(context, partName, id);
      
      if (m != null) {
        setStatus(Names.STATUS_PUBLISHING, false);
        queue.publish(m.getOrigin(), get(CFG_INTENT), get(CFG_PRIORITY), get(CFG_TARGET), context, id);      
      } else {
        log("\tPART NOT FOUND!");
        setStatus(Names.STATUS_COMPLETE, true);
      }
    } catch (Exception e) {      
      log("\tError (see below)");
      log("\t" + e.getMessage());      
      setStatus(Names.STATUS_ERROR, true);
      e.printStackTrace();
    } finally {
      setStatus(Names.STATUS_COMPLETE, true);
    }   
  }
  
  private String get(String property) {
    return config.getProperty(property);
  }
  
  private void setStatus(String status, boolean end) {    
    try {      
      if (end) RecorderUtil.endRecProcess(context, status);
      else RecorderUtil.setStatus(context, status);   
    } catch(Exception e) { }
  }

  public synchronized void log(String message) {
    try {
      logger.println(sdf.format(new java.util.Date()) + message);
      logger.flush();
    } catch (Exception e) { e.printStackTrace(); }
  }
  
  public static void main(String[] args) {
    
    File file;
    
    try {
      
      if (args.length != 1) {
        System.err.println("Usage: BulkPublish <properties file path>\n\n");
        return;
      } else {
        file = new File(args[0]);
      }
      
      if ( !file.exists() || !file.isFile() ) {
        System.err.println(args[0] + " not found or is not a file.\n\n");
        return;
      }
      
      BulkPublish loader = new BulkPublish();
      if ( !loader.init(file) ) return;
      loader.process();
      
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  
  private final String CFG_USERNAME = "username";
  private final String CFG_PASSWORD = "password";
  private final String CFG_NAMESPACE = "namespace";
  private final String CFG_INTENT = "intent";
  private final String CFG_SOURCE = "source";
  private final String CFG_TARGET = "target";
  private final String CFG_SERVER = "server";
  private final String CFG_PRIORITY = "priority";
  private final String CFG_DESCRIPTION = "description";
  private final String CFG_FILE = "file";
  private final String CFG_LOG = "log";
}
