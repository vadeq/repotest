package zws.repository.teamcenter;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on May 23, 2007 10:48:58 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.application.Names;
import zws.application.Properties;
import zws.exception.InvalidOrigin;

import zws.origin.Origin;
import zws.origin.tc10.TC10IMANFileOrigin;
import zws.qx.QxContext;
import zws.repository.source.RepositoryBinarySource;
import zws.security.Authentication;
import zws.util.Counter;
import zws.util.RemotePackage;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


import com.ugsolutions.iman.kernel.IMANComponentDataset;
import com.ugsolutions.iman.kernel.IMANComponentItemRevision;
import com.ugsolutions.iman.kernel.IMANSession;

public class TC10RepositoryBinarySource extends TC10RepositoryBase
implements RepositoryBinarySource
{

	protected TC10RepositoryBinarySource (QxContext parent)
	{
		configureParentContext(parent);
	}

	private void writeDummyFile(File out) throws Exception
	{
		if (!out.exists()) {
			String txt = "some text";
			OutputStream ostream = new java.io.FileOutputStream(out);
			ostream.write(txt.getBytes());
			ostream.close();
		}
	}

	public File download(QxContext runningCtx, String name, Authentication id) throws Exception
	{

		// TODO: Assign values
		//String datasetName = null;
		//String datasetType = null;
		//String fileName = null;

		HashMap tcSessions = (HashMap)getTeamcenterSessions();
		IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());

		//IMANComponentItem item = findItem(imanSession, name);
		//IMANComponentItemRevision latest = item.getLatestItemRevision();
		//IMANComponentDataset ds = findDataset(latest, datasetName, datasetType);

		File out = findImanFile(imanSession, name).getFile(null);
		//if( ds != null)
			//out = extractBinary(ds, fileName, null);
		return out;
	}

	public File download(QxContext runningCtx, String name, File toDir, Authentication id) throws Exception
	{
		// TODO: Assign values
		//String datasetName = null;
		//String datasetType = null;
		//String fileName = null;

		HashMap tcSessions = (HashMap)getTeamcenterSessions();
		IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());

		//IMANComponentItem item = findItem(imanSession, name);
		//IMANComponentItemRevision latest = item.getLatestItemRevision();
		//IMANComponentDataset ds = findDataset(latest, datasetName, datasetType);

		File out = findImanFile(imanSession, name).getFile(toDir.getPath());
		//if( ds != null)
			//out = extractBinary(ds, fileName, toDir.getPath());
		return out;
	}

	public Collection download(QxContext runningCtx, Origin origin, File toDir, Authentication id) throws Exception
	{
		HashMap tcSessions = (HashMap)getTeamcenterSessions();
		IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());

		if( !(origin instanceof TC10IMANFileOrigin) )
			throw new InvalidOrigin(origin);
		TC10IMANFileOrigin o = (TC10IMANFileOrigin)origin;

		String itemId = o.getItemId();
		String revision = o.getRevision();
		String datasetName = o.getDatasetName();
		String datasetType = o.getDatasetType();
		String fileName = o.getFileName();

		IMANComponentItemRevision itemRevision = findItemRevision(imanSession, itemId, revision);
		IMANComponentDataset ds = findDataset(itemRevision, datasetName, datasetType);

		Collection out = null;
		if( ds != null)
			out = extractBinary(ds, fileName, toDir.getPath());
		return out;
	}

	public Collection download(QxContext runningCtx, Origin origin, Authentication id) throws Exception
	{
		HashMap tcSessions = (HashMap)getTeamcenterSessions();
		IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());

		if( !(origin instanceof TC10IMANFileOrigin) )
			throw new InvalidOrigin(origin);
		TC10IMANFileOrigin o = (TC10IMANFileOrigin)origin;

		String itemId = o.getItemId();
		String revision = o.getRevision();
		String datasetName = o.getDatasetName();
		String datasetType = o.getDatasetType();
		String fileName = o.getFileName();

{} //System.out.println("Item Id: "+itemId);
{} //System.out.println("revision: "+revision);
{} //System.out.println("datasetName: "+datasetName);
{} //System.out.println("datasetType: "+datasetType);
{} //System.out.println("fileName: "+fileName);

		IMANComponentItemRevision itemRevision = findItemRevision(imanSession, itemId, revision);
		IMANComponentDataset ds = findDataset(itemRevision, datasetName, datasetType);

		Collection out = null;

		if( ds != null){
			{} //System.out.println("downloading..."+fileName);
			out = extractBinary(ds, fileName, null);
		}
		else
		{
			{} //System.out.println("Could not find dataset "+datasetName+" of type"+datasetType);
		}

