package zws.origin.tc10;

import zws.application.Names;
import zws.exception.CanNotMaterialize;

import zws.origin.OriginMaker;
import zws.origin.TC10Origin;


import java.util.StringTokenizer;

public class TC10IMANFileOrigin extends TC10Origin
{

	public TC10IMANFileOrigin() { super(); }

	public TC10IMANFileOrigin( String domainName,
							   String serverName,
							   String repositoryName,
							   String uniqueId,
							   long timeOfCreation,
							   String state) throws CanNotMaterialize
	{
		this.setDomainName(domainName);
		this.setServerName(serverName);
		this.setRepositoryName(repositoryName);
		this.loadUniqueSequence(uniqueId);
		this.setTimeOfCreationInMillis(timeOfCreation);
		this.loadState(state);
    //this.setName(this.getItemId());
	}

	public String getDatasourceType() { return OriginMaker.FROM_TEAMCENTER_10_IMANFILE; }

	public String getItemId() {return itemId;}
	public void setItemId(String itemId) {this.itemId = itemId;}
	public String getRevision() {return revision;}
	public void setRevision(String revision) {this.revision = revision;}
	public String getDatasetType() {return datasetType;}
	public void setDatasetType(String datasetType) {this.datasetType = datasetType;}
	public String getDatasetName() {return datasetName;}
	public void setDatasetName(String datasetName) {this.datasetName = datasetName;}
  public String getNamedRef() {return namedReference;}
  public void setNamedRef(String namedRef) {this.namedReference = namedRef;}
	public String getFileName() {return fileName;}
	public void setFileName(String fileName) {this.fileName = fileName;}

	public String getUniqueSequence() { return getUid() + delim + getItemId() + delim + getRevision()+ delim + getDatasetType() + delim + getDatasetName() + delim + getFileName(); }
	public String getUniqueIDDisplay() { return getUid() + " [" + getItemId() + "/" + getRevision() + "(" + getDatasetType() +")" + getDatasetName() + " - " + getFileName() + "]"; }

	public void load(String originAsString) throws CanNotMaterialize {
		StringTokenizer tokens = new StringTokenizer(originAsString, delim);
		if (tokens.hasMoreTokens()) setDomainName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
		if (tokens.hasMoreTokens()) setServerName (tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
		if (tokens.hasMoreTokens()) if (!getDatasourceType().equalsIgnoreCase(tokens.nextToken())) throw new CanNotMaterialize("Teamcenter Origin", "Repository type is not Teamcenter", originAsString);
		if (tokens.hasMoreTokens()) setDatasourceName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
		if (tokens.hasMoreTokens()) setUid(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
		if (tokens.hasMoreTokens()) setItemId(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
		if (tokens.hasMoreTokens()) setRevision(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
		if (tokens.hasMoreTokens()) setDatasetType(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
		if (tokens.hasMoreTokens()) setDatasetName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
		if (tokens.hasMoreTokens()) setFileName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
		if (tokens.hasMoreTokens()) setTimeOfCreationInMillis( convertToMilliSecs(tokens.nextToken()) ); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
		if (tokens.hasMoreTokens()) setReleaseLevel(tokens.nextToken());
		if (tokens.hasMoreTokens()) setLockStatus(tokens.nextToken());
		if (tokens.hasMoreTokens()) setOwner(tokens.nextToken());
    setName(getItemId());
	}

	public void loadUniqueSequence(String sequence) throws CanNotMaterialize {
		StringTokenizer tokens = new StringTokenizer(sequence, delim);
		if (tokens.hasMoreTokens()) setUid(tokens.nextToken());
		if (tokens.hasMoreTokens()) setItemId(tokens.nextToken());
		if (tokens.hasMoreTokens()) setRevision(tokens.nextToken());
		if (tokens.hasMoreTokens()) setDatasetType(tokens.nextToken());
		if (tokens.hasMoreTokens()) setDatasetName(tokens.nextToken());
		if (tokens.hasMoreTokens()) setFileName(tokens.nextToken());
		else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, sequence);
    setName(getItemId());
	}

	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append(getDomainName())
		.append(delim).append(getServerName())
		.append(delim).append(getDatasourceType())
		.append(delim).append(getDatasourceName())
		.append(delim).append(getUid())
		.append(delim).append(getItemId())
		.append(delim).append(getRevision())
		.append(delim).append(getDatasetType())
		.append(delim).append(getDatasetName())
		.append(delim).append(getFileName())
		.append(delim).append(getTimeOfCreationInMillis());
		if (null==getReleaseLevel()) b.append(delim).append(NA);
		else b.append(delim).append(getReleaseLevel());
		if (null==getLockStatus()) b.append(delim).append(NA);
		else b.append(delim).append(getLockStatus());
		if (null==getOwner()) b.append(delim).append(NA);
		else b.append(delim).append(getOwner());
		return b.toString();
	}

	public String toXML() { return null; }

	//unique path variables:
	private String itemId=null;
	private String revision=null;
	private String datasetName=null;
	private String datasetType=null;
  private String namedReference=null;
	private String fileName=null;

	//State variables:
	private String releaseLevel=null;
	private String lockStatus=null;
	private String owner=null;

	public static String LOCKED="locked";
	public static String UNLOCKED = "unlocked";
	public static String MODIFYING = "being modified";
	public static String delim = Names.ORIGIN_DELIMITER;
}
