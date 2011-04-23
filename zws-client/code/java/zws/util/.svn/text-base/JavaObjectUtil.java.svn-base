package zws.util; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Sep 17, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import java.io.*;

public class JavaObjectUtil {

	public static Object copy(Object o) throws Exception {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(o);
		byte buf[] = baos.toByteArray();
		oos.close();

  	// deserialize byte array into ArrayList
		ByteArrayInputStream bais = new ByteArrayInputStream(buf);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Object k = ois.readObject();
		ois.close();
		return k;
	}
    
}
