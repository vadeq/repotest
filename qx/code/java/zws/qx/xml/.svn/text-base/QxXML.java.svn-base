package zws.qx.xml;/*
                     * DesignState - Design Compression Technology. @author:
                     * Arbind Thakur @version: 1.0 Copywrite (c) 2003 Zero
                     * Wait-State Inc. All rights reserved
                     */

import zws.qx.program.QxInstruction;
import zws.util.FileUtil;
//impoer zws.util.Logwriter;

import java.io.File;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class QxXML implements Serializable {

  private static final long serialVersionUID = -6029704484529250128L;

  public QxXML() {};

  public QxXML(QxInstruction programTree) {xml.append(programTree.toString()); }

  public QxXML(String s) {
    xml.append(s);
  }

  public String getString() {
    return toString();
  }

  public void setString(String s) {
    xml = new StringBuffer(s);
  }

  public String toString() {
    return xml.toString();
  }

  public void loadFile(String path) throws Exception {
    loadFile(new File(path));
  }

  public void loadFile(File input) throws Exception {
    xml.append(FileUtil.read(input));
  }

  public QxXML write(String s) {
    xml.append(s);
    return this;
  }

  public QxInstruction toQxProgram() {
    // properties -> attributes
    // children -> subInstruction (QxInstruction)
    QxInstruction qxProgramTree = new QxInstruction();

    QxProgramHandler qxPrgHandler = new QxProgramHandler();
    try {

      XMLReader xr = XMLReaderFactory.createXMLReader();
      xr.setContentHandler(qxPrgHandler);
      StringReader reader = new StringReader(getString());
      InputSource source = new InputSource(reader);
      xr.parse(source);

      qxProgramTree = qxPrgHandler.getProgramTree();

    } catch (Exception ex) {
      ex.printStackTrace();
      {}//Logwriter.printOnConsole("Exception message in QxXML.toQxProgram: " + ex.getMessage());
      {}//Logwriter.printOnConsole(getString());
    }

    return qxProgramTree;
  }

  private StringBuffer xml = new StringBuffer();
}

class QxProgramHandler extends DefaultHandler {
 
  // This method is called when an element is encountered
  public void startElement(String namespaceURI, String localName, String qName,
      Attributes atts) {
    
    QxInstruction currInstrElem = new QxInstruction(qName);
    
    // Get the number of attribute
    int length = atts.getLength();

    // Process each attribute
    for (int i = 0; i < length; i++) {
      // Get names and values for each attribute
      String name = atts.getQName(i);
      String value = atts.getValue(i);
      currInstrElem.set(name, value);
    }
    stack.push(currInstrElem);
  }

  
  public void endElement(String namespaceURI, String localName, String qName ) {
    // This method is called when an end element is encountered - pop and add
    QxInstruction popedInstrElement = (QxInstruction)stack.pop();
    
    if (!stack.empty()) 
    {
      ((QxInstruction)stack.peek()).add(popedInstrElement);
    }
    
    else programTree = popedInstrElement;
  }
  
  public QxInstruction getProgramTree(){
    return programTree;  
  }

  QxInstruction programTree=null;
  Stack stack=new Stack();
}
