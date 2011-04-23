package zws.xml.xslt;/*
import zws.op.OpBase;
@version 1.0
@author athakur
DesignState - Design Compression Technology: May 9, 2004.
Copywrite (c) 2004 Zero Wait-State Inc. All rights reserved world-wide */
import zws.op.OpBase;

import java.io.*;
import java.util.Stack;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Stylizer extends OpBase {
  
  public void execute() throws Exception { setResult(styleXML(sourceXML)); }
  
  public ByteArrayOutputStream styleXML(String xmlPath) throws Exception {
    setSource(xmlPath);
    return styleXML(sourceXML);
  }
  public ByteArrayOutputStream styleXML(InputStream xmlSource) throws Exception {
    setSource(xmlSource);
    return styleXML(sourceXML);
  }
  private ByteArrayOutputStream styleXML(StreamSource source) throws Exception {
    TransformerFactory tFactory = TransformerFactory.newInstance();
    Transformer transformer = tFactory.newTransformer((StreamSource)stylesheets.pop());
    ByteArrayOutputStream oStream = new ByteArrayOutputStream();
    StreamResult result = new StreamResult(oStream);
    transformer.transform (source, result);
    if (0<stylesheets.size()) {
      ByteArrayInputStream nextSource = new ByteArrayInputStream(oStream.toByteArray());
      result.getOutputStream().close();
      {} //System.out.println("----------------");
      {} //System.out.println(result.getOutputStream().toString());
      return styleXML(new StreamSource(nextSource));
    }
    return oStream;
 }

 public void setSource(String xmlPath) { sourceXML = new StreamSource(xmlPath); }
 public void setSource(InputStream xmlSource) { sourceXML = new StreamSource(xmlSource); }
 
 public void addProcessingInstruction(InputStream styleSheetSource) { addProcessingInstruction(new StreamSource(styleSheetSource)); }
 public void addProcessingInstruction(String styleSheetPath) { addProcessingInstruction(new StreamSource(styleSheetPath)); }
 private void addProcessingInstruction(StreamSource styleSheetSource) { stylesheets.add(0,styleSheetSource); }

  private StreamSource sourceXML;
  private Stack stylesheets = new Stack();
}
