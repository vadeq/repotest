/*
 * Created on Nov 11, 2003
 * @author jyelizarov
 */
package zws.action.intralink;


import zws.datasource.intralink.IntralinkSource;
//impoer zws.util.{}//Logwriter;

public class Modelcheck extends IntralinkAction {

	public void execute() throws Exception {
	    //---obsolete!!
		{}//Logwriter.printOnConsole("MODELCHECK: getting datasource " + getDatasourceName());
		IntralinkSource ds = (IntralinkSource)getDatasource();
		{}//Logwriter.printOnConsole("MODELCHECK: got datasource " + ds);
		try{
//			Metadata m = getMetadata();
//			if(m == null)
//				m = (Metadata)getContext().get(Names.CTX_METADATA);
//			setMetadata(m);
			{}//Logwriter.printOnConsole("MODELCHECK: report metadata " + grabMetadata());
			{}//Logwriter.printOnConsole("MODELCHECK: metadata name" + grabMetadata().getName());
			
			//ds.modelcheck(grabOrigin(), getInputPath(), getOutputDir(), null);
			
		}catch(Exception ex){
				  ex.printStackTrace();
		}

	}
	
	public void setOutputDir(String s) { outputDir = s; }
    public String getOutputDir() { return outputDir; }
	public String getInputPath() { return inputPath; }
	public void setInputPath(String s) { inputPath = s; }
    
    private String outputDir;
    private String inputPath;
     
}
