/**
 * Developed by Swasen Inc
 */
package zws.repository.R8;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Security;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @author Senthil Swaminathan
 *
 */
public class R8ILRetrieveContentAgent {

	/**
	 * @param args
	 */
	public static void main(String args[]) throws Exception {
		String number = "axle_fastener.prt";
		String version = "A";
		String className = "wt.epm.EPMDocument";

		String url = "http://ilinkuser-training.ptc.com/ilinkuser/netmarkets/jsp/ssi/intralink/downloadpdmcontent.jsp";
		String username = "mmanager";
		String password = "mmanager";

		downloadContent(url, number, version, className, username, password);
	}
	//-----------------------------------------------------------------------------------------------------------------------------------	
	public static void downloadContent(String remoteURL, String number, String version, String className, String username, String password) throws Exception{
		
		PostMethod filePost = new PostMethod(remoteURL);				

		NameValuePair numberPair = new NameValuePair("number", number);
		NameValuePair versionPair = new NameValuePair("version", version);
		NameValuePair classPair = new NameValuePair("className", className);
		filePost.setQueryString(  new NameValuePair[] {numberPair, versionPair, classPair});			
	
        HttpClient httpclient = new HttpClient();
		System.getProperties().put("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		
        httpclient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        
        try {
            int result = httpclient.executeMethod(filePost);
            InputStream inputStream = filePost.getResponseBodyAsStream(); 
            String disposition = filePost.getResponseHeader("Content-Disposition").getValue();
            
            int startIndex = disposition.lastIndexOf("=");
            int endIndex = disposition.lastIndexOf("\"");
            String fileName = disposition.substring(startIndex + 2, endIndex);
            
			BufferedInputStream bufferedinputstream = new BufferedInputStream(inputStream);				
			BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(
					new FileOutputStream("c:/temp/R8if" + "/" + fileName));

			byte abyte0[] = new byte[8192];
			int i;
			while((i = bufferedinputstream.read(abyte0)) > 0){ bufferedoutputstream.write(abyte0, 0, i); }
			bufferedoutputstream.flush();
			bufferedoutputstream.close();
			bufferedinputstream.close();

        } finally {
        	filePost.releaseConnection();
        }		
	}
}
	
