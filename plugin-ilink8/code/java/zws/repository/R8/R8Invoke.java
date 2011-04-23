/**
 * Developed by Swasen Inc
 */
package zws.repository.R8;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
/**
 * @author Senthil Swamy
 *
 */
public class R8Invoke {
	public static String invokeMethod(HashMap originHM, HashMap dataHM) throws Exception {
		{} //System.out.println(originHM);
		String result = null;
		String r8URL = (String)originHM.get("R8_WBSERVICE_URL");
		String r8User = (String)originHM.get("R8_USERNAME");
		String r8Passwd = (String)originHM.get("R8_PASSWORD");
		String r8Operation = (String)originHM.get("R8_OPERATION");
		Call call = (Call) new Service().createCall();
		call.setTargetEndpointAddress( r8URL );		
		call.setUsername( r8User );
		call.setPassword( r8Passwd );
		call.setOperationName(new QName("http://www.swasen.com", r8Operation));
		
		Object objArray[] = new Object[dataHM.size()];
		int count = 0;
		Iterator iterator = dataHM.keySet().iterator();
		while(iterator.hasNext()) {
		      String param 		= (String) iterator.next();
		      String paramValue = (String) dataHM.get(param);
		      {} //System.out.println("Name " + param + " value " + paramValue);
		      call.addParameter(param, XMLType.XSD_STRING, ParameterMode.IN);
		      objArray[count++] = paramValue;
		}
		call.setReturnType( XMLType.XSD_STRING );
		try {
			result = (String) call.invoke( objArray );
		} catch (Exception e) {
			System.out.println("Error in invoking the R8 method with the following details.");
			System.out.println("r8URL " + r8URL);
			System.out.println("r8User " + r8User);
			System.out.println("r8Operation " + r8Operation);
			throw e;
		}
		return result;
	}
}
