package com.zws.test;

import com.zws.xml.parser.Parser;

import java.io.File;
public class ConfigTest {

  public static void main(String[] args) {
    loadConfig();
/*
    loadDataSources();
    loadSearchAgents();
    loadDataReports();
    loadAgentReportMappings();
    loadProcessors();
*/
  }


  public static void loadConfig(){
    File xmlMapping = new File("C:\\DesignState\\defaultroot\\WEB-INF\\classes\\config.tagmap");
    File xmlConfig = new File("C:\\DesignState\\defaultroot\\WEB-INF\\classes\\config.xml");
    loadConfig(xmlConfig, xmlMapping);
  }


  public static void loadDataSources(){
    File xmlMapping = new File("C:\\DesignState\\defaultroot\\config\\01-data-source.tagmap");
    File xmlConfig = new File("C:\\DesignState\\defaultroot\\config\\01-data-source.xml");
    loadConfig(xmlConfig, xmlMapping);
  }

  public static  void loadSearchAgents(){
    File xmlMapping = new File("C:\\DesignState\\defaultroot\\config\\02-search-agent.tagmap");
    File xmlConfig = new File("C:\\DesignState\\defaultroot\\config\\02-search-agent.xml");
    loadConfig(xmlConfig, xmlMapping);
  }

  public static  void loadDataReports(){
    File xmlMapping = new File("C:\\DesignState\\defaultroot\\config\\03-data-report.tagmap");
    File xmlConfig = new File("C:\\DesignState\\defaultroot\\config\\03-data-report.xml");
    loadConfig(xmlConfig, xmlMapping);
  }

  public static  void loadAgentReportMappings(){
    File xmlMapping = new File("C:\\DesignState\\defaultroot\\config\\04-agent-report-mapping.tagmap");
    File xmlConfig = new File("C:\\DesignState\\defaultroot\\config\\04-agent-report-mapping.xml");
    loadConfig(xmlConfig, xmlMapping);
  }

  public static  void loadProcessors(){
    File xmlMapping = new File("C:\\DesignState\\defaultroot\\config\\05-processor.tagmap");
    File xmlConfig = new File("C:\\DesignState\\defaultroot\\config\\05-processor.xml");
    loadConfig(xmlConfig, xmlMapping);
  }

  public static  void loadConfig(File xmlConfig,File xmlMapping ) {
    try {
      Parser parser = new Parser();
      parser.load(xmlConfig, xmlMapping);
    }
    catch (Exception e) { e.printStackTrace(); }
  }
}
