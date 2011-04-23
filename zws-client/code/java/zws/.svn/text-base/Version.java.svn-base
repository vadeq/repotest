package zws;

import java.io.*;

public class Version {

	private final String versionFile = "version.txt";
	
	public String getVersion() {
		
		InputStream in = ClassLoader.getSystemResourceAsStream(versionFile);
		StringBuilder info = new StringBuilder();
		int chr;
		
		try {
			
			if (in != null)
				while((chr = in.read()) != -1) 
					info.append( (char) chr );	
			
		} catch (Exception e){
			// do nothing
		} finally {
			try { 
				in.close(); 
			} catch (Exception e) { /* do nothing */ }
		}

		return info.toString();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Version().getVersion());
	}

}
