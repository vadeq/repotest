package zws.service.pen;

import zws.data.Metadata;
import zws.util.RemotePackage;

/*
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on May 25, 2007 12:09:48 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import java.util.ArrayList;

/**
 * The Class SourceDataElement.
 *
 * @author ptoleti
 */
public class SourceDataElement {
  public SourceDataElement(PENDataElement e) { penDataElement = e; }

  public Metadata getSourceData() { return sourceData; }
  public void setSourceData(Metadata srcData) { sourceData = srcData; }

  public void addBinary(RemotePackage binary) { binaryFiles.add(binary); }
  public ArrayList getBinaryCollection() { return binaryFiles; }

  public PENData getPENData() { return penDataElement.getPENData(); }
  public PENDataElement getPenDataElement() { return penDataElement; }

  private Metadata sourceData = null;
  private ArrayList binaryFiles = new ArrayList();

  private PENDataElement penDataElement = null;
}
