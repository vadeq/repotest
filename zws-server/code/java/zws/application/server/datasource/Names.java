package zws.application.server.datasource; 

/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class Names extends zws.application.server.designstate.Names {
 //File System Root Locations
 public static String SNAPSHOT_FOLDER = "snapshot-folder";    
 public static String PROMOTE_FORM_FOLDER = "promote-form-folder";
 //public static String MAX_ILINK_TK_LICENSES= "max-ilink-tk-licenses"; :moved into ilink    
 public static String PATH_PACKAGE_ROOT = "zws-package-root";
 public static String PUBLISH_TO_AGILE_ENABLED = "publish-to-agile-enabled";
 public static String xsltAGILE_DATA="xslt-agile-data";
 
 //common metadata field names
 public static String METADATA_SOURCEFILE_NAME = "zws-sourcefile-name";
 public static String METADATA_SOURCEFILE_TYPE = "zws-sourcefile-type";
 public static String METADATA_SOURCEFILE_EXTENTION = "zws-sourcefile-extention";
 public static String METADATA_SOURCEFILE_SERVER_NAME = "zws-sourcefile-server-name";
 public static String METADATA_SOURCEFILE_DATASOURCE_NAME = "zws-sourcefile-datasource-name";
 public static String METADATA_SOURCEFILE_SEARCH_AGENT_NAME = "zws-sourcefile-search-agent-name";

 //metadata field names for file-system datasource
 public static String METADATA_FILE_SIZE="zws-file-size";
 public static String METADATA_FILE_PERMISSIONS="zws-permissions";
 public static String METADATA_FILE_LAST_MODIFIED="zws-last-modified";
 public static String METADATA_FILE_DATE_LAST_MODIFIED="zws-date-last-modified";
 public static String METADATA_FILE_TIME_LAST_MODIFIED="zws-time-last-modified";

 //values for METADATA_FILE_PERMISSIONS
 public static String METADATA_VALUE_READ_ONLY_PERMISSIONS="read only";
 public static String METADATA_VALUE_READ_WRITE_PERMISSIONS="read write";
 
 public static String DOWNLOAD_REPOSITORY = "download-repository";
 public static String DOWNLOAD_SERVER_PROTOCOL = "download-server-protocol";
 //public static String DOWNLOAD_SERVER_HOSTNAME = "download-server-hostname";
 public static String DOWNLOAD_SERVER_PORT = "download-server-port";
 public static String DOWNLOAD_SERVER_PATH = "download-server-path";
 
 public static String PDF_PRINT_APP  = "pdf-print-appplication";
 public static String PS_PRINT_APP  = "ps-print-appplication";
 public static String PDF_STAMP_CONVENTION  = "pdf-stamp-convention";
 
 public static String PDF_NAMING_CONVENTION  = "pdf-naming-convention";
 public static String PS_NAMING_CONVENTION  = "ps-naming-convention";
 public static String HPGL_NAMING_CONVENTION  = "hpg-naming-convention";
 public static String CGM_NAMING_CONVENTION  = "cgm-naming-convention";
 public static String IGS_NAMING_CONVENTION  = "igs-naming-convention";
 public static String STP_NAMING_CONVENTION  = "stp-naming-convention";
 public static String NEU_NAMING_CONVENTION  = "neu-naming-convention";
 public static String IDF_NAMING_CONVENTION  = "idf-naming-convention";
 public static String DXF_NAMING_CONVENTION  = "dxf-naming-convention";
 public static String DWG_NAMING_CONVENTION  = "dwg-naming-convention";
 
 public static String PDFSTAMP_FONT_SIZE  = "pdf-stamp-font-size";
 public static String PDFSTAMP_FONT_COLOR = "pdf-stamp-font-color";
 public static String PDFSTAMP_ANGLE = "pdf-stamp-angle";
 public static String PDFSTAMP_OPACITY = "pdf-stamp-opacity";
 public static String PDFSTAMP_X_POSITION = "pdf-stamp-x-postion";
 public static String PDFSTAMP_Y_POSITION = "pdf-stamp-y-position";
 
 /*
 // native executables
 
 //FileSystem
 public static String EXE_FILESYSTEM_DWG_METADATA = "filesystem-dwg-metadata";
 public static String EXE_FILESYSTEM_DWG2PDF = "filesystem-dwg2pdf";

 //Agile
 public static String EXE_AGILE_PUBLISH_BILL ="agile-publish-bill";
 
 //ProE mdimpex
 public static String EXE_MDIMPEX_EXPORT = "mdimpex-export";
 public static String EXE_MDIMPEX_IMPORT = "mdimpex-import";
 public static String EXE_MDIMPEX_IMPORT_LIST = "mdimpex-import-list";
 
 //Intralink
 public static String EXE_ILINK_SEARCH = "ilink-search";
 public static String EXE_ILINK_FIND = "ilink-find";
 public static String EXE_ILINK_FIND_LATEST = "ilink-find-latest";
 public static String EXE_ILINK_CHECKIN = "ilink-checkin";
 public static String EXE_ILINK_CHECKOUT = "ilink-checkout";
 public static String EXE_ILINK_LOCK = "ilink-lock";
 public static String EXE_ILINK_UNLOCK = "ilink-unlock";
 public static String EXE_ILINK_MOVE = "ilink-move";
 public static String EXE_ILINK_RENAME = "ilink-rename";
 public static String EXE_ILINK_GET_FOLDER_LISTING = "ilink-get-folder-listing";
 public static String EXE_ILINK_GET_DEPENDENCIES = "ilink-get-dependencies";
 public static String EXE_ILINK_FIND_IN_WORKSPACE = "ilink-find-in-workspace";
 public static String EXE_ILINK_LIST_WORKSPACE_CONTENTS = "ilink-list-workspace-contents";
 public static String EXE_ILINK_DELETE_FOLDER = "ilink-delete-folder";
 public static String EXE_ILINK_CREATE_FOLDER = "ilink-create-folder";
 public static String EXE_ILINK_COMMON_SPACE_DELETE = "ilink-common-space-delete";
 public static String EXE_ILINK_IMPORT = "ilink-import";
 public static String EXE_ILINK_IMPORT_LIST = "ilink-import-list";
 public static String EXE_ILINK_EXPORT = "ilink-export";
 public static String EXE_ILINK_HOLD_GHOSTS = "ilink-hold-ghosts";
 public static String EXE_ILINK_ADOPT_GHOSTS = "ilink-adopt-ghosts";
 public static String EXE_ILINK_LINK = "ilink-link";
 public static String EXE_ILINK_SET_ATTRIBUTE = "ilink-set-attribute";
 public static String EXE_ILINK_SET_ATTRIBUTE_LIST = "ilink-set-attribute-list";
 public static String EXE_ILINK_CREATE_BASELINE = "ilink-create-baseline";
 public static String EXE_ILINK_DELETE_BASELINE = "ilink-delete-baseline";
 public static String EXE_ILINK_PROMOTE = "ilink-promote";
 public static String EXE_ILINK_PROMOTE_FORM_SEARCH = "ilink-promote-form-search";
 public static String EXE_ILINK_MODELCHECK = "ilink-model-check";
 public static String EXE_ILINK_GET_BILL = "ilink-get-bill";
 public static String EXE_ILINK_CREATE_WORKSPACE = "ilink-create-workspace";
 public static String EXE_ILINK_DESTROY_WORKSPACE = "ilink-destroy-workspace";
 public static String EXE_ILINK_DRW2PS = "ilink-drw2ps";
 public static String EXE_ILINK_DRW2HPGL = "ilink-drw2hpgl";
 public static String EXE_ILINK_DRW2CGM = "ilink-drw2cgm";
 public static String EXE_ILINK_ASMPRT2IGES = "ilink-asmprt2iges";
 public static String EXE_ILINK_ASMPRT2IDF = "ilink-asmprt2idf";

 // pdf converters
 public static String GS_PS2PDF = "gs-ps2pdf";  //batch file
 public static String GS_PS2PDF_EXE = "gs-ps2pdf-exe"; //ghostscript executable
 public static String PDF_STAMP = "pdf-stamp";
 */
 //still need this one cause it is version specific
 public static String GS_PS2PDF_EXE = "gs-ps2pdf-exe"; //ghostscript executable
}
