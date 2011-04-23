/*
 * Created on Sep 25, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor.processor.action.file;

import com.zws.application.Constants;
import com.zws.application.Properties;
import com.zws.domo.document.Document;
import com.zws.functor.pdfstamp.PdfStamp;
import com.zws.functor.processor.action.Action;

import java.io.File;

public class StampPdf extends Action {
	public void execute () throws Exception {
		Exception ex=null;
		PdfStamp action = new PdfStamp();
		
		Document doc = this.getDocument();
		
		String docname = doc.get("ds-binary-filename");
		setName(docname);
		
		action.setStampColor("blue");
		
		
		action.setStamptext(getProperty("stamp"));
		action.setX(getProperty("originX"));
		action.setY(getProperty("originY"));
		
		
		action.setFontSize(Properties.get("pdfstamp-font-size"));
		
		action.setInputFile(getOutputPath() + Constants.FILE_SEPARATOR + "raw" + Constants.FILE_SEPARATOR + getName());
		
		/*String newName = "";
		StringTokenizer st = new StringTokenizer(docname, ".");
		int count = st.countTokens();
		for(int i = 0; i < count - 1; i++)
		{
			newName = newName + st.nextToken() + ".";	
		}
		if(newName.length() < 1)
			newName = docname;
		newName += "stamped.pdf";*/
		String tmpPath = getOutputPath();
		File tmpDir = new File(tmpPath);
		//tmpDir.mkdir();
		
		action.setOutputFile(tmpDir.getPath() + Constants.FILE_SEPARATOR + docname);
		//action.setOutputFile(getOutputPath() + Constants.FILE_SEPARATOR + docname + ".pdf");
		
		//String inputFile = getActionLog().getProperty(postscriptFileName);
		//action.setInputFileName(inputFile);
		//File f = new File(getOutputPath());
		//if (!f.exists() || !f.isDirectory()) throw new FileNotFoundException("Output directory does not exist: " + getOutputPath());
		//action.setOutputFileName(getOutputPath()+Constants.FILE_SEPARATOR+FileNameUtil.getBaseName(action.getInputFileName())+".pdf");
		try {
		  action.execute();
		  //getActionLog().log("ok: stamped "+getName()+" to PDF: "+getOutputPath());
		  //getActionLog().setProperty(pdfFileName, action.getOutputFileName());
		  if (getDeleteInput()) {
			File in = new File(action.getInputFile());
			in.delete();
			//getActionLog().removeProperty(postscriptFileName);
		  }
		}
		catch(Exception e) {
		  //getActionLog().log("Failed: converting "+inputFile+" to PDF: "+getOutputPath());
		  ex = e;
		}
		if (null!=ex) throw ex;
	  }

	  //todo: add inputFileNameLogProperty so input filename is looked up from the log property
	  
	  
	  public String getName() { return name; }
	  public void setName(String s) { name=s; }
	  public String getNameMetadata() { return nameMetadata; }
	  public void setNameMetadata(String s) { nameMetadata=s; }
	  public String getStampMetadata() { return stampMetadata; }
	  public void setStampMetadata(String s) { stampMetadata=s; }
      public String getStamp() { return stamp; }
	  public void setStamp(String s) { stamp=s; }
	  
	  public String getInputPath() { return inputPath; }
	  public void setInputPath(String s) { inputPath=s; }
	  public String getOutputPath() { return outputPath; }
	  public void setOutputPath(String s) { outputPath=s; }
	  
	  public String getStampColor() { return stampColor; }
	  public void setStampColor(String s) { stampColor=s; }
	  public String getOriginX() { return originX; }
	  public void setOriginX(String s) { originX=s; }
	  public String getOriginY() { return originY; }
	  public void setOriginY(String s) { originY=s; }
	  
	  public boolean getDeleteInput() { return deleteInput; }
	  public void setDeleteInput(boolean b) { deleteInput=b; }

      private String originX=null;
      private String originY=null;
      private String stampColor = "blue";
	  private String name=null;
	  private String stamp=null;
	  private String stampMetadata=null;
	  private String nameMetadata=null;
	  private String inputPath=null;
	  private String outputPath=null;
	  private boolean deleteInput=false;
}

/*
< pdfstamp instance-of="com.zws.functor.processor.action.file.PdfStamper" method="addAction">
	 <stamp-metadata required="true"/>
	 <name-metadata required="true"/>
	 <input-path required="true"/>
	 <output-path required="true"/>
	 <delete-input type="boolean" default="false"/>
  <pdfstamp/> 
  */





