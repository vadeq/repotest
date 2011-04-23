package zws.event;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 10:34 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.util.RoutedEvent;

public interface ReleaseEvent extends RoutedEvent {
	String getOldReleaseLevel(Metadata data);
	String getNewReleaseLevel(Metadata data);
	String getAuthor();
}
