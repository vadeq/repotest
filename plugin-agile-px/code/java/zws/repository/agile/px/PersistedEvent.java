package zws.repository.agile.px;

/*
	DesignState - Design Compression Technology.
	@author: Rodney McCabe
	Created on Mar 13, 2008
	@version: 1.0
	Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved
*/

import java.io.*;
import java.util.*;

public class PersistedEvent {

	String filename;

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public PersistedEvent() {
	}

	public PersistedEvent(String filename) {
		setFilename(filename);
	}

	public void removeFile() {

		try {
			File file = new File(filename);
			if (file.exists()) file.delete();

		} catch (Exception e) {
			System.out.println("Error removing file: " + e.getMessage());
		}

	}

	public List getItems() {

		List list = new ArrayList();
		Object object;
		FileInputStream fileStream = null;
		ObjectInputStream stream = null;

		try {

			File file = new File(filename);

			if (file.exists()) {

				fileStream = new FileInputStream(file);
				stream = new ObjectInputStream(fileStream);
				list = (List) stream.readObject();
			}

		} catch (Exception e) {
				System.out.println("Error retrieving file: " + e.getMessage());
		} finally {

			try {
				if (stream != null) stream.close();
			} catch (Exception f) {
				System.out.println("Error closing output stream: " + f.getMessage());
			}

		}

    return list;

	}

	public void persistItems(List items) {

		FileOutputStream file = null;
		ObjectOutputStream stream = null;

		try {

			file = new FileOutputStream(filename);
			stream = new ObjectOutputStream(file);
			stream.writeObject( (List) items);

		} catch (Exception e) {
			System.out.println("Error persisting notifications: " + e.getMessage());
		} finally {

			try {
				if (stream != null) stream.close();
			} catch (Exception f) {
				System.out.println("Error closing output stream: " + f.getMessage());
			}

		}
	}
}