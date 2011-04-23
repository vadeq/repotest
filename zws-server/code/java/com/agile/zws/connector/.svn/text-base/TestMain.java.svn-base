package com.agile.zws.connector;

//import zws.util.{}//Logwriter;

import java.util.Vector;

public class TestMain {
	
	
	public static void main(String[] args) 
	{
	
		AgileConnector ac = new AgileConnector("test");
		boolean connected = ac.init();
		{}//Logwriter.printOnConsole("Connected : " + connected);
				if ( !connected )
					System.exit(-1);
		//ac.tester();
		//ac.attachmentTester();
		ac.testAttachments();
		ac.disconnect();
		System.exit(0);
	}   
	
	
}