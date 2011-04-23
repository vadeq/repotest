package zws.ilink8.server.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import javax.servlet.http.HttpServletResponse;
import wt.content.ApplicationData;
import wt.content.ContentHelper;
import wt.content.ContentHolder;
import wt.content.ContentRoleType;
import wt.content.DataFormat;
import wt.content.Streamed;
import wt.epm.EPMDocument;
import wt.epm.familytable.CompatibleFamilyTables;
import wt.epm.familytable.EPMFamilyTable;
import wt.epm.familytable.EPMFamilyTableHelper;
import wt.epm.familytable.EPMSepFamilyTable;
import wt.fc.PersistenceHelper;
import wt.fc.QueryResult;
import wt.fc.ReferenceFactory;
import wt.fc.WTObject;
import wt.fc.collections.WTCollection;
import wt.fc.collections.WTKeyedMap;
import wt.method.RemoteAccess;
import wt.method.RemoteMethodServer;
import wt.query.QuerySpec;
import wt.query.SearchCondition;
import wt.vc.config.OwnershipIndependentLatestConfigSpec;

public class IntralinkUtility implements RemoteAccess{
  // -----------------------------------------------------------------------------------------------------------------------------------
	

	public Vector getApplicationData(String number, String name,String version, String className) throws Exception {
		return getApplicationData(getObject(Class.forName(className), number, name, version).toString());
	}
	
