package zws.origin;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 7, 2004, 9:16 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.data.Metadata;
import zws.exception.CanNotMaterialize;
import zws.exception.InvalidComparison;
import zws.origin.tc10.TC10DatasetOrigin;
import zws.origin.tc10.TC10IMANFileOrigin;
import zws.origin.tc10.TC10ItemOrigin;
import zws.origin.tc10.TC10ItemRevOrigin;

import java.io.File;
import java.util.*;
import zws.data.workspace.Workspace;

public class OriginMaker {

	public static WorkspaceOrigin materialize(Workspace ws, String itemName, long timeOfCreationInMillis) {
		WorkspaceOrigin o = new WorkspaceOrigin();
		o.setDomainName(ws.getDomainName());
		o.setServerName(ws.getServerName());
		o.setDatasourceName(ws.getDatasourceName());
		o.setUsername(ws.getAuthentication().getUsername());
		o.setWorkspace(ws.getName());
		o.setName(itemName);
		o.setTimeOfCreationInMillis(timeOfCreationInMillis);
		return o;
	}

  public static Origin materialize(String domainName, String serverName, String datasourceType, String datasourceName, long timeOfCreation, String uniqueID, String location, String state) throws CanNotMaterialize {
    OriginBase o = createOrigin(datasourceType);
    o.loadState(state);
    o.setDomainName(domainName);
    o.setServerName(serverName);
    o.setDatasourceName(datasourceName);
    o.setTimeOfCreationInMillis(timeOfCreation);
    o.setLocation(location);
    //loading unique ID may override above values
    o.loadUniqueID(uniqueID);
    return o;
  }

  public static Origin materialize(String domainName, String serverName, String datasourceType, String datasourceName, long timeOfCreation, String uniqueSequence, String name, String location, String state) throws CanNotMaterialize {
    OriginBase o = createOrigin(datasourceType);
    o.loadState(state);
    o.setDomainName(domainName);
    o.setServerName(serverName);
    o.setDatasourceName(datasourceName);
    o.setTimeOfCreationInMillis(timeOfCreation);
    o.setName(name);
    o.setLocation(location);
    o.loadUniqueSequence(uniqueSequence);
    return o;
  }

  public static Origin materialize(String originAsString) throws CanNotMaterialize {
    OriginBase origin=null;
    String datasourceType;
    long timeOfCreation;
    StringTokenizer tokens = new StringTokenizer(originAsString, Names.ORIGIN_DELIMITER);
    /*domain*/ if (tokens.hasMoreTokens()) tokens.nextToken(); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+Names.ORIGIN_DELIMITER, originAsString);
    /*server*/ if (tokens.hasMoreTokens()) tokens.nextToken(); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+Names.ORIGIN_DELIMITER, originAsString);
    /*repositoryType*/ if (tokens.hasMoreTokens()) datasourceType = tokens.nextToken(); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+Names.ORIGIN_DELIMITER, originAsString);
    return materialize(datasourceType, originAsString);
  }

  public static Origin materialize(String datasourceType, String originAsString) throws CanNotMaterialize {
    OriginBase origin=createOrigin(datasourceType);
    origin.load(originAsString);
    return origin;
  }

  public static FileSystemOrigin materialize(String datasourceName, File root, File f) throws CanNotMaterialize {
    FileSystemOrigin origin = new FileSystemOrigin();
    origin.load(datasourceName, root, f);
    return origin;
  }

  private static OriginBase createOrigin(String datasourceType) throws CanNotMaterialize {
    OriginBase origin=null;
    if (datasourceType.endsWith(".workspace")) origin = new WorkspaceOrigin();
    else if (datasourceType.startsWith(FROM_ILINK_8)) origin = new R8Origin();
    else if (datasourceType.startsWith(FROM_ILINK)) origin = new IntralinkOrigin();
    //else if (datasourceType.startsWith(FROM_TEAMCENTER_10)) origin = new TC10Origin();
    else if (datasourceType.startsWith(FROM_TEAMCENTER_10_IMANFILE)) origin = new TC10IMANFileOrigin();
    else if (datasourceType.startsWith(FROM_TEAMCENTER_10_DATASET)) origin = new TC10DatasetOrigin();
    else if (datasourceType.startsWith(FROM_TEAMCENTER_10_ITEM)) origin = new TC10ItemOrigin();
    else if (datasourceType.startsWith(FROM_TEAMCENTER_10_REV)) origin = new TC10ItemRevOrigin();
    else if (datasourceType.startsWith(FROM_AGILE)) origin = new AgileOrigin(datasourceType);
    else if (datasourceType.startsWith(FROM_ILINK_PART_NUMBER)) origin = new IlinkPartNumberOrigin();
    else if (datasourceType.startsWith(FROM_HARRIS_ILINK)) origin = new HarrisOrigin();
    else if (datasourceType.startsWith(FROM_DISK)) origin = new FileSystemOrigin();
    else  throw new CanNotMaterialize("Origin", "Unsupported datasource type", datasourceType);
    return origin;
  }

