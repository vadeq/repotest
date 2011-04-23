package com.zws.application;

import com.zws.util.KeyValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Properties {

  public static void add(KeyValue pair) {
    String value="";
    if (null!= pair.getValue()) value=pair.getValue().toString();
    set(pair.getKey(), value);
  }
  public static void addFilePath(KeyValue pair) throws FileNotFoundException {
    String path = (String)pair.getValue();
    path = expandReferences(path);
    File f = new File(path);
    if (!f.exists()) throw new FileNotFoundException(pair.getKey() +"="+(String)pair.getValue());
    set(pair.getKey(), (String)pair.getValue());
  }

  public static void addDirectoryPath(KeyValue pair) throws FileNotFoundException {
    String path = (String)pair.getValue();
    path = expandReferences(path);
    File f = new File(path);
    if (!f.isDirectory()) throw new FileNotFoundException(pair.getKey() +"="+(String)pair.getValue()+" <- no such directory exists");
    if (!f.exists()) throw new FileNotFoundException(pair.getKey() +"="+(String)pair.getValue());
    set(pair.getKey(), (String)pair.getValue());
  }

  public static void set(String property, String value) { 
      properties.put(property, value); 
  }
  public static String get(String property) { 
      return zws.application.Properties.get(property);
      //return expandReferences((String) properties.get(property)); 
  }

  public static String expandReferences(String s) {
    int start=-1,end=-1;
    String value=s;
    String ref;
    while (((start=value.indexOf(Constants.PROPERTY_REF_START)) > -1) &&
           ((end=value.indexOf(Constants.PROPERTY_REF_END))>start)){
      ref = value.substring(start+1, end);
      try { value=get(ref)+value.substring(end+1); }
      catch (NullPointerException e) { value=null; }
      if (null==value) throw new RuntimeException("Reference to property not found: "+ref);
    }
    return value;
  }
  private static Map properties=new HashMap();

  public static String enableAuthentication = "enable-authentication";
  public static String startPage= "start-page"; 

  public static String docBase = "doc-base";
  public static String pdfStampToolkit = "pdfstamp-toolkit";
  public static String pdfstampFontSize = "pdfstamp-font-size";
  
  public static String linkToolkit = "link-toolkit";
  public static String checkinToolkit = "checkin-toolkit";
  public static String importToolkit = "import-toolkit";
  public static String attrsetToolkit = "attrset-toolkit";
 
  public static String folderAttributeName = "folder-attribute-name";
  
  
  public static String promotionFormToolkit = "promotion-form-toolkit";
  public static String baselineToolkit = "baseline-toolkit";
  public static String foldersToolkit = "folders-toolkit";
  public static String tempDirectory = "temp-directory";
  public static String downloadDirectory = "download-directory";
  public static String DesignStateDatabase = "DesignState-database";
  public static String exeProE= "exe-proE";
  public static String exeProCommMsg = "exe-proCOMM-msg";
  public static String exeILinkTKenv= "exe-ilink-tk-env";
  public static String exeGhostScript = "exe-gs-ps2pdf";
  public static String iLinkCheckout = "ilink-checkout";
  public static String iLinkGetFolders = "ilink-get-folders";
  public static String iLinkAddBaseline = "ilink-create-baseline";
  public static String iLinkDeleteBaseline = "ilink-delete-baseline";
  public static String iLinkCheckoutAsStored = "ilink-checkout-as-stored";
  public static String iLinkGetBOM = "ilink-bom-as-stored";
  public static String iLinkDestroyWorkspace = "ilink-destroy-workspace";
  public static String iLinkExport = "ilink-export";
  public static String iLinkSearch = "ilink-search";
  public static String dwg2ps = "dwg2ps";
  public static String dwg2psTimeout = "dwg2ps-timeout";
  public static String drw2ps = "drw2ps";
  public static String Office2pdf = "office2pdf";
  public static String drw2psOutputDrive ="drw2ps-output-drive"; //todo: remove this once toolkit is fixed to take inputfilename and output filename as parameters
  public static String drw2psType = "drw2ps-output-type"; //todo: remove this once toolkit is fixed to take inputfilename and output filename as parameters
  public static String drw2psTimeout = "drw2ps-timeout";
  public static String ps2pdf= "ps2pdf";
  public static String ps2colorpdf= "ps2colorpdf";
  public static String spoolerOutDirectory = "spooler-output-directory";
  public static String colorPDF = "color-pdf";
  public static String blackWhitePDF = "black-and-white-pdf";
  
  public static String typeIlinkAttribute = "type-ilink-attribute";
  public static String promoBaselineFolder = "promo-baseline-folder"; 
  public static String logDirectory = "zws-log";

//  public static String baselineDefaultRelease = "baseline-default-release";
//  public static String baselineDatasource = "baseline-datasource";
//  public static String baselineActiveReport = "baseline-active-report";

  public static String MODEL_CHECK_NOTIFICATION_RECIPIENTS = "model-check-notification-recipients";
  public static String MODEL_CHECK_NOTIFICATION_REPLY_TO = "model-check-notification-reply-to";
  public static String MODEL_CHECK_NOTIFICATION_SUBJECT = "model-check-notification-subject";
  public static String MODEL_CHECK_NOTIFICATION_HEADER = "model-check-notification-header";
  public static String MODEL_CHECK_NOTIFICATION_FOOTER = "model-check-notification-footer";
  public static String MODEL_CHECK_PROMOTION_FOLDER = "model-check-promotion-folder";
  public static String MODEL_CHECK_PROMOTION_LEVEL = "model-check-promotion-level";
  public static String MODEL_CHECK_ATTRIBUTES = "model-check-attributes";

  public static String SQLServerDriverClass = "ms-sql-server-driver-class";
}