	public Vector getApplicationData(String oids) throws Exception {
		 StringTokenizer tokens = new StringTokenizer(oids, ",");
		 Vector contentVector = new Vector();
		 String oid = null;
		 ContentHolder contentHolder = null;
		 ContentHolder holder = null;
		 WTCollection wtcollection = null;
		 EPMSepFamilyTable epmsepfamilytable = null;
		 EPMFamilyTable epmfamilytable = null;
		 java.util.Map.Entry entry = null;
		 Set set = null;
		 Iterator iterator = null;
		 ApplicationData applicationdata = null;
		 QueryResult queryResultPrimary = null;
		 HashMap hashMap = null;
		 
		 while(tokens.hasMoreTokens()){
			 oid = tokens.nextToken();
			 contentHolder = (ContentHolder) new ReferenceFactory().getReference(oid).getObject();
			 holder = ContentHelper.service.getContents(contentHolder);
			 
		    if (contentHolder instanceof EPMDocument) {
		      EPMDocument epmdocument = (EPMDocument) contentHolder;
		      Vector vector = new Vector();
		      vector.addElement(epmdocument);
		      WTKeyedMap wtkeyedmap = EPMFamilyTableHelper.manager.getCompatibleFamilyTables(vector, epmdocument.getContainer());
		      if (wtkeyedmap != null && !wtkeyedmap.isEmpty()) {
		        set = wtkeyedmap.entrySet();
		        iterator = set.iterator();
		        entry = (java.util.Map.Entry) iterator.next();

		        CompatibleFamilyTables compatiblefamilytables = (CompatibleFamilyTables) entry.getValue();
		        if (compatiblefamilytables != null && !compatiblefamilytables.getCompatibleFamilyTables().isEmpty()) {
		          wtcollection = compatiblefamilytables.getCompatibleFamilyTables();
		          epmsepfamilytable = EPMFamilyTableHelper.getLatestFamilyTable(wtcollection);
		          epmfamilytable = (EPMFamilyTable) epmsepfamilytable;
		          holder = ContentHelper.service.getContents(epmfamilytable);
		        }
		      }
		    }
		    //-------------------------------------
		   
		    queryResultPrimary = ContentHelper.service.getContentsByRole(holder, ContentRoleType.PRIMARY);
		    if (queryResultPrimary.hasMoreElements()) { // There must be only one primary content
		      applicationdata = ((ApplicationData) queryResultPrimary.nextElement());
		    }
		    String contentName = "";
		    if (contentHolder instanceof EPMDocument) {
		      contentName = ((EPMDocument) contentHolder).getCADName();
		    } else {
		      contentName = applicationdata.getFileName();
		    }

		    hashMap = new HashMap();
		    hashMap.put("contentholder", contentHolder);
			hashMap.put("mimetype", ((DataFormat)applicationdata.getFormat().getObject()).getMimeType());
			hashMap.put("filename", contentName);
			String contentString = (String) RemoteMethodServer.getDefault().invoke("getContent", IntralinkUtility.class.getName(), null, new Class[]{ApplicationData.class}, new Object[]{applicationdata});
			hashMap.put("conentstring", contentString);	
			contentVector.addElement(hashMap);
		 }
		 
		 return contentVector;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------

	public static void copy( final InputStream input, final OutputStream output ) throws IOException{
		final byte[] buffer = new byte[ 8024 ];
		int n = 0;
		while( -1 != ( n = input.read( buffer ) ) ){
			output.write( buffer, 0, n );
		}
	}
	// -----------------------------------------------------------------------------------------------------------------------------------
	public static String getContent(ApplicationData applicationdata) throws Exception{
		Streamed streamdata = (Streamed)applicationdata.getStreamData().getObject();
		InputStream is = streamdata.retrieveStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();		
		copy(is, baos);
		return Base64.encodeBytes(baos.toByteArray());
	}
	
	
	// -----------------------------------------------------------------------------------------------------------------------------------
	public void streamContent(String contentstring, HttpServletResponse response) throws Exception{
		byte[] decoded = Base64.decode( contentstring );
		BufferedOutputStream bufferedoutputstream = new BufferedOutputStream( response.getOutputStream());
		bufferedoutputstream.write(decoded);
		bufferedoutputstream.flush();
		bufferedoutputstream.close();	   
	}	
	// -----------------------------------------------------------------------------------------------------------------------------------
	public void streamZip(Vector applicationVector, HttpServletResponse response) throws Exception{
		OutputStream oss = response.getOutputStream();
		JarOutputStream cpJarOutputStream = new JarOutputStream(oss);
		cpJarOutputStream.setLevel(9);
		
		HashMap hashMap = null;
		String contentname = null;
		String conentstring = null;
		ZipEntry cpZipEntry = null;
		for(int i=0; i < applicationVector.size(); i++){
			hashMap = (HashMap)applicationVector.elementAt(i);
			contentname = (String)hashMap.get("filename");		
			conentstring = (String)hashMap.get("conentstring");
			byte[] decoded = Base64.decode( conentstring );
			cpZipEntry = new ZipEntry(contentname);
			cpJarOutputStream.putNextEntry(cpZipEntry);
			cpJarOutputStream.write(decoded);
			cpJarOutputStream.closeEntry();	
		}

		try { 
			cpJarOutputStream.finish();
			cpJarOutputStream.close();
		} catch(Exception e){ System.out.println("Empty zip file");}
		oss.flush();
		oss.close();		
	}
 // -----------------------------------------------------------------------------------------------------------------------------------
	
  public static void main(String args[]) throws Exception {
    RemoteMethodServer.getDefault().setUserName("wcadmin");
    RemoteMethodServer.getDefault().setPassword("wcadmin");
    String name = args[0];// "0000000021.asm";
    String className = args[1];
    Vector vector = new IntralinkUtility().getApplicationData(null, name, null, className);
    HashMap hashMap = (HashMap) vector.elementAt(0);
    System.out.println(hashMap.get("conentstring"));
  }

  // -----------------------------------------------------------------------------------------------------------------------------------
  public WTObject getObject(Class className, String number, String name, String version) throws Exception {

    OwnershipIndependentLatestConfigSpec lcSpec = new OwnershipIndependentLatestConfigSpec();
    QuerySpec querySpec = new QuerySpec(className);
    if (number != null && !number.equalsIgnoreCase("")) {
      querySpec.appendSearchCondition(new SearchCondition(className, "master>number", "=", number, false));
    } else {
      querySpec.appendSearchCondition(new SearchCondition(className, "master>name", "=", name, false));
    }
    querySpec.appendAnd();
    querySpec.appendSearchCondition(new SearchCondition(className, "iterationInfo.latest", "TRUE"));

    if (version != null && !version.equalsIgnoreCase("")) {
      querySpec.appendAnd();
      querySpec.appendSearchCondition(new SearchCondition(className, "versionInfo.identifier.versionId", "=", version, false));
    }
    QueryResult queryResult = PersistenceHelper.manager.find(querySpec);

    if (version == null) {
      queryResult = lcSpec.process(queryResult);
    }

    while (queryResult.hasMoreElements()) {
    	return (WTObject) queryResult.nextElement();
    }
    return null;
  }

  // -----------------------------------------------------------------------------------------------------------------------------------
  public static String getObidFromUfid(String ufid) {
    if (ufid == null) return null;
    String obid = ufid;
    final String DELIM = ":";
    int index = ufid.indexOf(DELIM);
    if (index != -1) {
      int endIdx = ufid.lastIndexOf(DELIM);
      int middleIdx = ufid.indexOf(DELIM, index + 1);
      if ((endIdx == middleIdx) || (endIdx == index)) {
        return ufid;
      } else {
        obid = ufid.substring(0, endIdx);
      }
    }
    return obid;
  }

  // -----------------------------------------------------------------------------------------------------------------------------------
  public static String getPropertyValue(String name, String configurationFile) {
    ResourceBundle configuration = ResourceBundle.getBundle(configurationFile);
    return (String) configuration.getObject(name);
  }

}
