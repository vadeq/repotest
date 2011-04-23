/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Oct 29, 2007 3:07:37 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.custom.harris;
import zws.application.Names;
import zws.data.Metadata;
import zws.data.MetadataSubComponent;
import zws.exception.NameNotFound;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.qx.QxContext;
import zws.recorder.util.RecorderUtil;
import zws.service.pen.PENDataElement;
import zws.service.pen.TxDataElement;
import zws.util.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class SetFindNumbers extends PENDataOpBase {
  
  public void execute() throws Exception {
    Metadata source = getPenData().lookupSrcMetaData(getCurrentItem());
    Metadata xfer = getPenData().lookupTxMetaData(getCurrentItem());
    String key = xfer.getName();
    key +="-Rev";
    key += source.get("rev");
    key += "." + getFileType();
    
    String directory = getBaseDirectory();
    
    if (directory==null || directory.length()<1 && getPropBaseDirectory()!=null) {
      directory = zws.application.Properties.get(getPropBaseDirectory());
      setBaseDirectory(directory);
    }

    File dir = new File(getBaseDirectory());
    File findNumberList = new File(dir, key);
    if (!findNumberList.exists()) {
      {} //System.out.println("Find-Number list not found: " + findNumberList.getAbsolutePath());
      return;
    }
    {} //System.out.println("Reading Find-Number list: " + findNumberList.getAbsolutePath());
    String findnumbers = FileUtil.read(findNumberList);
    {} //System.out.println(findnumbers);
    
    PENDataElement element = getPenData().lookupPENDataElement(getCurrentItem());
    TxDataElement txElement = element.getTxDataElement();
    Collection subcomponentList = txElement.getSubComponentRefNames();
    if (null==subcomponentList) return;
    Iterator i = subcomponentList.iterator();
    MetadataSubComponent subcomponent =null; 
    String sub=null;
    Map subcomponentMap = new HashMap();
    while (i.hasNext()) {
      sub = (String)i.next();
      subcomponent = txElement.getSubComponent(sub);
      subcomponentMap.put(subcomponentKey(subcomponent.getName()), sub);
    }
    BufferedReader in = new BufferedReader(new FileReader(findNumberList));
    String line = in.readLine(); //title row
    while (null!=line) {
      line = in.readLine();
      try {
        setFindNumber(line, subcomponentMap, txElement, getQxCtx());
      } catch (Exception e) {
        in.close();
        throw e;
      }
    }
    in.close();
  }

  private String subcomponentKey (String subcomponentName) {
    return subcomponentName.toLowerCase();
  }
  
  
  /**
   * Replace every consecutive commas (",,") with string ",nullval,"
   */
  private String normalizeFindNumberRow(String line) {
    String normalizedRow = line;
    int newlinesz = normalizedRow.length();
    int oldlinesz = newlinesz;
    newlinesz++;
    while (newlinesz > oldlinesz) {
      normalizedRow = normalizedRow.replaceAll(",,", ",nullval,"); 
      oldlinesz = newlinesz;
      newlinesz = normalizedRow.length();
    } 
    return normalizedRow;
  }
  
  private void setFindNumber(String line, Map subcomponentMap, TxDataElement txElement, QxContext ctx) throws Exception {
    if (null==line) return;
    
    String normalizedRow = normalizeFindNumberRow(line);
    StringTokenizer tokens = new StringTokenizer(normalizedRow, ",");
    String findNumber = tokens.nextToken().trim(); //get find number
    tokens.nextToken().trim(); //skip quantity
    tokens.nextToken().trim(); //skip unit of measure
    tokens.nextToken().trim(); //skip cage code
    String subcomponentName = tokens.nextToken().trim(); //get sub component part number
    String sub = (String)subcomponentMap.get(subcomponentKey(subcomponentName));
    
    if (null==sub) return;
    
    try {
      QxContext bomAttributes = txElement.getBOMAttributes(sub);
      bomAttributes.set(Names.FIND_NUMBER, findNumber);
      RecorderUtil.logActivity(ctx, "setting find number", findNumber+"(" +subcomponentName + ")");
    }
    catch(Exception ignore) {
      throw new NameNotFound("sub");
    }
  }

  public String getBaseDirectory() { return baseDirectory; }
  public void setBaseDirectory(String s) { baseDirectory = s; }

  public String getPropBaseDirectory()           { return property; }
  public void setPropBaseDirectory(String value) { property = value; }

  public String getFileType() { return fileType; }
  public void setFileType(String s) { fileType = s;  if (null!=fileType) fileType=fileType.trim(); }

  private String baseDirectory=null;
  private String fileType="csv";
  private String property=null;
}