//		if(out==null) 
			{} //System.out.println("Trouble downloading "+fileName);
	//	else
			{} //System.out.println("downloaded: "+out.size());

		return out;
	}

  public void downloadDesign(QxContext runningCtx, Collection origins, String location, Authentication id) throws Exception{
    //+++Todo download files in design structure...
  }

  public Collection downloadDesign(QxContext runningCtx, Origin origin, String location, Authentication id) throws Exception{
    //+++Todo download all files in design structure...
    return download(runningCtx, origin, location, id);
  }

	public Collection download(QxContext runningCtx, Origin origin, String location, Authentication id) throws Exception
	{
		HashMap tcSessions = (HashMap)getTeamcenterSessions();
		IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());

		if( !(origin instanceof TC10IMANFileOrigin) )
			throw new InvalidOrigin(origin);
		TC10IMANFileOrigin o = (TC10IMANFileOrigin)origin;

		String itemId = o.getItemId();
		String revision = o.getRevision();
		String datasetName = o.getDatasetName();
		String datasetType = o.getDatasetType();
		String fileName = o.getFileName();

		IMANComponentItemRevision itemRevision = findItemRevision(imanSession, itemId, revision);
		IMANComponentDataset ds = findDataset(itemRevision, datasetName, datasetType);

		Collection  out = null;
		if( ds != null)
			out = extractBinary(ds, fileName, location);
		return out;
	}

	/*public InputStream openStream(QxContext runningCtx, Origin origin, Authentication id) throws Exception
	{
		HashMap tcSessions = (HashMap)getTeamcenterSessions();
		IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());

		if( !(origin instanceof TC10IMANFileOrigin) )
			throw new InvalidOrigin(origin);
		TC10IMANFileOrigin o = (TC10IMANFileOrigin)origin;

		String itemId = o.getItemId();
		String revision = o.getRevision();
		String datasetName = o.getDatasetName();
		String datasetType = o.getDatasetType();
		String fileName = o.getFileName();

		IMANComponentItemRevision itemRevision = findItemRevision(imanSession, itemId, revision);
		IMANComponentDataset ds = findDataset(itemRevision, datasetName, datasetType);

		File out = null;
		if( ds != null)
			out = extractBinary(ds, fileName, null);
		return new FileInputStream(out);

	}

	public long getBinaryLength(QxContext runningCtx, Origin origin, Authentication id) throws Exception
	{
		HashMap tcSessions = (HashMap)getTeamcenterSessions();
		IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());

		if( !(origin instanceof TC10IMANFileOrigin) )
			throw new InvalidOrigin(origin);
		TC10IMANFileOrigin o = (TC10IMANFileOrigin)origin;

		String itemId = o.getItemId();
		String revision = o.getRevision();
		String datasetName = o.getDatasetName();
		String datasetType = o.getDatasetType();
		String fileName = o.getFileName();

		IMANComponentItemRevision itemRevision = findItemRevision(imanSession, itemId, revision);
		IMANComponentDataset ds = findDataset(itemRevision, datasetName, datasetType);

		File out = null;
		if( ds != null)
			out = extractBinary(ds, fileName, null);
		return out.length();
	}*/

  public Collection fetchDesignFilesLocally(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    //++todo this should be modified to download all the files of a design including subscomponents.
    Collection c = new ArrayList();
    String tempDir = Properties.get(Names.TEMP_DIR) + Names.PATH_SEPARATOR + "TC-10-standalone" + Counter.nextCount();
    String downloadDir = tempDir + Names.PATH_SEPARATOR + "download";
    Collection  f = downloadDesign(runningCtx, origin, downloadDir, id);

    return f ;
  }
  public File fetchNativeFileLocally(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    //++todo this should be modified to download all the files of a design including subscomponents.
    String tempDir = Properties.get(Names.TEMP_DIR) + Names.PATH_SEPARATOR + "TC-10-standalone" + Counter.nextCount();
    String downloadDir = tempDir + Names.PATH_SEPARATOR + "download";
    Collection  f = downloadDesign(runningCtx, origin, downloadDir, id);
    if(f.isEmpty())return null;
    //scan collection for .prt or .asm or ,dwg then return that first
    return (File)f.iterator().next();
  }
  public RemotePackage fetchDesignFiles(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    // using file-depot caused jar conflicts with httpclinet
      throw new zws.exception.UnsupportedOperation("fetchNativeFile(QxContext runningCtx, Origin origin, Authentication id)");
    //++todo this should be modified to download all the files of a design including subscomponents.
   /* String tempDir = Properties.get(Names.TEMP_DIR) + Names.PATH_SEPARATOR + "TC-10-standalone" + Counter.nextCount();
    String downloadDir = tempDir + Names.PATH_SEPARATOR + "download";
    String fileDepotTempDir= tempDir + Names.PATH_SEPARATOR + "file-depot";
    File f = downloadDesign(runningCtx, origin, downloadDir, id);

    FileDepotClient c = FileDepotClient.materializeClient(fileDepotTempDir);
    RemotePackage r = c.storeDirectory(new File(downloadDir), f.getName());
    return r;*/
  }

  public RemotePackage fetchNativeFile(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
   // using file-depot caused jar conflicts with httpclinet
    throw new zws.exception.UnsupportedOperation("fetchNativeFile(QxContext runningCtx, Origin origin, Authentication id)");
    /*String tempDir = Properties.get(Names.TEMP_DIR) + Names.PATH_SEPARATOR + "TC-10-standalone" + Counter.nextCount();
    String downloadDir = tempDir + Names.PATH_SEPARATOR + "download";
    String fileDepotTempDir= tempDir + Names.PATH_SEPARATOR + "file-depot";
    File f = download(runningCtx, origin, downloadDir, id);

    FileDepotClient c = FileDepotClient.materializeClient(fileDepotTempDir);
    RemotePackage r = c.storeDirectory(new File(downloadDir), f.getName());
    return r;*/
  }

	private static long count = 0;
	private static synchronized long nextCount() { return count++; }
	long unique = nextCount();

}