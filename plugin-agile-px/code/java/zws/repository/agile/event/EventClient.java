package zws.repository.agile.event;

/*
DesignState - Design Compression Technology.
@author: Arbind Thakur, Jason Brown @agile.com
Created on Mar 3, 2006
@version: 1.0
Copywrite (c) 2006 Zero Wait-State Inc. All rights reserved */

public interface EventClient {
	public boolean fireEvent(String msg) throws Exception;
}