/*
  public static IntralinkOrigin materializeIntralinkOrigin(Metadata data) throws CanNotMaterialize {
    IntralinkOrigin origin = new IntralinkOrigin();
    String domainName=NA, serverName=NA, datasourceName=NA;
    String branch=null,revision=null,version=null,name=null,releaseLevel=NA,location=NA, lockStatus=NA, owner=NA;
    long timeOfCreation=-1;
    origin.setDomainName(domainName);
    origin.setServerName(serverName);
    origin.setDatasourceType(datasourceType);
    orgin.setDatasourceName(datasourceName);
    orign.setTimeOfCreationInMillis(timeOfCreation);

    domainName=data.get(DOMAIN);
    serverName=data.get(SERVER);
    datasourceName=data.get(DATASOURCE);


    branch= data.get(BRANCH);
    revision= data.get(REVISION);
    version= data.get(VERSION);
    name= data.getName();

    releaseLevel= data.get(RELEASE_LEVEL);
    location= data.get(LOCATION);
    lockStatus= data.get(LOCK_STATUS);
    owner= data.get(OWNER);

    origin.setDomainName(domainName);
    origin.setServerName(serverName);
    origin.setDatasourceType(FROM_ILINK);
    origin.setDatasourceName(datasourceName);
    origin.setBranch(branch);
    origin.setRevision(revision);
    origin.setVersion(Integer.valueOf(version).intValue());
    origin.setName(name);
    origin.setReleaseLevel(releaseLevel);
    origin.setLocation(location);
    origin.setOwner(owner);
    origin.setLockStatus(lockStatus);
    origin.setOwner(owner);
    return origin;
  }
  */
  public static IlinkPartNumberOrigin materializeIntralinkPartNumberOrigin(IntralinkOrigin o, String partnumber) {
    IlinkPartNumberOrigin p = new IlinkPartNumberOrigin(o, partnumber);
    return p;
  }
  public static void materializeIntralinkOrigin(String serverName, String datasourceType, String datasourceName, Calendar timeOfCreation) {
    return;
  }

  public static void materializeFileSystemOrigin(String serverName, String datasourceType, String datasourceName, Calendar timeOfCreation) {
    return;
  }

  public static void materializeFTPOrigin(String serverName, String datasourceType, String datasourceName, Calendar timeOfCreation) {
    return;
  }

  public static void materializeURLOrigin(String serverName, String datasourceType, String datasourceName, Calendar timeOfCreation) {
    return;
  }

  protected List getStateChangeEvents(Origin oldOrigin, Origin newOrigin) throws InvalidComparison {
    return newOrigin.getStateChangeEvents(oldOrigin);
  }

  public static String DOMAIN_NAME = Metadata.DOMAIN_NAME;
  public static String SERVER_NAME = Metadata.SERVER_NAME;
  public static String REPOSITORY_NAME = Metadata.REPOSITORY_NAME;
  public static String BRANCH = Metadata.BRANCH;
  public static String REVISION = Metadata.REVISION;
  public static String VERSION = Metadata.VERSION;
  public static String FILE_TYPE= Metadata.FILE_TYPE;
  public static String LOCATION = Metadata.LOCATION;
  public static String TIME_OF_CREATION = Metadata.TIME_OF_CREATION;
  public static String LOCK_STATUS = Metadata.LOCK_STATUS;
  public static String OWNER = Metadata.OWNER;

  public static String NA = Origin.NA;
  public static String FROM_SQL = Origin.FROM_SQL;
  public static String FROM_WORKSPACE = Origin.FROM_WORKSPACE;
  public static String FROM_AGILE = Origin.FROM_AGILE;
  public static String FROM_TEAMCENTER_10 = Origin.FROM_TEAMCENTER_10;
  public static String FROM_TEAMCENTER_10_ITEM = Origin.FROM_TEAMCENTER_10_ITEM;
  public static String FROM_TEAMCENTER_10_REV = Origin.FROM_TEAMCENTER_10_REV;
  public static String FROM_TEAMCENTER_10_DATASET = Origin.FROM_TEAMCENTER_10_DATASET;
  public static String FROM_TEAMCENTER_10_IMANFILE = Origin.FROM_TEAMCENTER_10_IMANFILE;
  public static String FROM_ILINK_8 = Origin.FROM_ILINK_8;
  public static String FROM_ILINK = Origin.FROM_ILINK;
  public static String FROM_ILINK30 = Origin.FROM_ILINK30;
  public static String FROM_ILINK31 = Origin.FROM_ILINK31;
  public static String FROM_ILINK32 = Origin.FROM_ILINK32;
  public static String FROM_ILINK33 = Origin.FROM_ILINK33;
  public static String FROM_ILINK34 = Origin.FROM_ILINK34;
  public static String FROM_ILINK_PART_NUMBER = Origin.FROM_ILINK_PART_NUMBER;
  public static String FROM_PACKAGE = Origin.FROM_PACKAGE;
  public static String FROM_DISK = Origin.FROM_DISK;
  public static String FROM_FTP_SITE = Origin.FROM_FTP_SITE;
  public static String FROM_URL_LOCATION = Origin.FROM_URL_LOCATION;
  public static String FROM_ORACLE8i = Origin.FROM_ORACLE8i;
  public static String FROM_HARRIS_ILINK = Origin.FROM_HARRIS_ILINK;


  //some demo O's
  public static String FROM_AGILE_CAD_MODEL = Origin.FROM_AGILE_CAD_MODEL;
  public static String FROM_AGILE_CAD_DOCUMENT = Origin.FROM_AGILE_CAD_DOCUMENT;



  public static String delim = Names.ORIGIN_DELIMITER;
}
